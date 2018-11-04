package com.example.travelwith.travelwithandroidapp.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.connection.ConnectionWithTravelServer;
import com.example.travelwith.travelwithandroidapp.connection.Util;

import java.lang.ref.WeakReference;

public class CheckLoginTask extends AsyncTask<Void, Void, Boolean> {
    private WeakReference<Context> context;
    private WeakReference<EditText> userNameEditText;
    private static Exception exceptionInBackGround;

    public CheckLoginTask(Context context, EditText editText) {
        this.context = new WeakReference<>(context);
        userNameEditText = new WeakReference<>(editText);
    }

    protected Boolean doInBackground(Void... test) {
        try {
            return new ConnectionWithTravelServer(context.get()).checkUserName(userNameEditText.get().getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            exceptionInBackGround = e;
            return true;
        }
    }

    protected void onPostExecute(Boolean result) {
        if (!result)
            userNameEditText.get().setError(Util.getTranslationProperty("validation.login.exist", context.get()));
        if (exceptionInBackGround != null) {
            Toast.makeText(context.get(), Util.getTranslationProperty("error.userName", context.get()), Toast.LENGTH_SHORT).show();
            exceptionInBackGround = null;
        }
    }
}