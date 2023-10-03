package androidx.fragment.app;

import android.graphics.Rect;
import android.view.View;
import java.util.ArrayList;
import p000a.p005b.C0015b;

/* renamed from: androidx.fragment.app.T */
final class C0400T implements Runnable {

    /* renamed from: cp */
    final /* synthetic */ Object f376cp;

    /* renamed from: dp */
    final /* synthetic */ C0416ea f377dp;

    /* renamed from: ep */
    final /* synthetic */ View f378ep;

    /* renamed from: fp */
    final /* synthetic */ C0424j f379fp;

    /* renamed from: gp */
    final /* synthetic */ ArrayList f380gp;

    /* renamed from: jp */
    final /* synthetic */ C0424j f381jp;

    /* renamed from: kp */
    final /* synthetic */ boolean f382kp;

    /* renamed from: np */
    final /* synthetic */ C0015b f383np;

    /* renamed from: qp */
    final /* synthetic */ Object f384qp;

    /* renamed from: rp */
    final /* synthetic */ C0401U f385rp;

    /* renamed from: sp */
    final /* synthetic */ ArrayList f386sp;

    /* renamed from: tp */
    final /* synthetic */ Rect f387tp;

    C0400T(C0416ea eaVar, C0015b bVar, Object obj, C0401U u, ArrayList arrayList, View view, C0424j jVar, C0424j jVar2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
        this.f377dp = eaVar;
        this.f383np = bVar;
        this.f384qp = obj;
        this.f385rp = u;
        this.f380gp = arrayList;
        this.f378ep = view;
        this.f379fp = jVar;
        this.f381jp = jVar2;
        this.f382kp = z;
        this.f386sp = arrayList2;
        this.f376cp = obj2;
        this.f387tp = rect;
    }

    public void run() {
        C0015b a = C0402V.m362a(this.f377dp, this.f383np, this.f384qp, this.f385rp);
        if (a != null) {
            this.f380gp.addAll(a.values());
            this.f380gp.add(this.f378ep);
        }
        C0402V.m373a(this.f379fp, this.f381jp, this.f382kp, a, false);
        Object obj = this.f384qp;
        if (obj != null) {
            this.f377dp.mo4222b(obj, this.f386sp, this.f380gp);
            View a2 = C0402V.m363a(a, this.f385rp, this.f376cp, this.f382kp);
            if (a2 != null) {
                this.f377dp.mo4233a(a2, this.f387tp);
            }
        }
    }
}
