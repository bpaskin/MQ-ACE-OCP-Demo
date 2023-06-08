package com.ibm.example.utils;

import com.ibm.example.messages.Outgoing;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionProvider implements ExceptionMapper<ValidationException> {

	@Override
	public Response toResponse(ValidationException e) {
		Outgoing out = new Outgoing();
		out.setMessage(e.getMessage());
		out.setAuhhorized(false);
		return Response.status(Status.SERVICE_UNAVAILABLE).entity(out).build();
	}
}
