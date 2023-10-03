package com.google.android.material.internal;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public class CheckableImageButton extends C0531tl implements Checkable {

    /* renamed from: d */
    private static final int[] f5215d = {16842912};

    /* renamed from: a */
    public boolean f5216a;

    /* renamed from: b */
    public boolean f5217b;

    /* renamed from: c */
    public boolean f5218c;

    public final boolean isChecked() {
        return this.f5216a;
    }

    public CheckableImageButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.imageButtonStyle);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5217b = true;
        this.f5218c = true;
        C0340mj.m14698a((View) this, (C0315ll) new gfr(this));
    }

    public final int[] onCreateDrawableState(int i) {
        if (this.f5216a) {
            return mergeDrawableStates(super.onCreateDrawableState(i + f5215d.length), f5215d);
        }
        return super.onCreateDrawableState(i);
    }

    /* access modifiers changed from: protected */
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof gft)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        gft gft = (gft) parcelable;
        super.onRestoreInstanceState(gft.f15201b);
        setChecked(gft.f11179c);
    }

    /* access modifiers changed from: protected */
    public final Parcelable onSaveInstanceState() {
        gft gft = new gft(super.onSaveInstanceState());
        gft.f11179c = this.f5216a;
        return gft;
    }

    /* renamed from: a */
    public final void mo3663a(boolean z) {
        if (this.f5217b != z) {
            this.f5217b = z;
            sendAccessibilityEvent(0);
        }
    }

    public final void setChecked(boolean z) {
        if (this.f5217b && this.f5216a != z) {
            this.f5216a = z;
            refreshDrawableState();
            sendAccessibilityEvent(2048);
        }
    }

    public final void setPressed(boolean z) {
        if (this.f5218c) {
            super.setPressed(z);
        }
    }

    public final void toggle() {
        setChecked(!this.f5216a);
    }
}
