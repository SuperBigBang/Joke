package com.hfad.joke.service.delayedMessage;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.hfad.joke.R;
import com.hfad.joke.screen.mainActivity.MainActivity;

import timber.log.Timber;

public class DelayedMessageService extends IntentService {
    public static final String EXTRA_MESSAGE = "message";
    public static final int NOTIFICATION_ID = 99996;
    //  private Handler handler;

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

/*    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
 //       handler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }*/

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

        //Timber.e("DelayedMessageService The message is: %s", text);
        //       handler.post(() -> Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show());
        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
     /*   Notification notification=new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(text)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "5")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(text)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent);
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
        Timber.e("Канал уведомлений этого приложения");
    }
}
