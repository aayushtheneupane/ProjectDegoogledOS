package com.android.messaging.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.Settings;
import android.provider.Telephony;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.sms.C1027w;
import com.google.i18n.phonenumbers.C1734f;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber$PhoneNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import p000a.p005b.C0015b;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.sa */
public abstract class C1474sa {
    /* access modifiers changed from: private */

    /* renamed from: SK */
    public static final List f2339SK = new ArrayList();

    /* renamed from: TK */
    private static final C0015b f2340TK = new C0015b();
    protected final Context mContext = C0967f.get().getApplicationContext();
    protected final int mSubId;
    protected final TelephonyManager mTelephonyManager = ((TelephonyManager) this.mContext.getSystemService("phone"));

    public C1474sa(int i) {
        this.mSubId = i;
    }

    /* renamed from: La */
    public static boolean m3792La(String str) {
        return PhoneNumberUtils.isWellFormedSmsAddress(str) || C1027w.isEmailAddress(str);
    }

    /* renamed from: Mo */
    private static String m3793Mo() {
        String country = Locale.getDefault().getCountry();
        if (TextUtils.isEmpty(country)) {
            return null;
        }
        return country.toUpperCase();
    }

    /* renamed from: a */
    public static void m3794a(C1472ra raVar) {
        if (C1464na.m3759Zj()) {
            for (SubscriptionInfo subscriptionId : ((C1468pa) getDefault().mo8231mk()).getActiveSubscriptionInfoList()) {
                raVar.mo6074c(subscriptionId.getSubscriptionId());
            }
            return;
        }
        raVar.mo6074c(-1);
    }

