package androidx.media;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.support.p016v4.media.session.C0107q;

/* renamed from: androidx.media.p */
class C0509p extends MediaBrowserService {
    final /* synthetic */ C0510q this$1;

    C0509p(C0510q qVar, Context context) {
        this.this$1 = qVar;
        attachBaseContext(context);
    }

    @SuppressLint({"SyntheticAccessor"})
    public MediaBrowserService.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        C0107q.m130b(bundle);
        C0504k onGetRoot = this.this$1.onGetRoot(str, i, bundle == null ? null : new Bundle(bundle));
        if (onGetRoot == null) {
            return null;
        }
        return new MediaBrowserService.BrowserRoot(onGetRoot.f489wq, onGetRoot.mExtras);
    }

    public void onLoadChildren(String str, MediaBrowserService.Result result) {
        this.this$1.mo4597a(str, new C0520z(result));
    }
}
