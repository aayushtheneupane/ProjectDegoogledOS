package com.android.settings.deviceinfo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.android.settings.R$styleable;
import java.util.Collection;

public class PercentageBarChart extends View {
    private final Paint mEmptyPaint = new Paint();
    private Collection<Entry> mEntries;
    private int mMinTickWidth = 1;

    public static class Entry implements Comparable<Entry> {
        public final int order;
        public final Paint paint;
        public final float percentage;

        public int compareTo(Entry entry) {
            return this.order - entry.order;
        }
    }

    public PercentageBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PercentageBarChart);
        this.mMinTickWidth = obtainStyledAttributes.getDimensionPixelSize(1, 1);
        int color = obtainStyledAttributes.getColor(0, -16777216);
        obtainStyledAttributes.recycle();
        this.mEmptyPaint.setColor(color);
        this.mEmptyPaint.setStyle(Paint.Style.FILL);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f;
        float f2;
        float f3;
        float f4;
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int width = getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        int i = width - paddingLeft;
        if (isLayoutRtl()) {
            float f5 = (float) width;
            Collection<Entry> collection = this.mEntries;
            if (collection != null) {
                float f6 = f5;
                for (Entry next : collection) {
                    float f7 = next.percentage;
                    if (f7 == 0.0f) {
                        f4 = 0.0f;
                    } else {
                        f4 = Math.max((float) this.mMinTickWidth, ((float) i) * f7);
                    }
                    float f8 = f6 - f4;
                    float f9 = (float) paddingLeft;
                    if (f8 < f9) {
                        canvas.drawRect(f9, (float) paddingTop, f6, (float) height, next.paint);
                        return;
                    }
                    canvas.drawRect(f8, (float) paddingTop, f6, (float) height, next.paint);
                    f6 = f8;
                }
                f3 = f6;
            } else {
                f3 = f5;
            }
            canvas.drawRect((float) paddingLeft, (float) paddingTop, f3, (float) height, this.mEmptyPaint);
            return;
        }
        float f10 = (float) paddingLeft;
        Collection<Entry> collection2 = this.mEntries;
        if (collection2 != null) {
            float f11 = f10;
            for (Entry next2 : collection2) {
                float f12 = next2.percentage;
                if (f12 == 0.0f) {
                    f2 = 0.0f;
                } else {
                    f2 = Math.max((float) this.mMinTickWidth, ((float) i) * f12);
                }
                float f13 = f11 + f2;
                float f14 = (float) width;
                if (f13 > f14) {
                    canvas.drawRect(f11, (float) paddingTop, f14, (float) height, next2.paint);
                    return;
                }
                canvas.drawRect(f11, (float) paddingTop, f13, (float) height, next2.paint);
                f11 = f13;
            }
            f = f11;
        } else {
            f = f10;
        }
        canvas.drawRect(f, (float) paddingTop, (float) width, (float) height, this.mEmptyPaint);
    }

    public void setBackgroundColor(int i) {
        this.mEmptyPaint.setColor(i);
    }

    public void setEntries(Collection<Entry> collection) {
        this.mEntries = collection;
    }
}
