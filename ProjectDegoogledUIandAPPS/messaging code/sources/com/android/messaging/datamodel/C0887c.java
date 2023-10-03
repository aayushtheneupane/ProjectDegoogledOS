package com.android.messaging.datamodel;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.data.C0901M;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1426c;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.C1481w;
import com.android.messaging.util.C1488za;
import com.android.messaging.util.ContactUtil;
import com.android.messaging.widget.WidgetConversationProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import p000a.p005b.C0015b;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.c */
public class C0887c {

    /* renamed from: tx */
    private static final C0015b f1132tx = new C0015b();

    /* renamed from: ux */
    private static final String[] f1133ux = {"_id", "received_timestamp", "sender_id"};

    /* renamed from: a */
    static ArrayList m1641a(List list, int i) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(ParticipantData.m1835g((String) it.next(), i));
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public static boolean m1661b(C0955p pVar, String str, ContentValues contentValues) {
        C1424b.m3584Gj();
        return m1656a(pVar, "conversations", "_id", str, contentValues);
    }

    /* renamed from: c */
    public static void m1663c(C0955p pVar, String str, ContentValues contentValues) {
        C1424b.m3584Gj();
        C1424b.m3592ia(m1666d(pVar, str, contentValues));
    }

    /* renamed from: d */
    public static boolean m1666d(C0955p pVar, String str, ContentValues contentValues) {
        C1424b.m3584Gj();
        return m1656a(pVar, "messages", "_id", str, contentValues);
    }

    /* renamed from: e */
    public static int m1667e(C0955p pVar, String str) {
        ParticipantData c = m1662c(pVar, str);
        if (c == null) {
            return -1;
        }
        C1424b.m3592ia(c.mo6362zh());
        return c.getSubId();
    }

