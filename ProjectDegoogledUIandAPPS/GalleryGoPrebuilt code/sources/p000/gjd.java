package p000;

import android.widget.AutoCompleteTextView;

/* renamed from: gjd */
/* compiled from: PG */
final class gjd implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ AutoCompleteTextView f11471a;

    /* renamed from: b */
    private final /* synthetic */ gje f11472b;

    public gjd(gje gje, AutoCompleteTextView autoCompleteTextView) {
        this.f11472b = gje;
        this.f11471a = autoCompleteTextView;
    }

    public final void run() {
        boolean isPopupShowing = this.f11471a.isPopupShowing();
        this.f11472b.f11473a.mo6761b(isPopupShowing);
        this.f11472b.f11473a.f11486c = isPopupShowing;
    }
}
