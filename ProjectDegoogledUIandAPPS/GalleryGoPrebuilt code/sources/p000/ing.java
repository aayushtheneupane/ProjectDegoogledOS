package p000;

import java.util.concurrent.Callable;

/* renamed from: ing */
/* compiled from: PG */
public final /* synthetic */ class ing implements Callable {

    /* renamed from: a */
    private final imu f14557a;

    /* renamed from: b */
    private final inc f14558b;

    /* renamed from: c */
    private final Callable f14559c;

    public ing(imu imu, inc inc, Callable callable) {
        this.f14557a = imu;
        this.f14558b = inc;
        this.f14559c = callable;
    }

    public final Object call() {
        imu imu = this.f14557a;
        inc inc = this.f14558b;
        Callable callable = this.f14559c;
        ine.m14181a(imu);
        inc.mo9014c(imu);
        try {
            return callable.call();
        } finally {
            inc.mo9015d(imu);
            ine.m14180a();
        }
    }
}
