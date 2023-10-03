package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;

/* renamed from: ftr */
/* compiled from: PG */
public class ftr implements ioe {

    /* renamed from: a */
    private volatile Object f10579a;

    /* renamed from: b */
    private final Object f10580b = new Object();

    /* renamed from: c */
    private final C0147fh f10581c;

    /* renamed from: d */
    private final boolean f10582d;

    public ftr(C0147fh fhVar, boolean z) {
        this.f10581c = fhVar;
        this.f10582d = z;
    }

    /* renamed from: a */
    public static final Context m9609a(Context context) {
        while ((context instanceof ContextWrapper) && !(context instanceof Activity)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return context;
    }

    /* renamed from: b */
    public static final void m9611b(C0147fh fhVar) {
        ife.m12898e((Object) fhVar);
        if (fhVar.f9592k == null) {
            fhVar.mo5646f(new Bundle());
        }
    }

    /* renamed from: a */
    public static final void m9610a(C0147fh fhVar) {
        m9611b(fhVar);
        fhVar.f9592k.putInt("TIKTOK_FRAGMENT_ACCOUNT_ID", -1);
    }

    /* renamed from: b */
    public final Object mo2453b() {
        Object obj;
        if (this.f10579a == null) {
            synchronized (this.f10580b) {
                if (this.f10579a == null) {
                    ife.m12869b(this.f10581c.mo5654o(), (Object) "Sting Fragments must be attached before creating the component.");
                    ife.m12878b(this.f10581c.mo5654o() instanceof ioe, "Sting Fragments must be attached to an @Sting Activity. Found: %s", (Object) this.f10581c.mo5654o().getClass());
                    mo6181c(this.f10581c);
                    if (!this.f10582d) {
                        obj = ((ftq) ((ioe) this.f10581c.mo5654o()).mo2453b()).mo2367m().mo2131a(this.f10581c).mo2130a();
                    } else {
                        fti fti = (fti) this.f10581c.mo5654o();
                        Bundle bundle = this.f10581c.f9592k;
                        gkn gkn = null;
                        if (bundle != null && bundle.containsKey("TIKTOK_FRAGMENT_ACCOUNT_ID")) {
                            gkn = gkn.m10445a(bundle.getInt("TIKTOK_FRAGMENT_ACCOUNT_ID"), gtf.f12011a);
                        }
                        obj = ((ftp) fti.mo2452a(mo6180a(gkn))).mo2354b().mo2139a(this.f10581c).mo2138a();
                    }
                    this.f10579a = obj;
                }
            }
        }
        return this.f10579a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo6181c(C0147fh fhVar) {
        Bundle bundle = fhVar.f9592k;
        if (bundle != null) {
            boolean z = true;
            if (this.f10582d && bundle.getBoolean("TIKTOK_FRAGMENT_NO_ACCOUNT_ONLY")) {
                z = false;
            }
            ife.m12845a(z, (Object) "Account-scoped Fragment cannot be instantiated with an argument bundle marking it as no-Account only. If you are using NoAccountNavigation, you must switch to AccountNavigation to navigate to this fragment.");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public gkn mo6180a(gkn gkn) {
        ife.m12878b(gkn != null, "No AccountId found in Bundle. Did you forget to add an account id?\n\tFragment: %s", (Object) this.f10581c.getClass());
        return gkn;
    }
}
