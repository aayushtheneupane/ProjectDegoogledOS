package p000;

import java.util.concurrent.Executor;

/* renamed from: ini */
/* compiled from: PG */
final /* synthetic */ class ini implements Executor {

    /* renamed from: a */
    private final inc f14565a;

    /* renamed from: b */
    private final Executor f14566b;

    public ini(inc inc, Executor executor) {
        this.f14565a = inc;
        this.f14566b = executor;
    }

    public final void execute(Runnable runnable) {
        inc inc = this.f14565a;
        Executor executor = this.f14566b;
        ims.f14541a.set((Object) null);
        imu imu = (imu) ife.m12898e((Object) (imu) ims.f14541a.get());
        inc.mo9013b(imu);
        executor.execute(new inj(imu, runnable));
    }
}
