package androidx.media;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.media.g */
class C0500g extends C0519y {

    /* renamed from: Dq */
    final /* synthetic */ C0506m f482Dq;

    /* renamed from: Eq */
    final /* synthetic */ String f483Eq;

    /* renamed from: Fq */
    final /* synthetic */ Bundle f484Fq;

    /* renamed from: Gq */
    final /* synthetic */ Bundle f485Gq;
    final /* synthetic */ MediaBrowserServiceCompat this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0500g(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, C0506m mVar, String str, Bundle bundle, Bundle bundle2) {
        super(obj);
        this.this$0 = mediaBrowserServiceCompat;
        this.f482Dq = mVar;
        this.f483Eq = str;
        this.f484Fq = bundle;
        this.f485Gq = bundle2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public void mo4587o(Object obj) {
        List list = (List) obj;
        if (this.this$0.f478Ob.get(this.f482Dq.callbacks.asBinder()) == this.f482Dq) {
            if ((getFlags() & 1) != 0) {
                list = this.this$0.mo4562a(list, this.f484Fq);
            }
            try {
                this.f482Dq.callbacks.mo4556a(this.f483Eq, list, this.f484Fq, this.f485Gq);
            } catch (RemoteException unused) {
                StringBuilder Pa = C0632a.m1011Pa("Calling onLoadChildren() failed for id=");
                Pa.append(this.f483Eq);
                Pa.append(" package=");
                Pa.append(this.f482Dq.pkg);
                Log.w("MBServiceCompat", Pa.toString());
            }
        } else if (MediaBrowserServiceCompat.DEBUG) {
            StringBuilder Pa2 = C0632a.m1011Pa("Not sending onLoadChildren result for connection that has been disconnected. pkg=");
            Pa2.append(this.f482Dq.pkg);
            Pa2.append(" id=");
            Pa2.append(this.f483Eq);
            Log.d("MBServiceCompat", Pa2.toString());
        }
    }
}
