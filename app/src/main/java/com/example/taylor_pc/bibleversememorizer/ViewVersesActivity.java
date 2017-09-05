package com.example.taylor_pc.bibleversememorizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ViewVersesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_verses);

        BibleVerse[] verses = getVerses();

        Button[] verseButtons = new Button[verses.length];
        LinearLayout verseList = (LinearLayout) findViewById(R.id.verse_list);

        for(int x = 0; x < verses.length; x++) {
            verseButtons[x] = new Button(this);
            verseButtons[x].setId(x);
            verseButtons[x].setText(verses[x].reference);
            verseList.addView(verseButtons[x]);

            verseButtons[x].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    verseSelected(v);
                }
            });

        }

    }

    public BibleVerse[] getVerses() {

        BibleVerse verse1 = new BibleVerse("Deuteronomy 26:5", "You shall answer and say before the Lord your God, ‘My father was a wandering Aramean, and he went down to Egypt and sojourned there, few in number; but there he became a great, mighty and populous nation.");
        BibleVerse verse2 = new BibleVerse("2 Timothy 3:16-17", "All Scripture is inspired by God and profitable for teaching, for reproof, for correction, for training in righteousness; so that the man of God may be adequate, equipped for every good work");

        BibleVerse[] verses = new BibleVerse[] {verse1, verse2};

        return verses;

    }

    public void verseSelected(View view) {

        int btnId = view.getId();
        BibleVerse selectedVerse = getVerses()[btnId];

        Intent intent = new Intent(this, ViewSelectedBibleVerse.class);
        intent.putExtra("verse_reference", selectedVerse.reference);
        intent.putExtra("verse_content", selectedVerse.verse);
        startActivity(intent);

//        Toast.makeText(this, selectedVerse.verse,
//                Toast.LENGTH_LONG).show();

    }

}
