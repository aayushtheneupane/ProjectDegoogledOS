package p000;

import android.os.Bundle;
import com.google.android.apps.photosgo.picker.ExternalPickerActivity;

/* renamed from: eag */
/* compiled from: PG */
public class eag extends fww implements ioe, fti {

    /* renamed from: e */
    private volatile ftl f7766e;

    /* renamed from: f */
    private final Object f7767f = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public ftl mo2566m() {
        throw null;
    }

    /* renamed from: l */
    private final ftl mo3320l() {
        if (this.f7766e == null) {
            synchronized (this.f7767f) {
                if (this.f7766e == null) {
                    this.f7766e = mo2566m();
                }
            }
        }
        return this.f7766e;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ExternalPickerActivity externalPickerActivity = (ExternalPickerActivity) this;
        ((eae) mo2453b()).mo2374t();
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
