package com.android.systemui.statusbar.policy;

import android.content.Context;
import com.android.internal.util.Preconditions;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import java.util.ArrayList;

public class KeyguardMonitorImpl extends KeyguardUpdateMonitorCallback implements KeyguardMonitor {
    private boolean mBypassFadingAnimation;
    private final ArrayList<KeyguardMonitor.Callback> mCallbacks = new ArrayList<>();
    private final Context mContext;
    private boolean mKeyguardFadingAway;
    private long mKeyguardFadingAwayDelay;
    private long mKeyguardFadingAwayDuration;
    private boolean mKeyguardGoingAway;
    private final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    private boolean mLaunchTransitionFadingAway;
    private boolean mListening;
    private boolean mOccluded;
    private boolean mSecure;
    private boolean mShowing;

    public KeyguardMonitorImpl(Context context) {
        this.mContext = context;
        this.mKeyguardUpdateMonitor = KeyguardUpdateMonitor.getInstance(this.mContext);
    }

    public synchronized void addCallback(KeyguardMonitor.Callback callback) {
        Preconditions.checkNotNull(callback, "Callback must not be null. b/128895449");
        this.mCallbacks.add(callback);
        if (this.mCallbacks.size() != 0 && !this.mListening) {
            this.mListening = true;
            this.mKeyguardUpdateMonitor.registerCallback(this);
        }
    }

    public synchronized void removeCallback(KeyguardMonitor.Callback callback) {
        Preconditions.checkNotNull(callback, "Callback must not be null. b/128895449");
        if (this.mCallbacks.remove(callback) && this.mCallbacks.size() == 0 && this.mListening) {
            this.mListening = false;
            this.mKeyguardUpdateMonitor.removeCallback(this);
        }
    }

    public boolean isShowing() {
        return this.mShowing;
    }

    public boolean isSecure() {
        return this.mSecure;
    }

    public boolean isOccluded() {
        return this.mOccluded;
    }

    public void notifyKeyguardState(boolean z, boolean z2, boolean z3) {
        if (this.mShowing != z || this.mSecure != z2 || this.mOccluded != z3) {
            this.mShowing = z;
            this.mSecure = z2;
            this.mOccluded = z3;
            notifyKeyguardChanged();
        }
    }

    public void onTrustChanged(int i) {
        notifyKeyguardChanged();
    }

    public boolean isDeviceInteractive() {
        return this.mKeyguardUpdateMonitor.isDeviceInteractive();
    }

    private synchronized void notifyKeyguardChanged() {
        new ArrayList(this.mCallbacks).forEach($$Lambda$CusFj6pVztwBZlitsnMLA9Hx95I.INSTANCE);
    }

    public void notifyKeyguardFadingAway(long j, long j2, boolean z) {
        this.mKeyguardFadingAwayDelay = j;
        this.mKeyguardFadingAwayDuration = j2;
        this.mBypassFadingAnimation = z;
        setKeyguardFadingAway(true);
    }

    private void setKeyguardFadingAway(boolean z) {
        if (this.mKeyguardFadingAway != z) {
            this.mKeyguardFadingAway = z;
            ArrayList arrayList = new ArrayList(this.mCallbacks);
            for (int i = 0; i < arrayList.size(); i++) {
                ((KeyguardMonitor.Callback) arrayList.get(i)).onKeyguardFadingAwayChanged();
            }
        }
    }

    public void notifyKeyguardDoneFading() {
        this.mKeyguardGoingAway = false;
        setKeyguardFadingAway(false);
    }

    public boolean isKeyguardFadingAway() {
        return this.mKeyguardFadingAway;
    }

    public boolean isKeyguardGoingAway() {
        return this.mKeyguardGoingAway;
    }

    public boolean isBypassFadingAnimation() {
        return this.mBypassFadingAnimation;
    }

    public long getKeyguardFadingAwayDelay() {
        return this.mKeyguardFadingAwayDelay;
    }

    public long getKeyguardFadingAwayDuration() {
        return this.mKeyguardFadingAwayDuration;
    }

    public long calculateGoingToFullShadeDelay() {
        return this.mKeyguardFadingAwayDelay + this.mKeyguardFadingAwayDuration;
    }

    public void notifyKeyguardGoingAway(boolean z) {
        this.mKeyguardGoingAway = z;
    }

    public void setLaunchTransitionFadingAway(boolean z) {
        this.mLaunchTransitionFadingAway = z;
    }

    public boolean isLaunchTransitionFadingAway() {
        return this.mLaunchTransitionFadingAway;
    }
}
