package android.support.p002v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.app.ActionBar;
import android.support.p002v7.view.WindowCallbackWrapper;
import android.support.p002v7.view.menu.MenuBuilder;
import android.support.p002v7.view.menu.MenuPresenter;
import android.support.p002v7.widget.DecorToolbar;
import android.support.p002v7.widget.Toolbar;
import android.support.p002v7.widget.ToolbarWidgetWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import java.util.ArrayList;

/* renamed from: android.support.v7.app.ToolbarActionBar */
class ToolbarActionBar extends ActionBar {
    DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    private boolean mMenuCallbackSet;
    private final Toolbar.OnMenuItemClickListener mMenuClicker = new Toolbar.OnMenuItemClickListener() {
        public boolean onMenuItemClick(MenuItem menuItem) {
            return ToolbarActionBar.this.mWindowCallback.onMenuItemSelected(0, menuItem);
        }
    };
    private final Runnable mMenuInvalidator = new Runnable() {
        public void run() {
            ToolbarActionBar.this.populateOptionsMenu();
        }
    };
    private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners = new ArrayList<>();
    boolean mToolbarMenuPrepared;
    Window.Callback mWindowCallback;

    /* renamed from: android.support.v7.app.ToolbarActionBar$ActionMenuPresenterCallback */
    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        private boolean mClosingActionMenu;

        ActionMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (!this.mClosingActionMenu) {
                this.mClosingActionMenu = true;
                ((ToolbarWidgetWrapper) ToolbarActionBar.this.mDecorToolbar).dismissPopupMenus();
                Window.Callback callback = ToolbarActionBar.this.mWindowCallback;
                if (callback != null) {
                    callback.onPanelClosed(108, menuBuilder);
                }
                this.mClosingActionMenu = false;
            }
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback callback = ToolbarActionBar.this.mWindowCallback;
            if (callback == null) {
                return false;
            }
            callback.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    /* renamed from: android.support.v7.app.ToolbarActionBar$MenuBuilderCallback */
    private final class MenuBuilderCallback implements MenuBuilder.Callback {
        MenuBuilderCallback() {
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            return false;
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            ToolbarActionBar toolbarActionBar = ToolbarActionBar.this;
            if (toolbarActionBar.mWindowCallback == null) {
                return;
            }
            if (((ToolbarWidgetWrapper) toolbarActionBar.mDecorToolbar).isOverflowMenuShowing()) {
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(108, menuBuilder);
            } else if (ToolbarActionBar.this.mWindowCallback.onPreparePanel(0, (View) null, menuBuilder)) {
                ToolbarActionBar.this.mWindowCallback.onMenuOpened(108, menuBuilder);
            }
        }
    }

    /* renamed from: android.support.v7.app.ToolbarActionBar$ToolbarCallbackWrapper */
    private class ToolbarCallbackWrapper extends WindowCallbackWrapper {
        public ToolbarCallbackWrapper(Window.Callback callback) {
            super(callback);
        }

        public View onCreatePanelView(int i) {
            if (i == 0) {
                return new View(((ToolbarWidgetWrapper) ToolbarActionBar.this.mDecorToolbar).getContext());
            }
            return super.onCreatePanelView(i);
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (onPreparePanel) {
                ToolbarActionBar toolbarActionBar = ToolbarActionBar.this;
                if (!toolbarActionBar.mToolbarMenuPrepared) {
                    ((ToolbarWidgetWrapper) toolbarActionBar.mDecorToolbar).setMenuPrepared();
                    ToolbarActionBar.this.mToolbarMenuPrepared = true;
                }
            }
            return onPreparePanel;
        }
    }

    ToolbarActionBar(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        this.mDecorToolbar = new ToolbarWidgetWrapper(toolbar, false);
        this.mWindowCallback = new ToolbarCallbackWrapper(callback);
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setWindowCallback(this.mWindowCallback);
        toolbar.setOnMenuItemClickListener(this.mMenuClicker);
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setWindowTitle(charSequence);
    }

    private Menu getMenu() {
        if (!this.mMenuCallbackSet) {
            ((ToolbarWidgetWrapper) this.mDecorToolbar).setMenuCallbacks(new ActionMenuPresenterCallback(), new MenuBuilderCallback());
            this.mMenuCallbackSet = true;
        }
        return ((ToolbarWidgetWrapper) this.mDecorToolbar).getMenu();
    }

    public boolean closeOptionsMenu() {
        return ((ToolbarWidgetWrapper) this.mDecorToolbar).hideOverflowMenu();
    }

