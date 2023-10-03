package p000;

import android.content.Context;
import java.util.concurrent.Executor;

/* renamed from: bsz */
/* compiled from: PG */
public final class bsz {

    /* renamed from: a */
    public final Object f3508a = new Object();

    /* renamed from: b */
    public cxd f3509b = cxd.f5884h;

    /* renamed from: c */
    public grf f3510c;

    /* renamed from: d */
    public final Context f3511d;

    /* renamed from: e */
    public final cxo f3512e;

    /* renamed from: f */
    public final gus f3513f;

    /* renamed from: g */
    public final iel f3514g;

    /* renamed from: h */
    public final inw f3515h;

    /* renamed from: i */
    private final iel f3516i;

    public bsz(Context context, cxo cxo, gus gus, iel iel, iel iel2, inw inw) {
        this.f3511d = context;
        this.f3512e = cxo;
        this.f3514g = iel;
        this.f3516i = iel2;
        this.f3513f = gus;
        this.f3515h = inw;
    }

    /* renamed from: c */
    private static cxd m3545c(cxd cxd) {
        iir iir = (iir) cxd.mo8790b(5);
        iir.mo8503a((iix) cxd);
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        cxd cxd2 = (cxd) iir.f14318b;
        cxd cxd3 = cxd.f5884h;
        if (cxd2.f5887b == 1) {
            cxd2.f5887b = 0;
            cxd2.f5888c = null;
        }
        return (cxd) iir.mo8770g();
    }

    /* renamed from: b */
    public final ieh mo2731b(cxd cxd) {
        return gte.m10770a(mo2730a(cxd), (hpr) new bsx(cxd), (Executor) this.f3516i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0012, code lost:
        if (m3545c(r4).equals(r3.f3509b) == false) goto L_0x0014;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.ieh mo2730a(p000.cxd r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f3508a
            monitor-enter(r0)
            grf r1 = r3.f3510c     // Catch:{ all -> 0x0030 }
            if (r1 != 0) goto L_0x0008
            goto L_0x0014
        L_0x0008:
            cxd r1 = r3.f3509b     // Catch:{ all -> 0x0030 }
            cxd r2 = m3545c(r4)     // Catch:{ all -> 0x0030 }
            boolean r1 = r2.equals(r1)     // Catch:{ all -> 0x0030 }
            if (r1 != 0) goto L_0x0028
        L_0x0014:
            cxd r4 = m3545c(r4)     // Catch:{ all -> 0x0030 }
            r3.f3509b = r4     // Catch:{ all -> 0x0030 }
            grf r1 = new grf     // Catch:{ all -> 0x0030 }
            bsw r2 = new bsw     // Catch:{ all -> 0x0030 }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x0030 }
            iel r4 = r3.f3514g     // Catch:{ all -> 0x0030 }
            r1.<init>(r2, r4)     // Catch:{ all -> 0x0030 }
            r3.f3510c = r1     // Catch:{ all -> 0x0030 }
        L_0x0028:
            grf r4 = r3.f3510c     // Catch:{ all -> 0x0030 }
            ieh r4 = r4.mo6948a()     // Catch:{ all -> 0x0030 }
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return r4
        L_0x0030:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bsz.mo2730a(cxd):ieh");
    }
}
