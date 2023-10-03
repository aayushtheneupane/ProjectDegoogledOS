package com.android.settings.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.password.ConfirmDeviceCredentialActivity;
import com.android.settings.widget.ToggleSwitch;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.havoc.config.center.C1715R;
import java.util.List;

public class ToggleAccessibilityServicePreferenceFragment extends ToggleFeaturePreferenceFragment implements View.OnClickListener {
    private ComponentName mComponentName;
    private Dialog mDialog;
    private LockPatternUtils mLockPatternUtils;
    private final SettingsContentObserver mSettingsContentObserver = new SettingsContentObserver(new Handler()) {
        public void onChange(boolean z, Uri uri) {
            ToggleAccessibilityServicePreferenceFragment.this.updateSwitchBarToggleSwitch();
        }
    };

    public int getDialogMetricsCategory(int i) {
        return i == 1 ? 583 : 584;
    }

    public int getMetricsCategory() {
        return 4;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mLockPatternUtils = new LockPatternUtils(getActivity());
    }

    public void onResume() {
        this.mSettingsContentObserver.register(getContentResolver());
        updateSwitchBarToggleSwitch();
        super.onResume();
    }

    public void onPause() {
        this.mSettingsContentObserver.unregister(getContentResolver());
        super.onPause();
    }

    public void onPreferenceToggled(String str, boolean z) {
        AccessibilityUtils.setAccessibilityServiceState(getActivity(), ComponentName.unflattenFromString(str), z);
    }

    private AccessibilityServiceInfo getAccessibilityServiceInfo() {
        List<AccessibilityServiceInfo> installedAccessibilityServiceList = AccessibilityManager.getInstance(getActivity()).getInstalledAccessibilityServiceList();
        int size = installedAccessibilityServiceList.size();
        for (int i = 0; i < size; i++) {
            AccessibilityServiceInfo accessibilityServiceInfo = installedAccessibilityServiceList.get(i);
            ResolveInfo resolveInfo = accessibilityServiceInfo.getResolveInfo();
            if (this.mComponentName.getPackageName().equals(resolveInfo.serviceInfo.packageName) && this.mComponentName.getClassName().equals(resolveInfo.serviceInfo.name)) {
                return accessibilityServiceInfo;
            }
        }
        return null;
    }

    public Dialog onCreateDialog(int i) {
        if (i == 1) {
            AccessibilityServiceInfo accessibilityServiceInfo = getAccessibilityServiceInfo();
            if (accessibilityServiceInfo == null) {
                return null;
            }
            this.mDialog = AccessibilityServiceWarning.createCapabilitiesDialog(getActivity(), accessibilityServiceInfo, this);
        } else if (i == 2) {
            AccessibilityServiceInfo accessibilityServiceInfo2 = getAccessibilityServiceInfo();
            if (accessibilityServiceInfo2 == null) {
                return null;
            }
            this.mDialog = AccessibilityServiceWarning.createDisableDialog(getActivity(), accessibilityServiceInfo2, this);
        } else if (i != 3) {
            throw new IllegalArgumentException();
        } else if (isGestureNavigateEnabled()) {
            this.mDialog = AccessibilityGestureNavigationTutorial.showGestureNavigationTutorialDialog(getActivity());
        } else {
            this.mDialog = AccessibilityGestureNavigationTutorial.showAccessibilityButtonTutorialDialog(getActivity());
        }
        return this.mDialog;
    }

    /* access modifiers changed from: private */
    public void updateSwitchBarToggleSwitch() {
        this.mSwitchBar.setCheckedInternal(AccessibilityUtils.getEnabledServicesFromSettings(getActivity()).contains(this.mComponentName));
    }

