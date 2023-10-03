package p000;

import android.text.Editable;
import android.text.TextWatcher;
import com.google.android.apps.photosgo.R;
import com.google.android.material.button.MaterialButton;

/* renamed from: clq */
/* compiled from: PG */
final class clq implements TextWatcher {

    /* renamed from: a */
    private final /* synthetic */ MaterialButton f4621a;

    /* renamed from: b */
    private final /* synthetic */ clr f4622b;

    public clq(clr clr, MaterialButton materialButton) {
        this.f4622b = clr;
        this.f4621a = materialButton;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
        String a = clr.m4510a(editable);
        ((MaterialButton) ife.m12885c((Object) this.f4621a)).setEnabled(clr.m4511a(a));
        if (clr.m4511a(a) || a.isEmpty()) {
            this.f4622b.f4643s.mo3693b(false);
            return;
        }
        clr clr = this.f4622b;
        clr.f4643s.mo3687a((CharSequence) clr.f4625a.getString(R.string.folder_creation_invalid_folder_name));
    }
}
