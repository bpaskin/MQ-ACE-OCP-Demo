package com.ibm.example.utils;

import com.ibm.example.messages.Outgoing;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class RuntimeExceptionProvider implements ExceptionMapper<RuntimeException> {

	@Override
	public Response toResponse(RuntimeException e) {
		Outgoing out = new Outgoing();
		out.setMessage(e.getMessage());
		out.setAuhhorized(false);
		return Response.status(Status.UNAUTHORIZED).entity(out).build();
	}

}
