package p000;

/* renamed from: exe */
/* compiled from: PG */
public final class exe {

    /* renamed from: a */
    public final exb f9167a = new exb((byte[]) null);

    /* renamed from: a */
    public final void mo5364a(Exception exc) {
        exb exb = this.f9167a;
        abj.m86a((Object) exc, (Object) "Exception must not be null");
        synchronized (exb.f9158a) {
            exb.mo5360b();
            exb.f9160c = true;
            exb.f9163f = exc;
        }
        exb.f9159b.mo5362a(exb);
    }

    /* renamed from: a */
    public final void mo5365a(Object obj) {
        exb exb = this.f9167a;
        synchronized (exb.f9158a) {
            exb.mo5360b();
            exb.f9160c = true;
            exb.f9162e = obj;
        }
        exb.f9159b.mo5362a(exb);
    }

    /* renamed from: b */
    public final void mo5366b(Exception exc) {
        exb exb = this.f9167a;
        abj.m86a((Object) exc, (Object) "Exception must not be null");
        synchronized (exb.f9158a) {
            if (!exb.f9160c) {
                exb.f9160c = true;
                exb.f9163f = exc;
                exb.f9159b.mo5362a(exb);
            }
        }
    }
}
