package com.havoc.config.center.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
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
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.PackageListAdapter;
import com.havoc.support.preferences.SystemSettingSwitchPreference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HeadsUp extends SettingsPreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
    private Preference mAddBlacklistPref;
    private Preference mAddStoplistPref;
    private String mBlacklistPackageList;
    /* access modifiers changed from: private */
    public Map<String, Package> mBlacklistPackages;
    /* access modifiers changed from: private */
    public PreferenceGroup mBlacklistPrefList;
    private ListPreference mHeadsUpSnoozeTime;
    private ListPreference mHeadsUpTimeOut;
    private SystemSettingSwitchPreference mLessBoring;
    private SystemSettingSwitchPreference mMediaHeadsUp;
    private PackageListAdapter mPackageAdapter;
    private PackageManager mPackageManager;
    private String mStoplistPackageList;
    /* access modifiers changed from: private */
    public Map<String, Package> mStoplistPackages;
    /* access modifiers changed from: private */
    public PreferenceGroup mStoplistPrefList;
    private View mSwitchBar;
    private TextView mTextView;

    public int getDialogMetricsCategory(int i) {
        return (i == 0 || i == 1) ? 1999 : 0;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.heads_up_settings);
        this.mPackageManager = getPackageManager();
        this.mPackageAdapter = new PackageListAdapter(getActivity());
        this.mStoplistPrefList = (PreferenceGroup) findPreference("stoplist_applications");
        this.mStoplistPrefList.setOrderingAsAdded(false);
        this.mBlacklistPrefList = (PreferenceGroup) findPreference("blacklist_applications");
        this.mBlacklistPrefList.setOrderingAsAdded(false);
        this.mStoplistPackages = new HashMap();
        this.mBlacklistPackages = new HashMap();
        this.mAddStoplistPref = findPreference("add_stoplist_packages");
        this.mAddBlacklistPref = findPreference("add_blacklist_packages");
        this.mAddStoplistPref.setOnPreferenceClickListener(this);
        this.mAddBlacklistPref.setOnPreferenceClickListener(this);
        try {
            Resources resourcesForApplication = getPackageManager().getResourcesForApplication("com.android.systemui");
            this.mLessBoring = (SystemSettingSwitchPreference) findPreference("less_boring_heads_up");
            this.mMediaHeadsUp = (SystemSettingSwitchPreference) findPreference("show_media_heads_up");
            int integer = resourcesForApplication.getInteger(resourcesForApplication.getIdentifier("com.android.systemui:integer/heads_up_notification_decay", (String) null, (String) null));
            this.mHeadsUpTimeOut = (ListPreference) findPreference("heads_up_time_out");
            this.mHeadsUpTimeOut.setOnPreferenceChangeListener(this);
            int i = Settings.System.getInt(getContentResolver(), "heads_up_timeout", integer);
            this.mHeadsUpTimeOut.setValue(String.valueOf(i));
            updateHeadsUpTimeOutSummary(i);
            int integer2 = resourcesForApplication.getInteger(resourcesForApplication.getIdentifier("com.android.systemui:integer/heads_up_default_snooze_length_ms", (String) null, (String) null));
            this.mHeadsUpSnoozeTime = (ListPreference) findPreference("heads_up_snooze_time");
            this.mHeadsUpSnoozeTime.setOnPreferenceChangeListener(this);
            int i2 = Settings.System.getInt(getContentResolver(), "heads_up_notification_snooze", integer2);
            this.mHeadsUpSnoozeTime.setValue(String.valueOf(i2));
            updateHeadsUpSnoozeTimeSummary(i2);
        } catch (Exception unused) {
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = true;
        if (Settings.Global.getInt(getContentResolver(), "heads_up_notifications_enabled", 1) != 1) {
            z = false;
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
                HeadsUp.this.lambda$onViewCreated$0$HeadsUp(this.f$1, view);
            }
        });
        this.mHeadsUpTimeOut.setEnabled(z);
        this.mHeadsUpSnoozeTime.setEnabled(z);
        this.mLessBoring.setEnabled(z);
        this.mAddStoplistPref.setEnabled(z);
        this.mAddBlacklistPref.setEnabled(z);
        this.mMediaHeadsUp.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$HeadsUp(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.Global.putInt(getContentResolver(), "heads_up_notifications_enabled", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mHeadsUpTimeOut.setEnabled(z);
        this.mHeadsUpSnoozeTime.setEnabled(z);
        this.mLessBoring.setEnabled(z);
        this.mAddStoplistPref.setEnabled(z);
        this.mAddBlacklistPref.setEnabled(z);
        this.mMediaHeadsUp.setEnabled(z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mHeadsUpTimeOut) {
            int intValue = Integer.valueOf((String) obj).intValue();
            Settings.System.putInt(getContentResolver(), "heads_up_timeout", intValue);
            updateHeadsUpTimeOutSummary(intValue);
            return true;
        } else if (preference != this.mHeadsUpSnoozeTime) {
            return false;
        } else {
            int intValue2 = Integer.valueOf((String) obj).intValue();
            Settings.System.putInt(getContentResolver(), "heads_up_notification_snooze", intValue2);
            updateHeadsUpSnoozeTimeSummary(intValue2);
            return true;
        }
    }

    private void updateHeadsUpTimeOutSummary(int i) {
        this.mHeadsUpTimeOut.setSummary(getResources().getString(C1715R.string.heads_up_time_out_summary, new Object[]{Integer.valueOf(i / 1000)}));
    }

    private void updateHeadsUpSnoozeTimeSummary(int i) {
        if (i == 0) {
            this.mHeadsUpSnoozeTime.setSummary(getResources().getString(C1715R.string.heads_up_snooze_disabled_summary));
        } else if (i == 60000) {
            this.mHeadsUpSnoozeTime.setSummary(getResources().getString(C1715R.string.heads_up_snooze_summary_one_minute));
        } else {
            this.mHeadsUpSnoozeTime.setSummary(getResources().getString(C1715R.string.heads_up_snooze_summary, new Object[]{Integer.valueOf((i / 60) / 1000)}));
        }
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
        if (i == 0) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    HeadsUp headsUp = HeadsUp.this;
                    headsUp.addCustomApplicationPref(((PackageListAdapter.PackageItem) adapterView.getItemAtPosition(i)).packageName, headsUp.mStoplistPackages);
                    create.cancel();
                }
            });
        } else if (i == 1) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    HeadsUp headsUp = HeadsUp.this;
                    headsUp.addCustomApplicationPref(((PackageListAdapter.PackageItem) adapterView.getItemAtPosition(i)).packageName, headsUp.mBlacklistPackages);
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
        if (parsePackageList()) {
            PreferenceGroup preferenceGroup = this.mStoplistPrefList;
            if (!(preferenceGroup == null || this.mBlacklistPrefList == null)) {
                preferenceGroup.removeAll();
                this.mBlacklistPrefList.removeAll();
                for (Package createPreferenceFromInfo : this.mStoplistPackages.values()) {
                    try {
                        this.mStoplistPrefList.addPreference(createPreferenceFromInfo(createPreferenceFromInfo));
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                for (Package createPreferenceFromInfo2 : this.mBlacklistPackages.values()) {
                    try {
                        this.mBlacklistPrefList.addPreference(createPreferenceFromInfo(createPreferenceFromInfo2));
                    } catch (PackageManager.NameNotFoundException unused2) {
                    }
                }
            }
            this.mAddStoplistPref.setOrder(0);
            this.mAddBlacklistPref.setOrder(0);
            this.mStoplistPrefList.addPreference(this.mAddStoplistPref);
            this.mBlacklistPrefList.addPreference(this.mAddBlacklistPref);
        }
    }

    public boolean onPreferenceClick(final Preference preference) {
        if (preference == this.mAddStoplistPref) {
            showDialog(0);
        } else if (preference == this.mAddBlacklistPref) {
            showDialog(1);
        } else {
            new AlertDialog.Builder(getActivity()).setTitle(C1715R.string.dialog_delete_title).setMessage(C1715R.string.dialog_delete_message).setIconAttribute(16843605).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (preference == HeadsUp.this.mBlacklistPrefList.findPreference(preference.getKey())) {
                        HeadsUp.this.removeApplicationPref(preference.getKey(), HeadsUp.this.mBlacklistPackages);
                    } else if (preference == HeadsUp.this.mStoplistPrefList.findPreference(preference.getKey())) {
                        HeadsUp.this.removeApplicationPref(preference.getKey(), HeadsUp.this.mStoplistPackages);
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
        boolean z;
        String string = Settings.System.getString(getContentResolver(), "heads_up_stoplist_values");
        String string2 = Settings.System.getString(getContentResolver(), "heads_up_blacklist_values");
        if (!TextUtils.equals(this.mStoplistPackageList, string)) {
            this.mStoplistPackageList = string;
            this.mStoplistPackages.clear();
            parseAndAddToMap(string, this.mStoplistPackages);
            z = true;
        } else {
            z = false;
        }
        if (TextUtils.equals(this.mBlacklistPackageList, string2)) {
            return z;
        }
        this.mBlacklistPackageList = string2;
        this.mBlacklistPackages.clear();
        parseAndAddToMap(string2, this.mBlacklistPackages);
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
        String str = map == this.mStoplistPackages ? "heads_up_stoplist_values" : "heads_up_blacklist_values";
        ArrayList arrayList = new ArrayList();
        for (Package packageR : map.values()) {
            arrayList.add(packageR.toString());
        }
        String join = TextUtils.join("|", arrayList);
        if (z) {
            if (TextUtils.equals(str, "heads_up_stoplist_values")) {
                this.mStoplistPackageList = join;
            } else {
                this.mBlacklistPackageList = join;
            }
        }
        Settings.System.putString(getContentResolver(), str, join);
    }
}
