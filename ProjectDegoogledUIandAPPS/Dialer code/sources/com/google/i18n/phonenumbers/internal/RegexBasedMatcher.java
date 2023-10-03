package com.google.i18n.phonenumbers.internal;

import com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc;
import java.util.regex.Matcher;

public final class RegexBasedMatcher {
    private final RegexCache regexCache = new RegexCache(100);

    private RegexBasedMatcher() {
    }

    public static RegexBasedMatcher create() {
        return new RegexBasedMatcher();
    }

    public boolean matchNationalNumber(CharSequence charSequence, Phonemetadata$PhoneNumberDesc phonemetadata$PhoneNumberDesc, boolean z) {
        String nationalNumberPattern = phonemetadata$PhoneNumberDesc.getNationalNumberPattern();
        if (nationalNumberPattern.length() == 0) {
            return false;
        }
        Matcher matcher = this.regexCache.getPatternForRegex(nationalNumberPattern).matcher(charSequence);
        if (!matcher.lookingAt()) {
            return false;
        }
        if (matcher.matches()) {
            z = true;
        }
        return z;
    }
}
