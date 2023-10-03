package android.support.p016v4.media;

import android.media.browse.MediaBrowser;
import android.os.Build;

/* renamed from: android.support.v4.media.c */
public class C0073c {

    /* renamed from: ae */
    final MediaBrowser.ConnectionCallback f88ae = new C0072b(this);

    /* renamed from: be */
    C0075e f89be;

    public C0073c() {
        int i = Build.VERSION.SDK_INT;
    }

    public void onConnected() {
        throw null;
    }

    public void onConnectionFailed() {
        throw null;
    }

    public void onConnectionSuspended() {
        throw null;
    }
}
