package com.android.p032ex.chips.p033a;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.style.ReplacementSpan;
import com.android.p032ex.chips.C0699ra;

/* renamed from: com.android.ex.chips.a.c */
public class C0661c extends ReplacementSpan implements C0660b {

    /* renamed from: Le */
    private static final Rect f769Le = new Rect(0, 0, 0, 0);
    private final C0663e mDelegate;

    public C0661c(C0699ra raVar) {
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
        return f769Le;
    }

    public void draw(Canvas canvas) {
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
    }

    /* renamed from: ga */
    public long mo5467ga() {
        return this.mDelegate.mo5483ga();
    }

    public Rect getBounds() {
        return f769Le;
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

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return 0;
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
}
