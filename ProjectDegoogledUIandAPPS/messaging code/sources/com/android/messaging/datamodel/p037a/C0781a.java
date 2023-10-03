package com.android.messaging.datamodel.p037a;

/* renamed from: com.android.messaging.datamodel.a.a */
public abstract class C0781a {

    /* renamed from: gc */
    private String f1049gc;

    /* access modifiers changed from: protected */
    /* renamed from: Te */
    public abstract void mo5924Te();

    /* renamed from: V */
    public void mo5925V(String str) {
        if (isBound() || str == null) {
            throw new IllegalStateException();
        }
        this.f1049gc = str;
    }

    /* renamed from: W */
    public boolean mo5926W(String str) {
        return str.equals(this.f1049gc);
    }

    /* renamed from: X */
    public void mo5927X(String str) {
        if (str.equals(this.f1049gc)) {
            mo5924Te();
            this.f1049gc = null;
            return;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: protected */
    public boolean isBound() {
        return this.f1049gc != null;
    }
}
