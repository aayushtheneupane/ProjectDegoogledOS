package p000;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* renamed from: flv */
/* compiled from: PG */
final class flv implements iel {

    /* renamed from: a */
    public final flu f10001a;

    /* renamed from: b */
    private final iel f10002b;

    public flv(iel iel, flu flu) {
        this.f10002b = (iel) ife.m12898e((Object) iel);
        this.f10001a = (flu) ife.m12898e((Object) flu);
    }

    public final boolean awaitTermination(long j, TimeUnit timeUnit) {
        return this.f10002b.awaitTermination(j, timeUnit);
    }

    public final void execute(Runnable runnable) {
        this.f10002b.execute(m9176b(runnable));
    }

    public final List invokeAll(Collection collection) {
        return this.f10002b.invokeAll(m9175a(collection));
    }

    public final List invokeAll(Collection collection, long j, TimeUnit timeUnit) {
        return this.f10002b.invokeAll(m9175a(collection), j, timeUnit);
    }

    public final Object invokeAny(Collection collection) {
        return this.f10002b.invokeAny(m9175a(collection));
    }

    public final Object invokeAny(Collection collection, long j, TimeUnit timeUnit) {
        return this.f10002b.invokeAny(m9175a(collection), j, timeUnit);
    }

    public final boolean isShutdown() {
        return this.f10002b.isShutdown();
    }

    public final boolean isTerminated() {
        return this.f10002b.isTerminated();
    }

    /* renamed from: a */
    public final iej schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f10002b.mo5935a(m9176b(runnable), j, timeUnit);
    }

    /* renamed from: a */
    public final iej schedule(Callable callable, long j, TimeUnit timeUnit) {
        return this.f10002b.mo5936a(m9177b(callable), j, timeUnit);
    }

    /* renamed from: a */
    public final iej scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.f10002b.mo5934a(m9176b(runnable), j, j2, timeUnit);
    }

    /* renamed from: b */
    public final iej scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.f10002b.mo5938b(m9176b(runnable), j, j2, timeUnit);
    }

    public final void shutdown() {
        this.f10002b.shutdown();
    }

    public final List shutdownNow() {
        return this.f10002b.shutdownNow();
    }

    /* renamed from: a */
    public final ieh submit(Runnable runnable) {
        return this.f10002b.mo5931a(m9176b(runnable));
    }

    /* renamed from: a */
    public final ieh submit(Runnable runnable, Object obj) {
        return this.f10002b.mo5932a(m9176b(runnable), obj);
    }

    /* renamed from: a */
    public final ieh submit(Callable callable) {
        return this.f10002b.mo5933a(m9177b(callable));
    }

    /* renamed from: b */
    private final Runnable m9176b(Runnable runnable) {
        return new fls(this, runnable);
    }

    /* renamed from: b */
    private final Callable m9177b(Callable callable) {
        return new flt(this, callable);
    }

    /* renamed from: a */
    private final List m9175a(Collection collection) {
        ArrayList arrayList = new ArrayList();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(m9177b((Callable) it.next()));
        }
        return arrayList;
    }
}
