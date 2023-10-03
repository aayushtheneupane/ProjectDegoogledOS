package p000;

import android.view.View;

/* renamed from: dun */
/* compiled from: PG */
final /* synthetic */ class dun implements View.OnClickListener {

    /* renamed from: a */
    private final dup f7412a;

    public dun(dup dup) {
        this.f7412a = dup;
    }

    public final void onClick(View view) {
        dup dup = this.f7412a;
        if (!dup.f7424k.isPresent()) {
            return;
        }
        if (!dup.f7422i.mo3764d()) {
            ihg.m13039a((hoi) new duz((cia) dup.f7424k.get()), view);
        } else if (!dup.f7424k.isPresent()) {
        } else {
            if (!dup.f7426m) {
                dup.f7422i.mo3756a((cpt) ede.m7256a((cia) dup.f7424k.get()));
            } else {
                dup.f7422i.mo3760b((cpt) ede.m7256a((cia) dup.f7424k.get()));
            }
        }
    }
}
