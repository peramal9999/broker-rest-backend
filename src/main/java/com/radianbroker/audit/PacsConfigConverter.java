package com.radianbroker.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radianbroker.entity.PacsConfig;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;

public class PacsConfigConverter implements AttributeConverter<PacsConfig, String> {

	private final ObjectMapper objectMapper = new ObjectMapper();
	  
    @Override
    public String convertToDatabaseColumn(PacsConfig pacsConfig) {

        String pacsConfigJson = null;
        try {
        	pacsConfigJson = objectMapper.writeValueAsString(pacsConfig);
        } catch (final JsonProcessingException e) {
            System.out.println("JSON writing error");
            e.printStackTrace();
        }

        return pacsConfigJson;
    }

    @Override
    public PacsConfig convertToEntityAttribute(String pacsConfigJSON) {
    	PacsConfig pacsConfig = null;
        try {
        	if(pacsConfigJSON !=null && !pacsConfigJSON.isEmpty()) {
        		pacsConfig = objectMapper.readValue(pacsConfigJSON, PacsConfig.class);
        	}
        } catch (final IOException e) {
            System.out.println("JSON reading error");
            e.printStackTrace();
        }
        return pacsConfig;
    }

}