package p000;

import android.content.Context;
import android.view.View;

/* renamed from: sr */
/* compiled from: PG */
final class C0510sr extends C0484rs {

    /* renamed from: d */
    private final /* synthetic */ C0512st f15877d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0510sr(C0512st stVar, Context context, C0472rg rgVar, View view) {
        super(context, rgVar, view, true);
        this.f15877d = stVar;
        this.f15819b = 8388613;
        mo9997a((C0485rt) stVar.f15884l);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final void mo10001d() {
        C0472rg rgVar = this.f15877d.f15692c;
        if (rgVar != null) {
            rgVar.close();
        }
        this.f15877d.f15881i = null;
        super.mo10001d();
    }
}
