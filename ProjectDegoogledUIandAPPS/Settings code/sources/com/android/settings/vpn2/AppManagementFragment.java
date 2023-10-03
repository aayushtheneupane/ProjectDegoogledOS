package com.android.settings.vpn2;

import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.IConnectivityManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import com.android.internal.net.VpnConfig;
import com.android.internal.util.ArrayUtils;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.vpn2.AppDialogFragment;
import com.android.settings.vpn2.ConfirmLockdownFragment;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.RestrictedSwitchPreference;
import com.havoc.config.center.C1715R;
import java.util.List;

public class AppManagementFragment extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener, ConfirmLockdownFragment.ConfirmLockdownListener {
    private ConnectivityManager mConnectivityManager;
    private IConnectivityManager mConnectivityService;
    private final AppDialogFragment.Listener mForgetVpnDialogFragmentListener = new AppDialogFragment.Listener() {
        public void onCancel() {
        }

        public void onForget() {
            if (AppManagementFragment.this.isVpnAlwaysOn()) {
                boolean unused = AppManagementFragment.this.setAlwaysOnVpn(false, false);
            }
            AppManagementFragment.this.finish();
        }
    };
    private PackageInfo mPackageInfo;
    private PackageManager mPackageManager;
    private String mPackageName;
    private RestrictedSwitchPreference mPreferenceAlwaysOn;
    private RestrictedPreference mPreferenceForget;
    private RestrictedSwitchPreference mPreferenceLockdown;
    private Preference mPreferenceVersion;
    private final int mUserId = UserHandle.myUserId();
    private String mVpnLabel;

    public int getMetricsCategory() {
        return 100;
    }

