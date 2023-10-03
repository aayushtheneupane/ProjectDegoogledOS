package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;
import com.google.android.apps.photosgo.R;

/* renamed from: to */
/* compiled from: PG */
public final class C0534to extends MultiAutoCompleteTextView {

    /* renamed from: a */
    private static final int[] f15945a = {16843126};

    /* renamed from: b */
    private final C0523td f15946b;

    /* renamed from: c */
    private final C0557uk f15947c;

    public C0534to(Context context, AttributeSet attributeSet) {
        super(C0680yz.m16188a(context), attributeSet, R.attr.autoCompleteTextViewStyle);
        C0684zc a = C0684zc.m16192a(getContext(), attributeSet, f15945a, R.attr.autoCompleteTextViewStyle, 0);
        if (a.mo10735f(0)) {
            setDropDownBackgroundDrawable(a.mo10723a(0));
        }
        a.mo10724a();
        C0523td tdVar = new C0523td(this);
        this.f15946b = tdVar;
        tdVar.mo10104a(attributeSet, R.attr.autoCompleteTextViewStyle);
        C0557uk ukVar = new C0557uk(this);
        this.f15947c = ukVar;
        ukVar.mo10246a(attributeSet, (int) R.attr.autoCompleteTextViewStyle);
        this.f15947c.mo10241a();
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0523td tdVar = this.f15946b;
        if (tdVar != null) {
            tdVar.mo10102a();
        }
        C0557uk ukVar = this.f15947c;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return dcm.m5892a(super.onCreateInputConnection(editorInfo), editorInfo, (View) this);
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0523td tdVar = this.f15946b;
        if (tdVar != null) {
            tdVar.mo10105b();
        }
    }

    public final void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0523td tdVar = this.f15946b;
        if (tdVar != null) {
            tdVar.mo10103a(i);
        }
    }

    public final void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(C0436py.m15105b(getContext(), i));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C0557uk ukVar = this.f15947c;
        if (ukVar != null) {
            ukVar.mo10245a(context, i);
        }
    }
}
