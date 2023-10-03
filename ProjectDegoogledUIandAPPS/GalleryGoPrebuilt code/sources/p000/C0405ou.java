package p000;

import android.view.View;
import android.widget.PopupWindow;

/* renamed from: ou */
/* compiled from: PG */
final class C0405ou extends C0346mp {

    /* renamed from: a */
    private final /* synthetic */ C0406ov f15425a;

    public C0405ou(C0406ov ovVar) {
        this.f15425a = ovVar;
    }

    /* renamed from: b */
    public final void mo9406b() {
        this.f15425a.f15426a.f15490i.setVisibility(8);
        C0416pe peVar = this.f15425a.f15426a;
        PopupWindow popupWindow = peVar.f15491j;
        if (popupWindow != null) {
            popupWindow.dismiss();
        } else if (peVar.f15490i.getParent() instanceof View) {
            C0340mj.m14724o((View) this.f15425a.f15426a.f15490i.getParent());
        }
        this.f15425a.f15426a.f15490i.removeAllViews();
        this.f15425a.f15426a.f15493l.mo9402a((C0345mo) null);
        this.f15425a.f15426a.f15493l = null;
    }
}
