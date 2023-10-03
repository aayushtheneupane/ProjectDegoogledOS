package com.android.systemui.settings;

import android.animation.ValueAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.service.vr.IVrManager;
import android.service.vr.IVrStateCallbacks;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.display.BrightnessUtils;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.Dependency;
import com.android.systemui.SystemUIFactory;
import com.android.systemui.settings.ToggleSlider;
import java.util.ArrayList;
import java.util.Iterator;

public class BrightnessController implements ToggleSlider.Listener {
    /* access modifiers changed from: private */
    public volatile boolean mAutomatic;
    /* access modifiers changed from: private */
    public final boolean mAutomaticAvailable;
    /* access modifiers changed from: private */
    public final Handler mBackgroundHandler;
    /* access modifiers changed from: private */
    public final BrightnessObserver mBrightnessObserver;
    /* access modifiers changed from: private */
    public ArrayList<BrightnessStateChangeCallback> mChangeCallbacks = new ArrayList<>();
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final ToggleSlider mControl;
    private boolean mControlValueInitialized;
    /* access modifiers changed from: private */
    public final int mDefaultBacklight;
    /* access modifiers changed from: private */
    public final int mDefaultBacklightForVr;
    private final DisplayManager mDisplayManager;
    /* access modifiers changed from: private */
    public boolean mExternalChange;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            boolean z = true;
            boolean unused = BrightnessController.this.mExternalChange = true;
            try {
                int i = message.what;
                if (i == 0) {
                    BrightnessController.this.updateIcon(BrightnessController.this.mAutomatic);
                } else if (i == 1) {
                    BrightnessController brightnessController = BrightnessController.this;
                    int i2 = message.arg1;
                    if (message.arg2 == 0) {
                        z = false;
                    }
                    brightnessController.updateSlider(i2, z);
                } else if (i == 2) {
                    ToggleSlider access$1600 = BrightnessController.this.mControl;
                    if (message.arg1 == 0) {
                        z = false;
                    }
                    access$1600.setChecked(z);
                } else if (i == 3) {
                    BrightnessController.this.mControl.setOnChangedListener(BrightnessController.this);
                } else if (i == 4) {
                    BrightnessController.this.mControl.setOnChangedListener((ToggleSlider.Listener) null);
                } else if (i != 5) {
                    super.handleMessage(message);
                } else {
                    BrightnessController brightnessController2 = BrightnessController.this;
                    if (message.arg1 == 0) {
                        z = false;
                    }
                    brightnessController2.updateVrMode(z);
                }
            } finally {
                boolean unused2 = BrightnessController.this.mExternalChange = false;
            }
        }
    };
    private final ImageView mIcon;
    /* access modifiers changed from: private */
    public volatile boolean mIsVrModeEnabled;
    private boolean mListening;
    private final int mMaximumBacklight;
    private final int mMaximumBacklightForVr;
    private final int mMinimumBacklight;
    private final int mMinimumBacklightForVr;
    private ValueAnimator mSliderAnimator;
    private final Runnable mStartListeningRunnable = new Runnable() {
        public void run() {
            BrightnessController.this.mBrightnessObserver.startObserving();
            BrightnessController.this.mUserTracker.startTracking();
            BrightnessController.this.mUpdateModeRunnable.run();
            BrightnessController.this.mUpdateSliderRunnable.run();
            BrightnessController.this.mHandler.sendEmptyMessage(3);
        }
    };
    private final Runnable mStopListeningRunnable = new Runnable() {
        public void run() {
            BrightnessController.this.mBrightnessObserver.stopObserving();
            BrightnessController.this.mUserTracker.stopTracking();
            BrightnessController.this.mHandler.sendEmptyMessage(4);
        }
    };
    /* access modifiers changed from: private */
    public final Runnable mUpdateModeRunnable = new Runnable() {
        public void run() {
            if (BrightnessController.this.mAutomaticAvailable) {
                boolean unused = BrightnessController.this.mAutomatic = Settings.System.getIntForUser(BrightnessController.this.mContext.getContentResolver(), "screen_brightness_mode", 0, -2) != 0;
                BrightnessController.this.mHandler.obtainMessage(0, Integer.valueOf(BrightnessController.this.mAutomatic ? 1 : 0)).sendToTarget();
                return;
            }
            BrightnessController.this.mHandler.obtainMessage(2, 0).sendToTarget();
            BrightnessController.this.mHandler.obtainMessage(0, 0).sendToTarget();
        }
    };
    /* access modifiers changed from: private */
    public final Runnable mUpdateSliderRunnable = new Runnable() {
        public void run() {
            int i;
            boolean access$1000 = BrightnessController.this.mIsVrModeEnabled;
            if (access$1000) {
                i = Settings.System.getIntForUser(BrightnessController.this.mContext.getContentResolver(), "screen_brightness_for_vr", BrightnessController.this.mDefaultBacklightForVr, -2);
            } else {
                i = Settings.System.getIntForUser(BrightnessController.this.mContext.getContentResolver(), "screen_brightness", BrightnessController.this.mDefaultBacklight, -2);
            }
            BrightnessController.this.mHandler.obtainMessage(1, i, access$1000 ? 1 : 0).sendToTarget();
        }
    };
    /* access modifiers changed from: private */
    public final CurrentUserTracker mUserTracker;
    private final IVrManager mVrManager;
    private final IVrStateCallbacks mVrStateCallbacks = new IVrStateCallbacks.Stub() {
        public void onVrStateChanged(boolean z) {
            BrightnessController.this.mHandler.obtainMessage(5, z ? 1 : 0, 0).sendToTarget();
        }
    };

    public interface BrightnessStateChangeCallback {
        void onBrightnessLevelChanged();
    }

    public void onInit(ToggleSlider toggleSlider) {
    }

    private class BrightnessObserver extends ContentObserver {
        private final Uri BRIGHTNESS_FOR_VR_URI = Settings.System.getUriFor("screen_brightness_for_vr");
        private final Uri BRIGHTNESS_MODE_URI = Settings.System.getUriFor("screen_brightness_mode");
        private final Uri BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");

        public BrightnessObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z) {
            onChange(z, (Uri) null);
        }

        public void onChange(boolean z, Uri uri) {
            if (!z) {
                if (this.BRIGHTNESS_MODE_URI.equals(uri)) {
                    BrightnessController.this.mBackgroundHandler.post(BrightnessController.this.mUpdateModeRunnable);
                    BrightnessController.this.mBackgroundHandler.post(BrightnessController.this.mUpdateSliderRunnable);
                } else if (this.BRIGHTNESS_URI.equals(uri)) {
                    BrightnessController.this.mBackgroundHandler.post(BrightnessController.this.mUpdateSliderRunnable);
                } else if (this.BRIGHTNESS_FOR_VR_URI.equals(uri)) {
                    BrightnessController.this.mBackgroundHandler.post(BrightnessController.this.mUpdateSliderRunnable);
                } else {
                    BrightnessController.this.mBackgroundHandler.post(BrightnessController.this.mUpdateModeRunnable);
                    BrightnessController.this.mBackgroundHandler.post(BrightnessController.this.mUpdateSliderRunnable);
                }
                Iterator it = BrightnessController.this.mChangeCallbacks.iterator();
                while (it.hasNext()) {
                    ((BrightnessStateChangeCallback) it.next()).onBrightnessLevelChanged();
                }
            }
        }

        public void startObserving() {
            ContentResolver contentResolver = BrightnessController.this.mContext.getContentResolver();
            contentResolver.unregisterContentObserver(this);
            contentResolver.registerContentObserver(this.BRIGHTNESS_MODE_URI, false, this, -1);
            contentResolver.registerContentObserver(this.BRIGHTNESS_URI, false, this, -1);
            contentResolver.registerContentObserver(this.BRIGHTNESS_FOR_VR_URI, false, this, -1);
        }

        public void stopObserving() {
            BrightnessController.this.mContext.getContentResolver().unregisterContentObserver(this);
        }
    }

    public BrightnessController(Context context, ImageView imageView, ToggleSlider toggleSlider) {
        this.mContext = context;
        this.mIcon = imageView;
        this.mControl = toggleSlider;
        this.mControl.setMax(1023);
        Dependency.initDependencies(SystemUIFactory.getInstance().getRootComponent());
        this.mBackgroundHandler = new Handler((Looper) Dependency.get(Dependency.BG_LOOPER));
        this.mUserTracker = new CurrentUserTracker(this.mContext) {
            public void onUserSwitched(int i) {
                BrightnessController.this.mBackgroundHandler.post(BrightnessController.this.mUpdateModeRunnable);
                BrightnessController.this.mBackgroundHandler.post(BrightnessController.this.mUpdateSliderRunnable);
            }
        };
        this.mBrightnessObserver = new BrightnessObserver(this.mHandler);
        PowerManager powerManager = (PowerManager) context.getSystemService(PowerManager.class);
        this.mMinimumBacklight = powerManager.getMinimumScreenBrightnessSetting();
        this.mMaximumBacklight = powerManager.getMaximumScreenBrightnessSetting();
        this.mDefaultBacklight = powerManager.getDefaultScreenBrightnessSetting();
        this.mMinimumBacklightForVr = powerManager.getMinimumScreenBrightnessForVrSetting();
        this.mMaximumBacklightForVr = powerManager.getMaximumScreenBrightnessForVrSetting();
        this.mDefaultBacklightForVr = powerManager.getDefaultScreenBrightnessForVrSetting();
        this.mAutomaticAvailable = context.getResources().getBoolean(17891369);
        this.mDisplayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
        this.mVrManager = IVrManager.Stub.asInterface(ServiceManager.getService("vrmanager"));
        ImageView imageView2 = this.mIcon;
        if (imageView2 != null && this.mAutomaticAvailable) {
            imageView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Settings.System.putIntForUser(BrightnessController.this.mContext.getContentResolver(), "screen_brightness_mode", BrightnessController.this.mAutomatic ^ true ? 1 : 0, -2);
                }
            });
        }
    }

    public void registerCallbacks() {
        if (!this.mListening) {
            IVrManager iVrManager = this.mVrManager;
            if (iVrManager != null) {
                try {
                    iVrManager.registerListener(this.mVrStateCallbacks);
                    this.mIsVrModeEnabled = this.mVrManager.getVrModeState();
                } catch (RemoteException e) {
                    Log.e("StatusBar.BrightnessController", "Failed to register VR mode state listener: ", e);
                }
            }
            this.mBackgroundHandler.post(this.mStartListeningRunnable);
            this.mListening = true;
        }
    }

    public void unregisterCallbacks() {
        if (this.mListening) {
            IVrManager iVrManager = this.mVrManager;
            if (iVrManager != null) {
                try {
                    iVrManager.unregisterListener(this.mVrStateCallbacks);
                } catch (RemoteException e) {
                    Log.e("StatusBar.BrightnessController", "Failed to unregister VR mode state listener: ", e);
                }
            }
            this.mBackgroundHandler.post(this.mStopListeningRunnable);
            this.mListening = false;
            this.mControlValueInitialized = false;
        }
    }

    public void onChanged(ToggleSlider toggleSlider, boolean z, boolean z2, int i, boolean z3) {
        if (!this.mExternalChange) {
            ValueAnimator valueAnimator = this.mSliderAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            setBrightness(z, z3, i);
        }
    }

    public void setBrightnessFromSliderButtons(int i) {
        setBrightness(false, true, i);
    }

    private void setBrightness(boolean z, boolean z2, int i) {
        final String str;
        int i2;
        int i3;
        int i4;
        if (this.mIsVrModeEnabled) {
            i4 = 498;
            i3 = this.mMinimumBacklightForVr;
            i2 = this.mMaximumBacklightForVr;
            str = "screen_brightness_for_vr";
        } else {
            i4 = this.mAutomatic ? 219 : 218;
            i3 = this.mMinimumBacklight;
            i2 = this.mMaximumBacklight;
            str = "screen_brightness";
        }
        final int convertGammaToLinear = BrightnessUtils.convertGammaToLinear(i, i3, i2);
        if (z2) {
            MetricsLogger.action(this.mContext, i4, convertGammaToLinear);
        }
        setBrightness(convertGammaToLinear);
        if (!z) {
            AsyncTask.execute(new Runnable() {
                public void run() {
                    Settings.System.putIntForUser(BrightnessController.this.mContext.getContentResolver(), str, convertGammaToLinear, -2);
                }
            });
        }
        Iterator<BrightnessStateChangeCallback> it = this.mChangeCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onBrightnessLevelChanged();
        }
    }

    public void checkRestrictionAndSetEnabled() {
        this.mBackgroundHandler.post(new Runnable() {
            public void run() {
                ((ToggleSliderView) BrightnessController.this.mControl).setEnforcedAdmin(RestrictedLockUtilsInternal.checkIfRestrictionEnforced(BrightnessController.this.mContext, "no_config_brightness", BrightnessController.this.mUserTracker.getCurrentUserId()));
            }
        });
    }

    private void setBrightness(int i) {
        this.mDisplayManager.setTemporaryBrightness(i);
    }

    /* access modifiers changed from: private */
    public void updateIcon(boolean z) {
        int i;
        ImageView imageView = this.mIcon;
        if (imageView != null) {
            if (z) {
                i = C1776R$drawable.ic_qs_brightness_auto_on;
            } else {
                i = C1776R$drawable.ic_qs_brightness_auto_off;
            }
            imageView.setImageResource(i);
        }
    }

    /* access modifiers changed from: private */
    public void updateVrMode(boolean z) {
        if (this.mIsVrModeEnabled != z) {
            this.mIsVrModeEnabled = z;
            this.mBackgroundHandler.post(this.mUpdateSliderRunnable);
        }
    }

    /* access modifiers changed from: private */
    public void updateSlider(int i, boolean z) {
        int i2;
        int i3;
        if (z) {
            i2 = this.mMinimumBacklightForVr;
            i3 = this.mMaximumBacklightForVr;
        } else {
            i2 = this.mMinimumBacklight;
            i3 = this.mMaximumBacklight;
        }
        if (i != BrightnessUtils.convertGammaToLinear(this.mControl.getValue(), i2, i3)) {
            animateSliderTo(BrightnessUtils.convertLinearToGamma(i, i2, i3));
        }
    }

    private void animateSliderTo(int i) {
        if (!this.mControlValueInitialized) {
            this.mControl.setValue(i);
            this.mControlValueInitialized = true;
        }
        ValueAnimator valueAnimator = this.mSliderAnimator;
        if (valueAnimator != null && valueAnimator.isStarted()) {
            this.mSliderAnimator.cancel();
        }
        this.mSliderAnimator = ValueAnimator.ofInt(new int[]{this.mControl.getValue(), i});
        this.mSliderAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                BrightnessController.this.lambda$animateSliderTo$0$BrightnessController(valueAnimator);
            }
        });
        this.mSliderAnimator.setDuration((long) ((Math.abs(this.mControl.getValue() - i) * 3000) / 1023));
        this.mSliderAnimator.start();
    }

    public /* synthetic */ void lambda$animateSliderTo$0$BrightnessController(ValueAnimator valueAnimator) {
        this.mExternalChange = true;
        this.mControl.setValue(((Integer) valueAnimator.getAnimatedValue()).intValue());
        this.mExternalChange = false;
    }
}
