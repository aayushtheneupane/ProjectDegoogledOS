package p000;

import java.util.concurrent.Callable;

/* renamed from: efe */
/* compiled from: PG */
final /* synthetic */ class efe implements Callable {

    /* renamed from: a */
    private final efj f8140a;

    /* renamed from: b */
    private final ieh f8141b;

    /* renamed from: c */
    private final ieh f8142c;

    public efe(efj efj, ieh ieh, ieh ieh2) {
        this.f8140a = efj;
        this.f8141b = ieh;
        this.f8142c = ieh2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:88:0x020c A[Catch:{ ExecutionException -> 0x0263 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object call() {
        /*
            r18 = this;
            r1 = r18
            efj r0 = r1.f8140a
            ieh r2 = r1.f8141b
            ieh r3 = r1.f8142c
            java.lang.Object r2 = p000.ife.m12871b((java.util.concurrent.Future) r2)     // Catch:{ ExecutionException -> 0x0263 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ ExecutionException -> 0x0263 }
            java.lang.Object r3 = p000.ife.m12871b((java.util.concurrent.Future) r3)     // Catch:{ ExecutionException -> 0x0263 }
            edh r3 = (p000.edh) r3     // Catch:{ ExecutionException -> 0x0263 }
            java.lang.Object r4 = r0.f8147a     // Catch:{ ExecutionException -> 0x0263 }
            monitor-enter(r4)     // Catch:{ ExecutionException -> 0x0263 }
            eff r5 = new eff     // Catch:{ all -> 0x0260 }
            r5.<init>(r0)     // Catch:{ all -> 0x0260 }
            p000.ife.m12898e((java.lang.Object) r2)     // Catch:{ all -> 0x0260 }
            p000.ife.m12898e((java.lang.Object) r5)     // Catch:{ all -> 0x0260 }
            htr r6 = new htr     // Catch:{ all -> 0x0260 }
            r6.<init>(r2, r5)     // Catch:{ all -> 0x0260 }
            j$.util.function.Function r2 = p000.efg.f8144a     // Catch:{ all -> 0x0260 }
            java.util.Comparator r2 = p003j$.util.Comparator$$CC.comparing$$STATIC$$(r2)     // Catch:{ all -> 0x0260 }
            huv r2 = p000.huv.m12191a((java.util.Comparator) r2)     // Catch:{ all -> 0x0260 }
            int r5 = r0.f8148b     // Catch:{ all -> 0x0260 }
            huv r2 = r2.mo8116a()     // Catch:{ all -> 0x0260 }
            java.util.Iterator r7 = r6.iterator()     // Catch:{ all -> 0x0260 }
            p000.ife.m12898e((java.lang.Object) r7)     // Catch:{ all -> 0x0260 }
            java.lang.String r8 = "k"
            p000.ife.m12839a((int) r5, (java.lang.String) r8)     // Catch:{ all -> 0x0260 }
            if (r5 == 0) goto L_0x0200
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x0260 }
            if (r8 != 0) goto L_0x004d
            goto L_0x0200
        L_0x004d:
            r8 = 1073741823(0x3fffffff, float:1.9999999)
            if (r5 < r8) goto L_0x0073
            java.util.ArrayList r7 = p000.ife.m12835a((java.util.Iterator) r7)     // Catch:{ all -> 0x0260 }
            java.util.Collections.sort(r7, r2)     // Catch:{ all -> 0x0260 }
            int r2 = r7.size()     // Catch:{ all -> 0x0260 }
            if (r2 <= r5) goto L_0x006a
            int r2 = r7.size()     // Catch:{ all -> 0x0260 }
            java.util.List r2 = r7.subList(r5, r2)     // Catch:{ all -> 0x0260 }
            r2.clear()     // Catch:{ all -> 0x0260 }
        L_0x006a:
            r7.trimToSize()     // Catch:{ all -> 0x0260 }
            java.util.List r2 = java.util.Collections.unmodifiableList(r7)     // Catch:{ all -> 0x0260 }
            goto L_0x0204
        L_0x0073:
            hvp r8 = new hvp     // Catch:{ all -> 0x0260 }
            r8.<init>(r2, r5)     // Catch:{ all -> 0x0260 }
        L_0x0078:
            boolean r2 = r7.hasNext()     // Catch:{ all -> 0x0260 }
            r5 = 0
            if (r2 != 0) goto L_0x00b3
            java.lang.Object[] r2 = r8.f13486c     // Catch:{ all -> 0x0260 }
            int r7 = r8.f13487d     // Catch:{ all -> 0x0260 }
            java.util.Comparator r9 = r8.f13485b     // Catch:{ all -> 0x0260 }
            java.util.Arrays.sort(r2, r5, r7, r9)     // Catch:{ all -> 0x0260 }
            int r2 = r8.f13487d     // Catch:{ all -> 0x0260 }
            int r5 = r8.f13484a     // Catch:{ all -> 0x0260 }
            if (r2 > r5) goto L_0x008f
        L_0x008e:
            goto L_0x00a3
        L_0x008f:
            java.lang.Object[] r2 = r8.f13486c     // Catch:{ all -> 0x0260 }
            int r7 = r2.length     // Catch:{ all -> 0x0260 }
            r9 = 0
            java.util.Arrays.fill(r2, r5, r7, r9)     // Catch:{ all -> 0x0260 }
            int r2 = r8.f13484a     // Catch:{ all -> 0x0260 }
            r8.f13487d = r2     // Catch:{ all -> 0x0260 }
            java.lang.Object[] r5 = r8.f13486c     // Catch:{ all -> 0x0260 }
            int r7 = r2 + -1
            r5 = r5[r7]     // Catch:{ all -> 0x0260 }
            r8.f13488e = r5     // Catch:{ all -> 0x0260 }
            goto L_0x008e
        L_0x00a3:
            java.lang.Object[] r5 = r8.f13486c     // Catch:{ all -> 0x0260 }
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r5, r2)     // Catch:{ all -> 0x0260 }
            java.util.List r2 = java.util.Arrays.asList(r2)     // Catch:{ all -> 0x0260 }
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)     // Catch:{ all -> 0x0260 }
            goto L_0x0204
        L_0x00b3:
            java.lang.Object r2 = r7.next()     // Catch:{ all -> 0x0260 }
            int r9 = r8.f13484a     // Catch:{ all -> 0x0260 }
            if (r9 == 0) goto L_0x01fc
            int r10 = r8.f13487d     // Catch:{ all -> 0x0260 }
            r11 = 1
            if (r10 != 0) goto L_0x00c9
            java.lang.Object[] r9 = r8.f13486c     // Catch:{ all -> 0x0260 }
            r9[r5] = r2     // Catch:{ all -> 0x0260 }
            r8.f13488e = r2     // Catch:{ all -> 0x0260 }
            r8.f13487d = r11     // Catch:{ all -> 0x0260 }
            goto L_0x0078
        L_0x00c9:
            if (r10 < r9) goto L_0x01e0
            java.util.Comparator r9 = r8.f13485b     // Catch:{ all -> 0x0260 }
            java.lang.Object r10 = r8.f13488e     // Catch:{ all -> 0x0260 }
            int r9 = r9.compare(r2, r10)     // Catch:{ all -> 0x0260 }
            if (r9 >= 0) goto L_0x01dc
            java.lang.Object[] r9 = r8.f13486c     // Catch:{ all -> 0x0260 }
            int r10 = r8.f13487d     // Catch:{ all -> 0x0260 }
            int r12 = r10 + 1
            r8.f13487d = r12     // Catch:{ all -> 0x0260 }
            r9[r10] = r2     // Catch:{ all -> 0x0260 }
            int r2 = r8.f13484a     // Catch:{ all -> 0x0260 }
            int r2 = r2 + r2
            if (r12 != r2) goto L_0x01d8
            int r2 = r2 + -1
            java.math.RoundingMode r9 = java.math.RoundingMode.CEILING     // Catch:{ all -> 0x0260 }
            java.lang.String r10 = "x"
            if (r2 <= 0) goto L_0x01b7
            int[] r10 = p000.iaz.f13815a     // Catch:{ all -> 0x0260 }
            int r9 = r9.ordinal()     // Catch:{ all -> 0x0260 }
            r9 = r10[r9]     // Catch:{ all -> 0x0260 }
            switch(r9) {
                case 1: goto L_0x0114;
                case 2: goto L_0x0120;
                case 3: goto L_0x0120;
                case 4: goto L_0x010b;
                case 5: goto L_0x010b;
                case 6: goto L_0x00fb;
                case 7: goto L_0x00fb;
                case 8: goto L_0x00fb;
                default: goto L_0x00f7;
            }     // Catch:{ all -> 0x0260 }
        L_0x00f7:
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch:{ all -> 0x0260 }
            goto L_0x01b3
        L_0x00fb:
            int r9 = java.lang.Integer.numberOfLeadingZeros(r2)     // Catch:{ all -> 0x0260 }
            int r10 = 31 - r9
            r12 = -1257966797(0xffffffffb504f333, float:-4.9527733E-7)
            int r9 = r12 >>> r9
            int r9 = r9 - r2
            int r9 = r9 >>> 31
            int r10 = r10 + r9
            goto L_0x0126
        L_0x010b:
            int r9 = r2 + -1
            int r9 = java.lang.Integer.numberOfLeadingZeros(r9)     // Catch:{ all -> 0x0260 }
            int r10 = 32 - r9
            goto L_0x0126
        L_0x0114:
            int r9 = r2 + -1
            r9 = r9 & r2
            if (r9 != 0) goto L_0x011b
            r9 = 1
            goto L_0x011d
        L_0x011b:
            r9 = 0
        L_0x011d:
            p000.ife.m12844a((boolean) r9)     // Catch:{ all -> 0x0260 }
        L_0x0120:
            int r9 = java.lang.Integer.numberOfLeadingZeros(r2)     // Catch:{ all -> 0x0260 }
            int r10 = 31 - r9
        L_0x0126:
            int r10 = r10 * 3
            r9 = 0
            r12 = 0
        L_0x012a:
            if (r5 >= r2) goto L_0x0183
            int r13 = r5 + r2
            int r13 = r13 + r11
            int r13 = r13 >>> r11
            java.lang.Object[] r14 = r8.f13486c     // Catch:{ all -> 0x0260 }
            r15 = r14[r13]     // Catch:{ all -> 0x0260 }
            r16 = r14[r2]     // Catch:{ all -> 0x0260 }
            r14[r13] = r16     // Catch:{ all -> 0x0260 }
            r13 = r5
            r14 = r13
        L_0x013b:
            if (r13 >= r2) goto L_0x015c
            java.util.Comparator r11 = r8.f13485b     // Catch:{ all -> 0x0260 }
            java.lang.Object[] r1 = r8.f13486c     // Catch:{ all -> 0x0260 }
            r1 = r1[r13]     // Catch:{ all -> 0x0260 }
            int r1 = r11.compare(r1, r15)     // Catch:{ all -> 0x0260 }
            if (r1 < 0) goto L_0x014a
            goto L_0x0156
        L_0x014a:
            java.lang.Object[] r1 = r8.f13486c     // Catch:{ all -> 0x0260 }
            r11 = r1[r14]     // Catch:{ all -> 0x0260 }
            r17 = r1[r13]     // Catch:{ all -> 0x0260 }
            r1[r14] = r17     // Catch:{ all -> 0x0260 }
            r1[r13] = r11     // Catch:{ all -> 0x0260 }
            int r14 = r14 + 1
        L_0x0156:
            int r13 = r13 + 1
            r1 = r18
            r11 = 1
            goto L_0x013b
        L_0x015c:
            java.lang.Object[] r1 = r8.f13486c     // Catch:{ all -> 0x0260 }
            r11 = r1[r14]     // Catch:{ all -> 0x0260 }
            r1[r2] = r11     // Catch:{ all -> 0x0260 }
            r1[r14] = r15     // Catch:{ all -> 0x0260 }
            int r1 = r8.f13484a     // Catch:{ all -> 0x0260 }
            if (r14 <= r1) goto L_0x016c
            int r14 = r14 + -1
            r2 = r14
            goto L_0x0178
        L_0x016c:
            if (r14 >= r1) goto L_0x0183
            int r5 = r5 + 1
            int r1 = java.lang.Math.max(r14, r5)     // Catch:{ all -> 0x0260 }
            r5 = r1
            r9 = r14
        L_0x0178:
            r1 = 1
            int r12 = r12 + r1
            if (r12 < r10) goto L_0x0184
            java.lang.Object[] r1 = r8.f13486c     // Catch:{ all -> 0x0260 }
            java.util.Comparator r10 = r8.f13485b     // Catch:{ all -> 0x0260 }
            java.util.Arrays.sort(r1, r5, r2, r10)     // Catch:{ all -> 0x0260 }
        L_0x0183:
            goto L_0x0188
        L_0x0184:
            r1 = r18
            r11 = 1
            goto L_0x012a
        L_0x0188:
            int r1 = r8.f13484a     // Catch:{ all -> 0x0260 }
            r8.f13487d = r1     // Catch:{ all -> 0x0260 }
            java.lang.Object[] r1 = r8.f13486c     // Catch:{ all -> 0x0260 }
            r1 = r1[r9]     // Catch:{ all -> 0x0260 }
            r8.f13488e = r1     // Catch:{ all -> 0x0260 }
            int r9 = r9 + 1
        L_0x0194:
            int r1 = r8.f13484a     // Catch:{ all -> 0x0260 }
            if (r9 >= r1) goto L_0x01af
            java.util.Comparator r1 = r8.f13485b     // Catch:{ all -> 0x0260 }
            java.lang.Object[] r2 = r8.f13486c     // Catch:{ all -> 0x0260 }
            r2 = r2[r9]     // Catch:{ all -> 0x0260 }
            java.lang.Object r5 = r8.f13488e     // Catch:{ all -> 0x0260 }
            int r1 = r1.compare(r2, r5)     // Catch:{ all -> 0x0260 }
            if (r1 <= 0) goto L_0x01ac
            java.lang.Object[] r1 = r8.f13486c     // Catch:{ all -> 0x0260 }
            r1 = r1[r9]     // Catch:{ all -> 0x0260 }
            r8.f13488e = r1     // Catch:{ all -> 0x0260 }
        L_0x01ac:
            int r9 = r9 + 1
            goto L_0x0194
        L_0x01af:
            r1 = r18
            goto L_0x0078
        L_0x01b3:
            r0.<init>()     // Catch:{ all -> 0x0260 }
            throw r0     // Catch:{ all -> 0x0260 }
        L_0x01b7:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0260 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0260 }
            r3 = 27
            r1.<init>(r3)     // Catch:{ all -> 0x0260 }
            r1.append(r10)     // Catch:{ all -> 0x0260 }
            java.lang.String r3 = " ("
            r1.append(r3)     // Catch:{ all -> 0x0260 }
            r1.append(r2)     // Catch:{ all -> 0x0260 }
            java.lang.String r2 = ") must be > 0"
            r1.append(r2)     // Catch:{ all -> 0x0260 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0260 }
            r0.<init>(r1)     // Catch:{ all -> 0x0260 }
            throw r0     // Catch:{ all -> 0x0260 }
        L_0x01d8:
            r1 = r18
            goto L_0x0078
        L_0x01dc:
            r1 = r18
            goto L_0x0078
        L_0x01e0:
            java.lang.Object[] r1 = r8.f13486c     // Catch:{ all -> 0x0260 }
            int r5 = r10 + 1
            r8.f13487d = r5     // Catch:{ all -> 0x0260 }
            r1[r10] = r2     // Catch:{ all -> 0x0260 }
            java.util.Comparator r1 = r8.f13485b     // Catch:{ all -> 0x0260 }
            java.lang.Object r5 = r8.f13488e     // Catch:{ all -> 0x0260 }
            int r1 = r1.compare(r2, r5)     // Catch:{ all -> 0x0260 }
            if (r1 <= 0) goto L_0x01f8
            r8.f13488e = r2     // Catch:{ all -> 0x0260 }
            r1 = r18
            goto L_0x0078
        L_0x01f8:
            r1 = r18
            goto L_0x0078
        L_0x01fc:
            r1 = r18
            goto L_0x0078
        L_0x0200:
            java.util.List r2 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0260 }
        L_0x0204:
            int r1 = r2.size()     // Catch:{ all -> 0x0260 }
            int r5 = r0.f8148b     // Catch:{ all -> 0x0260 }
            if (r1 < r5) goto L_0x0253
            j$.util.function.Function r1 = p000.efh.f8145a     // Catch:{ all -> 0x0260 }
            java.util.Comparator r1 = p003j$.util.Comparator$$CC.comparing$$STATIC$$(r1)     // Catch:{ all -> 0x0260 }
            j$.util.function.Function r5 = p000.efi.f8146a     // Catch:{ all -> 0x0260 }
            java.util.Comparator r1 = p003j$.util.Comparator$$Dispatch.thenComparing((java.util.Comparator) r1, (p003j$.util.function.Function) r5)     // Catch:{ all -> 0x0260 }
            huv r1 = p000.huv.m12191a((java.util.Comparator) r1)     // Catch:{ all -> 0x0260 }
            java.lang.Object r1 = r1.mo8120b((java.lang.Iterable) r6)     // Catch:{ all -> 0x0260 }
            edw r1 = (p000.edw) r1     // Catch:{ all -> 0x0260 }
            java.util.Iterator r5 = r2.iterator()     // Catch:{ all -> 0x0260 }
        L_0x0226:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x0260 }
            if (r6 != 0) goto L_0x023f
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0260 }
            r5.<init>(r2)     // Catch:{ all -> 0x0260 }
            int r2 = r5.size()     // Catch:{ all -> 0x0260 }
            int r2 = r2 + -1
            r5.remove(r2)     // Catch:{ all -> 0x0260 }
            r5.add(r1)     // Catch:{ all -> 0x0260 }
            r2 = r5
            goto L_0x0253
        L_0x023f:
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x0260 }
            edw r6 = (p000.edw) r6     // Catch:{ all -> 0x0260 }
            java.lang.String r6 = r6.mo4729c()     // Catch:{ all -> 0x0260 }
            java.lang.String r7 = r1.mo4729c()     // Catch:{ all -> 0x0260 }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x0260 }
            if (r6 == 0) goto L_0x0226
        L_0x0253:
            boolean r1 = r3.f8038b     // Catch:{ all -> 0x0260 }
            efc r3 = new efc     // Catch:{ all -> 0x0260 }
            r3.<init>(r2, r1)     // Catch:{ all -> 0x0260 }
            r0.f8151e = r3     // Catch:{ all -> 0x0260 }
            efd r0 = r0.f8151e     // Catch:{ all -> 0x0260 }
            monitor-exit(r4)     // Catch:{ all -> 0x0260 }
            return r0
        L_0x0260:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0260 }
            throw r0     // Catch:{ ExecutionException -> 0x0263 }
        L_0x0263:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "SharingFragment: Failed to load apps to share with"
            r1.<init>(r2, r0)
            goto L_0x026d
        L_0x026c:
            throw r1
        L_0x026d:
            goto L_0x026c
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.efe.call():java.lang.Object");
    }
}
