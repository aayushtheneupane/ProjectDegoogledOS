package p000;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* renamed from: elq */
/* compiled from: PG */
public abstract class elq extends BasePendingResult {

    /* renamed from: a */
    public final ekn f8518a;

    /* renamed from: b */
    public final eki f8519b;

    protected elq(ekn ekn, ekv ekv) {
        super((ekv) abj.m86a((Object) ekv, (Object) "GoogleApiClient must not be null"));
        abj.m86a((Object) ekn, (Object) "Api must not be null");
        this.f8519b = ekn.mo4940a();
        this.f8518a = ekn;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo4881a(ekl ekl);

    /* renamed from: b */
    public final void mo4981b(ekl ekl) {
        if (ekl instanceof equ) {
            equ equ = (equ) ekl;
            ekl = null;
        }
        try {
            mo4881a(ekl);
        } catch (DeadObjectException e) {
            m7740a((RemoteException) e);
            throw e;
        } catch (RemoteException e2) {
            m7740a(e2);
        }
    }

    /* renamed from: a */
    private final void m7740a(RemoteException remoteException) {
        mo4980b(new Status(8, remoteException.getLocalizedMessage(), (byte[]) null));
    }

    /* renamed from: b */
    public final void mo4980b(Status status) {
        abj.m117b(!status.mo3499b(), (Object) "Failed result must not be success");
        mo3507a(mo3504a(status));
    }
}
