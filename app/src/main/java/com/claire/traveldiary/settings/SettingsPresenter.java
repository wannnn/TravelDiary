package com.claire.traveldiary.settings;

import android.annotation.SuppressLint;
import android.util.Log;

import com.claire.traveldiary.TravelDiaryApplication;
import com.claire.traveldiary.util.UserManager;

import static android.support.v4.util.Preconditions.checkNotNull;

public class SettingsPresenter implements SettingsContract.Presenter {

    private static final String TAG = "SettingsPresenter";

    private SettingsContract.View mView;

    @SuppressLint("RestrictedApi")
    public SettingsPresenter(SettingsContract.View view) {
        mView = checkNotNull(view, "view cannot be null!");
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void openSyncDialog() {
        mView.openSyncDialogUi();
    }

    @Override
    public void loginFacebook() {
        mView.loginFacebookUi();
    }

    @Override
    public void logoutFacebook() {
        mView.logoutFacebookUi();
    }
}
