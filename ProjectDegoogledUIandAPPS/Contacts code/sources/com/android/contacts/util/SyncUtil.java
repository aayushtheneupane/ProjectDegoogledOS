package com.android.contacts.util;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.model.account.GoogleAccountType;
import java.util.List;

public final class SyncUtil {
    public static final int SYNC_SETTING_ACCOUNT_SYNC_OFF = 2;
    public static final int SYNC_SETTING_GLOBAL_SYNC_OFF = 1;
    public static final int SYNC_SETTING_SYNC_ON = 0;
    private static final String TAG = "SyncUtil";

    private SyncUtil() {
    }

    public static final boolean isSyncStatusPendingOrActive(Account account) {
        if (account == null) {
            return false;
        }
        if (ContentResolver.isSyncPending(account, "com.android.contacts") || ContentResolver.isSyncActive(account, "com.android.contacts")) {
            return true;
        }
        return false;
    }

    public static final boolean isAnySyncing(List<AccountWithDataSet> list) {
        for (AccountWithDataSet accountOrNull : list) {
            if (isSyncStatusPendingOrActive(accountOrNull.getAccountOrNull())) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isUnsyncableGoogleAccount(Account account) {
        if (account == null || !GoogleAccountType.ACCOUNT_TYPE.equals(account.type) || ContentResolver.getIsSyncable(account, "com.android.contacts") > 0) {
            return false;
        }
        return true;
    }

    public static final boolean hasSyncableAccount(AccountTypeManager accountTypeManager) {
        return !accountTypeManager.getWritableGoogleAccounts().isEmpty();
    }

    public static boolean isAlertVisible(Context context, Account account, int i) {
        if (i == 1) {
            return SharedPreferenceUtil.getNumOfDismissesForAutoSyncOff(context) == 0;
        }
        if (i == 2 && account != null && SharedPreferenceUtil.getNumOfDismissesforAccountSyncOff(context, account.name) == 0) {
            return true;
        }
        return false;
    }

    public static int calculateReasonSyncOff(Context context, Account account) {
        if (ContentResolver.getMasterSyncAutomatically()) {
            SharedPreferenceUtil.resetNumOfDismissesForAutoSyncOff(context);
            if (account == null) {
                return 0;
            }
            if (!ContentResolver.getSyncAutomatically(account, "com.android.contacts")) {
                return 2;
            }
            SharedPreferenceUtil.resetNumOfDismissesForAccountSyncOff(context, account.name);
            return 0;
        } else if (account == null) {
            return 1;
        } else {
            SharedPreferenceUtil.resetNumOfDismissesForAccountSyncOff(context, account.name);
            return 1;
        }
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
