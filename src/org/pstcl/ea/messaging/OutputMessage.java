package org.pstcl.ea.messaging;

public class OutputMessage {

	private String content;
	public OutputMessage() {}
	
	public OutputMessage(String content) {
		this.setContent(content);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	
}
