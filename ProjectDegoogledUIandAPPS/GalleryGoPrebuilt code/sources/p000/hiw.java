package p000;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* renamed from: hiw */
/* compiled from: PG */
final /* synthetic */ class hiw implements Runnable {

    /* renamed from: a */
    private final hiz f12833a;

    /* renamed from: b */
    private final Set f12834b;

    public hiw(hiz hiz, Set set) {
        this.f12833a = hiz;
        this.f12834b = set;
    }

    public final void run() {
        hjy hjy;
        hiz hiz = this.f12833a;
        Set<hjh> set = this.f12834b;
        hiz.f12840b.writeLock().lock();
        try {
            hjy = hjy.f12891f;
            hjy = hiz.mo7490c();
        } catch (IOException e) {
            if (!hiz.mo7488a((Throwable) e)) {
                ((hvv) ((hvv) ((hvv) hiz.f12839a.mo8178a()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManagerDataStore", "lambda$updateScheduledAccountIds$5", 365, "SyncManagerDataStore.java")).mo8203a("Unable to read or clear store, will not update scheduled account ids. ");
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
        ((hjy) g.f14318b).f12897e = hjy.m13613j();
        TreeSet treeSet = new TreeSet();
        for (hjh hjh : set) {
            if (hjh.mo7492a()) {
                treeSet.add(Integer.valueOf(((gkp) hjh.f12851c).f11548a));
            }
        }
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        hjy hjy2 = (hjy) g.f14318b;
        if (!hjy2.f12897e.mo8521a()) {
            hjy2.f12897e = iix.m13606a(hjy2.f12897e);
        }
        igz.m12986a((Iterable) treeSet, (List) hjy2.f12897e);
        try {
            hiz.mo7487a((hjy) g.mo8770g());
        } catch (IOException e2) {
            ((hvv) ((hvv) ((hvv) hiz.f12839a.mo8178a()).mo8202a((Throwable) e2)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManagerDataStore", "lambda$updateScheduledAccountIds$5", 386, "SyncManagerDataStore.java")).mo8203a("Error writing scheduled account ids");
        }
        hiz.f12840b.writeLock().unlock();
    }
}
