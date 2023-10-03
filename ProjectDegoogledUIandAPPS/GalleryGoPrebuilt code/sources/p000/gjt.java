package p000;

import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gjt */
/* compiled from: PG */
final class gjt implements gkd {

    /* renamed from: a */
    private final /* synthetic */ gjw f11525a;

    public gjt(gjw gjw) {
        this.f11525a = gjw;
    }

    /* renamed from: a */
    public final void mo6734a(TextInputLayout textInputLayout) {
        EditText editText = textInputLayout.f5254a;
        textInputLayout.mo3695c(true);
        gjw gjw = this.f11525a;
        gjw.f11497m.setChecked(!gjw.mo6785c());
        editText.removeTextChangedListener(this.f11525a.f11527a);
        editText.addTextChangedListener(this.f11525a.f11527a);
    }
}
