package com.android.contacts.common.model;

import android.content.Context;
import com.android.contacts.common.model.account.AccountType;
import com.android.contacts.common.model.account.AccountTypeWithDataSet;
import com.android.contacts.common.model.account.AccountWithDataSet;
import java.util.List;
import java.util.Map;

public abstract class AccountTypeManager {
    private static AccountTypeManager mAccountTypeManager;
    private static final Object mInitializationLock = new Object();

    public static AccountTypeManager getInstance(Context context) {
        synchronized (mInitializationLock) {
            if (mAccountTypeManager == null) {
                mAccountTypeManager = new AccountTypeManagerImpl(context.getApplicationContext());
            }
        }
        return mAccountTypeManager;
    }

    public abstract AccountType getAccountType(AccountTypeWithDataSet accountTypeWithDataSet);

    public final AccountType getAccountType(String str, String str2) {
        return getAccountType(AccountTypeWithDataSet.get(str, str2));
    }

    public abstract List<AccountWithDataSet> getAccounts(boolean z);

    public abstract Map<AccountTypeWithDataSet, AccountType> getUsableInvitableAccountTypes();
}
