package com.android.messaging.datamodel.action;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.p016v4.media.session.C0107q;
import android.util.Log;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.C0778T;
import com.android.messaging.datamodel.C0779U;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.sms.C1029y;
import com.android.messaging.sms.DatabaseMessages$MmsMessage;
import com.android.messaging.sms.DatabaseMessages$MmsPart;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1451h;
import com.android.messaging.util.C1464na;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import p000a.p005b.C0019f;
import p026b.p027a.p030b.p031a.C0632a;

public class SyncMessagesAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0803Q();

    private SyncMessagesAction(long j, long j2, int i, long j3) {
        this.f1057Oy.putLong("lower_bound", j);
        this.f1057Oy.putLong("upper_bound", j2);
        this.f1057Oy.putInt("max_update", i);
        this.f1057Oy.putLong("start_timestamp", j3);
    }

    /* renamed from: I */
    private static void m1446I(long j) {
        if (C1464na.m3750Ha("android.permission.READ_SMS")) {
            new SyncMessagesAction(C1451h.m3724Hd().getLong("last_sync_time_millis", -1), j, 0, j).start();
        }
    }

    /* renamed from: Je */
    public static void m1447Je() {
        C1449g.get().getLong("bugle_sms_sync_backoff_time", 5000);
        long currentTimeMillis = System.currentTimeMillis() - 5000;
        new SyncMessagesAction(-1, currentTimeMillis, 0, currentTimeMillis).start();
    }

    /* renamed from: Ke */
    public static void m1448Ke() {
        m1446I(System.currentTimeMillis());
    }

    /* renamed from: a */
    private void m1449a(C0019f fVar) {
        Context applicationContext = C0967f.get().getApplicationContext();
        int size = fVar.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 128;
            int min = Math.min(i2, size) - i;
            String format = String.format(Locale.US, "%s != '%s' AND %s IN %s", new Object[]{"ct", "application/smil", "mid", C1029y.m2402Da(min)});
            String[] strArr = new String[min];
            for (int i3 = 0; i3 < min; i3++) {
                strArr[i3] = Long.toString(((DatabaseMessages$MmsMessage) fVar.valueAt(i + i3)).getId());
            }
            Cursor query = C0107q.query(applicationContext.getContentResolver(), C1029y.f1556aF, DatabaseMessages$MmsPart.f1504Wu, format, strArr, (String) null);
            if (query != null) {
                while (query.moveToNext()) {
                    try {
                        DatabaseMessages$MmsPart a = DatabaseMessages$MmsPart.m2340a(query, false);
                        DatabaseMessages$MmsMessage databaseMessages$MmsMessage = (DatabaseMessages$MmsMessage) fVar.get(a.f1507jy);
                        if (databaseMessages$MmsMessage != null) {
                            databaseMessages$MmsMessage.mo6793a(a);
                        }
                    } finally {
                        query.close();
                    }
                }
            }
            i = i2;
        }
    }

    public static void sync() {
        C1449g.get().getLong("bugle_sms_sync_backoff_time", 5000);
        m1446I(System.currentTimeMillis() - 5000);
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public Object mo5951i(Bundle bundle) {
        String str;
        long j;
        long j2;
        C0779U u;
        Bundle bundle2 = bundle;
        long j3 = bundle2.getLong("last_timestamp");
        long j4 = this.f1057Oy.getLong("lower_bound");
        long j5 = this.f1057Oy.getLong("upper_bound");
        int i = this.f1057Oy.getInt("max_update");
        long j6 = this.f1057Oy.getLong("start_timestamp");
        C0779U be = C0947h.get().mo6606be();
        if (!be.mo5916g(j5)) {
            C1430e.m3630w("MessagingAppDataModel", "SyncMessagesAction: Ignoring orphan sync batch for messages from " + j4 + " to " + j5);
            return null;
        }
        boolean f = be.mo5915f(j3);
        long j7 = j3;
        if (j3 == Long.MIN_VALUE) {
            C1430e.m3622e("MessagingAppDataModel", "SyncMessagesAction: Sync failed - terminating");
            C1451h Hd = C1451h.m3724Hd();
            Hd.putLong("last_sync_time_millis", j6);
            Hd.putLong("last_full_sync_time_millis", j6);
            be.complete();
            return null;
        } else if (f) {
            C1430e.m3630w("MessagingAppDataModel", "SyncMessagesAction: Redoing dirty sync batch of messages from " + j4 + " to " + j5);
            SyncMessagesAction syncMessagesAction = new SyncMessagesAction(j4, j5, i, j6);
            be.mo5918i(j5);
            mo5949b(syncMessagesAction);
            return null;
        } else {
            ArrayList parcelableArrayList = bundle2.getParcelableArrayList("sms_to_add");
            ArrayList parcelableArrayList2 = bundle2.getParcelableArrayList("mms_to_add");
            ArrayList parcelableArrayList3 = bundle2.getParcelableArrayList("messages_to_delete");
            int size = parcelableArrayList3.size() + parcelableArrayList2.size() + parcelableArrayList.size();
            if (size > 0) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                new C0802P(parcelableArrayList, parcelableArrayList2, parcelableArrayList3, be.mo5921se()).mo6001Se();
                j = SystemClock.elapsedRealtime() - elapsedRealtime;
                StringBuilder sb = new StringBuilder();
                str = "last_full_sync_time_millis";
                sb.append("SyncMessagesAction: Updated local database (took ");
                sb.append(j);
                sb.append(" ms). Added ");
                sb.append(parcelableArrayList.size());
                sb.append(" SMS, added ");
                sb.append(parcelableArrayList2.size());
                sb.append(" MMS, deleted ");
                sb.append(parcelableArrayList3.size());
                sb.append(" messages.");
                C1430e.m3625i("MessagingAppDataModel", sb.toString());
                MessagingContentProvider.m1264Xa();
                j2 = 0;
            } else {
                str = "last_full_sync_time_millis";
                if (Log.isLoggable("MessagingAppDataModel", 3)) {
                    C1430e.m3620d("MessagingAppDataModel", "SyncMessagesAction: No local database updates to make");
                }
                if (!be.mo5920re()) {
                    MessagingContentProvider.m1263Wa();
                    MessagingContentProvider.m1265Ya();
                }
                j2 = 0;
                j = 0;
            }
            int i2 = (j7 > j2 ? 1 : (j7 == j2 ? 0 : -1));
            boolean z = false;
            if (i2 < 0 || j7 < j4) {
                C1451h Hd2 = C1451h.m3724Hd();
                Hd2.putLong("last_sync_time_millis", j6);
                int i3 = (j4 > 0 ? 1 : (j4 == 0 ? 0 : -1));
                if (i3 < 0) {
                    Hd2.putLong(str, j6);
                }
                long currentTimeMillis = System.currentTimeMillis();
                C0801O o = new C0801O(j6, currentTimeMillis);
                C0801O o2 = new C0801O(-1, j6);
                C0955p database = C0947h.get().getDatabase();
                if (!o.mo5998b(database)) {
                    C1430e.m3625i("MessagingAppDataModel", "SyncMessagesAction: Changed messages after sync; scheduling an incremental sync now.");
                    u = be;
                    SyncMessagesAction syncMessagesAction2 = new SyncMessagesAction(j6, currentTimeMillis, 0, j6);
                    u.mo5918i(currentTimeMillis);
                    mo5949b(syncMessagesAction2);
                } else {
                    long j8 = j6;
                    u = be;
                    if (i3 < 0 || o2.mo5998b(database)) {
                        C1430e.m3625i("MessagingAppDataModel", "SyncMessagesAction: All messages now in sync");
                        u.complete();
                    } else {
                        C1430e.m3630w("MessagingAppDataModel", "SyncMessagesAction: Changed messages before sync batch; scheduling a full sync now.");
                        SyncMessagesAction syncMessagesAction3 = new SyncMessagesAction(-1, j8, 0, j8);
                        u.mo5918i(j8);
                        mo5949b(syncMessagesAction3);
                    }
                }
            } else {
                if (Log.isLoggable("MessagingAppDataModel", 3)) {
                    C1430e.m3620d("MessagingAppDataModel", "SyncMessagesAction: More messages to sync; scheduling next sync batch now.");
                }
                long j9 = j7 + 1;
                C1449g.get().getLong("bugle_sms_sync_batch_time_limit", 400);
                SyncMessagesAction syncMessagesAction4 = new SyncMessagesAction(j4, j9, j <= 0 ? 0 : (int) ((((double) size) / ((double) j)) * ((double) 400)), j6);
                be.mo5918i(j9);
                mo5949b(syncMessagesAction4);
                u = be;
            }
            if (mo5957xe() || !u.mo5922te()) {
                z = true;
            }
            C1424b.m3592ia(z);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x01ba  */
    /* renamed from: ue */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle mo5955ue() {
        /*
            r22 = this;
            r0 = r22
            com.android.messaging.util.g r1 = com.android.messaging.util.C1449g.get()
            com.android.messaging.datamodel.h r2 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r2 = r2.getDatabase()
            java.lang.String r3 = "bugle_sms_sync_batch_max_messages_to_scan"
            r4 = 4000(0xfa0, float:5.605E-42)
            r1.getInt(r3, r4)
            android.os.Bundle r3 = r0.f1057Oy
            java.lang.String r5 = "max_update"
            int r3 = r3.getInt(r5)
            java.lang.String r5 = "bugle_sms_sync_batch_size_min"
            r6 = 80
            r1.getInt(r5, r6)
            java.lang.String r5 = "bugle_sms_sync_batch_size_max"
            r7 = 1000(0x3e8, float:1.401E-42)
            r1.getInt(r5, r7)
            int r1 = java.lang.Math.min(r3, r7)
            int r9 = java.lang.Math.max(r6, r1)
            android.os.Bundle r1 = r0.f1057Oy
            java.lang.String r3 = "lower_bound"
            long r5 = r1.getLong(r3)
            android.os.Bundle r1 = r0.f1057Oy
            java.lang.String r3 = "upper_bound"
            long r7 = r1.getLong(r3)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "SyncMessagesAction: Starting batch for messages from "
            r1.append(r3)
            r1.append(r5)
            java.lang.String r3 = " to "
            r1.append(r3)
            r1.append(r7)
            java.lang.String r3 = " (message update limit = "
            r1.append(r3)
            r1.append(r9)
            java.lang.String r3 = ", message scan limit = "
            r1.append(r3)
            r1.append(r4)
            java.lang.String r14 = ")"
            r1.append(r14)
            java.lang.String r1 = r1.toString()
            java.lang.String r15 = "MessagingAppDataModel"
            com.android.messaging.util.C1430e.m3625i(r15, r1)
            com.android.messaging.datamodel.h r1 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.U r1 = r1.mo6606be()
            com.android.messaging.datamodel.T r13 = r1.mo5921se()
            r13.clear()
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            a.b.f r11 = new a.b.f
            r11.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            boolean r1 = r1.mo5916g(r7)
            r16 = -9223372036854775808
            if (r1 == 0) goto L_0x01ab
            com.android.messaging.datamodel.action.O r1 = new com.android.messaging.datamodel.action.O
            r1.<init>(r5, r7)
            java.lang.String r5 = " of "
            long r18 = android.os.SystemClock.elapsedRealtime()
            r6 = 3
            r1.mo5999c(r2)     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            int r2 = r1.mo5993Oe()     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            int r8 = r1.mo5995Qe()     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            boolean r6 = android.util.Log.isLoggable(r15, r6)     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            if (r6 == 0) goto L_0x00e6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            r6.<init>()     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            java.lang.String r7 = "SyncMessagesAction: Scanning cursors (local count = "
            r6.append(r7)     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            r6.append(r2)     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            java.lang.String r7 = ", remote count = "
            r6.append(r7)     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            r6.append(r8)     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            java.lang.String r7 = ", message update limit = "
            r6.append(r7)     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            r6.append(r9)     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            r6.append(r3)     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            r6.append(r4)     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            r6.append(r14)     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            java.lang.String r3 = r6.toString()     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
            com.android.messaging.util.C1430e.m3620d(r15, r3)     // Catch:{ SQLiteException -> 0x014e, Exception -> 0x0143 }
        L_0x00e6:
            r3 = 4000(0xfa0, float:5.605E-42)
            r7 = r1
            r4 = r8
            r8 = r3
            r3 = r10
            r10 = r12
            r6 = r11
            r20 = r12
            r12 = r3
            r21 = r13
            long r7 = r7.mo5997a((int) r8, (int) r9, (java.util.ArrayList) r10, (p000a.p005b.C0019f) r11, (java.util.ArrayList) r12, (com.android.messaging.datamodel.C0778T) r13)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            int r9 = r1.mo5994Pe()     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            int r10 = r1.mo5996Re()     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            r11 = 3
            boolean r11 = android.util.Log.isLoggable(r15, r11)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            if (r11 == 0) goto L_0x0131
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            r11.<init>()     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            java.lang.String r12 = "SyncMessagesAction: Scanned cursors (local position = "
            r11.append(r12)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            r11.append(r9)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            r11.append(r5)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            r11.append(r2)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            java.lang.String r2 = ", remote position = "
            r11.append(r2)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            r11.append(r10)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            r11.append(r5)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            r11.append(r4)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            r11.append(r14)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            java.lang.String r2 = r11.toString()     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            com.android.messaging.util.C1430e.m3620d(r15, r2)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
        L_0x0131:
            r0.m1449a(r6)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            r2 = r21
            r0.m1450a(r6, r2)     // Catch:{ SQLiteException -> 0x013f, Exception -> 0x013d }
            r1.close()
            goto L_0x015d
        L_0x013d:
            r0 = move-exception
            goto L_0x0148
        L_0x013f:
            r0 = move-exception
            goto L_0x0153
        L_0x0141:
            r0 = move-exception
            goto L_0x01a7
        L_0x0143:
            r0 = move-exception
            r3 = r10
            r6 = r11
            r20 = r12
        L_0x0148:
            java.lang.String r2 = "SyncMessagesAction: unexpected failure in scan"
            com.android.messaging.util.C1430e.wtf(r15, r2, r0)     // Catch:{ all -> 0x0141 }
            goto L_0x0158
        L_0x014e:
            r0 = move-exception
            r3 = r10
            r6 = r11
            r20 = r12
        L_0x0153:
            java.lang.String r2 = "SyncMessagesAction: Database exception"
            com.android.messaging.util.C1430e.m3623e(r15, r2, r0)     // Catch:{ all -> 0x0141 }
        L_0x0158:
            r1.close()
            r7 = r16
        L_0x015d:
            long r0 = android.os.SystemClock.elapsedRealtime()
            r2 = 3
            boolean r2 = android.util.Log.isLoggable(r15, r2)
            if (r2 == 0) goto L_0x01b1
            java.lang.String r2 = "SyncMessagesAction: Scan complete (took "
            java.lang.StringBuilder r2 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r2)
            long r0 = r0 - r18
            r2.append(r0)
            java.lang.String r0 = " ms). "
            r2.append(r0)
            int r0 = r20.size()
            r2.append(r0)
            java.lang.String r0 = " remote SMS to add, "
            r2.append(r0)
            int r0 = r6.size()
            r2.append(r0)
            java.lang.String r0 = " MMS to add, "
            r2.append(r0)
            int r0 = r3.size()
            r2.append(r0)
            java.lang.String r0 = " local messages to delete. Oldest timestamp seen = "
            r2.append(r0)
            r2.append(r7)
            java.lang.String r0 = r2.toString()
            com.android.messaging.util.C1430e.m3620d(r15, r0)
            goto L_0x01b1
        L_0x01a7:
            r1.close()
            throw r0
        L_0x01ab:
            r3 = r10
            r6 = r11
            r20 = r12
            r7 = r16
        L_0x01b1:
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            int r1 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r1 <= 0) goto L_0x01e3
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
        L_0x01c0:
            int r4 = r6.size()
            if (r2 >= r4) goto L_0x01d2
            java.lang.Object r4 = r6.valueAt(r2)
            com.android.messaging.sms.DatabaseMessages$MmsMessage r4 = (com.android.messaging.sms.DatabaseMessages$MmsMessage) r4
            r1.add(r4)
            int r2 = r2 + 1
            goto L_0x01c0
        L_0x01d2:
            java.lang.String r2 = "sms_to_add"
            r4 = r20
            r0.putParcelableArrayList(r2, r4)
            java.lang.String r2 = "mms_to_add"
            r0.putParcelableArrayList(r2, r1)
            java.lang.String r1 = "messages_to_delete"
            r0.putParcelableArrayList(r1, r3)
        L_0x01e3:
            java.lang.String r1 = "last_timestamp"
            r0.putLong(r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.SyncMessagesAction.mo5955ue():android.os.Bundle");
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        C0955p database = C0947h.get().getDatabase();
        long j = this.f1057Oy.getLong("lower_bound");
        long j2 = this.f1057Oy.getLong("upper_bound");
        int i = this.f1057Oy.getInt("max_update");
        long j3 = this.f1057Oy.getLong("start_timestamp");
        if (Log.isLoggable("MessagingAppDataModel", 3)) {
            C1430e.m3620d("MessagingAppDataModel", "SyncMessagesAction: Request to sync messages from " + j + " to " + j2 + " (start timestamp = " + j3 + ", message update limit = " + i + ")");
        }
        C0779U be = C0947h.get().mo6606be();
        String str = "MessagingAppDataModel";
        if (j >= 0) {
            if (new C0801O(-1, j).mo5998b(database)) {
                String str2 = str;
                if (Log.isLoggable(str2, 3)) {
                    C1430e.m3620d(str2, "SyncMessagesAction: Messages before " + j + " are in sync");
                }
            } else if (be.mo5913d(j3) == 0) {
                this.f1057Oy.putLong("lower_bound", -1);
                String str3 = str;
                if (Log.isLoggable(str3, 3)) {
                    C1430e.m3620d(str3, "SyncMessagesAction: Messages before " + -1 + " not in sync; promoting to full sync");
                }
                j = -1;
            } else {
                String str4 = str;
                if (Log.isLoggable(str4, 3)) {
                    C1430e.m3620d(str4, "SyncMessagesAction: Messages before " + j + " not in sync; will do incremental sync");
                }
            }
        }
        if (!be.mo5911a(j < 0, j3)) {
            return null;
        }
        be.mo5918i(j2);
        mo5944Ee();
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ SyncMessagesAction(Parcel parcel, C0803Q q) {
        super(parcel);
    }

    /* renamed from: a */
    private void m1450a(C0019f fVar, C0778T t) {
        for (int i = 0; i < fVar.size(); i++) {
            DatabaseMessages$MmsMessage databaseMessages$MmsMessage = (DatabaseMessages$MmsMessage) fVar.valueAt(i);
            String str = null;
            if (!(databaseMessages$MmsMessage.mType != 1)) {
                List c = t.mo5909c(databaseMessages$MmsMessage.mThreadId);
                C1424b.m3594t(c);
                C1424b.m3592ia(c.size() > 0);
                if (c.size() == 1) {
                    ParticipantData.m1842vh();
                    if (((String) c.get(0)).equals("ʼUNKNOWN_SENDER!ʼ")) {
                        StringBuilder Pa = C0632a.m1011Pa("SyncMessagesAction: MMS message ");
                        Pa.append(databaseMessages$MmsMessage.mUri);
                        Pa.append(" has unknown sender (thread id = ");
                        Pa.append(databaseMessages$MmsMessage.mThreadId);
                        Pa.append(")");
                        C1430e.m3630w("MessagingAppDataModel", Pa.toString());
                    }
                }
                str = C1029y.m2431a(c, databaseMessages$MmsMessage.mUri);
                if (str == null) {
                    StringBuilder Pa2 = C0632a.m1011Pa("SyncMessagesAction: Could not find sender of incoming MMS message ");
                    Pa2.append(databaseMessages$MmsMessage.getUri());
                    Pa2.append("; using 'unknown sender' instead");
                    C1430e.m3630w("MessagingAppDataModel", Pa2.toString());
                    ParticipantData.m1842vh();
                    str = "ʼUNKNOWN_SENDER!ʼ";
                }
            }
            databaseMessages$MmsMessage.mo6799qa(str);
        }
    }
}
