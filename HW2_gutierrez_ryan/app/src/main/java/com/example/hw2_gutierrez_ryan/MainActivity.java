package com.example.hw2_gutierrez_ryan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected Button linButton;
    protected Button relButton;
    protected Button tabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linButton = (Button) findViewById(R.id.button_linear);
        relButton = (Button) findViewById(R.id.button_relative);
        tabButton = (Button) findViewById(R.id.button_table);
    }

    public void change2Linear(View v) {
        Intent intent = new Intent(MainActivity.this, LinearLayoutActivity.class);
        startActivity(intent);
    }

    public void change2Relative(View v) {
        Intent intent = new Intent(MainActivity.this, RelativeLayoutActivity.class);
        startActivity(intent);
    }

    public void change2Table(View v) {
        Intent intent = new Intent(MainActivity.this, TableLayoutActivity.class);
        startActivity(intent);
    }
}