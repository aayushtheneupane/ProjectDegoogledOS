package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import com.google.android.apps.photosgo.R;

/* renamed from: ggc */
/* compiled from: PG */
public final class ggc extends C0537tr {

    /* renamed from: a */
    private static final int[][] f11232a = {new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};

    /* renamed from: b */
    private ColorStateList f11233b;

    /* renamed from: c */
    private boolean f11234c;

    public ggc(Context context, AttributeSet attributeSet) {
        super(gkl.m10444a(context, attributeSet, R.attr.radioButtonStyle, 2131952570), attributeSet, (byte[]) null);
        TypedArray a = gga.m10238a(getContext(), attributeSet, ggd.f11235a, R.attr.radioButtonStyle, 2131952570, new int[0]);
        this.f11234c = a.getBoolean(0, false);
        a.recycle();
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f11234c && cya.m5632a((CompoundButton) this) == null) {
            this.f11234c = true;
            if (this.f11233b == null) {
                int a = ggf.m10246a((View) this, (int) R.attr.colorControlActivated);
                int a2 = ggf.m10246a((View) this, (int) R.attr.colorOnSurface);
                int a3 = ggf.m10246a((View) this, (int) R.attr.colorSurface);
                int[] iArr = new int[f11232a.length];
                iArr[0] = ggf.m10243a(a3, a, 1.0f);
                iArr[1] = ggf.m10243a(a3, a2, 0.54f);
                iArr[2] = ggf.m10243a(a3, a2, 0.38f);
                iArr[3] = ggf.m10243a(a3, a2, 0.38f);
                this.f11233b = new ColorStateList(f11232a, iArr);
            }
            cya.m5634a(this, this.f11233b);
        }
    }
}
