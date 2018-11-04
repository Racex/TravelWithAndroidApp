package com.example.travelwith.travelwithandroidapp.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.connection.ConnectionWithTravelServer;
import com.example.travelwith.travelwithandroidapp.connection.Util;

import java.lang.ref.WeakReference;

public class CheckPhoneTask extends AsyncTask<Void, Void, Boolean> {
    private WeakReference<Context> context;
    private WeakReference<EditText> phoneEditText;
    private static Exception exceptionInBackGround;

    public CheckPhoneTask(Context context, EditText editText) {
        this.context = new WeakReference<>(context);
        phoneEditText = new WeakReference<>(editText);
    }

    protected Boolean doInBackground(Void... test) {
        try {
            return new ConnectionWithTravelServer(context.get()).checkPhoneNumber(phoneEditText.get().getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            exceptionInBackGround = e;
            return true;
        }
    }

    protected void onPostExecute(Boolean result) {
        if (!result)
            phoneEditText.get().setError(Util.getTranslationProperty("validation.phone.exist", context.get()));
        if (exceptionInBackGround != null) {
            Toast.makeText(context.get(), Util.getTranslationProperty("error.phone", context.get()), Toast.LENGTH_SHORT).show();
            exceptionInBackGround = null;
        }
    }
}