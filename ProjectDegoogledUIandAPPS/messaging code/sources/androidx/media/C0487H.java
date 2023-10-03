package androidx.media;

import android.os.Bundle;
import android.support.p016v4.p017os.ResultReceiver;
import android.util.Log;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.media.H */
class C0487H implements Runnable {

    /* renamed from: Hq */
    final /* synthetic */ ResultReceiver f469Hq;

    /* renamed from: Qq */
    final /* synthetic */ String f470Qq;

    /* renamed from: Rq */
    final /* synthetic */ Bundle f471Rq;
    final /* synthetic */ C0489J this$1;
    final /* synthetic */ C0490K val$callbacks;

    C0487H(C0489J j, C0490K k, String str, Bundle bundle, ResultReceiver resultReceiver) {
        this.this$1 = j;
        this.val$callbacks = k;
        this.f470Qq = str;
        this.f471Rq = bundle;
        this.f469Hq = resultReceiver;
    }

    public void run() {
        C0506m mVar = (C0506m) this.this$1.this$0.f478Ob.get(this.val$callbacks.asBinder());
        if (mVar == null) {
            StringBuilder Pa = C0632a.m1011Pa("search for callback that isn't registered query=");
            Pa.append(this.f470Qq);
            Log.w("MBServiceCompat", Pa.toString());
            return;
        }
        this.this$1.this$0.mo4573b(this.f470Qq, this.f471Rq, mVar, this.f469Hq);
    }
}
