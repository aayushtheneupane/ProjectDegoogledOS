package p000;

import java.util.Queue;

/* renamed from: auj */
/* compiled from: PG */
abstract class auj {

    /* renamed from: a */
    private final Queue f1714a = bfp.m2429a(20);

    /* renamed from: b */
    public abstract auv mo1641b();

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final auv mo1639a() {
        auv auv = (auv) this.f1714a.poll();
        return auv == null ? mo1641b() : auv;
    }

    /* renamed from: a */
    public final void mo1640a(auv auv) {
        if (this.f1714a.size() < 20) {
            this.f1714a.offer(auv);
        }
    }
}
