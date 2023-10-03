package com.android.messaging.mmslib.p039a;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.support.p016v4.media.session.C0107q;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.android.messaging.mmslib.MmsException;
import com.android.messaging.mmslib.p040b.C1000e;
import com.android.messaging.sms.C1026v;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import p000a.p005b.C0027n;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.mmslib.a.t */
public class C0990t {

    /* renamed from: BD */
    private static final SparseArray f1429BD = new SparseArray();

    /* renamed from: lD */
    private static C0990t f1430lD;

    /* renamed from: mD */
    private static final C1000e f1431mD = C1000e.getInstance();

    /* renamed from: nD */
    private static final int[] f1432nD = {129, 130, 137, 151};

    /* renamed from: oD */
    public static final String[] f1433oD = {"_id", "msg_box", "thread_id", "retr_txt", "sub", "ct_l", "ct_t", "m_cls", "m_id", "resp_txt", "tr_id", "ct_cls", "d_rpt", "m_type", "v", "pri", "rr", "read_status", "rpt_a", "retr_st", "st", "date", "d_tm", "exp", "m_size", "sub_cs", "retr_txt_cs", "read", "seen"};

    /* renamed from: pD */
    private static final String[] f1434pD = {"_id", "chset", "cd", "cid", "cl", "ct", "fn", "name", "text"};

    /* renamed from: qD */
    private static final C0027n f1435qD = new C0027n();

    /* renamed from: rD */
    private static final SparseIntArray f1436rD = new SparseIntArray();

    /* renamed from: sD */
    private static final SparseIntArray f1437sD = new SparseIntArray();

    /* renamed from: tD */
    private static final SparseIntArray f1438tD = new SparseIntArray();

    /* renamed from: uD */
    private static final SparseIntArray f1439uD = new SparseIntArray();

    /* renamed from: vD */
    private static final SparseIntArray f1440vD = new SparseIntArray();

    /* renamed from: wD */
    private static final SparseArray f1441wD = new SparseArray();

    /* renamed from: xD */
    private static final SparseArray f1442xD = new SparseArray();

    /* renamed from: yD */
    private static final SparseArray f1443yD = new SparseArray();

    /* renamed from: zD */
    private static final SparseArray f1444zD = new SparseArray();
    private final ContentResolver mContentResolver;
    private final Context mContext;

    static {
        f1435qD.put(Telephony.Mms.Inbox.CONTENT_URI, 1);
        f1435qD.put(Telephony.Mms.Sent.CONTENT_URI, 2);
        f1435qD.put(Telephony.Mms.Draft.CONTENT_URI, 3);
        f1435qD.put(Telephony.Mms.Outbox.CONTENT_URI, 4);
        f1436rD.put(150, 25);
        f1436rD.put(154, 26);
        f1441wD.put(150, "sub_cs");
        f1441wD.put(154, "retr_txt_cs");
        f1437sD.put(154, 3);
        f1437sD.put(150, 4);
        f1442xD.put(154, "retr_txt");
        f1442xD.put(150, "sub");
        f1438tD.put(131, 5);
        f1438tD.put(132, 6);
        f1438tD.put(138, 7);
        f1438tD.put(139, 8);
        f1438tD.put(147, 9);
        f1438tD.put(152, 10);
        f1443yD.put(131, "ct_l");
        f1443yD.put(132, "ct_t");
        f1443yD.put(138, "m_cls");
        f1443yD.put(139, "m_id");
        f1443yD.put(147, "resp_txt");
        f1443yD.put(152, "tr_id");
        f1439uD.put(186, 11);
        f1439uD.put(134, 12);
        f1439uD.put(140, 13);
        f1439uD.put(141, 14);
        f1439uD.put(143, 15);
        f1439uD.put(144, 16);
        f1439uD.put(155, 17);
        f1439uD.put(145, 18);
        f1439uD.put(153, 19);
        f1439uD.put(149, 20);
        f1444zD.put(186, "ct_cls");
        f1444zD.put(134, "d_rpt");
        f1444zD.put(140, "m_type");
        f1444zD.put(141, "v");
        f1444zD.put(143, "pri");
        f1444zD.put(144, "rr");
        f1444zD.put(155, "read_status");
        f1444zD.put(145, "rpt_a");
        f1444zD.put(153, "retr_st");
        f1444zD.put(149, "st");
        f1440vD.put(133, 21);
        f1440vD.put(135, 22);
        f1440vD.put(136, 23);
        f1440vD.put(142, 24);
        f1429BD.put(133, "date");
        f1429BD.put(135, "d_tm");
        f1429BD.put(136, "exp");
        f1429BD.put(142, "m_size");
    }

