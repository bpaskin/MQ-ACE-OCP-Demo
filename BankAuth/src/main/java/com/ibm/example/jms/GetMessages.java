package com.ibm.example.jms;

import java.security.SecureRandom;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;

import com.ibm.example.messages.Incoming;
import com.ibm.example.messages.Outgoing;


@MessageDriven(name = "GetMessages")
public class GetMessages implements MessageListener {

	@Inject
	@JMSConnectionFactory("jms/Bank")
	private JMSContext context;
	
	@Resource(name = "jms/InQ")
	private Destination inq;
	
	@Resource(name = "jms/OutQ")
	private Destination outq;
	
	private Outgoing out = new Outgoing();
	
	private final static SecureRandom random = new SecureRandom();

	
	public void onMessage(Message message) {
		
		TextMessage msg;
		String corrId = "";
		
		try {
			corrId = message.getJMSCorrelationID();
			
			if (message instanceof TextMessage) {
				msg = (TextMessage) message;
			} else {
				out.setAuhhorized(false);
				out.setMessage("Message format is incorrect");
				sendResponse(out, corrId);
				return;
			}
			
			Jsonb jsonb = JsonbBuilder.create();
			Incoming in = jsonb.fromJson(msg.getText(), Incoming.class);
			
			if (random.nextInt(100) < 5 ) {
				out.setAuhhorized(false);
				out.setMessage("Authentication Failed");
			} else {
				out.setAuhhorized(true);
				out.setMessage("Authentication Successful");
			}
			
			sendResponse(out, corrId);
			
		} catch (JMSException | JsonbException e) {
			e.printStackTrace(System.err);
			out.setAuhhorized(false);
			out.setMessage(e.getMessage());
			sendResponse(out, corrId);
		}
	}
	
	private void sendResponse(Outgoing out, String corrId) {
		try {
			Jsonb jsonb = JsonbBuilder.create();
			String incomingJson = jsonb.toJson(out);
			System.out.print(incomingJson);
			
			Message message = context.createTextMessage(incomingJson);
			message.setJMSCorrelationID(corrId);
			context.createProducer().send(outq, message);
			
		} catch (JMSException | JsonbException e) {
			e.printStackTrace(System.err);
		}
	}
}
