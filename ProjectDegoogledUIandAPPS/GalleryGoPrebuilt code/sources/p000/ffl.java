package p000;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: ffl */
/* compiled from: PG */
public final class ffl implements fcv {

    /* renamed from: a */
    public final iek f9466a;

    /* renamed from: b */
    public final fdd f9467b;

    /* renamed from: c */
    private final ffp f9468c;

    /* renamed from: d */
    private final fft f9469d;

    /* renamed from: e */
    private final ffs f9470e;

    public ffl(iek iek, ffp ffp, fft fft, fdd fdd) {
        this.f9466a = iek;
        this.f9468c = ffp;
        this.f9469d = fft;
        this.f9467b = fdd;
        ffs a = fft.mo5577a(-1);
        this.f9470e = a;
        ife.m12869b((Object) a, (Object) "AVE handler is required.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x0131  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m8739a(android.util.SparseArray r17, java.util.List r18, android.util.SparseIntArray r19, p000.C0293kq r20, java.util.Map r21) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            r2 = r19
            r3 = r21
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r5 = 0
            r6 = 0
        L_0x000f:
            int r7 = r18.size()
            if (r6 >= r7) goto L_0x0177
            java.lang.Object r7 = r1.get(r6)
            fdx r7 = (p000.fdx) r7
            fdh r8 = r7.f9343d
            if (r8 == 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            fdh r8 = p000.fdh.f9308e
        L_0x0022:
            int r8 = r8.f9313d
            int r8 = p000.ife.m12861b((int) r8)
            r9 = 1
            if (r8 != 0) goto L_0x002c
            goto L_0x002e
        L_0x002c:
            if (r8 != r9) goto L_0x016f
        L_0x002e:
            iih r8 = p000.ffg.f9450a
            r7.mo8786b(r8)
            iim r10 = r7.f14321j
            iiw r11 = r8.f14244d
            java.lang.Object r10 = r10.mo8728b((p000.iil) r11)
            if (r10 != 0) goto L_0x0040
            java.lang.Object r8 = r8.f14242b
            goto L_0x0044
        L_0x0040:
            java.lang.Object r8 = r8.mo8711a(r10)
        L_0x0044:
            ffi r8 = (p000.ffi) r8
            boolean r8 = r8.f9455b
            if (r8 == 0) goto L_0x016a
            r4.clear()
            iai r8 = p000.iai.f13716g
            iir r8 = r8.mo8793g()
            iah r8 = (p000.iah) r8
            int r10 = r7.f9341b
            boolean r11 = r8.f14319c
            if (r11 != 0) goto L_0x005c
            goto L_0x0061
        L_0x005c:
            r8.mo8751b()
            r8.f14319c = r5
        L_0x0061:
            iix r11 = r8.f14318b
            iai r11 = (p000.iai) r11
            int r12 = r11.f13718a
            r12 = r12 | r9
            r11.f13718a = r12
            r11.f13719b = r10
            fft r10 = r0.f9469d
            ijc r11 = r7.f9342c
            r10.mo5578a(r7, r11, r3, r4)
            iih r10 = p000.fex.f9413a
            r7.mo8786b(r10)
            iim r11 = r7.f14321j
            iiw r10 = r10.f14244d
            boolean r10 = r11.mo8726a((p000.iil) r10)
            if (r10 == 0) goto L_0x00b1
            iih r10 = p000.fex.f9413a
            r7.mo8786b(r10)
            iim r7 = r7.f14321j
            iiw r11 = r10.f14244d
            java.lang.Object r7 = r7.mo8728b((p000.iil) r11)
            if (r7 != 0) goto L_0x0094
            java.lang.Object r7 = r10.f14242b
            goto L_0x0098
        L_0x0094:
            java.lang.Object r7 = r10.mo8711a(r7)
        L_0x0098:
            fez r7 = (p000.fez) r7
            int r7 = r7.f9422b
            boolean r10 = r8.f14319c
            if (r10 == 0) goto L_0x00a5
            r8.mo8751b()
            r8.f14319c = r5
        L_0x00a5:
            iix r10 = r8.f14318b
            iai r10 = (p000.iai) r10
            int r11 = r10.f13718a
            r11 = r11 | 2
            r10.f13718a = r11
            r10.f13720c = r7
        L_0x00b1:
            int r7 = r2.valueAt(r6)
            r10 = r6
        L_0x00b6:
            r11 = -1
            if (r7 == r11) goto L_0x00ec
            java.lang.Object r10 = r1.get(r7)
            fdx r10 = (p000.fdx) r10
            fdh r11 = r10.f9343d
            if (r11 == 0) goto L_0x00c4
            goto L_0x00c6
        L_0x00c4:
            fdh r11 = p000.fdh.f9308e
        L_0x00c6:
            int r11 = r11.f9313d
            int r11 = p000.ife.m12861b((int) r11)
            if (r11 != 0) goto L_0x00cf
            goto L_0x00d7
        L_0x00cf:
            if (r11 == r9) goto L_0x00d7
            r7 = r17
            r8 = r20
            goto L_0x0173
        L_0x00d7:
            int r11 = r10.f9341b
            r8.mo8330a(r11)
            fft r11 = r0.f9469d
            ijc r12 = r10.f9342c
            r11.mo5578a(r10, r12, r3, r4)
            int r10 = r2.valueAt(r7)
            r15 = r10
            r10 = r7
            r7 = r15
            goto L_0x00b6
        L_0x00ec:
            r7 = r17
            java.lang.Object r10 = r7.get(r10)
            ffc r10 = (p000.ffc) r10
            java.util.List r10 = r10.mo5573a()
            r11 = 1
        L_0x00f9:
            int r12 = r10.size()
            if (r11 >= r12) goto L_0x0150
            java.lang.Object r12 = r10.get(r11)
            fdx r12 = (p000.fdx) r12
            fdh r13 = r12.f9343d
            if (r13 == 0) goto L_0x010a
            goto L_0x010c
        L_0x010a:
            fdh r13 = p000.fdh.f9308e
        L_0x010c:
            int r13 = r13.f9310a
            r13 = r13 & r9
            if (r13 == 0) goto L_0x0127
            fdh r13 = r12.f9343d
            if (r13 == 0) goto L_0x0116
            goto L_0x0118
        L_0x0116:
            fdh r13 = p000.fdh.f9308e
        L_0x0118:
            ial r13 = r13.f9311b
            if (r13 == 0) goto L_0x011d
            goto L_0x011f
        L_0x011d:
            ial r13 = p000.ial.f13725d
        L_0x011f:
            int r13 = r13.f13727a
            r13 = r13 & 2
            if (r13 != 0) goto L_0x0127
            r13 = 0
            goto L_0x0129
        L_0x0127:
            r13 = 1
        L_0x0129:
            p000.ife.m12890c((boolean) r13)
            fdh r13 = r12.f9343d
            if (r13 == 0) goto L_0x0131
            goto L_0x0133
        L_0x0131:
            fdh r13 = p000.fdh.f9308e
        L_0x0133:
            int r13 = r13.f9313d
            int r13 = p000.ife.m12861b((int) r13)
            if (r13 != 0) goto L_0x013c
            goto L_0x013e
        L_0x013c:
            if (r13 != r9) goto L_0x014d
        L_0x013e:
            int r13 = r12.f9341b
            r8.mo8330a(r13)
            fft r13 = r0.f9469d
            ijc r14 = r12.f9342c
            r13.mo5578a(r12, r14, r3, r4)
            int r11 = r11 + 1
            goto L_0x00f9
        L_0x014d:
            r8 = r20
            goto L_0x0173
        L_0x0150:
            iix r8 = r8.mo8770g()
            iai r8 = (p000.iai) r8
            ffs r9 = r0.f9470e
            ieh r8 = r9.mo3874a(r8)
            java.lang.String r9 = "AVE populator is required."
            p000.ife.m12869b((java.lang.Object) r8, (java.lang.Object) r9)
            r4.add(r8)
            r8 = r20
            r8.mo5576a(r4)
            goto L_0x0173
        L_0x016a:
            r7 = r17
            r8 = r20
            goto L_0x0173
        L_0x016f:
            r7 = r17
            r8 = r20
        L_0x0173:
            int r6 = r6 + 1
            goto L_0x000f
        L_0x0177:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ffl.m8739a(android.util.SparseArray, java.util.List, android.util.SparseIntArray, kq, java.util.Map):void");
    }

    /* renamed from: a */
    public final Set mo5492a() {
        return hto.m12120a((Object) ffe.class);
    }

    /* renamed from: a */
    public final ieh mo5491a(fcz fcz) {
        String a = this.f9468c.mo3871a();
        ieh b = this.f9468c.mo3872b();
        ArrayList arrayList = new ArrayList();
        ffj ffj = new ffj(this, arrayList, a, fcz, b);
        HashMap hashMap = new HashMap();
        ffe ffe = (ffe) fcz.f9291a;
        SparseArray sparseArray = new SparseArray();
        for (ffd ffd : ffe.f9442a) {
            if (ffd.f9439a == 1) {
                sparseArray.put(ffd.mo5575c(), ffd);
            }
        }
        m8739a(sparseArray, ffe.f9443b, ffe.f9444c, ffj, hashMap);
        sparseArray.clear();
        for (ffd ffd2 : ffe.f9442a) {
            if (ffd2.f9439a == 2) {
                sparseArray.put(ffd2.mo5575c(), ffd2);
            }
        }
        m8739a(sparseArray, ffe.f9445d, ffe.f9446e, ffj, hashMap);
        return ife.m12883c((Iterable) arrayList).mo8443a(ife.m12887c(), (Executor) idh.f13918a);
    }
}
