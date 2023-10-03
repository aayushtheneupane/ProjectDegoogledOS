package p000;

import android.graphics.Rect;
import android.view.View;
import java.util.ArrayList;

/* renamed from: gt */
/* compiled from: PG */
final class C0188gt implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0202hg f11989a;

    /* renamed from: b */
    private final /* synthetic */ C0290kn f11990b;

    /* renamed from: c */
    private final /* synthetic */ Object f11991c;

    /* renamed from: d */
    private final /* synthetic */ C0190gv f11992d;

    /* renamed from: e */
    private final /* synthetic */ ArrayList f11993e;

    /* renamed from: f */
    private final /* synthetic */ View f11994f;

    /* renamed from: g */
    private final /* synthetic */ boolean f11995g;

    /* renamed from: h */
    private final /* synthetic */ ArrayList f11996h;

    /* renamed from: i */
    private final /* synthetic */ Object f11997i;

    /* renamed from: j */
    private final /* synthetic */ Rect f11998j;

    public C0188gt(C0202hg hgVar, C0290kn knVar, Object obj, C0190gv gvVar, ArrayList arrayList, View view, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
        this.f11989a = hgVar;
        this.f11990b = knVar;
        this.f11991c = obj;
        this.f11992d = gvVar;
        this.f11993e = arrayList;
        this.f11994f = view;
        this.f11995g = z;
        this.f11996h = arrayList2;
        this.f11997i = obj2;
        this.f11998j = rect;
    }

    public final void run() {
        C0290kn a = C0191gw.m10917a(this.f11989a, this.f11990b, this.f11991c, this.f11992d);
        if (a != null) {
            this.f11993e.addAll(a.values());
            this.f11993e.add(this.f11994f);
        }
        Object obj = this.f11991c;
        if (obj != null) {
            this.f11989a.mo287a(obj, this.f11996h, this.f11993e);
            View a2 = C0191gw.m10912a(a, this.f11992d, this.f11997i, this.f11995g);
            if (a2 != null) {
                C0202hg.m11410a(a2, this.f11998j);
            }
        }
    }
}
