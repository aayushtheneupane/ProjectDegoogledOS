package com.havoc.config.center.fragments;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.internal.util.havoc.Utils;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SystemSettingSwitchPreference;
import java.util.Date;

public class Clock extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
    private PreferenceCategory mClockCategory;
    private ListPreference mClockDateDisplay;
    private ListPreference mClockDateFormat;
    private ListPreference mClockDatePosition;
    private ListPreference mClockDateStyle;
    private PreferenceCategory mDateCategory;
    private ListPreference mStatusBarAmPm;
    private ListPreference mStatusBarClock;
    private SystemSettingSwitchPreference mStatusBarSecondsShow;
    private View mSwitchBar;
    private TextView mTextView;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.clock_options);
        getPreferenceScreen();
        ContentResolver contentResolver = getActivity().getContentResolver();
        this.mClockCategory = (PreferenceCategory) findPreference("clock_category");
        this.mDateCategory = (PreferenceCategory) findPreference("date_category");
        this.mStatusBarSecondsShow = (SystemSettingSwitchPreference) findPreference("status_bar_clock_seconds");
        this.mStatusBarClock = (ListPreference) findPreference("statusbar_clock_style");
        this.mStatusBarAmPm = (ListPreference) findPreference("status_bar_am_pm");
        this.mClockDateDisplay = (ListPreference) findPreference("clock_date_display");
        this.mClockDateStyle = (ListPreference) findPreference("clock_date_style");
        this.mClockDatePosition = (ListPreference) findPreference("statusbar_clock_date_position");
        this.mStatusBarSecondsShow.setChecked(Settings.System.getInt(contentResolver, "status_bar_clock_seconds", 0) == 1);
        this.mStatusBarSecondsShow.setOnPreferenceChangeListener(this);
        int i = Settings.System.getInt(contentResolver, "statusbar_clock_style", 0);
        CharSequence[] charSequenceArr = {getResources().getString(C1715R.string.status_bar_clock_style_left), getResources().getString(C1715R.string.status_bar_clock_style_center), getResources().getString(C1715R.string.status_bar_clock_style_right)};
        CharSequence[] charSequenceArr2 = {getResources().getString(C1715R.string.status_bar_clock_style_left), getResources().getString(C1715R.string.status_bar_clock_style_right)};
        CharSequence[] charSequenceArr3 = {"0", "1", "2"};
        CharSequence[] charSequenceArr4 = {"0", "2"};
        ListPreference listPreference = this.mStatusBarClock;
        if (Utils.hasNotch(getActivity())) {
            charSequenceArr = charSequenceArr2;
        }
        listPreference.setEntries(charSequenceArr);
        ListPreference listPreference2 = this.mStatusBarClock;
        if (Utils.hasNotch(getActivity())) {
            charSequenceArr3 = charSequenceArr4;
        }
        listPreference2.setEntryValues(charSequenceArr3);
        this.mStatusBarClock.setValue(String.valueOf(i));
        ListPreference listPreference3 = this.mStatusBarClock;
        listPreference3.setSummary(listPreference3.getEntry());
        this.mStatusBarClock.setOnPreferenceChangeListener(this);
        if (DateFormat.is24HourFormat(getActivity())) {
            this.mStatusBarAmPm.setEnabled(false);
            this.mStatusBarAmPm.setSummary((int) C1715R.string.status_bar_am_pm_info);
        } else {
            this.mStatusBarAmPm.setValue(String.valueOf(Settings.System.getInt(contentResolver, "statusbar_clock_am_pm_style", 2)));
            ListPreference listPreference4 = this.mStatusBarAmPm;
            listPreference4.setSummary(listPreference4.getEntry());
            this.mStatusBarAmPm.setOnPreferenceChangeListener(this);
        }
        this.mClockDateDisplay.setValue(String.valueOf(Settings.System.getInt(contentResolver, "statusbar_clock_date_display", 0)));
        ListPreference listPreference5 = this.mClockDateDisplay;
        listPreference5.setSummary(listPreference5.getEntry());
        this.mClockDateDisplay.setOnPreferenceChangeListener(this);
        this.mClockDateStyle.setValue(String.valueOf(Settings.System.getInt(contentResolver, "statusbar_clock_date_style", 0)));
        ListPreference listPreference6 = this.mClockDateStyle;
        listPreference6.setSummary(listPreference6.getEntry());
        this.mClockDateStyle.setOnPreferenceChangeListener(this);
        this.mClockDateFormat = (ListPreference) findPreference("clock_date_format");
        this.mClockDateFormat.setOnPreferenceChangeListener(this);
        String string = Settings.System.getString(getActivity().getContentResolver(), "statusbar_clock_date_format");
        if (string == null || string.isEmpty()) {
            string = "EEE";
        }
        if (this.mClockDateFormat.findIndexOfValue(string) == -1) {
            this.mClockDateFormat.setValueIndex(18);
        } else {
            this.mClockDateFormat.setValue(string);
        }
        parseClockDateFormats();
        this.mClockDatePosition.setValue(String.valueOf(Settings.System.getInt(contentResolver, "statusbar_clock_date_position", 0)));
        ListPreference listPreference7 = this.mClockDatePosition;
        listPreference7.setSummary(listPreference7.getEntry());
        this.mClockDatePosition.setOnPreferenceChangeListener(this);
        setDateOptions();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = true;
        if (Settings.System.getInt(getContentResolver(), "status_bar_clock", 1) != 1) {
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
                Clock.this.lambda$onViewCreated$0$Clock(this.f$1, view);
            }
        });
        this.mClockCategory.setEnabled(z);
        this.mDateCategory.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$Clock(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.System.putInt(getContentResolver(), "status_bar_clock", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mClockCategory.setEnabled(z);
        this.mDateCategory.setEnabled(z);
    }

    public void onResume() {
        super.onResume();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        getActivity().getContentResolver();
        if (preference == this.mStatusBarSecondsShow) {
            Settings.System.putInt(getActivity().getContentResolver(), "status_bar_clock_seconds", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference == this.mStatusBarClock) {
            String str = (String) obj;
            int parseInt = Integer.parseInt(str);
            int findIndexOfValue = this.mStatusBarClock.findIndexOfValue(str);
            Settings.System.putInt(getActivity().getContentResolver(), "statusbar_clock_style", parseInt);
            ListPreference listPreference = this.mStatusBarClock;
            listPreference.setSummary(listPreference.getEntries()[findIndexOfValue]);
            return true;
        } else if (preference == this.mStatusBarAmPm) {
            String str2 = (String) obj;
            int intValue = Integer.valueOf(str2).intValue();
            int findIndexOfValue2 = this.mStatusBarAmPm.findIndexOfValue(str2);
            Settings.System.putInt(getActivity().getContentResolver(), "statusbar_clock_am_pm_style", intValue);
            ListPreference listPreference2 = this.mStatusBarAmPm;
            listPreference2.setSummary(listPreference2.getEntries()[findIndexOfValue2]);
            return true;
        } else if (preference == this.mClockDateDisplay) {
            String str3 = (String) obj;
            int intValue2 = Integer.valueOf(str3).intValue();
            int findIndexOfValue3 = this.mClockDateDisplay.findIndexOfValue(str3);
            Settings.System.putInt(getActivity().getContentResolver(), "statusbar_clock_date_display", intValue2);
            ListPreference listPreference3 = this.mClockDateDisplay;
            listPreference3.setSummary(listPreference3.getEntries()[findIndexOfValue3]);
            setDateOptions();
            return true;
        } else if (preference == this.mClockDateStyle) {
            String str4 = (String) obj;
            int intValue3 = Integer.valueOf(str4).intValue();
            int findIndexOfValue4 = this.mClockDateStyle.findIndexOfValue(str4);
            Settings.System.putInt(getActivity().getContentResolver(), "statusbar_clock_date_style", intValue3);
            ListPreference listPreference4 = this.mClockDateStyle;
            listPreference4.setSummary(listPreference4.getEntries()[findIndexOfValue4]);
            parseClockDateFormats();
            return true;
        } else {
            ListPreference listPreference5 = this.mClockDateFormat;
            if (preference == listPreference5) {
                String str5 = (String) obj;
                if (listPreference5.findIndexOfValue(str5) == 18) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(C1715R.string.clock_date_string_edittext_title);
                    builder.setMessage(C1715R.string.clock_date_string_edittext_summary);
                    final EditText editText = new EditText(getActivity());
                    String string = Settings.System.getString(getActivity().getContentResolver(), "statusbar_clock_date_format");
                    if (string != null) {
                        editText.setText(string);
                    }
                    builder.setView(editText);
                    builder.setPositiveButton(C1715R.string.menu_save, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String obj = editText.getText().toString();
                            if (!obj.equals("")) {
                                Settings.System.putString(Clock.this.getActivity().getContentResolver(), "statusbar_clock_date_format", obj);
                            }
                        }
                    });
                    builder.setNegativeButton(C1715R.string.menu_cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.create().show();
                } else if (str5 != null) {
                    Settings.System.putString(getActivity().getContentResolver(), "statusbar_clock_date_format", str5);
                }
                return true;
            } else if (preference != this.mClockDatePosition) {
                return false;
            } else {
                String str6 = (String) obj;
                int parseInt2 = Integer.parseInt(str6);
                int findIndexOfValue5 = this.mClockDatePosition.findIndexOfValue(str6);
                Settings.System.putInt(getActivity().getContentResolver(), "statusbar_clock_date_position", parseInt2);
                ListPreference listPreference6 = this.mClockDatePosition;
                listPreference6.setSummary(listPreference6.getEntries()[findIndexOfValue5]);
                parseClockDateFormats();
                return true;
            }
        }
    }

    private void parseClockDateFormats() {
        String str;
        String[] stringArray = getResources().getStringArray(C1715R.array.clock_date_format_entries_values);
        String[] strArr = new String[stringArray.length];
        Date date = new Date();
        int length = stringArray.length - 1;
        int i = Settings.System.getInt(getActivity().getContentResolver(), "statusbar_clock_date_style", 0);
        for (int i2 = 0; i2 < stringArray.length; i2++) {
            if (i2 == length) {
                strArr[i2] = stringArray[i2];
            } else {
                CharSequence format = DateFormat.format(stringArray[i2], date);
                if (i == 1) {
                    str = format.toString().toLowerCase();
                } else if (i == 2) {
                    str = format.toString().toUpperCase();
                } else {
                    str = format.toString();
                }
                strArr[i2] = str;
            }
        }
        this.mClockDateFormat.setEntries((CharSequence[]) strArr);
    }

    private void setDateOptions() {
        if (Settings.System.getInt(getActivity().getContentResolver(), "statusbar_clock_date_display", 0) == 0) {
            this.mClockDateStyle.setEnabled(false);
            this.mClockDateFormat.setEnabled(false);
            this.mClockDatePosition.setEnabled(false);
            return;
        }
        this.mClockDateStyle.setEnabled(true);
        this.mClockDateFormat.setEnabled(true);
        this.mClockDatePosition.setEnabled(true);
    }
}
