package p000;

import android.os.Bundle;
import java.util.concurrent.locks.Lock;

/* renamed from: emb */
/* compiled from: PG */
final class emb implements ent {

    /* renamed from: a */
    private final /* synthetic */ emc f8536a;

    public /* synthetic */ emb(emc emc) {
        this.f8536a = emc;
    }

    /* renamed from: a */
    public final void mo4997a(ejq ejq) {
        this.f8536a.f8543g.lock();
        try {
            emc emc = this.f8536a;
            emc.f8541e = ejq;
            emc.mo5005d();
        } finally {
            this.f8536a.f8543g.unlock();
        }
    }

    /* renamed from: a */
    public final void mo4996a(Bundle bundle) {
        this.f8536a.f8543g.lock();
        try {
            this.f8536a.f8541e = ejq.f8442a;
            this.f8536a.mo5005d();
        } finally {
            this.f8536a.f8543g.unlock();
        }
    }

    /* renamed from: a */
    public final void mo4995a(int i) {
        Lock lock;
        this.f8536a.f8543g.lock();
        try {
            emc emc = this.f8536a;
            if (!emc.f8542f) {
                emc.f8542f = true;
                emc.f8537a.mo4992a(i);
                lock = this.f8536a.f8543g;
            } else {
                emc.f8542f = false;
                emc.mo5000a(i);
                lock = this.f8536a.f8543g;
            }
            lock.unlock();
        } catch (Throwable th) {
            this.f8536a.f8543g.unlock();
            throw th;
        }
    }
}
