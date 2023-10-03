package androidx.appcompat.view.menu;

import androidx.appcompat.widget.ForwardingListener;

/* renamed from: androidx.appcompat.view.menu.b */
class C0223b extends ForwardingListener {
    final /* synthetic */ ActionMenuItemView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0223b(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.this$0 = actionMenuItemView;
    }

    public C0216I getPopup() {
        C0224c cVar = this.this$0.mPopupCallback;
        if (cVar != null) {
            return cVar.getPopup();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean onForwardingStarted() {
        ActionMenuItemView actionMenuItemView = this.this$0;
        C0237p pVar = actionMenuItemView.f197_j;
        if (pVar == null || !pVar.invokeItem(actionMenuItemView.f196Sg)) {
            return false;
        }
        C0224c cVar = this.this$0.mPopupCallback;
        C0216I popup = cVar != null ? cVar.getPopup() : null;
        if (popup == null || !popup.isShowing()) {
            return false;
        }
        return true;
    }
}
