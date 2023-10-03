package com.android.systemui.navigation.pulse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.IAudioService;
import android.media.MediaMetadata;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.navigation.pulse.VisualizerStreamHandler;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.NavigationBarFrame;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.PulseController;
import java.util.ArrayList;
import java.util.List;

public class PulseControllerImpl implements PulseController, CommandQueue.Callbacks, ConfigurationController.ConfigurationListener {
    private static final String TAG = "PulseControllerImpl";
    private boolean mAttached;
    private AudioManager mAudioManager;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int intExtra;
            boolean access$300;
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                boolean unused = PulseControllerImpl.this.mScreenOn = false;
                PulseControllerImpl.this.doLinkage();
            } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                boolean unused2 = PulseControllerImpl.this.mScreenOn = true;
                PulseControllerImpl.this.doLinkage();
            } else if ("android.os.action.POWER_SAVE_MODE_CHANGING".equals(intent.getAction())) {
                boolean unused3 = PulseControllerImpl.this.mPowerSaveModeEnabled = intent.getBooleanExtra("mode", false);
                PulseControllerImpl.this.doLinkage();
            } else if (("android.media.STREAM_MUTE_CHANGED_ACTION".equals(intent.getAction()) || "android.media.VOLUME_CHANGED_ACTION".equals(intent.getAction())) && (intExtra = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1)) == 3 && PulseControllerImpl.this.mMusicStreamMuted != (access$300 = PulseControllerImpl.this.isMusicMuted(intExtra))) {
                boolean unused4 = PulseControllerImpl.this.mMusicStreamMuted = access$300;
                PulseControllerImpl.this.doLinkage();
            }
        }
    };
    private ColorController mColorController;
    /* access modifiers changed from: private */
    public Context mContext;
    private boolean mDozing;
    private Handler mHandler;
    private boolean mIsMediaPlaying;
    private boolean mKeyguardGoingAway;
    private boolean mKeyguardShowing;
    private boolean mLeftInLandscape;
    private boolean mLinked;
    /* access modifiers changed from: private */
    public boolean mLsPulseEnabled;
    /* access modifiers changed from: private */
    public boolean mMusicStreamMuted;
    /* access modifiers changed from: private */
    public boolean mNavPulseEnabled;
    /* access modifiers changed from: private */
    public boolean mPowerSaveModeEnabled;
    /* access modifiers changed from: private */
    public int mPulseStyle;
    private PulseView mPulseView;
    /* access modifiers changed from: private */
    public Renderer mRenderer;
    /* access modifiers changed from: private */
    public boolean mScreenOn = true;
    private boolean mScreenPinningEnabled;
    private SettingsObserver mSettingsObserver;
    private final List<PulseController.PulseStateListener> mStateListeners = new ArrayList();
    private StatusBar mStatusbar;
    private VisualizerStreamHandler mStreamHandler;
    private final VisualizerStreamHandler.Listener mStreamListener = new VisualizerStreamHandler.Listener() {
        public void onStreamAnalyzed(boolean z) {
            if (PulseControllerImpl.this.mRenderer != null) {
                PulseControllerImpl.this.mRenderer.onStreamAnalyzed(z);
            }
            if (z) {
                PulseControllerImpl.this.notifyStateListeners(true);
                PulseControllerImpl.this.turnOnPulse();
                return;
            }
            PulseControllerImpl.this.doSilentUnlinkVisualizer();
        }

        public void onFFTUpdate(byte[] bArr) {
            if (PulseControllerImpl.this.mRenderer != null) {
                PulseControllerImpl.this.mRenderer.onFFTUpdate(bArr);
            }
        }
    };

    private void log(String str) {
    }

    private class SettingsObserver extends ContentObserver {
        public SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void register() {
            PulseControllerImpl.this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("pulse_enabled"), false, this, -1);
            PulseControllerImpl.this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("pulse_location"), false, this, -1);
            PulseControllerImpl.this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("pulse_render_style"), false, this, -1);
        }

        public void onChange(boolean z, Uri uri) {
            if (uri.equals(Settings.Secure.getUriFor("pulse_enabled")) || uri.equals(Settings.Secure.getUriFor("pulse_location"))) {
                updateEnabled();
                PulseControllerImpl.this.updatePulseVisibility();
            } else if (uri.equals(Settings.Secure.getUriFor("pulse_render_style"))) {
                updateRenderMode();
                PulseControllerImpl.this.loadRenderer();
            }
        }

        /* access modifiers changed from: package-private */
        public void updateSettings() {
            updateEnabled();
            updateRenderMode();
        }

        /* access modifiers changed from: package-private */
        public void updateEnabled() {
            boolean z = false;
            boolean z2 = Settings.Secure.getIntForUser(PulseControllerImpl.this.mContext.getContentResolver(), "pulse_enabled", 0, -2) == 1;
            int intForUser = Settings.Secure.getIntForUser(PulseControllerImpl.this.mContext.getContentResolver(), "pulse_location", 0, -2);
            boolean unused = PulseControllerImpl.this.mNavPulseEnabled = z2 && intForUser != 0;
            PulseControllerImpl pulseControllerImpl = PulseControllerImpl.this;
            if (z2 && intForUser != 1) {
                z = true;
            }
            boolean unused2 = pulseControllerImpl.mLsPulseEnabled = z;
        }

        /* access modifiers changed from: package-private */
        public void updateRenderMode() {
            PulseControllerImpl pulseControllerImpl = PulseControllerImpl.this;
            int unused = pulseControllerImpl.mPulseStyle = Settings.Secure.getIntForUser(pulseControllerImpl.mContext.getContentResolver(), "pulse_render_style", 1, -2);
        }
    }

    public void notifyKeyguardGoingAway() {
        if (this.mLsPulseEnabled) {
            this.mKeyguardGoingAway = true;
            updatePulseVisibility();
            this.mKeyguardGoingAway = false;
        }
    }

    /* access modifiers changed from: private */
    public void updatePulseVisibility() {
        StatusBar statusBar = this.mStatusbar;
        if (statusBar != null) {
            NavigationBarFrame navbarFrame = statusBar.getNavigationBarView() != null ? this.mStatusbar.getNavigationBarView().getNavbarFrame() : null;
            VisualizerView lsVisualizer = this.mStatusbar.getLsVisualizer();
            boolean z = true;
            boolean z2 = lsVisualizer != null && lsVisualizer.isAttached() && this.mLsPulseEnabled && this.mKeyguardShowing && !this.mDozing;
            if (navbarFrame == null || !navbarFrame.isAttached() || !this.mNavPulseEnabled || this.mKeyguardShowing) {
                z = false;
            }
            if (this.mKeyguardGoingAway) {
                detachPulseFrom(lsVisualizer, z);
                return;
            }
            if (!z) {
                detachPulseFrom(navbarFrame, z2);
            }
            if (!z2) {
                detachPulseFrom(lsVisualizer, z);
            }
            if (z2) {
                attachPulseTo(lsVisualizer);
            } else if (z) {
                attachPulseTo(navbarFrame);
            }
        }
    }

    public void setDozing(boolean z) {
        if (this.mDozing != z) {
            this.mDozing = z;
            updatePulseVisibility();
        }
    }

    public void setKeyguardShowing(boolean z) {
        if (z != this.mKeyguardShowing) {
            this.mKeyguardShowing = z;
            Renderer renderer = this.mRenderer;
            if (renderer != null) {
                renderer.setKeyguardShowing(z);
            }
            updatePulseVisibility();
        }
    }

    public PulseControllerImpl(Context context, Handler handler) {
        this.mContext = context;
        this.mStatusbar = (StatusBar) SysUiServiceProvider.getComponent(context, StatusBar.class);
        this.mHandler = handler;
        this.mSettingsObserver = new SettingsObserver(handler);
        this.mSettingsObserver.updateSettings();
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mMusicStreamMuted = isMusicMuted(3);
        this.mPowerSaveModeEnabled = ((PowerManager) this.mContext.getSystemService("power")).isPowerSaveMode();
        this.mSettingsObserver.register();
        this.mStreamHandler = new VisualizerStreamHandler(this.mContext, this, this.mStreamListener);
        this.mPulseView = new PulseView(context, this);
        this.mColorController = new ColorController(this.mContext, this.mHandler);
        loadRenderer();
        ((CommandQueue) SysUiServiceProvider.getComponent(context, CommandQueue.class)).addCallback((CommandQueue.Callbacks) this);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGING");
        intentFilter.addAction("android.media.STREAM_MUTE_CHANGED_ACTION");
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
        context.registerReceiverAsUser(this.mBroadcastReceiver, UserHandle.ALL, intentFilter, (String) null, (Handler) null);
    }

    public void attachPulseTo(FrameLayout frameLayout) {
        if (frameLayout != null && frameLayout.findViewWithTag("PulseView") == null) {
            frameLayout.addView(this.mPulseView);
            this.mAttached = true;
            log("attachPulseTo() ");
            doLinkage();
        }
    }

    public void detachPulseFrom(FrameLayout frameLayout, boolean z) {
        View findViewWithTag;
        if (frameLayout != null && (findViewWithTag = frameLayout.findViewWithTag("PulseView")) != null) {
            frameLayout.removeView(findViewWithTag);
            this.mAttached = z;
            log("detachPulseFrom() ");
            doLinkage();
        }
    }

    /* access modifiers changed from: private */
    public void notifyStateListeners(boolean z) {
        for (PulseController.PulseStateListener next : this.mStateListeners) {
            if (next != null) {
                if (z) {
                    next.onPulseStateChanged(true);
                } else {
                    next.onPulseStateChanged(false);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void loadRenderer() {
        boolean shouldDrawPulse = shouldDrawPulse();
        if (shouldDrawPulse) {
            this.mStreamHandler.pause();
        }
        Renderer renderer = this.mRenderer;
        if (renderer != null) {
            renderer.destroy();
            this.mRenderer = null;
        }
        this.mRenderer = getRenderer();
        this.mColorController.setRenderer(this.mRenderer);
        this.mRenderer.setLeftInLandscape(this.mLeftInLandscape);
        if (shouldDrawPulse) {
            this.mRenderer.onStreamAnalyzed(true);
            this.mStreamHandler.resume();
        }
    }

    public void screenPinningStateChanged(boolean z) {
        this.mScreenPinningEnabled = z;
        doLinkage();
    }

    public void leftInLandscapeChanged(boolean z) {
        if (this.mLeftInLandscape != z) {
            this.mLeftInLandscape = z;
            Renderer renderer = this.mRenderer;
            if (renderer != null) {
                renderer.setLeftInLandscape(z);
            }
        }
    }

    public boolean shouldDrawPulse() {
        return this.mLinked && this.mStreamHandler.isValidStream() && this.mRenderer != null;
    }

    public void onDraw(Canvas canvas) {
        if (shouldDrawPulse()) {
            this.mRenderer.draw(canvas);
        }
    }

    /* access modifiers changed from: private */
    public void turnOnPulse() {
        if (shouldDrawPulse()) {
            this.mStreamHandler.resume();
        }
    }

    /* access modifiers changed from: package-private */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        Renderer renderer = this.mRenderer;
        if (renderer != null) {
            renderer.onSizeChanged(i, i2, i3, i4);
        }
    }

    private Renderer getRenderer() {
        int i = this.mPulseStyle;
        if (i == 0) {
            return new FadingBlockRenderer(this.mContext, this.mHandler, this.mPulseView, this, this.mColorController);
        } else if (i != 1) {
            return new FadingBlockRenderer(this.mContext, this.mHandler, this.mPulseView, this, this.mColorController);
        } else {
            return new SolidLineRenderer(this.mContext, this.mHandler, this.mPulseView, this, this.mColorController);
        }
    }

    /* access modifiers changed from: private */
    public boolean isMusicMuted(int i) {
        return i == 3 && (this.mAudioManager.isStreamMute(i) || this.mAudioManager.getStreamVolume(i) == 0);
    }

    private static void setVisualizerLocked(boolean z) {
        try {
            IAudioService.Stub.asInterface(ServiceManager.getService("audio")).setVisualizerLocked(z);
        } catch (RemoteException unused) {
            Log.e(TAG, "Error setting visualizer lock");
        }
    }

    private boolean isUnlinkRequired() {
        return !this.mScreenOn || this.mPowerSaveModeEnabled || this.mMusicStreamMuted || this.mScreenPinningEnabled || !this.mAttached;
    }

    private boolean isAbleToLink() {
        return this.mScreenOn && this.mIsMediaPlaying && !this.mPowerSaveModeEnabled && !this.mMusicStreamMuted && !this.mScreenPinningEnabled && this.mAttached;
    }

    private void doUnlinkVisualizer() {
        VisualizerStreamHandler visualizerStreamHandler = this.mStreamHandler;
        if (visualizerStreamHandler != null && this.mLinked) {
            visualizerStreamHandler.unlink();
            setVisualizerLocked(false);
            this.mLinked = false;
            Renderer renderer = this.mRenderer;
            if (renderer != null) {
                renderer.onVisualizerLinkChanged(false);
            }
            this.mPulseView.postInvalidate();
            notifyStateListeners(false);
        }
    }

    /* access modifiers changed from: private */
    public void doLinkage() {
        if (isUnlinkRequired()) {
            if (this.mLinked) {
                doUnlinkVisualizer();
            }
        } else if (isAbleToLink()) {
            doLinkVisualizer();
        } else if (this.mLinked) {
            doUnlinkVisualizer();
        }
    }

    /* access modifiers changed from: private */
    public void doSilentUnlinkVisualizer() {
        VisualizerStreamHandler visualizerStreamHandler = this.mStreamHandler;
        if (visualizerStreamHandler != null && this.mLinked) {
            visualizerStreamHandler.unlink();
            setVisualizerLocked(false);
            this.mLinked = false;
        }
    }

    private void doLinkVisualizer() {
        if (this.mStreamHandler != null && !this.mLinked) {
            setVisualizerLocked(true);
            this.mStreamHandler.link();
            this.mLinked = true;
            Renderer renderer = this.mRenderer;
            if (renderer != null) {
                renderer.onVisualizerLinkChanged(true);
            }
        }
    }

    public void onMetadataOrStateChanged(MediaMetadata mediaMetadata, int i) {
        boolean z = i == 3;
        if (this.mIsMediaPlaying != z) {
            this.mIsMediaPlaying = z;
            doLinkage();
        }
    }

    public void setMediaNotificationColor(boolean z, int i) {
        this.mColorController.setMediaNotificationColor(z, i);
    }

    public String toString() {
        return TAG + " " + getState();
    }

    private String getState() {
        return "isAbleToLink() = " + isAbleToLink() + " shouldDrawPulse() = " + shouldDrawPulse() + " mScreenOn = " + this.mScreenOn + " mIsMediaPlaying = " + this.mIsMediaPlaying + " mLinked = " + this.mLinked + " mPowerSaveModeEnabled = " + this.mPowerSaveModeEnabled + " mMusicStreamMuted = " + this.mMusicStreamMuted + " mScreenPinningEnabled = " + this.mScreenPinningEnabled + " mAttached = " + this.mAttached + " mStreamHandler.isValidStream() = " + this.mStreamHandler.isValidStream() + " ";
    }
}
