package com.android.contacts.util;

import android.accounts.Account;
import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.contacts.R;
import com.android.contacts.list.AccountFilterActivity;
import com.android.contacts.list.ContactListFilter;
import com.android.contacts.list.ContactListFilterController;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountInfo;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.preference.ContactsPreferences;
import com.android.contacts.util.DeviceLocalAccountTypeFactory;
import com.android.contacts.util.concurrent.ContactsExecutors;
import com.android.contacts.util.concurrent.ListenableFutureLoader;
import com.android.contactsbind.ObjectFactory;
import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class AccountFilterUtil {
    private static final String TAG = "AccountFilterUtil";

    public static void startAccountFilterActivityForResult(Fragment fragment, int i, ContactListFilter contactListFilter) {
        Activity activity = fragment.getActivity();
        if (activity != null) {
            fragment.startActivityForResult(new Intent(activity, AccountFilterActivity.class), i);
        } else {
            Log.w(TAG, "getActivity() returned null. Ignored");
        }
    }

    public static void handleAccountFilterResult(ContactListFilterController contactListFilterController, int i, Intent intent) {
        ContactListFilter contactListFilter;
        if (i == -1 && (contactListFilter = (ContactListFilter) intent.getParcelableExtra("contactListFilter")) != null) {
            int i2 = contactListFilter.filterType;
            if (i2 == -3) {
                contactListFilterController.selectCustomFilter();
            } else {
                contactListFilterController.setContactListFilter(contactListFilter, i2 == -2);
            }
        }
    }

    public static class FilterLoader extends ListenableFutureLoader<List<ContactListFilter>> {
        private AccountTypeManager mAccountTypeManager;
        private DeviceLocalAccountTypeFactory mDeviceLocalFactory;

        public FilterLoader(Context context) {
            super(context, new IntentFilter(AccountTypeManager.BROADCAST_ACCOUNTS_CHANGED));
            this.mAccountTypeManager = AccountTypeManager.getInstance(context);
            this.mDeviceLocalFactory = ObjectFactory.getDeviceLocalAccountTypeFactory(context);
        }

        /* access modifiers changed from: protected */
        public ListenableFuture<List<ContactListFilter>> loadData() {
            return Futures.transform(this.mAccountTypeManager.filterAccountsAsync(AccountTypeManager.writableFilter()), new Function<List<AccountInfo>, List<ContactListFilter>>() {
                public List<ContactListFilter> apply(List<AccountInfo> list) {
                    return FilterLoader.this.getFiltersForAccounts(list);
                }
            }, (Executor) ContactsExecutors.getDefaultThreadPoolExecutor());
        }

        /* access modifiers changed from: private */
        public List<ContactListFilter> getFiltersForAccounts(List<AccountInfo> list) {
            ArrayList arrayList = new ArrayList();
            AccountInfo.sortAccounts(AccountFilterUtil.getDefaultAccount(getContext()), list);
            for (AccountInfo next : list) {
                AccountType type = next.getType();
                AccountWithDataSet account = next.getAccount();
                if ((!type.isExtension() && !DeviceLocalAccountTypeFactory.Util.isLocalAccountType(this.mDeviceLocalFactory, account.type)) || account.hasData(getContext())) {
                    Drawable displayIcon = type != null ? type.getDisplayIcon(getContext()) : null;
                    if (DeviceLocalAccountTypeFactory.Util.isLocalAccountType(this.mDeviceLocalFactory, account.type)) {
                        arrayList.add(ContactListFilter.createDeviceContactsFilter(displayIcon, account));
                    } else {
                        arrayList.add(ContactListFilter.createAccountFilter(account.type, account.name, account.dataSet, displayIcon));
                    }
                }
            }
            return arrayList;
        }
    }

    /* access modifiers changed from: private */
    public static AccountWithDataSet getDefaultAccount(Context context) {
        return new ContactsPreferences(context).getDefaultAccount();
    }

    public static ContactListFilter createContactsFilter(Context context) {
        return ContactListFilter.createFilterWithType(ContactListFilterController.getInstance(context).isCustomFilterPersisted() ? -3 : -2);
    }

    public static void startEditorIntent(Context context, Intent intent, ContactListFilter contactListFilter) {
        String str;
        String str2;
        Intent intent2 = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
        intent2.putExtras(intent);
        if (!isAllContactsFilter(contactListFilter) && (str = contactListFilter.accountName) != null && (str2 = contactListFilter.accountType) != null) {
            intent2.putExtra("android.provider.extra.ACCOUNT", new Account(str, str2));
            intent2.putExtra("android.provider.extra.DATA_SET", contactListFilter.dataSet);
        } else if (isDeviceContactsFilter(contactListFilter)) {
            intent2.putExtra("com.android.contacts.ACCOUNT_WITH_DATA_SET", contactListFilter.toAccountWithDataSet());
        }
        try {
            ImplicitIntentsUtil.startActivityInApp(context, intent2);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context, R.string.missing_app, 0).show();
        }
    }

    public static boolean isAllContactsFilter(ContactListFilter contactListFilter) {
        return contactListFilter != null && contactListFilter.isContactsFilterType();
    }

    public static boolean isDeviceContactsFilter(ContactListFilter contactListFilter) {
        return contactListFilter.filterType == -8;
    }

    public static String getActionBarTitleForFilter(Context context, ContactListFilter contactListFilter) {
        int i = contactListFilter.filterType;
        if (i == -8) {
            return context.getString(R.string.account_phone);
        }
        if (i != 0 || TextUtils.isEmpty(contactListFilter.accountName)) {
            return context.getString(R.string.contactsList);
        }
        return getActionBarTitleForAccount(context, contactListFilter);
    }

    private static String getActionBarTitleForAccount(Context context, ContactListFilter contactListFilter) {
        AccountInfo accountInfoForAccount = AccountTypeManager.getInstance(context).getAccountInfoForAccount(contactListFilter.toAccountWithDataSet());
        if (accountInfoForAccount == null) {
            return context.getString(R.string.contactsList);
        }
        if (accountInfoForAccount.hasGoogleAccountType()) {
            return context.getString(R.string.title_from_google);
        }
        return context.getString(R.string.title_from_other_accounts, new Object[]{accountInfoForAccount.getNameLabel().toString()});
    }
}
