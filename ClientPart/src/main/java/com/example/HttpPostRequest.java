package com.example;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.util.Random;

public class HttpPostRequest {
    private static final String API_URL = "http://localhost:8080/measurements/add";//Вставьте свой url
    private static final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJZZXJzIiwiaWF0IjoxNzM4ODU0NTEzLCJleHAiOjE3Mzg4NTUxMTN9.y7Zfzhfc7pARz8rACot7YyUlaUycoH6APkSi6ZQrMTA";//Token который вы получите при входе с помощью POSTMAN
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            sendPostRequest();
        }
    }

    private static void sendPostRequest() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + BEARER_TOKEN);
            conn.setDoOutput(true);

            int value = RANDOM.nextInt(101);
            int raining = RANDOM.nextInt(2);

            String requestBody = String.format(
                    "{ \"value\": %d, \"raining\": %d, \"sensor\": { \"name\": \"SensorName\" } }",
                    value, raining
            );

            try (OutputStream os = conn.getOutputStream()) {
                os.write(requestBody.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
