package p000;

import java.util.List;

/* renamed from: hsn */
/* compiled from: PG */
final class hsn extends hso {

    /* renamed from: a */
    private final transient int f13349a;

    /* renamed from: b */
    private final transient int f13350b;

    /* renamed from: c */
    private final /* synthetic */ hso f13351c;

    public hsn(hso hso, int i, int i2) {
        this.f13351c = hso;
        this.f13349a = i;
        this.f13350b = i2;
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return true;
    }

    public final int size() {
        return this.f13350b;
    }

    public final Object get(int i) {
        ife.m12873b(i, this.f13350b);
        return this.f13351c.get(i + this.f13349a);
    }

    /* renamed from: b */
    public final Object[] mo7879b() {
        return this.f13351c.mo7879b();
    }

    /* renamed from: d */
    public final int mo7883d() {
        return this.f13351c.mo7880c() + this.f13349a + this.f13350b;
    }

    /* renamed from: c */
    public final int mo7880c() {
        return this.f13351c.mo7880c() + this.f13349a;
    }

    /* renamed from: a */
    public final hso mo7909a(int i, int i2) {
        ife.m12874b(i, i2, this.f13350b);
        hso hso = this.f13351c;
        int i3 = this.f13349a;
        return hso.subList(i + i3, i2 + i3);
    }

    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }
}
