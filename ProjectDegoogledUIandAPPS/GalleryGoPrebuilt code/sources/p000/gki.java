package p000;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.ListAdapter;
import com.google.android.apps.photosgo.R;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gki */
/* compiled from: PG */
public final class gki extends C0522tc {

    /* renamed from: a */
    public final C0616wp f11541a;

    /* renamed from: b */
    private final AccessibilityManager f11542b;

    public gki(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.autoCompleteTextViewStyle);
        this.f11542b = (AccessibilityManager) context.getSystemService("accessibility");
        C0616wp wpVar = new C0616wp(getContext());
        this.f11541a = wpVar;
        wpVar.mo10509k();
        C0616wp wpVar2 = this.f11541a;
        wpVar2.f16254l = this;
        wpVar2.mo10508j();
        this.f11541a.mo10184a(getAdapter());
        this.f11541a.f16255m = new gkh(this);
    }

    public final CharSequence getHint() {
        TextInputLayout textInputLayout;
        ViewParent parent = getParent();
        while (true) {
            if (parent != null) {
                if (parent instanceof TextInputLayout) {
                    textInputLayout = (TextInputLayout) parent;
                    break;
                }
                parent = parent.getParent();
            } else {
                textInputLayout = null;
                break;
            }
        }
        if (textInputLayout == null || !textInputLayout.f5286h) {
            return super.getHint();
        }
        return textInputLayout.mo3680a();
    }

    public final void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        this.f11541a.mo10184a(getAdapter());
    }

    public final void showDropDown() {
        AccessibilityManager accessibilityManager;
        if (getInputType() != 0 || (accessibilityManager = this.f11542b) == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.showDropDown();
        } else {
            this.f11541a.mo9805ab();
        }
    }

    /* renamed from: a */
    public final void mo6800a(Object obj) {
        int i = Build.VERSION.SDK_INT;
        setText(convertSelectionToString(obj), false);
    }
}
