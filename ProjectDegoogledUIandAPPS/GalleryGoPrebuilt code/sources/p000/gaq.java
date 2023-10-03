package p000;

/* renamed from: gaq */
/* compiled from: PG */
final class gaq implements gaf {

    /* renamed from: a */
    public final /* synthetic */ gay f10797a;

    public gaq(gay gay) {
        this.f10797a = gay;
    }

    /* renamed from: a */
    public final void mo6370a() {
        boolean z;
        synchronized (this.f10797a.f10811k) {
            int i = this.f10797a.f10816p;
            if (i > 0) {
                z = true;
            } else {
                z = false;
            }
            ife.m12877b(z, "Refcount went negative!", i);
            gay gay = this.f10797a;
            gay.f10816p--;
            gay.mo6378a();
        }
    }
}
