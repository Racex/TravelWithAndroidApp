package com.example.travelwith.travelwithandroidapp.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.activity.TravelFormActivity;
import com.example.travelwith.travelwithandroidapp.connection.ConnectionWithTravelServer;
import com.example.travelwith.travelwithandroidapp.connection.Util;
import com.example.travelwith.travelwithandroidapp.object.User;

import java.lang.ref.WeakReference;

public class RegisterUserTask extends AsyncTask<Void, View, Boolean> {
    private WeakReference<Context> context;
    private WeakReference<Activity> activity;
    private User currentUser;
    private ProgressDialog progressDialog;
    private Exception exceptionInBackground;


    protected Boolean doInBackground(Void... test) {
        try {
            return new ConnectionWithTravelServer(context.get()).registerUser(currentUser.getJSONObject());
        } catch (Exception e) {
            e.printStackTrace();
            exceptionInBackground = e;
            return false;
        }
    }

    protected void onPreExecute() {
        progressDialog = new ProgressDialog(activity.get());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(Util.getTranslationProperty("register.massage", context.get()));
        progressDialog.show();
    }


    protected void onPostExecute(Boolean result) {
        exceptionCheck();
        resultCheck(result);
    }

    private void resultCheck(Boolean result) {
        if (result) {
            progressDialog.dismiss();
            Intent registerIntent = new Intent(context.get(), TravelFormActivity.class);
            activity.get().startActivity(registerIntent);
            activity.get().finish();
        } else {
            progressDialog.dismiss();
            progressDialog.cancel();
            Toast.makeText(context.get(), Util.getTranslationProperty("error.connection", context.get()), Toast.LENGTH_SHORT).show();
        }
    }

    private void exceptionCheck() {
        if (exceptionInBackground != null) {
            progressDialog.dismiss();
            Toast.makeText(context.get(), Util.getTranslationProperty("error.register", context.get()), Toast.LENGTH_SHORT).show();
            exceptionInBackground = null;
        }
    }

    public RegisterUserTask(Context context, User user, Activity activity) {
        this.context = new WeakReference<>(context);
        currentUser = user;
        this.activity = new WeakReference<>(activity);
    }
}