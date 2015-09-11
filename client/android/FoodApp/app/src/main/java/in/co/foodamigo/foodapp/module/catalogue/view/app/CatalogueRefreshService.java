package in.co.foodamigo.foodapp.module.catalogue.view.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CatalogueRefreshService extends Service {

    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> orderStatusListnerJob;

    public CatalogueRefreshService() {
        scheduler = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void startOrderStatusListnerJob(long scanIntervalInSeconds, long idleIntervalInSeconds) {
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
            }
        }, scanIntervalInSeconds, idleIntervalInSeconds, TimeUnit.SECONDS);
    }

    private void stopOrderStatusListnerJob() {
        if (orderStatusListnerJob != null) {
            orderStatusListnerJob.cancel(true);
        }
    }
}
