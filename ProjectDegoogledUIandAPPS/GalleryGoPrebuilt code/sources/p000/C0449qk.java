package p000;

import android.view.InflateException;
import android.view.MenuItem;
import java.lang.reflect.Method;

/* renamed from: qk */
/* compiled from: PG */
final class C0449qk implements MenuItem.OnMenuItemClickListener {

    /* renamed from: a */
    private static final Class[] f15621a = {MenuItem.class};

    /* renamed from: b */
    private final Object f15622b;

    /* renamed from: c */
    private final Method f15623c;

    public C0449qk(Object obj, String str) {
        this.f15622b = obj;
        Class<?> cls = obj.getClass();
        try {
            this.f15623c = cls.getMethod(str, f15621a);
        } catch (Exception e) {
            InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
            inflateException.initCause(e);
            throw inflateException;
        }
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        try {
            if (this.f15623c.getReturnType() == Boolean.TYPE) {
                return ((Boolean) this.f15623c.invoke(this.f15622b, new Object[]{menuItem})).booleanValue();
            }
            this.f15623c.invoke(this.f15622b, new Object[]{menuItem});
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
