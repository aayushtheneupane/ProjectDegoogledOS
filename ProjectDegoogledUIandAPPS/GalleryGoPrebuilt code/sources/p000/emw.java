package p000;

import android.os.Bundle;
import java.util.Collections;

/* renamed from: emw */
/* compiled from: PG */
public final class emw implements enb {

    /* renamed from: a */
    private final ene f8595a;

    public emw(ene ene) {
        this.f8595a = ene;
    }

    /* renamed from: a */
    public final void mo5011a(int i) {
    }

    /* renamed from: a */
    public final void mo5012a(Bundle bundle) {
    }

    /* renamed from: a */
    public final void mo5013a(ejq ejq, ekn ekn, boolean z) {
    }

    /* renamed from: c */
    public final void mo5016c() {
    }

    /* renamed from: a */
    public final void mo5010a() {
        for (ekm d : this.f8595a.f8633f.values()) {
            d.mo4931d();
        }
        this.f8595a.f8639l.f8611k = Collections.emptySet();
    }

    /* renamed from: b */
    public final void mo5015b() {
        ene ene = this.f8595a;
        ene.f8628a.lock();
        try {
            ene.f8637j = new emv(ene, ene.f8635h, ene.f8636i, ene.f8631d, ene.f8641n, ene.f8628a, ene.f8630c, (byte[]) null, (byte[]) null);
            ene.f8637j.mo5010a();
            ene.f8629b.signalAll();
        } finally {
            ene.f8628a.unlock();
        }
    }

    /* renamed from: a */
    public final elq mo5009a(elq elq) {
        this.f8595a.f8639l.f8608h.add(elq);
        return elq;
    }

    /* renamed from: b */
    public final elq mo5014b(elq elq) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
