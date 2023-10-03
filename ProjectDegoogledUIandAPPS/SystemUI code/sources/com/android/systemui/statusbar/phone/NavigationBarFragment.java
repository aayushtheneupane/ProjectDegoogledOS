package com.android.systemui.statusbar.phone;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.StatusBarManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.recents.Recents;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.ContextualButton;
import com.android.systemui.statusbar.phone.NavigationBarView;
import com.android.systemui.statusbar.phone.NavigationModeController;
import com.android.systemui.statusbar.policy.AccessibilityManagerWrapper;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.util.LifecycleFragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.function.Consumer;

public class NavigationBarFragment extends LifecycleFragment implements CommandQueue.Callbacks, NavigationModeController.ModeChangedListener, AutoHideElement {
    private final AccessibilityManager.AccessibilityServicesStateChangeListener mAccessibilityListener;
    /* access modifiers changed from: private */
    public AccessibilityManager mAccessibilityManager;
    private final AccessibilityManagerWrapper mAccessibilityManagerWrapper;
    private final ContentObserver mAssistContentObserver;
    protected final AssistManager mAssistManager;
    /* access modifiers changed from: private */
    public boolean mAssistantAvailable;
    private final Runnable mAutoDim;
    private AutoHideController mAutoHideController;
    private final BroadcastReceiver mBroadcastReceiver;
    private CommandQueue mCommandQueue;
    private ContentResolver mContentResolver;
    private final DeviceProvisionedController mDeviceProvisionedController;
    private int mDisabledFlags1;
    private int mDisabledFlags2;
    public int mDisplayId;
    private Divider mDivider;
    private Handler mHandler;
    private boolean mIsOnDefaultDisplay;
    private long mLastLockToAppLongPress;
    private int mLayoutDirection;
    private LightBarController mLightBarController;
    private Locale mLocale;
    private MagnificationContentObserver mMagnificationObserver;
    private final MetricsLogger mMetricsLogger;
    /* access modifiers changed from: private */
    public int mNavBarMode;
    private int mNavigationBarMode;
    protected NavigationBarView mNavigationBarView = null;
    private int mNavigationBarWindowState;
    private int mNavigationIconHints;
    private final NavigationModeController mNavigationModeController;
    private final OverviewProxyService.OverviewProxyListener mOverviewProxyListener;
    private OverviewProxyService mOverviewProxyService;
    private Recents mRecents;
    private final ContextualButton.ContextButtonListener mRotationButtonListener;
    private final Consumer<Integer> mRotationWatcher;
    private ScreenDecorations mScreenDecorations;
    private StatusBar mStatusBar;
    private final StatusBarStateController mStatusBarStateController;
    private int mSystemUiVisibility;
    private WindowManager mWindowManager;

    private int barMode(int i) {
        if ((134217728 & i) != 0) {
            return 1;
        }
        if ((Integer.MIN_VALUE & i) != 0) {
            return 2;
        }
        if ((i & 134217729) == 134217729) {
            return 6;
        }
        if ((32768 & i) != 0) {
            return 4;
        }
        return (i & 1) != 0 ? 3 : 0;
    }

    public /* synthetic */ void lambda$new$0$NavigationBarFragment(ContextualButton contextualButton, boolean z) {
        if (z) {
            this.mAutoHideController.touchAutoHide();
        }
    }

    public /* synthetic */ void lambda$new$1$NavigationBarFragment() {
        getBarTransitions().setAutoDim(true);
    }

