package com.android.dialer.oem;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.auto.value.AutoValue;
import java.util.concurrent.ConcurrentHashMap;

public class CequintCallerIdManager {
    public static final String CONFIG_CALLER_ID_ENABLED = "config_caller_id_enabled";
    private static final String[] EMPTY_PROJECTION = new String[0];
    private static String cequintProviderAuthority;
    private static boolean hasAlreadyCheckedCequintCallerIdPackage;
    private final ConcurrentHashMap<String, CequintCallerIdContact> callLogCache = new ConcurrentHashMap<>();

    @AutoValue
    public static abstract class CequintCallerIdContact {

        static abstract class Builder {
            Builder() {
            }

            /* access modifiers changed from: package-private */
            public abstract CequintCallerIdContact build();

            /* access modifiers changed from: package-private */
            public abstract Builder setGeolocation(String str);

            /* access modifiers changed from: package-private */
            public abstract Builder setName(String str);

            /* access modifiers changed from: package-private */
            public abstract Builder setPhotoUri(String str);
        }

        public abstract String geolocation();

        public abstract String name();

        public abstract String photoUri();
    }

    public static final class CequintColumnNames {
    }

    public static CequintCallerIdContact getCequintCallerIdContactForCall(Context context, String str, String str2, boolean z) {
        Assert.isWorkerThread();
        Object[] objArr = {LogUtil.sanitizePhoneNumber(str), LogUtil.sanitizePii(str2), Boolean.valueOf(z)};
        String[] strArr = {str2, String.valueOf(z ? 34 : 1)};
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("content://");
        outline13.append(cequintProviderAuthority);
        outline13.append("/incalllookup");
        return lookup(context, Uri.parse(outline13.toString()), str, strArr);
    }

    public static CequintCallerIdContact getCequintCallerIdContactForNumber(Context context, String str) {
        Assert.isWorkerThread();
        new Object[1][0] = LogUtil.sanitizePhoneNumber(str);
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("content://");
        outline13.append(cequintProviderAuthority);
        outline13.append("/lookup");
        return lookup(context, Uri.parse(outline13.toString()), PhoneNumberUtils.stripSeparators(str), new String[]{"system"});
    }

