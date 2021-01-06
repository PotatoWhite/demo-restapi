package me.potato.demo.cqrscommander.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class JpaJsonConverter implements AttributeConverter<Object, String> {
  private static final ObjectMapper om = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(Object attribute) {
    try {
      return om.writeValueAsString(attribute);
    } catch(JsonProcessingException ex) {
      return null;
    }
  }

  @Override
  public Object convertToEntityAttribute(String dbData) {
    try {
      return om.readValue(dbData, Object.class);
    } catch(IOException ex) {
      return null;
    }
  }
}