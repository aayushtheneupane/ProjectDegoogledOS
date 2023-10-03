package p000;

import com.google.android.gms.common.api.Status;
import java.util.Set;

/* renamed from: eno */
/* compiled from: PG */
final class eno implements eoi, epc {

    /* renamed from: a */
    public final ekm f8665a;

    /* renamed from: b */
    public final eln f8666b;

    /* renamed from: c */
    public eqg f8667c = null;

    /* renamed from: d */
    public Set f8668d = null;

    /* renamed from: e */
    public boolean f8669e = false;

    /* renamed from: f */
    public final /* synthetic */ enp f8670f;

    public eno(enp enp, ekm ekm, eln eln) {
        this.f8670f = enp;
        this.f8665a = ekm;
        this.f8666b = eln;
    }

    /* renamed from: a */
    public final void mo5018a(ejq ejq) {
        enp enp = this.f8670f;
        Status status = enp.f8671a;
        enp.f8684m.post(new enn(this, ejq));
    }

    /* renamed from: b */
    public final void mo5057b(ejq ejq) {
        enp enp = this.f8670f;
        Status status = enp.f8671a;
        enl enl = (enl) enp.f8681j.get(this.f8666b);
        abj.m90a(enl.f8655h.f8684m);
        enl.f8649b.mo4931d();
        enl.mo4994a(ejq);
    }

    /* renamed from: a */
    public final void mo5056a() {
        eqg eqg;
        if (this.f8669e && (eqg = this.f8667c) != null) {
            this.f8665a.mo4929a(eqg, this.f8668d);
        }
    }
}
