package p000;

import android.os.Bundle;

/* renamed from: ely */
/* compiled from: PG */
public final class ely implements ekt, eku {

    /* renamed from: a */
    public final ekn f8531a;

    /* renamed from: b */
    public elz f8532b;

    /* renamed from: c */
    private final boolean f8533c;

    public ely(ekn ekn, boolean z) {
        this.f8531a = ekn;
        this.f8533c = z;
    }

    /* renamed from: a */
    private final void m7753a() {
        abj.m86a((Object) this.f8532b, (Object) "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }

    /* renamed from: a */
    public final void mo4993a(Bundle bundle) {
        m7753a();
        this.f8532b.mo4993a(bundle);
    }

    /* renamed from: a */
    public final void mo4994a(ejq ejq) {
        m7753a();
        elz elz = this.f8532b;
        ekn ekn = this.f8531a;
        boolean z = this.f8533c;
        ene ene = (ene) elz;
        ene.f8628a.lock();
        try {
            ((ene) elz).f8637j.mo5013a(ejq, ekn, z);
        } finally {
            ene.f8628a.unlock();
        }
    }

    /* renamed from: a */
    public final void mo4992a(int i) {
        m7753a();
        this.f8532b.mo4992a(i);
    }
}
