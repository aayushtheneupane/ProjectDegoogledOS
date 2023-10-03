package com.android.contacts.compat;

import android.telephony.PhoneNumberUtils;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber$PhoneNumber;

public class PhoneNumberUtilsCompat {
    public static String normalizeNumber(String str) {
        if (CompatUtils.isLollipopCompatible()) {
            return PhoneNumberUtils.normalizeNumber(str);
        }
        return normalizeNumberInternal(str);
    }

    private static String normalizeNumberInternal(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            int digit = Character.digit(charAt, 10);
            if (digit != -1) {
                sb.append(digit);
            } else if (sb.length() == 0 && charAt == '+') {
                sb.append(charAt);
            } else if ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z')) {
                return normalizeNumber(PhoneNumberUtils.convertKeypadLettersToDigits(str));
            }
        }
        return sb.toString();
    }

    public static String formatNumber(String str, String str2, String str3) {
        if (CompatUtils.isLollipopCompatible()) {
            return PhoneNumberUtils.formatNumber(str, str2, str3);
        }
        return PhoneNumberUtils.formatNumber(str);
    }

    public static CharSequence createTtsSpannable(CharSequence charSequence) {
        if (CompatUtils.isMarshmallowCompatible()) {
            return PhoneNumberUtils.createTtsSpannable(charSequence);
        }
        return createTtsSpannableInternal(charSequence);
    }

    public static TtsSpan createTtsSpan(String str) {
        if (CompatUtils.isMarshmallowCompatible()) {
            return PhoneNumberUtils.createTtsSpan(str);
        }
        if (CompatUtils.isLollipopCompatible()) {
            return createTtsSpanLollipop(str);
        }
        return null;
    }

    private static CharSequence createTtsSpannableInternal(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        Spannable newSpannable = Spannable.Factory.getInstance().newSpannable(charSequence);
        addTtsSpanInternal(newSpannable, 0, newSpannable.length());
        return newSpannable;
    }

    public static void addTtsSpan(Spannable spannable, int i, int i2) {
        if (CompatUtils.isMarshmallowCompatible()) {
            PhoneNumberUtils.addTtsSpan(spannable, i, i2);
        } else {
            addTtsSpanInternal(spannable, i, i2);
        }
    }

    private static void addTtsSpanInternal(Spannable spannable, int i, int i2) {
        spannable.setSpan(createTtsSpan(spannable.subSequence(i, i2).toString()), i, i2, 33);
    }

    private static TtsSpan createTtsSpanLollipop(String str) {
        Phonenumber$PhoneNumber phonenumber$PhoneNumber = null;
        if (str == null) {
            return null;
        }
        try {
            phonenumber$PhoneNumber = PhoneNumberUtil.getInstance().parse(str, (String) null);
        } catch (NumberParseException unused) {
        }
        TtsSpan.TelephoneBuilder telephoneBuilder = new TtsSpan.TelephoneBuilder();
        if (phonenumber$PhoneNumber == null) {
            telephoneBuilder.setNumberParts(splitAtNonNumerics(str));
        } else {
            if (phonenumber$PhoneNumber.hasCountryCode()) {
                telephoneBuilder.setCountryCode(Integer.toString(phonenumber$PhoneNumber.getCountryCode()));
            }
            telephoneBuilder.setNumberParts(Long.toString(phonenumber$PhoneNumber.getNationalNumber()));
        }
        return telephoneBuilder.build();
    }

    private static String splitAtNonNumerics(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(charSequence.length());
        int i = 0;
        while (true) {
            String str = " ";
            if (i >= charSequence.length()) {
                return sb.toString().replaceAll(" +", str).trim();
            }
            sb.append(PhoneNumberUtils.isISODigit(charSequence.charAt(i)) ? Character.valueOf(charSequence.charAt(i)) : str);
            i++;
        }
    }
}
