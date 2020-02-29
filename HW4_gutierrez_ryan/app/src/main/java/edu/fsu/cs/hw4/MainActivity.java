package edu.fsu.cs.hw4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

// Used this as reference for Overflow menu: https://www.youtube.com/watch?v=oh4YOj9VkVE
// Used this as reference for Download Dialog: https://www.youtube.com/watch?v=ARezg1D9Zd0

public class MainActivity extends AppCompatActivity implements DownloadDialog.DownloadDialogListener {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO setup UI


        // TODO Do we need to handle any Extras?
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.of_download:
                // whatever would go in opendialog()
                DownloadDialog downloadDialog = new DownloadDialog();
                downloadDialog.show(getSupportFragmentManager(), "Download Dialog");
                return true;
            case R.id.of_exit:
                finish();
                System.exit(0);
                // TODO still need to make sure MediaPlayer service stops and the download service does not stop
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void applyURL(String url) {
        URL = url;
        //Toast.makeText(this, URL, Toast.LENGTH_LONG).show();
    }

    // onClick method for Play button
    public void play(View v) {
        // TODO play MediaPlayer
    }

    // onclick method for Pause button
    public void pause(View v) {
        // TODO pause MediaPlayer
    }

    // onClick method for Stop button
    public void stop(View v) {
        // TODO stop MediaPlayer
    }
}