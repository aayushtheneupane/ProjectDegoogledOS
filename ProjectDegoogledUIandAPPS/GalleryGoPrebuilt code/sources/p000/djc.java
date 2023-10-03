package p000;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: djc */
/* compiled from: PG */
final /* synthetic */ class djc implements Runnable {

    /* renamed from: a */
    private final djd f6651a;

    /* renamed from: b */
    private final AtomicBoolean f6652b;

    public djc(djd djd, AtomicBoolean atomicBoolean) {
        this.f6651a = djd;
        this.f6652b = atomicBoolean;
    }

    public final void run() {
        djd djd = this.f6651a;
        if (this.f6652b.getAndSet(false)) {
            djd.f6657e.mo2554a();
            djd.f6658f.mo7096a(ife.m12820a((Object) null), (Object) "MEDIA_STORE_SYNC_STATE_KEY");
        }
    }
}
