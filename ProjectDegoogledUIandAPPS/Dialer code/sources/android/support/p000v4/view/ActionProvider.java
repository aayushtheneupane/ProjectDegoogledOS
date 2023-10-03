package android.support.p000v4.view;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import com.android.tools.p006r8.GeneratedOutlineSupport;

/* renamed from: android.support.v4.view.ActionProvider */
public abstract class ActionProvider {
    private VisibilityListener mVisibilityListener;

    /* renamed from: android.support.v4.view.ActionProvider$SubUiVisibilityListener */
    public interface SubUiVisibilityListener {
    }

    /* renamed from: android.support.v4.view.ActionProvider$VisibilityListener */
    public interface VisibilityListener {
    }

    public ActionProvider(Context context) {
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isVisible() {
        return true;
    }

    public abstract View onCreateActionView();

    public View onCreateActionView(MenuItem menuItem) {
        return onCreateActionView();
    }

    public boolean overridesItemVisibility() {
        return false;
    }

    public void reset() {
        this.mVisibilityListener = null;
    }

    public void setVisibilityListener(VisibilityListener visibilityListener) {
        if (!(this.mVisibilityListener == null || visibilityListener == null)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this ");
            outline13.append(getClass().getSimpleName());
            outline13.append(" instance while it is still in use somewhere else?");
            Log.w("ActionProvider(support)", outline13.toString());
        }
        this.mVisibilityListener = visibilityListener;
    }
}
