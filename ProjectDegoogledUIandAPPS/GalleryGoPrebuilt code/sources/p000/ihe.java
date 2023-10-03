package p000;

/* renamed from: ihe */
/* compiled from: PG */
final class ihe {

    /* renamed from: a */
    public static final Class f14176a = m13010a("libcore.io.Memory");

    /* renamed from: b */
    private static final boolean f14177b = (m13010a("org.robolectric.Robolectric") != null);

    /* renamed from: a */
    static boolean m13011a() {
        return f14176a != null && !f14177b;
    }

    /* renamed from: a */
    private static Class m13010a(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }
}
