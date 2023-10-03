package p000;

import android.os.Bundle;
import com.google.android.apps.photosgo.wallpaper.CropAndSetWallpaperActivity;

/* renamed from: eif */
/* compiled from: PG */
public class eif extends fww implements ioe, fti {

    /* renamed from: e */
    private volatile ftl f8334e;

    /* renamed from: f */
    private final Object f8335f = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public ftl mo2566m() {
        throw null;
    }

    /* renamed from: l */
    private final ftl mo3320l() {
        if (this.f8334e == null) {
            synchronized (this.f8335f) {
                if (this.f8334e == null) {
                    this.f8334e = mo2566m();
                }
            }
        }
        return this.f8334e;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        CropAndSetWallpaperActivity cropAndSetWallpaperActivity = (CropAndSetWallpaperActivity) this;
        ((ehs) mo2453b()).mo2371q();
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
