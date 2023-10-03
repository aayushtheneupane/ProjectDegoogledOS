package p000;

import java.util.concurrent.Executor;

/* renamed from: ble */
/* compiled from: PG */
public final class ble {

    /* renamed from: a */
    private final Iterable f3101a;

    public ble(Iterable iterable) {
        this.f3101a = iterable;
    }

    /* renamed from: a */
    public final void mo2554a() {
        for (Runnable run : this.f3101a) {
            run.run();
        }
    }

    /* renamed from: a */
    public final ieh mo2553a(ieh ieh, Executor executor) {
        return gte.m10769a(ieh).mo7611a((ice) new bld(this, ieh), executor);
    }
}
