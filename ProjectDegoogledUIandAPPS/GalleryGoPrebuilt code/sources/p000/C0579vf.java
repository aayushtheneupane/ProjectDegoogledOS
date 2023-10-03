package p000;

import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;

/* renamed from: vf */
/* compiled from: PG */
public final class C0579vf {

    /* renamed from: a */
    public static final Rect f16087a = new Rect();

    /* renamed from: b */
    private static Class f16088b;

    static {
        int i = Build.VERSION.SDK_INT;
        try {
            f16088b = Class.forName("android.graphics.Insets");
        } catch (ClassNotFoundException e) {
        }
    }

    /* renamed from: b */
    public static boolean m15606b(Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        int i2 = Build.VERSION.SDK_INT;
        int i3 = Build.VERSION.SDK_INT;
        if (drawable instanceof DrawableContainer) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (!(constantState instanceof DrawableContainer.DrawableContainerState)) {
                return true;
            }
            for (Drawable b : ((DrawableContainer.DrawableContainerState) constantState).getChildren()) {
                if (!m15606b(b)) {
                    return false;
                }
            }
            return true;
        } else if (drawable instanceof C0253jd) {
            return m15606b(((C0253jd) drawable).mo9135a());
        } else {
            if (drawable instanceof C0437pz) {
                return m15606b(((C0437pz) drawable).f15599a);
            }
            if (drawable instanceof ScaleDrawable) {
                return m15606b(((ScaleDrawable) drawable).getDrawable());
            }
            return true;
        }
    }

    /* renamed from: a */
    static void m15605a() {
        int i = Build.VERSION.SDK_INT;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Rect m15604a(android.graphics.drawable.Drawable r12) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 29
            if (r0 < r1) goto L_0x0020
            android.graphics.Insets r12 = r12.getOpticalInsets()
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            int r1 = r12.left
            r0.left = r1
            int r1 = r12.right
            r0.right = r1
            int r1 = r12.top
            r0.top = r1
            int r12 = r12.bottom
            r0.bottom = r12
            return r0
        L_0x0020:
            java.lang.Class r0 = f16088b
            if (r0 == 0) goto L_0x00bc
            boolean r0 = r12 instanceof p000.C0253jd     // Catch:{ Exception -> 0x00b4 }
            if (r0 != 0) goto L_0x0029
            goto L_0x002f
        L_0x0029:
            jd r12 = (p000.C0253jd) r12     // Catch:{ Exception -> 0x00b4 }
            android.graphics.drawable.Drawable r12 = r12.mo9135a()     // Catch:{ Exception -> 0x00b4 }
        L_0x002f:
            java.lang.Class r0 = r12.getClass()     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r1 = "getOpticalInsets"
            r2 = 0
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x00b4 }
            java.lang.reflect.Method r0 = r0.getMethod(r1, r3)     // Catch:{ Exception -> 0x00b4 }
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x00b4 }
            java.lang.Object r12 = r0.invoke(r12, r1)     // Catch:{ Exception -> 0x00b4 }
            if (r12 == 0) goto L_0x00bc
            android.graphics.Rect r0 = new android.graphics.Rect     // Catch:{ Exception -> 0x00b4 }
            r0.<init>()     // Catch:{ Exception -> 0x00b4 }
            java.lang.Class r1 = f16088b     // Catch:{ Exception -> 0x00b4 }
            java.lang.reflect.Field[] r1 = r1.getFields()     // Catch:{ Exception -> 0x00b4 }
            int r3 = r1.length     // Catch:{ Exception -> 0x00b4 }
            r4 = 0
        L_0x0051:
            if (r4 >= r3) goto L_0x00b3
            r5 = r1[r4]     // Catch:{ Exception -> 0x00b4 }
            java.lang.String r6 = r5.getName()     // Catch:{ Exception -> 0x00b4 }
            int r7 = r6.hashCode()     // Catch:{ Exception -> 0x00b4 }
            r8 = 3
            r9 = 2
            r10 = 1
            r11 = -1
            switch(r7) {
                case -1383228885: goto L_0x0083;
                case 115029: goto L_0x0079;
                case 3317767: goto L_0x006f;
                case 108511772: goto L_0x0065;
                default: goto L_0x0064;
            }     // Catch:{ Exception -> 0x00b4 }
        L_0x0064:
            goto L_0x008c
        L_0x0065:
            java.lang.String r7 = "right"
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x00b4 }
            if (r6 == 0) goto L_0x0064
            r11 = 2
            goto L_0x008c
        L_0x006f:
            java.lang.String r7 = "left"
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x00b4 }
            if (r6 == 0) goto L_0x0064
            r11 = 0
            goto L_0x008c
        L_0x0079:
            java.lang.String r7 = "top"
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x00b4 }
            if (r6 == 0) goto L_0x0064
            r11 = 1
            goto L_0x008c
        L_0x0083:
            java.lang.String r7 = "bottom"
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x00b4 }
            if (r6 == 0) goto L_0x0064
            r11 = 3
        L_0x008c:
            if (r11 == 0) goto L_0x00aa
            if (r11 == r10) goto L_0x00a3
            if (r11 == r9) goto L_0x009c
            if (r11 == r8) goto L_0x0095
            goto L_0x00b0
        L_0x0095:
            int r5 = r5.getInt(r12)     // Catch:{ Exception -> 0x00b4 }
            r0.bottom = r5     // Catch:{ Exception -> 0x00b4 }
            goto L_0x00b0
        L_0x009c:
            int r5 = r5.getInt(r12)     // Catch:{ Exception -> 0x00b4 }
            r0.right = r5     // Catch:{ Exception -> 0x00b4 }
            goto L_0x00b0
        L_0x00a3:
            int r5 = r5.getInt(r12)     // Catch:{ Exception -> 0x00b4 }
            r0.top = r5     // Catch:{ Exception -> 0x00b4 }
            goto L_0x00b0
        L_0x00aa:
            int r5 = r5.getInt(r12)     // Catch:{ Exception -> 0x00b4 }
            r0.left = r5     // Catch:{ Exception -> 0x00b4 }
        L_0x00b0:
            int r4 = r4 + 1
            goto L_0x0051
        L_0x00b3:
            return r0
        L_0x00b4:
            r12 = move-exception
            java.lang.String r12 = "DrawableUtils"
            java.lang.String r0 = "Couldn't obtain the optical insets. Ignoring."
            android.util.Log.e(r12, r0)
        L_0x00bc:
            android.graphics.Rect r12 = f16087a
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0579vf.m15604a(android.graphics.drawable.Drawable):android.graphics.Rect");
    }

    /* renamed from: a */
    public static PorterDuff.Mode m15603a(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
