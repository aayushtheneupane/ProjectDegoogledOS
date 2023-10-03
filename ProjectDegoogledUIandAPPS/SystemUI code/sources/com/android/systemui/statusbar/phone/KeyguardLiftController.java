package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.TriggerEventListener;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.util.AsyncSensorManager;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KeyguardLiftController.kt */
public final class KeyguardLiftController extends KeyguardUpdateMonitorCallback implements StatusBarStateController.StateListener {
    private final AsyncSensorManager asyncSensorManager;
    private boolean bouncerVisible;
    /* access modifiers changed from: private */
    public boolean isListening;
    /* access modifiers changed from: private */
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    private final TriggerEventListener listener;
    private final Sensor pickupSensor = this.asyncSensorManager.getDefaultSensor(25);
    private final StatusBarStateController statusBarStateController;

    public KeyguardLiftController(Context context, StatusBarStateController statusBarStateController2, AsyncSensorManager asyncSensorManager2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(statusBarStateController2, "statusBarStateController");
        Intrinsics.checkParameterIsNotNull(asyncSensorManager2, "asyncSensorManager");
        this.statusBarStateController = statusBarStateController2;
        this.asyncSensorManager = asyncSensorManager2;
        this.keyguardUpdateMonitor = KeyguardUpdateMonitor.getInstance(context);
        this.statusBarStateController.addCallback(this);
        this.keyguardUpdateMonitor.registerCallback(this);
        updateListeningState();
        this.listener = new KeyguardLiftController$listener$1(this);
    }

    public void onDozingChanged(boolean z) {
        updateListeningState();
    }

    public void onKeyguardBouncerChanged(boolean z) {
        this.bouncerVisible = z;
        updateListeningState();
    }

    public void onKeyguardVisibilityChanged(boolean z) {
        updateListeningState();
    }

    /* access modifiers changed from: private */
    public final void updateListeningState() {
        if (this.pickupSensor != null) {
            KeyguardUpdateMonitor keyguardUpdateMonitor2 = this.keyguardUpdateMonitor;
            Intrinsics.checkExpressionValueIsNotNull(keyguardUpdateMonitor2, "keyguardUpdateMonitor");
            boolean z = true;
            boolean z2 = keyguardUpdateMonitor2.isKeyguardVisible() && !this.statusBarStateController.isDozing();
            boolean isFaceAuthEnabledForUser = this.keyguardUpdateMonitor.isFaceAuthEnabledForUser(KeyguardUpdateMonitor.getCurrentUser());
            if ((!z2 && !this.bouncerVisible) || !isFaceAuthEnabledForUser) {
                z = false;
            }
            if (z != this.isListening) {
                this.isListening = z;
                if (z) {
                    this.asyncSensorManager.requestTriggerSensor(this.listener, this.pickupSensor);
                } else {
                    this.asyncSensorManager.cancelTriggerSensor(this.listener, this.pickupSensor);
                }
            }
        }
    }
}
