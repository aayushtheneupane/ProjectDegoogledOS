package p000;

import java.util.concurrent.Executor;

/* renamed from: aau */
/* compiled from: PG */
final class aau {

    /* renamed from: a */
    public static final aau f47a = new aau((Runnable) null, (Executor) null);

    /* renamed from: b */
    public final Runnable f48b;

    /* renamed from: c */
    public final Executor f49c;
    public aau next;

    public aau(Runnable runnable, Executor executor) {
        this.f48b = runnable;
        this.f49c = executor;
    }
}
