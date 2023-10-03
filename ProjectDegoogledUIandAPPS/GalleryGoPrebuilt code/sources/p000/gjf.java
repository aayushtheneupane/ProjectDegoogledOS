package p000;

import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gjf */
/* compiled from: PG */
final class gjf extends gkc {

    /* renamed from: b */
    private final /* synthetic */ gjn f11474b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public gjf(gjn gjn, TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f11474b = gjn;
    }

    /* renamed from: a */
    public final void mo232a(View view, C0354mx mxVar) {
        super.mo232a(view, mxVar);
        mxVar.mo9423a((CharSequence) Spinner.class.getName());
        int i = Build.VERSION.SDK_INT;
        if (mxVar.f15257a.isShowingHintText()) {
            mxVar.mo9433c((CharSequence) null);
        }
    }

    /* renamed from: c */
    public final void mo6751c(View view, AccessibilityEvent accessibilityEvent) {
        super.mo6751c(view, accessibilityEvent);
        gjn gjn = this.f11474b;
        EditText editText = gjn.f11495k.f5254a;
        int i = gjn.f11483j;
        AutoCompleteTextView a = gjn.mo6758a(editText);
        if (accessibilityEvent.getEventType() == 1 && this.f11474b.f11491h.isTouchExplorationEnabled()) {
            this.f11474b.mo6759a(a);
        }
    }
}
