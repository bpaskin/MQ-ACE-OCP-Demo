package com.ibm.example.jms;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ibm.example.messages.Incoming;
import com.ibm.example.messages.Outgoing;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;

@RequestScoped
public class SendAndReceive {
	
    @Inject
    Logger LOG;

	@Resource(lookup = "jms/BankNet")
	private ConnectionFactory conn;
	
	@Resource(lookup = "jms/OutQ")
	private Destination outq;
	
	@Resource(lookup = "jms/InQ")
	private Destination inq;
	
	public Outgoing process(Incoming incoming) {
		LOG.log(Level.FINE, " >> entering process");
		
		Outgoing out = new Outgoing();
		
		// convert message from Object to Json before sending
		LOG.log(Level.FINE, " >> convert Object to JSON");
		Jsonb jsonb = JsonbBuilder.create();
		String incomingJson = jsonb.toJson(incoming);
		
		// try sending and receiving.  Expiration and Timeout set to 30 seconds
		LOG.log(Level.FINE, " >> Try to get connection to JMS");
		try (JMSContext context = conn.createContext();) {
			LOG.log(Level.FINE, " >> Create Message : " + incomingJson);
			Message message = context.createTextMessage(incomingJson);
			
			UUID uuid = UUID.fromString("9e763b6f-d168-4661-93db-99903eb9c865");
			ByteBuffer bb = ByteBuffer.allocate(16);
			bb.putLong(uuid.getMostSignificantBits());
			bb.putLong(uuid.getLeastSignificantBits());
			byte[] bytes = bb.array();
			String corrId = new String(Base64.getEncoder().encode(bytes));
			message.setJMSCorrelationID(corrId);
			
			LOG.log(Level.FINE, " >> Correlation ID : " + corrId);
			LOG.log(Level.FINE, " >> Send Message");
			context.createProducer().send(inq, message);
						
			// wait for response
			LOG.log(Level.FINE, " >> Create Consumer");
			JMSConsumer consumer = context.createConsumer(outq, "JMSCorrelationID='" + corrId + "'" );
			LOG.log(Level.FINE, " >> Wait for Message");
			Message response = consumer.receive(30000);
			if (response == null) {
				out.setAuhhorized(false);
				out.setMessage("Timeout");
				return out;
			}
			
			TextMessage textResponse;
			if (response instanceof TextMessage) {
				textResponse = (TextMessage) response;
			} else {
				out.setAuhhorized(false);
				out.setMessage("Invalid response type in response");
				return out;
			}
			
			LOG.log(Level.FINE, " >> Received message : " + textResponse.getText());

			// convert back to Object
			LOG.log(Level.FINE, " >> Convert back to Outgoing");
			out = jsonb.fromJson(textResponse.getText(), Outgoing.class);
			
		} catch (JMSException | JsonbException e) {
			e.printStackTrace(System.err);
			out.setAuhhorized(false);
			out.setMessage(e.getMessage());
			return out;
		}
		
		LOG.log(Level.FINE, " >> exiting process");
		return out;
	}
	
}
