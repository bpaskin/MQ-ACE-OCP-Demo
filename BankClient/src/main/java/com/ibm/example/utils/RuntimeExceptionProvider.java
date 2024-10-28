package com.ibm.example.utils;

import com.ibm.example.messages.Outgoing;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionProvider implements ExceptionMapper<RuntimeException> {

	@Override
	public Response toResponse(RuntimeException e) {
		Outgoing out = new Outgoing();
		out.setMessage(e.getMessage());
		out.setAuhhorized(false);
		return Response.status(Status.SERVICE_UNAVAILABLE).entity(out).build();
	}

}
