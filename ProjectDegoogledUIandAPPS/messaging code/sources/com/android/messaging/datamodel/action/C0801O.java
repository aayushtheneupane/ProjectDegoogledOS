package com.android.messaging.datamodel.action;

import android.database.Cursor;
import android.text.TextUtils;
import com.android.messaging.datamodel.C0778T;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.sms.C1017m;
import com.android.messaging.sms.C1029y;
import com.android.messaging.sms.DatabaseMessages$LocalDatabaseMessage;
import com.android.messaging.sms.DatabaseMessages$MmsMessage;
import com.android.messaging.sms.DatabaseMessages$SmsMessage;
import com.android.messaging.util.C1424b;
import com.google.common.collect.C1630W;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import p000a.p005b.C0019f;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.action.O */
class C0801O {

    /* renamed from: iz */
    private static final String f1068iz = String.format(Locale.US, "(%s NOTNULL)", new Object[]{"sms_message_uri"});

    /* renamed from: jz */
    private static final String[] f1069jz = {"count()"};

    /* renamed from: dz */
    private C0797K f1070dz;

    /* renamed from: ez */
    private C0797K f1071ez;

    /* renamed from: fz */
    private final String f1072fz;

    /* renamed from: gz */
    private final String f1073gz;

    /* renamed from: hz */
    private final String f1074hz;

    C0801O(long j, long j2) {
        this.f1072fz = m1388a(f1068iz, "received_timestamp", j, j2, (String) null, (String) null);
        this.f1073gz = m1388a(C1029y.m2409Hi(), "date", j, j2, (String) null, (String) null);
        this.f1074hz = m1388a(C1029y.m2408Gi(), "date", j >= 0 ? (j + 999) / 1000 : j, j2 >= 0 ? (j2 + 999) / 1000 : j2, (String) null, (String) null);
    }

    /* renamed from: i */
    static /* synthetic */ DatabaseMessages$LocalDatabaseMessage m1390i(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        return new DatabaseMessages$LocalDatabaseMessage(cursor.getLong(0), cursor.getInt(3), cursor.getString(2), cursor.getLong(1), cursor.getString(4));
    }

