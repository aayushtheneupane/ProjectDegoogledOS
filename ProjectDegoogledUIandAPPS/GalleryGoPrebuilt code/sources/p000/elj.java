package p000;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* renamed from: elj */
/* compiled from: PG */
public final class elj extends elg {

    /* renamed from: a */
    private final eoq f8508a;

    /* renamed from: b */
    private final exe f8509b;

    public elj(eoq eoq, exe exe) {
        this.f8509b = exe;
        this.f8508a = eoq;
    }

    /* renamed from: b */
    public final boolean mo4961b(enl enl) {
        return this.f8508a.f8724a;
    }

    /* renamed from: a */
    public final ejt[] mo4960a(enl enl) {
        return this.f8508a.mo5091a();
    }

    /* renamed from: a */
    public final void mo4962a(Status status) {
        this.f8509b.mo5366b(C0652xy.m16062a(status));
    }

    /* renamed from: a */
    public final void mo4964a(Exception exc) {
        this.f8509b.mo5366b(exc);
    }

    /* renamed from: c */
    public final void mo4965c(enl enl) {
        try {
            this.f8508a.mo5089a(enl.f8649b, this.f8509b);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e2) {
            mo4962a(ell.m7732a(e2));
        } catch (RuntimeException e3) {
            mo4964a((Exception) e3);
        }
    }

    /* renamed from: a */
    public final void mo4963a(emg emg, boolean z) {
        exe exe = this.f8509b;
        emg.f8553b.put(exe, Boolean.valueOf(z));
        exb exb = exe.f9167a;
        emf emf = new emf(emg, exe);
        exb.f9159b.mo5363a((exc) new ews(exg.f9169a, emf));
        exb.mo5361c();
    }
}
