package com.example.travelwith.travelwithandroidapp.asynctask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.connection.ConnectionWithTravelServer;
import com.example.travelwith.travelwithandroidapp.connection.Util;
@SuppressLint("StaticFieldLeak")
public class CheckLoginTask extends AsyncTask<Void, Void, Boolean> {
    private static Context context;
    private static EditText userNameEditText;
    private static Exception exceptionInBackGround;

    public CheckLoginTask(Context context, EditText editText) {
        CheckLoginTask.context = context;
        userNameEditText = editText;
    }

    protected Boolean doInBackground(Void... test) {
        try {
            return new ConnectionWithTravelServer(context).checkUserName(userNameEditText.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            exceptionInBackGround = e;
            return true;
        }
    }

    protected void onPostExecute(Boolean result) {
        if (!result)
            userNameEditText.setError(Util.getTranslationProperty("validation.login.exist", context));
        if (exceptionInBackGround != null) {
            Toast.makeText(context, Util.getTranslationProperty("error.userName", context), Toast.LENGTH_SHORT).show();
            exceptionInBackGround = null;
        }
    }
}