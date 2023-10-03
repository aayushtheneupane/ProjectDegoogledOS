package p000;

import android.graphics.BitmapRegionDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Semaphore;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: bgu */
/* compiled from: PG */
public final class bgu {

    /* renamed from: a */
    private final Semaphore f2353a = new Semaphore(0, true);

    /* renamed from: b */
    private final Map f2354b = new ConcurrentHashMap();

    private bgu() {
    }

    public /* synthetic */ bgu(byte[] bArr) {
    }

    /* renamed from: c */
    public final BitmapRegionDecoder mo2025c() {
        this.f2353a.acquireUninterruptibly();
        return m2512e();
    }

    /* renamed from: b */
    public final synchronized void mo2024b(BitmapRegionDecoder bitmapRegionDecoder) {
        this.f2354b.put(bitmapRegionDecoder, false);
        this.f2353a.release();
    }

    /* renamed from: e */
    private final synchronized BitmapRegionDecoder m2512e() {
        BitmapRegionDecoder bitmapRegionDecoder;
        Iterator it = this.f2354b.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                bitmapRegionDecoder = null;
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            if (!((Boolean) entry.getValue()).booleanValue()) {
                entry.setValue(true);
                bitmapRegionDecoder = (BitmapRegionDecoder) entry.getKey();
                break;
            }
        }
        return bitmapRegionDecoder;
    }

    /* renamed from: a */
    public final synchronized boolean mo2022a() {
        return this.f2354b.isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        return false;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean m2511c(android.graphics.BitmapRegionDecoder r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.Map r0 = r4.f2354b     // Catch:{ all -> 0x0036 }
            java.util.Set r0 = r0.entrySet()     // Catch:{ all -> 0x0036 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0036 }
        L_0x000b:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0036 }
            r2 = 0
            if (r1 == 0) goto L_0x0034
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0036 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x0036 }
            java.lang.Object r3 = r1.getKey()     // Catch:{ all -> 0x0036 }
            if (r5 != r3) goto L_0x000b
            java.lang.Object r5 = r1.getValue()     // Catch:{ all -> 0x0036 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x0036 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0036 }
            if (r5 == 0) goto L_0x0034
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x0036 }
            r1.setValue(r5)     // Catch:{ all -> 0x0036 }
            r5 = 1
            monitor-exit(r4)
            return r5
        L_0x0034:
            monitor-exit(r4)
            return r2
        L_0x0036:
            r5 = move-exception
            monitor-exit(r4)
            goto L_0x003a
        L_0x0039:
            throw r5
        L_0x003a:
            goto L_0x0039
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bgu.m2511c(android.graphics.BitmapRegionDecoder):boolean");
    }

    /* renamed from: d */
    public final synchronized void mo2026d() {
        while (!this.f2354b.isEmpty()) {
            BitmapRegionDecoder c = mo2025c();
            c.recycle();
            this.f2354b.remove(c);
        }
    }

    /* renamed from: a */
    public final void mo2021a(BitmapRegionDecoder bitmapRegionDecoder) {
        if (m2511c(bitmapRegionDecoder)) {
            this.f2353a.release();
        }
    }

    /* renamed from: b */
    public final synchronized int mo2023b() {
        return this.f2354b.size();
    }
}
