package p000;

import android.os.Bundle;
import com.google.android.apps.photosgo.oneup.secure.unlock.UnlockActivity;

/* renamed from: dsl */
/* compiled from: PG */
public class dsl extends fww implements ioe, fti {

    /* renamed from: e */
    private volatile ftl f7281e;

    /* renamed from: f */
    private final Object f7282f = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public ftl mo2566m() {
        throw null;
    }

    /* renamed from: l */
    private final ftl mo3320l() {
        if (this.f7281e == null) {
            synchronized (this.f7282f) {
                if (this.f7281e == null) {
                    this.f7281e = mo2566m();
                }
            }
        }
        return this.f7281e;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        UnlockActivity unlockActivity = (UnlockActivity) this;
        ((dsr) mo2453b()).mo2378x();
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
