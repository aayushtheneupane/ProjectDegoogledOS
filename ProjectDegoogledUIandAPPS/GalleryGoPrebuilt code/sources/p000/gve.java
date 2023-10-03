package p000;

/* renamed from: gve */
/* compiled from: PG */
abstract class gve {

    /* renamed from: a */
    public static final gvc f12120a = new gvd();

    /* renamed from: a */
    public abstract long mo7069a();

    /* renamed from: b */
    public abstract gvc mo7070b();

    /* renamed from: c */
    public abstract boolean mo7071c();

    /* renamed from: d */
    public abstract hpy mo7072d();

    /* renamed from: e */
    public abstract hpy mo7073e();

    /* renamed from: f */
    static gve m10877f() {
        return new gtz(0, f12120a, false, hph.f13219a, hph.f13219a);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final gve mo7109a(guo guo) {
        return new gtz(mo7069a(), mo7070b(), mo7071c(), hpy.m11893b(guo), hpy.m11893b(guo));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final gve mo7110a(boolean z) {
        boolean z2;
        ife.m12876b(mo7070b() instanceof gua, (Object) "Non-BackgroundFetchCallbacks shouldn't be mutating around background fetch callbacks.");
        if (z != mo7071c()) {
            z2 = true;
        } else {
            z2 = false;
        }
        ife.m12876b(z2, (Object) "Double-open or double-close on background fetch callbacks.");
        return new gtz(mo7069a(), mo7070b(), z, mo7072d(), mo7073e());
    }
}
