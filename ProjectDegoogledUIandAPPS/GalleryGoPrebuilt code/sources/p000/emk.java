package p000;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.api.Status;
import java.util.Set;

/* renamed from: emk */
/* compiled from: PG */
public final class emk implements enb {

    /* renamed from: a */
    private final ene f8557a;

    public emk(ene ene) {
        this.f8557a = ene;
    }

    /* renamed from: a */
    public final void mo5010a() {
    }

    /* renamed from: a */
    public final void mo5012a(Bundle bundle) {
    }

    /* renamed from: a */
    public final void mo5013a(ejq ejq, ekn ekn, boolean z) {
    }

    /* renamed from: b */
    public final void mo5015b() {
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: c */
    public final void mo5016c() {
        ena ena = this.f8557a.f8639l;
        ena.f8602b.lock();
        try {
            Set set = ena.f8617q;
            ena.f8602b.unlock();
            this.f8557a.mo5037d();
        } catch (Throwable th) {
            ena.f8602b.unlock();
            throw th;
        }
    }

    /* renamed from: a */
    public final elq mo5009a(elq elq) {
        return mo5014b(elq);
    }

    /* renamed from: b */
    public final elq mo5014b(elq elq) {
        try {
            this.f8557a.f8639l.f8618r.mo5093a(elq);
            ena ena = this.f8557a.f8639l;
            ekm ekm = (ekm) ena.f8610j.get(elq.f8519b);
            abj.m86a((Object) ekm, (Object) "Appropriate Api was not requested.");
            if (ekm.mo4932e() || !this.f8557a.f8634g.containsKey(elq.f8519b)) {
                if (ekm instanceof equ) {
                    equ equ = (equ) ekm;
                    ekm = null;
                }
                elq.mo4981b((ekl) ekm);
                return elq;
            }
            elq.mo4980b(new Status(17));
            return elq;
        } catch (DeadObjectException e) {
            this.f8557a.mo5036a((enc) new emj(this, this));
        }
    }

    /* renamed from: a */
    public final void mo5011a(int i) {
        this.f8557a.mo5037d();
        this.f8557a.f8640m.mo4995a(i);
    }
}
