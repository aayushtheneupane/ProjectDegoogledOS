package p000;

/* renamed from: alz */
/* compiled from: PG */
public final class alz implements Runnable {

    /* renamed from: b */
    private static final String f748b = iol.m14236b("EnqueueRunnable");

    /* renamed from: a */
    public final ahx f749a = new ahx();

    /* renamed from: c */
    private final aic f750c;

    public alz(aic aic) {
        this.f750c = aic;
    }

    /* JADX WARNING: Removed duplicated region for block: B:123:0x01b3 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00cb A[Catch:{ all -> 0x01e8, all -> 0x020f }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x010c A[Catch:{ all -> 0x01e8, all -> 0x020f }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0148 A[Catch:{ all -> 0x01e8, all -> 0x020f }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0149 A[Catch:{ all -> 0x01e8, all -> 0x020f }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0154 A[Catch:{ all -> 0x01e8, all -> 0x020f }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x017a A[Catch:{ all -> 0x01e8, all -> 0x020f }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x018a A[Catch:{ all -> 0x01e8, all -> 0x020f }, LOOP:6: B:84:0x0184->B:86:0x018a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01a3 A[Catch:{ all -> 0x01e8, all -> 0x020f }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01c6 A[Catch:{ all -> 0x01e8, all -> 0x020f }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01c7 A[Catch:{ all -> 0x01e8, all -> 0x020f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r22 = this;
            r1 = r22
            aic r0 = r1.f750c     // Catch:{ all -> 0x020f }
            java.util.HashSet r2 = new java.util.HashSet     // Catch:{ all -> 0x020f }
            r2.<init>()     // Catch:{ all -> 0x020f }
            java.util.List r3 = r0.f533d     // Catch:{ all -> 0x020f }
            r2.addAll(r3)     // Catch:{ all -> 0x020f }
            java.util.Set r3 = p000.aic.m534b()     // Catch:{ all -> 0x020f }
            java.util.Iterator r4 = r2.iterator()     // Catch:{ all -> 0x020f }
        L_0x0016:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x020f }
            r7 = 0
            if (r5 != 0) goto L_0x01ed
            java.util.List r0 = r0.f533d     // Catch:{ all -> 0x020f }
            r2.removeAll(r0)     // Catch:{ all -> 0x020f }
            aic r0 = r1.f750c     // Catch:{ all -> 0x020f }
            aip r0 = r0.f530a     // Catch:{ all -> 0x020f }
            androidx.work.impl.WorkDatabase r2 = r0.f554c     // Catch:{ all -> 0x020f }
            r2.mo2845e()     // Catch:{ all -> 0x020f }
            aic r0 = r1.f750c     // Catch:{ all -> 0x01e8 }
            java.util.Set r3 = p000.aic.m534b()     // Catch:{ all -> 0x01e8 }
            aip r4 = r0.f530a     // Catch:{ all -> 0x01e8 }
            java.util.List r5 = r0.f532c     // Catch:{ all -> 0x01e8 }
            java.lang.String[] r8 = new java.lang.String[r7]     // Catch:{ all -> 0x01e8 }
            java.lang.Object[] r3 = r3.toArray(r8)     // Catch:{ all -> 0x01e8 }
            java.lang.String[] r3 = (java.lang.String[]) r3     // Catch:{ all -> 0x01e8 }
            java.lang.String r8 = r0.f531b     // Catch:{ all -> 0x01e8 }
            int r9 = r0.f535f     // Catch:{ all -> 0x01e8 }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x01e8 }
            androidx.work.impl.WorkDatabase r12 = r4.f554c     // Catch:{ all -> 0x01e8 }
            if (r3 == 0) goto L_0x004f
            int r13 = r3.length     // Catch:{ all -> 0x01e8 }
            if (r13 <= 0) goto L_0x004e
            r13 = 1
            goto L_0x0050
        L_0x004e:
        L_0x004f:
            r13 = 0
        L_0x0050:
            if (r13 != 0) goto L_0x0059
            r17 = 1
            r18 = 0
            r19 = 0
            goto L_0x00a3
        L_0x0059:
            int r14 = r3.length     // Catch:{ all -> 0x01e8 }
            r15 = 0
            r17 = 1
            r18 = 0
            r19 = 0
        L_0x0061:
            if (r15 >= r14) goto L_0x00a3
            r7 = r3[r15]     // Catch:{ all -> 0x01e8 }
            alh r6 = r12.mo1226j()     // Catch:{ all -> 0x01e8 }
            alg r6 = r6.mo608b(r7)     // Catch:{ all -> 0x01e8 }
            if (r6 != 0) goto L_0x0089
            p000.iol.m14231a()     // Catch:{ all -> 0x01e8 }
            java.lang.String r3 = f748b     // Catch:{ all -> 0x01e8 }
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x01e8 }
            r4 = 0
            r5[r4] = r7     // Catch:{ all -> 0x01e8 }
            java.lang.String r6 = "Prerequisite %s doesn't exist; not enqueuing"
            java.lang.String r5 = java.lang.String.format(r6, r5)     // Catch:{ all -> 0x01e8 }
            java.lang.Throwable[] r6 = new java.lang.Throwable[r4]     // Catch:{ all -> 0x01e8 }
            p000.iol.m14234a((java.lang.String) r3, (java.lang.String) r5, (java.lang.Throwable[]) r6)     // Catch:{ all -> 0x01e8 }
            r7 = 0
            goto L_0x01ba
        L_0x0089:
            int r6 = r6.f728q     // Catch:{ all -> 0x01e8 }
            r7 = 3
            if (r6 != r7) goto L_0x0090
            r7 = 1
            goto L_0x0091
        L_0x0090:
            r7 = 0
        L_0x0091:
            r17 = r17 & r7
            r7 = 4
            if (r6 != r7) goto L_0x0099
            r19 = 1
            goto L_0x009e
        L_0x0099:
            r7 = 6
            if (r6 != r7) goto L_0x009e
            r18 = 1
        L_0x009e:
            int r15 = r15 + 1
            r7 = 0
            goto L_0x0061
        L_0x00a3:
            boolean r6 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x01e8 }
            r7 = 1
            r6 = r6 ^ r7
            if (r6 != 0) goto L_0x00ad
        L_0x00ab:
            r4 = 0
            goto L_0x0102
        L_0x00ad:
            if (r13 != 0) goto L_0x00ab
            alh r7 = r12.mo1226j()     // Catch:{ all -> 0x01e8 }
            java.util.List r7 = r7.mo612c(r8)     // Catch:{ all -> 0x01e8 }
            boolean r14 = r7.isEmpty()     // Catch:{ all -> 0x01e8 }
            if (r14 != 0) goto L_0x0101
            r14 = 2
            if (r9 == r14) goto L_0x00c1
            goto L_0x00dc
        L_0x00c1:
            java.util.Iterator r9 = r7.iterator()     // Catch:{ all -> 0x01e8 }
        L_0x00c5:
            boolean r15 = r9.hasNext()     // Catch:{ all -> 0x01e8 }
            if (r15 == 0) goto L_0x00dc
            java.lang.Object r15 = r9.next()     // Catch:{ all -> 0x01e8 }
            alf r15 = (p000.alf) r15     // Catch:{ all -> 0x01e8 }
            int r15 = r15.f711b     // Catch:{ all -> 0x01e8 }
            r14 = 1
            if (r15 == r14) goto L_0x00d9
            r14 = 2
            if (r15 != r14) goto L_0x00c5
        L_0x00d9:
            r7 = 0
            goto L_0x01ba
        L_0x00dc:
            alx r9 = new alx     // Catch:{ all -> 0x01e8 }
            r9.<init>(r4, r8)     // Catch:{ all -> 0x01e8 }
            r9.run()     // Catch:{ all -> 0x01e8 }
            alh r4 = r12.mo1226j()     // Catch:{ all -> 0x01e8 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x01e8 }
        L_0x00ec:
            boolean r9 = r7.hasNext()     // Catch:{ all -> 0x01e8 }
            if (r9 == 0) goto L_0x00fe
            java.lang.Object r9 = r7.next()     // Catch:{ all -> 0x01e8 }
            alf r9 = (p000.alf) r9     // Catch:{ all -> 0x01e8 }
            java.lang.String r9 = r9.f710a     // Catch:{ all -> 0x01e8 }
            r4.mo605a((java.lang.String) r9)     // Catch:{ all -> 0x01e8 }
            goto L_0x00ec
        L_0x00fe:
            r4 = 1
            goto L_0x0102
        L_0x0101:
            goto L_0x00ab
        L_0x0102:
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x01e8 }
        L_0x0106:
            boolean r7 = r5.hasNext()     // Catch:{ all -> 0x01e8 }
            if (r7 == 0) goto L_0x01b9
            java.lang.Object r7 = r5.next()     // Catch:{ all -> 0x01e8 }
            ahs r7 = (p000.ahs) r7     // Catch:{ all -> 0x01e8 }
            alg r9 = r7.f500b     // Catch:{ all -> 0x01e8 }
            if (r13 == 0) goto L_0x012d
            if (r17 != 0) goto L_0x012a
            if (r19 == 0) goto L_0x011e
            r14 = 4
            r9.f728q = r14     // Catch:{ all -> 0x01e8 }
            goto L_0x013d
        L_0x011e:
            r14 = 4
            if (r18 != 0) goto L_0x0125
            r15 = 5
            r9.f728q = r15     // Catch:{ all -> 0x01e8 }
            goto L_0x013d
        L_0x0125:
            r15 = 6
            r9.f728q = r15     // Catch:{ all -> 0x01e8 }
            goto L_0x013d
        L_0x012a:
            r14 = 4
            r15 = 6
            goto L_0x012f
        L_0x012d:
            r14 = 4
            r15 = 6
        L_0x012f:
            boolean r16 = r9.mo595a()     // Catch:{ all -> 0x01e8 }
            if (r16 != 0) goto L_0x0138
            r9.f724m = r10     // Catch:{ all -> 0x01e8 }
            goto L_0x013d
        L_0x0138:
            r14 = 0
            r9.f724m = r14     // Catch:{ all -> 0x01e8 }
        L_0x013d:
            int r14 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x01e8 }
            int r14 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x01e8 }
            int r14 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x01e8 }
            int r14 = r9.f728q     // Catch:{ all -> 0x01e8 }
            r15 = 1
            if (r14 == r15) goto L_0x0149
            goto L_0x014b
        L_0x0149:
            r4 = 1
        L_0x014b:
            alh r14 = r12.mo1226j()     // Catch:{ all -> 0x01e8 }
            r14.mo604a((p000.alg) r9)     // Catch:{ all -> 0x01e8 }
            if (r13 == 0) goto L_0x017a
            int r9 = r3.length     // Catch:{ all -> 0x01e8 }
            r14 = 0
        L_0x0156:
            if (r14 >= r9) goto L_0x0175
            r15 = r3[r14]     // Catch:{ all -> 0x01e8 }
            r20 = r3
            akj r3 = new akj     // Catch:{ all -> 0x01e8 }
            r21 = r4
            java.lang.String r4 = r7.mo499a()     // Catch:{ all -> 0x01e8 }
            r3.<init>(r4, r15)     // Catch:{ all -> 0x01e8 }
            akk r4 = r12.mo1227k()     // Catch:{ all -> 0x01e8 }
            r4.mo575a((p000.akj) r3)     // Catch:{ all -> 0x01e8 }
            int r14 = r14 + 1
            r3 = r20
            r4 = r21
            goto L_0x0156
        L_0x0175:
            r20 = r3
            r21 = r4
            goto L_0x017e
        L_0x017a:
            r20 = r3
            r21 = r4
        L_0x017e:
            java.util.Set r3 = r7.f501c     // Catch:{ all -> 0x01e8 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x01e8 }
        L_0x0184:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x01e8 }
            if (r4 == 0) goto L_0x01a1
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x01e8 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x01e8 }
            alt r9 = r12.mo1228l()     // Catch:{ all -> 0x01e8 }
            als r14 = new als     // Catch:{ all -> 0x01e8 }
            java.lang.String r15 = r7.mo499a()     // Catch:{ all -> 0x01e8 }
            r14.<init>(r4, r15)     // Catch:{ all -> 0x01e8 }
            r9.mo621a((p000.als) r14)     // Catch:{ all -> 0x01e8 }
            goto L_0x0184
        L_0x01a1:
            if (r6 == 0) goto L_0x01b3
            akx r3 = r12.mo1230n()     // Catch:{ all -> 0x01e8 }
            akw r4 = new akw     // Catch:{ all -> 0x01e8 }
            java.lang.String r7 = r7.mo499a()     // Catch:{ all -> 0x01e8 }
            r4.<init>(r8, r7)     // Catch:{ all -> 0x01e8 }
            r3.mo589a(r4)     // Catch:{ all -> 0x01e8 }
        L_0x01b3:
            r3 = r20
            r4 = r21
            goto L_0x0106
        L_0x01b9:
            r7 = r4
        L_0x01ba:
            r3 = 1
            r0.f534e = r3     // Catch:{ all -> 0x01e8 }
            r2.mo2847g()     // Catch:{ all -> 0x01e8 }
            r2.mo2846f()     // Catch:{ all -> 0x020f }
            if (r7 != 0) goto L_0x01c7
            goto L_0x01e0
        L_0x01c7:
            aic r0 = r1.f750c     // Catch:{ all -> 0x020f }
            aip r0 = r0.f530a     // Catch:{ all -> 0x020f }
            android.content.Context r0 = r0.f552a     // Catch:{ all -> 0x020f }
            java.lang.Class<androidx.work.impl.background.systemalarm.RescheduleReceiver> r2 = androidx.work.impl.background.systemalarm.RescheduleReceiver.class
            r3 = 1
            p000.amc.m760a(r0, r2, r3)     // Catch:{ all -> 0x020f }
            aic r0 = r1.f750c     // Catch:{ all -> 0x020f }
            aip r0 = r0.f530a     // Catch:{ all -> 0x020f }
            agz r2 = r0.f553b     // Catch:{ all -> 0x020f }
            androidx.work.impl.WorkDatabase r2 = r0.f554c     // Catch:{ all -> 0x020f }
            java.util.List r0 = r0.f556e     // Catch:{ all -> 0x020f }
            p000.aib.m533a((androidx.work.impl.WorkDatabase) r2, (java.util.List) r0)     // Catch:{ all -> 0x020f }
        L_0x01e0:
            ahx r0 = r1.f749a     // Catch:{ all -> 0x020f }
            ahn r2 = p000.aho.f494a     // Catch:{ all -> 0x020f }
            r0.mo504a(r2)     // Catch:{ all -> 0x020f }
            return
        L_0x01e8:
            r0 = move-exception
            r2.mo2846f()     // Catch:{ all -> 0x020f }
            throw r0     // Catch:{ all -> 0x020f }
        L_0x01ed:
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x020f }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x020f }
            boolean r5 = r3.contains(r5)     // Catch:{ all -> 0x020f }
            if (r5 != 0) goto L_0x01fb
            goto L_0x0016
        L_0x01fb:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x020f }
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x020f }
            aic r3 = r1.f750c     // Catch:{ all -> 0x020f }
            r4 = 0
            r2[r4] = r3     // Catch:{ all -> 0x020f }
            java.lang.String r3 = "WorkContinuation has cycles (%s)"
            java.lang.String r2 = java.lang.String.format(r3, r2)     // Catch:{ all -> 0x020f }
            r0.<init>(r2)     // Catch:{ all -> 0x020f }
            throw r0     // Catch:{ all -> 0x020f }
        L_0x020f:
            r0 = move-exception
            ahx r2 = r1.f749a
            ahl r3 = new ahl
            r3.<init>(r0)
            r2.mo504a(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.alz.run():void");
    }
}
