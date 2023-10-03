package com.android.dialer.contacts.displaypreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.UserManager;
import android.preference.PreferenceManager;
import com.android.dialer.R;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferences;

public final class ContactDisplayPreferencesImpl implements ContactDisplayPreferences {
    private final Context appContext;
    private final String displayOrderKey;
    private final SharedPreferences sharedPreferences;
    private final String sortOrderKey;

    ContactDisplayPreferencesImpl(Context context) {
        this.appContext = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.displayOrderKey = context.getString(R.string.display_options_view_names_as_key);
        this.sortOrderKey = context.getString(R.string.display_options_sort_list_by_key);
    }

    private void migrate() {
        String str;
        String str2;
        if (((UserManager) this.appContext.getSystemService(UserManager.class)).isUserUnlocked()) {
            Context context = this.appContext;
            SharedPreferences sharedPreferences2 = context.getSharedPreferences(context.getPackageName(), 0);
            if (sharedPreferences2.contains(this.displayOrderKey) || sharedPreferences2.contains(this.sortOrderKey)) {
                SharedPreferences.Editor edit = this.sharedPreferences.edit();
                String str3 = this.displayOrderKey;
                if (sharedPreferences2.getInt(str3, 1) != 2) {
                    str = ContactDisplayPreferences.DisplayOrder.PRIMARY.getValue(this.appContext);
                } else {
                    str = ContactDisplayPreferences.DisplayOrder.ALTERNATIVE.getValue(this.appContext);
                }
                SharedPreferences.Editor putString = edit.putString(str3, str);
                String str4 = this.sortOrderKey;
                if (sharedPreferences2.getInt(str4, 1) != 2) {
                    str2 = ContactDisplayPreferences.SortOrder.BY_PRIMARY.getValue(this.appContext);
                } else {
                    str2 = ContactDisplayPreferences.SortOrder.BY_ALTERNATIVE.getValue(this.appContext);
                }
                putString.putString(str4, str2).apply();
                sharedPreferences2.edit().remove(this.displayOrderKey).remove(this.sortOrderKey).apply();
            }
        }
    }

    public ContactDisplayPreferences.DisplayOrder getDisplayOrder() {
        migrate();
        if (!this.sharedPreferences.contains(this.displayOrderKey)) {
            return ContactDisplayPreferences.DisplayOrder.PRIMARY;
        }
        return (ContactDisplayPreferences.DisplayOrder) ContactDisplayPreferences.StringResEnum.fromValue(this.appContext, ContactDisplayPreferences.DisplayOrder.values(), this.sharedPreferences.getString(this.displayOrderKey, (String) null));
    }

    public ContactDisplayPreferences.SortOrder getSortOrder() {
        migrate();
        if (!this.sharedPreferences.contains(this.sortOrderKey)) {
            return ContactDisplayPreferences.SortOrder.BY_PRIMARY;
        }
        return (ContactDisplayPreferences.SortOrder) ContactDisplayPreferences.StringResEnum.fromValue(this.appContext, ContactDisplayPreferences.SortOrder.values(), this.sharedPreferences.getString(this.sortOrderKey, (String) null));
    }
}
