package p000;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;

@Deprecated
/* renamed from: bdm */
/* compiled from: PG */
public final class bdm {

    /* renamed from: a */
    public final Context f2089a;

    public bdm(Context context) {
        this.f2089a = context;
    }

    /* renamed from: a */
    public static bdk m2201a(String str) {
        try {
            Class<?> cls = Class.forName(str);
            Object obj = null;
            try {
                obj = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (InstantiationException e) {
                m2202a(cls, e);
            } catch (IllegalAccessException e2) {
                m2202a(cls, e2);
            } catch (NoSuchMethodException e3) {
                m2202a(cls, e3);
            } catch (InvocationTargetException e4) {
                m2202a(cls, e4);
            }
            if (obj instanceof bdk) {
                return (bdk) obj;
            }
            String valueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 44);
            sb.append("Expected instanceof GlideModule, but found: ");
            sb.append(valueOf);
            throw new RuntimeException(sb.toString());
        } catch (ClassNotFoundException e5) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e5);
        }
    }

    /* renamed from: a */
    private static void m2202a(Class cls, Exception exc) {
        String valueOf = String.valueOf(cls);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 53);
        sb.append("Unable to instantiate GlideModule implementation for ");
        sb.append(valueOf);
        throw new RuntimeException(sb.toString(), exc);
    }
}
