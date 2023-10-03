package com.android.messaging.sms;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import androidx.appcompat.mms.CarrierConfigValuesLoader;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1474sa;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.sms.j */
public class C1014j implements CarrierConfigValuesLoader {
    private final Context mContext;
    private final SparseArray mValuesCache = new SparseArray();

    public C1014j(Context context) {
        this.mContext = context;
    }

    /* renamed from: a */
    public static void m2365a(Bundle bundle, String str, String str2, String str3) {
        try {
            if (DefaultCarrierConfigValuesLoader.KEY_TYPE_INT.equals(str)) {
                bundle.putInt(str2, Integer.parseInt(str3));
            } else if (DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL.equals(str)) {
                bundle.putBoolean(str2, Boolean.parseBoolean(str3));
            } else if (DefaultCarrierConfigValuesLoader.KEY_TYPE_STRING.equals(str)) {
                bundle.putString(str2, str3);
            }
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Add carrier values: invalid ");
            sb.append(str2);
            sb.append(",");
            sb.append(str3);
            sb.append(",");
            C0632a.m1021a(sb, str, "MessagingApp");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0056, code lost:
        if (r2 != null) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0060, code lost:
        if (r2 == null) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0062, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0069, code lost:
        if (com.android.messaging.util.C1464na.m3758Yj() == false) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r5 = com.android.messaging.util.C1474sa.get(r6).mo8214ik().getCarrierConfigValues();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0077, code lost:
        if (r5 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0079, code lost:
        r7.putAll(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007d, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007e, code lost:
        com.android.messaging.util.C1430e.m3631w("MessagingApp", "Calling system getCarrierConfigValues exception", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0086, code lost:
        return "resources";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return "resources+system";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return "resources+system";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return "resources+system";
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String loadLocked(int r6, android.os.Bundle r7) {
        /*
            r5 = this;
            java.lang.String r0 = "MessagingApp"
            android.content.Context r1 = r5.mContext
            boolean r2 = com.android.messaging.util.C1464na.m3759Zj()
            if (r2 != 0) goto L_0x000b
            goto L_0x003b
        L_0x000b:
            com.android.messaging.util.sa r2 = com.android.messaging.util.C1474sa.get(r6)
            int[] r2 = r2.mo8207fk()
            r3 = 0
            r3 = r2[r3]
            r4 = 1
            r2 = r2[r4]
            android.content.res.Configuration r4 = new android.content.res.Configuration
            r4.<init>()
            if (r3 != 0) goto L_0x0033
            if (r2 != 0) goto L_0x0033
            android.content.res.Resources r2 = r1.getResources()
            android.content.res.Configuration r2 = r2.getConfiguration()
            int r3 = r2.mcc
            r4.mcc = r3
            int r2 = r2.mnc
            r4.mnc = r2
            goto L_0x0037
        L_0x0033:
            r4.mcc = r3
            r4.mnc = r2
        L_0x0037:
            android.content.Context r1 = r1.createConfigurationContext(r4)
        L_0x003b:
            r2 = 0
            android.content.res.Resources r1 = r1.getResources()     // Catch:{ NotFoundException -> 0x005b }
            r3 = 2132082691(0x7f150003, float:1.9805503E38)
            android.content.res.XmlResourceParser r2 = r1.getXml(r3)     // Catch:{ NotFoundException -> 0x005b }
            com.android.messaging.sms.e r1 = com.android.messaging.sms.C1009e.m2351a((org.xmlpull.v1.XmlPullParser) r2)     // Catch:{ NotFoundException -> 0x005b }
            com.android.messaging.sms.i r3 = new com.android.messaging.sms.i     // Catch:{ NotFoundException -> 0x005b }
            r3.<init>(r5, r7)     // Catch:{ NotFoundException -> 0x005b }
            r1.mo6816a((com.android.messaging.sms.C1008d) r3)     // Catch:{ NotFoundException -> 0x005b }
            r1.mo6817gi()     // Catch:{ NotFoundException -> 0x005b }
            if (r2 == 0) goto L_0x0065
            goto L_0x0062
        L_0x0059:
            r5 = move-exception
            goto L_0x0089
        L_0x005b:
            java.lang.String r5 = "Can not find mms_config.xml"
            com.android.messaging.util.C1430e.m3630w(r0, r5)     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0065
        L_0x0062:
            r2.close()
        L_0x0065:
            boolean r5 = com.android.messaging.util.C1464na.m3758Yj()
            if (r5 == 0) goto L_0x0086
            com.android.messaging.util.sa r5 = com.android.messaging.util.C1474sa.get(r6)     // Catch:{ Exception -> 0x007d }
            android.telephony.SmsManager r5 = r5.mo8214ik()     // Catch:{ Exception -> 0x007d }
            android.os.Bundle r5 = r5.getCarrierConfigValues()     // Catch:{ Exception -> 0x007d }
            if (r5 == 0) goto L_0x0083
            r7.putAll(r5)     // Catch:{ Exception -> 0x007d }
            goto L_0x0083
        L_0x007d:
            r5 = move-exception
            java.lang.String r6 = "Calling system getCarrierConfigValues exception"
            com.android.messaging.util.C1430e.m3631w(r0, r6, r5)
        L_0x0083:
            java.lang.String r5 = "resources+system"
            return r5
        L_0x0086:
            java.lang.String r5 = "resources"
            return r5
        L_0x0089:
            if (r2 == 0) goto L_0x008e
            r2.close()
        L_0x008e:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.sms.C1014j.loadLocked(int, android.os.Bundle):java.lang.String");
    }

    public Bundle get(int i) {
        Bundle bundle;
        String str;
        int Na = C1474sa.getDefault().mo8201Na(i);
        synchronized (this) {
            bundle = (Bundle) this.mValuesCache.get(Na);
            if (bundle == null) {
                bundle = new Bundle();
                this.mValuesCache.put(Na, bundle);
                str = loadLocked(Na, bundle);
            } else {
                str = null;
            }
        }
        if (str != null) {
            C1430e.m3625i("MessagingApp", "Carrier configs loaded: " + bundle + " from " + str + " for subId=" + Na);
        }
        return bundle;
    }

    public void reset() {
        synchronized (this) {
            this.mValuesCache.clear();
        }
    }
}
