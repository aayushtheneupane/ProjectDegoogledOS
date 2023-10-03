package com.android.dialer.dialpadview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

public class DialpadTextView extends TextView {
    private Rect textBounds = new Rect();
    private String textStr;

    public DialpadTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void draw(Canvas canvas) {
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        String str = this.textStr;
        Rect rect = this.textBounds;
        canvas.drawText(str, (float) (-rect.left), (float) (-rect.top), paint);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.textStr = getText().toString();
        TextPaint paint = getPaint();
        String str = this.textStr;
        paint.getTextBounds(str, 0, str.length(), this.textBounds);
        setMeasuredDimension(TextView.resolveSize(this.textBounds.width(), i), TextView.resolveSize(this.textBounds.height(), i2));
    }
}
