package p000;

import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* renamed from: bel */
/* compiled from: PG */
final class bel implements ViewTreeObserver.OnPreDrawListener {

    /* renamed from: a */
    private final WeakReference f2176a;

    public bel(bem bem) {
        this.f2176a = new WeakReference(bem);
    }

    public final boolean onPreDraw() {
        bem bem = (bem) this.f2176a.get();
        if (bem == null || bem.f2179b.isEmpty()) {
            return true;
        }
        int c = bem.mo1912c();
        int b = bem.mo1911b();
        if (!bem.m2355a(c, b)) {
            return true;
        }
        ArrayList arrayList = new ArrayList(bem.f2179b);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((beq) arrayList.get(i)).mo1906a(c, b);
        }
        bem.mo1910a();
        return true;
    }
}
