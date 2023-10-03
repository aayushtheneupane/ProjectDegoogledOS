package com.android.settingslib.widget;

import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.Comparator;

public class BarViewInfo implements Comparable<BarViewInfo> {
    private View.OnClickListener mClickListener;
    private CharSequence mContentDescription;
    /* access modifiers changed from: private */
    public int mHeight;
    private final Drawable mIcon;
    private int mNormalizedHeight;
    private CharSequence mSummary;
    private CharSequence mTitle;

    public BarViewInfo(Drawable drawable, int i, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        this.mIcon = drawable;
        this.mHeight = i;
        this.mTitle = charSequence;
        this.mSummary = charSequence2;
        this.mContentDescription = charSequence3;
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        this.mClickListener = onClickListener;
    }

    public int compareTo(BarViewInfo barViewInfo) {
        return Comparator.comparingInt($$Lambda$BarViewInfo$0E64JyWB2WmVqNcEtw_jyuLCMME.INSTANCE).compare(barViewInfo, this);
    }

    /* access modifiers changed from: package-private */
    public Drawable getIcon() {
        return this.mIcon;
    }

    /* access modifiers changed from: package-private */
    public int getHeight() {
        return this.mHeight;
    }

    /* access modifiers changed from: package-private */
    public View.OnClickListener getClickListener() {
        return this.mClickListener;
    }

    /* access modifiers changed from: package-private */
    public CharSequence getTitle() {
        return this.mTitle;
    }

    /* access modifiers changed from: package-private */
    public CharSequence getSummary() {
        return this.mSummary;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    /* access modifiers changed from: package-private */
    public void setNormalizedHeight(int i) {
        this.mNormalizedHeight = i;
    }

    /* access modifiers changed from: package-private */
    public int getNormalizedHeight() {
        return this.mNormalizedHeight;
    }
}
