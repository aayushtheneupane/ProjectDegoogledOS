package com.google.i18n.phonenumbers.internal;

import com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc;
import java.util.regex.Matcher;

/* renamed from: com.google.i18n.phonenumbers.internal.a */
public final class C1735a {

    /* renamed from: FO */
    private final C1737c f2593FO = new C1737c(100);

    private C1735a() {
    }

    public static C1735a create() {
        return new C1735a();
    }

    /* renamed from: a */
    public boolean mo9490a(CharSequence charSequence, Phonemetadata$PhoneNumberDesc phonemetadata$PhoneNumberDesc, boolean z) {
        String nationalNumberPattern = phonemetadata$PhoneNumberDesc.getNationalNumberPattern();
        if (nationalNumberPattern.length() == 0) {
            return false;
        }
        Matcher matcher = this.f2593FO.getPatternForRegex(nationalNumberPattern).matcher(charSequence);
        if (!matcher.lookingAt()) {
            return false;
        }
        if (matcher.matches()) {
            z = true;
        }
        return z;
    }
}
