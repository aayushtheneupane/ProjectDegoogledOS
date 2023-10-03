package p000;

import android.content.Context;
import android.support.p002v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

/* renamed from: pv */
/* compiled from: PG */
public final class C0433pv extends C0443qe implements C0470re {

    /* renamed from: a */
    public final C0472rg f15540a;

    /* renamed from: b */
    public C0442qd f15541b;

    /* renamed from: e */
    private final Context f15542e;

    /* renamed from: f */
    private WeakReference f15543f;

    /* renamed from: g */
    private final /* synthetic */ C0434pw f15544g;

    public C0433pv(C0434pw pwVar, Context context, C0442qd qdVar) {
        this.f15544g = pwVar;
        this.f15542e = context;
        this.f15541b = qdVar;
        C0472rg rgVar = new C0472rg(context);
        rgVar.mo9871m();
        this.f15540a = rgVar;
        rgVar.f15750b = this;
    }

    /* renamed from: b */
    public final Menu mo9647b() {
        return this.f15540a;
    }

    /* renamed from: c */
    public final void mo9650c() {
        C0434pw pwVar = this.f15544g;
        if (pwVar.f15554g == this) {
            boolean z = pwVar.f15559l;
            if (!C0434pw.m15085a(pwVar.f15560m, false)) {
                C0434pw pwVar2 = this.f15544g;
                pwVar2.f15555h = this;
                pwVar2.f15556i = this.f15541b;
            } else {
                this.f15541b.mo9576a(this);
            }
            this.f15541b = null;
            this.f15544g.mo9657g(false);
            ActionBarContextView actionBarContextView = this.f15544g.f15552e;
            if (actionBarContextView.f897h == null) {
                actionBarContextView.mo794a();
            }
            this.f15544g.f15551d.mo10322a().sendAccessibilityEvent(32);
            C0434pw pwVar3 = this.f15544g;
            pwVar3.f15549b.mo814a(pwVar3.f15562o);
            this.f15544g.f15554g = null;
        }
    }

    /* renamed from: h */
    public final View mo9655h() {
        WeakReference weakReference = this.f15543f;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    /* renamed from: a */
    public final MenuInflater mo9642a() {
        return new C0451qm(this.f15542e);
    }

    /* renamed from: f */
    public final CharSequence mo9653f() {
        return this.f15544g.f15552e.f896g;
    }

    /* renamed from: e */
    public final CharSequence mo9652e() {
        return this.f15544g.f15552e.f895f;
    }

    /* renamed from: d */
    public final void mo9651d() {
        if (this.f15544g.f15554g == this) {
            this.f15540a.mo9859e();
            try {
                this.f15541b.mo9579b(this, this.f15540a);
            } finally {
                this.f15540a.mo9860f();
            }
        }
    }

    /* renamed from: g */
    public final boolean mo9654g() {
        return this.f15544g.f15552e.f898i;
    }

    /* renamed from: a */
    public final boolean mo9607a(C0472rg rgVar, MenuItem menuItem) {
        C0442qd qdVar = this.f15541b;
        if (qdVar != null) {
            return qdVar.mo9578a((C0443qe) this, menuItem);
        }
        return false;
    }

    /* renamed from: a */
    public final void mo9603a(C0472rg rgVar) {
        if (this.f15541b != null) {
            mo9651d();
            this.f15544g.f15552e.mo800b();
        }
    }

    /* renamed from: a */
    public final void mo9644a(View view) {
        this.f15544g.f15552e.mo796a(view);
        this.f15543f = new WeakReference(view);
    }

    /* renamed from: b */
    public final void mo9648b(int i) {
        mo9645a((CharSequence) this.f15544g.f15548a.getResources().getString(i));
    }

    /* renamed from: a */
    public final void mo9645a(CharSequence charSequence) {
        this.f15544g.f15552e.mo801b(charSequence);
    }

    /* renamed from: a */
    public final void mo9643a(int i) {
        mo9649b((CharSequence) this.f15544g.f15548a.getResources().getString(i));
    }

    /* renamed from: b */
    public final void mo9649b(CharSequence charSequence) {
        this.f15544g.f15552e.mo797a(charSequence);
    }

    /* renamed from: a */
    public final void mo9646a(boolean z) {
        this.f15604d = z;
        this.f15544g.f15552e.mo799a(z);
    }
}
