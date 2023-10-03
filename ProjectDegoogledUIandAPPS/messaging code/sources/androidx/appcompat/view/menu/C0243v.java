package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
import androidx.core.view.ActionProvider;

/* renamed from: androidx.appcompat.view.menu.v */
class C0243v extends C0242u implements ActionProvider.VisibilityListener {
    private ActionProvider.VisibilityListener mListener;

    C0243v(C0247z zVar, Context context, android.view.ActionProvider actionProvider) {
        super(zVar, context, actionProvider);
    }

    public boolean isVisible() {
        return this.f301ye.isVisible();
    }

    public void onActionProviderVisibilityChanged(boolean z) {
        ActionProvider.VisibilityListener visibilityListener = this.mListener;
        if (visibilityListener != null) {
            visibilityListener.onActionProviderVisibilityChanged(z);
        }
    }

    public View onCreateActionView(MenuItem menuItem) {
        return this.f301ye.onCreateActionView(menuItem);
    }

    public boolean overridesItemVisibility() {
        return this.f301ye.overridesItemVisibility();
    }

    public void refreshVisibility() {
        this.f301ye.refreshVisibility();
    }

    public void setVisibilityListener(ActionProvider.VisibilityListener visibilityListener) {
        this.mListener = visibilityListener;
        android.view.ActionProvider actionProvider = this.f301ye;
        if (visibilityListener == null) {
            this = null;
        }
        actionProvider.setVisibilityListener(this);
    }
}
