package p000;

import android.content.Context;
import java.util.LinkedHashSet;
import java.util.Set;

/* renamed from: aka */
/* compiled from: PG */
public abstract class aka {

    /* renamed from: a */
    public final Context f658a;

    /* renamed from: b */
    public final Object f659b = new Object();

    /* renamed from: c */
    public final Set f660c = new LinkedHashSet();

    /* renamed from: d */
    public Object f661d;

    /* renamed from: e */
    private final amz f662e;

    static {
        iol.m14236b("ConstraintTracker");
    }

    /* renamed from: b */
    public abstract Object mo560b();

    /* renamed from: c */
    public abstract void mo562c();

    /* renamed from: d */
    public abstract void mo563d();

    public aka(Context context, amz amz) {
        this.f658a = context.getApplicationContext();
        this.f662e = amz;
    }

    /* renamed from: a */
    public final void mo565a(aji aji) {
        synchronized (this.f659b) {
            if (this.f660c.remove(aji) && this.f660c.isEmpty()) {
                mo563d();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo566a(java.lang.Object r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f659b
            monitor-enter(r0)
            java.lang.Object r1 = r3.f661d     // Catch:{ all -> 0x002c }
            if (r1 != r4) goto L_0x0008
            goto L_0x0011
        L_0x0008:
            if (r1 == 0) goto L_0x0013
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0011
            goto L_0x0013
        L_0x0011:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x0013:
            r3.f661d = r4     // Catch:{ all -> 0x002c }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x002c }
            java.util.Set r1 = r3.f660c     // Catch:{ all -> 0x002c }
            r4.<init>(r1)     // Catch:{ all -> 0x002c }
            amz r1 = r3.f662e     // Catch:{ all -> 0x002c }
            anb r1 = (p000.anb) r1     // Catch:{ all -> 0x002c }
            java.util.concurrent.Executor r1 = r1.f811c     // Catch:{ all -> 0x002c }
            ajz r2 = new ajz     // Catch:{ all -> 0x002c }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x002c }
            r1.execute(r2)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.aka.mo566a(java.lang.Object):void");
    }
}
