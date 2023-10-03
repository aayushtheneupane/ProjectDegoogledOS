package p000;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: gir */
/* compiled from: PG */
public final class gir {

    /* renamed from: e */
    private static gir f11438e;

    /* renamed from: a */
    public final Object f11439a = new Object();

    /* renamed from: b */
    public final Handler f11440b = new Handler(Looper.getMainLooper(), new gio(this));

    /* renamed from: c */
    public giq f11441c;

    /* renamed from: d */
    public giq f11442d;

    private gir() {
    }

    /* renamed from: a */
    public final boolean mo6725a(giq giq, int i) {
        gip gip = (gip) giq.f11435a.get();
        if (gip == null) {
            return false;
        }
        this.f11440b.removeCallbacksAndMessages(giq);
        gip.mo6697a(i);
        return true;
    }

    /* renamed from: a */
    public static gir m10379a() {
        if (f11438e == null) {
            f11438e = new gir();
        }
        return f11438e;
    }

    /* renamed from: c */
    public final boolean mo6728c(gip gip) {
        giq giq = this.f11441c;
        return giq != null && giq.mo6722a(gip);
    }

    /* renamed from: d */
    public final boolean mo6729d(gip gip) {
        giq giq = this.f11442d;
        return giq != null && giq.mo6722a(gip);
    }

    /* renamed from: a */
    public final void mo6723a(gip gip) {
        synchronized (this.f11439a) {
            if (mo6728c(gip)) {
                giq giq = this.f11441c;
                if (!giq.f11437c) {
                    giq.f11437c = true;
                    this.f11440b.removeCallbacksAndMessages(giq);
                }
            }
        }
    }

    /* renamed from: b */
    public final void mo6727b(gip gip) {
        synchronized (this.f11439a) {
            if (mo6728c(gip)) {
                giq giq = this.f11441c;
                if (giq.f11437c) {
                    giq.f11437c = false;
                    mo6724a(giq);
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo6724a(giq giq) {
        int i = giq.f11436b;
        if (i != -2) {
            if (i <= 0) {
                i = i == -1 ? 1500 : 2750;
            }
            this.f11440b.removeCallbacksAndMessages(giq);
            Handler handler = this.f11440b;
            handler.sendMessageDelayed(Message.obtain(handler, 0, giq), (long) i);
        }
    }

    /* renamed from: b */
    public final void mo6726b() {
        giq giq = this.f11442d;
        if (giq != null) {
            this.f11441c = giq;
            this.f11442d = null;
            gip gip = (gip) giq.f11435a.get();
            if (gip != null) {
                gip.mo6696a();
            } else {
                this.f11441c = null;
            }
        }
    }
}
