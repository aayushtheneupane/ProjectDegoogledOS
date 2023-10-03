package com.android.keyguard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.metrics.LogMaker;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Slog;
import android.util.StatsLog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.settingslib.utils.ThreadUtils;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.SystemUIFactory;
import com.android.systemui.statusbar.phone.UnlockMethodCache;
import com.android.systemui.util.InjectionInflationController;

public class KeyguardSecurityContainer extends FrameLayout implements KeyguardSecurityView {
    private int mActivePointerId;
    private AlertDialog mAlertDialog;
    private KeyguardSecurityCallback mCallback;
    private KeyguardSecurityModel.SecurityMode mCurrentSecuritySelection;
    private KeyguardSecurityView mCurrentSecurityView;
    private InjectionInflationController mInjectionInflationController;
    private boolean mIsDragging;
    private float mLastTouchY;
    /* access modifiers changed from: private */
    public LockPatternUtils mLockPatternUtils;
    /* access modifiers changed from: private */
    public final MetricsLogger mMetricsLogger;
    private KeyguardSecurityCallback mNullCallback;
    /* access modifiers changed from: private */
    public SecurityCallback mSecurityCallback;
    private KeyguardSecurityModel mSecurityModel;
    private KeyguardSecurityViewFlipper mSecurityViewFlipper;
    private final SpringAnimation mSpringAnimation;
    private float mStartTouchY;
    private boolean mSwipeUpToRetry;
    private final UnlockMethodCache mUnlockMethodCache;
    /* access modifiers changed from: private */
    public final KeyguardUpdateMonitor mUpdateMonitor;
    private final VelocityTracker mVelocityTracker;
    private final ViewConfiguration mViewConfiguration;

    public interface SecurityCallback {
        boolean dismiss(boolean z, int i);

        void finish(boolean z, int i);

        void onCancelClicked();

        void onSecurityModeChanged(KeyguardSecurityModel.SecurityMode securityMode, boolean z);

        void reset();

        void userActivity();
    }

    public boolean shouldDelayChildPressedState() {
        return true;
    }

    public KeyguardSecurityContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public KeyguardSecurityContainer(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public KeyguardSecurityContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentSecuritySelection = KeyguardSecurityModel.SecurityMode.Invalid;
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mMetricsLogger = (MetricsLogger) Dependency.get(MetricsLogger.class);
        this.mLastTouchY = -1.0f;
        this.mActivePointerId = -1;
        this.mStartTouchY = -1.0f;
        this.mCallback = new KeyguardSecurityCallback() {
            public void userActivity() {
                if (KeyguardSecurityContainer.this.mSecurityCallback != null) {
                    KeyguardSecurityContainer.this.mSecurityCallback.userActivity();
                }
            }

            public void onUserInput() {
                KeyguardSecurityContainer.this.mUpdateMonitor.cancelFaceAuth();
            }

            public void dismiss(boolean z, int i) {
                KeyguardSecurityContainer.this.mSecurityCallback.dismiss(z, i);
            }

            public void reportUnlockAttempt(int i, boolean z, int i2) {
                if (z) {
                    StatsLog.write(64, 2);
                    KeyguardSecurityContainer.this.mLockPatternUtils.reportSuccessfulPasswordAttempt(i);
                    ThreadUtils.postOnBackgroundThread(C0420x8e4c0c84.INSTANCE);
                } else {
                    StatsLog.write(64, 1);
                    KeyguardSecurityContainer.this.reportFailedUnlockAttempt(i, i2);
                }
                KeyguardSecurityContainer.this.mMetricsLogger.write(new LogMaker(197).setType(z ? 10 : 11));
            }

            static /* synthetic */ void lambda$reportUnlockAttempt$0() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException unused) {
                }
                Runtime.getRuntime().gc();
            }

            public void reset() {
                KeyguardSecurityContainer.this.mSecurityCallback.reset();
            }

