package p000;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.preference.EditTextPreference;

/* renamed from: ack */
/* compiled from: PG */
public final class ack extends add {

    /* renamed from: Z */
    private EditText f181Z;

    /* renamed from: aa */
    private CharSequence f182aa;

    /* access modifiers changed from: protected */
    /* renamed from: P */
    public final boolean mo164P() {
        return true;
    }

    /* renamed from: R */
    private final EditTextPreference m202R() {
        return (EditTextPreference) mo192Q();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo166b(View view) {
        super.mo166b(view);
        EditText editText = (EditText) view.findViewById(16908291);
        this.f181Z = editText;
        if (editText != null) {
            editText.requestFocus();
            this.f181Z.setText(this.f182aa);
            EditText editText2 = this.f181Z;
            editText2.setSelection(editText2.getText().length());
            m202R();
            return;
        }
        throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
    }

    /* renamed from: a */
    public final void mo165a(Bundle bundle) {
        super.mo165a(bundle);
        if (bundle != null) {
            this.f182aa = bundle.getCharSequence("EditTextPreferenceDialogFragment.text");
        } else {
            this.f182aa = m202R().f1079g;
        }
    }

    /* renamed from: b */
    public final void mo167b(boolean z) {
        if (z) {
            String obj = this.f181Z.getText().toString();
            EditTextPreference R = m202R();
            if (R.mo1179b((Object) obj)) {
                R.mo1161a(obj);
            }
        }
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        super.mo168e(bundle);
        bundle.putCharSequence("EditTextPreferenceDialogFragment.text", this.f182aa);
    }
}
