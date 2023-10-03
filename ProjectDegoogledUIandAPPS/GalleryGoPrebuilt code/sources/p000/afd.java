package p000;

import java.util.ArrayList;

/* renamed from: afd */
/* compiled from: PG */
final class afd extends afm {

    /* renamed from: a */
    private final /* synthetic */ Object f302a;

    /* renamed from: b */
    private final /* synthetic */ ArrayList f303b;

    /* renamed from: c */
    private final /* synthetic */ Object f304c;

    /* renamed from: d */
    private final /* synthetic */ ArrayList f305d;

    /* renamed from: e */
    private final /* synthetic */ Object f306e;

    /* renamed from: f */
    private final /* synthetic */ ArrayList f307f;

    /* renamed from: g */
    private final /* synthetic */ afg f308g;

    public afd(afg afg, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
        this.f308g = afg;
        this.f302a = obj;
        this.f303b = arrayList;
        this.f304c = obj2;
        this.f305d = arrayList2;
        this.f306e = obj3;
        this.f307f = arrayList3;
    }

    /* renamed from: a */
    public final void mo265a(afl afl) {
        afl.mo312b((afk) this);
    }

    /* renamed from: b */
    public final void mo278b(afl afl) {
        Object obj = this.f302a;
        if (obj != null) {
            this.f308g.mo293b(obj, this.f303b, (ArrayList) null);
        }
        Object obj2 = this.f304c;
        if (obj2 != null) {
            this.f308g.mo293b(obj2, this.f305d, (ArrayList) null);
        }
        Object obj3 = this.f306e;
        if (obj3 != null) {
            this.f308g.mo293b(obj3, this.f307f, (ArrayList) null);
        }
    }
}
