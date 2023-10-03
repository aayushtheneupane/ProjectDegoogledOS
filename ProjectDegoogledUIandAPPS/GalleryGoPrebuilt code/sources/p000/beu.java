package p000;

import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.apps.photosgo.R;

@Deprecated
/* renamed from: beu */
/* compiled from: PG */
public abstract class beu extends bei {

    /* renamed from: a */
    public final View f2189a;

    /* renamed from: b */
    public final bet f2190b;

    public beu(View view) {
        this.f2189a = (View) cns.m4632a((Object) view);
        this.f2190b = new bet(view);
    }

    /* renamed from: ae */
    public final bea mo1896ae() {
        Object tag = this.f2189a.getTag(R.id.glide_custom_view_target_tag);
        if (tag == null) {
            return null;
        }
        if (tag instanceof bea) {
            return (bea) tag;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    /* renamed from: a */
    public final void mo1895a(beq beq) {
        bet bet = this.f2190b;
        int c = bet.mo1917c();
        int b = bet.mo1916b();
        if (!bet.m2390a(c, b)) {
            if (!bet.f2187b.contains(beq)) {
                bet.f2187b.add(beq);
            }
            if (bet.f2188c == null) {
                ViewTreeObserver viewTreeObserver = bet.f2186a.getViewTreeObserver();
                bet.f2188c = new bes(bet);
                viewTreeObserver.addOnPreDrawListener(bet.f2188c);
                return;
            }
            return;
        }
        beq.mo1906a(c, b);
    }

    /* renamed from: b */
    public final void mo1897b(beq beq) {
        this.f2190b.f2187b.remove(beq);
    }

    /* renamed from: a */
    public final void mo1894a(bea bea) {
        this.f2189a.setTag(R.id.glide_custom_view_target_tag, bea);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f2189a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 12);
        sb.append("Target for: ");
        sb.append(valueOf);
        return sb.toString();
    }
}
