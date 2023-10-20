package com.education.brcmeducorn.api;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {

    public static String sendGetRequest(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            byte[] contents = new byte[1024];
            int bytesRead;
            StringBuilder builder = new StringBuilder();

            while ((bytesRead = in.read(contents)) != -1) {
                builder.append(new String(contents, 0, bytesRead));
            }

            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
