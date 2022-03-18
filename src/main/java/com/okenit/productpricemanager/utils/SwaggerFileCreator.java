package com.okenit.productpricemanager.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

@Slf4j
@Component
public class SwaggerFileCreator {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public void create() throws IOException {
        String url = "http://localhost:8080/v2/api-docs";
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            BufferedWriter writer = new BufferedWriter(new FileWriter("docs/swagger.json"));
            writer.write(jsonText);
            writer.close();
        }
    }
}
