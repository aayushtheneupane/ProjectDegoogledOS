package com.android.p032ex.photo;

import android.app.ActionBar;

/* renamed from: com.android.ex.photo.a */
class C0709a implements ActionBar.OnMenuVisibilityListener {
    private final C0734o mWrapped;

    public C0709a(C0713b bVar, C0734o oVar) {
        this.mWrapped = oVar;
    }

    public void onMenuVisibilityChanged(boolean z) {
        this.mWrapped.onMenuVisibilityChanged(z);
    }
}
