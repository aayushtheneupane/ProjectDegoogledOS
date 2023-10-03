package com.android.incallui.answerproximitysensor;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.PowerManager;
import com.android.dialer.common.LogUtil;
import com.android.incallui.answerproximitysensor.AnswerProximityWakeLock;

public class SystemProximityWakeLock implements AnswerProximityWakeLock, DisplayManager.DisplayListener {
    private final Context context;
    private AnswerProximityWakeLock.ScreenOnListener listener;
    private final PowerManager.WakeLock wakeLock;

    public SystemProximityWakeLock(Context context2) {
        this.context = context2;
        this.wakeLock = ((PowerManager) context2.getSystemService(PowerManager.class)).newWakeLock(32, "SystemProximityWakeLock");
    }

    public void acquire() {
        this.wakeLock.acquire();
        ((DisplayManager) this.context.getSystemService(DisplayManager.class)).registerDisplayListener(this, (Handler) null);
    }

    public boolean isHeld() {
        return this.wakeLock.isHeld();
    }

    public void onDisplayAdded(int i) {
    }

    public void onDisplayChanged(int i) {
        if (i == 0) {
            boolean z = true;
            if (((DisplayManager) this.context.getSystemService(DisplayManager.class)).getDisplay(0).getState() == 1) {
                z = false;
            }
            if (z) {
                LogUtil.m9i("SystemProximityWakeLock.onDisplayChanged", "display turned on", new Object[0]);
                AnswerProximityWakeLock.ScreenOnListener screenOnListener = this.listener;
                if (screenOnListener != null) {
                    ((AnswerProximitySensor) screenOnListener).onScreenOn();
                }
            }
        }
    }

    public void onDisplayRemoved(int i) {
    }

    public void release() {
        this.wakeLock.release();
        ((DisplayManager) this.context.getSystemService(DisplayManager.class)).unregisterDisplayListener(this);
    }

    public void setScreenOnListener(AnswerProximityWakeLock.ScreenOnListener screenOnListener) {
        this.listener = screenOnListener;
    }
}
