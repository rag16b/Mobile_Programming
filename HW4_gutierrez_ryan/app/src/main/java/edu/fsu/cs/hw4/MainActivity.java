package edu.fsu.cs.hw4;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

// Used this as reference for Overflow menu: https://www.youtube.com/watch?v=oh4YOj9VkVE
// Used this as reference for Download Dialog: https://www.youtube.com/watch?v=ARezg1D9Zd0

public class MainActivity extends AppCompatActivity implements DownloadDialog.DownloadDialogListener {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private ImageButton playPauseButton;
    private ImageButton stopButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playPauseButton = (ImageButton) findViewById(R.id.imageButton_playpause);
        stopButton = (ImageButton) findViewById(R.id.imageButton_stop);

        playPauseButton.setVisibility(View.GONE);
        stopButton.setVisibility(View.GONE);

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
        Intent intent = new Intent(this, DownloadSongService.class);
        intent.setAction("edu.fsu.cs.hw4.action.DOWNLOAD");
        intent.putExtra("edu.fsu.cs.hw4.extra.URL", url);
        startService(intent);
    }

    /*public void setButtons(){
        playPauseButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.VISIBLE);
    }*/

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