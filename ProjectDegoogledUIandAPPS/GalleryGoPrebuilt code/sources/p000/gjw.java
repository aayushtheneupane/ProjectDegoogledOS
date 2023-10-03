package p000;

import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.google.android.apps.photosgo.R;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gjw */
/* compiled from: PG */
public final class gjw extends gjo {

    /* renamed from: a */
    public final TextWatcher f11527a = new gjs(this);

    /* renamed from: b */
    private final gkd f11528b = new gjt(this);

    /* renamed from: c */
    private final gke f11529c = new gju();

    public gjw(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    /* renamed from: c */
    public final boolean mo6785c() {
        EditText editText = this.f11495k.f5254a;
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    /* renamed from: a */
    public final void mo6743a() {
        this.f11495k.mo3691b(C0436py.m15105b(this.f11496l, R.drawable.design_password_eye));
        TextInputLayout textInputLayout = this.f11495k;
        textInputLayout.mo3692b(textInputLayout.getResources().getText(R.string.password_toggle_content_description));
        this.f11495k.mo3683a((View.OnClickListener) new gjv(this));
        this.f11495k.mo3686a(this.f11528b);
        TextInputLayout textInputLayout2 = this.f11495k;
        textInputLayout2.f5291m.add(this.f11529c);
    }
}
