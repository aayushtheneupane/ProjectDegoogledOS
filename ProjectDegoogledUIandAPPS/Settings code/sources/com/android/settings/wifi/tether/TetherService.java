package com.android.settings.wifi.tether;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageStatsManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothPan;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.settings.Utils;
import java.util.ArrayList;
import java.util.List;

public class TetherService extends Service {
    /* access modifiers changed from: private */
    public static final boolean DEBUG = Log.isLoggable("TetherService", 3);
    public static final String EXTRA_RESULT = "EntitlementResult";
    public static final String EXTRA_SUBID = "subId";
    /* access modifiers changed from: private */
    public ArrayList<Integer> mCurrentTethers;
    /* access modifiers changed from: private */
    public int mCurrentTypeIndex;
    private HotspotOffReceiver mHotspotReceiver;
    /* access modifiers changed from: private */
    public boolean mInProvisionCheck;
    private ArrayMap<Integer, List<ResultReceiver>> mPendingCallbacks;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TetherService.DEBUG) {
                Log.d("TetherService", "Got provision result " + intent);
            }
            if (!TetherService.this.getResourceForActiveDataSubId().getString(17039801).equals(intent.getAction())) {
                return;
            }
            if (!TetherService.this.mInProvisionCheck) {
                Log.e("TetherService", "Unexpected provision response " + intent);
                return;
            }
            int intValue = ((Integer) TetherService.this.mCurrentTethers.get(TetherService.this.mCurrentTypeIndex)).intValue();
            boolean unused = TetherService.this.mInProvisionCheck = TetherService.DEBUG;
            int intExtra = intent.getIntExtra(TetherService.EXTRA_RESULT, 0);
            if (intExtra != -1) {
                if (intValue == 0) {
                    TetherService.this.disableWifiTethering();
                } else if (intValue == 1) {
                    TetherService.this.disableUsbTethering();
                } else if (intValue == 2) {
                    TetherService.this.disableBtTethering();
                }
            }
            TetherService.this.fireCallbacksForType(intValue, intExtra);
            if (TetherService.access$204(TetherService.this) >= TetherService.this.mCurrentTethers.size()) {
                TetherService.this.stopSelf();
                return;
            }
            TetherService tetherService = TetherService.this;
            tetherService.startProvisioning(tetherService.mCurrentTypeIndex);
        }
    };
    private TetherServiceWrapper mWrapper;

    public IBinder onBind(Intent intent) {
        return null;
    }

    static /* synthetic */ int access$204(TetherService tetherService) {
        int i = tetherService.mCurrentTypeIndex + 1;
        tetherService.mCurrentTypeIndex = i;
        return i;
    }

    public void onCreate() {
        super.onCreate();
        if (DEBUG) {
            Log.d("TetherService", "Creating TetherService");
        }
        registerReceiver(this.mReceiver, new IntentFilter(getResourceForActiveDataSubId().getString(17039801)), "android.permission.CONNECTIVITY_INTERNAL", (Handler) null);
        this.mCurrentTethers = stringToTethers(getSharedPreferences("tetherPrefs", 0).getString("currentTethers", ""));
        this.mCurrentTypeIndex = 0;
        this.mPendingCallbacks = new ArrayMap<>(3);
        this.mPendingCallbacks.put(0, new ArrayList());
        this.mPendingCallbacks.put(1, new ArrayList());
        this.mPendingCallbacks.put(2, new ArrayList());
        this.mHotspotReceiver = new HotspotOffReceiver(this);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        int activeDataSubscriptionId;
        if (!intent.hasExtra(EXTRA_SUBID) || intent.getIntExtra(EXTRA_SUBID, -1) == (activeDataSubscriptionId = getTetherServiceWrapper().getActiveDataSubscriptionId())) {
            if (intent.hasExtra("extraAddTetherType")) {
                int intExtra = intent.getIntExtra("extraAddTetherType", -1);
                ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra("extraProvisionCallback");
                if (resultReceiver != null) {
                    List list = this.mPendingCallbacks.get(Integer.valueOf(intExtra));
                    if (list != null) {
                        list.add(resultReceiver);
                    } else {
                        resultReceiver.send(1, (Bundle) null);
                        stopSelf();
                        return 2;
                    }
                }
                if (!this.mCurrentTethers.contains(Integer.valueOf(intExtra))) {
                    if (DEBUG) {
                        Log.d("TetherService", "Adding tether " + intExtra);
                    }
                    this.mCurrentTethers.add(Integer.valueOf(intExtra));
                }
            }
            if (intent.hasExtra("extraRemTetherType")) {
                if (!this.mInProvisionCheck) {
                    int intExtra2 = intent.getIntExtra("extraRemTetherType", -1);
                    int indexOf = this.mCurrentTethers.indexOf(Integer.valueOf(intExtra2));
                    if (DEBUG) {
                        Log.d("TetherService", "Removing tether " + intExtra2 + ", index " + indexOf);
                    }
                    if (indexOf >= 0) {
                        removeTypeAtIndex(indexOf);
                    }
                    cancelAlarmIfNecessary();
                } else if (DEBUG) {
                    Log.d("TetherService", "Don't cancel alarm during provisioning");
                }
            }
            if (intent.getBooleanExtra("extraSetAlarm", DEBUG) && this.mCurrentTethers.size() == 1) {
                scheduleAlarm();
            }
            if (intent.getBooleanExtra("extraRunProvision", DEBUG)) {
                startProvisioning(this.mCurrentTypeIndex);
                return 3;
            } else if (this.mInProvisionCheck) {
                return 3;
            } else {
                if (DEBUG) {
                    Log.d("TetherService", "Stopping self.  startid: " + i2);
                }
                stopSelf();
                return 2;
            }
        } else {
            Log.e("TetherService", "This Provisioning request is outdated, current subId: " + activeDataSubscriptionId);
            if (!this.mInProvisionCheck) {
                stopSelf();
            }
            return 2;
        }
    }

    public void onDestroy() {
        if (this.mInProvisionCheck) {
            Log.e("TetherService", "TetherService getting destroyed while mid-provisioning" + this.mCurrentTethers.get(this.mCurrentTypeIndex));
        }
        getSharedPreferences("tetherPrefs", 0).edit().putString("currentTethers", tethersToString(this.mCurrentTethers)).commit();
        unregisterReceivers();
        if (DEBUG) {
            Log.d("TetherService", "Destroying TetherService");
        }
        super.onDestroy();
    }

    private void unregisterReceivers() {
        unregisterReceiver(this.mReceiver);
        this.mHotspotReceiver.unregister();
    }

    private void removeTypeAtIndex(int i) {
        this.mCurrentTethers.remove(i);
        if (DEBUG) {
            Log.d("TetherService", "mCurrentTypeIndex: " + this.mCurrentTypeIndex);
        }
        int i2 = this.mCurrentTypeIndex;
        if (i <= i2 && i2 > 0) {
            this.mCurrentTypeIndex = i2 - 1;
        }
    }

    /* access modifiers changed from: package-private */
    public void setHotspotOffReceiver(HotspotOffReceiver hotspotOffReceiver) {
        this.mHotspotReceiver = hotspotOffReceiver;
    }

    private ArrayList<Integer> stringToTethers(String str) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        String[] split = str.split(",");
        for (String parseInt : split) {
            arrayList.add(Integer.valueOf(Integer.parseInt(parseInt)));
        }
        return arrayList;
    }

    private String tethersToString(ArrayList<Integer> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuffer.append(',');
            }
            stringBuffer.append(arrayList.get(i));
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: private */
    public void disableWifiTethering() {
        ((ConnectivityManager) getSystemService("connectivity")).stopTethering(0);
    }

    /* access modifiers changed from: private */
    public void disableUsbTethering() {
        ((ConnectivityManager) getSystemService("connectivity")).setUsbTethering(DEBUG);
    }

    /* access modifiers changed from: private */
    public void disableBtTethering() {
        final BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.getProfileProxy(this, new BluetoothProfile.ServiceListener() {
                public void onServiceDisconnected(int i) {
                }

                public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                    ((BluetoothPan) bluetoothProfile).setBluetoothTethering(TetherService.DEBUG);
                    defaultAdapter.closeProfileProxy(5, bluetoothProfile);
                }
            }, 5);
        }
    }

    /* access modifiers changed from: private */
    public void startProvisioning(int i) {
        if (i < this.mCurrentTethers.size()) {
            Intent provisionBroadcastIntent = getProvisionBroadcastIntent(i);
            setEntitlementAppActive(i);
            if (DEBUG) {
                Log.d("TetherService", "Sending provisioning broadcast: " + provisionBroadcastIntent.getAction() + " type: " + this.mCurrentTethers.get(i));
            }
            sendBroadcast(provisionBroadcastIntent);
            this.mInProvisionCheck = true;
        }
    }

    private Intent getProvisionBroadcastIntent(int i) {
        String string = getResourceForActiveDataSubId().getString(17039800);
        int activeDataSubscriptionId = getTetherServiceWrapper().getActiveDataSubscriptionId();
        Intent intent = new Intent(string);
        intent.putExtra("TETHER_TYPE", this.mCurrentTethers.get(i).intValue());
        intent.putExtra("android.telephony.extra.SUBSCRIPTION_INDEX", activeDataSubscriptionId);
        intent.setFlags(285212672);
        return intent;
    }

    private void setEntitlementAppActive(int i) {
        List<ResolveInfo> queryBroadcastReceivers = getPackageManager().queryBroadcastReceivers(getProvisionBroadcastIntent(i), 131072);
        if (queryBroadcastReceivers.isEmpty()) {
            Log.e("TetherService", "No found BroadcastReceivers for provision intent.");
            return;
        }
        for (ResolveInfo next : queryBroadcastReceivers) {
            if (next.activityInfo.applicationInfo.isSystemApp()) {
                getTetherServiceWrapper().setAppInactive(next.activityInfo.packageName, DEBUG);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void scheduleAlarm() {
        Intent intent = new Intent(this, TetherService.class);
        intent.putExtra("extraRunProvision", true);
        PendingIntent service = PendingIntent.getService(this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService("alarm");
        long integer = (long) (getResourceForActiveDataSubId().getInteger(17694865) * 3600000);
        long elapsedRealtime = SystemClock.elapsedRealtime() + integer;
        if (DEBUG) {
            Log.d("TetherService", "Scheduling alarm at interval " + integer);
        }
        alarmManager.setRepeating(3, elapsedRealtime, integer, service);
        this.mHotspotReceiver.register();
    }

    public static void cancelRecheckAlarmIfNecessary(Context context, int i) {
        Intent intent = new Intent(context, TetherService.class);
        intent.putExtra("extraRemTetherType", i);
        context.startService(intent);
    }

    /* access modifiers changed from: package-private */
    public void cancelAlarmIfNecessary() {
        if (this.mCurrentTethers.size() == 0) {
            ((AlarmManager) getSystemService("alarm")).cancel(PendingIntent.getService(this, 0, new Intent(this, TetherService.class), 0));
            if (DEBUG) {
                Log.d("TetherService", "Tethering no longer active, canceling recheck");
            }
            this.mHotspotReceiver.unregister();
        } else if (DEBUG) {
            Log.d("TetherService", "Tethering still active, not cancelling alarm");
        }
    }

    /* access modifiers changed from: private */
    public void fireCallbacksForType(int i, int i2) {
        List<ResultReceiver> list = this.mPendingCallbacks.get(Integer.valueOf(i));
        if (list != null) {
            int i3 = i2 == -1 ? 0 : 11;
            for (ResultReceiver resultReceiver : list) {
                if (DEBUG) {
                    Log.d("TetherService", "Firing result: " + i3 + " to callback");
                }
                resultReceiver.send(i3, (Bundle) null);
            }
            list.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void setTetherServiceWrapper(TetherServiceWrapper tetherServiceWrapper) {
        this.mWrapper = tetherServiceWrapper;
    }

    private TetherServiceWrapper getTetherServiceWrapper() {
        if (this.mWrapper == null) {
            this.mWrapper = new TetherServiceWrapper(this);
        }
        return this.mWrapper;
    }

    public static class TetherServiceWrapper {
        private final UsageStatsManager mUsageStatsManager;

        TetherServiceWrapper(Context context) {
            this.mUsageStatsManager = (UsageStatsManager) context.getSystemService("usagestats");
        }

        /* access modifiers changed from: package-private */
        public void setAppInactive(String str, boolean z) {
            this.mUsageStatsManager.setAppInactive(str, z);
        }

        /* access modifiers changed from: package-private */
        public int getActiveDataSubscriptionId() {
            return SubscriptionManager.getActiveDataSubscriptionId();
        }
    }

    /* access modifiers changed from: package-private */
    public Resources getResourceForActiveDataSubId() {
        return Utils.getResourcesForSubId(this, getTetherServiceWrapper().getActiveDataSubscriptionId());
    }
}
