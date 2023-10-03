package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import android.widget.TextView;

/* renamed from: tg */
/* compiled from: PG */
public final class C0526tg extends CheckedTextView {

    /* renamed from: a */
    private static final int[] f15924a = {16843016};

    /* renamed from: b */
    private final C0557uk f15925b;

    public C0526tg(Context context, AttributeSet attributeSet) {
        super(C0680yz.m16188a(context), attributeSet, 16843720);
        C0557uk ukVar = new C0557uk(this);
        this.f15925b = ukVar;
        ukVar.mo10246a(attributeSet, 16843720);
        this.f15925b.mo10241a();
        C0684zc a = C0684zc.m16192a(getContext(), attributeSet, f15924a, 16843720, 0);
        setCheckMarkDrawable(a.mo10723a(0));
        a.mo10724a();
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0557uk ukVar = this.f15925b;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return dcm.m5892a(super.onCreateInputConnection(editorInfo), editorInfo, (View) this);
    }

    public final void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(C0436py.m15105b(getContext(), i));
    }

    public final void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(dcm.m5891a((TextView) this, callback));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C0557uk ukVar = this.f15925b;
        if (ukVar != null) {
            ukVar.mo10245a(context, i);
        }
    }
}
