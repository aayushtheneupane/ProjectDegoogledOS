package com.android.incallui;

import android.content.Context;
import android.view.OrientationEventListener;
import com.android.dialer.common.LogUtil;

public class InCallOrientationEventListener extends OrientationEventListener {
    private static int currentOrientation;
    private boolean enabled = false;

    public InCallOrientationEventListener(Context context) {
        super(context);
    }

    public static int getCurrentOrientation() {
        return currentOrientation;
    }

    private static boolean isWithinRange(int i, int i2, int i3) {
        return i >= i2 && i < i3;
    }

    private static boolean isWithinThreshold(int i, int i2, int i3) {
        return isWithinRange(i, i2 - i3, i2 + i3);
    }

    public void disable() {
        if (!this.enabled) {
            Bindings.m40v(this, "enable: Orientation listener is already disabled. Ignoring...");
            return;
        }
        this.enabled = false;
        super.disable();
    }

    public void enable(boolean z) {
        if (this.enabled) {
            Bindings.m40v(this, "enable: Orientation listener is already enabled. Ignoring...");
            return;
        }
        super.enable();
        this.enabled = true;
        if (z) {
            InCallPresenter.getInstance().onDeviceOrientationChange(currentOrientation);
        }
    }

    public void onOrientationChanged(int i) {
        int i2;
        int i3;
        if (i != -1) {
            if (isWithinRange(i, 350, 360) || isWithinRange(i, 0, 10)) {
                i2 = 0;
            } else {
                i2 = 90;
                if (isWithinThreshold(i, 90, 10)) {
                    i2 = 270;
                } else if (isWithinThreshold(i, 180, 10)) {
                    i2 = 180;
                } else if (!isWithinThreshold(i, 270, 10)) {
                    i2 = -1;
                }
            }
            if (i2 != -1 && (i3 = currentOrientation) != i2) {
                LogUtil.m9i("InCallOrientationEventListener.onOrientationChanged", "orientation: %d -> %d", Integer.valueOf(i3), Integer.valueOf(i2));
                currentOrientation = i2;
                InCallPresenter.getInstance().onDeviceOrientationChange(currentOrientation);
            }
        }
    }

    public void enable() {
        enable(false);
    }
}
