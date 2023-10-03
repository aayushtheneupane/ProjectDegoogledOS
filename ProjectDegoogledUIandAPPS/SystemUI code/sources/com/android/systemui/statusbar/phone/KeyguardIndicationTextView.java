package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class KeyguardIndicationTextView extends TextView {
    public KeyguardIndicationTextView(Context context) {
        super(context);
    }

    public KeyguardIndicationTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KeyguardIndicationTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public KeyguardIndicationTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void switchIndication(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            setVisibility(4);
            return;
        }
        setVisibility(0);
        setText(charSequence);
    }

    public void switchIndication(int i) {
        switchIndication(getResources().getText(i));
    }
}
