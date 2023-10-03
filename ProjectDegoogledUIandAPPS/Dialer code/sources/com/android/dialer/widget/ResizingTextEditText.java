package com.android.dialer.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import com.android.dialer.util.CallUtil;

public class ResizingTextEditText extends EditText {
    private final int minTextSize;
    private final int originalTextSize = ((int) getTextSize());

    public ResizingTextEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ResizingText);
        this.minTextSize = (int) obtainStyledAttributes.getDimension(0, (float) this.originalTextSize);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        CallUtil.resizeText(this, this.originalTextSize, this.minTextSize);
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        CallUtil.resizeText(this, this.originalTextSize, this.minTextSize);
    }
}
