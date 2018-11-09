package com.example.travelwith.travelwithandroidapp.object;

import org.json.JSONObject;

public class User {

    private String login;
    private String password;
    private String email;
    private String phoneNumber;

    public User(String login, String password, String email, String phoneNumber) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public JSONObject getJSONObject() throws Exception {
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("userName", login);
        userJsonObject.put("email", email);
        userJsonObject.put("password", password);
        userJsonObject.put("phoneNumber", phoneNumber);
        return userJsonObject;
    }

    public JSONObject getJSONObjectToLogin() throws Exception {
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("userName", login);
        userJsonObject.put("password", password);
        return userJsonObject;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

}
