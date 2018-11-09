package com.example.travelwith.travelwithandroidapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.travelwith.travelwithandroidapp.R;
import com.example.travelwith.travelwithandroidapp.connection.Util;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;

public class TravelFormActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_form);
        initHitchHikingQuestionButton();
        initGroupTripQuestionButton();
        initTravelWithFriendsQuestionButton();
        initTravelWithFriendsButton();
        initGroupTripButton();
        initHitchHikingButton();
    }


    private void initTravelWithFriendsButton() {
        Button login = findViewById(R.id.travelWithFriendsButtonOnTravelForm);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " Tu będzie podróż z przyjaciółmi XD", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initGroupTripButton() {
        Button login = findViewById(R.id.groupTripButtonOnTravelForm);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " Tu będzie podróż  grupowa XD", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initHitchHikingButton() {
        Button login = findViewById(R.id.hitchHikingButtonOnTravelForm);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " Tu będzie podróż stopem kiedyś XD", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTravelWithFriendsQuestionButton() {
        ImageButton login = findViewById(R.id.travelWithFriendsQuestionButtonOnTravelForm);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showSimpleTooltip(v, "travel.wit.friends.question.translation");
            }
        });
    }

    private void initGroupTripQuestionButton() {
        ImageButton login = findViewById(R.id.groupTripQuestionButtonOnTravelForm);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showSimpleTooltip(v, "group.trip.question.translation");
            }
        });
    }

    private void initHitchHikingQuestionButton() {
        ImageButton login = findViewById(R.id.hitchHikingQuestionButtonOnTravelForm);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showSimpleTooltip(v, "hitch.hiking.question.translation");
            }
        });
    }

    private void showSimpleTooltip(View buttonView, String translationKey) {
        new SimpleTooltip.Builder(getApplicationContext())
                .anchorView(buttonView)
                .text(Util.getTranslationProperty(translationKey, getApplicationContext()))
                .gravity(Gravity.TOP)
                .animated(true)
                .build()
                .show();
    }
}