    public boolean collapseActionView() {
        if (!((ToolbarWidgetWrapper) this.mDecorToolbar).hasExpandedActionView()) {
            return false;
        }
        ((ToolbarWidgetWrapper) this.mDecorToolbar).collapseActionView();
        return true;
    }

    public void dispatchMenuVisibilityChanged(boolean z) {
        if (z != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = z;
            int size = this.mMenuVisibilityListeners.size();
            for (int i = 0; i < size; i++) {
                this.mMenuVisibilityListeners.get(i).onMenuVisibilityChanged(z);
            }
        }
    }

    public View getCustomView() {
        return ((ToolbarWidgetWrapper) this.mDecorToolbar).getCustomView();
    }

    public int getDisplayOptions() {
        return ((ToolbarWidgetWrapper) this.mDecorToolbar).getDisplayOptions();
    }

    public Context getThemedContext() {
        return ((ToolbarWidgetWrapper) this.mDecorToolbar).getContext();
    }

    public boolean invalidateOptionsMenu() {
        ((ToolbarWidgetWrapper) this.mDecorToolbar).getViewGroup().removeCallbacks(this.mMenuInvalidator);
        ViewCompat.postOnAnimation(((ToolbarWidgetWrapper) this.mDecorToolbar).getViewGroup(), this.mMenuInvalidator);
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    /* access modifiers changed from: package-private */
    public void onDestroy() {
        ((ToolbarWidgetWrapper) this.mDecorToolbar).getViewGroup().removeCallbacks(this.mMenuInvalidator);
    }

    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        Menu menu = getMenu();
        if (menu == null) {
            return false;
        }
        boolean z = true;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
            z = false;
        }
        menu.setQwertyMode(z);
        return menu.performShortcut(i, keyEvent, 0);
    }

    public boolean onMenuKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            ((ToolbarWidgetWrapper) this.mDecorToolbar).showOverflowMenu();
        }
        return true;
    }

    public boolean openOptionsMenu() {
        return ((ToolbarWidgetWrapper) this.mDecorToolbar).showOverflowMenu();
    }

    /* access modifiers changed from: package-private */
    public void populateOptionsMenu() {
        Menu menu = getMenu();
        MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
        if (menuBuilder != null) {
            menuBuilder.stopDispatchingItemsChanged();
        }
        try {
            menu.clear();
            if (!this.mWindowCallback.onCreatePanelMenu(0, menu) || !this.mWindowCallback.onPreparePanel(0, (View) null, menu)) {
                menu.clear();
            }
        } finally {
            if (menuBuilder != null) {
                menuBuilder.startDispatchingItemsChanged();
            }
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setBackgroundDrawable(drawable);
    }

    public void setCustomView(int i) {
        View inflate = LayoutInflater.from(((ToolbarWidgetWrapper) this.mDecorToolbar).getContext()).inflate(i, ((ToolbarWidgetWrapper) this.mDecorToolbar).getViewGroup(), false);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(-2, -2);
        if (inflate != null) {
            inflate.setLayoutParams(layoutParams);
        }
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setCustomView(inflate);
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
    }

    public void setDisplayHomeAsUpEnabled(boolean z) {
        setDisplayOptions(z ? 4 : 0, 4);
    }

    public void setDisplayOptions(int i, int i2) {
        int displayOptions = ((ToolbarWidgetWrapper) this.mDecorToolbar).getDisplayOptions();
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setDisplayOptions((i & i2) | ((~i2) & displayOptions));
    }

    public void setDisplayShowCustomEnabled(boolean z) {
        setDisplayOptions(z ? 16 : 0, 16);
    }

    public void setDisplayShowHomeEnabled(boolean z) {
        setDisplayOptions(z ? 2 : 0, 2);
    }

    public void setDisplayShowTitleEnabled(boolean z) {
        setDisplayOptions(z ? 8 : 0, 8);
    }

    public void setElevation(float f) {
        ViewCompat.setElevation(((ToolbarWidgetWrapper) this.mDecorToolbar).getViewGroup(), f);
    }

    public void setLogo(Drawable drawable) {
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setLogo(drawable);
    }

    public void setShowHideAnimationEnabled(boolean z) {
    }

    public void setTitle(CharSequence charSequence) {
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setTitle(charSequence);
    }

    public void setWindowTitle(CharSequence charSequence) {
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setWindowTitle(charSequence);
    }

    public void setTitle(int i) {
        DecorToolbar decorToolbar = this.mDecorToolbar;
        ((ToolbarWidgetWrapper) decorToolbar).setTitle(i != 0 ? ((ToolbarWidgetWrapper) decorToolbar).getContext().getText(i) : null);
    }
}
