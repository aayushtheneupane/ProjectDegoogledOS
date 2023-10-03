package com.havoc.config.center.fragments;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.internal.util.havoc.Utils;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SystemSettingListPreference;

public class CustomCarrierLabel extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
    private PreferenceScreen mCustomCarrierLabel;
    private String mCustomCarrierLabelText;
    private SystemSettingListPreference mShowCarrierLabel;
    private View mSwitchBar;
    private TextView mTextView;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ContentResolver contentResolver = getActivity().getContentResolver();
        addPreferencesFromResource(C1715R.xml.custom_carrier_label);
        getPreferenceScreen();
        this.mCustomCarrierLabel = (PreferenceScreen) findPreference("custom_carrier_label");
        updateCustomLabelTextSummary();
        this.mShowCarrierLabel = (SystemSettingListPreference) findPreference("carrier_label_location");
        int i = Settings.System.getInt(contentResolver, "carrier_label_location", 0);
        CharSequence[] charSequenceArr = {getResources().getString(C1715R.string.show_carrier_keyguard), getResources().getString(C1715R.string.show_carrier_statusbar), getResources().getString(C1715R.string.show_carrier_enabled)};
        CharSequence[] charSequenceArr2 = {getResources().getString(C1715R.string.show_carrier_keyguard)};
        CharSequence[] charSequenceArr3 = {"0", "1", "2"};
        CharSequence[] charSequenceArr4 = {"0"};
        SystemSettingListPreference systemSettingListPreference = this.mShowCarrierLabel;
        if (Utils.hasNotch(getActivity())) {
            charSequenceArr = charSequenceArr2;
        }
        systemSettingListPreference.setEntries(charSequenceArr);
        SystemSettingListPreference systemSettingListPreference2 = this.mShowCarrierLabel;
        if (Utils.hasNotch(getActivity())) {
            charSequenceArr3 = charSequenceArr4;
        }
        systemSettingListPreference2.setEntryValues(charSequenceArr3);
        this.mShowCarrierLabel.setValue(String.valueOf(i));
        SystemSettingListPreference systemSettingListPreference3 = this.mShowCarrierLabel;
        systemSettingListPreference3.setSummary(systemSettingListPreference3.getEntry());
        this.mShowCarrierLabel.setOnPreferenceChangeListener(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = true;
        if (Settings.System.getInt(getContentResolver(), "carrier_label_enabled", 1) != 1) {
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
                CustomCarrierLabel.this.lambda$onViewCreated$0$CustomCarrierLabel(this.f$1, view);
            }
        });
        this.mCustomCarrierLabel.setEnabled(z);
        this.mShowCarrierLabel.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$CustomCarrierLabel(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.System.putInt(getContentResolver(), "carrier_label_enabled", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mCustomCarrierLabel.setEnabled(z);
        this.mShowCarrierLabel.setEnabled(z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        getActivity().getContentResolver();
        if (preference != this.mShowCarrierLabel) {
            return false;
        }
        updateCarrierLabelSummary(Integer.parseInt((String) obj));
        return true;
    }

    private void updateCarrierLabelSummary(int i) {
        Resources resources = getResources();
        if (i == 0) {
            this.mShowCarrierLabel.setSummary(resources.getString(C1715R.string.show_carrier_keyguard));
        } else if (i == 1) {
            this.mShowCarrierLabel.setSummary(resources.getString(C1715R.string.show_carrier_statusbar));
        } else if (i == 2) {
            this.mShowCarrierLabel.setSummary(resources.getString(C1715R.string.show_carrier_both));
        }
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        final ContentResolver contentResolver = getActivity().getContentResolver();
        if (preference != this.mCustomCarrierLabel) {
            return false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(C1715R.string.custom_carrier_label_title);
        builder.setMessage(C1715R.string.custom_carrier_label_explain);
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(55, 20, 55, 20);
        final EditText editText = new EditText(getActivity());
        editText.setLayoutParams(layoutParams);
        editText.setGravity(8388659);
        editText.setText(TextUtils.isEmpty(this.mCustomCarrierLabelText) ? "" : this.mCustomCarrierLabelText);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(25)});
        linearLayout.addView(editText);
        builder.setView(linearLayout);
        builder.setPositiveButton(getString(17039370), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Settings.System.putString(contentResolver, "custom_carrier_label", editText.getText().toString().trim());
                CustomCarrierLabel.this.updateCustomLabelTextSummary();
                Intent intent = new Intent();
                intent.setAction("android.intent.action.CUSTOM_CARRIER_LABEL");
                CustomCarrierLabel.this.getActivity().sendBroadcast(intent);
            }
        });
        builder.setNegativeButton(getString(17039360), (DialogInterface.OnClickListener) null);
        builder.show();
        return true;
    }

    /* access modifiers changed from: private */
    public void updateCustomLabelTextSummary() {
        this.mCustomCarrierLabelText = Settings.System.getString(getContentResolver(), "custom_carrier_label");
        if (TextUtils.isEmpty(this.mCustomCarrierLabelText)) {
            this.mCustomCarrierLabel.setSummary((int) C1715R.string.custom_carrier_label_notset);
        } else {
            this.mCustomCarrierLabel.setSummary((CharSequence) this.mCustomCarrierLabelText);
        }
    }
}
