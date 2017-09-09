package com.example.taylor_pc.bibleversememorizer;

import java.util.UUID;

/**
 * Created by Taylor-PC on 9/4/2017.
 */

public class BibleVerse {

    private int ID;
    private String verseReference;
    private String verseContent;

    public BibleVerse(String ref, String ver, int ID) {
        this.verseReference = ref;
        this.verseContent = ver;
        this.ID = ID;
    }

    public int getID() {
        return this.ID;
    }

    public String getReference() {
        return this.verseReference;
    }

    public String getContent() {
        return this.verseContent;
    }

}
