package p000;

import android.os.Build;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import com.google.android.gms.common.api.Status;

/* renamed from: ell */
/* compiled from: PG */
public abstract class ell {
    /* renamed from: a */
    public abstract void mo4962a(Status status);

    /* renamed from: a */
    public abstract void mo4963a(emg emg, boolean z);

    /* renamed from: a */
    public abstract void mo4964a(Exception exc);

    /* renamed from: c */
    public abstract void mo4965c(enl enl);

    /* renamed from: a */
    public static Status m7732a(RemoteException remoteException) {
        StringBuilder sb = new StringBuilder();
        int i = Build.VERSION.SDK_INT;
        if (remoteException instanceof TransactionTooLargeException) {
            sb.append("TransactionTooLargeException: ");
        }
        sb.append(remoteException.getLocalizedMessage());
        return new Status(8, sb.toString());
    }
}
