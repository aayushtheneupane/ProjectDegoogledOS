package p000;

import java.util.concurrent.Executor;

/* renamed from: hnl */
/* compiled from: PG */
public final class hnl extends idu {
    private hnl(ieh ieh) {
        super(ieh);
    }

    /* renamed from: a */
    public static hnl m11801a(ieh ieh) {
        if (!(ieh instanceof hnl)) {
            return new hnl(ieh);
        }
        return (hnl) ieh;
    }

    /* renamed from: a */
    public final hnl mo7610a(icf icf, Executor executor) {
        return new hnl(gte.m10771a(this.f13941b, icf, executor));
    }
}
