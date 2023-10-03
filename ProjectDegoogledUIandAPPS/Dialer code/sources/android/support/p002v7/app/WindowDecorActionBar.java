package android.support.p002v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewPropertyAnimatorCompat;
import android.support.p000v4.view.ViewPropertyAnimatorListener;
import android.support.p000v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.p000v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.p002v7.app.ActionBar;
import android.support.p002v7.appcompat.R$styleable;
import android.support.p002v7.view.ActionBarPolicy;
import android.support.p002v7.view.ActionMode;
import android.support.p002v7.view.SupportMenuInflater;
import android.support.p002v7.view.ViewPropertyAnimatorCompatSet;
import android.support.p002v7.view.menu.MenuBuilder;
import android.support.p002v7.widget.ActionBarContainer;
import android.support.p002v7.widget.ActionBarContextView;
import android.support.p002v7.widget.ActionBarOverlayLayout;
import android.support.p002v7.widget.DecorToolbar;
import android.support.p002v7.widget.ScrollingTabContainerView;
import android.support.p002v7.widget.Toolbar;
import android.support.p002v7.widget.ToolbarWidgetWrapper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.android.dialer.R;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* renamed from: android.support.v7.app.WindowDecorActionBar */
public class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout.ActionBarVisibilityCallback {
    private static final Interpolator sHideInterpolator = new AccelerateInterpolator();
    private static final Interpolator sShowInterpolator = new DecelerateInterpolator();
    ActionModeImpl mActionMode;
    ActionBarContainer mContainerView;
    boolean mContentAnimations = true;
    View mContentView;
    Context mContext;
    ActionBarContextView mContextView;
    private int mCurWindowVisibility = 0;
    ViewPropertyAnimatorCompatSet mCurrentShowAnim;
    DecorToolbar mDecorToolbar;
    ActionMode mDeferredDestroyActionMode;
    ActionMode.Callback mDeferredModeDestroyCallback;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    boolean mHiddenByApp;
    boolean mHiddenBySystem;
    final ViewPropertyAnimatorListener mHideListener = new ViewPropertyAnimatorListenerAdapter() {
        public void onAnimationEnd(View view) {
            View view2;
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            if (windowDecorActionBar.mContentAnimations && (view2 = windowDecorActionBar.mContentView) != null) {
                view2.setTranslationY(0.0f);
                WindowDecorActionBar.this.mContainerView.setTranslationY(0.0f);
            }
            WindowDecorActionBar.this.mContainerView.setVisibility(8);
            WindowDecorActionBar.this.mContainerView.setTransitioning(false);
            WindowDecorActionBar windowDecorActionBar2 = WindowDecorActionBar.this;
            windowDecorActionBar2.mCurrentShowAnim = null;
            ActionMode.Callback callback = windowDecorActionBar2.mDeferredModeDestroyCallback;
            if (callback != null) {
                callback.onDestroyActionMode(windowDecorActionBar2.mDeferredDestroyActionMode);
                windowDecorActionBar2.mDeferredDestroyActionMode = null;
                windowDecorActionBar2.mDeferredModeDestroyCallback = null;
            }
            ActionBarOverlayLayout actionBarOverlayLayout = WindowDecorActionBar.this.mOverlayLayout;
            if (actionBarOverlayLayout != null) {
                ViewCompat.requestApplyInsets(actionBarOverlayLayout);
            }
        }
    };
    boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners = new ArrayList<>();
    private boolean mNowShowing = true;
    ActionBarOverlayLayout mOverlayLayout;
    private boolean mShowHideAnimationEnabled;
    final ViewPropertyAnimatorListener mShowListener = new ViewPropertyAnimatorListenerAdapter() {
        public void onAnimationEnd(View view) {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            windowDecorActionBar.mCurrentShowAnim = null;
            windowDecorActionBar.mContainerView.requestLayout();
        }
    };
    private boolean mShowingForMode;
    private Context mThemedContext;
    final ViewPropertyAnimatorUpdateListener mUpdateListener = new ViewPropertyAnimatorUpdateListener() {
        public void onAnimationUpdate(View view) {
            ((View) WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
        }
    };

    /* renamed from: android.support.v7.app.WindowDecorActionBar$ActionModeImpl */
    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
        private final Context mActionModeContext;
        private ActionMode.Callback mCallback;
        private WeakReference<View> mCustomView;
        private final MenuBuilder mMenu;

        public ActionModeImpl(Context context, ActionMode.Callback callback) {
            this.mActionModeContext = context;
            this.mCallback = callback;
            MenuBuilder menuBuilder = new MenuBuilder(context);
            menuBuilder.setDefaultShowAsAction(1);
            this.mMenu = menuBuilder;
            this.mMenu.setCallback(this);
        }

        public boolean dispatchOnCreate() {
            this.mMenu.stopDispatchingItemsChanged();
            try {
                return this.mCallback.onCreateActionMode(this, this.mMenu);
            } finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }

        public void finish() {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            if (windowDecorActionBar.mActionMode == this) {
                boolean z = windowDecorActionBar.mHiddenByApp;
                boolean z2 = windowDecorActionBar.mHiddenBySystem;
                boolean z3 = true;
                if (z || z2) {
                    z3 = false;
                }
                if (!z3) {
                    WindowDecorActionBar windowDecorActionBar2 = WindowDecorActionBar.this;
                    windowDecorActionBar2.mDeferredDestroyActionMode = this;
                    windowDecorActionBar2.mDeferredModeDestroyCallback = this.mCallback;
                } else {
                    this.mCallback.onDestroyActionMode(this);
                }
                this.mCallback = null;
                WindowDecorActionBar.this.animateToMode(false);
                WindowDecorActionBar.this.mContextView.closeMode();
                ((ToolbarWidgetWrapper) WindowDecorActionBar.this.mDecorToolbar).getViewGroup().sendAccessibilityEvent(32);
                WindowDecorActionBar windowDecorActionBar3 = WindowDecorActionBar.this;
                windowDecorActionBar3.mOverlayLayout.setHideOnContentScrollEnabled(windowDecorActionBar3.mHideOnContentScroll);
                WindowDecorActionBar.this.mActionMode = null;
            }
        }

        public View getCustomView() {
            WeakReference<View> weakReference = this.mCustomView;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
            return null;
        }

        public Menu getMenu() {
            return this.mMenu;
        }

        public MenuInflater getMenuInflater() {
            return new SupportMenuInflater(this.mActionModeContext);
        }

        public CharSequence getSubtitle() {
            return WindowDecorActionBar.this.mContextView.getSubtitle();
        }

        public CharSequence getTitle() {
            return WindowDecorActionBar.this.mContextView.getTitle();
        }

        public void invalidate() {
            if (WindowDecorActionBar.this.mActionMode == this) {
                this.mMenu.stopDispatchingItemsChanged();
                try {
                    this.mCallback.onPrepareActionMode(this, this.mMenu);
                } finally {
                    this.mMenu.startDispatchingItemsChanged();
                }
            }
        }

        public boolean isTitleOptional() {
            return WindowDecorActionBar.this.mContextView.isTitleOptional();
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            ActionMode.Callback callback = this.mCallback;
            if (callback != null) {
                return callback.onActionItemClicked(this, menuItem);
            }
            return false;
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (this.mCallback != null) {
                invalidate();
                WindowDecorActionBar.this.mContextView.showOverflowMenu();
            }
        }

        public void setCustomView(View view) {
            WindowDecorActionBar.this.mContextView.setCustomView(view);
            this.mCustomView = new WeakReference<>(view);
        }

        public void setSubtitle(CharSequence charSequence) {
            WindowDecorActionBar.this.mContextView.setSubtitle(charSequence);
        }

        public void setTitle(CharSequence charSequence) {
            WindowDecorActionBar.this.mContextView.setTitle(charSequence);
        }

        public void setTitleOptionalHint(boolean z) {
            super.setTitleOptionalHint(z);
            WindowDecorActionBar.this.mContextView.setTitleOptional(z);
        }

        public void setSubtitle(int i) {
            WindowDecorActionBar.this.mContextView.setSubtitle(WindowDecorActionBar.this.mContext.getResources().getString(i));
        }

        public void setTitle(int i) {
            WindowDecorActionBar.this.mContextView.setTitle(WindowDecorActionBar.this.mContext.getResources().getString(i));
        }
    }

