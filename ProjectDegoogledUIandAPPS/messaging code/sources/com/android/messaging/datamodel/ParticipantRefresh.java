package com.android.messaging.datamodel;

import android.content.ContentValues;
import android.util.Log;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1478ua;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParticipantRefresh {

    /* renamed from: Ay */
    private static final Runnable f1032Ay = new C0773N();
    private static final Object sLock = new Object();

    /* renamed from: xy */
    private static volatile boolean f1033xy = false;
    /* access modifiers changed from: private */

    /* renamed from: yy */
    public static final AtomicBoolean f1034yy = new AtomicBoolean(false);

    /* renamed from: zy */
    private static final Runnable f1035zy = new C0772M();

    private ParticipantRefresh() {
    }

    /* renamed from: a */
    static String m1283a(int i, int i2, String str, String str2) {
        return String.format((Locale) null, "UPDATE participants SET sim_slot_id = %d, subscription_color = %d, subscription_name = %s  WHERE %s", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str, str2});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007a, code lost:
        if (r10 != null) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0094, code lost:
        if (r10 != null) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0096, code lost:
        r10.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01df  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01d6  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m1284b(com.android.messaging.datamodel.C0955p r27, com.android.messaging.datamodel.data.ParticipantData r28) {
        /*
            r1 = r28
            boolean r0 = r28.mo6362zh()
            r2 = 3
            java.lang.String r3 = "Participant refresh: failed to refresh participant. exception="
            java.lang.String r4 = "MessagingAppDataModel"
            r6 = 2
            r7 = 0
            r8 = 1
            if (r0 == 0) goto L_0x00aa
            boolean r0 = r28.mo6329Bh()
            boolean r9 = com.android.messaging.util.C1464na.m3759Zj()
            if (r9 == 0) goto L_0x0034
            int r9 = r28.getSubId()
            com.android.messaging.util.sa r9 = com.android.messaging.util.C1474sa.get(r9)
            com.android.messaging.util.oa r9 = r9.mo8231mk()
            com.android.messaging.util.pa r9 = (com.android.messaging.util.C1468pa) r9
            android.telephony.SubscriptionInfo r9 = r9.mo8219nk()
            boolean r9 = r1.mo6331a(r9)
            if (r9 == 0) goto L_0x0034
            r9 = r8
            goto L_0x0035
        L_0x0034:
            r9 = r0
        L_0x0035:
            android.content.Context r0 = r27.getContext()     // Catch:{ Exception -> 0x0080, all -> 0x007d }
            com.android.messaging.datamodel.g r0 = com.android.messaging.util.ContactUtil.getSelf(r0)     // Catch:{ Exception -> 0x0080, all -> 0x007d }
            android.database.Cursor r10 = r0.mo6584Xd()     // Catch:{ Exception -> 0x0080, all -> 0x007d }
            if (r10 == 0) goto L_0x007a
            int r0 = r10.getCount()     // Catch:{ Exception -> 0x0078 }
            if (r0 <= 0) goto L_0x007a
            r10.moveToNext()     // Catch:{ Exception -> 0x0078 }
            long r11 = r10.getLong(r7)     // Catch:{ Exception -> 0x0078 }
            r1.setContactId(r11)     // Catch:{ Exception -> 0x0078 }
            java.lang.String r0 = r10.getString(r8)     // Catch:{ Exception -> 0x0078 }
            r1.mo6340la(r0)     // Catch:{ Exception -> 0x0078 }
            android.content.Context r0 = r27.getContext()     // Catch:{ Exception -> 0x0078 }
            java.lang.String r0 = com.android.messaging.util.ContactUtil.m3530a(r0, r11)     // Catch:{ Exception -> 0x0078 }
            r1.mo6339ka(r0)     // Catch:{ Exception -> 0x0078 }
            java.lang.String r0 = r10.getString(r6)     // Catch:{ Exception -> 0x0078 }
            r1.mo6345na(r0)     // Catch:{ Exception -> 0x0078 }
            java.lang.String r0 = r10.getString(r2)     // Catch:{ Exception -> 0x0078 }
            r1.mo6343ma(r0)     // Catch:{ Exception -> 0x0078 }
            r10.close()
            r9 = r6
            goto L_0x0099
        L_0x0078:
            r0 = move-exception
            goto L_0x0082
        L_0x007a:
            if (r10 == 0) goto L_0x0099
            goto L_0x0096
        L_0x007d:
            r0 = move-exception
            r10 = 0
            goto L_0x00a4
        L_0x0080:
            r0 = move-exception
            r10 = 0
        L_0x0082:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a3 }
            r11.<init>()     // Catch:{ all -> 0x00a3 }
            r11.append(r3)     // Catch:{ all -> 0x00a3 }
            r11.append(r0)     // Catch:{ all -> 0x00a3 }
            java.lang.String r0 = r11.toString()     // Catch:{ all -> 0x00a3 }
            com.android.messaging.util.C1430e.m3622e(r4, r0)     // Catch:{ all -> 0x00a3 }
            if (r10 == 0) goto L_0x0099
        L_0x0096:
            r10.close()
        L_0x0099:
            if (r9 != r6) goto L_0x009c
            return r8
        L_0x009c:
            if (r9 != r8) goto L_0x00a0
            r0 = r8
            goto L_0x00a1
        L_0x00a0:
            r0 = r7
        L_0x00a1:
            r9 = r0
            goto L_0x00ab
        L_0x00a3:
            r0 = move-exception
        L_0x00a4:
            if (r10 == 0) goto L_0x00a9
            r10.close()
        L_0x00a9:
            throw r0
        L_0x00aa:
            r9 = r7
        L_0x00ab:
            java.lang.String r0 = r28.mo6353sf()
            long r10 = r28.getContactId()
            java.lang.String r12 = r28.mo6349ph()
            java.lang.String r13 = r28.mo6348oh()
            java.lang.String r14 = r28.mo6350qh()
            java.lang.String r15 = r28.mo6341lh()
            boolean r16 = android.text.TextUtils.isEmpty(r0)
            if (r16 == 0) goto L_0x00cb
            goto L_0x01da
        L_0x00cb:
            android.content.Context r5 = r27.getContext()     // Catch:{ Exception -> 0x01bb, all -> 0x01b7 }
            com.android.messaging.datamodel.g r0 = com.android.messaging.util.ContactUtil.m3537n(r5, r0)     // Catch:{ Exception -> 0x01bb, all -> 0x01b7 }
            android.database.Cursor r5 = r0.mo6584Xd()     // Catch:{ Exception -> 0x01bb, all -> 0x01b7 }
            if (r5 == 0) goto L_0x0193
            int r0 = r5.getCount()     // Catch:{ Exception -> 0x018f }
            if (r0 != 0) goto L_0x00e1
            goto L_0x0193
        L_0x00e1:
            r17 = -1
            r19 = r17
            r0 = 0
            r16 = 0
            r21 = 0
            r22 = 0
            r23 = 0
        L_0x00ee:
            boolean r24 = r5.moveToNext()     // Catch:{ Exception -> 0x018f }
            if (r24 == 0) goto L_0x013c
            r25 = r3
            long r2 = r5.getLong(r7)     // Catch:{ Exception -> 0x01ae }
            int r26 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1))
            if (r26 == 0) goto L_0x0105
            int r26 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r26 != 0) goto L_0x0103
            goto L_0x0105
        L_0x0103:
            r8 = 3
            goto L_0x012a
        L_0x0105:
            java.lang.String r0 = r5.getString(r8)     // Catch:{ Exception -> 0x01ae }
            android.content.Context r7 = r27.getContext()     // Catch:{ Exception -> 0x01ae }
            java.lang.String r7 = com.android.messaging.util.ContactUtil.m3530a(r7, r2)     // Catch:{ Exception -> 0x01ae }
            java.lang.String r16 = r5.getString(r6)     // Catch:{ Exception -> 0x01ae }
            r6 = 6
            java.lang.String r6 = r5.getString(r6)     // Catch:{ Exception -> 0x01ae }
            r8 = 3
            java.lang.String r19 = r5.getString(r8)     // Catch:{ Exception -> 0x01ae }
            r23 = r6
            r21 = r16
            r22 = r19
            r16 = r0
            r19 = r2
            r0 = r7
        L_0x012a:
            r6 = 0
            int r6 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r6 < 0) goto L_0x013c
            int r2 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x0135
            goto L_0x013c
        L_0x0135:
            r2 = r8
            r3 = r25
            r6 = 2
            r7 = 0
            r8 = 1
            goto L_0x00ee
        L_0x013c:
            r8 = r0
            r4 = r16
            r6 = r19
            r0 = r21
            r2 = r22
            r3 = r23
            r5.close()
            int r5 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r5 == 0) goto L_0x0150
            r5 = 1
            goto L_0x0151
        L_0x0150:
            r5 = 0
        L_0x0151:
            boolean r10 = android.text.TextUtils.equals(r4, r12)
            r12 = 1
            r10 = r10 ^ r12
            boolean r11 = android.text.TextUtils.equals(r8, r13)
            r11 = r11 ^ r12
            boolean r13 = android.text.TextUtils.equals(r0, r14)
            r13 = r13 ^ r12
            boolean r14 = android.text.TextUtils.equals(r2, r15)
            r14 = r14 ^ r12
            if (r5 != 0) goto L_0x0174
            if (r10 != 0) goto L_0x0174
            if (r11 != 0) goto L_0x0174
            if (r13 != 0) goto L_0x0174
            if (r14 == 0) goto L_0x0171
            goto L_0x0174
        L_0x0171:
            r7 = 0
            goto L_0x01da
        L_0x0174:
            r1.setContactId(r6)
            r1.mo6340la(r4)
            r1.mo6339ka(r8)
            r1.mo6345na(r0)
            r1.mo6343ma(r3)
            r1.mo6338ja(r2)
            if (r14 == 0) goto L_0x018b
            r1.mo6347oa(r2)
        L_0x018b:
            r7 = r12
            goto L_0x01da
        L_0x018d:
            r0 = move-exception
            goto L_0x01dd
        L_0x018f:
            r0 = move-exception
            r25 = r3
            goto L_0x01c0
        L_0x0193:
            r25 = r3
            r12 = r8
            r2 = -2
            int r0 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x01b0
            r1.setContactId(r2)     // Catch:{ Exception -> 0x01ae }
            r2 = 0
            r1.mo6340la(r2)     // Catch:{ Exception -> 0x01ae }
            r1.mo6339ka(r2)     // Catch:{ Exception -> 0x01ae }
            r1.mo6345na(r2)     // Catch:{ Exception -> 0x01ae }
            r1.mo6343ma(r2)     // Catch:{ Exception -> 0x01ae }
            r7 = r12
            goto L_0x01b1
        L_0x01ae:
            r0 = move-exception
            goto L_0x01c0
        L_0x01b0:
            r7 = 0
        L_0x01b1:
            if (r5 == 0) goto L_0x01da
            r5.close()
            goto L_0x01da
        L_0x01b7:
            r0 = move-exception
            r2 = 0
            r5 = r2
            goto L_0x01dd
        L_0x01bb:
            r0 = move-exception
            r25 = r3
            r2 = 0
            r5 = r2
        L_0x01c0:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x018d }
            r1.<init>()     // Catch:{ all -> 0x018d }
            r2 = r25
            r1.append(r2)     // Catch:{ all -> 0x018d }
            r1.append(r0)     // Catch:{ all -> 0x018d }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x018d }
            com.android.messaging.util.C1430e.m3622e(r4, r0)     // Catch:{ all -> 0x018d }
            if (r5 == 0) goto L_0x0171
            r5.close()
            goto L_0x0171
        L_0x01da:
            r0 = r9 | r7
            return r0
        L_0x01dd:
            if (r5 == 0) goto L_0x01e2
            r5.close()
        L_0x01e2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.ParticipantRefresh.m1284b(com.android.messaging.datamodel.p, com.android.messaging.datamodel.data.ParticipantData):boolean");
    }

    /* renamed from: c */
    private static void m1285c(C0955p pVar, ParticipantData participantData) {
        ContentValues contentValues = new ContentValues();
        if (participantData.mo6362zh()) {
            contentValues.put("normalized_destination", participantData.mo6353sf());
            contentValues.put("display_destination", participantData.mo6344mh());
        }
        contentValues.put("contact_id", Long.valueOf(participantData.getContactId()));
        contentValues.put("lookup_key", participantData.mo6342m());
        contentValues.put("full_name", participantData.mo6349ph());
        contentValues.put("first_name", participantData.mo6348oh());
        contentValues.put("profile_photo_uri", participantData.mo6350qh());
        contentValues.put("contact_destination", participantData.mo6341lh());
        contentValues.put("send_destination", participantData.mo6351rh());
        pVar.beginTransaction();
        try {
            pVar.update("participants", contentValues, "_id=?", new String[]{participantData.getId()});
            pVar.setTransactionSuccessful();
        } finally {
            pVar.endTransaction();
        }
    }

    /* renamed from: do */
    private static boolean m1286do() {
        C0774O Jd = C0967f.get().mo6647Jd();
        if (Jd == null) {
            return false;
        }
        if (!f1033xy) {
            synchronized (sLock) {
                if (!f1033xy) {
                    Jd.initialize();
                    f1033xy = true;
                }
            }
        }
        return Jd.mo5901lb();
    }

    /* renamed from: oe */
    public static void m1287oe() {
        if (m1286do() && f1034yy.compareAndSet(false, true)) {
            if (Log.isLoggable("MessagingAppDataModel", 2)) {
                C1430e.m3628v("MessagingAppDataModel", "Started full participant refresh");
            }
            C1478ua.m3823a(f1035zy);
        } else if (Log.isLoggable("MessagingAppDataModel", 2)) {
            C1430e.m3628v("MessagingAppDataModel", "Skipped full participant refresh");
        }
    }

    /* renamed from: pe */
    public static void m1288pe() {
        C1478ua.m3823a(f1032Ay);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0327  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x032f  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x033c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void refreshParticipants(int r19) {
        /*
            r0 = r19
            r1 = 0
            r2 = 2
            com.android.messaging.util.C1424b.m3588b(r0, r1, r2)
            java.lang.String r3 = "MessagingAppDataModel"
            boolean r4 = android.util.Log.isLoggable(r3, r2)
            r5 = 1
            if (r4 == 0) goto L_0x0028
            if (r0 == 0) goto L_0x0023
            if (r0 == r5) goto L_0x001d
            if (r0 == r2) goto L_0x0017
            goto L_0x0028
        L_0x0017:
            java.lang.String r4 = "Start self participant refresh"
            com.android.messaging.util.C1430e.m3628v(r3, r4)
            goto L_0x0028
        L_0x001d:
            java.lang.String r4 = "Start partial participant refresh"
            com.android.messaging.util.C1430e.m3628v(r3, r4)
            goto L_0x0028
        L_0x0023:
            java.lang.String r4 = "Start full participant refresh"
            com.android.messaging.util.C1430e.m3628v(r3, r4)
        L_0x0028:
            boolean r4 = com.android.messaging.util.ContactUtil.m3525Kj()
            if (r4 == 0) goto L_0x0340
            java.lang.String r4 = "android.permission.READ_PHONE_STATE"
            boolean r4 = com.android.messaging.util.C1464na.m3750Ha(r4)
            if (r4 != 0) goto L_0x0038
            goto L_0x0340
        L_0x0038:
            if (r0 != 0) goto L_0x0047
            com.android.messaging.f r4 = com.android.messaging.C0967f.get()
            com.android.messaging.datamodel.O r4 = r4.mo6647Jd()
            if (r4 == 0) goto L_0x0047
            r4.mo5902mb()
        L_0x0047:
            if (r0 == 0) goto L_0x004b
            if (r0 != r2) goto L_0x027b
        L_0x004b:
            java.lang.String r4 = ")"
            boolean r6 = com.android.messaging.util.C1464na.m3759Zj()
            if (r6 != 0) goto L_0x0055
            goto L_0x027b
        L_0x0055:
            com.android.messaging.datamodel.h r6 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r6 = r6.getDatabase()
            com.android.messaging.util.sa r7 = com.android.messaging.util.C1474sa.getDefault()
            com.android.messaging.util.oa r7 = r7.mo8231mk()
            com.android.messaging.util.pa r7 = (com.android.messaging.util.C1468pa) r7
            java.util.List r7 = r7.getActiveSubscriptionInfoList()
            a.b.b r8 = new a.b.b
            r8.<init>()
            r6.beginTransaction()
            com.android.messaging.datamodel.h r9 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r10 = r9.getDatabase()
            java.util.HashSet r9 = new java.util.HashSet
            r9.<init>()
            java.lang.String r11 = "participants"
            java.lang.String[] r12 = com.android.messaging.datamodel.data.C0901M.f1157Wu     // Catch:{ all -> 0x0338 }
            java.lang.String r13 = "sub_id NOT IN ( -2 )"
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x0338 }
            if (r10 == 0) goto L_0x00a7
        L_0x0092:
            boolean r11 = r10.moveToNext()     // Catch:{ all -> 0x00a4 }
            if (r11 == 0) goto L_0x00a7
            int r11 = r10.getInt(r5)     // Catch:{ all -> 0x00a4 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x00a4 }
            r9.add(r11)     // Catch:{ all -> 0x00a4 }
            goto L_0x0092
        L_0x00a4:
            r0 = move-exception
            goto L_0x033a
        L_0x00a7:
            if (r10 == 0) goto L_0x00ac
            r10.close()
        L_0x00ac:
            r10 = -1
            if (r7 == 0) goto L_0x00f4
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x0333 }
        L_0x00b3:
            boolean r11 = r7.hasNext()     // Catch:{ all -> 0x0333 }
            if (r11 == 0) goto L_0x00f4
            java.lang.Object r11 = r7.next()     // Catch:{ all -> 0x0333 }
            android.telephony.SubscriptionInfo r11 = (android.telephony.SubscriptionInfo) r11     // Catch:{ all -> 0x0333 }
            int r12 = r11.getSubscriptionId()     // Catch:{ all -> 0x0333 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x0333 }
            boolean r13 = r9.contains(r13)     // Catch:{ all -> 0x0333 }
            if (r13 != 0) goto L_0x00db
            java.lang.String r13 = com.android.messaging.datamodel.C0951l.m2159p(r12)     // Catch:{ all -> 0x0333 }
            r6.execSQL(r13)     // Catch:{ all -> 0x0333 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x0333 }
            r9.add(r13)     // Catch:{ all -> 0x0333 }
        L_0x00db:
            java.lang.Integer r13 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x0333 }
            r8.put(r13, r11)     // Catch:{ all -> 0x0333 }
            com.android.messaging.util.sa r13 = com.android.messaging.util.C1474sa.getDefault()     // Catch:{ all -> 0x0333 }
            int r13 = r13.getDefaultSmsSubscriptionId()     // Catch:{ all -> 0x0333 }
            if (r12 != r13) goto L_0x00b3
            java.lang.Integer r12 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x0333 }
            r8.put(r12, r11)     // Catch:{ all -> 0x0333 }
            goto L_0x00b3
        L_0x00f4:
            java.util.Set r7 = r8.keySet()     // Catch:{ all -> 0x0333 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x0333 }
        L_0x00fc:
            boolean r9 = r7.hasNext()     // Catch:{ all -> 0x0333 }
            if (r9 == 0) goto L_0x013b
            java.lang.Object r9 = r7.next()     // Catch:{ all -> 0x0333 }
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch:{ all -> 0x0333 }
            java.lang.Object r11 = r8.get(r9)     // Catch:{ all -> 0x0333 }
            android.telephony.SubscriptionInfo r11 = (android.telephony.SubscriptionInfo) r11     // Catch:{ all -> 0x0333 }
            java.lang.CharSequence r12 = r11.getDisplayName()     // Catch:{ all -> 0x0333 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0333 }
            java.lang.String r12 = android.database.DatabaseUtils.sqlEscapeString(r12)     // Catch:{ all -> 0x0333 }
            int r13 = r11.getSimSlotIndex()     // Catch:{ all -> 0x0333 }
            int r11 = r11.getIconTint()     // Catch:{ all -> 0x0333 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0333 }
            r14.<init>()     // Catch:{ all -> 0x0333 }
            java.lang.String r15 = "sub_id = "
            r14.append(r15)     // Catch:{ all -> 0x0333 }
            r14.append(r9)     // Catch:{ all -> 0x0333 }
            java.lang.String r9 = r14.toString()     // Catch:{ all -> 0x0333 }
            java.lang.String r9 = m1283a(r13, r11, r12, r9)     // Catch:{ all -> 0x0333 }
            r6.execSQL(r9)     // Catch:{ all -> 0x0333 }
            goto L_0x00fc
        L_0x013b:
            java.lang.String r7 = "''"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0333 }
            r9.<init>()     // Catch:{ all -> 0x0333 }
            java.lang.String r11 = "sub_id NOT IN ("
            r9.append(r11)     // Catch:{ all -> 0x0333 }
            java.lang.String r11 = ", "
            com.google.common.base.A r11 = com.google.common.base.C1504A.m3943Ua(r11)     // Catch:{ all -> 0x0333 }
            java.util.Set r8 = r8.keySet()     // Catch:{ all -> 0x0333 }
            java.lang.String r8 = r11.mo8513a((java.lang.Iterable) r8)     // Catch:{ all -> 0x0333 }
            r9.append(r8)     // Catch:{ all -> 0x0333 }
            r9.append(r4)     // Catch:{ all -> 0x0333 }
            java.lang.String r8 = r9.toString()     // Catch:{ all -> 0x0333 }
            java.lang.String r7 = m1283a(r10, r1, r7, r8)     // Catch:{ all -> 0x0333 }
            r6.execSQL(r7)     // Catch:{ all -> 0x0333 }
            r6.setTransactionSuccessful()     // Catch:{ all -> 0x0333 }
            r6.endTransaction()
            java.lang.String r6 = "_id"
            com.android.messaging.datamodel.h r7 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r11 = r7.getDatabase()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.lang.String r12 = "participants"
            java.lang.String[] r13 = new java.lang.String[]{r6}     // Catch:{ all -> 0x032b }
            java.lang.String r14 = "sim_slot_id=? AND sub_id NOT IN ( -2 )"
            java.lang.String[] r15 = new java.lang.String[r5]     // Catch:{ all -> 0x032b }
            java.lang.String r8 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x032b }
            r15[r1] = r8     // Catch:{ all -> 0x032b }
            r16 = 0
            r17 = 0
            r18 = 0
            android.database.Cursor r8 = r11.query(r12, r13, r14, r15, r16, r17, r18)     // Catch:{ all -> 0x032b }
            if (r8 == 0) goto L_0x01a8
        L_0x0197:
            boolean r9 = r8.moveToNext()     // Catch:{ all -> 0x01a5 }
            if (r9 == 0) goto L_0x01a8
            java.lang.String r9 = r8.getString(r1)     // Catch:{ all -> 0x01a5 }
            r7.add(r9)     // Catch:{ all -> 0x01a5 }
            goto L_0x0197
        L_0x01a5:
            r0 = move-exception
            goto L_0x032d
        L_0x01a8:
            if (r8 == 0) goto L_0x01ad
            r8.close()
        L_0x01ad:
            int r8 = r7.size()
            if (r8 != 0) goto L_0x01b5
            goto L_0x027b
        L_0x01b5:
            com.android.messaging.datamodel.h r8 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r11 = r8.getDatabase()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0323 }
            r9.<init>()     // Catch:{ all -> 0x0323 }
            r12 = r1
        L_0x01c8:
            int r13 = r7.size()     // Catch:{ all -> 0x0323 }
            if (r12 >= r13) goto L_0x01e2
            r13 = 63
            r9.append(r13)     // Catch:{ all -> 0x0323 }
            int r13 = r7.size()     // Catch:{ all -> 0x0323 }
            int r13 = r13 + r10
            if (r12 >= r13) goto L_0x01df
            r13 = 44
            r9.append(r13)     // Catch:{ all -> 0x0323 }
        L_0x01df:
            int r12 = r12 + 1
            goto L_0x01c8
        L_0x01e2:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0323 }
            r12.<init>()     // Catch:{ all -> 0x0323 }
            java.lang.String r13 = "current_self_id IN ("
            r12.append(r13)     // Catch:{ all -> 0x0323 }
            r12.append(r9)     // Catch:{ all -> 0x0323 }
            r12.append(r4)     // Catch:{ all -> 0x0323 }
            java.lang.String r14 = r12.toString()     // Catch:{ all -> 0x0323 }
            java.lang.String r12 = "conversations"
            java.lang.String[] r13 = new java.lang.String[]{r6}     // Catch:{ all -> 0x0323 }
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch:{ all -> 0x0323 }
            java.lang.Object[] r4 = r7.toArray(r4)     // Catch:{ all -> 0x0323 }
            r15 = r4
            java.lang.String[] r15 = (java.lang.String[]) r15     // Catch:{ all -> 0x0323 }
            r16 = 0
            r17 = 0
            r18 = 0
            android.database.Cursor r4 = r11.query(r12, r13, r14, r15, r16, r17, r18)     // Catch:{ all -> 0x0323 }
            if (r4 == 0) goto L_0x0222
        L_0x0211:
            boolean r6 = r4.moveToNext()     // Catch:{ all -> 0x021f }
            if (r6 == 0) goto L_0x0222
            java.lang.String r6 = r4.getString(r1)     // Catch:{ all -> 0x021f }
            r8.add(r6)     // Catch:{ all -> 0x021f }
            goto L_0x0211
        L_0x021f:
            r0 = move-exception
            goto L_0x0325
        L_0x0222:
            if (r4 == 0) goto L_0x0227
            r4.close()
        L_0x0227:
            int r4 = r8.size()
            if (r4 != 0) goto L_0x022e
            goto L_0x027b
        L_0x022e:
            com.android.messaging.datamodel.h r4 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r4 = r4.getDatabase()
            com.android.messaging.datamodel.data.ParticipantData r4 = com.android.messaging.datamodel.C0887c.m1633a((com.android.messaging.datamodel.C0955p) r4, (int) r10)
            if (r4 == 0) goto L_0x027b
            java.util.Iterator r6 = r8.iterator()
        L_0x0240:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x027b
            java.lang.Object r7 = r6.next()
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r8 = r4.getId()
            com.android.messaging.datamodel.h r9 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r9 = r9.getDatabase()
            r9.beginTransaction()
            com.android.messaging.datamodel.C0887c.m1664c((com.android.messaging.datamodel.C0955p) r9, (java.lang.String) r7, (java.lang.String) r8)     // Catch:{ all -> 0x0276 }
            r9.setTransactionSuccessful()     // Catch:{ all -> 0x0276 }
            r9.endTransaction()
            com.android.messaging.datamodel.MessagingContentProvider.m1273r(r7)
            com.android.messaging.datamodel.MessagingContentProvider.m1272q(r7)
            com.android.messaging.ui.Ea r10 = com.android.messaging.p041ui.C1040Ea.get()
            android.content.Context r9 = r9.getContext()
            r10.mo6961a((android.content.Context) r9, (java.lang.String) r7, (java.lang.String) r8)
            goto L_0x0240
        L_0x0276:
            r0 = move-exception
            r9.endTransaction()
            throw r0
        L_0x027b:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            if (r0 != r5) goto L_0x0291
            java.lang.String[] r0 = new java.lang.String[r5]
            r6 = -1
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r0[r1] = r6
            java.lang.String r6 = "contact_id=?"
        L_0x028e:
            r11 = r0
            r10 = r6
            goto L_0x029a
        L_0x0291:
            if (r0 != r2) goto L_0x0296
            java.lang.String r0 = "sub_id NOT IN ( -2 )"
            goto L_0x0297
        L_0x0296:
            r0 = 0
        L_0x0297:
            r6 = r0
            r0 = 0
            goto L_0x028e
        L_0x029a:
            com.android.messaging.datamodel.h r0 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r6 = r0.getDatabase()
            java.lang.String r8 = "participants"
            java.lang.String[] r9 = com.android.messaging.datamodel.data.C0901M.f1157Wu     // Catch:{ all -> 0x031b }
            r12 = 0
            r13 = 0
            r14 = 0
            r7 = r6
            android.database.Cursor r7 = r7.query(r8, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x031b }
            if (r7 == 0) goto L_0x02db
        L_0x02b0:
            boolean r0 = r7.moveToNext()     // Catch:{ all -> 0x02d9 }
            if (r0 == 0) goto L_0x02db
            com.android.messaging.datamodel.data.ParticipantData r0 = com.android.messaging.datamodel.data.ParticipantData.m1839k(r7)     // Catch:{ Exception -> 0x02d2 }
            boolean r8 = m1284b(r6, r0)     // Catch:{ Exception -> 0x02d2 }
            if (r8 == 0) goto L_0x02b0
            boolean r8 = r0.mo6362zh()     // Catch:{ Exception -> 0x02d2 }
            if (r8 == 0) goto L_0x02c7
            r1 = r5
        L_0x02c7:
            m1285c(r6, r0)     // Catch:{ Exception -> 0x02d2 }
            java.lang.String r0 = r0.getId()     // Catch:{ Exception -> 0x02d2 }
            r4.add(r0)     // Catch:{ Exception -> 0x02d2 }
            goto L_0x02b0
        L_0x02d2:
            r0 = move-exception
            java.lang.String r8 = "ParticipantRefresh: Failed to update participant"
            com.android.messaging.util.C1430e.m3623e(r3, r8, r0)     // Catch:{ all -> 0x02d9 }
            goto L_0x02b0
        L_0x02d9:
            r0 = move-exception
            goto L_0x031d
        L_0x02db:
            if (r7 == 0) goto L_0x02e0
            r7.close()
        L_0x02e0:
            boolean r0 = android.util.Log.isLoggable(r3, r2)
            if (r0 == 0) goto L_0x02fa
            java.lang.String r0 = "Number of participants refreshed:"
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            int r2 = r4.size()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3628v(r3, r0)
        L_0x02fa:
            int r0 = r4.size()
            if (r0 <= 0) goto L_0x0303
            com.android.messaging.datamodel.C0887c.m1652a(r4)
        L_0x0303:
            if (r1 == 0) goto L_0x031a
            com.android.messaging.datamodel.MessagingContentProvider.m1262Va()
            com.android.messaging.f r0 = com.android.messaging.C0967f.get()
            android.content.Context r0 = r0.getApplicationContext()
            android.content.ContentResolver r0 = r0.getContentResolver()
            android.net.Uri r1 = com.android.messaging.datamodel.MessagingContentProvider.f1022Db
            r2 = 0
            r0.notifyChange(r1, r2)
        L_0x031a:
            return
        L_0x031b:
            r0 = move-exception
            r7 = 0
        L_0x031d:
            if (r7 == 0) goto L_0x0322
            r7.close()
        L_0x0322:
            throw r0
        L_0x0323:
            r0 = move-exception
            r4 = 0
        L_0x0325:
            if (r4 == 0) goto L_0x032a
            r4.close()
        L_0x032a:
            throw r0
        L_0x032b:
            r0 = move-exception
            r8 = 0
        L_0x032d:
            if (r8 == 0) goto L_0x0332
            r8.close()
        L_0x0332:
            throw r0
        L_0x0333:
            r0 = move-exception
            r6.endTransaction()
            throw r0
        L_0x0338:
            r0 = move-exception
            r10 = 0
        L_0x033a:
            if (r10 == 0) goto L_0x033f
            r10.close()
        L_0x033f:
            throw r0
        L_0x0340:
            boolean r0 = android.util.Log.isLoggable(r3, r2)
            if (r0 == 0) goto L_0x034b
            java.lang.String r0 = "Skipping participant referesh because of permissions"
            com.android.messaging.util.C1430e.m3628v(r3, r0)
        L_0x034b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.ParticipantRefresh.refreshParticipants(int):void");
    }
}
