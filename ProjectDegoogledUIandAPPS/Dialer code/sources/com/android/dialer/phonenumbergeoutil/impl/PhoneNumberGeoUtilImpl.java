package com.android.dialer.phonenumbergeoutil.impl;

import com.android.dialer.phonenumbergeoutil.PhoneNumberGeoUtil;

public class PhoneNumberGeoUtilImpl implements PhoneNumberGeoUtil {
    /* JADX WARNING: Removed duplicated region for block: B:13:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0097 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getGeoDescription(android.content.Context r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            java.lang.String r5 = "'"
            java.lang.String r0 = ""
            java.lang.StringBuilder r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r0)
            java.lang.String r1 = com.android.dialer.common.LogUtil.sanitizePii(r7)
            r0.append(r1)
            r0.toString()
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            if (r0 == 0) goto L_0x001a
            return r1
        L_0x001a:
            com.google.i18n.phonenumbers.PhoneNumberUtil r0 = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
            com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder r2 = com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder.getInstance()
            java.util.Locale r6 = android.support.p002v7.appcompat.R$style.getLocale(r6)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NumberParseException -> 0x0060 }
            r3.<init>()     // Catch:{ NumberParseException -> 0x0060 }
            java.lang.String r4 = "parsing '"
            r3.append(r4)     // Catch:{ NumberParseException -> 0x0060 }
            java.lang.String r4 = com.android.dialer.common.LogUtil.sanitizePii(r7)     // Catch:{ NumberParseException -> 0x0060 }
            r3.append(r4)     // Catch:{ NumberParseException -> 0x0060 }
            java.lang.String r4 = "' for countryIso '"
            r3.append(r4)     // Catch:{ NumberParseException -> 0x0060 }
            r3.append(r8)     // Catch:{ NumberParseException -> 0x0060 }
            java.lang.String r4 = "'..."
            r3.append(r4)     // Catch:{ NumberParseException -> 0x0060 }
            r3.toString()     // Catch:{ NumberParseException -> 0x0060 }
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r8 = r0.parse(r7, r8)     // Catch:{ NumberParseException -> 0x0060 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ NumberParseException -> 0x0061 }
            r0.<init>()     // Catch:{ NumberParseException -> 0x0061 }
            java.lang.String r3 = "- parsed number: "
            r0.append(r3)     // Catch:{ NumberParseException -> 0x0061 }
            java.lang.String r3 = com.android.dialer.common.LogUtil.sanitizePii(r8)     // Catch:{ NumberParseException -> 0x0061 }
            r0.append(r3)     // Catch:{ NumberParseException -> 0x0061 }
            r0.toString()     // Catch:{ NumberParseException -> 0x0061 }
            goto L_0x007d
        L_0x0060:
            r8 = r1
        L_0x0061:
            java.lang.String r0 = "getGeoDescription: NumberParseException for incoming number '"
            java.lang.StringBuilder r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r0)
            java.lang.String r7 = com.android.dialer.common.LogUtil.sanitizePii(r7)
            r0.append(r7)
            r0.append(r5)
            java.lang.String r7 = r0.toString()
            r0 = 0
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r3 = "PhoneNumberGeoUtilImpl.getGeoDescription"
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r3, (java.lang.String) r7, (java.lang.Object[]) r0)
        L_0x007d:
            if (r8 == 0) goto L_0x0097
            java.lang.String r6 = r2.getDescriptionForNumber(r8, r6)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "- got description: '"
            r7.append(r8)
            r7.append(r6)
            r7.append(r5)
            r7.toString()
            return r6
        L_0x0097:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonenumbergeoutil.impl.PhoneNumberGeoUtilImpl.getGeoDescription(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }
}
