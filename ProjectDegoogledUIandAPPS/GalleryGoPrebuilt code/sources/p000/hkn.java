package p000;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.util.List;

/* renamed from: hkn */
/* compiled from: PG */
public final class hkn {

    /* renamed from: a */
    public static final Runnable f12921a = hkm.f12920a;

    /* renamed from: b */
    public hlp f12922b;

    /* renamed from: c */
    public hlp f12923c;

    /* renamed from: d */
    public boolean f12924d = false;

    /* renamed from: e */
    public hlq f12925e;

    /* renamed from: f */
    private final Runnable f12926f = new hkg(this);

    /* renamed from: g */
    private final Activity f12927g;

    /* renamed from: h */
    private final hlq f12928h = new hkh(this);

    /* renamed from: i */
    private final hlq f12929i = new hki(this);

    /* renamed from: j */
    private boolean f12930j = false;

    /* renamed from: k */
    private boolean f12931k = false;

    /* renamed from: l */
    private hlq f12932l;

    public hkn(Activity activity) {
        this.f12927g = activity;
    }

    /* renamed from: c */
    private final String m11629c(String str) {
        String simpleName = this.f12927g.getClass().getSimpleName();
        StringBuilder sb = new StringBuilder(str.length() + 1 + String.valueOf(simpleName).length());
        sb.append(str);
        sb.append(" ");
        sb.append(simpleName);
        return sb.toString();
    }

    /* renamed from: a */
    private final void m11627a(String str, String str2, Intent intent) {
        this.f12923c = hnb.m11769a();
        hlp a = hmq.m11741a(intent, hnf.f13084a);
        if (a != null) {
            hnb.m11776b(a);
            this.f12922b = a;
        } else {
            hnb.m11777b(hnf.f13084a);
            this.f12931k = true;
            if (!hnb.m11774a(hnf.f13084a)) {
                this.f12932l = hok.m11840b((Context) this.f12927g).mo7578a(m11629c(str), hlo.f12991a, hnf.f13084a);
            }
            this.f12922b = hnb.m11769a();
        }
        this.f12925e = hnb.m11767a(m11629c(str2), hnf.f13084a, hlo.f12991a);
        fxk.m9824a(this.f12926f);
    }

    /* renamed from: l */
    public final hlq mo7529l() {
        m11631s();
        return m11628b("onActivityResult");
    }

    /* renamed from: h */
    public final hlq mo7525h() {
        m11631s();
        return m11628b("Back pressed");
    }

    /* renamed from: m */
    public final hlq mo7530m() {
        m11627a("Intenting into", "onCreate", this.f12927g.getIntent());
        return this.f12928h;
    }

    /* renamed from: g */
    public final hlq mo7524g() {
        List list = hnb.f13076a;
        mo7517a("onDestroy");
        return new hkl(this);
    }

    /* renamed from: a */
    public final void mo7518a(C0546u uVar) {
        C0546u uVar2 = C0546u.ON_CREATE;
        int ordinal = uVar.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1 || ordinal == 2 || ordinal == 3 || ordinal == 4 || ordinal == 5) {
                mo7527j();
                return;
            }
            String valueOf = String.valueOf(uVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 19);
            sb.append("Unknown lifecycle: ");
            sb.append(valueOf);
            throw new UnsupportedOperationException(sb.toString());
        } else if (this.f12924d) {
            mo7527j();
            this.f12924d = false;
        }
    }

    /* renamed from: a */
    public final hlq mo7516a(Intent intent) {
        m11627a("Reintenting into", "onNewIntent", intent);
        return this.f12928h;
    }

    /* renamed from: n */
    public final hlq mo7531n() {
        m11631s();
        return m11628b("onOptionsItemSelected");
    }

    /* renamed from: d */
    public final hlq mo7521d() {
        mo7517a("onPause");
        return this.f12929i;
    }

    /* renamed from: o */
    public final hlq mo7532o() {
        m11630r();
        mo7517a("onPostCreate");
        return this.f12928h;
    }

    /* renamed from: c */
    public final hlq mo7520c() {
        this.f12923c = hnb.m11769a();
        hnb.m11776b(this.f12922b);
        return new hkj(this);
    }

    /* renamed from: p */
    public final hlq mo7533p() {
        return m11628b("onRequestPermissionsResult");
    }

    /* renamed from: b */
    public final hlq mo7519b() {
        m11630r();
        mo7517a("onResume");
        return this.f12928h;
    }

    /* renamed from: f */
    public final hlq mo7523f() {
        mo7517a("retainCustomNonConfigurationInstance");
        hnb.m11769a();
        return new hkk(this);
    }

    /* renamed from: q */
    public final hlq mo7534q() {
        mo7517a("onSaveInstanceState");
        return this.f12929i;
    }

    /* renamed from: a */
    public final hlq mo7515a() {
        m11630r();
        mo7517a("onStart");
        return this.f12928h;
    }

    /* renamed from: e */
    public final hlq mo7522e() {
        mo7517a("onStop");
        return this.f12929i;
    }

    /* renamed from: i */
    public final hlq mo7526i() {
        m11631s();
        return m11628b("onSupportNavigateUp");
    }

    /* renamed from: r */
    private final void m11630r() {
        if (this.f12930j) {
            this.f12922b = null;
            this.f12930j = false;
        }
    }

    /* renamed from: k */
    public final void mo7528k() {
        this.f12930j = true;
        if (!this.f12927g.isChangingConfigurations() && !this.f12927g.isFinishing()) {
            this.f12922b = null;
        }
    }

    /* renamed from: a */
    public final void mo7517a(String str) {
        this.f12923c = hnb.m11769a();
        hlp hlp = this.f12922b;
        if (hlp != null) {
            hnb.m11776b(hlp);
        } else {
            this.f12931k = true;
            hnb.m11777b(hnf.f13084a);
            if (!hnb.m11774a(hnf.f13084a)) {
                hlz b = hok.m11840b((Context) this.f12927g);
                Class<?> cls = this.f12927g.getClass();
                hln hln = hlo.f12991a;
                String simpleName = cls.getSimpleName();
                StringBuilder sb = new StringBuilder(String.valueOf(simpleName).length() + 2 + str.length());
                sb.append(simpleName);
                sb.append(": ");
                sb.append(str);
                this.f12932l = b.f13017b.mo7580a(sb.toString(), hln.m11701a(b.f13018c, hln), b.f13016a.mo5370a(), b.f13016a.mo5372c(), b.f13019d);
            }
            this.f12922b = hnb.m11769a();
        }
        this.f12925e = hnb.m11767a(m11629c(str), hnf.f13084a, hlo.f12991a);
    }

    /* renamed from: b */
    private final hlq m11628b(String str) {
        if (!hnb.m11774a(hnf.f13084a)) {
            return hok.m11840b((Context) this.f12927g).mo7577a(str);
        }
        return hnb.m11765a(str);
    }

    /* renamed from: j */
    public final void mo7527j() {
        ife.m12898e((Object) this.f12925e);
        this.f12925e.close();
        this.f12925e = null;
        if (this.f12931k) {
            this.f12931k = false;
            hnb.m11781c(hnf.f13084a);
        }
        hlq hlq = this.f12932l;
        if (hlq != null) {
            hlq.close();
            this.f12932l = null;
        }
        hnb.m11776b(this.f12923c);
        this.f12923c = null;
    }

    /* renamed from: s */
    private final void m11631s() {
        hlq hlq = this.f12925e;
        if (hlq != null) {
            String valueOf = String.valueOf(hlq);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 46);
            sb.append("Expected lifecycleStepSpan to be null but was:");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }
}
