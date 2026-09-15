package mzn.faisal.employeesmanagement.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class FileUtils {

    private FileUtils(){}

    public static String readResourceFile(String path){
        try {
            ClassPathResource resource = new ClassPathResource(path);
            try(InputStream inputStream = resource.getInputStream()){
                return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            return "";
        }
    }

}
