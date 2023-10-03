package com.android.settings.inputmethod;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.textservice.SpellCheckerInfo;
import android.view.textservice.SpellCheckerSubtype;
import android.view.textservice.TextServicesManager;
import android.widget.Switch;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsActivity;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.widget.SwitchBar;
import com.havoc.config.center.C1715R;

public class SpellCheckersSettings extends SettingsPreferenceFragment implements SwitchBar.OnSwitchChangeListener, Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private SpellCheckerInfo mCurrentSci;
    private AlertDialog mDialog = null;
    private SpellCheckerInfo[] mEnabledScis;
    private Preference mSpellCheckerLanaguagePref;
    private SwitchBar mSwitchBar;
    private TextServicesManager mTsm;

    /* access modifiers changed from: private */
    public static int convertDialogItemIdToSubtypeIndex(int i) {
        return i - 1;
    }

    private static int convertSubtypeIndexToDialogItemId(int i) {
        return i + 1;
    }

    public int getMetricsCategory() {
        return 59;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.spellchecker_prefs);
        this.mSpellCheckerLanaguagePref = findPreference("spellchecker_language");
        this.mSpellCheckerLanaguagePref.setOnPreferenceClickListener(this);
        this.mTsm = (TextServicesManager) getSystemService("textservices");
        this.mCurrentSci = this.mTsm.getCurrentSpellChecker();
        this.mEnabledScis = this.mTsm.getEnabledSpellCheckers();
        populatePreferenceScreen();
    }

    private void populatePreferenceScreen() {
        SpellCheckerPreference spellCheckerPreference = new SpellCheckerPreference(getPrefContext(), this.mEnabledScis);
        spellCheckerPreference.setTitle((int) C1715R.string.default_spell_checker);
        SpellCheckerInfo[] spellCheckerInfoArr = this.mEnabledScis;
        if ((spellCheckerInfoArr == null ? 0 : spellCheckerInfoArr.length) > 0) {
            spellCheckerPreference.setSummary("%s");
        } else {
            spellCheckerPreference.setSummary((int) C1715R.string.spell_checker_not_selected);
        }
        spellCheckerPreference.setKey("default_spellchecker");
        spellCheckerPreference.setOnPreferenceChangeListener(this);
        getPreferenceScreen().addPreference(spellCheckerPreference);
    }

    public void onResume() {
        super.onResume();
        this.mSwitchBar = ((SettingsActivity) getActivity()).getSwitchBar();
        this.mSwitchBar.setSwitchBarText(C1715R.string.spell_checker_master_switch_title, C1715R.string.spell_checker_master_switch_title);
        this.mSwitchBar.show();
        this.mSwitchBar.addOnSwitchChangeListener(this);
        updatePreferenceScreen();
    }

    public void onPause() {
        super.onPause();
        this.mSwitchBar.removeOnSwitchChangeListener(this);
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        Settings.Secure.putInt(getContentResolver(), "spell_checker_enabled", z ? 1 : 0);
        updatePreferenceScreen();
    }

    /* access modifiers changed from: private */
    public void updatePreferenceScreen() {
        this.mCurrentSci = this.mTsm.getCurrentSpellChecker();
        boolean isSpellCheckerEnabled = this.mTsm.isSpellCheckerEnabled();
        this.mSwitchBar.setChecked(isSpellCheckerEnabled);
        boolean z = false;
        this.mSpellCheckerLanaguagePref.setSummary(getSpellCheckerSubtypeLabel(this.mCurrentSci, this.mCurrentSci != null ? this.mTsm.getCurrentSpellCheckerSubtype(false) : null));
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int preferenceCount = preferenceScreen.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceScreen.getPreference(i);
            preference.setEnabled(isSpellCheckerEnabled);
            if (preference instanceof SpellCheckerPreference) {
                ((SpellCheckerPreference) preference).setSelected(this.mCurrentSci);
            }
        }
        Preference preference2 = this.mSpellCheckerLanaguagePref;
        if (isSpellCheckerEnabled && this.mCurrentSci != null) {
            z = true;
        }
        preference2.setEnabled(z);
    }

    private CharSequence getSpellCheckerSubtypeLabel(SpellCheckerInfo spellCheckerInfo, SpellCheckerSubtype spellCheckerSubtype) {
        if (spellCheckerInfo == null) {
            return getString(C1715R.string.spell_checker_not_selected);
        }
        if (spellCheckerSubtype == null) {
            return getString(C1715R.string.use_system_language_to_select_input_method_subtypes);
        }
        return spellCheckerSubtype.getDisplayName(getActivity(), spellCheckerInfo.getPackageName(), spellCheckerInfo.getServiceInfo().applicationInfo);
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference != this.mSpellCheckerLanaguagePref) {
            return false;
        }
        showChooseLanguageDialog();
        return true;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        SpellCheckerInfo spellCheckerInfo = (SpellCheckerInfo) obj;
        if ((spellCheckerInfo.getServiceInfo().applicationInfo.flags & 1) != 0) {
            changeCurrentSpellChecker(spellCheckerInfo);
            return true;
        }
        showSecurityWarnDialog(spellCheckerInfo);
        return false;
    }

    private void showChooseLanguageDialog() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mDialog.dismiss();
        }
        final SpellCheckerInfo currentSpellChecker = this.mTsm.getCurrentSpellChecker();
        if (currentSpellChecker != null) {
            SpellCheckerSubtype currentSpellCheckerSubtype = this.mTsm.getCurrentSpellCheckerSubtype(false);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle((int) C1715R.string.phone_language);
            int subtypeCount = currentSpellChecker.getSubtypeCount();
            CharSequence[] charSequenceArr = new CharSequence[(subtypeCount + 1)];
            charSequenceArr[0] = getSpellCheckerSubtypeLabel(currentSpellChecker, (SpellCheckerSubtype) null);
            int i = 0;
            for (int i2 = 0; i2 < subtypeCount; i2++) {
                SpellCheckerSubtype subtypeAt = currentSpellChecker.getSubtypeAt(i2);
                int convertSubtypeIndexToDialogItemId = convertSubtypeIndexToDialogItemId(i2);
                charSequenceArr[convertSubtypeIndexToDialogItemId] = getSpellCheckerSubtypeLabel(currentSpellChecker, subtypeAt);
                if (subtypeAt.equals(currentSpellCheckerSubtype)) {
                    i = convertSubtypeIndexToDialogItemId;
                }
            }
            builder.setSingleChoiceItems(charSequenceArr, i, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    int i2;
                    if (i == 0) {
                        i2 = 0;
                    } else {
                        i2 = currentSpellChecker.getSubtypeAt(SpellCheckersSettings.convertDialogItemIdToSubtypeIndex(i)).hashCode();
                    }
                    Settings.Secure.putInt(SpellCheckersSettings.this.getContentResolver(), "selected_spell_checker_subtype", i2);
                    dialogInterface.dismiss();
                    SpellCheckersSettings.this.updatePreferenceScreen();
                }
            });
            this.mDialog = builder.create();
            this.mDialog.show();
        }
    }

    private void showSecurityWarnDialog(final SpellCheckerInfo spellCheckerInfo) {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mDialog.dismiss();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(17039380);
        builder.setMessage((CharSequence) getString(C1715R.string.spellchecker_security_warning, spellCheckerInfo.loadLabel(getPackageManager())));
        builder.setCancelable(true);
        builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SpellCheckersSettings.this.changeCurrentSpellChecker(spellCheckerInfo);
            }
        });
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        this.mDialog = builder.create();
        this.mDialog.show();
    }

    /* access modifiers changed from: private */
    public void changeCurrentSpellChecker(SpellCheckerInfo spellCheckerInfo) {
        Settings.Secure.putString(getContentResolver(), "selected_spell_checker", spellCheckerInfo.getId());
        Settings.Secure.putInt(getContentResolver(), "selected_spell_checker_subtype", 0);
        updatePreferenceScreen();
    }
}