    /* renamed from: b */
    public static String m3795b(int[] iArr) {
        if (iArr == null || iArr.length != 2) {
            return "000000";
        }
        return String.format("%03d%03d", new Object[]{Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1])});
    }

    /* renamed from: e */
    private static void m3797e(String str, String str2, String str3) {
        synchronized (f2340TK) {
            m3800kb(str2).put(str, str3);
        }
    }

    public static C1474sa get(int i) {
        return C0967f.get().mo6657ga(i);
    }

    public static C1474sa getDefault() {
        return C0967f.get().mo6657ga(-1);
    }

    /* renamed from: h */
    static /* synthetic */ String m3798h(Context context, int i) {
        String string = C1451h.m3725ha(i).getString(context.getString(R.string.mms_phone_number_pref_key), (String) null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        return null;
    }

    /* renamed from: j */
    public static String m3799j(String str, String str2) {
        try {
            return String.format("%03d%03d", new Object[]{Integer.valueOf(Integer.parseInt(str)), Integer.valueOf(Integer.parseInt(str2))});
        } catch (NumberFormatException unused) {
            C1430e.m3630w("MessagingApp", "canonicalizeMccMnc: invalid mccmnc:" + str + " ," + str2);
            return str + str2;
        }
    }

    /* renamed from: kb */
    private static C0015b m3800kb(String str) {
        if (str == null) {
            str = "";
        }
        C0015b bVar = (C0015b) f2340TK.get(str);
        if (bVar != null) {
            return bVar;
        }
        C0015b bVar2 = new C0015b();
        f2340TK.put(str, bVar2);
        return bVar2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* renamed from: n */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m3801n(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            com.android.messaging.util.C1424b.m3594t(r3)
            java.lang.String r2 = m3802o(r3, r4)
            if (r2 == 0) goto L_0x000a
            return r2
        L_0x000a:
            com.google.i18n.phonenumbers.f r2 = com.google.i18n.phonenumbers.C1734f.getInstance()
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r0 = r2.parse(r3, r4)     // Catch:{ NumberParseException -> 0x0021 }
            if (r0 == 0) goto L_0x003f
            boolean r1 = r2.mo9484b(r0)     // Catch:{ NumberParseException -> 0x0021 }
            if (r1 == 0) goto L_0x003f
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberFormat r1 = com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberFormat.E164     // Catch:{ NumberParseException -> 0x0021 }
            java.lang.String r2 = r2.mo9481a((com.google.i18n.phonenumbers.Phonenumber$PhoneNumber) r0, (com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberFormat) r1)     // Catch:{ NumberParseException -> 0x0021 }
            goto L_0x0040
        L_0x0021:
            java.lang.String r2 = "PhoneUtils.getValidE164Number(): Not able to parse phone number "
            java.lang.StringBuilder r2 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r2)
            java.lang.String r0 = com.android.messaging.util.C1430e.m3633xa(r3)
            r2.append(r0)
            java.lang.String r0 = " for country "
            r2.append(r0)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            java.lang.String r0 = "MessagingApp"
            com.android.messaging.util.C1430e.m3622e(r0, r2)
        L_0x003f:
            r2 = 0
        L_0x0040:
            if (r2 != 0) goto L_0x0043
            r2 = r3
        L_0x0043:
            m3797e(r3, r4, r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1474sa.m3801n(java.lang.String, java.lang.String):java.lang.String");
    }

    /* renamed from: o */
    private static String m3802o(String str, String str2) {
        String str3;
        synchronized (f2340TK) {
            str3 = (String) m3800kb(str2).get(str);
        }
        return str3;
    }

    /* renamed from: Ia */
    public String mo8220Ia(String str) {
        if (!TextUtils.isEmpty(str) && str.replaceAll("\\D", "").length() >= 6) {
            C1734f instance = C1734f.getInstance();
            String Mo = m3793Mo();
            int countryCodeForRegion = instance.getCountryCodeForRegion(Mo);
            try {
                Phonenumber$PhoneNumber parse = instance.parse(str, Mo);
                return instance.mo9481a(parse, (countryCodeForRegion <= 0 || parse.getCountryCode() != countryCodeForRegion) ? PhoneNumberUtil$PhoneNumberFormat.INTERNATIONAL : PhoneNumberUtil$PhoneNumberFormat.NATIONAL);
            } catch (NumberParseException unused) {
                StringBuilder Pa = C0632a.m1011Pa("PhoneUtils.formatForDisplay: invalid phone number ");
                Pa.append(C1430e.m3633xa(str));
                Pa.append(" with country ");
                Pa.append(Mo);
                C1430e.m3622e("MessagingApp", Pa.toString());
            }
        }
        return str;
    }

    /* renamed from: Ja */
    public String mo8221Ja(String str) {
        String hk = mo8213hk();
        if (hk == null) {
            hk = m3793Mo();
        }
        return m3801n(str, hk);
    }

    /* renamed from: Ka */
    public String mo8222Ka(String str) {
        return m3801n(str, m3793Mo());
    }

    /* renamed from: Na */
    public abstract int mo8201Na(int i);

    /* renamed from: a */
    public abstract int mo8202a(Intent intent, String str);

    /* renamed from: a */
    public abstract int mo8203a(Cursor cursor, int i);

    /* renamed from: bk */
    public abstract int mo8205bk();

    /* renamed from: ck */
    public String mo8223ck() {
        if (C1464na.m3757Xj()) {
            return Telephony.Sms.getDefaultSmsPackage(this.mContext);
        }
        return null;
    }

    /* renamed from: dk */
    public String mo8224dk() {
        if (!C1464na.m3757Xj()) {
            return "";
        }
        String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(this.mContext);
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(defaultSmsPackage, 0)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    /* renamed from: ek */
    public abstract boolean mo8206ek();

    /* renamed from: fk */
    public abstract int[] mo8207fk();

    public abstract String getCarrierName();

    public abstract int getDefaultSmsSubscriptionId();

    public abstract String getSimOperatorNumeric();

    /* renamed from: gk */
    public abstract HashSet mo8212gk();

    /* renamed from: hk */
    public abstract String mo8213hk();

    /* renamed from: ik */
    public abstract SmsManager mo8214ik();

    public abstract boolean isDataRoamingEnabled();

    public abstract boolean isMobileDataEnabled();

    public abstract boolean isRoaming();

    public boolean isSmsCapable() {
        return this.mTelephonyManager.isSmsCapable();
    }

    public boolean isVoiceCapable() {
        return this.mTelephonyManager.isVoiceCapable();
    }

    /* renamed from: jk */
    public boolean mo8227jk() {
        if (C1464na.m3755Vj()) {
            if (Settings.Global.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) != 0) {
                return true;
            }
            return false;
        } else if (Settings.System.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) != 0) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: kk */
    public boolean mo8228kk() {
        if (!C1464na.m3757Xj()) {
            return true;
        }
        return this.mContext.getPackageName().equals(Telephony.Sms.getDefaultSmsPackage(this.mContext));
    }

    /* renamed from: la */
    public String mo8229la(boolean z) {
        String str;
        try {
            str = mo8218ma(z);
        } catch (IllegalStateException unused) {
            str = null;
        }
        if (str == null) {
            return "";
        }
        return mo8221Ja(str);
    }

    /* renamed from: lk */
    public boolean mo8230lk() {
        return isSmsCapable() && mo8228kk();
    }

    /* renamed from: ma */
    public abstract String mo8218ma(boolean z);

    /* renamed from: mk */
    public C1466oa mo8231mk() {
        if (C1464na.m3759Zj()) {
            return (C1466oa) this;
        }
        C1424b.fail("PhoneUtils.toLMr1(): invalid OS version");
        return null;
    }
}
