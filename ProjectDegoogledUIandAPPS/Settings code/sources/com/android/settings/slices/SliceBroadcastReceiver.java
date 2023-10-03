package com.android.settings.slices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.SliderPreferenceController;
import com.android.settings.core.TogglePreferenceController;
import com.android.settings.overlay.FeatureFactory;

public class SliceBroadcastReceiver extends BroadcastReceiver {
    private static String TAG = "SettSliceBroadcastRec";

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r7, android.content.Intent r8) {
        /*
            r6 = this;
            java.lang.String r0 = r8.getAction()
            java.lang.String r1 = "com.android.settings.slice.extra.key"
            java.lang.String r1 = r8.getStringExtra(r1)
            r2 = 0
            java.lang.String r3 = "com.android.settings.slice.extra.platform"
            boolean r3 = r8.getBooleanExtra(r3, r2)
            boolean r4 = com.android.settings.slices.CustomSliceRegistry.isValidAction(r0)
            if (r4 == 0) goto L_0x0027
            android.net.Uri r6 = android.net.Uri.parse(r0)
            java.lang.Class r6 = com.android.settings.slices.CustomSliceRegistry.getSliceClassByUri(r6)
            com.android.settings.slices.CustomSliceable r6 = com.android.settings.slices.CustomSliceable.createInstance(r7, r6)
            r6.onNotifyChange(r8)
            return
        L_0x0027:
            int r4 = r0.hashCode()
            r5 = -1
            switch(r4) {
                case -2075790298: goto L_0x008d;
                case -2013863560: goto L_0x0083;
                case -932197342: goto L_0x0079;
                case -362341757: goto L_0x006f;
                case -86230637: goto L_0x0065;
                case 17552563: goto L_0x005b;
                case 176882490: goto L_0x0051;
                case 495970216: goto L_0x0046;
                case 902935346: goto L_0x003b;
                case 1913359032: goto L_0x0031;
                default: goto L_0x002f;
            }
        L_0x002f:
            goto L_0x0097
        L_0x0031:
            java.lang.String r4 = "com.android.settings.notification.ZEN_MODE_CHANGED"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0097
            r0 = 4
            goto L_0x0098
        L_0x003b:
            java.lang.String r4 = "com.android.settings.slice.action.COPY"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0097
            r0 = 9
            goto L_0x0098
        L_0x0046:
            java.lang.String r4 = "com.android.settings.slice.action.WIFI_CALLING_PREFERENCE_CELLULAR_PREFERRED"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0097
            r0 = 8
            goto L_0x0098
        L_0x0051:
            java.lang.String r4 = "com.android.settings.slice.action.WIFI_CALLING_PREFERENCE_WIFI_ONLY"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0097
            r0 = 6
            goto L_0x0098
        L_0x005b:
            java.lang.String r4 = "com.android.settings.slice.action.SLIDER_CHANGED"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0097
            r0 = 1
            goto L_0x0098
        L_0x0065:
            java.lang.String r4 = "com.android.settings.slice.action.WIFI_CALLING_PREFERENCE_WIFI_PREFERRED"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0097
            r0 = 7
            goto L_0x0098
        L_0x006f:
            java.lang.String r4 = "com.android.settings.wifi.calling.action.WIFI_CALLING_CHANGED"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0097
            r0 = 3
            goto L_0x0098
        L_0x0079:
            java.lang.String r4 = "com.android.settings.bluetooth.action.BLUETOOTH_MODE_CHANGED"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0097
            r0 = 2
            goto L_0x0098
        L_0x0083:
            java.lang.String r4 = "com.android.settings.mobilenetwork.action.ENHANCED_4G_LTE_CHANGED"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0097
            r0 = 5
            goto L_0x0098
        L_0x008d:
            java.lang.String r4 = "com.android.settings.slice.action.TOGGLE_CHANGED"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0097
            r0 = r2
            goto L_0x0098
        L_0x0097:
            r0 = r5
        L_0x0098:
            switch(r0) {
                case 0: goto L_0x00e2;
                case 1: goto L_0x00d8;
                case 2: goto L_0x00d4;
                case 3: goto L_0x00c4;
                case 4: goto L_0x00c0;
                case 5: goto L_0x00b0;
                case 6: goto L_0x00a0;
                case 7: goto L_0x00a0;
                case 8: goto L_0x00a0;
                case 9: goto L_0x009c;
                default: goto L_0x009b;
            }
        L_0x009b:
            goto L_0x00eb
        L_0x009c:
            r6.handleCopyAction(r7, r1, r3)
            goto L_0x00eb
        L_0x00a0:
            com.android.settings.overlay.FeatureFactory r6 = com.android.settings.overlay.FeatureFactory.getFactory(r7)
            com.android.settings.slices.SlicesFeatureProvider r6 = r6.getSlicesFeatureProvider()
            com.android.settings.wifi.calling.WifiCallingSliceHelper r6 = r6.getNewWifiCallingSliceHelper(r7)
            r6.handleWifiCallingPreferenceChanged(r8)
            goto L_0x00eb
        L_0x00b0:
            com.android.settings.overlay.FeatureFactory r6 = com.android.settings.overlay.FeatureFactory.getFactory(r7)
            com.android.settings.slices.SlicesFeatureProvider r6 = r6.getSlicesFeatureProvider()
            com.android.settings.network.telephony.Enhanced4gLteSliceHelper r6 = r6.getNewEnhanced4gLteSliceHelper(r7)
            r6.handleEnhanced4gLteChanged(r8)
            goto L_0x00eb
        L_0x00c0:
            com.android.settings.notification.ZenModeSliceBuilder.handleUriChange(r7, r8)
            goto L_0x00eb
        L_0x00c4:
            com.android.settings.overlay.FeatureFactory r6 = com.android.settings.overlay.FeatureFactory.getFactory(r7)
            com.android.settings.slices.SlicesFeatureProvider r6 = r6.getSlicesFeatureProvider()
            com.android.settings.wifi.calling.WifiCallingSliceHelper r6 = r6.getNewWifiCallingSliceHelper(r7)
            r6.handleWifiCallingChanged(r8)
            goto L_0x00eb
        L_0x00d4:
            com.android.settings.bluetooth.BluetoothSliceBuilder.handleUriChange(r7, r8)
            goto L_0x00eb
        L_0x00d8:
            java.lang.String r0 = "android.app.slice.extra.RANGE_VALUE"
            int r8 = r8.getIntExtra(r0, r5)
            r6.handleSliderAction(r7, r1, r8, r3)
            goto L_0x00eb
        L_0x00e2:
            java.lang.String r0 = "android.app.slice.extra.TOGGLE_STATE"
            boolean r8 = r8.getBooleanExtra(r0, r2)
            r6.handleToggleAction(r7, r1, r8, r3)
        L_0x00eb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.slices.SliceBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    private void handleToggleAction(Context context, String str, boolean z, boolean z2) {
        if (!TextUtils.isEmpty(str)) {
            BasePreferenceController preferenceController = getPreferenceController(context, str);
            if (!(preferenceController instanceof TogglePreferenceController)) {
                throw new IllegalStateException("Toggle action passed for a non-toggle key: " + str);
            } else if (!preferenceController.isAvailable()) {
                String str2 = TAG;
                Log.w(str2, "Can't update " + str + " since the setting is unavailable");
                if (!preferenceController.hasAsyncUpdate()) {
                    updateUri(context, str, z2);
                }
            } else {
                ((TogglePreferenceController) preferenceController).setChecked(z);
                logSliceValueChange(context, str, z ? 1 : 0);
                if (!preferenceController.hasAsyncUpdate()) {
                    updateUri(context, str, z2);
                }
            }
        } else {
            throw new IllegalStateException("No key passed to Intent for toggle controller");
        }
    }

    private void handleSliderAction(Context context, String str, int i, boolean z) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No key passed to Intent for slider controller. Use extra: com.android.settings.slice.extra.key");
        } else if (i != -1) {
            BasePreferenceController preferenceController = getPreferenceController(context, str);
            if (!(preferenceController instanceof SliderPreferenceController)) {
                throw new IllegalArgumentException("Slider action passed for a non-slider key: " + str);
            } else if (!preferenceController.isAvailable()) {
                String str2 = TAG;
                Log.w(str2, "Can't update " + str + " since the setting is unavailable");
                updateUri(context, str, z);
            } else {
                SliderPreferenceController sliderPreferenceController = (SliderPreferenceController) preferenceController;
                int min = sliderPreferenceController.getMin();
                int max = sliderPreferenceController.getMax();
                if (i < min || i > max) {
                    throw new IllegalArgumentException("Invalid position passed to Slider controller. Expected between " + min + " and " + max + " but found " + i);
                }
                sliderPreferenceController.setSliderPosition(i);
                logSliceValueChange(context, str, i);
                updateUri(context, str, z);
            }
        } else {
            throw new IllegalArgumentException("Invalid position passed to Slider controller");
        }
    }

    private void handleCopyAction(Context context, String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            BasePreferenceController preferenceController = getPreferenceController(context, str);
            if (!(preferenceController instanceof Sliceable)) {
                throw new IllegalArgumentException("Copyable action passed for a non-copyable key:" + str);
            } else if (!preferenceController.isAvailable()) {
                String str2 = TAG;
                Log.w(str2, "Can't update " + str + " since the setting is unavailable");
                if (!preferenceController.hasAsyncUpdate()) {
                    updateUri(context, str, z);
                }
            } else {
                preferenceController.copy();
            }
        } else {
            throw new IllegalArgumentException("No key passed to Intent for controller");
        }
    }

    private void logSliceValueChange(Context context, String str, int i) {
        FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(0, 1372, 0, str, i);
    }

    private BasePreferenceController getPreferenceController(Context context, String str) {
        return SliceBuilderUtils.getPreferenceController(context, new SlicesDatabaseAccessor(context).getSliceDataFromKey(str));
    }

    private void updateUri(Context context, String str, boolean z) {
        context.getContentResolver().notifyChange(new Uri.Builder().scheme("content").authority(z ? "android.settings.slices" : "com.android.settings.slices").appendPath("action").appendPath(str).build(), (ContentObserver) null);
    }
}
