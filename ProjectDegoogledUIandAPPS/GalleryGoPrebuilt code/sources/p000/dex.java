package p000;

/* renamed from: dex */
/* compiled from: PG */
final /* synthetic */ class dex implements hpr {

    /* renamed from: a */
    private final cjr f6418a;

    public dex(cjr cjr) {
        this.f6418a = cjr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x014c A[Catch:{ IllegalStateException -> 0x0179 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x016b A[LOOP:0: B:3:0x0077->B:41:0x016b, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0195 A[EDGE_INSN: B:48:0x0195->B:47:0x0195 ?: BREAK  , SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object mo1484a(java.lang.Object r28) {
        /*
            r27 = this;
            r1 = r27
            cjr r0 = r1.f6418a
            r2 = r28
            android.database.Cursor r2 = (android.database.Cursor) r2
            int r3 = p000.dfj.f6440a
            boolean r0 = r0.mo3175a()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            boolean r4 = r2.moveToFirst()
            if (r4 == 0) goto L_0x0195
            java.lang.String r4 = "_id"
            int r4 = r2.getColumnIndexOrThrow(r4)
            java.lang.String r5 = "mime_type"
            int r5 = r2.getColumnIndexOrThrow(r5)
            java.lang.String r6 = "media_type"
            int r6 = r2.getColumnIndexOrThrow(r6)
            java.lang.String r7 = "height"
            int r7 = r2.getColumnIndexOrThrow(r7)
            java.lang.String r8 = "width"
            int r8 = r2.getColumnIndexOrThrow(r8)
            java.lang.String r9 = "orientation"
            int r9 = r2.getColumnIndexOrThrow(r9)
            java.lang.String r10 = "_size"
            int r10 = r2.getColumnIndexOrThrow(r10)
            java.lang.String r11 = "bucket_id"
            int r11 = r2.getColumnIndexOrThrow(r11)
            java.lang.String r12 = "bucket_display_name"
            int r12 = r2.getColumnIndexOrThrow(r12)
            java.lang.String r13 = "_data"
            int r13 = r2.getColumnIndexOrThrow(r13)
            java.lang.String r14 = "duration"
            int r14 = r2.getColumnIndexOrThrow(r14)
            java.lang.String r15 = "datetaken"
            int r15 = r2.getColumnIndexOrThrow(r15)
            java.lang.String r1 = "date_added"
            int r1 = r2.getColumnIndexOrThrow(r1)
            r28 = r3
            java.lang.String r3 = "date_modified"
            int r3 = r2.getColumnIndexOrThrow(r3)
            r16 = r0
            java.lang.String r0 = "is_pending"
            int r0 = r2.getColumnIndex(r0)
        L_0x0077:
            r17 = r1
            cyf r1 = p000.cyg.m5687R()     // Catch:{ IllegalStateException -> 0x0179 }
            r18 = r14
            r19 = r15
            long r14 = r2.getLong(r4)     // Catch:{ IllegalStateException -> 0x0179 }
            r1.mo3976e((long) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            java.lang.String r14 = r2.getString(r5)     // Catch:{ IllegalStateException -> 0x0179 }
            java.lang.String r14 = p000.dfj.m6038a((java.lang.String) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            r1.mo3977e((java.lang.String) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            int r14 = r2.getInt(r6)     // Catch:{ IllegalStateException -> 0x0179 }
            r1.mo3986h((int) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            int r14 = r2.getInt(r7)     // Catch:{ IllegalStateException -> 0x0179 }
            r1.mo3955a((int) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            int r14 = r2.getInt(r8)     // Catch:{ IllegalStateException -> 0x0179 }
            r1.mo3971d((int) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            int r14 = r2.getInt(r9)     // Catch:{ IllegalStateException -> 0x0179 }
            r1.mo3960b((int) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            long r14 = r2.getLong(r10)     // Catch:{ IllegalStateException -> 0x0179 }
            r1.mo3972d((long) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            java.lang.String r14 = r2.getString(r11)     // Catch:{ IllegalStateException -> 0x0179 }
            if (r14 == 0) goto L_0x00bd
            goto L_0x00c7
        L_0x00bd:
            java.lang.String r14 = ""
            int r14 = p000.cya.m5637b((java.lang.String) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            java.lang.String r14 = java.lang.Integer.toString(r14)     // Catch:{ IllegalStateException -> 0x0179 }
        L_0x00c7:
            r1.mo3957a((java.lang.String) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            java.lang.String r14 = r2.getString(r12)     // Catch:{ IllegalStateException -> 0x0179 }
            java.lang.String r14 = p000.dfj.m6038a((java.lang.String) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            r1.mo3969c((java.lang.String) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            java.lang.String r14 = r2.getString(r13)     // Catch:{ IllegalStateException -> 0x0179 }
            java.lang.String r14 = p000.dfj.m6038a((java.lang.String) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            r1.mo3962b((java.lang.String) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            r14 = 0
            r1.mo3979f((int) r14)     // Catch:{ IllegalStateException -> 0x0179 }
            r14 = r18
            int r15 = r2.getInt(r14)     // Catch:{ IllegalStateException -> 0x0179 }
            if (r15 == 0) goto L_0x00f5
            r18 = r4
            r20 = r5
            long r4 = (long) r15     // Catch:{ IllegalStateException -> 0x0179 }
            r1.mo3956a((long) r4)     // Catch:{ IllegalStateException -> 0x0179 }
            goto L_0x00f9
        L_0x00f5:
            r18 = r4
            r20 = r5
        L_0x00f9:
            long r4 = r2.getLong(r3)     // Catch:{ IllegalStateException -> 0x0179 }
            r21 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 * r21
            r1.mo3980f((long) r4)     // Catch:{ IllegalStateException -> 0x0179 }
            r15 = r19
            boolean r19 = r2.isNull(r15)     // Catch:{ IllegalStateException -> 0x0179 }
            r23 = 0
            if (r19 == 0) goto L_0x010f
            goto L_0x011e
        L_0x010f:
            long r25 = r2.getLong(r15)     // Catch:{ IllegalStateException -> 0x0179 }
            int r19 = (r25 > r23 ? 1 : (r25 == r23 ? 0 : -1))
            if (r19 <= 0) goto L_0x011e
            r19 = r3
            r3 = r17
            r4 = r25
            goto L_0x013b
        L_0x011e:
            r19 = r3
            r3 = r17
            boolean r17 = r2.isNull(r3)     // Catch:{ IllegalStateException -> 0x0179 }
            if (r17 != 0) goto L_0x0133
            long r25 = r2.getLong(r3)     // Catch:{ IllegalStateException -> 0x0179 }
            int r17 = (r25 > r23 ? 1 : (r25 == r23 ? 0 : -1))
            if (r17 <= 0) goto L_0x0133
            long r4 = r25 * r21
            goto L_0x013b
        L_0x0133:
            int r17 = (r4 > r23 ? 1 : (r4 == r23 ? 0 : -1))
            if (r17 > 0) goto L_0x013a
            r4 = r23
            goto L_0x013b
        L_0x013a:
        L_0x013b:
            r1.mo3968c((long) r4)     // Catch:{ IllegalStateException -> 0x0179 }
            r17 = r3
            java.util.TimeZone r3 = p000.ehe.f8282b     // Catch:{ IllegalStateException -> 0x0179 }
            int r3 = r3.getOffset(r4)     // Catch:{ IllegalStateException -> 0x0179 }
            long r3 = (long) r3     // Catch:{ IllegalStateException -> 0x0179 }
            r1.mo3961b((long) r3)     // Catch:{ IllegalStateException -> 0x0179 }
            if (r16 == 0) goto L_0x015b
            if (r0 >= 0) goto L_0x014f
            goto L_0x015b
        L_0x014f:
            int r3 = r2.getInt(r0)     // Catch:{ IllegalStateException -> 0x0179 }
            if (r3 == 0) goto L_0x0157
            r3 = 1
            goto L_0x0158
        L_0x0157:
            r3 = 0
        L_0x0158:
            r1.mo3958a((boolean) r3)     // Catch:{ IllegalStateException -> 0x0179 }
        L_0x015b:
            cyg r1 = r1.mo3966c()     // Catch:{ IllegalStateException -> 0x0179 }
            r3 = r28
            r3.add(r1)     // Catch:{ IllegalStateException -> 0x0177 }
            boolean r1 = r2.moveToNext()     // Catch:{ IllegalStateException -> 0x0177 }
            if (r1 != 0) goto L_0x016b
            goto L_0x0195
        L_0x016b:
            r28 = r3
            r1 = r17
            r4 = r18
            r3 = r19
            r5 = r20
            goto L_0x0077
        L_0x0177:
            r0 = move-exception
            goto L_0x017c
        L_0x0179:
            r0 = move-exception
            r3 = r28
        L_0x017c:
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            ifs r2 = new ifs
            int r3 = r3.size()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2.<init>(r3)
            r3 = 0
            r1[r3] = r2
            java.lang.String r2 = "MediaStoreSyncerModule failed to load all system media, already loaded %s"
            p000.cwn.m5515b((java.lang.Throwable) r0, (java.lang.String) r2, (java.lang.Object[]) r1)
            throw r0
        L_0x0195:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dex.mo1484a(java.lang.Object):java.lang.Object");
    }
}
