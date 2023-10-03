package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: ide */
/* compiled from: PG */
final class ide extends idf {

    /* renamed from: b */
    private final Callable f13911b;

    /* renamed from: c */
    private final /* synthetic */ idg f13912c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ide(idg idg, Callable callable, Executor executor) {
        super(idg, executor);
        this.f13912c = idg;
        this.f13911b = (Callable) ife.m12898e((Object) callable);
    }

    /* renamed from: b */
    public final Object mo8408b() {
        this.f13913a = false;
        return this.f13911b.call();
    }

    /* renamed from: a */
    public final void mo8407a(Object obj) {
        this.f13912c.mo8346b(obj);
    }

    /* renamed from: a */
    public final String mo8406a() {
        return this.f13911b.toString();
    }
}
