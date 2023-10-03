package com.android.messaging.sms;

import android.os.Bundle;
import androidx.appcompat.mms.CarrierConfigValuesLoader;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.C1478ua;
import com.google.common.collect.C1633Xa;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: com.android.messaging.sms.t */
public class C1024t {

    /* renamed from: LE */
    private static final Map f1541LE = C1633Xa.newHashMap();

    /* renamed from: ME */
    private static final Map f1542ME = new HashMap();

    /* renamed from: NE */
    private static final C1024t f1543NE = new C1024t(-1, new Bundle());
    private final int mSubId;
    private final Bundle mValues;

    static {
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_ENABLED_MMS, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_ENABLED_TRANS_ID, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_ENABLED_NOTIFY_WAP_MMSC, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_ALIAS_ENABLED, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_ALLOW_ATTACH_AUDIO, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_ENABLE_MULTIPART_SMS, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_ENABLE_SMS_DELIVERY_REPORTS, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_ENABLE_GROUP_MMS, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_SUPPORT_MMS_CONTENT_DISPOSITION, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_CELL_BROADCAST_APP_LINKS, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_SEND_MULTIPART_SMS_AS_SEPARATE_MESSAGES, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_ENABLE_MMS_READ_REPORTS, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_ENABLE_MMS_DELIVERY_REPORTS, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_SUPPORT_HTTP_CHARSET_HEADER, DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_MAX_MESSAGE_SIZE, DefaultCarrierConfigValuesLoader.KEY_TYPE_INT);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_MAX_IMAGE_HEIGHT, DefaultCarrierConfigValuesLoader.KEY_TYPE_INT);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_MAX_IMAGE_WIDTH, DefaultCarrierConfigValuesLoader.KEY_TYPE_INT);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_RECIPIENT_LIMIT, DefaultCarrierConfigValuesLoader.KEY_TYPE_INT);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_HTTP_SOCKET_TIMEOUT, DefaultCarrierConfigValuesLoader.KEY_TYPE_INT);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_ALIAS_MIN_CHARS, DefaultCarrierConfigValuesLoader.KEY_TYPE_INT);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_ALIAS_MAX_CHARS, DefaultCarrierConfigValuesLoader.KEY_TYPE_INT);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_SMS_TO_MMS_TEXT_THRESHOLD, DefaultCarrierConfigValuesLoader.KEY_TYPE_INT);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_SMS_TO_MMS_TEXT_LENGTH_THRESHOLD, DefaultCarrierConfigValuesLoader.KEY_TYPE_INT);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_MAX_MESSAGE_TEXT_SIZE, DefaultCarrierConfigValuesLoader.KEY_TYPE_INT);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_MAX_SUBJECT_LENGTH, DefaultCarrierConfigValuesLoader.KEY_TYPE_INT);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_UA_PROF_TAG_NAME, DefaultCarrierConfigValuesLoader.KEY_TYPE_STRING);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_HTTP_PARAMS, DefaultCarrierConfigValuesLoader.KEY_TYPE_STRING);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_EMAIL_GATEWAY_NUMBER, DefaultCarrierConfigValuesLoader.KEY_TYPE_STRING);
        f1541LE.put(CarrierConfigValuesLoader.CONFIG_NAI_SUFFIX, DefaultCarrierConfigValuesLoader.KEY_TYPE_STRING);
        f1541LE.put("uaProfUrl", DefaultCarrierConfigValuesLoader.KEY_TYPE_STRING);
        f1541LE.put("userAgent", DefaultCarrierConfigValuesLoader.KEY_TYPE_STRING);
    }

    private C1024t(int i, Bundle bundle) {
        this.mSubId = i;
        this.mValues = bundle;
    }

    /* renamed from: Ci */
    public static void m2367Ci() {
        C1478ua.m3823a((Runnable) new C1023s());
    }

    /* renamed from: a */
    private static void m2368a(C1024t tVar) {
        boolean z = true;
        if (C1464na.m3759Zj() == (tVar.mSubId == -1)) {
            z = false;
        }
        C1424b.m3592ia(z);
        f1542ME.put(Integer.valueOf(tVar.mSubId), tVar);
    }

    public static C1024t get(int i) {
        int Na = C1474sa.getDefault().mo8201Na(i);
        synchronized (f1542ME) {
            C1024t tVar = (C1024t) f1542ME.get(Integer.valueOf(Na));
            if (tVar != null) {
                return tVar;
            }
            C1430e.m3622e("MessagingApp", "Get mms config failed: invalid subId. subId=" + i + ", real subId=" + Na + ", map=" + f1542ME.keySet());
            C1024t tVar2 = f1543NE;
            return tVar2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0061, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void load() {
        /*
            java.lang.Class<com.android.messaging.sms.t> r0 = com.android.messaging.sms.C1024t.class
            monitor-enter(r0)
            com.android.messaging.f r1 = com.android.messaging.C0967f.get()     // Catch:{ all -> 0x0062 }
            com.android.messaging.sms.j r1 = r1.getCarrierConfigValuesLoader()     // Catch:{ all -> 0x0062 }
            java.util.Map r2 = f1542ME     // Catch:{ all -> 0x0062 }
            r2.clear()     // Catch:{ all -> 0x0062 }
            r1.reset()     // Catch:{ all -> 0x0062 }
            boolean r2 = com.android.messaging.util.C1464na.m3759Zj()     // Catch:{ all -> 0x0062 }
            if (r2 == 0) goto L_0x0053
            com.android.messaging.util.sa r2 = com.android.messaging.util.C1474sa.getDefault()     // Catch:{ all -> 0x0062 }
            com.android.messaging.util.oa r2 = r2.mo8231mk()     // Catch:{ all -> 0x0062 }
            com.android.messaging.util.pa r2 = (com.android.messaging.util.C1468pa) r2
            java.util.List r2 = r2.getActiveSubscriptionInfoList()     // Catch:{ all -> 0x0062 }
            if (r2 != 0) goto L_0x0032
            java.lang.String r1 = "MessagingApp"
            java.lang.String r2 = "Loading mms config failed: no active SIM"
            com.android.messaging.util.C1430e.m3630w(r1, r2)     // Catch:{ all -> 0x0062 }
            monitor-exit(r0)
            return
        L_0x0032:
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0062 }
        L_0x0036:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0062 }
            if (r3 == 0) goto L_0x0060
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0062 }
            android.telephony.SubscriptionInfo r3 = (android.telephony.SubscriptionInfo) r3     // Catch:{ all -> 0x0062 }
            int r3 = r3.getSubscriptionId()     // Catch:{ all -> 0x0062 }
            android.os.Bundle r4 = r1.get(r3)     // Catch:{ all -> 0x0062 }
            com.android.messaging.sms.t r5 = new com.android.messaging.sms.t     // Catch:{ all -> 0x0062 }
            r5.<init>(r3, r4)     // Catch:{ all -> 0x0062 }
            m2368a(r5)     // Catch:{ all -> 0x0062 }
            goto L_0x0036
        L_0x0053:
            r2 = -1
            android.os.Bundle r1 = r1.get(r2)     // Catch:{ all -> 0x0062 }
            com.android.messaging.sms.t r3 = new com.android.messaging.sms.t     // Catch:{ all -> 0x0062 }
            r3.<init>(r2, r1)     // Catch:{ all -> 0x0062 }
            m2368a(r3)     // Catch:{ all -> 0x0062 }
        L_0x0060:
            monitor-exit(r0)
            return
        L_0x0062:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.sms.C1024t.load():void");
    }

    /* renamed from: ra */
    public static String m2369ra(String str) {
        return (String) f1541LE.get(str);
    }

    /* renamed from: Ai */
    public boolean mo6832Ai() {
        return this.mValues.getBoolean(CarrierConfigValuesLoader.CONFIG_ENABLED_TRANS_ID, false);
    }

    /* renamed from: Bi */
    public boolean mo6833Bi() {
        return this.mValues.getBoolean(CarrierConfigValuesLoader.CONFIG_ALIAS_ENABLED, false);
    }

    /* renamed from: c */
    public void mo6834c(String str, String str2, String str3) {
        C1014j.m2365a(this.mValues, str, str2, str3);
    }

    public int getMaxMessageSize() {
        return this.mValues.getInt(CarrierConfigValuesLoader.CONFIG_MAX_MESSAGE_SIZE, CarrierConfigValuesLoader.CONFIG_MAX_MESSAGE_SIZE_DEFAULT);
    }

    public Object getValue(String str) {
        return this.mValues.get(str);
    }

    /* renamed from: ii */
    public int mo6837ii() {
        return this.mValues.getInt(CarrierConfigValuesLoader.CONFIG_ALIAS_MAX_CHARS, 48);
    }

    /* renamed from: ji */
    public int mo6838ji() {
        return this.mValues.getInt(CarrierConfigValuesLoader.CONFIG_ALIAS_MIN_CHARS, 2);
    }

    public Set keySet() {
        return this.mValues.keySet();
    }

    /* renamed from: ki */
    public String mo6840ki() {
        return this.mValues.getString(CarrierConfigValuesLoader.CONFIG_EMAIL_GATEWAY_NUMBER, CarrierConfigValuesLoader.CONFIG_EMAIL_GATEWAY_NUMBER_DEFAULT);
    }

    /* renamed from: li */
    public boolean mo6841li() {
        return this.mValues.getBoolean(CarrierConfigValuesLoader.CONFIG_ENABLE_GROUP_MMS, true);
    }

    /* renamed from: mi */
    public int mo6842mi() {
        return this.mValues.getInt(CarrierConfigValuesLoader.CONFIG_MAX_IMAGE_HEIGHT, CarrierConfigValuesLoader.CONFIG_MAX_IMAGE_HEIGHT_DEFAULT);
    }

    /* renamed from: oi */
    public int mo6843oi() {
        return this.mValues.getInt(CarrierConfigValuesLoader.CONFIG_MAX_IMAGE_WIDTH, CarrierConfigValuesLoader.CONFIG_MAX_IMAGE_WIDTH_DEFAULT);
    }

    /* renamed from: pi */
    public int mo6844pi() {
        return this.mValues.getInt(CarrierConfigValuesLoader.CONFIG_MAX_SUBJECT_LENGTH, 40);
    }

    /* renamed from: qi */
    public int mo6845qi() {
        int i = this.mValues.getInt(CarrierConfigValuesLoader.CONFIG_MAX_MESSAGE_TEXT_SIZE, -1);
        if (i > -1) {
            return i;
        }
        return 2000;
    }

    /* renamed from: ri */
    public boolean mo6846ri() {
        return this.mValues.getBoolean(CarrierConfigValuesLoader.CONFIG_ENABLE_MULTIPART_SMS, true);
    }

    /* renamed from: si */
    public boolean mo6847si() {
        return this.mValues.getBoolean(CarrierConfigValuesLoader.CONFIG_ENABLED_NOTIFY_WAP_MMSC, false);
    }

    /* renamed from: ti */
    public int mo6848ti() {
        int i = this.mValues.getInt(CarrierConfigValuesLoader.CONFIG_RECIPIENT_LIMIT, Integer.MAX_VALUE);
        if (i < 0) {
            return Integer.MAX_VALUE;
        }
        return i;
    }

    /* renamed from: ui */
    public boolean mo6849ui() {
        return this.mValues.getBoolean(CarrierConfigValuesLoader.CONFIG_ENABLE_SMS_DELIVERY_REPORTS, true);
    }

    /* renamed from: vi */
    public boolean mo6850vi() {
        return this.mValues.getBoolean(CarrierConfigValuesLoader.CONFIG_SEND_MULTIPART_SMS_AS_SEPARATE_MESSAGES, false);
    }

    /* renamed from: wi */
    public boolean mo6851wi() {
        return this.mValues.getBoolean(CarrierConfigValuesLoader.CONFIG_CELL_BROADCAST_APP_LINKS, true);
    }

    /* renamed from: xi */
    public int mo6852xi() {
        return this.mValues.getInt(CarrierConfigValuesLoader.CONFIG_SMS_TO_MMS_TEXT_LENGTH_THRESHOLD, -1);
    }

    /* renamed from: yi */
    public int mo6853yi() {
        return this.mValues.getInt(CarrierConfigValuesLoader.CONFIG_SMS_TO_MMS_TEXT_THRESHOLD, -1);
    }

    /* renamed from: zi */
    public boolean mo6854zi() {
        return this.mValues.getBoolean(CarrierConfigValuesLoader.CONFIG_SUPPORT_MMS_CONTENT_DISPOSITION, true);
    }
}
