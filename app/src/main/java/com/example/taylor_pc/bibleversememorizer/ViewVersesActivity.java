package com.example.taylor_pc.bibleversememorizer;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ViewVersesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R. layout.activity_view_verses);

        loadActivity();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            loadActivity();
        }
    }

    public BibleVerse[] getVerses() {

        BibleVerseHandler bibleVerseHandler = new BibleVerseHandler(this);
        BibleVerse[] bibleVerses = bibleVerseHandler.getAllBibleVerses();

        return bibleVerses;

    }

    public void verseSelected(View view) {

        int btnId = view.getId();

        Intent intent = new Intent(this, ViewSelectedBibleVerse.class);
        Bundle extras = new Bundle();
        extras.putInt("verse_id",btnId);

        intent.putExtras(extras);
        startActivityForResult(intent, 1);

    }

    public void loadActivity() {

        BibleVerse[] verses = getVerses();

        Button[] verseButtons = new Button[verses.length];
        LinearLayout verseList = (LinearLayout) findViewById(R.id.verse_list);
        verseList.removeAllViews();

        for(int x = 0; x < verses.length; x++) {
            verseButtons[x] = new Button(this);
            verseButtons[x].setId(verses[x].getID());
            verseButtons[x].setText(verses[x].getReference());
            verseList.addView(verseButtons[x]);

            verseButtons[x].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    verseSelected(v);
                }
            });

        }

        if(verses.length == 0) {
            TextView noVersestext = new TextView(this);
            noVersestext.setText("No Verses Have Been Added");
            noVersestext.setPadding(50, 0, 0, 0);
            noVersestext.setTextSize(20);
            verseList.addView(noVersestext);
        }

        FloatingActionButton btnReturn = (FloatingActionButton) findViewById(R.id.btn_return);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

}
