package com.mycompany.cpaas.test.patch;

import java.io.Serializable;


public class EchoRequest implements Serializable {

	private static final long serialVersionUID = -4406130217799475502L;

	private String message;
	private String tenant;

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	@Override
	public String toString() {
		return "EchoRequest [message=" + message + ", tenant=" + tenant + "]";
	}
	
}
