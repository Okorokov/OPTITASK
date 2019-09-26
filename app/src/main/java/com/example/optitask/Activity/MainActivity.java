package com.example.optitask.Activity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.optitask.Common.Common;
import com.example.optitask.Interface.IMainContract;
import com.example.optitask.Model.User;
import com.example.optitask.Presenter.MainPresenter;
import com.example.optitask.R;


public class MainActivity extends AppCompatActivity implements IMainContract.IMainActivity.View {

    private static final String TAG = "MainActivity";
    private IMainContract.IMainActivity.Presenter mainPresenter;

    private SharedPreferences sPref;
    private Intent intent = new Intent();
    private SharedPreferences.Editor ed;

    @Override
    protected void onStart() {
        super.onStart();
        sPref = getSharedPreferences(Common.SHARED_BASE, MODE_PRIVATE);
        ed = sPref.edit();

        if (!mainPresenter.onAuthResult(sPref.getString(Common.USER_UID, ""))) {
            intent.setClass(this, Auth.class);
            startActivityForResult(intent, Common.REQUEST_CODE_AUTH);
        }

        if (!sPref.getBoolean(Common.SHARED_ON_VISIBLE_AUTHSHORT, false) & (mainPresenter.onAuthResult(sPref.getString(Common.USER_UID, "")))) {
            intent.setClass(this, AuthShort.class);
            startActivityForResult(intent, Common.REQUEST_CODE_AUTHSHORT);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == Common.REQUEST_CODE_AUTH) & (data != null)) {

            User user = new User();
            user.setDisplayname(data.getStringExtra(Common.USER_DISPLAYNAME));
            user.setEmail(data.getStringExtra(Common.USER_EMAIL));
            user.setPhonenumber(data.getStringExtra(Common.USER_PHONENUMBER));
            user.setUid(data.getStringExtra(Common.USER_UID));

            ed.putString(Common.USER_DISPLAYNAME, user.getDisplayname());
            ed.putString(Common.USER_EMAIL, user.getEmail());
            ed.putString(Common.USER_PHONENUMBER, user.getPhonenumber());
            ed.putString(Common.USER_UID, user.getUid());
            ed.apply();

            Log.d(TAG, user.toString());

        }
        if ((requestCode == Common.REQUEST_CODE_AUTHSHORT) & (data != null)) {
            String password = data.getStringExtra(Common.SHARED_CODE_STRING);
            ed.putBoolean(Common.SHARED_ON_VISIBLE_AUTHSHORT, true);
            ed.apply();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        ed.putBoolean(Common.SHARED_ON_VISIBLE_AUTHSHORT, false);
        ed.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ed.putBoolean(Common.SHARED_ON_VISIBLE_AUTHSHORT, false);
        ed.apply();
    }
}
