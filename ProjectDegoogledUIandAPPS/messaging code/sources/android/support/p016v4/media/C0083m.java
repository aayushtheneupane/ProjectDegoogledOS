package android.support.p016v4.media;

import android.os.Bundle;
import android.support.p016v4.media.session.C0107q;
import java.util.List;

/* renamed from: android.support.v4.media.m */
class C0083m extends C0082l {
    final /* synthetic */ C0084n this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0083m(C0084n nVar) {
        super(nVar);
        this.this$0 = nVar;
    }

    public void onChildrenLoaded(String str, List list, Bundle bundle) {
        C0107q.m130b(bundle);
        this.this$0.onChildrenLoaded(str, MediaBrowserCompat$MediaItem.m82c(list), bundle);
    }

    public void onError(String str, Bundle bundle) {
        C0107q.m130b(bundle);
        this.this$0.onError(str, bundle);
    }
}
