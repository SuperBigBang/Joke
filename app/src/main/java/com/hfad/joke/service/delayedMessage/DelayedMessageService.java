package com.hfad.joke.service.delayedMessage;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import timber.log.Timber;

public class DelayedMessageService extends IntentService {
    public static final String EXTRA_MESSAGE = "message";

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        synchronized (this) {
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        showText(text);
    }

    private void showText(final String text) {
        Timber.e("DelayedMessageService The message is: %s", text);
    }
}
