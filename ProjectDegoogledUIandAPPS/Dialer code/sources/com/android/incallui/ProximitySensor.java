package com.android.incallui;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Trace;
import android.telecom.CallAudioState;
import com.android.dialer.common.LogUtil;
import com.android.incallui.AccelerometerListener;
import com.android.incallui.InCallPresenter;
import com.android.incallui.audiomode.AudioModeProvider;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;

public class ProximitySensor implements AccelerometerListener.OrientationListener, InCallPresenter.InCallStateListener, AudioModeProvider.AudioModeListener {
    private final AccelerometerListener accelerometerListener;
    private final AudioModeProvider audioModeProvider;
    private boolean dialpadVisible;
    private final ProximityDisplayListener displayListener;
    private boolean isAttemptingVideoCall;
    private boolean isPhoneOffhook = false;
    private boolean isRttCall;
    private boolean isVideoCall;
    private int orientation = 0;
    private final PowerManager powerManager;
    private final PowerManager.WakeLock proximityWakeLock;
    private boolean uiShowing = false;

    public class ProximityDisplayListener implements DisplayManager.DisplayListener {
        private DisplayManager displayManager;
        private boolean isDisplayOn = true;

        ProximityDisplayListener(DisplayManager displayManager2) {
            this.displayManager = displayManager2;
        }

        public void onDisplayAdded(int i) {
        }

        public void onDisplayChanged(int i) {
            if (i == 0) {
                boolean z = true;
                if (this.displayManager.getDisplay(i).getState() == 1) {
                    z = false;
                }
                if (z != this.isDisplayOn) {
                    this.isDisplayOn = z;
                    ProximitySensor.this.onDisplayStateChanged(this.isDisplayOn);
                }
            }
        }

        public void onDisplayRemoved(int i) {
        }

        /* access modifiers changed from: package-private */
        public void register() {
            this.displayManager.registerDisplayListener(this, (Handler) null);
        }

        /* access modifiers changed from: package-private */
        public void unregister() {
            this.displayManager.unregisterDisplayListener(this);
        }
    }

    public ProximitySensor(Context context, AudioModeProvider audioModeProvider2, AccelerometerListener accelerometerListener2) {
        Trace.beginSection("ProximitySensor.Constructor");
        this.powerManager = (PowerManager) context.getSystemService("power");
        if (this.powerManager.isWakeLockLevelSupported(32)) {
            this.proximityWakeLock = this.powerManager.newWakeLock(32, "ProximitySensor");
        } else {
            LogUtil.m9i("ProximitySensor.constructor", "Device does not support proximity wake lock.", new Object[0]);
            this.proximityWakeLock = null;
        }
        this.accelerometerListener = accelerometerListener2;
        this.accelerometerListener.setListener(this);
        this.displayListener = new ProximityDisplayListener((DisplayManager) context.getSystemService("display"));
        this.displayListener.register();
        this.audioModeProvider = audioModeProvider2;
        this.audioModeProvider.addListener(this);
        Trace.endSection();
    }

