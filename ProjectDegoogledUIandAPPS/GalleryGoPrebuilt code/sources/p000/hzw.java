package p000;

import java.io.Closeable;
import java.lang.reflect.Method;

/* renamed from: hzw */
/* compiled from: PG */
final class hzw implements hzx {

    /* renamed from: a */
    public static final hzw f13701a = new hzw();

    /* renamed from: b */
    public static final Method f13702b;

    static {
        Method method;
        try {
            method = Throwable.class.getMethod("addSuppressed", new Class[]{Throwable.class});
        } catch (Throwable th) {
            method = null;
        }
        f13702b = method;
    }

    /* renamed from: a */
    public final void mo8310a(Closeable closeable, Throwable th, Throwable th2) {
        if (th != th2) {
            try {
                f13702b.invoke(th, new Object[]{th2});
            } catch (Throwable th3) {
                hzv.f13700a.mo8310a(closeable, th, th2);
            }
        }
    }
}
