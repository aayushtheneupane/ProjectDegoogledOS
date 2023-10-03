package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* renamed from: te */
/* compiled from: PG */
public class C0524te extends Button implements C0370nm {

    /* renamed from: b */
    public final C0523td f15919b;

    /* renamed from: c */
    private final C0557uk f15920c;

    public C0524te(Context context) {
        this(context, (AttributeSet) null);
    }

    public C0524te(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyle);
    }

    public C0524te(Context context, AttributeSet attributeSet, int i) {
        super(C0680yz.m16188a(context), attributeSet, i);
        C0523td tdVar = new C0523td(this);
        this.f15919b = tdVar;
        tdVar.mo10104a(attributeSet, i);
        C0557uk ukVar = new C0557uk(this);
        this.f15920c = ukVar;
        ukVar.mo10246a(attributeSet, i);
        this.f15920c.mo10241a();
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0523td tdVar = this.f15919b;
        if (tdVar != null) {
            tdVar.mo10102a();
        }
        C0557uk ukVar = this.f15920c;
        if (ukVar != null) {
            ukVar.mo10241a();
        }
    }

    public final int getAutoSizeMaxTextSize() {
        if (f15293a) {
            return super.getAutoSizeMaxTextSize();
        }
        C0557uk ukVar = this.f15920c;
        if (ukVar != null) {
            return ukVar.mo10253g();
        }
        return -1;
    }

    public final int getAutoSizeMinTextSize() {
        if (f15293a) {
            return super.getAutoSizeMinTextSize();
        }
        C0557uk ukVar = this.f15920c;
        if (ukVar != null) {
            return ukVar.mo10252f();
        }
        return -1;
    }

    public final int getAutoSizeStepGranularity() {
        if (f15293a) {
            return super.getAutoSizeStepGranularity();
        }
        C0557uk ukVar = this.f15920c;
        if (ukVar != null) {
            return ukVar.mo10251e();
        }
        return -1;
    }

    public final int[] getAutoSizeTextAvailableSizes() {
        if (f15293a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        C0557uk ukVar = this.f15920c;
        if (ukVar != null) {
            return ukVar.mo10254h();
        }
        return new int[0];
    }

    public final int getAutoSizeTextType() {
        if (f15293a) {
            return super.getAutoSizeTextType() != 1 ? 0 : 1;
        }
        C0557uk ukVar = this.f15920c;
        if (ukVar != null) {
            return ukVar.mo10250d();
        }
        return 0;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        C0557uk ukVar = this.f15920c;
        if (ukVar != null) {
            ukVar.mo10255i();
        }
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (this.f15920c != null && !f15293a && this.f15920c.mo10249c()) {
            this.f15920c.mo10248b();
        }
    }

    public final void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (!f15293a) {
            C0557uk ukVar = this.f15920c;
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
            C0557uk ukVar = this.f15920c;
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
            C0557uk ukVar = this.f15920c;
            if (ukVar != null) {
                ukVar.mo10242a(i);
                return;
            }
            return;
        }
        super.setAutoSizeTextTypeWithDefaults(i);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0523td tdVar = this.f15919b;
        if (tdVar != null) {
            tdVar.mo10105b();
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0523td tdVar = this.f15919b;
        if (tdVar != null) {
            tdVar.mo10103a(i);
        }
    }

    public final void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(dcm.m5891a((TextView) this, callback));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C0557uk ukVar = this.f15920c;
        if (ukVar != null) {
            ukVar.mo10245a(context, i);
        }
    }

    public final void setTextSize(int i, float f) {
        if (!f15293a) {
            C0557uk ukVar = this.f15920c;
            if (ukVar != null) {
                ukVar.mo10243a(i, f);
                return;
            }
            return;
        }
        super.setTextSize(i, f);
    }
}
