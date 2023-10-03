package com.google.android.apps.photosgo.media;

import android.support.p002v7.widget.RecyclerView;

/* compiled from: PG */
public enum Filter$Category implements iiz {
    UNKNOWN_CATEGORY(0),
    PEOPLE(1),
    SELFIE(2),
    NATURE(3),
    ANIMAL(4),
    SCREENSHOT(5),
    DOCUMENT(6),
    VIDEO(7),
    MOVIE(8),
    FOOD(9);
    
    public static final ija internalValueMap = null;
    public final int value;

    static {
        internalValueMap = new cxa();
    }

    public static Filter$Category forNumber(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_CATEGORY;
            case 1:
                return PEOPLE;
            case RecyclerView.SCROLL_STATE_SETTLING:
                return SELFIE;
            case 3:
                return NATURE;
            case 4:
                return ANIMAL;
            case 5:
                return SCREENSHOT;
            case 6:
                return DOCUMENT;
            case 7:
                return VIDEO;
            case 8:
                return MOVIE;
            case 9:
                return FOOD;
            default:
                return null;
        }
    }

    public static ijb internalGetVerifier() {
        return cxb.f5882a;
    }

    public final int getNumber() {
        return this.value;
    }

    public final String toString() {
        return Integer.toString(getNumber());
    }

    private Filter$Category(int i) {
        this.value = i;
    }
}
