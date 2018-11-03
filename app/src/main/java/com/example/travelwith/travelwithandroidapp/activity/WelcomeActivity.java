package com.example.travelwith.travelwithandroidapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.R;

public class WelcomeActivity extends Activity {

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
                //In future will be implementation LoginActivity
                Toast.makeText(v.getContext(), "loguje", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initLoginWithFacebook() {
        Button loginWithFacebook = findViewById(R.id.loginInWithFacebook);
        loginWithFacebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //In future will be implementation LoginWithFacebookActivity
                Toast.makeText(v.getContext(), "loguje przez fejsa", Toast.LENGTH_LONG).show();
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

}
