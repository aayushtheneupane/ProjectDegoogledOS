package com.android.systemui.statusbar.phone;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.havoc.Utils;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1784R$string;
import com.android.systemui.C1785R$style;
import com.android.systemui.Dependency;
import com.android.systemui.DockedStackExistsListener;
import com.android.systemui.Interpolators;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.recents.Recents;
import com.android.systemui.recents.RecentsOnboarding;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.shared.system.WindowManagerWrapper;
import com.android.systemui.statusbar.phone.NavigationModeController;
import com.android.systemui.statusbar.policy.DeadZone;
import com.android.systemui.statusbar.policy.KeyButtonDrawable;
import com.android.systemui.statusbar.policy.KeyButtonView;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.function.Consumer;

public class NavigationBarView extends FrameLayout implements NavigationModeController.ModeChangedListener {
    private final Region mActiveRegion;
    private KeyButtonDrawable mArrowLeftIcon;
    private KeyButtonDrawable mArrowRightIcon;
    private Rect mBackButtonBounds;
    private KeyButtonDrawable mBackIcon;
    private final NavigationBarTransitions mBarTransitions;
    private int mBasePaddingBottom;
    private int mBasePaddingLeft;
    private int mBasePaddingRight;
    private int mBasePaddingTop;
    private boolean mBlockedGesturalNavigation;
    private final SparseArray<ButtonDispatcher> mButtonDispatchers;
    private Configuration mConfiguration;
    private final ContextualButtonGroup mContextualButtonGroup;
    private int mCurrentRotation;
    View mCurrentView;
    private final DeadZone mDeadZone;
    private boolean mDeadZoneConsuming;
    int mDisabledFlags;
    private KeyButtonDrawable mDockedIcon;
    private final Consumer<Boolean> mDockedListener;
    private boolean mDockedStackExists;
    private final EdgeBackGestureHandler mEdgeBackGestureHandler;
    private FloatingRotationButton mFloatingRotationButton;
    private Rect mHomeButtonBounds;
    private KeyButtonDrawable mHomeDefaultIcon;
    private View mHorizontal;
    private final View.OnClickListener mImeSwitcherClickListener;
    private boolean mImeVisible;
    private boolean mInCarMode;
    private boolean mIsVertical;
    private boolean mLayoutTransitionsEnabled;
    boolean mLongClickableAccessibilityButton;
    private int mNavBarMode;
    private ViewGroup mNavigationBarContents;
    int mNavigationIconHints;
    private NavigationBarInflaterView mNavigationInflaterView;
    private final ViewTreeObserver.OnComputeInternalInsetsListener mOnComputeInternalInsetsListener;
    private OnVerticalChangedListener mOnVerticalChangedListener;
    private final OverviewProxyService mOverviewProxyService;
    private NotificationPanelView mPanelView;
    private final View.AccessibilityDelegate mQuickStepAccessibilityDelegate;
    private KeyButtonDrawable mRecentIcon;
    private Rect mRecentsButtonBounds;
    private RecentsOnboarding mRecentsOnboarding;
    private Rect mRotationButtonBounds;
    private RotationButtonController mRotationButtonController;
    private ScreenPinningNotify mScreenPinningNotify;
    private boolean mShowGestureNavbar;
    private NavBarTintController mTintController;
    private Configuration mTmpLastConfiguration;
    private int[] mTmpPosition;
    private final NavTransitionListener mTransitionListener;
    private boolean mUseCarModeUi;
    private View mVertical;
    private boolean mWakeAndUnlocking;

    public interface OnVerticalChangedListener {
        void onVerticalChanged(boolean z);
    }

    private static String visibilityToString(int i) {
        return i != 4 ? i != 8 ? "VISIBLE" : "GONE" : "INVISIBLE";
    }

    private class NavTransitionListener implements LayoutTransition.TransitionListener {
        private boolean mBackTransitioning;
        private long mDuration;
        private boolean mHomeAppearing;
        private TimeInterpolator mInterpolator;
        private long mStartDelay;

        private NavTransitionListener() {
        }

        public void startTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
            if (view.getId() == C1777R$id.back) {
                this.mBackTransitioning = true;
            } else if (view.getId() == C1777R$id.home && i == 2) {
                this.mHomeAppearing = true;
                this.mStartDelay = layoutTransition.getStartDelay(i);
                this.mDuration = layoutTransition.getDuration(i);
                this.mInterpolator = layoutTransition.getInterpolator(i);
            }
        }

