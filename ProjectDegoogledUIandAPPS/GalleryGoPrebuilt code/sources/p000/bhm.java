package p000;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import com.firebase.jobdispatcher.GooglePlayReceiver;
import java.util.Map;

/* renamed from: bhm */
/* compiled from: PG */
public final class bhm extends bim implements bhn {

    /* renamed from: a */
    private final /* synthetic */ bhd f2380a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public bhm(bhd bhd) {
        super("com.firebase.jobdispatcher.IJobCallback");
        this.f2380a = bhd;
    }

    /* renamed from: a */
    public final void mo2041a(Bundle bundle, int i) {
        bic bic;
        bhv a = GooglePlayReceiver.f4795a.mo2058a(bundle);
        if (a == null) {
            Log.wtf("FJD.ExecutionDelegator", "jobFinished: unknown invocation provided");
            return;
        }
        bhd bhd = this.f2380a;
        bhw a2 = a.mo2059a();
        Map map = bhd.f2361a;
        synchronized (bhd.f2361a) {
            bic = (bic) bhd.f2361a.get(a2.f2413b);
        }
        if (bic != null) {
            bic.mo2075a(a2);
            if (bic.mo2077a()) {
                synchronized (bhd.f2361a) {
                    bhd.f2361a.remove(a2.f2413b);
                }
            }
        }
        bhd.f2366f.mo2036a(a2, i);
    }

    public bhm() {
        super("com.firebase.jobdispatcher.IJobCallback");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo2042a(int i, Parcel parcel, Parcel parcel2) {
        if (i != 1) {
            return false;
        }
        mo2041a((Bundle) bin.m2617a(parcel, Bundle.CREATOR), parcel.readInt());
        return true;
    }
}
