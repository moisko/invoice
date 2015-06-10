package invoice.controller;

import invoice.db.InvoiceDAO;
import invoice.model.Invoice;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvoiceService {

	@Context
	ServletContext context;

	@GET
	@Path("/getInvoice/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInvoice(@PathParam("id") Long id) {
		EntityManagerFactory emf = (EntityManagerFactory) context
				.getAttribute("emf");
		InvoiceDAO invoiceDAO = new InvoiceDAO(emf);
		Invoice invoice = invoiceDAO.getInvoice(id);
		return sendInvoice(invoice);
	}

	private Response sendInvoice(Invoice invoice) {
		Response response = null;
		if (invoice != null) {
			response = Response.status(HttpServletResponse.SC_OK)
					.entity(invoice).build();
		} else {
			response = Response.status(HttpServletResponse.SC_NOT_FOUND)
					.entity(invoice).build();
		}
		return response;
	}
}
