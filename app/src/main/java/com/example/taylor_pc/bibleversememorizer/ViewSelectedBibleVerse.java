package com.example.taylor_pc.bibleversememorizer;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ViewSelectedBibleVerse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_bible_verse);

        Intent verseData = getIntent();
        String verseReference = verseData.getStringExtra("verse_reference");
        String verseContent = verseData.getStringExtra("verse_content");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(verseReference);

        TextView verseDisplay = (TextView) findViewById(R.id.verse_content);
        verseDisplay.setText(verseContent);

    }
}
