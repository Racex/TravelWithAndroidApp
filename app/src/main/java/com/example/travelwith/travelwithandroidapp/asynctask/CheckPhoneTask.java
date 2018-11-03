package com.example.travelwith.travelwithandroidapp.asynctask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.connection.ConnectionWithTravelServer;
import com.example.travelwith.travelwithandroidapp.connection.Util;
@SuppressLint("StaticFieldLeak")
public class CheckPhoneTask extends AsyncTask<Void, Void, Boolean> {
    private static Context context;
    private static EditText phoneEditText;
    private static Exception exceptionInBackGround;

    public CheckPhoneTask(Context context, EditText editText) {
        CheckPhoneTask.context = context;
        phoneEditText = editText;
    }

    protected Boolean doInBackground(Void... test) {
        try {
            return new ConnectionWithTravelServer(context).checkPhoneNumber(phoneEditText.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            exceptionInBackGround = e;
            return true;
        }
    }

    protected void onPostExecute(Boolean result) {
        if (!result)
            phoneEditText.setError(Util.getTranslationProperty("validation.phone.exist", context));
        if (exceptionInBackGround != null) {
            Toast.makeText(context, Util.getTranslationProperty("error.phone", context), Toast.LENGTH_SHORT).show();
            exceptionInBackGround = null;
        }
    }
}