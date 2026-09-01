package mzn.faisal.employeesmanagement.utils;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Utility class for JSON serialization and deserialization.
 * Provides methods to serialize objects to JSON strings and deserialize 
 * JSON strings back to objects or specific types.
 */
public class JsonUtils {

    private JsonUtils() {} // singleton class

    /**
     * Static instance of {@link ObjectMapper} pre-configured for JSON serialization and deserialization.
     * 
     *    Ignores unknown properties in JSON input during deserialization. 
     *    Ignores explicitly ignored properties during deserialization. 
     *    Writes dates as ISO-8601 formatted strings instead of timestamps. 
     *    Excludes properties with {@code null} values during serialization. 
     */
    private static final ObjectMapper objectMapper = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            .configure(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS, false)
//            .addModule(new tools.jackson.datatype.jsr310.JavaTimeModule())// Support for Java 8 date/time types. But i don't need this module, because it's already included in jackson 3

            .changeDefaultPropertyInclusion(
                    include -> include.withValueInclusion(JsonInclude.Include.NON_NULL)
            )
            .build();

    /**
     * convert an object to JSON string
     *
     * @param obj the object to be serialized into a JSON string; must not be null
     * @return the JSON string representation of the provided object
     */
    public static String writeValueAsString(Object obj){
        try{
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException("Failed to convert object to JSON string", e);
        }
    }


    /**
     * Deserializes a JSON string into an object of the specified class type.
     *
     * @param <T>   the type of the object to be deserialized
     * @param json  the JSON string to be deserialized; must not be null
     * @param clazz the class type to deserialize the JSON string into; must not be null
     * @return the deserialized object of the specified type
     */
    public static <T> T readValue(String json, Class<T> clazz){
        try{
            return objectMapper.readValue(json, clazz);
        } catch (Exception e){
            throw new RuntimeException("Failed to convert JSON string to object", e);
        }
    }


    /**
     * Deserializes a JSON string into an object of the specified type using a {@code TypeReference}.
     *
     * @param <T>           the type of the object to be deserialized
     * @param json          the JSON string to be deserialized; must not be null
     * @param typeReference a {@code TypeReference} representing the type to deserialize the JSON string into; must not be null
     * @return the deserialized object of the specified type
     */
    public static <T> T readValue(String json, TypeReference<T> typeReference){
        try{
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e){
            throw new RuntimeException("Failed to convert JSON string to object", e);
        }
    }


    /**
     * Converts an object to an instance of the specified class type.
     *
     * @param <T>   the type of the resulting object
     * @param obj   the source object to be converted; must not be null
     * @param clazz the target class type to convert the object into; must not be null
     * @return an object of the specified class type created from the source object
     */
    public static <T> T fromObject(Object obj, Class<T> clazz){
        return objectMapper.convertValue(obj, clazz);
    }
}
