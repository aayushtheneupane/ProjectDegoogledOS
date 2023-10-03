package p000;

import android.view.View;

/* renamed from: gjj */
/* compiled from: PG */
final class gjj implements View.OnFocusChangeListener {

    /* renamed from: a */
    private final /* synthetic */ gjn f11479a;

    public gjj(gjn gjn) {
        this.f11479a = gjn;
    }

    public final void onFocusChange(View view, boolean z) {
        this.f11479a.f11495k.f5290l.setActivated(z);
        if (!z) {
            this.f11479a.mo6761b(false);
            this.f11479a.f11486c = false;
        }
    }
}
