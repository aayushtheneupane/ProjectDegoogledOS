package p000;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/* renamed from: fli */
/* compiled from: PG */
final /* synthetic */ class fli implements hqk {

    /* renamed from: a */
    private final iel f9980a;

    /* renamed from: b */
    private final int f9981b;

    /* renamed from: c */
    private final int f9982c;

    public fli(iel iel, int i, int i2) {
        this.f9980a = iel;
        this.f9981b = i;
        this.f9982c = i2;
    }

    /* renamed from: a */
    public final Object mo2652a() {
        iel iel = this.f9980a;
        int i = this.f9981b;
        int i2 = this.f9982c;
        if (iel == null) {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(i2, new flm(i), new flk((byte[]) null));
            scheduledThreadPoolExecutor.setMaximumPoolSize(i2);
            iel = ife.m12825a((ScheduledExecutorService) scheduledThreadPoolExecutor);
        }
        return new flv(iel, flj.f9983a);
    }
}
