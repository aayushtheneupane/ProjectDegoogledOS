package p000;

import com.google.android.gms.common.api.Status;

/* renamed from: ejg */
/* compiled from: PG */
final class ejg extends elq {

    /* renamed from: h */
    private final eix f8424h;

    public ejg(eix eix, ekv ekv) {
        super(eja.f8395a, ekv);
        this.f8424h = eix;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ela mo3504a(Status status) {
        return status;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v15, resolved type: fqg} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002a, code lost:
        r5 = null;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0155  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x017d  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ void mo4881a(p000.ekl r22) {
        /*
            r21 = this;
            r1 = r21
            r2 = r22
            eji r2 = (p000.eji) r2
            ejj r3 = new ejj
            r3.<init>(r1)
            eix r0 = r1.f8424h     // Catch:{ RuntimeException -> 0x0254 }
            eja r5 = r0.f8384a     // Catch:{ RuntimeException -> 0x0254 }
            ekn r6 = p000.eja.f8395a     // Catch:{ RuntimeException -> 0x0254 }
            java.util.List r5 = r5.f8408l     // Catch:{ RuntimeException -> 0x0254 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ RuntimeException -> 0x0254 }
        L_0x0017:
            boolean r6 = r5.hasNext()     // Catch:{ RuntimeException -> 0x0254 }
            r7 = 0
            if (r6 == 0) goto L_0x002c
            java.lang.Object r0 = r5.next()     // Catch:{ RuntimeException -> 0x0254 }
            eiw r0 = (p000.eiw) r0     // Catch:{ RuntimeException -> 0x0254 }
            eix r0 = r0.mo4863a()     // Catch:{ RuntimeException -> 0x0254 }
            if (r0 != 0) goto L_0x0017
        L_0x002a:
            r5 = r7
            goto L_0x0046
        L_0x002c:
            java.util.List r5 = p000.eja.f8397k     // Catch:{ RuntimeException -> 0x0254 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ RuntimeException -> 0x0254 }
        L_0x0032:
            boolean r6 = r5.hasNext()     // Catch:{ RuntimeException -> 0x0254 }
            if (r6 == 0) goto L_0x0045
            java.lang.Object r0 = r5.next()     // Catch:{ RuntimeException -> 0x0254 }
            eiw r0 = (p000.eiw) r0     // Catch:{ RuntimeException -> 0x0254 }
            eix r0 = r0.mo4863a()     // Catch:{ RuntimeException -> 0x0254 }
            if (r0 != 0) goto L_0x0032
            goto L_0x002a
        L_0x0045:
            r5 = r0
        L_0x0046:
            if (r5 == 0) goto L_0x024e
            eja r0 = r5.f8384a
            eiy r0 = r0.f8407j
            java.lang.String r6 = r5.f8388e
            int r8 = r5.f8389f
            iit r9 = r5.f8391h
            iix r9 = r9.f14318b
            ins r9 = (p000.ins) r9
            int r9 = r9.f14581d
            if (r6 == 0) goto L_0x0062
            boolean r10 = r6.isEmpty()
            if (r10 != 0) goto L_0x0062
            r7 = r6
            goto L_0x0068
        L_0x0062:
            if (r8 >= 0) goto L_0x0065
            goto L_0x0068
        L_0x0065:
            java.lang.String r7 = "0"
        L_0x0068:
            if (r7 != 0) goto L_0x006c
            goto L_0x0181
        L_0x006c:
            ejn r0 = (p000.ejn) r0
            android.content.Context r8 = r0.f8431c
            if (r8 == 0) goto L_0x009d
            j$.util.concurrent.ConcurrentHashMap r8 = p000.ejn.f8428d
            java.lang.Object r8 = r8.get(r7)
            fqg r8 = (p000.fqg) r8
            if (r8 != 0) goto L_0x0093
            fqf r8 = p000.ejn.f8427b
            inv r10 = p000.inv.f14593b
            fqe r11 = new fqe
            r11.<init>(r8, r7, r10)
            j$.util.concurrent.ConcurrentHashMap r8 = p000.ejn.f8428d
            java.lang.Object r7 = r8.putIfAbsent(r7, r11)
            r8 = r7
            fqg r8 = (p000.fqg) r8
            if (r8 == 0) goto L_0x0091
            goto L_0x0093
        L_0x0091:
            r8 = r11
            goto L_0x0094
        L_0x0093:
        L_0x0094:
            java.lang.Object r7 = r8.mo6028c()
            inv r7 = (p000.inv) r7
            ije r7 = r7.f14595a
            goto L_0x00a1
        L_0x009d:
            java.util.List r7 = java.util.Collections.emptyList()
        L_0x00a1:
            java.util.Iterator r7 = r7.iterator()
        L_0x00a5:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0181
            java.lang.Object r8 = r7.next()
            inu r8 = (p000.inu) r8
            int r10 = r8.f14588a
            r11 = 1
            r10 = r10 & r11
            if (r10 != 0) goto L_0x00b8
            goto L_0x00be
        L_0x00b8:
            int r10 = r8.f14589b
            if (r10 == 0) goto L_0x00be
            if (r10 != r9) goto L_0x00a5
        L_0x00be:
            java.lang.String r10 = r8.f14590c
            android.content.Context r12 = r0.f8431c
            java.lang.Long r13 = p000.ejn.f8430f
            r14 = 0
            if (r13 == 0) goto L_0x00c9
            goto L_0x0107
        L_0x00c9:
            if (r12 == 0) goto L_0x010e
            java.lang.Boolean r13 = p000.ejn.f8429e
            if (r13 != 0) goto L_0x00e7
            erb r13 = p000.erc.m8049a(r12)
            android.content.Context r13 = r13.f8865a
            java.lang.String r11 = "com.google.android.providers.gsf.permission.READ_GSERVICES"
            int r11 = r13.checkCallingOrSelfPermission(r11)
            if (r11 != 0) goto L_0x00df
            r11 = 1
            goto L_0x00e1
        L_0x00df:
            r11 = 0
        L_0x00e1:
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r11)
            p000.ejn.f8429e = r11
        L_0x00e7:
            java.lang.Boolean r11 = p000.ejn.f8429e
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x0100
            android.content.ContentResolver r11 = r12.getContentResolver()
            java.lang.String r12 = "android_id"
            long r11 = p000.exi.m8309a((android.content.ContentResolver) r11, (java.lang.String) r12, (long) r14)
            java.lang.Long r11 = java.lang.Long.valueOf(r11)
            p000.ejn.f8430f = r11
            goto L_0x0107
        L_0x0100:
            java.lang.Long r11 = java.lang.Long.valueOf(r14)
            p000.ejn.f8430f = r11
        L_0x0107:
            java.lang.Long r11 = p000.ejn.f8430f
            long r11 = r11.longValue()
            goto L_0x0110
        L_0x010e:
            r11 = r14
        L_0x0110:
            r13 = 8
            if (r10 == 0) goto L_0x0135
            boolean r16 = r10.isEmpty()
            if (r16 != 0) goto L_0x0135
            java.nio.charset.Charset r4 = p000.ejn.f8426a
            byte[] r4 = r10.getBytes(r4)
            int r10 = r4.length
            int r10 = r10 + r13
            java.nio.ByteBuffer r10 = java.nio.ByteBuffer.allocate(r10)
            r10.put(r4)
            r10.putLong(r11)
            byte[] r4 = r10.array()
            long r10 = p000.eke.m7663a((byte[]) r4)
            goto L_0x0146
        L_0x0135:
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r13)
            java.nio.ByteBuffer r4 = r4.putLong(r11)
            byte[] r4 = r4.array()
            long r10 = p000.eke.m7663a((byte[]) r4)
        L_0x0146:
            long r12 = r8.f14591d
            r16 = r7
            long r6 = r8.f14592e
            int r8 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r8 < 0) goto L_0x017d
            int r8 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r8 <= 0) goto L_0x017d
            int r8 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r8 >= 0) goto L_0x016b
            r14 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            long r17 = r14 % r6
            r19 = 1
            long r17 = r17 + r19
            long r10 = r10 & r14
            long r10 = r10 % r6
            long r17 = r17 + r10
            long r17 = r17 % r6
            goto L_0x016f
        L_0x016b:
            long r17 = r10 % r6
        L_0x016f:
            int r6 = (r17 > r12 ? 1 : (r17 == r12 ? 0 : -1))
            if (r6 < 0) goto L_0x0179
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.f4972a
            r1.mo3507a((p000.ela) r0)
            return
        L_0x0179:
            r7 = r16
            goto L_0x00a5
        L_0x017d:
            r7 = r16
            goto L_0x00a5
        L_0x0181:
            eje r6 = new eje     // Catch:{ RuntimeException -> 0x0239 }
            ejo r15 = new ejo     // Catch:{ RuntimeException -> 0x0239 }
            eja r0 = r5.f8384a     // Catch:{ RuntimeException -> 0x0239 }
            java.lang.String r8 = r0.f8401d     // Catch:{ RuntimeException -> 0x0239 }
            android.content.Context r0 = r0.f8400c     // Catch:{ RuntimeException -> 0x0239 }
            int r7 = p000.eja.f8396b     // Catch:{ RuntimeException -> 0x0239 }
            r9 = -1
            if (r7 != r9) goto L_0x01b6
            java.lang.Class<eja> r7 = p000.eja.class
            monitor-enter(r7)     // Catch:{ RuntimeException -> 0x0239 }
            int r10 = p000.eja.f8396b     // Catch:{ all -> 0x01b3 }
            if (r10 != r9) goto L_0x01b1
            android.content.pm.PackageManager r9 = r0.getPackageManager()     // Catch:{ NameNotFoundException -> 0x01a9 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ NameNotFoundException -> 0x01a9 }
            r4 = 0
            android.content.pm.PackageInfo r0 = r9.getPackageInfo(r0, r4)     // Catch:{ NameNotFoundException -> 0x01a9 }
            int r0 = r0.versionCode     // Catch:{ NameNotFoundException -> 0x01a9 }
            p000.eja.f8396b = r0     // Catch:{ NameNotFoundException -> 0x01a9 }
            goto L_0x01b1
        L_0x01a9:
            r0 = move-exception
            java.lang.String r9 = "ClearcutLogger"
            java.lang.String r10 = "This can't happen."
            android.util.Log.wtf(r9, r10, r0)     // Catch:{ all -> 0x01b3 }
        L_0x01b1:
            monitor-exit(r7)     // Catch:{ all -> 0x01b3 }
            goto L_0x01b6
        L_0x01b3:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x01b3 }
            throw r0     // Catch:{ RuntimeException -> 0x0239 }
        L_0x01b6:
            int r9 = p000.eja.f8396b     // Catch:{ RuntimeException -> 0x0239 }
            int r10 = r5.f8389f     // Catch:{ RuntimeException -> 0x0239 }
            java.lang.String r11 = r5.f8388e     // Catch:{ RuntimeException -> 0x0239 }
            java.lang.String r12 = r5.f8387d     // Catch:{ RuntimeException -> 0x0239 }
            eja r0 = r5.f8384a     // Catch:{ RuntimeException -> 0x0239 }
            boolean r13 = r0.f8405h     // Catch:{ RuntimeException -> 0x0239 }
            int r14 = r5.f8390g     // Catch:{ RuntimeException -> 0x0239 }
            r7 = r15
            r7.<init>(r8, r9, r10, r11, r12, r13, r14)     // Catch:{ RuntimeException -> 0x0239 }
            iit r0 = r5.f8391h     // Catch:{ RuntimeException -> 0x0239 }
            iix r0 = r0.mo8770g()     // Catch:{ RuntimeException -> 0x0239 }
            ins r0 = (p000.ins) r0     // Catch:{ RuntimeException -> 0x0239 }
            eiz r7 = r5.f8385b     // Catch:{ RuntimeException -> 0x0239 }
            boolean r5 = r5.f8386c     // Catch:{ RuntimeException -> 0x0239 }
            r6.<init>(r15, r0, r7, r5)     // Catch:{ RuntimeException -> 0x0239 }
            ins r0 = r6.f8414b     // Catch:{ RuntimeException -> 0x0239 }
            r5 = 5
            java.lang.Object r5 = r0.mo8790b((int) r5)     // Catch:{ RuntimeException -> 0x0239 }
            iir r5 = (p000.iir) r5     // Catch:{ RuntimeException -> 0x0239 }
            r5.mo8503a((p000.iix) r0)     // Catch:{ RuntimeException -> 0x0239 }
            iit r5 = (p000.iit) r5     // Catch:{ RuntimeException -> 0x0239 }
            eiz r0 = r6.f8415c     // Catch:{ RuntimeException -> 0x0239 }
            if (r0 == 0) goto L_0x021f
            ins r0 = r6.f8414b     // Catch:{ RuntimeException -> 0x0239 }
            ihw r0 = r0.f14582e     // Catch:{ RuntimeException -> 0x0239 }
            int r0 = r0.mo8597a()     // Catch:{ RuntimeException -> 0x0239 }
            if (r0 != 0) goto L_0x021f
            eiz r0 = r6.f8415c     // Catch:{ RuntimeException -> 0x0239 }
            exy r0 = (p000.exy) r0     // Catch:{ RuntimeException -> 0x0239 }
            exu r0 = r0.f9194a     // Catch:{ RuntimeException -> 0x0239 }
            hdc r0 = (p000.hdc) r0     // Catch:{ RuntimeException -> 0x0239 }
            byte[] r0 = r0.f12515a     // Catch:{ RuntimeException -> 0x0239 }
            int r7 = p000.hde.f12516e     // Catch:{ RuntimeException -> 0x0239 }
            ihw r0 = p000.ihw.m13162a((byte[]) r0)     // Catch:{ RuntimeException -> 0x0239 }
            boolean r7 = r5.f14319c     // Catch:{ RuntimeException -> 0x0239 }
            if (r7 != 0) goto L_0x0208
            goto L_0x020e
        L_0x0208:
            r5.mo8751b()     // Catch:{ RuntimeException -> 0x0239 }
            r4 = 0
            r5.f14319c = r4     // Catch:{ RuntimeException -> 0x0239 }
        L_0x020e:
            iix r4 = r5.f14318b     // Catch:{ RuntimeException -> 0x0239 }
            ins r4 = (p000.ins) r4     // Catch:{ RuntimeException -> 0x0239 }
            ins r7 = p000.ins.f14576h     // Catch:{ RuntimeException -> 0x0239 }
            r0.getClass()     // Catch:{ RuntimeException -> 0x0239 }
            int r7 = r4.f14578a     // Catch:{ RuntimeException -> 0x0239 }
            r7 = r7 | 1024(0x400, float:1.435E-42)
            r4.f14578a = r7     // Catch:{ RuntimeException -> 0x0239 }
            r4.f14582e = r0     // Catch:{ RuntimeException -> 0x0239 }
        L_0x021f:
            iix r0 = r5.mo8770g()     // Catch:{ RuntimeException -> 0x0239 }
            ins r0 = (p000.ins) r0     // Catch:{ RuntimeException -> 0x0239 }
            r6.f8414b = r0     // Catch:{ RuntimeException -> 0x0239 }
            ins r0 = r6.f8414b     // Catch:{ RuntimeException -> 0x0239 }
            byte[] r0 = r0.mo8512ag()     // Catch:{ RuntimeException -> 0x0239 }
            r6.f8413a = r0     // Catch:{ RuntimeException -> 0x0239 }
            android.os.IInterface r0 = r2.mo5126p()
            ejm r0 = (p000.ejm) r0
            r0.mo4887a(r3, r6)
            return
        L_0x0239:
            r0 = move-exception
            java.lang.String r2 = "ClearcutLoggerApiImpl"
            java.lang.String r3 = "derived ClearcutLogger.MessageProducer "
            android.util.Log.e(r2, r3, r0)
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status
            java.lang.String r2 = "MessageProducer"
            r3 = 10
            r0.<init>(r3, r2)
            r1.mo4980b((com.google.android.gms.common.api.Status) r0)
            return
        L_0x024e:
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.f4972a
            r3.mo4886a(r0)
            return
        L_0x0254:
            r0 = move-exception
            java.lang.String r2 = "ClearcutLoggerApiImpl"
            java.lang.String r3 = "derived ClearcutLogger.EventModifier "
            android.util.Log.e(r2, r3, r0)
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status
            java.lang.String r2 = "EventModifier"
            r3 = 10
            r0.<init>(r3, r2)
            r1.mo4980b((com.google.android.gms.common.api.Status) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ejg.mo4881a(ekl):void");
    }
}
