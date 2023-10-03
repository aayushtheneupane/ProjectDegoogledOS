package p000;

import java.io.IOException;
import java.util.concurrent.Callable;

/* renamed from: hiy */
/* compiled from: PG */
final /* synthetic */ class hiy implements Callable {

    /* renamed from: a */
    private final hiz f12837a;

    /* renamed from: b */
    private final long f12838b;

    public hiy(hiz hiz, long j) {
        this.f12837a = hiz;
        this.f12838b = j;
    }

    public final Object call() {
        hiz hiz = this.f12837a;
        long j = this.f12838b;
        hjy hjy = hjy.f12891f;
        hiz.f12840b.writeLock().lock();
        try {
            hjy = hiz.mo7490c();
        } catch (IOException e) {
            hqo.m11927b(e);
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
        hjy hjy2 = (hjy) g.f14318b;
        hjy2.f12893a |= 2;
        hjy2.f12896d = j;
        try {
            hiz.mo7487a((hjy) g.mo8770g());
        } catch (IOException e2) {
            ((hvv) ((hvv) ((hvv) hiz.f12839a.mo8180b()).mo8202a((Throwable) e2)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManagerDataStore", "lambda$getLastWakeupAndSetNewWakeup$7", 457, "SyncManagerDataStore.java")).mo8203a("Error writing sync data file. Cannot update last wakeup.");
        }
        hiz.f12840b.writeLock().unlock();
        int i = hjy.f12893a;
        if ((i & 2) != 0) {
            return Long.valueOf(hjy.f12896d);
        }
        if ((i & 1) != 0) {
            return Long.valueOf(hjy.f12894b);
        }
        return -1L;
    }
}
