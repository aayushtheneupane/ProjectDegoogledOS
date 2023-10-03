package p000;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;

/* renamed from: fi */
/* compiled from: PG */
public final class C0148fi extends C0160fu implements C0026ax, aah {

    /* renamed from: a */
    public final /* synthetic */ C0149fj f9696a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0148fi(C0149fj fjVar) {
        super(fjVar);
        this.f9696a = fjVar;
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f9696a.f9773a;
    }

    /* renamed from: aa */
    public final C0025aw mo4aa() {
        return this.f9696a.mo4aa();
    }

    /* renamed from: a */
    public final View mo5559a(int i) {
        return this.f9696a.findViewById(i);
    }

    /* renamed from: Z */
    public final boolean mo5558Z() {
        Window window = this.f9696a.getWindow();
        return (window == null || window.peekDecorView() == null) ? false : true;
    }

    /* renamed from: a */
    public final void mo5743a(C0147fh fhVar, Intent intent, int i) {
        C0149fj fjVar = this.f9696a;
        fjVar.f9775c = true;
        if (i == -1) {
            try {
                C0071co.m4664a((Activity) fjVar, intent, -1);
            } catch (Throwable th) {
                fjVar.f9775c = false;
                throw th;
            }
        } else {
            C0149fj.m9012b(i);
            C0071co.m4664a((Activity) fjVar, intent, ((fjVar.mo5849a(fhVar) + 1) << 16) + i);
        }
        fjVar.f9775c = false;
    }

    /* renamed from: c */
    public final void mo5744c() {
        this.f9696a.mo5850c();
    }
}
