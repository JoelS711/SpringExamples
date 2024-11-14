package com.joels.books_gutendex.service;

public interface IConvertData {

	<T> T getData(String json, Class<T> clase);
}
