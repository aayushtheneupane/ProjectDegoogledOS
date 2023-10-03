package com.android.systemui.biometrics;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.hardware.biometrics.BiometricSourceType;
import android.net.Uri;
import android.os.Handler;
import android.os.IHwBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.RemoteException;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.R$styleable;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.settingslib.utils.ThreadUtils;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1774R$color;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.Dependency;
import com.android.systemui.biometrics.FODCircleView;
import com.android.systemui.statusbar.policy.ConfigurationController;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;
import vendor.lineage.biometrics.fingerprint.inscreen.V1_0.IFingerprintInscreen;
import vendor.lineage.biometrics.fingerprint.inscreen.V1_0.IFingerprintInscreenCallback;

public class FODCircleView extends ImageView implements ConfigurationController.ConfigurationListener {
    private final int[][] BRIGHTNESS_ALPHA_ARRAY = {new int[]{0, 255}, new int[]{1, 224}, new int[]{2, 213}, new int[]{3, 211}, new int[]{4, 208}, new int[]{5, 206}, new int[]{6, 203}, new int[]{8, 200}, new int[]{10, 196}, new int[]{15, 186}, new int[]{20, 176}, new int[]{30, 160}, new int[]{45, 139}, new int[]{70, R$styleable.AppCompatTheme_viewInflaterClass}, new int[]{100, 90}, new int[]{150, 56}, new int[]{227, 14}, new int[]{255, 0}};
    private final String SCREEN_BRIGHTNESS = "system:screen_brightness";
    /* access modifiers changed from: private */
    public Timer mBurnInProtectionTimer;
    private int mColor;
    private int mCurrentBrightness;
    private CustomSettingsObserver mCustomSettingsObserver = new CustomSettingsObserver(this.mHandler);
    private boolean mCutoutMasked;
    private IFingerprintInscreen mDaemon = null;
    private int mDefaultScreenBrightness;
    private final boolean mDimIcon;
    /* access modifiers changed from: private */
    public final int mDreamingMaxOffset;
    /* access modifiers changed from: private */
    public int mDreamingOffsetY;
    /* access modifiers changed from: private */
    public FODAnimation mFODAnimation;
    private IFingerprintInscreenCallback mFingerprintInscreenCallback = new IFingerprintInscreenCallback.Stub() {
        public /* synthetic */ void lambda$onFingerDown$0$FODCircleView$1() {
            FODCircleView.this.showCircle();
        }

        public void onFingerDown() {
            FODCircleView.this.mHandler.post(new Runnable() {
                public final void run() {
                    FODCircleView.C06731.this.lambda$onFingerDown$0$FODCircleView$1();
                }
            });
        }

        public /* synthetic */ void lambda$onFingerUp$1$FODCircleView$1() {
            FODCircleView.this.hideCircle();
        }

        public void onFingerUp() {
            FODCircleView.this.mHandler.post(new Runnable() {
                public final void run() {
                    FODCircleView.C06731.this.lambda$onFingerUp$1$FODCircleView$1();
                }
            });
        }
    };
    private IFingerprintInscreen mFingerprintInscreenDaemon;
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public boolean mIsBouncer;
    /* access modifiers changed from: private */
    public boolean mIsCircleShowing;
    /* access modifiers changed from: private */
    public boolean mIsDreaming;
    /* access modifiers changed from: private */
    public boolean mIsKeyguard;
    private boolean mIsRecognizingAnimEnabled;
    /* access modifiers changed from: private */
    public boolean mIsScreenTurnedOn;
    private boolean mIsShowing;
    private LockPatternUtils mLockPatternUtils;
    private KeyguardUpdateMonitorCallback mMonitorCallback = new KeyguardUpdateMonitorCallback() {
        public void onDreamingStateChanged(boolean z) {
            boolean unused = FODCircleView.this.mIsDreaming = z;
            FODCircleView.this.updateAlpha();
            if (z) {
                Timer unused2 = FODCircleView.this.mBurnInProtectionTimer = new Timer();
                FODCircleView.this.mBurnInProtectionTimer.schedule(new BurnInProtectionTask(), 0, 60000);
                if (FODCircleView.this.mShouldRemoveIconOnAOD) {
                    FODCircleView.this.resetFODIcon(false);
                }
            } else if (FODCircleView.this.mBurnInProtectionTimer != null) {
                FODCircleView.this.mBurnInProtectionTimer.cancel();
                FODCircleView.this.updatePosition();
            }
            if (FODCircleView.this.mShouldRemoveIconOnAOD && !z) {
                FODCircleView.this.resetFODIcon(true);
            }
        }

        public void onKeyguardVisibilityChanged(boolean z) {
            boolean unused = FODCircleView.this.mIsKeyguard = z;
            FODCircleView.this.updateSettings();
            if (FODCircleView.this.mFODAnimation != null) {
                FODCircleView.this.mFODAnimation.setAnimationKeyguard(FODCircleView.this.mIsKeyguard);
            }
        }

        public void onKeyguardBouncerChanged(boolean z) {
            boolean unused = FODCircleView.this.mIsBouncer = z;
            if (FODCircleView.this.mUpdateMonitor.isFingerprintDetectionRunning()) {
                FODCircleView fODCircleView = FODCircleView.this;
                KeyguardUpdateMonitor unused2 = fODCircleView.mUpdateMonitor;
                if (fODCircleView.isPinOrPattern(KeyguardUpdateMonitor.getCurrentUser()) || !z) {
                    FODCircleView.this.show();
                } else {
                    FODCircleView.this.hide();
                }
            } else {
                FODCircleView.this.hide();
            }
        }

        public void onScreenTurnedOff() {
            boolean unused = FODCircleView.this.mIsScreenTurnedOn = false;
            FODCircleView.this.hideCircle();
        }

        public void onScreenTurnedOn() {
            boolean unused = FODCircleView.this.mIsScreenTurnedOn = true;
        }

        public void onStartedWakingUp() {
            if (!FODCircleView.this.mIsScreenTurnedOn && FODCircleView.this.mUpdateMonitor.isFingerprintDetectionRunning()) {
                FODCircleView.this.show();
            }
        }

        public void onBiometricHelp(int i, String str, BiometricSourceType biometricSourceType) {
            if (biometricSourceType == BiometricSourceType.FINGERPRINT && i == -1) {
                FODCircleView.this.hideCircle();
                FODCircleView.this.mHandler.post(new Runnable() {
                    public final void run() {
                        FODCircleView.C06742.this.lambda$onBiometricHelp$0$FODCircleView$2();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onBiometricHelp$0$FODCircleView$2() {
            FODCircleView.this.mFODAnimation.hideFODanimation();
        }
    };
    private final int mNavigationBarSize;
    /* access modifiers changed from: private */
    public final Paint mPaintFingerprint = new Paint();
    private final WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
    private final int mPositionX;
    private final int mPositionY;
    private PowerManager mPowerManager;
    private final WindowManager.LayoutParams mPressedParams = new WindowManager.LayoutParams();
    private final ImageView mPressedView;
    private boolean mPressedViewDisplayed = false;
    private boolean mScreenOffFodEnabled;
    private boolean mScreenOffFodIconEnabled;
    private final boolean mShouldBoostBrightness;
    /* access modifiers changed from: private */
    public boolean mShouldRemoveIconOnAOD;
    /* access modifiers changed from: private */
    public final int mSize;
    private int mStatusbarHeight;
    /* access modifiers changed from: private */
    public KeyguardUpdateMonitor mUpdateMonitor;
    private final WindowManager mWindowManager;

    static /* synthetic */ int access$1920(FODCircleView fODCircleView, int i) {
        int i2 = fODCircleView.mDreamingOffsetY - i;
        fODCircleView.mDreamingOffsetY = i2;
        return i2;
    }

    public FODCircleView(Context context) {
        super(context);
        setScaleType(ImageView.ScaleType.CENTER);
        this.mDaemon = getFingerprintInScreenDaemon();
        IFingerprintInscreen iFingerprintInscreen = this.mDaemon;
        if (iFingerprintInscreen != null) {
            try {
                this.mShouldBoostBrightness = iFingerprintInscreen.shouldBoostBrightness();
                this.mPositionX = this.mDaemon.getPositionX();
                this.mPositionY = this.mDaemon.getPositionY();
                this.mSize = this.mDaemon.getSize();
                Resources resources = context.getResources();
                this.mColor = resources.getColor(C1774R$color.config_fodColor);
                this.mPaintFingerprint.setColor(this.mColor);
                this.mPaintFingerprint.setAntiAlias(true);
                this.mDimIcon = resources.getBoolean(C1773R$bool.config_fodIconDim);
                this.mWindowManager = (WindowManager) context.getSystemService(WindowManager.class);
                this.mNavigationBarSize = resources.getDimensionPixelSize(C1775R$dimen.navigation_bar_size);
                this.mDreamingMaxOffset = (int) (((float) this.mSize) * 0.1f);
                this.mHandler = new Handler(Looper.getMainLooper());
                WindowManager.LayoutParams layoutParams = this.mParams;
                int i = this.mSize;
                layoutParams.height = i;
                layoutParams.width = i;
                layoutParams.format = -3;
                layoutParams.packageName = "android";
                layoutParams.type = 2039;
                layoutParams.flags = 16777480;
                layoutParams.gravity = 51;
                this.mPressedParams.copyFrom(layoutParams);
                WindowManager.LayoutParams layoutParams2 = this.mPressedParams;
                layoutParams2.type = 2040;
                layoutParams2.flags = 2 | layoutParams2.flags;
                this.mParams.setTitle("Fingerprint on display");
                this.mPressedParams.setTitle("Fingerprint on display.touched");
                this.mPressedView = new ImageView(context) {
                    /* access modifiers changed from: protected */
                    public void onDraw(Canvas canvas) {
                        if (FODCircleView.this.mIsCircleShowing) {
                            canvas.drawCircle((float) (FODCircleView.this.mSize / 2), (float) (FODCircleView.this.mSize / 2), ((float) FODCircleView.this.mSize) / 2.0f, FODCircleView.this.mPaintFingerprint);
                        }
                        super.onDraw(canvas);
                    }
                };
                this.mPressedView.setImageResource(C1776R$drawable.fod_icon_pressed);
                this.mWindowManager.addView(this, this.mParams);
                this.mPowerManager = (PowerManager) context.getSystemService(PowerManager.class);
                this.mFODAnimation = new FODAnimation(context, this.mPositionX, this.mPositionY);
                this.mCustomSettingsObserver.observe();
                this.mCustomSettingsObserver.update();
                updateSettings();
                updatePosition();
                hide();
                this.mLockPatternUtils = new LockPatternUtils(this.mContext);
                this.mUpdateMonitor = KeyguardUpdateMonitor.getInstance(context);
                this.mUpdateMonitor.registerCallback(this.mMonitorCallback);
                updateCutoutFlags();
                ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this);
                this.mDefaultScreenBrightness = this.mPowerManager.getDefaultScreenBrightnessSetting();
            } catch (RemoteException unused) {
                throw new RuntimeException("Failed to retrieve FOD circle position or size");
            }
        } else {
            throw new RuntimeException("Unable to get IFingerprintInscreen");
        }
    }

    private class CustomSettingsObserver extends ContentObserver {
        CustomSettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            FODCircleView.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("screen_brightness"), false, this, -1);
        }

        public void onChange(boolean z, Uri uri) {
            if (uri.equals(Settings.System.getUriFor("screen_brightness"))) {
                FODCircleView.this.updateIconDim();
            }
        }

        public void update() {
            FODCircleView.this.updateIconDim();
        }
    }

    private int interpolate(int i, int i2, int i3, int i4, int i5) {
        int i6 = i5 - i4;
        int i7 = i - i2;
        int i8 = ((i6 * 2) * i7) / (i3 - i2);
        int i9 = i2 - i3;
        return i4 + (i8 / 2) + (i8 % 2) + ((i9 == 0 || i6 == 0) ? 0 : (((i7 * 2) * (i - i3)) / i6) / i9);
    }

    private int getDimAlpha() {
        int length = this.BRIGHTNESS_ALPHA_ARRAY.length;
        int i = 0;
        while (i < length && this.BRIGHTNESS_ALPHA_ARRAY[i][0] < this.mCurrentBrightness) {
            i++;
        }
        if (i == 0) {
            return this.BRIGHTNESS_ALPHA_ARRAY[0][1];
        }
        if (i == length) {
            return this.BRIGHTNESS_ALPHA_ARRAY[length - 1][1];
        }
        int[][] iArr = this.BRIGHTNESS_ALPHA_ARRAY;
        int i2 = i - 1;
        return interpolate(this.mCurrentBrightness, iArr[i2][0], iArr[i][0], iArr[i2][1], iArr[i][1]);
    }

    public void updateIconDim() {
        this.mCurrentBrightness = Settings.System.getIntForUser(this.mContext.getContentResolver(), "screen_brightness", this.mDefaultScreenBrightness, -2);
        if (this.mDimIcon) {
            this.mHandler.post(new Runnable() {
                public final void run() {
                    FODCircleView.this.lambda$updateIconDim$0$FODCircleView();
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateIconDim$0$FODCircleView() {
        setColorFilter(Color.argb(getDimAlpha(), 0, 0, 0), PorterDuff.Mode.SRC_ATOP);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            r0 = 0
            float r1 = r8.getAxisValue(r0)
            r2 = 1
            float r3 = r8.getAxisValue(r2)
            r4 = 0
            int r5 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r5 <= 0) goto L_0x0021
            int r5 = r7.mSize
            float r6 = (float) r5
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 >= 0) goto L_0x0021
            int r1 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r1 <= 0) goto L_0x0021
            float r1 = (float) r5
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 >= 0) goto L_0x0021
            r1 = r2
            goto L_0x0022
        L_0x0021:
            r1 = r0
        L_0x0022:
            int r3 = r8.getAction()
            if (r3 != 0) goto L_0x002e
            if (r1 == 0) goto L_0x002e
            r7.showCircle()
            return r2
        L_0x002e:
            int r1 = r8.getAction()
            if (r1 != r2) goto L_0x0038
            r7.hideCircle()
            return r2
        L_0x0038:
            int r7 = r8.getAction()
            r8 = 2
            if (r7 != r8) goto L_0x0040
            return r2
        L_0x0040:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.FODCircleView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateSettings();
        updatePosition();
    }

    public IFingerprintInscreen getFingerprintInScreenDaemon() {
        if (this.mFingerprintInscreenDaemon == null) {
            try {
                this.mFingerprintInscreenDaemon = IFingerprintInscreen.getService();
                if (this.mFingerprintInscreenDaemon != null) {
                    this.mFingerprintInscreenDaemon.setCallback(this.mFingerprintInscreenCallback);
                    this.mFingerprintInscreenDaemon.asBinder().linkToDeath(new IHwBinder.DeathRecipient() {
                        public final void serviceDied(long j) {
                            FODCircleView.this.lambda$getFingerprintInScreenDaemon$1$FODCircleView(j);
                        }
                    }, 0);
                }
            } catch (RemoteException | NoSuchElementException unused) {
            }
        }
        return this.mFingerprintInscreenDaemon;
    }

    public /* synthetic */ void lambda$getFingerprintInScreenDaemon$1$FODCircleView(long j) {
        this.mFingerprintInscreenDaemon = null;
    }

    /* renamed from: dispatchPress */
    public void lambda$showCircle$2$FODCircleView() {
        try {
            this.mDaemon.onPress();
        } catch (RemoteException unused) {
        }
    }

    /* renamed from: dispatchRelease */
    public void lambda$hideCircle$4$FODCircleView() {
        try {
            this.mDaemon.onRelease();
        } catch (RemoteException unused) {
        }
    }

    /* renamed from: dispatchShow */
    public void lambda$show$6$FODCircleView() {
        try {
            this.mDaemon.onShowFODView();
        } catch (RemoteException unused) {
        }
    }

    /* renamed from: dispatchHide */
    public void lambda$hide$7$FODCircleView() {
        try {
            this.mDaemon.onHideFODView();
        } catch (RemoteException unused) {
        }
    }

    public void showCircle() {
        this.mIsCircleShowing = true;
        setKeepScreenOn(true);
        setDim(true);
        ThreadUtils.postOnBackgroundThread(new Runnable() {
            public final void run() {
                FODCircleView.this.lambda$showCircle$2$FODCircleView();
            }
        });
        if (this.mIsRecognizingAnimEnabled) {
            this.mHandler.post(new Runnable() {
                public final void run() {
                    FODCircleView.this.lambda$showCircle$3$FODCircleView();
                }
            });
        }
        setImageDrawable((Drawable) null);
        updatePosition();
        invalidate();
    }

    public /* synthetic */ void lambda$showCircle$3$FODCircleView() {
        this.mFODAnimation.showFODanimation();
    }

    public void hideCircle() {
        this.mIsCircleShowing = false;
        setFODIcon();
        invalidate();
        ThreadUtils.postOnBackgroundThread(new Runnable() {
            public final void run() {
                FODCircleView.this.lambda$hideCircle$4$FODCircleView();
            }
        });
        setDim(false);
        this.mHandler.post(new Runnable() {
            public final void run() {
                FODCircleView.this.lambda$hideCircle$5$FODCircleView();
            }
        });
        setKeepScreenOn(false);
    }

    public /* synthetic */ void lambda$hideCircle$5$FODCircleView() {
        this.mFODAnimation.hideFODanimation();
    }

    /* access modifiers changed from: private */
    public void resetFODIcon(boolean z) {
        if (z) {
            setFODIcon();
        } else {
            setImageResource(0);
        }
    }

    private void setFODIcon() {
        if (!this.mIsDreaming || !this.mShouldRemoveIconOnAOD) {
            setImageResource(C1776R$drawable.fod_icon_default);
            setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }

    public void show() {
        if (!this.mIsShowing) {
            if (!this.mIsBouncer || isPinOrPattern(KeyguardUpdateMonitor.getCurrentUser())) {
                this.mIsShowing = true;
                updatePosition();
                ThreadUtils.postOnBackgroundThread(new Runnable() {
                    public final void run() {
                        FODCircleView.this.lambda$show$6$FODCircleView();
                    }
                });
                setVisibility(0);
            }
        }
    }

    public void hide() {
        this.mIsShowing = false;
        setVisibility(8);
        hideCircle();
        ThreadUtils.postOnBackgroundThread(new Runnable() {
            public final void run() {
                FODCircleView.this.lambda$hide$7$FODCircleView();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateAlpha() {
        setAlpha(this.mIsDreaming ? 0.5f : 1.0f);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updatePosition() {
        /*
            r5 = this;
            android.view.WindowManager r0 = r5.mWindowManager
            android.view.Display r0 = r0.getDefaultDisplay()
            android.graphics.Point r1 = new android.graphics.Point
            r1.<init>()
            r0.getRealSize(r1)
            int r0 = r0.getRotation()
            boolean r2 = r5.mCutoutMasked
            if (r2 == 0) goto L_0x0019
            int r2 = r5.mStatusbarHeight
            goto L_0x001a
        L_0x0019:
            r2 = 0
        L_0x001a:
            if (r0 == 0) goto L_0x005b
            r3 = 1
            if (r0 == r3) goto L_0x0056
            r3 = 2
            if (r0 == r3) goto L_0x004b
            r3 = 3
            if (r0 != r3) goto L_0x0034
            int r0 = r1.x
            int r1 = r5.mPositionY
            int r0 = r0 - r1
            int r1 = r5.mSize
            int r0 = r0 - r1
            int r1 = r5.mNavigationBarSize
            int r0 = r0 - r1
            int r0 = r0 - r2
            int r1 = r5.mPositionX
            goto L_0x0060
        L_0x0034:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown rotation: "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r5.<init>(r0)
            throw r5
        L_0x004b:
            int r0 = r5.mPositionX
            int r1 = r1.y
            int r3 = r5.mPositionY
            int r1 = r1 - r3
            int r3 = r5.mSize
            int r1 = r1 - r3
            goto L_0x005f
        L_0x0056:
            int r0 = r5.mPositionY
            int r1 = r5.mPositionX
            goto L_0x005f
        L_0x005b:
            int r0 = r5.mPositionX
            int r1 = r5.mPositionY
        L_0x005f:
            int r1 = r1 - r2
        L_0x0060:
            android.view.WindowManager$LayoutParams r3 = r5.mPressedParams
            android.view.WindowManager$LayoutParams r4 = r5.mParams
            r4.x = r0
            r3.x = r0
            r4.y = r1
            r3.y = r1
            boolean r0 = r5.mIsKeyguard
            if (r0 == 0) goto L_0x0079
            int r0 = r5.mPositionX
            r4.x = r0
            int r0 = r5.mPositionY
            int r0 = r0 - r2
            r4.y = r0
        L_0x0079:
            com.android.systemui.biometrics.FODAnimation r0 = r5.mFODAnimation
            if (r0 == 0) goto L_0x0084
            android.view.WindowManager$LayoutParams r1 = r5.mParams
            int r1 = r1.y
            r0.updateParams(r1)
        L_0x0084:
            boolean r0 = r5.mIsDreaming
            if (r0 == 0) goto L_0x0095
            boolean r0 = r5.mIsCircleShowing
            if (r0 != 0) goto L_0x0095
            android.view.WindowManager$LayoutParams r0 = r5.mParams
            int r1 = r0.y
            int r2 = r5.mDreamingOffsetY
            int r1 = r1 + r2
            r0.y = r1
        L_0x0095:
            android.view.WindowManager r0 = r5.mWindowManager
            android.view.WindowManager$LayoutParams r1 = r5.mParams
            r0.updateViewLayout(r5, r1)
            android.widget.ImageView r0 = r5.mPressedView
            android.view.ViewParent r0 = r0.getParent()
            if (r0 == 0) goto L_0x00ad
            android.view.WindowManager r0 = r5.mWindowManager
            android.widget.ImageView r1 = r5.mPressedView
            android.view.WindowManager$LayoutParams r5 = r5.mPressedParams
            r0.updateViewLayout(r1, r5)
        L_0x00ad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.FODCircleView.updatePosition():void");
    }

    private void setDim(boolean z) {
        int i = 0;
        if (z) {
            try {
                i = this.mDaemon.getDimAmount(this.mCurrentBrightness);
            } catch (RemoteException unused) {
            }
            if (this.mShouldBoostBrightness) {
                this.mPressedParams.screenBrightness = 1.0f;
            }
            WindowManager.LayoutParams layoutParams = this.mPressedParams;
            layoutParams.dimAmount = ((float) i) / 255.0f;
            if (!this.mPressedViewDisplayed && this.mIsShowing) {
                this.mPressedViewDisplayed = true;
                this.mWindowManager.addView(this.mPressedView, layoutParams);
            } else if (this.mPressedViewDisplayed) {
                this.mWindowManager.updateViewLayout(this.mPressedView, this.mPressedParams);
            }
        } else {
            WindowManager.LayoutParams layoutParams2 = this.mPressedParams;
            layoutParams2.screenBrightness = 0.0f;
            layoutParams2.dimAmount = 0.0f;
            if (this.mPressedViewDisplayed) {
                this.mPressedViewDisplayed = false;
                this.mWindowManager.removeViewImmediate(this.mPressedView);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isPinOrPattern(int i) {
        int activePasswordQuality = this.mLockPatternUtils.getActivePasswordQuality(i);
        return activePasswordQuality == 65536 || activePasswordQuality == 131072 || activePasswordQuality == 196608;
    }

    private class BurnInProtectionTask extends TimerTask {
        private BurnInProtectionTask() {
        }

        public void run() {
            FODCircleView fODCircleView = FODCircleView.this;
            int unused = fODCircleView.mDreamingOffsetY = (int) ((((System.currentTimeMillis() / 1000) / 60) + ((long) (fODCircleView.mDreamingMaxOffset / 3))) % ((long) (FODCircleView.this.mDreamingMaxOffset * 2)));
            FODCircleView fODCircleView2 = FODCircleView.this;
            FODCircleView.access$1920(fODCircleView2, fODCircleView2.mDreamingMaxOffset);
            FODCircleView.this.mHandler.post(new Runnable() {
                public final void run() {
                    FODCircleView.BurnInProtectionTask.this.lambda$run$0$FODCircleView$BurnInProtectionTask();
                }
            });
        }

        public /* synthetic */ void lambda$run$0$FODCircleView$BurnInProtectionTask() {
            FODCircleView.this.updatePosition();
        }
    }

    public void onOverlayChanged() {
        updateCutoutFlags();
    }

    private void updateCutoutFlags() {
        this.mStatusbarHeight = getContext().getResources().getDimensionPixelSize(17105436);
        boolean z = getContext().getResources().getBoolean(17891505);
        if (this.mCutoutMasked != z) {
            this.mCutoutMasked = z;
            updatePosition();
        }
    }

    /* access modifiers changed from: private */
    public void updateSettings() {
        boolean z = true;
        this.mIsRecognizingAnimEnabled = Settings.System.getInt(this.mContext.getContentResolver(), "fod_recognizing_animation", 1) != 0;
        this.mScreenOffFodEnabled = Settings.System.getInt(this.mContext.getContentResolver(), "screen_off_fod", 0) != 0;
        this.mScreenOffFodIconEnabled = Settings.System.getInt(this.mContext.getContentResolver(), "screen_off_fod_icon", 1) != 0;
        if (!this.mScreenOffFodEnabled || this.mScreenOffFodIconEnabled) {
            z = false;
        }
        this.mShouldRemoveIconOnAOD = z;
    }
}
