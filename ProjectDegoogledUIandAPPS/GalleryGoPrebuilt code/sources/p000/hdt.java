package p000;

import android.content.ComponentCallbacks2;
import android.net.Uri;
import android.widget.ImageView;

/* renamed from: hdt */
/* compiled from: PG */
public final class hdt {

    /* renamed from: a */
    public volatile apn f12550a;

    /* renamed from: b */
    public final ComponentCallbacks2 f12551b = new hdq(this);

    /* renamed from: c */
    private final iqk f12552c;

    /* renamed from: d */
    private final hdu f12553d;

    /* renamed from: e */
    private final boolean f12554e;

    /* renamed from: f */
    private final Object f12555f = new Object();

    public hdt(iqk iqk, hdu hdu, boolean z) {
        this.f12552c = iqk;
        this.f12553d = hdu;
        this.f12554e = z;
    }

    /* renamed from: a */
    public final apj mo7307a() {
        return m11316b().mo1445e();
    }

    /* renamed from: a */
    public final void mo7311a(ImageView imageView) {
        m11316b().mo1440a((ber) new apl(imageView));
    }

    /* renamed from: a */
    public final void mo7312a(ber ber) {
        m11316b().mo1440a(ber);
    }

    /* renamed from: b */
    private final apn m11316b() {
        if (this.f12554e) {
            fxk.m9830b();
        }
        if (this.f12550a == null) {
            synchronized (this.f12555f) {
                if (this.f12550a == null) {
                    this.f12550a = (apn) this.f12552c.mo2097a();
                    this.f12553d.mo7306a(this);
                }
            }
        }
        return this.f12550a;
    }

    /* renamed from: a */
    public final apj mo7308a(Uri uri) {
        return m11316b().mo1434a(uri);
    }

    /* renamed from: a */
    public final apj mo7309a(Object obj) {
        return m11316b().mo1436a(obj);
    }

    /* renamed from: a */
    public final apj mo7310a(String str) {
        return m11316b().mo1437a(str);
    }
}
