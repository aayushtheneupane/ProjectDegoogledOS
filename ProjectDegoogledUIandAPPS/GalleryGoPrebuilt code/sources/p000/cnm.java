package p000;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import p003j$.util.Optional;
import p003j$.util.function.Supplier;

/* renamed from: cnm */
/* compiled from: PG */
public final class cnm implements cnp, C0438q {

    /* renamed from: a */
    private final C0395ok f4732a;

    /* renamed from: b */
    private final ArrayDeque f4733b = new ArrayDeque();

    /* renamed from: c */
    private boolean f4734c = false;

    /* renamed from: d */
    private boolean f4735d = false;

    /* renamed from: e */
    private Optional f4736e = Optional.empty();

    /* renamed from: f */
    private Optional f4737f = Optional.empty();

    public cnm(Activity activity) {
        C0395ok okVar = (C0395ok) activity;
        this.f4732a = okVar;
        okVar.mo5ad().mo64a(this);
    }

    /* renamed from: a */
    public final void mo2556a() {
    }

    /* renamed from: c */
    public final void mo2561c(C0681z zVar) {
    }

    /* renamed from: d */
    public final boolean mo3273d() {
        return this.f4733b.size() > 1;
    }

    /* renamed from: b */
    private static void m4602b(C0147fh fhVar) {
        InputMethodManager inputMethodManager = (InputMethodManager) fhVar.mo2634k().getSystemService("input_method");
        if (inputMethodManager.isAcceptingText() && fhVar.mo5653m().getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(((View) ife.m12898e((Object) fhVar.mo5653m().getCurrentFocus())).getWindowToken(), 0);
        }
        m4604c(fhVar).ifPresent(cnl.f4731a);
    }

    /* renamed from: c */
    private static Optional m4604c(C0147fh fhVar) {
        Object a = ggq.m10280a((Object) fhVar);
        if (a instanceof cnq) {
            return Optional.m16285of((cnq) a);
        }
        return Optional.empty();
    }

    /* renamed from: g */
    public final C0147fh mo3283g() {
        C0171gd d = this.f4732a.mo5851d();
        cno cno = (cno) this.f4733b.peek();
        String str = cno != null ? cno.f4738a : "";
        C0147fh a = d.mo6418a(str);
        if (a != null) {
            return a;
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("No top fragment with tag ") : "No top fragment with tag ".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo3280a(Bundle bundle) {
        if (!this.f4734c) {
            try {
                this.f4736e = Optional.m16285of(new egt((egv) iix.m13603a((iix) egv.f8228d, bundle.getByteArray("systemUiState"))));
                this.f4733b.clear();
                this.f4733b.addAll(bundle.getParcelableArrayList("fragmentStack"));
                if (!this.f4733b.isEmpty()) {
                    this.f4734c = true;
                    return;
                }
                throw new IllegalStateException("Bundle must contain non-empty stack");
            } catch (IOException e) {
                throw new IllegalStateException("Invalid system UI value", e);
            }
        } else {
            throw new IllegalStateException("Navigator must be initialized only once");
        }
    }

    /* renamed from: a */
    public final void mo3281a(Supplier supplier) {
        if (!this.f4734c) {
            this.f4737f = Optional.m16285of(supplier);
            Window window = this.f4732a.getWindow();
            iir g = egv.f8228d.mo8793g();
            int i = window.getAttributes().flags;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            egv egv = (egv) g.f14318b;
            egv.f8230a |= 2;
            egv.f8232c = i;
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            egv egv2 = (egv) g.f14318b;
            egv2.f8230a |= 1;
            egv2.f8231b = systemUiVisibility;
            this.f4736e = Optional.m16285of(new egt((egv) g.mo8770g()));
            this.f4734c = true;
            return;
        }
        throw new IllegalStateException("Navigator must be initialized only once");
    }

    /* renamed from: e */
    public final boolean mo3274e() {
        return m4601a(Optional.empty());
    }

    /* renamed from: a */
    private final boolean m4601a(Optional optional) {
        if (!mo3273d()) {
            return false;
        }
        C0171gd d = this.f4732a.mo5851d();
        cno cno = (cno) this.f4733b.pop();
        C0147fh a = d.mo6418a(cno.f4738a);
        if (a != null) {
            cno cno2 = (cno) this.f4733b.peek();
            if (cno2 != null) {
                C0147fh a2 = d.mo6418a(cno2.f4738a);
                if (a2 != null) {
                    m4602b(a);
                    C0182gn a3 = d.mo6419a();
                    cnf cnf = cno.f4739b;
                    a3.mo6847a(cnf.f4726f, cnf.f4727g).mo5243a(a).mo5250b(a2).mo5244a();
                    m4603b(optional);
                    return true;
                }
                String str = cno2.f4738a;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 30);
                sb.append("No fragment with tag ");
                sb.append(str);
                sb.append(" to show.");
                throw new IllegalStateException(sb.toString());
            }
            throw new IllegalStateException("tried to peek at empty fragment stack");
        }
        String str2 = cno.f4738a;
        StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 32);
        sb2.append("No fragment with tag ");
        sb2.append(str2);
        sb2.append(" to remove.");
        throw new IllegalStateException(sb2.toString());
    }

