package codes.nttuan.utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtil {
    private String json;

    public HttpUtil(String json){
        this.json = json;
    }

    public static HttpUtil of(BufferedReader reader){
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return new HttpUtil(builder.toString());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public <T> T toModel(Class<T> tClass){
        if(this.json == null) {
            return null;
        } else{
            try {
                return new ObjectMapper().readValue(this.json, tClass);
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return null;
            }
        }
    }
}
