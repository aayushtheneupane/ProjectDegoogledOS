package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.media.A */
class C0480A implements Runnable {

    /* renamed from: Lq */
    final /* synthetic */ String f455Lq;

    /* renamed from: Mq */
    final /* synthetic */ int f456Mq;

    /* renamed from: Nq */
    final /* synthetic */ int f457Nq;

    /* renamed from: Oq */
    final /* synthetic */ Bundle f458Oq;
    final /* synthetic */ C0489J this$1;
    final /* synthetic */ C0490K val$callbacks;

    C0480A(C0489J j, C0490K k, String str, int i, int i2, Bundle bundle) {
        this.this$1 = j;
        this.val$callbacks = k;
        this.f455Lq = str;
        this.f456Mq = i;
        this.f457Nq = i2;
        this.f458Oq = bundle;
    }

    public void run() {
        IBinder asBinder = this.val$callbacks.asBinder();
        this.this$1.this$0.f478Ob.remove(asBinder);
        C0506m mVar = new C0506m(this.this$1.this$0, this.f455Lq, this.f456Mq, this.f457Nq, this.f458Oq, this.val$callbacks);
        mVar.root = this.this$1.this$0.onGetRoot(this.f455Lq, this.f457Nq, this.f458Oq);
        MediaBrowserServiceCompat mediaBrowserServiceCompat = this.this$1.this$0;
        if (mVar.root == null) {
            StringBuilder Pa = C0632a.m1011Pa("No root for client ");
            Pa.append(this.f455Lq);
            Pa.append(" from service ");
            Pa.append(C0480A.class.getName());
            Log.i("MBServiceCompat", Pa.toString());
            try {
                this.val$callbacks.onConnectFailed();
            } catch (RemoteException unused) {
                StringBuilder Pa2 = C0632a.m1011Pa("Calling onConnectFailed() failed. Ignoring. pkg=");
                Pa2.append(this.f455Lq);
                Log.w("MBServiceCompat", Pa2.toString());
            }
        } else {
            try {
                mediaBrowserServiceCompat.f478Ob.put(asBinder, mVar);
                asBinder.linkToDeath(mVar, 0);
                if (this.this$1.this$0.mSession != null) {
                    this.val$callbacks.mo4555a(mVar.root.getRootId(), this.this$1.this$0.mSession, mVar.root.getExtras());
                }
            } catch (RemoteException unused2) {
                StringBuilder Pa3 = C0632a.m1011Pa("Calling onConnect() failed. Dropping client. pkg=");
                Pa3.append(this.f455Lq);
                Log.w("MBServiceCompat", Pa3.toString());
                this.this$1.this$0.f478Ob.remove(asBinder);
            }
        }
    }
}
