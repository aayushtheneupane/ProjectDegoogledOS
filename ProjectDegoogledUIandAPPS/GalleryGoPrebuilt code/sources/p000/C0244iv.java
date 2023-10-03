package p000;

import android.graphics.Typeface;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: iv */
/* compiled from: PG */
public final class C0244iv extends C0243iu {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Typeface mo9112a(Object obj) {
        try {
            Object newInstance = Array.newInstance(this.f15057a, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.f15058b.invoke((Object) null, new Object[]{newInstance, "sans-serif", -1, -1});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Method mo9113a(Class cls) {
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[]{Array.newInstance(cls, 1).getClass(), String.class, Integer.TYPE, Integer.TYPE});
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
