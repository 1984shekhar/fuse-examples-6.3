package com.mycompany.cpaas.test.patch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class JsonProvider {


		private static final Logger LOGGER = LoggerFactory.getLogger(JsonProvider.class);
		
		private JsonProvider() {
			// TODO Auto-generated constructor stub
		}
		
		public static JacksonJsonProvider getProvider() {
			LOGGER.info("JacksonJsonProvider getProvider");
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);

			objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			
			JacksonJsonProvider provider = new JacksonJsonProvider();
			provider.setMapper(objectMapper);
			
			LOGGER.info("JacksonJsonProvider configured");
			return provider;
		}
		
		public static JacksonJsonProvider getProviderWithNullProperty() {
			LOGGER.info("JacksonJsonProvider getProvider");
			
			ObjectMapper objectMapper = new ObjectMapper();

			objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			
			JacksonJsonProvider provider = new JacksonJsonProvider();
			provider.setMapper(objectMapper);
			
			LOGGER.info("JacksonJsonProvider configured");
			return provider;
		}
}
