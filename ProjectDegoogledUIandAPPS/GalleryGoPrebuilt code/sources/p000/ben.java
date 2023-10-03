package p000;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.apps.photosgo.R;

/* renamed from: ben */
/* compiled from: PG */
public abstract class ben implements ber {

    /* renamed from: a */
    public final View f2181a;

    /* renamed from: b */
    private final bem f2182b;

    public ben(View view) {
        this.f2181a = (View) cns.m4632a((Object) view);
        this.f2182b = new bem(view);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo1431a();

    /* renamed from: b */
    public final void mo1442b() {
    }

    /* renamed from: c */
    public final void mo1443c() {
    }

    /* renamed from: c */
    public final void mo1898c(Drawable drawable) {
    }

    /* renamed from: d */
    public final void mo1444d() {
    }

    /* renamed from: ae */
    public final bea mo1896ae() {
        Object tag = this.f2181a.getTag(R.id.glide_custom_view_target_tag);
        if (tag == null) {
            return null;
        }
        if (tag instanceof bea) {
            return (bea) tag;
        }
        throw new IllegalArgumentException("You must not pass non-R.id ids to setTag(id)");
    }

    /* renamed from: a */
    public final void mo1895a(beq beq) {
        bem bem = this.f2182b;
        int c = bem.mo1912c();
        int b = bem.mo1911b();
        if (!bem.m2355a(c, b)) {
            if (!bem.f2179b.contains(beq)) {
                bem.f2179b.add(beq);
            }
            if (bem.f2180c == null) {
                ViewTreeObserver viewTreeObserver = bem.f2178a.getViewTreeObserver();
                bem.f2180c = new bel(bem);
                viewTreeObserver.addOnPreDrawListener(bem.f2180c);
                return;
            }
            return;
        }
        beq.mo1906a(c, b);
    }

    /* renamed from: b */
    public final void mo1798b(Drawable drawable) {
        this.f2182b.mo1910a();
        mo1431a();
    }

    /* renamed from: b */
    public final void mo1897b(beq beq) {
        this.f2182b.f2179b.remove(beq);
    }

    /* renamed from: a */
    public final void mo1894a(bea bea) {
        this.f2181a.setTag(R.id.glide_custom_view_target_tag, bea);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f2181a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 12);
        sb.append("Target for: ");
        sb.append(valueOf);
        return sb.toString();
    }
}
