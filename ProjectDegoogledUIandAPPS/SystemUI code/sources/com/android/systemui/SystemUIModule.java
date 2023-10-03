package com.android.systemui;

import android.content.Context;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.phone.KeyguardLiftController;
import com.android.systemui.util.AsyncSensorManager;

public abstract class SystemUIModule {
    static KeyguardLiftController provideKeyguardLiftController(Context context, StatusBarStateController statusBarStateController, AsyncSensorManager asyncSensorManager) {
        if (!context.getPackageManager().hasSystemFeature("android.hardware.biometrics.face")) {
            return null;
        }
        return new KeyguardLiftController(context, statusBarStateController, asyncSensorManager);
    }
}
