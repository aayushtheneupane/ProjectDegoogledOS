package p000;

import android.view.MenuItem;

/* renamed from: qx */
/* compiled from: PG */
public final class C0462qx implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0464qz f15701a;

    /* renamed from: b */
    private final /* synthetic */ MenuItem f15702b;

    /* renamed from: c */
    private final /* synthetic */ C0472rg f15703c;

    /* renamed from: d */
    private final /* synthetic */ C0463qy f15704d;

    public C0462qx(C0463qy qyVar, C0464qz qzVar, MenuItem menuItem, C0472rg rgVar) {
        this.f15704d = qyVar;
        this.f15701a = qzVar;
        this.f15702b = menuItem;
        this.f15703c = rgVar;
    }

    public final void run() {
        C0464qz qzVar = this.f15701a;
        if (qzVar != null) {
            this.f15704d.f15705a.f15714f = true;
            qzVar.f15707b.mo9835a(false);
            this.f15704d.f15705a.f15714f = false;
        }
        if (this.f15702b.isEnabled() && this.f15702b.hasSubMenu()) {
            this.f15703c.mo9836a(this.f15702b, 4);
        }
    }
}
