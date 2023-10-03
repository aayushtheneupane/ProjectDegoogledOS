package p000;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: zv */
/* compiled from: PG */
public final class C0703zv {

    /* renamed from: a */
    private static Method f16480a;

    static {
        int i = Build.VERSION.SDK_INT;
        try {
            Method declaredMethod = View.class.getDeclaredMethod("computeFitSystemWindows", new Class[]{Rect.class, Rect.class});
            f16480a = declaredMethod;
            if (!declaredMethod.isAccessible()) {
                f16480a.setAccessible(true);
            }
        } catch (NoSuchMethodException e) {
        }
    }

    /* renamed from: a */
    public static void m16279a(View view, Rect rect, Rect rect2) {
        Method method = f16480a;
        if (method != null) {
            try {
                method.invoke(view, new Object[]{rect, rect2});
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    public static boolean m16280a(View view) {
        return C0340mj.m14714f(view) == 1;
    }

    /* renamed from: b */
    public static void m16281b(View view) {
        int i = Build.VERSION.SDK_INT;
        try {
            Method method = view.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            method.invoke(view, new Object[0]);
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e2) {
        } catch (IllegalAccessException e3) {
        }
    }
}