    private boolean isFullDiskEncrypted() {
        return StorageManager.isNonDefaultBlockEncrypted();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1) {
            return;
        }
        if (i2 == -1) {
            handleConfirmServiceEnabled(true);
            if (isFullDiskEncrypted()) {
                this.mLockPatternUtils.clearEncryptionPassword();
                Settings.Global.putInt(getContentResolver(), "require_password_to_decrypt", 0);
                return;
            }
            return;
        }
        handleConfirmServiceEnabled(false);
    }

    public void onClick(View view) {
        if (view.getId() == C1715R.C1718id.permission_enable_allow_button) {
            if (isFullDiskEncrypted()) {
                startActivityForResult(ConfirmDeviceCredentialActivity.createIntent(createConfirmCredentialReasonMessage(), (CharSequence) null), 1);
            } else {
                handleConfirmServiceEnabled(true);
                if (isServiceSupportAccessibilityButton()) {
                    showDialog(3);
                }
            }
        } else if (view.getId() == C1715R.C1718id.permission_enable_deny_button) {
            handleConfirmServiceEnabled(false);
        } else if (view.getId() == C1715R.C1718id.permission_disable_stop_button) {
            handleConfirmServiceEnabled(false);
        } else if (view.getId() == C1715R.C1718id.permission_disable_cancel_button) {
            handleConfirmServiceEnabled(true);
        } else {
            throw new IllegalArgumentException();
        }
        this.mDialog.dismiss();
    }

    private boolean isGestureNavigateEnabled() {
        return getContext().getResources().getInteger(17694869) == 2;
    }

    private boolean isServiceSupportAccessibilityButton() {
        ServiceInfo serviceInfo;
        for (AccessibilityServiceInfo next : ((AccessibilityManager) getContext().getSystemService("accessibility")).getInstalledAccessibilityServiceList()) {
            if ((next.flags & 256) != 0 && (serviceInfo = next.getResolveInfo().serviceInfo) != null && TextUtils.equals(serviceInfo.name, getAccessibilityServiceInfo().getResolveInfo().serviceInfo.name)) {
                return true;
            }
        }
        return false;
    }

    private void handleConfirmServiceEnabled(boolean z) {
        this.mSwitchBar.setCheckedInternal(z);
        getArguments().putBoolean("checked", z);
        onPreferenceToggled(this.mPreferenceKey, z);
    }

    private String createConfirmCredentialReasonMessage() {
        int keyguardStoredPasswordQuality = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(UserHandle.myUserId());
        return getString(keyguardStoredPasswordQuality != 65536 ? (keyguardStoredPasswordQuality == 131072 || keyguardStoredPasswordQuality == 196608) ? C1715R.string.enable_service_pin_reason : C1715R.string.enable_service_password_reason : C1715R.string.enable_service_pattern_reason, getAccessibilityServiceInfo().getResolveInfo().loadLabel(getPackageManager()));
    }

    /* access modifiers changed from: protected */
    public void onInstallSwitchBarToggleSwitch() {
        super.onInstallSwitchBarToggleSwitch();
        this.mToggleSwitch.setOnBeforeCheckedChangeListener(new ToggleSwitch.OnBeforeCheckedChangeListener() {
            public boolean onBeforeCheckedChanged(ToggleSwitch toggleSwitch, boolean z) {
                if (z) {
                    ToggleAccessibilityServicePreferenceFragment.this.mSwitchBar.setCheckedInternal(false);
                    ToggleAccessibilityServicePreferenceFragment.this.getArguments().putBoolean("checked", false);
                    ToggleAccessibilityServicePreferenceFragment.this.showDialog(1);
                } else {
                    ToggleAccessibilityServicePreferenceFragment.this.mSwitchBar.setCheckedInternal(true);
                    ToggleAccessibilityServicePreferenceFragment.this.getArguments().putBoolean("checked", true);
                    ToggleAccessibilityServicePreferenceFragment.this.showDialog(2);
                }
                return true;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onProcessArguments(Bundle bundle) {
        super.onProcessArguments(bundle);
        String string = bundle.getString("settings_title");
        String string2 = bundle.getString("settings_component_name");
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
            Intent component = new Intent("android.intent.action.MAIN").setComponent(ComponentName.unflattenFromString(string2.toString()));
            if (!getPackageManager().queryIntentActivities(component, 0).isEmpty()) {
                this.mSettingsTitle = string;
                this.mSettingsIntent = component;
                setHasOptionsMenu(true);
            }
        }
        this.mComponentName = (ComponentName) bundle.getParcelable("component_name");
    }
}
