package androidx.media;

import android.support.p016v4.p017os.ResultReceiver;
import android.util.Log;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.media.E */
class C0484E implements Runnable {

    /* renamed from: Hq */
    final /* synthetic */ ResultReceiver f463Hq;

    /* renamed from: Pq */
    final /* synthetic */ String f464Pq;
    final /* synthetic */ C0489J this$1;
    final /* synthetic */ C0490K val$callbacks;

    C0484E(C0489J j, C0490K k, String str, ResultReceiver resultReceiver) {
        this.this$1 = j;
        this.val$callbacks = k;
        this.f464Pq = str;
        this.f463Hq = resultReceiver;
    }

    public void run() {
        C0506m mVar = (C0506m) this.this$1.this$0.f478Ob.get(this.val$callbacks.asBinder());
        if (mVar == null) {
            StringBuilder Pa = C0632a.m1011Pa("getMediaItem for callback that isn't registered id=");
            Pa.append(this.f464Pq);
            Log.w("MBServiceCompat", Pa.toString());
            return;
        }
        this.this$1.this$0.mo4568a(this.f464Pq, mVar, this.f463Hq);
    }
}
