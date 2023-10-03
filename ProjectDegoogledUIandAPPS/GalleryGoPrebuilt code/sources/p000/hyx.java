package p000;

/* renamed from: hyx */
/* compiled from: PG */
public final class hyx extends hwg {

    /* renamed from: b */
    private final StackTraceElement f13667b;

    public hyx(StackTraceElement stackTraceElement) {
        this.f13667b = (StackTraceElement) ife.m12827a((Object) stackTraceElement, "stack element");
    }

    public final boolean equals(Object obj) {
        return (obj instanceof hyx) && this.f13667b.equals(((hyx) obj).f13667b);
    }

    /* renamed from: a */
    public final String mo8219a() {
        return this.f13667b.getClassName();
    }

    /* renamed from: d */
    public final String mo8222d() {
        return this.f13667b.getFileName();
    }

    /* renamed from: c */
    public final int mo8221c() {
        return Math.max(this.f13667b.getLineNumber(), 0);
    }

    /* renamed from: b */
    public final String mo8220b() {
        return this.f13667b.getMethodName();
    }

    public final int hashCode() {
        return this.f13667b.hashCode();
    }
}
