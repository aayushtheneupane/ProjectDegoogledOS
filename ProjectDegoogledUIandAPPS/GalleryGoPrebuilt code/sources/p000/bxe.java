package p000;

import android.os.Bundle;
import com.google.android.apps.photosgo.editor.ExternalEditorActivity;

/* renamed from: bxe */
/* compiled from: PG */
public class bxe extends fww implements ioe, fti {

    /* renamed from: e */
    private volatile ftl f3817e;

    /* renamed from: f */
    private final Object f3818f = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public ftl mo2566m() {
        throw null;
    }

    /* renamed from: l */
    private final ftl mo3320l() {
        if (this.f3817e == null) {
            synchronized (this.f3818f) {
                if (this.f3817e == null) {
                    this.f3817e = mo2566m();
                }
            }
        }
        return this.f3817e;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ExternalEditorActivity externalEditorActivity = (ExternalEditorActivity) this;
        ((bwn) mo2453b()).mo2372r();
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
