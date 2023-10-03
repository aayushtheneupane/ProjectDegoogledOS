package com.android.settings.applications.specialaccess.deviceadmin;

import android.app.AppGlobals;
import android.app.admin.DeviceAdminInfo;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.widget.FilterTouchesSwitchPreference;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.widget.FooterPreference;
import com.android.settingslib.widget.FooterPreferenceMixinCompat;
import com.havoc.config.center.C1715R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

public class DeviceAdminListPreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private static final IntentFilter FILTER = new IntentFilter();
    private static final String TAG = "DeviceAdminListPrefCtrl";
    private final ArrayList<DeviceAdminListItem> mAdmins = new ArrayList<>();
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED", intent.getAction())) {
                DeviceAdminListPreferenceController.this.updateList();
            }
        }
    };
    private final DevicePolicyManager mDPM;
    private FooterPreferenceMixinCompat mFooterPreferenceMixin;
    private final IPackageManager mIPackageManager;
    private final PackageManager mPackageManager;
    private PreferenceGroup mPreferenceGroup;
    private final SparseArray<ComponentName> mProfileOwnerComponents = new SparseArray<>();
    private final UserManager mUm;

    static /* synthetic */ boolean lambda$bindPreference$1(Preference preference, Object obj) {
        return false;
    }

    public int getAvailabilityStatus() {
        return 0;
    }

    static {
        FILTER.addAction("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED");
    }

    public DeviceAdminListPreferenceController(Context context, String str) {
        super(context, str);
        this.mDPM = (DevicePolicyManager) context.getSystemService("device_policy");
        this.mUm = (UserManager) context.getSystemService("user");
        this.mPackageManager = this.mContext.getPackageManager();
        this.mIPackageManager = AppGlobals.getPackageManager();
    }

    public DeviceAdminListPreferenceController setFooterPreferenceMixin(FooterPreferenceMixinCompat footerPreferenceMixinCompat) {
        this.mFooterPreferenceMixin = footerPreferenceMixinCompat;
        return this;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(getPreferenceKey());
    }

    public void onStart() {
        this.mContext.registerReceiverAsUser(this.mBroadcastReceiver, UserHandle.ALL, FILTER, (String) null, (Handler) null);
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        this.mProfileOwnerComponents.clear();
        List<UserHandle> userProfiles = this.mUm.getUserProfiles();
        int size = userProfiles.size();
        for (int i = 0; i < size; i++) {
            int identifier = userProfiles.get(i).getIdentifier();
            this.mProfileOwnerComponents.put(identifier, this.mDPM.getProfileOwnerAsUser(identifier));
        }
        updateList();
    }

    public void onStop() {
        this.mContext.unregisterReceiver(this.mBroadcastReceiver);
    }

    /* access modifiers changed from: package-private */
    public void updateList() {
        refreshData();
        refreshUI();
    }

    private void refreshData() {
        this.mAdmins.clear();
        for (UserHandle identifier : this.mUm.getUserProfiles()) {
            updateAvailableAdminsForProfile(identifier.getIdentifier());
        }
        Collections.sort(this.mAdmins);
    }

    private void refreshUI() {
        if (this.mPreferenceGroup != null) {
            FooterPreferenceMixinCompat footerPreferenceMixinCompat = this.mFooterPreferenceMixin;
            if (footerPreferenceMixinCompat != null) {
                FooterPreference createFooterPreference = footerPreferenceMixinCompat.createFooterPreference();
                createFooterPreference.setTitle((int) C1715R.string.no_device_admins);
                createFooterPreference.setVisible(this.mAdmins.isEmpty());
            }
            ArrayMap arrayMap = new ArrayMap();
            Context context = this.mPreferenceGroup.getContext();
            int preferenceCount = this.mPreferenceGroup.getPreferenceCount();
            for (int i = 0; i < preferenceCount; i++) {
                Preference preference = this.mPreferenceGroup.getPreference(i);
                if (preference instanceof FilterTouchesSwitchPreference) {
                    FilterTouchesSwitchPreference filterTouchesSwitchPreference = (FilterTouchesSwitchPreference) preference;
                    arrayMap.put(filterTouchesSwitchPreference.getKey(), filterTouchesSwitchPreference);
                }
            }
            Iterator<DeviceAdminListItem> it = this.mAdmins.iterator();
            while (it.hasNext()) {
                DeviceAdminListItem next = it.next();
                FilterTouchesSwitchPreference filterTouchesSwitchPreference2 = (FilterTouchesSwitchPreference) arrayMap.remove(next.getKey());
                if (filterTouchesSwitchPreference2 == null) {
                    filterTouchesSwitchPreference2 = new FilterTouchesSwitchPreference(context);
                    this.mPreferenceGroup.addPreference(filterTouchesSwitchPreference2);
                }
                bindPreference(next, filterTouchesSwitchPreference2);
            }
            for (FilterTouchesSwitchPreference removePreference : arrayMap.values()) {
                this.mPreferenceGroup.removePreference(removePreference);
            }
        }
    }

    private void bindPreference(DeviceAdminListItem deviceAdminListItem, FilterTouchesSwitchPreference filterTouchesSwitchPreference) {
        filterTouchesSwitchPreference.setKey(deviceAdminListItem.getKey());
        filterTouchesSwitchPreference.setTitle(deviceAdminListItem.getName());
        filterTouchesSwitchPreference.setIcon(deviceAdminListItem.getIcon());
        filterTouchesSwitchPreference.setChecked(deviceAdminListItem.isActive());
        filterTouchesSwitchPreference.setSummary(deviceAdminListItem.getDescription());
        filterTouchesSwitchPreference.setEnabled(deviceAdminListItem.isEnabled());
        filterTouchesSwitchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(deviceAdminListItem) {
            private final /* synthetic */ DeviceAdminListItem f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onPreferenceClick(Preference preference) {
                return DeviceAdminListPreferenceController.this.lambda$bindPreference$0$DeviceAdminListPreferenceController(this.f$1, preference);
            }
        });
        filterTouchesSwitchPreference.setOnPreferenceChangeListener(C0578xd92800f2.INSTANCE);
    }

    public /* synthetic */ boolean lambda$bindPreference$0$DeviceAdminListPreferenceController(DeviceAdminListItem deviceAdminListItem, Preference preference) {
        UserHandle user = deviceAdminListItem.getUser();
        Context context = this.mContext;
        context.startActivityAsUser(deviceAdminListItem.getLaunchIntent(context), user);
        return true;
    }

    private void updateAvailableAdminsForProfile(int i) {
        List activeAdminsAsUser = this.mDPM.getActiveAdminsAsUser(i);
        addActiveAdminsForProfile(activeAdminsAsUser, i);
        addDeviceAdminBroadcastReceiversForProfile(activeAdminsAsUser, i);
    }

    private void addActiveAdminsForProfile(List<ComponentName> list, int i) {
        if (list != null) {
            for (ComponentName next : list) {
                try {
                    DeviceAdminInfo createDeviceAdminInfo = createDeviceAdminInfo(this.mContext, this.mIPackageManager.getReceiverInfo(next, 819328, i));
                    if (createDeviceAdminInfo != null) {
                        this.mAdmins.add(new DeviceAdminListItem(this.mContext, createDeviceAdminInfo));
                    }
                } catch (RemoteException unused) {
                    Log.w(TAG, "Unable to load component: " + next);
                }
            }
        }
    }

    private void addDeviceAdminBroadcastReceiversForProfile(Collection<ComponentName> collection, int i) {
        DeviceAdminInfo createDeviceAdminInfo;
        List<ResolveInfo> queryBroadcastReceiversAsUser = this.mPackageManager.queryBroadcastReceiversAsUser(new Intent("android.app.action.DEVICE_ADMIN_ENABLED"), 32896, i);
        if (queryBroadcastReceiversAsUser != null) {
            for (ResolveInfo resolveInfo : queryBroadcastReceiversAsUser) {
                ComponentName componentName = new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                if ((collection == null || !collection.contains(componentName)) && (createDeviceAdminInfo = createDeviceAdminInfo(this.mContext, resolveInfo.activityInfo)) != null && createDeviceAdminInfo.isVisible() && createDeviceAdminInfo.getActivityInfo().applicationInfo.isInternal()) {
                    this.mAdmins.add(new DeviceAdminListItem(this.mContext, createDeviceAdminInfo));
                }
            }
        }
    }

    private static DeviceAdminInfo createDeviceAdminInfo(Context context, ActivityInfo activityInfo) {
        try {
            return new DeviceAdminInfo(context, activityInfo);
        } catch (IOException | XmlPullParserException e) {
            Log.w(TAG, "Skipping " + activityInfo, e);
            return null;
        }
    }
}
