package p000;

import android.os.Bundle;
import com.google.android.apps.photosgo.home.HomeActivity;

/* renamed from: crm */
/* compiled from: PG */
public class crm extends fww implements ioe, fti {

    /* renamed from: e */
    private volatile ftl f5493e;

    /* renamed from: f */
    private final Object f5494f = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public ftl mo2566m() {
        throw null;
    }

    /* renamed from: l */
    private final ftl mo3320l() {
        if (this.f5493e == null) {
            synchronized (this.f5494f) {
                if (this.f5493e == null) {
                    this.f5493e = mo2566m();
                }
            }
        }
        return this.f5493e;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        HomeActivity homeActivity = (HomeActivity) this;
        ((cqs) mo2453b()).mo2375u();
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
