package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.C0450g;
import java.util.Map;
import p000a.p001a.p002a.p004b.C0013h;

@SuppressLint({"RestrictedApi"})
/* renamed from: androidx.savedstate.c */
public final class C0607c {

    /* renamed from: Jt */
    private C0013h f689Jt = new C0013h();

    /* renamed from: Kt */
    private Bundle f690Kt;
    private boolean mRestored;

    C0607c() {
    }

    /* renamed from: B */
    public Bundle mo5282B(String str) {
        if (this.mRestored) {
            Bundle bundle = this.f690Kt;
            if (bundle == null) {
                return null;
            }
            Bundle bundle2 = bundle.getBundle(str);
            this.f690Kt.remove(str);
            if (this.f690Kt.isEmpty()) {
                this.f690Kt = null;
            }
            return bundle2;
        }
        throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo5283a(C0450g gVar, Bundle bundle) {
        if (!this.mRestored) {
            if (bundle != null) {
                this.f690Kt = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
            }
            gVar.mo4460a(new SavedStateRegistry$1(this));
            this.mRestored = true;
            return;
        }
        throw new IllegalStateException("SavedStateRegistry was already restored.");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public void mo5284f(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.f690Kt;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        C0010e pc = this.f689Jt.mo30pc();
        while (pc.hasNext()) {
            Map.Entry entry = (Map.Entry) pc.next();
            bundle2.putBundle((String) entry.getKey(), ((C0606b) entry.getValue()).saveState());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}
