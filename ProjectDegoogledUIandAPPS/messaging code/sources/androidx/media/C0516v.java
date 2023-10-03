package androidx.media;

import android.content.Context;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.support.p016v4.media.session.C0107q;

/* renamed from: androidx.media.v */
class C0516v extends C0512s {
    final /* synthetic */ C0517w this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0516v(C0517w wVar, Context context) {
        super(wVar, context);
        this.this$1 = wVar;
    }

    public void onLoadChildren(String str, MediaBrowserService.Result result, Bundle bundle) {
        C0107q.m130b(bundle);
        C0517w wVar = this.this$1;
        C0506m mVar = wVar.this$0.f476Mb;
        wVar.mo4604a(str, new C0520z(result), bundle);
        MediaBrowserServiceCompat mediaBrowserServiceCompat = this.this$1.this$0;
    }
}
