package com.claire.traveldiary.edit;

import com.claire.traveldiary.base.BasePresenter;
import com.claire.traveldiary.base.BaseView;

public interface EditContract {

    interface View extends BaseView<Presenter> {

        void openWeatherDialogUi();


    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void openWeatherDialog();

        void editDiary();

        void saveDiary();

    }
}
