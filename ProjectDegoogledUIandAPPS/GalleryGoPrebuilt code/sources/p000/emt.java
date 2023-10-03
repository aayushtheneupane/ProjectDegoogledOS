package p000;

import android.os.Bundle;

/* renamed from: emt */
/* compiled from: PG */
final class emt implements ekt, eku {

    /* renamed from: a */
    private final /* synthetic */ emv f8572a;

    public /* synthetic */ emt(emv emv) {
        this.f8572a = emv;
    }

    /* renamed from: a */
    public final void mo4992a(int i) {
    }

    /* renamed from: a */
    public final void mo4993a(Bundle bundle) {
        emv emv = this.f8572a;
        emv.f8578e.mo5332a(new ems(emv));
    }

    /* renamed from: a */
    public final void mo4994a(ejq ejq) {
        this.f8572a.f8575b.lock();
        try {
            if (!this.f8572a.mo5022a(ejq)) {
                this.f8572a.mo5023b(ejq);
            } else {
                this.f8572a.mo5028f();
                this.f8572a.mo5027e();
            }
        } finally {
            this.f8572a.f8575b.unlock();
        }
    }
}
