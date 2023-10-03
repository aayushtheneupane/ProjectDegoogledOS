package p000;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/* renamed from: git */
/* compiled from: PG */
final class git implements View.OnFocusChangeListener {

    /* renamed from: a */
    private final /* synthetic */ giu f11444a;

    public git(giu giu) {
        this.f11444a = giu;
    }

    public final void onFocusChange(View view, boolean z) {
        boolean isEmpty = TextUtils.isEmpty(((EditText) view).getText());
        gja gja = this.f11444a.f11445a;
        boolean z2 = true;
        if (!(!isEmpty) || !z) {
            z2 = false;
        }
        gja.mo6745b(z2);
    }
}
