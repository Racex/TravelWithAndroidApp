package com.example.travelwith.travelwithandroidapp.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.connection.ConnectionWithTravelServer;
import com.example.travelwith.travelwithandroidapp.connection.Util;

import java.lang.ref.WeakReference;

public class CheckEmailTask extends AsyncTask<Void, Void, Boolean> {
    private WeakReference<Context> context;
    private WeakReference<EditText> userEmailEditText;
    private Exception exceptionInBackGround;

    public CheckEmailTask(Context context, EditText editText) {
        this.context = new WeakReference<>(context);
        userEmailEditText = new WeakReference<>(editText);
    }

    protected Boolean doInBackground(Void... test) {
        try {
            return new ConnectionWithTravelServer(context.get()).checkEmail(userEmailEditText.get().getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            exceptionInBackGround = e;
            return true;
        }
    }

    protected void onPostExecute(Boolean result) {
        if (!result)
            userEmailEditText.get().setError(Util.getTranslationProperty("validation.mail.exist", context.get()));
        if (exceptionInBackGround != null) {
            Toast.makeText(context.get(), Util.getTranslationProperty("error.mail", context.get()), Toast.LENGTH_SHORT).show();
            exceptionInBackGround = null;
        }
    }
}