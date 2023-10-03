package androidx.work;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;

/* compiled from: PG */
public final class WorkerParameters {

    /* renamed from: a */
    public UUID f1165a;

    /* renamed from: b */
    public ahf f1166b;

    /* renamed from: c */
    public Set f1167c;

    /* renamed from: d */
    public Executor f1168d;

    /* renamed from: e */
    public ahv f1169e;

    public WorkerParameters(UUID uuid, ahf ahf, Collection collection, Executor executor, ahv ahv) {
        this.f1165a = uuid;
        this.f1166b = ahf;
        this.f1167c = new HashSet(collection);
        this.f1168d = executor;
        this.f1169e = ahv;
    }
}
