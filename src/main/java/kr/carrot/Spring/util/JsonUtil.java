package kr.carrot.Spring.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

public final class JsonUtil {

    private static final String BASE_DIR_NAME = "json/";

    public static <T> T readJson(Class<? extends T> returnType, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource(BASE_DIR_NAME + fileName);

        URL url = resource.getURL();

        System.out.println("url = " + url);

        T data = objectMapper.readValue(url, returnType);

        return data;
    }
}
