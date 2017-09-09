package com.example.taylor_pc.bibleversememorizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Taylor-PC on 9/5/2017.
 */

public class BibleVerseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bible_verse_manager";
    private static final String TABLE_BIBLE_VERSES = "bible_verses";
    private static final String KEY_ID = "id";
    private static final String KEY_VERSE_REFERENCE = "verse_reference";
    private static final String KEY_VERSE_CONTENT = "verse_content";

    public BibleVerseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_BIBLE_VERSES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_VERSE_REFERENCE + " TEXT,"
                + KEY_VERSE_CONTENT + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIBLE_VERSES);
        onCreate(db);
    }

    public void addBibleVerse(BibleVerse bibleVerse) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, bibleVerse.getID());
        values.put(KEY_VERSE_REFERENCE, bibleVerse.getReference());
        values.put(KEY_VERSE_CONTENT, bibleVerse.getContent());

        db.insert(TABLE_BIBLE_VERSES, null, values);
        db.close();

    }

    public void updateBibleVerse(BibleVerse bibleVerse) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VERSE_REFERENCE, bibleVerse.getReference());
        values.put(KEY_VERSE_CONTENT, bibleVerse.getContent());
        values.put(KEY_ID, bibleVerse.getID());

        db.update(TABLE_BIBLE_VERSES, values, KEY_ID + " = ?", new String[] { String.valueOf(bibleVerse.getID()) });
        db.close();

    }


    public BibleVerse[] getAllBibleVerses() {
        List<BibleVerse> bibleVerseList = new ArrayList<BibleVerse>();

        String selectQuery = "SELECT  * FROM " + TABLE_BIBLE_VERSES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BibleVerse verse = new BibleVerse(cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(0)));

                bibleVerseList.add(verse);

            } while (cursor.moveToNext());
        }

        BibleVerse[] bibleVerseArray = new BibleVerse[bibleVerseList.size()];
        bibleVerseArray = bibleVerseList.toArray(bibleVerseArray);

        db.close();

        return bibleVerseArray;
    }

    public BibleVerse getBibleVerse(int id) {

        BibleVerse verse;

        String selectQuery = "SELECT * FROM " + TABLE_BIBLE_VERSES + " WHERE " + KEY_ID + " = ? LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id) });

        if (cursor.moveToFirst()) {
            do {
                verse = new BibleVerse(cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(0)));

            } while (cursor.moveToNext());
        } else {
            verse = new BibleVerse("NULL", "NULL", 0);
        }

        return verse;

    }

    public void deleteBibleVerse(int verseID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BIBLE_VERSES, KEY_ID + " = ?", new String[] { String.valueOf(verseID) });
        db.close();
    }

    public int getNewID() {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + KEY_ID + " FROM " + TABLE_BIBLE_VERSES + " ORDER BY " + KEY_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null && cursor.moveToFirst()) {
            int newId = Integer.parseInt(cursor.getString(0))+1;
            db.close();
            return newId;
        } else {
            db.close();
            return 20;
        }

    }

}
