package com.claire.traveldiary.settings;

import com.claire.traveldiary.base.BasePresenter;
import com.claire.traveldiary.base.BaseView;

public interface SettingsContract {

    interface View extends BaseView<Presenter> {

        void openSyncDialogUi();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void openSyncDialog();
    }
}
