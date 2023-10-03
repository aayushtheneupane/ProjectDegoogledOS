package com.android.systemui.statusbar.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.biometrics.BiometricSourceType;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.MathUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.havoc.ActionUtils;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardClockSwitch;
import com.android.keyguard.KeyguardStatusView;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1778R$integer;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.DejankUtils;
import com.android.systemui.Dependency;
import com.android.systemui.Interpolators;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.havoc.NotificationLightsView;
import com.android.systemui.p006qs.QSFragment;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.p005qs.C0862QS;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.FlingAnimationUtils;
import com.android.systemui.statusbar.GestureRecorder;
import com.android.systemui.statusbar.KeyguardAffordanceView;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.PulseExpansionHandler;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.notification.ActivityLaunchAnimator;
import com.android.systemui.statusbar.notification.AnimatableProperty;
import com.android.systemui.statusbar.notification.DynamicPrivacyController;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.PropertyAnimator;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.phone.KeyguardAffordanceHelper;
import com.android.systemui.statusbar.phone.KeyguardClockPositionAlgorithm;
import com.android.systemui.statusbar.phone.NotificationPanelView;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.KeyguardUserSwitcher;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.util.InjectionInflationController;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class NotificationPanelView extends PanelView implements ExpandableView.OnHeightChangedListener, View.OnClickListener, NotificationStackScrollLayout.OnOverscrollTopChangedListener, KeyguardAffordanceHelper.Callback, NotificationStackScrollLayout.OnEmptySpaceClickListener, OnHeadsUpChangedListener, C0862QS.HeightListener, ZenModeController.Callback, ConfigurationController.ConfigurationListener, StatusBarStateController.StateListener, PulseExpansionHandler.ExpansionCallback, DynamicPrivacyController.Listener, NotificationWakeUpCoordinator.WakeUpListener {
    private static final AnimationProperties CLOCK_ANIMATION_PROPERTIES;
    private static final AnimatableProperty KEYGUARD_HEADS_UP_SHOWING_AMOUNT = AnimatableProperty.from("KEYGUARD_HEADS_UP_SHOWING_AMOUNT", $$Lambda$NotificationPanelView$8G22_EmtDJSBkKVCqvCP10xXeA.INSTANCE, $$Lambda$NotificationPanelView$3eG2mRDkKhbGo7rATE21NiEDXnI.INSTANCE, C1777R$id.keyguard_hun_animator_tag, C1777R$id.keyguard_hun_animator_end_tag, C1777R$id.keyguard_hun_animator_start_tag);
    private static final AnimationProperties KEYGUARD_HUN_PROPERTIES;
    /* access modifiers changed from: private */
    public static final Rect mDummyDirtyRect = new Rect(0, 0, 1, 1);
    private static final Rect mEmptyRect = new Rect();
    private final AnimatableProperty PANEL_ALPHA = AnimatableProperty.from("panelAlpha", $$Lambda$aKsp0zdf_wKFZXD1TonJ2cFEsN4.INSTANCE, $$Lambda$SmdYpsZqQm1fpR9OgK3SiEL3pJQ.INSTANCE, C1777R$id.panel_alpha_animator_tag, C1777R$id.panel_alpha_animator_start_tag, C1777R$id.panel_alpha_animator_end_tag);
    private final AnimationProperties PANEL_ALPHA_IN_FAST_PROPERTIES;
    private final AnimationProperties PANEL_ALPHA_IN_PROPERTIES;
    private final AnimationProperties PANEL_ALPHA_OUT_FAST_PROPERTIES;
    private final AnimationProperties PANEL_ALPHA_OUT_PROPERTIES;
    private final AccessibilityManager mAccessibilityManager;
    private boolean mAffordanceHasPreview;
    @VisibleForTesting
    protected KeyguardAffordanceHelper mAffordanceHelper;
    private Consumer<Boolean> mAffordanceLaunchListener;
    private boolean mAllowExpandForSmallExpansion;
    private final Paint mAlphaPaint = new Paint();
    private int mAmbientIndicationBottomPadding;
    private final Runnable mAnimateKeyguardBottomAreaInvisibleEndRunnable;
    /* access modifiers changed from: private */
    public final Runnable mAnimateKeyguardStatusBarInvisibleEndRunnable;
    private final Runnable mAnimateKeyguardStatusViewGoneEndRunnable;
    private final Runnable mAnimateKeyguardStatusViewInvisibleEndRunnable;
    private final Runnable mAnimateKeyguardStatusViewVisibleEndRunnable;
    private boolean mAnimateNextPositionUpdate;
    private AnimatorListenerAdapter mAnimatorListenerAdapter = new AnimatorListenerAdapter() {
        public void onAnimationEnd(Animator animator) {
            if (NotificationPanelView.this.mPanelAlphaEndAction != null) {
                NotificationPanelView.this.mPanelAlphaEndAction.run();
            }
        }
    };
    protected int mBarState;
    @VisibleForTesting
    protected ViewGroup mBigClockContainer;
    private boolean mBlockTouches;
    private boolean mBlockingExpansionForCurrentTouch;
    private float mBottomAreaShadeAlpha;
    private final ValueAnimator mBottomAreaShadeAlphaAnimator;
    private final KeyguardClockPositionAlgorithm mClockPositionAlgorithm = new KeyguardClockPositionAlgorithm();
    private final KeyguardClockPositionAlgorithm.Result mClockPositionResult = new KeyguardClockPositionAlgorithm.Result();
    private boolean mClosingWithAlphaFadeOut;
    private boolean mCollapsedOnDown;
    private final CommandQueue mCommandQueue;
    private boolean mConflictingQsExpansionGesture;
    private int mCurrentPanelAlpha;
    private int mDarkIconSize;
    /* access modifiers changed from: private */
    public boolean mDelayShowingKeyguardStatusBar;
    private int mDisplayId;
    private GestureDetector mDoubleTapGesture;
    private float mDownX;
    private float mDownY;
    /* access modifiers changed from: private */
    public boolean mDozing;
    private boolean mDozingOnDown;
    private float mEmptyDragAmount;
    private final NotificationEntryManager mEntryManager;
    private Runnable mExpandAfterLayoutRunnable;
    private float mExpandOffset;
    private boolean mExpandingFromHeadsUp;
    private boolean mExpectingSynthesizedDown;
    private FalsingManager mFalsingManager;
    /* access modifiers changed from: private */
    public boolean mFirstBypassAttempt;
    private FlingAnimationUtils mFlingAnimationUtils;
    private final FragmentHostManager.FragmentListener mFragmentListener;
    private NotificationGroupManager mGroupManager;
    private Handler mHandler = new Handler();
    private boolean mHeadsUpAnimatingAway;
    private HeadsUpAppearanceController mHeadsUpAppearanceController;
    private Runnable mHeadsUpExistenceChangedRunnable = new Runnable() {
        public void run() {
            NotificationPanelView.this.setHeadsUpAnimatingAway(false);
            NotificationPanelView.this.notifyBarPanelExpansionChanged();
        }
    };
    private int mHeadsUpInset;
    private boolean mHeadsUpPinnedMode;
    private HeadsUpTouchHelper mHeadsUpTouchHelper;
    private boolean mHideIconsDuringNotificationLaunch = true;
    private int mIndicationBottomPadding;
    private float mInitialHeightOnTouch;
    private float mInitialTouchX;
    private float mInitialTouchY;
    private final InjectionInflationController mInjectionInflationController;
    private boolean mIntercepting;
    private float mInterpolatedDarkAmount;
    private boolean mIsExpanding;
    private boolean mIsExpansionFromHeadsUp;
    private boolean mIsFullWidth;
    private boolean mIsLaunchTransitionFinished;
    private boolean mIsLaunchTransitionRunning;
    private boolean mIsLockscreenDoubleTapEnabled;
    /* access modifiers changed from: private */
    public final KeyguardBypassController mKeyguardBypassController;
    private float mKeyguardHeadsUpShowingAmount;
    private KeyguardIndicationController mKeyguardIndicationController;
    private final KeyguardMonitor.Callback mKeyguardMonitorCallback = new KeyguardMonitor.Callback() {
        public void onKeyguardFadingAwayChanged() {
            if (!NotificationPanelView.this.mKeyguardMonitor.isKeyguardFadingAway()) {
                boolean unused = NotificationPanelView.this.mFirstBypassAttempt = false;
                boolean unused2 = NotificationPanelView.this.mDelayShowingKeyguardStatusBar = false;
            }
        }
    };
    private boolean mKeyguardOrShadeShowing;
    private boolean mKeyguardShowing;
    @VisibleForTesting
    protected KeyguardStatusBarView mKeyguardStatusBar;
    /* access modifiers changed from: private */
    public float mKeyguardStatusBarAnimateAlpha = 1.0f;
    @VisibleForTesting
    protected KeyguardStatusView mKeyguardStatusView;
    /* access modifiers changed from: private */
    public boolean mKeyguardStatusViewAnimating;
    @VisibleForTesting
    final KeyguardUpdateMonitorCallback mKeyguardUpdateCallback = new KeyguardUpdateMonitorCallback() {
        public void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType) {
            if (NotificationPanelView.this.mFirstBypassAttempt && NotificationPanelView.this.mUpdateMonitor.isUnlockingWithBiometricAllowed()) {
                boolean unused = NotificationPanelView.this.mDelayShowingKeyguardStatusBar = true;
            }
        }

        public void onBiometricRunningStateChanged(boolean z, BiometricSourceType biometricSourceType) {
            int i = NotificationPanelView.this.mBarState;
            boolean z2 = true;
            if (!(i == 1 || i == 2)) {
                z2 = false;
            }
            if (!z && NotificationPanelView.this.mFirstBypassAttempt && z2 && !NotificationPanelView.this.mDozing && !NotificationPanelView.this.mDelayShowingKeyguardStatusBar) {
                boolean unused = NotificationPanelView.this.mFirstBypassAttempt = false;
                NotificationPanelView.this.animateKeyguardStatusBarIn(360);
            }
        }

        public void onFinishedGoingToSleep(int i) {
            NotificationPanelView notificationPanelView = NotificationPanelView.this;
            boolean unused = notificationPanelView.mFirstBypassAttempt = notificationPanelView.mKeyguardBypassController.getBypassEnabled();
            boolean unused2 = NotificationPanelView.this.mDelayShowingKeyguardStatusBar = false;
        }
    };
    private KeyguardUserSwitcher mKeyguardUserSwitcher;
    /* access modifiers changed from: private */
    public String mLastCameraLaunchSource = "lockscreen_affordance";
    private boolean mLastEventSynthesizedDown;
    private int mLastOrientation = -1;
    private float mLastOverscroll;
    private float mLastTouchX;
    private float mLastTouchY;
    private Runnable mLaunchAnimationEndRunnable;
    private boolean mLaunchingAffordance;
    private float mLinearDarkAmount;
    private boolean mListenForHeadsUp;
    private LockPatternUtils mLockPatternUtils;
    private GestureDetector mLockscreenDoubleTapToSleep;
    private LockscreenGestureLogger mLockscreenGestureLogger = new LockscreenGestureLogger();
    private final NotificationLockscreenUserManager mLockscreenUserManager;
    private int mMaxFadeoutHeight;
    private int mNavigationBarBottomHeight;
    private boolean mNoVisibleNotifications = true;
    protected NotificationsQuickSettingsContainer mNotificationContainerParent;
    protected NotificationStackScrollLayout mNotificationStackScroller;
    private int mNotificationsHeaderCollideDistance;
    private int mOldLayoutDirection;
    private Runnable mOnReinflationListener;
    private int mOneFingerQuickSettingsIntercept;
    private boolean mOnlyAffordanceInThisMotion;
    private int mPanelAlpha;
    /* access modifiers changed from: private */
    public Runnable mPanelAlphaEndAction;
    private boolean mPanelExpanded;
    private int mPositionMinSideMargin;
    private final PowerManager mPowerManager;
    private final PulseExpansionHandler mPulseExpansionHandler;
    private NotificationLightsView mPulseLightsView;
    private boolean mPulsing;
    /* access modifiers changed from: private */
    public C0862QS mQs;
    private boolean mQsAnimatorExpand;
    /* access modifiers changed from: private */
    public boolean mQsExpandImmediate;
    private boolean mQsExpanded;
    private boolean mQsExpandedWhenExpandingStarted;
    /* access modifiers changed from: private */
    public ValueAnimator mQsExpansionAnimator;
    protected boolean mQsExpansionEnabled = true;
    private boolean mQsExpansionFromOverscroll;
    protected float mQsExpansionHeight;
    private int mQsFalsingThreshold;
    @VisibleForTesting
    protected FrameLayout mQsFrame;
    private boolean mQsFullyExpanded;
    protected int mQsMaxExpansionHeight;
    protected int mQsMinExpansionHeight;
    private int mQsNotificationTopPadding;
    private int mQsPeekHeight;
    private boolean mQsScrimEnabled = true;
    /* access modifiers changed from: private */
    public ValueAnimator mQsSizeChangeAnimator;
    /* access modifiers changed from: private */
    public int mQsSmartPullDown;
    private boolean mQsTouchAboveFalsingThreshold;
    private boolean mQsTracking;
    private VelocityTracker mQsVelocityTracker;
    private ScreenDecorations mScreenDecorations;
    private SettingsObserver mSettingsObserver;
    private final ShadeController mShadeController;
    private int mShelfHeight;
    private boolean mShowEmptyShadeView;
    private boolean mShowIconsWhenExpanded;
    private boolean mShowingKeyguardHeadsUp;
    private int mStackScrollerMeasuringPass;
    /* access modifiers changed from: private */
    public boolean mStackScrollerOverscrolling;
    private final ValueAnimator.AnimatorUpdateListener mStatusBarAnimateAlphaListener;
    private int mStatusBarHeaderHeight;
    private int mStatusBarHeight;
    private int mStatusBarMinHeight;
    /* access modifiers changed from: private */
    public boolean mStatusBarShownOnSecureKeyguard;
    private int mThemeResId;
    private ArrayList<Consumer<ExpandableNotificationRow>> mTrackingHeadsUpListeners = new ArrayList<>();
    private int mTrackingPointer;
    private boolean mTwoFingerQsExpandPossible;
    private int mUnlockMoveDistance;
    @VisibleForTesting
    protected KeyguardUpdateMonitor mUpdateMonitor;
    private boolean mUserSetupComplete;
    private ArrayList<Runnable> mVerticalTranslationListener = new ArrayList<>();
    private final NotificationWakeUpCoordinator mWakeUpCoordinator;

    public void onReset(ExpandableView expandableView) {
    }

    public boolean shouldDelayChildPressedState() {
        return true;
    }

    static {
        AnimationProperties animationProperties = new AnimationProperties();
        animationProperties.setDuration(360);
        CLOCK_ANIMATION_PROPERTIES = animationProperties;
        AnimationProperties animationProperties2 = new AnimationProperties();
        animationProperties2.setDuration(360);
        KEYGUARD_HUN_PROPERTIES = animationProperties2;
    }

    public NotificationPanelView(final Context context, AttributeSet attributeSet, InjectionInflationController injectionInflationController, NotificationWakeUpCoordinator notificationWakeUpCoordinator, PulseExpansionHandler pulseExpansionHandler, DynamicPrivacyController dynamicPrivacyController, KeyguardBypassController keyguardBypassController, FalsingManager falsingManager) {
        super(context, attributeSet);
        AnimationProperties animationProperties = new AnimationProperties();
        animationProperties.setDuration(150);
        animationProperties.setCustomInterpolator(this.PANEL_ALPHA.getProperty(), Interpolators.ALPHA_OUT);
        this.PANEL_ALPHA_OUT_PROPERTIES = animationProperties;
        AnimationProperties animationProperties2 = new AnimationProperties();
        animationProperties2.setDuration(200);
        animationProperties2.setAnimationFinishListener(this.mAnimatorListenerAdapter);
        animationProperties2.setCustomInterpolator(this.PANEL_ALPHA.getProperty(), Interpolators.ALPHA_IN);
        this.PANEL_ALPHA_IN_PROPERTIES = animationProperties2;
        AnimationProperties animationProperties3 = new AnimationProperties();
        animationProperties3.setDuration(100);
        animationProperties3.setCustomInterpolator(this.PANEL_ALPHA.getProperty(), Interpolators.ALPHA_OUT);
        this.PANEL_ALPHA_OUT_FAST_PROPERTIES = animationProperties3;
        AnimationProperties animationProperties4 = new AnimationProperties();
        animationProperties4.setDuration(100);
        animationProperties4.setAnimationFinishListener(this.mAnimatorListenerAdapter);
        animationProperties4.setCustomInterpolator(this.PANEL_ALPHA.getProperty(), Interpolators.ALPHA_IN);
        this.PANEL_ALPHA_IN_FAST_PROPERTIES = animationProperties4;
        this.mEntryManager = (NotificationEntryManager) Dependency.get(NotificationEntryManager.class);
        this.mLockscreenUserManager = (NotificationLockscreenUserManager) Dependency.get(NotificationLockscreenUserManager.class);
        this.mShadeController = (ShadeController) Dependency.get(ShadeController.class);
        this.mKeyguardHeadsUpShowingAmount = 0.0f;
        this.mAnimateKeyguardStatusViewInvisibleEndRunnable = new Runnable() {
            public void run() {
                boolean unused = NotificationPanelView.this.mKeyguardStatusViewAnimating = false;
                NotificationPanelView.this.mKeyguardStatusView.setVisibility(4);
            }
        };
        this.mAnimateKeyguardStatusViewGoneEndRunnable = new Runnable() {
            public void run() {
                boolean unused = NotificationPanelView.this.mKeyguardStatusViewAnimating = false;
                NotificationPanelView.this.mKeyguardStatusView.setVisibility(8);
            }
        };
        this.mAnimateKeyguardStatusViewVisibleEndRunnable = new Runnable() {
            public void run() {
                boolean unused = NotificationPanelView.this.mKeyguardStatusViewAnimating = false;
            }
        };
        this.mAnimateKeyguardStatusBarInvisibleEndRunnable = new Runnable() {
            public void run() {
                NotificationPanelView.this.mKeyguardStatusBar.setVisibility(4);
                NotificationPanelView.this.mKeyguardStatusBar.setAlpha(1.0f);
                float unused = NotificationPanelView.this.mKeyguardStatusBarAnimateAlpha = 1.0f;
            }
        };
        this.mStatusBarAnimateAlphaListener = new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = NotificationPanelView.this.mKeyguardStatusBarAnimateAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                NotificationPanelView.this.updateHeaderKeyguardAlpha();
            }
        };
        this.mAnimateKeyguardBottomAreaInvisibleEndRunnable = new Runnable() {
            public void run() {
                NotificationPanelView.this.mKeyguardBottomArea.setVisibility(8);
            }
        };
        this.mFragmentListener = new FragmentHostManager.FragmentListener() {
            public void onFragmentViewCreated(String str, Fragment fragment) {
                C0862QS unused = NotificationPanelView.this.mQs = (C0862QS) fragment;
                NotificationPanelView.this.mQs.setPanelView(NotificationPanelView.this);
                NotificationPanelView.this.mQs.setExpandClickListener(NotificationPanelView.this);
                NotificationPanelView.this.mQs.setHeaderClickable(NotificationPanelView.this.mQsExpansionEnabled);
                NotificationPanelView.this.updateQSPulseExpansion();
                NotificationPanelView.this.mQs.setOverscrolling(NotificationPanelView.this.mStackScrollerOverscrolling);
                NotificationPanelView.this.mQs.getView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                        NotificationPanelView.C141624.this.lambda$onFragmentViewCreated$0$NotificationPanelView$24(view, i, i2, i3, i4, i5, i6, i7, i8);
                    }
                });
                NotificationPanelView notificationPanelView = NotificationPanelView.this;
                notificationPanelView.mNotificationStackScroller.setQsContainer((ViewGroup) notificationPanelView.mQs.getView());
                if (NotificationPanelView.this.mQs instanceof QSFragment) {
                    NotificationPanelView notificationPanelView2 = NotificationPanelView.this;
                    notificationPanelView2.mKeyguardStatusBar.setQSPanel(((QSFragment) notificationPanelView2.mQs).getQsPanel());
                }
                NotificationPanelView.this.updateQsExpansion();
            }

            public /* synthetic */ void lambda$onFragmentViewCreated$0$NotificationPanelView$24(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (i4 - i2 != i8 - i6) {
                    NotificationPanelView.this.onQsHeightChanged();
                }
            }

            public void onFragmentViewDestroyed(String str, Fragment fragment) {
                if (fragment == NotificationPanelView.this.mQs) {
                    C0862QS unused = NotificationPanelView.this.mQs = null;
                }
            }
        };
        setWillNotDraw(true);
        this.mInjectionInflationController = injectionInflationController;
        this.mFalsingManager = falsingManager;
        this.mPowerManager = (PowerManager) context.getSystemService(PowerManager.class);
        this.mWakeUpCoordinator = notificationWakeUpCoordinator;
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService(AccessibilityManager.class);
        setAccessibilityPaneTitle(determineAccessibilityPaneTitle());
        this.mAlphaPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        setPanelAlpha(255, false);
        this.mCommandQueue = (CommandQueue) SysUiServiceProvider.getComponent(context, CommandQueue.class);
        this.mDisplayId = context.getDisplayId();
        this.mPulseExpansionHandler = pulseExpansionHandler;
        pulseExpansionHandler.setPulseExpandAbortListener(new Runnable() {
            public final void run() {
                NotificationPanelView.this.lambda$new$0$NotificationPanelView();
            }
        });
        this.mThemeResId = context.getThemeResId();
        this.mKeyguardBypassController = keyguardBypassController;
        this.mUpdateMonitor = KeyguardUpdateMonitor.getInstance(this.mContext);
        this.mFirstBypassAttempt = this.mKeyguardBypassController.getBypassEnabled();
        this.mKeyguardMonitor.addCallback(this.mKeyguardMonitorCallback);
        dynamicPrivacyController.addListener(this);
        this.mBottomAreaShadeAlphaAnimator = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        this.mBottomAreaShadeAlphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                NotificationPanelView.this.lambda$new$1$NotificationPanelView(valueAnimator);
            }
        });
        this.mBottomAreaShadeAlphaAnimator.setDuration(160);
        this.mBottomAreaShadeAlphaAnimator.setInterpolator(Interpolators.ALPHA_OUT);
        this.mSettingsObserver = new SettingsObserver(this.mHandler);
        this.mLockPatternUtils = new LockPatternUtils(this.mContext);
        this.mDoubleTapGesture = new GestureDetector(this.mContext, new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent motionEvent) {
                ActionUtils.switchScreenOff(context);
                boolean unused = NotificationPanelView.this.mQsExpandImmediate = false;
                NotificationPanelView.this.requestPanelHeightUpdate();
                NotificationPanelView.this.setListening(false);
                return true;
            }
        });
        this.mLockscreenDoubleTapToSleep = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent motionEvent) {
                ActionUtils.switchScreenOff(context);
                return true;
            }
        });
        this.mScreenDecorations = (ScreenDecorations) SysUiServiceProvider.getComponent(this.mContext, ScreenDecorations.class);
    }

    public /* synthetic */ void lambda$new$0$NotificationPanelView() {
        C0862QS qs = this.mQs;
        if (qs != null) {
            qs.animateHeaderSlidingOut();
        }
    }

    public /* synthetic */ void lambda$new$1$NotificationPanelView(ValueAnimator valueAnimator) {
        this.mBottomAreaShadeAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        updateKeyguardBottomAreaAlpha();
    }

    public boolean hasCustomClock() {
        return this.mKeyguardStatusView.hasCustomClock();
    }

    public KeyguardStatusView getKeyguardStatusView() {
        return this.mKeyguardStatusView;
    }

    private void setStatusBar(StatusBar statusBar) {
        this.mStatusBar = statusBar;
        this.mKeyguardBottomArea.setStatusBar(this.mStatusBar);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mKeyguardStatusBar = (KeyguardStatusBarView) findViewById(C1777R$id.keyguard_header);
        this.mKeyguardStatusView = (KeyguardStatusView) findViewById(C1777R$id.keyguard_status_view);
        this.mBigClockContainer = (ViewGroup) findViewById(C1777R$id.big_clock_container);
        ((KeyguardClockSwitch) findViewById(C1777R$id.keyguard_clock_container)).setBigClockContainer(this.mBigClockContainer);
        this.mNotificationContainerParent = (NotificationsQuickSettingsContainer) findViewById(C1777R$id.notification_container_parent);
        this.mNotificationStackScroller = (NotificationStackScrollLayout) findViewById(C1777R$id.notification_stack_scroller);
        this.mNotificationStackScroller.setOnHeightChangedListener(this);
        this.mNotificationStackScroller.setOverscrollTopChangedListener(this);
        this.mNotificationStackScroller.setOnEmptySpaceClickListener(this);
        NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationStackScroller;
        Objects.requireNonNull(notificationStackScrollLayout);
        addTrackingHeadsUpListener(new Consumer() {
            public final void accept(Object obj) {
                NotificationStackScrollLayout.this.setTrackingHeadsUp((ExpandableNotificationRow) obj);
            }
        });
        this.mKeyguardBottomArea = (KeyguardBottomAreaView) findViewById(C1777R$id.keyguard_bottom_area);
        this.mLastOrientation = getResources().getConfiguration().orientation;
        this.mPulseLightsView = (NotificationLightsView) findViewById(C1777R$id.lights_container);
        initBottomArea();
        this.mWakeUpCoordinator.setStackScroller(this.mNotificationStackScroller);
        this.mQsFrame = (FrameLayout) findViewById(C1777R$id.qs_frame);
        this.mPulseExpansionHandler.setUp(this.mNotificationStackScroller, this, this.mShadeController);
        this.mWakeUpCoordinator.addListener(new NotificationWakeUpCoordinator.WakeUpListener() {
            public void onFullyHiddenChanged(boolean z) {
                NotificationPanelView.this.updateKeyguardStatusBarForHeadsUp();
            }

            public void onPulseExpansionChanged(boolean z) {
                if (NotificationPanelView.this.mKeyguardBypassController.getBypassEnabled()) {
                    NotificationPanelView.this.requestScrollerTopPaddingUpdate(false);
                    NotificationPanelView.this.updateQSPulseExpansion();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mSettingsObserver.observe();
        FragmentHostManager.get(this).addTagListener(C0862QS.TAG, this.mFragmentListener);
        ((StatusBarStateController) Dependency.get(StatusBarStateController.class)).addCallback(this);
        ((ZenModeController) Dependency.get(ZenModeController.class)).addCallback(this);
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this);
        this.mUpdateMonitor.registerCallback(this.mKeyguardUpdateCallback);
        onThemeChanged();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mSettingsObserver.unobserve();
        FragmentHostManager.get(this).removeTagListener(C0862QS.TAG, this.mFragmentListener);
        ((StatusBarStateController) Dependency.get(StatusBarStateController.class)).removeCallback(this);
        ((ZenModeController) Dependency.get(ZenModeController.class)).removeCallback(this);
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).removeCallback(this);
        this.mUpdateMonitor.removeCallback(this.mKeyguardUpdateCallback);
    }

    /* access modifiers changed from: protected */
    public void loadDimens() {
        super.loadDimens();
        this.mFlingAnimationUtils = new FlingAnimationUtils(getContext(), 0.4f);
        this.mStatusBarMinHeight = getResources().getDimensionPixelSize(17105434);
        this.mQsPeekHeight = getResources().getDimensionPixelSize(C1775R$dimen.qs_peek_height);
        this.mNotificationsHeaderCollideDistance = getResources().getDimensionPixelSize(C1775R$dimen.header_notifications_collide_distance);
        this.mUnlockMoveDistance = getResources().getDimensionPixelOffset(C1775R$dimen.unlock_move_distance);
        this.mClockPositionAlgorithm.loadDimens(getResources());
        this.mQsFalsingThreshold = getResources().getDimensionPixelSize(C1775R$dimen.qs_falsing_threshold);
        this.mPositionMinSideMargin = getResources().getDimensionPixelSize(C1775R$dimen.notification_panel_min_side_margin);
        this.mMaxFadeoutHeight = getResources().getDimensionPixelSize(C1775R$dimen.max_notification_fadeout_height);
        this.mIndicationBottomPadding = getResources().getDimensionPixelSize(C1775R$dimen.keyguard_indication_bottom_padding);
        this.mQsNotificationTopPadding = getResources().getDimensionPixelSize(C1775R$dimen.qs_notification_padding);
        this.mStatusBarHeight = getResources().getDimensionPixelSize(17105436);
        this.mShelfHeight = getResources().getDimensionPixelSize(C1775R$dimen.notification_shelf_height);
        this.mDarkIconSize = getResources().getDimensionPixelSize(C1775R$dimen.status_bar_icon_drawing_size_dark);
        this.mStatusBarHeaderHeight = getResources().getDimensionPixelSize(17105434);
        this.mHeadsUpInset = this.mStatusBarHeaderHeight + getResources().getDimensionPixelSize(C1775R$dimen.heads_up_status_bar_padding);
    }

    public void setLaunchAffordanceListener(Consumer<Boolean> consumer) {
        this.mAffordanceLaunchListener = consumer;
    }

    public void updateResources() {
        Resources resources = getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(C1775R$dimen.qs_panel_width);
        int integer = getResources().getInteger(C1778R$integer.notification_panel_layout_gravity);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mQsFrame.getLayoutParams();
        if (!(layoutParams.width == dimensionPixelSize && layoutParams.gravity == integer)) {
            layoutParams.width = dimensionPixelSize;
            layoutParams.gravity = integer;
            this.mQsFrame.setLayoutParams(layoutParams);
        }
        int dimensionPixelSize2 = resources.getDimensionPixelSize(C1775R$dimen.notification_panel_width);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mNotificationStackScroller.getLayoutParams();
        if (layoutParams2.width != dimensionPixelSize2 || layoutParams2.gravity != integer) {
            layoutParams2.width = dimensionPixelSize2;
            layoutParams2.gravity = integer;
            this.mNotificationStackScroller.setLayoutParams(layoutParams2);
        }
    }

    public void onDensityOrFontScaleChanged() {
        updateShowEmptyShadeView();
        reInflateViews();
    }

    public void onThemeChanged() {
        int themeResId = getContext().getThemeResId();
        if (this.mThemeResId != themeResId) {
            this.mThemeResId = themeResId;
            reInflateViews();
        }
    }

    public void onOverlayChanged() {
        reInflateViews();
    }

    private void reInflateViews() {
        updateShowEmptyShadeView();
        int indexOfChild = indexOfChild(this.mKeyguardStatusView);
        removeView(this.mKeyguardStatusView);
        this.mKeyguardStatusView = (KeyguardStatusView) this.mInjectionInflationController.injectable(LayoutInflater.from(this.mContext)).inflate(C1779R$layout.keyguard_status_view, this, false);
        addView(this.mKeyguardStatusView, indexOfChild);
        int indexOfChild2 = indexOfChild(this.mKeyguardStatusBar);
        removeView(this.mKeyguardStatusBar);
        this.mKeyguardStatusBar = (KeyguardStatusBarView) this.mInjectionInflationController.injectable(LayoutInflater.from(this.mContext)).inflate(C1779R$layout.keyguard_status_bar, this, false);
        addView(this.mKeyguardStatusBar, indexOfChild2);
        C0862QS qs = this.mQs;
        if (qs != null && (qs instanceof QSFragment)) {
            this.mKeyguardStatusBar.setQSPanel(((QSFragment) qs).getQsPanel());
        }
        this.mKeyguardStatusBar.setAlpha(this.mBarState == 1 ? 0.0f : 1.0f);
        this.mKeyguardStatusBar.setVisibility(this.mBarState == 1 ? 0 : 4);
        this.mBigClockContainer.removeAllViews();
        ((KeyguardClockSwitch) findViewById(C1777R$id.keyguard_clock_container)).setBigClockContainer(this.mBigClockContainer);
        int indexOfChild3 = indexOfChild(this.mKeyguardBottomArea);
        removeView(this.mKeyguardBottomArea);
        KeyguardBottomAreaView keyguardBottomAreaView = this.mKeyguardBottomArea;
        this.mKeyguardBottomArea = (KeyguardBottomAreaView) this.mInjectionInflationController.injectable(LayoutInflater.from(this.mContext)).inflate(C1779R$layout.keyguard_bottom_area, this, false);
        this.mKeyguardBottomArea.initFrom(keyguardBottomAreaView);
        addView(this.mKeyguardBottomArea, indexOfChild3);
        initBottomArea();
        this.mKeyguardIndicationController.setIndicationArea(this.mKeyguardBottomArea);
        onDozeAmountChanged(this.mStatusBarStateController.getDozeAmount(), this.mStatusBarStateController.getInterpolatedDozeAmount());
        setKeyguardStatusViewVisibility(this.mBarState, false, false);
        setKeyguardBottomAreaVisibility(this.mBarState, false);
        Runnable runnable = this.mOnReinflationListener;
        if (runnable != null) {
            runnable.run();
        }
    }

    private void initBottomArea() {
        this.mAffordanceHelper = new KeyguardAffordanceHelper(this, getContext(), this.mFalsingManager);
        this.mKeyguardBottomArea.setAffordanceHelper(this.mAffordanceHelper);
        this.mKeyguardBottomArea.setStatusBar(this.mStatusBar);
        this.mKeyguardBottomArea.setUserSetupComplete(this.mUserSetupComplete);
    }

    public void setKeyguardIndicationController(KeyguardIndicationController keyguardIndicationController) {
        this.mKeyguardIndicationController = keyguardIndicationController;
        this.mKeyguardIndicationController.setIndicationArea(this.mKeyguardBottomArea);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        C0862QS qs;
        super.onLayout(z, i, i2, i3, i4);
        setIsFullWidth(this.mNotificationStackScroller.getWidth() == getWidth());
        this.mKeyguardStatusView.setPivotX((float) (getWidth() / 2));
        KeyguardStatusView keyguardStatusView = this.mKeyguardStatusView;
        keyguardStatusView.setPivotY(keyguardStatusView.getClockTextSize() * 0.34521484f);
        int i5 = this.mQsMaxExpansionHeight;
        C0862QS qs2 = this.mQs;
        if (qs2 != null) {
            this.mQsMinExpansionHeight = this.mKeyguardShowing ? 0 : qs2.getQsMinExpansionHeight();
            this.mQsMaxExpansionHeight = this.mQs.getDesiredHeight();
            this.mNotificationStackScroller.setMaxTopPadding(this.mQsMaxExpansionHeight + this.mQsNotificationTopPadding);
        }
        positionClockAndNotifications();
        if (this.mQsExpanded && this.mQsFullyExpanded) {
            this.mQsExpansionHeight = (float) this.mQsMaxExpansionHeight;
            requestScrollerTopPaddingUpdate(false);
            requestPanelHeightUpdate();
            int i6 = this.mQsMaxExpansionHeight;
            if (i6 != i5) {
                startQsSizeChangeAnimation(i5, i6);
            }
        } else if (!this.mQsExpanded) {
            setQsExpansion(((float) this.mQsMinExpansionHeight) + this.mLastOverscroll);
        }
        updateExpandedHeight(getExpandedHeight());
        updateHeader();
        if (this.mQsSizeChangeAnimator == null && (qs = this.mQs) != null) {
            qs.setHeightOverride(qs.getDesiredHeight());
        }
        updateMaxHeadsUpTranslation();
        updateGestureExclusionRect();
        Runnable runnable = this.mExpandAfterLayoutRunnable;
        if (runnable != null) {
            runnable.run();
            this.mExpandAfterLayoutRunnable = null;
        }
    }

    private void updateGestureExclusionRect() {
        List list;
        Rect calculateGestureExclusionRect = calculateGestureExclusionRect();
        if (calculateGestureExclusionRect.isEmpty()) {
            list = Collections.EMPTY_LIST;
        } else {
            list = Collections.singletonList(calculateGestureExclusionRect);
        }
        setSystemGestureExclusionRects(list);
    }

    private Rect calculateGestureExclusionRect() {
        Region calculateTouchableRegion = this.mHeadsUpManager.calculateTouchableRegion();
        Rect bounds = (!isFullyCollapsed() || calculateTouchableRegion == null) ? null : calculateTouchableRegion.getBounds();
        return bounds != null ? bounds : mEmptyRect;
    }

    private void setIsFullWidth(boolean z) {
        this.mIsFullWidth = z;
        this.mNotificationStackScroller.setIsFullWidth(z);
    }

    private void startQsSizeChangeAnimation(int i, int i2) {
        ValueAnimator valueAnimator = this.mQsSizeChangeAnimator;
        if (valueAnimator != null) {
            i = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            this.mQsSizeChangeAnimator.cancel();
        }
        this.mQsSizeChangeAnimator = ValueAnimator.ofInt(new int[]{i, i2});
        this.mQsSizeChangeAnimator.setDuration(300);
        this.mQsSizeChangeAnimator.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        this.mQsSizeChangeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                NotificationPanelView.this.requestScrollerTopPaddingUpdate(false);
                NotificationPanelView.this.requestPanelHeightUpdate();
                NotificationPanelView.this.mQs.setHeightOverride(((Integer) NotificationPanelView.this.mQsSizeChangeAnimator.getAnimatedValue()).intValue());
            }
        });
        this.mQsSizeChangeAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ValueAnimator unused = NotificationPanelView.this.mQsSizeChangeAnimator = null;
            }
        });
        this.mQsSizeChangeAnimator.start();
    }

    private void positionClockAndNotifications() {
        int i;
        boolean isAddOrRemoveAnimationPending = this.mNotificationStackScroller.isAddOrRemoveAnimationPending();
        boolean z = isAddOrRemoveAnimationPending || this.mAnimateNextPositionUpdate;
        if (this.mBarState != 1) {
            i = getUnlockedStackScrollerPadding();
        } else {
            int height = getHeight();
            int max = Math.max(this.mIndicationBottomPadding, this.mAmbientIndicationBottomPadding);
            int clockPreferredY = this.mKeyguardStatusView.getClockPreferredY(height);
            boolean bypassEnabled = this.mKeyguardBypassController.getBypassEnabled();
            boolean z2 = !bypassEnabled && this.mNotificationStackScroller.getVisibleNotificationCount() != 0;
            this.mKeyguardStatusView.setHasVisibleNotifications(z2);
            this.mClockPositionAlgorithm.setup(this.mStatusBarMinHeight, height - max, this.mNotificationStackScroller.getIntrinsicContentHeight(), getExpandedFraction(), height, (int) ((((float) this.mKeyguardStatusView.getHeight()) - (((float) this.mShelfHeight) / 2.0f)) - (((float) this.mDarkIconSize) / 2.0f)), clockPreferredY, hasCustomClock(), z2, this.mInterpolatedDarkAmount, this.mEmptyDragAmount, bypassEnabled, getUnlockedStackScrollerPadding());
            this.mClockPositionAlgorithm.run(this.mClockPositionResult);
            PropertyAnimator.setProperty(this.mKeyguardStatusView, AnimatableProperty.f63X, (float) this.mClockPositionResult.clockX, CLOCK_ANIMATION_PROPERTIES, z);
            PropertyAnimator.setProperty(this.mKeyguardStatusView, AnimatableProperty.f64Y, (float) this.mClockPositionResult.clockY, CLOCK_ANIMATION_PROPERTIES, z);
            updateNotificationTranslucency();
            updateClock();
            i = this.mClockPositionResult.stackScrollerPaddingExpanded;
        }
        this.mNotificationStackScroller.setIntrinsicPadding(i);
        this.mKeyguardBottomArea.setAntiBurnInOffsetX(this.mClockPositionResult.clockX);
        this.mStackScrollerMeasuringPass++;
        requestScrollerTopPaddingUpdate(isAddOrRemoveAnimationPending);
        this.mStackScrollerMeasuringPass = 0;
        this.mAnimateNextPositionUpdate = false;
    }

    private int getUnlockedStackScrollerPadding() {
        C0862QS qs = this.mQs;
        return (qs != null ? qs.getHeader().getHeight() : 0) + this.mQsPeekHeight + this.mQsNotificationTopPadding;
    }

    public int computeMaxKeyguardNotifications(int i) {
        float f;
        float minStackScrollerPadding = this.mClockPositionAlgorithm.getMinStackScrollerPadding();
        int max = Math.max(1, getResources().getDimensionPixelSize(C1775R$dimen.notification_divider_height));
        NotificationShelf notificationShelf = this.mNotificationStackScroller.getNotificationShelf();
        if (notificationShelf.getVisibility() == 8) {
            f = 0.0f;
        } else {
            f = (float) (notificationShelf.getIntrinsicHeight() + max);
        }
        int i2 = 0;
        float height = (((((float) this.mNotificationStackScroller.getHeight()) - minStackScrollerPadding) - f) - ((float) Math.max(this.mIndicationBottomPadding, this.mAmbientIndicationBottomPadding))) - ((float) this.mKeyguardStatusView.getLogoutButtonHeight());
        for (int i3 = 0; i3 < this.mNotificationStackScroller.getChildCount(); i3++) {
            ExpandableView expandableView = (ExpandableView) this.mNotificationStackScroller.getChildAt(i3);
            if (expandableView instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
                NotificationGroupManager notificationGroupManager = this.mGroupManager;
                if (!(notificationGroupManager != null && notificationGroupManager.isSummaryOfSuppressedGroup(expandableNotificationRow.getStatusBarNotification())) && this.mLockscreenUserManager.shouldShowOnKeyguard(expandableNotificationRow.getEntry()) && !expandableNotificationRow.isRemoved()) {
                    height -= (float) (expandableView.getMinHeight(true) + max);
                    if (height >= 0.0f && i2 < i) {
                        i2++;
                    } else if (height <= (-f)) {
                        return i2;
                    } else {
                        for (int i4 = i3 + 1; i4 < this.mNotificationStackScroller.getChildCount(); i4++) {
                            if (this.mNotificationStackScroller.getChildAt(i4) instanceof ExpandableNotificationRow) {
                                return i2;
                            }
                        }
                        return i2 + 1;
                    }
                }
            }
        }
        return i2;
    }

    private void updateClock() {
        if (!this.mKeyguardStatusViewAnimating) {
            this.mKeyguardStatusView.setAlpha(this.mClockPositionResult.clockAlpha);
        }
    }

    public void animateToFullShade(long j) {
        this.mNotificationStackScroller.goToFullShade(j);
        requestLayout();
        this.mAnimateNextPositionUpdate = true;
    }

    private boolean isQSEventBlocked() {
        return this.mLockPatternUtils.isSecure(KeyguardUpdateMonitor.getCurrentUser()) && !this.mStatusBarShownOnSecureKeyguard && this.mKeyguardOrShadeShowing;
    }

    public void setQsExpansionEnabled(boolean z) {
        this.mQsExpansionEnabled = z;
        C0862QS qs = this.mQs;
        if (qs != null) {
            qs.setHeaderClickable(this.mQsExpansionEnabled);
        }
    }

    public void resetViews(boolean z) {
        this.mIsLaunchTransitionFinished = false;
        this.mBlockTouches = false;
        if (!this.mLaunchingAffordance) {
            this.mAffordanceHelper.reset(false);
            this.mLastCameraLaunchSource = "lockscreen_affordance";
        }
        this.mStatusBar.getGutsManager().closeAndSaveGuts(true, true, true, -1, -1, true);
        if (z) {
            animateCloseQs(true);
        } else {
            closeQs();
        }
        this.mNotificationStackScroller.setOverScrollAmount(0.0f, true, z, !z);
        this.mNotificationStackScroller.resetScrollPosition();
    }

    public void collapse(boolean z, float f) {
        if (canPanelBeCollapsed()) {
            if (this.mQsExpanded) {
                this.mQsExpandImmediate = true;
                this.mNotificationStackScroller.setShouldShowShelfOnly(true);
            }
            super.collapse(z, f);
        }
    }

    public void closeQs() {
        cancelQsAnimation();
        setQsExpansion((float) this.mQsMinExpansionHeight);
    }

    public void animateCloseQs(boolean z) {
        ValueAnimator valueAnimator = this.mQsExpansionAnimator;
        if (valueAnimator != null) {
            if (this.mQsAnimatorExpand) {
                float f = this.mQsExpansionHeight;
                valueAnimator.cancel();
                setQsExpansion(f);
            } else {
                return;
            }
        }
        flingSettings(0.0f, z ? 2 : 1);
    }

    public void expandWithQs() {
        if (this.mQsExpansionEnabled && !isQSEventBlocked()) {
            this.mQsExpandImmediate = true;
            this.mNotificationStackScroller.setShouldShowShelfOnly(true);
        }
        if (isFullyCollapsed()) {
            expand(true);
        } else {
            flingSettings(0.0f, 0);
        }
    }

    public void expandWithoutQs() {
        if (isQsExpanded()) {
            flingSettings(0.0f, 1);
        } else {
            expand(true);
        }
    }

    public void fling(float f, boolean z) {
        GestureRecorder gestureRecorder = ((PhoneStatusBarView) this.mBar).mBar.getGestureRecorder();
        if (gestureRecorder != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("fling ");
            sb.append(f > 0.0f ? "open" : "closed");
            String sb2 = sb.toString();
            gestureRecorder.tag(sb2, "notifications,v=" + f);
        }
        super.fling(f, z);
    }

    /* access modifiers changed from: protected */
    public void flingToHeight(float f, boolean z, float f2, float f3, boolean z2) {
        this.mHeadsUpTouchHelper.notifyFling(!z);
        setClosingWithAlphaFadeout(!z && !isOnKeyguard() && getFadeoutAlpha() == 1.0f);
        super.flingToHeight(f, z, f2, f3, z2);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mBlockTouches) {
            return false;
        }
        if (this.mQsFullyExpanded && this.mQs.onInterceptTouchEvent(motionEvent)) {
            return false;
        }
        initDownStates(motionEvent);
        if (this.mStatusBar.isBouncerShowing()) {
            return true;
        }
        if (this.mBar.panelEnabled() && this.mHeadsUpTouchHelper.onInterceptTouchEvent(motionEvent)) {
            this.mIsExpansionFromHeadsUp = true;
            MetricsLogger.count(this.mContext, "panel_open", 1);
            MetricsLogger.count(this.mContext, "panel_open_peek", 1);
            return true;
        } else if (!shouldQuickSettingsIntercept(this.mDownX, this.mDownY, 0.0f) && this.mPulseExpansionHandler.onInterceptTouchEvent(motionEvent)) {
            return true;
        } else {
            if (isFullyCollapsed() || !onQsIntercept(motionEvent)) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            return true;
        }
    }

    private boolean onQsIntercept(MotionEvent motionEvent) {
        int pointerId;
        int findPointerIndex = motionEvent.findPointerIndex(this.mTrackingPointer);
        if (findPointerIndex < 0) {
            this.mTrackingPointer = motionEvent.getPointerId(0);
            findPointerIndex = 0;
        }
        float x = motionEvent.getX(findPointerIndex);
        float y = motionEvent.getY(findPointerIndex);
        int actionMasked = motionEvent.getActionMasked();
        boolean z = true;
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    float f = y - this.mInitialTouchY;
                    trackMovement(motionEvent);
                    if (this.mQsTracking) {
                        setQsExpansion(f + this.mInitialHeightOnTouch);
                        trackMovement(motionEvent);
                        this.mIntercepting = false;
                        return true;
                    } else if (Math.abs(f) > ((float) this.mTouchSlop) && Math.abs(f) > Math.abs(x - this.mInitialTouchX) && shouldQuickSettingsIntercept(this.mInitialTouchX, this.mInitialTouchY, f)) {
                        this.mQsTracking = true;
                        onQsExpansionStarted();
                        notifyExpandingFinished();
                        this.mInitialHeightOnTouch = this.mQsExpansionHeight;
                        this.mInitialTouchY = y;
                        this.mInitialTouchX = x;
                        this.mIntercepting = false;
                        this.mNotificationStackScroller.cancelLongPress();
                        return true;
                    }
                } else if (actionMasked != 3) {
                    if (actionMasked == 6 && this.mTrackingPointer == (pointerId = motionEvent.getPointerId(motionEvent.getActionIndex()))) {
                        if (motionEvent.getPointerId(0) != pointerId) {
                            z = false;
                        }
                        this.mTrackingPointer = motionEvent.getPointerId(z ? 1 : 0);
                        this.mInitialTouchX = motionEvent.getX(z);
                        this.mInitialTouchY = motionEvent.getY(z);
                    }
                }
            }
            trackMovement(motionEvent);
            if (this.mQsTracking) {
                if (motionEvent.getActionMasked() != 3) {
                    z = false;
                }
                flingQsWithCurrentVelocity(y, z);
                this.mQsTracking = false;
            }
            this.mIntercepting = false;
        } else {
            this.mIntercepting = true;
            this.mInitialTouchY = y;
            this.mInitialTouchX = x;
            initVelocityTracker();
            trackMovement(motionEvent);
            if (shouldQuickSettingsIntercept(this.mInitialTouchX, this.mInitialTouchY, 0.0f)) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            if (this.mQsExpansionAnimator != null) {
                onQsExpansionStarted();
                this.mInitialHeightOnTouch = this.mQsExpansionHeight;
                this.mQsTracking = true;
                this.mIntercepting = false;
                this.mNotificationStackScroller.cancelLongPress();
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isInContentBounds(float f, float f2) {
        float x = this.mNotificationStackScroller.getX();
        return !this.mNotificationStackScroller.isBelowLastNotification(f - x, f2) && x < f && f < x + ((float) this.mNotificationStackScroller.getWidth());
    }

    private void initDownStates(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            this.mOnlyAffordanceInThisMotion = false;
            this.mQsTouchAboveFalsingThreshold = this.mQsFullyExpanded;
            this.mDozingOnDown = isDozing();
            this.mDownX = motionEvent.getX();
            this.mDownY = motionEvent.getY();
            this.mCollapsedOnDown = isFullyCollapsed();
            this.mListenForHeadsUp = this.mCollapsedOnDown && this.mHeadsUpManager.hasPinnedHeadsUp();
            boolean z = this.mExpectingSynthesizedDown;
            this.mAllowExpandForSmallExpansion = z;
            this.mTouchSlopExceededBeforeDown = z;
            if (z) {
                this.mLastEventSynthesizedDown = true;
            } else {
                this.mLastEventSynthesizedDown = false;
            }
        } else {
            this.mLastEventSynthesizedDown = false;
        }
    }

    private void flingQsWithCurrentVelocity(float f, boolean z) {
        float currentQSVelocity = getCurrentQSVelocity();
        boolean flingExpandsQs = flingExpandsQs(currentQSVelocity);
        if (flingExpandsQs) {
            logQsSwipeDown(f);
        }
        flingSettings(currentQSVelocity, (!flingExpandsQs || z) ? 1 : 0);
    }

    private void logQsSwipeDown(float f) {
        this.mLockscreenGestureLogger.write(this.mBarState == 1 ? 193 : 194, (int) ((f - this.mInitialTouchY) / this.mStatusBar.getDisplayDensity()), (int) (getCurrentQSVelocity() / this.mStatusBar.getDisplayDensity()));
    }

    private boolean flingExpandsQs(float f) {
        if (this.mFalsingManager.isUnlockingDisabled() || isFalseTouch()) {
            return false;
        }
        if (Math.abs(f) < this.mFlingAnimationUtils.getMinVelocityPxPerSecond()) {
            if (getQsExpansionFraction() > 0.5f) {
                return true;
            }
            return false;
        } else if (f > 0.0f) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isFalseTouch() {
        if (!needsAntiFalsing()) {
            return false;
        }
        if (this.mFalsingManager.isClassiferEnabled()) {
            return this.mFalsingManager.isFalseTouch();
        }
        return !this.mQsTouchAboveFalsingThreshold;
    }

    private float getQsExpansionFraction() {
        float f = this.mQsExpansionHeight;
        int i = this.mQsMinExpansionHeight;
        return Math.min(1.0f, (f - ((float) i)) / ((float) (this.mQsMaxExpansionHeight - i)));
    }

    /* access modifiers changed from: protected */
    public boolean shouldExpandWhenNotFlinging() {
        if (super.shouldExpandWhenNotFlinging()) {
            return true;
        }
        if (!this.mAllowExpandForSmallExpansion) {
            return false;
        }
        if (SystemClock.uptimeMillis() - this.mDownTime <= 300) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public float getOpeningHeight() {
        return this.mNotificationStackScroller.getOpeningHeight();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mBlockTouches) {
            return false;
        }
        C0862QS qs = this.mQs;
        if ((qs != null && qs.isCustomizing()) || this.mStatusBar.isBouncerShowingScrimmed()) {
            return false;
        }
        if (!this.mQsExpanded && this.mDoubleTapToSleepEnabled && motionEvent.getY() < ((float) this.mStatusBarHeaderHeight) && this.mDoubleTapGesture.onTouchEvent(motionEvent)) {
            return false;
        }
        if (this.mIsLockscreenDoubleTapEnabled && !this.mPulsing && !this.mDozing && this.mBarState == 1) {
            this.mLockscreenDoubleTapToSleep.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.mBlockingExpansionForCurrentTouch = false;
        }
        if (this.mLastEventSynthesizedDown && motionEvent.getAction() == 1) {
            expand(true);
        }
        initDownStates(motionEvent);
        if (!this.mIsExpanding && !shouldQuickSettingsIntercept(this.mDownX, this.mDownY, 0.0f) && this.mPulseExpansionHandler.onTouchEvent(motionEvent)) {
            return true;
        }
        if (this.mListenForHeadsUp && !this.mHeadsUpTouchHelper.isTrackingHeadsUp() && this.mHeadsUpTouchHelper.onInterceptTouchEvent(motionEvent)) {
            this.mIsExpansionFromHeadsUp = true;
            MetricsLogger.count(this.mContext, "panel_open_peek", 1);
        }
        boolean onTouchEvent = ((!this.mIsExpanding || this.mHintAnimationRunning) && !this.mQsExpanded && this.mBarState != 0 && !this.mDozing) ? this.mAffordanceHelper.onTouchEvent(motionEvent) | false : false;
        if (this.mOnlyAffordanceInThisMotion) {
            return true;
        }
        boolean onTouchEvent2 = onTouchEvent | this.mHeadsUpTouchHelper.onTouchEvent(motionEvent);
        if (!this.mHeadsUpTouchHelper.isTrackingHeadsUp() && handleQsTouch(motionEvent)) {
            return true;
        }
        if (motionEvent.getActionMasked() == 0 && isFullyCollapsed()) {
            MetricsLogger.count(this.mContext, "panel_open", 1);
            updateVerticalPanelPosition(motionEvent.getX());
            onTouchEvent2 = true;
        }
        boolean onTouchEvent3 = super.onTouchEvent(motionEvent) | onTouchEvent2;
        if (!this.mDozing || this.mPulsing || onTouchEvent3) {
            return true;
        }
        return false;
    }

    public void setLockscreenDoubleTapToSleep(boolean z) {
        this.mIsLockscreenDoubleTapEnabled = z;
    }

    private boolean handleQsTouch(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 && getExpandedFraction() == 1.0f && this.mBarState != 1 && !this.mQsExpanded && this.mQsExpansionEnabled && !isQSEventBlocked()) {
            this.mQsTracking = true;
            this.mConflictingQsExpansionGesture = true;
            onQsExpansionStarted();
            this.mInitialHeightOnTouch = this.mQsExpansionHeight;
            this.mInitialTouchY = motionEvent.getY();
            this.mInitialTouchX = motionEvent.getX();
        }
        if (!isFullyCollapsed()) {
            handleQsDown(motionEvent);
        }
        if (!this.mQsExpandImmediate && this.mQsTracking) {
            onQsTouch(motionEvent);
            if (!this.mConflictingQsExpansionGesture) {
                return true;
            }
        }
        if (actionMasked == 3 || actionMasked == 1) {
            this.mConflictingQsExpansionGesture = false;
        }
        if (actionMasked == 0 && isFullyCollapsed() && this.mQsExpansionEnabled) {
            this.mTwoFingerQsExpandPossible = true;
        }
        if (this.mTwoFingerQsExpandPossible && isOpenQsEvent(motionEvent) && motionEvent.getY(motionEvent.getActionIndex()) < ((float) this.mStatusBarMinHeight)) {
            MetricsLogger.count(this.mContext, "panel_open_qs", 1);
            this.mQsExpandImmediate = true;
            this.mNotificationStackScroller.setShouldShowShelfOnly(true);
            requestPanelHeightUpdate();
            setListening(true);
        }
        return false;
    }

    private boolean isInQsArea(float f, float f2) {
        return f >= this.mQsFrame.getX() && f <= this.mQsFrame.getX() + ((float) this.mQsFrame.getWidth()) && (f2 <= this.mNotificationStackScroller.getBottomMostNotificationBottom() || f2 <= this.mQs.getView().getY() + ((float) this.mQs.getView().getHeight()));
    }

    private boolean isOpenQsEvent(MotionEvent motionEvent) {
        boolean z;
        int pointerCount = motionEvent.getPointerCount();
        int actionMasked = motionEvent.getActionMasked();
        boolean z2 = actionMasked == 5 && pointerCount == 2;
        boolean z3 = actionMasked == 0 && (motionEvent.isButtonPressed(32) || motionEvent.isButtonPressed(64));
        boolean z4 = actionMasked == 0 && (motionEvent.isButtonPressed(2) || motionEvent.isButtonPressed(4));
        float measuredWidth = (float) getMeasuredWidth();
        float x = motionEvent.getX();
        float f = (1.0f * measuredWidth) / 4.0f;
        int i = this.mOneFingerQuickSettingsIntercept;
        if (i == 1 ? !isLayoutRtl() ? measuredWidth - f >= x : x >= f : i != 2 || (!isLayoutRtl() ? x >= f : measuredWidth - f >= x)) {
            z = false;
        } else {
            z = true;
        }
        boolean z5 = z & (this.mBarState == 0);
        if ((this.mQsSmartPullDown == 1 && !hasActiveClearableNotifications()) || ((this.mQsSmartPullDown == 2 && !this.mStatusBar.hasActiveOngoingNotifications()) || (this.mQsSmartPullDown == 3 && !this.mStatusBar.hasActiveVisibleNotifications()))) {
            z5 = true;
        }
        if (z2 || z5 || z3 || z4) {
            return true;
        }
        return false;
    }

    private void handleQsDown(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0 && shouldQuickSettingsIntercept(motionEvent.getX(), motionEvent.getY(), -1.0f)) {
            this.mFalsingManager.onQsDown();
            this.mQsTracking = true;
            onQsExpansionStarted();
            this.mInitialHeightOnTouch = this.mQsExpansionHeight;
            this.mInitialTouchY = motionEvent.getX();
            this.mInitialTouchX = motionEvent.getY();
            notifyExpandingFinished();
        }
    }

    public void startWaitingForOpenPanelGesture() {
        if (isFullyCollapsed()) {
            this.mExpectingSynthesizedDown = true;
            onTrackingStarted();
            updatePanelExpanded();
        }
    }

    public void stopWaitingForOpenPanelGesture(float f) {
        if (this.mExpectingSynthesizedDown) {
            this.mExpectingSynthesizedDown = false;
            maybeVibrateOnOpening();
            $$Lambda$NotificationPanelView$xA7cX216Lge0MlKS0GBWcVNjPAk r1 = new Runnable(f) {
                private final /* synthetic */ float f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    NotificationPanelView.this.lambda$stopWaitingForOpenPanelGesture$2$NotificationPanelView(this.f$1);
                }
            };
            if (this.mStatusBar.getStatusBarWindow().getHeight() != this.mStatusBar.getStatusBarHeight()) {
                r1.run();
            } else {
                this.mExpandAfterLayoutRunnable = r1;
            }
            onTrackingStopped(false);
        }
    }

    public /* synthetic */ void lambda$stopWaitingForOpenPanelGesture$2$NotificationPanelView(float f) {
        fling(f > 1.0f ? f * 1000.0f : 0.0f, true);
    }

    /* access modifiers changed from: protected */
    public boolean flingExpands(float f, float f2, float f3, float f4) {
        boolean flingExpands = super.flingExpands(f, f2, f3, f4);
        if (this.mQsExpansionAnimator != null) {
            return true;
        }
        return flingExpands;
    }

    /* access modifiers changed from: protected */
    public boolean shouldGestureWaitForTouchSlop() {
        if (this.mExpectingSynthesizedDown) {
            this.mExpectingSynthesizedDown = false;
            return false;
        } else if (isFullyCollapsed() || this.mBarState != 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldGestureIgnoreXTouchSlop(float f, float f2) {
        return !this.mAffordanceHelper.isOnAffordanceIcon(f, f2);
    }

    private void onQsTouch(MotionEvent motionEvent) {
        int pointerId;
        int findPointerIndex = motionEvent.findPointerIndex(this.mTrackingPointer);
        boolean z = false;
        if (findPointerIndex < 0) {
            this.mTrackingPointer = motionEvent.getPointerId(0);
            findPointerIndex = 0;
        }
        float y = motionEvent.getY(findPointerIndex);
        float x = motionEvent.getX(findPointerIndex);
        float f = y - this.mInitialTouchY;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    setQsExpansion(this.mInitialHeightOnTouch + f);
                    if (f >= ((float) getFalsingThreshold())) {
                        this.mQsTouchAboveFalsingThreshold = true;
                    }
                    trackMovement(motionEvent);
                    return;
                } else if (actionMasked != 3) {
                    if (actionMasked == 6 && this.mTrackingPointer == (pointerId = motionEvent.getPointerId(motionEvent.getActionIndex()))) {
                        if (motionEvent.getPointerId(0) == pointerId) {
                            z = true;
                        }
                        float y2 = motionEvent.getY(z ? 1 : 0);
                        float x2 = motionEvent.getX(z);
                        this.mTrackingPointer = motionEvent.getPointerId(z);
                        this.mInitialHeightOnTouch = this.mQsExpansionHeight;
                        this.mInitialTouchY = y2;
                        this.mInitialTouchX = x2;
                        return;
                    }
                    return;
                }
            }
            this.mQsTracking = false;
            this.mTrackingPointer = -1;
            trackMovement(motionEvent);
            if (getQsExpansionFraction() != 0.0f || y >= this.mInitialTouchY) {
                if (motionEvent.getActionMasked() == 3) {
                    z = true;
                }
                flingQsWithCurrentVelocity(y, z);
            }
            VelocityTracker velocityTracker = this.mQsVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.mQsVelocityTracker = null;
                return;
            }
            return;
        }
        this.mQsTracking = true;
        this.mInitialTouchY = y;
        this.mInitialTouchX = x;
        onQsExpansionStarted();
        this.mInitialHeightOnTouch = this.mQsExpansionHeight;
        initVelocityTracker();
        trackMovement(motionEvent);
    }

    private int getFalsingThreshold() {
        return (int) (((float) this.mQsFalsingThreshold) * (this.mStatusBar.isWakeUpComingFromTouch() ? 1.5f : 1.0f));
    }

    public void onOverscrollTopChanged(float f, boolean z) {
        cancelQsAnimation();
        if (!this.mQsExpansionEnabled || isQSEventBlocked()) {
            f = 0.0f;
        }
        if (f < 1.0f) {
            f = 0.0f;
        }
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        boolean z2 = true;
        setOverScrolling(i != 0 && z);
        if (i == 0) {
            z2 = false;
        }
        this.mQsExpansionFromOverscroll = z2;
        this.mLastOverscroll = f;
        updateQsState();
        setQsExpansion(((float) this.mQsMinExpansionHeight) + f);
    }

    public void flingTopOverscroll(float f, boolean z) {
        this.mLastOverscroll = 0.0f;
        this.mQsExpansionFromOverscroll = false;
        setQsExpansion(this.mQsExpansionHeight);
        if (!this.mQsExpansionEnabled && z) {
            f = 0.0f;
        }
        flingSettings(f, (!z || !this.mQsExpansionEnabled) ? 1 : 0, new Runnable() {
            public void run() {
                boolean unused = NotificationPanelView.this.mStackScrollerOverscrolling = false;
                NotificationPanelView.this.setOverScrolling(false);
                NotificationPanelView.this.updateQsState();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void setOverScrolling(boolean z) {
        this.mStackScrollerOverscrolling = z;
        C0862QS qs = this.mQs;
        if (qs != null) {
            qs.setOverscrolling(z);
        }
    }

    private void onQsExpansionStarted() {
        onQsExpansionStarted(0);
    }

    /* access modifiers changed from: protected */
    public void onQsExpansionStarted(int i) {
        cancelQsAnimation();
        cancelHeightAnimator();
        float f = this.mQsExpansionHeight - ((float) i);
        setQsExpansion(f);
        requestPanelHeightUpdate();
        this.mNotificationStackScroller.checkSnoozeLeavebehind();
        if (f == 0.0f) {
            this.mStatusBar.requestFaceAuth();
        }
    }

    private void setQsExpanded(boolean z) {
        if (this.mQsExpanded != z) {
            this.mQsExpanded = z;
            updateQsState();
            requestPanelHeightUpdate();
            this.mFalsingManager.setQsExpanded(z);
            this.mStatusBar.setQsExpanded(z);
            this.mNotificationContainerParent.setQsExpanded(z);
            this.mPulseExpansionHandler.setQsExpanded(z);
            this.mKeyguardBypassController.setQSExpanded(z);
        }
    }

    public void onStateChanged(int i) {
        C0862QS qs;
        long j;
        boolean goingToFullShade = this.mStatusBarStateController.goingToFullShade();
        boolean isKeyguardFadingAway = this.mKeyguardMonitor.isKeyguardFadingAway();
        int i2 = this.mBarState;
        boolean z = i == 1;
        boolean z2 = i == 1 || i == 2;
        setKeyguardStatusViewVisibility(i, isKeyguardFadingAway, goingToFullShade);
        setKeyguardBottomAreaVisibility(i, goingToFullShade);
        this.mBarState = i;
        this.mKeyguardShowing = z;
        this.mKeyguardOrShadeShowing = z2;
        if (i2 == 1 && (goingToFullShade || i == 2)) {
            animateKeyguardStatusBarOut();
            if (this.mBarState == 2) {
                j = 0;
            } else {
                j = this.mKeyguardMonitor.calculateGoingToFullShadeDelay();
            }
            this.mQs.animateHeaderSlidingIn(j);
        } else if (i2 == 2 && i == 1) {
            animateKeyguardStatusBarIn(360);
            this.mNotificationStackScroller.resetScrollPosition();
            if (!this.mQsExpanded) {
                this.mQs.animateHeaderSlidingOut();
            }
        } else {
            this.mKeyguardStatusBar.setAlpha(1.0f);
            this.mKeyguardStatusBar.setVisibility(z ? 0 : 4);
            if (z) {
                this.mKeyguardStatusBar.toggleContents(true);
            }
            if (!(!z || i2 == this.mBarState || (qs = this.mQs) == null)) {
                qs.hideImmediately();
            }
        }
        updateKeyguardStatusBarForHeadsUp();
        if (z) {
            updateDozingVisibilities(false);
        }
        updateQSPulseExpansion();
        maybeAnimateBottomAreaAlpha();
        resetHorizontalPanelPosition();
        updateQsState();
    }

    private void maybeAnimateBottomAreaAlpha() {
        this.mBottomAreaShadeAlphaAnimator.cancel();
        if (this.mBarState == 2) {
            this.mBottomAreaShadeAlphaAnimator.start();
        } else {
            this.mBottomAreaShadeAlpha = 1.0f;
        }
    }

    private void animateKeyguardStatusBarOut() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.mKeyguardStatusBar.getAlpha(), 0.0f});
        ofFloat.addUpdateListener(this.mStatusBarAnimateAlphaListener);
        ofFloat.setStartDelay(this.mKeyguardMonitor.isKeyguardFadingAway() ? this.mKeyguardMonitor.getKeyguardFadingAwayDelay() : 0);
        ofFloat.setDuration(this.mKeyguardMonitor.isKeyguardFadingAway() ? this.mKeyguardMonitor.getShortenedFadingAwayDuration() : 360);
        ofFloat.setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN);
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                NotificationPanelView.this.mAnimateKeyguardStatusBarInvisibleEndRunnable.run();
            }
        });
        ofFloat.start();
    }

    /* access modifiers changed from: private */
    public void animateKeyguardStatusBarIn(long j) {
        this.mKeyguardStatusBar.setVisibility(0);
        this.mKeyguardStatusBar.setAlpha(0.0f);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(this.mStatusBarAnimateAlphaListener);
        ofFloat.setDuration(j);
        ofFloat.setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN);
        ofFloat.start();
    }

    private void setKeyguardBottomAreaVisibility(int i, boolean z) {
        this.mKeyguardBottomArea.animate().cancel();
        if (z) {
            this.mKeyguardBottomArea.animate().alpha(0.0f).setStartDelay(this.mKeyguardMonitor.getKeyguardFadingAwayDelay()).setDuration(this.mKeyguardMonitor.getShortenedFadingAwayDuration()).setInterpolator(Interpolators.ALPHA_OUT).withEndAction(this.mAnimateKeyguardBottomAreaInvisibleEndRunnable).start();
        } else if (i == 1 || i == 2) {
            this.mKeyguardBottomArea.setVisibility(0);
            this.mKeyguardBottomArea.setAlpha(1.0f);
        } else {
            this.mKeyguardBottomArea.setVisibility(8);
        }
    }

    private void setKeyguardStatusViewVisibility(int i, boolean z, boolean z2) {
        this.mKeyguardStatusView.animate().cancel();
        this.mKeyguardStatusViewAnimating = false;
        if ((!z && this.mBarState == 1 && i != 1) || z2) {
            this.mKeyguardStatusViewAnimating = true;
            this.mKeyguardStatusView.animate().alpha(0.0f).setStartDelay(0).setDuration(160).setInterpolator(Interpolators.ALPHA_OUT).withEndAction(this.mAnimateKeyguardStatusViewGoneEndRunnable);
            if (z) {
                this.mKeyguardStatusView.animate().setStartDelay(this.mKeyguardMonitor.getKeyguardFadingAwayDelay()).setDuration(this.mKeyguardMonitor.getShortenedFadingAwayDuration()).start();
            }
        } else if (this.mBarState == 2 && i == 1) {
            this.mKeyguardStatusView.setVisibility(0);
            this.mKeyguardStatusViewAnimating = true;
            this.mKeyguardStatusView.setAlpha(0.0f);
            this.mKeyguardStatusView.animate().alpha(1.0f).setStartDelay(0).setDuration(320).setInterpolator(Interpolators.ALPHA_IN).withEndAction(this.mAnimateKeyguardStatusViewVisibleEndRunnable);
        } else if (i != 1) {
            this.mKeyguardStatusView.setVisibility(8);
            this.mKeyguardStatusView.setAlpha(1.0f);
        } else if (z) {
            this.mKeyguardStatusViewAnimating = true;
            this.mKeyguardStatusView.animate().alpha(0.0f).translationYBy(((float) (-getHeight())) * 0.05f).setInterpolator(Interpolators.FAST_OUT_LINEAR_IN).setDuration(125).setStartDelay(0).withEndAction(this.mAnimateKeyguardStatusViewInvisibleEndRunnable).start();
        } else {
            this.mKeyguardStatusView.setVisibility(0);
            this.mKeyguardStatusView.setAlpha(1.0f);
        }
    }

    /* access modifiers changed from: private */
    public void updateQsState() {
        this.mNotificationStackScroller.setQsExpanded(this.mQsExpanded);
        this.mNotificationStackScroller.setScrollingEnabled(this.mBarState != 1 && (!this.mQsExpanded || this.mQsExpansionFromOverscroll));
        updateEmptyShadeView();
        KeyguardUserSwitcher keyguardUserSwitcher = this.mKeyguardUserSwitcher;
        if (keyguardUserSwitcher != null && this.mQsExpanded && !this.mStackScrollerOverscrolling) {
            keyguardUserSwitcher.hideIfNotSimple(true);
        }
        C0862QS qs = this.mQs;
        if (qs != null) {
            qs.setExpanded(this.mQsExpanded);
        }
    }

    private void setQsExpansion(float f) {
        float min = Math.min(Math.max(f, (float) this.mQsMinExpansionHeight), (float) this.mQsMaxExpansionHeight);
        int i = this.mQsMaxExpansionHeight;
        this.mQsFullyExpanded = min == ((float) i) && i != 0;
        if (min > ((float) this.mQsMinExpansionHeight) && !this.mQsExpanded && !this.mStackScrollerOverscrolling && !this.mDozing) {
            setQsExpanded(true);
        } else if (min <= ((float) this.mQsMinExpansionHeight) && this.mQsExpanded) {
            setQsExpanded(false);
        }
        if (this.mKeyguardShowing) {
            if (min > ((float) this.mStatusBarHeight)) {
                setTopCorners(false);
            } else {
                setTopCorners(true);
            }
        }
        this.mQsExpansionHeight = min;
        updateQsExpansion();
        requestScrollerTopPaddingUpdate(false);
        updateHeaderKeyguardAlpha();
        int i2 = this.mBarState;
        if (i2 == 2 || i2 == 1) {
            updateKeyguardBottomAreaAlpha();
            updateBigClockAlpha();
        }
        if (this.mAccessibilityManager.isEnabled()) {
            setAccessibilityPaneTitle(determineAccessibilityPaneTitle());
        }
        if (!this.mFalsingManager.isUnlockingDisabled() && this.mQsFullyExpanded && this.mFalsingManager.shouldEnforceBouncer()) {
            this.mStatusBar.executeRunnableDismissingKeyguard((Runnable) null, (Runnable) null, false, true, false);
        }
        for (int i3 = 0; i3 < this.mExpansionListeners.size(); i3++) {
            PanelExpansionListener panelExpansionListener = this.mExpansionListeners.get(i3);
            int i4 = this.mQsMaxExpansionHeight;
            panelExpansionListener.onQsExpansionChanged(i4 != 0 ? this.mQsExpansionHeight / ((float) i4) : 0.0f);
        }
    }

    /* access modifiers changed from: protected */
    public void updateQsExpansion() {
        if (this.mQs != null) {
            float qsExpansionFraction = getQsExpansionFraction();
            this.mQs.setQsExpansion(qsExpansionFraction, getHeaderTranslation());
            this.mNotificationStackScroller.setQsExpansionFraction(qsExpansionFraction);
        }
    }

    private String determineAccessibilityPaneTitle() {
        C0862QS qs = this.mQs;
        if (qs != null && qs.isCustomizing()) {
            return getContext().getString(C1784R$string.accessibility_desc_quick_settings_edit);
        }
        if (this.mQsExpansionHeight != 0.0f && this.mQsFullyExpanded) {
            return getContext().getString(C1784R$string.accessibility_desc_quick_settings);
        }
        if (this.mBarState == 1) {
            return getContext().getString(C1784R$string.accessibility_desc_lock_screen);
        }
        return getContext().getString(C1784R$string.accessibility_desc_notification_shade);
    }

    private float calculateQsTopPadding() {
        if (!this.mKeyguardShowing || (!this.mQsExpandImmediate && (!this.mIsExpanding || !this.mQsExpandedWhenExpandingStarted))) {
            ValueAnimator valueAnimator = this.mQsSizeChangeAnimator;
            if (valueAnimator != null) {
                return (float) Math.max(((Integer) valueAnimator.getAnimatedValue()).intValue(), getKeyguardNotificationStaticPadding());
            }
            if (this.mKeyguardShowing) {
                return MathUtils.lerp((float) getKeyguardNotificationStaticPadding(), (float) (this.mQsMaxExpansionHeight + this.mQsNotificationTopPadding), getQsExpansionFraction());
            }
            return this.mQsExpansionHeight + ((float) this.mQsNotificationTopPadding);
        }
        int keyguardNotificationStaticPadding = getKeyguardNotificationStaticPadding();
        int i = this.mQsMaxExpansionHeight + this.mQsNotificationTopPadding;
        if (this.mBarState == 1) {
            i = Math.max(keyguardNotificationStaticPadding, i);
        }
        return (float) ((int) MathUtils.lerp((float) this.mQsMinExpansionHeight, (float) i, getExpandedFraction()));
    }

    private int getKeyguardNotificationStaticPadding() {
        if (!this.mKeyguardShowing) {
            return 0;
        }
        if (!this.mKeyguardBypassController.getBypassEnabled()) {
            return this.mClockPositionResult.stackScrollerPadding;
        }
        int i = this.mHeadsUpInset;
        if (!this.mNotificationStackScroller.isPulseExpanding()) {
            return i;
        }
        return (int) MathUtils.lerp((float) i, (float) this.mClockPositionResult.stackScrollerPadding, this.mNotificationStackScroller.calculateAppearFractionBypass());
    }

    /* access modifiers changed from: protected */
    public void requestScrollerTopPaddingUpdate(boolean z) {
        this.mNotificationStackScroller.updateTopPadding(calculateQsTopPadding(), z);
        if (this.mKeyguardShowing && this.mKeyguardBypassController.getBypassEnabled()) {
            updateQsExpansion();
        }
    }

    /* access modifiers changed from: private */
    public void updateQSPulseExpansion() {
        C0862QS qs = this.mQs;
        if (qs != null) {
            qs.setShowCollapsedOnKeyguard(this.mKeyguardShowing && this.mKeyguardBypassController.getBypassEnabled() && this.mNotificationStackScroller.isPulseExpanding());
        }
    }

    private void trackMovement(MotionEvent motionEvent) {
        VelocityTracker velocityTracker = this.mQsVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        this.mLastTouchX = motionEvent.getX();
        this.mLastTouchY = motionEvent.getY();
    }

    private void initVelocityTracker() {
        VelocityTracker velocityTracker = this.mQsVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.mQsVelocityTracker = VelocityTracker.obtain();
    }

    private float getCurrentQSVelocity() {
        VelocityTracker velocityTracker = this.mQsVelocityTracker;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000);
        return this.mQsVelocityTracker.getYVelocity();
    }

    private void cancelQsAnimation() {
        ValueAnimator valueAnimator = this.mQsExpansionAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    public void flingSettings(float f, int i) {
        flingSettings(f, i, (Runnable) null, false);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void flingSettings(float r7, int r8, final java.lang.Runnable r9, boolean r10) {
        /*
            r6 = this;
            r0 = 0
            r1 = 1
            if (r8 == 0) goto L_0x000b
            if (r8 == r1) goto L_0x0008
            r2 = r0
            goto L_0x000e
        L_0x0008:
            int r2 = r6.mQsMinExpansionHeight
            goto L_0x000d
        L_0x000b:
            int r2 = r6.mQsMaxExpansionHeight
        L_0x000d:
            float r2 = (float) r2
        L_0x000e:
            float r3 = r6.mQsExpansionHeight
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x001a
            if (r9 == 0) goto L_0x0019
            r9.run()
        L_0x0019:
            return
        L_0x001a:
            r3 = 0
            if (r8 != 0) goto L_0x001f
            r8 = r1
            goto L_0x0020
        L_0x001f:
            r8 = r3
        L_0x0020:
            int r4 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r4 <= 0) goto L_0x0026
            if (r8 == 0) goto L_0x002c
        L_0x0026:
            int r4 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x002f
            if (r8 == 0) goto L_0x002f
        L_0x002c:
            r7 = r0
            r0 = r1
            goto L_0x0030
        L_0x002f:
            r0 = r3
        L_0x0030:
            r4 = 2
            float[] r4 = new float[r4]
            float r5 = r6.mQsExpansionHeight
            r4[r3] = r5
            r4[r1] = r2
            android.animation.ValueAnimator r1 = android.animation.ValueAnimator.ofFloat(r4)
            if (r10 == 0) goto L_0x004a
            android.view.animation.Interpolator r7 = com.android.systemui.Interpolators.TOUCH_RESPONSE
            r1.setInterpolator(r7)
            r2 = 368(0x170, double:1.82E-321)
            r1.setDuration(r2)
            goto L_0x0051
        L_0x004a:
            com.android.systemui.statusbar.FlingAnimationUtils r10 = r6.mFlingAnimationUtils
            float r3 = r6.mQsExpansionHeight
            r10.apply(r1, r3, r2, r7)
        L_0x0051:
            if (r0 == 0) goto L_0x0058
            r2 = 350(0x15e, double:1.73E-321)
            r1.setDuration(r2)
        L_0x0058:
            com.android.systemui.statusbar.phone.-$$Lambda$NotificationPanelView$GBIvrcRMfk5MdTVeindE6SW10Nw r7 = new com.android.systemui.statusbar.phone.-$$Lambda$NotificationPanelView$GBIvrcRMfk5MdTVeindE6SW10Nw
            r7.<init>()
            r1.addUpdateListener(r7)
            com.android.systemui.statusbar.phone.NotificationPanelView$18 r7 = new com.android.systemui.statusbar.phone.NotificationPanelView$18
            r7.<init>(r9)
            r1.addListener(r7)
            r1.start()
            r6.mQsExpansionAnimator = r1
            r6.mQsAnimatorExpand = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.NotificationPanelView.flingSettings(float, int, java.lang.Runnable, boolean):void");
    }

    public /* synthetic */ void lambda$flingSettings$3$NotificationPanelView(ValueAnimator valueAnimator) {
        setQsExpansion(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        r0 = r5.mQs;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean shouldQuickSettingsIntercept(float r6, float r7, float r8) {
        /*
            r5 = this;
            boolean r0 = r5.mQsExpansionEnabled
            r1 = 0
            if (r0 == 0) goto L_0x0072
            boolean r0 = r5.mCollapsedOnDown
            if (r0 != 0) goto L_0x0072
            boolean r0 = r5.mKeyguardShowing
            if (r0 == 0) goto L_0x0015
            com.android.systemui.statusbar.phone.KeyguardBypassController r0 = r5.mKeyguardBypassController
            boolean r0 = r0.getBypassEnabled()
            if (r0 != 0) goto L_0x0072
        L_0x0015:
            boolean r0 = r5.isQSEventBlocked()
            if (r0 == 0) goto L_0x001c
            goto L_0x0072
        L_0x001c:
            boolean r0 = r5.mKeyguardShowing
            if (r0 != 0) goto L_0x002a
            com.android.systemui.plugins.qs.QS r0 = r5.mQs
            if (r0 != 0) goto L_0x0025
            goto L_0x002a
        L_0x0025:
            android.view.View r0 = r0.getHeader()
            goto L_0x002c
        L_0x002a:
            com.android.systemui.statusbar.phone.KeyguardStatusBarView r0 = r5.mKeyguardStatusBar
        L_0x002c:
            android.widget.FrameLayout r2 = r5.mQsFrame
            float r2 = r2.getX()
            int r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            r3 = 1
            if (r2 < 0) goto L_0x005d
            android.widget.FrameLayout r2 = r5.mQsFrame
            float r2 = r2.getX()
            android.widget.FrameLayout r4 = r5.mQsFrame
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r2 = r2 + r4
            int r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x005d
            int r2 = r0.getTop()
            float r2 = (float) r2
            int r2 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x005d
            int r0 = r0.getBottom()
            float r0 = (float) r0
            int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x005d
            r0 = r3
            goto L_0x005e
        L_0x005d:
            r0 = r1
        L_0x005e:
            boolean r2 = r5.mQsExpanded
            if (r2 == 0) goto L_0x0071
            if (r0 != 0) goto L_0x006f
            r0 = 0
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 >= 0) goto L_0x0070
            boolean r5 = r5.isInQsArea(r6, r7)
            if (r5 == 0) goto L_0x0070
        L_0x006f:
            r1 = r3
        L_0x0070:
            return r1
        L_0x0071:
            return r0
        L_0x0072:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.NotificationPanelView.shouldQuickSettingsIntercept(float, float, float):boolean");
    }

    /* access modifiers changed from: protected */
    public boolean isScrolledToBottom() {
        if (isInSettings() || this.mBarState == 1 || this.mNotificationStackScroller.isScrolledToBottom()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public int getMaxPanelHeight() {
        if (!this.mKeyguardBypassController.getBypassEnabled() || this.mBarState != 1) {
            return getMaxPanelHeightNonBypass();
        }
        return getMaxPanelHeightBypass();
    }

    private int getMaxPanelHeightNonBypass() {
        int i;
        int i2 = this.mStatusBarMinHeight;
        if (this.mBarState != 1 && this.mNotificationStackScroller.getNotGoneChildCount() == 0) {
            i2 = Math.max(i2, (int) (((float) this.mQsMinExpansionHeight) + getOverExpansionAmount()));
        }
        if (this.mQsExpandImmediate || this.mQsExpanded || ((this.mIsExpanding && this.mQsExpandedWhenExpandingStarted) || this.mPulsing)) {
            i = calculatePanelHeightQsExpanded();
        } else {
            i = calculatePanelHeightShade();
        }
        return Math.max(i2, i);
    }

    private int getMaxPanelHeightBypass() {
        int expandedClockPosition = this.mClockPositionAlgorithm.getExpandedClockPosition() + this.mKeyguardStatusView.getHeight();
        return this.mNotificationStackScroller.getVisibleNotificationCount() != 0 ? (int) (((float) expandedClockPosition) + (((float) this.mShelfHeight) / 2.0f) + (((float) this.mDarkIconSize) / 2.0f)) : expandedClockPosition;
    }

    public boolean isInSettings() {
        return this.mQsExpanded;
    }

    public boolean isExpanding() {
        return this.mIsExpanding;
    }

    /* access modifiers changed from: protected */
    public void onHeightUpdated(float f) {
        float f2;
        if ((!this.mQsExpanded || this.mQsExpandImmediate || (this.mIsExpanding && this.mQsExpandedWhenExpandingStarted)) && this.mStackScrollerMeasuringPass <= 2) {
            positionClockAndNotifications();
        }
        if (this.mQsExpandImmediate || (this.mQsExpanded && !this.mQsTracking && this.mQsExpansionAnimator == null && !this.mQsExpansionFromOverscroll)) {
            if (this.mKeyguardShowing) {
                f2 = f / ((float) getMaxPanelHeight());
            } else {
                float intrinsicPadding = (float) (this.mNotificationStackScroller.getIntrinsicPadding() + this.mNotificationStackScroller.getLayoutMinHeight());
                f2 = (f - intrinsicPadding) / (((float) calculatePanelHeightQsExpanded()) - intrinsicPadding);
            }
            int i = this.mQsMinExpansionHeight;
            setQsExpansion(((float) i) + (f2 * ((float) (this.mQsMaxExpansionHeight - i))));
        }
        if (!this.mKeyguardShowing) {
            if (f > ((float) this.mStatusBarHeight)) {
                setTopCorners(false);
            } else {
                setTopCorners(true);
            }
        }
        updateExpandedHeight(f);
        updateHeader();
        updateNotificationTranslucency();
        updatePanelExpanded();
        updateGestureExclusionRect();
    }

    private void updatePanelExpanded() {
        boolean z = !isFullyCollapsed() || this.mExpectingSynthesizedDown;
        if (this.mPanelExpanded != z) {
            this.mHeadsUpManager.setIsPanelExpanded(z);
            this.mStatusBar.setPanelExpanded(z);
            this.mPanelExpanded = z;
        }
    }

    private int calculatePanelHeightShade() {
        int height = (int) (((float) (this.mNotificationStackScroller.getHeight() - this.mNotificationStackScroller.getEmptyBottomMargin())) + this.mNotificationStackScroller.getTopPaddingOverflow());
        return this.mBarState == 1 ? Math.max(height, this.mClockPositionAlgorithm.getExpandedClockPosition() + this.mKeyguardStatusView.getHeight() + this.mNotificationStackScroller.getIntrinsicContentHeight()) : height;
    }

    private int calculatePanelHeightQsExpanded() {
        float height = (float) ((this.mNotificationStackScroller.getHeight() - this.mNotificationStackScroller.getEmptyBottomMargin()) - this.mNotificationStackScroller.getTopPadding());
        if (this.mNotificationStackScroller.getNotGoneChildCount() == 0 && this.mShowEmptyShadeView) {
            height = (float) this.mNotificationStackScroller.getEmptyShadeViewHeight();
        }
        int i = this.mQsMaxExpansionHeight;
        if (this.mKeyguardShowing) {
            i += this.mQsNotificationTopPadding;
        }
        ValueAnimator valueAnimator = this.mQsSizeChangeAnimator;
        if (valueAnimator != null) {
            i = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        }
        float max = ((float) Math.max(i, this.mBarState == 1 ? this.mClockPositionResult.stackScrollerPadding : 0)) + height + this.mNotificationStackScroller.getTopPaddingOverflow();
        if (max > ((float) this.mNotificationStackScroller.getHeight())) {
            max = Math.max((float) (i + this.mNotificationStackScroller.getLayoutMinHeight()), (float) this.mNotificationStackScroller.getHeight());
        }
        return (int) max;
    }

    private void updateNotificationTranslucency() {
        float fadeoutAlpha = (!this.mClosingWithAlphaFadeOut || this.mExpandingFromHeadsUp || this.mHeadsUpManager.hasPinnedHeadsUp()) ? 1.0f : getFadeoutAlpha();
        if (this.mBarState == 1 && !this.mHintAnimationRunning && !this.mKeyguardBypassController.getBypassEnabled()) {
            fadeoutAlpha *= this.mClockPositionResult.clockAlpha;
        }
        this.mNotificationStackScroller.setAlpha(fadeoutAlpha);
        this.mStatusBar.updateBlurVisibility();
    }

    private float getFadeoutAlpha() {
        if (this.mQsMinExpansionHeight == 0) {
            return 1.0f;
        }
        return (float) Math.pow((double) Math.max(0.0f, Math.min(getExpandedHeight() / ((float) this.mQsMinExpansionHeight), 1.0f)), 0.75d);
    }

    /* access modifiers changed from: protected */
    public float getOverExpansionAmount() {
        return this.mNotificationStackScroller.getCurrentOverScrollAmount(true);
    }

    /* access modifiers changed from: protected */
    public float getOverExpansionPixels() {
        return this.mNotificationStackScroller.getCurrentOverScrolledPixels(true);
    }

    private void updateHeader() {
        if (this.mBarState == 1) {
            updateHeaderKeyguardAlpha();
        }
        updateQsExpansion();
    }

    /* access modifiers changed from: protected */
    public float getHeaderTranslation() {
        if (this.mBarState == 1 && !this.mKeyguardBypassController.getBypassEnabled()) {
            return (float) (-this.mQs.getQsMinExpansionHeight());
        }
        float calculateAppearFraction = this.mNotificationStackScroller.calculateAppearFraction(this.mExpandedHeight);
        float f = -this.mQsExpansionHeight;
        if (this.mKeyguardBypassController.getBypassEnabled() && isOnKeyguard() && this.mNotificationStackScroller.isPulseExpanding()) {
            if (this.mPulseExpansionHandler.isExpanding() || this.mPulseExpansionHandler.getLeavingLockscreen()) {
                calculateAppearFraction = this.mNotificationStackScroller.calculateAppearFractionBypass();
            } else {
                calculateAppearFraction = 0.0f;
            }
            f = (float) (-this.mQs.getQsMinExpansionHeight());
        }
        return Math.min(0.0f, MathUtils.lerp(f, 0.0f, Math.min(1.0f, calculateAppearFraction)) + this.mExpandOffset);
    }

    private float getKeyguardContentsAlpha() {
        float f;
        float f2;
        if (this.mBarState == 1) {
            f2 = getExpandedHeight();
            f = (float) (this.mKeyguardStatusBar.getHeight() + this.mNotificationsHeaderCollideDistance);
        } else {
            f2 = getExpandedHeight();
            f = (float) this.mKeyguardStatusBar.getHeight();
        }
        return (float) Math.pow((double) MathUtils.saturate(f2 / f), 0.75d);
    }

    /* access modifiers changed from: private */
    public void updateHeaderKeyguardAlpha() {
        if (this.mKeyguardShowing) {
            float min = Math.min(getKeyguardContentsAlpha(), 1.0f - Math.min(1.0f, getQsExpansionFraction() * 2.0f)) * this.mKeyguardStatusBarAnimateAlpha * (1.0f - this.mKeyguardHeadsUpShowingAmount);
            this.mKeyguardStatusBar.setAlpha(min);
            int i = 0;
            boolean z = (this.mFirstBypassAttempt && this.mUpdateMonitor.shouldListenForFace()) || this.mDelayShowingKeyguardStatusBar;
            KeyguardStatusBarView keyguardStatusBarView = this.mKeyguardStatusBar;
            if (min == 0.0f || this.mDozing || z) {
                i = 4;
            }
            keyguardStatusBarView.setVisibility(i);
        }
    }

    private void updateKeyguardBottomAreaAlpha() {
        float min = Math.min(MathUtils.map(isUnlockHintRunning() ? 0.0f : 0.95f, 1.0f, 0.0f, 1.0f, getExpandedFraction()), 1.0f - getQsExpansionFraction()) * this.mBottomAreaShadeAlpha;
        this.mKeyguardBottomArea.setAffordanceAlpha(min);
        this.mKeyguardBottomArea.setImportantForAccessibility(min == 0.0f ? 4 : 0);
        View ambientIndicationContainer = this.mStatusBar.getAmbientIndicationContainer();
        if (ambientIndicationContainer != null) {
            ambientIndicationContainer.setAlpha(min);
        }
    }

    private void updateBigClockAlpha() {
        this.mBigClockContainer.setAlpha(Math.min(MathUtils.map(isUnlockHintRunning() ? 0.0f : 0.95f, 1.0f, 0.0f, 1.0f, getExpandedFraction()), 1.0f - getQsExpansionFraction()));
    }

    private void setTopCorners(boolean z) {
        if (this.mScreenDecorations == null) {
            this.mScreenDecorations = (ScreenDecorations) SysUiServiceProvider.getComponent(this.mContext, ScreenDecorations.class);
        }
        this.mScreenDecorations.setTopCorners(z);
    }

    /* access modifiers changed from: protected */
    public void onExpandingStarted() {
        super.onExpandingStarted();
        this.mNotificationStackScroller.onExpansionStarted();
        this.mIsExpanding = true;
        this.mQsExpandedWhenExpandingStarted = this.mQsFullyExpanded;
        if (this.mQsExpanded) {
            onQsExpansionStarted();
        }
        C0862QS qs = this.mQs;
        if (qs != null) {
            qs.setHeaderListening(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onExpandingFinished() {
        super.onExpandingFinished();
        this.mNotificationStackScroller.onExpansionStopped();
        this.mHeadsUpManager.onExpandingFinished();
        this.mIsExpanding = false;
        if (isFullyCollapsed()) {
            DejankUtils.postAfterTraversal(new Runnable() {
                public void run() {
                    NotificationPanelView.this.setListening(false);
                }
            });
            postOnAnimation(new Runnable() {
                public void run() {
                    NotificationPanelView.this.getParent().invalidateChild(NotificationPanelView.this, NotificationPanelView.mDummyDirtyRect);
                }
            });
        } else {
            setListening(true);
        }
        this.mQsExpandImmediate = false;
        this.mNotificationStackScroller.setShouldShowShelfOnly(false);
        this.mTwoFingerQsExpandPossible = false;
        this.mIsExpansionFromHeadsUp = false;
        notifyListenersTrackingHeadsUp((ExpandableNotificationRow) null);
        this.mExpandingFromHeadsUp = false;
        setPanelScrimMinFraction(0.0f);
    }

    private void notifyListenersTrackingHeadsUp(ExpandableNotificationRow expandableNotificationRow) {
        for (int i = 0; i < this.mTrackingHeadsUpListeners.size(); i++) {
            this.mTrackingHeadsUpListeners.get(i).accept(expandableNotificationRow);
        }
    }

    /* access modifiers changed from: private */
    public void setListening(boolean z) {
        C0862QS qs = this.mQs;
        if (qs != null) {
            qs.setListening(z);
        }
    }

    public void expand(boolean z) {
        super.expand(z);
        setListening(true);
    }

    /* access modifiers changed from: protected */
    public void setOverExpansion(float f, boolean z) {
        if (!this.mConflictingQsExpansionGesture && !this.mQsExpandImmediate && this.mBarState != 1) {
            this.mNotificationStackScroller.setOnHeightChangedListener((ExpandableView.OnHeightChangedListener) null);
            if (z) {
                this.mNotificationStackScroller.setOverScrolledPixels(f, true, false);
            } else {
                this.mNotificationStackScroller.setOverScrollAmount(f, true, false);
            }
            this.mNotificationStackScroller.setOnHeightChangedListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onTrackingStarted() {
        this.mFalsingManager.onTrackingStarted(this.mStatusBar.isKeyguardCurrentlySecure());
        super.onTrackingStarted();
        if (this.mQsFullyExpanded) {
            this.mQsExpandImmediate = true;
            this.mNotificationStackScroller.setShouldShowShelfOnly(true);
        }
        int i = this.mBarState;
        if (i == 1 || i == 2) {
            this.mAffordanceHelper.animateHideLeftRightIcon();
        }
        this.mNotificationStackScroller.onPanelTrackingStarted();
    }

    /* access modifiers changed from: protected */
    public void onTrackingStopped(boolean z) {
        this.mFalsingManager.onTrackingStopped();
        super.onTrackingStopped(z);
        if (z) {
            this.mNotificationStackScroller.setOverScrolledPixels(0.0f, true, true);
        }
        this.mNotificationStackScroller.onPanelTrackingStopped();
        if (z) {
            int i = this.mBarState;
            if ((i == 1 || i == 2) && !this.mHintAnimationRunning) {
                this.mAffordanceHelper.reset(true);
            }
        }
    }

    public void onHeightChanged(ExpandableView expandableView, boolean z) {
        if (expandableView != null || !this.mQsExpanded) {
            if (z && this.mInterpolatedDarkAmount == 0.0f) {
                this.mAnimateNextPositionUpdate = true;
            }
            ExpandableView firstChildNotGone = this.mNotificationStackScroller.getFirstChildNotGone();
            ExpandableNotificationRow expandableNotificationRow = firstChildNotGone instanceof ExpandableNotificationRow ? (ExpandableNotificationRow) firstChildNotGone : null;
            if (expandableNotificationRow != null && (expandableView == expandableNotificationRow || expandableNotificationRow.getNotificationParent() == expandableNotificationRow)) {
                requestScrollerTopPaddingUpdate(false);
            }
            requestPanelHeightUpdate();
        }
    }

    public void onQsHeightChanged() {
        C0862QS qs = this.mQs;
        this.mQsMaxExpansionHeight = qs != null ? qs.getDesiredHeight() : 0;
        if (this.mQsExpanded && this.mQsFullyExpanded) {
            this.mQsExpansionHeight = (float) this.mQsMaxExpansionHeight;
            requestScrollerTopPaddingUpdate(false);
            requestPanelHeightUpdate();
        }
        if (this.mAccessibilityManager.isEnabled()) {
            setAccessibilityPaneTitle(determineAccessibilityPaneTitle());
        }
        this.mNotificationStackScroller.setMaxTopPadding(this.mQsMaxExpansionHeight + this.mQsNotificationTopPadding);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mAffordanceHelper.onConfigurationChanged();
        if (configuration.orientation != this.mLastOrientation) {
            resetHorizontalPanelPosition();
        }
        this.mLastOrientation = configuration.orientation;
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        this.mNavigationBarBottomHeight = windowInsets.getStableInsetBottom();
        updateMaxHeadsUpTranslation();
        return windowInsets;
    }

    private void updateMaxHeadsUpTranslation() {
        this.mNotificationStackScroller.setHeadsUpBoundaries(getHeight(), this.mNavigationBarBottomHeight);
    }

    public void onRtlPropertiesChanged(int i) {
        if (i != this.mOldLayoutDirection) {
            this.mAffordanceHelper.onRtlPropertiesChanged();
            this.mOldLayoutDirection = i;
        }
    }

    public void onClick(View view) {
        onQsExpansionStarted();
        if (this.mQsExpanded) {
            flingSettings(0.0f, 1, (Runnable) null, true);
        } else if (this.mQsExpansionEnabled) {
            this.mLockscreenGestureLogger.write(195, 0, 0);
            flingSettings(0.0f, 0, (Runnable) null, true);
        }
    }

    public void onAnimationToSideStarted(boolean z, float f, float f2) {
        if (getLayoutDirection() != 1) {
            z = !z;
        }
        this.mIsLaunchTransitionRunning = true;
        this.mLaunchAnimationEndRunnable = null;
        float displayDensity = this.mStatusBar.getDisplayDensity();
        int abs = Math.abs((int) (f / displayDensity));
        int abs2 = Math.abs((int) (f2 / displayDensity));
        if (z) {
            this.mLockscreenGestureLogger.write(190, abs, abs2);
            this.mFalsingManager.onLeftAffordanceOn();
            if (this.mFalsingManager.shouldEnforceBouncer()) {
                this.mStatusBar.executeRunnableDismissingKeyguard(new Runnable() {
                    public void run() {
                        NotificationPanelView.this.mKeyguardBottomArea.launchLeftAffordance();
                    }
                }, (Runnable) null, true, false, true);
            } else {
                this.mKeyguardBottomArea.launchLeftAffordance();
            }
        } else {
            if ("lockscreen_affordance".equals(this.mLastCameraLaunchSource)) {
                this.mLockscreenGestureLogger.write(189, abs, abs2);
            }
            this.mFalsingManager.onCameraOn();
            if (this.mFalsingManager.shouldEnforceBouncer()) {
                this.mStatusBar.executeRunnableDismissingKeyguard(new Runnable() {
                    public void run() {
                        NotificationPanelView notificationPanelView = NotificationPanelView.this;
                        notificationPanelView.mKeyguardBottomArea.launchCamera(notificationPanelView.mLastCameraLaunchSource);
                    }
                }, (Runnable) null, true, false, true);
            } else {
                this.mKeyguardBottomArea.launchCamera(this.mLastCameraLaunchSource);
            }
        }
        this.mStatusBar.startLaunchTransitionTimeout();
        this.mBlockTouches = true;
    }

    public void onAnimationToSideEnded() {
        this.mIsLaunchTransitionRunning = false;
        this.mIsLaunchTransitionFinished = true;
        Runnable runnable = this.mLaunchAnimationEndRunnable;
        if (runnable != null) {
            runnable.run();
            this.mLaunchAnimationEndRunnable = null;
        }
        this.mStatusBar.readyForKeyguardDone();
    }

    /* access modifiers changed from: protected */
    public void startUnlockHintAnimation() {
        if (this.mPowerManager.isPowerSaveMode()) {
            onUnlockHintStarted();
            onUnlockHintFinished();
            return;
        }
        super.startUnlockHintAnimation();
    }

    public float getMaxTranslationDistance() {
        return (float) Math.hypot((double) getWidth(), (double) getHeight());
    }

    public void onSwipingStarted(boolean z) {
        this.mFalsingManager.onAffordanceSwipingStarted(z);
        if (getLayoutDirection() == 1) {
            z = !z;
        }
        if (z) {
            this.mKeyguardBottomArea.bindCameraPrewarmService();
        }
        requestDisallowInterceptTouchEvent(true);
        this.mOnlyAffordanceInThisMotion = true;
        this.mQsTracking = false;
    }

    public void onSwipingAborted() {
        this.mFalsingManager.onAffordanceSwipingAborted();
        this.mKeyguardBottomArea.unbindCameraPrewarmService(false);
    }

    public void onIconClicked(boolean z) {
        if (!this.mHintAnimationRunning) {
            this.mHintAnimationRunning = true;
            this.mAffordanceHelper.startHintAnimation(z, new Runnable() {
                public void run() {
                    NotificationPanelView notificationPanelView = NotificationPanelView.this;
                    notificationPanelView.mHintAnimationRunning = false;
                    notificationPanelView.mStatusBar.onHintFinished();
                }
            });
            if (getLayoutDirection() == 1) {
                z = !z;
            }
            if (z) {
                this.mStatusBar.onCameraHintStarted();
            } else if (this.mKeyguardBottomArea.isLeftVoiceAssist()) {
                this.mStatusBar.onVoiceAssistHintStarted();
            } else {
                this.mStatusBar.onPhoneHintStarted();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onUnlockHintFinished() {
        super.onUnlockHintFinished();
        this.mNotificationStackScroller.setUnlockHintRunning(false);
    }

    /* access modifiers changed from: protected */
    public void onUnlockHintStarted() {
        super.onUnlockHintStarted();
        this.mNotificationStackScroller.setUnlockHintRunning(true);
    }

    public KeyguardAffordanceView getLeftIcon() {
        if (getLayoutDirection() == 1) {
            return this.mKeyguardBottomArea.getRightView();
        }
        return this.mKeyguardBottomArea.getLeftView();
    }

    public KeyguardAffordanceView getRightIcon() {
        if (getLayoutDirection() == 1) {
            return this.mKeyguardBottomArea.getLeftView();
        }
        return this.mKeyguardBottomArea.getRightView();
    }

    public View getLeftPreview() {
        if (getLayoutDirection() == 1) {
            return this.mKeyguardBottomArea.getRightPreview();
        }
        return this.mKeyguardBottomArea.getLeftPreview();
    }

    public View getRightPreview() {
        if (getLayoutDirection() == 1) {
            return this.mKeyguardBottomArea.getLeftPreview();
        }
        return this.mKeyguardBottomArea.getRightPreview();
    }

    public float getAffordanceFalsingFactor() {
        return this.mStatusBar.isWakeUpComingFromTouch() ? 1.5f : 1.0f;
    }

    public boolean needsAntiFalsing() {
        return this.mBarState == 1;
    }

    /* access modifiers changed from: protected */
    public float getPeekHeight() {
        int i;
        if (this.mNotificationStackScroller.getNotGoneChildCount() > 0) {
            i = this.mNotificationStackScroller.getPeekHeight();
        } else {
            i = this.mQsMinExpansionHeight;
        }
        return (float) i;
    }

    /* access modifiers changed from: protected */
    public boolean shouldUseDismissingAnimation() {
        return this.mBarState != 0 && (!this.mStatusBar.isKeyguardCurrentlySecure() || !isTracking());
    }

    /* access modifiers changed from: protected */
    public boolean fullyExpandedClearAllVisible() {
        return this.mNotificationStackScroller.isFooterViewNotGone() && this.mNotificationStackScroller.isScrolledToBottom() && !this.mQsExpandImmediate;
    }

    /* access modifiers changed from: protected */
    public boolean isClearAllVisible() {
        return this.mNotificationStackScroller.isFooterViewContentVisible();
    }

    /* access modifiers changed from: protected */
    public int getClearAllHeight() {
        return this.mNotificationStackScroller.getFooterViewHeight();
    }

    /* access modifiers changed from: protected */
    public boolean isTrackingBlocked() {
        return (this.mConflictingQsExpansionGesture && this.mQsExpanded) || this.mBlockingExpansionForCurrentTouch;
    }

    public boolean isQsExpanded() {
        return this.mQsExpanded;
    }

    public boolean isQsDetailShowing() {
        return this.mQs.isShowingDetail();
    }

    public void closeQsDetail() {
        this.mQs.closeDetail();
    }

    public boolean isLaunchTransitionFinished() {
        return this.mIsLaunchTransitionFinished;
    }

    public boolean isLaunchTransitionRunning() {
        return this.mIsLaunchTransitionRunning;
    }

    public void setLaunchTransitionEndRunnable(Runnable runnable) {
        this.mLaunchAnimationEndRunnable = runnable;
    }

    public void setEmptyDragAmount(float f) {
        this.mEmptyDragAmount = f * 0.2f;
        positionClockAndNotifications();
    }

    private void updateDozingVisibilities(boolean z) {
        this.mKeyguardBottomArea.setDozing(this.mDozing, z);
        if (!this.mDozing && z) {
            animateKeyguardStatusBarIn(360);
        }
    }

    public boolean isDozing() {
        return this.mDozing;
    }

    public void showEmptyShadeView(boolean z) {
        this.mShowEmptyShadeView = z;
        updateEmptyShadeView();
    }

    private void updateEmptyShadeView() {
        this.mNotificationStackScroller.updateEmptyShadeView(this.mShowEmptyShadeView && !this.mQsExpanded);
    }

    public void setQsScrimEnabled(boolean z) {
        boolean z2 = this.mQsScrimEnabled != z;
        this.mQsScrimEnabled = z;
        if (z2) {
            updateQsState();
        }
    }

    public void setKeyguardUserSwitcher(KeyguardUserSwitcher keyguardUserSwitcher) {
        this.mKeyguardUserSwitcher = keyguardUserSwitcher;
    }

    public void onScreenTurningOn() {
        this.mKeyguardStatusView.dozeTimeTick();
    }

    public void onEmptySpaceClicked(float f, float f2) {
        onEmptySpaceClick(f);
    }

    /* access modifiers changed from: protected */
    public boolean onMiddleClicked() {
        int i = this.mBarState;
        if (i == 0) {
            post(this.mPostCollapseRunnable);
            return false;
        } else if (i != 1) {
            if (i == 2 && !this.mQsExpanded) {
                this.mShadeController.goToKeyguard();
            }
            return true;
        } else {
            if (!this.mDozingOnDown) {
                if (this.mKeyguardBypassController.getBypassEnabled()) {
                    this.mUpdateMonitor.requestFaceAuth();
                } else {
                    this.mLockscreenGestureLogger.write(188, 0, 0);
                    startUnlockHintAnimation();
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mCurrentPanelAlpha != 255) {
            canvas.drawRect(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), this.mAlphaPaint);
        }
    }

    public float getCurrentPanelAlpha() {
        return (float) this.mCurrentPanelAlpha;
    }

    public boolean setPanelAlpha(int i, boolean z) {
        if (this.mPanelAlpha == i) {
            return false;
        }
        this.mPanelAlpha = i;
        PropertyAnimator.setProperty(this, this.PANEL_ALPHA, (float) i, i == 255 ? this.PANEL_ALPHA_IN_PROPERTIES : this.PANEL_ALPHA_OUT_PROPERTIES, z);
        return true;
    }

    public boolean setPanelAlphaFast(int i, boolean z) {
        if (this.mPanelAlpha == i) {
            return false;
        }
        this.mPanelAlpha = i;
        PropertyAnimator.setProperty(this, this.PANEL_ALPHA, (float) i, i == 255 ? this.PANEL_ALPHA_IN_FAST_PROPERTIES : this.PANEL_ALPHA_OUT_FAST_PROPERTIES, z);
        return true;
    }

    public void setPanelAlphaInternal(float f) {
        this.mCurrentPanelAlpha = (int) f;
        this.mAlphaPaint.setARGB(this.mCurrentPanelAlpha, 255, 255, 255);
        invalidate();
    }

    public void setPanelAlphaEndAction(Runnable runnable) {
        this.mPanelAlphaEndAction = runnable;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void onHeadsUpPinnedModeChanged(boolean z) {
        this.mNotificationStackScroller.setInHeadsUpPinnedMode(z);
        if (z) {
            this.mHeadsUpExistenceChangedRunnable.run();
            updateNotificationTranslucency();
        } else {
            setHeadsUpAnimatingAway(true);
            this.mNotificationStackScroller.runAfterAnimationFinished(this.mHeadsUpExistenceChangedRunnable);
        }
        updateGestureExclusionRect();
        this.mHeadsUpPinnedMode = z;
        updateHeadsUpVisibility();
        updateKeyguardStatusBarForHeadsUp();
    }

    /* access modifiers changed from: private */
    public void updateKeyguardStatusBarForHeadsUp() {
        boolean z = this.mKeyguardShowing && this.mHeadsUpAppearanceController.shouldBeVisible();
        if (this.mShowingKeyguardHeadsUp != z) {
            this.mShowingKeyguardHeadsUp = z;
            float f = 0.0f;
            if (this.mKeyguardShowing) {
                AnimatableProperty animatableProperty = KEYGUARD_HEADS_UP_SHOWING_AMOUNT;
                if (z) {
                    f = 1.0f;
                }
                PropertyAnimator.setProperty(this, animatableProperty, f, KEYGUARD_HUN_PROPERTIES, true);
                return;
            }
            PropertyAnimator.applyImmediately(this, KEYGUARD_HEADS_UP_SHOWING_AMOUNT, 0.0f);
        }
    }

    /* access modifiers changed from: private */
    public void setKeyguardHeadsUpShowingAmount(float f) {
        this.mKeyguardHeadsUpShowingAmount = f;
        updateHeaderKeyguardAlpha();
    }

    /* access modifiers changed from: private */
    public float getKeyguardHeadsUpShowingAmount() {
        return this.mKeyguardHeadsUpShowingAmount;
    }

    public void setHeadsUpAnimatingAway(boolean z) {
        this.mHeadsUpAnimatingAway = z;
        this.mNotificationStackScroller.setHeadsUpAnimatingAway(z);
        updateHeadsUpVisibility();
    }

    private void updateHeadsUpVisibility() {
        ((PhoneStatusBarView) this.mBar).setHeadsUpVisible(this.mHeadsUpAnimatingAway || this.mHeadsUpPinnedMode);
    }

    public void onHeadsUpPinned(NotificationEntry notificationEntry) {
        if (!isOnKeyguard()) {
            this.mNotificationStackScroller.generateHeadsUpAnimation(notificationEntry.getHeadsUpAnimationView(), true);
        }
    }

    public void onHeadsUpUnPinned(NotificationEntry notificationEntry) {
        if (isFullyCollapsed() && notificationEntry.isRowHeadsUp() && !isOnKeyguard()) {
            this.mNotificationStackScroller.generateHeadsUpAnimation(notificationEntry.getHeadsUpAnimationView(), false);
            notificationEntry.setHeadsUpIsVisible();
        }
    }

    public void onHeadsUpStateChanged(NotificationEntry notificationEntry, boolean z) {
        this.mNotificationStackScroller.generateHeadsUpAnimation(notificationEntry, z);
    }

    public void setHeadsUpManager(HeadsUpManagerPhone headsUpManagerPhone) {
        super.setHeadsUpManager(headsUpManagerPhone);
        this.mHeadsUpTouchHelper = new HeadsUpTouchHelper(headsUpManagerPhone, this.mNotificationStackScroller.getHeadsUpCallback(), this);
    }

    public void setTrackedHeadsUp(ExpandableNotificationRow expandableNotificationRow) {
        if (expandableNotificationRow != null) {
            notifyListenersTrackingHeadsUp(expandableNotificationRow);
            this.mExpandingFromHeadsUp = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onClosingFinished() {
        super.onClosingFinished();
        resetHorizontalPanelPosition();
        setClosingWithAlphaFadeout(false);
    }

    private void setClosingWithAlphaFadeout(boolean z) {
        this.mClosingWithAlphaFadeOut = z;
        this.mNotificationStackScroller.forceNoOverlappingRendering(z);
    }

    /* access modifiers changed from: protected */
    public void updateVerticalPanelPosition(float f) {
        if (this.mKeyguardShowing || ((float) this.mNotificationStackScroller.getWidth()) * 1.75f > ((float) getWidth())) {
            resetHorizontalPanelPosition();
            return;
        }
        float width = (float) (this.mPositionMinSideMargin + (this.mNotificationStackScroller.getWidth() / 2));
        float width2 = (float) ((getWidth() - this.mPositionMinSideMargin) - (this.mNotificationStackScroller.getWidth() / 2));
        if (Math.abs(f - ((float) (getWidth() / 2))) < ((float) (this.mNotificationStackScroller.getWidth() / 4))) {
            f = (float) (getWidth() / 2);
        }
        setHorizontalPanelTranslation(Math.min(width2, Math.max(width, f)) - ((float) (this.mNotificationStackScroller.getLeft() + (this.mNotificationStackScroller.getWidth() / 2))));
    }

    private void resetHorizontalPanelPosition() {
        setHorizontalPanelTranslation(0.0f);
    }

    /* access modifiers changed from: protected */
    public void setHorizontalPanelTranslation(float f) {
        this.mNotificationStackScroller.setTranslationX(f);
        this.mQsFrame.setTranslationX(f);
        int size = this.mVerticalTranslationListener.size();
        for (int i = 0; i < size; i++) {
            this.mVerticalTranslationListener.get(i).run();
        }
    }

    /* access modifiers changed from: protected */
    public void updateExpandedHeight(float f) {
        if (this.mTracking) {
            this.mNotificationStackScroller.setExpandingVelocity(getCurrentExpandVelocity());
        }
        if (this.mKeyguardBypassController.getBypassEnabled() && isOnKeyguard()) {
            f = (float) getMaxPanelHeightNonBypass();
        }
        this.mNotificationStackScroller.setExpandedHeight(f);
        updateKeyguardBottomAreaAlpha();
        updateBigClockAlpha();
        updateStatusBarIcons();
    }

    public boolean isFullWidth() {
        return this.mIsFullWidth;
    }

    private void updateStatusBarIcons() {
        boolean z = (isPanelVisibleBecauseOfHeadsUp() || isFullWidth()) && getExpandedHeight() < getOpeningHeight();
        if (z && this.mNoVisibleNotifications && isOnKeyguard()) {
            z = false;
        }
        if (z != this.mShowIconsWhenExpanded) {
            this.mShowIconsWhenExpanded = z;
            this.mCommandQueue.recomputeDisableFlags(this.mDisplayId, false);
        }
    }

    private boolean isOnKeyguard() {
        return this.mBarState == 1;
    }

    public void setPanelScrimMinFraction(float f) {
        this.mBar.panelScrimMinFractionChanged(f);
    }

    public void clearNotificationEffects() {
        this.mStatusBar.clearNotificationEffects();
    }

    /* access modifiers changed from: protected */
    public boolean isPanelVisibleBecauseOfHeadsUp() {
        return (this.mHeadsUpManager.hasPinnedHeadsUp() || this.mHeadsUpAnimatingAway) && this.mBarState == 0;
    }

    public boolean hasOverlappingRendering() {
        return !this.mDozing;
    }

    public void launchCamera(boolean z, int i) {
        boolean z2 = true;
        if (i == 1) {
            this.mLastCameraLaunchSource = "power_double_tap";
        } else if (i == 0) {
            this.mLastCameraLaunchSource = "wiggle_gesture";
        } else if (i == 2) {
            this.mLastCameraLaunchSource = "lift_to_launch_ml";
        } else {
            this.mLastCameraLaunchSource = "lockscreen_affordance";
        }
        if (!isFullyCollapsed()) {
            setLaunchingAffordance(true);
        } else {
            z = false;
        }
        this.mAffordanceHasPreview = this.mKeyguardBottomArea.getRightPreview() != null;
        KeyguardAffordanceHelper keyguardAffordanceHelper = this.mAffordanceHelper;
        if (getLayoutDirection() != 1) {
            z2 = false;
        }
        keyguardAffordanceHelper.launchAffordance(z, z2);
    }

    public void onAffordanceLaunchEnded() {
        setLaunchingAffordance(false);
    }

    private void setLaunchingAffordance(boolean z) {
        this.mLaunchingAffordance = z;
        getLeftIcon().setLaunchingAffordance(z);
        getRightIcon().setLaunchingAffordance(z);
        this.mKeyguardBypassController.setLaunchingAffordance(z);
        Consumer<Boolean> consumer = this.mAffordanceLaunchListener;
        if (consumer != null) {
            consumer.accept(Boolean.valueOf(z));
        }
    }

    public boolean isLaunchingAffordanceWithPreview() {
        return this.mLaunchingAffordance && this.mAffordanceHasPreview;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        r0 = r0.activityInfo;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean canCameraGestureBeLaunched(boolean r3) {
        /*
            r2 = this;
            com.android.systemui.statusbar.phone.StatusBar r0 = r2.mStatusBar
            boolean r0 = r0.isCameraAllowedByAdmin()
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            com.android.systemui.statusbar.phone.KeyguardBottomAreaView r0 = r2.mKeyguardBottomArea
            android.content.pm.ResolveInfo r0 = r0.resolveCameraIntent()
            if (r0 == 0) goto L_0x001a
            android.content.pm.ActivityInfo r0 = r0.activityInfo
            if (r0 != 0) goto L_0x0017
            goto L_0x001a
        L_0x0017:
            java.lang.String r0 = r0.packageName
            goto L_0x001b
        L_0x001a:
            r0 = 0
        L_0x001b:
            if (r0 == 0) goto L_0x002e
            if (r3 != 0) goto L_0x0025
            boolean r3 = r2.isForegroundApp(r0)
            if (r3 != 0) goto L_0x002e
        L_0x0025:
            com.android.systemui.statusbar.phone.KeyguardAffordanceHelper r2 = r2.mAffordanceHelper
            boolean r2 = r2.isSwipingInProgress()
            if (r2 != 0) goto L_0x002e
            r1 = 1
        L_0x002e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.NotificationPanelView.canCameraGestureBeLaunched(boolean):boolean");
    }

    private boolean isForegroundApp(String str) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) getContext().getSystemService(ActivityManager.class)).getRunningTasks(1);
        if (runningTasks.isEmpty() || !str.equals(runningTasks.get(0).topActivity.getPackageName())) {
            return false;
        }
        return true;
    }

    private void setGroupManager(NotificationGroupManager notificationGroupManager) {
        this.mGroupManager = notificationGroupManager;
    }

    public boolean hideStatusBarIconsWhenExpanded() {
        if (this.mLaunchingNotification) {
            return this.mHideIconsDuringNotificationLaunch;
        }
        HeadsUpAppearanceController headsUpAppearanceController = this.mHeadsUpAppearanceController;
        if (headsUpAppearanceController != null && headsUpAppearanceController.shouldBeVisible()) {
            return false;
        }
        if (!isFullWidth() || !this.mShowIconsWhenExpanded) {
            return true;
        }
        return false;
    }

    class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            ContentResolver contentResolver = NotificationPanelView.this.mContext.getContentResolver();
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("status_bar_shown_on_secure_keyguard"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_smart_pulldown"), false, this, -1);
            update();
        }

        /* access modifiers changed from: package-private */
        public void unobserve() {
            NotificationPanelView.this.mContext.getContentResolver().unregisterContentObserver(this);
        }

        public void onChange(boolean z) {
            update();
        }

        public void onChange(boolean z, Uri uri) {
            update();
        }

        public void update() {
            ContentResolver contentResolver = NotificationPanelView.this.mContext.getContentResolver();
            NotificationPanelView notificationPanelView = NotificationPanelView.this;
            boolean z = true;
            if (Settings.Secure.getIntForUser(contentResolver, "status_bar_shown_on_secure_keyguard", 1, -2) != 1) {
                z = false;
            }
            boolean unused = notificationPanelView.mStatusBarShownOnSecureKeyguard = z;
            int unused2 = NotificationPanelView.this.mQsSmartPullDown = Settings.System.getIntForUser(contentResolver, "qs_smart_pulldown", 0, -2);
        }
    }

    public void setTouchAndAnimationDisabled(boolean z) {
        super.setTouchAndAnimationDisabled(z);
        if (z && this.mAffordanceHelper.isSwipingInProgress() && !this.mIsLaunchTransitionRunning) {
            this.mAffordanceHelper.reset(false);
        }
        this.mNotificationStackScroller.setAnimationsEnabled(!z);
    }

    public void setDozing(boolean z, boolean z2, PointF pointF) {
        if (z != this.mDozing) {
            this.mDozing = z;
            this.mNotificationStackScroller.setDozing(this.mDozing, z2, pointF);
            this.mKeyguardBottomArea.setDozing(this.mDozing, z2);
            if (z) {
                this.mBottomAreaShadeAlphaAnimator.cancel();
            }
            int i = this.mBarState;
            if (i == 1 || i == 2) {
                updateDozingVisibilities(z2);
            }
            this.mStatusBarStateController.setDozeAmount(z ? 1.0f : 0.0f, z2);
        }
    }

    public void onDozeAmountChanged(float f, float f2) {
        this.mInterpolatedDarkAmount = f2;
        this.mLinearDarkAmount = f;
        this.mKeyguardStatusView.setDarkAmount(this.mInterpolatedDarkAmount);
        this.mKeyguardBottomArea.setDarkAmount(this.mInterpolatedDarkAmount);
        positionClockAndNotifications();
    }

    public void updateAmbientPulse() {
        int i;
        NotificationLightsView notificationLightsView = this.mPulseLightsView;
        if (notificationLightsView == null) {
            return;
        }
        if (this.mPulsing) {
            ContentResolver contentResolver = this.mContext.getContentResolver();
            boolean z = true;
            ExpandableNotificationRow firstActiveClearableNotification = this.mNotificationStackScroller.getFirstActiveClearableNotification(1);
            boolean z2 = firstActiveClearableNotification != null;
            if (Settings.System.getIntForUser(contentResolver, "ambient_notification_light", 1, -2) == 0) {
                z = false;
            }
            int intForUser = Settings.System.getIntForUser(contentResolver, "ambient_light_color", 0, -2);
            if (z && intForUser == 0) {
                int notificationLightsColor = this.mPulseLightsView.getNotificationLightsColor();
                if (intForUser != 0 || !z2 || (i = firstActiveClearableNotification.getStatusBarNotification().getNotification().color) == 0) {
                    i = notificationLightsColor;
                }
                this.mPulseLightsView.animateNotificationWithColor(i | -16777216);
                this.mPulseLightsView.setVisibility(0);
                return;
            }
            return;
        }
        notificationLightsView.endAnimation();
        this.mPulseLightsView.setVisibility(8);
    }

    public void setPulsing(boolean z) {
        this.mPulsing = z;
        DozeParameters instance = DozeParameters.getInstance(this.mContext);
        boolean z2 = !instance.getDisplayNeedsBlanking() && instance.getAlwaysOn();
        ContentResolver contentResolver = this.mContext.getContentResolver();
        boolean z3 = Settings.System.getIntForUser(contentResolver, "ambient_notification_light", 1, -2) != 0;
        ExpandableNotificationRow firstActiveClearableNotification = this.mNotificationStackScroller.getFirstActiveClearableNotification(1);
        boolean z4 = firstActiveClearableNotification != null;
        boolean z5 = Settings.System.getIntForUser(contentResolver, "pulse_trigger_reason", -1, -2) == 1;
        boolean z6 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "ambient_light_pulse_for_all", 0, -2) == 1;
        int intForUser = Settings.System.getIntForUser(contentResolver, "ambient_light_color", 0, -2);
        if (z2) {
            this.mAnimateNextPositionUpdate = true;
        }
        if (!this.mPulsing && !this.mDozing) {
            this.mAnimateNextPositionUpdate = false;
        }
        NotificationLightsView notificationLightsView = this.mPulseLightsView;
        if (notificationLightsView != null) {
            int notificationLightsColor = notificationLightsView.getNotificationLightsColor();
            if (firstActiveClearableNotification != null) {
                if (intForUser != 0 || !z4) {
                    notificationLightsColor = this.mPulseLightsView.getNotificationLightsColor();
                } else {
                    int i = firstActiveClearableNotification.getStatusBarNotification().getNotification().color;
                    if (i != 0) {
                        notificationLightsColor = i;
                    }
                }
            }
            int i2 = -16777216 | notificationLightsColor;
            if (!this.mPulsing) {
                this.mPulseLightsView.endAnimation();
                this.mPulseLightsView.setVisibility(8);
            } else if ((z4 && z5) || z6) {
                if (z3) {
                    this.mPulseLightsView.animateNotificationWithColor(i2);
                    this.mPulseLightsView.setVisibility(0);
                } else {
                    this.mPulseLightsView.endAnimation();
                    this.mPulseLightsView.setVisibility(8);
                }
            }
        }
        this.mNotificationStackScroller.setPulsing(z, z2);
        this.mKeyguardStatusView.setPulsing(z);
    }

    public void dozeTimeTick() {
        this.mKeyguardBottomArea.dozeTimeTick();
        this.mKeyguardStatusView.dozeTimeTick();
        if (this.mInterpolatedDarkAmount > 0.0f) {
            positionClockAndNotifications();
        }
    }

    public void setStatusAccessibilityImportance(int i) {
        this.mKeyguardStatusView.setImportantForAccessibility(i);
    }

    public KeyguardBottomAreaView getKeyguardBottomAreaView() {
        return this.mKeyguardBottomArea;
    }

    public void setUserSetupComplete(boolean z) {
        this.mUserSetupComplete = z;
        this.mKeyguardBottomArea.setUserSetupComplete(z);
    }

    public void applyExpandAnimationParams(ActivityLaunchAnimator.ExpandAnimationParameters expandAnimationParameters) {
        this.mExpandOffset = expandAnimationParameters != null ? (float) expandAnimationParameters.getTopChange() : 0.0f;
        updateQsExpansion();
        if (expandAnimationParameters != null) {
            boolean z = expandAnimationParameters.getProgress(14, 100) == 0.0f;
            if (z != this.mHideIconsDuringNotificationLaunch) {
                this.mHideIconsDuringNotificationLaunch = z;
                if (!z) {
                    this.mCommandQueue.recomputeDisableFlags(this.mDisplayId, true);
                }
            }
        }
    }

    public void addTrackingHeadsUpListener(Consumer<ExpandableNotificationRow> consumer) {
        this.mTrackingHeadsUpListeners.add(consumer);
    }

    public void removeTrackingHeadsUpListener(Consumer<ExpandableNotificationRow> consumer) {
        this.mTrackingHeadsUpListeners.remove(consumer);
    }

    public void addVerticalTranslationListener(Runnable runnable) {
        this.mVerticalTranslationListener.add(runnable);
    }

    public void removeVerticalTranslationListener(Runnable runnable) {
        this.mVerticalTranslationListener.remove(runnable);
    }

    public void setHeadsUpAppearanceController(HeadsUpAppearanceController headsUpAppearanceController) {
        this.mHeadsUpAppearanceController = headsUpAppearanceController;
    }

    public void onBouncerPreHideAnimation() {
        setKeyguardStatusViewVisibility(this.mBarState, true, false);
    }

    public void blockExpansionForCurrentTouch() {
        this.mBlockingExpansionForCurrentTouch = this.mTracking;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(fileDescriptor, printWriter, strArr);
        printWriter.println("    gestureExclusionRect: " + calculateGestureExclusionRect());
        KeyguardStatusBarView keyguardStatusBarView = this.mKeyguardStatusBar;
        if (keyguardStatusBarView != null) {
            keyguardStatusBarView.dump(fileDescriptor, printWriter, strArr);
        }
        KeyguardStatusView keyguardStatusView = this.mKeyguardStatusView;
        if (keyguardStatusView != null) {
            keyguardStatusView.dump(fileDescriptor, printWriter, strArr);
        }
    }

    public boolean hasActiveClearableNotifications() {
        return this.mNotificationStackScroller.hasActiveClearableNotifications(0);
    }

    public void onZenChanged(int i) {
        updateShowEmptyShadeView();
    }

    private void updateShowEmptyShadeView() {
        boolean z = true;
        if (this.mBarState == 1 || this.mEntryManager.getNotificationData().getActiveNotifications().size() != 0) {
            z = false;
        }
        showEmptyShadeView(z);
    }

    public RemoteInputController.Delegate createRemoteInputDelegate() {
        return this.mNotificationStackScroller.createDelegate();
    }

    public void updateNotificationViews() {
        this.mNotificationStackScroller.updateSectionBoundaries();
        this.mNotificationStackScroller.updateSpeedBumpIndex();
        this.mNotificationStackScroller.updateFooter();
        updateShowEmptyShadeView();
        this.mNotificationStackScroller.updateIconAreaViews();
    }

    public void onUpdateRowStates() {
        this.mNotificationStackScroller.onUpdateRowStates();
    }

    public boolean hasPulsingNotifications() {
        return this.mNotificationStackScroller.hasPulsingNotifications();
    }

    public ActivatableNotificationView getActivatedChild() {
        return this.mNotificationStackScroller.getActivatedChild();
    }

    public void setActivatedChild(ActivatableNotificationView activatableNotificationView) {
        this.mNotificationStackScroller.setActivatedChild(activatableNotificationView);
    }

    public void runAfterAnimationFinished(Runnable runnable) {
        this.mNotificationStackScroller.runAfterAnimationFinished(runnable);
    }

    public void initDependencies(StatusBar statusBar, NotificationGroupManager notificationGroupManager, NotificationShelf notificationShelf, HeadsUpManagerPhone headsUpManagerPhone, NotificationIconAreaController notificationIconAreaController, ScrimController scrimController) {
        setStatusBar(statusBar);
        setGroupManager(this.mGroupManager);
        this.mNotificationStackScroller.setNotificationPanel(this);
        this.mNotificationStackScroller.setIconAreaController(notificationIconAreaController);
        this.mNotificationStackScroller.setStatusBar(statusBar);
        this.mNotificationStackScroller.setGroupManager(notificationGroupManager);
        this.mNotificationStackScroller.setShelf(notificationShelf);
        this.mNotificationStackScroller.setScrimController(scrimController);
        updateShowEmptyShadeView();
    }

    public void showTransientIndication(int i) {
        this.mKeyguardIndicationController.showTransientIndication(i);
    }

    public void onDynamicPrivacyChanged() {
        if (this.mLinearDarkAmount == 0.0f) {
            this.mAnimateNextPositionUpdate = true;
        }
    }

    public void setOnReinflationListener(Runnable runnable) {
        this.mOnReinflationListener = runnable;
    }

    public void updateDoubleTapToSleep(boolean z) {
        this.mDoubleTapToSleepEnabled = z;
    }

    public void setQsQuickPulldown(int i) {
        this.mOneFingerQuickSettingsIntercept = i;
    }
}
