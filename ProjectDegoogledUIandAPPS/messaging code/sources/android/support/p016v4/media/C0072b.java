package android.support.p016v4.media;

import android.media.browse.MediaBrowser;

/* renamed from: android.support.v4.media.b */
class C0072b extends MediaBrowser.ConnectionCallback {
    final /* synthetic */ C0073c this$0;

    C0072b(C0073c cVar) {
        this.this$0 = cVar;
    }

    public void onConnected() {
        C0075e eVar = this.this$0.f89be;
        if (eVar != null) {
            eVar.onConnected();
        }
        this.this$0.onConnected();
    }

    public void onConnectionFailed() {
        C0075e eVar = this.this$0.f89be;
        if (eVar != null) {
            eVar.onConnectionFailed();
        }
        this.this$0.onConnectionFailed();
    }

    public void onConnectionSuspended() {
        C0075e eVar = this.this$0.f89be;
        if (eVar != null) {
            eVar.onConnectionSuspended();
        }
        this.this$0.onConnectionSuspended();
    }
}
