package p000;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: bmv */
/* compiled from: PG */
final /* synthetic */ class bmv implements Runnable {

    /* renamed from: a */
    private final bmy f3163a;

    /* renamed from: b */
    private final AtomicBoolean f3164b;

    public bmv(bmy bmy, AtomicBoolean atomicBoolean) {
        this.f3163a = bmy;
        this.f3164b = atomicBoolean;
    }

    public final void run() {
        bmy bmy = this.f3163a;
        if (this.f3164b.getAndSet(false)) {
            bmy.f3169c.mo2554a();
            bmy.f3170d.mo7096a(ife.m12820a((Object) null), (Object) "MEDIA_STORE_SYNC_STATE_KEY");
        }
    }
}
