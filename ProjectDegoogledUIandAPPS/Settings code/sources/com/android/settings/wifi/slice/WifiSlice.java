package com.android.settings.wifi.slice;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.settings.SubSettings;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.android.settings.slices.SliceBackgroundWorker;
import com.android.settings.slices.SliceBuilderUtils;
import com.android.settings.wifi.WifiDialogActivity;
import com.android.settings.wifi.WifiSettings;
import com.android.settings.wifi.WifiUtils;
import com.android.settings.wifi.details.WifiNetworkDetailsFragment;
import com.android.settingslib.Utils;
import com.android.settingslib.wifi.AccessPoint;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WifiSlice implements CustomSliceable {
    static final int DEFAULT_EXPANDED_ROW_COUNT = 3;
    protected final ConnectivityManager mConnectivityManager = ((ConnectivityManager) this.mContext.getSystemService(ConnectivityManager.class));
    protected final Context mContext;
    protected final WifiManager mWifiManager = ((WifiManager) this.mContext.getSystemService(WifiManager.class));

    public WifiSlice(Context context) {
        this.mContext = context;
    }

    public Uri getUri() {
        return CustomSliceRegistry.WIFI_SLICE_URI;
    }

    public Slice getSlice() {
        int i;
        boolean z = true;
        this.mContext.getTheme().applyStyle(2131952299, true);
        boolean isWifiEnabled = isWifiEnabled();
        ListBuilder headerRow = getHeaderRow(isWifiEnabled);
        if (!isWifiEnabled) {
            WifiScanWorker.clearClickedWifi();
            return headerRow.build();
        }
        WifiScanWorker wifiScanWorker = (WifiScanWorker) SliceBackgroundWorker.getInstance(getUri());
        List results = wifiScanWorker != null ? wifiScanWorker.getResults() : null;
        if (results == null) {
            i = 0;
        } else {
            i = results.size();
        }
        boolean z2 = i > 0 && ((AccessPoint) results.get(0)).isActive();
        handleNetworkCallback(wifiScanWorker, z2);
        int i2 = z2;
        while (true) {
            if (i2 >= i) {
                break;
            } else if (((AccessPoint) results.get(i2)).isReachable()) {
                z = false;
                break;
            } else {
                i2++;
            }
        }
        CharSequence text = this.mContext.getText(C1715R.string.summary_placeholder);
        boolean z3 = z;
        for (int i3 = 0; i3 < 3; i3++) {
            if (i3 < i) {
                headerRow.addRow(getAccessPointRow((AccessPoint) results.get(i3)));
            } else if (z3) {
                headerRow.addRow(getLoadingRow(text));
                z3 = false;
            } else {
                ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
                rowBuilder.setTitle(text);
                rowBuilder.setSubtitle(text);
                headerRow.addRow(rowBuilder);
            }
        }
        return headerRow.build();
    }

    private void handleNetworkCallback(WifiScanWorker wifiScanWorker, boolean z) {
        if (wifiScanWorker != null) {
            if (z) {
                wifiScanWorker.registerNetworkCallback(this.mWifiManager.getCurrentNetwork());
            } else {
                wifiScanWorker.unregisterNetworkCallback();
            }
        }
    }

    private ListBuilder getHeaderRow(boolean z) {
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.ic_settings_wireless);
        String string = this.mContext.getString(C1715R.string.wifi_settings_master_switch_title);
        PendingIntent broadcastIntent = getBroadcastIntent(this.mContext);
        SliceAction createDeeplink = SliceAction.createDeeplink(getPrimaryAction(), createWithResource, 0, string);
        SliceAction createToggle = SliceAction.createToggle(broadcastIntent, (CharSequence) null, z);
        ListBuilder listBuilder = new ListBuilder(this.mContext, getUri(), -1);
        listBuilder.setAccentColor(-1);
        listBuilder.setKeywords(getKeywords());
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitle(string);
        rowBuilder.addEndItem(createToggle);
        rowBuilder.setPrimaryAction(createDeeplink);
        listBuilder.addRow(rowBuilder);
        return listBuilder;
    }

    private ListBuilder.RowBuilder getAccessPointRow(AccessPoint accessPoint) {
        boolean z = accessPoint.isActive() && isCaptivePortal();
        String title = accessPoint.getTitle();
        CharSequence accessPointSummary = getAccessPointSummary(accessPoint, z);
        IconCompat accessPointLevelIcon = getAccessPointLevelIcon(accessPoint);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitleItem(accessPointLevelIcon, 0);
        rowBuilder.setTitle(title);
        rowBuilder.setSubtitle(accessPointSummary);
        rowBuilder.setPrimaryAction(getAccessPointAction(accessPoint, z, accessPointLevelIcon, title));
        if (z) {
            rowBuilder.addEndItem(getCaptivePortalEndAction(accessPoint, title));
        } else {
            IconCompat endIcon = getEndIcon(accessPoint);
            if (endIcon != null) {
                rowBuilder.addEndItem(endIcon, 0);
            }
        }
        return rowBuilder;
    }

    private CharSequence getAccessPointSummary(AccessPoint accessPoint, boolean z) {
        if (z) {
            return this.mContext.getText(C1715R.string.wifi_tap_to_sign_in);
        }
        String settingsSummary = accessPoint.getSettingsSummary();
        return TextUtils.isEmpty(settingsSummary) ? this.mContext.getText(C1715R.string.disconnected) : settingsSummary;
    }

    private IconCompat getAccessPointLevelIcon(AccessPoint accessPoint) {
        int i;
        Drawable drawable = this.mContext.getDrawable(Utils.getWifiIconResource(accessPoint.getLevel()));
        if (!accessPoint.isActive()) {
            i = Utils.getColorAttrDefaultColor(this.mContext, 16843817);
        } else if (accessPoint.getNetworkInfo().getState() == NetworkInfo.State.CONNECTED) {
            i = Utils.getColorAccentDefaultColor(this.mContext);
        } else {
            Context context = this.mContext;
            i = Utils.getDisabled(context, Utils.getColorAttrDefaultColor(context, 16843817));
        }
        drawable.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
        return com.android.settings.Utils.createIconWithDrawable(drawable);
    }

    private IconCompat getEndIcon(AccessPoint accessPoint) {
        if (accessPoint.isActive()) {
            return null;
        }
        if (accessPoint.getSecurity() != 0) {
            return IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.ic_friction_lock_closed);
        }
        if (accessPoint.isMetered()) {
            return IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.ic_friction_money);
        }
        return null;
    }

    private SliceAction getCaptivePortalEndAction(AccessPoint accessPoint, CharSequence charSequence) {
        return getAccessPointAction(accessPoint, false, IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.ic_settings_accent), charSequence);
    }

    private SliceAction getAccessPointAction(AccessPoint accessPoint, boolean z, IconCompat iconCompat, CharSequence charSequence) {
        int hashCode = accessPoint.hashCode();
        if (z) {
            return getBroadcastAction(hashCode, new Intent(this.mContext, ConnectToWifiHandler.class).putExtra("android.net.extra.NETWORK", this.mWifiManager.getCurrentNetwork()), iconCompat, charSequence);
        }
        Bundle bundle = new Bundle();
        accessPoint.saveWifiState(bundle);
        if (accessPoint.isActive()) {
            return getActivityAction(hashCode, new SubSettingLauncher(this.mContext).setTitleRes(C1715R.string.pref_title_network_details).setDestination(WifiNetworkDetailsFragment.class.getName()).setArguments(bundle).setSourceMetricsCategory(103).toIntent(), iconCompat, charSequence);
        }
        if (WifiUtils.getConnectingType(accessPoint) != 0) {
            return getBroadcastAction(hashCode, new Intent(this.mContext, ConnectToWifiHandler.class).putExtra("access_point_state", bundle), iconCompat, charSequence);
        }
        return getActivityAction(hashCode, new Intent(this.mContext, WifiDialogActivity.class).putExtra("access_point_state", bundle), iconCompat, charSequence);
    }

    private SliceAction getActivityAction(int i, Intent intent, IconCompat iconCompat, CharSequence charSequence) {
        return SliceAction.createDeeplink(PendingIntent.getActivity(this.mContext, i, intent, 0), iconCompat, 0, charSequence);
    }

    private SliceAction getBroadcastAction(int i, Intent intent, IconCompat iconCompat, CharSequence charSequence) {
        intent.addFlags(268435456);
        return SliceAction.create(PendingIntent.getBroadcast(this.mContext, i, intent, 134217728), iconCompat, 0, charSequence);
    }

    private ListBuilder.RowBuilder getLoadingRow(CharSequence charSequence) {
        CharSequence text = this.mContext.getText(C1715R.string.wifi_empty_list_wifi_on);
        IconCompat createIconWithDrawable = com.android.settings.Utils.createIconWithDrawable(new ColorDrawable(0));
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitleItem(createIconWithDrawable, 0);
        rowBuilder.setTitle(charSequence);
        rowBuilder.setSubtitle(text);
        return rowBuilder;
    }

    private boolean isCaptivePortal() {
        return WifiUtils.canSignIntoNetwork(this.mConnectivityManager.getNetworkCapabilities(this.mWifiManager.getCurrentNetwork()));
    }

    public void onNotifyChange(Intent intent) {
        this.mWifiManager.setWifiEnabled(intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", this.mWifiManager.isWifiEnabled()));
    }

    public Intent getIntent() {
        String charSequence = this.mContext.getText(C1715R.string.wifi_settings).toString();
        return SliceBuilderUtils.buildSearchResultPageIntent(this.mContext, WifiSettings.class.getName(), "wifi", charSequence, 603).setClassName(this.mContext.getPackageName(), SubSettings.class.getName()).setData(new Uri.Builder().appendPath("wifi").build());
    }

    private boolean isWifiEnabled() {
        int wifiState = this.mWifiManager.getWifiState();
        return wifiState == 2 || wifiState == 3;
    }

    private PendingIntent getPrimaryAction() {
        return PendingIntent.getActivity(this.mContext, 0, getIntent(), 0);
    }

    private Set<String> getKeywords() {
        return (Set) Arrays.asList(TextUtils.split(this.mContext.getString(C1715R.string.keywords_wifi), ",")).stream().map($$Lambda$MGZTkxm_LWhWFo0u65o5bz97bA.INSTANCE).collect(Collectors.toSet());
    }

    public Class getBackgroundWorkerClass() {
        return WifiScanWorker.class;
    }
}
