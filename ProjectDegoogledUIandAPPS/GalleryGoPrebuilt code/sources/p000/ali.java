package p000;

/* renamed from: ali */
/* compiled from: PG */
final class ali extends C0047br {
    public ali(C0053bx bxVar) {
        super(bxVar);
    }

    /* renamed from: a */
    public final String mo578a() {
        return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`period_start_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x019d A[SYNTHETIC, Splitter:B:75:0x019d] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01ba A[SYNTHETIC, Splitter:B:87:0x01ba] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01d1  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:62:0x0186=Splitter:B:62:0x0186, B:79:0x01a6=Splitter:B:79:0x01a6} */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ void mo579a(p000.C0037bh r17, java.lang.Object r18) {
        /*
            r16 = this;
            r1 = r17
            r0 = r18
            alg r0 = (p000.alg) r0
            java.lang.String r2 = r0.f713b
            r3 = 1
            if (r2 != 0) goto L_0x000f
            r1.mo1920a(r3)
            goto L_0x0013
        L_0x000f:
            r1.mo1922a((int) r3, (java.lang.String) r2)
        L_0x0013:
            int r2 = r0.f728q
            int r2 = p000.ihg.m13050c(r2)
            long r4 = (long) r2
            r2 = 2
            r1.mo1921a((int) r2, (long) r4)
            java.lang.String r4 = r0.f714c
            r5 = 3
            if (r4 != 0) goto L_0x0028
            r1.mo1920a(r5)
            goto L_0x002c
        L_0x0028:
            r1.mo1922a((int) r5, (java.lang.String) r4)
        L_0x002c:
            java.lang.String r4 = r0.f715d
            r6 = 4
            if (r4 != 0) goto L_0x0035
            r1.mo1920a(r6)
            goto L_0x0039
        L_0x0035:
            r1.mo1922a((int) r6, (java.lang.String) r4)
        L_0x0039:
            ahf r4 = r0.f716e
            byte[] r4 = p000.ahf.m483a((p000.ahf) r4)
            r7 = 5
            if (r4 != 0) goto L_0x0046
            r1.mo1920a(r7)
            goto L_0x004a
        L_0x0046:
            r1.mo1923a((int) r7, (byte[]) r4)
        L_0x004a:
            ahf r4 = r0.f717f
            byte[] r4 = p000.ahf.m483a((p000.ahf) r4)
            r7 = 6
            if (r4 != 0) goto L_0x0057
            r1.mo1920a(r7)
            goto L_0x005b
        L_0x0057:
            r1.mo1923a((int) r7, (byte[]) r4)
        L_0x005b:
            r4 = 7
            long r7 = r0.f718g
            r1.mo1921a((int) r4, (long) r7)
            r4 = 8
            long r7 = r0.f719h
            r1.mo1921a((int) r4, (long) r7)
            r4 = 9
            long r7 = r0.f720i
            r1.mo1921a((int) r4, (long) r7)
            r4 = 10
            int r7 = r0.f722k
            long r7 = (long) r7
            r1.mo1921a((int) r4, (long) r7)
            int r4 = r0.f729r
            int r7 = r4 + -1
            if (r4 == 0) goto L_0x01fe
            java.lang.String r9 = " to int"
            java.lang.String r10 = "Could not convert "
            if (r7 == 0) goto L_0x00a8
            if (r7 == r3) goto L_0x00a4
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r10)
            if (r4 == r3) goto L_0x0094
            java.lang.String r2 = "LINEAR"
            goto L_0x0096
        L_0x0094:
            java.lang.String r2 = "EXPONENTIAL"
        L_0x0096:
            r1.append(r2)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x00a4:
            r4 = 1
            goto L_0x00a9
        L_0x00a8:
            r4 = 0
        L_0x00a9:
            r7 = 11
            long r12 = (long) r4
            r1.mo1921a((int) r7, (long) r12)
            r4 = 12
            long r12 = r0.f723l
            r1.mo1921a((int) r4, (long) r12)
            r4 = 13
            long r12 = r0.f724m
            r1.mo1921a((int) r4, (long) r12)
            r4 = 14
            long r12 = r0.f725n
            r1.mo1921a((int) r4, (long) r12)
            r4 = 15
            long r12 = r0.f726o
            r1.mo1921a((int) r4, (long) r12)
            r4 = 16
            boolean r7 = r0.f727p
            long r12 = (long) r7
            r1.mo1921a((int) r4, (long) r12)
            ahb r0 = r0.f721j
            r7 = 22
            r12 = 21
            r13 = 20
            r14 = 19
            r15 = 18
            r11 = 17
            if (r0 == 0) goto L_0x01e0
            int r8 = r0.f482i
            int r4 = r8 + -1
            if (r8 == 0) goto L_0x01dd
            if (r4 == 0) goto L_0x0119
            if (r4 == r3) goto L_0x011b
            if (r4 == r2) goto L_0x0115
            if (r4 == r5) goto L_0x0111
            if (r4 != r6) goto L_0x00f5
            r3 = 4
            goto L_0x011b
        L_0x00f5:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r10)
            java.lang.String r2 = p000.fxk.m9822a((int) r8)
            r1.append(r2)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0111:
            r3 = 3
            goto L_0x011b
        L_0x0115:
            r3 = 2
            goto L_0x011b
        L_0x0119:
            r3 = 0
        L_0x011b:
            long r2 = (long) r3
            r1.mo1921a((int) r11, (long) r2)
            boolean r2 = r0.f475b
            long r2 = (long) r2
            r1.mo1921a((int) r15, (long) r2)
            boolean r2 = r0.f476c
            long r2 = (long) r2
            r1.mo1921a((int) r14, (long) r2)
            boolean r2 = r0.f477d
            long r2 = (long) r2
            r1.mo1921a((int) r13, (long) r2)
            boolean r2 = r0.f478e
            long r2 = (long) r2
            r1.mo1921a((int) r12, (long) r2)
            long r2 = r0.f479f
            r1.mo1921a((int) r7, (long) r2)
            long r2 = r0.f480g
            r4 = 23
            r1.mo1921a((int) r4, (long) r2)
            ahd r0 = r0.f481h
            int r2 = r0.mo464a()
            if (r2 == 0) goto L_0x01cd
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream
            r2.<init>()
            java.io.ObjectOutputStream r3 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0196, all -> 0x0192 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x0196, all -> 0x0192 }
            int r4 = r0.mo464a()     // Catch:{ IOException -> 0x018f, all -> 0x018a }
            r3.writeInt(r4)     // Catch:{ IOException -> 0x018f, all -> 0x018a }
            java.util.Set r0 = r0.f485a     // Catch:{ IOException -> 0x018f, all -> 0x018a }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ IOException -> 0x018f, all -> 0x018a }
        L_0x0162:
            boolean r4 = r0.hasNext()     // Catch:{ IOException -> 0x018f, all -> 0x018a }
            if (r4 == 0) goto L_0x017d
            java.lang.Object r4 = r0.next()     // Catch:{ IOException -> 0x018f, all -> 0x018a }
            ahc r4 = (p000.ahc) r4     // Catch:{ IOException -> 0x018f, all -> 0x018a }
            android.net.Uri r5 = r4.f483a     // Catch:{ IOException -> 0x018f, all -> 0x018a }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x018f, all -> 0x018a }
            r3.writeUTF(r5)     // Catch:{ IOException -> 0x018f, all -> 0x018a }
            boolean r4 = r4.f484b     // Catch:{ IOException -> 0x018f, all -> 0x018a }
            r3.writeBoolean(r4)     // Catch:{ IOException -> 0x018f, all -> 0x018a }
            goto L_0x0162
        L_0x017d:
            r3.close()     // Catch:{ IOException -> 0x0181 }
            goto L_0x0186
        L_0x0181:
            r0 = move-exception
            r3 = r0
            p000.ifn.m12932a(r3)
        L_0x0186:
            r2.close()     // Catch:{ IOException -> 0x01aa }
            goto L_0x01af
        L_0x018a:
            r0 = move-exception
            r1 = r0
            r8 = r3
            goto L_0x01b7
        L_0x018f:
            r0 = move-exception
            r8 = r3
            goto L_0x0198
        L_0x0192:
            r0 = move-exception
            r1 = r0
            r8 = 0
            goto L_0x01b7
        L_0x0196:
            r0 = move-exception
            r8 = 0
        L_0x0198:
            p000.ifn.m12932a(r0)     // Catch:{ all -> 0x01b4 }
            if (r8 == 0) goto L_0x01a6
            r8.close()     // Catch:{ IOException -> 0x01a1 }
            goto L_0x01a6
        L_0x01a1:
            r0 = move-exception
            r3 = r0
            p000.ifn.m12932a(r3)
        L_0x01a6:
            r2.close()     // Catch:{ IOException -> 0x01aa }
            goto L_0x01af
        L_0x01aa:
            r0 = move-exception
            r3 = r0
            p000.ifn.m12932a(r3)
        L_0x01af:
            byte[] r8 = r2.toByteArray()
            goto L_0x01cf
        L_0x01b4:
            r0 = move-exception
            r1 = r0
        L_0x01b7:
            if (r8 != 0) goto L_0x01ba
            goto L_0x01c3
        L_0x01ba:
            r8.close()     // Catch:{ IOException -> 0x01be }
            goto L_0x01c3
        L_0x01be:
            r0 = move-exception
            r3 = r0
            p000.ifn.m12932a(r3)
        L_0x01c3:
            r2.close()     // Catch:{ IOException -> 0x01c7 }
            goto L_0x01cc
        L_0x01c7:
            r0 = move-exception
            r2 = r0
            p000.ifn.m12932a(r2)
        L_0x01cc:
            throw r1
        L_0x01cd:
            r8 = 0
        L_0x01cf:
            if (r8 == 0) goto L_0x01d7
            r2 = 24
            r1.mo1923a((int) r2, (byte[]) r8)
            return
        L_0x01d7:
            r2 = 24
            r1.mo1920a(r2)
            return
        L_0x01dd:
            r1 = 0
            throw r1
        L_0x01e0:
            r1.mo1920a(r11)
            r1.mo1920a(r15)
            r1.mo1920a(r14)
            r1.mo1920a(r13)
            r1.mo1920a(r12)
            r1.mo1920a(r7)
            r0 = 23
            r1.mo1920a(r0)
            r2 = 24
            r1.mo1920a(r2)
            return
        L_0x01fe:
            r1 = 0
            goto L_0x0202
        L_0x0201:
            throw r1
        L_0x0202:
            goto L_0x0201
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ali.mo579a(bh, java.lang.Object):void");
    }
}
