package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.C0450g;
import androidx.lifecycle.Lifecycle$State;

/* renamed from: androidx.savedstate.d */
public final class C0608d {

    /* renamed from: Lt */
    private final C0607c f691Lt = new C0607c();
    private final C0609e mOwner;

    private C0608d(C0609e eVar) {
        this.mOwner = eVar;
    }

    /* renamed from: b */
    public static C0608d m958b(C0609e eVar) {
        return new C0608d(eVar);
    }

    /* renamed from: f */
    public void mo5285f(Bundle bundle) {
        this.f691Lt.mo5284f(bundle);
    }

    /* renamed from: g */
    public void mo5286g(Bundle bundle) {
        C0450g lifecycle = this.mOwner.getLifecycle();
        if (lifecycle.getCurrentState() == Lifecycle$State.INITIALIZED) {
            lifecycle.mo4460a(new Recreator(this.mOwner));
            this.f691Lt.mo5283a(lifecycle, bundle);
            return;
        }
        throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
    }

    public C0607c getSavedStateRegistry() {
        return this.f691Lt;
    }
}
