package p000;

import android.view.View;
import java.util.ArrayList;

/* renamed from: gr */
/* compiled from: PG */
final class C0186gr implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ Object f11870a;

    /* renamed from: b */
    private final /* synthetic */ C0202hg f11871b;

    /* renamed from: c */
    private final /* synthetic */ View f11872c;

    /* renamed from: d */
    private final /* synthetic */ C0147fh f11873d;

    /* renamed from: e */
    private final /* synthetic */ ArrayList f11874e;

    /* renamed from: f */
    private final /* synthetic */ ArrayList f11875f;

    /* renamed from: g */
    private final /* synthetic */ ArrayList f11876g;

    /* renamed from: h */
    private final /* synthetic */ Object f11877h;

    public C0186gr(Object obj, C0202hg hgVar, View view, C0147fh fhVar, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
        this.f11870a = obj;
        this.f11871b = hgVar;
        this.f11872c = view;
        this.f11873d = fhVar;
        this.f11874e = arrayList;
        this.f11875f = arrayList2;
        this.f11876g = arrayList3;
        this.f11877h = obj2;
    }

    public final void run() {
        Object obj = this.f11870a;
        if (obj != null) {
            this.f11871b.mo295c(obj, this.f11872c);
            this.f11875f.addAll(C0191gw.m10916a(this.f11871b, this.f11870a, this.f11873d, this.f11874e, this.f11872c));
        }
        if (this.f11876g != null) {
            if (this.f11877h != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.f11872c);
                this.f11871b.mo293b(this.f11877h, this.f11876g, arrayList);
            }
            this.f11876g.clear();
            this.f11876g.add(this.f11872c);
        }
    }
}
