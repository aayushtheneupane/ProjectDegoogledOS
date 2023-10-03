package p000;

import android.widget.AutoCompleteTextView;

/* renamed from: gjk */
/* compiled from: PG */
final class gjk implements AutoCompleteTextView.OnDismissListener {

    /* renamed from: a */
    private final /* synthetic */ gjn f11480a;

    public gjk(gjn gjn) {
        this.f11480a = gjn;
    }

    public final void onDismiss() {
        gjn gjn = this.f11480a;
        gjn.f11486c = true;
        gjn.f11488e = System.currentTimeMillis();
        this.f11480a.mo6761b(false);
    }
}
