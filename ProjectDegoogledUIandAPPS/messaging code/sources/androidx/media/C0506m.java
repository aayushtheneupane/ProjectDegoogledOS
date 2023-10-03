package androidx.media;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import java.util.HashMap;

/* renamed from: androidx.media.m */
class C0506m implements IBinder.DeathRecipient {
    public final C0490K callbacks;
    public final int pid;
    public final String pkg;
    public C0504k root;
    public final HashMap subscriptions = new HashMap();
    final /* synthetic */ MediaBrowserServiceCompat this$0;
    public final int uid;

    C0506m(MediaBrowserServiceCompat mediaBrowserServiceCompat, String str, int i, int i2, Bundle bundle, C0490K k) {
        this.this$0 = mediaBrowserServiceCompat;
        this.pkg = str;
        this.pid = i;
        this.uid = i2;
        int i3 = Build.VERSION.SDK_INT;
        new C0492M(str, i, i2);
        this.callbacks = k;
    }

    public void binderDied() {
        this.this$0.mHandler.post(new C0505l(this));
    }
}
