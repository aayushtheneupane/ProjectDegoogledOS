package p000;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: hiu */
/* compiled from: PG */
final /* synthetic */ class hiu implements Callable {

    /* renamed from: a */
    private final hiz f12827a;

    /* renamed from: b */
    private final Collection f12828b;

    public hiu(hiz hiz, Collection collection) {
        this.f12827a = hiz;
        this.f12828b = collection;
    }

    public final Object call() {
        boolean valueOf;
        ReentrantReadWriteLock reentrantReadWriteLock;
        hiz hiz = this.f12827a;
        Collection<hjh> collection = this.f12828b;
        hiz.f12840b.writeLock().lock();
        hjy hjy = hjy.f12891f;
        boolean z = false;
        try {
            hjy = hiz.mo7490c();
        } catch (IOException e) {
        } catch (IOException e2) {
            if (!hiz.mo7488a((Throwable) e2)) {
                ((hvv) ((hvv) ((hvv) hiz.f12839a.mo8178a()).mo8202a((Throwable) e2)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManagerDataStore", "lambda$prepareForSync$3", 195, "SyncManagerDataStore.java")).mo8203a("Error, could not read or clear store. Aborting sync attempt.");
                valueOf = false;
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
        long a = hiz.f12842d.mo5370a();
        HashSet hashSet = new HashSet();
        for (hjx hjx : hjy.f12895c) {
            hka hka = hjx.f12887b;
            if (hka == null) {
                hka = hka.f12906d;
            }
            if (collection.contains(hjh.m11577a(hka))) {
                hka hka2 = hjx.f12887b;
                if (hka2 == null) {
                    hka2 = hka.f12906d;
                }
                hashSet.add(hjh.m11577a(hka2));
                iir g2 = hjx.f12884f.mo8793g();
                g2.mo8503a((iix) hjx);
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                hjx hjx2 = (hjx) g2.f14318b;
                hjx2.f12886a |= 4;
                hjx2.f12889d = a;
                g.mo8747a((hjx) g2.mo8770g());
            } else {
                g.mo8747a(hjx);
            }
        }
        for (hjh hjh : collection) {
            if (!hashSet.contains(hjh)) {
                iir g3 = hjx.f12884f.mo8793g();
                hka hka3 = hjh.f12849a;
                if (g3.f14319c) {
                    g3.mo8751b();
                    g3.f14319c = false;
                }
                hjx hjx3 = (hjx) g3.f14318b;
                hka3.getClass();
                hjx3.f12887b = hka3;
                int i = hjx3.f12886a | 1;
                hjx3.f12886a = i;
                long j = hiz.f12844f;
                int i2 = i | 2;
                hjx3.f12886a = i2;
                hjx3.f12888c = j;
                int i3 = i2 | 4;
                hjx3.f12886a = i3;
                hjx3.f12889d = a;
                hjx3.f12886a = i3 | 8;
                hjx3.f12890e = 0;
                g.mo8747a((hjx) g3.mo8770g());
            }
        }
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
        hiz.mo7487a((hjy) g.mo8770g());
        hiz.f12843e.set(true);
        z = true;
        valueOf = Boolean.valueOf(z);
        reentrantReadWriteLock = hiz.f12840b;
        reentrantReadWriteLock.writeLock().unlock();
        return valueOf;
    }
}
