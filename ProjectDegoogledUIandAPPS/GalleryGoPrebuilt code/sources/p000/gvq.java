package p000;

/* renamed from: gvq */
/* compiled from: PG */
final /* synthetic */ class gvq implements Runnable {

    /* renamed from: a */
    private final gvu f12147a;

    /* renamed from: b */
    private final gva f12148b;

    /* renamed from: c */
    private final guo f12149c;

    public gvq(gvu gvu, gva gva, guo guo) {
        this.f12147a = gvu;
        this.f12148b = gva;
        this.f12149c = guo;
    }

    public final void run() {
        gvu gvu = this.f12147a;
        gva gva = this.f12148b;
        guo guo = this.f12149c;
        fxk.m9830b();
        ife.m12876b(!gva.equals(gvu.f12160f), (Object) "The same LoadTask was processed twice.");
        ife.m12896d(guo.mo7087b().isDone());
        if (gvu.f12164j.mo7093a(guo) && !guo.mo7087b().isCancelled()) {
            try {
                if (gva.mo7106a(gvu.f12160f)) {
                    guo.mo7088c();
                } else if (!guo.mo7087b().isCancelled()) {
                    int a = ((gtx) gvu.f12161g).f12049b.mo7084a(((gty) gva).f12058f, guo.mo7086a(), !gva.mo7107g());
                    guu guu = guu.LOCAL_STATE_CHANGE;
                    int i = a - 1;
                    if (a == 0) {
                        throw null;
                    } else if (i == 0) {
                        guo.mo7088c();
                        if (gva.mo7107g()) {
                            gvu.mo7123a((Throwable) new gum());
                        } else {
                            gvu.mo7121a(gva);
                        }
                    } else if (i == 1) {
                        gvu.mo7122a(gva, guo);
                        if (gvu.f12162h.mo7071c() && gvu.mo7124a()) {
                            ife.m12876b(gvu.f12162h.mo7072d().mo7646a(), (Object) "Completed load, fetch is still open, and the callbacks didn't receive data. This is an impossible state.");
                            gvu.m10901b((gua) gvu.f12162h.mo7070b());
                            gvu.f12162h = gvu.f12162h.mo7110a(false);
                        }
                    } else if (i != 2) {
                        Object[] objArr = new Object[1];
                        String str = a != 1 ? a != 2 ? a != 3 ? "null" : "SHOW_THEN_FETCH" : "SHOW" : "FETCH";
                        if (a != 0) {
                            objArr[0] = str;
                            throw new IllegalStateException(String.format("Unrecognized Action enum value %s", objArr));
                        }
                        throw null;
                    } else {
                        gvu.mo7122a(gva, guo);
                        if (gva.mo7107g()) {
                            gvu.mo7123a((Throwable) new gum());
                        } else {
                            gvu.mo7121a(gva);
                        }
                    }
                }
                if (gvu.f12162h.mo7071c() && gvu.mo7124a()) {
                    ife.m12876b(gvu.f12162h.mo7072d().mo7646a(), (Object) "Completed load, fetch is still open, and the callbacks didn't receive data. This is an impossible state.");
                    gvu.m10901b((gua) gvu.f12162h.mo7070b());
                    gvu.f12162h = gvu.f12162h.mo7110a(false);
                }
            } catch (gvf e) {
                gvu.mo7123a(e.getCause());
            } catch (Throwable th) {
                gvu.f12157c.execute(new gvp(th));
            }
        }
    }
}
