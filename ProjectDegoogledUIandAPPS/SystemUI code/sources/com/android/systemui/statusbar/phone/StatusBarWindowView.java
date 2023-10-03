package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.session.MediaSessionLegacyHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.DisplayCutout;
import android.view.GestureDetector;
import android.view.InputQueue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsetsController;
import android.widget.FrameLayout;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.view.FloatingActionMode;
import com.android.internal.widget.FloatingToolbar;
import com.android.systemui.C1777R$id;
import com.android.systemui.Dependency;
import com.android.systemui.R$styleable;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.DragDownHelper;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.tuner.TunerService;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class StatusBarWindowView extends FrameLayout {
    private PhoneStatusBarTransitions mBarTransitions;
    private View mBrightnessMirror;
    private KeyguardBypassController mBypassController;
    /* access modifiers changed from: private */
    public boolean mDoubleTapDozeEnabled;
    /* access modifiers changed from: private */
    public boolean mDoubleTapEnabled;
    /* access modifiers changed from: private */
    public boolean mDoubleTapEnabledNative;
    private DragDownHelper mDragDownHelper;
    private boolean mExpandAnimationPending;
    private boolean mExpandAnimationRunning;
    private boolean mExpandingBelowNotch;
    private Window mFakeWindow = new Window(this.mContext) {
        public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        }

        public void alwaysReadCloseOnTouchAttr() {
        }

        public void clearContentView() {
        }

        public void closeAllPanels() {
        }

        public void closePanel(int i) {
        }

        public View getCurrentFocus() {
            return null;
        }

        public WindowInsetsController getInsetsController() {
            return null;
        }

        public LayoutInflater getLayoutInflater() {
            return null;
        }

        public int getNavigationBarColor() {
            return 0;
        }

        public int getStatusBarColor() {
            return 0;
        }

        public int getVolumeControlStream() {
            return 0;
        }

        public void invalidatePanelMenu(int i) {
        }

        public boolean isFloating() {
            return false;
        }

        public boolean isShortcutKey(int i, KeyEvent keyEvent) {
            return false;
        }

        /* access modifiers changed from: protected */
        public void onActive() {
        }

        public void onConfigurationChanged(Configuration configuration) {
        }

        public void onMultiWindowModeChanged() {
        }

        public void onPictureInPictureModeChanged(boolean z) {
        }

        public void openPanel(int i, KeyEvent keyEvent) {
        }

        public View peekDecorView() {
            return null;
        }

        public boolean performContextMenuIdentifierAction(int i, int i2) {
            return false;
        }

        public boolean performPanelIdentifierAction(int i, int i2, int i3) {
            return false;
        }

        public boolean performPanelShortcut(int i, int i2, KeyEvent keyEvent, int i3) {
            return false;
        }

        public void reportActivityRelaunched() {
        }

        public void restoreHierarchyState(Bundle bundle) {
        }

        public Bundle saveHierarchyState() {
            return null;
        }

        public void setBackgroundDrawable(Drawable drawable) {
        }

        public void setChildDrawable(int i, Drawable drawable) {
        }

        public void setChildInt(int i, int i2) {
        }

        public void setContentView(int i) {
        }

        public void setContentView(View view) {
        }

        public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        }

        public void setDecorCaptionShade(int i) {
        }

        public void setFeatureDrawable(int i, Drawable drawable) {
        }

        public void setFeatureDrawableAlpha(int i, int i2) {
        }

        public void setFeatureDrawableResource(int i, int i2) {
        }

        public void setFeatureDrawableUri(int i, Uri uri) {
        }

        public void setFeatureInt(int i, int i2) {
        }

        public void setNavigationBarColor(int i) {
        }

        public void setResizingCaptionDrawable(Drawable drawable) {
        }

        public void setStatusBarColor(int i) {
        }

        public void setTitle(CharSequence charSequence) {
        }

        public void setTitleColor(int i) {
        }

        public void setVolumeControlStream(int i) {
        }

        public boolean superDispatchGenericMotionEvent(MotionEvent motionEvent) {
            return false;
        }

        public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
            return false;
        }

        public boolean superDispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return false;
        }

        public boolean superDispatchTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public boolean superDispatchTrackballEvent(MotionEvent motionEvent) {
            return false;
        }

        public void takeInputQueue(InputQueue.Callback callback) {
        }

        public void takeKeyEvents(boolean z) {
        }

        public void takeSurface(SurfaceHolder.Callback2 callback2) {
        }

        public void togglePanel(int i, KeyEvent keyEvent) {
        }

        public View getDecorView() {
            return StatusBarWindowView.this;
        }
    };
    private FalsingManager mFalsingManager;
    /* access modifiers changed from: private */
    public ActionMode mFloatingActionMode;
    private View mFloatingActionModeOriginatingView;
    private FloatingToolbar mFloatingToolbar;
    private ViewTreeObserver.OnPreDrawListener mFloatingToolbarPreDrawListener;
    private final GestureDetector mGestureDetector;
    private final GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (!StatusBarWindowView.this.mSingleTapEnabled || StatusBarWindowView.this.mSuppressingWakeUpGesture) {
                return false;
            }
            StatusBarWindowView.this.mService.wakeUpIfDozing(SystemClock.uptimeMillis(), StatusBarWindowView.this, "SINGLE_TAP");
            return true;
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (StatusBarWindowView.this.mIsMusicTickerTap) {
                StatusBarWindowView.this.mService.handleSystemKey(87);
                return true;
            } else if (!StatusBarWindowView.this.mDoubleTapEnabled && !StatusBarWindowView.this.mSingleTapEnabled && !StatusBarWindowView.this.mDoubleTapEnabledNative && !StatusBarWindowView.this.mDoubleTapDozeEnabled) {
                return false;
            } else {
                StatusBarWindowView.this.mService.wakeUpIfDozing(SystemClock.uptimeMillis(), StatusBarWindowView.this, "DOUBLE_TAP");
                return true;
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mIsMusicTickerTap;
    private int mLeftInset = 0;
    private LockIcon mLockIcon;
    private NotificationPanelView mNotificationPanel;
    private int mRightInset = 0;
    /* access modifiers changed from: private */
    public StatusBar mService;
    /* access modifiers changed from: private */
    public boolean mSingleTapEnabled;
    private NotificationStackScrollLayout mStackScrollLayout;
    private final StatusBarStateController mStatusBarStateController;
    private PhoneStatusBarView mStatusBarView;
    /* access modifiers changed from: private */
    public boolean mSuppressingWakeUpGesture;
    private boolean mTouchActive;
    private boolean mTouchCancelled;
    private final Paint mTransparentSrcPaint = new Paint();
    private final TunerService.Tunable mTunable = new TunerService.Tunable() {
        public final void onTuningChanged(String str, String str2) {
            StatusBarWindowView.this.lambda$new$0$StatusBarWindowView(str, str2);
        }
    };

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$new$0$StatusBarWindowView(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            android.hardware.display.AmbientDisplayConfiguration r9 = new android.hardware.display.AmbientDisplayConfiguration
            android.content.Context r0 = r7.mContext
            r9.<init>(r0)
            int r0 = r8.hashCode()
            java.lang.String r1 = "doze_trigger_doubletap"
            java.lang.String r2 = "double_tap_to_wake"
            r3 = 3
            r4 = 2
            r5 = 0
            r6 = 1
            switch(r0) {
                case -1415590690: goto L_0x0033;
                case -66261662: goto L_0x002b;
                case 417936100: goto L_0x0021;
                case 1073289638: goto L_0x0017;
                default: goto L_0x0016;
            }
        L_0x0016:
            goto L_0x003b
        L_0x0017:
            java.lang.String r0 = "doze_pulse_on_double_tap"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x003b
            r8 = r5
            goto L_0x003c
        L_0x0021:
            java.lang.String r0 = "doze_tap_gesture"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x003b
            r8 = r6
            goto L_0x003c
        L_0x002b:
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x003b
            r8 = r3
            goto L_0x003c
        L_0x0033:
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x003b
            r8 = r4
            goto L_0x003c
        L_0x003b:
            r8 = -1
        L_0x003c:
            r0 = -2
            if (r8 == 0) goto L_0x006d
            if (r8 == r6) goto L_0x0066
            if (r8 == r4) goto L_0x0056
            if (r8 == r3) goto L_0x0046
            goto L_0x0073
        L_0x0046:
            android.content.Context r8 = r7.mContext
            android.content.ContentResolver r8 = r8.getContentResolver()
            int r8 = android.provider.Settings.System.getInt(r8, r1, r5)
            if (r8 != r6) goto L_0x0053
            r5 = r6
        L_0x0053:
            r7.mDoubleTapDozeEnabled = r5
            goto L_0x0073
        L_0x0056:
            android.content.Context r8 = r7.mContext
            android.content.ContentResolver r8 = r8.getContentResolver()
            int r8 = android.provider.Settings.Secure.getIntForUser(r8, r2, r5, r0)
            if (r8 != r6) goto L_0x0063
            r5 = r6
        L_0x0063:
            r7.mDoubleTapEnabledNative = r5
            goto L_0x0073
        L_0x0066:
            boolean r8 = r9.tapGestureEnabled(r0)
            r7.mSingleTapEnabled = r8
            goto L_0x0073
        L_0x006d:
            boolean r8 = r9.doubleTapGestureEnabled(r0)
            r7.mDoubleTapEnabled = r8
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.StatusBarWindowView.lambda$new$0$StatusBarWindowView(java.lang.String, java.lang.String):void");
    }

    public StatusBarWindowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setMotionEventSplittingEnabled(false);
        this.mTransparentSrcPaint.setColor(0);
        this.mTransparentSrcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        this.mFalsingManager = (FalsingManager) Dependency.get(FalsingManager.class);
        this.mGestureDetector = new GestureDetector(context, this.mGestureListener);
        this.mStatusBarStateController = (StatusBarStateController) Dependency.get(StatusBarStateController.class);
        ((TunerService) Dependency.get(TunerService.class)).addTunable(this.mTunable, "doze_pulse_on_double_tap", "doze_tap_gesture", "double_tap_to_wake", "doze_trigger_doubletap");
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(Rect rect) {
        int i;
        int i2;
        boolean z = true;
        if (getFitsSystemWindows()) {
            if (rect.top == getPaddingTop() && rect.bottom == getPaddingBottom()) {
                z = false;
            }
            DisplayCutout displayCutout = getRootWindowInsets().getDisplayCutout();
            if (displayCutout != null) {
                i = displayCutout.getSafeInsetLeft();
                i2 = displayCutout.getSafeInsetRight();
            } else {
                i2 = 0;
                i = 0;
            }
            int max = Math.max(rect.left, i);
            int max2 = Math.max(rect.right, i2);
            if (!(max2 == this.mRightInset && max == this.mLeftInset)) {
                this.mRightInset = max2;
                this.mLeftInset = max;
                applyMargins();
            }
            if (z) {
                setPadding(0, 0, 0, 0);
            }
            rect.left = 0;
            rect.top = 0;
            rect.right = 0;
        } else {
            if (!(this.mRightInset == 0 && this.mLeftInset == 0)) {
                this.mRightInset = 0;
                this.mLeftInset = 0;
                applyMargins();
            }
            if (getPaddingLeft() == 0 && getPaddingRight() == 0 && getPaddingTop() == 0 && getPaddingBottom() == 0) {
                z = false;
            }
            if (z) {
                setPadding(0, 0, 0, 0);
            }
            rect.top = 0;
        }
        return false;
    }

    private void applyMargins() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getLayoutParams() instanceof LayoutParams) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (!layoutParams.ignoreRightInset && !(layoutParams.rightMargin == this.mRightInset && layoutParams.leftMargin == this.mLeftInset)) {
                    layoutParams.rightMargin = this.mRightInset;
                    layoutParams.leftMargin = this.mLeftInset;
                    childAt.requestLayout();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public NotificationStackScrollLayout getStackScrollLayout() {
        return this.mStackScrollLayout;
    }

    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mStackScrollLayout = (NotificationStackScrollLayout) findViewById(C1777R$id.notification_stack_scroller);
        this.mNotificationPanel = (NotificationPanelView) findViewById(C1777R$id.notification_panel);
        this.mBrightnessMirror = findViewById(C1777R$id.brightness_mirror);
        this.mLockIcon = (LockIcon) findViewById(C1777R$id.lock_icon);
    }

    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view.getId() == C1777R$id.brightness_mirror) {
            this.mBrightnessMirror = view;
        }
    }

    public void setPulsing(boolean z) {
        LockIcon lockIcon = this.mLockIcon;
        if (lockIcon != null) {
            lockIcon.setPulsing(z);
        }
    }

    public void onBiometricAuthModeChanged(boolean z, boolean z2) {
        LockIcon lockIcon = this.mLockIcon;
        if (lockIcon != null) {
            lockIcon.onBiometricAuthModeChanged(z, z2);
        }
    }

    public void setStatusBarView(PhoneStatusBarView phoneStatusBarView) {
        this.mStatusBarView = phoneStatusBarView;
        this.mBarTransitions = new PhoneStatusBarTransitions(phoneStatusBarView, findViewById(C1777R$id.status_bar_container));
    }

    public PhoneStatusBarTransitions getBarTransitions() {
        return this.mBarTransitions;
    }

    public void setService(StatusBar statusBar) {
        this.mService = statusBar;
        NotificationStackScrollLayout stackScrollLayout = getStackScrollLayout();
        setDragDownHelper(new DragDownHelper(getContext(), this, stackScrollLayout.getExpandHelperCallback(), stackScrollLayout.getDragDownCallback(), this.mFalsingManager));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setDragDownHelper(DragDownHelper dragDownHelper) {
        this.mDragDownHelper = dragDownHelper;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setWillNotDraw(true);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.mService.interceptMediaKey(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        boolean z = keyEvent.getAction() == 0;
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 4) {
            if (keyCode != 62) {
                if (keyCode != 82) {
                    if ((keyCode == 24 || keyCode == 25) && this.mService.isDozing()) {
                        MediaSessionLegacyHelper.getHelper(this.mContext).sendVolumeKeyEvent(keyEvent, Integer.MIN_VALUE, true);
                        return true;
                    }
                    return false;
                } else if (!z) {
                    return this.mService.onMenuPressed();
                }
            }
            if (!z) {
                return this.mService.onSpacePressed();
            }
            return false;
        }
        if (!z) {
            this.mService.onBackPressed();
        }
        return true;
    }

    public void setTouchActive(boolean z) {
        this.mTouchActive = z;
    }

    /* access modifiers changed from: package-private */
    public void suppressWakeUpGesture(boolean z) {
        this.mSuppressingWakeUpGesture = z;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        boolean z2 = motionEvent.getActionMasked() == 0;
        boolean z3 = motionEvent.getActionMasked() == 1;
        boolean z4 = motionEvent.getActionMasked() == 3;
        boolean z5 = this.mExpandingBelowNotch;
        if (z3 || z4) {
            this.mExpandingBelowNotch = false;
        }
        if (!z4 && this.mService.shouldIgnoreTouch()) {
            return false;
        }
        if (z2 && this.mNotificationPanel.isFullyCollapsed()) {
            this.mNotificationPanel.startExpandLatencyTracking();
        }
        if (z2) {
            setTouchActive(true);
            this.mTouchCancelled = false;
        } else if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
            setTouchActive(false);
        }
        if (this.mTouchCancelled || this.mExpandAnimationRunning || this.mExpandAnimationPending) {
            return false;
        }
        this.mFalsingManager.onTouchEvent(motionEvent, getWidth(), getHeight());
        this.mGestureDetector.onTouchEvent(motionEvent);
        View view = this.mBrightnessMirror;
        if (view != null && view.getVisibility() == 0 && motionEvent.getActionMasked() == 5) {
            return false;
        }
        if (z2) {
            getStackScrollLayout().closeControlsIfOutsideTouch(motionEvent);
        }
        if (this.mService.isDozing()) {
            this.mService.mDozeScrimController.extendPulse();
        }
        if (!z2 || motionEvent.getY() < ((float) this.mBottom)) {
            z = z5;
        } else {
            this.mExpandingBelowNotch = true;
        }
        if (z) {
            return this.mStatusBarView.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        NotificationStackScrollLayout stackScrollLayout = getStackScrollLayout();
        boolean z = false;
        this.mIsMusicTickerTap = false;
        if (this.mService.isDozing()) {
            if (this.mService.isDoubleTapOnMusicTicker(motionEvent.getX(), motionEvent.getY())) {
                this.mIsMusicTickerTap = true;
            }
            if (!this.mService.isPulsing()) {
                return true;
            }
        }
        if (this.mNotificationPanel.isFullyExpanded() && this.mDragDownHelper.isDragDownEnabled() && !this.mService.isBouncerShowing() && !this.mService.isDozing()) {
            z = this.mDragDownHelper.onInterceptTouchEvent(motionEvent);
        }
        if (!z) {
            super.onInterceptTouchEvent(motionEvent);
        }
        if (z) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(3);
            stackScrollLayout.onInterceptTouchEvent(obtain);
            this.mNotificationPanel.onInterceptTouchEvent(obtain);
            obtain.recycle();
        }
        return z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = this.mService.isDozing() ? !this.mService.isPulsing() : false;
        if ((this.mDragDownHelper.isDragDownEnabled() && !z) || this.mDragDownHelper.isDraggingDown()) {
            z = this.mDragDownHelper.onTouchEvent(motionEvent);
        }
        if (!z) {
            z = super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (!z && (action == 1 || action == 3)) {
            this.mService.setInteracting(1, false);
        }
        return z;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void cancelExpandHelper() {
        NotificationStackScrollLayout stackScrollLayout = getStackScrollLayout();
        if (stackScrollLayout != null) {
            stackScrollLayout.cancelExpandHelper();
        }
    }

    public void cancelCurrentTouch() {
        if (this.mTouchActive) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            obtain.setSource(4098);
            dispatchTouchEvent(obtain);
            obtain.recycle();
            this.mTouchCancelled = true;
        }
    }

    public void setExpandAnimationRunning(boolean z) {
        this.mExpandAnimationRunning = z;
    }

    public void setExpandAnimationPending(boolean z) {
        this.mExpandAnimationPending = z;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print("  mExpandAnimationPending=");
        printWriter.println(this.mExpandAnimationPending);
        printWriter.print("  mExpandAnimationRunning=");
        printWriter.println(this.mExpandAnimationRunning);
        printWriter.print("  mTouchCancelled=");
        printWriter.println(this.mTouchCancelled);
        printWriter.print("  mTouchActive=");
        printWriter.println(this.mTouchActive);
    }

    public void onScrimVisibilityChanged(int i) {
        LockIcon lockIcon = this.mLockIcon;
        if (lockIcon != null) {
            lockIcon.onScrimVisibilityChanged(i);
        }
    }

    public void onShowingLaunchAffordanceChanged(boolean z) {
        LockIcon lockIcon = this.mLockIcon;
        if (lockIcon != null) {
            lockIcon.onShowingLaunchAffordanceChanged(z);
        }
    }

    public void setBypassController(KeyguardBypassController keyguardBypassController) {
        this.mBypassController = keyguardBypassController;
    }

    public void setBouncerShowingScrimmed(boolean z) {
        LockIcon lockIcon = this.mLockIcon;
        if (lockIcon != null) {
            lockIcon.setBouncerShowingScrimmed(z);
        }
    }

    public class LayoutParams extends FrameLayout.LayoutParams {
        public boolean ignoreRightInset;

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.StatusBarWindowView_Layout);
            this.ignoreRightInset = obtainStyledAttributes.getBoolean(R$styleable.StatusBarWindowView_Layout_ignoreRightInset, false);
            obtainStyledAttributes.recycle();
        }
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i == 1) {
            return startActionMode(view, callback, i);
        }
        return super.startActionModeForChild(view, callback, i);
    }

    private ActionMode createFloatingActionMode(View view, ActionMode.Callback2 callback2) {
        ActionMode actionMode = this.mFloatingActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        cleanupFloatingActionModeViews();
        this.mFloatingToolbar = new FloatingToolbar(this.mFakeWindow);
        final FloatingActionMode floatingActionMode = new FloatingActionMode(this.mContext, callback2, view, this.mFloatingToolbar);
        this.mFloatingActionModeOriginatingView = view;
        this.mFloatingToolbarPreDrawListener = new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                floatingActionMode.updateViewLocationInWindow();
                return true;
            }
        };
        return floatingActionMode;
    }

    private void setHandledFloatingActionMode(ActionMode actionMode) {
        this.mFloatingActionMode = actionMode;
        this.mFloatingActionMode.invalidate();
        this.mFloatingActionModeOriginatingView.getViewTreeObserver().addOnPreDrawListener(this.mFloatingToolbarPreDrawListener);
    }

    /* access modifiers changed from: private */
    public void cleanupFloatingActionModeViews() {
        FloatingToolbar floatingToolbar = this.mFloatingToolbar;
        if (floatingToolbar != null) {
            floatingToolbar.dismiss();
            this.mFloatingToolbar = null;
        }
        View view = this.mFloatingActionModeOriginatingView;
        if (view != null) {
            if (this.mFloatingToolbarPreDrawListener != null) {
                view.getViewTreeObserver().removeOnPreDrawListener(this.mFloatingToolbarPreDrawListener);
                this.mFloatingToolbarPreDrawListener = null;
            }
            this.mFloatingActionModeOriginatingView = null;
        }
    }

    private ActionMode startActionMode(View view, ActionMode.Callback callback, int i) {
        ActionModeCallback2Wrapper actionModeCallback2Wrapper = new ActionModeCallback2Wrapper(callback);
        ActionMode createFloatingActionMode = createFloatingActionMode(view, actionModeCallback2Wrapper);
        if (createFloatingActionMode == null || !actionModeCallback2Wrapper.onCreateActionMode(createFloatingActionMode, createFloatingActionMode.getMenu())) {
            return null;
        }
        setHandledFloatingActionMode(createFloatingActionMode);
        return createFloatingActionMode;
    }

    private class ActionModeCallback2Wrapper extends ActionMode.Callback2 {
        private final ActionMode.Callback mWrapped;

        public ActionModeCallback2Wrapper(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            StatusBarWindowView.this.requestFitSystemWindows();
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            if (actionMode == StatusBarWindowView.this.mFloatingActionMode) {
                StatusBarWindowView.this.cleanupFloatingActionModeViews();
                ActionMode unused = StatusBarWindowView.this.mFloatingActionMode = null;
            }
            StatusBarWindowView.this.requestFitSystemWindows();
        }

        public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
            ActionMode.Callback callback = this.mWrapped;
            if (callback instanceof ActionMode.Callback2) {
                ((ActionMode.Callback2) callback).onGetContentRect(actionMode, view, rect);
            } else {
                super.onGetContentRect(actionMode, view, rect);
            }
        }
    }

    public void updateSettings() {
        boolean z = true;
        boolean z2 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "double_tap_sleep_gesture", 0, -2) == 1;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "double_tap_sleep_lockscreen", 0, -2) != 1) {
            z = false;
        }
        int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_quick_qs_pulldown", 0, -2);
        NotificationPanelView notificationPanelView = this.mNotificationPanel;
        if (notificationPanelView != null) {
            notificationPanelView.updateDoubleTapToSleep(z2);
            this.mNotificationPanel.setLockscreenDoubleTapToSleep(z);
            this.mNotificationPanel.setQsQuickPulldown(intForUser);
        }
        DragDownHelper dragDownHelper = this.mDragDownHelper;
        if (dragDownHelper != null) {
            dragDownHelper.updateDoubleTapToSleep(z2);
        }
    }
}
