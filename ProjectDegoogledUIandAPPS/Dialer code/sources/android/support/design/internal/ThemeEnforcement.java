package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import com.android.dialer.R;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class ThemeEnforcement {
    private static final int[] APPCOMPAT_CHECK_ATTRS = {R.attr.colorPrimary};
    private static final int[] MATERIAL_CHECK_ATTRS = {R.attr.colorSecondary};

    public static void checkAppCompatTheme(Context context) {
        checkTheme(context, APPCOMPAT_CHECK_ATTRS, "Theme.AppCompat");
    }

    private static void checkTheme(Context context, int[] iArr, String str) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(iArr);
        boolean hasValue = obtainStyledAttributes.hasValue(0);
        obtainStyledAttributes.recycle();
        if (!hasValue) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline9("The style on this component requires your app theme to be ", str, " (or a descendant)."));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
        if (r0.getResourceId(android.support.design.R$styleable.ThemeEnforcement_android_textAppearance, -1) != -1) goto L_0x0056;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.res.TypedArray obtainStyledAttributes(android.content.Context r8, android.util.AttributeSet r9, int[] r10, int r11, int r12, int... r13) {
        /*
            int[] r0 = android.support.design.R$styleable.ThemeEnforcement
            android.content.res.TypedArray r0 = r8.obtainStyledAttributes(r9, r0, r11, r12)
            r1 = 0
            r2 = 1
            boolean r3 = r0.getBoolean(r2, r1)
            r0.recycle()
            if (r3 == 0) goto L_0x0018
            int[] r0 = MATERIAL_CHECK_ATTRS
            java.lang.String r3 = "Theme.MaterialComponents"
            checkTheme(r8, r0, r3)
        L_0x0018:
            checkAppCompatTheme(r8)
            int[] r0 = android.support.design.R$styleable.ThemeEnforcement
            android.content.res.TypedArray r0 = r8.obtainStyledAttributes(r9, r0, r11, r12)
            r3 = 2
            boolean r3 = r0.getBoolean(r3, r1)
            if (r3 != 0) goto L_0x002c
            r0.recycle()
            goto L_0x005c
        L_0x002c:
            r3 = -1
            if (r13 == 0) goto L_0x004e
            int r4 = r13.length
            if (r4 != 0) goto L_0x0033
            goto L_0x004e
        L_0x0033:
            android.content.res.TypedArray r4 = r8.obtainStyledAttributes(r9, r10, r11, r12)
            int r5 = r13.length
            r6 = r1
        L_0x0039:
            if (r6 >= r5) goto L_0x004a
            r7 = r13[r6]
            int r7 = r4.getResourceId(r7, r3)
            if (r7 != r3) goto L_0x0047
            r4.recycle()
            goto L_0x0057
        L_0x0047:
            int r6 = r6 + 1
            goto L_0x0039
        L_0x004a:
            r4.recycle()
            goto L_0x0056
        L_0x004e:
            int r13 = android.support.design.R$styleable.ThemeEnforcement_android_textAppearance
            int r13 = r0.getResourceId(r13, r3)
            if (r13 == r3) goto L_0x0057
        L_0x0056:
            r1 = r2
        L_0x0057:
            r0.recycle()
            if (r1 == 0) goto L_0x0061
        L_0x005c:
            android.content.res.TypedArray r8 = r8.obtainStyledAttributes(r9, r10, r11, r12)
            return r8
        L_0x0061:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant)."
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.internal.ThemeEnforcement.obtainStyledAttributes(android.content.Context, android.util.AttributeSet, int[], int, int, int[]):android.content.res.TypedArray");
    }
}
