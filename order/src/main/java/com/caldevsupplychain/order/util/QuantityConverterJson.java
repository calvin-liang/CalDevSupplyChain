package com.caldevsupplychain.order.util;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import lombok.extern.slf4j.Slf4j;

import com.caldevsupplychain.order.model.Quantity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public class QuantityConverterJson implements AttributeConverter<Quantity, String> {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Quantity meta) {
		try {
			return objectMapper.writeValueAsString(meta);
		} catch (JsonProcessingException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	@Override
	public Quantity convertToEntityAttribute(String dbData) {
		try {
			return objectMapper.readValue(dbData, Quantity.class);
		} catch (IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

}
