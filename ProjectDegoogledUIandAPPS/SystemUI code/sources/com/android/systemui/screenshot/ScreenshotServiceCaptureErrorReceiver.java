package com.android.systemui.screenshot;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.C1784R$string;

public class ScreenshotServiceCaptureErrorReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        GlobalScreenshot.notifyScreenshotError(context, (NotificationManager) context.getSystemService("notification"), C1784R$string.screenshot_failed_to_capture_text);
    }
}
