package p000;

import android.os.Bundle;
import com.google.android.apps.photosgo.oneup.ExternalOneUpActivity;

/* renamed from: dok */
/* compiled from: PG */
public class dok extends fww implements ioe, fti {

    /* renamed from: e */
    private volatile ftl f6941e;

    /* renamed from: f */
    private final Object f6942f = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public ftl mo2566m() {
        throw null;
    }

    /* renamed from: l */
    private final ftl mo3320l() {
        if (this.f6941e == null) {
            synchronized (this.f6942f) {
                if (this.f6941e == null) {
                    this.f6941e = mo2566m();
                }
            }
        }
        return this.f6941e;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ExternalOneUpActivity externalOneUpActivity = (ExternalOneUpActivity) this;
        ((dlg) mo2453b()).mo2373s();
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
