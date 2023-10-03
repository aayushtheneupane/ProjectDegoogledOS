package p000;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: hir */
/* compiled from: PG */
final /* synthetic */ class hir implements Callable {

    /* renamed from: a */
    private final hiz f12824a;

    public hir(hiz hiz) {
        this.f12824a = hiz;
    }

    public final Object call() {
        iir iir;
        long j;
        Long valueOf;
        ReentrantReadWriteLock reentrantReadWriteLock;
        hiz hiz = this.f12824a;
        hiz.f12840b.writeLock().lock();
        try {
            if (hiz.f12843e.get()) {
                valueOf = Long.valueOf(hiz.f12844f);
                reentrantReadWriteLock = hiz.f12840b;
            } else {
                hjy c = hiz.mo7490c();
                j = c.f12894b;
                iir = hjy.f12891f.mo8793g();
                iir.mo8503a((iix) c);
                if (j <= 0) {
                    long a = hiz.f12842d.mo5370a();
                    hiz.f12844f = a;
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    hjy hjy = (hjy) iir.f14318b;
                    hjy.f12893a |= 1;
                    hjy.f12894b = a;
                    try {
                        hiz.mo7487a((hjy) iir.mo8770g());
                        hiz.f12843e.set(true);
                    } catch (IOException e) {
                        ((hvv) ((hvv) ((hvv) hiz.f12839a.mo8180b()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManagerDataStore", "lambda$getSyncEpoch$0", 110, "SyncManagerDataStore.java")).mo8203a("Could not write sync epoch. Using current time but future runs may be delayed.");
                        hiz.f12843e.set(false);
                    }
                    valueOf = Long.valueOf(hiz.f12844f);
                    reentrantReadWriteLock = hiz.f12840b;
                } else {
                    hiz.f12844f = j;
                    hiz.f12843e.set(true);
                    valueOf = Long.valueOf(hiz.f12844f);
                    reentrantReadWriteLock = hiz.f12840b;
                }
            }
        } catch (IOException e2) {
            hiz.mo7488a((Throwable) e2);
            j = hiz.f12842d.mo5370a();
            iir = hjy.f12891f.mo8793g();
        } catch (Throwable th) {
            hiz.f12840b.writeLock().unlock();
            throw th;
        }
        reentrantReadWriteLock.writeLock().unlock();
        return valueOf;
    }
}
