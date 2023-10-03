package com.android.systemui;

import android.content.Context;
import com.android.systemui.statusbar.phone.ShadeController;
import com.android.systemui.statusbar.phone.StatusBar;

abstract class SystemUIDefaultModule {
    static boolean provideAllowNotificationLongPress() {
        return true;
    }

    static String provideLeakReportEmail() {
        return null;
    }

    static ShadeController provideShadeController(Context context) {
        return (ShadeController) SysUiServiceProvider.getComponent(context, StatusBar.class);
    }
}