    static {
        WindowDecorActionBar.class.desiredAssertionStatus();
    }

    public WindowDecorActionBar(Activity activity, boolean z) {
        new ArrayList();
        View decorView = activity.getWindow().getDecorView();
        init(decorView);
        if (!z) {
            this.mContentView = decorView.findViewById(16908290);
        }
    }

    private void init(View view) {
        DecorToolbar decorToolbar;
        this.mOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R.id.decor_content_parent);
        ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        View findViewById = view.findViewById(R.id.action_bar);
        if (findViewById instanceof DecorToolbar) {
            decorToolbar = (DecorToolbar) findViewById;
        } else if (findViewById instanceof Toolbar) {
            decorToolbar = ((Toolbar) findViewById).getWrapper();
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Can't make a decor toolbar out of ");
            outline13.append(findViewById != null ? findViewById.getClass().getSimpleName() : "null");
            throw new IllegalStateException(outline13.toString());
        }
        this.mDecorToolbar = decorToolbar;
        this.mContextView = (ActionBarContextView) view.findViewById(R.id.action_context_bar);
        this.mContainerView = (ActionBarContainer) view.findViewById(R.id.action_bar_container);
        DecorToolbar decorToolbar2 = this.mDecorToolbar;
        if (decorToolbar2 == null || this.mContextView == null || this.mContainerView == null) {
            throw new IllegalStateException(WindowDecorActionBar.class.getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.mContext = ((ToolbarWidgetWrapper) decorToolbar2).getContext();
        boolean z = (((ToolbarWidgetWrapper) this.mDecorToolbar).getDisplayOptions() & 4) != 0;
        if (z) {
            this.mDisplayHomeAsUpSet = true;
        }
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(this.mContext);
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setHomeButtonEnabled(actionBarPolicy.enableHomeButtonByDefault() || z);
        setHasEmbeddedTabs(actionBarPolicy.hasEmbeddedTabs());
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes((AttributeSet) null, R$styleable.ActionBar, R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(14, false)) {
            if (this.mOverlayLayout.isInOverlayMode()) {
                this.mHideOnContentScroll = true;
                this.mOverlayLayout.setHideOnContentScrollEnabled(true);
            } else {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(12, 0);
        if (dimensionPixelSize != 0) {
            ViewCompat.setElevation(this.mContainerView, (float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    private void setHasEmbeddedTabs(boolean z) {
        this.mHasEmbeddedTabs = z;
        if (!this.mHasEmbeddedTabs) {
            ((ToolbarWidgetWrapper) this.mDecorToolbar).setEmbeddedTabView((ScrollingTabContainerView) null);
            this.mContainerView.setTabContainer((ScrollingTabContainerView) null);
        } else {
            this.mContainerView.setTabContainer((ScrollingTabContainerView) null);
            ((ToolbarWidgetWrapper) this.mDecorToolbar).setEmbeddedTabView((ScrollingTabContainerView) null);
        }
        boolean z2 = true;
        boolean z3 = ((ToolbarWidgetWrapper) this.mDecorToolbar).getNavigationMode() == 2;
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setCollapsible(!this.mHasEmbeddedTabs && z3);
        ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
        if (this.mHasEmbeddedTabs || !z3) {
            z2 = false;
        }
        actionBarOverlayLayout.setHasNonEmbeddedTabs(z2);
    }

    private void updateVisibility(boolean z) {
        View view;
        View view2;
        View view3;
        if (this.mShowingForMode || (!this.mHiddenByApp && !this.mHiddenBySystem)) {
            if (!this.mNowShowing) {
                this.mNowShowing = true;
                ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.mCurrentShowAnim;
                if (viewPropertyAnimatorCompatSet != null) {
                    viewPropertyAnimatorCompatSet.cancel();
                }
                this.mContainerView.setVisibility(0);
                if (this.mCurWindowVisibility != 0 || (!this.mShowHideAnimationEnabled && !z)) {
                    this.mContainerView.setAlpha(1.0f);
                    this.mContainerView.setTranslationY(0.0f);
                    if (this.mContentAnimations && (view2 = this.mContentView) != null) {
                        view2.setTranslationY(0.0f);
                    }
                    this.mShowListener.onAnimationEnd((View) null);
                } else {
                    this.mContainerView.setTranslationY(0.0f);
                    float f = (float) (-this.mContainerView.getHeight());
                    if (z) {
                        int[] iArr = {0, 0};
                        this.mContainerView.getLocationInWindow(iArr);
                        f -= (float) iArr[1];
                    }
                    this.mContainerView.setTranslationY(f);
                    ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet2 = new ViewPropertyAnimatorCompatSet();
                    ViewPropertyAnimatorCompat animate = ViewCompat.animate(this.mContainerView);
                    animate.translationY(0.0f);
                    animate.setUpdateListener(this.mUpdateListener);
                    viewPropertyAnimatorCompatSet2.play(animate);
                    if (this.mContentAnimations && (view3 = this.mContentView) != null) {
                        view3.setTranslationY(f);
                        ViewPropertyAnimatorCompat animate2 = ViewCompat.animate(this.mContentView);
                        animate2.translationY(0.0f);
                        viewPropertyAnimatorCompatSet2.play(animate2);
                    }
                    viewPropertyAnimatorCompatSet2.setInterpolator(sShowInterpolator);
                    viewPropertyAnimatorCompatSet2.setDuration(250);
                    viewPropertyAnimatorCompatSet2.setListener(this.mShowListener);
                    this.mCurrentShowAnim = viewPropertyAnimatorCompatSet2;
                    viewPropertyAnimatorCompatSet2.start();
                }
                ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
                if (actionBarOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(actionBarOverlayLayout);
                }
            }
        } else if (this.mNowShowing) {
            this.mNowShowing = false;
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet3 = this.mCurrentShowAnim;
            if (viewPropertyAnimatorCompatSet3 != null) {
                viewPropertyAnimatorCompatSet3.cancel();
            }
            if (this.mCurWindowVisibility != 0 || (!this.mShowHideAnimationEnabled && !z)) {
                this.mHideListener.onAnimationEnd((View) null);
                return;
            }
            this.mContainerView.setAlpha(1.0f);
            this.mContainerView.setTransitioning(true);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet4 = new ViewPropertyAnimatorCompatSet();
            float f2 = (float) (-this.mContainerView.getHeight());
            if (z) {
                int[] iArr2 = {0, 0};
                this.mContainerView.getLocationInWindow(iArr2);
                f2 -= (float) iArr2[1];
            }
            ViewPropertyAnimatorCompat animate3 = ViewCompat.animate(this.mContainerView);
            animate3.translationY(f2);
            animate3.setUpdateListener(this.mUpdateListener);
            viewPropertyAnimatorCompatSet4.play(animate3);
            if (this.mContentAnimations && (view = this.mContentView) != null) {
                ViewPropertyAnimatorCompat animate4 = ViewCompat.animate(view);
                animate4.translationY(f2);
                viewPropertyAnimatorCompatSet4.play(animate4);
            }
            viewPropertyAnimatorCompatSet4.setInterpolator(sHideInterpolator);
            viewPropertyAnimatorCompatSet4.setDuration(250);
            viewPropertyAnimatorCompatSet4.setListener(this.mHideListener);
            this.mCurrentShowAnim = viewPropertyAnimatorCompatSet4;
            viewPropertyAnimatorCompatSet4.start();
        }
    }

    public void animateToMode(boolean z) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat;
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2;
        if (z) {
            if (!this.mShowingForMode) {
                this.mShowingForMode = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                updateVisibility(false);
            }
        } else if (this.mShowingForMode) {
            this.mShowingForMode = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.mOverlayLayout;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            updateVisibility(false);
        }
        if (ViewCompat.isLaidOut(this.mContainerView)) {
            if (z) {
                ViewPropertyAnimatorCompat viewPropertyAnimatorCompat3 = ((ToolbarWidgetWrapper) this.mDecorToolbar).setupAnimatorToVisibility(4, 100);
                viewPropertyAnimatorCompat = this.mContextView.setupAnimatorToVisibility(0, 200);
                viewPropertyAnimatorCompat2 = viewPropertyAnimatorCompat3;
            } else {
                viewPropertyAnimatorCompat = ((ToolbarWidgetWrapper) this.mDecorToolbar).setupAnimatorToVisibility(0, 200);
                viewPropertyAnimatorCompat2 = this.mContextView.setupAnimatorToVisibility(8, 100);
            }
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
            viewPropertyAnimatorCompatSet.playSequentially(viewPropertyAnimatorCompat2, viewPropertyAnimatorCompat);
            viewPropertyAnimatorCompatSet.start();
        } else if (z) {
            ((ToolbarWidgetWrapper) this.mDecorToolbar).setVisibility(4);
            this.mContextView.setVisibility(0);
        } else {
            ((ToolbarWidgetWrapper) this.mDecorToolbar).setVisibility(0);
            this.mContextView.setVisibility(8);
        }
    }

    public boolean collapseActionView() {
        DecorToolbar decorToolbar = this.mDecorToolbar;
        if (decorToolbar == null || !((ToolbarWidgetWrapper) decorToolbar).hasExpandedActionView()) {
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

    public void enableContentAnimations(boolean z) {
        this.mContentAnimations = z;
    }

    public View getCustomView() {
        return ((ToolbarWidgetWrapper) this.mDecorToolbar).getCustomView();
    }

    public int getDisplayOptions() {
        return ((ToolbarWidgetWrapper) this.mDecorToolbar).getDisplayOptions();
    }

    public Context getThemedContext() {
        if (this.mThemedContext == null) {
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.mThemedContext = new ContextThemeWrapper(this.mContext, i);
            } else {
                this.mThemedContext = this.mContext;
            }
        }
        return this.mThemedContext;
    }

    public void hideForSystem() {
        if (!this.mHiddenBySystem) {
            this.mHiddenBySystem = true;
            updateVisibility(true);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        setHasEmbeddedTabs(ActionBarPolicy.get(this.mContext).hasEmbeddedTabs());
    }

    public void onContentScrollStarted() {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.mCurrentShowAnim;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.cancel();
            this.mCurrentShowAnim = null;
        }
    }

    public void onContentScrollStopped() {
    }

    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        Menu menu;
        ActionModeImpl actionModeImpl = this.mActionMode;
        if (actionModeImpl == null || (menu = actionModeImpl.getMenu()) == null) {
            return false;
        }
        boolean z = true;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
            z = false;
        }
        menu.setQwertyMode(z);
        return menu.performShortcut(i, keyEvent, 0);
    }

    public void onWindowVisibilityChanged(int i) {
        this.mCurWindowVisibility = i;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.mContainerView.setPrimaryBackground(drawable);
    }

    public void setCustomView(int i) {
        if (this.mThemedContext == null) {
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.mThemedContext = new ContextThemeWrapper(this.mContext, i2);
            } else {
                this.mThemedContext = this.mContext;
            }
        }
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setCustomView(LayoutInflater.from(this.mThemedContext).inflate(i, ((ToolbarWidgetWrapper) this.mDecorToolbar).getViewGroup(), false));
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
        if (!this.mDisplayHomeAsUpSet) {
            setDisplayOptions(z ? 4 : 0, 4);
        }
    }

    public void setDisplayHomeAsUpEnabled(boolean z) {
        setDisplayOptions(z ? 4 : 0, 4);
    }

    public void setDisplayOptions(int i, int i2) {
        int displayOptions = ((ToolbarWidgetWrapper) this.mDecorToolbar).getDisplayOptions();
        if ((i2 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
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
        ViewCompat.setElevation(this.mContainerView, f);
    }

    public void setHideOffset(int i) {
        if (i == 0 || this.mOverlayLayout.isInOverlayMode()) {
            this.mOverlayLayout.setActionBarHideOffset(i);
            return;
        }
        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
    }

    public void setLogo(Drawable drawable) {
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setLogo(drawable);
    }

    public void setShowHideAnimationEnabled(boolean z) {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet;
        this.mShowHideAnimationEnabled = z;
        if (!z && (viewPropertyAnimatorCompatSet = this.mCurrentShowAnim) != null) {
            viewPropertyAnimatorCompatSet.cancel();
        }
    }

    public void setTitle(int i) {
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setTitle(this.mContext.getString(i));
    }

    public void setWindowTitle(CharSequence charSequence) {
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setWindowTitle(charSequence);
    }

    public void showForSystem() {
        if (this.mHiddenBySystem) {
            this.mHiddenBySystem = false;
            updateVisibility(true);
        }
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        ActionModeImpl actionModeImpl = this.mActionMode;
        if (actionModeImpl != null) {
            actionModeImpl.finish();
        }
        this.mOverlayLayout.setHideOnContentScrollEnabled(false);
        this.mContextView.killMode();
        ActionModeImpl actionModeImpl2 = new ActionModeImpl(this.mContextView.getContext(), callback);
        if (!actionModeImpl2.dispatchOnCreate()) {
            return null;
        }
        this.mActionMode = actionModeImpl2;
        actionModeImpl2.invalidate();
        this.mContextView.initForMode(actionModeImpl2);
        animateToMode(true);
        this.mContextView.sendAccessibilityEvent(32);
        return actionModeImpl2;
    }

    public void setTitle(CharSequence charSequence) {
        ((ToolbarWidgetWrapper) this.mDecorToolbar).setTitle(charSequence);
    }

    public WindowDecorActionBar(Dialog dialog) {
        new ArrayList();
        init(dialog.getWindow().getDecorView());
    }
}
