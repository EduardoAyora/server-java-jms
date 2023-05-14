package org.jboss.tools.examples.model;

public class MessageData {
	private String receiver;
	private String message;
	
	
	
	public MessageData(String message, String receiver) {
		super();
		this.receiver = receiver;
		this.message = message;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
