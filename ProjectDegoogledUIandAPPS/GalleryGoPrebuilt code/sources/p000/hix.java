package p000;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: hix */
/* compiled from: PG */
final /* synthetic */ class hix implements Runnable {

    /* renamed from: a */
    private final hiz f12835a;

    /* renamed from: b */
    private final Set f12836b;

    public hix(hiz hiz, Set set) {
        this.f12835a = hiz;
        this.f12836b = set;
    }

    public final void run() {
        hjy hjy;
        ReentrantReadWriteLock reentrantReadWriteLock;
        hiz hiz = this.f12835a;
        Set set = this.f12836b;
        hiz.f12840b.writeLock().lock();
        try {
            hjy = hjy.f12891f;
            hjy = hiz.mo7490c();
        } catch (IOException e) {
            if (!hiz.mo7488a((Throwable) e)) {
                ((hvv) ((hvv) ((hvv) hiz.f12839a.mo8178a()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManagerDataStore", "lambda$removeSyncRequests$6", 405, "SyncManagerDataStore.java")).mo8203a("Unable to read or clear store. Cannot remove account.");
                reentrantReadWriteLock = hiz.f12840b;
            }
        } catch (Throwable th) {
            hiz.f12840b.writeLock().unlock();
            throw th;
        }
        iir g = hjy.f12891f.mo8793g();
        g.mo8503a((iix) hjy);
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        ((hjy) g.f14318b).f12895c = hjy.m13615l();
        for (hjx hjx : hjy.f12895c) {
            hka hka = hjx.f12887b;
            if (hka == null) {
                hka = hka.f12906d;
            }
            if (!set.contains(hjh.m11577a(hka))) {
                g.mo8747a(hjx);
            }
        }
        try {
            hiz.mo7487a((hjy) g.mo8770g());
        } catch (IOException e2) {
            ((hvv) ((hvv) ((hvv) hiz.f12839a.mo8178a()).mo8202a((Throwable) e2)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManagerDataStore", "lambda$removeSyncRequests$6", 425, "SyncManagerDataStore.java")).mo8203a("Error writing sync data file. Cannot remove account.");
        }
        reentrantReadWriteLock = hiz.f12840b;
        reentrantReadWriteLock.writeLock().unlock();
    }
}
