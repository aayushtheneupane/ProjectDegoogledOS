package com.android.settings;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothPan;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.SearchIndexableResource;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.settings.datausage.DataSaverBackend;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.wifi.tether.WifiTetherPreferenceController;
import com.android.settingslib.TetherUtil;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TetherSettings extends RestrictedSettingsFragment implements DataSaverBackend.Listener {
    static final String KEY_ENABLE_BLUETOOTH_TETHERING = "enable_bluetooth_tethering";
    static final String KEY_TETHER_PREFS_SCREEN = "tether_prefs_screen";
    static final String KEY_USB_TETHER_SETTINGS = "usb_tether_settings";
    static final String KEY_WIFI_TETHER = "wifi_tether";
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.tether_prefs;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<String> getNonIndexableKeys(Context context) {
            List<String> nonIndexableKeys = super.getNonIndexableKeys(context);
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
            if (!TetherUtil.isTetherAvailable(context)) {
                nonIndexableKeys.add(TetherSettings.KEY_TETHER_PREFS_SCREEN);
                nonIndexableKeys.add(TetherSettings.KEY_WIFI_TETHER);
            }
            boolean z = true;
            if (!(connectivityManager.getTetherableUsbRegexs().length != 0) || Utils.isMonkeyRunning()) {
                nonIndexableKeys.add(TetherSettings.KEY_USB_TETHER_SETTINGS);
            }
            if (connectivityManager.getTetherableBluetoothRegexs().length == 0) {
                z = false;
            }
            if (!z) {
                nonIndexableKeys.add(TetherSettings.KEY_ENABLE_BLUETOOTH_TETHERING);
            }
            return nonIndexableKeys;
        }
    };
    /* access modifiers changed from: private */
    public boolean mBluetoothEnableForTether;
    /* access modifiers changed from: private */
    public AtomicReference<BluetoothPan> mBluetoothPan = new AtomicReference<>();
    private String[] mBluetoothRegexs;
    private SwitchPreference mBluetoothTether;
    private ConnectivityManager mCm;
    private DataSaverBackend mDataSaverBackend;
    private boolean mDataSaverEnabled;
    private Preference mDataSaverFooter;
    private Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public boolean mMassStorageActive;
    private BluetoothProfile.ServiceListener mProfileServiceListener = new BluetoothProfile.ServiceListener() {
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            TetherSettings.this.mBluetoothPan.set((BluetoothPan) bluetoothProfile);
        }

        public void onServiceDisconnected(int i) {
            TetherSettings.this.mBluetoothPan.set((Object) null);
        }
    };
    private OnStartTetheringCallback mStartTetheringCallback;
    private BroadcastReceiver mTetherChangeReceiver;
    private boolean mUnavailable;
    /* access modifiers changed from: private */
    public boolean mUsbConnected;
    private String[] mUsbRegexs;
    private SwitchPreference mUsbTether;
    private WifiTetherPreferenceController mWifiTetherPreferenceController;

    public int getHelpResource() {
        return C1715R.string.help_url_tether;
    }

    public int getMetricsCategory() {
        return 90;
    }

    public void onBlacklistStatusChanged(int i, boolean z) {
    }

    public void onWhitelistStatusChanged(int i, boolean z) {
    }

    public TetherSettings() {
        super("no_config_tethering");
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mWifiTetherPreferenceController = new WifiTetherPreferenceController(context, getSettingsLifecycle());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.tether_prefs);
        this.mFooterPreferenceMixin.createFooterPreference().setTitle((int) C1715R.string.tethering_footer_info);
        this.mDataSaverBackend = new DataSaverBackend(getContext());
        this.mDataSaverEnabled = this.mDataSaverBackend.isDataSaverEnabled();
        this.mDataSaverFooter = findPreference("disabled_on_data_saver");
        setIfOnlyAvailableForAdmins(true);
        if (isUiRestricted()) {
            this.mUnavailable = true;
            getPreferenceScreen().removeAll();
            return;
        }
        FragmentActivity activity = getActivity();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.getProfileProxy(activity.getApplicationContext(), this.mProfileServiceListener, 5);
        }
        this.mUsbTether = (SwitchPreference) findPreference(KEY_USB_TETHER_SETTINGS);
        this.mBluetoothTether = (SwitchPreference) findPreference(KEY_ENABLE_BLUETOOTH_TETHERING);
        this.mDataSaverBackend.addListener(this);
        this.mCm = (ConnectivityManager) getSystemService("connectivity");
        this.mUsbRegexs = this.mCm.getTetherableUsbRegexs();
        this.mBluetoothRegexs = this.mCm.getTetherableBluetoothRegexs();
        boolean z = this.mUsbRegexs.length != 0;
        boolean z2 = this.mBluetoothRegexs.length != 0;
        if (!z || Utils.isMonkeyRunning()) {
            getPreferenceScreen().removePreference(this.mUsbTether);
        }
        this.mWifiTetherPreferenceController.displayPreference(getPreferenceScreen());
        if (!z2) {
            getPreferenceScreen().removePreference(this.mBluetoothTether);
        } else {
            BluetoothPan bluetoothPan = this.mBluetoothPan.get();
            if (bluetoothPan == null || !bluetoothPan.isTetheringOn()) {
                this.mBluetoothTether.setChecked(false);
            } else {
                this.mBluetoothTether.setChecked(true);
            }
        }
        onDataSaverChanged(this.mDataSaverBackend.isDataSaverEnabled());
    }

    public void onDestroy() {
        this.mDataSaverBackend.remListener(this);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothProfile andSet = this.mBluetoothPan.getAndSet((Object) null);
        if (!(andSet == null || defaultAdapter == null)) {
            defaultAdapter.closeProfileProxy(5, andSet);
        }
        super.onDestroy();
    }

    public void onDataSaverChanged(boolean z) {
        this.mDataSaverEnabled = z;
        this.mUsbTether.setEnabled(!this.mDataSaverEnabled);
        this.mBluetoothTether.setEnabled(!this.mDataSaverEnabled);
        this.mDataSaverFooter.setVisible(this.mDataSaverEnabled);
    }

    private class TetherChangeReceiver extends BroadcastReceiver {
        private TetherChangeReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.net.conn.TETHER_STATE_CHANGED")) {
                ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("availableArray");
                ArrayList<String> stringArrayListExtra2 = intent.getStringArrayListExtra("tetherArray");
                ArrayList<String> stringArrayListExtra3 = intent.getStringArrayListExtra("erroredArray");
                TetherSettings.this.updateState((String[]) stringArrayListExtra.toArray(new String[stringArrayListExtra.size()]), (String[]) stringArrayListExtra2.toArray(new String[stringArrayListExtra2.size()]), (String[]) stringArrayListExtra3.toArray(new String[stringArrayListExtra3.size()]));
            } else if (action.equals("android.intent.action.MEDIA_SHARED")) {
                boolean unused = TetherSettings.this.mMassStorageActive = true;
                TetherSettings.this.updateState();
            } else if (action.equals("android.intent.action.MEDIA_UNSHARED")) {
                boolean unused2 = TetherSettings.this.mMassStorageActive = false;
                TetherSettings.this.updateState();
            } else if (action.equals("android.hardware.usb.action.USB_STATE")) {
                boolean unused3 = TetherSettings.this.mUsbConnected = intent.getBooleanExtra("connected", false);
                TetherSettings.this.updateState();
            } else if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                if (TetherSettings.this.mBluetoothEnableForTether) {
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                    if (intExtra == Integer.MIN_VALUE || intExtra == 10) {
                        boolean unused4 = TetherSettings.this.mBluetoothEnableForTether = false;
                    } else if (intExtra == 12) {
                        TetherSettings.this.startTethering(2);
                        boolean unused5 = TetherSettings.this.mBluetoothEnableForTether = false;
                    }
                }
                TetherSettings.this.updateState();
            }
        }
    }

    public void onStart() {
        super.onStart();
        if (this.mUnavailable) {
            if (!isUiRestrictedByOnlyAdmin()) {
                getEmptyTextView().setText(C1715R.string.tethering_settings_not_available);
            }
            getPreferenceScreen().removeAll();
            return;
        }
        FragmentActivity activity = getActivity();
        this.mStartTetheringCallback = new OnStartTetheringCallback(this);
        this.mMassStorageActive = "shared".equals(Environment.getExternalStorageState());
        this.mTetherChangeReceiver = new TetherChangeReceiver();
        Intent registerReceiver = activity.registerReceiver(this.mTetherChangeReceiver, new IntentFilter("android.net.conn.TETHER_STATE_CHANGED"));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_STATE");
        activity.registerReceiver(this.mTetherChangeReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.MEDIA_SHARED");
        intentFilter2.addAction("android.intent.action.MEDIA_UNSHARED");
        intentFilter2.addDataScheme("file");
        activity.registerReceiver(this.mTetherChangeReceiver, intentFilter2);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        activity.registerReceiver(this.mTetherChangeReceiver, intentFilter3);
        if (registerReceiver != null) {
            this.mTetherChangeReceiver.onReceive(activity, registerReceiver);
        }
        updateState();
    }

    public void onStop() {
        super.onStop();
        if (!this.mUnavailable) {
            getActivity().unregisterReceiver(this.mTetherChangeReceiver);
            this.mTetherChangeReceiver = null;
            this.mStartTetheringCallback = null;
        }
    }

    /* access modifiers changed from: private */
    public void updateState() {
        updateState(this.mCm.getTetherableIfaces(), this.mCm.getTetheredIfaces(), this.mCm.getTetheringErroredIfaces());
    }

    /* access modifiers changed from: private */
    public void updateState(String[] strArr, String[] strArr2, String[] strArr3) {
        updateUsbState(strArr, strArr2, strArr3);
        updateBluetoothState();
    }

    private void updateUsbState(String[] strArr, String[] strArr2, String[] strArr3) {
        boolean z = this.mUsbConnected && !this.mMassStorageActive;
        int length = strArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str = strArr[i];
            int i3 = i2;
            for (String matches : this.mUsbRegexs) {
                if (str.matches(matches) && i3 == 0) {
                    i3 = this.mCm.getLastTetherError(str);
                }
            }
            i++;
            i2 = i3;
        }
        int length2 = strArr2.length;
        int i4 = 0;
        boolean z2 = false;
        while (i4 < length2) {
            String str2 = strArr2[i4];
            boolean z3 = z2;
            for (String matches2 : this.mUsbRegexs) {
                if (str2.matches(matches2)) {
                    z3 = true;
                }
            }
            i4++;
            z2 = z3;
        }
        for (String str3 : strArr3) {
            for (String matches3 : this.mUsbRegexs) {
                str3.matches(matches3);
            }
        }
        if (z2) {
            this.mUsbTether.setEnabled(!this.mDataSaverEnabled);
            this.mUsbTether.setChecked(true);
        } else if (z) {
            this.mUsbTether.setEnabled(!this.mDataSaverEnabled);
            this.mUsbTether.setChecked(false);
        } else {
            this.mUsbTether.setEnabled(false);
            this.mUsbTether.setChecked(false);
        }
    }

    private void updateBluetoothState() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            int state = defaultAdapter.getState();
            if (state == 13) {
                this.mBluetoothTether.setEnabled(false);
            } else if (state == 11) {
                this.mBluetoothTether.setEnabled(false);
            } else {
                BluetoothPan bluetoothPan = this.mBluetoothPan.get();
                if (state != 12 || bluetoothPan == null || !bluetoothPan.isTetheringOn()) {
                    this.mBluetoothTether.setEnabled(!this.mDataSaverEnabled);
                    this.mBluetoothTether.setChecked(false);
                    return;
                }
                this.mBluetoothTether.setChecked(true);
                this.mBluetoothTether.setEnabled(!this.mDataSaverEnabled);
            }
        }
    }

    public static boolean isProvisioningNeededButUnavailable(Context context) {
        return TetherUtil.isProvisioningNeeded(context) && !isIntentAvailable(context);
    }

    private static boolean isIntentAvailable(Context context) {
        String[] stringArray = context.getResources().getStringArray(17236057);
        if (stringArray.length < 2) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName(stringArray[0], stringArray[1]);
        if (packageManager.queryIntentActivities(intent, 65536).size() > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void startTethering(int i) {
        if (i == 2) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter.getState() == 10) {
                this.mBluetoothEnableForTether = true;
                defaultAdapter.enable();
                this.mBluetoothTether.setEnabled(false);
                return;
            }
        }
        this.mCm.startTethering(i, true, this.mStartTetheringCallback, this.mHandler);
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        SwitchPreference switchPreference = this.mUsbTether;
        if (preference != switchPreference) {
            SwitchPreference switchPreference2 = this.mBluetoothTether;
            if (preference == switchPreference2) {
                if (switchPreference2.isChecked()) {
                    startTethering(2);
                } else {
                    this.mCm.stopTethering(2);
                }
            }
        } else if (switchPreference.isChecked()) {
            startTethering(1);
        } else {
            this.mCm.stopTethering(1);
        }
        return super.onPreferenceTreeClick(preference);
    }

    private static final class OnStartTetheringCallback extends ConnectivityManager.OnStartTetheringCallback {
        final WeakReference<TetherSettings> mTetherSettings;

        OnStartTetheringCallback(TetherSettings tetherSettings) {
            this.mTetherSettings = new WeakReference<>(tetherSettings);
        }

        public void onTetheringStarted() {
            update();
        }

        public void onTetheringFailed() {
            update();
        }

        private void update() {
            TetherSettings tetherSettings = (TetherSettings) this.mTetherSettings.get();
            if (tetherSettings != null) {
                tetherSettings.updateState();
            }
        }
    }
}
