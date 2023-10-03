package com.android.messaging.datamodel.action;

import android.database.sqlite.SQLiteConstraintException;
import android.text.TextUtils;
import android.util.Log;
import com.android.messaging.datamodel.C0778T;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.sms.C1029y;
import com.android.messaging.sms.DatabaseMessages$LocalDatabaseMessage;
import com.android.messaging.sms.DatabaseMessages$MmsMessage;
import com.android.messaging.sms.DatabaseMessages$SmsMessage;
import com.android.messaging.util.C1430e;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.action.P */
class C0802P {

    /* renamed from: kz */
    private final HashSet f1075kz = new HashSet();

    /* renamed from: lz */
    private final C0778T f1076lz;

    /* renamed from: mz */
    private final ArrayList f1077mz;

    /* renamed from: nz */
    private final ArrayList f1078nz;

    /* renamed from: oz */
    private final ArrayList f1079oz;

    C0802P(ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, C0778T t) {
        this.f1077mz = arrayList;
        this.f1078nz = arrayList2;
        this.f1079oz = arrayList3;
        this.f1076lz = t;
    }

    /* renamed from: a */
    private void m1401a(C0955p pVar, DatabaseMessages$SmsMessage databaseMessages$SmsMessage) {
        String str;
        C0955p pVar2 = pVar;
        DatabaseMessages$SmsMessage databaseMessages$SmsMessage2 = databaseMessages$SmsMessage;
        if (databaseMessages$SmsMessage2.f1524im == null) {
            StringBuilder Pa = C0632a.m1011Pa("SyncMessageBatch: SMS ");
            Pa.append(databaseMessages$SmsMessage2.mUri);
            Pa.append(" has no body; adding empty one");
            C1430e.m3630w("MessagingApp", Pa.toString());
            databaseMessages$SmsMessage2.f1524im = "";
        }
        if (TextUtils.isEmpty(databaseMessages$SmsMessage2.mAddress)) {
            C1430e.m3622e("MessagingApp", "SyncMessageBatch: SMS has no address; using unknown sender");
            ParticipantData.m1842vh();
            databaseMessages$SmsMessage2.mAddress = "ʼUNKNOWN_SENDER!ʼ";
        }
        boolean z = true;
        if (databaseMessages$SmsMessage2.mType == 1) {
            z = false;
        }
        boolean z2 = z;
        String str2 = databaseMessages$SmsMessage2.mAddress;
        String a = this.f1076lz.mo5908a(pVar, databaseMessages$SmsMessage2.mThreadId, databaseMessages$SmsMessage2.mSubId, C0947h.get().mo6606be().mo5914e(databaseMessages$SmsMessage2.mThreadId));
        if (a == null) {
            StringBuilder Pa2 = C0632a.m1011Pa("SyncMessageBatch: Failed to create conversation for SMS thread ");
            Pa2.append(databaseMessages$SmsMessage2.mThreadId);
            C1430e.m3622e("MessagingApp", Pa2.toString());
            return;
        }
        ParticipantData sa = ParticipantData.m1841sa(databaseMessages$SmsMessage.getSubId());
        String a2 = C0887c.m1639a(pVar2, sa);
        if (!z2) {
            sa = ParticipantData.m1835g(str2, databaseMessages$SmsMessage.getSubId());
        }
        if (z2) {
            str = a2;
        } else {
            str = C0887c.m1639a(pVar2, sa);
        }
        int c = m1402c(z2, databaseMessages$SmsMessage2.mType, databaseMessages$SmsMessage2.mStatus);
        MessageData a3 = MessageData.m1754a(databaseMessages$SmsMessage2.mUri, str, a2, a, c, databaseMessages$SmsMessage2.f1521BA, databaseMessages$SmsMessage2.f1523_b, databaseMessages$SmsMessage2.f1525yE, databaseMessages$SmsMessage2.f1522YD, databaseMessages$SmsMessage2.f1524im);
        try {
            C0887c.m1643a(pVar2, a3);
            if (Log.isLoggable("MessagingApp", 2)) {
                StringBuilder Pa3 = C0632a.m1011Pa("SyncMessageBatch: Inserted new message ");
                Pa3.append(a3.getMessageId());
                Pa3.append(" for SMS ");
                Pa3.append(a3.mo6253Wg());
                Pa3.append(" received at ");
                Pa3.append(a3.mo6288rg());
                C1430e.m3628v("MessagingApp", Pa3.toString());
            }
            this.f1075kz.add(a);
        } catch (SQLiteConstraintException e) {
            m1399a(e, pVar, databaseMessages$SmsMessage2.mUri, databaseMessages$SmsMessage2.mThreadId, a, a2, str);
            throw null;
        }
    }

