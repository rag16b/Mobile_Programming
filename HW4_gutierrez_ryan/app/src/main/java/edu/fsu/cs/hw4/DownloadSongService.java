package edu.fsu.cs.hw4;

import android.app.DownloadManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DownloadSongService extends IntentService {
    private static final String ACTION_DOWNLOAD = "edu.fsu.cs.hw4.action.DOWNLOAD";
    private static final String EXTRA_URL = "edu.fsu.cs.hw4.extra.URL";
    DownloadCompleteReceiver receiver;

    public DownloadSongService() {
        super("DownloadSongService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWNLOAD.equals(action)) {
                //Log.i(TAG, "Working");
                final String url = intent.getStringExtra(EXTRA_URL);
                Uri uri = Uri.parse(url);

                // Create download manager
                DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                assert(manager!=null);
                // Create request
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle("MP3 Download")
                    .setDescription("Who Cares")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);


                // Create and register broadcast receiver
                receiver = new DownloadCompleteReceiver();
                registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

                // Enqueue request
                manager.enqueue(request);

                // Building the notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Download Started")
                    .setContentText("Your download of " + url + " has begun.");
                // Add as notification
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
            }
        }
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
    }
}