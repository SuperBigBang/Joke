package com.hfad.joke.screen.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.hfad.joke.R;
import com.hfad.joke.service.delayedMessage.DelayedMessageService;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView {

@InjectPresenter
MainActivityPresenter mMainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        mMainActivityPresenter.onClick();
    }

    @Override
    public void runService() {
        Intent intent = new Intent(this, DelayedMessageService.class);
        intent.putExtra(DelayedMessageService.EXTRA_MESSAGE,
                getResources().getString(R.string.button_responce));
        startService(intent);
    }
}
