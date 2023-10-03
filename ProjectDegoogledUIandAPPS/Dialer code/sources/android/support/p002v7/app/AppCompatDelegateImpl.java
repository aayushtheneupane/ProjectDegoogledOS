package android.support.p002v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.R$dimen;
import android.support.p000v4.view.OnApplyWindowInsetsListener;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewPropertyAnimatorCompat;
import android.support.p000v4.view.ViewPropertyAnimatorListener;
import android.support.p000v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.p000v4.view.WindowInsetsCompat;
import android.support.p002v7.appcompat.R$styleable;
import android.support.p002v7.content.res.AppCompatResources;
import android.support.p002v7.view.ActionMode;
import android.support.p002v7.view.ContextThemeWrapper;
import android.support.p002v7.view.SupportActionModeWrapper;
import android.support.p002v7.view.SupportMenuInflater;
import android.support.p002v7.view.WindowCallbackWrapper;
import android.support.p002v7.view.menu.ListMenuPresenter;
import android.support.p002v7.view.menu.MenuBuilder;
import android.support.p002v7.view.menu.MenuPresenter;
import android.support.p002v7.widget.ActionBarContextView;
import android.support.p002v7.widget.AppCompatDrawableManager;
import android.support.p002v7.widget.ContentFrameLayout;
import android.support.p002v7.widget.DecorContentParent;
import android.support.p002v7.widget.TintTypedArray;
import android.support.p002v7.widget.Toolbar;
import android.support.p002v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.Thread;
import java.util.List;

