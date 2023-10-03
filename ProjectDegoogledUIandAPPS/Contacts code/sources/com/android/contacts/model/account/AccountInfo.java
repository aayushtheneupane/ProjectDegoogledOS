package com.android.contacts.model.account;

import android.graphics.drawable.Drawable;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class AccountInfo {
    public static final Function<AccountInfo, AccountWithDataSet> ACCOUNT_EXTRACTOR = new Function<AccountInfo, AccountWithDataSet>() {
        public AccountWithDataSet apply(AccountInfo accountInfo) {
            return accountInfo.getAccount();
        }
    };
    private final AccountDisplayInfo mDisplayInfo;
    private final AccountType mType;

    public AccountInfo(AccountDisplayInfo accountDisplayInfo, AccountType accountType) {
        this.mDisplayInfo = accountDisplayInfo;
        this.mType = accountType;
    }

    public AccountType getType() {
        return this.mType;
    }

    public AccountWithDataSet getAccount() {
        return this.mDisplayInfo.getSource();
    }

    public CharSequence getNameLabel() {
        return this.mDisplayInfo.getNameLabel();
    }

    public CharSequence getTypeLabel() {
        return this.mDisplayInfo.getTypeLabel();
    }

    public Drawable getIcon() {
        return this.mDisplayInfo.getIcon();
    }

    public boolean hasDistinctName() {
        return this.mDisplayInfo.hasDistinctName();
    }

    public boolean isDeviceAccount() {
        return this.mDisplayInfo.isDeviceAccount();
    }

    public boolean hasGoogleAccountType() {
        return this.mDisplayInfo.hasGoogleAccountType();
    }

    public boolean sameAccount(AccountInfo accountInfo) {
        return sameAccount(accountInfo.getAccount());
    }

    public boolean sameAccount(AccountWithDataSet accountWithDataSet) {
        return Objects.equals(getAccount(), accountWithDataSet);
    }

    public static boolean contains(List<AccountInfo> list, AccountInfo accountInfo) {
        return contains(list, accountInfo.getAccount());
    }

    public static boolean contains(List<AccountInfo> list, AccountWithDataSet accountWithDataSet) {
        return getAccount(list, accountWithDataSet) != null;
    }

    public static AccountInfo getAccount(List<AccountInfo> list, AccountWithDataSet accountWithDataSet) {
        Preconditions.checkNotNull(list);
        for (AccountInfo next : list) {
            if (next.sameAccount(accountWithDataSet)) {
                return next;
            }
        }
        return null;
    }

    public static void sortAccounts(AccountWithDataSet accountWithDataSet, List<AccountInfo> list) {
        Collections.sort(list, sourceComparator(accountWithDataSet));
    }

    public static List<AccountWithDataSet> extractAccounts(List<AccountInfo> list) {
        return Lists.transform(list, ACCOUNT_EXTRACTOR);
    }

    private static Comparator<AccountInfo> sourceComparator(AccountWithDataSet accountWithDataSet) {
        final AccountComparator accountComparator = new AccountComparator(accountWithDataSet);
        return new Comparator<AccountInfo>() {
            public int compare(AccountInfo accountInfo, AccountInfo accountInfo2) {
                return AccountComparator.this.compare(accountInfo.getAccount(), accountInfo2.getAccount());
            }
        };
    }
}
