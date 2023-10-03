package p000;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* renamed from: ul */
/* compiled from: PG */
public class C0558ul extends TextView implements C0370nm {

    /* renamed from: b */
    private final C0523td f16009b;

    /* renamed from: c */
    private final C0557uk f16010c;

    /* renamed from: d */
    private final C0554uh f16011d;

    public C0558ul(Context context) {
        this(context, (AttributeSet) null);
    }

    public C0558ul(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public C0558ul(Context context, AttributeSet attributeSet, int i) {
        super(C0680yz.m16188a(context), attributeSet, i);
        C0523td tdVar = new C0523td(this);
        this.f16009b = tdVar;
        tdVar.mo10104a(attributeSet, i);
        C0557uk ukVar = new C0557uk(this);
        this.f16010c = ukVar;
        ukVar.mo10246a(attributeSet, i);
        this.f16010c.mo10241a();
        this.f16011d = new C0554uh(this);
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0523td tdVar = this.f16009b;
        if (tdVar != null) {
            tdVar.mo10102a();
        }
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final int getAutoSizeMaxTextSize() {
        if (f15293a) {
            return super.getAutoSizeMaxTextSize();
        }
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            return ukVar.mo10253g();
        }
        return -1;
    }

    public final int getAutoSizeMinTextSize() {
        if (f15293a) {
            return super.getAutoSizeMinTextSize();
        }
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            return ukVar.mo10252f();
        }
        return -1;
    }

    public final int getAutoSizeStepGranularity() {
        if (f15293a) {
            return super.getAutoSizeStepGranularity();
        }
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            return ukVar.mo10251e();
        }
        return -1;
    }

    public final int[] getAutoSizeTextAvailableSizes() {
        if (f15293a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            return ukVar.mo10254h();
        }
        return new int[0];
    }

    public final int getAutoSizeTextType() {
        if (f15293a) {
            return super.getAutoSizeTextType() != 1 ? 0 : 1;
        }
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            return ukVar.mo10250d();
        }
        return 0;
    }

    public final int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    public final int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public final TextClassifier getTextClassifier() {
        C0554uh uhVar;
        if (Build.VERSION.SDK_INT >= 28 || (uhVar = this.f16011d) == null) {
            return super.getTextClassifier();
        }
        return uhVar.mo10239a();
    }

    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return dcm.m5892a(super.onCreateInputConnection(editorInfo), editorInfo, (View) this);
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            ukVar.mo10255i();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (this.f16010c != null && !f15293a && this.f16010c.mo10249c()) {
            this.f16010c.mo10248b();
        }
    }

    public final void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (!f15293a) {
            C0557uk ukVar = this.f16010c;
            if (ukVar != null) {
                ukVar.mo10244a(i, i2, i3, i4);
                return;
            }
            return;
        }
        super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
    }

    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (!f15293a) {
            C0557uk ukVar = this.f16010c;
            if (ukVar != null) {
                ukVar.mo10247a(iArr, i);
                return;
            }
            return;
        }
        super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
    }

    public final void setAutoSizeTextTypeWithDefaults(int i) {
        if (!f15293a) {
            C0557uk ukVar = this.f16010c;
            if (ukVar != null) {
                ukVar.mo10242a(i);
                return;
            }
            return;
        }
        super.setAutoSizeTextTypeWithDefaults(i);
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0523td tdVar = this.f16009b;
        if (tdVar != null) {
            tdVar.mo10105b();
        }
    }

    public final void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0523td tdVar = this.f16009b;
        if (tdVar != null) {
            tdVar.mo10103a(i);
        }
    }

    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        Drawable drawable = null;
        Drawable b = i != 0 ? C0436py.m15105b(context, i) : null;
        Drawable b2 = i2 != 0 ? C0436py.m15105b(context, i2) : null;
        Drawable b3 = i3 != 0 ? C0436py.m15105b(context, i3) : null;
        if (i4 != 0) {
            drawable = C0436py.m15105b(context, i4);
        }
        setCompoundDrawablesRelativeWithIntrinsicBounds(b, b2, b3, drawable);
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        Drawable drawable = null;
        Drawable b = i != 0 ? C0436py.m15105b(context, i) : null;
        Drawable b2 = i2 != 0 ? C0436py.m15105b(context, i2) : null;
        Drawable b3 = i3 != 0 ? C0436py.m15105b(context, i3) : null;
        if (i4 != 0) {
            drawable = C0436py.m15105b(context, i4);
        }
        setCompoundDrawablesWithIntrinsicBounds(b, b2, b3, drawable);
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(dcm.m5891a((TextView) this, callback));
    }

    public final void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            dcm.m5905b((TextView) this, i);
        }
    }

    public final void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            dcm.m5906c((TextView) this, i);
        }
    }

    public final void setLineHeight(int i) {
        dcm.m5907d(this, i);
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C0557uk ukVar = this.f16010c;
        if (ukVar != null) {
            ukVar.mo10245a(context, i);
        }
    }

    public final void setTextClassifier(TextClassifier textClassifier) {
        C0554uh uhVar;
        if (Build.VERSION.SDK_INT >= 28 || (uhVar = this.f16011d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            uhVar.f15990a = textClassifier;
        }
    }

    public final void setTextSize(int i, float f) {
        if (!f15293a) {
            C0557uk ukVar = this.f16010c;
            if (ukVar != null) {
                ukVar.mo10243a(i, f);
                return;
            }
            return;
        }
        super.setTextSize(i, f);
    }

    public final void setTypeface(Typeface typeface, int i) {
        Typeface typeface2 = null;
        if (typeface != null && i > 0) {
            typeface2 = C0241is.m14372a(getContext(), typeface, i);
        }
        if (typeface2 != null) {
            typeface = typeface2;
        }
        super.setTypeface(typeface, i);
    }
}
