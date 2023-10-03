package p000;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: hiv */
/* compiled from: PG */
final /* synthetic */ class hiv implements Runnable {

    /* renamed from: a */
    private final hiz f12829a;

    /* renamed from: b */
    private final hjh f12830b;

    /* renamed from: c */
    private final long f12831c;

    /* renamed from: d */
    private final boolean f12832d;

    public hiv(hiz hiz, hjh hjh, long j, boolean z) {
        this.f12829a = hiz;
        this.f12830b = hjh;
        this.f12831c = j;
        this.f12832d = z;
    }

    public final void run() {
        hjy hjy;
        ReentrantReadWriteLock reentrantReadWriteLock;
        hiz hiz = this.f12829a;
        hjh hjh = this.f12830b;
        long j = this.f12831c;
        boolean z = this.f12832d;
        hiz.f12840b.writeLock().lock();
        try {
            hjy = hjy.f12891f;
            hjy = hiz.mo7490c();
        } catch (IOException e) {
            IOException iOException = e;
            if (!hiz.mo7488a((Throwable) iOException)) {
                ((hvv) ((hvv) ((hvv) hiz.f12839a.mo8178a()).mo8202a((Throwable) iOException)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManagerDataStore", "lambda$updateLastSyncTime$4", 285, "SyncManagerDataStore.java")).mo8203a("Unable to read or clear store, will not update sync time. Sync may run too frequently.");
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
        hjx hjx = null;
        for (hjx hjx2 : hjy.f12895c) {
            hka hka = hjx2.f12887b;
            if (hka == null) {
                hka = hka.f12906d;
            }
            if (!hjh.equals(hjh.m11577a(hka))) {
                g.mo8747a(hjx2);
            } else {
                hjx = hjx2;
            }
        }
        if (hjx != null) {
            if (hjy.f12894b < 0) {
                long j2 = hiz.f12844f;
                if (j2 < 0) {
                    j2 = hiz.f12842d.mo5370a();
                    hiz.f12844f = j2;
                }
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                hjy hjy2 = (hjy) g.f14318b;
                hjy2.f12893a |= 1;
                hjy2.f12894b = j2;
            }
            iir g2 = hjx.f12884f.mo8793g();
            hka hka2 = hjh.f12849a;
            if (g2.f14319c) {
                g2.mo8751b();
                g2.f14319c = false;
            }
            hjx hjx3 = (hjx) g2.f14318b;
            hka2.getClass();
            hjx3.f12887b = hka2;
            int i = hjx3.f12886a | 1;
            hjx3.f12886a = i;
            int i2 = i | 4;
            hjx3.f12886a = i2;
            hjx3.f12889d = j;
            if (z) {
                int i3 = i2 | 2;
                hjx3.f12886a = i3;
                hjx3.f12888c = j;
                hjx3.f12886a = i3 | 8;
                hjx3.f12890e = 0;
            } else if (hjx == null) {
                long j3 = hiz.f12844f;
                int i4 = i2 | 2;
                hjx3.f12886a = i4;
                hjx3.f12888c = j3;
                hjx3.f12886a = i4 | 8;
                hjx3.f12890e = 1;
            } else {
                long j4 = hjx.f12888c;
                int i5 = i2 | 2;
                hjx3.f12886a = i5;
                hjx3.f12888c = j4;
                hjx3.f12886a = i5 | 8;
                hjx3.f12890e = hjx.f12890e + 1;
            }
            g.mo8747a((hjx) g2.mo8770g());
            try {
                hiz.mo7487a((hjy) g.mo8770g());
            } catch (IOException e2) {
                ((hvv) ((hvv) ((hvv) hiz.f12839a.mo8178a()).mo8202a((Throwable) e2)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManagerDataStore", "lambda$updateLastSyncTime$4", 345, "SyncManagerDataStore.java")).mo8203a("Error writing sync data file after sync. Sync may run too frequently.");
            }
            reentrantReadWriteLock = hiz.f12840b;
        } else {
            reentrantReadWriteLock = hiz.f12840b;
        }
        reentrantReadWriteLock.writeLock().unlock();
    }
}
