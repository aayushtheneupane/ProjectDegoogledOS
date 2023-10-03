package p000;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.util.Log;
import com.firebase.jobdispatcher.GooglePlayReceiver;

/* renamed from: bhp */
/* compiled from: PG */
public final class bhp extends bim implements bhq {

    /* renamed from: a */
    private final /* synthetic */ bib f2381a;

    public bhp() {
        super("com.firebase.jobdispatcher.IRemoteJobService");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo2042a(int i, Parcel parcel, Parcel parcel2) {
        bhn bhn;
        if (i == 1) {
            Bundle bundle = (Bundle) bin.m2617a(parcel, Bundle.CREATOR);
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.firebase.jobdispatcher.IJobCallback");
                bhn = queryLocalInterface instanceof bhn ? (bhn) queryLocalInterface : new bhl(readStrongBinder);
            } else {
                bhn = null;
            }
            mo2043a(bundle, bhn);
        } else if (i != 2) {
            return false;
        } else {
            mo2044a((Bundle) bin.m2617a(parcel, Bundle.CREATOR), bin.m2621a(parcel));
        }
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public bhp(bib bib) {
        super("com.firebase.jobdispatcher.IRemoteJobService");
        this.f2381a = bib;
    }

    /* renamed from: a */
    public final void mo2043a(Bundle bundle, bhn bhn) {
        bhv a = GooglePlayReceiver.f4795a.mo2058a(bundle);
        if (a == null) {
            Log.wtf("FJD.JobService", "start: unknown invocation provided");
            return;
        }
        bib bib = this.f2381a;
        bhw a2 = a.mo2059a();
        Handler handler = bib.f2434a;
        bib.f2435b.execute(new bia(4, bib, a2, bhn, (bhz) null, false, 0));
    }

    /* renamed from: a */
    public final void mo2044a(Bundle bundle, boolean z) {
        bhv a = GooglePlayReceiver.f4795a.mo2058a(bundle);
        if (a == null) {
            Log.wtf("FJD.JobService", "stop: unknown invocation provided");
            return;
        }
        bib bib = this.f2381a;
        bhw a2 = a.mo2059a();
        Handler handler = bib.f2434a;
        bib.f2435b.execute(new bia(5, bib, a2, (bhn) null, (bhz) null, z, 0));
    }
}
