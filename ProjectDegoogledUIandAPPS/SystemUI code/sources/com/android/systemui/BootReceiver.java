package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.havoc.screenstate.ScreenStateService;

public class BootReceiver extends BroadcastReceiver {
    /* access modifiers changed from: private */
    public Context mContext;
    private Handler mHandler = new Handler();
    private SettingsObserver mSettingsObserver;

    private class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            BootReceiver.this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("show_cpu_overlay"), false, this);
            BootReceiver.this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("show_fps_overlay"), false, this);
            update();
        }

        public void onChange(boolean z) {
            update();
        }

        public void update() {
            Intent intent = new Intent(BootReceiver.this.mContext, CPUInfoService.class);
            Intent intent2 = new Intent(BootReceiver.this.mContext, FPSInfoService.class);
            if (Settings.Global.getInt(BootReceiver.this.mContext.getContentResolver(), "show_cpu_overlay", 0) != 0) {
                BootReceiver.this.mContext.startService(intent);
            } else {
                BootReceiver.this.mContext.stopService(intent);
            }
            if (Settings.Global.getInt(BootReceiver.this.mContext.getContentResolver(), "show_fps_overlay", 0) != 0) {
                BootReceiver.this.mContext.startService(intent2);
            } else {
                BootReceiver.this.mContext.stopService(intent2);
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        try {
            this.mContext = context;
            if (this.mSettingsObserver == null) {
                this.mSettingsObserver = new SettingsObserver(this.mHandler);
                this.mSettingsObserver.observe();
            }
            if (Settings.Global.getInt(this.mContext.getContentResolver(), "show_cpu_overlay", 0) != 0) {
                this.mContext.startService(new Intent(this.mContext, CPUInfoService.class));
            }
            if (Settings.Global.getInt(this.mContext.getContentResolver(), "show_fps_overlay", 0) != 0) {
                this.mContext.startService(new Intent(this.mContext, FPSInfoService.class));
            }
            if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "start_screen_state_service", 0, -2) != 0) {
                this.mContext.startService(new Intent(this.mContext, ScreenStateService.class));
            }
        } catch (Exception e) {
            Log.e("SystemUIBootReceiver", "Can't start load average service", e);
        }
    }
}
