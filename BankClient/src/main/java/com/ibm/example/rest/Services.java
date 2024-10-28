package com.ibm.example.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ibm.example.jms.SendAndReceive;
import com.ibm.example.messages.Incoming;
import com.ibm.example.messages.Outgoing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("services")
public class Services {
	
    @Inject
    Logger LOG;
    
    @Inject 
    SendAndReceive process;
    
	@POST
	@Path("withdrawal")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Outgoing moneyWithdrawl(@Valid Incoming message) {
		LOG.log(Level.FINE, " >> entering moneyWithdrawl");
		
		LOG.log(Level.FINE, " >> send to process message");
		Outgoing out = process.process(message);
		LOG.log(Level.FINE, " >> after processing message");

		LOG.log(Level.FINE, " >> exiting moneyWithdrawl");
		return out;
	}
}
