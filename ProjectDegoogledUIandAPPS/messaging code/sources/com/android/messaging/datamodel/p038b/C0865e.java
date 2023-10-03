package com.android.messaging.datamodel.p038b;

import com.android.messaging.datamodel.p037a.C0782b;

/* renamed from: com.android.messaging.datamodel.b.e */
public abstract class C0865e extends C0782b implements C0883w, C0839B {
    private C0839B mListener;

    public C0865e(C0839B b) {
        this.mListener = b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Te */
    public void mo5924Te() {
        this.mListener = null;
    }

    public void onMediaResourceLoadError(C0883w wVar, Exception exc) {
        C0839B b;
        if (isBound() && (b = this.mListener) != null) {
            b.onMediaResourceLoadError(wVar, exc);
        }
    }

    public void onMediaResourceLoaded(C0883w wVar, C0846I i, boolean z) {
        C0839B b;
        if (isBound() && (b = this.mListener) != null) {
            b.onMediaResourceLoaded(wVar, i, z);
        }
    }
}
