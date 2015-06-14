package invoice.controller;

import invoice.db.InvoiceDAO;
import invoice.model.Invoice;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInvoice(@PathParam("id") Long id) {
		EntityManagerFactory emf = (EntityManagerFactory) context
				.getAttribute("emf");
		InvoiceDAO invoiceDAO = new InvoiceDAO(emf);
		Invoice invoice = invoiceDAO.getInvoice(id);
		return buildResponse(invoice);
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createInvoice(Invoice invoice) {
		EntityManagerFactory emf = (EntityManagerFactory) context
				.getAttribute("emf");
		InvoiceDAO invoiceDAO = new InvoiceDAO(emf);
		invoiceDAO.createInvoice(invoice);
		return buildResponse(invoice.getId());
	}

}
