package com.joels.books_gutendex.service;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConvertData implements IConvertData {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public <T> T getData(String json, Class<T> clase) {
		try {
			return objectMapper.readValue(json, clase);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
