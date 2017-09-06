package com.example.taylor_pc.bibleversememorizer;

import java.util.UUID;

/**
 * Created by Taylor-PC on 9/4/2017.
 */

public class BibleVerse {

    private String verseReference;
    private String verseContent;

    public BibleVerse(String ref, String ver) {
        this.verseReference = ref;
        this.verseContent = ver;
    }

    public String getReference() {
        return this.verseReference;
    }

    public String getContent() {
        return this.verseContent;
    }

    public void setVerseReference(String ref) {
        this.verseReference = ref;
    }

    public void setVerseContent(String ver) {
        this.verseContent = ver;
    }

}
