package p000;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.firebase.jobdispatcher.GooglePlayReceiver;

/* renamed from: bhz */
/* compiled from: PG */
final class bhz {

    /* renamed from: a */
    public final bhx f2423a;

    /* renamed from: b */
    public final long f2424b;

    /* renamed from: c */
    private final bhn f2425c;

    public /* synthetic */ bhz(bhx bhx, bhn bhn, long j) {
        this.f2423a = bhx;
        this.f2425c = bhn;
        this.f2424b = j;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo2064a(int i) {
        try {
            this.f2425c.mo2041a(GooglePlayReceiver.f4795a.mo2057a(this.f2423a, new Bundle()), i);
        } catch (RemoteException e) {
            Log.e("FJD.JobService", "Failed to send result to driver", e);
        }
    }
}
