package org.pstcl.ea.messaging;

public class Message {

	private String name;
		
	public Message() {}
	
	public Message(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
