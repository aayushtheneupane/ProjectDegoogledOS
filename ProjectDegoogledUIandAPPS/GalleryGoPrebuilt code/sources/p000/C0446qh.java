package p000;

import android.content.Context;
import android.support.p002v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

/* renamed from: qh */
/* compiled from: PG */
public final class C0446qh extends C0443qe implements C0470re {

    /* renamed from: a */
    public final C0472rg f15609a;

    /* renamed from: b */
    private final Context f15610b;

    /* renamed from: e */
    private final ActionBarContextView f15611e;

    /* renamed from: f */
    private final C0442qd f15612f;

    /* renamed from: g */
    private WeakReference f15613g;

    /* renamed from: h */
    private boolean f15614h;

    public C0446qh(Context context, ActionBarContextView actionBarContextView, C0442qd qdVar) {
        this.f15610b = context;
        this.f15611e = actionBarContextView;
        this.f15612f = qdVar;
        C0472rg rgVar = new C0472rg(actionBarContextView.getContext());
        rgVar.mo9871m();
        this.f15609a = rgVar;
        rgVar.f15750b = this;
    }

    /* renamed from: b */
    public final Menu mo9647b() {
        return this.f15609a;
    }

    /* renamed from: e */
    public final CharSequence mo9652e() {
        return this.f15611e.f895f;
    }

    /* renamed from: f */
    public final CharSequence mo9653f() {
        return this.f15611e.f896g;
    }

    /* renamed from: g */
    public final boolean mo9654g() {
        return this.f15611e.f898i;
    }

    /* renamed from: c */
    public final void mo9650c() {
        if (!this.f15614h) {
            this.f15614h = true;
            this.f15611e.sendAccessibilityEvent(32);
            this.f15612f.mo9576a(this);
        }
    }

    /* renamed from: h */
    public final View mo9655h() {
        WeakReference weakReference = this.f15613g;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    /* renamed from: a */
    public final MenuInflater mo9642a() {
        return new C0451qm(this.f15611e.getContext());
    }

    /* renamed from: d */
    public final void mo9651d() {
        this.f15612f.mo9579b(this, this.f15609a);
    }

    /* renamed from: a */
    public final boolean mo9607a(C0472rg rgVar, MenuItem menuItem) {
        return this.f15612f.mo9578a((C0443qe) this, menuItem);
    }

    /* renamed from: a */
    public final void mo9603a(C0472rg rgVar) {
        mo9651d();
        this.f15611e.mo800b();
    }

    /* renamed from: a */
    public final void mo9644a(View view) {
        this.f15611e.mo796a(view);
        this.f15613g = view != null ? new WeakReference(view) : null;
    }

    /* renamed from: b */
    public final void mo9648b(int i) {
        mo9645a((CharSequence) this.f15610b.getString(i));
    }

    /* renamed from: a */
    public final void mo9645a(CharSequence charSequence) {
        this.f15611e.mo801b(charSequence);
    }

    /* renamed from: a */
    public final void mo9643a(int i) {
        mo9649b((CharSequence) this.f15610b.getString(i));
    }

    /* renamed from: b */
    public final void mo9649b(CharSequence charSequence) {
        this.f15611e.mo797a(charSequence);
    }

    /* renamed from: a */
    public final void mo9646a(boolean z) {
        this.f15604d = z;
        this.f15611e.mo799a(z);
    }
}
