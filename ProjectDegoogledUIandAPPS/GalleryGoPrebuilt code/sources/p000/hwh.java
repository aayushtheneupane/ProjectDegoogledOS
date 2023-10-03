package p000;

/* renamed from: hwh */
/* compiled from: PG */
public final class hwh extends Exception {
    public hwh(Throwable th, hwo hwo, StackTraceElement[] stackTraceElementArr) {
        super(hwo.toString(), th);
        setStackTrace(stackTraceElementArr);
    }

    public final Throwable fillInStackTrace() {
        return this;
    }
}
