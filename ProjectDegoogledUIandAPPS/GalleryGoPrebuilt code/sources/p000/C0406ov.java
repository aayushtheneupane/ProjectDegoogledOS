package p000;

import android.view.Menu;

/* renamed from: ov */
/* compiled from: PG */
final class C0406ov implements C0442qd {

    /* renamed from: a */
    public final /* synthetic */ C0416pe f15426a;

    /* renamed from: b */
    private final C0442qd f15427b;

    public C0406ov(C0416pe peVar, C0442qd qdVar) {
        this.f15426a = peVar;
        this.f15427b = qdVar;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [jf, android.view.MenuItem] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo9578a(p000.C0443qe r4, android.view.MenuItem r5) {
        /*
            r3 = this;
            qd r0 = r3.f15427b
            qi r0 = (p000.C0447qi) r0
            android.view.ActionMode$Callback r1 = r0.f15615a
            android.view.ActionMode r4 = r0.mo9702b(r4)
            rp r2 = new rp
            android.content.Context r0 = r0.f15616b
            r2.<init>(r0, r5)
            boolean r4 = r1.onActionItemClicked(r4, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0406ov.mo9578a(qe, android.view.MenuItem):boolean");
    }

    /* renamed from: a */
    public final boolean mo9577a(C0443qe qeVar, Menu menu) {
        C0447qi qiVar = (C0447qi) this.f15427b;
        return qiVar.f15615a.onCreateActionMode(qiVar.mo9702b(qeVar), qiVar.mo9701a(menu));
    }

    /* renamed from: a */
    public final void mo9576a(C0443qe qeVar) {
        C0447qi qiVar = (C0447qi) this.f15427b;
        qiVar.f15615a.onDestroyActionMode(qiVar.mo9702b(qeVar));
        C0416pe peVar = this.f15426a;
        if (peVar.f15491j != null) {
            peVar.f15486e.getDecorView().removeCallbacks(this.f15426a.f15492k);
        }
        C0416pe peVar2 = this.f15426a;
        if (peVar2.f15490i != null) {
            peVar2.mo9616s();
            C0416pe peVar3 = this.f15426a;
            C0344mn k = C0340mj.m14720k(peVar3.f15490i);
            k.mo9400a(0.0f);
            peVar3.f15493l = k;
            this.f15426a.f15493l.mo9402a((C0345mo) new C0405ou(this));
        }
        C0416pe peVar4 = this.f15426a;
        C0396ol olVar = peVar4.f15487f;
        if (olVar != null) {
            olVar.mo6273a(peVar4.f15489h);
        }
        this.f15426a.f15489h = null;
    }

    /* renamed from: b */
    public final void mo9579b(C0443qe qeVar, Menu menu) {
        C0447qi qiVar = (C0447qi) this.f15427b;
        qiVar.f15615a.onPrepareActionMode(qiVar.mo9702b(qeVar), qiVar.mo9701a(menu));
    }
}