    /* renamed from: f */
    public final void mo3275f() {
        if (!mo3274e()) {
            this.f4732a.finish();
        }
    }

    /* renamed from: a */
    public final void mo3270a(hoi hoi) {
        m4601a(Optional.m16285of(hoi));
    }

    /* renamed from: b */
    public final void mo2558b() {
        hlj a = hnb.m11765a("FragmentNavigatorImpl onPause");
        try {
            m4602b(mo3283g());
            this.f4735d = false;
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: c */
    public final void mo2560c() {
        hlj a = hnb.m11765a("FragmentNavigatorImpl onResume");
        try {
            m4603b(Optional.empty());
            this.f4735d = true;
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo2557a(C0681z zVar) {
        hlj a = hnb.m11765a("FragmentNavigatorImpl onStart");
        try {
            if (this.f4734c) {
                if (this.f4733b.isEmpty()) {
                    C0182gn a2 = this.f4732a.mo5851d().mo6419a();
                    a2.mo6850a(16908290, (C0147fh) ((Supplier) this.f4737f.get()).get(), "home");
                    a2.mo5244a();
                    this.f4733b.push(new cno("home", cnf.NONE));
                }
                if (a != null) {
                    a.close();
                    return;
                }
                return;
            }
            throw new IllegalStateException("Navigator must be initialized before onStart");
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: b */
    public final void mo2559b(C0681z zVar) {
        if (this.f4736e.isPresent()) {
            ((egt) this.f4736e.get()).mo4801a(this.f4732a.getWindow());
            return;
        }
        throw new IllegalStateException("SystemUiSaver hasn't been initialized.");
    }

    /* renamed from: a */
    public final void mo3271a(Supplier supplier, String str) {
        mo3272a(supplier, str, cnf.DEFAULT);
    }

    /* renamed from: a */
    public final void mo3272a(Supplier supplier, String str, cnf cnf) {
        C0171gd d = this.f4732a.mo5851d();
        if (d.mo6418a(str) == null) {
            C0147fh fhVar = (C0147fh) supplier.get();
            C0182gn a = d.mo6419a();
            a.mo6847a(cnf.f4724d, cnf.f4725e);
            a.mo6853b(16908290, fhVar, str);
            if (!this.f4733b.isEmpty()) {
                cno cno = (cno) this.f4733b.peek();
                C0147fh a2 = d.mo6418a(cno != null ? cno.f4738a : "");
                if (a2 != null) {
                    m4602b(a2);
                    C0171gd gdVar = a2.f9604w;
                    if (gdVar == null || gdVar == ((C0133eu) a).f9018a) {
                        a.mo6852a(new C0180gm(4, a2));
                    } else {
                        throw new IllegalStateException("Cannot hide Fragment attached to a different FragmentManager. Fragment " + a2.toString() + " is already attached to a FragmentManager.");
                    }
                } else {
                    throw new IllegalStateException("No fragment matching the tag in the stack");
                }
            }
            this.f4733b.push(new cno(str, cnf));
            a.mo5244a();
            m4600a(fhVar, Optional.empty());
        }
    }

    /* renamed from: h */
    public final void mo3284h() {
        if (this.f4735d) {
            m4599a(mo3283g());
        }
    }

    /* renamed from: b */
    public final void mo3282b(Bundle bundle) {
        if (this.f4736e.isPresent()) {
            bundle.putByteArray("systemUiState", ((egt) this.f4736e.get()).f8226a.mo8512ag());
            bundle.putParcelableArrayList("fragmentStack", new ArrayList(this.f4733b));
            return;
        }
        throw new IllegalStateException("SystemUiSaver hasn't been initialized.");
    }

    /* renamed from: a */
    private static void m4599a(C0147fh fhVar) {
        m4604c(fhVar).ifPresent(cnj.f4729a);
    }

    /* renamed from: a */
    private final void m4600a(C0147fh fhVar, Optional optional) {
        ife.m12876b(!fhVar.f9566E, (Object) "can only setup attached fragments");
        if (this.f4736e.isPresent()) {
            ((egt) this.f4736e.get()).mo4801a(this.f4732a.getWindow());
            m4599a(fhVar);
            m4604c(fhVar).ifPresent(cnk.f4730a);
            Object a = ggq.m10280a((Object) fhVar);
            if (a instanceof cnd) {
                this.f4732a.setRequestedOrientation(((cnd) a).mo3046a());
            } else {
                this.f4732a.setRequestedOrientation(-1);
            }
            optional.ifPresent(new cni(fhVar));
            return;
        }
        throw new IllegalStateException("SystemUiSaver hasn't been initialized.");
    }

    /* renamed from: b */
    private final void m4603b(Optional optional) {
        m4600a(mo3283g(), optional);
    }
}
