package p000;

import java.util.concurrent.ThreadFactory;

/* renamed from: bgz */
/* compiled from: PG */
final class bgz implements ThreadFactory {
    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "FJD.ExecutionDelegator gms-bg-executor");
    }
}
