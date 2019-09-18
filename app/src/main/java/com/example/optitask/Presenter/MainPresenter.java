package com.example.optitask.Presenter;

import android.util.Log;

import com.example.optitask.Interface.IMainContract;

public class MainPresenter implements IMainContract.IMainActivity.Presenter {

    private static final String TAG = "MainPresenter";
    private IMainContract.IMainActivity.View mView;
    private IMainContract.IMainActivity.Repository mRepository;

    public MainPresenter(IMainContract.IMainActivity.View mView) {
        this.mView = mView;
        Log.d(TAG, "Constructor");
    }
}
