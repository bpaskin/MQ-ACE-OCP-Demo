package com.ibm.example.messages;

import java.io.Serializable;

public class Outgoing implements Serializable {

	private String message;
	private boolean auhhorized;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isAuhhorized() {
		return auhhorized;
	}
	public void setAuhhorized(boolean auhhorized) {
		this.auhhorized = auhhorized;
	}
}