    /* renamed from: o */
    private static int m1391o(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        throw new IllegalArgumentException(C0632a.m1023d("Cannot get count from ", cursor != null ? cursor.getCount() == 0 ? "empty" : "" : "null", " cursor"));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Oe */
    public int mo5993Oe() {
        return this.f1070dz.getCount();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Pe */
    public int mo5994Pe() {
        return this.f1070dz.getPosition();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Qe */
    public int mo5995Qe() {
        return this.f1071ez.getCount();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Re */
    public int mo5996Re() {
        return this.f1071ez.getPosition();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public long mo5997a(int i, int i2, ArrayList arrayList, C0019f fVar, ArrayList arrayList2, C0778T t) {
        ArrayList arrayList3 = arrayList;
        C0019f fVar2 = fVar;
        ArrayList arrayList4 = arrayList2;
        C0778T t2 = t;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        C1017m next = this.f1071ez.next();
        C1017m next2 = this.f1070dz.next();
        int i3 = 0;
        long j = Long.MAX_VALUE;
        int i4 = 0;
        while (i3 + i4 < i) {
            if (arrayList2.size() + fVar.size() + arrayList.size() >= i2) {
                return j;
            }
            if (next == null && next2 == null) {
                return -1;
            }
            if ((next == null && next2 != null) || (next2 != null && next != null && next2.mo6791hi() > next.mo6791hi())) {
                arrayList4.add((DatabaseMessages$LocalDatabaseMessage) next2);
                j = Math.min(j, next2.mo6791hi());
                next2 = this.f1070dz.next();
                i3++;
            } else if ((next2 != null || next == null) && (next2 == null || next == null || next2.mo6791hi() >= next.mo6791hi())) {
                long hi = next2.mo6791hi();
                long min = Math.min(j, hi);
                C1017m next3 = this.f1071ez.next();
                long j2 = min;
                C1017m next4 = this.f1070dz.next();
                if ((next3 == null || next3.mo6791hi() != hi) && (next4 == null || next4.mo6791hi() != hi)) {
                    if (!next.equals(next2)) {
                        arrayList4.add((DatabaseMessages$LocalDatabaseMessage) next2);
                        m1389a(arrayList3, fVar2, next, t2);
                    }
                    i3++;
                    i4++;
                } else {
                    hashSet2.clear();
                    hashSet2.add(next);
                    i4++;
                    while (next3 != null && next3.mo6791hi() == hi) {
                        C1424b.m3592ia(!hashSet2.contains(next3));
                        hashSet2.add(next3);
                        i4++;
                        next3 = this.f1071ez.next();
                    }
                    hashSet.clear();
                    hashSet.add(next2);
                    i3++;
                    while (next4 != null && next4.mo6791hi() == hi) {
                        if (hashSet.contains(next4)) {
                            arrayList4.add((DatabaseMessages$LocalDatabaseMessage) next4);
                        } else {
                            hashSet.add(next4);
                        }
                        i3++;
                        next4 = this.f1070dz.next();
                    }
                    Iterator it = C1630W.m4530a((Set) hashSet, (Set) hashSet2).iterator();
                    while (it.hasNext()) {
                        arrayList4.add((DatabaseMessages$LocalDatabaseMessage) it.next());
                    }
                    Iterator it2 = C1630W.m4530a((Set) hashSet2, (Set) hashSet).iterator();
                    while (it2.hasNext()) {
                        m1389a(arrayList3, fVar2, (C1017m) it2.next(), t2);
                    }
                }
                next = next3;
                next2 = next4;
                j = j2;
            } else {
                m1389a(arrayList3, fVar2, next, t2);
                j = Math.min(j, next.mo6791hi());
                next = this.f1071ez.next();
                i4++;
            }
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo5998b(com.android.messaging.datamodel.C0955p r20) {
        /*
            r19 = this;
            r0 = r19
            java.lang.String r3 = r0.f1072fz
            java.lang.String r8 = r0.f1073gz
            java.lang.String r12 = r0.f1074hz
            java.lang.String r15 = "MessagingApp"
            com.android.messaging.f r0 = com.android.messaging.C0967f.get()
            android.content.Context r10 = r0.getApplicationContext()
            r16 = 1
            r17 = 0
            java.lang.String r1 = "messages"
            java.lang.String[] r2 = f1069jz     // Catch:{ Exception -> 0x00c6, all -> 0x00c1 }
            r5 = 0
            r6 = 0
            r7 = 0
            r4 = 0
            r0 = r20
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x00c6, all -> 0x00c1 }
            int r0 = m1391o(r1)     // Catch:{ Exception -> 0x00ba, all -> 0x00b3 }
            android.content.ContentResolver r4 = r10.getContentResolver()     // Catch:{ Exception -> 0x00ba, all -> 0x00b3 }
            android.net.Uri r5 = android.provider.Telephony.Sms.CONTENT_URI     // Catch:{ Exception -> 0x00ba, all -> 0x00b3 }
            java.lang.String[] r6 = f1069jz     // Catch:{ Exception -> 0x00ba, all -> 0x00b3 }
            r9 = 0
            r2 = 0
            r7 = r8
            r8 = r2
            android.database.Cursor r2 = android.support.p016v4.media.session.C0107q.query(r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x00ba, all -> 0x00b3 }
            int r3 = m1391o(r2)     // Catch:{ Exception -> 0x00ab, all -> 0x00a3 }
            android.content.ContentResolver r9 = r10.getContentResolver()     // Catch:{ Exception -> 0x00ab, all -> 0x00a3 }
            android.net.Uri r10 = android.provider.Telephony.Mms.CONTENT_URI     // Catch:{ Exception -> 0x00ab, all -> 0x00a3 }
            java.lang.String[] r11 = f1069jz     // Catch:{ Exception -> 0x00ab, all -> 0x00a3 }
            r14 = 0
            r13 = 0
            android.database.Cursor r4 = android.support.p016v4.media.session.C0107q.query(r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x00ab, all -> 0x00a3 }
            int r5 = m1391o(r4)     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            int r3 = r3 + r5
            if (r0 != r3) goto L_0x0054
            r5 = r16
            goto L_0x0055
        L_0x0054:
            r5 = 0
        L_0x0055:
            if (r5 == 0) goto L_0x0073
            r3 = 3
            boolean r3 = android.util.Log.isLoggable(r15, r3)     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            if (r3 == 0) goto L_0x008f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            r3.<init>()     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            java.lang.String r6 = "SyncCursorPair: Same # of local and remote messages = "
            r3.append(r6)     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            r3.append(r0)     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            com.android.messaging.util.C1430e.m3620d(r15, r0)     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            goto L_0x008f
        L_0x0073:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            r6.<init>()     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            java.lang.String r7 = "SyncCursorPair: Not in sync; # local messages = "
            r6.append(r7)     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            r6.append(r0)     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            java.lang.String r0 = ", # remote message = "
            r6.append(r0)     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            r6.append(r3)     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x009f, all -> 0x009b }
            com.android.messaging.util.C1430e.m3625i(r15, r0)     // Catch:{ Exception -> 0x009f, all -> 0x009b }
        L_0x008f:
            r1.close()
            r2.close()
            r4.close()
            r16 = r5
            goto L_0x00de
        L_0x009b:
            r0 = move-exception
            r17 = r4
            goto L_0x00a4
        L_0x009f:
            r0 = move-exception
            r17 = r4
            goto L_0x00ac
        L_0x00a3:
            r0 = move-exception
        L_0x00a4:
            r18 = r17
            r17 = r1
            r1 = r18
            goto L_0x00e0
        L_0x00ab:
            r0 = move-exception
        L_0x00ac:
            r18 = r17
            r17 = r1
            r1 = r18
            goto L_0x00ca
        L_0x00b3:
            r0 = move-exception
            r2 = r17
            r17 = r1
            r1 = r2
            goto L_0x00e0
        L_0x00ba:
            r0 = move-exception
            r2 = r17
            r17 = r1
            r1 = r2
            goto L_0x00ca
        L_0x00c1:
            r0 = move-exception
            r1 = r17
            r2 = r1
            goto L_0x00e0
        L_0x00c6:
            r0 = move-exception
            r1 = r17
            r2 = r1
        L_0x00ca:
            java.lang.String r3 = "SyncCursorPair: failed to query local or remote message counts"
            com.android.messaging.util.C1430e.m3623e(r15, r3, r0)     // Catch:{ all -> 0x00df }
            if (r17 == 0) goto L_0x00d4
            r17.close()
        L_0x00d4:
            if (r2 == 0) goto L_0x00d9
            r2.close()
        L_0x00d9:
            if (r1 == 0) goto L_0x00de
            r1.close()
        L_0x00de:
            return r16
        L_0x00df:
            r0 = move-exception
        L_0x00e0:
            if (r17 == 0) goto L_0x00e5
            r17.close()
        L_0x00e5:
            if (r2 == 0) goto L_0x00ea
            r2.close()
        L_0x00ea:
            if (r1 == 0) goto L_0x00ef
            r1.close()
        L_0x00ef:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.C0801O.mo5998b(com.android.messaging.datamodel.p):boolean");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo5999c(C0955p pVar) {
        this.f1070dz = new C0798L(pVar, this.f1072fz);
        this.f1071ez = new C0800N(this.f1073gz, this.f1074hz);
    }

    /* access modifiers changed from: package-private */
    public void close() {
        C0797K k = this.f1070dz;
        if (k != null) {
            k.close();
        }
        C0797K k2 = this.f1071ez;
        if (k2 != null) {
            k2.close();
        }
    }

    /* renamed from: a */
    private static String m1388a(String str, String str2, long j, long j2, String str3, String str4) {
        StringBuilder Pa = C0632a.m1011Pa(str);
        if (j > 0) {
            Pa.append(" AND ");
            Pa.append(str2);
            Pa.append(">=");
            Pa.append(j);
        }
        if (j2 > 0) {
            Pa.append(" AND ");
            Pa.append(str2);
            Pa.append("<");
            Pa.append(j2);
        }
        if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            Pa.append(" AND ");
            Pa.append(str3);
            Pa.append("=");
            Pa.append(str4);
        }
        return Pa.toString();
    }

    /* renamed from: a */
    private void m1389a(List list, C0019f fVar, C1017m mVar, C0778T t) {
        long j;
        if (mVar.getProtocol() == 1) {
            DatabaseMessages$MmsMessage databaseMessages$MmsMessage = (DatabaseMessages$MmsMessage) mVar;
            fVar.append(databaseMessages$MmsMessage.getId(), databaseMessages$MmsMessage);
            j = databaseMessages$MmsMessage.mThreadId;
        } else {
            DatabaseMessages$SmsMessage databaseMessages$SmsMessage = (DatabaseMessages$SmsMessage) mVar;
            list.add(databaseMessages$SmsMessage);
            j = databaseMessages$SmsMessage.mThreadId;
        }
        t.mo5909c(j);
    }
}