            public void onCancelClicked() {
                KeyguardSecurityContainer.this.mSecurityCallback.onCancelClicked();
            }
        };
        this.mNullCallback = new KeyguardSecurityCallback() {
            public void dismiss(boolean z, int i) {
            }

            public void onUserInput() {
            }

            public void reportUnlockAttempt(int i, boolean z, int i2) {
            }

            public void reset() {
            }

            public void userActivity() {
            }
        };
        this.mSecurityModel = new KeyguardSecurityModel(context);
        this.mLockPatternUtils = new LockPatternUtils(context);
        this.mUpdateMonitor = KeyguardUpdateMonitor.getInstance(this.mContext);
        this.mSpringAnimation = new SpringAnimation(this, DynamicAnimation.f14Y);
        this.mInjectionInflationController = new InjectionInflationController(SystemUIFactory.getInstance().getRootComponent());
        this.mUnlockMethodCache = UnlockMethodCache.getInstance(context);
        this.mViewConfiguration = ViewConfiguration.get(context);
    }

    public void setSecurityCallback(SecurityCallback securityCallback) {
        this.mSecurityCallback = securityCallback;
    }

    public void onResume(int i) {
        KeyguardSecurityModel.SecurityMode securityMode = this.mCurrentSecuritySelection;
        if (securityMode != KeyguardSecurityModel.SecurityMode.None) {
            getSecurityView(securityMode).onResume(i);
        }
        updateBiometricRetry();
    }

    public void onPause() {
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mAlertDialog = null;
        }
        KeyguardSecurityModel.SecurityMode securityMode = this.mCurrentSecuritySelection;
        if (securityMode != KeyguardSecurityModel.SecurityMode.None) {
            getSecurityView(securityMode).onPause();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
        if (r0 != 3) goto L_0x0061;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            r1 = 0
            if (r0 == 0) goto L_0x004c
            r2 = 1
            if (r0 == r2) goto L_0x0049
            r3 = 2
            if (r0 == r3) goto L_0x0011
            r6 = 3
            if (r0 == r6) goto L_0x0049
            goto L_0x0061
        L_0x0011:
            boolean r0 = r5.mIsDragging
            if (r0 == 0) goto L_0x0016
            return r2
        L_0x0016:
            boolean r0 = r5.mSwipeUpToRetry
            if (r0 != 0) goto L_0x001b
            return r1
        L_0x001b:
            com.android.keyguard.KeyguardSecurityView r0 = r5.mCurrentSecurityView
            boolean r0 = r0.disallowInterceptTouch(r6)
            if (r0 == 0) goto L_0x0024
            return r1
        L_0x0024:
            int r0 = r5.mActivePointerId
            int r0 = r6.findPointerIndex(r0)
            android.view.ViewConfiguration r3 = r5.mViewConfiguration
            int r3 = r3.getScaledTouchSlop()
            float r3 = (float) r3
            r4 = 1073741824(0x40000000, float:2.0)
            float r3 = r3 * r4
            com.android.keyguard.KeyguardSecurityView r4 = r5.mCurrentSecurityView
            if (r4 == 0) goto L_0x0061
            r4 = -1
            if (r0 == r4) goto L_0x0061
            float r4 = r5.mStartTouchY
            float r6 = r6.getY(r0)
            float r4 = r4 - r6
            int r6 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x0061
            r5.mIsDragging = r2
            return r2
        L_0x0049:
            r5.mIsDragging = r1
            goto L_0x0061
        L_0x004c:
            int r0 = r6.getActionIndex()
            float r2 = r6.getY(r0)
            r5.mStartTouchY = r2
            int r6 = r6.getPointerId(r0)
            r5.mActivePointerId = r6
            android.view.VelocityTracker r5 = r5.mVelocityTracker
            r5.clear()
        L_0x0061:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.keyguard.KeyguardSecurityContainer.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int i = 0;
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                this.mVelocityTracker.addMovement(motionEvent);
                float y = motionEvent.getY(motionEvent.findPointerIndex(this.mActivePointerId));
                float f = this.mLastTouchY;
                if (f != -1.0f) {
                    setTranslationY(getTranslationY() + ((y - f) * 0.25f));
                }
                this.mLastTouchY = y;
            } else if (actionMasked != 3) {
                if (actionMasked == 6) {
                    int actionIndex = motionEvent.getActionIndex();
                    if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
                        if (actionIndex == 0) {
                            i = 1;
                        }
                        this.mLastTouchY = motionEvent.getY(i);
                        this.mActivePointerId = motionEvent.getPointerId(i);
                    }
                }
            }
            if (actionMasked == 1 && (-getTranslationY()) > TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics()) && !this.mUpdateMonitor.isFaceDetectionRunning()) {
                this.mUpdateMonitor.requestFaceAuth();
                this.mCallback.userActivity();
                showMessage((CharSequence) null, (ColorStateList) null);
            }
            return true;
        }
        this.mActivePointerId = -1;
        this.mLastTouchY = -1.0f;
        this.mIsDragging = false;
        startSpringAnimation(this.mVelocityTracker.getYVelocity());
        this.mUpdateMonitor.requestFaceAuth();
        this.mCallback.userActivity();
        showMessage((CharSequence) null, (ColorStateList) null);
        return true;
    }

    private void startSpringAnimation(float f) {
        SpringAnimation springAnimation = this.mSpringAnimation;
        springAnimation.setStartVelocity(f);
        springAnimation.animateToFinalPosition(0.0f);
    }

    public void startAppearAnimation() {
        KeyguardSecurityModel.SecurityMode securityMode = this.mCurrentSecuritySelection;
        if (securityMode != KeyguardSecurityModel.SecurityMode.None) {
            getSecurityView(securityMode).startAppearAnimation();
        }
    }

    public boolean startDisappearAnimation(Runnable runnable) {
        KeyguardSecurityModel.SecurityMode securityMode = this.mCurrentSecuritySelection;
        if (securityMode != KeyguardSecurityModel.SecurityMode.None) {
            return getSecurityView(securityMode).startDisappearAnimation(runnable);
        }
        return false;
    }

    private void updateBiometricRetry() {
        KeyguardSecurityModel.SecurityMode securityMode = getSecurityMode();
        this.mSwipeUpToRetry = (!this.mUnlockMethodCache.isFaceAuthEnabled() || securityMode == KeyguardSecurityModel.SecurityMode.SimPin || securityMode == KeyguardSecurityModel.SecurityMode.SimPuk || securityMode == KeyguardSecurityModel.SecurityMode.None) ? false : true;
    }

    public CharSequence getTitle() {
        return this.mSecurityViewFlipper.getTitle();
    }

    private KeyguardSecurityView getSecurityView(KeyguardSecurityModel.SecurityMode securityMode) {
        KeyguardSecurityView keyguardSecurityView;
        int securityViewIdForMode = getSecurityViewIdForMode(securityMode);
        int childCount = this.mSecurityViewFlipper.getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                keyguardSecurityView = null;
                break;
            } else if (this.mSecurityViewFlipper.getChildAt(i).getId() == securityViewIdForMode) {
                keyguardSecurityView = (KeyguardSecurityView) this.mSecurityViewFlipper.getChildAt(i);
                break;
            } else {
                i++;
            }
        }
        int layoutIdFor = getLayoutIdFor(securityMode);
        if (keyguardSecurityView != null || layoutIdFor == 0) {
            return keyguardSecurityView;
        }
        View inflate = this.mInjectionInflationController.injectable(LayoutInflater.from(this.mContext)).inflate(layoutIdFor, this.mSecurityViewFlipper, false);
        this.mSecurityViewFlipper.addView(inflate);
        updateSecurityView(inflate);
        KeyguardSecurityView keyguardSecurityView2 = (KeyguardSecurityView) inflate;
        keyguardSecurityView2.reset();
        return keyguardSecurityView2;
    }

    private void updateSecurityView(View view) {
        if (view instanceof KeyguardSecurityView) {
            KeyguardSecurityView keyguardSecurityView = (KeyguardSecurityView) view;
            keyguardSecurityView.setKeyguardCallback(this.mCallback);
            keyguardSecurityView.setLockPatternUtils(this.mLockPatternUtils);
            return;
        }
        Log.w("KeyguardSecurityView", "View " + view + " is not a KeyguardSecurityView");
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.mSecurityViewFlipper = (KeyguardSecurityViewFlipper) findViewById(C1777R$id.view_flipper);
        this.mSecurityViewFlipper.setLockPatternUtils(this.mLockPatternUtils);
    }

    public void setLockPatternUtils(LockPatternUtils lockPatternUtils) {
        this.mLockPatternUtils = lockPatternUtils;
        this.mSecurityModel.setLockPatternUtils(lockPatternUtils);
        this.mSecurityViewFlipper.setLockPatternUtils(this.mLockPatternUtils);
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(Rect rect) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(C1775R$dimen.kg_security_container_min_bottom_margin);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int i = rect.bottom;
        if (dimensionPixelSize <= i) {
            dimensionPixelSize = i;
        }
        setPadding(paddingLeft, paddingTop, paddingRight, dimensionPixelSize);
        rect.bottom = 0;
        return false;
    }

    private void showDialog(String str, String str2) {
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.mAlertDialog = new AlertDialog.Builder(this.mContext).setTitle(str).setMessage(str2).setCancelable(false).setPositiveButton(C1784R$string.f31ok, (DialogInterface.OnClickListener) null).create();
        if (!(this.mContext instanceof Activity)) {
            this.mAlertDialog.getWindow().setType(2009);
        }
        this.mAlertDialog.show();
    }

    private void showTimeoutDialog(int i, int i2) {
        int i3;
        int i4 = i2 / 1000;
        int i5 = C04613.f22xdc0e830a[this.mSecurityModel.getSecurityMode(i).ordinal()];
        if (i5 == 1) {
            i3 = C1784R$string.kg_too_many_failed_pattern_attempts_dialog_message;
        } else if (i5 == 2) {
            i3 = C1784R$string.kg_too_many_failed_pin_attempts_dialog_message;
        } else if (i5 != 3) {
            i3 = 0;
        } else {
            i3 = C1784R$string.kg_too_many_failed_password_attempts_dialog_message;
        }
        if (i3 != 0) {
            showDialog((String) null, this.mContext.getString(i3, new Object[]{Integer.valueOf(this.mLockPatternUtils.getCurrentFailedPasswordAttempts(i)), Integer.valueOf(i4)}));
        }
    }

    /* renamed from: com.android.keyguard.KeyguardSecurityContainer$3 */
    static /* synthetic */ class C04613 {

        /* renamed from: $SwitchMap$com$android$keyguard$KeyguardSecurityModel$SecurityMode */
        static final /* synthetic */ int[] f22xdc0e830a = new int[KeyguardSecurityModel.SecurityMode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.android.keyguard.KeyguardSecurityModel$SecurityMode[] r0 = com.android.keyguard.KeyguardSecurityModel.SecurityMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f22xdc0e830a = r0
                int[] r0 = f22xdc0e830a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.android.keyguard.KeyguardSecurityModel$SecurityMode r1 = com.android.keyguard.KeyguardSecurityModel.SecurityMode.Pattern     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f22xdc0e830a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.android.keyguard.KeyguardSecurityModel$SecurityMode r1 = com.android.keyguard.KeyguardSecurityModel.SecurityMode.PIN     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f22xdc0e830a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.android.keyguard.KeyguardSecurityModel$SecurityMode r1 = com.android.keyguard.KeyguardSecurityModel.SecurityMode.Password     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f22xdc0e830a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.android.keyguard.KeyguardSecurityModel$SecurityMode r1 = com.android.keyguard.KeyguardSecurityModel.SecurityMode.Invalid     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f22xdc0e830a     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.android.keyguard.KeyguardSecurityModel$SecurityMode r1 = com.android.keyguard.KeyguardSecurityModel.SecurityMode.None     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f22xdc0e830a     // Catch:{ NoSuchFieldError -> 0x004b }
                com.android.keyguard.KeyguardSecurityModel$SecurityMode r1 = com.android.keyguard.KeyguardSecurityModel.SecurityMode.SimPin     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f22xdc0e830a     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.android.keyguard.KeyguardSecurityModel$SecurityMode r1 = com.android.keyguard.KeyguardSecurityModel.SecurityMode.SimPuk     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.keyguard.KeyguardSecurityContainer.C04613.<clinit>():void");
        }
    }

    private void showAlmostAtWipeDialog(int i, int i2, int i3) {
        String str;
        if (i3 == 1) {
            str = this.mContext.getString(C1784R$string.kg_failed_attempts_almost_at_wipe, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
        } else if (i3 == 2) {
            str = this.mContext.getString(C1784R$string.kg_failed_attempts_almost_at_erase_profile, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
        } else if (i3 != 3) {
            str = null;
        } else {
            str = this.mContext.getString(C1784R$string.kg_failed_attempts_almost_at_erase_user, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
        }
        showDialog((String) null, str);
    }

    private void showWipeDialog(int i, int i2) {
        String str;
        if (i2 == 1) {
            str = this.mContext.getString(C1784R$string.kg_failed_attempts_now_wiping, new Object[]{Integer.valueOf(i)});
        } else if (i2 == 2) {
            str = this.mContext.getString(C1784R$string.kg_failed_attempts_now_erasing_profile, new Object[]{Integer.valueOf(i)});
        } else if (i2 != 3) {
            str = null;
        } else {
            str = this.mContext.getString(C1784R$string.kg_failed_attempts_now_erasing_user, new Object[]{Integer.valueOf(i)});
        }
        showDialog((String) null, str);
    }

    /* access modifiers changed from: private */
    public void reportFailedUnlockAttempt(int i, int i2) {
        int i3 = 1;
        int currentFailedPasswordAttempts = this.mLockPatternUtils.getCurrentFailedPasswordAttempts(i) + 1;
        DevicePolicyManager devicePolicyManager = this.mLockPatternUtils.getDevicePolicyManager();
        int maximumFailedPasswordsForWipe = devicePolicyManager.getMaximumFailedPasswordsForWipe((ComponentName) null, i);
        int i4 = maximumFailedPasswordsForWipe > 0 ? maximumFailedPasswordsForWipe - currentFailedPasswordAttempts : Integer.MAX_VALUE;
        if (i4 < 5) {
            int profileWithMinimumFailedPasswordsForWipe = devicePolicyManager.getProfileWithMinimumFailedPasswordsForWipe(i);
            if (profileWithMinimumFailedPasswordsForWipe == i) {
                if (profileWithMinimumFailedPasswordsForWipe != 0) {
                    i3 = 3;
                }
            } else if (profileWithMinimumFailedPasswordsForWipe != -10000) {
                i3 = 2;
            }
            if (i4 > 0) {
                showAlmostAtWipeDialog(currentFailedPasswordAttempts, i4, i3);
            } else {
                Slog.i("KeyguardSecurityView", "Too many unlock attempts; user " + profileWithMinimumFailedPasswordsForWipe + " will be wiped!");
                showWipeDialog(currentFailedPasswordAttempts, i3);
            }
        }
        this.mLockPatternUtils.reportFailedPasswordAttempt(i);
        if (i2 > 0) {
            this.mLockPatternUtils.reportPasswordLockout(i2, i);
            showTimeoutDialog(i, i2);
        }
    }

    /* access modifiers changed from: package-private */
    public void showPrimarySecurityScreen(boolean z) {
        showSecurityScreen(this.mSecurityModel.getSecurityMode(KeyguardUpdateMonitor.getCurrentUser()));
    }

    /* access modifiers changed from: package-private */
    public boolean showNextSecurityScreenOrFinish(boolean z, int i) {
        int i2 = 2;
        boolean z2 = false;
        boolean z3 = true;
        if (this.mUpdateMonitor.getUserHasTrust(i)) {
            i2 = 3;
        } else if (!this.mUpdateMonitor.getUserUnlockedWithBiometric(i)) {
            KeyguardSecurityModel.SecurityMode securityMode = KeyguardSecurityModel.SecurityMode.None;
            KeyguardSecurityModel.SecurityMode securityMode2 = this.mCurrentSecuritySelection;
            if (securityMode == securityMode2) {
                KeyguardSecurityModel.SecurityMode securityMode3 = this.mSecurityModel.getSecurityMode(i);
                if (KeyguardSecurityModel.SecurityMode.None == securityMode3) {
                    i2 = 0;
                } else {
                    showSecurityScreen(securityMode3);
                }
            } else if (z) {
                int i3 = C04613.f22xdc0e830a[securityMode2.ordinal()];
                if (i3 == 1 || i3 == 2 || i3 == 3) {
                    i2 = 1;
                    z2 = true;
                } else if (i3 == 6 || i3 == 7) {
                    KeyguardSecurityModel.SecurityMode securityMode4 = this.mSecurityModel.getSecurityMode(i);
                    if (securityMode4 != KeyguardSecurityModel.SecurityMode.None || !this.mLockPatternUtils.isLockScreenDisabled(KeyguardUpdateMonitor.getCurrentUser())) {
                        showSecurityScreen(securityMode4);
                    } else {
                        i2 = 4;
                    }
                } else {
                    Log.v("KeyguardSecurityView", "Bad security screen " + this.mCurrentSecuritySelection + ", fail safe");
                    showPrimarySecurityScreen(false);
                }
            }
            i2 = -1;
            z3 = false;
        }
        if (i2 != -1) {
            this.mMetricsLogger.write(new LogMaker(197).setType(5).setSubtype(i2));
        }
        if (z3) {
            this.mSecurityCallback.finish(z2, i);
        }
        return z3;
    }

    private void showSecurityScreen(KeyguardSecurityModel.SecurityMode securityMode) {
        KeyguardSecurityModel.SecurityMode securityMode2 = this.mCurrentSecuritySelection;
        if (securityMode != securityMode2) {
            KeyguardSecurityView securityView = getSecurityView(securityMode2);
            KeyguardSecurityView securityView2 = getSecurityView(securityMode);
            if (securityView != null) {
                securityView.onPause();
                securityView.setKeyguardCallback(this.mNullCallback);
            }
            if (securityMode != KeyguardSecurityModel.SecurityMode.None) {
                securityView2.onResume(2);
                securityView2.setKeyguardCallback(this.mCallback);
            }
            int childCount = this.mSecurityViewFlipper.getChildCount();
            int securityViewIdForMode = getSecurityViewIdForMode(securityMode);
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    break;
                } else if (this.mSecurityViewFlipper.getChildAt(i).getId() == securityViewIdForMode) {
                    this.mSecurityViewFlipper.setDisplayedChild(i);
                    break;
                } else {
                    i++;
                }
            }
            this.mCurrentSecuritySelection = securityMode;
            this.mCurrentSecurityView = securityView2;
            SecurityCallback securityCallback = this.mSecurityCallback;
            if (securityMode != KeyguardSecurityModel.SecurityMode.None && securityView2.needsInput()) {
                z = true;
            }
            securityCallback.onSecurityModeChanged(securityMode, z);
        }
    }

    private int getSecurityViewIdForMode(KeyguardSecurityModel.SecurityMode securityMode) {
        int i = C04613.f22xdc0e830a[securityMode.ordinal()];
        if (i == 1) {
            return C1777R$id.keyguard_pattern_view;
        }
        if (i == 2) {
            return C1777R$id.keyguard_pin_view;
        }
        if (i == 3) {
            return C1777R$id.keyguard_password_view;
        }
        if (i == 6) {
            return C1777R$id.keyguard_sim_pin_view;
        }
        if (i != 7) {
            return 0;
        }
        return C1777R$id.keyguard_sim_puk_view;
    }

    public int getLayoutIdFor(KeyguardSecurityModel.SecurityMode securityMode) {
        int i = C04613.f22xdc0e830a[securityMode.ordinal()];
        if (i == 1) {
            return C1779R$layout.keyguard_pattern_view;
        }
        if (i == 2) {
            return C1779R$layout.keyguard_pin_view;
        }
        if (i == 3) {
            return C1779R$layout.keyguard_password_view;
        }
        if (i == 6) {
            return C1779R$layout.keyguard_sim_pin_view;
        }
        if (i != 7) {
            return 0;
        }
        return C1779R$layout.keyguard_sim_puk_view;
    }

    public KeyguardSecurityModel.SecurityMode getSecurityMode() {
        return this.mSecurityModel.getSecurityMode(KeyguardUpdateMonitor.getCurrentUser());
    }

    public KeyguardSecurityModel.SecurityMode getCurrentSecurityMode() {
        return this.mCurrentSecuritySelection;
    }

    public KeyguardSecurityView getCurrentSecurityView() {
        return this.mCurrentSecurityView;
    }

    public boolean needsInput() {
        return this.mSecurityViewFlipper.needsInput();
    }

    public void setKeyguardCallback(KeyguardSecurityCallback keyguardSecurityCallback) {
        this.mSecurityViewFlipper.setKeyguardCallback(keyguardSecurityCallback);
    }

    public void reset() {
        this.mSecurityViewFlipper.reset();
    }

    public void showPromptReason(int i) {
        if (this.mCurrentSecuritySelection != KeyguardSecurityModel.SecurityMode.None) {
            if (i != 0) {
                Log.i("KeyguardSecurityView", "Strong auth required, reason: " + i);
            }
            getSecurityView(this.mCurrentSecuritySelection).showPromptReason(i);
        }
    }

    public void showMessage(CharSequence charSequence, ColorStateList colorStateList) {
        KeyguardSecurityModel.SecurityMode securityMode = this.mCurrentSecuritySelection;
        if (securityMode != KeyguardSecurityModel.SecurityMode.None) {
            getSecurityView(securityMode).showMessage(charSequence, colorStateList);
        }
    }
}
