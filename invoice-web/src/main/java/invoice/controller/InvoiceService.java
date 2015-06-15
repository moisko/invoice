package invoice.controller;

import invoice.db.InvoiceDAO;
import invoice.model.Invoice;
import invoice.validator.InvoiceValidator;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
			invoiceDAO.createInvoice(invoice);
			return buildResponse(Response.Status.OK.getStatusCode(), invoice);
		} else {
			return buildResponse(Response.Status.BAD_REQUEST.getStatusCode(),
					"Invoice request is not valid");
		}
	}

	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInvoice(@PathParam("id") Long id) {// TODO discuss the
																// rest of the
																// update params
		// TODO provide implementation
		return null;
	}
}
