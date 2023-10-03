package com.android.settings.deviceinfo.aboutphone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.Process;
import android.os.UserManager;
import android.provider.SearchIndexableResource;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.Utils;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.deviceinfo.BluetoothAddressPreferenceController;
import com.android.settings.deviceinfo.BuildNumberPreferenceController;
import com.android.settings.deviceinfo.DeviceNamePreferenceController;
import com.android.settings.deviceinfo.FccEquipmentIdPreferenceController;
import com.android.settings.deviceinfo.IpAddressPreferenceController;
import com.android.settings.deviceinfo.SELinuxStatusPreferenceController;
import com.android.settings.deviceinfo.UptimePreferenceController;
import com.android.settings.deviceinfo.WifiMacAddressPreferenceController;
import com.android.settings.deviceinfo.simstatus.SimStatusPreferenceController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.EntityHeaderController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyDeviceInfoFragment extends DashboardFragment implements DeviceNamePreferenceController.DeviceNamePreferenceHost {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.my_device_info;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return MyDeviceInfoFragment.buildPreferenceControllers(context, (MyDeviceInfoFragment) null, (Lifecycle) null);
        }
    };
    private BuildNumberPreferenceController mBuildNumberPreferenceController;

    public int getHelpResource() {
        return C1715R.string.help_uri_about;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "MyDeviceInfoFragment";
    }

    public int getMetricsCategory() {
        return 40;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.my_device_info;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((DeviceNamePreferenceController) use(DeviceNamePreferenceController.class)).setHost(this);
        this.mBuildNumberPreferenceController = (BuildNumberPreferenceController) use(BuildNumberPreferenceController.class);
        this.mBuildNumberPreferenceController.setHost(this);
    }

    public void onStart() {
        super.onStart();
        initHeader();
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, this, getSettingsLifecycle());
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, MyDeviceInfoFragment myDeviceInfoFragment, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SimStatusPreferenceController(context, myDeviceInfoFragment));
        arrayList.add(new IpAddressPreferenceController(context, lifecycle));
        arrayList.add(new WifiMacAddressPreferenceController(context, lifecycle));
        arrayList.add(new BluetoothAddressPreferenceController(context, lifecycle));
        arrayList.add(new FccEquipmentIdPreferenceController(context));
        arrayList.add(new UptimePreferenceController(context, lifecycle));
        arrayList.add(new SELinuxStatusPreferenceController(context));
        return arrayList;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (!this.mBuildNumberPreferenceController.onActivityResult(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    private void initHeader() {
        LayoutPreference layoutPreference = (LayoutPreference) getPreferenceScreen().findPreference("my_device_info_header");
        boolean z = getContext().getResources().getBoolean(C1715R.bool.config_show_device_header_in_device_info);
        layoutPreference.setVisible(z);
        if (z) {
            View findViewById = layoutPreference.findViewById(C1715R.C1718id.entity_header);
            FragmentActivity activity = getActivity();
            Bundle arguments = getArguments();
            EntityHeaderController buttonActions = EntityHeaderController.newInstance(activity, this, findViewById).setRecyclerView(getListView(), getSettingsLifecycle()).setButtonActions(0, 0);
            if (arguments.getInt("icon_id", 0) == 0) {
                UserManager userManager = (UserManager) getActivity().getSystemService("user");
                UserInfo existingUser = Utils.getExistingUser(userManager, Process.myUserHandle());
                buttonActions.setLabel((CharSequence) existingUser.name);
                buttonActions.setIcon(com.android.settingslib.Utils.getUserIcon(getActivity(), userManager, existingUser));
            }
            buttonActions.done((Activity) activity, true);
        }
    }

    public void showDeviceNameWarningDialog(String str) {
        DeviceNameWarningDialog.show(this);
    }

    public void onSetDeviceNameConfirm(boolean z) {
        ((DeviceNamePreferenceController) use(DeviceNamePreferenceController.class)).updateDeviceName(z);
    }
}
