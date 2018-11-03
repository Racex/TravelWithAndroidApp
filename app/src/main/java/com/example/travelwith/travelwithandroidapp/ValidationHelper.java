package com.example.travelwith.travelwithandroidapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.EditText;

import com.example.travelwith.travelwithandroidapp.connection.Util;

import java.util.regex.Pattern;
@SuppressLint("StaticFieldLeak")
public class ValidationHelper {
    private static final String PHONE_NUMBER_REGEX = "^\\d{9}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,64}$";
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private static Context context;

    public ValidationHelper(Context context) {
        ValidationHelper.context = context;
    }


    public void checkPhoneNumber(EditText editText) {
        String textToCheck = editText.getText().toString();
        if (!textToCheck.equals(""))
            if (!Pattern.matches(PHONE_NUMBER_REGEX, textToCheck))
                editText.setError(Util.getTranslationProperty("validation.phone.incorrect", context));
    }

    public void checkPassword(EditText editText) {
        String textToCheck = editText.getText().toString();
        if (!Pattern.matches(PASSWORD_REGEX, textToCheck))
            editText.setError(Util.getTranslationProperty("validation.password.incorrect", context));
    }

    public void checkEmail(EditText editText) {
        String textToCheck = editText.getText().toString();
        if (!Pattern.matches(EMAIL_REGEX, textToCheck))
            editText.setError(Util.getTranslationProperty("validation.email.incorrect", context));
    }

    public boolean checkEditTextContainsErorrs(EditText... editTextArray) {
        for (EditText singleEditText : editTextArray) {
            if (singleEditText.getError() != null)
                return true;
        }
        return false;
    }


    public void checkEmptyEditTextAndSetError(EditText... editTextArray) {
        for (EditText singleEditText : editTextArray) {
            if (singleEditText.getText().toString().equals("")) {
                singleEditText.setError("Pole " + singleEditText.getHint().toString() + " nie powinno być puste");
            }
        }
    }


    public void checkPasswordDifference(EditText password, EditText repassword) {
        if (!password.getText().toString().equals(repassword.getText().toString())) {
            repassword.setError(Util.getTranslationProperty("validation.password.different", context));
        }
    }
}
