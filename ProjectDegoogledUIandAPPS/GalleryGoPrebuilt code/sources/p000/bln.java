package p000;

import android.os.Bundle;
import com.google.android.apps.photosgo.assassin.AssassinActivity;

/* renamed from: bln */
/* compiled from: PG */
public class bln extends fww implements ioe, fti {

    /* renamed from: e */
    private volatile ftl f3111e;

    /* renamed from: f */
    private final Object f3112f = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public ftl mo2566m() {
        throw null;
    }

    /* renamed from: l */
    private final ftl mo3320l() {
        if (this.f3111e == null) {
            synchronized (this.f3112f) {
                if (this.f3111e == null) {
                    this.f3111e = mo2566m();
                }
            }
        }
        return this.f3111e;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        AssassinActivity assassinActivity = (AssassinActivity) this;
        ((blm) mo2453b()).mo2370p();
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
