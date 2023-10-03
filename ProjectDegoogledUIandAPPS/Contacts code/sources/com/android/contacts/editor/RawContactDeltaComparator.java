package com.android.contacts.editor;

import android.content.Context;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.RawContactDelta;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.GoogleAccountType;
import java.util.Comparator;

class RawContactDeltaComparator implements Comparator<RawContactDelta> {
    private Context mContext;

    public RawContactDeltaComparator(Context context) {
        this.mContext = context;
    }

    public int compare(RawContactDelta rawContactDelta, RawContactDelta rawContactDelta2) {
        String str;
        int compareTo;
        String str2;
        int compareTo2;
        if (rawContactDelta.equals(rawContactDelta2)) {
            return 0;
        }
        AccountTypeManager instance = AccountTypeManager.getInstance(this.mContext);
        AccountType accountType = instance.getAccountType(rawContactDelta.getValues().getAsString("account_type"), rawContactDelta.getValues().getAsString("data_set"));
        AccountType accountType2 = instance.getAccountType(rawContactDelta2.getValues().getAsString("account_type"), rawContactDelta2.getValues().getAsString("data_set"));
        if (!accountType.areContactsWritable() && accountType2.areContactsWritable()) {
            return 1;
        }
        if (accountType.areContactsWritable() && !accountType2.areContactsWritable()) {
            return -1;
        }
        boolean z = accountType instanceof GoogleAccountType;
        boolean z2 = accountType2 instanceof GoogleAccountType;
        if (z && !z2) {
            return -1;
        }
        if (!z && z2) {
            return 1;
        }
        if (!(z && z2)) {
            if (accountType.accountType != null && accountType2.accountType == null) {
                return -1;
            }
            if (accountType.accountType == null && accountType2.accountType != null) {
                return 1;
            }
            String str3 = accountType.accountType;
            if (str3 != null && (str2 = accountType2.accountType) != null && (compareTo2 = str3.compareTo(str2)) != 0) {
                return compareTo2;
            }
            if (accountType.dataSet != null && accountType2.dataSet == null) {
                return -1;
            }
            if (accountType.dataSet == null && accountType2.dataSet != null) {
                return 1;
            }
            String str4 = accountType.dataSet;
            if (!(str4 == null || (str = accountType2.dataSet) == null || (compareTo = str4.compareTo(str)) == 0)) {
                return compareTo;
            }
        }
        String accountName = rawContactDelta.getAccountName();
        String str5 = "";
        if (accountName == null) {
            accountName = str5;
        }
        String accountName2 = rawContactDelta2.getAccountName();
        if (accountName2 != null) {
            str5 = accountName2;
        }
        int compareTo3 = accountName.compareTo(str5);
        if (compareTo3 != 0) {
            return compareTo3;
        }
        Long rawContactId = rawContactDelta.getRawContactId();
        Long rawContactId2 = rawContactDelta2.getRawContactId();
        if (rawContactId == null && rawContactId2 == null) {
            return 0;
        }
        if (rawContactId == null) {
            return -1;
        }
        if (rawContactId2 == null) {
            return 1;
        }
        return Long.compare(rawContactId.longValue(), rawContactId2.longValue());
    }
}
