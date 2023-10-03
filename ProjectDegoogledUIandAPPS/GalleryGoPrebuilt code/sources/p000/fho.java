package p000;

import android.app.Application;
import java.util.concurrent.Executors;

/* renamed from: fho */
/* compiled from: PG */
final /* synthetic */ class fho implements iqk {

    /* renamed from: a */
    private final Application f9689a;

    /* renamed from: b */
    private final fmm f9690b;

    /* renamed from: c */
    private final iqk f9691c;

    /* renamed from: d */
    private final hqk f9692d;

    /* renamed from: e */
    private final hqk f9693e;

    /* renamed from: f */
    private final hqk f9694f;

    public fho(Application application, fmm fmm, iqk iqk, hqk hqk, hqk hqk2, hqk hqk3) {
        this.f9689a = application;
        this.f9690b = fmm;
        this.f9691c = iqk;
        this.f9692d = hqk;
        this.f9693e = hqk2;
        this.f9694f = hqk3;
    }

    /* renamed from: a */
    public final Object mo2097a() {
        iel iel;
        Application application = this.f9689a;
        fmm fmm = this.f9690b;
        iqk iqk = this.f9691c;
        hqk hqk = this.f9692d;
        hqk hqk2 = this.f9693e;
        hqk hqk3 = this.f9694f;
        fks fks = new fks(application, ife.m12811a((hqk) new fli(fmm.mo5801a(), fmm.mo5803c(), fmm.mo5804d())), fmm.mo5805e());
        if (fmm.mo5801a() != null) {
            iel = fmm.mo5801a();
        } else {
            iel = Executors.newSingleThreadExecutor(new flm("Primes-init", fmm.mo5802b()));
        }
        fmm.mo5808g();
        Runnable a = fks.m9094a((Runnable) new fkn(fks, iel, fks.m9094a((Runnable) new fkp(fks, iqk, hqk, hqk2, hqk3, new fkq(fid.m8938a(fks.f9918a)), new fkr(fid.m8938a(fks.f9918a), fks.f9919b))), fmm.mo5801a() == null));
        fmm.mo5807f();
        new fhp(application);
        flw.m9202d("PrimesInit", "Primes instant initialization", new Object[0]);
        a.run();
        return fks;
    }
}
