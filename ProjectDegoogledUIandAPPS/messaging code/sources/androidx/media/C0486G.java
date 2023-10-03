package androidx.media;

import android.os.IBinder;

/* renamed from: androidx.media.G */
class C0486G implements Runnable {
    final /* synthetic */ C0489J this$1;
    final /* synthetic */ C0490K val$callbacks;

    C0486G(C0489J j, C0490K k) {
        this.this$1 = j;
        this.val$callbacks = k;
    }

    public void run() {
        IBinder asBinder = this.val$callbacks.asBinder();
        C0506m mVar = (C0506m) this.this$1.this$0.f478Ob.remove(asBinder);
        if (mVar != null) {
            asBinder.unlinkToDeath(mVar, 0);
        }
    }
}
