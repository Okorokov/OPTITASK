package com.example.optitask.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.optitask.Common.Common;
import com.example.optitask.R;

public class AuthShort extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthShort";
    private float rating;
    private RatingBar defaultRatingBar;
    private String password;
    private SharedPreferences sPref;
    private TextView textView;
    private SharedPreferences.Editor ed;
    private Intent intent = new Intent();

    @Override
    protected void onStart() {
        super.onStart();
        textView = (TextView) findViewById(R.id.textView);
        sPref = getSharedPreferences(Common.SHARED_BASE, MODE_PRIVATE);
        ed = sPref.edit();
        //Есть код, или нету
        if (sPref.getBoolean(Common.SHARED_CODE, false)) {
            textView.setText(R.string.as_name_title);
        } else {
            textView.setText(R.string.as_name_title_set);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_short);

        defaultRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        defaultRatingBar.setStepSize(1);
        rating = Common.RATING_MIN;
        password = Common.PASSWORD_DEF;
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.buttondel).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if ((rating == Common.RATING_MAX) || (rating < Common.RATING_MIN)) {
            password = Common.PASSWORD_DEF;
            rating = Common.RATING_MIN;
        }

        int i = v.getId();
        if (i == R.id.button1) {
            rating++;
            password = password + "1";
        } else if (i == R.id.button2) {
            rating++;
            password = password + "2";
        } else if (i == R.id.button3) {
            rating++;
            password = password + "3";
        } else if (i == R.id.button4) {
            rating++;
            password = password + "4";
        } else if (i == R.id.button5) {
            rating++;
            password = password + "5";
        } else if (i == R.id.button6) {
            rating++;
            password = password + "6";
        } else if (i == R.id.button7) {
            rating++;
            password = password + "7";
        } else if (i == R.id.button8) {
            rating++;
            password = password + "8";
        } else if (i == R.id.button9) {
            rating++;
            password = password + "9";
        } else if (i == R.id.button0) {
            rating++;
            password = password + "0";
        } else if (i == R.id.buttondel) {
            rating--;
            if (password.length() != 0) {
                password = password.substring(0, password.length() - 1);
            }
        }


        if (rating == Common.RATING_MAX - 1) {

            if (sPref.getBoolean(Common.SHARED_CODE, false)) {

                String password_true = sPref.getString(Common.SHARED_CODE_STRING, "");
                if (password_true.equals(password)) {
                    ed.putBoolean(Common.SHARED_CODE, true);
                    ed.putString(Common.SHARED_CODE_STRING, password);
                    ed.apply();
                    //Intent intent =new Intent();
                    intent.putExtra(Common.SHARED_CODE_STRING, password);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    textView.setText(R.string.as_name_title_set_pop);
                    password = Common.PASSWORD_DEF;
                    rating = Common.RATING_MIN;
                }

            } else {

                if (sPref.getBoolean(Common.SHARED_CODE_ONE, false)) {

                    String password_true = sPref.getString(Common.SHARED_CODE_ONE_STRING, "");
                    if (password_true.equals(password)) {
                        ed.putBoolean(Common.SHARED_CODE, true);
                        ed.putString(Common.SHARED_CODE_STRING, password);
                        ed.apply();
                        Intent intent =new Intent();
                        intent.putExtra(Common.SHARED_CODE_STRING, password);
                        setResult(RESULT_OK, intent);
                        finish();
                    }else {
                        textView.setText(R.string.as_name_title_set_pop);
                        password = Common.PASSWORD_DEF;
                        rating = Common.RATING_MIN;
                    }

                } else {
                    ed.putBoolean(Common.SHARED_CODE_ONE, true);
                    ed.putString(Common.SHARED_CODE_ONE_STRING, password);
                    ed.apply();
                    textView.setText(R.string.as_name_title_set_repit);
                    password = Common.PASSWORD_DEF;
                    rating = Common.RATING_MIN;
                }
            }


        }

        Log.d(TAG, password);

        defaultRatingBar.setRating(rating);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed");
    }
}
