package com.android.settings.network;

import android.content.Context;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.havoc.config.center.C1715R;

public class PrivateDnsPreferenceController extends BasePreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnStart, OnStop {
    private static final String KEY_PRIVATE_DNS_SETTINGS = "private_dns_settings";
    private static final Uri[] SETTINGS_URIS = {Settings.Global.getUriFor("private_dns_mode"), Settings.Global.getUriFor("private_dns_default_mode"), Settings.Global.getUriFor("private_dns_specifier")};
    private final ConnectivityManager mConnectivityManager;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public LinkProperties mLatestLinkProperties;
    private final ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback() {
        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            LinkProperties unused = PrivateDnsPreferenceController.this.mLatestLinkProperties = linkProperties;
            if (PrivateDnsPreferenceController.this.mPreference != null) {
                PrivateDnsPreferenceController privateDnsPreferenceController = PrivateDnsPreferenceController.this;
                privateDnsPreferenceController.updateState(privateDnsPreferenceController.mPreference);
            }
        }

        public void onLost(Network network) {
            LinkProperties unused = PrivateDnsPreferenceController.this.mLatestLinkProperties = null;
            if (PrivateDnsPreferenceController.this.mPreference != null) {
                PrivateDnsPreferenceController privateDnsPreferenceController = PrivateDnsPreferenceController.this;
                privateDnsPreferenceController.updateState(privateDnsPreferenceController.mPreference);
            }
        }
    };
    /* access modifiers changed from: private */
    public Preference mPreference;
    private final ContentObserver mSettingsObserver = new PrivateDnsSettingsObserver(this.mHandler);

    public String getPreferenceKey() {
        return KEY_PRIVATE_DNS_SETTINGS;
    }

    public PrivateDnsPreferenceController(Context context) {
        super(context, KEY_PRIVATE_DNS_SETTINGS);
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_private_dns_settings) ? 0 : 3;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    public void onStart() {
        for (Uri registerContentObserver : SETTINGS_URIS) {
            this.mContext.getContentResolver().registerContentObserver(registerContentObserver, false, this.mSettingsObserver);
        }
        Network activeNetwork = this.mConnectivityManager.getActiveNetwork();
        if (activeNetwork != null) {
            this.mLatestLinkProperties = this.mConnectivityManager.getLinkProperties(activeNetwork);
        }
        this.mConnectivityManager.registerDefaultNetworkCallback(this.mNetworkCallback, this.mHandler);
    }

    public void onStop() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mSettingsObserver);
        this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.CharSequence getSummary() {
        /*
            r8 = this;
            android.content.Context r0 = r8.mContext
            android.content.res.Resources r0 = r0.getResources()
            android.content.Context r1 = r8.mContext
            android.content.ContentResolver r1 = r1.getContentResolver()
            java.lang.String r2 = com.android.settings.network.PrivateDnsModeDialogPreference.getModeFromSettings(r1)
            android.net.LinkProperties r8 = r8.mLatestLinkProperties
            if (r8 != 0) goto L_0x0016
            r8 = 0
            goto L_0x001a
        L_0x0016:
            java.util.List r8 = r8.getValidatedPrivateDnsServers()
        L_0x001a:
            boolean r8 = com.android.internal.util.ArrayUtils.isEmpty(r8)
            r3 = 1
            r8 = r8 ^ r3
            r4 = -1
            int r5 = r2.hashCode()
            r6 = -539229175(0xffffffffdfdc0409, float:-3.1707613E19)
            r7 = 2
            if (r5 == r6) goto L_0x004a
            r6 = -299803597(0xffffffffee215c33, float:-1.2484637E28)
            if (r5 == r6) goto L_0x0040
            r6 = 109935(0x1ad6f, float:1.54052E-40)
            if (r5 == r6) goto L_0x0036
            goto L_0x0054
        L_0x0036:
            java.lang.String r5 = "off"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x0054
            r2 = 0
            goto L_0x0055
        L_0x0040:
            java.lang.String r5 = "hostname"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x0054
            r2 = r7
            goto L_0x0055
        L_0x004a:
            java.lang.String r5 = "opportunistic"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x0054
            r2 = r3
            goto L_0x0055
        L_0x0054:
            r2 = r4
        L_0x0055:
            if (r2 == 0) goto L_0x007f
            if (r2 == r3) goto L_0x006d
            if (r2 == r7) goto L_0x005e
            java.lang.String r8 = ""
            return r8
        L_0x005e:
            if (r8 == 0) goto L_0x0065
            java.lang.String r8 = com.android.settings.network.PrivateDnsModeDialogPreference.getHostnameFromSettings(r1)
            goto L_0x006c
        L_0x0065:
            r8 = 2131889770(0x7f120e6a, float:1.9414213E38)
            java.lang.String r8 = r0.getString(r8)
        L_0x006c:
            return r8
        L_0x006d:
            if (r8 == 0) goto L_0x0077
            r8 = 2131890918(0x7f1212e6, float:1.9416541E38)
            java.lang.String r8 = r0.getString(r8)
            goto L_0x007e
        L_0x0077:
            r8 = 2131889768(0x7f120e68, float:1.9414209E38)
            java.lang.String r8 = r0.getString(r8)
        L_0x007e:
            return r8
        L_0x007f:
            r8 = 2131889767(0x7f120e67, float:1.9414207E38)
            java.lang.String r8 = r0.getString(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.network.PrivateDnsPreferenceController.getSummary():java.lang.CharSequence");
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setEnabled(!isManagedByAdmin());
    }

    private boolean isManagedByAdmin() {
        return RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mContext, "disallow_config_private_dns", UserHandle.myUserId()) != null;
    }

    private class PrivateDnsSettingsObserver extends ContentObserver {
        public PrivateDnsSettingsObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z) {
            if (PrivateDnsPreferenceController.this.mPreference != null) {
                PrivateDnsPreferenceController privateDnsPreferenceController = PrivateDnsPreferenceController.this;
                privateDnsPreferenceController.updateState(privateDnsPreferenceController.mPreference);
            }
        }
    }
}
