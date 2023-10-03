package p000;

import android.content.Context;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: hiz */
/* compiled from: PG */
public final class hiz {

    /* renamed from: a */
    public static final hvy f12839a = hvy.m12261a("com/google/apps/tiktok/sync/impl/SyncManagerDataStore");

    /* renamed from: b */
    public final ReentrantReadWriteLock f12840b = new ReentrantReadWriteLock();

    /* renamed from: c */
    public final iek f12841c;

    /* renamed from: d */
    public final exm f12842d;

    /* renamed from: e */
    public final AtomicBoolean f12843e = new AtomicBoolean(false);

    /* renamed from: f */
    public long f12844f = -1;

    /* renamed from: g */
    private final Context f12845g;

    public hiz(Context context, iek iek, exm exm) {
        this.f12842d = exm;
        this.f12845g = context;
        this.f12841c = iek;
    }

    /* renamed from: a */
    public final boolean mo7488a(Throwable th) {
        ((hvv) ((hvv) ((hvv) f12839a.mo8180b()).mo8202a(th)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManagerDataStore", "clearStore", 515, "SyncManagerDataStore.java")).mo8203a("Could not read sync datastore. There was probably a write error. Wiping store.");
        this.f12840b.writeLock().lock();
        try {
            boolean z = false;
            this.f12843e.set(false);
            long j = this.f12844f;
            if (j <= 0) {
                j = this.f12842d.mo5370a();
            }
            iir g = hjy.f12891f.mo8793g();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            hjy hjy = (hjy) g.f14318b;
            hjy.f12893a |= 1;
            hjy.f12894b = j;
            try {
                mo7487a((hjy) g.mo8770g());
                this.f12843e.set(true);
                z = true;
            } catch (IOException e) {
                ((hvv) ((hvv) ((hvv) f12839a.mo8178a()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/sync/impl/SyncManagerDataStore", "clearStore", 535, "SyncManagerDataStore.java")).mo8203a("Could not write to datastore to clear store.");
                this.f12843e.set(false);
            }
            this.f12840b.writeLock().unlock();
            return z;
        } catch (Throwable th2) {
            this.f12840b.writeLock().unlock();
            throw th2;
        }
    }

    /* renamed from: b */
    public final ieh mo7489b() {
        return ibv.m12657a(mo7485a(), hmq.m11742a((hpr) new his(this)), (Executor) this.f12841c);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ieh mo7485a() {
        if (this.f12843e.get()) {
            return ife.m12820a((Object) Long.valueOf(this.f12844f));
        }
        return this.f12841c.mo5933a(hmq.m11749a((Callable) new hir(this)));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: hjy} */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r2v3, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.hjy mo7490c() {
        /*
            r3 = this;
            java.io.File r0 = new java.io.File
            android.content.Context r1 = r3.f12845g
            java.io.File r1 = r1.getFilesDir()
            java.lang.String r2 = "103795117"
            r0.<init>(r1, r2)
            java.util.concurrent.locks.ReentrantReadWriteLock r1 = r3.f12840b
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r1.readLock()
            r1.lock()
            boolean r1 = r0.exists()     // Catch:{ all -> 0x0047 }
            r2 = 0
            if (r1 == 0) goto L_0x0037
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0032 }
            r1.<init>(r0)     // Catch:{ all -> 0x0032 }
            hjy r0 = p000.hjy.f12891f     // Catch:{ all -> 0x002f }
            iix r0 = p000.hjy.m13602a((p000.iix) r0, (java.io.InputStream) r1)     // Catch:{ all -> 0x002f }
            r2 = r0
            hjy r2 = (p000.hjy) r2     // Catch:{ all -> 0x002f }
            p000.fsa.m9484a((java.io.Closeable) r1)     // Catch:{ all -> 0x0047 }
            goto L_0x0038
        L_0x002f:
            r0 = move-exception
            r2 = r1
            goto L_0x0033
        L_0x0032:
            r0 = move-exception
        L_0x0033:
            p000.fsa.m9484a((java.io.Closeable) r2)     // Catch:{ all -> 0x0047 }
            throw r0     // Catch:{ all -> 0x0047 }
        L_0x0037:
        L_0x0038:
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r3.f12840b
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()
            r0.unlock()
            if (r2 != 0) goto L_0x0046
            hjy r0 = p000.hjy.f12891f
            return r0
        L_0x0046:
            return r2
        L_0x0047:
            r0 = move-exception
            java.util.concurrent.locks.ReentrantReadWriteLock r1 = r3.f12840b
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r1.readLock()
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hiz.mo7490c():hjy");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ieh mo7486a(hjh hjh, long j, boolean z) {
        return this.f12841c.mo5931a((Runnable) new hiv(this, hjh, j, z));
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo7487a(p000.hjy r5) {
        /*
            r4 = this;
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ all -> 0x001c }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x001c }
            android.content.Context r2 = r4.f12845g     // Catch:{ all -> 0x001c }
            java.io.File r2 = r2.getFilesDir()     // Catch:{ all -> 0x001c }
            java.lang.String r3 = "103795117"
            r1.<init>(r2, r3)     // Catch:{ all -> 0x001c }
            r0.<init>(r1)     // Catch:{ all -> 0x001c }
            r5.mo8514b(r0)     // Catch:{ all -> 0x0019 }
            r0.close()
            return
        L_0x0019:
            r5 = move-exception
            goto L_0x001e
        L_0x001c:
            r5 = move-exception
            r0 = 0
        L_0x001e:
            if (r0 == 0) goto L_0x0023
            r0.close()
        L_0x0023:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hiz.mo7487a(hjy):void");
    }
}
