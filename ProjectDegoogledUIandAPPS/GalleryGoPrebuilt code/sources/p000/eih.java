package p000;

import android.os.Bundle;
import com.google.android.apps.photosgo.wallpaper.SetWallpaperActivity;

/* renamed from: eih */
/* compiled from: PG */
public class eih extends fww implements ioe, fti {

    /* renamed from: e */
    private volatile ftl f8339e;

    /* renamed from: f */
    private final Object f8340f = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public ftl mo2566m() {
        throw null;
    }

    /* renamed from: l */
    private final ftl mo3320l() {
        if (this.f8339e == null) {
            synchronized (this.f8340f) {
                if (this.f8339e == null) {
                    this.f8339e = mo2566m();
                }
            }
        }
        return this.f8339e;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        SetWallpaperActivity setWallpaperActivity = (SetWallpaperActivity) this;
        ((eie) mo2453b()).mo2376v();
        super.onCreate(bundle);
    }

    /* renamed from: b */
    public final Object mo2453b() {
        return mo3320l().mo2453b();
    }

    /* renamed from: a */
    public final Object mo2452a(gkn gkn) {
        return mo3320l().mo2452a(gkn);
    }
}
