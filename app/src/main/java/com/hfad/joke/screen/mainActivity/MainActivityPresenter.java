package com.hfad.joke.screen.mainActivity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView>{

    public void onClick() {
        getViewState().runService();
    }

    public void watchMileage() {
        getViewState().showDistance();
    }
}
