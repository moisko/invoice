package invoice.controller;

import java.io.Serializable;

public class InvoiceResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private int status;

	private String message;

	public InvoiceResponse() {

	}

	public InvoiceResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
