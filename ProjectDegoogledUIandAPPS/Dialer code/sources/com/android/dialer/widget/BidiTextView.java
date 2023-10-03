package com.android.dialer.widget;

import android.content.Context;
import android.support.p002v7.appcompat.R$style;
import android.util.AttributeSet;
import android.widget.TextView;

public final class BidiTextView extends TextView {
    public BidiTextView(Context context) {
        super(context);
    }

    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(R$style.format(charSequence), bufferType);
    }

    public BidiTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
