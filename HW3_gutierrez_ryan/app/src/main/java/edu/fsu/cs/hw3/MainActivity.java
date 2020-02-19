package edu.fsu.cs.hw3;

/*
    REFERENCES *************************************************************************************
    http://www.java2s.com/example/android/android-os/check-if-a-bundle-contains-a-key.html  // helped when dealing with adding element to listview
    https://www.freakyjolly.com/add-list-item-in-listview-android-example/  // helped with listview in general
    https://www.youtube.com/watch?v=pke6sMxOsuw     // helped with broadcast receiver
    https://www.youtube.com/watch?v=KTPOvXh5a8M     // helped with broadcast receiver
    https://www.youtube.com/watch?v=i22INe14JUc     // helped with fragment communication

 */

import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
//import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends Activity implements UrlListFragment.UrlListFragListener {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private UrlListFragment urlListFrag;
    private MyWebFragment webFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlListFrag = new UrlListFragment();
        webFrag = new MyWebFragment();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();

        // Adding the UrlListFragment to it's container in the main activity.
        trans.add(R.id.frame_urlList, urlListFrag);
        // Adding the MyWebFragment to it's container in the main activity.
        trans.add(R.id.frame_webView, webFrag);
        // Committing
        trans.commit();

        // Requesting permission for access to messages
        if (ContextCompat.checkSelfPermission( MainActivity.this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.RECEIVE_SMS }, 100);
        }
    }

    @Override
    public void onUrlSelect(String input) {
        webFrag.updateUrl(input);
    }

    // Overriding onReceive for broadcast receiver
    private SmsReceiver receiver = new SmsReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            UrlListFragment newListFrag = new UrlListFragment();
            // creating bundle to send to UrlListFragment
            Bundle bundle = new Bundle();
            bundle.putString("message", message);
            newListFrag.setArguments(bundle);
            // replacing the fragment
            FragmentManager manager = getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.replace(R.id.frame_urlList, newListFrag);
            trans.commit();
            // show new url on webview fragment
            //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            webFrag.updateUrl(message);
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
