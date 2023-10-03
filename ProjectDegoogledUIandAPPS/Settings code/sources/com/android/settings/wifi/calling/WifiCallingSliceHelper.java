package com.android.settings.wifi.calling;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.ims.ImsManager;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.SliceBroadcastReceiver;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class WifiCallingSliceHelper {
    private final Context mContext;

    public WifiCallingSliceHelper(Context context) {
        this.mContext = context;
    }

    public Slice createWifiCallingSlice(Uri uri) {
        int defaultVoiceSubId = getDefaultVoiceSubId();
        if (defaultVoiceSubId <= -1) {
            Log.d("WifiCallingSliceHelper", "Invalid subscription Id");
            return null;
        }
        ImsManager imsManager = getImsManager(defaultVoiceSubId);
        if (!imsManager.isWfcEnabledByPlatform() || !imsManager.isWfcProvisionedOnDevice()) {
            Log.d("WifiCallingSliceHelper", "Wifi calling is either not provisioned or not enabled by Platform");
            return null;
        }
        try {
            boolean isWifiCallingEnabled = isWifiCallingEnabled(imsManager);
            if (getWifiCallingCarrierActivityIntent(defaultVoiceSubId) == null || isWifiCallingEnabled) {
                return getWifiCallingSlice(uri, isWifiCallingEnabled);
            }
            Log.d("WifiCallingSliceHelper", "Needs Activation");
            return getNonActionableWifiCallingSlice(this.mContext.getText(C1715R.string.wifi_calling_settings_title), this.mContext.getText(C1715R.string.wifi_calling_settings_activation_instructions), uri, getActivityIntent("android.settings.WIFI_CALLING_SETTINGS"));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.e("WifiCallingSliceHelper", "Unable to read the current WiFi calling status", e);
            return null;
        }
    }

    private boolean isWifiCallingEnabled(final ImsManager imsManager) throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask futureTask = new FutureTask(new Callable<Boolean>() {
            public Boolean call() {
                return Boolean.valueOf(imsManager.isWfcEnabledByUser());
            }
        });
        Executors.newSingleThreadExecutor().execute(futureTask);
        return ((Boolean) futureTask.get(2000, TimeUnit.MILLISECONDS)).booleanValue() && imsManager.isNonTtyOrTtyOnVolteEnabled();
    }

    private Slice getWifiCallingSlice(Uri uri, boolean z) {
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.wifi_signal);
        ListBuilder listBuilder = new ListBuilder(this.mContext, uri, -1);
        listBuilder.setAccentColor(Utils.getColorAccentDefaultColor(this.mContext));
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitle(this.mContext.getText(C1715R.string.wifi_calling_settings_title));
        rowBuilder.addEndItem(SliceAction.createToggle(getBroadcastIntent("com.android.settings.wifi.calling.action.WIFI_CALLING_CHANGED"), (CharSequence) null, z));
        rowBuilder.setPrimaryAction(SliceAction.createDeeplink(getActivityIntent("android.settings.WIFI_CALLING_SETTINGS"), createWithResource, 0, this.mContext.getText(C1715R.string.wifi_calling_settings_title)));
        listBuilder.addRow(rowBuilder);
        return listBuilder.build();
    }

    public Slice createWifiCallingPreferenceSlice(Uri uri) {
        int defaultVoiceSubId = getDefaultVoiceSubId();
        if (defaultVoiceSubId <= -1) {
            Log.d("WifiCallingSliceHelper", "Invalid Subscription Id");
            return null;
        }
        boolean isCarrierConfigManagerKeyEnabled = isCarrierConfigManagerKeyEnabled("editable_wfc_mode_bool", defaultVoiceSubId, false);
        boolean isCarrierConfigManagerKeyEnabled2 = isCarrierConfigManagerKeyEnabled("carrier_wfc_supports_wifi_only_bool", defaultVoiceSubId, true);
        ImsManager imsManager = getImsManager(defaultVoiceSubId);
        if (!imsManager.isWfcEnabledByPlatform() || !imsManager.isWfcProvisionedOnDevice()) {
            Log.d("WifiCallingSliceHelper", "Wifi calling is either not provisioned or not enabled by platform");
            return null;
        } else if (!isCarrierConfigManagerKeyEnabled) {
            Log.d("WifiCallingSliceHelper", "Wifi calling preference is not editable");
            return null;
        } else {
            try {
                boolean isWifiCallingEnabled = isWifiCallingEnabled(imsManager);
                int wfcMode = getWfcMode(imsManager);
                if (!isWifiCallingEnabled) {
                    return getNonActionableWifiCallingSlice(this.mContext.getText(C1715R.string.wifi_calling_mode_title), this.mContext.getText(C1715R.string.wifi_calling_turn_on), uri, getActivityIntent("android.settings.WIFI_CALLING_SETTINGS"));
                }
                return getWifiCallingPreferenceSlice(isCarrierConfigManagerKeyEnabled2, wfcMode, uri);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.e("WifiCallingSliceHelper", "Unable to get wifi calling preferred mode", e);
                return null;
            }
        }
    }

    private Slice getWifiCallingPreferenceSlice(boolean z, int i, Uri uri) {
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.wifi_signal);
        ListBuilder listBuilder = new ListBuilder(this.mContext, uri, -1);
        listBuilder.setAccentColor(Utils.getColorAccentDefaultColor(this.mContext));
        ListBuilder.HeaderBuilder headerBuilder = new ListBuilder.HeaderBuilder();
        headerBuilder.setTitle(this.mContext.getText(C1715R.string.wifi_calling_mode_title));
        headerBuilder.setSubtitle(getWifiCallingPreferenceSummary(i));
        headerBuilder.setPrimaryAction(SliceAction.createDeeplink(getActivityIntent("android.settings.WIFI_CALLING_SETTINGS"), createWithResource, 0, this.mContext.getText(C1715R.string.wifi_calling_mode_title)));
        listBuilder.setHeader(headerBuilder);
        boolean z2 = true;
        if (z) {
            listBuilder.addRow(wifiPreferenceRowBuilder(listBuilder, 17041329, "com.android.settings.slice.action.WIFI_CALLING_PREFERENCE_WIFI_ONLY", i == 0));
        }
        listBuilder.addRow(wifiPreferenceRowBuilder(listBuilder, 17041330, "com.android.settings.slice.action.WIFI_CALLING_PREFERENCE_WIFI_PREFERRED", i == 2));
        if (i != 1) {
            z2 = false;
        }
        listBuilder.addRow(wifiPreferenceRowBuilder(listBuilder, 17041327, "com.android.settings.slice.action.WIFI_CALLING_PREFERENCE_CELLULAR_PREFERRED", z2));
        return listBuilder.build();
    }

    private ListBuilder.RowBuilder wifiPreferenceRowBuilder(ListBuilder listBuilder, int i, String str, boolean z) {
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.radio_button_check);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitle(this.mContext.getText(i));
        rowBuilder.setTitleItem(SliceAction.createToggle(getBroadcastIntent(str), createWithResource, this.mContext.getText(i), z));
        return rowBuilder;
    }

    private CharSequence getWifiCallingPreferenceSummary(int i) {
        if (i == 0) {
            return this.mContext.getText(17041329);
        }
        if (i == 1) {
            return this.mContext.getText(17041327);
        }
        if (i != 2) {
            return null;
        }
        return this.mContext.getText(17041330);
    }

    /* access modifiers changed from: protected */
    public ImsManager getImsManager(int i) {
        return ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(i));
    }

    private int getWfcMode(final ImsManager imsManager) throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask futureTask = new FutureTask(new Callable<Integer>() {
            public Integer call() {
                return Integer.valueOf(imsManager.getWfcMode(false));
            }
        });
        Executors.newSingleThreadExecutor().execute(futureTask);
        return ((Integer) futureTask.get(2000, TimeUnit.MILLISECONDS)).intValue();
    }

    public void handleWifiCallingChanged(Intent intent) {
        int defaultVoiceSubId = getDefaultVoiceSubId();
        if (defaultVoiceSubId > -1) {
            ImsManager imsManager = getImsManager(defaultVoiceSubId);
            if (imsManager.isWfcEnabledByPlatform() && imsManager.isWfcProvisionedOnDevice()) {
                boolean z = imsManager.isWfcEnabledByUser() && imsManager.isNonTtyOrTtyOnVolteEnabled();
                boolean booleanExtra = intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", z);
                Intent wifiCallingCarrierActivityIntent = getWifiCallingCarrierActivityIntent(defaultVoiceSubId);
                if ((!booleanExtra || wifiCallingCarrierActivityIntent == null) && booleanExtra != z) {
                    imsManager.setWfcSetting(booleanExtra);
                }
            }
        }
        this.mContext.getContentResolver().notifyChange(CustomSliceRegistry.WIFI_CALLING_URI, (ContentObserver) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0073, code lost:
        if (r10 != true) goto L_0x007c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleWifiCallingPreferenceChanged(android.content.Intent r10) {
        /*
            r9 = this;
            int r0 = r9.getDefaultVoiceSubId()
            r1 = -1
            if (r0 <= r1) goto L_0x0084
            r2 = 0
            java.lang.String r3 = "editable_wfc_mode_bool"
            boolean r3 = r9.isCarrierConfigManagerKeyEnabled(r3, r0, r2)
            r4 = 1
            java.lang.String r5 = "carrier_wfc_supports_wifi_only_bool"
            boolean r5 = r9.isCarrierConfigManagerKeyEnabled(r5, r0, r4)
            com.android.ims.ImsManager r0 = r9.getImsManager(r0)
            if (r3 == 0) goto L_0x0084
            boolean r3 = r0.isWfcEnabledByPlatform()
            if (r3 == 0) goto L_0x0084
            boolean r3 = r0.isWfcProvisionedOnDevice()
            if (r3 == 0) goto L_0x0084
            boolean r3 = r0.isWfcEnabledByUser()
            if (r3 == 0) goto L_0x0084
            boolean r3 = r0.isNonTtyOrTtyOnVolteEnabled()
            if (r3 == 0) goto L_0x0084
            int r3 = r0.getWfcMode(r2)
            java.lang.String r10 = r10.getAction()
            int r6 = r10.hashCode()
            r7 = -86230637(0xfffffffffadc3993, float:-5.7173653E35)
            r8 = 2
            if (r6 == r7) goto L_0x0064
            r7 = 176882490(0xa8b033a, float:1.3386427E-32)
            if (r6 == r7) goto L_0x005a
            r7 = 495970216(0x1d8fe7a8, float:3.809131E-21)
            if (r6 == r7) goto L_0x0050
            goto L_0x006e
        L_0x0050:
            java.lang.String r6 = "com.android.settings.slice.action.WIFI_CALLING_PREFERENCE_CELLULAR_PREFERRED"
            boolean r10 = r10.equals(r6)
            if (r10 == 0) goto L_0x006e
            r10 = r8
            goto L_0x006f
        L_0x005a:
            java.lang.String r6 = "com.android.settings.slice.action.WIFI_CALLING_PREFERENCE_WIFI_ONLY"
            boolean r10 = r10.equals(r6)
            if (r10 == 0) goto L_0x006e
            r10 = r2
            goto L_0x006f
        L_0x0064:
            java.lang.String r6 = "com.android.settings.slice.action.WIFI_CALLING_PREFERENCE_WIFI_PREFERRED"
            boolean r10 = r10.equals(r6)
            if (r10 == 0) goto L_0x006e
            r10 = r4
            goto L_0x006f
        L_0x006e:
            r10 = r1
        L_0x006f:
            if (r10 == 0) goto L_0x0078
            if (r10 == r4) goto L_0x0076
            if (r10 == r8) goto L_0x007d
            goto L_0x007c
        L_0x0076:
            r4 = r8
            goto L_0x007d
        L_0x0078:
            if (r5 == 0) goto L_0x007c
            r4 = r2
            goto L_0x007d
        L_0x007c:
            r4 = r1
        L_0x007d:
            if (r4 == r1) goto L_0x0084
            if (r4 == r3) goto L_0x0084
            r0.setWfcMode(r4, r2)
        L_0x0084:
            android.content.Context r9 = r9.mContext
            android.content.ContentResolver r9 = r9.getContentResolver()
            android.net.Uri r10 = com.android.settings.slices.CustomSliceRegistry.WIFI_CALLING_PREFERENCE_URI
            r0 = 0
            r9.notifyChange(r10, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.wifi.calling.WifiCallingSliceHelper.handleWifiCallingPreferenceChanged(android.content.Intent):void");
    }

    private Slice getNonActionableWifiCallingSlice(CharSequence charSequence, CharSequence charSequence2, Uri uri, PendingIntent pendingIntent) {
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.wifi_signal);
        ListBuilder listBuilder = new ListBuilder(this.mContext, uri, -1);
        listBuilder.setAccentColor(Utils.getColorAccentDefaultColor(this.mContext));
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitle(charSequence);
        rowBuilder.setSubtitle(charSequence2);
        rowBuilder.setPrimaryAction(SliceAction.createDeeplink(pendingIntent, createWithResource, 1, charSequence));
        listBuilder.addRow(rowBuilder);
        return listBuilder.build();
    }

    /* access modifiers changed from: protected */
    public boolean isCarrierConfigManagerKeyEnabled(String str, int i, boolean z) {
        PersistableBundle configForSubId;
        CarrierConfigManager carrierConfigManager = getCarrierConfigManager(this.mContext);
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(i)) == null) {
            return false;
        }
        return configForSubId.getBoolean(str, z);
    }

    /* access modifiers changed from: protected */
    public CarrierConfigManager getCarrierConfigManager(Context context) {
        return (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
    }

    /* access modifiers changed from: protected */
    public int getDefaultVoiceSubId() {
        return SubscriptionManager.getDefaultVoiceSubscriptionId();
    }

    /* access modifiers changed from: protected */
    public Intent getWifiCallingCarrierActivityIntent(int i) {
        PersistableBundle configForSubId;
        ComponentName unflattenFromString;
        CarrierConfigManager carrierConfigManager = getCarrierConfigManager(this.mContext);
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(i)) == null) {
            return null;
        }
        String string = configForSubId.getString("wfc_emergency_address_carrier_app_string");
        if (TextUtils.isEmpty(string) || (unflattenFromString = ComponentName.unflattenFromString(string)) == null) {
            return null;
        }
        Intent intent = new Intent();
        intent.setComponent(unflattenFromString);
        return intent;
    }

    private PendingIntent getBroadcastIntent(String str) {
        Intent intent = new Intent(str);
        intent.setClass(this.mContext, SliceBroadcastReceiver.class);
        intent.addFlags(268435456);
        return PendingIntent.getBroadcast(this.mContext, 0, intent, 268435456);
    }

    private PendingIntent getActivityIntent(String str) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        return PendingIntent.getActivity(this.mContext, 0, intent, 0);
    }
}
