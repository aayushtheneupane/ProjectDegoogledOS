package p000;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* renamed from: ahr */
/* compiled from: PG */
public abstract class ahr {

    /* renamed from: a */
    public UUID f496a = UUID.randomUUID();

    /* renamed from: b */
    public alg f497b;

    /* renamed from: c */
    public final Set f498c = new HashSet();

    public ahr(Class cls) {
        this.f497b = new alg(this.f496a.toString(), cls.getName());
        mo497a(cls.getName());
    }

    /* renamed from: a */
    public abstract ahr mo484a();

    /* renamed from: b */
    public abstract ahs mo485b();

    /* renamed from: a */
    public final void mo497a(String str) {
        this.f498c.add(str);
    }

    /* renamed from: c */
    public final ahs mo498c() {
        ahs b = mo485b();
        this.f496a = UUID.randomUUID();
        alg alg = new alg(this.f497b);
        this.f497b = alg;
        alg.f713b = this.f496a.toString();
        return b;
    }

    /* renamed from: a */
    public final ahr mo495a(ahb ahb) {
        this.f497b.f721j = ahb;
        return mo484a();
    }

    /* renamed from: a */
    public final ahr mo494a(long j, TimeUnit timeUnit) {
        this.f497b.f718g = timeUnit.toMillis(j);
        return mo484a();
    }

    /* renamed from: a */
    public final ahr mo496a(ahf ahf) {
        this.f497b.f716e = ahf;
        return mo484a();
    }
}
