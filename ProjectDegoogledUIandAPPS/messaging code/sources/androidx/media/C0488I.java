package androidx.media;

import android.os.Bundle;
import android.support.p016v4.p017os.ResultReceiver;
import android.util.Log;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.media.I */
class C0488I implements Runnable {

    /* renamed from: Hq */
    final /* synthetic */ ResultReceiver f472Hq;

    /* renamed from: Rq */
    final /* synthetic */ Bundle f473Rq;

    /* renamed from: Sq */
    final /* synthetic */ String f474Sq;
    final /* synthetic */ C0489J this$1;
    final /* synthetic */ C0490K val$callbacks;

    C0488I(C0489J j, C0490K k, String str, Bundle bundle, ResultReceiver resultReceiver) {
        this.this$1 = j;
        this.val$callbacks = k;
        this.f474Sq = str;
        this.f473Rq = bundle;
        this.f472Hq = resultReceiver;
    }

    public void run() {
        C0506m mVar = (C0506m) this.this$1.this$0.f478Ob.get(this.val$callbacks.asBinder());
        if (mVar == null) {
            StringBuilder Pa = C0632a.m1011Pa("sendCustomAction for callback that isn't registered action=");
            Pa.append(this.f474Sq);
            Pa.append(", extras=");
            Pa.append(this.f473Rq);
            Log.w("MBServiceCompat", Pa.toString());
            return;
        }
        this.this$1.this$0.mo4564a(this.f474Sq, this.f473Rq, mVar, this.f472Hq);
    }
}
