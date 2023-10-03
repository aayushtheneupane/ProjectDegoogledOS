package android.support.p016v4.media;

import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: android.support.v4.media.n */
public abstract class C0084n {

    /* renamed from: le */
    WeakReference f98le;
    final IBinder mToken = new Binder();

    public C0084n() {
        int i = Build.VERSION.SDK_INT;
        new C0083m(this);
    }

    public void onChildrenLoaded(String str, List list) {
    }

    public void onChildrenLoaded(String str, List list, Bundle bundle) {
    }

    public void onError(String str) {
    }

    public void onError(String str, Bundle bundle) {
    }
}
