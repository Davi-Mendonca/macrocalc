package com.macrocalc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.macrocalc.model.AccessResponse;
import com.macrocalc.model.ResponseBody;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class SearchFoodsService {

    public String getAccesstoken() throws IOException {

        String AUTH_URL = "https://oauth.fatsecret.com/connect/token";
        String form = "grant_type=client_credentials" +
                "&client_id=c50b6296d89c486180c9d7626d81418b" +
                "&client_secret=713c31ac114847fb9b8d1704f60625c0" +
                "&scope=basic";

        URL url = new URL(AUTH_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("accept", "application/json");
        conn.setRequestMethod("POST");
        conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(form.getBytes());

        InputStream is = conn.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        AccessResponse response = mapper.readValue(is, AccessResponse.class);

        return response.getAccess_token();
    }

    public String getFood(String food) throws IOException {
        InputStream is = null;
        OutputStream os = null;

        ResponseBody response = null;
        try {
            String body = "method=foods.search&search_expression=xxx&format=json";
            String REQUEST_URL = "https://platform.fatsecret.com/rest/server.api";
            URL url = new URL(REQUEST_URL);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Authorization", "Bearer ".concat(getAccesstoken()));
            conn.setDoOutput(true);

            os = conn.getOutputStream();
            os.write(body.replace("xxx", food).getBytes());

            is = conn.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(is, ResponseBody.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (is != null)
                is.close();

            if (os != null)
                os.close();
        }

        if (response != null)
            return response.getFood().toString();
        else return "ERRO";
    }
}
