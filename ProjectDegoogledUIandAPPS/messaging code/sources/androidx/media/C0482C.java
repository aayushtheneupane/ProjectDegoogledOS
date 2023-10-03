package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.media.C */
class C0482C implements Runnable {

    /* renamed from: Jq */
    final /* synthetic */ Bundle f462Jq;
    final /* synthetic */ C0489J this$1;
    final /* synthetic */ C0490K val$callbacks;
    final /* synthetic */ String val$id;
    final /* synthetic */ IBinder val$token;

    C0482C(C0489J j, C0490K k, String str, IBinder iBinder, Bundle bundle) {
        this.this$1 = j;
        this.val$callbacks = k;
        this.val$id = str;
        this.val$token = iBinder;
        this.f462Jq = bundle;
    }

    public void run() {
        C0506m mVar = (C0506m) this.this$1.this$0.f478Ob.get(this.val$callbacks.asBinder());
        if (mVar == null) {
            StringBuilder Pa = C0632a.m1011Pa("addSubscription for callback that isn't registered id=");
            Pa.append(this.val$id);
            Log.w("MBServiceCompat", Pa.toString());
            return;
        }
        this.this$1.this$0.mo4567a(this.val$id, mVar, this.val$token, this.f462Jq);
    }
}
