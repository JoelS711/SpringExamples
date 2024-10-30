package com.joels.screenmatchv2.service;

public interface IConvertData {

	<T> T getData(String json, Class<T> clase);
}
