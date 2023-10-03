package com.android.contacts.model.account;

import com.google.common.base.Objects;
import java.util.Comparator;

public class AccountComparator implements Comparator<AccountWithDataSet> {
    private AccountWithDataSet mDefaultAccount;

    public AccountComparator(AccountWithDataSet accountWithDataSet) {
        this.mDefaultAccount = accountWithDataSet;
    }

    public int compare(AccountWithDataSet accountWithDataSet, AccountWithDataSet accountWithDataSet2) {
        if (Objects.equal(accountWithDataSet.name, accountWithDataSet2.name) && Objects.equal(accountWithDataSet.type, accountWithDataSet2.type) && Objects.equal(accountWithDataSet.dataSet, accountWithDataSet2.dataSet)) {
            return 0;
        }
        if (accountWithDataSet2.name == null || accountWithDataSet2.type == null) {
            return -1;
        }
        if (accountWithDataSet.name == null || accountWithDataSet.type == null) {
            return 1;
        }
        if (isWritableGoogleAccount(accountWithDataSet) && accountWithDataSet.equals(this.mDefaultAccount)) {
            return -1;
        }
        if (isWritableGoogleAccount(accountWithDataSet2) && accountWithDataSet2.equals(this.mDefaultAccount)) {
            return 1;
        }
        if (isWritableGoogleAccount(accountWithDataSet) && !isWritableGoogleAccount(accountWithDataSet2)) {
            return -1;
        }
        if (isWritableGoogleAccount(accountWithDataSet2) && !isWritableGoogleAccount(accountWithDataSet)) {
            return 1;
        }
        int compareToIgnoreCase = accountWithDataSet.name.compareToIgnoreCase(accountWithDataSet2.name);
        if (compareToIgnoreCase != 0) {
            return compareToIgnoreCase;
        }
        int compareToIgnoreCase2 = accountWithDataSet.type.compareToIgnoreCase(accountWithDataSet2.type);
        if (compareToIgnoreCase2 != 0) {
            return compareToIgnoreCase2;
        }
        String str = accountWithDataSet.dataSet;
        if (str == null) {
            return -1;
        }
        String str2 = accountWithDataSet2.dataSet;
        if (str2 == null) {
            return 1;
        }
        return str.compareToIgnoreCase(str2);
    }

    private static boolean isWritableGoogleAccount(AccountWithDataSet accountWithDataSet) {
        return GoogleAccountType.ACCOUNT_TYPE.equals(accountWithDataSet.type) && accountWithDataSet.dataSet == null;
    }
}
