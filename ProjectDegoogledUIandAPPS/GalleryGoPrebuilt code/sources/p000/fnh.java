package p000;

import android.app.Application;

/* renamed from: fnh */
/* compiled from: PG */
final class fnh extends fhn {
    private fnh(iqk iqk, Application application, hqk hqk, hqk hqk2) {
        super(iqk, application, hqk, hqk2, 2);
    }

    /* renamed from: d */
    public final void mo5733d() {
    }

    /* renamed from: a */
    static synchronized fnh m9266a(iqk iqk, Application application, hqk hqk, hqk hqk2) {
        fnh fnh;
        synchronized (fnh.class) {
            fnh = new fnh(iqk, application, hqk, hqk2);
        }
        return fnh;
    }
}
