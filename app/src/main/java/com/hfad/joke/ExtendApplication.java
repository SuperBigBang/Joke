package com.hfad.joke;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import timber.log.Timber;

public class ExtendApplication extends Application {

    /*  private static BaseComponent sBaseComponent;

      public static BaseComponent getBaseComponent() {
          return sBaseComponent;
      }

      @VisibleForTesting
      public static void setBaseComponent(@NonNull BaseComponent baseComponent) {
          sBaseComponent = baseComponent;
      }
  */
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

   /*     sBaseComponent = DaggerBaseComponent.builder()
                .contextModule(new ContextModule(this))
                .starbuzzDatabaseHelperModule(new StarbuzzDatabaseHelperModule(this))
                .build();
*/
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = "Канал с дичью";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("5", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}