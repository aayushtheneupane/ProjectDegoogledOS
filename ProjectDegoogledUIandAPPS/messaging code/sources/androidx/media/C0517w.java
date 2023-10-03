package androidx.media;

import android.os.Bundle;

/* renamed from: androidx.media.w */
class C0517w extends C0514t {
    final /* synthetic */ MediaBrowserServiceCompat this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0517w(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
        super(mediaBrowserServiceCompat);
        this.this$0 = mediaBrowserServiceCompat;
    }

    /* renamed from: a */
    public void mo4604a(String str, C0520z zVar, Bundle bundle) {
        C0515u uVar = new C0515u(this, str, zVar, bundle);
        MediaBrowserServiceCompat mediaBrowserServiceCompat = this.this$0;
        C0506m mVar = mediaBrowserServiceCompat.f476Mb;
        mediaBrowserServiceCompat.mo4570a(str, (C0519y) uVar, bundle);
    }

    public void onCreate() {
        this.f492yq = new C0516v(this, this.this$0);
        this.f492yq.onCreate();
    }
}
