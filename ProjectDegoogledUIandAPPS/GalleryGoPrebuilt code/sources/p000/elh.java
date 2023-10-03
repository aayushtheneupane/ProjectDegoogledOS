package p000;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* renamed from: elh */
/* compiled from: PG */
abstract class elh extends elg {

    /* renamed from: a */
    public final exe f8506a;

    public elh(exe exe) {
        this.f8506a = exe;
    }

    /* renamed from: a */
    public void mo4963a(emg emg, boolean z) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract void mo4966d(enl enl);

    /* renamed from: a */
    public final void mo4962a(Status status) {
        this.f8506a.mo5366b(new eko(status));
    }

    /* renamed from: a */
    public final void mo4964a(Exception exc) {
        this.f8506a.mo5366b(exc);
    }

    /* renamed from: c */
    public final void mo4965c(enl enl) {
        try {
            mo4966d(enl);
        } catch (DeadObjectException e) {
            mo4962a(ell.m7732a((RemoteException) e));
            throw e;
        } catch (RemoteException e2) {
            mo4962a(ell.m7732a(e2));
        } catch (RuntimeException e3) {
            mo4964a((Exception) e3);
        }
    }
}