    private static String getString(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        String string = cursor.getString(i);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0052, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized boolean isCequintCallerIdEnabled(android.content.Context r8) {
        /*
            java.lang.Class<com.android.dialer.oem.CequintCallerIdManager> r0 = com.android.dialer.oem.CequintCallerIdManager.class
            monitor-enter(r0)
            com.android.dialer.configprovider.ConfigProviderComponent r1 = com.android.dialer.configprovider.ConfigProviderComponent.get(r8)     // Catch:{ all -> 0x0053 }
            com.android.dialer.configprovider.ConfigProvider r1 = r1.getConfigProvider()     // Catch:{ all -> 0x0053 }
            java.lang.String r2 = "config_caller_id_enabled"
            com.android.dialer.configprovider.SharedPrefConfigProvider r1 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r1
            r3 = 1
            boolean r1 = r1.getBoolean(r2, r3)     // Catch:{ all -> 0x0053 }
            r2 = 0
            if (r1 != 0) goto L_0x0019
            monitor-exit(r0)
            return r2
        L_0x0019:
            boolean r1 = hasAlreadyCheckedCequintCallerIdPackage     // Catch:{ all -> 0x0053 }
            if (r1 != 0) goto L_0x004c
            hasAlreadyCheckedCequintCallerIdPackage = r3     // Catch:{ all -> 0x0053 }
            android.content.res.Resources r1 = r8.getResources()     // Catch:{ all -> 0x0053 }
            r4 = 2130903047(0x7f030007, float:1.74129E38)
            java.lang.String[] r1 = r1.getStringArray(r4)     // Catch:{ all -> 0x0053 }
            android.content.pm.PackageManager r8 = r8.getPackageManager()     // Catch:{ all -> 0x0053 }
            int r4 = r1.length     // Catch:{ all -> 0x0053 }
            r5 = r2
        L_0x0030:
            if (r5 >= r4) goto L_0x004c
            r6 = r1[r5]     // Catch:{ all -> 0x0053 }
            boolean r7 = com.android.dialer.oem.CequintPackageUtils.isCallerIdInstalled(r8, r6)     // Catch:{ all -> 0x0053 }
            if (r7 == 0) goto L_0x0049
            cequintProviderAuthority = r6     // Catch:{ all -> 0x0053 }
            java.lang.String r8 = "CequintCallerIdManager.isCequintCallerIdEnabled"
            java.lang.String r1 = "found provider: %s"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x0053 }
            r4[r2] = r6     // Catch:{ all -> 0x0053 }
            com.android.dialer.common.LogUtil.m9i(r8, r1, r4)     // Catch:{ all -> 0x0053 }
            monitor-exit(r0)
            return r3
        L_0x0049:
            int r5 = r5 + 1
            goto L_0x0030
        L_0x004c:
            java.lang.String r8 = cequintProviderAuthority     // Catch:{ all -> 0x0053 }
            if (r8 == 0) goto L_0x0051
            r2 = r3
        L_0x0051:
            monitor-exit(r0)
            return r2
        L_0x0053:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.oem.CequintCallerIdManager.isCequintCallerIdEnabled(android.content.Context):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00cb A[Catch:{ all -> 0x013a, all -> 0x013d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.android.dialer.oem.CequintCallerIdManager.CequintCallerIdContact lookup(android.content.Context r16, android.net.Uri r17, java.lang.String r18, java.lang.String[] r19) {
        /*
            com.android.dialer.common.Assert.isWorkerThread()
            com.android.dialer.common.Assert.isNotNull(r18)
            android.content.ContentResolver r2 = r16.getContentResolver()     // Catch:{ Exception -> 0x0152 }
            java.lang.String[] r4 = EMPTY_PROJECTION     // Catch:{ Exception -> 0x0152 }
            r7 = 0
            r3 = r17
            r5 = r18
            r6 = r19
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0152 }
            if (r2 == 0) goto L_0x014b
            boolean r0 = r2.moveToFirst()     // Catch:{ all -> 0x013a }
            if (r0 == 0) goto L_0x014b
            java.lang.String r0 = "cid_pCityName"
            int r0 = r2.getColumnIndex(r0)     // Catch:{ all -> 0x013a }
            java.lang.String r0 = getString(r2, r0)     // Catch:{ all -> 0x013a }
            java.lang.String r3 = "cid_pStateName"
            int r3 = r2.getColumnIndex(r3)     // Catch:{ all -> 0x013a }
            java.lang.String r3 = getString(r2, r3)     // Catch:{ all -> 0x013a }
            java.lang.String r4 = "cid_pStateAbbr"
            int r4 = r2.getColumnIndex(r4)     // Catch:{ all -> 0x013a }
            java.lang.String r4 = getString(r2, r4)     // Catch:{ all -> 0x013a }
            java.lang.String r5 = "cid_pCountryName"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ all -> 0x013a }
            java.lang.String r5 = getString(r2, r5)     // Catch:{ all -> 0x013a }
            java.lang.String r6 = "cid_pCompany"
            int r6 = r2.getColumnIndex(r6)     // Catch:{ all -> 0x013a }
            java.lang.String r6 = getString(r2, r6)     // Catch:{ all -> 0x013a }
            java.lang.String r7 = "cid_pName"
            int r7 = r2.getColumnIndex(r7)     // Catch:{ all -> 0x013a }
            java.lang.String r7 = getString(r2, r7)     // Catch:{ all -> 0x013a }
            java.lang.String r8 = "cid_pFirstName"
            int r8 = r2.getColumnIndex(r8)     // Catch:{ all -> 0x013a }
            java.lang.String r8 = getString(r2, r8)     // Catch:{ all -> 0x013a }
            java.lang.String r9 = "cid_pLastName"
            int r9 = r2.getColumnIndex(r9)     // Catch:{ all -> 0x013a }
            java.lang.String r9 = getString(r2, r9)     // Catch:{ all -> 0x013a }
            java.lang.String r10 = "cid_pLogo"
            int r10 = r2.getColumnIndex(r10)     // Catch:{ all -> 0x013a }
            java.lang.String r10 = getString(r2, r10)     // Catch:{ all -> 0x013a }
            java.lang.String r11 = "cid_pDisplayName"
            int r11 = r2.getColumnIndex(r11)     // Catch:{ all -> 0x013a }
            java.lang.String r11 = getString(r2, r11)     // Catch:{ all -> 0x013a }
            boolean r12 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x013a }
            r13 = 1
            if (r12 == 0) goto L_0x00d2
            boolean r11 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x013a }
            r11 = r11 ^ r13
            boolean r12 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x013a }
            r12 = r12 ^ r13
            boolean r14 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x013a }
            r14 = r14 ^ r13
            boolean r15 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x013a }
            r15 = r15 ^ r13
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x013a }
            r1.<init>()     // Catch:{ all -> 0x013a }
            if (r11 != 0) goto L_0x00b4
            if (r12 == 0) goto L_0x00a8
            goto L_0x00b4
        L_0x00a8:
            if (r14 == 0) goto L_0x00ae
            r1.append(r6)     // Catch:{ all -> 0x013a }
            goto L_0x00c5
        L_0x00ae:
            if (r15 == 0) goto L_0x00d1
            r1.append(r7)     // Catch:{ all -> 0x013a }
            goto L_0x00c5
        L_0x00b4:
            if (r11 == 0) goto L_0x00c0
            r1.append(r8)     // Catch:{ all -> 0x013a }
            if (r12 == 0) goto L_0x00c0
            java.lang.String r6 = " "
            r1.append(r6)     // Catch:{ all -> 0x013a }
        L_0x00c0:
            if (r12 == 0) goto L_0x00c5
            r1.append(r9)     // Catch:{ all -> 0x013a }
        L_0x00c5:
            int r6 = r1.length()     // Catch:{ all -> 0x013a }
            if (r6 <= 0) goto L_0x00d1
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x013a }
            r11 = r1
            goto L_0x00d2
        L_0x00d1:
            r11 = 0
        L_0x00d2:
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x013a }
            if (r1 == 0) goto L_0x00e0
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x013a }
            if (r1 != 0) goto L_0x00e0
            r1 = r3
            goto L_0x010a
        L_0x00e0:
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x013a }
            if (r1 != 0) goto L_0x0101
            boolean r1 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x013a }
            if (r1 != 0) goto L_0x0101
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x013a }
            r1.<init>()     // Catch:{ all -> 0x013a }
            r1.append(r0)     // Catch:{ all -> 0x013a }
            java.lang.String r0 = ", "
            r1.append(r0)     // Catch:{ all -> 0x013a }
            r1.append(r4)     // Catch:{ all -> 0x013a }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x013a }
            goto L_0x010a
        L_0x0101:
            boolean r0 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x013a }
            if (r0 != 0) goto L_0x0109
            r1 = r5
            goto L_0x010a
        L_0x0109:
            r1 = 0
        L_0x010a:
            r0 = 4
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x013a }
            r3 = 0
            java.lang.String r4 = com.android.dialer.common.LogUtil.sanitizePhoneNumber(r18)     // Catch:{ all -> 0x013a }
            r0[r3] = r4     // Catch:{ all -> 0x013a }
            java.lang.String r3 = com.android.dialer.common.LogUtil.sanitizePii(r11)     // Catch:{ all -> 0x013a }
            r0[r13] = r3     // Catch:{ all -> 0x013a }
            r3 = 2
            java.lang.String r4 = com.android.dialer.common.LogUtil.sanitizePii(r1)     // Catch:{ all -> 0x013a }
            r0[r3] = r4     // Catch:{ all -> 0x013a }
            r3 = 3
            r0[r3] = r10     // Catch:{ all -> 0x013a }
            com.android.dialer.oem.AutoValue_CequintCallerIdManager_CequintCallerIdContact$Builder r0 = new com.android.dialer.oem.AutoValue_CequintCallerIdManager_CequintCallerIdContact$Builder     // Catch:{ all -> 0x013a }
            r0.<init>()     // Catch:{ all -> 0x013a }
            r0.setName(r11)     // Catch:{ all -> 0x013a }
            r0.setGeolocation(r1)     // Catch:{ all -> 0x013a }
            r0.setPhotoUri(r10)     // Catch:{ all -> 0x013a }
            com.android.dialer.oem.CequintCallerIdManager$CequintCallerIdContact r0 = r0.build()     // Catch:{ all -> 0x013a }
            r2.close()     // Catch:{ Exception -> 0x0152 }
            return r0
        L_0x013a:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch:{ all -> 0x013d }
        L_0x013d:
            r0 = move-exception
            r3 = r0
            if (r2 == 0) goto L_0x014a
            r2.close()     // Catch:{ all -> 0x0145 }
            goto L_0x014a
        L_0x0145:
            r0 = move-exception
            r2 = r0
            r1.addSuppressed(r2)     // Catch:{ Exception -> 0x0152 }
        L_0x014a:
            throw r3     // Catch:{ Exception -> 0x0152 }
        L_0x014b:
            if (r2 == 0) goto L_0x0150
            r2.close()     // Catch:{ Exception -> 0x0152 }
        L_0x0150:
            r1 = 0
            return r1
        L_0x0152:
            r0 = move-exception
            r1 = 0
            java.lang.String r2 = "CequintCallerIdManager.lookup"
            java.lang.String r3 = "exception on query"
            com.android.dialer.common.LogUtil.m7e((java.lang.String) r2, (java.lang.String) r3, (java.lang.Throwable) r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.oem.CequintCallerIdManager.lookup(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):com.android.dialer.oem.CequintCallerIdManager$CequintCallerIdContact");
    }

    @Deprecated
    public CequintCallerIdContact getCachedCequintCallerIdContact(Context context, String str) {
        Assert.isWorkerThread();
        new Object[1][0] = LogUtil.sanitizePhoneNumber(str);
        if (this.callLogCache.containsKey(str)) {
            return this.callLogCache.get(str);
        }
        CequintCallerIdContact cequintCallerIdContactForNumber = getCequintCallerIdContactForNumber(context, str);
        if (cequintCallerIdContactForNumber != null) {
            this.callLogCache.put(str, cequintCallerIdContactForNumber);
        }
        return cequintCallerIdContactForNumber;
    }
}
