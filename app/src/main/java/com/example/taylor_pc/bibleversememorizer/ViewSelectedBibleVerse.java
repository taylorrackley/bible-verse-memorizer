package com.example.taylor_pc.bibleversememorizer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewSelectedBibleVerse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_bible_verse);

        loadActivity();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            loadActivity();
        }
    }

    public void loadActivity() {

        final int verseID = getIntent().getIntExtra("verse_id", 1);

        final BibleVerseHandler bibleVerseHandler = new BibleVerseHandler(this);
        BibleVerse selectedVerse = bibleVerseHandler.getBibleVerse(verseID);

        final String verseReference = selectedVerse.getReference();
        final String verseContent = selectedVerse.getContent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(verseReference);

        TextView verseDisplay = (TextView) findViewById(R.id.verse_content);
        verseDisplay.setText(verseContent);

        // Return Button
        FloatingActionButton btnReturn = (FloatingActionButton) findViewById(R.id.btn_return);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        // Edit Button
        FloatingActionButton btnEditVerse = (FloatingActionButton) findViewById(R.id.btn_edit_verse);
        btnEditVerse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(ViewSelectedBibleVerse.this, EditBibleVerse.class);
                Bundle extras = new Bundle();
                extras.putInt("verse_id", verseID);

                intent.putExtras(extras);
                startActivityForResult(intent, 1);

            }
        });

        // Delete Button
        FloatingActionButton btnDeleteVerse = (FloatingActionButton) findViewById(R.id.btn_delete_verse);
        btnDeleteVerse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new AlertDialog.Builder(ViewSelectedBibleVerse.this)
                        .setTitle(verseReference)
                        .setMessage("Are you sure you want to delete this verse?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                bibleVerseHandler.deleteBibleVerse(verseID);

                                Toast.makeText(ViewSelectedBibleVerse.this, verseReference+" deleted", Toast.LENGTH_LONG).show();

                                new CountDownTimer(500, 500) {

                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    @Override
                                    public void onFinish() {

                                        setResult(RESULT_OK);
                                        finish();

                                    }

                                }.start();

                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });

    }

}
