package com.android.settings.network.telephony;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
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

public class Enhanced4gLteSliceHelper {
    private final Context mContext;

    public Enhanced4gLteSliceHelper(Context context) {
        this.mContext = context;
    }

    public Slice createEnhanced4gLteSlice(Uri uri) {
        int defaultVoiceSubId = getDefaultVoiceSubId();
        if (defaultVoiceSubId <= -1) {
            Log.d("Enhanced4gLteSlice", "Invalid subscription Id");
            return null;
        }
        ImsManager imsManager = getImsManager(defaultVoiceSubId);
        if (!imsManager.isVolteEnabledByPlatform() || !imsManager.isVolteProvisionedOnDevice()) {
            Log.d("Enhanced4gLteSlice", "Setting is either not provisioned or not enabled by Platform");
            return null;
        } else if (isCarrierConfigManagerKeyEnabled("hide_enhanced_4g_lte_bool", defaultVoiceSubId, false) || !isCarrierConfigManagerKeyEnabled("editable_enhanced_4g_lte_bool", defaultVoiceSubId, true)) {
            Log.d("Enhanced4gLteSlice", "Setting is either hidden or not editable");
            return null;
        } else {
            try {
                return getEnhanced4gLteSlice(uri, isEnhanced4gLteModeEnabled(imsManager), defaultVoiceSubId);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.e("Enhanced4gLteSlice", "Unable to read the current Enhanced 4g LTE status", e);
                return null;
            }
        }
    }

    private boolean isEnhanced4gLteModeEnabled(final ImsManager imsManager) throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask futureTask = new FutureTask(new Callable<Boolean>() {
            public Boolean call() {
                return Boolean.valueOf(imsManager.isEnhanced4gLteModeSettingEnabledByUser());
            }
        });
        Executors.newSingleThreadExecutor().execute(futureTask);
        return ((Boolean) futureTask.get(2000, TimeUnit.MILLISECONDS)).booleanValue();
    }

    private Slice getEnhanced4gLteSlice(Uri uri, boolean z, int i) {
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.ic_launcher_settings);
        ListBuilder listBuilder = new ListBuilder(this.mContext, uri, -1);
        listBuilder.setAccentColor(Utils.getColorAccentDefaultColor(this.mContext));
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitle(getEnhanced4glteModeTitle(i));
        rowBuilder.addEndItem(SliceAction.createToggle(getBroadcastIntent("com.android.settings.mobilenetwork.action.ENHANCED_4G_LTE_CHANGED"), (CharSequence) null, z));
        rowBuilder.setPrimaryAction(SliceAction.createDeeplink(getActivityIntent("android.settings.NETWORK_OPERATOR_SETTINGS"), createWithResource, 0, getEnhanced4glteModeTitle(i)));
        listBuilder.addRow(rowBuilder);
        return listBuilder.build();
    }

    /* access modifiers changed from: protected */
    public ImsManager getImsManager(int i) {
        return ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(i));
    }

    public void handleEnhanced4gLteChanged(Intent intent) {
        int defaultVoiceSubId = getDefaultVoiceSubId();
        if (defaultVoiceSubId > -1) {
            ImsManager imsManager = getImsManager(defaultVoiceSubId);
            if (imsManager.isVolteEnabledByPlatform() && imsManager.isVolteProvisionedOnDevice()) {
                boolean z = imsManager.isEnhanced4gLteModeSettingEnabledByUser() && imsManager.isNonTtyOrTtyOnVolteEnabled();
                boolean booleanExtra = intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", z);
                if (booleanExtra != z) {
                    imsManager.setEnhanced4gLteModeSetting(booleanExtra);
                }
            }
        }
        this.mContext.getContentResolver().notifyChange(CustomSliceRegistry.ENHANCED_4G_SLICE_URI, (ContentObserver) null);
    }

    private CharSequence getEnhanced4glteModeTitle(int i) {
        CharSequence text = this.mContext.getText(C1715R.string.enhanced_4g_lte_mode_title);
        try {
            if (!isCarrierConfigManagerKeyEnabled("enhanced_4g_lte_title_variant_bool", i, false)) {
                return text;
            }
            Resources resourcesForApplication = this.mContext.getPackageManager().getResourcesForApplication("com.android.phone");
            return resourcesForApplication.getText(resourcesForApplication.getIdentifier("enhanced_4g_lte_mode_title_variant", "string", "com.android.phone"));
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("Enhanced4gLteSlice", "package name not found");
            return text;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r0.getConfigForSubId(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isCarrierConfigManagerKeyEnabled(java.lang.String r1, int r2, boolean r3) {
        /*
            r0 = this;
            android.telephony.CarrierConfigManager r0 = r0.getCarrierConfigManager()
            if (r0 == 0) goto L_0x0010
            android.os.PersistableBundle r0 = r0.getConfigForSubId(r2)
            if (r0 == 0) goto L_0x0010
            boolean r3 = r0.getBoolean(r1, r3)
        L_0x0010:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.network.telephony.Enhanced4gLteSliceHelper.isCarrierConfigManagerKeyEnabled(java.lang.String, int, boolean):boolean");
    }

    /* access modifiers changed from: protected */
    public CarrierConfigManager getCarrierConfigManager() {
        return (CarrierConfigManager) this.mContext.getSystemService(CarrierConfigManager.class);
    }

    private PendingIntent getBroadcastIntent(String str) {
        Intent intent = new Intent(str);
        intent.setClass(this.mContext, SliceBroadcastReceiver.class);
        return PendingIntent.getBroadcast(this.mContext, 0, intent, 268435456);
    }

    /* access modifiers changed from: protected */
    public int getDefaultVoiceSubId() {
        return SubscriptionManager.getDefaultVoiceSubscriptionId();
    }

    private PendingIntent getActivityIntent(String str) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        return PendingIntent.getActivity(this.mContext, 0, intent, 0);
    }
}
