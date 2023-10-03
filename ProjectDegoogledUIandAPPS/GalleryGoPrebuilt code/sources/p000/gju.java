package p000;

import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gju */
/* compiled from: PG */
final class gju implements gke {
    /* renamed from: a */
    public final void mo6783a(TextInputLayout textInputLayout, int i) {
        EditText editText = textInputLayout.f5254a;
        if (editText != null && i == 1) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
