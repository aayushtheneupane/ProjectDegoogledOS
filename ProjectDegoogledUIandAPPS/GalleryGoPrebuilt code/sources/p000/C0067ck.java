package p000;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* renamed from: ck */
/* compiled from: PG */
public final class C0067ck {

    /* renamed from: a */
    private final String f4532a;

    /* renamed from: b */
    private final Map f4533b;

    /* renamed from: c */
    private final Set f4534c;

    /* renamed from: d */
    private final Set f4535d;

    public C0067ck(String str, Map map, Set set, Set set2) {
        this.f4532a = str;
        this.f4533b = Collections.unmodifiableMap(map);
        this.f4534c = Collections.unmodifiableSet(set);
        this.f4535d = set2 != null ? Collections.unmodifiableSet(set2) : null;
    }

    public final boolean equals(Object obj) {
        Set set;
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0067ck) {
            C0067ck ckVar = (C0067ck) obj;
            if (this.f4532a.equals(ckVar.f4532a)) {
                Map map = this.f4533b;
                if (map == null ? ckVar.f4533b != null : !map.equals(ckVar.f4533b)) {
                    return false;
                }
                Set set2 = this.f4534c;
                if (set2 == null ? ckVar.f4534c != null : !set2.equals(ckVar.f4534c)) {
                    return false;
                }
                Set set3 = this.f4535d;
                if (set3 == null || (set = ckVar.f4535d) == null) {
                    return true;
                }
                return set3.equals(set);
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.f4532a.hashCode() * 31;
        Map map = this.f4533b;
        int i = 0;
        int hashCode2 = (hashCode + (map != null ? map.hashCode() : 0)) * 31;
        Set set = this.f4534c;
        if (set != null) {
            i = set.hashCode();
        }
        return hashCode2 + i;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0268, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x026c, code lost:
        throw r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x027b A[EDGE_INSN: B:105:0x027b->B:81:0x027b ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x025c A[Catch:{ all -> 0x0268, all -> 0x0287 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p000.C0067ck m4436a(p000.C0028az r29, java.lang.String r30) {
        /*
            r0 = r29
            r1 = r30
            java.lang.String r2 = "seq"
            java.lang.String r3 = "id"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "PRAGMA table_info(`"
            r4.append(r5)
            r4.append(r1)
            java.lang.String r5 = "`)"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.database.Cursor r4 = r0.mo1733b(r4)
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            int r7 = r4.getColumnCount()     // Catch:{ all -> 0x0291 }
            java.lang.String r8 = "name"
            if (r7 <= 0) goto L_0x007c
            int r7 = r4.getColumnIndex(r8)     // Catch:{ all -> 0x0291 }
            java.lang.String r11 = "type"
            int r11 = r4.getColumnIndex(r11)     // Catch:{ all -> 0x0291 }
            java.lang.String r12 = "notnull"
            int r12 = r4.getColumnIndex(r12)     // Catch:{ all -> 0x0291 }
            java.lang.String r13 = "pk"
            int r13 = r4.getColumnIndex(r13)     // Catch:{ all -> 0x0291 }
            java.lang.String r14 = "dflt_value"
            int r14 = r4.getColumnIndex(r14)     // Catch:{ all -> 0x0291 }
        L_0x004b:
            boolean r15 = r4.moveToNext()     // Catch:{ all -> 0x0291 }
            if (r15 == 0) goto L_0x007c
            java.lang.String r15 = r4.getString(r7)     // Catch:{ all -> 0x0291 }
            java.lang.String r18 = r4.getString(r11)     // Catch:{ all -> 0x0291 }
            int r16 = r4.getInt(r12)     // Catch:{ all -> 0x0291 }
            if (r16 == 0) goto L_0x0062
            r19 = 1
            goto L_0x0065
        L_0x0062:
            r19 = 0
        L_0x0065:
            int r20 = r4.getInt(r13)     // Catch:{ all -> 0x0291 }
            java.lang.String r21 = r4.getString(r14)     // Catch:{ all -> 0x0291 }
            cg r9 = new cg     // Catch:{ all -> 0x0291 }
            r22 = 2
            r16 = r9
            r17 = r15
            r16.<init>(r17, r18, r19, r20, r21, r22)     // Catch:{ all -> 0x0291 }
            r6.put(r15, r9)     // Catch:{ all -> 0x0291 }
            goto L_0x004b
        L_0x007c:
            r4.close()
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "PRAGMA foreign_key_list(`"
            r7.append(r9)
            r7.append(r1)
            r7.append(r5)
            java.lang.String r7 = r7.toString()
            android.database.Cursor r7 = r0.mo1733b(r7)
            int r9 = r7.getColumnIndex(r3)     // Catch:{ all -> 0x028c }
            int r11 = r7.getColumnIndex(r2)     // Catch:{ all -> 0x028c }
            java.lang.String r12 = "table"
            int r12 = r7.getColumnIndex(r12)     // Catch:{ all -> 0x028c }
            java.lang.String r13 = "on_delete"
            int r13 = r7.getColumnIndex(r13)     // Catch:{ all -> 0x028c }
            java.lang.String r14 = "on_update"
            int r14 = r7.getColumnIndex(r14)     // Catch:{ all -> 0x028c }
            int r3 = r7.getColumnIndex(r3)     // Catch:{ all -> 0x028c }
            int r2 = r7.getColumnIndex(r2)     // Catch:{ all -> 0x028c }
            java.lang.String r15 = "from"
            int r15 = r7.getColumnIndex(r15)     // Catch:{ all -> 0x028c }
            java.lang.String r10 = "to"
            int r10 = r7.getColumnIndex(r10)     // Catch:{ all -> 0x028c }
            r17 = r6
            int r6 = r7.getCount()     // Catch:{ all -> 0x028c }
            r18 = r8
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x028c }
            r8.<init>()     // Catch:{ all -> 0x028c }
            r0 = 0
        L_0x00d8:
            if (r0 >= r6) goto L_0x010c
            r7.moveToPosition(r0)     // Catch:{ all -> 0x028c }
            r19 = r6
            ci r6 = new ci     // Catch:{ all -> 0x028c }
            r20 = r5
            int r5 = r7.getInt(r3)     // Catch:{ all -> 0x028c }
            r21 = r3
            int r3 = r7.getInt(r2)     // Catch:{ all -> 0x028c }
            r22 = r2
            java.lang.String r2 = r7.getString(r15)     // Catch:{ all -> 0x028c }
            r23 = r15
            java.lang.String r15 = r7.getString(r10)     // Catch:{ all -> 0x028c }
            r6.<init>(r5, r3, r2, r15)     // Catch:{ all -> 0x028c }
            r8.add(r6)     // Catch:{ all -> 0x028c }
            int r0 = r0 + 1
            r6 = r19
            r5 = r20
            r3 = r21
            r2 = r22
            r15 = r23
            goto L_0x00d8
        L_0x010c:
            r20 = r5
            java.util.Collections.sort(r8)     // Catch:{ all -> 0x028c }
            int r0 = r7.getCount()     // Catch:{ all -> 0x028c }
            r2 = 0
        L_0x0116:
            if (r2 >= r0) goto L_0x0179
            r7.moveToPosition(r2)     // Catch:{ all -> 0x028c }
            int r3 = r7.getInt(r11)     // Catch:{ all -> 0x028c }
            if (r3 != 0) goto L_0x0172
            int r3 = r7.getInt(r9)     // Catch:{ all -> 0x028c }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x028c }
            r5.<init>()     // Catch:{ all -> 0x028c }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x028c }
            r6.<init>()     // Catch:{ all -> 0x028c }
            java.util.Iterator r10 = r8.iterator()     // Catch:{ all -> 0x028c }
        L_0x0133:
            boolean r15 = r10.hasNext()     // Catch:{ all -> 0x028c }
            if (r15 != 0) goto L_0x0156
            ch r3 = new ch     // Catch:{ all -> 0x028c }
            java.lang.String r24 = r7.getString(r12)     // Catch:{ all -> 0x028c }
            java.lang.String r25 = r7.getString(r13)     // Catch:{ all -> 0x028c }
            java.lang.String r26 = r7.getString(r14)     // Catch:{ all -> 0x028c }
            r23 = r3
            r27 = r5
            r28 = r6
            r23.<init>(r24, r25, r26, r27, r28)     // Catch:{ all -> 0x028c }
            r4.add(r3)     // Catch:{ all -> 0x028c }
            r19 = r0
            goto L_0x0174
        L_0x0156:
            java.lang.Object r15 = r10.next()     // Catch:{ all -> 0x028c }
            ci r15 = (p000.C0065ci) r15     // Catch:{ all -> 0x028c }
            r19 = r0
            int r0 = r15.f4446a     // Catch:{ all -> 0x028c }
            if (r0 != r3) goto L_0x016f
            java.lang.String r0 = r15.f4447b     // Catch:{ all -> 0x028c }
            r5.add(r0)     // Catch:{ all -> 0x028c }
            java.lang.String r0 = r15.f4448c     // Catch:{ all -> 0x028c }
            r6.add(r0)     // Catch:{ all -> 0x028c }
            r0 = r19
            goto L_0x0133
        L_0x016f:
            r0 = r19
            goto L_0x0133
        L_0x0172:
            r19 = r0
        L_0x0174:
            int r2 = r2 + 1
            r0 = r19
            goto L_0x0116
        L_0x0179:
            r7.close()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "PRAGMA index_list(`"
            r0.append(r2)
            r0.append(r1)
            r2 = r20
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r3 = r29
            android.database.Cursor r5 = r3.mo1733b(r0)
            r0 = r18
            int r6 = r5.getColumnIndex(r0)     // Catch:{ all -> 0x0287 }
            java.lang.String r7 = "origin"
            int r7 = r5.getColumnIndex(r7)     // Catch:{ all -> 0x0287 }
            java.lang.String r8 = "unique"
            int r8 = r5.getColumnIndex(r8)     // Catch:{ all -> 0x0287 }
            r10 = -1
            if (r6 != r10) goto L_0x01af
            goto L_0x027b
        L_0x01af:
            if (r7 == r10) goto L_0x027b
            if (r8 == r10) goto L_0x027b
            java.util.HashSet r11 = new java.util.HashSet     // Catch:{ all -> 0x0287 }
            r11.<init>()     // Catch:{ all -> 0x0287 }
        L_0x01b8:
            boolean r12 = r5.moveToNext()     // Catch:{ all -> 0x0287 }
            if (r12 == 0) goto L_0x0276
            java.lang.String r12 = "c"
            java.lang.String r13 = r5.getString(r7)     // Catch:{ all -> 0x0287 }
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x0287 }
            if (r12 == 0) goto L_0x026d
            java.lang.String r12 = r5.getString(r6)     // Catch:{ all -> 0x0287 }
            int r13 = r5.getInt(r8)     // Catch:{ all -> 0x0287 }
            r14 = 1
            if (r13 != r14) goto L_0x01d7
            r13 = 1
            goto L_0x01d9
        L_0x01d7:
            r13 = 0
        L_0x01d9:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x0287 }
            r15.<init>()     // Catch:{ all -> 0x0287 }
            java.lang.String r9 = "PRAGMA index_xinfo(`"
            r15.append(r9)     // Catch:{ all -> 0x0287 }
            r15.append(r12)     // Catch:{ all -> 0x0287 }
            r15.append(r2)     // Catch:{ all -> 0x0287 }
            java.lang.String r9 = r15.toString()     // Catch:{ all -> 0x0287 }
            android.database.Cursor r9 = r3.mo1733b(r9)     // Catch:{ all -> 0x0287 }
            java.lang.String r15 = "seqno"
            int r15 = r9.getColumnIndex(r15)     // Catch:{ all -> 0x0268 }
            java.lang.String r14 = "cid"
            int r14 = r9.getColumnIndex(r14)     // Catch:{ all -> 0x0268 }
            int r10 = r9.getColumnIndex(r0)     // Catch:{ all -> 0x0268 }
            r20 = r0
            r0 = -1
            if (r15 != r0) goto L_0x0209
            r22 = r2
            goto L_0x0256
        L_0x0209:
            if (r14 == r0) goto L_0x0254
            if (r10 == r0) goto L_0x0254
            java.util.TreeMap r0 = new java.util.TreeMap     // Catch:{ all -> 0x0268 }
            r0.<init>()     // Catch:{ all -> 0x0268 }
        L_0x0212:
            boolean r21 = r9.moveToNext()     // Catch:{ all -> 0x0268 }
            if (r21 != 0) goto L_0x0233
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x0268 }
            int r14 = r0.size()     // Catch:{ all -> 0x0268 }
            r10.<init>(r14)     // Catch:{ all -> 0x0268 }
            java.util.Collection r0 = r0.values()     // Catch:{ all -> 0x0268 }
            r10.addAll(r0)     // Catch:{ all -> 0x0268 }
            cj r0 = new cj     // Catch:{ all -> 0x0268 }
            r0.<init>(r12, r13, r10)     // Catch:{ all -> 0x0268 }
            r9.close()     // Catch:{ all -> 0x0287 }
            r22 = r2
            goto L_0x025a
        L_0x0233:
            int r21 = r9.getInt(r14)     // Catch:{ all -> 0x0268 }
            if (r21 < 0) goto L_0x024f
            int r21 = r9.getInt(r15)     // Catch:{ all -> 0x0268 }
            r22 = r2
            java.lang.String r2 = r9.getString(r10)     // Catch:{ all -> 0x0268 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r21)     // Catch:{ all -> 0x0268 }
            r0.put(r3, r2)     // Catch:{ all -> 0x0268 }
            r3 = r29
            r2 = r22
            goto L_0x0212
        L_0x024f:
            r22 = r2
            r3 = r29
            goto L_0x0212
        L_0x0254:
            r22 = r2
        L_0x0256:
            r9.close()     // Catch:{ all -> 0x0287 }
            r0 = 0
        L_0x025a:
            if (r0 == 0) goto L_0x027b
            r11.add(r0)     // Catch:{ all -> 0x0287 }
            r3 = r29
            r0 = r20
            r2 = r22
            r10 = -1
            goto L_0x01b8
        L_0x0268:
            r0 = move-exception
            r9.close()     // Catch:{ all -> 0x0287 }
            throw r0     // Catch:{ all -> 0x0287 }
        L_0x026d:
            r20 = r0
            r22 = r2
            r3 = r29
            r10 = -1
            goto L_0x01b8
        L_0x0276:
            r5.close()
            r9 = r11
            goto L_0x027f
        L_0x027b:
            r5.close()
            r9 = 0
        L_0x027f:
            ck r0 = new ck
            r2 = r17
            r0.<init>(r1, r2, r4, r9)
            return r0
        L_0x0287:
            r0 = move-exception
            r5.close()
            throw r0
        L_0x028c:
            r0 = move-exception
            r7.close()
            throw r0
        L_0x0291:
            r0 = move-exception
            r4.close()
            goto L_0x0297
        L_0x0296:
            throw r0
        L_0x0297:
            goto L_0x0296
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0067ck.m4436a(az, java.lang.String):ck");
    }

    public final String toString() {
        return "TableInfo{name='" + this.f4532a + "', columns=" + this.f4533b + ", foreignKeys=" + this.f4534c + ", indices=" + this.f4535d + '}';
    }
}
