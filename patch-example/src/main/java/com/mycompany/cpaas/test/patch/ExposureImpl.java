package com.mycompany.cpaas.test.patch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExposureImpl implements Exposure {

	public static final Logger LOG = LoggerFactory.getLogger(ExposureImpl.class);
	
	@Override
	public EchoResponse echoTenant(EchoRequest request) {
		
		EchoResponse response = new EchoResponse();
		response.setMessage(request.getMessage());
		
		return response;
	}


}
