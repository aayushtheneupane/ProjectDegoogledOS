package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.SubMenu;
import android.view.View;
import androidx.core.view.ActionProvider;

/* renamed from: androidx.appcompat.view.menu.u */
class C0242u extends ActionProvider {
    final /* synthetic */ C0247z this$0;

    /* renamed from: ye */
    final android.view.ActionProvider f301ye;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0242u(C0247z zVar, Context context, android.view.ActionProvider actionProvider) {
        super(context);
        this.this$0 = zVar;
        this.f301ye = actionProvider;
    }

    public boolean hasSubMenu() {
        return this.f301ye.hasSubMenu();
    }

    public View onCreateActionView() {
        return this.f301ye.onCreateActionView();
    }

    public boolean onPerformDefaultAction() {
        return this.f301ye.onPerformDefaultAction();
    }

    public void onPrepareSubMenu(SubMenu subMenu) {
        this.f301ye.onPrepareSubMenu(this.this$0.mo1552a(subMenu));
    }
}
