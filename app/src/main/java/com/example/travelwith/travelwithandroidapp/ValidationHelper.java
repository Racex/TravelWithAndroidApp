package com.example.travelwith.travelwithandroidapp;

import android.content.Context;
import android.widget.EditText;

import com.example.travelwith.travelwithandroidapp.connection.Util;

import java.util.regex.Pattern;

public class ValidationHelper {
    private static final String PHONE_NUMBER_REGEX = "^\\d{9}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,64}$";
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private Context context;

    public ValidationHelper(Context context) {
        this.context = context;
    }


    public void checkPhoneNumberAndSetError(EditText editText) {
        String textToCheck = editText.getText().toString();
        if (!textToCheck.equals(""))
            if (!Pattern.matches(PHONE_NUMBER_REGEX, textToCheck))
                setErrorOnEditText(editText, "validation.phone.incorrect");
    }

    public void checkPasswordAndSetError(EditText editText) {
        String textToCheck = editText.getText().toString();
        if (!Pattern.matches(PASSWORD_REGEX, textToCheck))
            setErrorOnEditText(editText, "validation.password.incorrect");
    }

    public void checkEmailAndSetError(EditText editText) {
        String textToCheck = editText.getText().toString();
        if (!Pattern.matches(EMAIL_REGEX, textToCheck))
            setErrorOnEditText(editText, "validation.email.incorrect");
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


    public void checkPasswordDifferenceAndSetError(EditText password, EditText repassword) {
        if (!password.getText().toString().equals(repassword.getText().toString())) {
            setErrorOnEditText(repassword, "validation.password.different");
        }
    }

    private void setErrorOnEditText(EditText editText, String translationKey) {
        editText.setError(Util.getTranslationProperty(translationKey, context));
    }
}
