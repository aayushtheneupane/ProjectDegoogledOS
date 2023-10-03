package p000;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

/* renamed from: gje */
/* compiled from: PG */
final class gje implements TextWatcher {

    /* renamed from: a */
    public final /* synthetic */ gjn f11473a;

    public gje(gjn gjn) {
        this.f11473a = gjn;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
        gjn gjn = this.f11473a;
        AutoCompleteTextView a = gjn.mo6758a(gjn.f11495k.f5254a);
        a.post(new gjd(this, a));
    }
}
