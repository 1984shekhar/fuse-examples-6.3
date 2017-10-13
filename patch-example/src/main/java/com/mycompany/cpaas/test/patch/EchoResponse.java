package com.mycompany.cpaas.test.patch;

import java.io.Serializable;


public class EchoResponse implements Serializable {

	private static final long serialVersionUID = -4406130217799475502L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "EchoResponse [message=" + message + "]";
	}
	
}
