package com.oljalatinovic.oljaee.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
@Converter
public class BooleanConverter implements AttributeConverter<Boolean, String>{
    
    @Override
    public String convertToDatabaseColumn(Boolean value) {
        if (value) {
            return "D";
        } else {
            return "N";
        }
    }
    @Override
    public Boolean convertToEntityAttribute(String value) {
        return "D".equals(value);
    }
}