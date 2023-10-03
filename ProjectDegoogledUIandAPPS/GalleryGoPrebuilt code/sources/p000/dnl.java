package p000;

import com.google.android.apps.photosgo.R;

/* renamed from: dnl */
/* compiled from: PG */
final class dnl implements gry {

    /* renamed from: a */
    private final /* synthetic */ dnn f6868a;

    public dnl(dnn dnn) {
        this.f6868a = dnn;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2651a(Object obj, Throwable th) {
        imh imh = (imh) obj;
        this.f6868a.f6904m.mo2572a((int) R.string.oneup_delete_failed_snackbar);
        cwn.m5515b(th, "OneUpFragment: Deletion failed", new Object[0]);
        this.f6868a.f6883M = false;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2649a(Object obj) {
        imh imh = (imh) obj;
    }

    /* JADX WARNING: type inference failed for: r14v37, types: [iix] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ void mo2650a(java.lang.Object r14, java.lang.Object r15) {
        /*
            r13 = this;
            imh r14 = (p000.imh) r14
            java.lang.Void r15 = (java.lang.Void) r15
            dnn r15 = r13.f6868a
            j$.util.Optional r0 = p003j$.util.Optional.empty()
            r15.f6893b = r0
            dnn r15 = r13.f6868a
            dog r15 = r15.f6878H
            cyd r0 = p000.cyd.f5985i
            dnn r1 = r13.f6868a
            iij r1 = r1.f6909r
            ikf r14 = r14.mo8995a(r0, r1)
            cyd r14 = (p000.cyd) r14
            eav r0 = r15.f6934a
            j$.util.Optional r0 = r0.mo4634a()
            java.lang.Object r0 = r0.get()
            dls r0 = (p000.dls) r0
            cxi r0 = r0.f6796a
            cjr r1 = r15.f6935b
            boolean r1 = r1.mo3175a()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0036
        L_0x0034:
            goto L_0x00a9
        L_0x0036:
            boolean r0 = p000.flw.m9194a((p000.cxi) r0)
            if (r0 == 0) goto L_0x0034
            eav r0 = r15.f6934a
            j$.util.Optional r1 = r0.mo4634a()
            java.lang.Object r1 = r1.get()
            dls r1 = (p000.dls) r1
            cxi r1 = r1.f6796a
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            ije r5 = r1.f5929u
            java.lang.Object r5 = r5.get(r3)
            cxi r5 = (p000.cxi) r5
            ije r1 = r1.f5929u
            int r6 = r1.size()
        L_0x005d:
            if (r3 >= r6) goto L_0x0083
            java.lang.Object r7 = r1.get(r3)
            cxi r7 = (p000.cxi) r7
            long r8 = r7.f5911c
            long r10 = r14.f5989c
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 == 0) goto L_0x0080
            r4.add(r7)
            long r8 = r5.f5911c
            long r10 = r14.f5989c
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 == 0) goto L_0x007f
            int r8 = p000.flw.m9197b(r5, r7)
            if (r8 < 0) goto L_0x007f
            goto L_0x0080
        L_0x007f:
            r5 = r7
        L_0x0080:
            int r3 = r3 + 1
            goto L_0x005d
        L_0x0083:
            int r14 = r4.size()
            if (r14 <= r2) goto L_0x009e
            r14 = 5
            java.lang.Object r14 = r5.mo8790b((int) r14)
            iir r14 = (p000.iir) r14
            r14.mo8503a((p000.iix) r5)
            r14.mo8749a((java.lang.Iterable) r4)
            iix r14 = r14.mo8770g()
            r5 = r14
            cxi r5 = (p000.cxi) r5
            goto L_0x009f
        L_0x009e:
        L_0x009f:
            r14 = 0
            dls r14 = r15.mo4295a((p000.cxi) r5, (p000.dik) r14)
            r0.mo4637a((p000.eaq) r14)
            goto L_0x013e
        L_0x00a9:
            eav r15 = r15.f6934a
            dob r0 = new dob
            r0.<init>(r14)
            java.util.ArrayList r14 = new java.util.ArrayList
            java.util.List r1 = r15.f7803c
            r14.<init>(r1)
            r15.f7803c = r14
            r14 = 0
            r1 = 0
        L_0x00bb:
            java.util.List r4 = r15.f7803c
            int r4 = r4.size()
            if (r14 >= r4) goto L_0x00f3
            java.util.List r4 = r15.f7803c
            java.lang.Object r4 = r4.get(r14)
            eaq r4 = (p000.eaq) r4
            boolean r4 = r0.test(r4)
            if (r4 == 0) goto L_0x00f0
            int r4 = r15.f7804d
            if (r14 != r4) goto L_0x00e2
            java.util.List r1 = r15.f7803c
            java.lang.Object r1 = r1.get(r4)
            eaq r1 = (p000.eaq) r1
            r1.mo4230a((int) r2)
            r1 = 1
            goto L_0x00e8
        L_0x00e2:
            if (r14 >= r4) goto L_0x00e8
            int r4 = r4 + -1
            r15.f7804d = r4
        L_0x00e8:
            java.util.List r4 = r15.f7803c
            r4.remove(r14)
            int r14 = r14 + -1
            goto L_0x00f1
        L_0x00f0:
        L_0x00f1:
            int r14 = r14 + r2
            goto L_0x00bb
        L_0x00f3:
            int r14 = r15.f7804d
            java.util.List r0 = r15.f7803c
            int r0 = r0.size()
            if (r14 < r0) goto L_0x0107
            java.util.List r14 = r15.f7803c
            int r14 = r14.size()
            int r14 = r14 + -1
            r15.f7804d = r14
        L_0x0107:
            int r14 = r15.f7804d
            if (r14 < 0) goto L_0x010c
            goto L_0x010f
        L_0x010c:
            r15.f7804d = r3
        L_0x010f:
            gwd r14 = r15.f7802b
            java.util.List r0 = r15.f7803c
            r14.mo7129a((java.util.List) r0)
            if (r1 == 0) goto L_0x013e
            int r14 = r15.f7804d
            java.util.List r0 = r15.f7803c
            int r0 = r0.size()
            if (r14 >= r0) goto L_0x013e
            java.util.List r14 = r15.f7803c
            int r0 = r15.f7804d
            java.lang.Object r14 = r14.get(r0)
            eaq r14 = (p000.eaq) r14
            eau r0 = r15.f7801a
            int r1 = r15.f7804d
            int r0 = r0.mo4633a(r1)
            r14.mo4230a((int) r0)
            j$.util.Optional r14 = r15.f7805e
            j$.util.function.Consumer r15 = p000.eas.f7796a
            r14.ifPresent(r15)
        L_0x013e:
            dnn r14 = r13.f6868a
            blu r14 = r14.f6904m
            r15 = 2131886335(0x7f1200ff, float:1.9407246E38)
            r14.mo2572a((int) r15)
            dnn r14 = r13.f6868a
            dog r14 = r14.f6878H
            eav r14 = r14.f6934a
            java.util.List r14 = r14.f7803c
            boolean r14 = r14.isEmpty()
            if (r14 != 0) goto L_0x0157
            goto L_0x015e
        L_0x0157:
            dnn r14 = r13.f6868a
            cnh r14 = r14.f6898g
            r14.mo3275f()
        L_0x015e:
            dnn r14 = r13.f6868a
            r14.f6883M = false
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dnl.mo2650a(java.lang.Object, java.lang.Object):void");
    }
}
