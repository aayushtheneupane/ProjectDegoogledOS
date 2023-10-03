package p000;

import android.text.Editable;
import android.text.TextWatcher;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gjy */
/* compiled from: PG */
public final class gjy implements TextWatcher {

    /* renamed from: a */
    private final /* synthetic */ TextInputLayout f11531a;

    public gjy(TextInputLayout textInputLayout) {
        this.f11531a = textInputLayout;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
        TextInputLayout textInputLayout = this.f11531a;
        textInputLayout.mo3688a(!textInputLayout.f5293o);
        TextInputLayout textInputLayout2 = this.f11531a;
        if (textInputLayout2.f5281c) {
            textInputLayout2.mo3681a(editable.length());
        }
    }
}