    private void turnOffProximitySensor(boolean z) {
        PowerManager.WakeLock wakeLock = this.proximityWakeLock;
        if (wakeLock == null) {
            return;
        }
        if (wakeLock.isHeld()) {
            LogUtil.m9i("ProximitySensor.turnOffProximitySensor", "releasing wake lock", new Object[0]);
            this.proximityWakeLock.release(z ^ true ? 1 : 0);
            return;
        }
        LogUtil.m9i("ProximitySensor.turnOffProximitySensor", "wake lock already released", new Object[0]);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void updateProximitySensorMode() {
        /*
            r11 = this;
            monitor-enter(r11)
            java.lang.String r0 = "ProximitySensor.updateProximitySensorMode"
            android.os.Trace.beginSection(r0)     // Catch:{ all -> 0x00b2 }
            com.android.incallui.audiomode.AudioModeProvider r0 = r11.audioModeProvider     // Catch:{ all -> 0x00b2 }
            android.telecom.CallAudioState r0 = r0.getAudioState()     // Catch:{ all -> 0x00b2 }
            int r0 = r0.getRoute()     // Catch:{ all -> 0x00b2 }
            r1 = 4
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == r0) goto L_0x002b
            r5 = 8
            if (r5 == r0) goto L_0x002b
            if (r2 == r0) goto L_0x002b
            boolean r5 = r11.isAttemptingVideoCall     // Catch:{ all -> 0x00b2 }
            if (r5 != 0) goto L_0x002b
            boolean r5 = r11.isVideoCall     // Catch:{ all -> 0x00b2 }
            if (r5 != 0) goto L_0x002b
            boolean r5 = r11.isRttCall     // Catch:{ all -> 0x00b2 }
            if (r5 == 0) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r5 = r4
            goto L_0x002c
        L_0x002b:
            r5 = r3
        L_0x002c:
            int r6 = r11.orientation     // Catch:{ all -> 0x00b2 }
            if (r6 != r2) goto L_0x0032
            r6 = r3
            goto L_0x0033
        L_0x0032:
            r6 = r4
        L_0x0033:
            boolean r7 = r11.uiShowing     // Catch:{ all -> 0x00b2 }
            if (r7 != 0) goto L_0x003b
            if (r6 == 0) goto L_0x003b
            r7 = r3
            goto L_0x003c
        L_0x003b:
            r7 = r4
        L_0x003c:
            r5 = r5 | r7
            boolean r7 = r11.dialpadVisible     // Catch:{ all -> 0x00b2 }
            if (r7 == 0) goto L_0x0045
            if (r6 == 0) goto L_0x0045
            r6 = r3
            goto L_0x0046
        L_0x0045:
            r6 = r4
        L_0x0046:
            r5 = r5 | r6
            java.lang.String r6 = "ProximitySensor.updateProximitySensorMode"
            java.lang.String r7 = "screenOnImmediately: %b, dialPadVisible: %b, offHook: %b, horizontal: %b, uiShowing: %b, audioRoute: %s"
            r8 = 6
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x00b2 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r5)     // Catch:{ all -> 0x00b2 }
            r8[r4] = r9     // Catch:{ all -> 0x00b2 }
            boolean r9 = r11.dialpadVisible     // Catch:{ all -> 0x00b2 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)     // Catch:{ all -> 0x00b2 }
            r8[r3] = r9     // Catch:{ all -> 0x00b2 }
            boolean r9 = r11.isPhoneOffhook     // Catch:{ all -> 0x00b2 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)     // Catch:{ all -> 0x00b2 }
            r8[r2] = r9     // Catch:{ all -> 0x00b2 }
            r9 = 3
            int r10 = r11.orientation     // Catch:{ all -> 0x00b2 }
            if (r10 != r2) goto L_0x006a
            goto L_0x006b
        L_0x006a:
            r3 = r4
        L_0x006b:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x00b2 }
            r8[r9] = r2     // Catch:{ all -> 0x00b2 }
            boolean r2 = r11.uiShowing     // Catch:{ all -> 0x00b2 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x00b2 }
            r8[r1] = r2     // Catch:{ all -> 0x00b2 }
            r1 = 5
            java.lang.String r0 = android.telecom.CallAudioState.audioRouteToString(r0)     // Catch:{ all -> 0x00b2 }
            r8[r1] = r0     // Catch:{ all -> 0x00b2 }
            com.android.dialer.common.LogUtil.m9i(r6, r7, r8)     // Catch:{ all -> 0x00b2 }
            boolean r0 = r11.isPhoneOffhook     // Catch:{ all -> 0x00b2 }
            if (r0 == 0) goto L_0x00aa
            if (r5 != 0) goto L_0x00aa
            android.os.PowerManager$WakeLock r0 = r11.proximityWakeLock     // Catch:{ all -> 0x00b2 }
            if (r0 == 0) goto L_0x00ad
            boolean r0 = r0.isHeld()     // Catch:{ all -> 0x00b2 }
            java.lang.String r1 = "ProximitySensor.turnOnProximitySensor"
            if (r0 != 0) goto L_0x00a2
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch:{ all -> 0x00b2 }
            java.lang.String r2 = "acquiring wake lock"
            com.android.dialer.common.LogUtil.m9i(r1, r2, r0)     // Catch:{ all -> 0x00b2 }
            android.os.PowerManager$WakeLock r0 = r11.proximityWakeLock     // Catch:{ all -> 0x00b2 }
            r0.acquire()     // Catch:{ all -> 0x00b2 }
            goto L_0x00ad
        L_0x00a2:
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch:{ all -> 0x00b2 }
            java.lang.String r2 = "wake lock already acquired"
            com.android.dialer.common.LogUtil.m9i(r1, r2, r0)     // Catch:{ all -> 0x00b2 }
            goto L_0x00ad
        L_0x00aa:
            r11.turnOffProximitySensor(r5)     // Catch:{ all -> 0x00b2 }
        L_0x00ad:
            android.os.Trace.endSection()     // Catch:{ all -> 0x00b2 }
            monitor-exit(r11)
            return
        L_0x00b2:
            r0 = move-exception
            monitor-exit(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.ProximitySensor.updateProximitySensorMode():void");
    }

    public void onAudioStateChanged(CallAudioState callAudioState) {
        updateProximitySensorMode();
    }

    public void onDialpadVisible(boolean z) {
        this.dialpadVisible = z;
        updateProximitySensorMode();
    }

    /* access modifiers changed from: package-private */
    public void onDisplayStateChanged(boolean z) {
        LogUtil.m9i("ProximitySensor.onDisplayStateChanged", "isDisplayOn: %b", Boolean.valueOf(z));
        this.accelerometerListener.enable(z);
    }

    public void onInCallShowing(boolean z) {
        if (z) {
            this.uiShowing = true;
        } else if (this.powerManager.isScreenOn()) {
            this.uiShowing = false;
        }
        updateProximitySensorMode();
    }

    public void onStateChange(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, CallList callList) {
        boolean z = true;
        boolean z2 = InCallPresenter.InCallState.PENDING_OUTGOING == inCallState2 || InCallPresenter.InCallState.OUTGOING == inCallState2 || (InCallPresenter.InCallState.INCALL == inCallState2 && callList.hasLiveCall());
        DialerCall activeCall = callList.getActiveCall();
        boolean z3 = activeCall != null && activeCall.isVideoCall();
        if (activeCall == null || !activeCall.isActiveRttCall()) {
            z = false;
        }
        if (z2 != this.isPhoneOffhook || this.isVideoCall != z3 || this.isRttCall != z) {
            this.isPhoneOffhook = z2;
            this.isVideoCall = z3;
            this.isRttCall = z;
            this.orientation = 0;
            this.accelerometerListener.enable(this.isPhoneOffhook);
            updateProximitySensorMode();
        }
    }

    public void orientationChanged(int i) {
        this.orientation = i;
        updateProximitySensorMode();
    }

    public void setIsAttemptingVideoCall(boolean z) {
        LogUtil.m9i("ProximitySensor.setIsAttemptingVideoCall", "isAttemptingVideoCall: %b", Boolean.valueOf(z));
        this.isAttemptingVideoCall = z;
        updateProximitySensorMode();
    }

    public void tearDown() {
        this.audioModeProvider.removeListener(this);
        this.accelerometerListener.enable(false);
        this.displayListener.unregister();
        turnOffProximitySensor(true);
    }
}
