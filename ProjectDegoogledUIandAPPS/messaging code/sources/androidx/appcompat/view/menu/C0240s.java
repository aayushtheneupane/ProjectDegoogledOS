package androidx.appcompat.view.menu;

import androidx.core.view.ActionProvider;

/* renamed from: androidx.appcompat.view.menu.s */
class C0240s implements ActionProvider.VisibilityListener {
    final /* synthetic */ C0241t this$0;

    C0240s(C0241t tVar) {
        this.this$0 = tVar;
    }

    public void onActionProviderVisibilityChanged(boolean z) {
        C0241t tVar = this.this$0;
        tVar.mMenu.mo1600d(tVar);
    }
}
