package com.android.contacts.model;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.android.contacts.model.account.AccountInfo;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.AccountTypeWithDataSet;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.model.account.GoogleAccountType;
import com.android.contacts.model.dataitem.DataKind;
import com.android.contacts.util.PermissionsUtil;
import com.google.common.base.Predicate;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;

public abstract class AccountTypeManager {
    public static final String BROADCAST_ACCOUNTS_CHANGED = (AccountTypeManager.class.getName() + ".AccountsChanged");
    public static final String DEVICE_ACCOUNT_NAME = "DEVICE";
    private static final AccountTypeManager EMPTY = new AccountTypeManager() {
        public AccountInfo getAccountInfoForAccount(AccountWithDataSet accountWithDataSet) {
            return null;
        }

        public AccountType getAccountType(AccountTypeWithDataSet accountTypeWithDataSet) {
            return null;
        }

        public Account getDefaultGoogleAccount() {
            return null;
        }

        public ListenableFuture<List<AccountInfo>> getAccountsAsync() {
            return Futures.immediateFuture(Collections.emptyList());
        }

        public ListenableFuture<List<AccountInfo>> filterAccountsAsync(Predicate<AccountInfo> predicate) {
            return Futures.immediateFuture(Collections.emptyList());
        }
    };
    static final String TAG = "AccountTypeManager";
    private static AccountTypeManager mAccountTypeManager;
    private static final Object mInitializationLock = new Object();

    public enum AccountFilter implements Predicate<AccountInfo> {
        ALL {
            public boolean apply(AccountInfo accountInfo) {
                return accountInfo != null;
            }
        },
        CONTACTS_WRITABLE {
            public boolean apply(AccountInfo accountInfo) {
                return accountInfo != null && accountInfo.getType().areContactsWritable();
            }
        },
        GROUPS_WRITABLE {
            public boolean apply(AccountInfo accountInfo) {
                return accountInfo != null && accountInfo.getType().isGroupMembershipEditable();
            }
        }
    }

    public abstract ListenableFuture<List<AccountInfo>> filterAccountsAsync(Predicate<AccountInfo> predicate);

    public abstract AccountInfo getAccountInfoForAccount(AccountWithDataSet accountWithDataSet);

    public abstract AccountType getAccountType(AccountTypeWithDataSet accountTypeWithDataSet);

    public abstract ListenableFuture<List<AccountInfo>> getAccountsAsync();

    public abstract Account getDefaultGoogleAccount();

    public static AccountTypeManager getInstance(Context context) {
        if (!hasRequiredPermissions(context)) {
            return EMPTY;
        }
        synchronized (mInitializationLock) {
            if (mAccountTypeManager == null) {
                mAccountTypeManager = new AccountTypeManagerImpl(context.getApplicationContext());
            }
        }
        return mAccountTypeManager;
    }

    public static void setInstanceForTest(AccountTypeManager accountTypeManager) {
        synchronized (mInitializationLock) {
            mAccountTypeManager = accountTypeManager;
        }
    }

    public List<AccountWithDataSet> getAccounts(boolean z) {
        if (z) {
            return blockForWritableAccounts();
        }
        return AccountInfo.extractAccounts((List) Futures.getUnchecked(getAccountsAsync()));
    }

    public List<AccountWithDataSet> blockForWritableAccounts() {
        return AccountInfo.extractAccounts((List) Futures.getUnchecked(filterAccountsAsync(AccountFilter.CONTACTS_WRITABLE)));
    }

    public List<AccountInfo> getWritableGoogleAccounts() {
        return (List) Futures.getUnchecked(filterAccountsAsync(new Predicate<AccountInfo>() {
            public boolean apply(AccountInfo accountInfo) {
                return accountInfo.getType().areContactsWritable() && GoogleAccountType.ACCOUNT_TYPE.equals(accountInfo.getType().accountType);
            }
        }));
    }

    public boolean hasNonLocalAccount() {
        List<AccountWithDataSet> extractAccounts = AccountInfo.extractAccounts((List) Futures.getUnchecked(getAccountsAsync()));
        if (extractAccounts == null || extractAccounts.size() == 0) {
            return false;
        }
        if (extractAccounts.size() > 1) {
            return true;
        }
        return !extractAccounts.get(0).isNullAccount();
    }

    static Account getDefaultGoogleAccount(AccountManager accountManager, SharedPreferences sharedPreferences, String str) {
        Account[] accountsByType = accountManager.getAccountsByType(GoogleAccountType.ACCOUNT_TYPE);
        AccountWithDataSet accountWithDataSet = null;
        if (accountsByType == null || accountsByType.length == 0) {
            return null;
        }
        String string = sharedPreferences.getString(str, (String) null);
        if (string != null) {
            accountWithDataSet = AccountWithDataSet.unstringify(string);
        }
        if (accountWithDataSet != null) {
            for (int i = 0; i < accountsByType.length; i++) {
                if (TextUtils.equals(accountWithDataSet.name, accountsByType[i].name) && TextUtils.equals(accountWithDataSet.type, accountsByType[i].type)) {
                    return accountsByType[i];
                }
            }
        }
        return accountsByType[0];
    }

    public final AccountType getAccountType(String str, String str2) {
        return getAccountType(AccountTypeWithDataSet.get(str, str2));
    }

    public final AccountType getAccountTypeForAccount(AccountWithDataSet accountWithDataSet) {
        if (accountWithDataSet != null) {
            return getAccountType(accountWithDataSet.getAccountTypeWithDataSet());
        }
        return getAccountType((String) null, (String) null);
    }

    public DataKind getKindOrFallback(AccountType accountType, String str) {
        if (accountType == null) {
            return null;
        }
        return accountType.getKindForMimetype(str);
    }

    public boolean exists(AccountWithDataSet accountWithDataSet) {
        return AccountInfo.extractAccounts((List) Futures.getUnchecked(getAccountsAsync())).contains(accountWithDataSet);
    }

    public boolean isWritable(AccountWithDataSet accountWithDataSet) {
        return exists(accountWithDataSet) && getAccountInfoForAccount(accountWithDataSet).getType().areContactsWritable();
    }

    public boolean hasGoogleAccount() {
        return getDefaultGoogleAccount() != null;
    }

    private static boolean hasRequiredPermissions(Context context) {
        boolean z = ContextCompat.checkSelfPermission(context, "android.permission.GET_ACCOUNTS") == 0;
        boolean z2 = ContextCompat.checkSelfPermission(context, PermissionsUtil.CONTACTS) == 0;
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    public static Predicate<AccountInfo> writableFilter() {
        return AccountFilter.CONTACTS_WRITABLE;
    }

    public static Predicate<AccountInfo> groupWritableFilter() {
        return AccountFilter.GROUPS_WRITABLE;
    }
}
