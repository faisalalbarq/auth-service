package mzn.faisal.employeesmanagement.DataLayer.db.base;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mzn.faisal.employeesmanagement.utils.JsonUtils;

@Converter(autoApply = true)
public class LangAttributeConverter implements AttributeConverter<LangAttribute, String> {

    @Override
    public String convertToDatabaseColumn(LangAttribute attribute) {
        if (attribute == null) return null;
        try {
            return JsonUtils.writeValueAsString(attribute);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public LangAttribute convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData.isBlank()) return null;

        try {
            return JsonUtils.readValue(dbData, LangAttribute.class);
        } catch (Exception e) {

            // If deserialization fails, we can return a LangAttribute with the same value for both languages as a fallback
            return new LangAttribute(dbData, dbData);
        }
    }
}
