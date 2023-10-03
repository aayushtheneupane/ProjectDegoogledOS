package androidx.media;

import android.os.Bundle;
import android.support.p016v4.p017os.ResultReceiver;

/* renamed from: androidx.media.j */
class C0503j extends C0519y {

    /* renamed from: Hq */
    final /* synthetic */ ResultReceiver f488Hq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0503j(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
        super(obj);
        this.f488Hq = resultReceiver;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo4588d(Bundle bundle) {
        this.f488Hq.send(-1, bundle);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public void mo4587o(Object obj) {
        this.f488Hq.send(0, (Bundle) obj);
    }
}
