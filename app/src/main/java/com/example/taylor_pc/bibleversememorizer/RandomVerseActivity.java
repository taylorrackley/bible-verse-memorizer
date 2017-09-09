package com.example.taylor_pc.bibleversememorizer;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomVerseActivity extends AppCompatActivity {

    BibleVerseHandler bibleVerseHandler;
    List<BibleVerse> bibleVerses;
    boolean btnToggle = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_verse);

        bibleVerseHandler = new BibleVerseHandler(this);
        bibleVerses = new ArrayList<>(Arrays.asList(bibleVerseHandler.getAllBibleVerses()));

        loadActivity();

        final Button btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(btnToggle) {
                    TextView verseContent = (TextView) findViewById(R.id.verse_content);
                    verseContent.setVisibility(View.VISIBLE);
                    btnSubmit.setText("NEXT");
                    btnToggle = !btnToggle;
                } else {
                    loadActivity();
                    btnToggle = !btnToggle;
                }

            }
        });

        // Return Button
        FloatingActionButton btnReturn = (FloatingActionButton) findViewById(R.id.btn_return);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void loadActivity() {

        if(bibleVerses == null || bibleVerses.size() < 1) {
            bibleVerses =  new ArrayList<>(Arrays.asList(bibleVerseHandler.getAllBibleVerses()));
        }

        TextView verseReference = (TextView) findViewById(R.id.verse_reference);
        TextView verseContent = (TextView) findViewById(R.id.verse_content);
        Button btnSubmit = (Button) findViewById(R.id.btn_submit);

        Random random = new Random();
        int randInt = random.nextInt(bibleVerses.size());
        BibleVerse randomBibleVerse = bibleVerses.get( randInt );
        bibleVerses.remove(randomBibleVerse);

        verseReference.setText(randomBibleVerse.getReference());
        verseContent.setText(randomBibleVerse.getContent());
        verseContent.setVisibility(View.INVISIBLE);
        btnSubmit.setText(randInt+" SHOW");

    }

}
