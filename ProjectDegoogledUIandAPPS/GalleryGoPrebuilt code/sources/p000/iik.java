package p000;

/* renamed from: iik */
/* compiled from: PG */
final class iik {

    /* renamed from: a */
    public static final imi f14252a = new imi((byte[]) null);

    /* renamed from: b */
    private static final imi f14253b;

    static {
        imi imi = null;
        try {
            imi = (imi) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
        }
        f14253b = imi;
    }

    /* renamed from: a */
    static imi m13504a() {
        imi imi = f14253b;
        if (imi != null) {
            return imi;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
