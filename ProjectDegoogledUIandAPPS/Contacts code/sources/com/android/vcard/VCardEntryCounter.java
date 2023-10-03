package com.android.vcard;

public class VCardEntryCounter implements VCardInterpreter {
    private int mCount;

    public void onEntryStarted() {
    }

    public void onPropertyCreated(VCardProperty vCardProperty) {
    }

    public void onVCardEnded() {
    }

    public void onVCardStarted() {
    }

    public int getCount() {
        return this.mCount;
    }

    public void onEntryEnded() {
        this.mCount++;
    }
}