    /* renamed from: f */
    public static long m1668f(C0955p pVar, String str) {
        long j;
        C1424b.m3584Gj();
        Cursor cursor = null;
        try {
            boolean z = true;
            Cursor query = pVar.query("conversations", new String[]{"sms_thread_id"}, "_id =?", new String[]{str}, (String) null, (String) null, (String) null);
            if (query.moveToFirst()) {
                if (query.getCount() != 1) {
                    z = false;
                }
                C1424b.m3592ia(z);
                if (!query.isNull(0)) {
                    j = query.getLong(0);
                    query.close();
                    return j;
                }
            }
            j = -1;
            query.close();
            return j;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: g */
    public static ArrayList m1669g(List list) {
        C1424b.m3584Gj();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((ParticipantData) it.next()).mo6351rh());
        }
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r5v1 */
    /* JADX WARNING: type inference failed for: r5v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r5v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getExistingConversation(com.android.messaging.datamodel.C0955p r2, long r3, boolean r5) {
        /*
            com.android.messaging.util.C1424b.m3584Gj()
            r5 = 0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0037 }
            r0.<init>()     // Catch:{ all -> 0x0037 }
            java.lang.String r1 = "SELECT _id FROM conversations WHERE sms_thread_id="
            r0.append(r1)     // Catch:{ all -> 0x0037 }
            r0.append(r3)     // Catch:{ all -> 0x0037 }
            java.lang.String r3 = r0.toString()     // Catch:{ all -> 0x0037 }
            android.database.Cursor r2 = r2.rawQuery(r3, r5)     // Catch:{ all -> 0x0037 }
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x0034 }
            if (r3 == 0) goto L_0x0030
            int r3 = r2.getCount()     // Catch:{ all -> 0x0034 }
            r4 = 0
            r5 = 1
            if (r3 != r5) goto L_0x0028
            goto L_0x0029
        L_0x0028:
            r5 = r4
        L_0x0029:
            com.android.messaging.util.C1424b.m3592ia(r5)     // Catch:{ all -> 0x0034 }
            java.lang.String r5 = r2.getString(r4)     // Catch:{ all -> 0x0034 }
        L_0x0030:
            r2.close()
            return r5
        L_0x0034:
            r3 = move-exception
            r5 = r2
            goto L_0x0038
        L_0x0037:
            r3 = move-exception
        L_0x0038:
            if (r5 == 0) goto L_0x003d
            r5.close()
        L_0x003d:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0887c.getExistingConversation(com.android.messaging.datamodel.p, long, boolean):java.lang.String");
    }

    private static String getParticipantId(C0955p pVar, int i, String str) {
        String str2;
        Cursor query;
        synchronized (f1132tx) {
            str2 = (String) f1132tx.get(str);
        }
        if (str2 != null) {
            return str2;
        }
        Cursor cursor = null;
        boolean z = true;
        if (i != -2) {
            try {
                query = pVar.query("participants", new String[]{"_id"}, "sub_id=?", new String[]{Integer.toString(i)}, (String) null, (String) null, (String) null);
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } else {
            query = pVar.query("participants", new String[]{"_id"}, "normalized_destination=? AND sub_id=?", new String[]{str, Integer.toString(i)}, (String) null, (String) null, (String) null);
        }
        cursor = query;
        if (cursor.moveToFirst()) {
            if (cursor.getCount() != 1) {
                z = false;
            }
            C1424b.m3592ia(z);
            str2 = cursor.getString(0);
            synchronized (f1132tx) {
                f1132tx.put(str, str2);
            }
        }
        cursor.close();
        return str2;
    }

    public static ArrayList getParticipantsForConversation(C0955p pVar, String str) {
        C1424b.m3584Gj();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor query = pVar.query("participants", C0901M.f1157Wu, "_id IN ( SELECT participant_id AS _id FROM conversation_participants WHERE conversation_id =? )", new String[]{str}, (String) null, (String) null, (String) null);
            while (query.moveToNext()) {
                arrayList.add(ParticipantData.m1839k(query));
            }
            query.close();
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: h */
    public static void m1672h(List list) {
        C1424b.m3584Gj();
        if (list.size() > 0) {
            HashSet hashSet = new HashSet();
            for (int size = list.size() - 1; size >= 0; size--) {
                String sf = ((ParticipantData) list.get(size)).mo6353sf();
                if (!hashSet.contains(sf)) {
                    hashSet.add(sf);
                } else {
                    list.remove(size);
                }
            }
            if (list.size() > 1) {
                HashSet gk = C1474sa.getDefault().mo8212gk();
                int i = 0;
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (gk.contains(((ParticipantData) it.next()).mo6353sf())) {
                        i++;
                    }
                }
                if (i < list.size()) {
                    for (int size2 = list.size() - 1; size2 >= 0; size2--) {
                        if (gk.contains(((ParticipantData) list.get(size2)).mo6353sf())) {
                            list.remove(size2);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0039  */
    /* renamed from: i */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.messaging.datamodel.data.MessageData m1673i(com.android.messaging.datamodel.C0955p r11, java.lang.String r12) {
        /*
            com.android.messaging.util.C1424b.m3584Gj()
            r0 = 0
            java.lang.String r2 = "messages"
            java.lang.String[] r3 = com.android.messaging.datamodel.data.MessageData.getProjection()     // Catch:{ all -> 0x0035 }
            java.lang.String r4 = "_id=?"
            r9 = 1
            java.lang.String[] r5 = new java.lang.String[r9]     // Catch:{ all -> 0x0035 }
            r10 = 0
            r5[r10] = r12     // Catch:{ all -> 0x0035 }
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r11
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0035 }
            int r12 = r11.getCount()     // Catch:{ all -> 0x0033 }
            com.android.messaging.util.C1424b.m3588b(r12, r10, r9)     // Catch:{ all -> 0x0033 }
            boolean r12 = r11.moveToFirst()     // Catch:{ all -> 0x0033 }
            if (r12 == 0) goto L_0x002f
            com.android.messaging.datamodel.data.MessageData r0 = new com.android.messaging.datamodel.data.MessageData     // Catch:{ all -> 0x0033 }
            r0.<init>()     // Catch:{ all -> 0x0033 }
            r0.mo6260c(r11)     // Catch:{ all -> 0x0033 }
        L_0x002f:
            r11.close()
            return r0
        L_0x0033:
            r12 = move-exception
            goto L_0x0037
        L_0x0035:
            r12 = move-exception
            r11 = r0
        L_0x0037:
            if (r11 == 0) goto L_0x003c
            r11.close()
        L_0x003c:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0887c.m1673i(com.android.messaging.datamodel.p, java.lang.String):com.android.messaging.datamodel.data.MessageData");
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [com.android.messaging.datamodel.data.MessagePartData] */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.android.messaging.datamodel.data.MessagePartData readMessagePartData(com.android.messaging.datamodel.C0955p r11, java.lang.String r12) {
        /*
            r0 = 0
            java.lang.String r2 = "parts"
            java.lang.String[] r3 = com.android.messaging.datamodel.data.MessagePartData.getProjection()     // Catch:{ all -> 0x002f }
            java.lang.String r4 = "_id=?"
            r9 = 1
            java.lang.String[] r5 = new java.lang.String[r9]     // Catch:{ all -> 0x002f }
            r10 = 0
            r5[r10] = r12     // Catch:{ all -> 0x002f }
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r11
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x002f }
            int r12 = r11.getCount()     // Catch:{ all -> 0x002c }
            com.android.messaging.util.C1424b.m3588b(r12, r10, r9)     // Catch:{ all -> 0x002c }
            boolean r12 = r11.moveToFirst()     // Catch:{ all -> 0x002c }
            if (r12 == 0) goto L_0x0028
            com.android.messaging.datamodel.data.MessagePartData r0 = com.android.messaging.datamodel.data.MessagePartData.createFromCursor(r11)     // Catch:{ all -> 0x002c }
        L_0x0028:
            r11.close()
            return r0
        L_0x002c:
            r12 = move-exception
            r0 = r11
            goto L_0x0030
        L_0x002f:
            r12 = move-exception
        L_0x0030:
            if (r0 == 0) goto L_0x0035
            r0.close()
        L_0x0035:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0887c.readMessagePartData(com.android.messaging.datamodel.p, java.lang.String):com.android.messaging.datamodel.data.MessagePartData");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1658b(com.android.messaging.datamodel.C0955p r11, java.lang.String r12) {
        /*
            com.android.messaging.util.C1424b.m3584Gj()
            r0 = 0
            java.lang.String r2 = "conversations"
            java.lang.String r1 = "current_self_id"
            java.lang.String[] r3 = new java.lang.String[]{r1}     // Catch:{ all -> 0x0038 }
            java.lang.String r4 = "_id=?"
            r9 = 1
            java.lang.String[] r5 = new java.lang.String[r9]     // Catch:{ all -> 0x0038 }
            r10 = 0
            r5[r10] = r12     // Catch:{ all -> 0x0038 }
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r11
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0038 }
            int r12 = r11.getCount()     // Catch:{ all -> 0x0035 }
            com.android.messaging.util.C1424b.m3588b(r12, r10, r9)     // Catch:{ all -> 0x0035 }
            boolean r12 = r11.moveToFirst()     // Catch:{ all -> 0x0035 }
            if (r12 == 0) goto L_0x0031
            java.lang.String r12 = r11.getString(r10)     // Catch:{ all -> 0x0035 }
            r11.close()
            return r12
        L_0x0031:
            r11.close()
            return r0
        L_0x0035:
            r12 = move-exception
            r0 = r11
            goto L_0x0039
        L_0x0038:
            r12 = move-exception
        L_0x0039:
            if (r0 == 0) goto L_0x003e
            r0.close()
        L_0x003e:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0887c.m1658b(com.android.messaging.datamodel.p, java.lang.String):java.lang.String");
    }

    /* renamed from: d */
    public static ArrayList m1665d(C0955p pVar, String str) {
        C1424b.m3584Gj();
        ArrayList participantsForConversation = getParticipantsForConversation(pVar, str);
        ArrayList arrayList = new ArrayList();
        Iterator it = participantsForConversation.iterator();
        while (it.hasNext()) {
            arrayList.add(((ParticipantData) it.next()).mo6351rh());
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String m1635a(C0955p pVar, long j, boolean z, int i) {
        C1424b.m3584Gj();
        return m1637a(pVar, j, z, m1641a(C1029y.m2400A(j), i));
    }

    /* renamed from: c */
    public static void m1664c(C0955p pVar, String str, String str2) {
        C1424b.m3584Gj();
        C1424b.m3592ia(pVar.getDatabase().inTransaction());
        ContentValues contentValues = new ContentValues();
        if (m1654a(pVar, str2, contentValues)) {
            m1661b(pVar, str, contentValues);
        }
    }

    /* renamed from: g */
    public static boolean m1670g(C0955p pVar, String str) {
        C1424b.m3584Gj();
        return m1655a(pVar, str, "normalized_destination");
    }

    /* renamed from: a */
    public static String m1636a(C0955p pVar, long j, boolean z, ParticipantData participantData) {
        C1424b.m3584Gj();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(participantData);
        return m1637a(pVar, j, z, arrayList);
    }

    /* renamed from: b */
    public static void m1659b(C0955p pVar, MessageData messageData) {
        C1424b.m3584Gj();
        C1424b.m3592ia(pVar.getDatabase().inTransaction());
        if (m1671h(pVar, messageData.getMessageId()) != null) {
            C1424b.m3588b(pVar.delete("parts", "message_id =?", new String[]{messageData.getMessageId()}), 0, Integer.MAX_VALUE);
            for (MessagePartData messagePartData : messageData.getParts()) {
                messagePartData.mo6312ha((String) null);
                messagePartData.mo6301ea(messageData.getMessageId());
                m1638a(pVar, messagePartData, messageData.mo6250Ue());
            }
            ContentValues contentValues = new ContentValues();
            messageData.mo6256a(contentValues);
            m1666d(pVar, messageData.getMessageId(), contentValues);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.android.messaging.datamodel.data.ParticipantData} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033  */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.messaging.datamodel.data.ParticipantData m1662c(com.android.messaging.datamodel.C0955p r11, java.lang.String r12) {
        /*
            com.android.messaging.util.C1424b.m3584Gj()
            r0 = 0
            java.lang.String r2 = "participants"
            java.lang.String[] r3 = com.android.messaging.datamodel.data.C0901M.f1157Wu     // Catch:{ all -> 0x0030 }
            java.lang.String r4 = "_id =?"
            r9 = 1
            java.lang.String[] r5 = new java.lang.String[r9]     // Catch:{ all -> 0x0030 }
            r10 = 0
            r5[r10] = r12     // Catch:{ all -> 0x0030 }
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r11
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0030 }
            int r12 = r11.getCount()     // Catch:{ all -> 0x002d }
            com.android.messaging.util.C1424b.m3588b(r12, r10, r9)     // Catch:{ all -> 0x002d }
            boolean r12 = r11.moveToFirst()     // Catch:{ all -> 0x002d }
            if (r12 == 0) goto L_0x0029
            com.android.messaging.datamodel.data.ParticipantData r0 = com.android.messaging.datamodel.data.ParticipantData.m1839k(r11)     // Catch:{ all -> 0x002d }
        L_0x0029:
            r11.close()
            return r0
        L_0x002d:
            r12 = move-exception
            r0 = r11
            goto L_0x0031
        L_0x0030:
            r12 = move-exception
        L_0x0031:
            if (r0 == 0) goto L_0x0036
            r0.close()
        L_0x0036:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0887c.m1662c(com.android.messaging.datamodel.p, java.lang.String):com.android.messaging.datamodel.data.ParticipantData");
    }

    /* renamed from: a */
    public static String m1637a(C0955p pVar, long j, boolean z, ArrayList arrayList) {
        C1424b.m3584Gj();
        String existingConversation = getExistingConversation(pVar, j, false);
        if (existingConversation == null) {
            C0934q.m1991i(arrayList);
            ParticipantData sa = ParticipantData.m1841sa(-1);
            pVar.beginTransaction();
            try {
                existingConversation = m1634a(pVar, j, m1639a(pVar, sa), (List) arrayList, z);
                pVar.setTransactionSuccessful();
            } finally {
                pVar.endTransaction();
            }
        }
        return existingConversation;
    }

    /* renamed from: h */
    public static MessageData m1671h(C0955p pVar, String str) {
        C1424b.m3584Gj();
        MessageData i = m1673i(pVar, str);
        if (i != null) {
            m1644a(pVar, i, false);
        }
        return i;
    }

    /* renamed from: a */
    static boolean m1655a(C0955p pVar, String str, String str2) {
        Cursor cursor = null;
        try {
            boolean z = false;
            Cursor query = pVar.query("participants", new String[]{"blocked"}, str2 + "=? AND " + "sub_id" + "=?", new String[]{str, Integer.toString(-2)}, (String) null, (String) null, (String) null);
            C1424b.m3588b(query.getCount(), 0, 1);
            if (query.moveToFirst()) {
                if (query.getInt(0) == 1) {
                    z = true;
                }
                query.close();
                return z;
            }
            query.close();
            return false;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x006f  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.messaging.datamodel.data.MessageData m1657b(com.android.messaging.datamodel.C0955p r11, java.lang.String r12, java.lang.String r13) {
        /*
            com.android.messaging.util.C1424b.m3584Gj()
            r11.beginTransaction()
            r0 = 0
            java.lang.String r2 = "messages"
            java.lang.String[] r3 = com.android.messaging.datamodel.data.MessageData.getProjection()     // Catch:{ all -> 0x0068 }
            java.lang.String r4 = "message_status=? AND conversation_id=?"
            r1 = 2
            java.lang.String[] r5 = new java.lang.String[r1]     // Catch:{ all -> 0x0068 }
            r1 = 3
            java.lang.String r1 = java.lang.Integer.toString(r1)     // Catch:{ all -> 0x0068 }
            r9 = 0
            r5[r9] = r1     // Catch:{ all -> 0x0068 }
            r10 = 1
            r5[r10] = r12     // Catch:{ all -> 0x0068 }
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r11
            android.database.Cursor r12 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0068 }
            int r1 = r12.getCount()     // Catch:{ all -> 0x0066 }
            com.android.messaging.util.C1424b.m3588b(r1, r9, r10)     // Catch:{ all -> 0x0066 }
            boolean r1 = r12.moveToFirst()     // Catch:{ all -> 0x0066 }
            if (r1 == 0) goto L_0x005c
            com.android.messaging.datamodel.data.MessageData r1 = new com.android.messaging.datamodel.data.MessageData     // Catch:{ all -> 0x0066 }
            r1.<init>()     // Catch:{ all -> 0x0066 }
            r1.mo6258b(r12, r13)     // Catch:{ all -> 0x0066 }
            m1644a((com.android.messaging.datamodel.C0955p) r11, (com.android.messaging.datamodel.data.MessageData) r1, (boolean) r10)     // Catch:{ all -> 0x0066 }
            java.lang.Iterable r13 = r1.getParts()     // Catch:{ all -> 0x0066 }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ all -> 0x0066 }
        L_0x0045:
            boolean r2 = r13.hasNext()     // Catch:{ all -> 0x0066 }
            if (r2 == 0) goto L_0x0058
            java.lang.Object r2 = r13.next()     // Catch:{ all -> 0x0066 }
            com.android.messaging.datamodel.data.MessagePartData r2 = (com.android.messaging.datamodel.data.MessagePartData) r2     // Catch:{ all -> 0x0066 }
            r2.mo6312ha(r0)     // Catch:{ all -> 0x0066 }
            r2.mo6301ea(r0)     // Catch:{ all -> 0x0066 }
            goto L_0x0045
        L_0x0058:
            r1.mo6265ea(r0)     // Catch:{ all -> 0x0066 }
            r0 = r1
        L_0x005c:
            r11.setTransactionSuccessful()     // Catch:{ all -> 0x0066 }
            r11.endTransaction()
            r12.close()
            return r0
        L_0x0066:
            r13 = move-exception
            goto L_0x006a
        L_0x0068:
            r13 = move-exception
            r12 = r0
        L_0x006a:
            r11.endTransaction()
            if (r12 == 0) goto L_0x0072
            r12.close()
        L_0x0072:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0887c.m1657b(com.android.messaging.datamodel.p, java.lang.String, java.lang.String):com.android.messaging.datamodel.data.MessageData");
    }

    /* renamed from: a */
    static String m1634a(C0955p pVar, long j, String str, List list, boolean z) {
        C1424b.m3592ia(pVar.getDatabase().inTransaction());
        Iterator it = list.iterator();
        boolean z2 = false;
        int i = 0;
        while (it.hasNext()) {
            ParticipantData participantData = (ParticipantData) it.next();
            C1424b.m3592ia(!participantData.mo6362zh());
            if (participantData.isEmail()) {
                i = 1;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("sms_thread_id", Long.valueOf(j));
        contentValues.put("sort_timestamp", 0L);
        contentValues.put("current_self_id", str);
        contentValues.put("participant_count", Integer.valueOf(list.size()));
        contentValues.put("include_email_addr", Integer.valueOf(i));
        if (z) {
            contentValues.put("archive_status", 1);
        }
        m1642a(contentValues, list);
        long insert = pVar.insert("conversations", (String) null, contentValues);
        int i2 = (insert > -1 ? 1 : (insert == -1 ? 0 : -1));
        if (i2 != 0) {
            z2 = true;
        }
        C1424b.m3592ia(z2);
        if (i2 == 0) {
            C1430e.m3622e("MessagingAppDb", "BugleDatabaseOperations : failed to insert conversation into table");
            return null;
        }
        String l = Long.toString(insert);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            String a = m1639a(pVar, (ParticipantData) it2.next());
            C1424b.m3594t(a);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("conversation_id", l);
            contentValues2.put("participant_id", a);
            pVar.insert("conversation_participants", (String) null, contentValues2);
        }
        m1649a(pVar, l, (List) getParticipantsForConversation(pVar, l));
        return l;
    }

    /* renamed from: b */
    public static void m1660b(C0955p pVar, String str, boolean z) {
        C1424b.m3584Gj();
        ContentValues contentValues = new ContentValues();
        contentValues.put("blocked", Integer.valueOf(z ? 1 : 0));
        pVar.update("participants", contentValues, "normalized_destination=? AND sub_id=?", new String[]{str, Integer.toString(-2)});
    }

    /* renamed from: a */
    private static void m1642a(ContentValues contentValues, List list) {
        String str;
        long j;
        if (list != null && !list.isEmpty()) {
            contentValues.put("icon", C1426c.m3602m(list).toString());
            String str2 = null;
            if (list.size() == 1) {
                ParticipantData participantData = (ParticipantData) list.get(0);
                j = participantData.getContactId();
                str2 = participantData.mo6342m();
                str = participantData.mo6353sf();
            } else {
                j = 0;
                str = null;
            }
            contentValues.put("participant_contact_id", Long.valueOf(j));
            contentValues.put("participant_lookup_key", str2);
            contentValues.put("participant_normalized_destination", str);
        }
    }

    /* renamed from: a */
    public static void m1646a(C0955p pVar, String str, String str2, long j, boolean z, String str3, boolean z2) {
        String str4;
        String str5;
        int subId;
        C1424b.m3584Gj();
        C1424b.m3592ia(pVar.getDatabase().inTransaction());
        ContentValues contentValues = new ContentValues();
        contentValues.put("latest_message_id", str2);
        contentValues.put("sort_timestamp", Long.valueOf(j));
        if (!TextUtils.isEmpty(str3)) {
            contentValues.put("sms_service_center", str3);
        }
        if (!z) {
            contentValues.put("archive_status", 0);
        }
        MessageData h = m1671h(pVar, str2);
        contentValues.put("show_draft", 0);
        contentValues.put("snippet_text", h.mo6274hf());
        contentValues.put("subject_text", h.mo6279mg());
        Iterator it = h.getParts().iterator();
        while (true) {
            str4 = null;
            if (!it.hasNext()) {
                str5 = null;
                break;
            }
            MessagePartData messagePartData = (MessagePartData) it.next();
            if (messagePartData.mo6300dh() && C1481w.m3826Aa(messagePartData.getContentType())) {
                str4 = messagePartData.getContentUri().toString();
                str5 = messagePartData.getContentType();
                break;
            }
        }
        contentValues.put("preview_content_type", str5);
        contentValues.put("preview_uri", str4);
        if (z2 && C1464na.m3759Zj() && h.mo6272gg()) {
            String b = m1658b(pVar, str);
            String kf = h.mo6275kf();
            if (!(b == null || kf == null)) {
                ParticipantData c = m1662c(pVar, b);
                ParticipantData c2 = m1662c(pVar, kf);
                if (!(!c2.mo6358wh() || (subId = c2.getSubId()) == -1 || C1474sa.getDefault().mo8201Na(c.getSubId()) == subId)) {
                    m1654a(pVar, c2.getId(), contentValues);
                }
            }
        }
        C1424b.m3584Gj();
        C1424b.m3592ia(m1661b(pVar, str, contentValues));
        if (z2 && C1464na.m3759Zj()) {
            C1040Ea.get().mo6961a(pVar.getContext(), str, m1658b(pVar, str));
        }
    }

    /* renamed from: a */
    public static void m1647a(C0955p pVar, String str, String str2, long j, boolean z, boolean z2) {
        C1424b.m3584Gj();
        m1646a(pVar, str, str2, j, z, (String) null, z2);
    }

    /* renamed from: a */
    public static void m1650a(C0955p pVar, String str, boolean z) {
        C1424b.m3584Gj();
        C1424b.m3592ia(pVar.getDatabase().inTransaction());
        ContentValues contentValues = new ContentValues();
        contentValues.put("archive_status", Integer.valueOf(z ? 1 : 0));
        m1661b(pVar, str, contentValues);
    }

    /* renamed from: a */
    static boolean m1654a(C0955p pVar, String str, ContentValues contentValues) {
        Cursor cursor = null;
        try {
            C0955p pVar2 = pVar;
            cursor = pVar2.query("participants", new String[]{"_id"}, "_id=? AND sim_slot_id<>?", new String[]{str, String.valueOf(-1)}, (String) null, (String) null, (String) null);
            if (cursor == null || cursor.getCount() <= 0) {
                return false;
            }
            contentValues.put("current_self_id", str);
            cursor.close();
            return true;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d0  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m1645a(com.android.messaging.datamodel.C0955p r13, java.lang.String r14, com.android.messaging.datamodel.data.MessageData r15) {
        /*
            android.database.sqlite.SQLiteDatabase r0 = r13.getDatabase()
            boolean r0 = r0.inTransaction()
            com.android.messaging.util.C1424b.m3592ia(r0)
            r0 = 0
            java.lang.String r2 = "messages"
            java.lang.String[] r3 = f1133ux     // Catch:{ all -> 0x00cd }
            java.lang.String r4 = "conversation_id=?"
            r10 = 1
            java.lang.String[] r5 = new java.lang.String[r10]     // Catch:{ all -> 0x00cd }
            r11 = 0
            r5[r11] = r14     // Catch:{ all -> 0x00cd }
            r6 = 0
            r7 = 0
            java.lang.String r8 = "received_timestamp DESC"
            java.lang.String r9 = "1"
            r1 = r13
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00cd }
            boolean r2 = r1.moveToFirst()     // Catch:{ all -> 0x00ca }
            if (r2 == 0) goto L_0x002e
            long r2 = r1.getLong(r10)     // Catch:{ all -> 0x00ca }
            goto L_0x0030
        L_0x002e:
            r2 = 0
        L_0x0030:
            r1.close()
            android.content.ContentValues r1 = new android.content.ContentValues
            r1.<init>()
            java.lang.String r4 = "draft_preview_uri"
            java.lang.String r5 = "draft_preview_content_type"
            java.lang.String r6 = "draft_subject_text"
            java.lang.String r7 = "draft_snippet_text"
            java.lang.String r8 = "show_draft"
            if (r15 == 0) goto L_0x00a1
            boolean r9 = r15.hasContent()
            if (r9 != 0) goto L_0x004b
            goto L_0x00a1
        L_0x004b:
            long r11 = r15.mo6288rg()
            long r2 = java.lang.Math.max(r2, r11)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r10)
            r1.put(r8, r9)
            java.lang.String r8 = r15.mo6274hf()
            r1.put(r7, r8)
            java.lang.String r7 = r15.mo6279mg()
            r1.put(r6, r7)
            java.lang.Iterable r15 = r15.getParts()
            java.util.Iterator r15 = r15.iterator()
        L_0x0070:
            boolean r6 = r15.hasNext()
            if (r6 == 0) goto L_0x0099
            java.lang.Object r6 = r15.next()
            com.android.messaging.datamodel.data.MessagePartData r6 = (com.android.messaging.datamodel.data.MessagePartData) r6
            boolean r7 = r6.mo6300dh()
            if (r7 == 0) goto L_0x0070
            java.lang.String r7 = r6.getContentType()
            boolean r7 = com.android.messaging.util.C1481w.m3826Aa(r7)
            if (r7 == 0) goto L_0x0070
            android.net.Uri r15 = r6.getContentUri()
            java.lang.String r0 = r15.toString()
            java.lang.String r15 = r6.getContentType()
            goto L_0x009a
        L_0x0099:
            r15 = r0
        L_0x009a:
            r1.put(r5, r15)
            r1.put(r4, r0)
            goto L_0x00b6
        L_0x00a1:
            java.lang.Integer r15 = java.lang.Integer.valueOf(r11)
            r1.put(r8, r15)
            java.lang.String r15 = ""
            r1.put(r7, r15)
            r1.put(r6, r15)
            r1.put(r5, r15)
            r1.put(r4, r15)
        L_0x00b6:
            java.lang.Long r15 = java.lang.Long.valueOf(r2)
            java.lang.String r0 = "sort_timestamp"
            r1.put(r0, r15)
            com.android.messaging.util.C1424b.m3584Gj()
            boolean r13 = m1661b((com.android.messaging.datamodel.C0955p) r13, (java.lang.String) r14, (android.content.ContentValues) r1)
            com.android.messaging.util.C1424b.m3592ia(r13)
            return
        L_0x00ca:
            r13 = move-exception
            r0 = r1
            goto L_0x00ce
        L_0x00cd:
            r13 = move-exception
        L_0x00ce:
            if (r0 == 0) goto L_0x00d3
            r0.close()
        L_0x00d3:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0887c.m1645a(com.android.messaging.datamodel.p, java.lang.String, com.android.messaging.datamodel.data.MessageData):void");
    }

    /* renamed from: a */
    private static void m1649a(C0955p pVar, String str, List list) {
        boolean z;
        C1424b.m3592ia(pVar.getDatabase().inTransaction());
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", C0934q.m1991i(list));
        Iterator it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                if (ContactUtil.isEnterpriseContactId(((ParticipantData) it.next()).getContactId())) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        contentValues.put("IS_ENTERPRISE", Boolean.valueOf(z));
        m1642a(contentValues, list);
        m1661b(pVar, str, contentValues);
        WidgetConversationProvider.m3888b(C0967f.get().getApplicationContext(), str);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.android.messaging.datamodel.data.MessageData} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003d  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.messaging.datamodel.data.MessageData m1632a(com.android.messaging.datamodel.C0955p r11, android.net.Uri r12) {
        /*
            com.android.messaging.util.C1424b.m3584Gj()
            r0 = 0
            java.lang.String r2 = "messages"
            java.lang.String[] r3 = com.android.messaging.datamodel.data.MessageData.getProjection()     // Catch:{ all -> 0x003a }
            java.lang.String r4 = "sms_message_uri=?"
            r9 = 1
            java.lang.String[] r5 = new java.lang.String[r9]     // Catch:{ all -> 0x003a }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x003a }
            r10 = 0
            r5[r10] = r12     // Catch:{ all -> 0x003a }
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r11
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x003a }
            int r12 = r11.getCount()     // Catch:{ all -> 0x0037 }
            com.android.messaging.util.C1424b.m3588b(r12, r10, r9)     // Catch:{ all -> 0x0037 }
            boolean r12 = r11.moveToFirst()     // Catch:{ all -> 0x0037 }
            if (r12 == 0) goto L_0x0033
            com.android.messaging.datamodel.data.MessageData r0 = new com.android.messaging.datamodel.data.MessageData     // Catch:{ all -> 0x0037 }
            r0.<init>()     // Catch:{ all -> 0x0037 }
            r0.mo6260c(r11)     // Catch:{ all -> 0x0037 }
        L_0x0033:
            r11.close()
            return r0
        L_0x0037:
            r12 = move-exception
            r0 = r11
            goto L_0x003b
        L_0x003a:
            r12 = move-exception
        L_0x003b:
            if (r0 == 0) goto L_0x0040
            r0.close()
        L_0x0040:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0887c.m1632a(com.android.messaging.datamodel.p, android.net.Uri):com.android.messaging.datamodel.data.MessageData");
    }

    /* renamed from: a */
    private static void m1644a(C0955p pVar, MessageData messageData, boolean z) {
        MessagePartData createFromCursor;
        ContentResolver Pk = C0632a.m1012Pk();
        Cursor cursor = null;
        try {
            cursor = pVar.query("parts", MessagePartData.getProjection(), "message_id=?", new String[]{messageData.getMessageId()}, (String) null, (String) null, (String) null);
            while (cursor.moveToNext()) {
                createFromCursor = MessagePartData.createFromCursor(cursor);
                if (!z || !createFromCursor.mo6300dh() || C1488za.m3875x(createFromCursor.getContentUri())) {
                    messageData.mo6267g(createFromCursor);
                } else {
                    ParcelFileDescriptor openFileDescriptor = Pk.openFileDescriptor(createFromCursor.getContentUri(), "r");
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                        messageData.mo6267g(createFromCursor);
                    }
                }
            }
            cursor.close();
        } catch (IOException unused) {
        } catch (SecurityException unused2) {
            if (Log.isLoggable("MessagingApp", 3)) {
                C1430e.m3620d("MessagingApp", "uri: " + createFromCursor.getContentUri());
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    static String m1638a(C0955p pVar, MessagePartData messagePartData, String str) {
        C1424b.m3592ia(pVar.getDatabase().inTransaction());
        C1424b.m3592ia(!TextUtils.isEmpty(messagePartData.getMessageId()));
        long executeInsert = messagePartData.mo6319k(pVar, str).executeInsert();
        C1424b.m3585a(executeInsert, 0, Long.MAX_VALUE);
        String l = Long.toString(executeInsert);
        messagePartData.mo6312ha(l);
        return l;
    }

    /* renamed from: a */
    public static void m1643a(C0955p pVar, MessageData messageData) {
        C1424b.m3584Gj();
        C1424b.m3592ia(pVar.getDatabase().inTransaction());
        long executeInsert = messageData.mo6262d(pVar).executeInsert();
        C1424b.m3585a(executeInsert, 0, Long.MAX_VALUE);
        String l = Long.toString(executeInsert);
        messageData.mo6265ea(l);
        for (MessagePartData messagePartData : messageData.getParts()) {
            messagePartData.mo6301ea(l);
            m1638a(pVar, messagePartData, messageData.mo6250Ue());
        }
    }

    /* renamed from: a */
    public static boolean m1653a(C0955p pVar, String str) {
        C1424b.m3584Gj();
        C1424b.m3592ia(pVar.getDatabase().inTransaction());
        Cursor cursor = null;
        try {
            Cursor query = pVar.query("messages", f1133ux, "conversation_id=? AND message_status!=3", new String[]{str}, (String) null, (String) null, "received_timestamp DESC", "1");
            if (query.getCount() == 0) {
                pVar.delete("conversations", "_id=?", new String[]{str});
                C1430e.m3625i("MessagingAppDb", "BugleDatabaseOperations: Deleted empty conversation " + str);
                query.close();
                return true;
            }
            query.close();
            return false;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static void m1651a(C0955p pVar, String str, boolean z, boolean z2) {
        boolean z3;
        C1424b.m3584Gj();
        C1424b.m3592ia(pVar.getDatabase().inTransaction());
        Cursor cursor = null;
        try {
            Cursor query = pVar.query("messages", f1133ux, "conversation_id=? AND message_status!=3", new String[]{str}, (String) null, (String) null, "received_timestamp DESC", "1");
            if (query.moveToFirst()) {
                String string = query.getString(0);
                long j = query.getLong(1);
                if (!m1655a(pVar, query.getString(2), "_id")) {
                    if (!z2) {
                        z3 = false;
                        m1647a(pVar, str, string, j, z3, z);
                    }
                }
                z3 = true;
                m1647a(pVar, str, string, j, z3, z);
            }
            query.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static void m1648a(C0955p pVar, String str, String str2, boolean z, boolean z2) {
        C1424b.m3584Gj();
        boolean z3 = true;
        if (!TextUtils.isEmpty(str2)) {
            Cursor cursor = null;
            try {
                Cursor query = pVar.query("conversations", new String[]{"latest_message_id"}, "_id=?", new String[]{str}, (String) null, (String) null, (String) null);
                C1424b.m3588b(query.getCount(), 0, 1);
                z3 = query.moveToFirst() ? TextUtils.equals(query.getString(0), str2) : false;
                query.close();
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        if (z3) {
            m1651a(pVar, str, z, z2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x011e A[SYNTHETIC, Splitter:B:55:0x011e] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x012c  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1640a(com.android.messaging.datamodel.C0955p r18, java.lang.String r19, com.android.messaging.datamodel.data.MessageData r20, int r21) {
        /*
            r9 = r18
            r0 = r19
            r10 = r20
            r11 = r21
            com.android.messaging.util.C1424b.m3584Gj()
            com.android.messaging.util.C1424b.m3594t(r19)
            r12 = 2
            r13 = 1
            com.android.messaging.util.C1424b.m3588b(r11, r13, r12)
            r18.beginTransaction()
            a.b.n r15 = new a.b.n     // Catch:{ all -> 0x0124 }
            r15.<init>()     // Catch:{ all -> 0x0124 }
            java.lang.String r2 = "draft_parts_view"
            java.lang.String[] r3 = com.android.messaging.datamodel.data.MessagePartData.getProjection()     // Catch:{ all -> 0x0124 }
            java.lang.String r4 = "conversation_id =?"
            java.lang.String[] r5 = new java.lang.String[r13]     // Catch:{ all -> 0x0124 }
            r8 = 0
            r5[r8] = r0     // Catch:{ all -> 0x0124 }
            r6 = 0
            r7 = 0
            r16 = 0
            r1 = r18
            r14 = r8
            r8 = r16
            android.database.Cursor r16 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0124 }
        L_0x0035:
            boolean r1 = r16.moveToNext()     // Catch:{ all -> 0x0122 }
            if (r1 == 0) goto L_0x004d
            com.android.messaging.datamodel.data.MessagePartData r1 = com.android.messaging.datamodel.data.MessagePartData.createFromCursor(r16)     // Catch:{ all -> 0x0122 }
            boolean r2 = r1.mo6300dh()     // Catch:{ all -> 0x0122 }
            if (r2 == 0) goto L_0x0035
            android.net.Uri r2 = r1.getContentUri()     // Catch:{ all -> 0x0122 }
            r15.put(r2, r1)     // Catch:{ all -> 0x0122 }
            goto L_0x0035
        L_0x004d:
            java.lang.String r2 = "conversations"
            java.lang.String[] r3 = new java.lang.String[r14]     // Catch:{ all -> 0x0119 }
            java.lang.String r4 = "_id=?"
            java.lang.String[] r5 = new java.lang.String[r13]     // Catch:{ all -> 0x0119 }
            r5[r14] = r0     // Catch:{ all -> 0x0119 }
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r18
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0119 }
            int r2 = r1.getCount()     // Catch:{ all -> 0x0115 }
            if (r2 != r13) goto L_0x0068
            r2 = r13
            goto L_0x0069
        L_0x0068:
            r2 = r14
        L_0x0069:
            r1.close()     // Catch:{ all -> 0x0122 }
            if (r10 == 0) goto L_0x0092
            if (r2 == 0) goto L_0x0092
            java.lang.Iterable r1 = r20.getParts()     // Catch:{ all -> 0x0122 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0122 }
        L_0x0078:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0122 }
            if (r3 == 0) goto L_0x0092
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0122 }
            com.android.messaging.datamodel.data.MessagePartData r3 = (com.android.messaging.datamodel.data.MessagePartData) r3     // Catch:{ all -> 0x0122 }
            boolean r4 = r3.mo6300dh()     // Catch:{ all -> 0x0122 }
            if (r4 == 0) goto L_0x0078
            android.net.Uri r3 = r3.getContentUri()     // Catch:{ all -> 0x0122 }
            r15.remove(r3)     // Catch:{ all -> 0x0122 }
            goto L_0x0078
        L_0x0092:
            r1 = r14
        L_0x0093:
            int r3 = r15.size()     // Catch:{ all -> 0x0122 }
            if (r1 >= r3) goto L_0x00a5
            java.lang.Object r3 = r15.valueAt(r1)     // Catch:{ all -> 0x0122 }
            com.android.messaging.datamodel.data.MessagePartData r3 = (com.android.messaging.datamodel.data.MessagePartData) r3     // Catch:{ all -> 0x0122 }
            r3.mo6295ah()     // Catch:{ all -> 0x0122 }
            int r1 = r1 + 1
            goto L_0x0093
        L_0x00a5:
            java.lang.String r1 = "messages"
            java.lang.String r3 = "message_status=? AND conversation_id=?"
            java.lang.String[] r4 = new java.lang.String[r12]     // Catch:{ all -> 0x0122 }
            r5 = 3
            java.lang.String r6 = java.lang.Integer.toString(r5)     // Catch:{ all -> 0x0122 }
            r4[r14] = r6     // Catch:{ all -> 0x0122 }
            r4[r13] = r0     // Catch:{ all -> 0x0122 }
            r9.delete(r1, r3, r4)     // Catch:{ all -> 0x0122 }
            if (r11 != r12) goto L_0x00d2
            if (r10 == 0) goto L_0x00d2
            boolean r1 = r20.hasContent()     // Catch:{ all -> 0x0122 }
            if (r1 == 0) goto L_0x00d2
            if (r2 == 0) goto L_0x00d2
            int r1 = r20.getStatus()     // Catch:{ all -> 0x0122 }
            com.android.messaging.util.C1424b.equals((int) r5, (int) r1)     // Catch:{ all -> 0x0122 }
            m1643a((com.android.messaging.datamodel.C0955p) r9, (com.android.messaging.datamodel.data.MessageData) r10)     // Catch:{ all -> 0x0122 }
            java.lang.String r14 = r20.getMessageId()     // Catch:{ all -> 0x0122 }
            goto L_0x00d3
        L_0x00d2:
            r14 = 0
        L_0x00d3:
            if (r2 == 0) goto L_0x00e7
            m1645a((com.android.messaging.datamodel.C0955p) r18, (java.lang.String) r19, (com.android.messaging.datamodel.data.MessageData) r20)     // Catch:{ all -> 0x0122 }
            if (r10 == 0) goto L_0x00e7
            java.lang.String r1 = r20.mo6275kf()     // Catch:{ all -> 0x0122 }
            if (r1 == 0) goto L_0x00e7
            java.lang.String r1 = r20.mo6275kf()     // Catch:{ all -> 0x0122 }
            m1664c((com.android.messaging.datamodel.C0955p) r9, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x0122 }
        L_0x00e7:
            r18.setTransactionSuccessful()     // Catch:{ all -> 0x0122 }
            r18.endTransaction()
            r16.close()
            java.lang.String r1 = "MessagingAppDb"
            boolean r2 = android.util.Log.isLoggable(r1, r12)
            if (r2 == 0) goto L_0x0114
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Updated draft message "
            r2.append(r3)
            r2.append(r14)
            java.lang.String r3 = " for conversation "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.android.messaging.util.C1430e.m3628v(r1, r0)
        L_0x0114:
            return r14
        L_0x0115:
            r0 = move-exception
            r17 = r1
            goto L_0x011c
        L_0x0119:
            r0 = move-exception
            r17 = 0
        L_0x011c:
            if (r17 == 0) goto L_0x0121
            r17.close()     // Catch:{ all -> 0x0122 }
        L_0x0121:
            throw r0     // Catch:{ all -> 0x0122 }
        L_0x0122:
            r0 = move-exception
            goto L_0x0127
        L_0x0124:
            r0 = move-exception
            r16 = 0
        L_0x0127:
            r18.endTransaction()
            if (r16 == 0) goto L_0x012f
            r16.close()
        L_0x012f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0887c.m1640a(com.android.messaging.datamodel.p, java.lang.String, com.android.messaging.datamodel.data.MessageData, int):java.lang.String");
    }

    /* renamed from: a */
    public static ParticipantData m1633a(C0955p pVar, int i) {
        C1424b.m3584Gj();
        pVar.beginTransaction();
        try {
            ParticipantData c = m1662c(pVar, m1639a(pVar, ParticipantData.m1841sa(i)));
            pVar.setTransactionSuccessful();
            return c;
        } finally {
            pVar.endTransaction();
        }
    }

    /* renamed from: a */
    public static String m1639a(C0955p pVar, ParticipantData participantData) {
        String str;
        int i;
        C1424b.m3584Gj();
        C1424b.m3592ia(pVar.getDatabase().inTransaction());
        if (participantData.mo6362zh()) {
            i = participantData.getSubId();
            str = "SELF(" + i + ")";
        } else {
            str = participantData.mo6353sf();
            i = -2;
        }
        C1424b.m3594t(str);
        String participantId = getParticipantId(pVar, i, str);
        if (participantId != null) {
            return participantId;
        }
        if (!participantData.mo6360xh()) {
            ParticipantRefresh.m1284b(pVar, participantData);
        }
        String l = Long.toString(pVar.insert("participants", (String) null, participantData.toContentValues()));
        C1424b.m3594t(str);
        synchronized (f1132tx) {
            f1132tx.put(str, l);
        }
        return l;
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public static void m1652a(ArrayList arrayList) {
        C1424b.m3584Gj();
        C0955p database = C0947h.get().getDatabase();
        HashSet hashSet = new HashSet();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C0955p pVar = database;
            Cursor query = pVar.query("conversation_participants", C0775P.f1031Wu, "participant_id=?", new String[]{(String) it.next()}, (String) null, (String) null, (String) null);
            if (query != null) {
                while (query.moveToNext()) {
                    try {
                        hashSet.add(query.getString(1));
                    } finally {
                        query.close();
                    }
                }
            }
        }
        if (hashSet.size() > 0) {
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                String str = (String) it2.next();
                C0955p database2 = C0947h.get().getDatabase();
                database2.beginTransaction();
                try {
                    C1424b.m3584Gj();
                    C1424b.m3592ia(database2.getDatabase().inTransaction());
                    m1649a(database2, str, (List) getParticipantsForConversation(database2, str));
                    database2.setTransactionSuccessful();
                    database2.endTransaction();
                    MessagingContentProvider.m1274s(str);
                    MessagingContentProvider.m1273r(str);
                    MessagingContentProvider.m1272q(str);
                } catch (Throwable th) {
                    database2.endTransaction();
                    throw th;
                }
            }
            MessagingContentProvider.m1263Wa();
            if (Log.isLoggable("MessagingAppDb", 2)) {
                StringBuilder Pa = C0632a.m1011Pa("Number of conversations refreshed:");
                Pa.append(hashSet.size());
                C1430e.m3628v("MessagingAppDb", Pa.toString());
            }
        }
    }

    /* renamed from: a */
    public static boolean m1656a(C0955p pVar, String str, String str2, String str3, ContentValues contentValues) {
        C1424b.m3584Gj();
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList(contentValues.size() + 1);
        arrayList.add(str3);
        for (String next : contentValues.keySet()) {
            if (sb.length() > 0) {
                sb.append(" OR ");
            }
            Object obj = contentValues.get(next);
            sb.append(next);
            if (obj != null) {
                sb.append(" IS NOT ?");
                arrayList.add(obj.toString());
            } else {
                sb.append(" IS NOT NULL");
            }
        }
        int update = pVar.update(str, contentValues, str2 + "=? AND (" + sb.toString() + ")", (String[]) arrayList.toArray(new String[arrayList.size()]));
        if (update > 1) {
            C1430e.m3630w("MessagingApp", "Updated more than 1 row " + update + "; " + str + " for " + str2 + " = " + str3 + " (deleted?)");
        }
        C1424b.m3588b(update, 0, 1);
        if (update >= 0) {
            return true;
        }
        return false;
    }
}
