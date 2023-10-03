package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import android.hardware.display.NightDisplayListener;
import android.os.Handler;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.p006qs.AutoAddTracker;
import com.android.systemui.p006qs.QSTileHost;
import com.android.systemui.p006qs.SecureSetting;
import com.android.systemui.statusbar.phone.AutoTileManager;
import com.android.systemui.statusbar.phone.ManagedProfileController;
import com.android.systemui.statusbar.policy.CastController;
import com.android.systemui.statusbar.policy.DataSaverController;
import com.android.systemui.statusbar.policy.HotspotController;
import java.util.Iterator;

public class AutoTileManager {
    /* access modifiers changed from: private */
    public final AutoAddTracker mAutoTracker;
    @VisibleForTesting
    final CastController.Callback mCastCallback = new CastController.Callback() {
        public void onCastDevicesChanged() {
            if (!AutoTileManager.this.mAutoTracker.isAdded("cast")) {
                boolean z = false;
                Iterator<CastController.CastDevice> it = AutoTileManager.this.mCastController.getCastDevices().iterator();
                while (true) {
                    if (it.hasNext()) {
                        int i = it.next().state;
                        if (i != 2) {
                            if (i == 1) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                z = true;
                if (z) {
                    AutoTileManager.this.mHost.addTile("cast");
                    AutoTileManager.this.mAutoTracker.setTileAdded("cast");
                    AutoTileManager.this.mHandler.post(new Runnable() {
                        public final void run() {
                            AutoTileManager.C13186.this.lambda$onCastDevicesChanged$0$AutoTileManager$6();
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onCastDevicesChanged$0$AutoTileManager$6() {
            AutoTileManager.this.mCastController.removeCallback(AutoTileManager.this.mCastCallback);
        }
    };
    /* access modifiers changed from: private */
    public final CastController mCastController;
    /* access modifiers changed from: private */
    public SecureSetting mColorsSetting;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final DataSaverController mDataSaverController;
    /* access modifiers changed from: private */
    public final DataSaverController.Listener mDataSaverListener = new DataSaverController.Listener() {
        public void onDataSaverChanged(boolean z) {
            if (!AutoTileManager.this.mAutoTracker.isAdded("saver") && z) {
                AutoTileManager.this.mHost.addTile("saver");
                AutoTileManager.this.mAutoTracker.setTileAdded("saver");
                AutoTileManager.this.mHandler.post(new Runnable() {
                    public final void run() {
                        AutoTileManager.C13153.this.lambda$onDataSaverChanged$0$AutoTileManager$3();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onDataSaverChanged$0$AutoTileManager$3() {
            AutoTileManager.this.mDataSaverController.removeCallback(AutoTileManager.this.mDataSaverListener);
        }
    };
    /* access modifiers changed from: private */
    public final Handler mHandler;
    /* access modifiers changed from: private */
    public final QSTileHost mHost;
    /* access modifiers changed from: private */
    public final HotspotController.Callback mHotspotCallback = new HotspotController.Callback() {
        public void onHotspotChanged(boolean z, int i) {
            if (!AutoTileManager.this.mAutoTracker.isAdded("hotspot") && z) {
                AutoTileManager.this.mHost.addTile("hotspot");
                AutoTileManager.this.mAutoTracker.setTileAdded("hotspot");
                AutoTileManager.this.mHandler.post(new Runnable() {
                    public final void run() {
                        AutoTileManager.C13164.this.lambda$onHotspotChanged$0$AutoTileManager$4();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onHotspotChanged$0$AutoTileManager$4() {
            AutoTileManager.this.mHotspotController.removeCallback(AutoTileManager.this.mHotspotCallback);
        }
    };
    /* access modifiers changed from: private */
    public final HotspotController mHotspotController;
    /* access modifiers changed from: private */
    public final ManagedProfileController mManagedProfileController;
    @VisibleForTesting
    final NightDisplayListener.Callback mNightDisplayCallback = new NightDisplayListener.Callback() {
        public void onActivated(boolean z) {
            if (z) {
                addNightTile();
            }
        }

        public void onAutoModeChanged(int i) {
            if (i == 1 || i == 2) {
                addNightTile();
            }
        }

        private void addNightTile() {
            if (!AutoTileManager.this.mAutoTracker.isAdded("night")) {
                AutoTileManager.this.mHost.addTile("night");
                AutoTileManager.this.mAutoTracker.setTileAdded("night");
                AutoTileManager.this.mHandler.post(new Runnable() {
                    public final void run() {
                        AutoTileManager.C13175.this.lambda$addNightTile$0$AutoTileManager$5();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$addNightTile$0$AutoTileManager$5() {
            AutoTileManager.this.mNightDisplayListener.setCallback((NightDisplayListener.Callback) null);
        }
    };
    /* access modifiers changed from: private */
    public final NightDisplayListener mNightDisplayListener;
    private final ManagedProfileController.Callback mProfileCallback = new ManagedProfileController.Callback() {
        public void onManagedProfileRemoved() {
        }

        public void onManagedProfileChanged() {
            if (!AutoTileManager.this.mAutoTracker.isAdded("work") && AutoTileManager.this.mManagedProfileController.hasActiveProfile()) {
                AutoTileManager.this.mHost.addTile("work");
                AutoTileManager.this.mAutoTracker.setTileAdded("work");
            }
        }
    };

    public AutoTileManager(Context context, AutoAddTracker autoAddTracker, QSTileHost qSTileHost, Handler handler, HotspotController hotspotController, DataSaverController dataSaverController, ManagedProfileController managedProfileController, NightDisplayListener nightDisplayListener, CastController castController) {
        this.mAutoTracker = autoAddTracker;
        this.mContext = context;
        this.mHost = qSTileHost;
        this.mHandler = handler;
        this.mHotspotController = hotspotController;
        this.mDataSaverController = dataSaverController;
        this.mManagedProfileController = managedProfileController;
        this.mNightDisplayListener = nightDisplayListener;
        this.mCastController = castController;
        if (!this.mAutoTracker.isAdded("hotspot")) {
            hotspotController.addCallback(this.mHotspotCallback);
        }
        if (!this.mAutoTracker.isAdded("saver")) {
            dataSaverController.addCallback(this.mDataSaverListener);
        }
        if (!this.mAutoTracker.isAdded("inversion")) {
            this.mColorsSetting = new SecureSetting(this.mContext, this.mHandler, "accessibility_display_inversion_enabled") {
                /* access modifiers changed from: protected */
                public void handleValueChanged(int i, boolean z) {
                    if (!AutoTileManager.this.mAutoTracker.isAdded("inversion") && i != 0) {
                        AutoTileManager.this.mHost.addTile("inversion");
                        AutoTileManager.this.mAutoTracker.setTileAdded("inversion");
                        AutoTileManager.this.mHandler.post(new Runnable() {
                            public final void run() {
                                AutoTileManager.C13131.this.lambda$handleValueChanged$0$AutoTileManager$1();
                            }
                        });
                    }
                }

                public /* synthetic */ void lambda$handleValueChanged$0$AutoTileManager$1() {
                    AutoTileManager.this.mColorsSetting.setListening(false);
                }
            };
            this.mColorsSetting.setListening(true);
        }
        if (!this.mAutoTracker.isAdded("work")) {
            managedProfileController.addCallback(this.mProfileCallback);
        }
        if (!this.mAutoTracker.isAdded("night") && ColorDisplayManager.isNightDisplayAvailable(this.mContext)) {
            nightDisplayListener.setCallback(this.mNightDisplayCallback);
        }
        if (!this.mAutoTracker.isAdded("cast")) {
            castController.addCallback(this.mCastCallback);
        }
    }

    public void unmarkTileAsAutoAdded(String str) {
        this.mAutoTracker.setTileRemoved(str);
    }
}
