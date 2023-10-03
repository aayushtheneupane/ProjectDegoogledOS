package com.android.dialer.assisteddialing;

import android.content.Context;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.strictmode.StrictModeUtils;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber$PhoneNumber;
import java.util.Optional;

final class Constraints {
    private final Context context;
    private final CountryCodeProvider countryCodeProvider;
    private final PhoneNumberUtil phoneNumberUtil;

    public Constraints(Context context2, CountryCodeProvider countryCodeProvider2) {
        if (context2 != null) {
            this.context = context2;
            if (countryCodeProvider2 != null) {
                this.countryCodeProvider = countryCodeProvider2;
                this.phoneNumberUtil = (PhoneNumberUtil) StrictModeUtils.bypass($$Lambda$Constraints$bPWFTJnp86K8qRMR3bG4gySaNKA.INSTANCE);
                return;
            }
            throw new NullPointerException("Provided configProviderCountryCodes cannot be null");
        }
        throw new NullPointerException("Provided context cannot be null");
    }

    public /* synthetic */ Boolean lambda$isValidNumber$2$Constraints(Optional optional) {
        return Boolean.valueOf(this.phoneNumberUtil.isValidNumber((Phonenumber$PhoneNumber) optional.get()));
    }

    public /* synthetic */ Optional lambda$parsePhoneNumber$1$Constraints(String str, String str2) {
        try {
            return Optional.of(this.phoneNumberUtil.parseAndKeepRawInput(str, str2));
        } catch (NumberParseException unused) {
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.ASSISTED_DIALING_CONSTRAINT_PARSING_FAILURE);
            LogUtil.m9i("Constraints.parsePhoneNumber", "could not parse the number", new Object[0]);
            return Optional.empty();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean meetsPreconditions(java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            java.lang.String r1 = "Constraints.meetsPreconditions"
            r2 = 0
            if (r0 == 0) goto L_0x0011
            java.lang.Object[] r7 = new java.lang.Object[r2]
            java.lang.String r8 = "numberToCheck was empty"
            com.android.dialer.common.LogUtil.m9i(r1, r8, r7)
            return r2
        L_0x0011:
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            java.lang.String r3 = "userHomeCountryCode was empty"
            if (r0 == 0) goto L_0x001f
            java.lang.Object[] r7 = new java.lang.Object[r2]
            com.android.dialer.common.LogUtil.m9i(r1, r3, r7)
            return r2
        L_0x001f:
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            java.lang.String r4 = "userRoamingCountryCode was empty"
            if (r0 == 0) goto L_0x002d
            java.lang.Object[] r7 = new java.lang.Object[r2]
            com.android.dialer.common.LogUtil.m9i(r1, r4, r7)
            return r2
        L_0x002d:
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r9 = r9.toUpperCase(r0)
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r10 = r10.toUpperCase(r0)
            com.android.dialer.assisteddialing.-$$Lambda$Constraints$4OXbFGUrmaLmzzLuLLqap2IWJzc r0 = new com.android.dialer.assisteddialing.-$$Lambda$Constraints$4OXbFGUrmaLmzzLuLLqap2IWJzc
            r0.<init>(r8, r9)
            java.lang.Object r0 = com.android.dialer.strictmode.StrictModeUtils.bypass(r0)
            java.util.Optional r0 = (java.util.Optional) r0
            boolean r5 = r0.isPresent()
            if (r5 != 0) goto L_0x0052
            java.lang.Object[] r7 = new java.lang.Object[r2]
            java.lang.String r8 = "parsedPhoneNumber was empty"
            com.android.dialer.common.LogUtil.m9i(r1, r8, r7)
            return r2
        L_0x0052:
            boolean r1 = android.text.TextUtils.isEmpty(r9)
            java.lang.String r5 = "Constraints.areSupportedCountryCodes"
            r6 = 1
            if (r1 == 0) goto L_0x0062
            java.lang.Object[] r1 = new java.lang.Object[r2]
            com.android.dialer.common.LogUtil.m9i(r5, r3, r1)
        L_0x0060:
            r1 = r2
            goto L_0x008a
        L_0x0062:
            boolean r1 = android.text.TextUtils.isEmpty(r10)
            if (r1 == 0) goto L_0x006e
            java.lang.Object[] r1 = new java.lang.Object[r2]
            com.android.dialer.common.LogUtil.m9i(r5, r4, r1)
            goto L_0x0060
        L_0x006e:
            com.android.dialer.assisteddialing.CountryCodeProvider r1 = r7.countryCodeProvider
            boolean r1 = r1.isSupportedCountryCode(r9)
            if (r1 == 0) goto L_0x0080
            com.android.dialer.assisteddialing.CountryCodeProvider r1 = r7.countryCodeProvider
            boolean r1 = r1.isSupportedCountryCode(r10)
            if (r1 == 0) goto L_0x0080
            r1 = r6
            goto L_0x0081
        L_0x0080:
            r1 = r2
        L_0x0081:
            java.lang.String r3 = java.lang.String.valueOf(r1)
            java.lang.Object[] r4 = new java.lang.Object[r2]
            com.android.dialer.common.LogUtil.m9i(r5, r3, r4)
        L_0x008a:
            if (r1 == 0) goto L_0x0145
            boolean r9 = r9.equals(r10)
            r9 = r9 ^ r6
            java.lang.String r10 = java.lang.String.valueOf(r9)
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r3 = "Constraints.isUserRoaming"
            com.android.dialer.common.LogUtil.m9i(r3, r10, r1)
            if (r9 == 0) goto L_0x0145
            java.lang.Object r9 = r0.get()
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r9 = (com.google.i18n.phonenumbers.Phonenumber$PhoneNumber) r9
            boolean r9 = r9.hasCountryCode()
            if (r9 == 0) goto L_0x00d0
            java.lang.Object r9 = r0.get()
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r9 = (com.google.i18n.phonenumbers.Phonenumber$PhoneNumber) r9
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber$CountryCodeSource r9 = r9.getCountryCodeSource()
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber$CountryCodeSource r10 = com.google.i18n.phonenumbers.Phonenumber$PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY
            if (r9 == r10) goto L_0x00d0
            android.content.Context r9 = r7.context
            com.android.dialer.logging.LoggingBindings r9 = com.android.dialer.logging.Logger.get(r9)
            com.android.dialer.logging.DialerImpression$Type r10 = com.android.dialer.logging.DialerImpression$Type.ASSISTED_DIALING_CONSTRAINT_NUMBER_HAS_COUNTRY_CODE
            com.android.dialer.logging.LoggingBindingsStub r9 = (com.android.dialer.logging.LoggingBindingsStub) r9
            r9.logImpression(r10)
            java.lang.Object[] r9 = new java.lang.Object[r2]
            java.lang.String r10 = "Constraints.isNotInternationalNumber"
            java.lang.String r1 = "phone number already provided the country code"
            com.android.dialer.common.LogUtil.m9i(r10, r1, r9)
            r9 = r2
            goto L_0x00d1
        L_0x00d0:
            r9 = r6
        L_0x00d1:
            if (r9 == 0) goto L_0x0145
            android.content.Context r9 = r7.context
            boolean r10 = android.telephony.PhoneNumberUtils.isEmergencyNumber(r8)
            if (r10 != 0) goto L_0x00e3
            boolean r8 = com.android.dialer.phonenumberutil.PhoneNumberHelper.isLocalEmergencyNumber(r9, r8)
            if (r8 != 0) goto L_0x00e3
            r8 = r6
            goto L_0x00e4
        L_0x00e3:
            r8 = r2
        L_0x00e4:
            java.lang.String r9 = java.lang.String.valueOf(r8)
            java.lang.Object[] r10 = new java.lang.Object[r2]
            java.lang.String r1 = "Constraints.isNotEmergencyNumber"
            com.android.dialer.common.LogUtil.m9i(r1, r9, r10)
            if (r8 == 0) goto L_0x0145
            com.android.dialer.assisteddialing.-$$Lambda$Constraints$Ii1CUaYzaPQjDPogC0DOWUnwmLw r8 = new com.android.dialer.assisteddialing.-$$Lambda$Constraints$Ii1CUaYzaPQjDPogC0DOWUnwmLw
            r8.<init>(r0)
            java.lang.Object r8 = com.android.dialer.strictmode.StrictModeUtils.bypass(r8)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            java.lang.String r9 = java.lang.String.valueOf(r8)
            java.lang.Object[] r10 = new java.lang.Object[r2]
            java.lang.String r1 = "Constraints.isValidNumber"
            com.android.dialer.common.LogUtil.m9i(r1, r9, r10)
            if (r8 == 0) goto L_0x0145
            java.lang.Object r8 = r0.get()
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r8 = (com.google.i18n.phonenumbers.Phonenumber$PhoneNumber) r8
            boolean r8 = r8.hasExtension()
            if (r8 == 0) goto L_0x0141
            java.lang.Object r8 = r0.get()
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r8 = (com.google.i18n.phonenumbers.Phonenumber$PhoneNumber) r8
            java.lang.String r8 = r8.getExtension()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x0141
            android.content.Context r7 = r7.context
            com.android.dialer.logging.LoggingBindings r7 = com.android.dialer.logging.Logger.get(r7)
            com.android.dialer.logging.DialerImpression$Type r8 = com.android.dialer.logging.DialerImpression$Type.ASSISTED_DIALING_CONSTRAINT_NUMBER_HAS_EXTENSION
            com.android.dialer.logging.LoggingBindingsStub r7 = (com.android.dialer.logging.LoggingBindingsStub) r7
            r7.logImpression(r8)
            java.lang.Object[] r7 = new java.lang.Object[r2]
            java.lang.String r8 = "Constraints.doesNotHaveExtension"
            java.lang.String r9 = "phone number has an extension"
            com.android.dialer.common.LogUtil.m9i(r8, r9, r7)
            r7 = r2
            goto L_0x0142
        L_0x0141:
            r7 = r6
        L_0x0142:
            if (r7 == 0) goto L_0x0145
            r2 = r6
        L_0x0145:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.assisteddialing.Constraints.meetsPreconditions(java.lang.String, java.lang.String, java.lang.String):boolean");
    }
}
