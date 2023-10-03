package com.android.systemui.havoc.screenstate;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class ScreenStateService extends Service {
    private List<ScreenStateToggle> fAllToggles;
    /* access modifiers changed from: private */
    public List<ScreenStateToggle> fEnabledToggles;
    /* access modifiers changed from: private */
    public Context mContext;
    private boolean mEnabled = true;
    private GpsToggle mGpsToggle;
    private MobileDataToggle mMobileDataToggle;
    private BroadcastReceiver mPowerKeyReceiver;
    private ThreeGToggle mThreeGToggle;
    private TwoGToggle mTwoGToggle;
    /* access modifiers changed from: private */
    public boolean offScheduled = true;
    /* access modifiers changed from: private */
    public boolean onScheduled = false;
    /* access modifiers changed from: private */
    public Handler scrOffHandler;
    /* access modifiers changed from: private */
    public Runnable scrOffTask = new Runnable() {
        public void run() {
            Log.v("ScreenStateService", "scrOffTask");
            for (ScreenStateToggle doScreenOff : ScreenStateService.this.fEnabledToggles) {
                doScreenOff.doScreenOff();
            }
            boolean unused = ScreenStateService.this.offScheduled = false;
        }
    };
    /* access modifiers changed from: private */
    public Handler scrOnHandler;
    /* access modifiers changed from: private */
    public Runnable scrOnTask = new Runnable() {
        public void run() {
            Log.v("ScreenStateService", "scrOnTask");
            for (ScreenStateToggle doScreenOn : ScreenStateService.this.fEnabledToggles) {
                doScreenOn.doScreenOn();
            }
            boolean unused = ScreenStateService.this.onScheduled = false;
        }
    };

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        Log.d("ScreenStateService", "onDestroy");
        if (this.mEnabled) {
            unregisterReceiver();
        }
    }

    public void onStart(Intent intent, int i) {
        Log.d("ScreenStateService", "onStart");
        this.mContext = getApplicationContext();
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "start_screen_state_service", 0, -2) != 0) {
            this.mEnabled = true;
        } else {
            this.mEnabled = false;
        }
        if (this.mEnabled) {
            registerBroadcastReceiver();
        }
        this.scrOnHandler = new Handler();
        this.scrOffHandler = new Handler();
        this.fAllToggles = new ArrayList();
        this.mTwoGToggle = new TwoGToggle(this.mContext);
        this.fAllToggles.add(this.mTwoGToggle);
        this.mThreeGToggle = new ThreeGToggle(this.mContext);
        this.fAllToggles.add(this.mThreeGToggle);
        this.mGpsToggle = new GpsToggle(this.mContext);
        this.fAllToggles.add(this.mGpsToggle);
        this.mMobileDataToggle = new MobileDataToggle(this.mContext);
        this.fAllToggles.add(this.mMobileDataToggle);
        updateEnabledToggles();
    }

    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_STATE_SERVICE_UPDATE");
        this.mPowerKeyReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("android.intent.action.SCREEN_OFF")) {
                    Log.d("ScreenStateService", "screen off");
                    if (ScreenStateService.this.onScheduled) {
                        ScreenStateService.this.scrOnHandler.removeCallbacks(ScreenStateService.this.scrOnTask);
                    } else {
                        int intForUser = Settings.System.getIntForUser(ScreenStateService.this.mContext.getContentResolver(), "screen_state_off_delay", 0, -2);
                        Log.d("ScreenStateService", "screen off delay: " + intForUser);
                        ScreenStateService.this.scrOffHandler.postDelayed(ScreenStateService.this.scrOffTask, (long) (intForUser * 1000));
                        boolean unused = ScreenStateService.this.offScheduled = true;
                    }
                }
                if (action.equals("android.intent.action.SCREEN_ON")) {
                    Log.d("ScreenStateService", "scren on");
                    if (ScreenStateService.this.offScheduled) {
                        ScreenStateService.this.scrOffHandler.removeCallbacks(ScreenStateService.this.scrOffTask);
                    } else {
                        int intForUser2 = Settings.System.getIntForUser(ScreenStateService.this.mContext.getContentResolver(), "screen_state_on_delay", 0, -2);
                        Log.d("ScreenStateService", "screen on delay: " + intForUser2);
                        ScreenStateService.this.scrOnHandler.postDelayed(ScreenStateService.this.scrOnTask, (long) (intForUser2 * 1000));
                        boolean unused2 = ScreenStateService.this.onScheduled = true;
                    }
                }
                if (action.equals("android.intent.action.SCREEN_STATE_SERVICE_UPDATE")) {
                    Log.d("ScreenStateService", "update enabled toggles");
                    ScreenStateService.this.updateEnabledToggles();
                }
            }
        };
        Log.d("ScreenStateService", "registerBroadcastReceiver");
        this.mContext.registerReceiver(this.mPowerKeyReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        try {
            Log.d("ScreenStateService", "unregisterReceiver");
            this.mContext.unregisterReceiver(this.mPowerKeyReceiver);
        } catch (IllegalArgumentException unused) {
            this.mPowerKeyReceiver = null;
        }
    }

    /* access modifiers changed from: private */
    public void updateEnabledToggles() {
        this.fEnabledToggles = new ArrayList();
        for (ScreenStateToggle next : this.fAllToggles) {
            if (next.isEnabled()) {
                Log.d("ScreenStateService", "active toggle " + next.getClass().getName());
                this.fEnabledToggles.add(next);
            }
        }
    }
}
