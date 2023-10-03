package p000;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.google.android.apps.photosgo.R;

/* renamed from: gkj */
/* compiled from: PG */
public final class gkj extends C0558ul {
    public gkj(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 16842884);
        if (m10443a(context)) {
            Resources.Theme theme = context.getTheme();
            TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, gkk.f11544b, 16842884, 0);
            int a = m10441a(context, obtainStyledAttributes, 1, 2);
            obtainStyledAttributes.recycle();
            if (a == -1) {
                TypedArray obtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, gkk.f11544b, 16842884, 0);
                int resourceId = obtainStyledAttributes2.getResourceId(0, -1);
                obtainStyledAttributes2.recycle();
                if (resourceId != -1) {
                    m10442a(theme, resourceId);
                }
            }
        }
    }

    /* renamed from: a */
    private final void m10442a(Resources.Theme theme, int i) {
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(i, gkk.f11543a);
        int a = m10441a(getContext(), obtainStyledAttributes, 0, 1);
        obtainStyledAttributes.recycle();
        if (a >= 0) {
            dcm.m5907d(this, a);
        }
    }

    /* renamed from: a */
    private static boolean m10443a(Context context) {
        return ggf.m10253a(context, (int) R.attr.textAppearanceLineHeightEnabled, true);
    }

    /* renamed from: a */
    private static int m10441a(Context context, TypedArray typedArray, int... iArr) {
        int i = -1;
        for (int i2 = 0; i2 < iArr.length && i < 0; i2++) {
            int i3 = iArr[i2];
            TypedValue typedValue = new TypedValue();
            if (!typedArray.getValue(i3, typedValue) || typedValue.type != 2) {
                i = typedArray.getDimensionPixelSize(i3, -1);
            } else {
                TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
                obtainStyledAttributes.recycle();
                i = dimensionPixelSize;
            }
        }
        return i;
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (m10443a(context)) {
            m10442a(context.getTheme(), i);
        }
    }
}
