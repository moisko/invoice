package invoice.controller;

import invoice.db.InvoiceDAO;
import invoice.model.Invoice;
import invoice.validator.InvoiceValidator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvoiceService extends InvoiceBaseService {

	@Context
	ServletContext context;

	@GET
	@Path("/get/{id}")
	public Response getInvoice(@PathParam("id") Long id) {
		EntityManagerFactory emf = (EntityManagerFactory) context
				.getAttribute("emf");
		InvoiceDAO invoiceDAO = new InvoiceDAO(emf);
		Invoice invoice = invoiceDAO.getInvoice(id);
		if (invoice != null) {
			return buildResponse(Response.Status.OK.getStatusCode(), invoice);
		} else {
			return buildResponse(Response.Status.NOT_FOUND.getStatusCode(),
					"Invoice not found");
		}
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createInvoice(Invoice invoice) {
		if (InvoiceValidator.validate(invoice)) {
			EntityManagerFactory emf = (EntityManagerFactory) context
					.getAttribute("emf");
			InvoiceDAO invoiceDAO = new InvoiceDAO(emf);
			try {
				invoiceDAO.createInvoice(invoice);
			} catch (Exception e) {
				if (e instanceof RollbackException) {
					return buildResponse(
							Response.Status.BAD_REQUEST.getStatusCode(),
							"Invoice request is not valid");
				} else {
					return buildResponse(
							Response.Status.INTERNAL_SERVER_ERROR
									.getStatusCode(),
							e.getMessage());
				}
			}
			return buildResponse(Response.Status.OK.getStatusCode(), invoice);
		} else {
			return buildResponse(Response.Status.BAD_REQUEST.getStatusCode(),
					"Invoice request is not valid");
		}
	}

	@DELETE
	@Path("/delete/{id}")
	public void deleteInvoice(@PathParam("id") Long id) {
		EntityManagerFactory emf = (EntityManagerFactory) context
				.getAttribute("emf");
		InvoiceDAO invoiceDAO = new InvoiceDAO(emf);
		invoiceDAO.deleteInvoice(id);
	}
}
