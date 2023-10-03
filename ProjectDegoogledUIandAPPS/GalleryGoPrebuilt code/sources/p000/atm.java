package p000;

import java.util.concurrent.Executor;

/* renamed from: atm */
/* compiled from: PG */
final class atm {

    /* renamed from: a */
    public final bef f1627a;

    /* renamed from: b */
    public final Executor f1628b;

    public atm(bef bef, Executor executor) {
        this.f1627a = bef;
        this.f1628b = executor;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof atm) {
            return this.f1627a.equals(((atm) obj).f1627a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f1627a.hashCode();
    }
}
