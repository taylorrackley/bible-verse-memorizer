package com.example.taylor_pc.bibleversememorizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewVersesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_verses);

        String[] verses = getVerses();

        Button[] verseButtons = new Button[verses.length];
        LinearLayout verseList = (LinearLayout) findViewById(R.id.verse_list);

        for(int x = 0; x < verses.length; x++) {
            verseButtons[x] = new Button(this);
            verseButtons[x].setId(x);
            verseButtons[x].setText(verses[x]);
            verseList.addView(verseButtons[x]);
        }

    }

    public String[] getVerses() {

        String[] verses = new String[] {"Deuteronomy 26:5","2 Timothy 3:16-17"};

        return verses;

    }

}
