package com.google.android.apps.photosgo.oneup.photo;

import android.content.Context;
import android.util.AttributeSet;
import p003j$.util.Optional;

/* compiled from: PG */
public final class PhotoView extends ish {

    /* renamed from: b */
    private ism f4883b;

    /* renamed from: c */
    private float f4884c;

    /* renamed from: d */
    private float f4885d;

    /* renamed from: e */
    private float f4886e;

    /* renamed from: f */
    private Optional f4887f;

    public PhotoView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4883b = null;
        this.f4884c = -1.0f;
        this.f4885d = -1.0f;
        this.f4886e = -1.0f;
        this.f4887f = Optional.empty();
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        mo3404a(this.f4883b);
        float f = this.f4884c;
        if (f != -1.0f) {
            float f2 = this.f4885d;
            if (f2 != -1.0f) {
                float f3 = this.f4886e;
                if (f3 != -1.0f) {
                    mo3403a(f, f2, f3);
                }
            }
        }
        if (this.f4887f.isPresent()) {
            mo3405a(((Boolean) this.f4887f.get()).booleanValue());
        }
    }

    /* renamed from: a */
    public final void mo3404a(ism ism) {
        this.f15001a.f15024j = ism;
        this.f4883b = ism;
    }

    /* renamed from: a */
    public final void mo3403a(float f, float f2, float f3) {
        isn isn = this.f15001a;
        if (f >= f2) {
            throw new IllegalArgumentException("Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
        } else if (f2 < f3) {
            isn.f15017c = f;
            isn.f15018d = f2;
            isn.f15019e = f3;
            this.f4884c = f;
            this.f4885d = f2;
            this.f4886e = f3;
        } else {
            throw new IllegalArgumentException("Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
        }
    }

    /* renamed from: a */
    public final void mo3405a(boolean z) {
        this.f15001a.mo9092a(z);
        this.f4887f = Optional.m16285of(Boolean.valueOf(z));
    }
}