/* renamed from: android.support.v7.app.AppCompatDelegateImpl */
class AppCompatDelegateImpl extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
    private static final boolean IS_PRE_LOLLIPOP = false;
    private static boolean sInstalledExceptionHandler = true;
    private static final int[] sWindowBackgroundStyleable = {16842836};
    ActionBar mActionBar;
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    final AppCompatCallback mAppCompatCallback;
    private AppCompatViewInflater mAppCompatViewInflater;
    final Window.Callback mAppCompatWindowCallback;
    private boolean mApplyDayNightCalled;
    private AutoNightModeManager mAutoNightModeManager;
    private boolean mClosingActionMenu;
    final Context mContext;
    private DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    ViewPropertyAnimatorCompat mFadeAnim = null;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    private boolean mHandleNativeActionModes = true;
    boolean mHasActionBar;
    int mInvalidatePanelMenuFeatures;
    boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable = new Runnable() {
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.mInvalidatePanelMenuFeatures & 1) != 0) {
                appCompatDelegateImpl.doInvalidatePanelMenu(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.mInvalidatePanelMenuFeatures & 4096) != 0) {
                appCompatDelegateImpl2.doInvalidatePanelMenu(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.mInvalidatePanelMenuPosted = false;
            appCompatDelegateImpl3.mInvalidatePanelMenuFeatures = 0;
        }
    };
    /* access modifiers changed from: private */
    public boolean mIsDestroyed;
    boolean mIsFloating;
    private int mLocalNightMode = -100;
    private boolean mLongPressBackDown;
    MenuInflater mMenuInflater;
    final Window.Callback mOriginalWindowCallback;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    private ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private CharSequence mTitle;
    private TextView mTitleView;
    final Window mWindow;
    boolean mWindowNoTitle;

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$ActionMenuPresenterCallback */
    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        ActionMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            AppCompatDelegateImpl.this.checkCloseActionMenu(menuBuilder);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback windowCallback = AppCompatDelegateImpl.this.getWindowCallback();
            if (windowCallback == null) {
                return true;
            }
            windowCallback.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9 */
    class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
        private ActionMode.Callback mWrapped;

        public ActionModeCallbackWrapperV9(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.mActionModePopup != null) {
                appCompatDelegateImpl.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.mShowActionModePopup);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.mActionModeView != null) {
                appCompatDelegateImpl2.endOnGoingFadeAnimation();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                ViewPropertyAnimatorCompat animate = ViewCompat.animate(appCompatDelegateImpl3.mActionModeView);
                animate.alpha(0.0f);
                appCompatDelegateImpl3.mFadeAnim = animate;
                AppCompatDelegateImpl.this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter() {
                    public void onAnimationEnd(View view) {
                        AppCompatDelegateImpl.this.mActionModeView.setVisibility(8);
                        AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                        PopupWindow popupWindow = appCompatDelegateImpl.mActionModePopup;
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        } else if (appCompatDelegateImpl.mActionModeView.getParent() instanceof View) {
                            ViewCompat.requestApplyInsets((View) AppCompatDelegateImpl.this.mActionModeView.getParent());
                        }
                        AppCompatDelegateImpl.this.mActionModeView.removeAllViews();
                        AppCompatDelegateImpl.this.mFadeAnim.setListener((ViewPropertyAnimatorListener) null);
                        AppCompatDelegateImpl.this.mFadeAnim = null;
                    }
                });
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            AppCompatCallback appCompatCallback = appCompatDelegateImpl4.mAppCompatCallback;
            if (appCompatCallback != null) {
                appCompatCallback.onSupportActionModeFinished(appCompatDelegateImpl4.mActionMode);
            }
            AppCompatDelegateImpl.this.mActionMode = null;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$AppCompatWindowCallback */
    class AppCompatWindowCallback extends WindowCallbackWrapper {
        AppCompatWindowCallback(Window.Callback callback) {
            super(callback);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.onKeyShortcut(keyEvent.getKeyCode(), keyEvent);
        }

        public void onContentChanged() {
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.onMenuOpened(i);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.onPanelClosed(i);
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
            if (i == 0 && menuBuilder == null) {
                return false;
            }
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(true);
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(false);
            }
            return onPreparePanel;
        }

        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            MenuBuilder menuBuilder;
            PanelFeatureState panelState = AppCompatDelegateImpl.this.getPanelState(0, true);
            if (panelState == null || (menuBuilder = panelState.menu) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, menuBuilder, i);
            }
        }

        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            int i = Build.VERSION.SDK_INT;
            return null;
        }

        /* access modifiers changed from: package-private */
        public final android.view.ActionMode startAsSupportActionMode(ActionMode.Callback callback) {
            SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImpl.this.mContext, callback);
            android.support.p002v7.view.ActionMode startSupportActionMode = AppCompatDelegateImpl.this.startSupportActionMode(callbackWrapper);
            if (startSupportActionMode != null) {
                return callbackWrapper.getActionModeWrapper(startSupportActionMode);
            }
            return null;
        }

        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (!AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled() || i != 0) {
                return super.onWindowStartingActionMode(callback, i);
            }
            return startAsSupportActionMode(callback);
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$AutoNightModeManager */
    final class AutoNightModeManager {
        private BroadcastReceiver mAutoTimeChangeReceiver;
        private IntentFilter mAutoTimeChangeReceiverFilter;
        private boolean mIsNight;
        private TwilightManager mTwilightManager;

        AutoNightModeManager(TwilightManager twilightManager) {
            this.mTwilightManager = twilightManager;
            this.mIsNight = twilightManager.isNight();
        }

        /* access modifiers changed from: package-private */
        public void cleanup() {
            BroadcastReceiver broadcastReceiver = this.mAutoTimeChangeReceiver;
            if (broadcastReceiver != null) {
                AppCompatDelegateImpl.this.mContext.unregisterReceiver(broadcastReceiver);
                this.mAutoTimeChangeReceiver = null;
            }
        }

        /* access modifiers changed from: package-private */
        public void dispatchTimeChanged() {
            boolean isNight = this.mTwilightManager.isNight();
            if (isNight != this.mIsNight) {
                this.mIsNight = isNight;
                AppCompatDelegateImpl.this.applyDayNight();
            }
        }

        /* access modifiers changed from: package-private */
        public int getApplyableNightMode() {
            this.mIsNight = this.mTwilightManager.isNight();
            return this.mIsNight ? 2 : 1;
        }

        /* access modifiers changed from: package-private */
        public void setup() {
            cleanup();
            if (this.mAutoTimeChangeReceiver == null) {
                this.mAutoTimeChangeReceiver = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        AutoNightModeManager.this.dispatchTimeChanged();
                    }
                };
            }
            if (this.mAutoTimeChangeReceiverFilter == null) {
                this.mAutoTimeChangeReceiverFilter = new IntentFilter();
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
            }
            AppCompatDelegateImpl.this.mContext.registerReceiver(this.mAutoTimeChangeReceiver, this.mAutoTimeChangeReceiverFilter);
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$ListMenuDecorView */
    private class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(Context context) {
            super(context);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5) {
                    AppCompatDelegateImpl.this.closePanel(0);
                    return true;
                }
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        public void setBackgroundResource(int i) {
            setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), i));
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState */
    protected static final class PanelFeatureState {
        int background;
        View createdPanelView;
        ViewGroup decorView;
        int featureId;
        Bundle frozenActionViewState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView = false;
        boolean refreshMenuContent;
        View shownPanelView;
        int windowAnimations;

        /* renamed from: x */
        int f1x;

        /* renamed from: y */
        int f2y;

        /* renamed from: android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState$SavedState */
        private static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
                public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.readFromParcel(parcel, classLoader);
                }

                public Object[] newArray(int i) {
                    return new SavedState[i];
                }

                public Object createFromParcel(Parcel parcel) {
                    return SavedState.readFromParcel(parcel, (ClassLoader) null);
                }
            };
            int featureId;
            boolean isOpen;
            Bundle menuState;

            SavedState() {
            }

            static SavedState readFromParcel(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.featureId = parcel.readInt();
                boolean z = true;
                if (parcel.readInt() != 1) {
                    z = false;
                }
                savedState.isOpen = z;
                if (savedState.isOpen) {
                    savedState.menuState = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.featureId);
                parcel.writeInt(this.isOpen ? 1 : 0);
                if (this.isOpen) {
                    parcel.writeBundle(this.menuState);
                }
            }
        }

        PanelFeatureState(int i) {
            this.featureId = i;
        }

        /* access modifiers changed from: package-private */
        public void setMenu(MenuBuilder menuBuilder) {
            ListMenuPresenter listMenuPresenter2;
            MenuBuilder menuBuilder2 = this.menu;
            if (menuBuilder != menuBuilder2) {
                if (menuBuilder2 != null) {
                    menuBuilder2.removeMenuPresenter(this.listMenuPresenter);
                }
                this.menu = menuBuilder;
                if (menuBuilder != null && (listMenuPresenter2 = this.listMenuPresenter) != null) {
                    menuBuilder.addMenuPresenter(listMenuPresenter2);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setStyle(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                newTheme.applyStyle(i, true);
            }
            newTheme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                newTheme.applyStyle(i2, true);
            } else {
                newTheme.applyStyle(2131886507, true);
            }
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 0);
            contextThemeWrapper.getTheme().setTo(newTheme);
            this.listPresenterContext = contextThemeWrapper;
            TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(R$styleable.AppCompatTheme);
            this.background = obtainStyledAttributes.getResourceId(80, 0);
            this.windowAnimations = obtainStyledAttributes.getResourceId(1, 0);
            obtainStyledAttributes.recycle();
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImpl$PanelMenuPresenterCallback */
    private final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
        PanelMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            MenuBuilder rootMenu = menuBuilder.getRootMenu();
            boolean z2 = rootMenu != menuBuilder;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                menuBuilder = rootMenu;
            }
            PanelFeatureState findMenuPanel = appCompatDelegateImpl.findMenuPanel(menuBuilder);
            if (findMenuPanel == null) {
                return;
            }
            if (z2) {
                AppCompatDelegateImpl.this.callOnPanelClosed(findMenuPanel.featureId, findMenuPanel, rootMenu);
                AppCompatDelegateImpl.this.closePanel(findMenuPanel, true);
                return;
            }
            AppCompatDelegateImpl.this.closePanel(findMenuPanel, z);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback windowCallback;
            if (menuBuilder != null) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.mHasActionBar || (windowCallback = appCompatDelegateImpl.getWindowCallback()) == null || AppCompatDelegateImpl.this.mIsDestroyed) {
                return true;
            }
            windowCallback.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (IS_PRE_LOLLIPOP && !sInstalledExceptionHandler) {
            final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    String message;
                    boolean z = false;
                    if ((th instanceof Resources.NotFoundException) && (message = th.getMessage()) != null && (message.contains("drawable") || message.contains("Drawable"))) {
                        z = true;
                    }
                    if (z) {
                        Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                        notFoundException.initCause(th.getCause());
                        notFoundException.setStackTrace(th.getStackTrace());
                        defaultUncaughtExceptionHandler.uncaughtException(thread, notFoundException);
                        return;
                    }
                    defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                }
            });
        }
    }

    AppCompatDelegateImpl(Context context, Window window, AppCompatCallback appCompatCallback) {
        this.mContext = context;
        this.mWindow = window;
        this.mAppCompatCallback = appCompatCallback;
        this.mOriginalWindowCallback = this.mWindow.getCallback();
        Window.Callback callback = this.mOriginalWindowCallback;
        if (!(callback instanceof AppCompatWindowCallback)) {
            this.mAppCompatWindowCallback = new AppCompatWindowCallback(callback);
            this.mWindow.setCallback(this.mAppCompatWindowCallback);
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, (AttributeSet) null, sWindowBackgroundStyleable);
            Drawable drawableIfKnown = obtainStyledAttributes.getDrawableIfKnown(0);
            if (drawableIfKnown != null) {
                this.mWindow.setBackgroundDrawable(drawableIfKnown);
            }
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    private void ensureAutoNightModeManager() {
        if (this.mAutoNightModeManager == null) {
            this.mAutoNightModeManager = new AutoNightModeManager(TwilightManager.getInstance(this.mContext));
        }
    }

    private void ensureSubDecor() {
        ViewGroup viewGroup;
        Context context;
        if (!this.mSubDecorInstalled) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
            if (obtainStyledAttributes.hasValue(111)) {
                if (obtainStyledAttributes.getBoolean(120, false)) {
                    requestWindowFeature(1);
                } else if (obtainStyledAttributes.getBoolean(111, false)) {
                    requestWindowFeature(108);
                }
                if (obtainStyledAttributes.getBoolean(112, false)) {
                    requestWindowFeature(109);
                }
                if (obtainStyledAttributes.getBoolean(113, false)) {
                    requestWindowFeature(10);
                }
                this.mIsFloating = obtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
                obtainStyledAttributes.recycle();
                this.mWindow.getDecorView();
                LayoutInflater from = LayoutInflater.from(this.mContext);
                if (this.mWindowNoTitle) {
                    if (this.mOverlayActionMode) {
                        viewGroup = (ViewGroup) from.inflate(R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null);
                    } else {
                        viewGroup = (ViewGroup) from.inflate(R.layout.abc_screen_simple, (ViewGroup) null);
                    }
                    int i = Build.VERSION.SDK_INT;
                    ViewCompat.setOnApplyWindowInsetsListener(viewGroup, new OnApplyWindowInsetsListener() {
                        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                            int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
                            int updateStatusGuard = AppCompatDelegateImpl.this.updateStatusGuard(systemWindowInsetTop);
                            if (systemWindowInsetTop != updateStatusGuard) {
                                windowInsetsCompat = windowInsetsCompat.replaceSystemWindowInsets(windowInsetsCompat.getSystemWindowInsetLeft(), updateStatusGuard, windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
                            }
                            return ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
                        }
                    });
                } else if (this.mIsFloating) {
                    viewGroup = (ViewGroup) from.inflate(R.layout.abc_dialog_title_material, (ViewGroup) null);
                    this.mOverlayActionBar = false;
                    this.mHasActionBar = false;
                } else if (this.mHasActionBar) {
                    TypedValue typedValue = new TypedValue();
                    this.mContext.getTheme().resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                    int i2 = typedValue.resourceId;
                    if (i2 != 0) {
                        context = new ContextThemeWrapper(this.mContext, i2);
                    } else {
                        context = this.mContext;
                    }
                    viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.abc_screen_toolbar, (ViewGroup) null);
                    this.mDecorContentParent = (DecorContentParent) viewGroup.findViewById(R.id.decor_content_parent);
                    this.mDecorContentParent.setWindowCallback(getWindowCallback());
                    if (this.mOverlayActionBar) {
                        this.mDecorContentParent.initFeature(109);
                    }
                    if (this.mFeatureProgress) {
                        this.mDecorContentParent.initFeature(2);
                    }
                    if (this.mFeatureIndeterminateProgress) {
                        this.mDecorContentParent.initFeature(5);
                    }
                } else {
                    viewGroup = null;
                }
                if (viewGroup != null) {
                    if (this.mDecorContentParent == null) {
                        this.mTitleView = (TextView) viewGroup.findViewById(R.id.title);
                    }
                    ViewUtils.makeOptionalFitsSystemWindows(viewGroup);
                    ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R.id.action_bar_activity_content);
                    ViewGroup viewGroup2 = (ViewGroup) this.mWindow.findViewById(16908290);
                    if (viewGroup2 != null) {
                        while (viewGroup2.getChildCount() > 0) {
                            View childAt = viewGroup2.getChildAt(0);
                            viewGroup2.removeViewAt(0);
                            contentFrameLayout.addView(childAt);
                        }
                        viewGroup2.setId(-1);
                        contentFrameLayout.setId(16908290);
                        if (viewGroup2 instanceof FrameLayout) {
                            ((FrameLayout) viewGroup2).setForeground((Drawable) null);
                        }
                    }
                    this.mWindow.setContentView(viewGroup);
                    contentFrameLayout.setAttachListener(new ContentFrameLayout.OnAttachListener() {
                        public void onAttachedFromWindow() {
                        }

                        public void onDetachedFromWindow() {
                            AppCompatDelegateImpl.this.dismissPopups();
                        }
                    });
                    this.mSubDecor = viewGroup;
                    CharSequence title = getTitle();
                    if (!TextUtils.isEmpty(title)) {
                        DecorContentParent decorContentParent = this.mDecorContentParent;
                        if (decorContentParent != null) {
                            decorContentParent.setWindowTitle(title);
                        } else if (peekSupportActionBar() != null) {
                            peekSupportActionBar().setWindowTitle(title);
                        } else {
                            TextView textView = this.mTitleView;
                            if (textView != null) {
                                textView.setText(title);
                            }
                        }
                    }
                    ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.mSubDecor.findViewById(16908290);
                    View decorView = this.mWindow.getDecorView();
                    contentFrameLayout2.setDecorPadding(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
                    TypedArray obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
                    obtainStyledAttributes2.getValue(118, contentFrameLayout2.getMinWidthMajor());
                    obtainStyledAttributes2.getValue(119, contentFrameLayout2.getMinWidthMinor());
                    if (obtainStyledAttributes2.hasValue(116)) {
                        obtainStyledAttributes2.getValue(116, contentFrameLayout2.getFixedWidthMajor());
                    }
                    if (obtainStyledAttributes2.hasValue(117)) {
                        obtainStyledAttributes2.getValue(117, contentFrameLayout2.getFixedWidthMinor());
                    }
                    if (obtainStyledAttributes2.hasValue(114)) {
                        obtainStyledAttributes2.getValue(114, contentFrameLayout2.getFixedHeightMajor());
                    }
                    if (obtainStyledAttributes2.hasValue(115)) {
                        obtainStyledAttributes2.getValue(115, contentFrameLayout2.getFixedHeightMinor());
                    }
                    obtainStyledAttributes2.recycle();
                    contentFrameLayout2.requestLayout();
                    onSubDecorInstalled(this.mSubDecor);
                    this.mSubDecorInstalled = true;
                    PanelFeatureState panelState = getPanelState(0, false);
                    if (this.mIsDestroyed) {
                        return;
                    }
                    if (panelState == null || panelState.menu == null) {
                        invalidatePanelMenu(108);
                        return;
                    }
                    return;
                }
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("AppCompat does not support the current theme features: { windowActionBar: ");
                outline13.append(this.mHasActionBar);
                outline13.append(", windowActionBarOverlay: ");
                outline13.append(this.mOverlayActionBar);
                outline13.append(", android:windowIsFloating: ");
                outline13.append(this.mIsFloating);
                outline13.append(", windowActionModeOverlay: ");
                outline13.append(this.mOverlayActionMode);
                outline13.append(", windowNoTitle: ");
                outline13.append(this.mWindowNoTitle);
                outline13.append(" }");
                throw new IllegalArgumentException(outline13.toString());
            }
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
    }

    private void initWindowDecorActionBar() {
        ensureSubDecor();
        if (this.mHasActionBar && this.mActionBar == null) {
            Window.Callback callback = this.mOriginalWindowCallback;
            if (callback instanceof Activity) {
                this.mActionBar = new WindowDecorActionBar((Activity) callback, this.mOverlayActionBar);
            } else if (callback instanceof Dialog) {
                this.mActionBar = new WindowDecorActionBar((Dialog) callback);
            }
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null) {
                actionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
            }
        }
    }

    private void invalidatePanelMenu(int i) {
        this.mInvalidatePanelMenuFeatures = (1 << i) | this.mInvalidatePanelMenuFeatures;
        if (!this.mInvalidatePanelMenuPosted) {
            ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00e0, code lost:
        if (r14.shownPanelView != null) goto L_0x00e2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:89:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void openPanel(android.support.p002v7.app.AppCompatDelegateImpl.PanelFeatureState r14, android.view.KeyEvent r15) {
        /*
            r13 = this;
            boolean r0 = r14.isOpen
            if (r0 != 0) goto L_0x0160
            boolean r0 = r13.mIsDestroyed
            if (r0 == 0) goto L_0x000a
            goto L_0x0160
        L_0x000a:
            int r0 = r14.featureId
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0027
            android.content.Context r0 = r13.mContext
            android.content.res.Resources r0 = r0.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.screenLayout
            r0 = r0 & 15
            r3 = 4
            if (r0 != r3) goto L_0x0023
            r0 = r2
            goto L_0x0024
        L_0x0023:
            r0 = r1
        L_0x0024:
            if (r0 == 0) goto L_0x0027
            return
        L_0x0027:
            android.view.Window$Callback r0 = r13.getWindowCallback()
            if (r0 == 0) goto L_0x003b
            int r3 = r14.featureId
            android.support.v7.view.menu.MenuBuilder r4 = r14.menu
            boolean r0 = r0.onMenuOpened(r3, r4)
            if (r0 != 0) goto L_0x003b
            r13.closePanel(r14, r2)
            return
        L_0x003b:
            android.content.Context r0 = r13.mContext
            java.lang.String r3 = "window"
            java.lang.Object r0 = r0.getSystemService(r3)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            if (r0 != 0) goto L_0x0048
            return
        L_0x0048:
            boolean r15 = r13.preparePanel(r14, r15)
            if (r15 != 0) goto L_0x004f
            return
        L_0x004f:
            android.view.ViewGroup r15 = r14.decorView
            r3 = -1
            r4 = -2
            if (r15 == 0) goto L_0x006b
            boolean r15 = r14.refreshDecorView
            if (r15 == 0) goto L_0x005a
            goto L_0x006b
        L_0x005a:
            android.view.View r13 = r14.createdPanelView
            if (r13 == 0) goto L_0x013e
            android.view.ViewGroup$LayoutParams r13 = r13.getLayoutParams()
            if (r13 == 0) goto L_0x013e
            int r13 = r13.width
            if (r13 != r3) goto L_0x013e
            r6 = r3
            goto L_0x013f
        L_0x006b:
            android.view.ViewGroup r15 = r14.decorView
            if (r15 != 0) goto L_0x0088
            android.content.Context r15 = r13.getActionBarThemedContext()
            r14.setStyle(r15)
            android.support.v7.app.AppCompatDelegateImpl$ListMenuDecorView r15 = new android.support.v7.app.AppCompatDelegateImpl$ListMenuDecorView
            android.content.Context r3 = r14.listPresenterContext
            r15.<init>(r3)
            r14.decorView = r15
            r15 = 81
            r14.gravity = r15
            android.view.ViewGroup r15 = r14.decorView
            if (r15 != 0) goto L_0x0097
            return
        L_0x0088:
            boolean r3 = r14.refreshDecorView
            if (r3 == 0) goto L_0x0097
            int r15 = r15.getChildCount()
            if (r15 <= 0) goto L_0x0097
            android.view.ViewGroup r15 = r14.decorView
            r15.removeAllViews()
        L_0x0097:
            android.view.View r15 = r14.createdPanelView
            if (r15 == 0) goto L_0x009e
            r14.shownPanelView = r15
            goto L_0x00e2
        L_0x009e:
            android.support.v7.view.menu.MenuBuilder r15 = r14.menu
            if (r15 != 0) goto L_0x00a3
            goto L_0x00e4
        L_0x00a3:
            android.support.v7.app.AppCompatDelegateImpl$PanelMenuPresenterCallback r15 = r13.mPanelMenuPresenterCallback
            if (r15 != 0) goto L_0x00ae
            android.support.v7.app.AppCompatDelegateImpl$PanelMenuPresenterCallback r15 = new android.support.v7.app.AppCompatDelegateImpl$PanelMenuPresenterCallback
            r15.<init>()
            r13.mPanelMenuPresenterCallback = r15
        L_0x00ae:
            android.support.v7.app.AppCompatDelegateImpl$PanelMenuPresenterCallback r13 = r13.mPanelMenuPresenterCallback
            android.support.v7.view.menu.MenuBuilder r15 = r14.menu
            if (r15 != 0) goto L_0x00b6
            r13 = 0
            goto L_0x00da
        L_0x00b6:
            android.support.v7.view.menu.ListMenuPresenter r15 = r14.listMenuPresenter
            if (r15 != 0) goto L_0x00d2
            android.support.v7.view.menu.ListMenuPresenter r15 = new android.support.v7.view.menu.ListMenuPresenter
            android.content.Context r3 = r14.listPresenterContext
            r5 = 2131492880(0x7f0c0010, float:1.8609224E38)
            r15.<init>(r3, r5)
            r14.listMenuPresenter = r15
            android.support.v7.view.menu.ListMenuPresenter r15 = r14.listMenuPresenter
            r15.setCallback(r13)
            android.support.v7.view.menu.MenuBuilder r13 = r14.menu
            android.support.v7.view.menu.ListMenuPresenter r15 = r14.listMenuPresenter
            r13.addMenuPresenter(r15)
        L_0x00d2:
            android.support.v7.view.menu.ListMenuPresenter r13 = r14.listMenuPresenter
            android.view.ViewGroup r15 = r14.decorView
            android.support.v7.view.menu.MenuView r13 = r13.getMenuView(r15)
        L_0x00da:
            android.view.View r13 = (android.view.View) r13
            r14.shownPanelView = r13
            android.view.View r13 = r14.shownPanelView
            if (r13 == 0) goto L_0x00e4
        L_0x00e2:
            r13 = r2
            goto L_0x00e5
        L_0x00e4:
            r13 = r1
        L_0x00e5:
            if (r13 == 0) goto L_0x0160
            android.view.View r13 = r14.shownPanelView
            if (r13 != 0) goto L_0x00ec
            goto L_0x00ff
        L_0x00ec:
            android.view.View r13 = r14.createdPanelView
            if (r13 == 0) goto L_0x00f1
            goto L_0x00fd
        L_0x00f1:
            android.support.v7.view.menu.ListMenuPresenter r13 = r14.listMenuPresenter
            android.widget.ListAdapter r13 = r13.getAdapter()
            int r13 = r13.getCount()
            if (r13 <= 0) goto L_0x00ff
        L_0x00fd:
            r13 = r2
            goto L_0x0100
        L_0x00ff:
            r13 = r1
        L_0x0100:
            if (r13 != 0) goto L_0x0103
            goto L_0x0160
        L_0x0103:
            android.view.View r13 = r14.shownPanelView
            android.view.ViewGroup$LayoutParams r13 = r13.getLayoutParams()
            if (r13 != 0) goto L_0x0110
            android.view.ViewGroup$LayoutParams r13 = new android.view.ViewGroup$LayoutParams
            r13.<init>(r4, r4)
        L_0x0110:
            int r15 = r14.background
            android.view.ViewGroup r3 = r14.decorView
            r3.setBackgroundResource(r15)
            android.view.View r15 = r14.shownPanelView
            android.view.ViewParent r15 = r15.getParent()
            if (r15 == 0) goto L_0x012a
            boolean r3 = r15 instanceof android.view.ViewGroup
            if (r3 == 0) goto L_0x012a
            android.view.ViewGroup r15 = (android.view.ViewGroup) r15
            android.view.View r3 = r14.shownPanelView
            r15.removeView(r3)
        L_0x012a:
            android.view.ViewGroup r15 = r14.decorView
            android.view.View r3 = r14.shownPanelView
            r15.addView(r3, r13)
            android.view.View r13 = r14.shownPanelView
            boolean r13 = r13.hasFocus()
            if (r13 != 0) goto L_0x013e
            android.view.View r13 = r14.shownPanelView
            r13.requestFocus()
        L_0x013e:
            r6 = r4
        L_0x013f:
            r14.isHandled = r1
            android.view.WindowManager$LayoutParams r13 = new android.view.WindowManager$LayoutParams
            r7 = -2
            int r8 = r14.f1x
            int r9 = r14.f2y
            r10 = 1002(0x3ea, float:1.404E-42)
            r11 = 8519680(0x820000, float:1.1938615E-38)
            r12 = -3
            r5 = r13
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)
            int r15 = r14.gravity
            r13.gravity = r15
            int r15 = r14.windowAnimations
            r13.windowAnimations = r15
            android.view.ViewGroup r15 = r14.decorView
            r0.addView(r15, r13)
            r14.isOpen = r2
        L_0x0160:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.app.AppCompatDelegateImpl.openPanel(android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState, android.view.KeyEvent):void");
    }

    private boolean performPanelShortcut(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent, int i2) {
        MenuBuilder menuBuilder;
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.isPrepared || preparePanel(panelFeatureState, keyEvent)) && (menuBuilder = panelFeatureState.menu) != null) {
            z = menuBuilder.performShortcut(i, keyEvent, i2);
        }
        if (z && (i2 & 1) == 0 && this.mDecorContentParent == null) {
            closePanel(panelFeatureState, true);
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c2 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean preparePanel(android.support.p002v7.app.AppCompatDelegateImpl.PanelFeatureState r12, android.view.KeyEvent r13) {
        /*
            r11 = this;
            boolean r0 = r11.mIsDestroyed
            r1 = 0
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            boolean r0 = r12.isPrepared
            r2 = 1
            if (r0 == 0) goto L_0x000c
            return r2
        L_0x000c:
            android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState r0 = r11.mPreparedPanel
            if (r0 == 0) goto L_0x0015
            if (r0 == r12) goto L_0x0015
            r11.closePanel(r0, r1)
        L_0x0015:
            android.view.Window$Callback r0 = r11.getWindowCallback()
            if (r0 == 0) goto L_0x0023
            int r3 = r12.featureId
            android.view.View r3 = r0.onCreatePanelView(r3)
            r12.createdPanelView = r3
        L_0x0023:
            int r3 = r12.featureId
            r4 = 108(0x6c, float:1.51E-43)
            if (r3 == 0) goto L_0x002e
            if (r3 != r4) goto L_0x002c
            goto L_0x002e
        L_0x002c:
            r3 = r1
            goto L_0x002f
        L_0x002e:
            r3 = r2
        L_0x002f:
            if (r3 == 0) goto L_0x0038
            android.support.v7.widget.DecorContentParent r5 = r11.mDecorContentParent
            if (r5 == 0) goto L_0x0038
            r5.setMenuPrepared()
        L_0x0038:
            android.view.View r5 = r12.createdPanelView
            if (r5 != 0) goto L_0x014b
            if (r3 == 0) goto L_0x0046
            android.support.v7.app.ActionBar r5 = r11.peekSupportActionBar()
            boolean r5 = r5 instanceof android.support.p002v7.app.ToolbarActionBar
            if (r5 != 0) goto L_0x014b
        L_0x0046:
            android.support.v7.view.menu.MenuBuilder r5 = r12.menu
            r6 = 0
            if (r5 == 0) goto L_0x004f
            boolean r5 = r12.refreshMenuContent
            if (r5 == 0) goto L_0x00fd
        L_0x004f:
            android.support.v7.view.menu.MenuBuilder r5 = r12.menu
            if (r5 != 0) goto L_0x00c3
            android.content.Context r5 = r11.mContext
            int r7 = r12.featureId
            if (r7 == 0) goto L_0x005b
            if (r7 != r4) goto L_0x00b2
        L_0x005b:
            android.support.v7.widget.DecorContentParent r4 = r11.mDecorContentParent
            if (r4 == 0) goto L_0x00b2
            android.util.TypedValue r4 = new android.util.TypedValue
            r4.<init>()
            android.content.res.Resources$Theme r7 = r5.getTheme()
            r8 = 2130968585(0x7f040009, float:1.7545828E38)
            r7.resolveAttribute(r8, r4, r2)
            int r8 = r4.resourceId
            r9 = 2130968586(0x7f04000a, float:1.754583E38)
            if (r8 == 0) goto L_0x0089
            android.content.res.Resources r8 = r5.getResources()
            android.content.res.Resources$Theme r8 = r8.newTheme()
            r8.setTo(r7)
            int r10 = r4.resourceId
            r8.applyStyle(r10, r2)
            r8.resolveAttribute(r9, r4, r2)
            goto L_0x008d
        L_0x0089:
            r7.resolveAttribute(r9, r4, r2)
            r8 = r6
        L_0x008d:
            int r9 = r4.resourceId
            if (r9 == 0) goto L_0x00a3
            if (r8 != 0) goto L_0x009e
            android.content.res.Resources r8 = r5.getResources()
            android.content.res.Resources$Theme r8 = r8.newTheme()
            r8.setTo(r7)
        L_0x009e:
            int r4 = r4.resourceId
            r8.applyStyle(r4, r2)
        L_0x00a3:
            if (r8 == 0) goto L_0x00b2
            android.support.v7.view.ContextThemeWrapper r4 = new android.support.v7.view.ContextThemeWrapper
            r4.<init>(r5, r1)
            android.content.res.Resources$Theme r5 = r4.getTheme()
            r5.setTo(r8)
            goto L_0x00b3
        L_0x00b2:
            r4 = r5
        L_0x00b3:
            android.support.v7.view.menu.MenuBuilder r5 = new android.support.v7.view.menu.MenuBuilder
            r5.<init>(r4)
            r5.setCallback(r11)
            r12.setMenu(r5)
            android.support.v7.view.menu.MenuBuilder r4 = r12.menu
            if (r4 != 0) goto L_0x00c3
            return r1
        L_0x00c3:
            if (r3 == 0) goto L_0x00dd
            android.support.v7.widget.DecorContentParent r4 = r11.mDecorContentParent
            if (r4 == 0) goto L_0x00dd
            android.support.v7.app.AppCompatDelegateImpl$ActionMenuPresenterCallback r4 = r11.mActionMenuPresenterCallback
            if (r4 != 0) goto L_0x00d4
            android.support.v7.app.AppCompatDelegateImpl$ActionMenuPresenterCallback r4 = new android.support.v7.app.AppCompatDelegateImpl$ActionMenuPresenterCallback
            r4.<init>()
            r11.mActionMenuPresenterCallback = r4
        L_0x00d4:
            android.support.v7.widget.DecorContentParent r4 = r11.mDecorContentParent
            android.support.v7.view.menu.MenuBuilder r5 = r12.menu
            android.support.v7.app.AppCompatDelegateImpl$ActionMenuPresenterCallback r7 = r11.mActionMenuPresenterCallback
            r4.setMenu(r5, r7)
        L_0x00dd:
            android.support.v7.view.menu.MenuBuilder r4 = r12.menu
            r4.stopDispatchingItemsChanged()
            int r4 = r12.featureId
            android.support.v7.view.menu.MenuBuilder r5 = r12.menu
            boolean r4 = r0.onCreatePanelMenu(r4, r5)
            if (r4 != 0) goto L_0x00fb
            r12.setMenu(r6)
            if (r3 == 0) goto L_0x00fa
            android.support.v7.widget.DecorContentParent r12 = r11.mDecorContentParent
            if (r12 == 0) goto L_0x00fa
            android.support.v7.app.AppCompatDelegateImpl$ActionMenuPresenterCallback r11 = r11.mActionMenuPresenterCallback
            r12.setMenu(r6, r11)
        L_0x00fa:
            return r1
        L_0x00fb:
            r12.refreshMenuContent = r1
        L_0x00fd:
            android.support.v7.view.menu.MenuBuilder r4 = r12.menu
            r4.stopDispatchingItemsChanged()
            android.os.Bundle r4 = r12.frozenActionViewState
            if (r4 == 0) goto L_0x010d
            android.support.v7.view.menu.MenuBuilder r5 = r12.menu
            r5.restoreActionViewStates(r4)
            r12.frozenActionViewState = r6
        L_0x010d:
            android.view.View r4 = r12.createdPanelView
            android.support.v7.view.menu.MenuBuilder r5 = r12.menu
            boolean r0 = r0.onPreparePanel(r1, r4, r5)
            if (r0 != 0) goto L_0x0128
            if (r3 == 0) goto L_0x0122
            android.support.v7.widget.DecorContentParent r13 = r11.mDecorContentParent
            if (r13 == 0) goto L_0x0122
            android.support.v7.app.AppCompatDelegateImpl$ActionMenuPresenterCallback r11 = r11.mActionMenuPresenterCallback
            r13.setMenu(r6, r11)
        L_0x0122:
            android.support.v7.view.menu.MenuBuilder r11 = r12.menu
            r11.startDispatchingItemsChanged()
            return r1
        L_0x0128:
            if (r13 == 0) goto L_0x012f
            int r13 = r13.getDeviceId()
            goto L_0x0130
        L_0x012f:
            r13 = -1
        L_0x0130:
            android.view.KeyCharacterMap r13 = android.view.KeyCharacterMap.load(r13)
            int r13 = r13.getKeyboardType()
            if (r13 == r2) goto L_0x013c
            r13 = r2
            goto L_0x013d
        L_0x013c:
            r13 = r1
        L_0x013d:
            r12.qwertyMode = r13
            android.support.v7.view.menu.MenuBuilder r13 = r12.menu
            boolean r0 = r12.qwertyMode
            r13.setQwertyMode(r0)
            android.support.v7.view.menu.MenuBuilder r13 = r12.menu
            r13.startDispatchingItemsChanged()
        L_0x014b:
            r12.isPrepared = r2
            r12.isHandled = r1
            r11.mPreparedPanel = r12
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.app.AppCompatDelegateImpl.preparePanel(android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState, android.view.KeyEvent):boolean");
    }

    private void throwFeatureRequestIfSubDecorInstalled() {
        if (this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ensureSubDecor();
        ((ViewGroup) this.mSubDecor.findViewById(16908290)).addView(view, layoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004c, code lost:
        if ((r6.getPackageManager().getActivityInfo(new android.content.ComponentName(r10.mContext, r10.mContext.getClass()), 0).configChanges & 512) == 0) goto L_0x0057;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean applyDayNight() {
        /*
            r10 = this;
            int r0 = r10.mLocalNightMode
            r1 = -100
            r2 = -1
            if (r0 == r1) goto L_0x0008
            goto L_0x0009
        L_0x0008:
            r0 = r2
        L_0x0009:
            int r1 = r10.mapNightMode(r0)
            r3 = 1
            r4 = 0
            if (r1 == r2) goto L_0x0078
            android.content.Context r2 = r10.mContext
            android.content.res.Resources r2 = r2.getResources()
            android.content.res.Configuration r5 = r2.getConfiguration()
            int r6 = r5.uiMode
            r6 = r6 & 48
            r7 = 2
            if (r1 != r7) goto L_0x0025
            r1 = 32
            goto L_0x0027
        L_0x0025:
            r1 = 16
        L_0x0027:
            if (r6 == r1) goto L_0x0078
            boolean r6 = r10.mApplyDayNightCalled
            if (r6 == 0) goto L_0x0058
            android.content.Context r6 = r10.mContext
            boolean r7 = r6 instanceof android.app.Activity
            if (r7 == 0) goto L_0x0058
            android.content.pm.PackageManager r6 = r6.getPackageManager()
            android.content.ComponentName r7 = new android.content.ComponentName     // Catch:{ NameNotFoundException -> 0x004f }
            android.content.Context r8 = r10.mContext     // Catch:{ NameNotFoundException -> 0x004f }
            android.content.Context r9 = r10.mContext     // Catch:{ NameNotFoundException -> 0x004f }
            java.lang.Class r9 = r9.getClass()     // Catch:{ NameNotFoundException -> 0x004f }
            r7.<init>(r8, r9)     // Catch:{ NameNotFoundException -> 0x004f }
            android.content.pm.ActivityInfo r6 = r6.getActivityInfo(r7, r4)     // Catch:{ NameNotFoundException -> 0x004f }
            int r6 = r6.configChanges     // Catch:{ NameNotFoundException -> 0x004f }
            r6 = r6 & 512(0x200, float:7.175E-43)
            if (r6 != 0) goto L_0x0058
            goto L_0x0057
        L_0x004f:
            r4 = move-exception
            java.lang.String r6 = "AppCompatDelegate"
            java.lang.String r7 = "Exception while getting ActivityInfo"
            android.util.Log.d(r6, r7, r4)
        L_0x0057:
            r4 = r3
        L_0x0058:
            if (r4 == 0) goto L_0x0062
            android.content.Context r1 = r10.mContext
            android.app.Activity r1 = (android.app.Activity) r1
            r1.recreate()
            goto L_0x0077
        L_0x0062:
            android.content.res.Configuration r4 = new android.content.res.Configuration
            r4.<init>(r5)
            android.util.DisplayMetrics r5 = r2.getDisplayMetrics()
            int r6 = r4.uiMode
            r6 = r6 & -49
            r1 = r1 | r6
            r4.uiMode = r1
            r2.updateConfiguration(r4, r5)
            int r1 = android.os.Build.VERSION.SDK_INT
        L_0x0077:
            r4 = r3
        L_0x0078:
            if (r0 != 0) goto L_0x0082
            r10.ensureAutoNightModeManager()
            android.support.v7.app.AppCompatDelegateImpl$AutoNightModeManager r0 = r10.mAutoNightModeManager
            r0.setup()
        L_0x0082:
            r10.mApplyDayNightCalled = r3
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.app.AppCompatDelegateImpl.applyDayNight():boolean");
    }

    /* access modifiers changed from: package-private */
    public void callOnPanelClosed(int i, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.mPanels;
                if (i < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.menu;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.isOpen) && !this.mIsDestroyed) {
            this.mOriginalWindowCallback.onPanelClosed(i, menu);
        }
    }

    /* access modifiers changed from: package-private */
    public void checkCloseActionMenu(MenuBuilder menuBuilder) {
        if (!this.mClosingActionMenu) {
            this.mClosingActionMenu = true;
            this.mDecorContentParent.dismissPopups();
            Window.Callback windowCallback = getWindowCallback();
            if (windowCallback != null && !this.mIsDestroyed) {
                windowCallback.onPanelClosed(108, menuBuilder);
            }
            this.mClosingActionMenu = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void closePanel(int i) {
        closePanel(getPanelState(i, true), true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0072, code lost:
        if (((org.xmlpull.v1.XmlPullParser) r15).getDepth() > 1) goto L_0x0074;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View createView(android.view.View r12, java.lang.String r13, android.content.Context r14, android.util.AttributeSet r15) {
        /*
            r11 = this;
            android.support.v7.app.AppCompatViewInflater r0 = r11.mAppCompatViewInflater
            r1 = 0
            if (r0 != 0) goto L_0x0062
            android.content.Context r0 = r11.mContext
            int[] r2 = android.support.p002v7.appcompat.R$styleable.AppCompatTheme
            android.content.res.TypedArray r0 = r0.obtainStyledAttributes(r2)
            r2 = 110(0x6e, float:1.54E-43)
            java.lang.String r0 = r0.getString(r2)
            if (r0 == 0) goto L_0x005b
            java.lang.Class<android.support.v7.app.AppCompatViewInflater> r2 = android.support.p002v7.app.AppCompatViewInflater.class
            java.lang.String r2 = r2.getName()
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0022
            goto L_0x005b
        L_0x0022:
            java.lang.Class r2 = java.lang.Class.forName(r0)     // Catch:{ all -> 0x0037 }
            java.lang.Class[] r3 = new java.lang.Class[r1]     // Catch:{ all -> 0x0037 }
            java.lang.reflect.Constructor r2 = r2.getDeclaredConstructor(r3)     // Catch:{ all -> 0x0037 }
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ all -> 0x0037 }
            java.lang.Object r2 = r2.newInstance(r3)     // Catch:{ all -> 0x0037 }
            android.support.v7.app.AppCompatViewInflater r2 = (android.support.p002v7.app.AppCompatViewInflater) r2     // Catch:{ all -> 0x0037 }
            r11.mAppCompatViewInflater = r2     // Catch:{ all -> 0x0037 }
            goto L_0x0062
        L_0x0037:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Failed to instantiate custom view inflater "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = ". Falling back to default."
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            java.lang.String r3 = "AppCompatDelegate"
            android.util.Log.i(r3, r0, r2)
            android.support.v7.app.AppCompatViewInflater r0 = new android.support.v7.app.AppCompatViewInflater
            r0.<init>()
            r11.mAppCompatViewInflater = r0
            goto L_0x0062
        L_0x005b:
            android.support.v7.app.AppCompatViewInflater r0 = new android.support.v7.app.AppCompatViewInflater
            r0.<init>()
            r11.mAppCompatViewInflater = r0
        L_0x0062:
            boolean r0 = IS_PRE_LOLLIPOP
            if (r0 == 0) goto L_0x009a
            boolean r0 = r15 instanceof org.xmlpull.v1.XmlPullParser
            r2 = 1
            if (r0 == 0) goto L_0x0076
            r0 = r15
            org.xmlpull.v1.XmlPullParser r0 = (org.xmlpull.v1.XmlPullParser) r0
            int r0 = r0.getDepth()
            if (r0 <= r2) goto L_0x009a
        L_0x0074:
            r1 = r2
            goto L_0x009a
        L_0x0076:
            r0 = r12
            android.view.ViewParent r0 = (android.view.ViewParent) r0
            if (r0 != 0) goto L_0x007c
            goto L_0x009a
        L_0x007c:
            android.view.Window r3 = r11.mWindow
            android.view.View r3 = r3.getDecorView()
        L_0x0082:
            if (r0 != 0) goto L_0x0085
            goto L_0x0074
        L_0x0085:
            if (r0 == r3) goto L_0x009a
            boolean r4 = r0 instanceof android.view.View
            if (r4 == 0) goto L_0x009a
            r4 = r0
            android.view.View r4 = (android.view.View) r4
            boolean r4 = android.support.p000v4.view.ViewCompat.isAttachedToWindow(r4)
            if (r4 == 0) goto L_0x0095
            goto L_0x009a
        L_0x0095:
            android.view.ViewParent r0 = r0.getParent()
            goto L_0x0082
        L_0x009a:
            r7 = r1
            android.support.v7.app.AppCompatViewInflater r2 = r11.mAppCompatViewInflater
            boolean r8 = IS_PRE_LOLLIPOP
            r9 = 1
            android.support.p002v7.widget.VectorEnabledTintResources.shouldBeUsed()
            r10 = 0
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            android.view.View r11 = r2.createView(r3, r4, r5, r6, r7, r8, r9, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.app.AppCompatDelegateImpl.createView(android.view.View, java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }

    /* access modifiers changed from: package-private */
    public void dismissPopups() {
        MenuBuilder menuBuilder;
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent != null) {
            decorContentParent.dismissPopups();
        }
        if (this.mActionModePopup != null) {
            this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
            if (this.mActionModePopup.isShowing()) {
                try {
                    this.mActionModePopup.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.mActionModePopup = null;
        }
        endOnGoingFadeAnimation();
        PanelFeatureState panelState = getPanelState(0, false);
        if (panelState != null && (menuBuilder = panelState.menu) != null) {
            menuBuilder.close(true);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        ViewCompat.dispatchUnhandledKeyEventPre(this.mWindow.getDecorView(), keyEvent);
        if (keyEvent.getKeyCode() == 82 && this.mOriginalWindowCallback.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (!(keyEvent.getAction() == 0) ? onKeyUp(keyCode, keyEvent) : onKeyDown(keyCode, keyEvent)) {
            return true;
        }
        int i = Build.VERSION.SDK_INT;
        return false;
    }

    /* access modifiers changed from: package-private */
    public void doInvalidatePanelMenu(int i) {
        PanelFeatureState panelState;
        PanelFeatureState panelState2 = getPanelState(i, true);
        if (panelState2.menu != null) {
            Bundle bundle = new Bundle();
            panelState2.menu.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                panelState2.frozenActionViewState = bundle;
            }
            panelState2.menu.stopDispatchingItemsChanged();
            panelState2.menu.clear();
        }
        panelState2.refreshMenuContent = true;
        panelState2.refreshDecorView = true;
        if ((i == 108 || i == 0) && this.mDecorContentParent != null && (panelState = getPanelState(0, false)) != null) {
            panelState.isPrepared = false;
            preparePanel(panelState, (KeyEvent) null);
        }
    }

    /* access modifiers changed from: package-private */
    public void endOnGoingFadeAnimation() {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mFadeAnim;
        if (viewPropertyAnimatorCompat != null) {
            viewPropertyAnimatorCompat.cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public PanelFeatureState findMenuPanel(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i = 0; i < length; i++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
            if (panelFeatureState != null && panelFeatureState.menu == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    public <T extends View> T findViewById(int i) {
        ensureSubDecor();
        return this.mWindow.findViewById(i);
    }

    /* access modifiers changed from: package-private */
    public final Context getActionBarThemedContext() {
        ActionBar supportActionBar = getSupportActionBar();
        Context themedContext = supportActionBar != null ? supportActionBar.getThemedContext() : null;
        return themedContext == null ? this.mContext : themedContext;
    }

    /* access modifiers changed from: package-private */
    public final AutoNightModeManager getAutoNightModeManager() {
        ensureAutoNightModeManager();
        return this.mAutoNightModeManager;
    }

    public MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            initWindowDecorActionBar();
            ActionBar actionBar = this.mActionBar;
            this.mMenuInflater = new SupportMenuInflater(actionBar != null ? actionBar.getThemedContext() : this.mContext);
        }
        return this.mMenuInflater;
    }

    /* access modifiers changed from: protected */
    public PanelFeatureState getPanelState(int i, boolean z) {
        PanelFeatureState[] panelFeatureStateArr = this.mPanels;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[(i + 1)];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.mPanels = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i);
        panelFeatureStateArr[i] = panelFeatureState2;
        return panelFeatureState2;
    }

    public ActionBar getSupportActionBar() {
        initWindowDecorActionBar();
        return this.mActionBar;
    }

    /* access modifiers changed from: package-private */
    public final CharSequence getTitle() {
        Window.Callback callback = this.mOriginalWindowCallback;
        if (callback instanceof Activity) {
            return ((Activity) callback).getTitle();
        }
        return this.mTitle;
    }

    /* access modifiers changed from: package-private */
    public final Window.Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }

    public void installViewFactory() {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        if (from.getFactory() == null) {
            from.setFactory2(this);
            int i = Build.VERSION.SDK_INT;
        } else if (!(from.getFactory2() instanceof AppCompatDelegateImpl)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public void invalidateOptionsMenu() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.invalidateOptionsMenu()) {
            invalidatePanelMenu(0);
        }
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }

    /* access modifiers changed from: package-private */
    public int mapNightMode(int i) {
        if (i == -100) {
            return -1;
        }
        if (i != 0) {
            return i;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (((UiModeManager) this.mContext.getSystemService(UiModeManager.class)).getNightMode() == 0) {
            return -1;
        }
        ensureAutoNightModeManager();
        return this.mAutoNightModeManager.getApplyableNightMode();
    }

    /* access modifiers changed from: package-private */
    public boolean onBackPressed() {
        android.support.p002v7.view.ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            actionMode.finish();
            return true;
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.collapseActionView()) {
            return false;
        }
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        ActionBar supportActionBar;
        if (this.mHasActionBar && this.mSubDecorInstalled && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.onConfigurationChanged(configuration);
        }
        AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
        applyDayNight();
    }

    public void onCreate(Bundle bundle) {
        Window.Callback callback = this.mOriginalWindowCallback;
        if (callback instanceof Activity) {
            String str = null;
            try {
                Activity activity = (Activity) callback;
                str = R$dimen.getParentActivityName(activity, activity.getComponentName());
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalArgumentException(e);
            } catch (IllegalArgumentException unused) {
            }
            if (str != null) {
                ActionBar peekSupportActionBar = peekSupportActionBar();
                if (peekSupportActionBar == null) {
                    this.mEnableDefaultActionBarUp = true;
                } else {
                    peekSupportActionBar.setDefaultDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (bundle != null && this.mLocalNightMode == -100) {
            this.mLocalNightMode = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return createView(view, str, context, attributeSet);
    }

    public void onDestroy() {
        if (this.mInvalidatePanelMenuPosted) {
            this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
        }
        this.mIsDestroyed = true;
        ActionBar actionBar = this.mActionBar;
        if (actionBar != null) {
            actionBar.onDestroy();
        }
        AutoNightModeManager autoNightModeManager = this.mAutoNightModeManager;
        if (autoNightModeManager != null) {
            autoNightModeManager.cleanup();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = true;
        if (i == 4) {
            if ((keyEvent.getFlags() & 128) == 0) {
                z = false;
            }
            this.mLongPressBackDown = z;
        } else if (i == 82) {
            if (keyEvent.getRepeatCount() == 0) {
                PanelFeatureState panelState = getPanelState(0, true);
                if (!panelState.isOpen) {
                    preparePanel(panelState, keyEvent);
                }
            }
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null && supportActionBar.onKeyShortcut(i, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.mPreparedPanel;
        if (panelFeatureState == null || !performPanelShortcut(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            if (this.mPreparedPanel == null) {
                PanelFeatureState panelState = getPanelState(0, true);
                preparePanel(panelState, keyEvent);
                boolean performPanelShortcut = performPanelShortcut(panelState, keyEvent.getKeyCode(), keyEvent, 1);
                panelState.isPrepared = false;
                if (performPanelShortcut) {
                    return true;
                }
            }
            return false;
        }
        PanelFeatureState panelFeatureState2 = this.mPreparedPanel;
        if (panelFeatureState2 != null) {
            panelFeatureState2.isHandled = true;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onKeyUp(int r4, android.view.KeyEvent r5) {
        /*
            r3 = this;
            r0 = 4
            r1 = 1
            r2 = 0
            if (r4 == r0) goto L_0x008d
            r0 = 82
            if (r4 == r0) goto L_0x000b
            goto L_0x00a8
        L_0x000b:
            android.support.v7.view.ActionMode r4 = r3.mActionMode
            if (r4 == 0) goto L_0x0011
            goto L_0x008c
        L_0x0011:
            android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState r4 = r3.getPanelState(r2, r1)
            android.support.v7.widget.DecorContentParent r0 = r3.mDecorContentParent
            if (r0 == 0) goto L_0x004b
            boolean r0 = r0.canShowOverflowMenu()
            if (r0 == 0) goto L_0x004b
            android.content.Context r0 = r3.mContext
            android.view.ViewConfiguration r0 = android.view.ViewConfiguration.get(r0)
            boolean r0 = r0.hasPermanentMenuKey()
            if (r0 != 0) goto L_0x004b
            android.support.v7.widget.DecorContentParent r0 = r3.mDecorContentParent
            boolean r0 = r0.isOverflowMenuShowing()
            if (r0 != 0) goto L_0x0044
            boolean r0 = r3.mIsDestroyed
            if (r0 != 0) goto L_0x006b
            boolean r4 = r3.preparePanel(r4, r5)
            if (r4 == 0) goto L_0x006b
            android.support.v7.widget.DecorContentParent r4 = r3.mDecorContentParent
            boolean r4 = r4.showOverflowMenu()
            goto L_0x0073
        L_0x0044:
            android.support.v7.widget.DecorContentParent r4 = r3.mDecorContentParent
            boolean r4 = r4.hideOverflowMenu()
            goto L_0x0073
        L_0x004b:
            boolean r0 = r4.isOpen
            if (r0 != 0) goto L_0x006d
            boolean r0 = r4.isHandled
            if (r0 == 0) goto L_0x0054
            goto L_0x006d
        L_0x0054:
            boolean r0 = r4.isPrepared
            if (r0 == 0) goto L_0x006b
            boolean r0 = r4.refreshMenuContent
            if (r0 == 0) goto L_0x0063
            r4.isPrepared = r2
            boolean r0 = r3.preparePanel(r4, r5)
            goto L_0x0064
        L_0x0063:
            r0 = r1
        L_0x0064:
            if (r0 == 0) goto L_0x006b
            r3.openPanel(r4, r5)
            r4 = r1
            goto L_0x0073
        L_0x006b:
            r4 = r2
            goto L_0x0073
        L_0x006d:
            boolean r5 = r4.isOpen
            r3.closePanel(r4, r1)
            r4 = r5
        L_0x0073:
            if (r4 == 0) goto L_0x008c
            android.content.Context r3 = r3.mContext
            java.lang.String r4 = "audio"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.media.AudioManager r3 = (android.media.AudioManager) r3
            if (r3 == 0) goto L_0x0085
            r3.playSoundEffect(r2)
            goto L_0x008c
        L_0x0085:
            java.lang.String r3 = "AppCompatDelegate"
            java.lang.String r4 = "Couldn't get audio manager"
            android.util.Log.w(r3, r4)
        L_0x008c:
            return r1
        L_0x008d:
            boolean r4 = r3.mLongPressBackDown
            r3.mLongPressBackDown = r2
            android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState r5 = r3.getPanelState(r2, r2)
            if (r5 == 0) goto L_0x00a1
            boolean r0 = r5.isOpen
            if (r0 == 0) goto L_0x00a1
            if (r4 != 0) goto L_0x00a0
            r3.closePanel(r5, r1)
        L_0x00a0:
            return r1
        L_0x00a1:
            boolean r3 = r3.onBackPressed()
            if (r3 == 0) goto L_0x00a8
            return r1
        L_0x00a8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.app.AppCompatDelegateImpl.onKeyUp(int, android.view.KeyEvent):boolean");
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        PanelFeatureState findMenuPanel;
        Window.Callback windowCallback = getWindowCallback();
        if (windowCallback == null || this.mIsDestroyed || (findMenuPanel = findMenuPanel(menuBuilder.getRootMenu())) == null) {
            return false;
        }
        return windowCallback.onMenuItemSelected(findMenuPanel.featureId, menuItem);
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent == null || !decorContentParent.canShowOverflowMenu() || (ViewConfiguration.get(this.mContext).hasPermanentMenuKey() && !this.mDecorContentParent.isOverflowMenuShowPending())) {
            PanelFeatureState panelState = getPanelState(0, true);
            panelState.refreshDecorView = true;
            closePanel(panelState, false);
            openPanel(panelState, (KeyEvent) null);
            return;
        }
        Window.Callback windowCallback = getWindowCallback();
        if (this.mDecorContentParent.isOverflowMenuShowing()) {
            this.mDecorContentParent.hideOverflowMenu();
            if (!this.mIsDestroyed) {
                windowCallback.onPanelClosed(108, getPanelState(0, true).menu);
            }
        } else if (windowCallback != null && !this.mIsDestroyed) {
            if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 1) != 0) {
                this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
                this.mInvalidatePanelMenuRunnable.run();
            }
            PanelFeatureState panelState2 = getPanelState(0, true);
            MenuBuilder menuBuilder2 = panelState2.menu;
            if (menuBuilder2 != null && !panelState2.refreshMenuContent && windowCallback.onPreparePanel(0, panelState2.createdPanelView, menuBuilder2)) {
                windowCallback.onMenuOpened(108, panelState2.menu);
                this.mDecorContentParent.showOverflowMenu();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onMenuOpened(int i) {
        ActionBar supportActionBar;
        if (i == 108 && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.dispatchMenuVisibilityChanged(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void onPanelClosed(int i) {
        if (i == 108) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.dispatchMenuVisibilityChanged(false);
            }
        } else if (i == 0) {
            PanelFeatureState panelState = getPanelState(i, true);
            if (panelState.isOpen) {
                closePanel(panelState, false);
            }
        }
    }

    public void onPostCreate(Bundle bundle) {
        ensureSubDecor();
    }

    public void onPostResume() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(true);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        int i = this.mLocalNightMode;
        if (i != -100) {
            bundle.putInt("appcompat:local_night_mode", i);
        }
    }

    public void onStart() {
        applyDayNight();
    }

    public void onStop() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
        }
        AutoNightModeManager autoNightModeManager = this.mAutoNightModeManager;
        if (autoNightModeManager != null) {
            autoNightModeManager.cleanup();
        }
    }

    /* access modifiers changed from: package-private */
    public void onSubDecorInstalled(ViewGroup viewGroup) {
    }

    /* access modifiers changed from: package-private */
    public final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    public boolean requestWindowFeature(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            i = 108;
        } else if (i == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            i = 109;
        }
        if (this.mWindowNoTitle && i == 108) {
            return false;
        }
        if (this.mHasActionBar && i == 1) {
            this.mHasActionBar = false;
        }
        if (i == 1) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mWindowNoTitle = true;
            return true;
        } else if (i == 2) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mFeatureProgress = true;
            return true;
        } else if (i == 5) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mFeatureIndeterminateProgress = true;
            return true;
        } else if (i == 10) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mOverlayActionMode = true;
            return true;
        } else if (i == 108) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mHasActionBar = true;
            return true;
        } else if (i != 109) {
            return this.mWindow.requestFeature(i);
        } else {
            throwFeatureRequestIfSubDecorInstalled();
            this.mOverlayActionBar = true;
            return true;
        }
    }

    public void setContentView(View view) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setSupportActionBar(Toolbar toolbar) {
        if (this.mOriginalWindowCallback instanceof Activity) {
            ActionBar supportActionBar = getSupportActionBar();
            if (!(supportActionBar instanceof WindowDecorActionBar)) {
                this.mMenuInflater = null;
                if (supportActionBar != null) {
                    supportActionBar.onDestroy();
                }
                if (toolbar != null) {
                    ToolbarActionBar toolbarActionBar = new ToolbarActionBar(toolbar, ((Activity) this.mOriginalWindowCallback).getTitle(), this.mAppCompatWindowCallback);
                    this.mActionBar = toolbarActionBar;
                    this.mWindow.setCallback(toolbarActionBar.mWindowCallback);
                } else {
                    this.mActionBar = null;
                    this.mWindow.setCallback(this.mAppCompatWindowCallback);
                }
                invalidateOptionsMenu();
                return;
            }
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
    }

    public final void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent != null) {
            decorContentParent.setWindowTitle(charSequence);
        } else if (peekSupportActionBar() != null) {
            peekSupportActionBar().setWindowTitle(charSequence);
        } else {
            TextView textView = this.mTitleView;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1.mSubDecor;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean shouldAnimateActionModeView() {
        /*
            r1 = this;
            boolean r0 = r1.mSubDecorInstalled
            if (r0 == 0) goto L_0x0010
            android.view.ViewGroup r1 = r1.mSubDecor
            if (r1 == 0) goto L_0x0010
            boolean r1 = android.support.p000v4.view.ViewCompat.isLaidOut(r1)
            if (r1 == 0) goto L_0x0010
            r1 = 1
            goto L_0x0011
        L_0x0010:
            r1 = 0
        L_0x0011:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.app.AppCompatDelegateImpl.shouldAnimateActionModeView():boolean");
    }

    public android.support.p002v7.view.ActionMode startSupportActionMode(ActionMode.Callback callback) {
        AppCompatCallback appCompatCallback;
        if (callback != null) {
            android.support.p002v7.view.ActionMode actionMode = this.mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
            ActionModeCallbackWrapperV9 actionModeCallbackWrapperV9 = new ActionModeCallbackWrapperV9(callback);
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                this.mActionMode = supportActionBar.startActionMode(actionModeCallbackWrapperV9);
                android.support.p002v7.view.ActionMode actionMode2 = this.mActionMode;
                if (!(actionMode2 == null || (appCompatCallback = this.mAppCompatCallback) == null)) {
                    appCompatCallback.onSupportActionModeStarted(actionMode2);
                }
            }
            if (this.mActionMode == null) {
                this.mActionMode = startSupportActionModeFromWindow(actionModeCallbackWrapperV9);
            }
            return this.mActionMode;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.p002v7.view.ActionMode startSupportActionModeFromWindow(android.support.p002v7.view.ActionMode.Callback r9) {
        /*
            r8 = this;
            r8.endOnGoingFadeAnimation()
            android.support.v7.view.ActionMode r0 = r8.mActionMode
            if (r0 == 0) goto L_0x000a
            r0.finish()
        L_0x000a:
            boolean r0 = r9 instanceof android.support.p002v7.app.AppCompatDelegateImpl.ActionModeCallbackWrapperV9
            if (r0 != 0) goto L_0x0014
            android.support.v7.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9 r0 = new android.support.v7.app.AppCompatDelegateImpl$ActionModeCallbackWrapperV9
            r0.<init>(r9)
            r9 = r0
        L_0x0014:
            android.support.v7.app.AppCompatCallback r0 = r8.mAppCompatCallback
            r1 = 0
            if (r0 == 0) goto L_0x0022
            boolean r2 = r8.mIsDestroyed
            if (r2 != 0) goto L_0x0022
            android.support.v7.view.ActionMode r0 = r0.onWindowStartingSupportActionMode(r9)     // Catch:{ AbstractMethodError -> 0x0022 }
            goto L_0x0023
        L_0x0022:
            r0 = r1
        L_0x0023:
            if (r0 == 0) goto L_0x0029
            r8.mActionMode = r0
            goto L_0x016b
        L_0x0029:
            android.support.v7.widget.ActionBarContextView r0 = r8.mActionModeView
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L_0x00dc
            boolean r0 = r8.mIsFloating
            if (r0 == 0) goto L_0x00bc
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            android.content.Context r4 = r8.mContext
            android.content.res.Resources$Theme r4 = r4.getTheme()
            r5 = 2130968585(0x7f040009, float:1.7545828E38)
            r4.resolveAttribute(r5, r0, r3)
            int r5 = r0.resourceId
            if (r5 == 0) goto L_0x0069
            android.content.Context r5 = r8.mContext
            android.content.res.Resources r5 = r5.getResources()
            android.content.res.Resources$Theme r5 = r5.newTheme()
            r5.setTo(r4)
            int r4 = r0.resourceId
            r5.applyStyle(r4, r3)
            android.support.v7.view.ContextThemeWrapper r4 = new android.support.v7.view.ContextThemeWrapper
            android.content.Context r6 = r8.mContext
            r4.<init>(r6, r2)
            android.content.res.Resources$Theme r6 = r4.getTheme()
            r6.setTo(r5)
            goto L_0x006b
        L_0x0069:
            android.content.Context r4 = r8.mContext
        L_0x006b:
            android.support.v7.widget.ActionBarContextView r5 = new android.support.v7.widget.ActionBarContextView
            r5.<init>(r4)
            r8.mActionModeView = r5
            android.widget.PopupWindow r5 = new android.widget.PopupWindow
            r6 = 2130968599(0x7f040017, float:1.7545856E38)
            r5.<init>(r4, r1, r6)
            r8.mActionModePopup = r5
            android.widget.PopupWindow r5 = r8.mActionModePopup
            r6 = 2
            int r7 = android.os.Build.VERSION.SDK_INT
            r5.setWindowLayoutType(r6)
            android.widget.PopupWindow r5 = r8.mActionModePopup
            android.support.v7.widget.ActionBarContextView r6 = r8.mActionModeView
            r5.setContentView(r6)
            android.widget.PopupWindow r5 = r8.mActionModePopup
            r6 = -1
            r5.setWidth(r6)
            android.content.res.Resources$Theme r5 = r4.getTheme()
            r6 = 2130968579(0x7f040003, float:1.7545816E38)
            r5.resolveAttribute(r6, r0, r3)
            int r0 = r0.data
            android.content.res.Resources r4 = r4.getResources()
            android.util.DisplayMetrics r4 = r4.getDisplayMetrics()
            int r0 = android.util.TypedValue.complexToDimensionPixelSize(r0, r4)
            android.support.v7.widget.ActionBarContextView r4 = r8.mActionModeView
            r4.setContentHeight(r0)
            android.widget.PopupWindow r0 = r8.mActionModePopup
            r4 = -2
            r0.setHeight(r4)
            android.support.v7.app.AppCompatDelegateImpl$6 r0 = new android.support.v7.app.AppCompatDelegateImpl$6
            r0.<init>()
            r8.mShowActionModePopup = r0
            goto L_0x00dc
        L_0x00bc:
            android.view.ViewGroup r0 = r8.mSubDecor
            r4 = 2131296282(0x7f09001a, float:1.8210476E38)
            android.view.View r0 = r0.findViewById(r4)
            android.support.v7.widget.ViewStubCompat r0 = (android.support.p002v7.widget.ViewStubCompat) r0
            if (r0 == 0) goto L_0x00dc
            android.content.Context r4 = r8.getActionBarThemedContext()
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            r0.setLayoutInflater(r4)
            android.view.View r0 = r0.inflate()
            android.support.v7.widget.ActionBarContextView r0 = (android.support.p002v7.widget.ActionBarContextView) r0
            r8.mActionModeView = r0
        L_0x00dc:
            android.support.v7.widget.ActionBarContextView r0 = r8.mActionModeView
            if (r0 == 0) goto L_0x016b
            r8.endOnGoingFadeAnimation()
            android.support.v7.widget.ActionBarContextView r0 = r8.mActionModeView
            r0.killMode()
            android.support.v7.view.StandaloneActionMode r0 = new android.support.v7.view.StandaloneActionMode
            android.support.v7.widget.ActionBarContextView r4 = r8.mActionModeView
            android.content.Context r4 = r4.getContext()
            android.support.v7.widget.ActionBarContextView r5 = r8.mActionModeView
            android.widget.PopupWindow r6 = r8.mActionModePopup
            if (r6 != 0) goto L_0x00f7
            goto L_0x00f8
        L_0x00f7:
            r3 = r2
        L_0x00f8:
            r0.<init>(r4, r5, r9, r3)
            android.view.Menu r3 = r0.getMenu()
            boolean r9 = r9.onCreateActionMode(r0, r3)
            if (r9 == 0) goto L_0x0169
            r0.invalidate()
            android.support.v7.widget.ActionBarContextView r9 = r8.mActionModeView
            r9.initForMode(r0)
            r8.mActionMode = r0
            boolean r9 = r8.shouldAnimateActionModeView()
            r0 = 1065353216(0x3f800000, float:1.0)
            if (r9 == 0) goto L_0x0133
            android.support.v7.widget.ActionBarContextView r9 = r8.mActionModeView
            r1 = 0
            r9.setAlpha(r1)
            android.support.v7.widget.ActionBarContextView r9 = r8.mActionModeView
            android.support.v4.view.ViewPropertyAnimatorCompat r9 = android.support.p000v4.view.ViewCompat.animate(r9)
            r9.alpha(r0)
            r8.mFadeAnim = r9
            android.support.v4.view.ViewPropertyAnimatorCompat r9 = r8.mFadeAnim
            android.support.v7.app.AppCompatDelegateImpl$7 r0 = new android.support.v7.app.AppCompatDelegateImpl$7
            r0.<init>()
            r9.setListener(r0)
            goto L_0x0159
        L_0x0133:
            android.support.v7.widget.ActionBarContextView r9 = r8.mActionModeView
            r9.setAlpha(r0)
            android.support.v7.widget.ActionBarContextView r9 = r8.mActionModeView
            r9.setVisibility(r2)
            android.support.v7.widget.ActionBarContextView r9 = r8.mActionModeView
            r0 = 32
            r9.sendAccessibilityEvent(r0)
            android.support.v7.widget.ActionBarContextView r9 = r8.mActionModeView
            android.view.ViewParent r9 = r9.getParent()
            boolean r9 = r9 instanceof android.view.View
            if (r9 == 0) goto L_0x0159
            android.support.v7.widget.ActionBarContextView r9 = r8.mActionModeView
            android.view.ViewParent r9 = r9.getParent()
            android.view.View r9 = (android.view.View) r9
            android.support.p000v4.view.ViewCompat.requestApplyInsets(r9)
        L_0x0159:
            android.widget.PopupWindow r9 = r8.mActionModePopup
            if (r9 == 0) goto L_0x016b
            android.view.Window r9 = r8.mWindow
            android.view.View r9 = r9.getDecorView()
            java.lang.Runnable r0 = r8.mShowActionModePopup
            r9.post(r0)
            goto L_0x016b
        L_0x0169:
            r8.mActionMode = r1
        L_0x016b:
            android.support.v7.view.ActionMode r9 = r8.mActionMode
            if (r9 == 0) goto L_0x0176
            android.support.v7.app.AppCompatCallback r0 = r8.mAppCompatCallback
            if (r0 == 0) goto L_0x0176
            r0.onSupportActionModeStarted(r9)
        L_0x0176:
            android.support.v7.view.ActionMode r8 = r8.mActionMode
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.app.AppCompatDelegateImpl.startSupportActionModeFromWindow(android.support.v7.view.ActionMode$Callback):android.support.v7.view.ActionMode");
    }

    /* access modifiers changed from: package-private */
    public int updateStatusGuard(int i) {
        boolean z;
        boolean z2;
        ActionBarContextView actionBarContextView = this.mActionModeView;
        int i2 = 0;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mActionModeView.getLayoutParams();
            z = true;
            if (this.mActionModeView.isShown()) {
                if (this.mTempRect1 == null) {
                    this.mTempRect1 = new Rect();
                    this.mTempRect2 = new Rect();
                }
                Rect rect = this.mTempRect1;
                Rect rect2 = this.mTempRect2;
                rect.set(0, i, 0, 0);
                ViewUtils.computeFitSystemWindows(this.mSubDecor, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i : 0)) {
                    marginLayoutParams.topMargin = i;
                    View view = this.mStatusGuard;
                    if (view == null) {
                        this.mStatusGuard = new View(this.mContext);
                        this.mStatusGuard.setBackgroundColor(this.mContext.getResources().getColor(R.color.abc_input_method_navigation_guard));
                        this.mSubDecor.addView(this.mStatusGuard, -1, new ViewGroup.LayoutParams(-1, i));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i) {
                            layoutParams.height = i;
                            this.mStatusGuard.setLayoutParams(layoutParams);
                        }
                    }
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (this.mStatusGuard == null) {
                    z = false;
                }
                if (!this.mOverlayActionMode && z) {
                    i = 0;
                }
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z2 = true;
                z = false;
            } else {
                z2 = false;
                z = false;
            }
            if (z2) {
                this.mActionModeView.setLayoutParams(marginLayoutParams);
            }
        }
        View view2 = this.mStatusGuard;
        if (view2 != null) {
            if (!z) {
                i2 = 8;
            }
            view2.setVisibility(i2);
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public void closePanel(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        DecorContentParent decorContentParent;
        if (!z || panelFeatureState.featureId != 0 || (decorContentParent = this.mDecorContentParent) == null || !decorContentParent.isOverflowMenuShowing()) {
            WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
            if (!(windowManager == null || !panelFeatureState.isOpen || (viewGroup = panelFeatureState.decorView) == null)) {
                windowManager.removeView(viewGroup);
                if (z) {
                    callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, (Menu) null);
                }
            }
            panelFeatureState.isPrepared = false;
            panelFeatureState.isHandled = false;
            panelFeatureState.isOpen = false;
            panelFeatureState.shownPanelView = null;
            panelFeatureState.refreshDecorView = true;
            if (this.mPreparedPanel == panelFeatureState) {
                this.mPreparedPanel = null;
                return;
            }
            return;
        }
        checkCloseActionMenu(panelFeatureState.menu);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView((View) null, str, context, attributeSet);
    }

    public void setContentView(int i) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.mContext).inflate(i, viewGroup);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ensureSubDecor();
        ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }
}
