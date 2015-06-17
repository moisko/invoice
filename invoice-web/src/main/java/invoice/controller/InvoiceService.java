package invoice.controller;

import invoice.db.InvoiceDAO;
import invoice.model.Invoice;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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

@Produces(MediaType.APPLICATION_JSON)
public class InvoiceService extends InvoiceBaseService {

	@Context
	ServletContext context;

	@GET
	@Path("/get/{id}")
	public Response getInvoice(@PathParam("id") Long id) {
		EntityManagerFactory emf = (EntityManagerFactory) context
				.getAttribute("emf");
		InvoiceDAO invoiceDAO = new InvoiceDAO(emf);
		try {
			Invoice invoice = invoiceDAO.getInvoice(id);
			return buildResponse(Response.Status.OK.getStatusCode(), invoice);
		} catch (NoResultException e) {
			return buildResponse(new InvoiceResponse(
					Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()));
		}
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createInvoice(Invoice invoice) {
		EntityManagerFactory emf = (EntityManagerFactory) context
				.getAttribute("emf");
		InvoiceDAO invoiceDAO = new InvoiceDAO(emf);
		try {
			invoiceDAO.createInvoice(invoice);
		} catch (RollbackException e) {
			return buildResponse(new InvoiceResponse(
					Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()));
		}
		return buildResponse(Response.Status.OK.getStatusCode(), invoice);
	}

	@DELETE
	@Path("/delete/{id}")
	public Response deleteInvoice(@PathParam("id") Long id) {
		EntityManagerFactory emf = (EntityManagerFactory) context
				.getAttribute("emf");
		InvoiceDAO invoiceDAO = new InvoiceDAO(emf);
		try {
			invoiceDAO.deleteInvoice(id);
			return buildResponse(new InvoiceResponse(
					Response.Status.OK.getStatusCode(),
					"Invoice successfully deleted"));
		} catch (IllegalArgumentException e) {
			return buildResponse(new InvoiceResponse(
					Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()));
		}
	}
}
