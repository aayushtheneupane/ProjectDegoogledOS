package com.android.contacts.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.ListPreference;
import android.util.AttributeSet;
import com.android.contacts.R;

public final class SortOrderPreference extends ListPreference {
    private Context mContext;
    private ContactsPreferences mPreferences;

    /* access modifiers changed from: protected */
    public boolean shouldPersist() {
        return false;
    }

    public SortOrderPreference(Context context) {
        super(context);
        prepare();
    }

    public SortOrderPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        prepare();
    }

    private void prepare() {
        this.mContext = getContext();
        this.mPreferences = new ContactsPreferences(this.mContext);
        setEntries(new String[]{this.mContext.getString(R.string.display_options_sort_by_given_name), this.mContext.getString(R.string.display_options_sort_by_family_name)});
        setEntryValues(new String[]{String.valueOf(1), String.valueOf(2)});
        setValue(String.valueOf(this.mPreferences.getSortOrder()));
    }

    public CharSequence getSummary() {
        int sortOrder = this.mPreferences.getSortOrder();
        if (sortOrder == 1) {
            return this.mContext.getString(R.string.display_options_sort_by_given_name);
        }
        if (sortOrder != 2) {
            return null;
        }
        return this.mContext.getString(R.string.display_options_sort_by_family_name);
    }

    /* access modifiers changed from: protected */
    public boolean persistString(String str) {
        int parseInt = Integer.parseInt(str);
        if (parseInt == this.mPreferences.getSortOrder()) {
            return true;
        }
        this.mPreferences.setSortOrder(parseInt);
        notifyChanged();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        super.onPrepareDialogBuilder(builder);
        builder.setNegativeButton((CharSequence) null, (DialogInterface.OnClickListener) null);
    }
}
