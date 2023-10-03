package p000;

import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: gis */
/* compiled from: PG */
final class gis implements TextWatcher {

    /* renamed from: a */
    private final /* synthetic */ gja f11443a;

    public gis(gja gja) {
        this.f11443a = gja;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
        gja gja = this.f11443a;
        if (gja.f11495k.f5285g == null) {
            gja.mo6745b(gja.m10389a(editable));
        }
    }
}
