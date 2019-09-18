package com.example.optitask.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.optitask.Interface.IMainContract;
import com.example.optitask.Presenter.MainPresenter;
import com.example.optitask.R;

public class MainActivity extends AppCompatActivity implements IMainContract.IMainActivity.View {

    private static final String TAG = "MainActivity";
    private IMainContract.IMainActivity.Presenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);
    }
}
