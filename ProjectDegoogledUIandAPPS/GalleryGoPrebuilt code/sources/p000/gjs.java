package p000;

import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: gjs */
/* compiled from: PG */
final class gjs implements TextWatcher {

    /* renamed from: a */
    private final /* synthetic */ gjw f11524a;

    public gjs(gjw gjw) {
        this.f11524a = gjw;
    }

    public final void afterTextChanged(Editable editable) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        gjw gjw = this.f11524a;
        gjw.f11497m.setChecked(!gjw.mo6785c());
    }
}
