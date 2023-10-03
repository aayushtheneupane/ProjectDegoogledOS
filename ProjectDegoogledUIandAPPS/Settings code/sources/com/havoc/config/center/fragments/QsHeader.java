package com.havoc.config.center.fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.CustomSeekBarPreference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class QsHeader extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
    private ListPreference mDaylightHeaderPack;
    private String mDaylightHeaderProvider;
    private Preference mFileHeader;
    private String mFileHeaderProvider;
    private Preference mHeaderBrowse;
    private ListPreference mHeaderProvider;
    private CustomSeekBarPreference mHeaderShadow;
    private View mSwitchBar;
    private TextView mTextView;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onResume() {
        super.onResume();
        updateEnablement();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.qs_header);
        ContentResolver contentResolver = getActivity().getContentResolver();
        this.mDaylightHeaderProvider = getResources().getString(C1715R.string.daylight_header_provider);
        this.mFileHeaderProvider = getResources().getString(C1715R.string.file_header_provider);
        this.mHeaderBrowse = findPreference("custom_header_browse");
        this.mDaylightHeaderPack = (ListPreference) findPreference("daylight_header_pack");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        getAvailableHeaderPacks(arrayList, arrayList2);
        this.mDaylightHeaderPack.setEntries((CharSequence[]) arrayList.toArray(new String[arrayList.size()]));
        this.mDaylightHeaderPack.setEntryValues((CharSequence[]) arrayList2.toArray(new String[arrayList2.size()]));
        updateHeaderProviderSummary(Settings.System.getInt(contentResolver, "status_bar_custom_header", 0) != 0);
        this.mDaylightHeaderPack.setOnPreferenceChangeListener(this);
        this.mHeaderShadow = (CustomSeekBarPreference) findPreference("status_bar_custom_header_shadow");
        this.mHeaderShadow.setValue((int) ((((double) Settings.System.getInt(contentResolver, "status_bar_custom_header_shadow", 0)) / 255.0d) * 100.0d));
        this.mHeaderShadow.setOnPreferenceChangeListener(this);
        this.mHeaderProvider = (ListPreference) findPreference("custom_header_provider");
        this.mHeaderProvider.setOnPreferenceChangeListener(this);
        this.mFileHeader = findPreference("file_header_select");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = false;
        if (Settings.System.getInt(getContentResolver(), "status_bar_custom_header", 0) == 1) {
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
                QsHeader.this.lambda$onViewCreated$0$QsHeader(this.f$1, view);
            }
        });
        this.mHeaderProvider.setEnabled(z);
        this.mFileHeader.setEnabled(z);
        this.mHeaderShadow.setEnabled(z);
        this.mDaylightHeaderPack.setEnabled(z);
        updateEnablement();
    }

    public /* synthetic */ void lambda$onViewCreated$0$QsHeader(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.System.putInt(getContentResolver(), "status_bar_custom_header", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mHeaderProvider.setEnabled(z);
        this.mFileHeader.setEnabled(z);
        this.mHeaderShadow.setEnabled(z);
        this.mDaylightHeaderPack.setEnabled(z);
        updateEnablement();
    }

    private void updateHeaderProviderSummary(boolean z) {
        String string;
        this.mDaylightHeaderPack.setSummary(getResources().getString(C1715R.string.header_provider_disabled));
        if (z && (string = Settings.System.getString(getContentResolver(), "status_bar_daylight_header_pack")) != null) {
            int findIndexOfValue = this.mDaylightHeaderPack.findIndexOfValue(string);
            ListPreference listPreference = this.mDaylightHeaderPack;
            if (findIndexOfValue < 0) {
                findIndexOfValue = 0;
            }
            listPreference.setValueIndex(findIndexOfValue);
            ListPreference listPreference2 = this.mDaylightHeaderPack;
            listPreference2.setSummary(listPreference2.getEntry());
        }
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference != this.mFileHeader) {
            return super.onPreferenceTreeClick(preference);
        }
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        startActivityForResult(intent, 0);
        return true;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mDaylightHeaderPack) {
            String str = (String) obj;
            Settings.System.putString(getContentResolver(), "status_bar_daylight_header_pack", str);
            int findIndexOfValue = this.mDaylightHeaderPack.findIndexOfValue(str);
            ListPreference listPreference = this.mDaylightHeaderPack;
            listPreference.setSummary(listPreference.getEntries()[findIndexOfValue]);
            return true;
        } else if (preference == this.mHeaderShadow) {
            Settings.System.putInt(getContentResolver(), "status_bar_custom_header_shadow", (int) ((((double) ((Integer) obj).intValue()) / 100.0d) * 255.0d));
            return true;
        } else if (preference != this.mHeaderProvider) {
            return true;
        } else {
            String str2 = (String) obj;
            Settings.System.putString(getContentResolver(), "status_bar_custom_header_provider", str2);
            int findIndexOfValue2 = this.mHeaderProvider.findIndexOfValue(str2);
            ListPreference listPreference2 = this.mHeaderProvider;
            listPreference2.setSummary(listPreference2.getEntries()[findIndexOfValue2]);
            updateEnablement();
            return true;
        }
    }

    private boolean isBrowseHeaderAvailable() {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent();
        intent.setClassName("org.omnirom.omnistyle", "org.omnirom.omnistyle.PickHeaderActivity");
        if (packageManager.resolveActivity(intent, 0) != null) {
            return true;
        }
        return false;
    }

    private void getAvailableHeaderPacks(List<String> list, List<String> list2) {
        HashMap hashMap = new HashMap();
        Intent intent = new Intent();
        PackageManager packageManager = getPackageManager();
        intent.setAction("org.omnirom.DaylightHeaderPack");
        for (ResolveInfo next : packageManager.queryIntentActivities(intent, 0)) {
            String str = next.activityInfo.packageName;
            String charSequence = next.activityInfo.loadLabel(getPackageManager()).toString();
            if (charSequence == null) {
                charSequence = next.activityInfo.packageName;
            }
            hashMap.put(charSequence, str);
        }
        intent.setAction("org.omnirom.DaylightHeaderPack1");
        for (ResolveInfo next2 : packageManager.queryIntentActivities(intent, 0)) {
            String str2 = next2.activityInfo.packageName;
            String charSequence2 = next2.activityInfo.loadLabel(getPackageManager()).toString();
            if (!next2.activityInfo.name.endsWith(".theme")) {
                if (charSequence2 == null) {
                    charSequence2 = str2;
                }
                hashMap.put(charSequence2, str2 + "/" + next2.activityInfo.name);
            }
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(hashMap.keySet());
        Collections.sort(arrayList);
        for (String str3 : arrayList) {
            list.add(str3);
            list2.add((String) hashMap.get(str3));
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 0 && i2 == -1) {
            Uri data = intent.getData();
            Settings.System.putString(getContentResolver(), "status_bar_custom_header_provider", "file");
            Settings.System.putString(getContentResolver(), "status_bar_file_header_image", data.toString());
        }
    }

    private void updateEnablement() {
        boolean z = false;
        boolean z2 = Settings.System.getInt(getContentResolver(), "status_bar_custom_header", 0) == 1;
        String string = Settings.System.getString(getContentResolver(), "status_bar_custom_header_provider");
        if (string == null) {
            string = this.mDaylightHeaderProvider;
        }
        if (!string.equals(this.mDaylightHeaderProvider)) {
            string = this.mFileHeaderProvider;
        }
        int findIndexOfValue = this.mHeaderProvider.findIndexOfValue(string);
        ListPreference listPreference = this.mHeaderProvider;
        if (findIndexOfValue < 0) {
            findIndexOfValue = 0;
        }
        listPreference.setValueIndex(findIndexOfValue);
        ListPreference listPreference2 = this.mHeaderProvider;
        listPreference2.setSummary(listPreference2.getEntry());
        this.mDaylightHeaderPack.setEnabled(string.equals(this.mDaylightHeaderProvider) && z2);
        this.mFileHeader.setEnabled(string.equals(this.mFileHeaderProvider) && z2);
        Preference preference = this.mHeaderBrowse;
        if (isBrowseHeaderAvailable() && string.equals(this.mFileHeaderProvider) && z2) {
            z = true;
        }
        preference.setEnabled(z);
    }
}
