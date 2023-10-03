package p000;

import android.app.Activity;
import android.view.View;

/* renamed from: fea */
/* compiled from: PG */
public final class fea extends fdj {

    /* renamed from: a */
    private final /* synthetic */ feb f9356a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public fea(feb feb, int i, fdo fdo) {
        super(i, fdo, ffa.f9433a);
        this.f9356a = feb;
    }

    /* renamed from: a */
    public final fdr mo5560a(View view) {
        fxk.m9830b();
        fdr c = mo5514c();
        fdr a = fdz.m8660a(view, ffa.f9433a);
        if (a == null) {
            fdz fdz = new fdz(view, c);
            c.f9326c = fdz;
            fdz.mo5528d();
            return c;
        } else if (a.mo5538a()) {
            if (a.mo5542c(ffa.f9433a)) {
                this.f9356a.f9357a.mo5549a(new IllegalStateException("CVE is already impressed and cannot be replaced."));
            } else {
                this.f9356a.f9357a.mo5549a(new IllegalStateException("CVE is already attached and cannot be replaced."));
            }
            return a;
        } else {
            a.mo5537a(c);
            return a;
        }
    }

    /* renamed from: b */
    public final void mo5562b(View view) {
        if (fdz.m8660a(view, ffa.f9433a) == null) {
            mo5560a(view);
        }
    }

    /* renamed from: a */
    public final void mo5561a(Activity activity) {
        mo5560a(fdz.m8658a(activity));
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final /* bridge */ /* synthetic */ fdj mo5511b() {
        fxk.m9830b();
        return this;
    }
}
