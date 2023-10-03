package com.android.systemui.statusbar.phone;

import android.graphics.Color;
import android.os.Trace;
import com.android.systemui.statusbar.ScrimView;

public enum ScrimState {
    UNINITIALIZED(-1),
    KEYGUARD(0) {
        public void prepare(ScrimState scrimState) {
            this.mBlankScreen = false;
            if (scrimState == ScrimState.AOD) {
                this.mAnimationDuration = 500;
                if (this.mDisplayRequiresBlanking) {
                    this.mBlankScreen = true;
                }
            } else if (scrimState == ScrimState.KEYGUARD) {
                this.mAnimationDuration = 500;
            } else {
                this.mAnimationDuration = 220;
            }
            this.mCurrentInFrontTint = -16777216;
            this.mCurrentBehindTint = -16777216;
            this.mCurrentBehindAlpha = this.mScrimBehindAlphaKeyguard;
            this.mCurrentInFrontAlpha = 0.0f;
        }
    },
    BOUNCER(1) {
        public void prepare(ScrimState scrimState) {
            this.mCurrentBehindAlpha = 0.7f;
            this.mCurrentInFrontAlpha = 0.0f;
        }
    },
    BOUNCER_SCRIMMED(2) {
        public void prepare(ScrimState scrimState) {
            this.mCurrentBehindAlpha = 0.0f;
            this.mCurrentInFrontAlpha = 0.7f;
        }
    },
    BRIGHTNESS_MIRROR(3) {
        public void prepare(ScrimState scrimState) {
            this.mCurrentBehindAlpha = 0.0f;
            this.mCurrentInFrontAlpha = 0.0f;
        }
    },
    AOD(4) {
        public boolean isLowPowerState() {
            return true;
        }

        public void prepare(ScrimState scrimState) {
            boolean alwaysOn = this.mDozeParameters.getAlwaysOn();
            this.mBlankScreen = this.mDisplayRequiresBlanking;
            this.mCurrentInFrontAlpha = alwaysOn ? this.mAodFrontScrimAlpha : 1.0f;
            this.mCurrentInFrontTint = -16777216;
            this.mCurrentBehindTint = -16777216;
            this.mAnimationDuration = 1000;
            this.mAnimateChange = this.mDozeParameters.shouldControlScreenOff();
        }

        public float getBehindAlpha() {
            return (!this.mWallpaperSupportsAmbientMode || this.mHasBackdrop) ? 1.0f : 0.0f;
        }
    },
    PULSING(5) {
        public void prepare(ScrimState scrimState) {
            this.mCurrentInFrontAlpha = 0.0f;
            this.mCurrentBehindTint = -16777216;
            this.mCurrentInFrontTint = -16777216;
            this.mBlankScreen = this.mDisplayRequiresBlanking;
            this.mAnimationDuration = this.mWakeLockScreenSensorActive ? 1000 : 220;
            if (this.mWakeLockScreenSensorActive && scrimState == ScrimState.AOD) {
                updateScrimColor(this.mScrimBehind, 1.0f, -16777216);
            }
        }

        public float getBehindAlpha() {
            if (this.mWakeLockScreenSensorActive) {
                return 0.6f;
            }
            return ScrimState.AOD.getBehindAlpha();
        }
    },
    UNLOCKED(6) {
        public void prepare(ScrimState scrimState) {
            this.mCurrentBehindAlpha = 0.0f;
            this.mCurrentInFrontAlpha = 0.0f;
            this.mAnimationDuration = this.mKeyguardFadingAway ? this.mKeyguardFadingAwayDuration : 300;
            this.mAnimateChange = !this.mLaunchingAffordanceWithPreview;
            if (scrimState == ScrimState.AOD) {
                updateScrimColor(this.mScrimInFront, 1.0f, -16777216);
                updateScrimColor(this.mScrimBehind, 1.0f, -16777216);
                this.mCurrentInFrontTint = -16777216;
                this.mCurrentBehindTint = -16777216;
                this.mBlankScreen = true;
                return;
            }
            this.mCurrentInFrontTint = 0;
            this.mCurrentBehindTint = 0;
            this.mBlankScreen = false;
        }
    },
    BUBBLE_EXPANDED(7) {
        public void prepare(ScrimState scrimState) {
            this.mCurrentInFrontTint = 0;
            this.mCurrentBehindTint = 0;
            this.mAnimationDuration = 220;
            this.mCurrentBehindAlpha = 0.7f;
            this.mBlankScreen = false;
        }
    };
    
