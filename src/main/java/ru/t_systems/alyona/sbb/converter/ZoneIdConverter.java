package ru.t_systems.alyona.sbb.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.ZoneId;

@Converter
public class ZoneIdConverter implements AttributeConverter<ZoneId, String> {

    @Override
    public String convertToDatabaseColumn(ZoneId attributeValue) {
        if (attributeValue == null) {
            return null;
        }
        return attributeValue.getId();
    }

    @Override
    public ZoneId convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return ZoneId.of(dbData);
    }
}