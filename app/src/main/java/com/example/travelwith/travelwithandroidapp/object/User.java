package com.example.travelwith.travelwithandroidapp.object;

import org.json.JSONObject;

public class User {

    private static String login;
    private static String password;
    private static String email;
    private static String phoneNumber;

    public User(String login, String password, String email, String phoneNumber) {
        User.login = login;
        User.password = password;
        User.email = email;
        User.phoneNumber = phoneNumber;
    }

    public JSONObject getJSONObject() throws Exception {
            JSONObject userJsonObject = new JSONObject();
            userJsonObject.put("userName", login);
            userJsonObject.put("email", email);
            userJsonObject.put("password", password);
            userJsonObject.put("phoneNumber", phoneNumber);
            return userJsonObject;
    }

}
