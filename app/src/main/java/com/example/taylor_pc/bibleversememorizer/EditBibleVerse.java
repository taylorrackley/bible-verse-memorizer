package com.example.taylor_pc.bibleversememorizer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditBibleVerse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bible_verse);

        EditText viewVerseReference = (EditText) findViewById(R.id.input_verse_reference);
        EditText viewVerseContent = (EditText) findViewById(R.id.input_verse_content);

        final int verseID = getIntent().getIntExtra("verse_id", 1);

        BibleVerseHandler bibleVerseHandler = new BibleVerseHandler(this);
        BibleVerse selectedVerse = bibleVerseHandler.getBibleVerse(verseID);

        String verseReference = selectedVerse.getReference();
        String verseContent = selectedVerse.getContent();

        viewVerseReference.setText(verseReference);
        viewVerseContent.setText(verseContent);

        Button btnUpdateVerse = (Button )findViewById(R.id.btn_update_verse);
        btnUpdateVerse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                updateVerse(view, verseID);

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

    private void updateVerse(View view, int verseID) {

        EditText viewVerseReference = (EditText) findViewById(R.id.input_verse_reference);
        EditText viewVerseContent = (EditText) findViewById(R.id.input_verse_content);

        BibleVerseHandler bibleVerseHandler = new BibleVerseHandler(EditBibleVerse.this);

        BibleVerse updatedVerse = new BibleVerse(
                viewVerseReference.getText().toString(),
                viewVerseContent.getText().toString(),
                verseID);

        bibleVerseHandler.updateBibleVerse(updatedVerse);

        Toast.makeText(this, "Successfully Updated Bible Verse.",
                Toast.LENGTH_LONG).show();

        new CountDownTimer(500, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                setResult(RESULT_OK);
                finish();
            }

        }.start();


    }

}
