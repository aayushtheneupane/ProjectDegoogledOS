package p000;

/* renamed from: ci */
/* compiled from: PG */
final class C0065ci implements Comparable {

    /* renamed from: a */
    public final int f4446a;

    /* renamed from: b */
    public final String f4447b;

    /* renamed from: c */
    public final String f4448c;

    /* renamed from: d */
    private final int f4449d;

    public C0065ci(int i, int i2, String str, String str2) {
        this.f4446a = i;
        this.f4449d = i2;
        this.f4447b = str;
        this.f4448c = str2;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        C0065ci ciVar = (C0065ci) obj;
        int i = this.f4446a - ciVar.f4446a;
        return i == 0 ? this.f4449d - ciVar.f4449d : i;
    }
}
