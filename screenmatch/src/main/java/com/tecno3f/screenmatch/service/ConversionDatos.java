package com.tecno3f.screenmatch.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversionDatos implements IConversionData {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) throws JsonProcessingException {
        return objectMapper.readValue(json, clase);
    }
}
