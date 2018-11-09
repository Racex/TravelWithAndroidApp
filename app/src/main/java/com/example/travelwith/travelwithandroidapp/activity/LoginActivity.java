package com.example.travelwith.travelwithandroidapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.R;
import com.example.travelwith.travelwithandroidapp.ValidationHelper;
import com.example.travelwith.travelwithandroidapp.asynctask.LoginUserTask;
import com.example.travelwith.travelwithandroidapp.connection.Util;
import com.example.travelwith.travelwithandroidapp.object.AESCrypt;
import com.example.travelwith.travelwithandroidapp.object.User;

public class LoginActivity extends Activity {

    private ValidationHelper validationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        validationHelper = new ValidationHelper(getApplicationContext());
        initLoginEditText();
        initLoginButton();
        initPasswordEditText();
        initRecoveryButton();
    }

    private void initRecoveryButton() {
        final Button recoveryPasswordButton = findViewById(R.id.recoveryPasswordButtonInLoginActivity);
        recoveryPasswordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //In future will be implementation LoginActivity
                Toast.makeText(getApplicationContext(), "Tu kiedyś będzie przypomnienie hasła ale nie było w sprincie :D", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initLoginEditText() {
        final EditText loginEditText = findViewById(R.id.loginEditTextInLoginActivity);
        loginEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                    validationHelper.checkEmptyEditTextAndSetError(loginEditText);
                }
            }
        });
    }

    private void initPasswordEditText() {
        final EditText passwordEditText = findViewById(R.id.passwordEditTextInLoginActivity);
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                    validationHelper.checkEmptyEditTextAndSetError(passwordEditText);
                }
            }
        });
    }

    private void initLoginButton() {
        Button loginButton = findViewById(R.id.loginInButtonInLoginActivity);
        final EditText login = findViewById(R.id.loginEditTextInLoginActivity);
        final EditText password = findViewById(R.id.passwordEditTextInLoginActivity);
        final Activity activity = this;
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //In future will be implementation LoginActivity
                hideKeyboard(v);
                validationHelper.checkEmptyEditTextAndSetError(login, password);
                if (validationHelper.checkEditTextContainsErorrs(login, password)) {
                    Toast.makeText(getApplicationContext(), Util.getTranslationProperty("validation.login.field.get.errors", getApplicationContext()), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        User user = new User(
                                login.getText().toString(),
                                AESCrypt.encrypt(getApplicationContext(), password.getText().toString()));
                        new LoginUserTask(activity, user).execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), Util.getTranslationProperty("error.get.user", getApplicationContext()), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void hideKeyboard(final View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
