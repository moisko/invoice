package invoice.exception;

import invoice.controller.InvoiceResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.google.gson.JsonParseException;

public class JsonParseExceptionMapper implements
		ExceptionMapper<JsonParseException> {

	@Override
	public Response toResponse(JsonParseException e) {
		return Response
				.status(Response.Status.BAD_REQUEST)
				.entity(new InvoiceResponse(Response.Status.BAD_REQUEST
						.getStatusCode(), e.getMessage())).build();
	}

}
