package p000;

import java.io.Closeable;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: hzv */
/* compiled from: PG */
final class hzv implements hzx {

    /* renamed from: a */
    public static final hzv f13700a = new hzv();

    /* renamed from: a */
    public final void mo8310a(Closeable closeable, Throwable th, Throwable th2) {
        Logger logger = hzu.f13699a;
        Level level = Level.WARNING;
        String valueOf = String.valueOf(closeable);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 42);
        sb.append("Suppressing exception thrown when closing ");
        sb.append(valueOf);
        logger.logp(level, "com.google.common.io.Closer$LoggingSuppressor", "suppress", sb.toString(), th2);
    }
}
