package p000;

import java.util.concurrent.TimeoutException;

/* renamed from: iez */
/* compiled from: PG */
final class iez extends TimeoutException {
    public /* synthetic */ iez(String str) {
        super(str);
    }

    public final synchronized Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
