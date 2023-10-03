package p000;

import android.app.Application;

/* renamed from: fid */
/* compiled from: PG */
public final class fid {

    /* renamed from: a */
    public static volatile fid f9697a;

    /* renamed from: b */
    public final fif f9698b = new fif();

    private fid() {
    }

    /* renamed from: a */
    public static fid m8938a(Application application) {
        if (f9697a == null) {
            synchronized (fid.class) {
                if (f9697a == null) {
                    fid fid = new fid();
                    fif fif = fid.f9698b;
                    application.registerActivityLifecycleCallbacks(fif.f9709a);
                    application.registerComponentCallbacks(fif.f9709a);
                    f9697a = fid;
                }
            }
        }
        return f9697a;
    }

    /* renamed from: a */
    public final void mo5747a(fic fic) {
        fif fif = this.f9698b;
        ife.m12898e((Object) fic);
        fif.f9709a.f9699a.add(fic);
    }

    /* renamed from: b */
    public final void mo5748b(fic fic) {
        fif fif = this.f9698b;
        ife.m12898e((Object) fic);
        fif.f9709a.f9699a.remove(fic);
    }
}
