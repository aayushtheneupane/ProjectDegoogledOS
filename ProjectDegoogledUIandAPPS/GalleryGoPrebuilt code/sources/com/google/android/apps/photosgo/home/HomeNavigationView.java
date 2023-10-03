package com.google.android.apps.photosgo.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public final class HomeNavigationView extends ViewGroup {

    /* renamed from: a */
    public final View f4864a;

    /* renamed from: b */
    public final View f4865b;

    /* renamed from: c */
    private final int f4866c;

    public HomeNavigationView(Context context) {
        this(context, (AttributeSet) null);
    }

    public HomeNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HomeNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.home_navigation_view, this, true);
        this.f4864a = findViewById(R.id.home_photos);
        this.f4865b = findViewById(R.id.home_folders);
        this.f4866c = getResources().getDimensionPixelSize(R.dimen.home_navigation_item_width);
        setClickable(true);
        mo3383a();
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = (i3 - i) / 2;
        int i6 = 0;
        for (int i7 = 0; i7 < 2; i7++) {
            View childAt = getChildAt(C0340mj.m14714f(this) == 1 ? 1 - i7 : i7);
            int measuredWidth = childAt.getMeasuredWidth();
            int i8 = (i5 - measuredWidth) + i6;
            childAt.layout(i8, 0, i8 + measuredWidth, i4 - i2);
            i6 += measuredWidth;
        }
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int min = Math.min(size / getChildCount(), this.f4866c);
        int i3 = 0;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            View childAt = getChildAt(i4);
            childAt.measure(View.MeasureSpec.makeMeasureSpec(min, 1073741824), i2);
            childAt.getLayoutParams().width = childAt.getMeasuredWidth();
            i3 += childAt.getMeasuredWidth();
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(i3, getSuggestedMinimumWidth()), i, 0), View.resolveSizeAndState(size2, i2, 0));
    }

    /* renamed from: b */
    public final void mo3384b() {
        this.f4864a.setSelected(false);
        this.f4865b.setSelected(true);
    }

    /* renamed from: a */
    public final void mo3383a() {
        this.f4864a.setSelected(true);
        this.f4865b.setSelected(false);
    }
}
