package com.android.contacts.common.list;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.android.contacts.common.list.ContactListFilterController;
import com.android.contacts.common.model.AccountTypeManager;
import com.android.contacts.common.model.account.AccountWithDataSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: ContactListFilterController */
class ContactListFilterControllerImpl extends ContactListFilterController {
    private final Context mAppContext;
    private ContactListFilter mFilter;
    private final List<ContactListFilterController.ContactListFilterListener> mListeners = new ArrayList();

    public ContactListFilterControllerImpl(Context context) {
        this.mAppContext = context.getApplicationContext();
        this.mFilter = ContactListFilter.restoreDefaultPreferences(getSharedPreferences());
        checkFilterValidity(true);
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this.mAppContext);
    }

    private void setContactListFilter(ContactListFilter contactListFilter, boolean z, boolean z2) {
        int i;
        String str;
        String str2;
        if (!contactListFilter.equals(this.mFilter)) {
            this.mFilter = contactListFilter;
            if (z) {
                SharedPreferences sharedPreferences = getSharedPreferences();
                ContactListFilter contactListFilter2 = this.mFilter;
                if (contactListFilter2 == null || contactListFilter2.filterType != -6) {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    if (contactListFilter2 == null) {
                        i = -1;
                    } else {
                        i = contactListFilter2.filterType;
                    }
                    SharedPreferences.Editor putInt = edit.putInt("filter.type", i);
                    String str3 = null;
                    if (contactListFilter2 == null) {
                        str = null;
                    } else {
                        str = contactListFilter2.accountName;
                    }
                    SharedPreferences.Editor putString = putInt.putString("filter.accountName", str);
                    if (contactListFilter2 == null) {
                        str2 = null;
                    } else {
                        str2 = contactListFilter2.accountType;
                    }
                    SharedPreferences.Editor putString2 = putString.putString("filter.accountType", str2);
                    if (contactListFilter2 != null) {
                        str3 = contactListFilter2.dataSet;
                    }
                    putString2.putString("filter.dataSet", str3).apply();
                }
            }
            if (z2 && !this.mListeners.isEmpty()) {
                for (ContactListFilterController.ContactListFilterListener onContactListFilterChanged : this.mListeners) {
                    onContactListFilterChanged.onContactListFilterChanged();
                }
            }
        }
    }

    public void checkFilterValidity(boolean z) {
        ContactListFilter contactListFilter = this.mFilter;
        if (contactListFilter != null) {
            int i = contactListFilter.filterType;
            boolean z2 = false;
            if (i == -6) {
                setContactListFilter(ContactListFilter.restoreDefaultPreferences(getSharedPreferences()), false, z);
            } else if (i == 0) {
                AccountTypeManager instance = AccountTypeManager.getInstance(this.mAppContext);
                ContactListFilter contactListFilter2 = this.mFilter;
                AccountWithDataSet accountWithDataSet = new AccountWithDataSet(contactListFilter2.accountName, contactListFilter2.accountType, contactListFilter2.dataSet);
                Iterator<AccountWithDataSet> it = instance.getAccounts(false).iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (accountWithDataSet.equals(it.next())) {
                            z2 = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (!z2) {
                    setContactListFilter(ContactListFilter.createFilterWithType(-2), true, z);
                }
            }
        }
    }
}
