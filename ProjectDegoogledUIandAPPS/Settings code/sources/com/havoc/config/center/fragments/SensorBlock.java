package com.havoc.config.center.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.PackageListAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SensorBlock extends SettingsPreferenceFragment implements Preference.OnPreferenceClickListener, CompoundButton.OnCheckedChangeListener {
    private Preference mAddSensorBlockPref;
    private String mBlockedPackageList;
    /* access modifiers changed from: private */
    public Map<String, Package> mBlockedPackages;
    private Context mContext;
    private PackageListAdapter mPackageAdapter;
    private PackageManager mPackageManager;
    /* access modifiers changed from: private */
    public PreferenceGroup mSensorBlockPrefList;
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
        addPreferencesFromResource(C1715R.xml.sensor_block_settings);
        this.mFooterPreferenceMixin.createFooterPreference().setTitle((int) C1715R.string.add_sensor_block_package_summary);
        getPreferenceScreen();
        this.mPackageManager = getPackageManager();
        this.mPackageAdapter = new PackageListAdapter(getActivity());
        this.mSensorBlockPrefList = (PreferenceGroup) findPreference("sensor_block_applications");
        this.mSensorBlockPrefList.setOrderingAsAdded(false);
        this.mBlockedPackages = new HashMap();
        this.mAddSensorBlockPref = findPreference("add_sensor_block_packages");
        this.mAddSensorBlockPref.setOnPreferenceClickListener(this);
        this.mContext = getActivity().getApplicationContext();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = false;
        if (Settings.System.getInt(getContentResolver(), "sensor_block", 0) == 1) {
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
                SensorBlock.this.lambda$onViewCreated$0$SensorBlock(this.f$1, view);
            }
        });
        this.mSensorBlockPrefList.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$SensorBlock(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.System.putInt(getContentResolver(), "sensor_block", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mSensorBlockPrefList.setEnabled(z);
    }

    public void onResume() {
        super.onResume();
        refreshCustomApplicationPrefs();
    }

    public Dialog onCreateDialog(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ListView listView = new ListView(getActivity());
        listView.setAdapter(this.mPackageAdapter);
        listView.setDivider((Drawable) null);
        builder.setTitle(C1715R.string.profile_choose_app);
        builder.setView(listView);
        final AlertDialog create = builder.create();
        if (i == 1) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    SensorBlock sensorBlock = SensorBlock.this;
                    sensorBlock.addCustomApplicationPref(((PackageListAdapter.PackageItem) adapterView.getItemAtPosition(i)).packageName, sensorBlock.mBlockedPackages);
                    create.cancel();
                }
            });
        }
        return create;
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

    private void refreshCustomApplicationPrefs() {
        PreferenceGroup preferenceGroup;
        if (parsePackageList() && (preferenceGroup = this.mSensorBlockPrefList) != null) {
            preferenceGroup.removeAll();
            for (Package createPreferenceFromInfo : this.mBlockedPackages.values()) {
                try {
                    this.mSensorBlockPrefList.addPreference(createPreferenceFromInfo(createPreferenceFromInfo));
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            this.mAddSensorBlockPref.setOrder(0);
            this.mSensorBlockPrefList.addPreference(this.mAddSensorBlockPref);
        }
    }

    public boolean onPreferenceClick(final Preference preference) {
        if (preference == this.mAddSensorBlockPref) {
            showDialog(1);
        } else {
            new AlertDialog.Builder(getActivity()).setTitle(C1715R.string.dialog_delete_title).setMessage(C1715R.string.dialog_delete_message).setIconAttribute(16843605).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (preference == SensorBlock.this.mSensorBlockPrefList.findPreference(preference.getKey())) {
                        SensorBlock.this.removeApplicationPref(preference.getKey(), SensorBlock.this.mBlockedPackages);
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
        String string = Settings.System.getString(getContentResolver(), "sensor_blocked_app");
        if (string == null || TextUtils.equals(this.mBlockedPackageList, string)) {
            return false;
        }
        this.mBlockedPackageList = string;
        this.mBlockedPackages.clear();
        parseAndAddToMap(string, this.mBlockedPackages);
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
        String str = map == this.mBlockedPackages ? "sensor_blocked_app" : "sensor_blocked_app_dummy";
        ArrayList arrayList = new ArrayList();
        for (Package packageR : map.values()) {
            arrayList.add(packageR.toString());
        }
        String join = TextUtils.join("|", arrayList);
        if (z && TextUtils.equals(str, "sensor_blocked_app")) {
            this.mBlockedPackageList = join;
        }
        Settings.System.putString(getContentResolver(), str, join);
    }
}
