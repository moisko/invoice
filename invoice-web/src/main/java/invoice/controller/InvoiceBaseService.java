package invoice.controller;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvoiceBaseService {

	protected Response buildResponse(Object object) {
		Response response = null;
		if (object != null) {
			response = Response.ok(object, MediaType.APPLICATION_JSON).build();
		} else {
			response = Response.status(Response.Status.NOT_FOUND)
					.entity("Invoice not found").build();
		}
		return response;
	}

}
