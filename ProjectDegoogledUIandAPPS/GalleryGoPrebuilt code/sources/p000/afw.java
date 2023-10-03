package p000;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* renamed from: afw */
/* compiled from: PG */
final class afw implements afx {

    /* renamed from: a */
    private final ViewGroupOverlay f362a;

    public afw(ViewGroup viewGroup) {
        this.f362a = viewGroup.getOverlay();
    }

    /* renamed from: a */
    public final void mo336a(View view) {
        this.f362a.add(view);
    }

    /* renamed from: b */
    public final void mo337b(View view) {
        this.f362a.remove(view);
    }
}
