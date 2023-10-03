package p000;

import android.os.Bundle;
import java.util.concurrent.locks.Lock;

/* renamed from: ema */
/* compiled from: PG */
final class ema implements ent {

    /* renamed from: a */
    private final /* synthetic */ emc f8535a;

    public /* synthetic */ ema(emc emc) {
        this.f8535a = emc;
    }

    /* renamed from: a */
    public final void mo4997a(ejq ejq) {
        this.f8535a.f8543g.lock();
        try {
            emc emc = this.f8535a;
            emc.f8540d = ejq;
            emc.mo5005d();
        } finally {
            this.f8535a.f8543g.unlock();
        }
    }

    /* renamed from: a */
    public final void mo4996a(Bundle bundle) {
        this.f8535a.f8543g.lock();
        try {
            emc emc = this.f8535a;
            Bundle bundle2 = emc.f8539c;
            if (bundle2 == null) {
                emc.f8539c = bundle;
            } else if (bundle != null) {
                bundle2.putAll(bundle);
            }
            this.f8535a.f8540d = ejq.f8442a;
            this.f8535a.mo5005d();
        } finally {
            this.f8535a.f8543g.unlock();
        }
    }

    /* renamed from: a */
    public final void mo4995a(int i) {
        Lock lock;
        ejq ejq;
        this.f8535a.f8543g.lock();
        try {
            emc emc = this.f8535a;
            if (emc.f8542f || (ejq = emc.f8541e) == null || !ejq.mo4895b()) {
                emc emc2 = this.f8535a;
                emc2.f8542f = false;
                emc2.mo5000a(i);
                lock = this.f8535a.f8543g;
            } else {
                emc emc3 = this.f8535a;
                emc3.f8542f = true;
                emc3.f8538b.mo4992a(i);
                lock = this.f8535a.f8543g;
            }
            lock.unlock();
        } catch (Throwable th) {
            this.f8535a.f8543g.unlock();
            throw th;
        }
    }
}
