package androidx.media;

import android.os.IBinder;
import android.util.Log;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.media.D */
class C0483D implements Runnable {
    final /* synthetic */ C0489J this$1;
    final /* synthetic */ C0490K val$callbacks;
    final /* synthetic */ String val$id;
    final /* synthetic */ IBinder val$token;

    C0483D(C0489J j, C0490K k, String str, IBinder iBinder) {
        this.this$1 = j;
        this.val$callbacks = k;
        this.val$id = str;
        this.val$token = iBinder;
    }

    public void run() {
        C0506m mVar = (C0506m) this.this$1.this$0.f478Ob.get(this.val$callbacks.asBinder());
        if (mVar == null) {
            StringBuilder Pa = C0632a.m1011Pa("removeSubscription for callback that isn't registered id=");
            Pa.append(this.val$id);
            Log.w("MBServiceCompat", Pa.toString());
        } else if (!this.this$1.this$0.mo4572a(this.val$id, mVar, this.val$token)) {
            StringBuilder Pa2 = C0632a.m1011Pa("removeSubscription called for ");
            Pa2.append(this.val$id);
            Pa2.append(" which is not subscribed");
            Log.w("MBServiceCompat", Pa2.toString());
        }
    }
}
