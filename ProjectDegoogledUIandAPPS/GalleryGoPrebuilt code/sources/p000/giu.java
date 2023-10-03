package p000;

import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: giu */
/* compiled from: PG */
final class giu implements gkd {

    /* renamed from: a */
    public final /* synthetic */ gja f11445a;

    public giu(gja gja) {
        this.f11445a = gja;
    }

    /* renamed from: a */
    public final void mo6734a(TextInputLayout textInputLayout) {
        EditText editText = textInputLayout.f5254a;
        textInputLayout.mo3695c(gja.m10389a(editText.getText()));
        textInputLayout.mo3696d(false);
        editText.setOnFocusChangeListener(new git(this));
        editText.removeTextChangedListener(this.f11445a.f11464a);
        editText.addTextChangedListener(this.f11445a.f11464a);
    }
}
