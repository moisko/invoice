package invoice.controller;

import javax.ws.rs.core.Response;

public class InvoiceBaseService {

	protected Response buildResponse(int status, Object message) {
		Response response = Response.status(status).entity(message).build();
		return response;
	}

	protected Response buildResponse(InvoiceResponse invoiceResponse) {
		Response response = Response.status(invoiceResponse.getStatus())
				.entity(invoiceResponse).build();
		return response;
	}

}
