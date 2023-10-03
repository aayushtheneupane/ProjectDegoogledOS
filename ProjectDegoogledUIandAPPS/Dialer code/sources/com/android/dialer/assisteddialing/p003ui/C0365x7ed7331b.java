package com.android.dialer.assisteddialing.p003ui;

import com.android.dialer.assisteddialing.p003ui.AssistedDialingSettingFragment;
import com.android.tools.p006r8.GeneratedOutlineSupport;

/* renamed from: com.android.dialer.assisteddialing.ui.AutoValue_AssistedDialingSettingFragment_DisplayNameAndCountryCodeTuple */
final class C0365x7ed7331b extends AssistedDialingSettingFragment.DisplayNameAndCountryCodeTuple {
    private final CharSequence countryCode;
    private final CharSequence countryDisplayname;

    C0365x7ed7331b(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence != null) {
            this.countryDisplayname = charSequence;
            if (charSequence2 != null) {
                this.countryCode = charSequence2;
                return;
            }
            throw new NullPointerException("Null countryCode");
        }
        throw new NullPointerException("Null countryDisplayname");
    }

    /* access modifiers changed from: package-private */
    public CharSequence countryCode() {
        return this.countryCode;
    }

    /* access modifiers changed from: package-private */
    public CharSequence countryDisplayname() {
        return this.countryDisplayname;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AssistedDialingSettingFragment.DisplayNameAndCountryCodeTuple)) {
            return false;
        }
        AssistedDialingSettingFragment.DisplayNameAndCountryCodeTuple displayNameAndCountryCodeTuple = (AssistedDialingSettingFragment.DisplayNameAndCountryCodeTuple) obj;
        if (!this.countryDisplayname.equals(displayNameAndCountryCodeTuple.countryDisplayname()) || !this.countryCode.equals(((C0365x7ed7331b) displayNameAndCountryCodeTuple).countryCode)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.countryCode.hashCode() ^ ((this.countryDisplayname.hashCode() ^ 1000003) * 1000003);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("DisplayNameAndCountryCodeTuple{countryDisplayname=");
        outline13.append(this.countryDisplayname);
        outline13.append(", countryCode=");
        return GeneratedOutlineSupport.outline11(outline13, this.countryCode, "}");
    }
}