        public void endTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
            if (view.getId() == C1777R$id.back) {
                this.mBackTransitioning = false;
            } else if (view.getId() == C1777R$id.home && i == 2) {
                this.mHomeAppearing = false;
            }
        }

        public void onBackAltCleared() {
            ButtonDispatcher backButton = NavigationBarView.this.getBackButton();
            if (!this.mBackTransitioning && backButton.getVisibility() == 0 && this.mHomeAppearing && NavigationBarView.this.getHomeButton().getAlpha() == 0.0f) {
                NavigationBarView.this.getBackButton().setAlpha(0.0f);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(backButton, "alpha", new float[]{0.0f, 1.0f});
                ofFloat.setStartDelay(this.mStartDelay);
                ofFloat.setDuration(this.mDuration);
                ofFloat.setInterpolator(this.mInterpolator);
                ofFloat.start();
            }
        }
    }

    public /* synthetic */ void lambda$new$0$NavigationBarView(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        if (!QuickStepContract.isGesturalMode(this.mNavBarMode) || this.mImeVisible) {
            internalInsetsInfo.setTouchableInsets(0);
            return;
        }
        internalInsetsInfo.setTouchableInsets(3);
        ButtonDispatcher imeSwitchButton = getImeSwitchButton();
        if (imeSwitchButton.getVisibility() == 0) {
            int[] iArr = new int[2];
            View currentView = imeSwitchButton.getCurrentView();
            currentView.getLocationInWindow(iArr);
            internalInsetsInfo.touchableRegion.set(iArr[0], iArr[1], iArr[0] + currentView.getWidth(), iArr[1] + currentView.getHeight());
            return;
        }
        internalInsetsInfo.touchableRegion.setEmpty();
    }

    public NavigationBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentView = null;
        this.mCurrentRotation = -1;
        this.mDisabledFlags = 0;
        this.mNavigationIconHints = 0;
        this.mNavBarMode = 0;
        this.mHomeButtonBounds = new Rect();
        this.mBackButtonBounds = new Rect();
        this.mRecentsButtonBounds = new Rect();
        this.mRotationButtonBounds = new Rect();
        this.mActiveRegion = new Region();
        this.mTmpPosition = new int[2];
        this.mDeadZoneConsuming = false;
        this.mTransitionListener = new NavTransitionListener();
        this.mLayoutTransitionsEnabled = true;
        this.mUseCarModeUi = false;
        this.mInCarMode = false;
        this.mButtonDispatchers = new SparseArray<>();
        this.mImeSwitcherClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                ((InputMethodManager) NavigationBarView.this.mContext.getSystemService(InputMethodManager.class)).showInputMethodPickerFromSystem(true, NavigationBarView.this.getContext().getDisplayId());
            }
        };
        this.mQuickStepAccessibilityDelegate = new View.AccessibilityDelegate() {
            private AccessibilityNodeInfo.AccessibilityAction mToggleOverviewAction;

            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                if (this.mToggleOverviewAction == null) {
                    this.mToggleOverviewAction = new AccessibilityNodeInfo.AccessibilityAction(C1777R$id.action_toggle_overview, NavigationBarView.this.getContext().getString(C1784R$string.quick_step_accessibility_toggle_overview));
                }
                accessibilityNodeInfo.addAction(this.mToggleOverviewAction);
            }

            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i != C1777R$id.action_toggle_overview) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                ((Recents) SysUiServiceProvider.getComponent(NavigationBarView.this.getContext(), Recents.class)).toggleRecentApps();
                return true;
            }
        };
        this.mOnComputeInternalInsetsListener = new ViewTreeObserver.OnComputeInternalInsetsListener() {
            public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
                NavigationBarView.this.lambda$new$0$NavigationBarView(internalInsetsInfo);
            }
        };
        this.mDockedListener = new Consumer() {
            public final void accept(Object obj) {
                NavigationBarView.this.lambda$new$2$NavigationBarView((Boolean) obj);
            }
        };
        this.mIsVertical = false;
        this.mLongClickableAccessibilityButton = false;
        this.mNavBarMode = ((NavigationModeController) Dependency.get(NavigationModeController.class)).addListener(this);
        boolean isGesturalMode = QuickStepContract.isGesturalMode(this.mNavBarMode);
        this.mShowGestureNavbar = Utils.shouldShowGestureNav(context);
        this.mContextualButtonGroup = new ContextualButtonGroup(C1777R$id.menu_container);
        ContextualButton contextualButton = new ContextualButton(C1777R$id.ime_switcher, C1776R$drawable.ic_ime_switcher_default);
        RotationContextButton rotationContextButton = new RotationContextButton(C1777R$id.rotate_suggestion, C1776R$drawable.ic_sysbar_rotate_button);
        ContextualButton contextualButton2 = new ContextualButton(C1777R$id.accessibility_button, C1776R$drawable.ic_sysbar_accessibility_button);
        this.mContextualButtonGroup.addButton(contextualButton);
        if (!isGesturalMode) {
            this.mContextualButtonGroup.addButton(rotationContextButton);
        }
        this.mContextualButtonGroup.addButton(contextualButton2);
        this.mOverviewProxyService = (OverviewProxyService) Dependency.get(OverviewProxyService.class);
        this.mRecentsOnboarding = new RecentsOnboarding(context, this.mOverviewProxyService);
        this.mFloatingRotationButton = new FloatingRotationButton(context);
        this.mRotationButtonController = new RotationButtonController(context, C1785R$style.RotateButtonCCWStart90, isGesturalMode ? this.mFloatingRotationButton : rotationContextButton);
        ContextualButton contextualButton3 = new ContextualButton(C1777R$id.back, 0);
        this.mConfiguration = new Configuration();
        this.mTmpLastConfiguration = new Configuration();
        this.mConfiguration.updateFrom(context.getResources().getConfiguration());
        this.mScreenPinningNotify = new ScreenPinningNotify(this.mContext);
        this.mBarTransitions = new NavigationBarTransitions(this);
        this.mButtonDispatchers.put(C1777R$id.back, contextualButton3);
        SparseArray<ButtonDispatcher> sparseArray = this.mButtonDispatchers;
        int i = C1777R$id.home;
        sparseArray.put(i, new ButtonDispatcher(i));
        SparseArray<ButtonDispatcher> sparseArray2 = this.mButtonDispatchers;
        int i2 = C1777R$id.home_handle;
        sparseArray2.put(i2, new ButtonDispatcher(i2));
        SparseArray<ButtonDispatcher> sparseArray3 = this.mButtonDispatchers;
        int i3 = C1777R$id.recent_apps;
        sparseArray3.put(i3, new ButtonDispatcher(i3));
        this.mButtonDispatchers.put(C1777R$id.ime_switcher, contextualButton);
        this.mButtonDispatchers.put(C1777R$id.accessibility_button, contextualButton2);
        this.mButtonDispatchers.put(C1777R$id.rotate_suggestion, rotationContextButton);
        this.mButtonDispatchers.put(C1777R$id.menu_container, this.mContextualButtonGroup);
        this.mDeadZone = new DeadZone(this);
        this.mEdgeBackGestureHandler = new EdgeBackGestureHandler(context, this.mOverviewProxyService);
        this.mTintController = new NavBarTintController(this, getLightTransitionsController());
    }

    public NavBarTintController getTintController() {
        return this.mTintController;
    }

    public NavigationBarTransitions getBarTransitions() {
        return this.mBarTransitions;
    }

    public LightBarTransitionsController getLightTransitionsController() {
        return this.mBarTransitions.getLightTransitionsController();
    }

    public void setComponents(NotificationPanelView notificationPanelView, AssistManager assistManager) {
        this.mPanelView = notificationPanelView;
        updatePanelSystemUiStateFlags();
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        this.mTintController.onDraw();
    }

    public void setOnVerticalChangedListener(OnVerticalChangedListener onVerticalChangedListener) {
        this.mOnVerticalChangedListener = onVerticalChangedListener;
        notifyVerticalChangedListener(this.mIsVertical);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return shouldDeadZoneConsumeTouchEvents(motionEvent) || super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        shouldDeadZoneConsumeTouchEvents(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: package-private */
    public void onSystemUiVisibilityChanged(int i) {
        this.mEdgeBackGestureHandler.onSystemUiVisibilityChanged(i);
    }

    /* access modifiers changed from: package-private */
    public void onBarTransition(int i) {
        if (i == 0) {
            this.mTintController.stop();
            getLightTransitionsController().setIconsDark(false, true);
            return;
        }
        this.mTintController.start();
    }

    private boolean shouldDeadZoneConsumeTouchEvents(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mDeadZoneConsuming = false;
        }
        if (!this.mDeadZone.onTouchEvent(motionEvent) && !this.mDeadZoneConsuming) {
            return false;
        }
        if (actionMasked == 0) {
            setSlippery(true);
            this.mDeadZoneConsuming = true;
        } else if (actionMasked == 1 || actionMasked == 3) {
            updateSlippery();
            this.mDeadZoneConsuming = false;
        }
        return true;
    }

    public void abortCurrentGesture() {
        getHomeButton().abortCurrentGesture();
    }

    public View getCurrentView() {
        return this.mCurrentView;
    }

    public RotationButtonController getRotationButtonController() {
        return this.mRotationButtonController;
    }

    public ButtonDispatcher getRecentsButton() {
        return this.mButtonDispatchers.get(C1777R$id.recent_apps);
    }

    public ButtonDispatcher getBackButton() {
        return this.mButtonDispatchers.get(C1777R$id.back);
    }

    public ButtonDispatcher getHomeButton() {
        return this.mButtonDispatchers.get(C1777R$id.home);
    }

    public ButtonDispatcher getImeSwitchButton() {
        return this.mButtonDispatchers.get(C1777R$id.ime_switcher);
    }

    public ButtonDispatcher getAccessibilityButton() {
        return this.mButtonDispatchers.get(C1777R$id.accessibility_button);
    }

    public RotationContextButton getRotateSuggestionButton() {
        return (RotationContextButton) this.mButtonDispatchers.get(C1777R$id.rotate_suggestion);
    }

    public ButtonDispatcher getHomeHandle() {
        return this.mButtonDispatchers.get(C1777R$id.home_handle);
    }

    public SparseArray<ButtonDispatcher> getButtonDispatchers() {
        return this.mButtonDispatchers;
    }

    public boolean isRecentsButtonVisible() {
        return getRecentsButton().getVisibility() == 0;
    }

    public boolean isOverviewEnabled() {
        return (this.mDisabledFlags & 16777216) == 0;
    }

    public boolean isQuickStepSwipeUpEnabled() {
        return this.mOverviewProxyService.shouldShowSwipeUpUI() && isOverviewEnabled();
    }

    public KeyButtonView getKeyButtonViewById(int i) {
        return (KeyButtonView) getCurrentView().findViewById(i);
    }

    private void reloadNavIcons() {
        updateIcons(Configuration.EMPTY);
    }

    private void updateIcons(Configuration configuration) {
        boolean z = true;
        boolean z2 = configuration.orientation != this.mConfiguration.orientation;
        boolean z3 = configuration.densityDpi != this.mConfiguration.densityDpi;
        if (configuration.getLayoutDirection() == this.mConfiguration.getLayoutDirection()) {
            z = false;
        }
        if (z2 || z3) {
            this.mDockedIcon = getDrawable(C1776R$drawable.ic_sysbar_docked);
            this.mHomeDefaultIcon = getHomeDrawable();
        }
        if (z3 || z) {
            this.mRecentIcon = getDrawable(C1776R$drawable.ic_sysbar_recent);
            this.mContextualButtonGroup.updateIcons();
        }
        if (z2 || z3 || z) {
            this.mBackIcon = getBackDrawable();
        }
        this.mArrowLeftIcon = getDrawable(C1776R$drawable.ic_navbar_chevron_left);
        this.mArrowRightIcon = getDrawable(C1776R$drawable.ic_navbar_chevron_right);
    }

    public KeyButtonDrawable getBackDrawable() {
        KeyButtonDrawable drawable = getDrawable(getBackDrawableRes());
        orientBackButton(drawable);
        return drawable;
    }

    public int getBackDrawableRes() {
        return chooseNavigationIconDrawableRes(C1776R$drawable.ic_sysbar_back, C1776R$drawable.ic_sysbar_back_quick_step);
    }

    public KeyButtonDrawable getHomeDrawable() {
        KeyButtonDrawable keyButtonDrawable;
        if (this.mOverviewProxyService.shouldShowSwipeUpUI()) {
            keyButtonDrawable = getDrawable(C1776R$drawable.ic_sysbar_home_quick_step);
        } else {
            keyButtonDrawable = getDrawable(C1776R$drawable.ic_sysbar_home);
        }
        orientHomeButton(keyButtonDrawable);
        return keyButtonDrawable;
    }

    private void orientBackButton(KeyButtonDrawable keyButtonDrawable) {
        float f;
        boolean z = (this.mNavigationIconHints & 1) != 0;
        boolean z2 = this.mConfiguration.getLayoutDirection() == 1;
        float f2 = 0.0f;
        if (z) {
            f = (float) (z2 ? 90 : -90);
        } else {
            f = 0.0f;
        }
        if (keyButtonDrawable.getRotation() != f) {
            if (QuickStepContract.isGesturalMode(this.mNavBarMode)) {
                keyButtonDrawable.setRotation(f);
                return;
            }
            if (!this.mOverviewProxyService.shouldShowSwipeUpUI() && !this.mIsVertical && z) {
                f2 = -getResources().getDimension(C1775R$dimen.navbar_back_button_ime_offset);
            }
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(keyButtonDrawable, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(KeyButtonDrawable.KEY_DRAWABLE_ROTATE, new float[]{f}), PropertyValuesHolder.ofFloat(KeyButtonDrawable.KEY_DRAWABLE_TRANSLATE_Y, new float[]{f2})});
            ofPropertyValuesHolder.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
            ofPropertyValuesHolder.setDuration(200);
            ofPropertyValuesHolder.start();
        }
    }

    private void orientHomeButton(KeyButtonDrawable keyButtonDrawable) {
        keyButtonDrawable.setRotation(this.mIsVertical ? 90.0f : 0.0f);
    }

    private int chooseNavigationIconDrawableRes(int i, int i2) {
        return this.mOverviewProxyService.shouldShowSwipeUpUI() ? i2 : i;
    }

    private KeyButtonDrawable getDrawable(int i) {
        return KeyButtonDrawable.create(this.mContext, i, true);
    }

    public void setWindowVisible(boolean z) {
        this.mTintController.setWindowVisible(z);
        this.mRotationButtonController.onNavigationBarWindowVisibilityChange(z);
    }

    public void setLayoutDirection(int i) {
        reloadNavIcons();
        super.setLayoutDirection(i);
    }

    public void setNavigationIconHints(int i) {
        if (i != this.mNavigationIconHints) {
            boolean z = false;
            boolean z2 = (i & 1) != 0;
            if ((this.mNavigationIconHints & 1) != 0) {
                z = true;
            }
            if (z2 != z) {
                onImeVisibilityChanged(z2);
            }
            this.mNavigationIconHints = i;
            updateNavButtonIcons();
        }
    }

    private void onImeVisibilityChanged(boolean z) {
        if (!z) {
            this.mTransitionListener.onBackAltCleared();
        }
        this.mImeVisible = z;
        this.mRotationButtonController.getRotationButton().setCanShowRotationButton(!this.mImeVisible);
        this.mFloatingRotationButton.setCanShowRotationButton(!this.mImeVisible);
    }

    public void setDisabledFlags(int i) {
        if (this.mDisabledFlags != i) {
            boolean isOverviewEnabled = isOverviewEnabled();
            this.mDisabledFlags = i;
            if (!isOverviewEnabled && isOverviewEnabled()) {
                reloadNavIcons();
            }
            updateNavButtonIcons();
            updateSlippery();
            setUpSwipeUpOnboarding(isQuickStepSwipeUpEnabled());
            updateDisabledSystemUiStateFlags();
        }
    }

    public void updateNavButtonIcons() {
        LayoutTransition layoutTransition;
        boolean z = (this.mNavigationIconHints & 1) != 0;
        KeyButtonDrawable keyButtonDrawable = this.mBackIcon;
        orientBackButton(keyButtonDrawable);
        KeyButtonDrawable keyButtonDrawable2 = this.mHomeDefaultIcon;
        if (!this.mUseCarModeUi) {
            orientHomeButton(keyButtonDrawable2);
        }
        getHomeButton().setImageDrawable(keyButtonDrawable2);
        getBackButton().setImageDrawable(keyButtonDrawable);
        updateRecentsIcon();
        if (showDpadArrowKeys()) {
            getKeyButtonViewById(C1777R$id.dpad_left).setImageDrawable(this.mArrowLeftIcon);
            getKeyButtonViewById(C1777R$id.dpad_right).setImageDrawable(this.mArrowRightIcon);
            updateDpadKeys();
        }
        this.mContextualButtonGroup.setButtonVisibility(C1777R$id.ime_switcher, (this.mNavigationIconHints & 2) != 0);
        if (QuickStepContract.isLegacyMode(this.mNavBarMode) && showDpadArrowKeys()) {
            this.mContextualButtonGroup.setButtonVisibility(C1777R$id.ime_switcher, false);
        }
        this.mBarTransitions.reapplyDarkIntensity();
        boolean z2 = QuickStepContract.isGesturalMode(this.mNavBarMode) || (this.mDisabledFlags & 2097152) != 0;
        boolean isRecentsButtonDisabled = isRecentsButtonDisabled();
        boolean z3 = isRecentsButtonDisabled && (2097152 & this.mDisabledFlags) != 0;
        boolean z4 = !z && (QuickStepContract.isGesturalMode(this.mNavBarMode) || (this.mDisabledFlags & 4194304) != 0);
        boolean isScreenPinningActive = ActivityManagerWrapper.getInstance().isScreenPinningActive();
        if (this.mOverviewProxyService.isEnabled()) {
            isRecentsButtonDisabled |= !QuickStepContract.isLegacyMode(this.mNavBarMode);
            if (isScreenPinningActive && !QuickStepContract.isGesturalMode(this.mNavBarMode)) {
                z4 = false;
                z2 = false;
            }
        } else if (isScreenPinningActive) {
            z4 = false;
            isRecentsButtonDisabled = false;
        }
        if (isScreenPinningActive && QuickStepContract.isGesturalMode(this.mNavBarMode)) {
            z4 = true;
        }
        ViewGroup viewGroup = (ViewGroup) getCurrentView().findViewById(C1777R$id.nav_buttons);
        if (!(viewGroup == null || (layoutTransition = viewGroup.getLayoutTransition()) == null || layoutTransition.getTransitionListeners().contains(this.mTransitionListener))) {
            layoutTransition.addTransitionListener(this.mTransitionListener);
        }
        int i = 4;
        getBackButton().setVisibility(z4 ? 4 : 0);
        getHomeButton().setVisibility(z2 ? 4 : 0);
        getRecentsButton().setVisibility(isRecentsButtonDisabled ? 4 : 0);
        ButtonDispatcher homeHandle = getHomeHandle();
        if (!z3 && this.mShowGestureNavbar) {
            i = 0;
        }
        homeHandle.setVisibility(i);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isRecentsButtonDisabled() {
        return this.mUseCarModeUi || !isOverviewEnabled() || getContext().getDisplayId() != 0;
    }

    private Display getContextDisplay() {
        return getContext().getDisplay();
    }

    public void setLayoutTransitionsEnabled(boolean z) {
        this.mLayoutTransitionsEnabled = z;
        updateLayoutTransitionsEnabled();
    }

    public void setWakeAndUnlocking(boolean z) {
        setUseFadingAnimations(z);
        this.mWakeAndUnlocking = z;
        updateLayoutTransitionsEnabled();
    }

    private void updateLayoutTransitionsEnabled() {
        boolean z = !this.mWakeAndUnlocking && this.mLayoutTransitionsEnabled;
        LayoutTransition layoutTransition = ((ViewGroup) getCurrentView().findViewById(C1777R$id.nav_buttons)).getLayoutTransition();
        if (layoutTransition == null) {
            return;
        }
        if (z) {
            layoutTransition.enableTransitionType(2);
            layoutTransition.enableTransitionType(3);
            layoutTransition.enableTransitionType(0);
            layoutTransition.enableTransitionType(1);
            return;
        }
        layoutTransition.disableTransitionType(2);
        layoutTransition.disableTransitionType(3);
        layoutTransition.disableTransitionType(0);
        layoutTransition.disableTransitionType(1);
    }

    private void setUseFadingAnimations(boolean z) {
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) ((ViewGroup) getParent()).getLayoutParams();
        if (layoutParams != null) {
            boolean z2 = layoutParams.windowAnimations != 0;
            if (!z2 && z) {
                layoutParams.windowAnimations = C1785R$style.Animation_NavigationBarFadeIn;
            } else if (z2 && !z) {
                layoutParams.windowAnimations = 0;
            } else {
                return;
            }
            ((WindowManager) getContext().getSystemService("window")).updateViewLayout((View) getParent(), layoutParams);
        }
    }

    public void onStatusBarPanelStateChanged() {
        updateSlippery();
        updatePanelSystemUiStateFlags();
    }

    public void updateBackArrowForGesture() {
        this.mEdgeBackGestureHandler.setStateForBackArrowGesture();
    }

    public void updateBackGestureHaptic() {
        this.mEdgeBackGestureHandler.setStateForBackGestureHaptic();
    }

    public void updateDisabledSystemUiStateFlags() {
        int displayId = this.mContext.getDisplayId();
        boolean z = true;
        this.mOverviewProxyService.setSystemUiStateFlag(1, ActivityManagerWrapper.getInstance().isScreenPinningActive(), displayId);
        this.mOverviewProxyService.setSystemUiStateFlag(128, this.mBlockedGesturalNavigation || (this.mDisabledFlags & 16777216) != 0, displayId);
        this.mOverviewProxyService.setSystemUiStateFlag(256, this.mBlockedGesturalNavigation || (this.mDisabledFlags & 2097152) != 0, displayId);
        OverviewProxyService overviewProxyService = this.mOverviewProxyService;
        if (!this.mBlockedGesturalNavigation && (this.mDisabledFlags & 33554432) == 0) {
            z = false;
        }
        overviewProxyService.setSystemUiStateFlag(1024, z, displayId);
    }

    public void updatePanelSystemUiStateFlags() {
        int displayId = this.mContext.getDisplayId();
        NotificationPanelView notificationPanelView = this.mPanelView;
        if (notificationPanelView != null) {
            this.mOverviewProxyService.setSystemUiStateFlag(4, this.mBlockedGesturalNavigation || (notificationPanelView.isFullyExpanded() && !this.mPanelView.isInSettings()), displayId);
            this.mOverviewProxyService.setSystemUiStateFlag(2048, this.mPanelView.isInSettings(), displayId);
        }
    }

    public void updateStates() {
        boolean shouldShowSwipeUpUI = this.mOverviewProxyService.shouldShowSwipeUpUI();
        NavigationBarInflaterView navigationBarInflaterView = this.mNavigationInflaterView;
        if (navigationBarInflaterView != null) {
            navigationBarInflaterView.onLikelyDefaultLayoutChange();
        }
        updateSlippery();
        reloadNavIcons();
        updateNavButtonIcons();
        setUpSwipeUpOnboarding(isQuickStepSwipeUpEnabled());
        WindowManagerWrapper.getInstance().setNavBarVirtualKeyHapticFeedbackEnabled(!shouldShowSwipeUpUI);
        getHomeButton().setAccessibilityDelegate(shouldShowSwipeUpUI ? this.mQuickStepAccessibilityDelegate : null);
    }

    public void setPartialScreenshot(boolean z) {
        this.mBlockedGesturalNavigation = z;
        updateDisabledSystemUiStateFlags();
        updatePanelSystemUiStateFlags();
    }

    public void setLongSwipeOptions() {
        this.mEdgeBackGestureHandler.setLongSwipeOptions();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r1.mPanelView;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateSlippery() {
        /*
            r1 = this;
            boolean r0 = r1.isQuickStepSwipeUpEnabled()
            if (r0 == 0) goto L_0x001b
            com.android.systemui.statusbar.phone.NotificationPanelView r0 = r1.mPanelView
            if (r0 == 0) goto L_0x0019
            boolean r0 = r0.isFullyExpanded()
            if (r0 == 0) goto L_0x0019
            com.android.systemui.statusbar.phone.NotificationPanelView r0 = r1.mPanelView
            boolean r0 = r0.isCollapsing()
            if (r0 != 0) goto L_0x0019
            goto L_0x001b
        L_0x0019:
            r0 = 0
            goto L_0x001c
        L_0x001b:
            r0 = 1
        L_0x001c:
            r1.setSlippery(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.NavigationBarView.updateSlippery():void");
    }

    private void setSlippery(boolean z) {
        setWindowFlag(536870912, z);
    }

    private void setWindowFlag(int i, boolean z) {
        WindowManager.LayoutParams layoutParams;
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null && (layoutParams = (WindowManager.LayoutParams) viewGroup.getLayoutParams()) != null) {
            if (z != ((layoutParams.flags & i) != 0)) {
                if (z) {
                    layoutParams.flags = i | layoutParams.flags;
                } else {
                    layoutParams.flags = (~i) & layoutParams.flags;
                }
                ((WindowManager) getContext().getSystemService("window")).updateViewLayout(viewGroup, layoutParams);
            }
        }
    }

    public void onNavigationModeChanged(int i) {
        Context currentUserContext = ((NavigationModeController) Dependency.get(NavigationModeController.class)).getCurrentUserContext();
        this.mNavBarMode = i;
        this.mBarTransitions.onNavigationModeChanged(this.mNavBarMode);
        this.mEdgeBackGestureHandler.onNavigationModeChanged(this.mNavBarMode, currentUserContext);
        this.mRecentsOnboarding.onNavigationModeChanged(this.mNavBarMode);
        getRotateSuggestionButton().onNavigationModeChanged(this.mNavBarMode);
        this.mTintController.onNavigationModeChanged(this.mNavBarMode);
        if (QuickStepContract.isGesturalMode(this.mNavBarMode)) {
            this.mTintController.start();
        } else {
            this.mTintController.stop();
        }
    }

    public void onSettingsChanged() {
        this.mEdgeBackGestureHandler.onSettingsChanged();
    }

    public void setAccessibilityButtonState(boolean z, boolean z2) {
        this.mLongClickableAccessibilityButton = z2;
        getAccessibilityButton().setLongClickable(z2);
        this.mContextualButtonGroup.setButtonVisibility(C1777R$id.accessibility_button, z);
    }

    /* access modifiers changed from: package-private */
    public void hideRecentsOnboarding() {
        this.mRecentsOnboarding.hide(true);
    }

    public void swiftNavigationBarItems(int i, int i2) {
        ViewGroup viewGroup = this.mNavigationBarContents;
        if (viewGroup != null) {
            viewGroup.setPaddingRelative(this.mBasePaddingLeft + i, this.mBasePaddingTop + i2, this.mBasePaddingRight + i, this.mBasePaddingBottom - i2);
            invalidate();
        }
    }

    public void onFinishInflate() {
        this.mNavigationInflaterView = (NavigationBarInflaterView) findViewById(C1777R$id.navigation_inflater);
        this.mNavigationInflaterView.setButtonDispatchers(this.mButtonDispatchers);
        getImeSwitchButton().setOnClickListener(this.mImeSwitcherClickListener);
        this.mNavigationBarContents = (ViewGroup) findViewById(C1777R$id.nav_buttons);
        this.mBasePaddingLeft = this.mNavigationBarContents.getPaddingStart();
        this.mBasePaddingTop = this.mNavigationBarContents.getPaddingTop();
        this.mBasePaddingRight = this.mNavigationBarContents.getPaddingEnd();
        this.mBasePaddingBottom = this.mNavigationBarContents.getPaddingBottom();
        DockedStackExistsListener.register(this.mDockedListener);
        updateOrientationViews();
        reloadNavIcons();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.mDeadZone.onDraw(canvas);
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mActiveRegion.setEmpty();
        updateButtonLocation(getBackButton(), this.mBackButtonBounds, true);
        updateButtonLocation(getHomeButton(), this.mHomeButtonBounds, false);
        updateButtonLocation(getRecentsButton(), this.mRecentsButtonBounds, false);
        updateButtonLocation(getRotateSuggestionButton(), this.mRotationButtonBounds, true);
        this.mOverviewProxyService.onActiveNavBarRegionChanges(this.mActiveRegion);
        this.mRecentsOnboarding.setNavBarHeight(getMeasuredHeight());
    }

    private void updateButtonLocation(ButtonDispatcher buttonDispatcher, Rect rect, boolean z) {
        View currentView = buttonDispatcher.getCurrentView();
        if (currentView == null) {
            rect.setEmpty();
            return;
        }
        float translationX = currentView.getTranslationX();
        float translationY = currentView.getTranslationY();
        currentView.setTranslationX(0.0f);
        currentView.setTranslationY(0.0f);
        if (z) {
            currentView.getLocationOnScreen(this.mTmpPosition);
            int[] iArr = this.mTmpPosition;
            rect.set(iArr[0], iArr[1], iArr[0] + currentView.getMeasuredWidth(), this.mTmpPosition[1] + currentView.getMeasuredHeight());
            this.mActiveRegion.op(rect, Region.Op.UNION);
        }
        currentView.getLocationInWindow(this.mTmpPosition);
        int[] iArr2 = this.mTmpPosition;
        rect.set(iArr2[0], iArr2[1], iArr2[0] + currentView.getMeasuredWidth(), this.mTmpPosition[1] + currentView.getMeasuredHeight());
        currentView.setTranslationX(translationX);
        currentView.setTranslationY(translationY);
    }

    private void updateOrientationViews() {
        this.mHorizontal = findViewById(C1777R$id.horizontal);
        this.mVertical = findViewById(C1777R$id.vertical);
        updateCurrentView();
    }

    /* access modifiers changed from: package-private */
    public boolean needsReorient(int i) {
        return this.mCurrentRotation != i;
    }

    private void updateCurrentView() {
        resetViews();
        this.mCurrentView = this.mIsVertical ? this.mVertical : this.mHorizontal;
        boolean z = false;
        this.mCurrentView.setVisibility(0);
        this.mNavigationInflaterView.setVertical(this.mIsVertical);
        this.mCurrentRotation = getContextDisplay().getRotation();
        NavigationBarInflaterView navigationBarInflaterView = this.mNavigationInflaterView;
        if (this.mCurrentRotation == 1) {
            z = true;
        }
        navigationBarInflaterView.setAlternativeOrder(z);
        this.mNavigationInflaterView.updateButtonDispatchersCurrentView();
        updateLayoutTransitionsEnabled();
    }

    private void resetViews() {
        this.mHorizontal.setVisibility(8);
        this.mVertical.setVisibility(8);
    }

    private void updateRecentsIcon() {
        this.mDockedIcon.setRotation((!this.mDockedStackExists || !this.mIsVertical) ? 0.0f : 90.0f);
        getRecentsButton().setImageDrawable(this.mDockedStackExists ? this.mDockedIcon : this.mRecentIcon);
        this.mBarTransitions.reapplyDarkIntensity();
    }

    public void showPinningEnterExitToast(boolean z) {
        if (z) {
            this.mScreenPinningNotify.showPinningStartToast();
        } else {
            this.mScreenPinningNotify.showPinningExitToast();
        }
    }

    public void showPinningEscapeToast() {
        this.mScreenPinningNotify.showEscapeToast(this.mNavBarMode == 2, isRecentsButtonVisible());
    }

    public NavigationBarFrame getNavbarFrame() {
        return (NavigationBarFrame) getRootView();
    }

    public void reorient() {
        updateCurrentView();
        ((NavigationBarFrame) getRootView()).setDeadZone(this.mDeadZone);
        this.mDeadZone.onConfigurationChanged(this.mCurrentRotation);
        this.mBarTransitions.init();
        if (!isLayoutDirectionResolved()) {
            resolveLayoutDirection();
        }
        updateNavButtonIcons();
        getHomeButton().setVertical(this.mIsVertical);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        boolean z = size > 0 && size2 > size && !QuickStepContract.isGesturalMode(this.mNavBarMode);
        if (z != this.mIsVertical) {
            this.mIsVertical = z;
            reorient();
            notifyVerticalChangedListener(z);
        }
        if (QuickStepContract.isGesturalMode(this.mNavBarMode)) {
            if (this.mIsVertical) {
                i3 = getResources().getDimensionPixelSize(17105293);
            } else {
                i3 = getResources().getDimensionPixelSize(17105291);
            }
            if (!this.mShowGestureNavbar) {
                i3 = 0;
            }
            this.mBarTransitions.setBackgroundFrame(new Rect(0, ((showIMESpace() || !gestureNavbarHidden()) ? getResources().getDimensionPixelSize(17105288) : 0) - i3, size, size2));
        }
        super.onMeasure(i, i2);
    }

    public boolean showGestureNavbar() {
        return this.mShowGestureNavbar;
    }

    private void notifyVerticalChangedListener(boolean z) {
        OnVerticalChangedListener onVerticalChangedListener = this.mOnVerticalChangedListener;
        if (onVerticalChangedListener != null) {
            onVerticalChangedListener.onVerticalChanged(z);
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mTmpLastConfiguration.updateFrom(this.mConfiguration);
        this.mConfiguration.updateFrom(configuration);
        boolean updateCarMode = updateCarMode();
        updateIcons(this.mTmpLastConfiguration);
        updateRecentsIcon();
        this.mRecentsOnboarding.onConfigurationChanged(this.mConfiguration);
        if (!updateCarMode) {
            Configuration configuration2 = this.mTmpLastConfiguration;
            if (configuration2.densityDpi == this.mConfiguration.densityDpi && configuration2.getLayoutDirection() == this.mConfiguration.getLayoutDirection()) {
                return;
            }
        }
        updateNavButtonIcons();
    }

    private boolean updateCarMode() {
        Configuration configuration = this.mConfiguration;
        if (configuration != null) {
            boolean z = (configuration.uiMode & 15) == 3;
            if (z != this.mInCarMode) {
                this.mInCarMode = z;
                this.mUseCarModeUi = false;
            }
        }
        return false;
    }

    private String getResourceName(int i) {
        if (i == 0) {
            return "(null)";
        }
        try {
            return getContext().getResources().getResourceName(i);
        } catch (Resources.NotFoundException unused) {
            return "(unknown)";
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        requestApplyInsets();
        reorient();
        onNavigationModeChanged(this.mNavBarMode);
        setUpSwipeUpOnboarding(isQuickStepSwipeUpEnabled());
        RotationButtonController rotationButtonController = this.mRotationButtonController;
        if (rotationButtonController != null) {
            rotationButtonController.registerListeners();
        }
        this.mEdgeBackGestureHandler.onNavBarAttached();
        getViewTreeObserver().addOnComputeInternalInsetsListener(this.mOnComputeInternalInsetsListener);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((NavigationModeController) Dependency.get(NavigationModeController.class)).removeListener(this);
        setUpSwipeUpOnboarding(false);
        for (int i = 0; i < this.mButtonDispatchers.size(); i++) {
            this.mButtonDispatchers.valueAt(i).onDestroy();
        }
        RotationButtonController rotationButtonController = this.mRotationButtonController;
        if (rotationButtonController != null) {
            rotationButtonController.unregisterListeners();
        }
        this.mEdgeBackGestureHandler.onNavBarDetached();
        getViewTreeObserver().removeOnComputeInternalInsetsListener(this.mOnComputeInternalInsetsListener);
    }

    private void setUpSwipeUpOnboarding(boolean z) {
        if (z) {
            this.mRecentsOnboarding.onConnectedToLauncher();
        } else {
            this.mRecentsOnboarding.onDisconnectedFromLauncher();
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("NavigationBarView {");
        Rect rect = new Rect();
        Point point = new Point();
        getContextDisplay().getRealSize(point);
        printWriter.println(String.format("      this: " + StatusBar.viewInfo(this) + " " + visibilityToString(getVisibility()), new Object[0]));
        getWindowVisibleDisplayFrame(rect);
        boolean z = rect.right > point.x || rect.bottom > point.y;
        StringBuilder sb = new StringBuilder();
        sb.append("      window: ");
        sb.append(rect.toShortString());
        sb.append(" ");
        sb.append(visibilityToString(getWindowVisibility()));
        sb.append(z ? " OFFSCREEN!" : "");
        printWriter.println(sb.toString());
        printWriter.println(String.format("      mCurrentView: id=%s (%dx%d) %s %f", new Object[]{getResourceName(getCurrentView().getId()), Integer.valueOf(getCurrentView().getWidth()), Integer.valueOf(getCurrentView().getHeight()), visibilityToString(getCurrentView().getVisibility()), Float.valueOf(getCurrentView().getAlpha())}));
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(this.mDisabledFlags);
        objArr[1] = this.mIsVertical ? "true" : "false";
        objArr[2] = Float.valueOf(getLightTransitionsController().getCurrentDarkIntensity());
        printWriter.println(String.format("      disabled=0x%08x vertical=%s darkIntensity=%.2f", objArr));
        dumpButton(printWriter, "back", getBackButton());
        dumpButton(printWriter, "home", getHomeButton());
        dumpButton(printWriter, "rcnt", getRecentsButton());
        dumpButton(printWriter, "rota", getRotateSuggestionButton());
        dumpButton(printWriter, "a11y", getAccessibilityButton());
        printWriter.println("    }");
        this.mContextualButtonGroup.dump(printWriter);
        this.mRecentsOnboarding.dump(printWriter);
        this.mTintController.dump(printWriter);
        this.mEdgeBackGestureHandler.dump(printWriter);
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        int systemWindowInsetLeft = windowInsets.getSystemWindowInsetLeft();
        int systemWindowInsetRight = windowInsets.getSystemWindowInsetRight();
        setPadding(systemWindowInsetLeft, windowInsets.getSystemWindowInsetTop(), systemWindowInsetRight, windowInsets.getSystemWindowInsetBottom());
        this.mEdgeBackGestureHandler.setInsets(systemWindowInsetLeft, systemWindowInsetRight);
        return super.onApplyWindowInsets(windowInsets);
    }

    private static void dumpButton(PrintWriter printWriter, String str, ButtonDispatcher buttonDispatcher) {
        printWriter.print("      " + str + ": ");
        if (buttonDispatcher == null) {
            printWriter.print("null");
        } else {
            printWriter.print(visibilityToString(buttonDispatcher.getVisibility()) + " alpha=" + buttonDispatcher.getAlpha());
        }
        printWriter.println();
    }

    public /* synthetic */ void lambda$new$2$NavigationBarView(Boolean bool) {
        post(new Runnable(bool) {
            private final /* synthetic */ Boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                NavigationBarView.this.lambda$new$1$NavigationBarView(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$new$1$NavigationBarView(Boolean bool) {
        this.mDockedStackExists = bool.booleanValue();
        updateRecentsIcon();
    }

    private void updateDpadKeys() {
        int i = (!showDpadArrowKeys() || (this.mNavigationIconHints & 1) == 0) ? 8 : 0;
        getKeyButtonViewById(C1777R$id.dpad_left).setVisibility(i);
        getKeyButtonViewById(C1777R$id.dpad_right).setVisibility(i);
    }

    public void setDpadDarkIntensity(float f) {
        if (showDpadArrowKeys()) {
            getKeyButtonViewById(C1777R$id.dpad_left).setDarkIntensity(f);
            getKeyButtonViewById(C1777R$id.dpad_right).setDarkIntensity(f);
        }
    }

    private boolean showDpadArrowKeys() {
        return Settings.System.getIntForUser(getContext().getContentResolver(), "navigation_bar_menu_arrow_keys", 0, -2) != 0;
    }

    private boolean showIMESpace() {
        return Settings.System.getIntForUser(getContext().getContentResolver(), "navigation_bar_ime_space", 1, -2) != 0;
    }

    private boolean gestureNavbarHidden() {
        boolean z = Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural_wide_back") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural_extra_wide_back") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural_narrow_back");
        boolean z2 = Settings.System.getInt(getContext().getContentResolver(), "navigation_handle_width", 1) == 0;
        if (!z || !z2) {
            return false;
        }
        return true;
    }
}
