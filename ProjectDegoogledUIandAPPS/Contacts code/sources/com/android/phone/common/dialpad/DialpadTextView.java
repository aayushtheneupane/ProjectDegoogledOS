package com.android.phone.common.dialpad;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

public class DialpadTextView extends TextView {
    private Rect mTextBounds = new Rect();
    private String mTextStr;

    public DialpadTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void draw(Canvas canvas) {
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        String str = this.mTextStr;
        Rect rect = this.mTextBounds;
        canvas.drawText(str, (float) (-rect.left), (float) (-rect.top), paint);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mTextStr = getText().toString();
        TextPaint paint = getPaint();
        String str = this.mTextStr;
        paint.getTextBounds(str, 0, str.length(), this.mTextBounds);
        setMeasuredDimension(TextView.resolveSize(this.mTextBounds.width(), i), TextView.resolveSize(this.mTextBounds.height(), i2));
    }
}
