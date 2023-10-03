package com.android.dialer.dialpadview;

import android.telephony.PhoneNumberUtils;
import android.text.Spanned;
import android.text.method.DialerKeyListener;

public class UnicodeDialerKeyListener extends DialerKeyListener {
    public static final UnicodeDialerKeyListener INSTANCE = new UnicodeDialerKeyListener();

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        String convertKeypadLettersToDigits = PhoneNumberUtils.convertKeypadLettersToDigits(PhoneNumberUtils.replaceUnicodeDigits(charSequence.toString()));
        CharSequence filter = super.filter(convertKeypadLettersToDigits, i, i2, spanned, i3, i4);
        if (filter != null) {
            return filter;
        }
        if (charSequence.equals(convertKeypadLettersToDigits)) {
            return null;
        }
        return convertKeypadLettersToDigits.subSequence(i, i2);
    }
}
