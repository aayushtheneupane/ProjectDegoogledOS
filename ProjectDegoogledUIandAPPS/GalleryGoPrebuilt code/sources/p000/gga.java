package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.google.android.apps.photosgo.R;

/* renamed from: gga */
/* compiled from: PG */
public final class gga {

    /* renamed from: a */
    private static final int[] f11230a = {R.attr.colorPrimary};

    /* renamed from: b */
    private static final int[] f11231b = {R.attr.colorPrimaryVariant};

    /* renamed from: a */
    public static void m10239a(Context context) {
        m10241a(context, f11230a, "Theme.AppCompat");
    }

    /* renamed from: a */
    public static void m10240a(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, gfz.f11229b, i, i2);
        boolean z = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
        if (z) {
            TypedValue typedValue = new TypedValue();
            if (!context.getTheme().resolveAttribute(R.attr.isMaterialTheme, typedValue, true) || (typedValue.type == 18 && typedValue.data == 0)) {
                m10241a(context, f11231b, "Theme.MaterialComponents");
            }
        }
        m10239a(context);
    }

    /* renamed from: b */
    public static void m10242b(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2, int... iArr2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, gfz.f11229b, i, i2);
        boolean z = false;
        if (obtainStyledAttributes.getBoolean(2, false)) {
            int length = iArr2.length;
            if (length != 0) {
                TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr, i, i2);
                int i3 = 0;
                while (true) {
                    if (i3 < length) {
                        if (obtainStyledAttributes2.getResourceId(iArr2[i3], -1) == -1) {
                            obtainStyledAttributes2.recycle();
                            break;
                        }
                        i3++;
                    } else {
                        obtainStyledAttributes2.recycle();
                        z = true;
                        break;
                    }
                }
            } else if (obtainStyledAttributes.getResourceId(0, -1) != -1) {
                z = true;
            }
            obtainStyledAttributes.recycle();
            if (!z) {
                throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
            }
            return;
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    private static void m10241a(Context context, int[] iArr, String str) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(iArr);
        int i = 0;
        while (i < iArr.length) {
            if (obtainStyledAttributes.hasValue(i)) {
                i++;
            } else {
                obtainStyledAttributes.recycle();
                StringBuilder sb = new StringBuilder(str.length() + 77);
                sb.append("The style on this component requires your app theme to be ");
                sb.append(str);
                sb.append(" (or a descendant).");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    public static TypedArray m10238a(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2, int... iArr2) {
        m10240a(context, attributeSet, i, i2);
        m10242b(context, attributeSet, iArr, i, i2, iArr2);
        return context.obtainStyledAttributes(attributeSet, iArr, i, i2);
    }
}
