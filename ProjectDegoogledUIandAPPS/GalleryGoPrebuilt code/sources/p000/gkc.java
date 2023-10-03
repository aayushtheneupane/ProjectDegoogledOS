package p000;

import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gkc */
/* compiled from: PG */
public class gkc extends C0315ll {

    /* renamed from: b */
    private final TextInputLayout f11537b;

    public gkc(TextInputLayout textInputLayout) {
        this.f11537b = textInputLayout;
    }

    /* renamed from: a */
    public void mo232a(View view, C0354mx mxVar) {
        boolean z;
        String str;
        TextView textView;
        C0354mx mxVar2 = mxVar;
        super.mo232a(view, mxVar);
        EditText editText = this.f11537b.f5254a;
        CharSequence charSequence = null;
        Editable text = editText != null ? editText.getText() : null;
        CharSequence a = this.f11537b.mo3680a();
        TextInputLayout textInputLayout = this.f11537b;
        gjq gjq = textInputLayout.f5280b;
        CharSequence charSequence2 = gjq.f11515m ? gjq.f11514l : null;
        CharSequence c = textInputLayout.mo3694c();
        TextInputLayout textInputLayout2 = this.f11537b;
        int i = textInputLayout2.f5282d;
        if (textInputLayout2.f5281c && textInputLayout2.f5283e && (textView = textInputLayout2.f5284f) != null) {
            charSequence = textView.getContentDescription();
        }
        boolean z2 = !TextUtils.isEmpty(text);
        boolean z3 = !TextUtils.isEmpty(a);
        boolean z4 = !TextUtils.isEmpty(charSequence2);
        boolean isEmpty = TextUtils.isEmpty(c);
        boolean z5 = !isEmpty;
        if (z5 || !TextUtils.isEmpty(charSequence)) {
            z = true;
        } else {
            z = false;
        }
        String charSequence3 = !z3 ? "" : a.toString();
        String valueOf = String.valueOf(charSequence3);
        if ((!z5 && !z4) || TextUtils.isEmpty(charSequence3)) {
            str = "";
        } else {
            str = ", ";
        }
        String valueOf2 = String.valueOf(str.length() == 0 ? new String(valueOf) : valueOf.concat(str));
        if (z5) {
            charSequence2 = c;
        } else if (!z4) {
            charSequence2 = "";
        }
        String valueOf3 = String.valueOf(charSequence2);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append(valueOf2);
        sb.append(valueOf3);
        String sb2 = sb.toString();
        if (z2) {
            mxVar2.mo9428b((CharSequence) text);
        } else if (!TextUtils.isEmpty(sb2)) {
            mxVar2.mo9428b((CharSequence) sb2);
        }
        if (!TextUtils.isEmpty(sb2)) {
            int i2 = Build.VERSION.SDK_INT;
            mxVar2.mo9433c((CharSequence) sb2);
            int i3 = Build.VERSION.SDK_INT;
            mxVar2.f15257a.setShowingHintText(!z2);
        }
        if (text == null || text.length() != i) {
            i = -1;
        }
        int i4 = Build.VERSION.SDK_INT;
        mxVar2.f15257a.setMaxTextLength(i);
        if (z) {
            if (isEmpty) {
                c = charSequence;
            }
            int i5 = Build.VERSION.SDK_INT;
            mxVar2.f15257a.setError(c);
            int i6 = Build.VERSION.SDK_INT;
            mxVar2.f15257a.setContentInvalid(true);
        }
    }
}
