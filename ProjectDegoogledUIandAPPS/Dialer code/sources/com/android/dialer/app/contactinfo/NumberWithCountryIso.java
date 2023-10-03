package com.android.dialer.app.contactinfo;

import android.text.TextUtils;

public final class NumberWithCountryIso {
    public final String countryIso;
    public final String number;

    public NumberWithCountryIso(String str, String str2) {
        this.number = str;
        this.countryIso = str2;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof NumberWithCountryIso)) {
            return false;
        }
        NumberWithCountryIso numberWithCountryIso = (NumberWithCountryIso) obj;
        if (!TextUtils.equals(this.number, numberWithCountryIso.number) || !TextUtils.equals(this.countryIso, numberWithCountryIso.countryIso)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.number;
        int i = 0;
        int hashCode = str == null ? 0 : str.hashCode();
        String str2 = this.countryIso;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode ^ i;
    }
}
