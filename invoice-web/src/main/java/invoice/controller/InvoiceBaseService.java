package invoice.controller;

import javax.ws.rs.core.Response;

public class InvoiceBaseService {

	protected Response buildResponse(int status, Object message) {
		Response response = Response.status(status).entity(message).build();
		return response;
	}

	protected Response buildErrorResponse(InvoiceError invoiceError) {
		Response response = Response.status(invoiceError.getStatus())
				.entity(invoiceError).build();
		return response;
	}

}
