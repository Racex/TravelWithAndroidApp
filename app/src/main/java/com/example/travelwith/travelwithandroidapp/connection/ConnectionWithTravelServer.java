package com.example.travelwith.travelwithandroidapp.connection;

import android.content.Context;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionWithTravelServer {
    private Context context;

    public ConnectionWithTravelServer(Context applicationContext) {
        context = applicationContext;
    }

    public boolean checkUserName(String username) throws Exception {
        URL checkUserNameURL = new URL(Util.getProperty("check.userName", context));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", username);
        return sendPostWithProperty(checkUserNameURL, jsonObject);
    }

    public Boolean checkEmail(String email) throws Exception {

        URL checkEmailURL = new URL(Util.getProperty("check.email", context));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        return sendPostWithProperty(checkEmailURL, jsonObject);
    }

    public Boolean registerUser(JSONObject user) throws IOException {

        URL registerUserURL = new URL(Util.getProperty("register", context));
        return sendPostWithProperty(registerUserURL, user);
    }

    public Boolean checkPhoneNumber(String phone) throws Exception {

        URL checkPhoneNumberURL = new URL(Util.getProperty("check.phoneNumber", context));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phoneNumber", phone);
        return sendPostWithProperty(checkPhoneNumberURL, jsonObject);
    }

    private boolean sendPostWithProperty(URL url, JSONObject property) throws IOException {
        HttpURLConnection con =
                (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(Integer.valueOf(Util.getProperty("connection.timeout", context)));
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("POST");
        OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
        osw.write(property.toString());
        osw.close();
        System.out.println(con.getResponseCode());
        return con.getResponseCode() == 200;
    }
}
