package com.android.messaging.datamodel.data;

import android.content.Intent;
import android.net.Uri;
import com.android.messaging.datamodel.p037a.C0781a;

/* renamed from: com.android.messaging.datamodel.data.V */
public abstract class C0909V extends C0781a {
    private C0908U mListener;

    /* renamed from: Mb */
    public abstract Intent mo6127Mb();

    /* access modifiers changed from: protected */
    /* renamed from: Te */
    public void mo5924Te() {
        this.mListener = null;
    }

    /* renamed from: a */
    public void mo6379a(C0908U u) {
        if (isBound()) {
            this.mListener = u;
        }
    }

    public abstract long getContactId();

    public abstract String getDetails();

    public abstract String getDisplayName();

    /* renamed from: m */
    public abstract String mo6131m();

    /* renamed from: rf */
    public abstract Uri mo6132rf();

    /* renamed from: sf */
    public abstract String mo6133sf();

    /* access modifiers changed from: protected */
    /* renamed from: tf */
    public void mo6381tf() {
        C0908U u;
        if (isBound() && (u = this.mListener) != null) {
            u.mo6378b(this);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo6380a(Exception exc) {
        C0908U u;
        if (isBound() && (u = this.mListener) != null) {
            u.mo6377a(this, exc);
        }
    }
}
