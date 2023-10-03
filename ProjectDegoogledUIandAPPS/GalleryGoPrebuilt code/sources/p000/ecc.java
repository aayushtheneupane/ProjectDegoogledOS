package p000;

import android.os.Bundle;
import com.google.android.apps.photosgo.settings.SettingsActivity;

/* renamed from: ecc */
/* compiled from: PG */
public class ecc extends fww implements ioe, fti {

    /* renamed from: e */
    private volatile ftl f7911e;

    /* renamed from: f */
    private final Object f7912f = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public ftl mo2566m() {
        throw null;
    }

    /* renamed from: l */
    private final ftl mo3320l() {
        if (this.f7911e == null) {
            synchronized (this.f7912f) {
                if (this.f7911e == null) {
                    this.f7911e = mo2566m();
                }
            }
        }
        return this.f7911e;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        SettingsActivity settingsActivity = (SettingsActivity) this;
        ((ebm) mo2453b()).mo2377w();
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
