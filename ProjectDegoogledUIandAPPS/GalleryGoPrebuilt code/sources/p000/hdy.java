package p000;

import android.content.Context;
import android.view.View;

/* renamed from: hdy */
/* compiled from: PG */
public final /* synthetic */ class hdy implements iqk {

    /* renamed from: a */
    private final C0147fh f12562a;

    public hdy(C0147fh fhVar) {
        this.f12562a = fhVar;
    }

    /* renamed from: a */
    public final Object mo2097a() {
        apn apn;
        View view;
        C0147fh fhVar = this.f12562a;
        bdc b = aow.m1349b(fhVar.mo2634k());
        cns.m4633a((Object) fhVar.mo2634k(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (bfp.m2439d()) {
            apn = b.mo1824a(fhVar.mo2634k().getApplicationContext());
        } else {
            C0171gd r = fhVar.mo5659r();
            Context k = fhVar.mo2634k();
            boolean z = false;
            if (fhVar.mo5660s() && !fhVar.f9565D && (view = fhVar.f9573L) != null && view.getWindowToken() != null && fhVar.f9573L.getVisibility() == 0) {
                z = true;
            }
            apn = b.mo1825a(k, r, fhVar, z);
        }
        return (hdp) apn;
    }
}
