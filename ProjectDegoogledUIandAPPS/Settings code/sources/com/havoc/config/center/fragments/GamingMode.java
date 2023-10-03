package com.havoc.config.center.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.PackageListAdapter;
import com.havoc.support.preferences.SystemSettingListPreference;
import com.havoc.support.preferences.SystemSettingSwitchPreference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamingMode extends SettingsPreferenceFragment implements Preference.OnPreferenceClickListener, CompoundButton.OnCheckedChangeListener {
    private Preference mAddGamingPref;
    /* access modifiers changed from: private */
    public Context mContext;
    private SystemSettingSwitchPreference mDynamicMode;
    private SystemSettingListPreference mGamingNotification;
    private String mGamingPackageList;
    /* access modifiers changed from: private */
    public Map<String, Package> mGamingPackages;
    /* access modifiers changed from: private */
    public PreferenceGroup mGamingPrefList;
    private SystemSettingSwitchPreference mHardwareKeysDisable;
    private SystemSettingSwitchPreference mHeadsUpDisable;
    private SystemSettingSwitchPreference mManualBrightness;
    private PackageListAdapter mPackageAdapter;
    private PackageManager mPackageManager;
    private SystemSettingListPreference mRingerMode;
    private View mSwitchBar;
    private TextView mTextView;

    public int getDialogMetricsCategory(int i) {
        return i == 1 ? 1999 : 0;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.gaming_mode_settings);
        this.mFooterPreferenceMixin.createFooterPreference().setTitle((int) C1715R.string.add_gaming_mode_package_summary);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mRingerMode = (SystemSettingListPreference) findPreference("gaming_mode_ringer_mode");
        this.mGamingNotification = (SystemSettingListPreference) findPreference("gaming_mode_notifications");
        this.mHeadsUpDisable = (SystemSettingSwitchPreference) findPreference("gaming_mode_headsup_toggle");
        this.mHardwareKeysDisable = (SystemSettingSwitchPreference) findPreference("gaming_mode_hw_keys_toggle");
        this.mManualBrightness = (SystemSettingSwitchPreference) findPreference("gaming_mode_manual_brightness_toggle");
        this.mDynamicMode = (SystemSettingSwitchPreference) findPreference("gaming_mode_dynamic_state");
        if (!hasHWkeys()) {
            preferenceScreen.removePreference(this.mHardwareKeysDisable);
        }
        this.mPackageManager = getPackageManager();
        this.mPackageAdapter = new PackageListAdapter(getActivity());
        this.mGamingPrefList = (PreferenceGroup) findPreference("gamingmode_applications");
        this.mGamingPrefList.setOrderingAsAdded(false);
        this.mGamingPackages = new HashMap();
        this.mAddGamingPref = findPreference("add_gamingmode_packages");
        this.mAddGamingPref.setOnPreferenceClickListener(this);
        this.mContext = getActivity().getApplicationContext();
        new SettingsObserver(new Handler(Looper.getMainLooper())).observe();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = false;
        if (Settings.System.getInt(getContentResolver(), "gaming_mode_enabled", 0) == 1) {
            z = true;
        }
        this.mTextView = (TextView) view.findViewById(C1715R.C1718id.switch_text);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar = view.findViewById(C1715R.C1718id.switch_bar);
        Switch switchR = (Switch) this.mSwitchBar.findViewById(16908352);
        switchR.setChecked(z);
        switchR.setOnCheckedChangeListener(this);
        this.mSwitchBar.setActivated(z);
        this.mSwitchBar.setOnClickListener(new View.OnClickListener(switchR) {
            private final /* synthetic */ Switch f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                GamingMode.this.lambda$onViewCreated$0$GamingMode(this.f$1, view);
            }
        });
        this.mRingerMode.setEnabled(z);
        this.mGamingNotification.setEnabled(z);
        this.mHeadsUpDisable.setEnabled(z);
        this.mHardwareKeysDisable.setEnabled(z);
        this.mManualBrightness.setEnabled(z);
        this.mDynamicMode.setEnabled(z);
        this.mAddGamingPref.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$GamingMode(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.System.putInt(getContentResolver(), "gaming_mode_enabled", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mRingerMode.setEnabled(z);
        this.mGamingNotification.setEnabled(z);
        this.mHeadsUpDisable.setEnabled(z);
        this.mHardwareKeysDisable.setEnabled(z);
        this.mManualBrightness.setEnabled(z);
        this.mDynamicMode.setEnabled(z);
        this.mAddGamingPref.setEnabled(z);
    }

    public void onResume() {
        super.onResume();
        refreshCustomApplicationPrefs();
    }

    public Dialog onCreateDialog(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ListView listView = new ListView(getActivity());
        listView.setAdapter(this.mPackageAdapter);
        builder.setTitle(C1715R.string.profile_choose_app);
        builder.setView(listView);
        final AlertDialog create = builder.create();
        if (i == 1) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    GamingMode gamingMode = GamingMode.this;
                    gamingMode.addCustomApplicationPref(((PackageListAdapter.PackageItem) adapterView.getItemAtPosition(i)).packageName, gamingMode.mGamingPackages);
                    create.cancel();
                }
            });
        }
        return create;
    }

    class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            GamingMode.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("gaming_mode_active"), false, this, -1);
        }

        public void onChange(boolean z, Uri uri) {
            if (uri.equals(Settings.System.getUriFor("gaming_mode_active"))) {
                int i = Settings.System.getInt(GamingMode.this.mContext.getContentResolver(), "gaming_mode_active", 0);
            }
        }
    }

    private static class Package {
        public String name;

        public Package(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }

        public static Package fromString(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                return new Package(str);
            } catch (NumberFormatException unused) {
                return null;
            }
        }
    }

    private boolean hasHWkeys() {
        int integer = getContext().getResources().getInteger(17694793);
        return ((integer & 1) != 0) || ((integer & 2) != 0) || ((integer & 4) != 0) || ((integer & 16) != 0);
    }

    private void refreshCustomApplicationPrefs() {
        PreferenceGroup preferenceGroup;
        if (parsePackageList() && (preferenceGroup = this.mGamingPrefList) != null) {
            preferenceGroup.removeAll();
            for (Package createPreferenceFromInfo : this.mGamingPackages.values()) {
                try {
                    this.mGamingPrefList.addPreference(createPreferenceFromInfo(createPreferenceFromInfo));
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            this.mAddGamingPref.setOrder(0);
            this.mGamingPrefList.addPreference(this.mAddGamingPref);
        }
    }

    public boolean onPreferenceClick(final Preference preference) {
        if (preference == this.mAddGamingPref) {
            showDialog(1);
        } else {
            new AlertDialog.Builder(getActivity()).setTitle(C1715R.string.dialog_delete_title).setMessage(C1715R.string.dialog_delete_message).setIconAttribute(16843605).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (preference == GamingMode.this.mGamingPrefList.findPreference(preference.getKey())) {
                        GamingMode.this.removeApplicationPref(preference.getKey(), GamingMode.this.mGamingPackages);
                    }
                }
            }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).show();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void addCustomApplicationPref(String str, Map<String, Package> map) {
        if (map.get(str) == null) {
            map.put(str, new Package(str));
            savePackageList(false, map);
            refreshCustomApplicationPrefs();
        }
    }

    private Preference createPreferenceFromInfo(Package packageR) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = this.mPackageManager.getPackageInfo(packageR.name, 128);
        Preference preference = new Preference(getActivity());
        preference.setKey(packageR.name);
        preference.setTitle(packageInfo.applicationInfo.loadLabel(this.mPackageManager));
        preference.setIcon(packageInfo.applicationInfo.loadIcon(this.mPackageManager));
        preference.setPersistent(false);
        preference.setOnPreferenceClickListener(this);
        return preference;
    }

    /* access modifiers changed from: private */
    public void removeApplicationPref(String str, Map<String, Package> map) {
        if (map.remove(str) != null) {
            savePackageList(false, map);
            refreshCustomApplicationPrefs();
        }
    }

    private boolean parsePackageList() {
        String string = Settings.System.getString(getContentResolver(), "gaming_mode_values");
        if (TextUtils.equals(this.mGamingPackageList, string)) {
            return false;
        }
        this.mGamingPackageList = string;
        this.mGamingPackages.clear();
        parseAndAddToMap(string, this.mGamingPackages);
        return true;
    }

    private void parseAndAddToMap(String str, Map<String, Package> map) {
        if (str != null) {
            for (String str2 : TextUtils.split(str, "\\|")) {
                if (!TextUtils.isEmpty(str2)) {
                    Package fromString = Package.fromString(str2);
                    map.put(fromString.name, fromString);
                }
            }
        }
    }

    private void savePackageList(boolean z, Map<String, Package> map) {
        String str = map == this.mGamingPackages ? "gaming_mode_values" : "gaming_mode_dummy";
        ArrayList arrayList = new ArrayList();
        for (Package packageR : map.values()) {
            arrayList.add(packageR.toString());
        }
        String join = TextUtils.join("|", arrayList);
        if (z && TextUtils.equals(str, "gaming_mode_values")) {
            this.mGamingPackageList = join;
        }
        Settings.System.putString(getContentResolver(), str, join);
    }
}
