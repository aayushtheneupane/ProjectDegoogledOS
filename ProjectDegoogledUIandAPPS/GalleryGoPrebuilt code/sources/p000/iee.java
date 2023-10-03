package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: iee */
/* compiled from: PG */
final class iee implements ieh {

    /* renamed from: a */
    public static final ieh f13951a = new iee((Object) null);

    /* renamed from: b */
    private static final Logger f13952b = Logger.getLogger(iee.class.getName());

    /* renamed from: c */
    private final Object f13953c;

    public final boolean cancel(boolean z) {
        return false;
    }

    public final Object get() {
        return this.f13953c;
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return true;
    }

    public iee(Object obj) {
        this.f13953c = obj;
    }

    /* renamed from: a */
    public final void mo53a(Runnable runnable, Executor executor) {
        ife.m12869b((Object) runnable, (Object) "Runnable was null.");
        ife.m12869b((Object) executor, (Object) "Executor was null.");
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = f13952b;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 57 + String.valueOf(valueOf2).length());
            sb.append("RuntimeException while executing runnable ");
            sb.append(valueOf);
            sb.append(" with executor ");
            sb.append(valueOf2);
            logger.logp(level, "com.google.common.util.concurrent.ImmediateFuture", "addListener", sb.toString(), e);
        }
    }

    public final Object get(long j, TimeUnit timeUnit) {
        ife.m12898e((Object) timeUnit);
        return this.f13953c;
    }

    public final String toString() {
        String obj = super.toString();
        String valueOf = String.valueOf(this.f13953c);
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 27 + String.valueOf(valueOf).length());
        sb.append(obj);
        sb.append("[status=SUCCESS, result=[");
        sb.append(valueOf);
        sb.append("]]");
        return sb.toString();
    }
}