    /* renamed from: c */
    public static int m1402c(boolean z, int i, int i2) {
        if (!z) {
            return 100;
        }
        if (i == 5 || i == 4 || i == 6 || (i == 2 && i2 >= 64)) {
            return 8;
        }
        return i2 == 0 ? 2 : 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0006 A[SYNTHETIC] */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1403e(com.android.messaging.datamodel.C0955p r10) {
        /*
            r9 = this;
            java.util.HashSet r0 = r9.f1075kz
            java.util.Iterator r0 = r0.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0069
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = com.android.messaging.datamodel.C0887c.m1653a((com.android.messaging.datamodel.C0955p) r10, (java.lang.String) r1)
            if (r2 == 0) goto L_0x0019
            goto L_0x0006
        L_0x0019:
            com.android.messaging.datamodel.T r2 = r9.f1076lz
            boolean r2 = r2.mo5907O(r1)
            com.android.messaging.util.C1424b.m3584Gj()
            r3 = 0
            r4 = 1
            com.android.messaging.util.C1424b.m3584Gj()     // Catch:{ SQLiteDoneException -> 0x004f }
            r5 = 2
            java.lang.String r6 = "SELECT latest_message_id FROM conversations WHERE _id=? LIMIT 1"
            android.database.sqlite.SQLiteStatement r5 = r10.mo6624b(r5, r6)     // Catch:{ SQLiteDoneException -> 0x004f }
            r5.clearBindings()     // Catch:{ SQLiteDoneException -> 0x004f }
            r5.bindString(r4, r1)     // Catch:{ SQLiteDoneException -> 0x004f }
            java.lang.String r5 = r5.simpleQueryForString()     // Catch:{ SQLiteDoneException -> 0x004f }
            com.android.messaging.util.C1424b.m3584Gj()     // Catch:{ SQLiteDoneException -> 0x004d }
            r6 = 3
            java.lang.String r7 = "SELECT _id FROM messages WHERE conversation_id=? ORDER BY received_timestamp DESC LIMIT 1"
            android.database.sqlite.SQLiteStatement r6 = r10.mo6624b(r6, r7)     // Catch:{ SQLiteDoneException -> 0x004d }
            r6.clearBindings()     // Catch:{ SQLiteDoneException -> 0x004d }
            r6.bindString(r4, r1)     // Catch:{ SQLiteDoneException -> 0x004d }
            java.lang.String r3 = r6.simpleQueryForString()     // Catch:{ SQLiteDoneException -> 0x004d }
            goto L_0x0059
        L_0x004d:
            r6 = move-exception
            goto L_0x0052
        L_0x004f:
            r5 = move-exception
            r6 = r5
            r5 = r3
        L_0x0052:
            java.lang.String r7 = "MessagingAppDb"
            java.lang.String r8 = "BugleDatabaseOperations: Query for latest message failed"
            com.android.messaging.util.C1430e.m3623e(r7, r8, r6)
        L_0x0059:
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x0065
            boolean r3 = android.text.TextUtils.equals(r5, r3)
            if (r3 != 0) goto L_0x0006
        L_0x0065:
            com.android.messaging.datamodel.C0887c.m1651a((com.android.messaging.datamodel.C0955p) r10, (java.lang.String) r1, (boolean) r4, (boolean) r2)
            goto L_0x0006
        L_0x0069:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.C0802P.m1403e(com.android.messaging.datamodel.p):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Se */
    public void mo6001Se() {
        C0955p database = C0947h.get().getDatabase();
        database.beginTransaction();
        try {
            Iterator it = this.f1077mz.iterator();
            while (it.hasNext()) {
                m1401a(database, (DatabaseMessages$SmsMessage) it.next());
            }
            Iterator it2 = this.f1078nz.iterator();
            while (it2.hasNext()) {
                m1400a(database, (DatabaseMessages$MmsMessage) it2.next());
            }
            Iterator it3 = this.f1079oz.iterator();
            while (it3.hasNext()) {
                this.f1075kz.add(((DatabaseMessages$LocalDatabaseMessage) it3.next()).mo6786Ue());
            }
            ArrayList arrayList = this.f1079oz;
            String[] strArr = new String[arrayList.size()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = Long.toString(((DatabaseMessages$LocalDatabaseMessage) arrayList.get(i)).getLocalId());
            }
            int length = strArr.length;
            int i2 = 0;
            while (i2 < length) {
                int i3 = i2 + 128;
                int min = Math.min(i3, length);
                database.delete("messages", String.format(Locale.US, "%s IN %s", new Object[]{"_id", C1029y.m2402Da(min - i2)}), (String[]) Arrays.copyOfRange(strArr, i2, min));
                i2 = i3;
            }
            Iterator it4 = this.f1079oz.iterator();
            while (it4.hasNext()) {
                DatabaseMessages$LocalDatabaseMessage databaseMessages$LocalDatabaseMessage = (DatabaseMessages$LocalDatabaseMessage) it4.next();
                if (Log.isLoggable("MessagingApp", 2)) {
                    C1430e.m3628v("MessagingApp", "SyncMessageBatch: Deleted message " + databaseMessages$LocalDatabaseMessage.getLocalId() + " for SMS/MMS " + databaseMessages$LocalDatabaseMessage.getUri() + " with timestamp " + databaseMessages$LocalDatabaseMessage.mo6791hi());
                }
            }
            m1403e(database);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    /* renamed from: a */
    private void m1400a(C0955p pVar, DatabaseMessages$MmsMessage databaseMessages$MmsMessage) {
        String str;
        C0955p pVar2 = pVar;
        DatabaseMessages$MmsMessage databaseMessages$MmsMessage2 = databaseMessages$MmsMessage;
        if (databaseMessages$MmsMessage2.f1491_l.size() < 1) {
            StringBuilder Pa = C0632a.m1011Pa("SyncMessageBatch: MMS ");
            Pa.append(databaseMessages$MmsMessage2.mUri);
            Pa.append(" has no parts");
            C1430e.m3630w("MessagingApp", Pa.toString());
        }
        boolean z = databaseMessages$MmsMessage2.mType != 1;
        boolean z2 = databaseMessages$MmsMessage2.f1493bE == 130;
        String str2 = databaseMessages$MmsMessage2.f1495dE;
        String a = this.f1076lz.mo5908a(pVar, databaseMessages$MmsMessage2.mThreadId, databaseMessages$MmsMessage2.mSubId, C0947h.get().mo6606be().mo5914e(databaseMessages$MmsMessage2.mThreadId));
        if (a == null) {
            StringBuilder Pa2 = C0632a.m1011Pa("SyncMessageBatch: Failed to create conversation for MMS thread ");
            Pa2.append(databaseMessages$MmsMessage2.mThreadId);
            C1430e.m3622e("MessagingApp", Pa2.toString());
            return;
        }
        ParticipantData sa = ParticipantData.m1841sa(databaseMessages$MmsMessage.getSubId());
        String a2 = C0887c.m1639a(pVar2, sa);
        if (!z) {
            sa = ParticipantData.m1835g(str2, databaseMessages$MmsMessage.getSubId());
        }
        if (z) {
            str = a2;
        } else {
            str = C0887c.m1639a(pVar2, sa);
        }
        MessageData a3 = C1029y.m2424a(databaseMessages$MmsMessage2, a, str, a2, C1029y.m2437b(z, z2, databaseMessages$MmsMessage2.mType));
        try {
            C0887c.m1643a(pVar2, a3);
            if (Log.isLoggable("MessagingApp", 2)) {
                StringBuilder Pa3 = C0632a.m1011Pa("SyncMessageBatch: Inserted new message ");
                Pa3.append(a3.getMessageId());
                Pa3.append(" for MMS ");
                Pa3.append(a3.mo6253Wg());
                Pa3.append(" received at ");
                Pa3.append(a3.mo6288rg());
                C1430e.m3628v("MessagingApp", Pa3.toString());
            }
            this.f1075kz.add(a);
        } catch (SQLiteConstraintException e) {
            m1399a(e, pVar, databaseMessages$MmsMessage2.mUri, databaseMessages$MmsMessage2.mThreadId, a, a2, str);
            throw null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a5  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m1399a(android.database.sqlite.SQLiteConstraintException r4, com.android.messaging.datamodel.C0955p r5, java.lang.String r6, long r7, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a2 }
            r1.<init>()     // Catch:{ all -> 0x00a2 }
            java.lang.String r2 = "SELECT _id FROM conversations WHERE _id="
            r1.append(r2)     // Catch:{ all -> 0x00a2 }
            r1.append(r9)     // Catch:{ all -> 0x00a2 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00a2 }
            android.database.Cursor r1 = r5.rawQuery(r1, r0)     // Catch:{ all -> 0x00a2 }
            if (r1 == 0) goto L_0x0033
            boolean r2 = r1.moveToFirst()     // Catch:{ all -> 0x0030 }
            if (r2 == 0) goto L_0x0033
            int r0 = r1.getCount()     // Catch:{ all -> 0x0030 }
            r2 = 0
            r3 = 1
            if (r0 != r3) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r3 = r2
        L_0x0028:
            com.android.messaging.util.C1424b.m3592ia(r3)     // Catch:{ all -> 0x0030 }
            java.lang.String r0 = r1.getString(r2)     // Catch:{ all -> 0x0030 }
            goto L_0x0033
        L_0x0030:
            r4 = move-exception
            r0 = r1
            goto L_0x00a3
        L_0x0033:
            if (r1 == 0) goto L_0x0038
            r1.close()
        L_0x0038:
            com.android.messaging.datamodel.data.ParticipantData r1 = com.android.messaging.datamodel.C0887c.m1662c(r5, r10)
            com.android.messaging.datamodel.data.ParticipantData r5 = com.android.messaging.datamodel.C0887c.m1662c(r5, r11)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "SQLiteConstraintException while inserting message for "
            r2.append(r3)
            r2.append(r6)
            java.lang.String r6 = "; conversation id from getOrCreateConversation = "
            r2.append(r6)
            r2.append(r9)
            java.lang.String r6 = " (lookup thread = "
            r2.append(r6)
            r2.append(r7)
            java.lang.String r6 = "), found conversation id = "
            r2.append(r6)
            r2.append(r0)
            java.lang.String r6 = ", found self participant = "
            r2.append(r6)
            java.lang.String r6 = r1.mo6353sf()
            java.lang.String r6 = com.android.messaging.util.C1430e.m3633xa(r6)
            r2.append(r6)
            java.lang.String r6 = " (lookup id = "
            r2.append(r6)
            r2.append(r10)
            java.lang.String r7 = "), found sender participant = "
            r2.append(r7)
            java.lang.String r5 = r5.mo6353sf()
            java.lang.String r5 = com.android.messaging.util.C1430e.m3633xa(r5)
            r2.append(r5)
            r2.append(r6)
            r2.append(r11)
            java.lang.String r5 = ")"
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            r6.<init>(r5, r4)
            throw r6
        L_0x00a2:
            r4 = move-exception
        L_0x00a3:
            if (r0 == 0) goto L_0x00a8
            r0.close()
        L_0x00a8:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.C0802P.m1399a(android.database.sqlite.SQLiteConstraintException, com.android.messaging.datamodel.p, java.lang.String, long, java.lang.String, java.lang.String, java.lang.String):void");
    }
}
