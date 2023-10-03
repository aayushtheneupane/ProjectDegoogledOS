package p000;

import android.app.Application;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: fkp */
/* compiled from: PG */
final class fkp implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ fks f9903a;

    /* renamed from: b */
    private final /* synthetic */ iqk f9904b;

    /* renamed from: c */
    private final /* synthetic */ hqk f9905c;

    /* renamed from: d */
    private final /* synthetic */ hqk f9906d;

    /* renamed from: e */
    private final /* synthetic */ hqk f9907e;

    /* renamed from: f */
    private final /* synthetic */ fkq f9908f;

    /* renamed from: g */
    private final /* synthetic */ fkr f9909g;

    public fkp(fks fks, iqk iqk, hqk hqk, hqk hqk2, hqk hqk3, fkq fkq, fkr fkr) {
        this.f9903a = fks;
        this.f9904b = iqk;
        this.f9905c = hqk;
        this.f9906d = hqk2;
        this.f9907e = hqk3;
        this.f9908f = fkq;
        this.f9909g = fkr;
    }

    public final void run() {
        CountDownLatch countDownLatch;
        try {
            flw.m9202d("Primes", "background initialization", new Object[0]);
            fks fks = this.f9903a;
            iqk iqk = this.f9904b;
            hqk hqk = this.f9905c;
            hqk hqk2 = this.f9906d;
            hqk hqk3 = this.f9907e;
            fkq fkq = this.f9908f;
            fkr fkr = this.f9909g;
            Application application = fks.f9918a;
            hqk hqk4 = fks.f9919b;
            AtomicReference atomicReference = fks.f9920c;
            fmw fmw = (fmw) hqk3.mo2652a();
            fmw.mo5978a((fmx) fkq);
            fmw.mo5978a((fmx) fkr);
            fob fob = new fob(application);
            fmw.mo5977a((hqk) fob);
            if (!fmw.f10070a) {
                application.registerReceiver(new foa(fmw, fob, hqk4), new IntentFilter("com.google.gservices.intent.action.GSERVICES_CHANGED"));
            }
            if (!fmw.f10070a) {
                SharedPreferences sharedPreferences = (SharedPreferences) hqk2.mo2652a();
                fld a = fld.m9134a((fld) ife.m12898e((Object) (fld) iqk.mo2097a()));
                if (!fmw.f10070a) {
                    fkm fkm = (fkm) atomicReference.get();
                    fkk fkk = fkm instanceof fkk ? (fkk) fkm : null;
                    if (fkm != null) {
                        fji fji = r4;
                        fji fji2 = new fji(application, hqk4, a, hqk, sharedPreferences, fmw, fkk.f9889c);
                        application.getPackageName();
                        fit fit = new fit(fji);
                        if (!fmw.f10070a) {
                            fkm fkm2 = (fkm) atomicReference.get();
                            if (fkm2 instanceof fkk) {
                                if (atomicReference.compareAndSet(fkm2, fit)) {
                                    ArrayList arrayList = new ArrayList();
                                    if (fit.f9756a.mo5872a()) {
                                        arrayList.add(fit.f9756a.mo5873b());
                                    }
                                    fji fji3 = fit.f9756a;
                                    if (Build.VERSION.SDK_INT >= 28) {
                                        if (StrictMode.ThreadPolicy.LAX.equals(StrictMode.getThreadPolicy()) && StrictMode.VmPolicy.LAX.equals(StrictMode.getVmPolicy())) {
                                            if (fji3.f9812h.mo5916k().mo7646a()) {
                                                flw flw = (flw) fji3.f9812h.mo5916k().mo7647b();
                                                throw null;
                                            }
                                        }
                                    }
                                    fji fji4 = fit.f9756a;
                                    if (fji4.f9812h.mo5911f().mo7646a()) {
                                        if (((fma) fji4.f9812h.mo5911f().mo7647b()).f10016a) {
                                            fma fma = (fma) fji4.f9812h.mo5911f().mo7647b();
                                            fji fji5 = fit.f9756a;
                                            arrayList.add((fkd) fji5.mo5871a(fkd.m9064a(fji5.f9809e, fji5.f9808d, fji5.f9810f, fji5.f9811g, fji5.f9814j, ((fma) fji5.f9812h.mo5911f().mo7647b()).f10017b)));
                                        }
                                    }
                                    fji fji6 = fit.f9756a;
                                    int i = Build.VERSION.SDK_INT;
                                    if (fji6.f9812h.mo5915j().mo7646a()) {
                                        if (((fky) fji6.f9812h.mo5915j().mo7647b()).mo5759a()) {
                                            fji fji7 = fit.f9756a;
                                            if (fji7.f9806b == null) {
                                                synchronized (fis.class) {
                                                    if (fji7.f9806b == null) {
                                                        iqk iqk2 = fji7.f9809e;
                                                        Application application2 = fji7.f9808d;
                                                        hqk hqk5 = fji7.f9810f;
                                                        fji7.f9806b = (fis) fji7.mo5871a(new fis(iqk2, application2, hqk5, fji7.f9811g, fji7.f9814j, new fnm(hqk5, new fnx(application2), ((fky) fji7.f9812h.mo5915j().mo7645a(fky.m9108c().mo5903a())).mo5760b())));
                                                    }
                                                }
                                            }
                                            arrayList.add(fji7.f9806b);
                                        }
                                    }
                                    fji fji8 = fit.f9756a;
                                    int i2 = Build.VERSION.SDK_INT;
                                    if (fji8.f9812h.mo5912g().mo7646a()) {
                                        if (((flr) fji8.f9812h.mo5912g().mo7647b()).mo5784a() && !((flr) fji8.f9812h.mo5912g().mo7647b()).mo5786c()) {
                                            fji fji9 = fit.f9756a;
                                            if (fji9.f9805a == null) {
                                                synchronized (fjd.class) {
                                                    if (fji9.f9805a == null) {
                                                        foe foe = new foe();
                                                        iqk iqk3 = fji9.f9809e;
                                                        Application application3 = fji9.f9808d;
                                                        hqk hqk6 = fji9.f9810f;
                                                        hqk hqk7 = fji9.f9811g;
                                                        flr flr = (flr) fji9.f9812h.mo5912g().mo7647b();
                                                        int i3 = Build.VERSION.SDK_INT;
                                                        ife.m12896d(true);
                                                        fji9.f9805a = (fjd) fji9.mo5871a(new fjd(iqk3, application3, hqk6, hqk7, flr.mo5785b(), flr.mo5787d(), foe, (iqk) flr.mo5788e().mo7648c()));
                                                    }
                                                }
                                            }
                                            arrayList.add(fji9.f9805a);
                                        }
                                    }
                                    fji fji10 = fit.f9756a;
                                    int i4 = Build.VERSION.SDK_INT;
                                    if (!fji10.f9812h.mo5916k().mo7646a()) {
                                        if (fit.f9756a.mo5878g() && fmj.f10033a.f10037c > 0) {
                                            fji fji11 = fit.f9756a;
                                            fid a2 = fid.m8938a(fji11.f9808d);
                                            hqk hqk8 = fji11.f9807c;
                                            fjg fjg = new fjg(fji11);
                                            fji11.f9812h.mo5914i();
                                            fmk fmk = (fmk) fji11.mo5871a(new fmk(a2, hqk8, fjg, fji11.f9813i));
                                        }
                                        int size = arrayList.size();
                                        for (int i5 = 0; i5 < size; i5++) {
                                            fmc fmc = (fmc) arrayList.get(i5);
                                            fmc.mo5833e();
                                            synchronized (fkq) {
                                                if (!fkq.f9911b) {
                                                    fkq.f9910a.add(fmc);
                                                } else {
                                                    fmc.mo5834f();
                                                }
                                            }
                                        }
                                        if (!fmw.f10070a) {
                                            fkk fkk2 = (fkk) fkm2;
                                            fkk2.mo5899a(fit);
                                            synchronized (fkk2.f9888b) {
                                                fkk2.f9887a = fit;
                                            }
                                            fkk2.mo5899a(fit);
                                        }
                                        fkm2.mo5835a();
                                    } else {
                                        flw flw2 = (flw) fji10.f9812h.mo5916k().mo7647b();
                                        throw null;
                                    }
                                }
                            }
                            flw.m9199b("Primes", "Primes shutdown during initialization", new Object[0]);
                            fit.mo5835a();
                        } else {
                            fks.mo5835a();
                        }
                    }
                } else {
                    fks.mo5835a();
                }
            } else {
                fks.mo5835a();
            }
            countDownLatch = this.f9903a.f9921d;
        } catch (RuntimeException e) {
            try {
                flw.m9198b("Primes", "Primes failed to initialize in the background", e, new Object[0]);
                this.f9903a.mo5835a();
                countDownLatch = this.f9903a.f9921d;
            } catch (Throwable th) {
                this.f9903a.f9921d.countDown();
                throw th;
            }
        }
        countDownLatch.countDown();
    }
}
