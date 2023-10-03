package com.android.dialer.spannable;

import android.text.TextPaint;
import android.text.style.URLSpan;

final class UrlSpanWithoutUnderline extends URLSpan {
    UrlSpanWithoutUnderline(String str) {
        super(str);
    }

    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(false);
    }
}
