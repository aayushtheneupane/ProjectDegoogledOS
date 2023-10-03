package p000;

import java.util.Set;

/* renamed from: hjj */
/* compiled from: PG */
public abstract class hjj {
    /* renamed from: a */
    public abstract Set mo7449a();

    /* renamed from: b */
    public abstract long mo7450b();

    /* renamed from: c */
    public abstract hpy mo7451c();

    /* renamed from: a */
    public static hjj m11581a(hjj hjj, hjj hjj2) {
        ife.m12896d(hjj.mo7449a().equals(hjj2.mo7449a()));
        hji d = m11582d();
        d.mo7496a(hjj.mo7449a());
        d.f12852a = Math.min(hjj.mo7450b(), hjj2.mo7450b());
        hpy c = hjj.mo7451c();
        hpy c2 = hjj2.mo7451c();
        if (c.mo7646a() && c2.mo7646a()) {
            d.f12853b = hpy.m11893b(Long.valueOf(Math.min(((Long) c.mo7647b()).longValue(), ((Long) c2.mo7647b()).longValue())));
        } else if (c.mo7646a()) {
            d.f12853b = c;
        } else if (c2.mo7646a()) {
            d.f12853b = c2;
        }
        return d.mo7495a();
    }

    /* renamed from: d */
    public static final hji m11582d() {
        return new hji((byte[]) null);
    }
}
