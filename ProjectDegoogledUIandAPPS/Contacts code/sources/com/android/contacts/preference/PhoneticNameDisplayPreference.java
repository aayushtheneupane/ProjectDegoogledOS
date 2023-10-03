package com.android.contacts.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.ListPreference;
import android.util.AttributeSet;
import com.android.contacts.R;

public final class PhoneticNameDisplayPreference extends ListPreference {
    private Context mContext;
    private ContactsPreferences mPreferences;

    /* access modifiers changed from: protected */
    public boolean shouldPersist() {
        return false;
    }

    public PhoneticNameDisplayPreference(Context context) {
        super(context);
        prepare();
    }

    public PhoneticNameDisplayPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        prepare();
    }

    private void prepare() {
        this.mContext = getContext();
        this.mPreferences = new ContactsPreferences(this.mContext);
        setEntries(new String[]{this.mContext.getString(R.string.editor_options_always_show_phonetic_names), this.mContext.getString(R.string.editor_options_hide_phonetic_names_if_empty)});
        setEntryValues(new String[]{String.valueOf(0), String.valueOf(1)});
        setValue(String.valueOf(this.mPreferences.getPhoneticNameDisplayPreference()));
    }

    public CharSequence getSummary() {
        int phoneticNameDisplayPreference = this.mPreferences.getPhoneticNameDisplayPreference();
        if (phoneticNameDisplayPreference == 0) {
            return this.mContext.getString(R.string.editor_options_always_show_phonetic_names);
        }
        if (phoneticNameDisplayPreference != 1) {
            return null;
        }
        return this.mContext.getString(R.string.editor_options_hide_phonetic_names_if_empty);
    }

    /* access modifiers changed from: protected */
    public boolean persistString(String str) {
        int parseInt = Integer.parseInt(str);
        if (parseInt == this.mPreferences.getPhoneticNameDisplayPreference()) {
            return true;
        }
        this.mPreferences.setPhoneticNameDisplayPreference(parseInt);
        notifyChanged();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        super.onPrepareDialogBuilder(builder);
        builder.setNegativeButton((CharSequence) null, (DialogInterface.OnClickListener) null);
    }
}
