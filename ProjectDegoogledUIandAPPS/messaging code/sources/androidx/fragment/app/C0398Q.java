package androidx.fragment.app;

import android.view.View;
import java.util.ArrayList;

/* renamed from: androidx.fragment.app.Q */
final class C0398Q implements Runnable {

    /* renamed from: bp */
    final /* synthetic */ ArrayList f362bp;

    /* renamed from: cp */
    final /* synthetic */ Object f363cp;

    /* renamed from: dp */
    final /* synthetic */ C0416ea f364dp;

    /* renamed from: ep */
    final /* synthetic */ View f365ep;

    /* renamed from: fp */
    final /* synthetic */ C0424j f366fp;

    /* renamed from: gp */
    final /* synthetic */ ArrayList f367gp;

    /* renamed from: hp */
    final /* synthetic */ ArrayList f368hp;

    /* renamed from: ip */
    final /* synthetic */ Object f369ip;

    C0398Q(Object obj, C0416ea eaVar, View view, C0424j jVar, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
        this.f363cp = obj;
        this.f364dp = eaVar;
        this.f365ep = view;
        this.f366fp = jVar;
        this.f367gp = arrayList;
        this.f368hp = arrayList2;
        this.f362bp = arrayList3;
        this.f369ip = obj2;
    }

    public void run() {
        Object obj = this.f363cp;
        if (obj != null) {
            this.f364dp.mo4214a(obj, this.f365ep);
            this.f368hp.addAll(C0402V.m369a(this.f364dp, this.f363cp, this.f366fp, this.f367gp, this.f365ep));
        }
        if (this.f362bp != null) {
            if (this.f369ip != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.f365ep);
                this.f364dp.mo4218a(this.f369ip, this.f362bp, arrayList);
            }
            this.f362bp.clear();
            this.f362bp.add(this.f365ep);
        }
    }
}
