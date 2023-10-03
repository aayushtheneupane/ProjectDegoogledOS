package com.android.phone.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import com.android.phone.common.R$styleable;
import com.android.phone.common.util.ViewUtil;

public class ResizingTextEditText extends EditText {
    private boolean mIsResizeEnabled = true;
    private final int mMinTextSize;
    private final int mOriginalTextSize = ((int) getTextSize());

    public ResizingTextEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ResizingText);
        this.mMinTextSize = (int) obtainStyledAttributes.getDimension(0, (float) this.mOriginalTextSize);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (this.mIsResizeEnabled) {
            ViewUtil.resizeText(this, this.mOriginalTextSize, this.mMinTextSize);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mIsResizeEnabled) {
            ViewUtil.resizeText(this, this.mOriginalTextSize, this.mMinTextSize);
        }
    }
}
