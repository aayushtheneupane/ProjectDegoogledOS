package p000;

/* renamed from: hyi */
/* compiled from: PG */
public final class hyi extends hxf {

    /* renamed from: a */
    public static final hxf f13617a = new hyi();

    public final String toString() {
        return "Default stack-based caller finder";
    }

    private hyi() {
    }

    /* renamed from: a */
    public final hwg mo8244a(Class cls, int i) {
        StackTraceElement a = hyv.m12483a(cls, new Throwable(), i + 1);
        return a != null ? new hyx(a) : hwg.f13519a;
    }

    /* renamed from: a */
    public final String mo8245a(Class cls) {
        StackTraceElement a = hyv.m12483a(cls, new Throwable(), 1);
        if (a != null) {
            return a.getClassName();
        }
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() == 0 ? new String("no caller found on the stack for: ") : "no caller found on the stack for: ".concat(valueOf));
    }
}
