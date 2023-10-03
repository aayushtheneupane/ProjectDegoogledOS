package p000;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.FrameMetrics;
import android.view.Window;

/* renamed from: fjb */
/* compiled from: PG */
final class fjb implements Window.OnFrameMetricsAvailableListener, fhv, fhu {

    /* renamed from: a */
    public HandlerThread f9783a;

    /* renamed from: b */
    public Handler f9784b;

    /* renamed from: c */
    private final fjc f9785c;

    /* renamed from: d */
    private final boolean f9786d;

    /* renamed from: e */
    private Activity f9787e;

    /* renamed from: f */
    private boolean f9788f;

    public fjb(fjc fjc, boolean z) {
        this.f9785c = fjc;
        this.f9786d = z;
        if (z) {
            this.f9788f = true;
        }
    }

    /* renamed from: b */
    private final void m9019b() {
        Activity activity = this.f9787e;
        if (activity != null) {
            Window window = activity.getWindow();
            if (this.f9784b == null) {
                HandlerThread handlerThread = new HandlerThread("Primes-Jank");
                this.f9783a = handlerThread;
                handlerThread.start();
                this.f9784b = new Handler(this.f9783a.getLooper());
            }
            window.addOnFrameMetricsAvailableListener(this, this.f9784b);
        }
    }

    /* renamed from: c */
    private final void m9021c() {
        Activity activity = this.f9787e;
        if (activity != null) {
            try {
                activity.getWindow().removeOnFrameMetricsAvailableListener(this);
            } catch (RuntimeException e) {
                flw.m9192a("FrameMetricService", "remove frame metrics listener failed", (Throwable) e, new Object[0]);
            }
        }
    }

    /* renamed from: c */
    private static String m9020c(Activity activity) {
        if (activity instanceof fnk) {
            return fjy.m9052a(((fnk) activity).mo5980a());
        }
        return activity.getClass().getName();
    }

    /* renamed from: a */
    public final void mo5737a(Activity activity) {
        iqx iqx;
        fof fof;
        iqx iqx2;
        synchronized (this) {
            if (this.f9788f) {
                m9021c();
            }
            iqx = null;
            this.f9787e = null;
        }
        if (this.f9786d) {
            fjc fjc = this.f9785c;
            String c = m9020c(activity);
            fjd fjd = ((fja) fjc).f9782a;
            synchronized (fjd.f9790e) {
                fof = (fof) fjd.f9790e.remove(c);
                if (fjd.f9790e.isEmpty() && !fjd.f9791f) {
                    fjd.f9789d.mo5869a();
                }
            }
            if (fof == null) {
                flw.m9202d("FrameMetricService", "Measurement not found: %s", c);
            } else if (fof.mo5996a()) {
                iir g = isc.f14974r.mo8793g();
                irw b = fof.mo5997b();
                iir iir = (iir) b.mo8790b(5);
                iir.mo8503a((iix) b);
                int a = foj.m9310a(fjd.f9685a);
                if (iir.f14319c) {
                    iir.mo8751b();
                    iir.f14319c = false;
                }
                irw irw = (irw) iir.f14318b;
                irw irw2 = irw.f14932h;
                irw.f14934a |= 16;
                irw.f14940g = a;
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                isc isc = (isc) g.f14318b;
                irw irw3 = (irw) iir.mo8770g();
                irw3.getClass();
                isc.f14987l = irw3;
                isc.f14976a |= 2048;
                iqk iqk = fjd.f9794i;
                if (iqk != null) {
                    try {
                        iqx2 = (iqx) iqk.mo2097a();
                    } catch (Exception e) {
                        flw.m9198b("FrameMetricService", "Exception while getting jank metric extension!", e, new Object[0]);
                        iqx2 = null;
                    }
                } else {
                    iqx2 = null;
                }
                if (!iqx.f14783a.equals(iqx2)) {
                    iqx = iqx2;
                }
                if (iqx != null) {
                    if (g.f14319c) {
                        g.mo8751b();
                        g.f14319c = false;
                    }
                    isc isc2 = (isc) g.f14318b;
                    iqx.getClass();
                    isc2.f14988m = iqx;
                    isc2.f14976a |= 8192;
                }
                fjd.mo5730a(c, true, (isc) g.mo8770g(), (iqx) null, (String) null);
            }
        }
    }

    /* renamed from: b */
    public final void mo5738b(Activity activity) {
        if (this.f9786d) {
            fjc fjc = this.f9785c;
            String c = m9020c(activity);
            fjd fjd = ((fja) fjc).f9782a;
            synchronized (fjd.f9790e) {
                if (fjd.f9790e.containsKey(c)) {
                    flw.m9202d("FrameMetricService", "measurement already started: %s", c);
                } else if (fjd.f9790e.size() < 25) {
                    fjd.f9790e.put(c, fjd.f9793h.mo5998a());
                    if (fjd.f9790e.size() == 1 && !fjd.f9791f) {
                        flw.m9199b("FrameMetricService", "measuring start", new Object[0]);
                        fjb fjb = fjd.f9789d;
                        synchronized (fjb) {
                            fjb.f9788f = true;
                            if (fjb.f9787e == null) {
                                flw.m9199b("FrameMetricService", "No activity", new Object[0]);
                            } else {
                                fjb.m9019b();
                            }
                        }
                    }
                } else {
                    flw.m9202d("FrameMetricService", "Too many concurrent measurements, ignoring %s", c);
                }
            }
        }
        synchronized (this) {
            this.f9787e = activity;
            if (this.f9788f) {
                m9019b();
            }
        }
    }

    public final void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i) {
        double metric = (double) frameMetrics.getMetric(8);
        Double.isNaN(metric);
        int i2 = (int) (metric / 1000000.0d);
        fjd fjd = ((fja) this.f9785c).f9782a;
        synchronized (fjd.f9790e) {
            for (fof a : fjd.f9790e.values()) {
                a.mo5995a(i2, fjd.f9792g);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo5869a() {
        synchronized (this) {
            this.f9788f = false;
            m9021c();
        }
    }
}
