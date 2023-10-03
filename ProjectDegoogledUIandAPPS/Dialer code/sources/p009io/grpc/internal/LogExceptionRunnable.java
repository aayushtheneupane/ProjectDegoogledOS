package p009io.grpc.internal;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Throwables;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: io.grpc.internal.LogExceptionRunnable */
public final class LogExceptionRunnable implements Runnable {
    private static final Logger log = Logger.getLogger(LogExceptionRunnable.class.getName());
    private final Runnable task;

    public LogExceptionRunnable(Runnable runnable) {
        if (runnable != null) {
            this.task = runnable;
            return;
        }
        throw new NullPointerException();
    }

    public void run() {
        try {
            this.task.run();
        } catch (Throwable th) {
            Logger logger = log;
            Level level = Level.SEVERE;
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Exception while executing runnable ");
            outline13.append(this.task);
            logger.log(level, outline13.toString(), th);
            Throwables.propagateIfPossible(th);
            throw new AssertionError(th);
        }
    }

    public String toString() {
        return GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("LogExceptionRunnable("), this.task, ")");
    }
}
