package com.example.travelwith.travelwithandroidapp.asynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.activity.TravelFormActivity;
import com.example.travelwith.travelwithandroidapp.connection.ConnectionWithTravelServer;
import com.example.travelwith.travelwithandroidapp.connection.Util;
import com.example.travelwith.travelwithandroidapp.object.User;

import java.lang.ref.WeakReference;


public class LoginUserTask extends AsyncTask<Void, Void, Boolean> {
    private static WeakReference<Activity> activity;
    private static User user;
    private static Exception exceptionInBackGround;

    public LoginUserTask(Activity activity, User user) {
        LoginUserTask.activity = new WeakReference<>(activity);
        LoginUserTask.user = user;
    }

    protected Boolean doInBackground(Void... test) {
        try {
            return new ConnectionWithTravelServer(activity.get().getApplicationContext()).loginUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            exceptionInBackGround = e;
            return true;
        }
    }

    protected void onPostExecute(Boolean result) {

        if (result) {
            Intent registerIntent = new Intent(activity.get().getApplicationContext(), TravelFormActivity.class);
            activity.get().startActivity(registerIntent);
            activity.get().finish();
        }
        if (!result)
            Toast.makeText(activity.get().getApplicationContext(), Util.getTranslationProperty("error.login.incorrect", activity.get().getApplicationContext()), Toast.LENGTH_SHORT).show();
        if (exceptionInBackGround != null) {
            Toast.makeText(activity.get().getApplicationContext(), Util.getTranslationProperty("error.login", activity.get().getApplicationContext()), Toast.LENGTH_SHORT).show();
            exceptionInBackGround = null;
        }
    }
}

