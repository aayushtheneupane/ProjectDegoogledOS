package com.android.settings.development;

import android.app.Activity;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.settings.SettingsActivity;
import com.android.settings.Utils;
import com.android.settings.dashboard.RestrictedDashboardFragment;
import com.android.settings.development.BluetoothA2dpHwOffloadRebootDialog;
import com.android.settings.development.autofill.AutofillLoggingLevelPreferenceController;
import com.android.settings.development.autofill.AutofillResetOptionsPreferenceController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.SwitchBar;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.android.settingslib.development.DevelopmentSettingsEnabler;
import com.android.settingslib.development.SystemPropPoker;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DevelopmentSettingsDashboardFragment extends RestrictedDashboardFragment implements SwitchBar.OnSwitchChangeListener, OemUnlockDialogHost, AdbDialogHost, AdbClearKeysDialogHost, LogPersistDialogHost, BluetoothA2dpHwOffloadRebootDialog.OnA2dpHwDialogConfirmedListener, AdbNetworkDialogHost {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(context);
        }

        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.development_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return DevelopmentSettingsDashboardFragment.buildPreferenceControllers(context, (Activity) null, (Lifecycle) null, (DevelopmentSettingsDashboardFragment) null, (BluetoothA2dpConfigStore) null);
        }
    };
    /* access modifiers changed from: private */
    public BluetoothA2dp mBluetoothA2dp;
    /* access modifiers changed from: private */
    public final BluetoothA2dpConfigStore mBluetoothA2dpConfigStore = new BluetoothA2dpConfigStore();
    private final BroadcastReceiver mBluetoothA2dpReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.d("DevSettingsDashboard", "mBluetoothA2dpReceiver.onReceive intent=" + intent);
            if ("android.bluetooth.a2dp.profile.action.CODEC_CONFIG_CHANGED".equals(intent.getAction())) {
                Log.d("DevSettingsDashboard", "Received BluetoothCodecStatus=" + intent.getParcelableExtra("android.bluetooth.codec.extra.CODEC_STATUS"));
                for (AbstractPreferenceController abstractPreferenceController : DevelopmentSettingsDashboardFragment.this.mPreferenceControllers) {
                    if (abstractPreferenceController instanceof BluetoothServiceConnectionListener) {
                        ((BluetoothServiceConnectionListener) abstractPreferenceController).onBluetoothCodecUpdated();
                    }
                }
            }
        }
    };
    private final BluetoothProfile.ServiceListener mBluetoothA2dpServiceListener = new BluetoothProfile.ServiceListener() {
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            synchronized (DevelopmentSettingsDashboardFragment.this.mBluetoothA2dpConfigStore) {
                BluetoothA2dp unused = DevelopmentSettingsDashboardFragment.this.mBluetoothA2dp = (BluetoothA2dp) bluetoothProfile;
            }
            for (AbstractPreferenceController abstractPreferenceController : DevelopmentSettingsDashboardFragment.this.mPreferenceControllers) {
                if (abstractPreferenceController instanceof BluetoothServiceConnectionListener) {
                    ((BluetoothServiceConnectionListener) abstractPreferenceController).onBluetoothServiceConnected(DevelopmentSettingsDashboardFragment.this.mBluetoothA2dp);
                }
            }
        }

        public void onServiceDisconnected(int i) {
            synchronized (DevelopmentSettingsDashboardFragment.this.mBluetoothA2dpConfigStore) {
                BluetoothA2dp unused = DevelopmentSettingsDashboardFragment.this.mBluetoothA2dp = null;
            }
            for (AbstractPreferenceController abstractPreferenceController : DevelopmentSettingsDashboardFragment.this.mPreferenceControllers) {
                if (abstractPreferenceController instanceof BluetoothServiceConnectionListener) {
                    ((BluetoothServiceConnectionListener) abstractPreferenceController).onBluetoothServiceDisconnected();
                }
            }
        }
    };
    private final BroadcastReceiver mEnableAdbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            for (AbstractPreferenceController abstractPreferenceController : DevelopmentSettingsDashboardFragment.this.mPreferenceControllers) {
                if (abstractPreferenceController instanceof AdbOnChangeListener) {
                    ((AdbOnChangeListener) abstractPreferenceController).onAdbSettingChanged();
                }
            }
        }
    };
    private boolean mIsAvailable = true;
    /* access modifiers changed from: private */
    public List<AbstractPreferenceController> mPreferenceControllers = new ArrayList();
    private SwitchBar mSwitchBar;
    private DevelopmentSwitchBarController mSwitchBarController;

    public int getHelpResource() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "DevSettingsDashboard";
    }

    public int getMetricsCategory() {
        return 39;
    }

    public DevelopmentSettingsDashboardFragment() {
        super("no_debugging_features");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Utils.isMonkeyRunning()) {
            getActivity().finish();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setIfOnlyAvailableForAdmins(true);
        if (isUiRestricted() || !Utils.isDeviceProvisioned(getActivity())) {
            this.mIsAvailable = false;
            if (!isUiRestrictedByOnlyAdmin()) {
                getEmptyTextView().setText(C1715R.string.development_settings_not_available);
            }
            getPreferenceScreen().removeAll();
            return;
        }
        this.mSwitchBar = ((SettingsActivity) getActivity()).getSwitchBar();
        this.mSwitchBarController = new DevelopmentSwitchBarController(this, this.mSwitchBar, this.mIsAvailable, getSettingsLifecycle());
        this.mSwitchBar.show();
        if (DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(getContext())) {
            enableDeveloperOptions();
        } else {
            disableDeveloperOptions();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        registerReceivers();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.getProfileProxy(getActivity(), this.mBluetoothA2dpServiceListener, 2);
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroyView() {
        super.onDestroyView();
        unregisterReceivers();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.closeProfileProxy(2, this.mBluetoothA2dp);
            this.mBluetoothA2dp = null;
        }
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        if (switchR != this.mSwitchBar.getSwitch() || z == DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(getContext())) {
            return;
        }
        if (z) {
            EnableDevelopmentSettingWarningDialog.show(this);
            return;
        }
        BluetoothA2dpHwOffloadPreferenceController bluetoothA2dpHwOffloadPreferenceController = (BluetoothA2dpHwOffloadPreferenceController) getDevelopmentOptionsController(BluetoothA2dpHwOffloadPreferenceController.class);
        if (bluetoothA2dpHwOffloadPreferenceController == null || bluetoothA2dpHwOffloadPreferenceController.isDefaultValue()) {
            disableDeveloperOptions();
        } else {
            DisableDevSettingsDialogFragment.show(this);
        }
    }

    public void onOemUnlockDialogConfirmed() {
        ((OemUnlockPreferenceController) getDevelopmentOptionsController(OemUnlockPreferenceController.class)).onOemUnlockConfirmed();
    }

    public void onOemUnlockDialogDismissed() {
        ((OemUnlockPreferenceController) getDevelopmentOptionsController(OemUnlockPreferenceController.class)).onOemUnlockDismissed();
    }

    public void onEnableAdbDialogConfirmed() {
        ((AdbPreferenceController) getDevelopmentOptionsController(AdbPreferenceController.class)).onAdbDialogConfirmed();
    }

    public void onEnableAdbDialogDismissed() {
        ((AdbPreferenceController) getDevelopmentOptionsController(AdbPreferenceController.class)).onAdbDialogDismissed();
    }

    public void onAdbClearKeysDialogConfirmed() {
        ((ClearAdbKeysPreferenceController) getDevelopmentOptionsController(ClearAdbKeysPreferenceController.class)).onClearAdbKeysConfirmed();
    }

    public void onDisableLogPersistDialogConfirmed() {
        ((LogPersistPreferenceController) getDevelopmentOptionsController(LogPersistPreferenceController.class)).onDisableLogPersistDialogConfirmed();
    }

    public void onDisableLogPersistDialogRejected() {
        ((LogPersistPreferenceController) getDevelopmentOptionsController(LogPersistPreferenceController.class)).onDisableLogPersistDialogRejected();
    }

    public void onA2dpHwDialogConfirmed() {
        ((BluetoothA2dpHwOffloadPreferenceController) getDevelopmentOptionsController(BluetoothA2dpHwOffloadPreferenceController.class)).onA2dpHwDialogConfirmed();
    }

    public void onEnableAdbNetworkDialogConfirmed() {
        ((AdbNetworkPreferenceController) getDevelopmentOptionsController(AdbNetworkPreferenceController.class)).onAdbDialogConfirmed();
    }

    public void onEnableAdbNetworkDialogDismissed() {
        ((AdbNetworkPreferenceController) getDevelopmentOptionsController(AdbNetworkPreferenceController.class)).onAdbDialogDismissed();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z = false;
        for (AbstractPreferenceController next : this.mPreferenceControllers) {
            if (next instanceof OnActivityResultListener) {
                z |= ((OnActivityResultListener) next).onActivityResult(i, i2, intent);
            }
        }
        if (!z) {
            super.onActivityResult(i, i2, intent);
        }
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return Utils.isMonkeyRunning() ? C1715R.xml.placeholder_prefs : C1715R.xml.development_settings;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        if (Utils.isMonkeyRunning()) {
            this.mPreferenceControllers = new ArrayList();
            return null;
        }
        this.mPreferenceControllers = buildPreferenceControllers(context, getActivity(), getSettingsLifecycle(), this, new BluetoothA2dpConfigStore());
        return this.mPreferenceControllers;
    }

    private void registerReceivers() {
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.mEnableAdbReceiver, new IntentFilter("com.android.settingslib.development.AbstractEnableAdbController.ENABLE_ADB_STATE_CHANGED"));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.CODEC_CONFIG_CHANGED");
        getActivity().registerReceiver(this.mBluetoothA2dpReceiver, intentFilter);
    }

    private void unregisterReceivers() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mEnableAdbReceiver);
        getActivity().unregisterReceiver(this.mBluetoothA2dpReceiver);
    }

    private void enableDeveloperOptions() {
        if (!Utils.isMonkeyRunning()) {
            DevelopmentSettingsEnabler.setDevelopmentSettingsEnabled(getContext(), true);
            for (AbstractPreferenceController next : this.mPreferenceControllers) {
                if (next instanceof DeveloperOptionsPreferenceController) {
                    ((DeveloperOptionsPreferenceController) next).onDeveloperOptionsEnabled();
                }
            }
        }
    }

    private void disableDeveloperOptions() {
        if (!Utils.isMonkeyRunning()) {
            DevelopmentSettingsEnabler.setDevelopmentSettingsEnabled(getContext(), false);
            SystemPropPoker instance = SystemPropPoker.getInstance();
            instance.blockPokes();
            for (AbstractPreferenceController next : this.mPreferenceControllers) {
                if (next instanceof DeveloperOptionsPreferenceController) {
                    ((DeveloperOptionsPreferenceController) next).onDeveloperOptionsDisabled();
                }
            }
            instance.unblockPokes();
            instance.poke();
        }
    }

    /* access modifiers changed from: package-private */
    public void onEnableDevelopmentOptionsConfirmed() {
        enableDeveloperOptions();
    }

    /* access modifiers changed from: package-private */
    public void onEnableDevelopmentOptionsRejected() {
        this.mSwitchBar.setChecked(false);
    }

    /* access modifiers changed from: package-private */
    public void onDisableDevelopmentOptionsConfirmed() {
        disableDeveloperOptions();
    }

    /* access modifiers changed from: package-private */
    public void onDisableDevelopmentOptionsRejected() {
        this.mSwitchBar.setChecked(true);
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Activity activity, Lifecycle lifecycle, DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment, BluetoothA2dpConfigStore bluetoothA2dpConfigStore) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MemoryUsagePreferenceController(context));
        arrayList.add(new BugReportPreferenceController(context));
        arrayList.add(new SystemServerHeapDumpPreferenceController(context));
        arrayList.add(new LocalBackupPasswordPreferenceController(context));
        arrayList.add(new StayAwakePreferenceController(context, lifecycle));
        arrayList.add(new HdcpCheckingPreferenceController(context));
        arrayList.add(new BluetoothSnoopLogPreferenceController(context));
        arrayList.add(new OemUnlockPreferenceController(context, activity, developmentSettingsDashboardFragment));
        arrayList.add(new FileEncryptionPreferenceController(context));
        arrayList.add(new PictureColorModePreferenceController(context, lifecycle));
        arrayList.add(new WebViewAppPreferenceController(context));
        arrayList.add(new CoolColorTemperaturePreferenceController(context));
        arrayList.add(new AdbPreferenceController(context, developmentSettingsDashboardFragment));
        arrayList.add(new AdbRootPreferenceController(context, developmentSettingsDashboardFragment));
        arrayList.add(new ClearAdbKeysPreferenceController(context, developmentSettingsDashboardFragment));
        arrayList.add(new LocalTerminalPreferenceController(context));
        arrayList.add(new BugReportInPowerPreferenceController(context));
        arrayList.add(new AutomaticSystemServerHeapDumpPreferenceController(context));
        arrayList.add(new MockLocationAppPreferenceController(context, developmentSettingsDashboardFragment));
        arrayList.add(new DebugViewAttributesPreferenceController(context));
        arrayList.add(new SelectDebugAppPreferenceController(context, developmentSettingsDashboardFragment));
        arrayList.add(new WaitForDebuggerPreferenceController(context));
        arrayList.add(new EnableGpuDebugLayersPreferenceController(context));
        arrayList.add(new VerifyAppsOverUsbPreferenceController(context));
        arrayList.add(new ArtVerifierPreferenceController(context));
        arrayList.add(new LogdSizePreferenceController(context));
        arrayList.add(new LogPersistPreferenceController(context, developmentSettingsDashboardFragment, lifecycle));
        arrayList.add(new CameraLaserSensorPreferenceController(context));
        arrayList.add(new WifiDisplayCertificationPreferenceController(context));
        arrayList.add(new WifiVerboseLoggingPreferenceController(context));
        arrayList.add(new WifiScanThrottlingPreferenceController(context));
        arrayList.add(new MobileDataAlwaysOnPreferenceController(context));
        arrayList.add(new TetheringHardwareAccelPreferenceController(context));
        arrayList.add(new BluetoothDeviceNoNamePreferenceController(context));
        arrayList.add(new BluetoothAbsoluteVolumePreferenceController(context));
        arrayList.add(new BluetoothAvrcpVersionPreferenceController(context));
        arrayList.add(new BluetoothA2dpHwOffloadPreferenceController(context, developmentSettingsDashboardFragment));
        arrayList.add(new BluetoothAudioCodecPreferenceController(context, lifecycle, bluetoothA2dpConfigStore));
        arrayList.add(new BluetoothAudioSampleRatePreferenceController(context, lifecycle, bluetoothA2dpConfigStore));
        arrayList.add(new BluetoothAudioBitsPerSamplePreferenceController(context, lifecycle, bluetoothA2dpConfigStore));
        arrayList.add(new BluetoothAudioChannelModePreferenceController(context, lifecycle, bluetoothA2dpConfigStore));
        arrayList.add(new BluetoothAudioQualityPreferenceController(context, lifecycle, bluetoothA2dpConfigStore));
        arrayList.add(new BluetoothMaxConnectedAudioDevicesPreferenceController(context));
        arrayList.add(new ShowTapsPreferenceController(context));
        arrayList.add(new PointerLocationPreferenceController(context));
        arrayList.add(new ShowSurfaceUpdatesPreferenceController(context));
        arrayList.add(new ShowLayoutBoundsPreferenceController(context));
        arrayList.add(new RtlLayoutPreferenceController(context));
        arrayList.add(new WindowAnimationScalePreferenceController(context));
        arrayList.add(new EmulateDisplayCutoutPreferenceController(context));
        arrayList.add(new TransitionAnimationScalePreferenceController(context));
        arrayList.add(new AnimatorDurationScalePreferenceController(context));
        arrayList.add(new SecondaryDisplayPreferenceController(context));
        arrayList.add(new GpuViewUpdatesPreferenceController(context));
        arrayList.add(new HardwareLayersUpdatesPreferenceController(context));
        arrayList.add(new DebugGpuOverdrawPreferenceController(context));
        arrayList.add(new DebugNonRectClipOperationsPreferenceController(context));
        arrayList.add(new ForceDarkPreferenceController(context));
        arrayList.add(new ForceMSAAPreferenceController(context));
        arrayList.add(new HardwareOverlaysPreferenceController(context));
        arrayList.add(new SimulateColorSpacePreferenceController(context));
        arrayList.add(new SetGpuRendererPreferenceController(context));
        arrayList.add(new UsbAudioRoutingPreferenceController(context));
        arrayList.add(new StrictModePreferenceController(context));
        arrayList.add(new ProfileGpuRenderingPreferenceController(context));
        arrayList.add(new KeepActivitiesPreferenceController(context));
        arrayList.add(new BackgroundProcessLimitPreferenceController(context));
        arrayList.add(new ShowFirstCrashDialogPreferenceController(context));
        arrayList.add(new AppsNotRespondingPreferenceController(context));
        arrayList.add(new NotificationChannelWarningsPreferenceController(context));
        arrayList.add(new AllowAppsOnExternalPreferenceController(context));
        arrayList.add(new ResizableActivityPreferenceController(context));
        arrayList.add(new FreeformWindowsPreferenceController(context));
        arrayList.add(new DesktopModePreferenceController(context));
        arrayList.add(new ShortcutManagerThrottlingPreferenceController(context));
        arrayList.add(new BubbleGlobalPreferenceController(context));
        arrayList.add(new EnableGnssRawMeasFullTrackingPreferenceController(context));
        arrayList.add(new DefaultLaunchPreferenceController(context, "running_apps"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "demo_mode"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "quick_settings_tiles"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "feature_flags_dashboard"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "default_usb_configuration"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "background_check"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "inactive_apps"));
        arrayList.add(new AutofillLoggingLevelPreferenceController(context, lifecycle));
        arrayList.add(new AutofillResetOptionsPreferenceController(context));
        arrayList.add(new TrustAgentsExtendUnlockPreferenceController(context));
        arrayList.add(new TrustLostLocksScreenPreferenceController(context));
        arrayList.add(new AdbNetworkPreferenceController(context, developmentSettingsDashboardFragment));
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public <T extends AbstractPreferenceController> T getDevelopmentOptionsController(Class<T> cls) {
        return use(cls);
    }
}
