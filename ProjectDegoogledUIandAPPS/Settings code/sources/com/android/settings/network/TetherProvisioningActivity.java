package com.android.settings.network;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.os.UserHandle;
import android.telephony.SubscriptionManager;
import android.util.Log;
import com.android.settings.Utils;
import com.android.settings.wifi.tether.TetherService;

public class TetherProvisioningActivity extends Activity {
    private static final boolean DEBUG = Log.isLoggable("TetherProvisioningAct", 3);
    private ResultReceiver mResultReceiver;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mResultReceiver = (ResultReceiver) getIntent().getParcelableExtra("extraProvisionCallback");
        int intExtra = getIntent().getIntExtra("extraAddTetherType", -1);
        int intExtra2 = getIntent().getIntExtra(TetherService.EXTRA_SUBID, -1);
        int activeDataSubscriptionId = SubscriptionManager.getActiveDataSubscriptionId();
        if (intExtra2 != activeDataSubscriptionId) {
            Log.e("TetherProvisioningAct", "This Provisioning request is outdated, current subId: " + activeDataSubscriptionId);
            return;
        }
        String[] stringArray = Utils.getResourcesForSubId(this, activeDataSubscriptionId).getStringArray(17236057);
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName(stringArray[0], stringArray[1]);
        intent.putExtra("TETHER_TYPE", intExtra);
        intent.putExtra("android.telephony.extra.SUBSCRIPTION_INDEX", activeDataSubscriptionId);
        if (DEBUG) {
            Log.d("TetherProvisioningAct", "Starting provisioning app: " + stringArray[0] + "." + stringArray[1]);
        }
        if (getPackageManager().queryIntentActivities(intent, 65536).isEmpty()) {
            Log.e("TetherProvisioningAct", "Provisioning app is configured, but not available.");
            this.mResultReceiver.send(11, (Bundle) null);
            finish();
            return;
        }
        startActivityForResultAsUser(intent, 0, UserHandle.CURRENT);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0) {
            if (DEBUG) {
                Log.d("TetherProvisioningAct", "Got result from app: " + i2);
            }
            this.mResultReceiver.send(i2 == -1 ? 0 : 11, (Bundle) null);
            finish();
        }
    }
}
