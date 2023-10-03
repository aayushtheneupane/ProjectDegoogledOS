package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* renamed from: tk */
/* compiled from: PG */
public class C0530tk extends EditText {

    /* renamed from: a */
    private final C0523td f15937a;

    /* renamed from: b */
    private final C0557uk f15938b;

    /* renamed from: c */
    private final C0554uh f15939c;

    public C0530tk(Context context) {
        this(context, (AttributeSet) null);
    }

    public C0530tk(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.editTextStyle);
    }

    public C0530tk(Context context, AttributeSet attributeSet, int i) {
        super(C0680yz.m16188a(context), attributeSet, i);
        C0523td tdVar = new C0523td(this);
        this.f15937a = tdVar;
        tdVar.mo10104a(attributeSet, i);
        C0557uk ukVar = new C0557uk(this);
        this.f15938b = ukVar;
        ukVar.mo10246a(attributeSet, i);
        this.f15938b.mo10241a();
        this.f15939c = new C0554uh(this);
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0523td tdVar = this.f15937a;
        if (tdVar != null) {
            tdVar.mo10102a();
        }
        C0557uk ukVar = this.f15938b;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final Editable getText() {
        if (Build.VERSION.SDK_INT >= 28) {
            return super.getText();
        }
        return super.getEditableText();
    }

    public final TextClassifier getTextClassifier() {
        C0554uh uhVar;
        if (Build.VERSION.SDK_INT >= 28 || (uhVar = this.f15939c) == null) {
            return super.getTextClassifier();
        }
        return uhVar.mo10239a();
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return dcm.m5892a(super.onCreateInputConnection(editorInfo), editorInfo, (View) this);
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0523td tdVar = this.f15937a;
        if (tdVar != null) {
            tdVar.mo10105b();
        }
    }

    public final void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0523td tdVar = this.f15937a;
        if (tdVar != null) {
            tdVar.mo10103a(i);
        }
    }

    public final void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(dcm.m5891a((TextView) this, callback));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C0557uk ukVar = this.f15938b;
        if (ukVar != null) {
            ukVar.mo10245a(context, i);
        }
    }

    public final void setTextClassifier(TextClassifier textClassifier) {
        C0554uh uhVar;
        if (Build.VERSION.SDK_INT >= 28 || (uhVar = this.f15939c) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            uhVar.f15990a = textClassifier;
        }
    }
}
