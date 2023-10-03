package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;

/* renamed from: androidx.media.F */
class C0485F implements Runnable {

    /* renamed from: Lq */
    final /* synthetic */ String f465Lq;

    /* renamed from: Mq */
    final /* synthetic */ int f466Mq;

    /* renamed from: Nq */
    final /* synthetic */ int f467Nq;

    /* renamed from: Oq */
    final /* synthetic */ Bundle f468Oq;
    final /* synthetic */ C0489J this$1;
    final /* synthetic */ C0490K val$callbacks;

    C0485F(C0489J j, C0490K k, int i, String str, int i2, Bundle bundle) {
        this.this$1 = j;
        this.val$callbacks = k;
        this.f467Nq = i;
        this.f465Lq = str;
        this.f466Mq = i2;
        this.f468Oq = bundle;
    }

    public void run() {
        IBinder asBinder = this.val$callbacks.asBinder();
        this.this$1.this$0.f478Ob.remove(asBinder);
        Iterator it = this.this$1.this$0.f477Nb.iterator();
        C0506m mVar = null;
        while (it.hasNext()) {
            C0506m mVar2 = (C0506m) it.next();
            if (mVar2.uid == this.f467Nq) {
                if (TextUtils.isEmpty(this.f465Lq) || this.f466Mq <= 0) {
                    mVar = new C0506m(this.this$1.this$0, mVar2.pkg, mVar2.pid, mVar2.uid, this.f468Oq, this.val$callbacks);
                }
                it.remove();
            }
        }
        C0506m mVar3 = mVar == null ? new C0506m(this.this$1.this$0, this.f465Lq, this.f466Mq, this.f467Nq, this.f468Oq, this.val$callbacks) : mVar;
        this.this$1.this$0.f478Ob.put(asBinder, mVar3);
        try {
            asBinder.linkToDeath(mVar3, 0);
        } catch (RemoteException unused) {
            Log.w("MBServiceCompat", "IBinder is already dead.");
        }
    }
}
