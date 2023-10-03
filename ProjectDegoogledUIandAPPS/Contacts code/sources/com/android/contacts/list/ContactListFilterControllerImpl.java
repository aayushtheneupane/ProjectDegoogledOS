package com.android.contacts.list;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.android.contacts.list.ContactListFilterController;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountWithDataSet;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ContactListFilterController */
class ContactListFilterControllerImpl extends ContactListFilterController {
    private final Context mContext;
    private ContactListFilter mFilter;
    private final List<ContactListFilterController.ContactListFilterListener> mListeners = new ArrayList();

    public ContactListFilterControllerImpl(Context context) {
        this.mContext = context.getApplicationContext();
        this.mFilter = ContactListFilter.restoreDefaultPreferences(getSharedPreferences());
        checkFilterValidity(true);
    }

    public void addListener(ContactListFilterController.ContactListFilterListener contactListFilterListener) {
        this.mListeners.add(contactListFilterListener);
    }

    public void removeListener(ContactListFilterController.ContactListFilterListener contactListFilterListener) {
        this.mListeners.remove(contactListFilterListener);
    }

    public ContactListFilter getFilter() {
        return this.mFilter;
    }

    public int getFilterListType() {
        ContactListFilter contactListFilter = this.mFilter;
        if (contactListFilter == null) {
            return 0;
        }
        return contactListFilter.toListType();
    }

    public boolean isCustomFilterPersisted() {
        ContactListFilter persistedFilter = getPersistedFilter();
        return persistedFilter != null && persistedFilter.filterType == -3;
    }

    public ContactListFilter getPersistedFilter() {
        return ContactListFilter.restoreDefaultPreferences(getSharedPreferences());
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this.mContext);
    }

    public void setContactListFilter(ContactListFilter contactListFilter, boolean z) {
        setContactListFilter(contactListFilter, z, true);
    }

    private void setContactListFilter(ContactListFilter contactListFilter, boolean z, boolean z2) {
        if (!contactListFilter.equals(this.mFilter)) {
            this.mFilter = contactListFilter;
            if (z) {
                ContactListFilter.storeToPreferences(getSharedPreferences(), this.mFilter);
            }
            if (z2 && !this.mListeners.isEmpty()) {
                notifyContactListFilterChanged();
            }
        }
    }

    public void selectCustomFilter() {
        setContactListFilter(ContactListFilter.createFilterWithType(-3), true);
    }

    private void notifyContactListFilterChanged() {
        for (ContactListFilterController.ContactListFilterListener onContactListFilterChanged : this.mListeners) {
            onContactListFilterChanged.onContactListFilterChanged();
        }
    }

    public void checkFilterValidity(boolean z) {
        ContactListFilter contactListFilter = this.mFilter;
        if (contactListFilter != null) {
            int i = contactListFilter.filterType;
            if (i == -6) {
                setContactListFilter(ContactListFilter.restoreDefaultPreferences(getSharedPreferences()), false, z);
            } else if (i == 0 && !filterAccountExists()) {
                setContactListFilter(ContactListFilter.createFilterWithType(-2), true, z);
            }
        }
    }

    private boolean filterAccountExists() {
        AccountTypeManager instance = AccountTypeManager.getInstance(this.mContext);
        ContactListFilter contactListFilter = this.mFilter;
        return instance.exists(new AccountWithDataSet(contactListFilter.accountName, contactListFilter.accountType, contactListFilter.dataSet));
    }
}
