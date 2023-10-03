package p000;

import android.content.res.TypedArray;
import android.view.View;

/* renamed from: gdl */
/* compiled from: PG */
final class gdl implements View.OnClickListener {

    /* renamed from: a */
    private final /* synthetic */ gdo f11028a;

    public gdl(gdo gdo) {
        this.f11028a = gdo;
    }

    public final void onClick(View view) {
        gdo gdo = this.f11028a;
        if (gdo.f11030a && gdo.isShowing()) {
            gdo gdo2 = this.f11028a;
            if (!gdo2.f11032c) {
                TypedArray obtainStyledAttributes = gdo2.getContext().obtainStyledAttributes(new int[]{16843611});
                gdo2.f11031b = obtainStyledAttributes.getBoolean(0, true);
                obtainStyledAttributes.recycle();
                gdo2.f11032c = true;
            }
            if (gdo2.f11031b) {
                this.f11028a.cancel();
            }
        }
    }
}
