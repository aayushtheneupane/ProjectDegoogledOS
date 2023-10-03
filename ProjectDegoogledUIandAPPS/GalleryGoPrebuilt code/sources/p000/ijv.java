package p000;

/* renamed from: ijv */
/* compiled from: PG */
final class ijv implements ikz {

    /* renamed from: b */
    private static final ikd f14361b = new ijt();

    /* renamed from: a */
    public final ikd f14362a;

    public ijv() {
        ikd ikd;
        ikd[] ikdArr = new ikd[2];
        ikdArr[0] = iiq.f14316a;
        try {
            ikd = (ikd) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception e) {
            ikd = f14361b;
        }
        ikdArr[1] = ikd;
        this.f14362a = (ikd) ijf.m13648a((Object) new iju(ikdArr), "messageInfoFactory");
    }

    /* renamed from: a */
    public static boolean m13698a(ikc ikc) {
        return ikc.mo8861c() == 1;
    }
}