    public static void show(Context context, AppPreference appPreference, int i) {
        Bundle bundle = new Bundle();
        bundle.putString("package", appPreference.getPackageName());
        new SubSettingLauncher(context).setDestination(AppManagementFragment.class.getName()).setArguments(bundle).setTitleText(appPreference.getLabel()).setSourceMetricsCategory(i).setUserHandle(new UserHandle(appPreference.getUserId())).launch();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.vpn_app_management);
        this.mPackageManager = getContext().getPackageManager();
        this.mConnectivityManager = (ConnectivityManager) getContext().getSystemService(ConnectivityManager.class);
        this.mConnectivityService = IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity"));
        this.mPreferenceVersion = findPreference("version");
        this.mPreferenceAlwaysOn = (RestrictedSwitchPreference) findPreference("always_on_vpn");
        this.mPreferenceLockdown = (RestrictedSwitchPreference) findPreference("lockdown_vpn");
        this.mPreferenceForget = (RestrictedPreference) findPreference("forget_vpn");
        this.mPreferenceAlwaysOn.setOnPreferenceChangeListener(this);
        this.mPreferenceLockdown.setOnPreferenceChangeListener(this);
        this.mPreferenceForget.setOnPreferenceClickListener(this);
    }

    public void onResume() {
        super.onResume();
        if (loadInfo()) {
            this.mPreferenceVersion.setTitle((CharSequence) getPrefContext().getString(C1715R.string.vpn_version, new Object[]{this.mPackageInfo.versionName}));
            updateUI();
            return;
        }
        finish();
    }

    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if (((key.hashCode() == -591389790 && key.equals("forget_vpn")) ? (char) 0 : 65535) == 0) {
            return onForgetVpnClick();
        }
        Log.w("AppManagementFragment", "unknown key is clicked: " + key);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPreferenceChange(androidx.preference.Preference r6, java.lang.Object r7) {
        /*
            r5 = this;
            java.lang.String r0 = r6.getKey()
            int r1 = r0.hashCode()
            r2 = -2008102204(0xffffffff884ecac4, float:-6.222922E-34)
            r3 = 0
            r4 = 1
            if (r1 == r2) goto L_0x001f
            r2 = -1808701950(0xffffffff94316602, float:-8.956334E-27)
            if (r1 == r2) goto L_0x0015
            goto L_0x0029
        L_0x0015:
            java.lang.String r1 = "lockdown_vpn"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0029
            r0 = r4
            goto L_0x002a
        L_0x001f:
            java.lang.String r1 = "always_on_vpn"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0029
            r0 = r3
            goto L_0x002a
        L_0x0029:
            r0 = -1
        L_0x002a:
            if (r0 == 0) goto L_0x005a
            if (r0 == r4) goto L_0x0049
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "unknown key is clicked: "
            r5.append(r7)
            java.lang.String r6 = r6.getKey()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = "AppManagementFragment"
            android.util.Log.w(r6, r5)
            return r3
        L_0x0049:
            com.android.settingslib.RestrictedSwitchPreference r6 = r5.mPreferenceAlwaysOn
            boolean r6 = r6.isChecked()
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            boolean r5 = r5.onAlwaysOnVpnClick(r6, r7)
            return r5
        L_0x005a:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r6 = r7.booleanValue()
            com.android.settingslib.RestrictedSwitchPreference r7 = r5.mPreferenceLockdown
            boolean r7 = r7.isChecked()
            boolean r5 = r5.onAlwaysOnVpnClick(r6, r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.vpn2.AppManagementFragment.onPreferenceChange(androidx.preference.Preference, java.lang.Object):boolean");
    }

    private boolean onForgetVpnClick() {
        updateRestrictedViews();
        if (!this.mPreferenceForget.isEnabled()) {
            return false;
        }
        AppDialogFragment.show(this, this.mForgetVpnDialogFragmentListener, this.mPackageInfo, this.mVpnLabel, true, true);
        return true;
    }

    private boolean onAlwaysOnVpnClick(boolean z, boolean z2) {
        boolean isAnotherVpnActive = isAnotherVpnActive();
        boolean isAnyLockdownActive = VpnUtils.isAnyLockdownActive(getActivity());
        if (!ConfirmLockdownFragment.shouldShow(isAnotherVpnActive, isAnyLockdownActive, z2)) {
            return setAlwaysOnVpnByUI(z, z2);
        }
        ConfirmLockdownFragment.show(this, isAnotherVpnActive, z, isAnyLockdownActive, z2, (Bundle) null);
        return false;
    }

    public void onConfirmLockdown(Bundle bundle, boolean z, boolean z2) {
        setAlwaysOnVpnByUI(z, z2);
    }

    private boolean setAlwaysOnVpnByUI(boolean z, boolean z2) {
        updateRestrictedViews();
        if (!this.mPreferenceAlwaysOn.isEnabled()) {
            return false;
        }
        if (this.mUserId == 0) {
            VpnUtils.clearLockdownVpn(getContext());
        }
        boolean alwaysOnVpn = setAlwaysOnVpn(z, z2);
        if (!z || (alwaysOnVpn && isVpnAlwaysOn())) {
            updateUI();
        } else {
            CannotConnectFragment.show(this, this.mVpnLabel);
        }
        return alwaysOnVpn;
    }

    /* access modifiers changed from: private */
    public boolean setAlwaysOnVpn(boolean z, boolean z2) {
        return this.mConnectivityManager.setAlwaysOnVpnPackageForUser(this.mUserId, z ? this.mPackageName : null, z2, (List) null);
    }

    private void updateUI() {
        if (isAdded()) {
            boolean isVpnAlwaysOn = isVpnAlwaysOn();
            boolean z = isVpnAlwaysOn && VpnUtils.isAnyLockdownActive(getActivity());
            this.mPreferenceAlwaysOn.setChecked(isVpnAlwaysOn);
            this.mPreferenceLockdown.setChecked(z);
            updateRestrictedViews();
        }
    }

    private void updateRestrictedViews() {
        if (isAdded()) {
            this.mPreferenceAlwaysOn.checkRestrictionAndSetDisabled("no_config_vpn", this.mUserId);
            this.mPreferenceLockdown.checkRestrictionAndSetDisabled("no_config_vpn", this.mUserId);
            this.mPreferenceForget.checkRestrictionAndSetDisabled("no_config_vpn", this.mUserId);
            if (this.mConnectivityManager.isAlwaysOnVpnPackageSupportedForUser(this.mUserId, this.mPackageName)) {
                this.mPreferenceAlwaysOn.setSummary((int) C1715R.string.vpn_always_on_summary);
                return;
            }
            this.mPreferenceAlwaysOn.setEnabled(false);
            this.mPreferenceLockdown.setEnabled(false);
            this.mPreferenceAlwaysOn.setSummary((int) C1715R.string.vpn_always_on_summary_not_supported);
        }
    }

    private String getAlwaysOnVpnPackage() {
        return this.mConnectivityManager.getAlwaysOnVpnPackageForUser(this.mUserId);
    }

    /* access modifiers changed from: private */
    public boolean isVpnAlwaysOn() {
        return this.mPackageName.equals(getAlwaysOnVpnPackage());
    }

    private boolean loadInfo() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            Log.e("AppManagementFragment", "empty bundle");
            return false;
        }
        this.mPackageName = arguments.getString("package");
        String str = this.mPackageName;
        if (str == null) {
            Log.e("AppManagementFragment", "empty package name");
            return false;
        }
        try {
            this.mPackageInfo = this.mPackageManager.getPackageInfo(str, 0);
            this.mVpnLabel = VpnConfig.getVpnLabel(getPrefContext(), this.mPackageName).toString();
            if (this.mPackageInfo.applicationInfo == null) {
                Log.e("AppManagementFragment", "package does not include an application");
                return false;
            } else if (appHasVpnPermission(getContext(), this.mPackageInfo.applicationInfo)) {
                return true;
            } else {
                Log.e("AppManagementFragment", "package didn't register VPN profile");
                return false;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("AppManagementFragment", "package not found", e);
            return false;
        }
    }

    static boolean appHasVpnPermission(Context context, ApplicationInfo applicationInfo) {
        return !ArrayUtils.isEmpty(((AppOpsManager) context.getSystemService("appops")).getOpsForPackage(applicationInfo.uid, applicationInfo.packageName, new int[]{47}));
    }

    private boolean isAnotherVpnActive() {
        try {
            VpnConfig vpnConfig = this.mConnectivityService.getVpnConfig(this.mUserId);
            if (vpnConfig == null || TextUtils.equals(vpnConfig.user, this.mPackageName)) {
                return false;
            }
            return true;
        } catch (RemoteException e) {
            Log.w("AppManagementFragment", "Failure to look up active VPN", e);
            return false;
        }
    }

    public static class CannotConnectFragment extends InstrumentedDialogFragment {
        public int getMetricsCategory() {
            return 547;
        }

        public static void show(AppManagementFragment appManagementFragment, String str) {
            if (appManagementFragment.getFragmentManager().findFragmentByTag("CannotConnect") == null) {
                Bundle bundle = new Bundle();
                bundle.putString("label", str);
                CannotConnectFragment cannotConnectFragment = new CannotConnectFragment();
                cannotConnectFragment.setArguments(bundle);
                cannotConnectFragment.show(appManagementFragment.getFragmentManager(), "CannotConnect");
            }
        }

        public Dialog onCreateDialog(Bundle bundle) {
            String string = getArguments().getString("label");
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle((CharSequence) getActivity().getString(C1715R.string.vpn_cant_connect_title, new Object[]{string}));
            builder.setMessage((CharSequence) getActivity().getString(C1715R.string.vpn_cant_connect_message));
            builder.setPositiveButton((int) C1715R.string.okay, (DialogInterface.OnClickListener) null);
            return builder.create();
        }
    }
}
