package com.android.p032ex.photo;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;

/* renamed from: com.android.ex.photo.b */
public class C0713b {
    private final ActionBar mActionBar;

    public C0713b(ActionBar actionBar) {
        this.mActionBar = actionBar;
    }

    /* renamed from: Bd */
    public void mo5718Bd() {
        this.mActionBar.setDisplayOptions(8, 8);
    }

    /* renamed from: b */
    public void mo5719b(C0734o oVar) {
        this.mActionBar.addOnMenuVisibilityListener(new C0709a(this, oVar));
    }

    public void setDisplayHomeAsUpEnabled(boolean z) {
        this.mActionBar.setDisplayHomeAsUpEnabled(z);
    }

    public void setLogo(Drawable drawable) {
        this.mActionBar.setLogo(drawable);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mActionBar.setSubtitle(charSequence);
    }

    public void setTitle(CharSequence charSequence) {
        this.mActionBar.setTitle(charSequence);
    }
}
