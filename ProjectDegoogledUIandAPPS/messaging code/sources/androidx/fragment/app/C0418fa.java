package androidx.fragment.app;

import androidx.lifecycle.C0450g;
import androidx.lifecycle.C0453j;
import androidx.lifecycle.C0455l;
import androidx.lifecycle.Lifecycle$Event;

/* renamed from: androidx.fragment.app.fa */
class C0418fa implements C0453j {
    private C0455l mLifecycleRegistry = null;

    C0418fa() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4238a(Lifecycle$Event lifecycle$Event) {
        this.mLifecycleRegistry.mo4464a(lifecycle$Event);
    }

    public C0450g getLifecycle() {
        initialize();
        return this.mLifecycleRegistry;
    }

    /* access modifiers changed from: package-private */
    public void initialize() {
        if (this.mLifecycleRegistry == null) {
            this.mLifecycleRegistry = new C0455l(this);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isInitialized() {
        return this.mLifecycleRegistry != null;
    }
}
