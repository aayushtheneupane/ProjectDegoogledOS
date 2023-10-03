package android.support.p016v4.media.session;

import android.content.Context;
import android.os.Build;
import android.view.KeyEvent;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: android.support.v4.media.session.m */
public final class C0103m {
    private final C0099i mImpl;

    public C0103m(Context context, MediaSessionCompat$Token mediaSessionCompat$Token) {
        new ConcurrentHashMap();
        if (mediaSessionCompat$Token != null) {
            int i = Build.VERSION.SDK_INT;
            this.mImpl = new C0101k(context, mediaSessionCompat$Token);
            return;
        }
        throw new IllegalArgumentException("sessionToken must not be null");
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.mImpl.dispatchMediaButtonEvent(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }
}
