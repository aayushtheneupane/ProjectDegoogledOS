package com.android.p032ex.chips;

import android.accounts.Account;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.android.ex.chips.N */
public class C0646N extends CursorAdapter {

    /* renamed from: rl */
    private static final Map f759rl = new HashMap();

    /* renamed from: ga */
    private final long f760ga;
    private C0704v mDropdownChipLayouter;

    /* renamed from: nl */
    private final StateListDrawable f761nl;

    /* renamed from: ol */
    private int f762ol;

    /* renamed from: pl */
    private C0644L f763pl;

    /* renamed from: ql */
    private final Long f764ql;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00e5 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00d8 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0646N(android.content.Context r20, long r21, java.lang.Long r23, java.lang.String r24, long r25, int r27, com.android.p032ex.chips.C0644L r28, com.android.p032ex.chips.C0704v r29, android.graphics.drawable.StateListDrawable r30) {
        /*
            r19 = this;
            r0 = r19
            r1 = r21
            r3 = r23
            r4 = r24
            java.lang.String r5 = "directory"
            java.lang.String r6 = "entities"
            if (r27 != 0) goto L_0x003b
            com.android.ex.chips.B r8 = com.android.p032ex.chips.C0635C.EMAIL
            java.lang.String[] r8 = r8.getProjection()
            if (r3 == 0) goto L_0x0034
            if (r4 != 0) goto L_0x0019
            goto L_0x0034
        L_0x0019:
            android.net.Uri r9 = android.provider.ContactsContract.Contacts.getLookupUri(r1, r4)
            android.net.Uri$Builder r9 = r9.buildUpon()
            android.net.Uri$Builder r6 = r9.appendPath(r6)
            java.lang.String r9 = java.lang.String.valueOf(r23)
            android.net.Uri$Builder r5 = r6.appendQueryParameter(r5, r9)
            android.net.Uri r5 = r5.build()
            java.lang.String r6 = "vnd.android.cursor.item/email_v2"
            goto L_0x0067
        L_0x0034:
            com.android.ex.chips.B r5 = com.android.p032ex.chips.C0635C.EMAIL
            android.net.Uri r5 = r5.getContentUri()
            goto L_0x0049
        L_0x003b:
            com.android.ex.chips.B r8 = com.android.p032ex.chips.C0635C.PHONE
            java.lang.String[] r8 = r8.getProjection()
            if (r4 != 0) goto L_0x004d
            com.android.ex.chips.B r5 = com.android.p032ex.chips.C0635C.PHONE
            android.net.Uri r5 = r5.getContentUri()
        L_0x0049:
            r10 = r5
            r11 = r8
            r6 = 0
            goto L_0x0069
        L_0x004d:
            android.net.Uri r9 = android.provider.ContactsContract.Contacts.getLookupUri(r1, r4)
            android.net.Uri$Builder r9 = r9.buildUpon()
            android.net.Uri$Builder r6 = r9.appendPath(r6)
            java.lang.String r9 = java.lang.String.valueOf(r23)
            android.net.Uri$Builder r5 = r6.appendQueryParameter(r5, r9)
            android.net.Uri r5 = r5.build()
            java.lang.String r6 = "vnd.android.cursor.item/phone_v2"
        L_0x0067:
            r10 = r5
            r11 = r8
        L_0x0069:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r8 = 4
            r9 = r11[r8]
            r5.append(r9)
            java.lang.String r9 = " = ?"
            r5.append(r9)
            java.lang.String r12 = r5.toString()
            boolean r5 = com.android.p032ex.chips.C0688m.m1070g(r20)
            r15 = 1
            r14 = 0
            if (r5 == 0) goto L_0x0099
            android.content.ContentResolver r9 = r20.getContentResolver()
            java.lang.String[] r13 = new java.lang.String[r15]
            java.lang.String r1 = java.lang.String.valueOf(r21)
            r13[r14] = r1
            r1 = 0
            r2 = r14
            r14 = r1
            android.database.Cursor r1 = r9.query(r10, r11, r12, r13, r14)
            goto L_0x009f
        L_0x0099:
            r2 = r14
            android.database.MatrixCursor r1 = new android.database.MatrixCursor
            r1.<init>(r11)
        L_0x009f:
            android.database.MatrixCursor r5 = new android.database.MatrixCursor
            java.lang.String[] r9 = r1.getColumnNames()
            int r10 = r1.getCount()
            r5.<init>(r9, r10)
            java.util.HashSet r9 = new java.util.HashSet
            r9.<init>()
            r10 = -1
            r1.moveToPosition(r10)
        L_0x00b5:
            boolean r11 = r1.moveToNext()
            r12 = 6
            r13 = 9
            r14 = 7
            if (r11 == 0) goto L_0x00d8
            java.lang.String r11 = r1.getString(r13)
            java.lang.String r7 = "vnd.android.cursor.item/name"
            boolean r7 = r7.equals(r11)
            if (r7 == 0) goto L_0x00b5
            java.lang.String r7 = r1.getString(r2)
            java.lang.String r11 = r1.getString(r12)
            int r16 = r1.getInt(r14)
            goto L_0x00dc
        L_0x00d8:
            r16 = r2
            r7 = 0
            r11 = 0
        L_0x00dc:
            r1.moveToPosition(r10)
        L_0x00df:
            boolean r17 = r1.moveToNext()
            if (r17 == 0) goto L_0x01de
            if (r6 == 0) goto L_0x00f2
            java.lang.String r10 = r1.getString(r13)
            boolean r10 = r6.equals(r10)
            if (r10 != 0) goto L_0x00f2
            goto L_0x00fc
        L_0x00f2:
            java.lang.String r10 = r1.getString(r15)
            boolean r17 = r9.contains(r10)
            if (r17 == 0) goto L_0x00fe
        L_0x00fc:
            r10 = -1
            goto L_0x00df
        L_0x00fe:
            r9.add(r10)
            r10 = 10
            java.lang.Object[] r10 = new java.lang.Object[r10]
            java.lang.String r17 = r1.getString(r2)
            r10[r2] = r17
            java.lang.String r17 = r1.getString(r15)
            r10[r15] = r17
            r15 = 2
            int r17 = r1.getInt(r15)
            java.lang.Integer r17 = java.lang.Integer.valueOf(r17)
            r10[r15] = r17
            r15 = 3
            java.lang.String r17 = r1.getString(r15)
            r10[r15] = r17
            long r17 = r1.getLong(r8)
            java.lang.Long r15 = java.lang.Long.valueOf(r17)
            r10[r8] = r15
            r15 = 5
            long r17 = r1.getLong(r15)
            java.lang.Long r17 = java.lang.Long.valueOf(r17)
            r10[r15] = r17
            java.lang.String r15 = r1.getString(r12)
            r10[r12] = r15
            int r15 = r1.getInt(r14)
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            r10[r14] = r15
            r15 = 8
            java.lang.String r17 = r1.getString(r15)
            r10[r15] = r17
            java.lang.String r17 = r1.getString(r13)
            r10[r13] = r17
            r17 = r10[r2]
            if (r17 != 0) goto L_0x015c
            r10[r2] = r7
        L_0x015c:
            r17 = r10[r12]
            if (r17 != 0) goto L_0x0162
            r10[r12] = r11
        L_0x0162:
            r17 = r10[r14]
            java.lang.Integer r17 = (java.lang.Integer) r17
            int r17 = r17.intValue()
            if (r17 != 0) goto L_0x0172
            java.lang.Integer r17 = java.lang.Integer.valueOf(r16)
            r10[r14] = r17
        L_0x0172:
            r17 = r10[r15]
            if (r17 != 0) goto L_0x0178
            r10[r15] = r4
        L_0x0178:
            r15 = r10[r12]
            java.lang.String r15 = (java.lang.String) r15
            if (r15 == 0) goto L_0x01d1
            java.util.Map r8 = f759rl
            boolean r8 = r8.containsKey(r15)
            if (r8 == 0) goto L_0x018f
            java.util.Map r8 = f759rl
            java.lang.Object r8 = r8.get(r15)
            r10[r12] = r8
            goto L_0x01d1
        L_0x018f:
            r8 = 63
            int r13 = r15.indexOf(r8)
            int r8 = r15.lastIndexOf(r8)
            if (r13 == r8) goto L_0x01d1
            java.lang.String r8 = "\\?"
            java.lang.String[] r8 = r15.split(r8)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r14 = r2
        L_0x01a7:
            int r2 = r8.length
            if (r14 >= r2) goto L_0x01c3
            r2 = 1
            if (r14 != r2) goto L_0x01b3
            java.lang.String r12 = "?"
            r13.append(r12)
            goto L_0x01ba
        L_0x01b3:
            if (r14 <= r2) goto L_0x01ba
            java.lang.String r12 = "&"
            r13.append(r12)
        L_0x01ba:
            r12 = r8[r14]
            r13.append(r12)
            int r14 = r14 + 1
            r12 = 6
            goto L_0x01a7
        L_0x01c3:
            r2 = 1
            java.lang.String r8 = r13.toString()
            java.util.Map r12 = f759rl
            r12.put(r15, r8)
            r12 = 6
            r10[r12] = r8
            goto L_0x01d2
        L_0x01d1:
            r2 = 1
        L_0x01d2:
            r5.addRow(r10)
            r15 = r2
            r2 = 0
            r8 = 4
            r10 = -1
            r13 = 9
            r14 = 7
            goto L_0x00df
        L_0x01de:
            r1.close()
            r1 = r20
            r2 = 0
            r0.<init>(r1, r5, r2)
            r1 = -1
            r0.f762ol = r1
            r1 = r25
            r0.f760ga = r1
            r0.f764ql = r3
            r1 = r28
            r0.f763pl = r1
            r1 = r29
            r0.mDropdownChipLayouter = r1
            r1 = r30
            r0.f761nl = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.p032ex.chips.C0646N.<init>(android.content.Context, long, java.lang.Long, java.lang.String, long, int, com.android.ex.chips.L, com.android.ex.chips.v, android.graphics.drawable.StateListDrawable):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:73:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01ab  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01e2  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1043a(android.content.Context r21, com.android.p032ex.chips.C0684k r22, java.util.ArrayList r23, android.accounts.Account r24, com.android.p032ex.chips.C0645M r25) {
        /*
            r0 = r22
            r1 = r23
            r2 = r25
            com.android.ex.chips.B r9 = com.android.p032ex.chips.C0635C.EMAIL
            r3 = 50
            int r4 = r23.size()
            int r3 = java.lang.Math.min(r3, r4)
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r10 = 0
            r6 = r10
        L_0x001e:
            if (r6 >= r3) goto L_0x0052
            java.lang.Object r7 = r1.get(r6)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r7 = r7.toLowerCase()
            android.text.util.Rfc822Token[] r7 = android.text.util.Rfc822Tokenizer.tokenize(r7)
            int r8 = r7.length
            if (r8 <= 0) goto L_0x0038
            r7 = r7[r10]
            java.lang.String r7 = r7.getAddress()
            goto L_0x003e
        L_0x0038:
            java.lang.Object r7 = r1.get(r6)
            java.lang.String r7 = (java.lang.String) r7
        L_0x003e:
            r4.add(r7)
            java.lang.String r7 = "?"
            r5.append(r7)
            int r7 = r3 + -1
            if (r6 >= r7) goto L_0x004f
            java.lang.String r7 = ","
            r5.append(r7)
        L_0x004f:
            int r6 = r6 + 1
            goto L_0x001e
        L_0x0052:
            r1 = 3
            java.lang.String r3 = "RecipAlternates"
            boolean r1 = android.util.Log.isLoggable(r3, r1)
            if (r1 == 0) goto L_0x006f
            java.lang.String r1 = "Doing reverse lookup for "
            java.lang.StringBuilder r1 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r1)
            java.lang.String r6 = r4.toString()
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r3, r1)
        L_0x006f:
            int r1 = r4.size()
            java.lang.String[] r15 = new java.lang.String[r1]
            r4.toArray(r15)
            r1 = 0
            boolean r3 = com.android.p032ex.chips.C0688m.m1070g(r21)     // Catch:{ all -> 0x01df }
            if (r3 == 0) goto L_0x00b6
            android.content.ContentResolver r11 = r21.getContentResolver()     // Catch:{ all -> 0x01df }
            android.net.Uri r12 = r9.getContentUri()     // Catch:{ all -> 0x01df }
            java.lang.String[] r13 = r9.getProjection()     // Catch:{ all -> 0x01df }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01df }
            r3.<init>()     // Catch:{ all -> 0x01df }
            java.lang.String[] r6 = r9.getProjection()     // Catch:{ all -> 0x01df }
            r7 = 1
            r6 = r6[r7]     // Catch:{ all -> 0x01df }
            r3.append(r6)     // Catch:{ all -> 0x01df }
            java.lang.String r6 = " IN ("
            r3.append(r6)     // Catch:{ all -> 0x01df }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x01df }
            r3.append(r5)     // Catch:{ all -> 0x01df }
            java.lang.String r5 = ")"
            r3.append(r5)     // Catch:{ all -> 0x01df }
            java.lang.String r14 = r3.toString()     // Catch:{ all -> 0x01df }
            r16 = 0
            android.database.Cursor r3 = r11.query(r12, r13, r14, r15, r16)     // Catch:{ all -> 0x01df }
            goto L_0x00b7
        L_0x00b6:
            r3 = r1
        L_0x00b7:
            java.util.HashMap r5 = m1042a((android.database.Cursor) r3, (java.lang.Long) r1)     // Catch:{ all -> 0x01dc }
            r2.mo5444a((java.util.Map) r5)     // Catch:{ all -> 0x01dc }
            if (r3 == 0) goto L_0x00c3
            r3.close()
        L_0x00c3:
            java.util.HashSet r11 = new java.util.HashSet
            r11.<init>()
            int r3 = r5.size()
            int r6 = r4.size()
            if (r3 >= r6) goto L_0x01af
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.Iterator r4 = r4.iterator()
        L_0x00db:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x00f1
            java.lang.Object r6 = r4.next()
            java.lang.String r6 = (java.lang.String) r6
            boolean r7 = r5.containsKey(r6)
            if (r7 != 0) goto L_0x00db
            r3.add(r6)
            goto L_0x00db
        L_0x00f1:
            r11.addAll(r3)
            boolean r4 = com.android.p032ex.chips.C0688m.m1070g(r21)     // Catch:{ all -> 0x01a8 }
            if (r4 == 0) goto L_0x010c
            android.content.ContentResolver r12 = r21.getContentResolver()     // Catch:{ all -> 0x01a8 }
            android.net.Uri r13 = com.android.p032ex.chips.C0676g.URI     // Catch:{ all -> 0x01a8 }
            java.lang.String[] r14 = com.android.p032ex.chips.C0676g.f793Wu     // Catch:{ all -> 0x01a8 }
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r4 = r12.query(r13, r14, r15, r16, r17)     // Catch:{ all -> 0x01a8 }
            goto L_0x010d
        L_0x010c:
            r4 = r1
        L_0x010d:
            if (r4 != 0) goto L_0x0116
            if (r4 == 0) goto L_0x01af
            r4.close()
            goto L_0x01af
        L_0x0116:
            r12 = r21
            r13 = r24
            java.util.List r14 = com.android.p032ex.chips.C0684k.setupOtherDirectories(r12, r4, r13)     // Catch:{ all -> 0x01a5 }
            r4.close()
            if (r14 == 0) goto L_0x01af
            java.util.Iterator r15 = r3.iterator()
        L_0x0127:
            r3 = r1
        L_0x0128:
            boolean r4 = r15.hasNext()
            if (r4 == 0) goto L_0x01af
            java.lang.Object r4 = r15.next()
            r16 = r4
            java.lang.String r16 = (java.lang.String) r16
            r17 = r3
            r8 = r10
        L_0x0139:
            int r3 = r14.size()
            if (r8 >= r3) goto L_0x01a2
            java.lang.Object r3 = r14.get(r8)
            com.android.ex.chips.h r3 = (com.android.p032ex.chips.C0678h) r3
            long r6 = r3.f794Xu
            r4 = 1
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x019b }
            r3 = r16
            r18 = r6
            r6 = r24
            r7 = r21
            r20 = r8
            r8 = r9
            android.database.Cursor r3 = m1040a(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x019b }
            if (r3 == 0) goto L_0x018e
            int r4 = r3.getCount()     // Catch:{ all -> 0x018a }
            if (r4 == 0) goto L_0x018e
            java.lang.Long r4 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x018a }
            java.util.HashMap r4 = m1042a((android.database.Cursor) r3, (java.lang.Long) r4)     // Catch:{ all -> 0x018a }
            java.util.Set r5 = r4.keySet()     // Catch:{ all -> 0x018a }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x018a }
        L_0x0173:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x018a }
            if (r6 == 0) goto L_0x0183
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x018a }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x018a }
            r11.remove(r6)     // Catch:{ all -> 0x018a }
            goto L_0x0173
        L_0x0183:
            r2.mo5444a((java.util.Map) r4)     // Catch:{ all -> 0x018a }
            r3.close()
            goto L_0x0127
        L_0x018a:
            r0 = move-exception
            r17 = r3
            goto L_0x019c
        L_0x018e:
            if (r3 == 0) goto L_0x0196
            r3.close()
            r17 = r1
            goto L_0x0198
        L_0x0196:
            r17 = r3
        L_0x0198:
            int r8 = r20 + 1
            goto L_0x0139
        L_0x019b:
            r0 = move-exception
        L_0x019c:
            if (r17 == 0) goto L_0x01a1
            r17.close()
        L_0x01a1:
            throw r0
        L_0x01a2:
            r3 = r17
            goto L_0x0128
        L_0x01a5:
            r0 = move-exception
            r1 = r4
            goto L_0x01a9
        L_0x01a8:
            r0 = move-exception
        L_0x01a9:
            if (r1 == 0) goto L_0x01ae
            r1.close()
        L_0x01ae:
            throw r0
        L_0x01af:
            if (r0 == 0) goto L_0x01d8
            java.util.Map r0 = r0.getMatchingRecipients(r11)
            if (r0 == 0) goto L_0x01d8
            int r1 = r0.size()
            if (r1 <= 0) goto L_0x01d8
            r2.mo5444a((java.util.Map) r0)
            java.util.Set r0 = r0.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x01c8:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x01d8
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            r11.remove(r1)
            goto L_0x01c8
        L_0x01d8:
            r2.mo5445a((java.util.Set) r11)
            return
        L_0x01dc:
            r0 = move-exception
            r1 = r3
            goto L_0x01e0
        L_0x01df:
            r0 = move-exception
        L_0x01e0:
            if (r1 == 0) goto L_0x01e5
            r1.close()
        L_0x01e5:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.p032ex.chips.C0646N.m1043a(android.content.Context, com.android.ex.chips.k, java.util.ArrayList, android.accounts.Account, com.android.ex.chips.M):void");
    }

    /* renamed from: B */
    public C0699ra mo5446B(int i) {
        Cursor cursor = getCursor();
        cursor.moveToPosition(i);
        return C0699ra.m1082b(cursor.getString(0), cursor.getInt(7), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getLong(4), this.f764ql, cursor.getLong(5), cursor.getString(6), true, cursor.getString(8));
    }

    public void bindView(View view, Context context, Cursor cursor) {
        int position = cursor.getPosition();
        View view2 = view;
        this.mDropdownChipLayouter.bindView(view2, (ViewGroup) null, mo5446B(position), position, DropdownChipLayouter$AdapterType.RECIPIENT_ALTERNATES, (String) null, this.f761nl);
    }

    public long getItemId(int i) {
        Cursor cursor = getCursor();
        if (!cursor.moveToPosition(i)) {
            return -1;
        }
        cursor.getLong(5);
        return -1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Cursor cursor = getCursor();
        cursor.moveToPosition(i);
        if (view == null) {
            view = this.mDropdownChipLayouter.newView(DropdownChipLayouter$AdapterType.RECIPIENT_ALTERNATES);
        }
        if (cursor.getLong(5) == this.f760ga) {
            this.f762ol = i;
            C0644L l = this.f763pl;
            if (l != null) {
                l.onCheckedItemChanged(this.f762ol);
            }
        }
        view.getContext();
        int position = cursor.getPosition();
        View view2 = view;
        this.mDropdownChipLayouter.bindView(view2, (ViewGroup) null, mo5446B(position), position, DropdownChipLayouter$AdapterType.RECIPIENT_ALTERNATES, (String) null, this.f761nl);
        return view;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.mDropdownChipLayouter.newView(DropdownChipLayouter$AdapterType.RECIPIENT_ALTERNATES);
    }

    /* renamed from: a */
    private static HashMap m1042a(Cursor cursor, Long l) {
        Cursor cursor2 = cursor;
        HashMap hashMap = new HashMap();
        if (cursor2 == null || !cursor.moveToFirst()) {
            return hashMap;
        }
        do {
            String string = cursor2.getString(1);
            hashMap.put(string, m1041a((C0699ra) hashMap.get(string), C0699ra.m1082b(cursor2.getString(0), cursor2.getInt(7), cursor2.getString(1), cursor2.getInt(2), cursor2.getString(3), cursor2.getLong(4), l, cursor2.getLong(5), cursor2.getString(6), true, cursor2.getString(8))));
            if (Log.isLoggable("RecipAlternates", 3)) {
                Log.d("RecipAlternates", "Received reverse look up information for " + string + " RESULTS:  NAME : " + cursor2.getString(0) + " CONTACT ID : " + cursor2.getLong(4) + " ADDRESS :" + cursor2.getString(1));
            }
        } while (cursor.moveToNext());
        return hashMap;
    }

    /* renamed from: a */
    static C0699ra m1041a(C0699ra raVar, C0699ra raVar2) {
        if (raVar2 == null) {
            return raVar;
        }
        if (raVar == null) {
            return raVar2;
        }
        if (!TextUtils.isEmpty(raVar.getDisplayName()) && TextUtils.isEmpty(raVar2.getDisplayName())) {
            return raVar;
        }
        if (!TextUtils.isEmpty(raVar2.getDisplayName()) && TextUtils.isEmpty(raVar.getDisplayName())) {
            return raVar2;
        }
        if (!TextUtils.equals(raVar.getDisplayName(), raVar.getDestination()) && TextUtils.equals(raVar2.getDisplayName(), raVar2.getDestination())) {
            return raVar;
        }
        if (!TextUtils.equals(raVar2.getDisplayName(), raVar2.getDestination()) && TextUtils.equals(raVar.getDisplayName(), raVar.getDestination())) {
            return raVar2;
        }
        if ((raVar.mo5666yd() != null || raVar.mo5665xd() != null) && raVar2.mo5666yd() == null && raVar2.mo5665xd() == null) {
            return raVar;
        }
        if (!(raVar2.mo5666yd() == null && raVar2.mo5665xd() == null) && raVar.mo5666yd() == null && raVar.mo5665xd() == null) {
        }
        return raVar2;
    }

    /* renamed from: a */
    private static Cursor m1040a(CharSequence charSequence, int i, Long l, Account account, Context context, C0634B b) {
        if (C0688m.m1070g(context)) {
            Uri.Builder appendQueryParameter = b.mo5440sd().buildUpon().appendPath(charSequence.toString()).appendQueryParameter("limit", String.valueOf(i + 5));
            if (l != null) {
                appendQueryParameter.appendQueryParameter("directory", String.valueOf(l));
            }
            if (account != null) {
                appendQueryParameter.appendQueryParameter("name_for_primary_account", account.name);
                appendQueryParameter.appendQueryParameter("type_for_primary_account", account.type);
            }
            return context.getContentResolver().query(appendQueryParameter.build(), b.getProjection(), (String) null, (String[]) null, (String) null);
        } else if (!Log.isLoggable("RecipAlternates", 3)) {
            return null;
        } else {
            Log.d("RecipAlternates", "Not doing query because we don't have required permissions.");
            return null;
        }
    }
}
