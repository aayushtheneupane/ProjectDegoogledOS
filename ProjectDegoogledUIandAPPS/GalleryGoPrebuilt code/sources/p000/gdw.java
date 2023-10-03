package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import com.google.android.apps.photosgo.R;

/* renamed from: gdw */
/* compiled from: PG */
public final class gdw extends C0525tf {

    /* renamed from: a */
    private static final int[][] f11077a = {new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};

    /* renamed from: b */
    private ColorStateList f11078b;

    /* renamed from: c */
    private boolean f11079c;

    public gdw(Context context, AttributeSet attributeSet) {
        super(gkl.m10444a(context, attributeSet, R.attr.checkboxStyle, 2131952569), attributeSet, (byte[]) null);
        Context context2 = getContext();
        TypedArray a = gga.m10238a(context2, attributeSet, gdx.f11080a, R.attr.checkboxStyle, 2131952569, new int[0]);
        if (a.hasValue(0)) {
            cya.m5634a(this, gqb.m10615a(context2, a, 0));
        }
        this.f11079c = a.getBoolean(1, false);
        a.recycle();
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f11079c && cya.m5632a((CompoundButton) this) == null) {
            this.f11079c = true;
            if (this.f11078b == null) {
                int[] iArr = new int[f11077a.length];
                int a = ggf.m10246a((View) this, (int) R.attr.colorControlActivated);
                int a2 = ggf.m10246a((View) this, (int) R.attr.colorSurface);
                int a3 = ggf.m10246a((View) this, (int) R.attr.colorOnSurface);
                iArr[0] = ggf.m10243a(a2, a, 1.0f);
                iArr[1] = ggf.m10243a(a2, a3, 0.54f);
                iArr[2] = ggf.m10243a(a2, a3, 0.38f);
                iArr[3] = ggf.m10243a(a2, a3, 0.38f);
                this.f11078b = new ColorStateList(f11077a, iArr);
            }
            cya.m5634a(this, this.f11078b);
        }
    }
}
