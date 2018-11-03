package com.example.travelwith.travelwithandroidapp.asynctask;

import android.annotation.SuppressLint;
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
@SuppressLint("StaticFieldLeak")
public class RegisterUserTask extends AsyncTask<Void, View, Boolean> {
    private Activity activity;
    private Context context;
    private User currentUser;
    private ProgressDialog progressDialog;
    private Exception exceptionInBackground;


    protected Boolean doInBackground(Void... test) {
        try {
            return new ConnectionWithTravelServer(context).registerUser(currentUser.getJSONObject());
        } catch (Exception e) {
            e.printStackTrace();
            exceptionInBackground = e;
            return false;
        }
    }

    protected void onPreExecute() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(Util.getTranslationProperty("register.massage", context));
        progressDialog.show();
    }


    protected void onPostExecute(Boolean result) {
        exceptionCheck();
        resultCheck(result);
    }

    private void resultCheck(Boolean result) {
        if (result) {
            progressDialog.dismiss();
            Intent registerIntent = new Intent(context, TravelFormActivity.class);
            activity.startActivity(registerIntent);
            activity.finish();
        } else {
            progressDialog.dismiss();
            progressDialog.cancel();
            Toast.makeText(context, Util.getTranslationProperty("error.connection", context), Toast.LENGTH_SHORT).show();
        }
    }

    private void exceptionCheck() {
        if (exceptionInBackground != null) {
            progressDialog.dismiss();
            Toast.makeText(context, Util.getTranslationProperty("error.register", context), Toast.LENGTH_SHORT).show();
            exceptionInBackground = null;
        }
    }

    public RegisterUserTask(Context context, User user, Activity activity) {
        this.context = context;
        currentUser = user;
        this.activity = activity;
    }
}