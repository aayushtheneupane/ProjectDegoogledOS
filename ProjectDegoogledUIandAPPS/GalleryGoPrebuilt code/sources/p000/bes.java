package p000;

import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* renamed from: bes */
/* compiled from: PG */
final class bes implements ViewTreeObserver.OnPreDrawListener {

    /* renamed from: a */
    private final WeakReference f2184a;

    public bes(bet bet) {
        this.f2184a = new WeakReference(bet);
    }

    public final boolean onPreDraw() {
        bet bet = (bet) this.f2184a.get();
        if (bet == null || bet.f2187b.isEmpty()) {
            return true;
        }
        int c = bet.mo1917c();
        int b = bet.mo1916b();
        if (!bet.m2390a(c, b)) {
            return true;
        }
        ArrayList arrayList = new ArrayList(bet.f2187b);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((beq) arrayList.get(i)).mo1906a(c, b);
        }
        bet.mo1915a();
        return true;
    }
}
