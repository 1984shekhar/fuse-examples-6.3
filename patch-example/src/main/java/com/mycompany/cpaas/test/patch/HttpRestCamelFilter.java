package com.mycompany.cpaas.test.patch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRestCamelFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpRestCamelFilter.class);
	
	public void filter(Exchange messageExchange) {
		
		String headerProp = "Content-Type|Date|Transfer-Encoding|Server|Access-Control-Allow-Headers|Access-Control-Allow-Methods|Access-Control-Allow-Origin";
		
		LOGGER.info("  #### --> Headers to preserve are : [{}]", headerProp);
		
		if(headerProp != null) {
			
			String[] split = headerProp.split("\\|");
			if(split != null && split.length > 0) {
				
				List<String> headersToPreserve = Arrays.asList(split);
				
				Map<String, Object> headers = new HashMap<String, Object>(messageExchange.getIn().getHeaders());
				
				Iterator<Entry<String, Object>> iterator = headers.entrySet().iterator();
				
				while(iterator.hasNext()) {
					
					Entry<String, Object> headerEntry= iterator.next();
					String headerNameString = headerEntry.getKey();
					
					if(headersToPreserve.contains(headerNameString)) {
						LOGGER.trace("  #### --> Header name to preserve");
						LOGGER.trace("           - Name   : [{}]", headerNameString);
						LOGGER.trace("           - Value  : [{}]", messageExchange.getIn().getHeader(headerNameString));
					}
					else {
						messageExchange.getIn().removeHeader(headerNameString);
						LOGGER.trace("  #### --> Header name [{}] removed", headerNameString);
					}
				}
			}
		}
	}
}
