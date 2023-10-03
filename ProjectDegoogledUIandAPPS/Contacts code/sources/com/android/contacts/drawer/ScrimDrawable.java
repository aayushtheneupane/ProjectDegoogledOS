package com.android.contacts.drawer;

import android.graphics.drawable.ColorDrawable;

public class ScrimDrawable extends ColorDrawable {
    private int mIntrinsicHeight;

    public ScrimDrawable() {
        this(855638016);
    }

    public ScrimDrawable(int i) {
        super(i);
    }

    public int getIntrinsicHeight() {
        return this.mIntrinsicHeight;
    }

    public void setIntrinsicHeight(int i) {
        this.mIntrinsicHeight = i;
    }
}
