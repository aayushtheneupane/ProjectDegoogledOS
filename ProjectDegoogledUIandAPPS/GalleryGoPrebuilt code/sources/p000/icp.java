package p000;

import java.io.Closeable;
import java.util.IdentityHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import p003j$.util.Map;
import p003j$.util.Map$$CC;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiFunction;
import p003j$.util.function.Function;

/* renamed from: icp */
/* compiled from: PG */
public final class icp extends IdentityHashMap implements Closeable, Map, icw {

    /* renamed from: a */
    private volatile boolean f13887a;

    /* renamed from: b */
    private volatile CountDownLatch f13888b;

    public icp() {
    }

    public final Object compute(Object obj, BiFunction biFunction) {
        return Map$$CC.compute$$dflt$$(this, obj, biFunction);
    }

    public final Object computeIfAbsent(Object obj, Function function) {
        return Map$$CC.computeIfAbsent$$dflt$$(this, obj, function);
    }

    public final Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map$$CC.computeIfPresent$$dflt$$(this, obj, biFunction);
    }

    public final void forEach(BiConsumer biConsumer) {
        Map$$CC.forEach$$dflt$$(this, biConsumer);
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        return Map$$CC.getOrDefault$$dflt$$(this, obj, obj2);
    }

    public final Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map$$CC.merge$$dflt$$(this, obj, obj2, biFunction);
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        return Map$$CC.putIfAbsent$$dflt$$(this, obj, obj2);
    }

    public final boolean remove(Object obj, Object obj2) {
        return Map$$CC.remove$$dflt$$(this, obj, obj2);
    }

    public final Object replace(Object obj, Object obj2) {
        return Map$$CC.replace$$dflt$$(this, obj, obj2);
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        return Map$$CC.replace$$dflt$$(this, obj, obj2, obj3);
    }

    public final void replaceAll(BiFunction biFunction) {
        Map$$CC.replaceAll$$dflt$$(this, biFunction);
    }

    public /* synthetic */ icp(byte[] bArr) {
    }

    /* renamed from: a */
    public final void mo8381a(Closeable closeable, Executor executor) {
        ife.m12898e((Object) executor);
        if (closeable != null) {
            synchronized (this) {
                if (!this.f13887a) {
                    put(closeable, executor);
                } else {
                    idb.m12701a(closeable, executor);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r0.hasNext() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        r1 = (java.util.Map.Entry) r0.next();
        p000.idb.m12701a((java.io.Closeable) r1.getKey(), (java.util.concurrent.Executor) r1.getValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000d, code lost:
        r0 = entrySet().iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void close() {
        /*
            r3 = this;
            boolean r0 = r3.f13887a
            if (r0 != 0) goto L_0x003a
            monitor-enter(r3)
            boolean r0 = r3.f13887a     // Catch:{ all -> 0x0037 }
            if (r0 != 0) goto L_0x0035
            r0 = 1
            r3.f13887a = r0     // Catch:{ all -> 0x0037 }
            monitor-exit(r3)     // Catch:{ all -> 0x0037 }
            java.util.Set r0 = r3.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0015:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0031
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.io.Closeable r2 = (java.io.Closeable) r2
            java.lang.Object r1 = r1.getValue()
            java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
            p000.idb.m12701a((java.io.Closeable) r2, (java.util.concurrent.Executor) r1)
            goto L_0x0015
        L_0x0031:
            r3.clear()
            return
        L_0x0035:
            monitor-exit(r3)     // Catch:{ all -> 0x0037 }
            return
        L_0x0037:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0037 }
            throw r0
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.icp.close():void");
    }

    /* renamed from: b */
    public final void mo8382b(Closeable closeable, Executor executor) {
        ife.m12898e((Object) executor);
        if (closeable != null) {
            mo8381a(closeable, executor);
        }
    }
}
