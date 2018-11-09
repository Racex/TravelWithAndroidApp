package com.example.travelwith.travelwithandroidapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

public class WelcomeActivity extends Activity {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initLogin();
        initLoginWithFacebook();
        initRegistration();
    }

    private void initLogin() {
        Button login = findViewById(R.id.loginInButton);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent loginIntent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });

    }

    private void initLoginWithFacebook() {
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginWithFacebook = findViewById(R.id.loginInWithFacebook);
        loginWithFacebook.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginWithFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        //TODO connect with server and transfer user authorization ?
                        Intent registerIntent = new Intent(getBaseContext(), TravelFormActivity.class);
                        startActivity(registerIntent);
                        finish();
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,first_name,last_name,email");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {
                //TODO make toast translation
                Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                //TODO make toast translation
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRegistration() {
        Button register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO check server is connect and mobile phone have internet connection
                Intent registerIntent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
