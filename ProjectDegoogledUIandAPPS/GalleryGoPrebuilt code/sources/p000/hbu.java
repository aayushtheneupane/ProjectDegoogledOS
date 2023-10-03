package p000;

/* renamed from: hbu */
/* compiled from: PG */
public final class hbu implements C0438q {

    /* renamed from: a */
    private final C0002ab f12467a;

    public hbu(C0002ab abVar) {
        this.f12467a = abVar;
    }

    /* renamed from: a */
    public final void mo2556a() {
        hlq c = hnb.m11779c();
        try {
            this.f12467a.mo62a(C0546u.ON_CREATE);
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: c */
    public final void mo2561c(C0681z zVar) {
        hlq c = hnb.m11779c();
        try {
            this.f12467a.mo62a(C0546u.ON_DESTROY);
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: b */
    public final void mo2558b() {
        hlq c = hnb.m11779c();
        try {
            this.f12467a.mo62a(C0546u.ON_PAUSE);
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: c */
    public final void mo2560c() {
        hlq c = hnb.m11779c();
        try {
            this.f12467a.mo62a(C0546u.ON_RESUME);
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo2557a(C0681z zVar) {
        hlq c = hnb.m11779c();
        try {
            this.f12467a.mo62a(C0546u.ON_START);
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: b */
    public final void mo2559b(C0681z zVar) {
        hlq c = hnb.m11779c();
        try {
            this.f12467a.mo62a(C0546u.ON_STOP);
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
