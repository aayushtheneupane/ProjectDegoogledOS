package p000;

/* renamed from: hlj */
/* compiled from: PG */
public final class hlj implements Runnable, hlq {

    /* renamed from: a */
    private hlp f12977a;

    /* renamed from: b */
    private hlp f12978b;

    /* renamed from: c */
    private final boolean f12979c = fxk.m9826a();

    /* renamed from: d */
    private boolean f12980d;

    /* renamed from: e */
    private boolean f12981e;

    public hlj(hlp hlp) {
        this.f12977a = hlp;
        this.f12978b = hlp;
    }

    /* renamed from: a */
    public final ieh mo7548a(ieh ieh) {
        if (this.f12980d) {
            throw new IllegalStateException("Span was already closed. Did you attach it to a future after calling Tracer.endSpan()?");
        } else if (!this.f12981e) {
            this.f12981e = true;
            ieh.mo53a(this, idh.f13918a);
            return ieh;
        } else {
            throw new IllegalStateException("Signal is already attached to future");
        }
    }

    public final void close() {
        hlp hlp = this.f12978b;
        this.f12978b = null;
        try {
            if (!this.f12981e) {
                if (!this.f12980d) {
                    m11691a();
                } else {
                    throw new IllegalStateException("Span was already closed!");
                }
            }
        } finally {
            hnb.m11772a(hlp);
        }
    }

    /* renamed from: a */
    private final void m11691a() {
        boolean z = true;
        this.f12980d = true;
        hlp hlp = this.f12977a;
        if (!this.f12979c || this.f12981e || !fxk.m9826a()) {
            z = false;
        }
        hlp.mo7545a(z);
        this.f12977a = null;
    }

    public final void run() {
        if (!this.f12980d && this.f12981e) {
            m11691a();
        } else {
            fxk.m9824a(hli.f12976a);
        }
    }
}
