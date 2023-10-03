package androidx.fragment.app;

import android.graphics.Rect;
import android.view.View;
import p000a.p005b.C0015b;

/* renamed from: androidx.fragment.app.S */
final class C0399S implements Runnable {

    /* renamed from: dp */
    final /* synthetic */ C0416ea f370dp;

    /* renamed from: fp */
    final /* synthetic */ C0424j f371fp;

    /* renamed from: jp */
    final /* synthetic */ C0424j f372jp;

    /* renamed from: kp */
    final /* synthetic */ boolean f373kp;

    /* renamed from: lp */
    final /* synthetic */ C0015b f374lp;

    /* renamed from: mp */
    final /* synthetic */ View f375mp;
    final /* synthetic */ Rect val$epicenter;

    C0399S(C0424j jVar, C0424j jVar2, boolean z, C0015b bVar, View view, C0416ea eaVar, Rect rect) {
        this.f371fp = jVar;
        this.f372jp = jVar2;
        this.f373kp = z;
        this.f374lp = bVar;
        this.f375mp = view;
        this.f370dp = eaVar;
        this.val$epicenter = rect;
    }

    public void run() {
        C0402V.m373a(this.f371fp, this.f372jp, this.f373kp, this.f374lp, false);
        View view = this.f375mp;
        if (view != null) {
            this.f370dp.mo4233a(view, this.val$epicenter);
        }
    }
}
