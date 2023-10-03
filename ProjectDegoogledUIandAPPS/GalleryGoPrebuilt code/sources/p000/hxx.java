package p000;

import android.os.Build;
import android.util.Log;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

/* renamed from: hxx */
/* compiled from: PG */
public final class hxx extends hxm {

    /* renamed from: b */
    public static final AtomicReference f13600b = new AtomicReference();

    /* renamed from: d */
    private static final hxp f13601d;

    /* renamed from: e */
    private static final AtomicLong f13602e = new AtomicLong();

    /* renamed from: f */
    private static final ConcurrentLinkedQueue f13603f = new ConcurrentLinkedQueue();

    /* renamed from: c */
    private volatile hxa f13604c;

    static {
        boolean z = false;
        boolean z2 = Build.FINGERPRINT == null || "robolectric".equals(Build.FINGERPRINT);
        if ("goldfish".equals(Build.HARDWARE) || "ranchu".equals(Build.HARDWARE)) {
            z = true;
        }
        if (!z2 && !z) {
            f13601d = null;
        } else {
            f13601d = new hxn();
        }
    }

    public hxx(String str) {
        super(str);
        hxp hxp = f13601d;
        this.f13604c = hxp != null ? hxp.mo7281a(mo8243a()) : null;
    }

    /* renamed from: b */
    public static void m12427b() {
        while (true) {
            hxx hxx = (hxx) hxv.f13597a.poll();
            if (hxx != null) {
                hxx.f13604c = ((hxp) f13600b.get()).mo7281a(hxx.mo8243a());
            } else {
                m12428c();
                return;
            }
        }
    }

    /* renamed from: c */
    private static void m12428c() {
        while (true) {
            hxw hxw = (hxw) f13603f.poll();
            if (hxw != null) {
                f13602e.getAndDecrement();
                hxa hxa = hxw.f13598a;
                hwz hwz = hxw.f13599b;
                if (hwz.mo8216k() || hxa.mo7301a(hwz.mo8209d())) {
                    hxa.mo7299a(hwz);
                }
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    public final boolean mo7301a(Level level) {
        if (this.f13604c != null) {
            return this.f13604c.mo7301a(level);
        }
        return true;
    }

    /* renamed from: a */
    public final void mo7299a(hwz hwz) {
        if (this.f13604c == null) {
            if (f13602e.incrementAndGet() > 20) {
                f13603f.poll();
                Log.w("ProxyAndroidLoggerBackend", "Too many Flogger logs received before configuration. Dropping old logs.");
            }
            f13603f.offer(new hxw(this, hwz));
            if (this.f13604c != null) {
                m12428c();
                return;
            }
            return;
        }
        this.f13604c.mo7299a(hwz);
    }
}
