package p000;

import java.util.List;

/* renamed from: bjs */
/* compiled from: PG */
final class bjs implements dda {

    /* renamed from: a */
    private List f2648a;

    /* renamed from: b */
    private List f2649b;

    /* renamed from: c */
    private imq f2650c;

    /* renamed from: d */
    private final /* synthetic */ bjw f2651d;

    public /* synthetic */ bjs(bjw bjw) {
        this.f2651d = bjw;
    }

    /* renamed from: a */
    public final ddb mo2144a() {
        iol.m14233a((Object) this.f2648a, List.class);
        iol.m14233a((Object) this.f2649b, List.class);
        iol.m14233a((Object) this.f2650c, imq.class);
        return new bju(this.f2651d, this.f2648a, this.f2649b, this.f2650c);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ dda mo2143a(List list) {
        this.f2649b = (List) iol.m14228a((Object) list);
        return this;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ dda mo2145b(List list) {
        this.f2648a = (List) iol.m14228a((Object) list);
        return this;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ dda mo2142a(imq imq) {
        this.f2650c = (imq) iol.m14228a((Object) imq);
        return this;
    }
}
