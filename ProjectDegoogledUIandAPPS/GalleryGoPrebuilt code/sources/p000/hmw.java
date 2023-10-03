package p000;

import android.os.Build;
import java.util.ArrayList;

/* renamed from: hmw */
/* compiled from: PG */
public final class hmw extends RuntimeException {
    public hmw(String str, Throwable th, StackTraceElement[] stackTraceElementArr) {
        super(str, th);
        setStackTrace(stackTraceElementArr);
    }

    public final synchronized Throwable fillInStackTrace() {
        return this;
    }

    /* renamed from: a */
    public static Throwable m11758a(Throwable th) {
        int i = Build.VERSION.SDK_INT;
        ifn.m12935a(th, (Throwable) m11757a());
        return th;
    }

    /* renamed from: a */
    public static RuntimeException m11757a() {
        return new hmw("", (Throwable) null, m11759a(hnb.m11769a()));
    }

    /* renamed from: a */
    private static StackTraceElement[] m11759a(hlp hlp) {
        ArrayList arrayList = new ArrayList();
        while (hlp != null) {
            arrayList.add(new StackTraceElement("tk_trace", hlp.mo7509c(), (String) null, 0));
            hlp = hlp.mo7507a();
        }
        return (StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]);
    }

    /* renamed from: b */
    public static void m11760b(Throwable th) {
        throw new hmw("", th, m11759a(hnb.m11769a()));
    }
}
