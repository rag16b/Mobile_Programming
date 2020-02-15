package edu.fsu.cs.hw3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UrlListFragment urlListFrag = new UrlListFragment();
        MyWebFragment webFrag = new MyWebFragment();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();

        // Adding the UrlListFragment to it's container in the main activity.
        //Bundle urlListExtras = new Bundle();
        //urlListExtras.putInt("selected",15);
        //urlListFrag.setArguments(urlListExtras);
        trans.add(R.id.frame_urlList, urlListFrag);

        // Adding the MyWebFragment to it's container in the main activity.
        //Bundle webExtras = new Bundle();
        //webExtras.putInt("selected",15);
        //webFrag.setArguments(webExtras);
        trans.add(R.id.frame_webView, webFrag);

        trans.commit();
    }
}
