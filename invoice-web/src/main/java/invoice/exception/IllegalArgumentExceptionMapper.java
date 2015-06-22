package invoice.exception;

import invoice.controller.InvoiceResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class IllegalArgumentExceptionMapper implements
		ExceptionMapper<IllegalArgumentException> {

	@Override
	public Response toResponse(IllegalArgumentException e) {
		return Response
				.status(Response.Status.NOT_FOUND)
				.entity(new InvoiceResponse(Response.Status.NOT_FOUND
						.getStatusCode(), e.getMessage())).build();
	}
}
