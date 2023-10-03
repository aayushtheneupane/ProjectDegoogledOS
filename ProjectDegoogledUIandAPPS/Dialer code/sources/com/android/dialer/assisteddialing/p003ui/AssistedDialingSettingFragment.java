package com.android.dialer.assisteddialing.p003ui;

import android.icu.util.ULocale;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.telephony.TelephonyManager;
import com.android.dialer.R;
import com.android.dialer.assisteddialing.AssistedDialingMediator;
import com.android.dialer.assisteddialing.ConcreteCreator;
import com.android.dialer.assisteddialing.CountryCodeProvider;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.Optional;

/* renamed from: com.android.dialer.assisteddialing.ui.AssistedDialingSettingFragment */
public class AssistedDialingSettingFragment extends PreferenceFragment {
    private AssistedDialingMediator assistedDialingMediator;
    private CountryCodeProvider countryCodeProvider;

    @AutoValue
    /* renamed from: com.android.dialer.assisteddialing.ui.AssistedDialingSettingFragment$DisplayNameAndCountryCodeTuple */
    static abstract class DisplayNameAndCountryCodeTuple {
        DisplayNameAndCountryCodeTuple() {
        }

        /* access modifiers changed from: package-private */
        public abstract CharSequence countryCode();

        /* access modifiers changed from: package-private */
        public abstract CharSequence countryDisplayname();
    }

    /* access modifiers changed from: package-private */
    public boolean logIfUserDisabledFeature(Preference preference, Object obj) {
        if (((Boolean) obj).booleanValue()) {
            return true;
        }
        ((LoggingBindingsStub) Logger.get(getActivity().getApplicationContext())).logImpression(DialerImpression$Type.ASSISTED_DIALING_FEATURE_DISABLED_BY_USER);
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.assistedDialingMediator = ConcreteCreator.createNewAssistedDialingMediator((TelephonyManager) getContext().getSystemService(TelephonyManager.class), getContext());
        this.countryCodeProvider = ConcreteCreator.getCountryCodeProvider(ConfigProviderComponent.get(getContext()).getConfigProvider());
        addPreferencesFromResource(R.xml.assisted_dialing_setting);
        SwitchPreference switchPreference = (SwitchPreference) findPreference(getContext().getString(R.string.assisted_dialing_setting_toggle_key));
        ListPreference listPreference = (ListPreference) findPreference(getContext().getString(R.string.assisted_dialing_setting_cc_key));
        CharSequence[] entries = listPreference.getEntries();
        CharSequence[] entryValues = listPreference.getEntryValues();
        if (entries.length == entryValues.length) {
            ArrayList<DisplayNameAndCountryCodeTuple> arrayList = new ArrayList<>();
            ULocale build = new ULocale.Builder().setRegion(getResources().getConfiguration().getLocales().get(0).getCountry()).setLanguage(getResources().getConfiguration().getLocales().get(0).getLanguage()).build();
            for (int i = 0; i < entries.length; i++) {
                StringBuilder outline14 = GeneratedOutlineSupport.outline14(new ULocale.Builder().setRegion(entryValues[i].toString()).build().getDisplayCountry(build), " ");
                outline14.append(entries[i]);
                arrayList.add(new C0365x7ed7331b(outline14.toString(), entryValues[i]));
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            arrayList2.add(listPreference.getEntries()[0]);
            arrayList3.add(listPreference.getEntryValues()[0]);
            for (DisplayNameAndCountryCodeTuple displayNameAndCountryCodeTuple : arrayList) {
                if (this.countryCodeProvider.isSupportedCountryCode(displayNameAndCountryCodeTuple.countryCode().toString())) {
                    arrayList2.add(displayNameAndCountryCodeTuple.countryDisplayname());
                    arrayList3.add(displayNameAndCountryCodeTuple.countryCode());
                }
            }
            listPreference.setEntries((CharSequence[]) arrayList2.toArray(new CharSequence[arrayList2.size()]));
            listPreference.setEntryValues((CharSequence[]) arrayList3.toArray(new CharSequence[arrayList3.size()]));
            if (!arrayList3.contains(listPreference.getValue())) {
                listPreference.setValue(listPreference.getEntryValues()[0].toString());
                LogUtil.m9i("AssistedDialingSettingFragment.ameliorateInvalidSelectedValue", "Reset the country chooser preference to the default value.", new Object[0]);
            }
            if (listPreference.getEntry().equals(listPreference.getEntries()[0].toString())) {
                Optional<String> userHomeCountryCode = this.assistedDialingMediator.userHomeCountryCode();
                if (userHomeCountryCode.isPresent()) {
                    try {
                        listPreference.setSummary(getContext().getString(R.string.assisted_dialing_setting_cc_default_summary, new Object[]{listPreference.getEntries()[listPreference.findIndexOfValue(userHomeCountryCode.get())]}));
                    } catch (ArrayIndexOutOfBoundsException unused) {
                        LogUtil.m9i("AssistedDialingSettingFragment.onCreate", "Failed to find human readable mapping for country code, using default.", new Object[0]);
                    }
                }
            } else {
                listPreference.setSummary(listPreference.getEntry());
            }
            listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    AssistedDialingSettingFragment.this.updateListSummary(preference, obj);
                    return true;
                }
            });
            switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    AssistedDialingSettingFragment.this.logIfUserDisabledFeature(preference, obj);
                    return true;
                }
            });
            return;
        }
        throw new IllegalStateException("Unexpected mismatch in country chooser key/value size");
    }

    /* access modifiers changed from: package-private */
    public boolean updateListSummary(Preference preference, Object obj) {
        ListPreference listPreference = (ListPreference) preference;
        listPreference.setSummary(listPreference.getEntries()[listPreference.findIndexOfValue(obj.toString())]);
        return true;
    }
}
