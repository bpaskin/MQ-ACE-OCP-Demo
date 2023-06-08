package com.ibm.example.rest;

import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ibm.example.messages.Outgoing;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
@Path("services")
public class Services {

	private final static SecureRandom random = new SecureRandom();
	
	@Inject
	Logger LOG;
	
	@POST @GET
	@Path("authenticate/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Outgoing authenticate(@PathParam("name") String name) {
		LOG.fine(">> entering authenticate");
		Outgoing out = new Outgoing();

		if (random.nextInt(100) < 5 ) {
			LOG.fine(" >> exiting authenticate false");
			throw new RuntimeException("Authentication Failed");
		} else {
			LOG.fine(" >> exiting authenticate true");
			out.setAuhhorized(true);
			out.setMessage("Authentication Successful");
			return out;
		}
	}
}
