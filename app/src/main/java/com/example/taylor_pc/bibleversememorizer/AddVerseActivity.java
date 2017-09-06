package com.example.taylor_pc.bibleversememorizer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddVerseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_verse);

        final Button btnAddVerse = (Button )findViewById(R.id.btn_add_verse);
        btnAddVerse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                EditText inputVerseReference = (EditText) findViewById(R.id.input_verse_reference);
                EditText inputVerseContent = (EditText) findViewById(R.id.input_verse_content);

                BibleVerse newVerse = new BibleVerse(
                        inputVerseReference.getText().toString(),
                        inputVerseContent.getText().toString());

                final BibleVerseHandler bibleVerseHandler = new BibleVerseHandler(AddVerseActivity.this);
                bibleVerseHandler.addBibleVerse(newVerse);

                Toast.makeText(AddVerseActivity.this, "Successfully added new Bible verse.",
                Toast.LENGTH_LONG).show();

                new CountDownTimer(500, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        finish();
                    }

                }.start();

            }
        });

    }
}
