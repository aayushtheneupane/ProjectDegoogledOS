package com.android.p032ex.chips.p033a;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.android.p032ex.chips.C0699ra;

/* renamed from: com.android.ex.chips.a.f */
public class C0664f extends C0662d implements C0660b {

    /* renamed from: Oe */
    private Rect f778Oe = new Rect(0, 0, 0, 0);
    private final C0663e mDelegate;

    public C0664f(Drawable drawable, C0699ra raVar) {
        super(drawable);
        this.mDelegate = new C0663e(raVar);
    }

    /* renamed from: R */
    public Long mo5463R() {
        return this.mDelegate.mo5481R();
    }

    /* renamed from: a */
    public void mo5464a(String str) {
        this.mDelegate.mo5482a(str);
    }

    /* renamed from: aa */
    public Rect mo5465aa() {
        return this.f778Oe;
    }

    public void draw(Canvas canvas) {
        this.mDrawable.draw(canvas);
    }

    /* renamed from: ga */
    public long mo5467ga() {
        return this.mDelegate.mo5483ga();
    }

    public Rect getBounds() {
        return this.mDrawable.getBounds();
    }

    public long getContactId() {
        return this.mDelegate.getContactId();
    }

    public C0699ra getEntry() {
        return this.mDelegate.getEntry();
    }

    public CharSequence getOriginalText() {
        return this.mDelegate.getOriginalText();
    }

    public CharSequence getValue() {
        return this.mDelegate.getValue();
    }

    public boolean isSelected() {
        return this.mDelegate.isSelected();
    }

    /* renamed from: m */
    public String mo5474m() {
        return this.mDelegate.mo5489m();
    }

    public String toString() {
        return this.mDelegate.toString();
    }

    /* renamed from: a */
    public void mo5491a(Rect rect) {
        this.f778Oe = rect;
    }
}
