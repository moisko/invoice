package invoice.controller;

import invoice.model.Invoice;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvoiceBaseService {

	protected Response buildResponse(Invoice invoice) {
		Response response = null;
		if (invoice != null) {
			response = Response.ok(invoice, MediaType.APPLICATION_JSON).build();
		} else {
			response = Response.status(Response.Status.NOT_FOUND)
					.entity("Invoice not found").build();
		}
		return response;
	}

}
