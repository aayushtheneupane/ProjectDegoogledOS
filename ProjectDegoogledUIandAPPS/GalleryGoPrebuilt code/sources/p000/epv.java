package p000;

import java.util.Collections;
import java.util.Set;

/* renamed from: epv */
/* compiled from: PG */
public abstract class epv extends epi implements ekm, epw {

    /* renamed from: q */
    public final Set f8806q;

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0062, code lost:
        r0 = th;
     */
    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected epv(android.content.Context r14, android.os.Looper r15, int r16, p000.epk r17, p000.ekt r18, p000.eku r19) {
        /*
            r13 = this;
            r0 = r17
            java.lang.Object r1 = p000.epz.f8818a
            monitor-enter(r1)
            epz r2 = p000.epz.f8819b     // Catch:{ all -> 0x005e }
            if (r2 != 0) goto L_0x0014
            eqb r2 = new eqb     // Catch:{ all -> 0x005e }
            android.content.Context r3 = r14.getApplicationContext()     // Catch:{ all -> 0x005e }
            r2.<init>(r3)     // Catch:{ all -> 0x005e }
            p000.epz.f8819b = r2     // Catch:{ all -> 0x005e }
        L_0x0014:
            monitor-exit(r1)     // Catch:{ all -> 0x005e }
            epz r7 = p000.epz.f8819b
            ejw r8 = p000.ejw.f8454a
            java.lang.Object r1 = p000.abj.m85a((java.lang.Object) r18)
            emd r1 = (p000.emd) r1
            java.lang.Object r2 = p000.abj.m85a((java.lang.Object) r19)
            eob r2 = (p000.eob) r2
            ept r10 = new ept
            r10.<init>(r1)
            epu r11 = new epu
            r11.<init>(r2)
            java.lang.String r12 = r0.f8780e
            r4 = r13
            r5 = r14
            r6 = r15
            r9 = r16
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12)
            java.util.Set r0 = r0.f8777b
            java.util.Iterator r1 = r0.iterator()
        L_0x003f:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x005a
            java.lang.Object r2 = r1.next()
            com.google.android.gms.common.api.Scope r2 = (com.google.android.gms.common.api.Scope) r2
            boolean r2 = r0.contains(r2)
            if (r2 == 0) goto L_0x0052
            goto L_0x003f
        L_0x0052:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Expanding scopes is not permitted, use implied scopes instead"
            r0.<init>(r1)
            throw r0
        L_0x005a:
            r2 = r13
            r2.f8806q = r0
            return
        L_0x005e:
            r0 = move-exception
            r2 = r13
        L_0x0060:
            monitor-exit(r1)     // Catch:{ all -> 0x0062 }
            throw r0
        L_0x0062:
            r0 = move-exception
            goto L_0x0060
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.epv.<init>(android.content.Context, android.os.Looper, int, epk, ekt, eku):void");
    }

    /* renamed from: s */
    public final ejt[] mo5128s() {
        return new ejt[0];
    }

    /* renamed from: i */
    public final Set mo4936i() {
        return mo5120g() ? this.f8806q : Collections.emptySet();
    }
}
