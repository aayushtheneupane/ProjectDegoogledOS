package p000;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: fis */
/* compiled from: PG */
final class fis extends fhn implements fmc, fia, fhz {

    /* renamed from: d */
    public final fnw f9752d;

    /* renamed from: e */
    public final fnm f9753e;

    /* renamed from: f */
    private final AtomicBoolean f9754f = new AtomicBoolean();

    /* renamed from: g */
    private final AtomicBoolean f9755g = new AtomicBoolean();

    public fis(iqk iqk, Application application, hqk hqk, hqk hqk2, SharedPreferences sharedPreferences, fnm fnm) {
        super(iqk, application, hqk, hqk2, 1);
        new ConcurrentHashMap();
        this.f9752d = new fnw(sharedPreferences);
        this.f9753e = fnm;
    }

    /* renamed from: e */
    public final void mo5833e() {
    }

    /* renamed from: a */
    private final ieh m8988a(iqm iqm) {
        return ife.m12816a((ice) new fir(this, iqm), (Executor) mo5732c());
    }

    /* renamed from: b */
    public final void mo5742b(Activity activity) {
        ieh ieh;
        try {
            ife.m12896d(this.f9755g.getAndSet(false));
            ieh = m8988a(iqm.FOREGROUND_TO_BACKGROUND);
        } catch (Exception e) {
            ieh = ife.m12822a((Throwable) e);
        }
        flw.m9191a(ieh);
    }

    /* renamed from: a */
    public final void mo5745a(Activity activity) {
        ieh ieh;
        if (!this.f9755g.get()) {
            if (this.f9755g.getAndSet(true)) {
                flw.m9202d("BatteryMetricService", "App is already in the foreground.", new Object[0]);
                ieh = ife.m12868b();
            } else {
                ieh = m8988a(iqm.BACKGROUND_TO_FOREGROUND);
            }
            flw.m9191a(ieh);
        }
    }

    /* renamed from: f */
    public final void mo5834f() {
        mo5745a((Activity) null);
        if (!this.f9754f.getAndSet(true)) {
            fid.m8938a(this.f9685a).mo5747a((fic) this);
        }
    }

    /* renamed from: d */
    public final void mo5733d() {
        if (this.f9754f.getAndSet(false)) {
            fid.m8938a(this.f9685a).mo5748b(this);
        }
        synchronized (this.f9752d) {
            this.f9752d.f10122a.f10156a.edit().remove("primes.battery.snapshot").commit();
        }
    }
}
