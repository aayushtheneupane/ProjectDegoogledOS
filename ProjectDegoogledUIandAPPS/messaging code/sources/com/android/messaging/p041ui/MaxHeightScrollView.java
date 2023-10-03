package com.android.messaging.p041ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ScrollView;
import com.android.messaging.C0970i;

/* renamed from: com.android.messaging.ui.MaxHeightScrollView */
public class MaxHeightScrollView extends ScrollView {
    private final int mMaxHeight;

    public MaxHeightScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0970i.MaxHeightScrollView, 0, 0);
        this.mMaxHeight = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mMaxHeight != -1) {
            setMeasuredDimension(getMeasuredWidth(), Math.min(getMeasuredHeight(), this.mMaxHeight));
        }
    }
}
