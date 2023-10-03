package com.android.messaging.datamodel.p037a;

/* renamed from: com.android.messaging.datamodel.a.b */
public abstract class C0782b extends C0781a {

    /* renamed from: pz */
    private boolean f1050pz = false;

    /* renamed from: V */
    public void mo5925V(String str) {
        if (!this.f1050pz) {
            super.mo5925V(str);
            this.f1050pz = true;
            return;
        }
        throw new IllegalStateException();
    }

    public boolean isBound() {
        return super.isBound();
    }
}
