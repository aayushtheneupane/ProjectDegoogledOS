package p000;

import android.app.Application;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: fiz */
/* compiled from: PG */
final class fiz extends fhn implements fmc {

    /* renamed from: d */
    public final AtomicBoolean f9763d = new AtomicBoolean();

    /* renamed from: e */
    public volatile fjy f9764e;

    /* renamed from: f */
    public final iqk f9765f;

    /* renamed from: g */
    public final fpa f9766g;

    /* renamed from: h */
    public final AtomicBoolean f9767h;

    /* renamed from: i */
    private final boolean f9768i;

    /* renamed from: j */
    private final int f9769j;

    /* renamed from: k */
    private final fid f9770k;

    /* renamed from: l */
    private volatile fix f9771l;

    /* renamed from: m */
    private volatile irt f9772m;

    public fiz(iqk iqk, iqk iqk2, fpa fpa, hqk hqk, hqk hqk2, Application application, float f, boolean z) {
        super(iqk, application, hqk, hqk2, 1);
        ife.m12898e((Object) fpa);
        boolean z2 = false;
        if (f > 0.0f && f <= 100.0f) {
            z2 = true;
        }
        ife.m12845a(z2, (Object) "StartupSamplePercentage should be a floating number > 0 and <= 100.");
        this.f9770k = fid.m8938a(application);
        this.f9768i = foq.m9328a(f / 100.0f).mo6000a();
        this.f9769j = (int) (100.0f / f);
        this.f9765f = iqk2;
        this.f9766g = fpa;
        this.f9767h = new AtomicBoolean(z);
    }

    /* renamed from: g */
    public final void mo5848g() {
        if (this.f9767h.getAndSet(false)) {
            mo5847a(isa.PRIMES_CRASH_MONITORING_INITIALIZED);
            mo5847a(isa.PRIMES_FIRST_ACTIVITY_LAUNCHED);
        }
    }

    /* renamed from: f */
    public final void mo5834f() {
        flw.m9199b("CrashMetricService", "onFirstActivityCreated", new Object[0]);
        if (!this.f9767h.get()) {
            isa isa = isa.PRIMES_FIRST_ACTIVITY_LAUNCHED;
            if (!mo5731b() || !this.f9768i) {
                flw.m9201c("CrashMetricService", "Startup metric for '%s' dropped.", isa);
            } else if (fxk.m9826a()) {
                mo5732c().mo5931a((Runnable) new fiu(this, isa));
            } else {
                mo5847a(isa);
            }
        }
        this.f9771l = new fiw(this);
        this.f9770k.mo5747a((fic) this.f9771l);
    }

    /* renamed from: e */
    public final void mo5833e() {
        flw.m9199b("CrashMetricService", "onPrimesInitialize", new Object[0]);
        if (this.f9767h.get()) {
            this.f9772m = null;
        } else if (mo5731b() && this.f9768i) {
            mo5847a(isa.PRIMES_CRASH_MONITORING_INITIALIZED);
        } else {
            flw.m9201c("CrashMetricService", "Startup metric for 'PRIMES_CRASH_MONITORING_INITIALIZED' dropped.", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo5847a(isa isa) {
        iir g = isc.f14974r.mo8793g();
        iir g2 = isb.f14969d.mo8793g();
        int i = this.f9769j;
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        isb isb = (isb) g2.f14318b;
        int i2 = isb.f14971a | 2;
        isb.f14971a = i2;
        isb.f14973c = i;
        isb.f14972b = isa.f14968c;
        isb.f14971a = i2 | 1;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        isc isc = (isc) g.f14318b;
        isb isb2 = (isb) g2.mo8770g();
        isb2.getClass();
        isc.f14984i = isb2;
        isc.f14976a |= 128;
        mo5728a((isc) g.mo8770g());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo5846a(fjy fjy) {
        String valueOf = String.valueOf(fjy.m9052a(fjy));
        flw.m9199b("CrashMetricService", valueOf.length() == 0 ? new String("activeComponentName: ") : "activeComponentName: ".concat(valueOf), new Object[0]);
        this.f9764e = fjy;
    }

    /* renamed from: d */
    public final void mo5733d() {
        if (this.f9771l != null) {
            this.f9770k.mo5748b(this.f9771l);
            this.f9771l = null;
        }
        if (this.f9763d.get() && (Thread.getDefaultUncaughtExceptionHandler() instanceof fiy)) {
            Thread.setDefaultUncaughtExceptionHandler(((fiy) Thread.getDefaultUncaughtExceptionHandler()).f9761a);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final Thread.UncaughtExceptionHandler mo5845a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        return new fiy(this, uncaughtExceptionHandler);
    }
}
