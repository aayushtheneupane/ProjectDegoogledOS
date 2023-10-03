package com.android.systemui.statusbar.policy;

import com.android.internal.net.VpnProfile;
import com.android.systemui.Dumpable;
import java.util.List;

public interface SecurityController extends CallbackController<SecurityControllerCallback>, Dumpable {

    public interface SecurityControllerCallback {
        void onStateChanged();
    }

    void connectLegacyVpn(VpnProfile vpnProfile);

    void disconnectPrimaryVpn();

    List<VpnProfile> getConfiguredLegacyVpns();

    CharSequence getDeviceOwnerOrganizationName();

    String getPrimaryVpnName();

    List<String> getVpnAppPackageNames();

    CharSequence getWorkProfileOrganizationName();

    String getWorkProfileVpnName();

    boolean hasCACertInCurrentUser();

    boolean hasCACertInWorkProfile();

    boolean hasWorkProfile();

    boolean isDeviceManaged();

    boolean isNetworkLoggingEnabled();

    boolean isVpnBranded();

    boolean isVpnEnabled();

    boolean isVpnRestricted();

    void launchVpnApp(String str);

    void onUserSwitched(int i);
}