    public NavigationBarFragment(AccessibilityManagerWrapper accessibilityManagerWrapper, DeviceProvisionedController deviceProvisionedController, MetricsLogger metricsLogger, AssistManager assistManager, OverviewProxyService overviewProxyService, NavigationModeController navigationModeController, StatusBarStateController statusBarStateController) {
        boolean z = false;
        this.mNavigationBarWindowState = 0;
        this.mNavigationIconHints = 0;
        this.mSystemUiVisibility = 0;
        this.mNavBarMode = 0;
        this.mHandler = (Handler) Dependency.get(Dependency.MAIN_HANDLER);
        this.mOverviewProxyListener = new OverviewProxyService.OverviewProxyListener() {
            public void onConnectionChanged(boolean z) {
                NavigationBarFragment.this.mNavigationBarView.updateStates();
                NavigationBarFragment.this.updateScreenPinningGestures();
                if (z) {
                    NavigationBarFragment navigationBarFragment = NavigationBarFragment.this;
                    navigationBarFragment.sendAssistantAvailability(navigationBarFragment.mAssistantAvailable);
                }
            }

            public void startAssistant(Bundle bundle) {
                NavigationBarFragment.this.mAssistManager.startAssist(bundle);
            }

            public void onNavBarButtonAlphaChanged(float f, boolean z) {
                ButtonDispatcher buttonDispatcher = null;
                if (QuickStepContract.isSwipeUpMode(NavigationBarFragment.this.mNavBarMode)) {
                    buttonDispatcher = NavigationBarFragment.this.mNavigationBarView.getBackButton();
                } else if (QuickStepContract.isGesturalMode(NavigationBarFragment.this.mNavBarMode) && NavigationBarFragment.this.mNavigationBarView.showGestureNavbar()) {
                    buttonDispatcher = NavigationBarFragment.this.mNavigationBarView.getHomeHandle();
                }
                if (buttonDispatcher != null) {
                    buttonDispatcher.setVisibility(f > 0.0f ? 0 : 4);
                    buttonDispatcher.setAlpha(f, z);
                }
            }
        };
        this.mRotationButtonListener = new ContextualButton.ContextButtonListener() {
            public final void onVisibilityChanged(ContextualButton contextualButton, boolean z) {
                NavigationBarFragment.this.lambda$new$0$NavigationBarFragment(contextualButton, z);
            }
        };
        this.mAutoDim = new Runnable() {
            public final void run() {
                NavigationBarFragment.this.lambda$new$1$NavigationBarFragment();
            }
        };
        this.mAssistContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
            public void onChange(boolean z, Uri uri) {
                boolean z2 = NavigationBarFragment.this.mAssistManager.getAssistInfoForUser(-2) != null;
                if (NavigationBarFragment.this.mAssistantAvailable != z2) {
                    NavigationBarFragment.this.sendAssistantAvailability(z2);
                    boolean unused = NavigationBarFragment.this.mAssistantAvailable = z2;
                }
            }
        };
        this.mAccessibilityListener = new AccessibilityManager.AccessibilityServicesStateChangeListener() {
            public final void onAccessibilityServicesStateChanged(AccessibilityManager accessibilityManager) {
                NavigationBarFragment.this.updateAccessibilityServicesState(accessibilityManager);
            }
        };
        this.mRotationWatcher = new Consumer() {
            public final void accept(Object obj) {
                NavigationBarFragment.this.lambda$new$4$NavigationBarFragment((Integer) obj);
            }
        };
        this.mBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_OFF".equals(action) || "android.intent.action.SCREEN_ON".equals(action)) {
                    NavigationBarFragment.this.notifyNavigationBarScreenOn();
                    if (!"android.intent.action.SCREEN_ON".equals(action)) {
                        NavigationBarFragment.this.mNavigationBarView.getTintController().stop();
                    } else if (NavBarTintController.isEnabled(NavigationBarFragment.this.getContext(), NavigationBarFragment.this.mNavBarMode)) {
                        NavigationBarFragment.this.mNavigationBarView.getTintController().start();
                    }
                }
                if ("android.intent.action.USER_SWITCHED".equals(action)) {
                    NavigationBarFragment navigationBarFragment = NavigationBarFragment.this;
                    navigationBarFragment.updateAccessibilityServicesState(navigationBarFragment.mAccessibilityManager);
                }
            }
        };
        this.mAccessibilityManagerWrapper = accessibilityManagerWrapper;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mStatusBarStateController = statusBarStateController;
        this.mMetricsLogger = metricsLogger;
        this.mAssistManager = assistManager;
        this.mAssistantAvailable = this.mAssistManager.getAssistInfoForUser(-2) != null ? true : z;
        this.mOverviewProxyService = overviewProxyService;
        this.mNavigationModeController = navigationModeController;
        this.mNavBarMode = navigationModeController.addListener(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCommandQueue = (CommandQueue) SysUiServiceProvider.getComponent(getContext(), CommandQueue.class);
        this.mCommandQueue.observe(getLifecycle(), this);
        this.mStatusBar = (StatusBar) SysUiServiceProvider.getComponent(getContext(), StatusBar.class);
        this.mRecents = (Recents) SysUiServiceProvider.getComponent(getContext(), Recents.class);
        this.mDivider = (Divider) SysUiServiceProvider.getComponent(getContext(), Divider.class);
        this.mWindowManager = (WindowManager) getContext().getSystemService(WindowManager.class);
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService(AccessibilityManager.class);
        this.mContentResolver = getContext().getContentResolver();
        this.mMagnificationObserver = new MagnificationContentObserver(getContext().getMainThreadHandler());
        this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("accessibility_display_magnification_navbar_enabled"), false, this.mMagnificationObserver, -1);
        this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("assistant"), false, this.mAssistContentObserver, -1);
        if (bundle != null) {
            this.mDisabledFlags1 = bundle.getInt("disabled_state", 0);
            this.mDisabledFlags2 = bundle.getInt("disabled2_state", 0);
            this.mSystemUiVisibility = bundle.getInt("system_ui_visibility", 0);
        }
        this.mAccessibilityManagerWrapper.addCallback(this.mAccessibilityListener);
        this.mCommandQueue.recomputeDisableFlags(this.mDisplayId, false);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mNavigationModeController.removeListener(this);
        this.mAccessibilityManagerWrapper.removeCallback(this.mAccessibilityListener);
        this.mContentResolver.unregisterContentObserver(this.mMagnificationObserver);
        this.mContentResolver.unregisterContentObserver(this.mAssistContentObserver);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C1779R$layout.navigation_bar, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mNavigationBarView = (NavigationBarView) view;
        Display display = view.getDisplay();
        if (display != null) {
            this.mDisplayId = display.getDisplayId();
            this.mIsOnDefaultDisplay = this.mDisplayId == 0;
        }
        this.mNavigationBarView.setComponents(this.mStatusBar.getPanel(), this.mAssistManager);
        this.mNavigationBarView.setDisabledFlags(this.mDisabledFlags1);
        this.mNavigationBarView.setOnVerticalChangedListener(new NavigationBarView.OnVerticalChangedListener() {
            public final void onVerticalChanged(boolean z) {
                NavigationBarFragment.this.onVerticalChanged(z);
            }
        });
        this.mNavigationBarView.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return NavigationBarFragment.this.onNavigationTouch(view, motionEvent);
            }
        });
        if (bundle != null) {
            this.mNavigationBarView.getLightTransitionsController().restoreState(bundle);
        }
        this.mNavigationBarView.setNavigationIconHints(this.mNavigationIconHints);
        this.mNavigationBarView.setWindowVisible(isNavBarWindowVisible());
        prepareNavigationBarView();
        checkNavBarModes();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.USER_SWITCHED");
        getContext().registerReceiverAsUser(this.mBroadcastReceiver, UserHandle.ALL, intentFilter, (String) null, (Handler) null);
        notifyNavigationBarScreenOn();
        this.mOverviewProxyService.addCallback(this.mOverviewProxyListener);
        updateSystemUiStateFlags(-1);
        if (this.mIsOnDefaultDisplay) {
            this.mNavigationBarView.getRotateSuggestionButton().setListener(this.mRotationButtonListener);
            RotationButtonController rotationButtonController = this.mNavigationBarView.getRotationButtonController();
            rotationButtonController.addRotationCallback(this.mRotationWatcher);
            if (display != null && rotationButtonController.isRotationLocked()) {
                rotationButtonController.setRotationLockedAtAngle(display.getRotation());
            }
        } else {
            this.mDisabledFlags2 |= 16;
        }
        setDisabled2Flags(this.mDisabledFlags2);
        this.mScreenDecorations = (ScreenDecorations) SysUiServiceProvider.getComponent(getContext(), ScreenDecorations.class);
        getBarTransitions().addDarkIntensityListener(this.mScreenDecorations);
    }

    public void onDestroyView() {
        super.onDestroyView();
        NavigationBarView navigationBarView = this.mNavigationBarView;
        if (navigationBarView != null) {
            navigationBarView.getBarTransitions().removeDarkIntensityListener(this.mScreenDecorations);
            this.mNavigationBarView.getBarTransitions().destroy();
            this.mNavigationBarView.getLightTransitionsController().destroy(getContext());
        }
        this.mOverviewProxyService.removeCallback(this.mOverviewProxyListener);
        getContext().unregisterReceiver(this.mBroadcastReceiver);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("disabled_state", this.mDisabledFlags1);
        bundle.putInt("disabled2_state", this.mDisabledFlags2);
        bundle.putInt("system_ui_visibility", this.mSystemUiVisibility);
        NavigationBarView navigationBarView = this.mNavigationBarView;
        if (navigationBarView != null) {
            navigationBarView.getLightTransitionsController().saveState(bundle);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Locale locale = getContext().getResources().getConfiguration().locale;
        int layoutDirectionFromLocale = TextUtils.getLayoutDirectionFromLocale(locale);
        if (!locale.equals(this.mLocale) || layoutDirectionFromLocale != this.mLayoutDirection) {
            this.mLocale = locale;
            this.mLayoutDirection = layoutDirectionFromLocale;
            refreshLayout(layoutDirectionFromLocale);
        }
        repositionNavigationBar();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (this.mNavigationBarView != null) {
            printWriter.print("  mNavigationBarWindowState=");
            printWriter.println(StatusBarManager.windowStateToString(this.mNavigationBarWindowState));
            printWriter.print("  mNavigationBarMode=");
            printWriter.println(BarTransitions.modeToString(this.mNavigationBarMode));
            StatusBar.dumpBarTransitions(printWriter, "mNavigationBarView", this.mNavigationBarView.getBarTransitions());
        }
        printWriter.print("  mNavigationBarView=");
        NavigationBarView navigationBarView = this.mNavigationBarView;
        if (navigationBarView == null) {
            printWriter.println("null");
        } else {
            navigationBarView.dump(fileDescriptor, printWriter, strArr);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        if (r5 != 3) goto L_0x0021;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0023  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setImeWindowStatus(int r2, android.os.IBinder r3, int r4, int r5, boolean r6) {
        /*
            r1 = this;
            int r3 = r1.mDisplayId
            if (r2 == r3) goto L_0x0005
            return
        L_0x0005:
            r2 = 2
            r3 = r4 & 2
            r4 = 1
            if (r3 == 0) goto L_0x000d
            r3 = r4
            goto L_0x000e
        L_0x000d:
            r3 = 0
        L_0x000e:
            int r0 = r1.mNavigationIconHints
            if (r5 == 0) goto L_0x001d
            if (r5 == r4) goto L_0x001d
            if (r5 == r2) goto L_0x001d
            r3 = 3
            if (r5 == r3) goto L_0x001a
            goto L_0x0021
        L_0x001a:
            r0 = r0 & -2
            goto L_0x0021
        L_0x001d:
            if (r3 == 0) goto L_0x001a
            r0 = r0 | 1
        L_0x0021:
            if (r6 == 0) goto L_0x0025
            r2 = r2 | r0
            goto L_0x0027
        L_0x0025:
            r2 = r0 & -3
        L_0x0027:
            int r3 = r1.mNavigationIconHints
            if (r2 != r3) goto L_0x002c
            return
        L_0x002c:
            r1.mNavigationIconHints = r2
            com.android.systemui.statusbar.phone.NavigationBarView r3 = r1.mNavigationBarView
            if (r3 == 0) goto L_0x0035
            r3.setNavigationIconHints(r2)
        L_0x0035:
            r1.checkBarModes()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.NavigationBarFragment.setImeWindowStatus(int, android.os.IBinder, int, int, boolean):void");
    }

    public void setWindowState(int i, int i2, int i3) {
        if (i == this.mDisplayId && this.mNavigationBarView != null && i2 == 2 && this.mNavigationBarWindowState != i3) {
            this.mNavigationBarWindowState = i3;
            updateSystemUiStateFlags(-1);
            this.mNavigationBarView.setWindowVisible(isNavBarWindowVisible());
        }
    }

    public void onRotationProposal(int i, boolean z) {
        int rotation = this.mNavigationBarView.getDisplay().getRotation();
        boolean hasDisable2RotateSuggestionFlag = RotationButtonController.hasDisable2RotateSuggestionFlag(this.mDisabledFlags2);
        RotationButtonController rotationButtonController = this.mNavigationBarView.getRotationButtonController();
        rotationButtonController.getRotationButton();
        if (!hasDisable2RotateSuggestionFlag) {
            rotationButtonController.onRotationProposal(i, rotation, z);
        }
    }

    public void restoreSystemUiVisibilityState() {
        int computeBarMode = computeBarMode(0, this.mSystemUiVisibility);
        if (computeBarMode != -1) {
            this.mNavigationBarMode = computeBarMode;
        }
        checkNavBarModes();
        this.mAutoHideController.touchAutoHide();
        this.mLightBarController.onNavigationVisibilityChanged(this.mSystemUiVisibility, 0, true, this.mNavigationBarMode, false);
    }

    public void setSystemUiVisibility(int i, int i2, int i3, int i4, int i5, Rect rect, Rect rect2, boolean z) {
        int i6;
        if (i == this.mDisplayId) {
            int i7 = this.mSystemUiVisibility;
            int i8 = ((~i5) & i7) | (i2 & i5);
            boolean z2 = false;
            if ((i8 ^ i7) != 0) {
                this.mSystemUiVisibility = i8;
                if (getView() == null) {
                    i6 = -1;
                } else {
                    i6 = computeBarMode(i7, i8);
                }
                if (i6 != -1) {
                    z2 = true;
                }
                if (z2) {
                    int i9 = this.mNavigationBarMode;
                    if (i9 != i6) {
                        if (i9 == 4 || i9 == 6) {
                            this.mNavigationBarView.hideRecentsOnboarding();
                        }
                        this.mNavigationBarMode = i6;
                        checkNavBarModes();
                    }
                    this.mAutoHideController.touchAutoHide();
                }
                NavigationBarView navigationBarView = this.mNavigationBarView;
                if (navigationBarView != null) {
                    navigationBarView.onSystemUiVisibilityChanged(this.mSystemUiVisibility);
                }
            }
            this.mLightBarController.onNavigationVisibilityChanged(i2, i5, z2, this.mNavigationBarMode, z);
        }
    }

    private int computeBarMode(int i, int i2) {
        int barMode = barMode(i);
        int barMode2 = barMode(i2);
        if (barMode == barMode2) {
            return -1;
        }
        return barMode2;
    }

    public void disable(int i, int i2, int i3, boolean z) {
        int i4;
        if (i == this.mDisplayId) {
            int i5 = 56623104 & i2;
            if (i5 != this.mDisabledFlags1) {
                this.mDisabledFlags1 = i5;
                NavigationBarView navigationBarView = this.mNavigationBarView;
                if (navigationBarView != null) {
                    navigationBarView.setDisabledFlags(i2);
                }
                updateScreenPinningGestures();
            }
            if (this.mIsOnDefaultDisplay && (i4 = i3 & 16) != this.mDisabledFlags2) {
                this.mDisabledFlags2 = i4;
                setDisabled2Flags(i4);
            }
        }
    }

    private void setDisabled2Flags(int i) {
        NavigationBarView navigationBarView = this.mNavigationBarView;
        if (navigationBarView != null) {
            navigationBarView.getRotationButtonController().onDisable2FlagChanged(i);
        }
    }

    private void refreshLayout(int i) {
        NavigationBarView navigationBarView = this.mNavigationBarView;
        if (navigationBarView != null) {
            navigationBarView.setLayoutDirection(i);
        }
    }

    private boolean shouldDisableNavbarGestures() {
        return !this.mDeviceProvisionedController.isDeviceProvisioned() || (this.mDisabledFlags1 & 33554432) != 0;
    }

    private void repositionNavigationBar() {
        NavigationBarView navigationBarView = this.mNavigationBarView;
        if (navigationBarView != null && navigationBarView.isAttachedToWindow()) {
            prepareNavigationBarView();
            this.mWindowManager.updateViewLayout((View) this.mNavigationBarView.getParent(), ((View) this.mNavigationBarView.getParent()).getLayoutParams());
        }
    }

    /* access modifiers changed from: private */
    public void updateScreenPinningGestures() {
        NavigationBarView navigationBarView = this.mNavigationBarView;
        if (navigationBarView != null) {
            boolean isRecentsButtonVisible = navigationBarView.isRecentsButtonVisible();
            ButtonDispatcher backButton = this.mNavigationBarView.getBackButton();
            if (isRecentsButtonVisible) {
                backButton.setOnLongClickListener(new View.OnLongClickListener() {
                    public final boolean onLongClick(View view) {
                        return NavigationBarFragment.this.onLongPressBackRecents(view);
                    }
                });
            } else {
                backButton.setOnLongClickListener(new View.OnLongClickListener() {
                    public final boolean onLongClick(View view) {
                        return NavigationBarFragment.this.onLongPressBackHome(view);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void notifyNavigationBarScreenOn() {
        this.mNavigationBarView.updateNavButtonIcons();
    }

    private void prepareNavigationBarView() {
        this.mNavigationBarView.reorient();
        this.mNavigationBarView.getRecentsButton().setLongClickable(false);
        this.mNavigationBarView.getBackButton().setLongClickable(false);
        this.mNavigationBarView.getHomeButton().setLongClickable(false);
        ButtonDispatcher accessibilityButton = this.mNavigationBarView.getAccessibilityButton();
        accessibilityButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                NavigationBarFragment.this.onAccessibilityClick(view);
            }
        });
        accessibilityButton.setOnLongClickListener(new View.OnLongClickListener() {
            public final boolean onLongClick(View view) {
                return NavigationBarFragment.this.onAccessibilityLongClick(view);
            }
        });
        updateAccessibilityServicesState(this.mAccessibilityManager);
        updateScreenPinningGestures();
    }

    /* access modifiers changed from: private */
    public void onVerticalChanged(boolean z) {
        this.mStatusBar.setQsScrimEnabled(!z);
    }

    /* access modifiers changed from: private */
    public boolean onNavigationTouch(View view, MotionEvent motionEvent) {
        this.mAutoHideController.checkUserAutoHide(motionEvent);
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean onHomeLongClick(View view) {
        if (!this.mNavigationBarView.isRecentsButtonVisible() && ActivityManagerWrapper.getInstance().isScreenPinningActive()) {
            return onLongPressBackHome(view);
        }
        if (shouldDisableNavbarGestures()) {
            return false;
        }
        this.mMetricsLogger.action(239);
        Bundle bundle = new Bundle();
        bundle.putInt("invocation_type", 5);
        this.mAssistManager.startAssist(bundle);
        this.mStatusBar.awakenDreams();
        NavigationBarView navigationBarView = this.mNavigationBarView;
        if (navigationBarView == null) {
            return true;
        }
        navigationBarView.abortCurrentGesture();
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onLongPressBackHome(View view) {
        return onLongPressNavigationButtons(view, C1777R$id.back, C1777R$id.home);
    }

    /* access modifiers changed from: private */
    public boolean onLongPressBackRecents(View view) {
        return onLongPressNavigationButtons(view, C1777R$id.back, C1777R$id.recent_apps);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0096, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0097, code lost:
        android.util.Log.d("NavigationBar", "Unable to reach activity manager", r9);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean onLongPressNavigationButtons(android.view.View r10, int r11, int r12) {
        /*
            r9 = this;
            r0 = 0
            android.app.IActivityTaskManager r1 = android.app.ActivityTaskManager.getService()     // Catch:{ RemoteException -> 0x0096 }
            android.view.accessibility.AccessibilityManager r2 = r9.mAccessibilityManager     // Catch:{ RemoteException -> 0x0096 }
            boolean r2 = r2.isTouchExplorationEnabled()     // Catch:{ RemoteException -> 0x0096 }
            boolean r3 = r1.isInLockTaskMode()     // Catch:{ RemoteException -> 0x0096 }
            r4 = 1
            if (r3 == 0) goto L_0x0052
            if (r2 != 0) goto L_0x0052
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0094 }
            long r5 = r9.mLastLockToAppLongPress     // Catch:{ all -> 0x0094 }
            long r5 = r2 - r5
            r7 = 200(0xc8, double:9.9E-322)
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 >= 0) goto L_0x002b
            r1.stopSystemLockTaskMode()     // Catch:{ RemoteException -> 0x0096 }
            com.android.systemui.statusbar.phone.NavigationBarView r9 = r9.mNavigationBarView     // Catch:{ RemoteException -> 0x0096 }
            r9.updateNavButtonIcons()     // Catch:{ RemoteException -> 0x0096 }
            return r4
        L_0x002b:
            int r1 = r10.getId()     // Catch:{ all -> 0x0094 }
            if (r1 != r11) goto L_0x004e
            int r11 = com.android.systemui.C1777R$id.recent_apps     // Catch:{ all -> 0x0094 }
            if (r12 != r11) goto L_0x003c
            com.android.systemui.statusbar.phone.NavigationBarView r11 = r9.mNavigationBarView     // Catch:{ all -> 0x0094 }
            com.android.systemui.statusbar.phone.ButtonDispatcher r11 = r11.getRecentsButton()     // Catch:{ all -> 0x0094 }
            goto L_0x0042
        L_0x003c:
            com.android.systemui.statusbar.phone.NavigationBarView r11 = r9.mNavigationBarView     // Catch:{ all -> 0x0094 }
            com.android.systemui.statusbar.phone.ButtonDispatcher r11 = r11.getHomeButton()     // Catch:{ all -> 0x0094 }
        L_0x0042:
            android.view.View r11 = r11.getCurrentView()     // Catch:{ all -> 0x0094 }
            boolean r11 = r11.isPressed()     // Catch:{ all -> 0x0094 }
            if (r11 != 0) goto L_0x004e
            r11 = r4
            goto L_0x004f
        L_0x004e:
            r11 = r0
        L_0x004f:
            r9.mLastLockToAppLongPress = r2     // Catch:{ all -> 0x0094 }
            goto L_0x0086
        L_0x0052:
            int r5 = r10.getId()     // Catch:{ all -> 0x0094 }
            if (r5 != r11) goto L_0x005a
            r11 = r4
            goto L_0x0086
        L_0x005a:
            if (r2 == 0) goto L_0x0067
            if (r3 == 0) goto L_0x0067
            r1.stopSystemLockTaskMode()     // Catch:{ RemoteException -> 0x0096 }
            com.android.systemui.statusbar.phone.NavigationBarView r9 = r9.mNavigationBarView     // Catch:{ RemoteException -> 0x0096 }
            r9.updateNavButtonIcons()     // Catch:{ RemoteException -> 0x0096 }
            return r4
        L_0x0067:
            int r11 = r10.getId()     // Catch:{ all -> 0x0094 }
            if (r11 != r12) goto L_0x0085
            int r10 = com.android.systemui.C1777R$id.recent_apps     // Catch:{ all -> 0x0094 }
            if (r12 != r10) goto L_0x0076
            boolean r9 = r9.onLongPressRecents()     // Catch:{ all -> 0x0094 }
            goto L_0x0084
        L_0x0076:
            com.android.systemui.statusbar.phone.NavigationBarView r10 = r9.mNavigationBarView     // Catch:{ all -> 0x0094 }
            com.android.systemui.statusbar.phone.ButtonDispatcher r10 = r10.getHomeButton()     // Catch:{ all -> 0x0094 }
            android.view.View r10 = r10.getCurrentView()     // Catch:{ all -> 0x0094 }
            boolean r9 = r9.onHomeLongClick(r10)     // Catch:{ all -> 0x0094 }
        L_0x0084:
            return r9
        L_0x0085:
            r11 = r0
        L_0x0086:
            if (r11 == 0) goto L_0x009e
            com.android.systemui.statusbar.policy.KeyButtonView r10 = (com.android.systemui.statusbar.policy.KeyButtonView) r10     // Catch:{ RemoteException -> 0x0096 }
            r9 = 128(0x80, float:1.794E-43)
            r10.sendEvent(r0, r9)     // Catch:{ RemoteException -> 0x0096 }
            r9 = 2
            r10.sendAccessibilityEvent(r9)     // Catch:{ RemoteException -> 0x0096 }
            return r4
        L_0x0094:
            r9 = move-exception
            throw r9     // Catch:{ RemoteException -> 0x0096 }
        L_0x0096:
            r9 = move-exception
            java.lang.String r10 = "NavigationBar"
            java.lang.String r11 = "Unable to reach activity manager"
            android.util.Log.d(r10, r11, r9)
        L_0x009e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.NavigationBarFragment.onLongPressNavigationButtons(android.view.View, int, int):boolean");
    }

    private boolean onLongPressRecents() {
        if (this.mRecents == null || !ActivityTaskManager.supportsMultiWindow(getContext()) || !this.mDivider.getView().getSnapAlgorithm().isSplitScreenFeasible() || ActivityManager.isLowRamDeviceStatic() || this.mOverviewProxyService.getProxy() != null) {
            return false;
        }
        return this.mStatusBar.toggleSplitScreenMode(271, 286);
    }

    /* access modifiers changed from: private */
    public void onAccessibilityClick(View view) {
        Display display = view.getDisplay();
        this.mAccessibilityManager.notifyAccessibilityButtonClicked(display != null ? display.getDisplayId() : 0);
    }

    /* access modifiers changed from: private */
    public boolean onAccessibilityLongClick(View view) {
        Intent intent = new Intent("com.android.internal.intent.action.CHOOSE_ACCESSIBILITY_BUTTON");
        intent.addFlags(268468224);
        view.getContext().startActivityAsUser(intent, UserHandle.CURRENT);
        return true;
    }

    /* access modifiers changed from: private */
    public void updateAccessibilityServicesState(AccessibilityManager accessibilityManager) {
        boolean z = true;
        int a11yButtonState = getA11yButtonState(new boolean[1]);
        boolean z2 = (a11yButtonState & 16) != 0;
        if ((a11yButtonState & 32) == 0) {
            z = false;
        }
        this.mNavigationBarView.setAccessibilityButtonState(z2, z);
        updateSystemUiStateFlags(a11yButtonState);
    }

    public void updateSystemUiStateFlags(int i) {
        if (i < 0) {
            i = getA11yButtonState((boolean[]) null);
        }
        boolean z = false;
        boolean z2 = (i & 16) != 0;
        if ((i & 32) != 0) {
            z = true;
        }
        this.mOverviewProxyService.setSystemUiStateFlag(16, z2, this.mDisplayId);
        this.mOverviewProxyService.setSystemUiStateFlag(32, z, this.mDisplayId);
        this.mOverviewProxyService.setSystemUiStateFlag(2, !isNavBarWindowVisible(), this.mDisplayId);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x003a A[EDGE_INSN: B:27:0x003a->B:18:0x003a ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getA11yButtonState(boolean[] r9) {
        /*
            r8 = this;
            r0 = 0
            r1 = 1
            android.content.ContentResolver r2 = r8.mContentResolver     // Catch:{ SettingNotFoundException -> 0x000f }
            java.lang.String r3 = "accessibility_display_magnification_navbar_enabled"
            r4 = -2
            int r2 = android.provider.Settings.Secure.getIntForUser(r2, r3, r4)     // Catch:{ SettingNotFoundException -> 0x000f }
            if (r2 != r1) goto L_0x000f
            r2 = r1
            goto L_0x0010
        L_0x000f:
            r2 = r0
        L_0x0010:
            android.view.accessibility.AccessibilityManager r8 = r8.mAccessibilityManager
            r3 = -1
            java.util.List r8 = r8.getEnabledAccessibilityServiceList(r3)
            int r3 = r8.size()
            int r3 = r3 - r1
            r4 = r2
            r2 = r0
        L_0x001e:
            r5 = 16
            if (r3 < 0) goto L_0x003a
            java.lang.Object r6 = r8.get(r3)
            android.accessibilityservice.AccessibilityServiceInfo r6 = (android.accessibilityservice.AccessibilityServiceInfo) r6
            int r7 = r6.flags
            r7 = r7 & 256(0x100, float:3.59E-43)
            if (r7 == 0) goto L_0x0030
            int r4 = r4 + 1
        L_0x0030:
            int r6 = r6.feedbackType
            if (r6 == 0) goto L_0x0037
            if (r6 == r5) goto L_0x0037
            r2 = r1
        L_0x0037:
            int r3 = r3 + -1
            goto L_0x001e
        L_0x003a:
            if (r9 == 0) goto L_0x003e
            r9[r0] = r2
        L_0x003e:
            if (r4 < r1) goto L_0x0041
            goto L_0x0042
        L_0x0041:
            r5 = r0
        L_0x0042:
            r8 = 2
            if (r4 < r8) goto L_0x0047
            r0 = 32
        L_0x0047:
            r8 = r5 | r0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.NavigationBarFragment.getA11yButtonState(boolean[]):int");
    }

    /* access modifiers changed from: private */
    public void sendAssistantAvailability(boolean z) {
        if (this.mOverviewProxyService.getProxy() != null) {
            try {
                this.mOverviewProxyService.getProxy().onAssistantAvailable(z && QuickStepContract.isGesturalMode(this.mNavBarMode));
            } catch (RemoteException unused) {
                Log.w("NavigationBar", "Unable to send assistant availability data to launcher");
            }
        }
    }

    public void touchAutoDim() {
        getBarTransitions().setAutoDim(false);
        this.mHandler.removeCallbacks(this.mAutoDim);
        int state = this.mStatusBarStateController.getState();
        if (state != 1 && state != 2) {
            this.mHandler.postDelayed(this.mAutoDim, 2250);
        }
    }

    public void setLightBarController(LightBarController lightBarController) {
        this.mLightBarController = lightBarController;
        this.mLightBarController.setNavigationBar(this.mNavigationBarView.getLightTransitionsController());
    }

    public void setAutoHideController(AutoHideController autoHideController) {
        this.mAutoHideController = autoHideController;
        this.mAutoHideController.setNavigationBar(this);
    }

    public boolean isSemiTransparent() {
        return this.mNavigationBarMode == 1;
    }

    public void synchronizeState() {
        checkNavBarModes();
    }

    private void checkBarModes() {
        if (this.mIsOnDefaultDisplay) {
            this.mStatusBar.checkBarModes();
        } else {
            checkNavBarModes();
        }
    }

    public boolean isNavBarWindowVisible() {
        return this.mNavigationBarWindowState == 0;
    }

    public void checkNavBarModes() {
        this.mNavigationBarView.getBarTransitions().transitionTo(this.mNavigationBarMode, this.mStatusBar.isDeviceInteractive() && this.mNavigationBarWindowState != 2);
    }

    public void onNavigationModeChanged(int i) {
        this.mNavBarMode = i;
        updateScreenPinningGestures();
        if (ActivityManagerWrapper.getInstance().getCurrentUserId() != 0) {
            this.mHandler.post(new Runnable() {
                public final void run() {
                    NavigationBarFragment.this.lambda$onNavigationModeChanged$2$NavigationBarFragment();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onNavigationModeChanged$2$NavigationBarFragment() {
        FragmentHostManager.get(this.mNavigationBarView).reloadFragments();
    }

    public void disableAnimationsDuringHide(long j) {
        this.mNavigationBarView.setLayoutTransitionsEnabled(false);
        this.mNavigationBarView.postDelayed(new Runnable() {
            public final void run() {
                NavigationBarFragment.this.lambda$disableAnimationsDuringHide$3$NavigationBarFragment();
            }
        }, j + 448);
    }

    public /* synthetic */ void lambda$disableAnimationsDuringHide$3$NavigationBarFragment() {
        this.mNavigationBarView.setLayoutTransitionsEnabled(true);
    }

    public void transitionTo(int i, boolean z) {
        getBarTransitions().transitionTo(i, z);
    }

    public NavigationBarTransitions getBarTransitions() {
        return this.mNavigationBarView.getBarTransitions();
    }

    public void finishBarAnimations() {
        this.mNavigationBarView.getBarTransitions().finishAnimations();
    }

    private class MagnificationContentObserver extends ContentObserver {
        public MagnificationContentObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z) {
            NavigationBarFragment navigationBarFragment = NavigationBarFragment.this;
            navigationBarFragment.updateAccessibilityServicesState(navigationBarFragment.mAccessibilityManager);
        }
    }

    public /* synthetic */ void lambda$new$4$NavigationBarFragment(Integer num) {
        NavigationBarView navigationBarView = this.mNavigationBarView;
        if (navigationBarView != null && navigationBarView.needsReorient(num.intValue())) {
            repositionNavigationBar();
        }
    }

    public static View create(Context context, final FragmentHostManager.FragmentListener fragmentListener) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2019, 545521768, -3);
        layoutParams.token = new Binder();
        layoutParams.setTitle("NavigationBar" + context.getDisplayId());
        layoutParams.accessibilityTitle = context.getString(C1784R$string.nav_bar);
        layoutParams.windowAnimations = 0;
        layoutParams.privateFlags = layoutParams.privateFlags | 16777216;
        View inflate = LayoutInflater.from(context).inflate(C1779R$layout.navigation_bar_window, (ViewGroup) null);
        if (inflate == null) {
            return null;
        }
        inflate.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                FragmentHostManager fragmentHostManager = FragmentHostManager.get(view);
                fragmentHostManager.getFragmentManager().beginTransaction().replace(C1777R$id.navigation_bar_frame, NavigationBarFragment.this, "NavigationBar").commit();
                fragmentHostManager.addTagListener("NavigationBar", fragmentListener);
            }

            public void onViewDetachedFromWindow(View view) {
                FragmentHostManager.removeAndDestroy(view);
            }
        });
        ((WindowManager) context.getSystemService(WindowManager.class)).addView(inflate, layoutParams);
        return inflate;
    }

    /* access modifiers changed from: package-private */
    public int getNavigationIconHints() {
        return this.mNavigationIconHints;
    }
}
