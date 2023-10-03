package p000;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* renamed from: ibo */
/* compiled from: PG */
class ibo implements PrivilegedExceptionAction {
    public final /* bridge */ /* synthetic */ Object run() {
        return m12624a();
    }

    /* renamed from: a */
    private static final Unsafe m12624a() {
        Class<Unsafe> cls = Unsafe.class;
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            Object obj = field.get((Object) null);
            if (cls.isInstance(obj)) {
                return cls.cast(obj);
            }
        }
        throw new NoSuchFieldError("the Unsafe");
    }
}
