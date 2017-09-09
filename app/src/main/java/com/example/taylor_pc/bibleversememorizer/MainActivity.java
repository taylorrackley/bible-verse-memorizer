package com.example.taylor_pc.bibleversememorizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View contentMain = findViewById(R.id.content_main_view);

        Button btnViewVerses = contentMain.findViewById(R.id.view_all_verses);
        btnViewVerses.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                startViewVersesActivity();

            }
        });

        Button btnAddVerse = contentMain.findViewById(R.id.add_verse);
        btnAddVerse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                startAddVerseActivity();

            }
        });

        Button btnRandomVerse = contentMain.findViewById(R.id.random_verse);
        btnRandomVerse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startRandomVerseActivity();

            }
        });

    }

    public void startViewVersesActivity() {
        Intent intent = new Intent(this, ViewVersesActivity.class);
        startActivity(intent);
    }

    public void startAddVerseActivity() {
        Intent intent = new Intent(this, AddVerseActivity.class);
        startActivity(intent);
    }

    public void startRandomVerseActivity() {
        Intent intent = new Intent(this, RandomVerseActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
