package p000;

import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: idj */
/* compiled from: PG */
public final class idj {

    /* renamed from: c */
    private static final Logger f13923c = Logger.getLogger(idj.class.getName());

    /* renamed from: a */
    public idi f13924a;

    /* renamed from: b */
    public boolean f13925b;

    /* renamed from: a */
    public static void m12727a(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = f13923c;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 57 + String.valueOf(valueOf2).length());
            sb.append("RuntimeException while executing runnable ");
            sb.append(valueOf);
            sb.append(" with executor ");
            sb.append(valueOf2);
            logger.logp(level, "com.google.common.util.concurrent.ExecutionList", "executeListener", sb.toString(), e);
        }
    }
}
