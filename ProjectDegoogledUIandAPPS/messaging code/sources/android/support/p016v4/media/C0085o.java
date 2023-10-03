package android.support.p016v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.p016v4.media.session.MediaSessionCompat$Token;
import android.util.Log;

/* renamed from: android.support.v4.media.o */
public final class C0085o {
    static final boolean DEBUG = Log.isLoggable("MediaBrowserCompat", 3);
    private final C0074d mImpl;

    public C0085o(Context context, ComponentName componentName, C0073c cVar, Bundle bundle) {
        int i = Build.VERSION.SDK_INT;
        this.mImpl = new C0077g(context, componentName, cVar, bundle);
    }

    public void connect() {
        Log.d("MediaBrowserCompat", "Connecting to a MediaBrowserService.");
        this.mImpl.connect();
    }

    public void disconnect() {
        this.mImpl.disconnect();
    }

    public MediaSessionCompat$Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }
}
