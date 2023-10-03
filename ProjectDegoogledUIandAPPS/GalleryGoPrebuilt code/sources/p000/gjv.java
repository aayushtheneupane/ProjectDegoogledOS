package p000;

import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.EditText;

/* renamed from: gjv */
/* compiled from: PG */
final class gjv implements View.OnClickListener {

    /* renamed from: a */
    private final /* synthetic */ gjw f11526a;

    public gjv(gjw gjw) {
        this.f11526a = gjw;
    }

    public final void onClick(View view) {
        EditText editText = this.f11526a.f11495k.f5254a;
        if (editText != null) {
            int selectionEnd = editText.getSelectionEnd();
            if (this.f11526a.mo6785c()) {
                editText.setTransformationMethod((TransformationMethod) null);
            } else {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            editText.setSelection(selectionEnd);
        }
    }
}
