package com.example.travelwith.travelwithandroidapp.asynctask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.connection.ConnectionWithTravelServer;
import com.example.travelwith.travelwithandroidapp.connection.Util;
@SuppressLint("StaticFieldLeak")
public class CheckEmailTask extends AsyncTask<Void, Void, Boolean> {
    private static Context context;
    private static EditText userEmailEditText;
    private static Exception exceptionInBackGround;

    public CheckEmailTask(Context context, EditText editText) {
        CheckEmailTask.context = context;
        userEmailEditText = editText;
    }

    protected Boolean doInBackground(Void... test) {
        try {
            return new ConnectionWithTravelServer(context).checkEmail(userEmailEditText.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            exceptionInBackGround = e;
            return true;
        }
    }

    protected void onPostExecute(Boolean result) {
        if (!result)
            userEmailEditText.setError(Util.getTranslationProperty("validation.mail.exist", context));
        if (exceptionInBackGround != null) {
            Toast.makeText(context, Util.getTranslationProperty("error.mail", context), Toast.LENGTH_SHORT).show();
            exceptionInBackGround = null;
        }
    }
}