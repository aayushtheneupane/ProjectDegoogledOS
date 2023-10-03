package p000;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* renamed from: ilq */
/* compiled from: PG */
final class ilq implements PrivilegedExceptionAction {
    public final /* bridge */ /* synthetic */ Object run() {
        return m13984a();
    }

    /* renamed from: a */
    private static final Unsafe m13984a() {
        Class<Unsafe> cls = Unsafe.class;
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            Object obj = field.get((Object) null);
            if (cls.isInstance(obj)) {
                return cls.cast(obj);
            }
        }
        return null;
    }
}
