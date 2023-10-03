package p000;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/* renamed from: flk */
/* compiled from: PG */
final class flk implements RejectedExecutionHandler {
    private flk() {
    }

    public /* synthetic */ flk(byte[] bArr) {
    }

    public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        String valueOf = String.valueOf(runnable);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30);
        sb.append("Service rejected execution of ");
        sb.append(valueOf);
        flw.m9199b("PrimesExecutors", sb.toString(), new Object[0]);
    }
}