    boolean mAnimateChange;
    long mAnimationDuration;
    float mAodFrontScrimAlpha;
    boolean mBlankScreen;
    float mCurrentBehindAlpha;
    int mCurrentBehindTint;
    float mCurrentInFrontAlpha;
    int mCurrentInFrontTint;
    boolean mDisplayRequiresBlanking;
    DozeParameters mDozeParameters;
    boolean mHasBackdrop;
    int mIndex;
    boolean mKeyguardFadingAway;
    long mKeyguardFadingAwayDuration;
    boolean mLaunchingAffordanceWithPreview;
    ScrimView mScrimBehind;
    float mScrimBehindAlphaKeyguard;
    ScrimView mScrimInFront;
    boolean mWakeLockScreenSensorActive;
    boolean mWallpaperSupportsAmbientMode;

    public boolean isLowPowerState() {
        return false;
    }

    public void prepare(ScrimState scrimState) {
    }

    private ScrimState(int i) {
        this.mBlankScreen = false;
        this.mAnimationDuration = 220;
        this.mCurrentInFrontTint = 0;
        this.mCurrentBehindTint = 0;
        this.mAnimateChange = true;
        this.mIndex = i;
    }

    public void init(ScrimView scrimView, ScrimView scrimView2, DozeParameters dozeParameters) {
        this.mScrimInFront = scrimView;
        this.mScrimBehind = scrimView2;
        this.mDozeParameters = dozeParameters;
        this.mDisplayRequiresBlanking = dozeParameters.getDisplayNeedsBlanking();
    }

    public int getIndex() {
        return this.mIndex;
    }

    public float getFrontAlpha() {
        return this.mCurrentInFrontAlpha;
    }

    public float getBehindAlpha() {
        return this.mCurrentBehindAlpha;
    }

    public int getFrontTint() {
        return this.mCurrentInFrontTint;
    }

    public int getBehindTint() {
        return this.mCurrentBehindTint;
    }

    public long getAnimationDuration() {
        return this.mAnimationDuration;
    }

    public boolean getBlanksScreen() {
        return this.mBlankScreen;
    }

    public void updateScrimColor(ScrimView scrimView, float f, int i) {
        Trace.traceCounter(4096, scrimView == this.mScrimInFront ? "front_scrim_alpha" : "back_scrim_alpha", (int) (255.0f * f));
        Trace.traceCounter(4096, scrimView == this.mScrimInFront ? "front_scrim_tint" : "back_scrim_tint", Color.alpha(i));
        scrimView.setTint(i);
        scrimView.setViewAlpha(f);
    }

    public boolean getAnimateChange() {
        return this.mAnimateChange;
    }

    public void setAodFrontScrimAlpha(float f) {
        this.mAodFrontScrimAlpha = f;
    }

    public void setScrimBehindAlphaKeyguard(float f) {
        this.mScrimBehindAlphaKeyguard = f;
    }

    public void setWallpaperSupportsAmbientMode(boolean z) {
        this.mWallpaperSupportsAmbientMode = z;
    }

    public void setLaunchingAffordanceWithPreview(boolean z) {
        this.mLaunchingAffordanceWithPreview = z;
    }

    public void setHasBackdrop(boolean z) {
        this.mHasBackdrop = z;
    }

    public void setWakeLockScreenSensorActive(boolean z) {
        this.mWakeLockScreenSensorActive = z;
    }

    public void setKeyguardFadingAway(boolean z, long j) {
        this.mKeyguardFadingAway = z;
        this.mKeyguardFadingAwayDuration = j;
    }
}
