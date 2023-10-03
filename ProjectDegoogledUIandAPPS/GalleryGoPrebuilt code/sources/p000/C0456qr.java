package p000;

import android.support.p002v7.view.menu.ActionMenuItemView;

/* renamed from: qr */
/* compiled from: PG */
public final class C0456qr extends C0590vq {

    /* renamed from: c */
    private final /* synthetic */ ActionMenuItemView f15688c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0456qr(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.f15688c = actionMenuItemView;
    }

    /* renamed from: a */
    public final C0490ry mo9782a() {
        C0506sn snVar;
        C0457qs qsVar = this.f15688c.f860d;
        if (qsVar == null || (snVar = qsVar.f15689a.f15882j) == null) {
            return null;
        }
        return snVar.mo9995a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final boolean mo9783b() {
        C0490ry a;
        ActionMenuItemView actionMenuItemView = this.f15688c;
        C0471rf rfVar = actionMenuItemView.f859c;
        if (rfVar == null || !rfVar.mo775a(actionMenuItemView.f858b) || (a = mo9782a()) == null || !a.mo9811e()) {
            return false;
        }
        return true;
    }
}
