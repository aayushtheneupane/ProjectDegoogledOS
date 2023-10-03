package p000;

import java.io.File;
import java.util.concurrent.Callable;

/* renamed from: gnk */
/* compiled from: PG */
final /* synthetic */ class gnk implements Callable {

    /* renamed from: a */
    private final gnl f11689a;

    public gnk(gnl gnl) {
        this.f11689a = gnl;
    }

    public final Object call() {
        gnl gnl = this.f11689a;
        hfr hfr = gnl.f11690a;
        File a = hfr.f12666b.mo7372a(hfr.f12665a);
        String str = hfr.f12667c;
        if (str != null) {
            a = new File(a, str);
        }
        a.getParentFile().mkdirs();
        hfr hfr2 = gnl.f11690a;
        ife.m12898e((Object) gtf.f12011a);
        return hfr2.f12666b.mo7370a(hfr2.f12665a, hfr2.f12667c, gtf.f12011a);
    }
}