    private C0990t(Context context) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
    }

    /* renamed from: a */
    private void m2284a(long j, C0987q qVar) {
        ContentResolver contentResolver = this.mContentResolver;
        Cursor query = C0107q.query(contentResolver, Uri.parse("content://mms/" + j + "/addr"), new String[]{"address", "charset", "type"}, (String) null, (String[]) null, (String) null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    String string = query.getString(0);
                    if (!TextUtils.isEmpty(string)) {
                        int i = query.getInt(2);
                        if (!(i == 129 || i == 130)) {
                            if (i == 137) {
                                qVar.mo6718b(new C0975e(query.getInt(1), getBytes(string)), i);
                            } else if (i != 151) {
                                Log.e("PduPersister", "Unknown address type: " + i);
                            }
                        }
                        qVar.mo6716a(new C0975e(query.getInt(1), getBytes(string)), i);
                    }
                } finally {
                    query.close();
                }
            }
        }
    }

    /* renamed from: b */
    private byte[] m2288b(Cursor cursor, int i) {
        if (!cursor.isNull(i)) {
            return getBytes(cursor.getString(i));
        }
        return null;
    }

    public static byte[] getBytes(String str) {
        try {
            return str.getBytes("iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            Log.e("PduPersister", "ISO_8859_1 must be supported!", e);
            return new byte[0];
        }
    }

    /* renamed from: o */
    public static C0990t m2289o(Context context) {
        C0990t tVar = f1430lD;
        if (tVar == null || !context.equals(tVar.mContext)) {
            f1430lD = new C0990t(context);
        }
        return f1430lD;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r1 = android.support.p016v4.media.session.C0107q.query(r10.mContentResolver, r11, f1433oD, (java.lang.String) null, (java.lang.String[]) null, (java.lang.String) null);
        r2 = new com.android.messaging.mmslib.p039a.C0987q();
        r4 = android.content.ContentUris.parseId(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0057, code lost:
        if (r1 == null) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x005d, code lost:
        if (r1.getCount() != 1) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0063, code lost:
        if (r1.moveToFirst() != false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0066, code lost:
        r3 = r1.getInt(1);
        m2285a(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0074, code lost:
        if (r4 == -1) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0076, code lost:
        m2284a(r4, r2);
        r1 = r2.mo6713F(140);
        r10 = m2282a(r1, r2, m2286b(r4, r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0087, code lost:
        r1 = f1431mD;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0089, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008a, code lost:
        if (r10 == null) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r2 = f1431mD.get(r11);
        com.android.messaging.util.C1424b.m3587a(r2, "Pdu exists for " + r11);
        f1431mD.mo6765a(r11, new com.android.messaging.mmslib.p040b.C1001f(r10, r3, -1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b0, code lost:
        f1431mD.mo6764a(r11, false);
        f1431mD.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ba, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00bb, code lost:
        return r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00c6, code lost:
        throw new com.android.messaging.mmslib.MmsException("Error! ID of the message: -1.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00cd, code lost:
        if (r1 == null) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00cf, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d2, code lost:
        r1 = f1431mD;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00d4, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        f1431mD.mo6764a(r11, false);
        f1431mD.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00df, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00e0, code lost:
        return null;
     */
    /* renamed from: i */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.messaging.mmslib.p039a.C0976f mo6745i(android.net.Uri r11) {
        /*
            r10 = this;
            r0 = 0
            com.android.messaging.mmslib.b.e r1 = f1431mD     // Catch:{ all -> 0x00e7 }
            monitor-enter(r1)     // Catch:{ all -> 0x00e7 }
            com.android.messaging.mmslib.b.e r2 = f1431mD     // Catch:{ all -> 0x00e4 }
            boolean r2 = r2.mo6766j(r11)     // Catch:{ all -> 0x00e4 }
            if (r2 == 0) goto L_0x001a
            com.android.messaging.mmslib.b.e r2 = f1431mD     // Catch:{ InterruptedException -> 0x0012 }
            r2.wait()     // Catch:{ InterruptedException -> 0x0012 }
            goto L_0x001a
        L_0x0012:
            r2 = move-exception
            java.lang.String r3 = "PduPersister"
            java.lang.String r4 = "load: "
            android.util.Log.e(r3, r4, r2)     // Catch:{ all -> 0x00e4 }
        L_0x001a:
            com.android.messaging.mmslib.b.e r2 = f1431mD     // Catch:{ all -> 0x00e4 }
            java.lang.Object r2 = r2.get(r11)     // Catch:{ all -> 0x00e4 }
            com.android.messaging.mmslib.b.f r2 = (com.android.messaging.mmslib.p040b.C1001f) r2     // Catch:{ all -> 0x00e4 }
            if (r2 == 0) goto L_0x003b
            com.android.messaging.mmslib.a.f r10 = r2.getPdu()     // Catch:{ all -> 0x00e4 }
            monitor-exit(r1)     // Catch:{ all -> 0x00e4 }
            com.android.messaging.mmslib.b.e r2 = f1431mD
            monitor-enter(r2)
            com.android.messaging.mmslib.b.e r1 = f1431mD     // Catch:{ all -> 0x0038 }
            r1.mo6764a((android.net.Uri) r11, (boolean) r0)     // Catch:{ all -> 0x0038 }
            com.android.messaging.mmslib.b.e r11 = f1431mD     // Catch:{ all -> 0x0038 }
            r11.notifyAll()     // Catch:{ all -> 0x0038 }
            monitor-exit(r2)     // Catch:{ all -> 0x0038 }
            return r10
        L_0x0038:
            r10 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0038 }
            throw r10
        L_0x003b:
            com.android.messaging.mmslib.b.e r2 = f1431mD     // Catch:{ all -> 0x00e4 }
            r3 = 1
            r2.mo6764a((android.net.Uri) r11, (boolean) r3)     // Catch:{ all -> 0x00e4 }
            monitor-exit(r1)     // Catch:{ all -> 0x00e4 }
            android.content.ContentResolver r4 = r10.mContentResolver     // Catch:{ all -> 0x00e7 }
            java.lang.String[] r6 = f1433oD     // Catch:{ all -> 0x00e7 }
            r7 = 0
            r8 = 0
            r9 = 0
            r5 = r11
            android.database.Cursor r1 = android.support.p016v4.media.session.C0107q.query(r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00e7 }
            com.android.messaging.mmslib.a.q r2 = new com.android.messaging.mmslib.a.q     // Catch:{ all -> 0x00e7 }
            r2.<init>()     // Catch:{ all -> 0x00e7 }
            long r4 = android.content.ContentUris.parseId(r11)     // Catch:{ all -> 0x00e7 }
            if (r1 == 0) goto L_0x00cc
            int r6 = r1.getCount()     // Catch:{ all -> 0x00c7 }
            if (r6 != r3) goto L_0x00cc
            boolean r6 = r1.moveToFirst()     // Catch:{ all -> 0x00c7 }
            if (r6 != 0) goto L_0x0066
            goto L_0x00cc
        L_0x0066:
            int r3 = r1.getInt(r3)     // Catch:{ all -> 0x00c7 }
            r10.m2285a((android.database.Cursor) r1, (com.android.messaging.mmslib.p039a.C0987q) r2)     // Catch:{ all -> 0x00c7 }
            r1.close()     // Catch:{ all -> 0x00e7 }
            r6 = -1
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 == 0) goto L_0x00bf
            r10.m2284a((long) r4, (com.android.messaging.mmslib.p039a.C0987q) r2)     // Catch:{ all -> 0x00e7 }
            r1 = 140(0x8c, float:1.96E-43)
            int r1 = r2.mo6713F(r1)     // Catch:{ all -> 0x00e7 }
            com.android.messaging.mmslib.a.j r4 = r10.m2286b((long) r4, (int) r1)     // Catch:{ all -> 0x00e7 }
            com.android.messaging.mmslib.a.f r10 = r10.m2282a((int) r1, (com.android.messaging.mmslib.p039a.C0987q) r2, (com.android.messaging.mmslib.p039a.C0980j) r4)     // Catch:{ all -> 0x00e7 }
            com.android.messaging.mmslib.b.e r1 = f1431mD
            monitor-enter(r1)
            if (r10 == 0) goto L_0x00b0
            com.android.messaging.mmslib.b.e r2 = f1431mD     // Catch:{ all -> 0x00bc }
            java.lang.Object r2 = r2.get(r11)     // Catch:{ all -> 0x00bc }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bc }
            r4.<init>()     // Catch:{ all -> 0x00bc }
            java.lang.String r5 = "Pdu exists for "
            r4.append(r5)     // Catch:{ all -> 0x00bc }
            r4.append(r11)     // Catch:{ all -> 0x00bc }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00bc }
            com.android.messaging.util.C1424b.m3587a(r2, r4)     // Catch:{ all -> 0x00bc }
            com.android.messaging.mmslib.b.f r2 = new com.android.messaging.mmslib.b.f     // Catch:{ all -> 0x00bc }
            r2.<init>(r10, r3, r6)     // Catch:{ all -> 0x00bc }
            com.android.messaging.mmslib.b.e r3 = f1431mD     // Catch:{ all -> 0x00bc }
            r3.mo6765a((android.net.Uri) r11, (com.android.messaging.mmslib.p040b.C1001f) r2)     // Catch:{ all -> 0x00bc }
        L_0x00b0:
            com.android.messaging.mmslib.b.e r2 = f1431mD     // Catch:{ all -> 0x00bc }
            r2.mo6764a((android.net.Uri) r11, (boolean) r0)     // Catch:{ all -> 0x00bc }
            com.android.messaging.mmslib.b.e r11 = f1431mD     // Catch:{ all -> 0x00bc }
            r11.notifyAll()     // Catch:{ all -> 0x00bc }
            monitor-exit(r1)     // Catch:{ all -> 0x00bc }
            return r10
        L_0x00bc:
            r10 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00bc }
            throw r10
        L_0x00bf:
            com.android.messaging.mmslib.MmsException r10 = new com.android.messaging.mmslib.MmsException     // Catch:{ all -> 0x00e7 }
            java.lang.String r1 = "Error! ID of the message: -1."
            r10.<init>((java.lang.String) r1)     // Catch:{ all -> 0x00e7 }
            throw r10     // Catch:{ all -> 0x00e7 }
        L_0x00c7:
            r10 = move-exception
            r1.close()     // Catch:{ all -> 0x00e7 }
            throw r10     // Catch:{ all -> 0x00e7 }
        L_0x00cc:
            r10 = 0
            if (r1 == 0) goto L_0x00d2
            r1.close()     // Catch:{ all -> 0x00e7 }
        L_0x00d2:
            com.android.messaging.mmslib.b.e r1 = f1431mD
            monitor-enter(r1)
            com.android.messaging.mmslib.b.e r2 = f1431mD     // Catch:{ all -> 0x00e1 }
            r2.mo6764a((android.net.Uri) r11, (boolean) r0)     // Catch:{ all -> 0x00e1 }
            com.android.messaging.mmslib.b.e r11 = f1431mD     // Catch:{ all -> 0x00e1 }
            r11.notifyAll()     // Catch:{ all -> 0x00e1 }
            monitor-exit(r1)     // Catch:{ all -> 0x00e1 }
            return r10
        L_0x00e1:
            r10 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00e1 }
            throw r10
        L_0x00e4:
            r10 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00e4 }
            throw r10     // Catch:{ all -> 0x00e7 }
        L_0x00e7:
            r10 = move-exception
            com.android.messaging.mmslib.b.e r1 = f1431mD
            monitor-enter(r1)
            com.android.messaging.mmslib.b.e r2 = f1431mD     // Catch:{ all -> 0x00f7 }
            r2.mo6764a((android.net.Uri) r11, (boolean) r0)     // Catch:{ all -> 0x00f7 }
            com.android.messaging.mmslib.b.e r11 = f1431mD     // Catch:{ all -> 0x00f7 }
            r11.notifyAll()     // Catch:{ all -> 0x00f7 }
            monitor-exit(r1)     // Catch:{ all -> 0x00f7 }
            throw r10
        L_0x00f7:
            r10 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00f7 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.mmslib.p039a.C0990t.mo6745i(android.net.Uri):com.android.messaging.mmslib.a.f");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: com.android.messaging.mmslib.a.s[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: com.android.messaging.mmslib.a.s[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: com.android.messaging.mmslib.a.s} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: com.android.messaging.mmslib.a.s[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: com.android.messaging.mmslib.a.s[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: com.android.messaging.mmslib.a.s[]} */
    /* JADX WARNING: type inference failed for: r6v9, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r6v10 */
    /* JADX WARNING: type inference failed for: r6v17 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0134 A[SYNTHETIC, Splitter:B:71:0x0134] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0185  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.android.messaging.mmslib.p039a.C0980j m2286b(long r13, int r15) {
        /*
            r12 = this;
            com.android.messaging.mmslib.a.j r0 = new com.android.messaging.mmslib.a.j
            r0.<init>()
            r1 = 132(0x84, float:1.85E-43)
            if (r15 == r1) goto L_0x000d
            r1 = 128(0x80, float:1.794E-43)
            if (r15 != r1) goto L_0x0190
        L_0x000d:
            java.lang.String r15 = "Failed to close stream"
            java.lang.String r1 = "PduPersister"
            android.content.ContentResolver r2 = r12.mContentResolver
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "content://mms/"
            r3.append(r4)
            r3.append(r13)
            java.lang.String r13 = "/part"
            r3.append(r13)
            java.lang.String r13 = r3.toString()
            android.net.Uri r3 = android.net.Uri.parse(r13)
            java.lang.String[] r4 = f1434pD
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r13 = android.support.p016v4.media.session.C0107q.query(r2, r3, r4, r5, r6, r7)
            r14 = 0
            r2 = 0
            if (r13 == 0) goto L_0x017e
            int r3 = r13.getCount()     // Catch:{ all -> 0x0179 }
            if (r3 != 0) goto L_0x0042
            goto L_0x017e
        L_0x0042:
            int r3 = r13.getCount()     // Catch:{ all -> 0x0179 }
            com.android.messaging.mmslib.a.s[] r3 = new com.android.messaging.mmslib.p039a.C0989s[r3]     // Catch:{ all -> 0x0179 }
            r4 = r2
        L_0x0049:
            boolean r5 = r13.moveToNext()     // Catch:{ all -> 0x0179 }
            if (r5 == 0) goto L_0x0174
            com.android.messaging.mmslib.a.s r5 = new com.android.messaging.mmslib.a.s     // Catch:{ all -> 0x0179 }
            r5.<init>()     // Catch:{ all -> 0x0179 }
            r6 = 1
            boolean r7 = r13.isNull(r6)     // Catch:{ all -> 0x0179 }
            if (r7 != 0) goto L_0x0064
            int r6 = r13.getInt(r6)     // Catch:{ all -> 0x0179 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0179 }
            goto L_0x0065
        L_0x0064:
            r6 = r14
        L_0x0065:
            if (r6 == 0) goto L_0x006e
            int r7 = r6.intValue()     // Catch:{ all -> 0x0179 }
            r5.mo6723H(r7)     // Catch:{ all -> 0x0179 }
        L_0x006e:
            r7 = 2
            byte[] r7 = r12.m2288b((android.database.Cursor) r13, (int) r7)     // Catch:{ all -> 0x0179 }
            if (r7 == 0) goto L_0x0078
            r5.mo6724c(r7)     // Catch:{ all -> 0x0179 }
        L_0x0078:
            r7 = 3
            byte[] r7 = r12.m2288b((android.database.Cursor) r13, (int) r7)     // Catch:{ all -> 0x0179 }
            if (r7 == 0) goto L_0x0082
            r5.mo6725d(r7)     // Catch:{ all -> 0x0179 }
        L_0x0082:
            r7 = 4
            byte[] r7 = r12.m2288b((android.database.Cursor) r13, (int) r7)     // Catch:{ all -> 0x0179 }
            if (r7 == 0) goto L_0x008c
            r5.mo6726e(r7)     // Catch:{ all -> 0x0179 }
        L_0x008c:
            r7 = 5
            byte[] r7 = r12.m2288b((android.database.Cursor) r13, (int) r7)     // Catch:{ all -> 0x0179 }
            if (r7 == 0) goto L_0x016c
            r5.mo6728g(r7)     // Catch:{ all -> 0x0179 }
            r8 = 6
            byte[] r8 = r12.m2288b((android.database.Cursor) r13, (int) r8)     // Catch:{ all -> 0x0179 }
            if (r8 == 0) goto L_0x00a0
            r5.mo6736h((byte[]) r8)     // Catch:{ all -> 0x0179 }
        L_0x00a0:
            r8 = 7
            byte[] r8 = r12.m2288b((android.database.Cursor) r13, (int) r8)     // Catch:{ all -> 0x0179 }
            if (r8 == 0) goto L_0x00aa
            r5.mo6737i(r8)     // Catch:{ all -> 0x0179 }
        L_0x00aa:
            long r8 = r13.getLong(r2)     // Catch:{ all -> 0x0179 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0179 }
            r10.<init>()     // Catch:{ all -> 0x0179 }
            java.lang.String r11 = "content://mms/part/"
            r10.append(r11)     // Catch:{ all -> 0x0179 }
            r10.append(r8)     // Catch:{ all -> 0x0179 }
            java.lang.String r8 = r10.toString()     // Catch:{ all -> 0x0179 }
            android.net.Uri r8 = android.net.Uri.parse(r8)     // Catch:{ all -> 0x0179 }
            r5.mo6735h((android.net.Uri) r8)     // Catch:{ all -> 0x0179 }
            java.lang.String r7 = m2290o((byte[]) r7)     // Catch:{ all -> 0x0179 }
            boolean r9 = com.android.messaging.util.C1481w.isImageType(r7)     // Catch:{ all -> 0x0179 }
            if (r9 != 0) goto L_0x0165
            boolean r9 = com.android.messaging.util.C1481w.m3831za(r7)     // Catch:{ all -> 0x0179 }
            if (r9 != 0) goto L_0x0165
            boolean r9 = com.android.messaging.util.C1481w.m3830Ea(r7)     // Catch:{ all -> 0x0179 }
            if (r9 != 0) goto L_0x0165
            java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0179 }
            r9.<init>()     // Catch:{ all -> 0x0179 }
            java.lang.String r10 = "text/plain"
            boolean r10 = r10.equals(r7)     // Catch:{ all -> 0x0179 }
            if (r10 != 0) goto L_0x013d
            java.lang.String r10 = "application/smil"
            boolean r10 = r10.equals(r7)     // Catch:{ all -> 0x0179 }
            if (r10 != 0) goto L_0x013d
            java.lang.String r10 = "text/html"
            boolean r7 = r10.equals(r7)     // Catch:{ all -> 0x0179 }
            if (r7 == 0) goto L_0x00fa
            goto L_0x013d
        L_0x00fa:
            android.content.ContentResolver r6 = r12.mContentResolver     // Catch:{ IOException -> 0x0123 }
            java.io.InputStream r6 = r6.openInputStream(r8)     // Catch:{ IOException -> 0x0123 }
            r7 = 256(0x100, float:3.59E-43)
            byte[] r7 = new byte[r7]     // Catch:{ IOException -> 0x011d, all -> 0x011b }
            int r8 = r6.read(r7)     // Catch:{ IOException -> 0x011d, all -> 0x011b }
        L_0x0108:
            if (r8 < 0) goto L_0x0112
            r9.write(r7, r2, r8)     // Catch:{ IOException -> 0x011d, all -> 0x011b }
            int r8 = r6.read(r7)     // Catch:{ IOException -> 0x011d, all -> 0x011b }
            goto L_0x0108
        L_0x0112:
            r6.close()     // Catch:{ IOException -> 0x0116 }
            goto L_0x015e
        L_0x0116:
            r6 = move-exception
            android.util.Log.e(r1, r15, r6)     // Catch:{ all -> 0x0179 }
            goto L_0x015e
        L_0x011b:
            r12 = move-exception
            goto L_0x0132
        L_0x011d:
            r12 = move-exception
            r14 = r6
            goto L_0x0124
        L_0x0120:
            r12 = move-exception
            r6 = r14
            goto L_0x0132
        L_0x0123:
            r12 = move-exception
        L_0x0124:
            java.lang.String r0 = "Failed to load part data"
            android.util.Log.e(r1, r0, r12)     // Catch:{ all -> 0x0120 }
            r13.close()     // Catch:{ all -> 0x0120 }
            com.android.messaging.mmslib.MmsException r0 = new com.android.messaging.mmslib.MmsException     // Catch:{ all -> 0x0120 }
            r0.<init>((java.lang.Throwable) r12)     // Catch:{ all -> 0x0120 }
            throw r0     // Catch:{ all -> 0x0120 }
        L_0x0132:
            if (r6 == 0) goto L_0x013c
            r6.close()     // Catch:{ IOException -> 0x0138 }
            goto L_0x013c
        L_0x0138:
            r14 = move-exception
            android.util.Log.e(r1, r15, r14)     // Catch:{ all -> 0x0179 }
        L_0x013c:
            throw r12     // Catch:{ all -> 0x0179 }
        L_0x013d:
            r7 = 8
            java.lang.String r7 = r13.getString(r7)     // Catch:{ all -> 0x0179 }
            com.android.messaging.mmslib.a.e r8 = new com.android.messaging.mmslib.a.e     // Catch:{ all -> 0x0179 }
            if (r6 == 0) goto L_0x014c
            int r6 = r6.intValue()     // Catch:{ all -> 0x0179 }
            goto L_0x014e
        L_0x014c:
            r6 = 106(0x6a, float:1.49E-43)
        L_0x014e:
            if (r7 == 0) goto L_0x0151
            goto L_0x0153
        L_0x0151:
            java.lang.String r7 = ""
        L_0x0153:
            r8.<init>((int) r6, (java.lang.String) r7)     // Catch:{ all -> 0x0179 }
            byte[] r6 = r8.mo6666fc()     // Catch:{ all -> 0x0179 }
            int r7 = r6.length     // Catch:{ all -> 0x0179 }
            r9.write(r6, r2, r7)     // Catch:{ all -> 0x0179 }
        L_0x015e:
            byte[] r6 = r9.toByteArray()     // Catch:{ all -> 0x0179 }
            r5.setData(r6)     // Catch:{ all -> 0x0179 }
        L_0x0165:
            int r6 = r4 + 1
            r3[r4] = r5     // Catch:{ all -> 0x0179 }
            r4 = r6
            goto L_0x0049
        L_0x016c:
            com.android.messaging.mmslib.MmsException r12 = new com.android.messaging.mmslib.MmsException     // Catch:{ all -> 0x0179 }
            java.lang.String r14 = "Content-Type must be set."
            r12.<init>((java.lang.String) r14)     // Catch:{ all -> 0x0179 }
            throw r12     // Catch:{ all -> 0x0179 }
        L_0x0174:
            r13.close()
            r14 = r3
            goto L_0x0183
        L_0x0179:
            r12 = move-exception
            r13.close()
            throw r12
        L_0x017e:
            if (r13 == 0) goto L_0x0183
            r13.close()
        L_0x0183:
            if (r14 == 0) goto L_0x0190
            int r12 = r14.length
        L_0x0186:
            if (r2 >= r12) goto L_0x0190
            r13 = r14[r2]
            r0.mo6689a(r13)
            int r2 = r2 + 1
            goto L_0x0186
        L_0x0190:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.mmslib.p039a.C0990t.m2286b(long, int):com.android.messaging.mmslib.a.j");
    }

    /* renamed from: o */
    public static String m2290o(byte[] bArr) {
        try {
            return new String(bArr, "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            Log.e("PduPersister", "ISO_8859_1 must be supported!", e);
            return "";
        }
    }

    /* renamed from: a */
    private void m2285a(Cursor cursor, C0987q qVar) {
        int size = f1437sD.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            int valueAt = f1437sD.valueAt(size);
            int keyAt = f1437sD.keyAt(size);
            String string = cursor.getString(valueAt);
            if (string != null && string.length() > 0) {
                qVar.mo6718b(new C0975e(cursor.getInt(f1436rD.get(keyAt)), getBytes(string)), keyAt);
            }
        }
        int size2 = f1438tD.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            int valueAt2 = f1438tD.valueAt(size2);
            int keyAt2 = f1438tD.keyAt(size2);
            String string2 = cursor.getString(valueAt2);
            if (string2 != null) {
                qVar.mo6719b(getBytes(string2), keyAt2);
            }
        }
        int size3 = f1439uD.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            int valueAt3 = f1439uD.valueAt(size3);
            int keyAt3 = f1439uD.keyAt(size3);
            if (!cursor.isNull(valueAt3)) {
                qVar.mo6720e(cursor.getInt(valueAt3), keyAt3);
            }
        }
        int size4 = f1440vD.size();
        while (true) {
            size4--;
            if (size4 >= 0) {
                int valueAt4 = f1440vD.valueAt(size4);
                int keyAt4 = f1440vD.keyAt(size4);
                if (!cursor.isNull(valueAt4)) {
                    qVar.mo6715a(cursor.getLong(valueAt4), keyAt4);
                }
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    private C0976f m2282a(int i, C0987q qVar, C0980j jVar) {
        switch (i) {
            case 128:
                return new C0995y(qVar, jVar);
            case 129:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
                StringBuilder Pa = C0632a.m1011Pa("Unsupported PDU type: ");
                Pa.append(Integer.toHexString(i));
                throw new MmsException(Pa.toString());
            case 130:
                return new C0978h(qVar);
            case 131:
                return new C0979i(qVar);
            case 132:
                return new C0993w(qVar, jVar);
            case 133:
                return new C0971a(qVar);
            case 134:
                return new C0974d(qVar);
            case 135:
                return new C0992v(qVar);
            case 136:
                return new C0991u(qVar);
            default:
                StringBuilder Pa2 = C0632a.m1011Pa("Unrecognized PDU type: ");
                Pa2.append(Integer.toHexString(i));
                throw new MmsException(Pa2.toString());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.util.Map} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v25, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v26, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v25, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v26, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v38, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v16, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v41, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v47, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v27, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v28, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v38, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v39, resolved type: java.io.InputStream} */
    /* JADX WARNING: type inference failed for: r2v12 */
    /* JADX WARNING: type inference failed for: r3v6, types: [java.lang.Object, java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: type inference failed for: r3v32 */
    /* JADX WARNING: type inference failed for: r3v35 */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0362, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0363, code lost:
        r3 = 0;
        r2 = null;
        r14 = null;
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0369, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x036a, code lost:
        r2 = null;
        r6 = null;
        r14 = null;
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x037a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x037b, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x038b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x038c, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x039c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x039d, code lost:
        r2 = r6;
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x03a5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x03a6, code lost:
        android.util.Log.e("PduPersister", "IOException while closing: " + r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x03bf, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x03c0, code lost:
        android.util.Log.e("PduPersister", "IOException while closing: " + r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x03d5, code lost:
        r14.close(r8);
        r0 = new java.io.File(r8);
        r2 = new android.content.ContentValues(0);
        r1 = r1.mContentResolver;
        r3 = p026b.p027a.p030b.p031a.C0632a.m1011Pa("content://mms/resetFilePerm/");
        r3.append(r0.getName());
        android.support.p016v4.media.session.C0107q.m122a(r1, android.net.Uri.parse(r3.toString()), r2, (java.lang.String) null, (java.lang.String[]) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0159, code lost:
        if (new java.io.File(r8).length() > 0) goto L_0x035e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x015d, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01a0, code lost:
        r2 = null;
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01a5, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01a6, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01a9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01aa, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01ad, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01ae, code lost:
        r2 = null;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0278 A[Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0300 A[SYNTHETIC, Splitter:B:166:0x0300] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x031a A[SYNTHETIC, Splitter:B:171:0x031a] */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x0334  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0362 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:45:0x0120] */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x03a1 A[SYNTHETIC, Splitter:B:203:0x03a1] */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x03bb A[SYNTHETIC, Splitter:B:208:0x03bb] */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x03d5  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x015d A[ExcHandler: all (th java.lang.Throwable), PHI: r8 
      PHI: (r8v23 java.lang.String) = (r8v21 java.lang.String), (r8v24 java.lang.String) binds: [B:65:0x0163, B:57:0x014c] A[DONT_GENERATE, DONT_INLINE], Splitter:B:57:0x014c] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0184 A[SYNTHETIC, Splitter:B:69:0x0184] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01bb  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.net.Uri mo6744a(com.android.messaging.mmslib.p039a.C0989s r20, long r21, java.util.Map r23) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            r3 = r23
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "content://mms/"
            r0.append(r4)
            r4 = r21
            r0.append(r4)
            java.lang.String r4 = "/part"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            android.net.Uri r0 = android.net.Uri.parse(r0)
            android.content.ContentValues r4 = new android.content.ContentValues
            r5 = 8
            r4.<init>(r5)
            int r5 = r20.getCharset()
            if (r5 == 0) goto L_0x0038
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            java.lang.String r7 = "chset"
            r4.put(r7, r6)
        L_0x0038:
            byte[] r6 = r20.getContentType()
            if (r6 != 0) goto L_0x0040
            r6 = 0
            goto L_0x0048
        L_0x0040:
            byte[] r6 = r20.getContentType()
            java.lang.String r6 = m2290o((byte[]) r6)
        L_0x0048:
            byte[] r7 = r20.getData()
            if (r6 == 0) goto L_0x0404
            java.lang.String r8 = "image/jpg"
            boolean r8 = r8.equals(r6)
            if (r8 == 0) goto L_0x0058
            java.lang.String r6 = "image/jpeg"
        L_0x0058:
            java.lang.String r8 = "text/plain"
            boolean r9 = r8.equals(r6)
            java.lang.String r10 = "PduPersister"
            if (r9 == 0) goto L_0x00a6
            if (r7 == 0) goto L_0x00a6
            com.android.messaging.mmslib.a.e r9 = new com.android.messaging.mmslib.a.e
            r9.<init>((int) r5, (byte[]) r7)
            java.lang.String r5 = r9.getString()
            java.lang.String r7 = "BEGIN:VCARD"
            boolean r5 = r5.startsWith(r7)
            if (r5 == 0) goto L_0x00a6
            java.lang.String r6 = "text/x-vCard"
            byte[] r5 = r6.getBytes()
            r2.mo6728g(r5)
            r5 = 3
            boolean r5 = android.util.Log.isLoggable(r10, r5)
            if (r5 == 0) goto L_0x00a6
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "PduPersister.persistPart part: "
            r5.append(r7)
            r5.append(r0)
            java.lang.String r7 = " contentType: "
            r5.append(r7)
            r5.append(r6)
            java.lang.String r7 = " changing to vcard"
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            com.android.messaging.util.C1430e.m3620d(r10, r5)
        L_0x00a6:
            java.lang.String r5 = "ct"
            r4.put(r5, r6)
            java.lang.String r5 = "application/smil"
            boolean r7 = r5.equals(r6)
            r9 = -1
            if (r7 == 0) goto L_0x00bd
            java.lang.Integer r7 = java.lang.Integer.valueOf(r9)
            java.lang.String r11 = "seq"
            r4.put(r11, r7)
        L_0x00bd:
            byte[] r7 = r20.mo6741lc()
            if (r7 == 0) goto L_0x00cd
            java.lang.String r11 = new java.lang.String
            r11.<init>(r7)
            java.lang.String r7 = "fn"
            r4.put(r7, r11)
        L_0x00cd:
            byte[] r7 = r20.getName()
            if (r7 == 0) goto L_0x00dd
            java.lang.String r11 = new java.lang.String
            r11.<init>(r7)
            java.lang.String r7 = "name"
            r4.put(r7, r11)
        L_0x00dd:
            byte[] r7 = r20.getContentDisposition()
            if (r7 == 0) goto L_0x00ec
            java.lang.String r7 = m2290o((byte[]) r7)
            java.lang.String r11 = "cd"
            r4.put(r11, r7)
        L_0x00ec:
            byte[] r7 = r20.mo6738ic()
            if (r7 == 0) goto L_0x00fb
            java.lang.String r7 = m2290o((byte[]) r7)
            java.lang.String r11 = "cid"
            r4.put(r11, r7)
        L_0x00fb:
            byte[] r7 = r20.mo6739jc()
            if (r7 == 0) goto L_0x010a
            java.lang.String r7 = m2290o((byte[]) r7)
            java.lang.String r11 = "cl"
            r4.put(r11, r7)
        L_0x010a:
            android.content.ContentResolver r7 = r1.mContentResolver     // Catch:{ IllegalStateException -> 0x0111 }
            android.net.Uri r0 = android.support.p016v4.media.session.C0107q.m123a((android.content.ContentResolver) r7, (android.net.Uri) r0, (android.content.ContentValues) r4)     // Catch:{ IllegalStateException -> 0x0111 }
            goto L_0x0118
        L_0x0111:
            r0 = move-exception
            java.lang.String r4 = "SqliteWrapper.insert threw: "
            com.android.messaging.util.C1430e.m3623e(r10, r4, r0)
            r0 = 0
        L_0x0118:
            r4 = r0
            if (r4 == 0) goto L_0x03fc
            java.lang.String r7 = "content://mms/resetFilePerm/"
            java.lang.String r11 = "IOException while closing: "
            r12 = 0
            byte[] r13 = r20.getData()     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            int r0 = r20.getCharset()     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            boolean r8 = r8.equals(r6)     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            if (r8 != 0) goto L_0x02bf
            boolean r5 = r5.equals(r6)     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            if (r5 != 0) goto L_0x02bf
            java.lang.String r5 = "text/html"
            boolean r5 = r5.equals(r6)     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            if (r5 == 0) goto L_0x013e
            goto L_0x02bf
        L_0x013e:
            java.lang.String r0 = "application/vnd.oma.drm.message"
            boolean r5 = r0.equals(r6)     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            if (r5 == 0) goto L_0x01b1
            android.content.Context r0 = r1.mContext     // Catch:{ Exception -> 0x0161, all -> 0x0362 }
            java.lang.String r8 = m2287b((android.content.Context) r0, (android.net.Uri) r4)     // Catch:{ Exception -> 0x0161, all -> 0x0362 }
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x015f, all -> 0x015d }
            r0.<init>(r8)     // Catch:{ Exception -> 0x015f, all -> 0x015d }
            long r14 = r0.length()     // Catch:{ Exception -> 0x015f, all -> 0x015d }
            r16 = 0
            int r0 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r0 <= 0) goto L_0x017b
            goto L_0x035e
        L_0x015d:
            r0 = move-exception
            goto L_0x01a0
        L_0x015f:
            r0 = move-exception
            goto L_0x0163
        L_0x0161:
            r0 = move-exception
            r8 = 0
        L_0x0163:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x01ad, FileNotFoundException -> 0x01a9, IOException -> 0x01a5, all -> 0x015d }
            r14.<init>()     // Catch:{ SQLiteException -> 0x01ad, FileNotFoundException -> 0x01a9, IOException -> 0x01a5, all -> 0x015d }
            java.lang.String r15 = "Can't get file info for: "
            r14.append(r15)     // Catch:{ SQLiteException -> 0x01ad, FileNotFoundException -> 0x01a9, IOException -> 0x01a5, all -> 0x015d }
            android.net.Uri r15 = r20.getDataUri()     // Catch:{ SQLiteException -> 0x01ad, FileNotFoundException -> 0x01a9, IOException -> 0x01a5, all -> 0x015d }
            r14.append(r15)     // Catch:{ SQLiteException -> 0x01ad, FileNotFoundException -> 0x01a9, IOException -> 0x01a5, all -> 0x015d }
            java.lang.String r14 = r14.toString()     // Catch:{ SQLiteException -> 0x01ad, FileNotFoundException -> 0x01a9, IOException -> 0x01a5, all -> 0x015d }
            android.util.Log.e(r10, r14, r0)     // Catch:{ SQLiteException -> 0x01ad, FileNotFoundException -> 0x01a9, IOException -> 0x01a5, all -> 0x015d }
        L_0x017b:
            android.content.Context r0 = r1.mContext     // Catch:{ SQLiteException -> 0x01ad, FileNotFoundException -> 0x01a9, IOException -> 0x01a5, all -> 0x015d }
            com.android.messaging.mmslib.b.d r14 = com.android.messaging.mmslib.p040b.C0999d.m2304f(r0, r6)     // Catch:{ SQLiteException -> 0x01ad, FileNotFoundException -> 0x01a9, IOException -> 0x01a5, all -> 0x015d }
            if (r14 == 0) goto L_0x0184
            goto L_0x01b3
        L_0x0184:
            com.android.messaging.mmslib.MmsException r0 = new com.android.messaging.mmslib.MmsException     // Catch:{ SQLiteException -> 0x02b8, FileNotFoundException -> 0x02b1, IOException -> 0x02aa, all -> 0x02a0 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x02b8, FileNotFoundException -> 0x02b1, IOException -> 0x02aa, all -> 0x02a0 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x02b8, FileNotFoundException -> 0x02b1, IOException -> 0x02aa, all -> 0x02a0 }
            java.lang.String r3 = "Mimetype "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x02b8, FileNotFoundException -> 0x02b1, IOException -> 0x02aa, all -> 0x02a0 }
            r2.append(r6)     // Catch:{ SQLiteException -> 0x02b8, FileNotFoundException -> 0x02b1, IOException -> 0x02aa, all -> 0x02a0 }
            java.lang.String r3 = " can not be converted."
            r2.append(r3)     // Catch:{ SQLiteException -> 0x02b8, FileNotFoundException -> 0x02b1, IOException -> 0x02aa, all -> 0x02a0 }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteException -> 0x02b8, FileNotFoundException -> 0x02b1, IOException -> 0x02aa, all -> 0x02a0 }
            r0.<init>((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x02b8, FileNotFoundException -> 0x02b1, IOException -> 0x02aa, all -> 0x02a0 }
            throw r0     // Catch:{ SQLiteException -> 0x02b8, FileNotFoundException -> 0x02b1, IOException -> 0x02aa, all -> 0x02a0 }
        L_0x01a0:
            r2 = 0
            r3 = 0
            r14 = r3
            goto L_0x0291
        L_0x01a5:
            r0 = move-exception
            r2 = 0
            goto L_0x02ac
        L_0x01a9:
            r0 = move-exception
            r2 = 0
            goto L_0x02b3
        L_0x01ad:
            r0 = move-exception
            r2 = 0
            goto L_0x02ba
        L_0x01b1:
            r14 = 0
            r8 = 0
        L_0x01b3:
            android.content.ContentResolver r0 = r1.mContentResolver     // Catch:{ SQLiteException -> 0x02b8, FileNotFoundException -> 0x02b1, IOException -> 0x02aa, all -> 0x02a0 }
            java.io.OutputStream r6 = r0.openOutputStream(r4)     // Catch:{ SQLiteException -> 0x02b8, FileNotFoundException -> 0x02b1, IOException -> 0x02aa, all -> 0x02a0 }
            if (r6 == 0) goto L_0x0278
            java.lang.String r0 = "Error converting drm data."
            if (r13 != 0) goto L_0x025e
            android.net.Uri r13 = r20.getDataUri()     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            if (r13 == 0) goto L_0x0229
            boolean r15 = r13.equals(r4)     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            if (r15 == 0) goto L_0x01cc
            goto L_0x0229
        L_0x01cc:
            if (r3 == 0) goto L_0x01db
            boolean r15 = r3.containsKey(r13)     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            if (r15 == 0) goto L_0x01db
            java.lang.Object r3 = r3.get(r13)     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            java.io.InputStream r3 = (java.io.InputStream) r3     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            goto L_0x01dc
        L_0x01db:
            r3 = 0
        L_0x01dc:
            if (r3 != 0) goto L_0x01ee
            android.content.ContentResolver r15 = r1.mContentResolver     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            java.io.InputStream r3 = r15.openInputStream(r13)     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            goto L_0x01ee
        L_0x01e5:
            r0 = move-exception
            goto L_0x036f
        L_0x01e8:
            r0 = move-exception
            goto L_0x0380
        L_0x01eb:
            r0 = move-exception
            goto L_0x0391
        L_0x01ee:
            if (r3 == 0) goto L_0x0212
            r13 = 8192(0x2000, float:1.14794E-41)
            byte[] r13 = new byte[r13]     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
        L_0x01f4:
            int r15 = r3.read(r13)     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            if (r15 == r9) goto L_0x02fe
            if (r5 != 0) goto L_0x0200
            r6.write(r13, r12, r15)     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            goto L_0x01f4
        L_0x0200:
            byte[] r9 = r14.mo6763e(r13, r15)     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            if (r9 == 0) goto L_0x020c
            int r15 = r9.length     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            r6.write(r9, r12, r15)     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            r9 = -1
            goto L_0x01f4
        L_0x020c:
            com.android.messaging.mmslib.MmsException r2 = new com.android.messaging.mmslib.MmsException     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            r2.<init>((java.lang.String) r0)     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            throw r2     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
        L_0x0212:
            com.android.messaging.mmslib.MmsException r0 = new com.android.messaging.mmslib.MmsException     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            java.lang.String r4 = "Failed to create input stream on "
            r2.append(r4)     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            r2.append(r13)     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            r0.<init>((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
            throw r0     // Catch:{ SQLiteException -> 0x01eb, FileNotFoundException -> 0x01e8, IOException -> 0x01e5 }
        L_0x0229:
            java.lang.String r0 = "Can't find data for this part."
            android.util.Log.w(r10, r0)     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            r6.close()     // Catch:{ IOException -> 0x0232 }
            goto L_0x0246
        L_0x0232:
            r0 = move-exception
            r3 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r10, r0, r3)
        L_0x0246:
            if (r14 == 0) goto L_0x035e
            r14.close(r8)
            java.io.File r0 = new java.io.File
            r0.<init>(r8)
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>(r12)
            android.content.ContentResolver r1 = r1.mContentResolver
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            goto L_0x0348
        L_0x025e:
            if (r5 != 0) goto L_0x0264
            r6.write(r13)     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            goto L_0x026f
        L_0x0264:
            int r3 = r13.length     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            byte[] r3 = r14.mo6763e(r13, r3)     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            if (r3 == 0) goto L_0x0272
            int r0 = r3.length     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            r6.write(r3, r12, r0)     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
        L_0x026f:
            r3 = 0
            goto L_0x02fe
        L_0x0272:
            com.android.messaging.mmslib.MmsException r2 = new com.android.messaging.mmslib.MmsException     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            r2.<init>((java.lang.String) r0)     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            throw r2     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
        L_0x0278:
            com.android.messaging.mmslib.MmsException r0 = new com.android.messaging.mmslib.MmsException     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            r2.<init>()     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            java.lang.String r3 = "Failed to create output stream on "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            r2.append(r4)     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            r0.<init>((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
            throw r0     // Catch:{ SQLiteException -> 0x029c, FileNotFoundException -> 0x0298, IOException -> 0x0294, all -> 0x028f }
        L_0x028f:
            r0 = move-exception
            r2 = r6
        L_0x0291:
            r3 = 0
            goto L_0x0367
        L_0x0294:
            r0 = move-exception
            r2 = 0
            goto L_0x036e
        L_0x0298:
            r0 = move-exception
            r2 = 0
            goto L_0x037f
        L_0x029c:
            r0 = move-exception
            r2 = 0
            goto L_0x0390
        L_0x02a0:
            r0 = move-exception
            r2 = 0
            r3 = 0
            r18 = r3
            r3 = r2
            r2 = r18
            goto L_0x0367
        L_0x02aa:
            r0 = move-exception
            r2 = r14
        L_0x02ac:
            r14 = r2
            r2 = 0
            r6 = 0
            goto L_0x036e
        L_0x02b1:
            r0 = move-exception
            r2 = r14
        L_0x02b3:
            r14 = r2
            r2 = 0
            r6 = 0
            goto L_0x037f
        L_0x02b8:
            r0 = move-exception
            r2 = r14
        L_0x02ba:
            r14 = r2
            r2 = 0
            r6 = 0
            goto L_0x0390
        L_0x02bf:
            if (r13 == 0) goto L_0x02fa
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            java.lang.String r5 = "text"
            com.android.messaging.mmslib.a.e r6 = new com.android.messaging.mmslib.a.e     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            r6.<init>((int) r0, (byte[]) r13)     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            java.lang.String r0 = r6.getString()     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            r3.put(r5, r0)     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            android.content.ContentResolver r0 = r1.mContentResolver     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            r5 = 0
            int r0 = r0.update(r4, r3, r5, r5)     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            r3 = 1
            if (r0 != r3) goto L_0x02df
            goto L_0x02fa
        L_0x02df:
            com.android.messaging.mmslib.MmsException r0 = new com.android.messaging.mmslib.MmsException     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            java.lang.String r3 = "unable to update "
            r2.append(r3)     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            java.lang.String r3 = r4.toString()     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            r2.append(r3)     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            r0.<init>((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
            throw r0     // Catch:{ SQLiteException -> 0x038b, FileNotFoundException -> 0x037a, IOException -> 0x0369, all -> 0x0362 }
        L_0x02fa:
            r3 = 0
            r6 = 0
            r14 = 0
            r8 = 0
        L_0x02fe:
            if (r6 == 0) goto L_0x0318
            r6.close()     // Catch:{ IOException -> 0x0304 }
            goto L_0x0318
        L_0x0304:
            r0 = move-exception
            r5 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r10, r0, r5)
        L_0x0318:
            if (r3 == 0) goto L_0x0332
            r3.close()     // Catch:{ IOException -> 0x031e }
            goto L_0x0332
        L_0x031e:
            r0 = move-exception
            r5 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r10, r0, r5)
        L_0x0332:
            if (r14 == 0) goto L_0x035e
            r14.close(r8)
            java.io.File r0 = new java.io.File
            r0.<init>(r8)
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>(r12)
            android.content.ContentResolver r1 = r1.mContentResolver
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
        L_0x0348:
            r5.append(r7)
            java.lang.String r0 = r0.getName()
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            android.net.Uri r0 = android.net.Uri.parse(r0)
            r5 = 0
            android.support.p016v4.media.session.C0107q.m122a((android.content.ContentResolver) r1, (android.net.Uri) r0, (android.content.ContentValues) r3, (java.lang.String) r5, (java.lang.String[]) r5)
        L_0x035e:
            r2.mo6735h((android.net.Uri) r4)
            return r4
        L_0x0362:
            r0 = move-exception
            r3 = 0
            r2 = 0
            r14 = 0
            r8 = 0
        L_0x0367:
            r4 = r0
            goto L_0x039f
        L_0x0369:
            r0 = move-exception
            r2 = 0
            r6 = 0
            r14 = 0
            r8 = 0
        L_0x036e:
            r3 = r2
        L_0x036f:
            java.lang.String r2 = "Failed to read/write data."
            android.util.Log.e(r10, r2, r0)     // Catch:{ all -> 0x039c }
            com.android.messaging.mmslib.MmsException r2 = new com.android.messaging.mmslib.MmsException     // Catch:{ all -> 0x039c }
            r2.<init>((java.lang.Throwable) r0)     // Catch:{ all -> 0x039c }
            throw r2     // Catch:{ all -> 0x039c }
        L_0x037a:
            r0 = move-exception
            r2 = 0
            r6 = 0
            r14 = 0
            r8 = 0
        L_0x037f:
            r3 = r2
        L_0x0380:
            java.lang.String r2 = "Failed to open Input/Output stream."
            android.util.Log.e(r10, r2, r0)     // Catch:{ all -> 0x039c }
            com.android.messaging.mmslib.MmsException r2 = new com.android.messaging.mmslib.MmsException     // Catch:{ all -> 0x039c }
            r2.<init>((java.lang.Throwable) r0)     // Catch:{ all -> 0x039c }
            throw r2     // Catch:{ all -> 0x039c }
        L_0x038b:
            r0 = move-exception
            r2 = 0
            r6 = 0
            r14 = 0
            r8 = 0
        L_0x0390:
            r3 = r2
        L_0x0391:
            java.lang.String r2 = "Failed with SQLiteException."
            android.util.Log.e(r10, r2, r0)     // Catch:{ all -> 0x039c }
            com.android.messaging.mmslib.MmsException r2 = new com.android.messaging.mmslib.MmsException     // Catch:{ all -> 0x039c }
            r2.<init>((java.lang.Throwable) r0)     // Catch:{ all -> 0x039c }
            throw r2     // Catch:{ all -> 0x039c }
        L_0x039c:
            r0 = move-exception
            r2 = r6
            goto L_0x0367
        L_0x039f:
            if (r2 == 0) goto L_0x03b9
            r2.close()     // Catch:{ IOException -> 0x03a5 }
            goto L_0x03b9
        L_0x03a5:
            r0 = move-exception
            r5 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r10, r0, r5)
        L_0x03b9:
            if (r3 == 0) goto L_0x03d3
            r3.close()     // Catch:{ IOException -> 0x03bf }
            goto L_0x03d3
        L_0x03bf:
            r0 = move-exception
            r2 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r10, r0, r2)
        L_0x03d3:
            if (r14 == 0) goto L_0x03fb
            r14.close(r8)
            java.io.File r0 = new java.io.File
            r0.<init>(r8)
            android.content.ContentValues r2 = new android.content.ContentValues
            r2.<init>(r12)
            android.content.ContentResolver r1 = r1.mContentResolver
            java.lang.StringBuilder r3 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r7)
            java.lang.String r0 = r0.getName()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.net.Uri r0 = android.net.Uri.parse(r0)
            r3 = 0
            android.support.p016v4.media.session.C0107q.m122a((android.content.ContentResolver) r1, (android.net.Uri) r0, (android.content.ContentValues) r2, (java.lang.String) r3, (java.lang.String[]) r3)
        L_0x03fb:
            throw r4
        L_0x03fc:
            com.android.messaging.mmslib.MmsException r0 = new com.android.messaging.mmslib.MmsException
            java.lang.String r1 = "Failed to persist part, return null."
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0404:
            com.android.messaging.mmslib.MmsException r0 = new com.android.messaging.mmslib.MmsException
            java.lang.String r1 = "MIME type of the part must be set."
            r0.<init>((java.lang.String) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.mmslib.p039a.C0990t.mo6744a(com.android.messaging.mmslib.a.s, long, java.util.Map):android.net.Uri");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0064, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006d, code lost:
        throw new java.lang.IllegalArgumentException("Given Uri is not formatted in a way so that it can be found in media store.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006e, code lost:
        if (r0 != null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0070, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0073, code lost:
        throw r8;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0066 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m2287b(android.content.Context r8, android.net.Uri r9) {
        /*
            r0 = 0
            if (r9 == 0) goto L_0x0080
            java.lang.String r1 = r9.getScheme()
            if (r1 == 0) goto L_0x007c
            java.lang.String r2 = ""
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L_0x007c
            java.lang.String r2 = "file"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x001a
            goto L_0x007c
        L_0x001a:
            java.lang.String r2 = "http"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0027
            java.lang.String r0 = r9.toString()
            goto L_0x0080
        L_0x0027:
            java.lang.String r2 = "content"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0074
            java.lang.String r1 = "_data"
            java.lang.String[] r4 = new java.lang.String[]{r1}
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch:{ SQLiteException -> 0x0066 }
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r9
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0066 }
            if (r0 == 0) goto L_0x005c
            int r8 = r0.getCount()     // Catch:{ SQLiteException -> 0x0066 }
            if (r8 == 0) goto L_0x005c
            boolean r8 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0066 }
            if (r8 == 0) goto L_0x005c
            int r8 = r0.getColumnIndexOrThrow(r1)     // Catch:{ SQLiteException -> 0x0066 }
            java.lang.String r8 = r0.getString(r8)     // Catch:{ SQLiteException -> 0x0066 }
            r0.close()
            r0 = r8
            goto L_0x0080
        L_0x005c:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException     // Catch:{ SQLiteException -> 0x0066 }
            java.lang.String r9 = "Given Uri could not be found in media store"
            r8.<init>(r9)     // Catch:{ SQLiteException -> 0x0066 }
            throw r8     // Catch:{ SQLiteException -> 0x0066 }
        L_0x0064:
            r8 = move-exception
            goto L_0x006e
        L_0x0066:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0064 }
            java.lang.String r9 = "Given Uri is not formatted in a way so that it can be found in media store."
            r8.<init>(r9)     // Catch:{ all -> 0x0064 }
            throw r8     // Catch:{ all -> 0x0064 }
        L_0x006e:
            if (r0 == 0) goto L_0x0073
            r0.close()
        L_0x0073:
            throw r8
        L_0x0074:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Given Uri scheme is not supported"
            r8.<init>(r9)
            throw r8
        L_0x007c:
            java.lang.String r0 = r9.getPath()
        L_0x0080:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.mmslib.p039a.C0990t.m2287b(android.content.Context, android.net.Uri):java.lang.String");
    }

    /* renamed from: a */
    public Uri mo6743a(C0976f fVar, Uri uri, int i, String str, Map map) {
        long j;
        int i2;
        Uri uri2;
        C0980j body;
        long j2;
        C0975e[] eVarArr;
        C0976f fVar2 = fVar;
        Uri uri3 = uri;
        if (uri3 != null) {
            try {
                j = ContentUris.parseId(uri);
            } catch (NumberFormatException unused) {
                j = -1;
            }
            boolean z = j != -1;
            if (z || f1435qD.get(uri3) != null) {
                synchronized (f1431mD) {
                    if (f1431mD.mo6766j(uri3)) {
                        try {
                            f1431mD.wait();
                        } catch (InterruptedException e) {
                            Log.e("PduPersister", "persist1: ", e);
                        }
                    }
                }
                f1431mD.mo6767k(uri3);
                C0987q qVar = fVar2.f1404Zl;
                ContentValues contentValues = new ContentValues();
                contentValues.put("seen", 1);
                int size = f1442xD.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    }
                    int keyAt = f1442xD.keyAt(size);
                    C0975e C = qVar.mo6710C(keyAt);
                    if (C != null) {
                        contentValues.put((String) f1442xD.valueAt(size), m2290o(C.mo6666fc()));
                        contentValues.put((String) f1441wD.get(keyAt), Integer.valueOf(C.mo6663Zh()));
                    }
                }
                int size2 = f1443yD.size();
                while (true) {
                    size2--;
                    if (size2 < 0) {
                        break;
                    }
                    byte[] G = qVar.mo6714G(f1443yD.keyAt(size2));
                    if (G != null) {
                        contentValues.put((String) f1443yD.valueAt(size2), m2290o(G));
                    }
                }
                int size3 = f1444zD.size();
                while (true) {
                    size3--;
                    if (size3 < 0) {
                        break;
                    }
                    int F = qVar.mo6713F(f1444zD.keyAt(size3));
                    if (F != 0) {
                        contentValues.put((String) f1444zD.valueAt(size3), Integer.valueOf(F));
                    }
                }
                int size4 = f1429BD.size();
                while (true) {
                    size4--;
                    if (size4 < 0) {
                        break;
                    }
                    long E = qVar.mo6712E(f1429BD.keyAt(size4));
                    if (E != -1) {
                        contentValues.put((String) f1429BD.valueAt(size4), Long.valueOf(E));
                    }
                }
                SparseArray sparseArray = new SparseArray(f1432nD.length);
                for (int i3 : f1432nD) {
                    if (i3 == 137) {
                        C0975e C2 = qVar.mo6710C(i3);
                        eVarArr = C2 != null ? new C0975e[]{C2} : null;
                    } else {
                        eVarArr = qVar.mo6711D(i3);
                    }
                    sparseArray.put(i3, eVarArr);
                }
                HashSet hashSet = new HashSet();
                int messageType = fVar.getMessageType();
                if (messageType == 130 || messageType == 132 || messageType == 128) {
                    if (messageType == 128) {
                        m2283a(151, hashSet, sparseArray);
                    } else if (messageType == 130 || messageType == 132) {
                        m2283a(137, hashSet, sparseArray);
                        C0975e[] eVarArr2 = (C0975e[]) sparseArray.get(151);
                        C0975e[] eVarArr3 = (C0975e[]) sparseArray.get(130);
                        ArrayList arrayList = new ArrayList();
                        if (eVarArr2 != null) {
                            for (C0975e eVar : eVarArr2) {
                                if (eVar != null) {
                                    arrayList.add(eVar.getString());
                                }
                            }
                        }
                        if (eVarArr3 != null) {
                            for (C0975e eVar2 : eVarArr3) {
                                if (eVar2 != null) {
                                    arrayList.add(eVar2.getString());
                                }
                            }
                        }
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            String str2 = (String) it.next();
                            if (TextUtils.isEmpty(str)) {
                                String str3 = str;
                            } else if (PhoneNumberUtils.compare(str2, str)) {
                            }
                            if (!hashSet.contains(str2)) {
                                hashSet.add(str2);
                            }
                        }
                    }
                    if (!hashSet.isEmpty()) {
                        j2 = C1026v.getOrCreateThreadId(this.mContext, (Set) hashSet);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("PduPersister.persist No recipients; persisting PDU to thread: ");
                        j2 = -1;
                        sb.append(-1);
                        C1430e.m3630w("PduPersister", sb.toString());
                    }
                    contentValues.put("thread_id", Long.valueOf(j2));
                }
                long currentTimeMillis = System.currentTimeMillis();
                if (!(fVar2 instanceof C0977g) || (body = ((C0977g) fVar2).getBody()) == null) {
                    i2 = 1;
                } else {
                    int bi = body.mo6690bi();
                    i2 = bi > 2 ? 0 : 1;
                    for (int i4 = 0; i4 < bi; i4++) {
                        C0989s part = body.getPart(i4);
                        mo6744a(part, currentTimeMillis, map);
                        String o = part.getContentType() == null ? null : m2290o(part.getContentType());
                        if (o != null && !"application/smil".equals(o) && !"text/plain".equals(o)) {
                            i2 = 0;
                        }
                    }
                }
                if (C1464na.m3755Vj()) {
                    contentValues.put("text_only", Integer.valueOf(i2));
                }
                if (C1464na.m3759Zj()) {
                    contentValues.put("sub_id", Integer.valueOf(i));
                } else {
                    C1424b.equals(-1, i);
                }
                if (z) {
                    C0107q.m122a(this.mContentResolver, uri3, contentValues, (String) null, (String[]) null);
                    uri2 = uri3;
                } else {
                    uri2 = C0107q.m123a(this.mContentResolver, uri3, contentValues);
                    if (uri2 != null) {
                        j = ContentUris.parseId(uri2);
                    } else {
                        throw new MmsException("persist() failed: return null.");
                    }
                }
                ContentValues contentValues2 = new ContentValues(1);
                contentValues2.put("mid", Long.valueOf(j));
                C0107q.m122a(this.mContentResolver, Uri.parse("content://mms/" + currentTimeMillis + "/part"), contentValues2, (String) null, (String[]) null);
                if (!z) {
                    uri2 = Uri.parse(uri3 + "/" + j);
                }
                for (int i5 : f1432nD) {
                    C0975e[] eVarArr4 = (C0975e[]) sparseArray.get(i5);
                    if (eVarArr4 != null) {
                        ContentValues contentValues3 = new ContentValues(3);
                        for (C0975e eVar3 : eVarArr4) {
                            contentValues3.clear();
                            contentValues3.put("address", m2290o(eVar3.mo6666fc()));
                            contentValues3.put("charset", Integer.valueOf(eVar3.mo6663Zh()));
                            contentValues3.put("type", Integer.valueOf(i5));
                            C0107q.m123a(this.mContentResolver, Uri.parse("content://mms/" + j + "/addr"), contentValues3);
                        }
                    }
                }
                return uri2;
            }
            throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
        }
        throw new MmsException("Uri may not be null.");
    }

    /* renamed from: a */
    private void m2283a(int i, HashSet hashSet, SparseArray sparseArray) {
        C0975e[] eVarArr = (C0975e[]) sparseArray.get(i);
        if (eVarArr != null) {
            for (C0975e eVar : eVarArr) {
                if (eVar != null) {
                    String string = eVar.getString();
                    if (!hashSet.contains(string)) {
                        hashSet.add(string);
                    }
                }
            }
        }
    }
}
