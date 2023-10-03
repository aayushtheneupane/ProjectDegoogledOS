package com.android.settings.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Message;
import android.widget.Toast;
import com.android.internal.app.AlertActivity;
import com.android.internal.app.AlertController;
import com.havoc.config.center.C1715R;

public class RequestToggleWiFiActivity extends AlertActivity implements DialogInterface.OnClickListener {
    private CharSequence mAppLabel;
    private int mLastUpdateState = -1;
    private final StateChangeReceiver mReceiver = new StateChangeReceiver();
    /* access modifiers changed from: private */
    public int mState = -1;
    private final Runnable mTimeoutCommand = new Runnable() {
        public final void run() {
            RequestToggleWiFiActivity.this.lambda$new$0$RequestToggleWiFiActivity();
        }
    };
    /* access modifiers changed from: private */
    public WifiManager mWiFiManager;

    public void dismiss() {
    }

    public /* synthetic */ void lambda$new$0$RequestToggleWiFiActivity() {
        if (!isFinishing() && !isDestroyed()) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0063, code lost:
        if (r0.equals("android.net.wifi.action.REQUEST_ENABLE") != false) goto L_0x0067;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r6) {
        /*
            r5 = this;
            com.android.settings.wifi.RequestToggleWiFiActivity.super.onCreate(r6)
            java.lang.Class<android.net.wifi.WifiManager> r6 = android.net.wifi.WifiManager.class
            java.lang.Object r6 = r5.getSystemService(r6)
            android.net.wifi.WifiManager r6 = (android.net.wifi.WifiManager) r6
            r5.mWiFiManager = r6
            r6 = 0
            r5.setResult(r6)
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r1 = "android.intent.extra.PACKAGE_NAME"
            java.lang.String r0 = r0.getStringExtra(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x0025
            r5.finish()
            return
        L_0x0025:
            android.content.pm.PackageManager r1 = r5.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0076 }
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo(r0, r6)     // Catch:{ NameNotFoundException -> 0x0076 }
            android.content.pm.PackageManager r2 = r5.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0076 }
            r3 = 1140457472(0x43fa0000, float:500.0)
            r4 = 5
            java.lang.CharSequence r1 = r1.loadSafeLabel(r2, r3, r4)     // Catch:{ NameNotFoundException -> 0x0076 }
            r5.mAppLabel = r1     // Catch:{ NameNotFoundException -> 0x0076 }
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r0 = r0.getAction()
            r1 = -1
            int r2 = r0.hashCode()
            r3 = -2035256254(0xffffffff86b07442, float:-6.637467E-35)
            r4 = 1
            if (r2 == r3) goto L_0x005d
            r6 = 317500393(0x12ecabe9, float:1.4936073E-27)
            if (r2 == r6) goto L_0x0053
            goto L_0x0066
        L_0x0053:
            java.lang.String r6 = "android.net.wifi.action.REQUEST_DISABLE"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x0066
            r6 = r4
            goto L_0x0067
        L_0x005d:
            java.lang.String r2 = "android.net.wifi.action.REQUEST_ENABLE"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0066
            goto L_0x0067
        L_0x0066:
            r6 = r1
        L_0x0067:
            if (r6 == 0) goto L_0x0073
            if (r6 == r4) goto L_0x006f
            r5.finish()
            goto L_0x0075
        L_0x006f:
            r6 = 3
            r5.mState = r6
            goto L_0x0075
        L_0x0073:
            r5.mState = r4
        L_0x0075:
            return
        L_0x0076:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "Couldn't find app with package name "
            r6.append(r1)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            java.lang.String r0 = "RequestToggleWiFiActivity"
            android.util.Log.e(r0, r6)
            r5.finish()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.wifi.RequestToggleWiFiActivity.onCreate(android.os.Bundle):void");
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -2) {
            finish();
        } else if (i == -1) {
            int i2 = this.mState;
            if (i2 == 1) {
                this.mWiFiManager.setWifiEnabled(true);
                this.mState = 2;
                scheduleToggleTimeout();
                updateUi();
            } else if (i2 == 3) {
                this.mWiFiManager.setWifiEnabled(false);
                this.mState = 4;
                scheduleToggleTimeout();
                updateUi();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        RequestToggleWiFiActivity.super.onStart();
        this.mReceiver.register();
        int wifiState = this.mWiFiManager.getWifiState();
        int i = this.mState;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        if (wifiState == 0) {
                            scheduleToggleTimeout();
                        } else if (wifiState == 1) {
                            setResult(-1);
                            finish();
                            return;
                        } else if (wifiState == 2 || wifiState == 3) {
                            this.mState = 3;
                        }
                    }
                } else if (wifiState == 1) {
                    setResult(-1);
                    finish();
                    return;
                } else if (wifiState == 2) {
                    this.mState = 4;
                    scheduleToggleTimeout();
                }
            } else if (wifiState == 0 || wifiState == 1) {
                this.mState = 1;
            } else if (wifiState == 2) {
                scheduleToggleTimeout();
            } else if (wifiState == 3) {
                setResult(-1);
                finish();
                return;
            }
        } else if (wifiState == 2) {
            this.mState = 2;
            scheduleToggleTimeout();
        } else if (wifiState == 3) {
            setResult(-1);
            finish();
            return;
        }
        updateUi();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.mReceiver.unregister();
        unscheduleToggleTimeout();
        RequestToggleWiFiActivity.super.onStop();
    }

    private void updateUi() {
        int i = this.mLastUpdateState;
        int i2 = this.mState;
        if (i != i2) {
            this.mLastUpdateState = i2;
            if (i2 == 1) {
                this.mAlertParams.mPositiveButtonText = getString(C1715R.string.allow);
                AlertController.AlertParams alertParams = this.mAlertParams;
                alertParams.mPositiveButtonListener = this;
                alertParams.mNegativeButtonText = getString(C1715R.string.deny);
                AlertController.AlertParams alertParams2 = this.mAlertParams;
                alertParams2.mNegativeButtonListener = this;
                alertParams2.mMessage = getString(C1715R.string.wifi_ask_enable, new Object[]{this.mAppLabel});
            } else if (i2 == 2) {
                this.mAlert.setButton(-1, (CharSequence) null, (DialogInterface.OnClickListener) null, (Message) null);
                this.mAlert.setButton(-2, (CharSequence) null, (DialogInterface.OnClickListener) null, (Message) null);
                AlertController.AlertParams alertParams3 = this.mAlertParams;
                alertParams3.mPositiveButtonText = null;
                alertParams3.mPositiveButtonListener = null;
                alertParams3.mNegativeButtonText = null;
                alertParams3.mNegativeButtonListener = null;
                alertParams3.mMessage = getString(C1715R.string.wifi_starting);
            } else if (i2 == 3) {
                this.mAlertParams.mPositiveButtonText = getString(C1715R.string.allow);
                AlertController.AlertParams alertParams4 = this.mAlertParams;
                alertParams4.mPositiveButtonListener = this;
                alertParams4.mNegativeButtonText = getString(C1715R.string.deny);
                AlertController.AlertParams alertParams5 = this.mAlertParams;
                alertParams5.mNegativeButtonListener = this;
                alertParams5.mMessage = getString(C1715R.string.wifi_ask_disable, new Object[]{this.mAppLabel});
            } else if (i2 == 4) {
                this.mAlert.setButton(-1, (CharSequence) null, (DialogInterface.OnClickListener) null, (Message) null);
                this.mAlert.setButton(-2, (CharSequence) null, (DialogInterface.OnClickListener) null, (Message) null);
                AlertController.AlertParams alertParams6 = this.mAlertParams;
                alertParams6.mPositiveButtonText = null;
                alertParams6.mPositiveButtonListener = null;
                alertParams6.mNegativeButtonText = null;
                alertParams6.mNegativeButtonListener = null;
                alertParams6.mMessage = getString(C1715R.string.wifi_stopping);
            }
            setupAlert();
        }
    }

    private void scheduleToggleTimeout() {
        getWindow().getDecorView().postDelayed(this.mTimeoutCommand, 10000);
    }

    private void unscheduleToggleTimeout() {
        getWindow().getDecorView().removeCallbacks(this.mTimeoutCommand);
    }

    private final class StateChangeReceiver extends BroadcastReceiver {
        private final IntentFilter mFilter;

        private StateChangeReceiver() {
            this.mFilter = new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED");
        }

        public void register() {
            RequestToggleWiFiActivity.this.registerReceiver(this, this.mFilter);
        }

        public void unregister() {
            RequestToggleWiFiActivity.this.unregisterReceiver(this);
        }

        /* JADX WARNING: type inference failed for: r2v1, types: [com.android.settings.wifi.RequestToggleWiFiActivity, android.content.Context, android.app.Activity] */
        public void onReceive(Context context, Intent intent) {
            ? r2 = RequestToggleWiFiActivity.this;
            if (!r2.isFinishing() && !r2.isDestroyed()) {
                int wifiState = RequestToggleWiFiActivity.this.mWiFiManager.getWifiState();
                if (wifiState == 0) {
                    Toast.makeText(r2, C1715R.string.wifi_error, 0).show();
                    RequestToggleWiFiActivity.this.finish();
                } else if (wifiState != 1 && wifiState != 3) {
                } else {
                    if (RequestToggleWiFiActivity.this.mState == 2 || RequestToggleWiFiActivity.this.mState == 4) {
                        RequestToggleWiFiActivity.this.setResult(-1);
                        RequestToggleWiFiActivity.this.finish();
                    }
                }
            }
        }
    }
}
