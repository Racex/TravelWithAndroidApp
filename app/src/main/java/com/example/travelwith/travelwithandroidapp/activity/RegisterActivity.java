package com.example.travelwith.travelwithandroidapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.R;
import com.example.travelwith.travelwithandroidapp.ValidationHelper;
import com.example.travelwith.travelwithandroidapp.asynctask.CheckEmailTask;
import com.example.travelwith.travelwithandroidapp.asynctask.CheckLoginTask;
import com.example.travelwith.travelwithandroidapp.asynctask.CheckPhoneTask;
import com.example.travelwith.travelwithandroidapp.asynctask.RegisterUserTask;
import com.example.travelwith.travelwithandroidapp.connection.Util;
import com.example.travelwith.travelwithandroidapp.object.AESCrypt;
import com.example.travelwith.travelwithandroidapp.object.User;

public class RegisterActivity extends Activity {
    @SuppressLint("StaticFieldLeak")
    private static ValidationHelper validationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        validationHelper = new ValidationHelper(getApplicationContext());
        initRegister();
        initLoginEditText();
        initEmailTextView();
        initPasswordAndRepasswordCheck();
        initPhoneTextView();
    }

    private void initLoginEditText() {
        final EditText login = findViewById(R.id.loginEditText);
        login.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                    new CheckLoginTask(getApplicationContext(), login).execute();
                    validationHelper.checkEmptyEditTextAndSetError(login);
                }
            }
        });
    }

    private void initEmailTextView() {
        final EditText email = findViewById(R.id.emailEditText);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                    new CheckEmailTask(getApplicationContext(), email).execute();
                    validationHelper.checkEmptyEditTextAndSetError(email);
                    validationHelper.checkEmail(email);
                }
            }
        });
    }

    private void initPhoneTextView() {
        final EditText phone = findViewById(R.id.phoneEditText);
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                    new CheckPhoneTask(getApplicationContext(), phone).execute();
                    validationHelper.checkPhoneNumber(phone);
                }
            }
        });
    }

    private void initPasswordAndRepasswordCheck() {
        final EditText repassword = findViewById(R.id.repasswordEditText);
        final EditText password = findViewById(R.id.passwordEditText);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                    validationHelper.checkEmptyEditTextAndSetError(password);
                    validationHelper.checkPassword(password);
                }
            }
        });
        repassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                    validationHelper.checkEmptyEditTextAndSetError(password, repassword);
                    EditText password = findViewById(R.id.passwordEditText);
                    validationHelper.checkPasswordDifference(password, repassword);
                }
            }
        });
    }

    private void initRegister() {
        final ImageButton register = findViewById(R.id.registerImageButton);
        final EditText login = findViewById(R.id.loginEditText);
        final EditText password = findViewById(R.id.passwordEditText);
        final EditText repassword = findViewById(R.id.repasswordEditText);
        final EditText email = findViewById(R.id.emailEditText);
        final EditText phone = findViewById(R.id.phoneEditText);
        final Activity activity = this;
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validationHelper.checkEmptyEditTextAndSetError(login, password, email, repassword);
                if (validationHelper.checkEditTextContainsErorrs(login, password, email, phone)) {
                    Toast.makeText(getApplicationContext(), Util.getTranslationProperty("validation.field.get.errors", getApplicationContext()), Toast.LENGTH_SHORT).show();
                } else {
                    User currentUser = null;
                    try {
                        currentUser = new User(
                                login.getText().toString(),
                                AESCrypt.encrypt(getApplicationContext(), password.getText().toString()),
                                email.getText().toString(),
                                phone.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), Util.getTranslationProperty("error.create.user", getApplicationContext()), Toast.LENGTH_SHORT).show();

                    }
                    new RegisterUserTask(getApplicationContext(), currentUser, activity).execute();
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
