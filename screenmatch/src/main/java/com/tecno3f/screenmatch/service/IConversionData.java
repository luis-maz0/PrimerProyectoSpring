package com.tecno3f.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConversionData {
    <T> T obtenerDatos(String json, Class<T> clase) throws JsonProcessingException;
}
