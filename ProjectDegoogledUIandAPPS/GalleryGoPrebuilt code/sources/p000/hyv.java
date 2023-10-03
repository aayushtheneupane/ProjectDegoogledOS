package p000;

import java.lang.reflect.InvocationTargetException;

/* renamed from: hyv */
/* compiled from: PG */
public final class hyv {

    /* renamed from: a */
    private static final hyw f13663a = hyw.m12485a();

    /* renamed from: a */
    public static StackTraceElement m12483a(Class cls, Throwable th, int i) {
        StackTraceElement stackTraceElement;
        ife.m12827a((Object) cls, "target");
        ife.m12827a((Object) th, "throwable");
        if (i >= 0) {
            StackTraceElement[] stackTrace = f13663a == null ? th.getStackTrace() : null;
            boolean z = false;
            while (true) {
                try {
                    if (f13663a != null) {
                        stackTraceElement = f13663a.mo8278a(th, i);
                    } else {
                        stackTraceElement = stackTrace[i];
                    }
                    if (cls.getName().equals(stackTraceElement.getClassName())) {
                        z = true;
                    } else if (z) {
                        return stackTraceElement;
                    }
                    i++;
                } catch (Exception e) {
                    return null;
                }
            }
        } else {
            StringBuilder sb = new StringBuilder(42);
            sb.append("skip count cannot be negative: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* renamed from: b */
    public static StackTraceElement[] m12484b(Class cls, Throwable th, int i) {
        int i2;
        StackTraceElement[] stackTraceElementArr;
        StackTraceElement stackTraceElement;
        ife.m12827a((Object) cls, "target");
        ife.m12827a((Object) th, "throwable");
        if (i <= 0 && i != -1) {
            StringBuilder sb = new StringBuilder(34);
            sb.append("invalid maximum depth: 0");
            throw new IllegalArgumentException(sb.toString());
        }
        hyw hyw = f13663a;
        if (hyw == null) {
            stackTraceElementArr = th.getStackTrace();
            i2 = stackTraceElementArr.length;
        } else {
            try {
                i2 = ((Integer) hyw.f13665b.invoke(hyw.f13664a, new Object[]{th})).intValue();
                stackTraceElementArr = null;
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof RuntimeException) {
                    throw ((RuntimeException) e.getCause());
                } else if (e.getCause() instanceof Error) {
                    throw ((Error) e.getCause());
                } else {
                    throw new RuntimeException(e.getCause());
                }
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
        boolean z = false;
        for (int i3 = 0; i3 < i2; i3++) {
            hyw hyw2 = f13663a;
            StackTraceElement a = hyw2 != null ? hyw2.mo8278a(th, i3) : stackTraceElementArr[i3];
            if (cls.getName().equals(a.getClassName())) {
                z = true;
            } else if (z) {
                int i4 = i2 - i3;
                if (i <= 0 || i >= i4) {
                    i = i4;
                }
                StackTraceElement[] stackTraceElementArr2 = new StackTraceElement[i];
                stackTraceElementArr2[0] = a;
                for (int i5 = 1; i5 < i; i5++) {
                    hyw hyw3 = f13663a;
                    if (hyw3 != null) {
                        stackTraceElement = hyw3.mo8278a(th, i3 + i5);
                    } else {
                        stackTraceElement = stackTraceElementArr[i3 + i5];
                    }
                    stackTraceElementArr2[i5] = stackTraceElement;
                }
                return stackTraceElementArr2;
            }
        }
        return new StackTraceElement[0];
    }
}
