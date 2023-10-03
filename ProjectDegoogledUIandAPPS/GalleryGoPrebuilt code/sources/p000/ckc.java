package p000;

import android.net.Uri;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: ckc */
/* compiled from: PG */
final /* synthetic */ class ckc implements hqk {

    /* renamed from: a */
    private final ckk f4540a;

    /* renamed from: b */
    private final cyd f4541b;

    /* renamed from: c */
    private final Uri f4542c;

    /* renamed from: d */
    private final String f4543d;

    /* renamed from: e */
    private final boolean f4544e;

    /* renamed from: f */
    private final Runnable f4545f;

    /* renamed from: g */
    private final AtomicBoolean f4546g;

    public ckc(ckk ckk, cyd cyd, Uri uri, String str, boolean z, Runnable runnable, AtomicBoolean atomicBoolean) {
        this.f4540a = ckk;
        this.f4541b = cyd;
        this.f4542c = uri;
        this.f4543d = str;
        this.f4544e = z;
        this.f4545f = runnable;
        this.f4546g = atomicBoolean;
    }

    /* renamed from: a */
    public final Object mo2652a() {
        ckk ckk = this.f4540a;
        cyd cyd = this.f4541b;
        Uri uri = this.f4542c;
        String str = this.f4543d;
        boolean z = this.f4544e;
        Runnable runnable = this.f4545f;
        AtomicBoolean atomicBoolean = this.f4546g;
        if (atomicBoolean.get()) {
            return ife.m12820a((Object) hso.m12047f());
        }
        ieh a = gte.m10771a(gte.m10770a(ckk.f4563d.mo4023a(uri), (hpr) new cki(uri, str), (Executor) ckk.f4567h), (icf) new cke(ckk, atomicBoolean, uri, cyd), (Executor) ckk.f4566g);
        if (z && !atomicBoolean.get()) {
            a = gte.m10771a(a, (icf) new ckf(ckk, cyd), (Executor) ckk.f4566g);
        }
        ieh a2 = ckk.mo3202a(a, (List) hso.m12047f());
        if (atomicBoolean.get()) {
            return a2;
        }
        a2.mo53a(hmq.m11748a(runnable), ckk.f4567h);
        return a2;
    }
}
