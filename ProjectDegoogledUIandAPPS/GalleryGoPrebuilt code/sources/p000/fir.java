package p000;

/* renamed from: fir */
/* compiled from: PG */
final /* synthetic */ class fir implements ice {

    /* renamed from: a */
    private final fis f9750a;

    /* renamed from: b */
    private final iqm f9751b;

    public fir(fis fis, iqm iqm) {
        this.f9750a = fis;
        this.f9751b = iqm;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0323, code lost:
        if ((r5 / r7) <= 3.472222222222222E-5d) goto L_0x0325;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0074 A[Catch:{ ijh -> 0x004c }] */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x05ef  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0115 A[Catch:{ ijh -> 0x004c }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.ieh mo2539a() {
        /*
            r25 = this;
            r1 = r25
            fis r2 = r1.f9750a
            iqm r8 = r1.f9751b
            p000.fxk.m9836c()
            boolean r0 = r2.f9687c
            r11 = 0
            r12 = 0
            if (r0 == 0) goto L_0x001a
            java.lang.Object[] r0 = new java.lang.Object[r12]
            java.lang.String r2 = "BatteryMetricService"
            java.lang.String r3 = "service is shutdown - skipping capture"
            p000.flw.m9199b(r2, r3, r0)
            goto L_0x0601
        L_0x001a:
            fnw r3 = r2.f9752d
            monitor-enter(r3)
            fnw r0 = r2.f9752d     // Catch:{ all -> 0x0619 }
            foo r0 = r0.f10122a     // Catch:{ all -> 0x0619 }
            java.lang.String r4 = "primes.battery.snapshot"
            fpo r5 = p000.fpo.f10212k     // Catch:{ all -> 0x0619 }
            r6 = 7
            java.lang.Object r5 = r5.mo8790b((int) r6)     // Catch:{ all -> 0x0619 }
            ikn r5 = (p000.ikn) r5     // Catch:{ all -> 0x0619 }
            android.content.SharedPreferences r0 = r0.f10156a     // Catch:{ all -> 0x0619 }
            java.lang.String r6 = ""
            java.lang.String r0 = r0.getString(r4, r6)     // Catch:{ all -> 0x0619 }
            byte[] r0 = android.util.Base64.decode(r0, r12)     // Catch:{ all -> 0x0619 }
            r13 = 1
            if (r0 != 0) goto L_0x003c
            goto L_0x0065
        L_0x003c:
            int r4 = r0.length     // Catch:{ all -> 0x0619 }
            if (r4 == 0) goto L_0x0065
            byte r6 = r0[r12]     // Catch:{ all -> 0x0619 }
            if (r6 != r13) goto L_0x0059
            int r4 = r4 + -1
            java.lang.Object r0 = r5.mo8520a((byte[]) r0, (int) r4)     // Catch:{ ijh -> 0x004c }
            ikf r0 = (p000.ikf) r0     // Catch:{ ijh -> 0x004c }
            goto L_0x006f
        L_0x004c:
            r0 = move-exception
            java.lang.String r4 = "PersistStorage"
            java.lang.String r5 = "failure reading proto"
            java.lang.Object[] r6 = new java.lang.Object[r12]     // Catch:{ all -> 0x0619 }
            p000.flw.m9198b(r4, r5, r0, r6)     // Catch:{ all -> 0x0619 }
            r0 = r11
            goto L_0x006f
        L_0x0059:
            java.lang.String r0 = "PersistStorage"
            java.lang.String r4 = "wrong header"
            java.lang.Object[] r5 = new java.lang.Object[r12]     // Catch:{ all -> 0x0619 }
            p000.flw.m9202d(r0, r4, r5)     // Catch:{ all -> 0x0619 }
            r0 = r11
            goto L_0x006f
        L_0x0065:
            java.lang.String r0 = "PersistStorage"
            java.lang.String r4 = "unknown key"
            java.lang.Object[] r5 = new java.lang.Object[r12]     // Catch:{ all -> 0x0619 }
            p000.flw.m9202d(r0, r4, r5)     // Catch:{ all -> 0x0619 }
            r0 = r11
        L_0x006f:
            fpo r0 = (p000.fpo) r0     // Catch:{ all -> 0x0619 }
            r14 = 2
            if (r0 == 0) goto L_0x0115
            int r4 = r0.f10214a     // Catch:{ all -> 0x0619 }
            r4 = r4 & 32
            if (r4 == 0) goto L_0x008a
            int r4 = r0.f10220g     // Catch:{ all -> 0x0619 }
            iqm r4 = p000.iqm.m14321a(r4)     // Catch:{ all -> 0x0619 }
            if (r4 == 0) goto L_0x0085
            r21 = r4
            goto L_0x008d
        L_0x0085:
            iqm r4 = p000.iqm.UNKNOWN     // Catch:{ all -> 0x0619 }
            r21 = r4
            goto L_0x008d
        L_0x008a:
            r21 = r11
        L_0x008d:
            fnv r4 = new fnv     // Catch:{ all -> 0x0619 }
            iqw r5 = r0.f10215b     // Catch:{ all -> 0x0619 }
            if (r5 == 0) goto L_0x0096
            r16 = r5
            goto L_0x009a
        L_0x0096:
            iqw r5 = p000.iqw.f14716aq     // Catch:{ all -> 0x0619 }
            r16 = r5
        L_0x009a:
            int r5 = r0.f10214a     // Catch:{ all -> 0x0619 }
            r5 = r5 & r14
            if (r5 == 0) goto L_0x00a8
            long r5 = r0.f10216c     // Catch:{ all -> 0x0619 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0619 }
            r17 = r5
            goto L_0x00ab
        L_0x00a8:
            r17 = r11
        L_0x00ab:
            int r5 = r0.f10214a     // Catch:{ all -> 0x0619 }
            r5 = r5 & 4
            if (r5 == 0) goto L_0x00ba
            long r5 = r0.f10217d     // Catch:{ all -> 0x0619 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0619 }
            r18 = r5
            goto L_0x00bd
        L_0x00ba:
            r18 = r11
        L_0x00bd:
            int r5 = r0.f10214a     // Catch:{ all -> 0x0619 }
            r5 = r5 & 8
            if (r5 == 0) goto L_0x00cc
            long r5 = r0.f10218e     // Catch:{ all -> 0x0619 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0619 }
            r19 = r5
            goto L_0x00cf
        L_0x00cc:
            r19 = r11
        L_0x00cf:
            int r5 = r0.f10214a     // Catch:{ all -> 0x0619 }
            r5 = r5 & 16
            if (r5 == 0) goto L_0x00de
            long r5 = r0.f10219f     // Catch:{ all -> 0x0619 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0619 }
            r20 = r5
            goto L_0x00e0
        L_0x00de:
            r20 = r11
        L_0x00e0:
            int r5 = r0.f10214a     // Catch:{ all -> 0x0619 }
            r6 = r5 & 64
            if (r6 == 0) goto L_0x00eb
            java.lang.String r6 = r0.f10221h     // Catch:{ all -> 0x0619 }
            r22 = r6
            goto L_0x00ee
        L_0x00eb:
            r22 = r11
        L_0x00ee:
            r5 = r5 & 128(0x80, float:1.794E-43)
            if (r5 == 0) goto L_0x00fb
            boolean r5 = r0.f10222i     // Catch:{ all -> 0x0619 }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ all -> 0x0619 }
            r23 = r5
            goto L_0x00fd
        L_0x00fb:
            r23 = r11
        L_0x00fd:
            int r5 = r0.f10214a     // Catch:{ all -> 0x0619 }
            r5 = r5 & 256(0x100, float:3.59E-43)
            if (r5 == 0) goto L_0x010c
            iqx r0 = r0.f10223j     // Catch:{ all -> 0x0619 }
            if (r0 != 0) goto L_0x0109
            iqx r0 = p000.iqx.f14783a     // Catch:{ all -> 0x0619 }
        L_0x0109:
            r24 = r0
            goto L_0x010f
        L_0x010c:
            r24 = r11
        L_0x010f:
            r15 = r4
            r15.<init>(r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ all -> 0x0619 }
            r0 = r4
            goto L_0x0117
        L_0x0115:
            r0 = r11
        L_0x0117:
            monitor-exit(r3)     // Catch:{ all -> 0x0619 }
            fnm r4 = r2.f9753e
            fnl r15 = new fnl
            long r5 = android.os.SystemClock.elapsedRealtime()
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            long r6 = java.lang.System.currentTimeMillis()
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            fnx r3 = r4.f10097a
            android.content.Context r3 = r3.f10123a
            java.lang.String r7 = "systemhealth"
            java.lang.Object r3 = r3.getSystemService(r7)
            android.os.health.SystemHealthManager r3 = (android.os.health.SystemHealthManager) r3
            if (r3 == 0) goto L_0x0140
            android.os.health.HealthStats r3 = r3.takeMyUidSnapshot()
            r7 = r3
            goto L_0x0142
        L_0x0140:
            r7 = r11
        L_0x0142:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r13)
            fiq r3 = r4.f10098b
            iqx r10 = r3.mo5832a()
            r3 = r15
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            fnv r3 = r15.mo5981a()
            fnw r4 = r2.f9752d
            monitor-enter(r4)
            fnw r5 = r2.f9752d     // Catch:{ all -> 0x0616 }
            fpo r6 = p000.fpo.f10212k     // Catch:{ all -> 0x0616 }
            iir r6 = r6.mo8793g()     // Catch:{ all -> 0x0616 }
            iqw r7 = r3.f10113a     // Catch:{ all -> 0x0616 }
            if (r7 == 0) goto L_0x017b
            boolean r8 = r6.f14319c     // Catch:{ all -> 0x0616 }
            if (r8 != 0) goto L_0x0168
            goto L_0x016d
        L_0x0168:
            r6.mo8751b()     // Catch:{ all -> 0x0616 }
            r6.f14319c = r12     // Catch:{ all -> 0x0616 }
        L_0x016d:
            iix r8 = r6.f14318b     // Catch:{ all -> 0x0616 }
            fpo r8 = (p000.fpo) r8     // Catch:{ all -> 0x0616 }
            r7.getClass()     // Catch:{ all -> 0x0616 }
            r8.f10215b = r7     // Catch:{ all -> 0x0616 }
            int r7 = r8.f10214a     // Catch:{ all -> 0x0616 }
            r7 = r7 | r13
            r8.f10214a = r7     // Catch:{ all -> 0x0616 }
        L_0x017b:
            java.lang.Long r7 = r3.f10114b     // Catch:{ all -> 0x0616 }
            if (r7 == 0) goto L_0x0198
            long r7 = r7.longValue()     // Catch:{ all -> 0x0616 }
            boolean r9 = r6.f14319c     // Catch:{ all -> 0x0616 }
            if (r9 != 0) goto L_0x0188
            goto L_0x018d
        L_0x0188:
            r6.mo8751b()     // Catch:{ all -> 0x0616 }
            r6.f14319c = r12     // Catch:{ all -> 0x0616 }
        L_0x018d:
            iix r9 = r6.f14318b     // Catch:{ all -> 0x0616 }
            fpo r9 = (p000.fpo) r9     // Catch:{ all -> 0x0616 }
            int r10 = r9.f10214a     // Catch:{ all -> 0x0616 }
            r10 = r10 | r14
            r9.f10214a = r10     // Catch:{ all -> 0x0616 }
            r9.f10216c = r7     // Catch:{ all -> 0x0616 }
        L_0x0198:
            java.lang.Long r7 = r3.f10115c     // Catch:{ all -> 0x0616 }
            if (r7 == 0) goto L_0x01b6
            long r7 = r7.longValue()     // Catch:{ all -> 0x0616 }
            boolean r9 = r6.f14319c     // Catch:{ all -> 0x0616 }
            if (r9 != 0) goto L_0x01a5
            goto L_0x01aa
        L_0x01a5:
            r6.mo8751b()     // Catch:{ all -> 0x0616 }
            r6.f14319c = r12     // Catch:{ all -> 0x0616 }
        L_0x01aa:
            iix r9 = r6.f14318b     // Catch:{ all -> 0x0616 }
            fpo r9 = (p000.fpo) r9     // Catch:{ all -> 0x0616 }
            int r10 = r9.f10214a     // Catch:{ all -> 0x0616 }
            r10 = r10 | 4
            r9.f10214a = r10     // Catch:{ all -> 0x0616 }
            r9.f10217d = r7     // Catch:{ all -> 0x0616 }
        L_0x01b6:
            java.lang.Long r7 = r3.f10116d     // Catch:{ all -> 0x0616 }
            if (r7 == 0) goto L_0x01d4
            long r7 = r7.longValue()     // Catch:{ all -> 0x0616 }
            boolean r9 = r6.f14319c     // Catch:{ all -> 0x0616 }
            if (r9 != 0) goto L_0x01c3
            goto L_0x01c8
        L_0x01c3:
            r6.mo8751b()     // Catch:{ all -> 0x0616 }
            r6.f14319c = r12     // Catch:{ all -> 0x0616 }
        L_0x01c8:
            iix r9 = r6.f14318b     // Catch:{ all -> 0x0616 }
            fpo r9 = (p000.fpo) r9     // Catch:{ all -> 0x0616 }
            int r10 = r9.f10214a     // Catch:{ all -> 0x0616 }
            r10 = r10 | 8
            r9.f10214a = r10     // Catch:{ all -> 0x0616 }
            r9.f10218e = r7     // Catch:{ all -> 0x0616 }
        L_0x01d4:
            java.lang.Long r7 = r3.f10117e     // Catch:{ all -> 0x0616 }
            if (r7 == 0) goto L_0x01f2
            long r7 = r7.longValue()     // Catch:{ all -> 0x0616 }
            boolean r9 = r6.f14319c     // Catch:{ all -> 0x0616 }
            if (r9 != 0) goto L_0x01e1
            goto L_0x01e6
        L_0x01e1:
            r6.mo8751b()     // Catch:{ all -> 0x0616 }
            r6.f14319c = r12     // Catch:{ all -> 0x0616 }
        L_0x01e6:
            iix r9 = r6.f14318b     // Catch:{ all -> 0x0616 }
            fpo r9 = (p000.fpo) r9     // Catch:{ all -> 0x0616 }
            int r10 = r9.f10214a     // Catch:{ all -> 0x0616 }
            r10 = r10 | 16
            r9.f10214a = r10     // Catch:{ all -> 0x0616 }
            r9.f10219f = r7     // Catch:{ all -> 0x0616 }
        L_0x01f2:
            iqm r7 = r3.f10118f     // Catch:{ all -> 0x0616 }
            if (r7 == 0) goto L_0x020e
            int r7 = r7.f14659d     // Catch:{ all -> 0x0616 }
            boolean r8 = r6.f14319c     // Catch:{ all -> 0x0616 }
            if (r8 != 0) goto L_0x01fd
            goto L_0x0202
        L_0x01fd:
            r6.mo8751b()     // Catch:{ all -> 0x0616 }
            r6.f14319c = r12     // Catch:{ all -> 0x0616 }
        L_0x0202:
            iix r8 = r6.f14318b     // Catch:{ all -> 0x0616 }
            fpo r8 = (p000.fpo) r8     // Catch:{ all -> 0x0616 }
            int r9 = r8.f10214a     // Catch:{ all -> 0x0616 }
            r9 = r9 | 32
            r8.f10214a = r9     // Catch:{ all -> 0x0616 }
            r8.f10220g = r7     // Catch:{ all -> 0x0616 }
        L_0x020e:
            java.lang.String r7 = r3.f10119g     // Catch:{ all -> 0x0616 }
            if (r7 == 0) goto L_0x022b
            boolean r8 = r6.f14319c     // Catch:{ all -> 0x0616 }
            if (r8 != 0) goto L_0x0217
            goto L_0x021c
        L_0x0217:
            r6.mo8751b()     // Catch:{ all -> 0x0616 }
            r6.f14319c = r12     // Catch:{ all -> 0x0616 }
        L_0x021c:
            iix r8 = r6.f14318b     // Catch:{ all -> 0x0616 }
            fpo r8 = (p000.fpo) r8     // Catch:{ all -> 0x0616 }
            r7.getClass()     // Catch:{ all -> 0x0616 }
            int r9 = r8.f10214a     // Catch:{ all -> 0x0616 }
            r9 = r9 | 64
            r8.f10214a = r9     // Catch:{ all -> 0x0616 }
            r8.f10221h = r7     // Catch:{ all -> 0x0616 }
        L_0x022b:
            java.lang.Boolean r7 = r3.f10120h     // Catch:{ all -> 0x0616 }
            if (r7 == 0) goto L_0x0249
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x0616 }
            boolean r8 = r6.f14319c     // Catch:{ all -> 0x0616 }
            if (r8 != 0) goto L_0x0238
            goto L_0x023d
        L_0x0238:
            r6.mo8751b()     // Catch:{ all -> 0x0616 }
            r6.f14319c = r12     // Catch:{ all -> 0x0616 }
        L_0x023d:
            iix r8 = r6.f14318b     // Catch:{ all -> 0x0616 }
            fpo r8 = (p000.fpo) r8     // Catch:{ all -> 0x0616 }
            int r9 = r8.f10214a     // Catch:{ all -> 0x0616 }
            r9 = r9 | 128(0x80, float:1.794E-43)
            r8.f10214a = r9     // Catch:{ all -> 0x0616 }
            r8.f10222i = r7     // Catch:{ all -> 0x0616 }
        L_0x0249:
            iqx r7 = r3.f10121i     // Catch:{ all -> 0x0616 }
            if (r7 != 0) goto L_0x024e
            goto L_0x0267
        L_0x024e:
            boolean r8 = r6.f14319c     // Catch:{ all -> 0x0616 }
            if (r8 != 0) goto L_0x0253
            goto L_0x0258
        L_0x0253:
            r6.mo8751b()     // Catch:{ all -> 0x0616 }
            r6.f14319c = r12     // Catch:{ all -> 0x0616 }
        L_0x0258:
            iix r8 = r6.f14318b     // Catch:{ all -> 0x0616 }
            fpo r8 = (p000.fpo) r8     // Catch:{ all -> 0x0616 }
            r7.getClass()     // Catch:{ all -> 0x0616 }
            r8.f10223j = r7     // Catch:{ all -> 0x0616 }
            int r7 = r8.f10214a     // Catch:{ all -> 0x0616 }
            r7 = r7 | 256(0x100, float:3.59E-43)
            r8.f10214a = r7     // Catch:{ all -> 0x0616 }
        L_0x0267:
            foo r5 = r5.f10122a     // Catch:{ all -> 0x0616 }
            java.lang.String r7 = "primes.battery.snapshot"
            iix r6 = r6.mo8770g()     // Catch:{ all -> 0x0616 }
            fpo r6 = (p000.fpo) r6     // Catch:{ all -> 0x0616 }
            java.lang.Object r6 = p000.ife.m12898e((java.lang.Object) r6)     // Catch:{ all -> 0x0616 }
            ikf r6 = (p000.ikf) r6     // Catch:{ all -> 0x0616 }
            byte[] r6 = r6.mo8512ag()     // Catch:{ all -> 0x0616 }
            int r8 = r6.length     // Catch:{ all -> 0x0616 }
            int r9 = r8 + 1
            byte[] r9 = new byte[r9]     // Catch:{ all -> 0x0616 }
            r9[r12] = r13     // Catch:{ all -> 0x0616 }
            java.lang.System.arraycopy(r6, r12, r9, r13, r8)     // Catch:{ all -> 0x0616 }
            android.content.SharedPreferences r5 = r5.f10156a     // Catch:{ all -> 0x0616 }
            android.content.SharedPreferences$Editor r5 = r5.edit()     // Catch:{ all -> 0x0616 }
            java.lang.String r6 = android.util.Base64.encodeToString(r9, r12)     // Catch:{ all -> 0x0616 }
            android.content.SharedPreferences$Editor r5 = r5.putString(r7, r6)     // Catch:{ all -> 0x0616 }
            boolean r5 = r5.commit()     // Catch:{ all -> 0x0616 }
            monitor-exit(r4)     // Catch:{ all -> 0x0616 }
            if (r5 == 0) goto L_0x0607
            fnv[] r4 = new p000.fnv[r14]
            r4[r12] = r0
            r4[r13] = r3
            java.lang.String r5 = "BatteryMetricService"
            java.lang.String r6 = "log start: %s\nend: %s"
            p000.flw.m9193a(r5, r6, r4)
            fnm r4 = r2.f9753e
            if (r0 == 0) goto L_0x05e3
            java.lang.Long r5 = r0.f10116d
            java.lang.Long r6 = r3.f10116d
            boolean r5 = p003j$.util.Objects.equals(r5, r6)
            if (r5 == 0) goto L_0x05e3
            java.lang.Long r5 = r0.f10117e
            java.lang.Long r6 = r3.f10117e
            boolean r5 = p003j$.util.Objects.equals(r5, r6)
            if (r5 != 0) goto L_0x02c1
            goto L_0x05e3
        L_0x02c1:
            java.lang.Long r5 = r0.f10114b
            if (r5 == 0) goto L_0x05e3
            java.lang.Long r5 = r0.f10115c
            if (r5 == 0) goto L_0x05e3
            java.lang.Long r5 = r3.f10114b
            if (r5 == 0) goto L_0x05e3
            java.lang.Long r6 = r3.f10115c
            if (r6 == 0) goto L_0x05e3
            java.lang.Object r5 = p000.ife.m12898e((java.lang.Object) r5)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            java.lang.Long r7 = r0.f10114b
            java.lang.Object r7 = p000.ife.m12898e((java.lang.Object) r7)
            java.lang.Long r7 = (java.lang.Long) r7
            long r7 = r7.longValue()
            long r5 = r5 - r7
            java.lang.Long r7 = r3.f10115c
            java.lang.Object r7 = p000.ife.m12898e((java.lang.Object) r7)
            java.lang.Long r7 = (java.lang.Long) r7
            long r7 = r7.longValue()
            java.lang.Long r9 = r0.f10115c
            java.lang.Object r9 = p000.ife.m12898e((java.lang.Object) r9)
            java.lang.Long r9 = (java.lang.Long) r9
            long r9 = r9.longValue()
            long r7 = r7 - r9
            r9 = 0
            int r14 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r14 <= 0) goto L_0x05e3
            long r5 = r5 - r7
            long r5 = java.lang.Math.abs(r5)
            r14 = 25
            int r16 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            if (r16 >= 0) goto L_0x0313
            goto L_0x0325
        L_0x0313:
            double r5 = (double) r5
            double r7 = (double) r7
            java.lang.Double.isNaN(r5)
            java.lang.Double.isNaN(r7)
            double r5 = r5 / r7
            r7 = 4540248920338119903(0x3f023456789abcdf, double:3.472222222222222E-5)
            int r14 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r14 > 0) goto L_0x05e3
        L_0x0325:
            fnx r4 = r4.f10097a
            iqw r5 = r3.f10113a
            iqw r6 = r0.f10113a
            iqw r5 = p000.fpt.m9377a((p000.iqw) r5, (p000.iqw) r6)
            if (r5 == 0) goto L_0x044e
            r6 = 5
            java.lang.Object r6 = r5.mo8790b((int) r6)
            iir r6 = (p000.iir) r6
            r6.mo8503a((p000.iix) r5)
            fno r4 = r4.f10124b
            iix r5 = r6.f14318b
            iqw r5 = (p000.iqw) r5
            ije r5 = r5.f14765g
            java.util.Collections.unmodifiableList(r5)
            r5 = 0
        L_0x0347:
            iix r7 = r6.f14318b
            iqw r7 = (p000.iqw) r7
            ije r7 = r7.f14765g
            int r7 = r7.size()
            if (r5 >= r7) goto L_0x0363
            fnn r7 = p000.fnn.WAKELOCK
            iqv r7 = r6.mo8750b((int) r5)
            iqv r7 = r4.mo5983a((p000.iqv) r7)
            r6.mo8764e(r5, r7)
            int r5 = r5 + 1
            goto L_0x0347
        L_0x0363:
            iix r5 = r6.f14318b
            iqw r5 = (p000.iqw) r5
            ije r5 = r5.f14766h
            java.util.Collections.unmodifiableList(r5)
            r5 = 0
        L_0x036d:
            iix r7 = r6.f14318b
            iqw r7 = (p000.iqw) r7
            ije r7 = r7.f14766h
            int r7 = r7.size()
            if (r5 >= r7) goto L_0x0389
            fnn r7 = p000.fnn.WAKELOCK
            iqv r7 = r6.mo8754c((int) r5)
            iqv r7 = r4.mo5983a((p000.iqv) r7)
            r6.mo8768f(r5, r7)
            int r5 = r5 + 1
            goto L_0x036d
        L_0x0389:
            iix r5 = r6.f14318b
            iqw r5 = (p000.iqw) r5
            ije r5 = r5.f14767i
            java.util.Collections.unmodifiableList(r5)
            r5 = 0
        L_0x0393:
            iix r7 = r6.f14318b
            iqw r7 = (p000.iqw) r7
            ije r7 = r7.f14767i
            int r7 = r7.size()
            if (r5 >= r7) goto L_0x03af
            fnn r7 = p000.fnn.WAKELOCK
            iqv r7 = r6.mo8759d((int) r5)
            iqv r7 = r4.mo5983a((p000.iqv) r7)
            r6.mo8772g(r5, r7)
            int r5 = r5 + 1
            goto L_0x0393
        L_0x03af:
            iix r5 = r6.f14318b
            iqw r5 = (p000.iqw) r5
            ije r5 = r5.f14768j
            java.util.Collections.unmodifiableList(r5)
            r5 = 0
        L_0x03b9:
            iix r7 = r6.f14318b
            iqw r7 = (p000.iqw) r7
            ije r7 = r7.f14768j
            int r7 = r7.size()
            if (r5 >= r7) goto L_0x03d5
            fnn r7 = p000.fnn.WAKELOCK
            iqv r7 = r6.mo8763e((int) r5)
            iqv r7 = r4.mo5983a((p000.iqv) r7)
            r6.mo8760d(r5, r7)
            int r5 = r5 + 1
            goto L_0x03b9
        L_0x03d5:
            iix r5 = r6.f14318b
            iqw r5 = (p000.iqw) r5
            ije r5 = r5.f14769k
            java.util.Collections.unmodifiableList(r5)
            r5 = 0
        L_0x03df:
            iix r7 = r6.f14318b
            iqw r7 = (p000.iqw) r7
            ije r7 = r7.f14769k
            int r7 = r7.size()
            if (r5 >= r7) goto L_0x03fb
            fnn r7 = p000.fnn.WAKELOCK
            iqv r7 = r6.mo8767f((int) r5)
            iqv r7 = r4.mo5983a((p000.iqv) r7)
            r6.mo8755c(r5, r7)
            int r5 = r5 + 1
            goto L_0x03df
        L_0x03fb:
            iix r5 = r6.f14318b
            iqw r5 = (p000.iqw) r5
            ije r5 = r5.f14770l
            java.util.Collections.unmodifiableList(r5)
            r5 = 0
        L_0x0405:
            iix r7 = r6.f14318b
            iqw r7 = (p000.iqw) r7
            ije r7 = r7.f14770l
            int r7 = r7.size()
            if (r5 >= r7) goto L_0x0421
            fnn r7 = p000.fnn.WAKELOCK
            iqv r7 = r6.mo8771g((int) r5)
            iqv r7 = r4.mo5983a((p000.iqv) r7)
            r6.mo8745a((int) r5, (p000.iqv) r7)
            int r5 = r5 + 1
            goto L_0x0405
        L_0x0421:
            iix r5 = r6.f14318b
            iqw r5 = (p000.iqw) r5
            ije r5 = r5.f14772n
            java.util.Collections.unmodifiableList(r5)
            r5 = 0
        L_0x042b:
            iix r7 = r6.f14318b
            iqw r7 = (p000.iqw) r7
            ije r7 = r7.f14772n
            int r7 = r7.size()
            if (r5 >= r7) goto L_0x0447
            fnn r7 = p000.fnn.WAKELOCK
            iqv r7 = r6.mo8775h((int) r5)
            iqv r7 = r4.mo5983a((p000.iqv) r7)
            r6.mo8752b(r5, r7)
            int r5 = r5 + 1
            goto L_0x042b
        L_0x0447:
            iix r4 = r6.mo8770g()
            iqw r4 = (p000.iqw) r4
            goto L_0x0450
        L_0x044e:
            r4 = r11
        L_0x0450:
            if (r4 != 0) goto L_0x045f
            java.lang.Object[] r0 = new java.lang.Object[r12]
            java.lang.String r4 = "BatteryCapture"
            java.lang.String r5 = "null diff"
            p000.flw.m9199b(r4, r5, r0)
            r0 = r11
            goto L_0x05ed
        L_0x045f:
            int r5 = r4.f14743a
            r5 = r5 & r13
            if (r5 != 0) goto L_0x0466
            goto L_0x05d7
        L_0x0466:
            long r5 = r4.f14761c
            int r7 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r7 <= 0) goto L_0x05d7
            iqn r5 = p000.iqn.f14660k
            iir r5 = r5.mo8793g()
            java.lang.Long r6 = r3.f10114b
            java.lang.Object r6 = p000.ife.m12898e((java.lang.Object) r6)
            java.lang.Long r6 = (java.lang.Long) r6
            long r6 = r6.longValue()
            java.lang.Long r8 = r0.f10114b
            java.lang.Object r8 = p000.ife.m12898e((java.lang.Object) r8)
            java.lang.Long r8 = (java.lang.Long) r8
            long r8 = r8.longValue()
            long r6 = r6 - r8
            boolean r8 = r5.f14319c
            if (r8 == 0) goto L_0x0494
            r5.mo8751b()
            r5.f14319c = r12
        L_0x0494:
            iix r8 = r5.f14318b
            iqn r8 = (p000.iqn) r8
            int r9 = r8.f14662a
            r9 = r9 | 64
            r8.f14662a = r9
            r8.f14669h = r6
            iqm r6 = r0.f10118f
            if (r6 == 0) goto L_0x04c0
            java.lang.Object r6 = p000.ife.m12898e((java.lang.Object) r6)
            iqm r6 = (p000.iqm) r6
            boolean r7 = r5.f14319c
            if (r7 == 0) goto L_0x04b3
            r5.mo8751b()
            r5.f14319c = r12
        L_0x04b3:
            iix r7 = r5.f14318b
            iqn r7 = (p000.iqn) r7
            int r6 = r6.f14659d
            r7.f14663b = r6
            int r6 = r7.f14662a
            r6 = r6 | r13
            r7.f14662a = r6
        L_0x04c0:
            java.lang.String r6 = r0.f10119g
            if (r6 == 0) goto L_0x0513
            java.lang.Boolean r6 = r0.f10120h
            java.lang.Object r6 = p000.ife.m12898e((java.lang.Object) r6)
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x04f3
            java.lang.String r6 = r0.f10119g
            java.lang.Object r6 = p000.ife.m12898e((java.lang.Object) r6)
            java.lang.String r6 = (java.lang.String) r6
            boolean r7 = r5.f14319c
            if (r7 == 0) goto L_0x04e3
            r5.mo8751b()
            r5.f14319c = r12
        L_0x04e3:
            iix r7 = r5.f14318b
            iqn r7 = (p000.iqn) r7
            r6.getClass()
            int r8 = r7.f14662a
            r8 = r8 | 8
            r7.f14662a = r8
            r7.f14666e = r6
            goto L_0x0513
        L_0x04f3:
            java.lang.String r6 = r0.f10119g
            java.lang.Object r6 = p000.ife.m12898e((java.lang.Object) r6)
            java.lang.String r6 = (java.lang.String) r6
            boolean r7 = r5.f14319c
            if (r7 == 0) goto L_0x0504
            r5.mo8751b()
            r5.f14319c = r12
        L_0x0504:
            iix r7 = r5.f14318b
            iqn r7 = (p000.iqn) r7
            r6.getClass()
            int r8 = r7.f14662a
            r8 = r8 | 4
            r7.f14662a = r8
            r7.f14665d = r6
        L_0x0513:
            iqx r0 = r0.f10121i
            if (r0 == 0) goto L_0x0535
            java.lang.Object r0 = p000.ife.m12898e((java.lang.Object) r0)
            iqx r0 = (p000.iqx) r0
            boolean r6 = r5.f14319c
            if (r6 == 0) goto L_0x0526
            r5.mo8751b()
            r5.f14319c = r12
        L_0x0526:
            iix r6 = r5.f14318b
            iqn r6 = (p000.iqn) r6
            r0.getClass()
            r6.f14667f = r0
            int r0 = r6.f14662a
            r0 = r0 | 16
            r6.f14662a = r0
        L_0x0535:
            iqm r0 = r3.f10118f
            if (r0 == 0) goto L_0x0556
            java.lang.Object r0 = p000.ife.m12898e((java.lang.Object) r0)
            iqm r0 = (p000.iqm) r0
            boolean r6 = r5.f14319c
            if (r6 == 0) goto L_0x0548
            r5.mo8751b()
            r5.f14319c = r12
        L_0x0548:
            iix r6 = r5.f14318b
            iqn r6 = (p000.iqn) r6
            int r0 = r0.f14659d
            r6.f14668g = r0
            int r0 = r6.f14662a
            r0 = r0 | 32
            r6.f14662a = r0
        L_0x0556:
            java.lang.Long r0 = r3.f10114b
            if (r0 == 0) goto L_0x0579
            java.lang.Object r0 = p000.ife.m12898e((java.lang.Object) r0)
            java.lang.Long r0 = (java.lang.Long) r0
            long r6 = r0.longValue()
            boolean r0 = r5.f14319c
            if (r0 == 0) goto L_0x056d
            r5.mo8751b()
            r5.f14319c = r12
        L_0x056d:
            iix r0 = r5.f14318b
            iqn r0 = (p000.iqn) r0
            int r8 = r0.f14662a
            r8 = r8 | 256(0x100, float:3.59E-43)
            r0.f14662a = r8
            r0.f14671j = r6
        L_0x0579:
            iix r0 = r5.f14318b
            iqn r0 = (p000.iqn) r0
            r4.getClass()
            r0.f14670i = r4
            int r4 = r0.f14662a
            r4 = r4 | 128(0x80, float:1.794E-43)
            r0.f14662a = r4
            isc r0 = p000.isc.f14974r
            iir r0 = r0.mo8793g()
            iqo r4 = p000.iqo.f14672c
            iir r4 = r4.mo8793g()
            boolean r6 = r4.f14319c
            if (r6 != 0) goto L_0x0599
            goto L_0x059e
        L_0x0599:
            r4.mo8751b()
            r4.f14319c = r12
        L_0x059e:
            iix r6 = r4.f14318b
            iqo r6 = (p000.iqo) r6
            iix r5 = r5.mo8770g()
            iqn r5 = (p000.iqn) r5
            r5.getClass()
            r6.f14675b = r5
            int r5 = r6.f14674a
            r5 = r5 | r13
            r6.f14674a = r5
            boolean r5 = r0.f14319c
            if (r5 == 0) goto L_0x05bb
            r0.mo8751b()
            r0.f14319c = r12
        L_0x05bb:
            iix r5 = r0.f14318b
            isc r5 = (p000.isc) r5
            iix r4 = r4.mo8770g()
            iqo r4 = (p000.iqo) r4
            r4.getClass()
            r5.f14986k = r4
            int r4 = r5.f14976a
            r4 = r4 | 512(0x200, float:7.175E-43)
            r5.f14976a = r4
            iix r0 = r0.mo8770g()
            isc r0 = (p000.isc) r0
            goto L_0x05ed
        L_0x05d7:
            java.lang.Object[] r0 = new java.lang.Object[r12]
            java.lang.String r4 = "BatteryCapture"
            java.lang.String r5 = "invalid realtime"
            p000.flw.m9199b(r4, r5, r0)
            r0 = r11
            goto L_0x05ed
        L_0x05e3:
            java.lang.Object[] r0 = new java.lang.Object[r12]
            java.lang.String r4 = "BatteryCapture"
            java.lang.String r5 = "inconsistent stats"
            p000.flw.m9199b(r4, r5, r0)
            r0 = r11
        L_0x05ed:
            if (r0 == 0) goto L_0x0601
            java.lang.Boolean r4 = r3.f10120h
            java.lang.String r5 = r3.f10119g
            if (r4 == 0) goto L_0x05fa
            boolean r12 = r4.booleanValue()
            goto L_0x05fc
        L_0x05fa:
        L_0x05fc:
            iqx r3 = r3.f10121i
            r2.mo5729a(r5, r12, r0, r3)
        L_0x0601:
            ieh r0 = p000.ife.m12820a((java.lang.Object) r11)
            goto L_0x0615
        L_0x0607:
            r2.mo5733d()
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r2 = "Failure storing persistent snapshot and helper data"
            r0.<init>(r2)
            ieh r0 = p000.ife.m12822a((java.lang.Throwable) r0)
        L_0x0615:
            return r0
        L_0x0616:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0616 }
            throw r0
        L_0x0619:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0619 }
            goto L_0x061d
        L_0x061c:
            throw r0
        L_0x061d:
            goto L_0x061c
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fir.mo2539a():ieh");
    }
}
