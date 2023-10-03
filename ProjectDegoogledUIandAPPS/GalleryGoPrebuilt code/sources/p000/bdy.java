package p000;

/* renamed from: bdy */
/* compiled from: PG */
public final class bdy implements bec, bea {

    /* renamed from: a */
    public volatile bea f2126a;

    /* renamed from: b */
    public volatile bea f2127b;

    /* renamed from: c */
    private final Object f2128c;

    /* renamed from: d */
    private final bec f2129d;

    /* renamed from: e */
    private beb f2130e = beb.CLEARED;

    /* renamed from: f */
    private beb f2131f = beb.CLEARED;

    public bdy(Object obj, bec bec) {
        this.f2128c = obj;
        this.f2129d = bec;
    }

    /* renamed from: a */
    public final void mo1878a() {
        synchronized (this.f2128c) {
            if (this.f2130e != beb.RUNNING) {
                this.f2130e = beb.RUNNING;
                this.f2126a.mo1878a();
            }
        }
    }

    /* renamed from: d */
    public final boolean mo1885d(bea bea) {
        boolean z;
        synchronized (this.f2128c) {
            bec bec = this.f2129d;
            z = false;
            if (bec != null) {
                if (!bec.mo1885d(this)) {
                }
            }
            if (m2254g(bea)) {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: c */
    public final boolean mo1883c(bea bea) {
        boolean z;
        synchronized (this.f2128c) {
            bec bec = this.f2129d;
            z = false;
            if (bec != null) {
                if (!bec.mo1883c(this)) {
                }
            }
            if (m2254g(bea)) {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: b */
    public final boolean mo1881b(bea bea) {
        boolean z;
        synchronized (this.f2128c) {
            bec bec = this.f2129d;
            z = false;
            if (bec != null) {
                if (!bec.mo1881b(this)) {
                }
            }
            if (m2254g(bea)) {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: b */
    public final void mo1880b() {
        synchronized (this.f2128c) {
            this.f2130e = beb.CLEARED;
            this.f2126a.mo1880b();
            if (this.f2131f != beb.CLEARED) {
                this.f2131f = beb.CLEARED;
                this.f2127b.mo1880b();
            }
        }
    }

    /* renamed from: h */
    public final bec mo1891h() {
        bec h;
        synchronized (this.f2128c) {
            bec bec = this.f2129d;
            h = bec != null ? bec.mo1891h() : this;
        }
        return h;
    }

    /* renamed from: g */
    public final boolean mo1890g() {
        boolean z;
        synchronized (this.f2128c) {
            z = true;
            if (!this.f2126a.mo1890g() && !this.f2127b.mo1890g()) {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: f */
    public final boolean mo1889f() {
        boolean z;
        synchronized (this.f2128c) {
            z = false;
            if (this.f2130e == beb.CLEARED && this.f2131f == beb.CLEARED) {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: e */
    public final boolean mo1887e() {
        boolean z;
        synchronized (this.f2128c) {
            z = true;
            if (!(this.f2130e == beb.SUCCESS || this.f2131f == beb.SUCCESS)) {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: a */
    public final boolean mo1879a(bea bea) {
        if (bea instanceof bdy) {
            bdy bdy = (bdy) bea;
            if (!this.f2126a.mo1879a(bdy.f2126a) || !this.f2127b.mo1879a(bdy.f2127b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: d */
    public final boolean mo1884d() {
        boolean z;
        synchronized (this.f2128c) {
            z = true;
            if (!(this.f2130e == beb.RUNNING || this.f2131f == beb.RUNNING)) {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: g */
    private final boolean m2254g(bea bea) {
        if (bea.equals(this.f2126a) || (this.f2130e == beb.FAILED && bea.equals(this.f2127b))) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        return;
     */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1888f(p000.bea r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.f2128c
            monitor-enter(r0)
            bea r1 = r2.f2127b     // Catch:{ all -> 0x002d }
            boolean r3 = r3.equals(r1)     // Catch:{ all -> 0x002d }
            if (r3 != 0) goto L_0x0020
            beb r3 = p000.beb.FAILED     // Catch:{ all -> 0x002d }
            r2.f2130e = r3     // Catch:{ all -> 0x002d }
            beb r3 = r2.f2131f     // Catch:{ all -> 0x002d }
            beb r1 = p000.beb.RUNNING     // Catch:{ all -> 0x002d }
            if (r3 == r1) goto L_0x001e
            beb r3 = p000.beb.RUNNING     // Catch:{ all -> 0x002d }
            r2.f2131f = r3     // Catch:{ all -> 0x002d }
            bea r3 = r2.f2127b     // Catch:{ all -> 0x002d }
            r3.mo1878a()     // Catch:{ all -> 0x002d }
        L_0x001e:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x0020:
            beb r3 = p000.beb.FAILED     // Catch:{ all -> 0x002d }
            r2.f2131f = r3     // Catch:{ all -> 0x002d }
            bec r3 = r2.f2129d     // Catch:{ all -> 0x002d }
            if (r3 == 0) goto L_0x002b
            r3.mo1888f(r2)     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bdy.mo1888f(bea):void");
    }

    /* renamed from: e */
    public final void mo1886e(bea bea) {
        synchronized (this.f2128c) {
            if (bea.equals(this.f2126a)) {
                this.f2130e = beb.SUCCESS;
            } else if (bea.equals(this.f2127b)) {
                this.f2131f = beb.SUCCESS;
            }
            bec bec = this.f2129d;
            if (bec != null) {
                bec.mo1886e(this);
            }
        }
    }

    /* renamed from: c */
    public final void mo1882c() {
        synchronized (this.f2128c) {
            if (this.f2130e == beb.RUNNING) {
                this.f2130e = beb.PAUSED;
                this.f2126a.mo1882c();
            }
            if (this.f2131f == beb.RUNNING) {
                this.f2131f = beb.PAUSED;
                this.f2127b.mo1882c();
            }
        }
    }
}
