package com.claire.traveldiary.mainpage;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.claire.traveldiary.data.Diary;

import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

public class MainPagePresenter implements MainPageContract.Presenter {

    private MainPageContract.View mView;

    @SuppressLint("RestrictedApi")
    public MainPagePresenter(MainPageContract.View view) {
        mView = checkNotNull(view, "view cannot be null!");
        mView.setPresenter(this);
    }

    @Override
    public void start() {}

    @Override
    public void result(int requestCode, int resultCode) {}

    @Override
    public void openEdit(Diary diary) {
        mView.openEditPage(diary);
    }

    @Override
    public void deleteDiary(int id) {
        mView.deleteDiaryUi(id);
    }

    @Override
    public void shareDiary(Diary diary) {
        mView.shareDiaryUi(diary);
    }

    @Override
    public void loadSearchData(List<Diary> diaries) {
        mView.loadSearchDataUi(diaries);
    }

    @Override
    public void refreshSearchStatus() {
        mView.refreshSearchStatusUi();
    }

    @Override
    public void changeLayout(int status) {
        mView.changeLayoutUi(status);
    }

    @Override
    public void setFontType(TextView title, TextView date) {
        mView.setFontTypeUi(title, date);
    }

}
