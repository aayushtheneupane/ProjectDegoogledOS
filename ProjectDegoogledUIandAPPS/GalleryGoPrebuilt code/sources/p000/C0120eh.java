package p000;

import java.util.ArrayList;

/* renamed from: eh */
/* compiled from: PG */
public final class C0120eh {

    /* renamed from: a */
    public int f8254a;

    /* renamed from: b */
    public int f8255b;

    /* renamed from: c */
    public int f8256c;

    /* renamed from: d */
    public int f8257d;

    /* renamed from: e */
    public ArrayList f8258e = new ArrayList();

    public C0120eh(C0116ed edVar) {
        this.f8254a = edVar.f8006t;
        this.f8255b = edVar.f8007u;
        this.f8256c = edVar.mo4699c();
        this.f8257d = edVar.mo4706f();
        ArrayList arrayList = edVar.f8000n;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            this.f8258e.add(new C0119eg((C0115ec) arrayList.get(i)));
        }
    }
}
