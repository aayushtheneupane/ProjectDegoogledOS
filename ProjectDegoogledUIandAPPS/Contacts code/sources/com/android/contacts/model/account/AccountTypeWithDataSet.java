package com.android.contacts.model.account;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.google.common.base.Objects;

public class AccountTypeWithDataSet {
    private static final String[] ID_PROJECTION = {"_id"};
    private static final Uri RAW_CONTACTS_URI_LIMIT_1 = ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter("limit", "1").build();
    public final String accountType;
    public final String dataSet;

    private AccountTypeWithDataSet(String str, String str2) {
        this.accountType = TextUtils.isEmpty(str) ? null : str;
        this.dataSet = TextUtils.isEmpty(str2) ? null : str2;
    }

    public static AccountTypeWithDataSet get(String str, String str2) {
        return new AccountTypeWithDataSet(str, str2);
    }

    public boolean hasData(Context context) {
        String str;
        String[] strArr;
        if (TextUtils.isEmpty(this.dataSet)) {
            strArr = new String[]{this.accountType};
            str = "account_type = ? AND data_set IS NULL";
        } else {
            strArr = new String[]{this.accountType, this.dataSet};
            str = "account_type = ? AND data_set = ?";
        }
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = RAW_CONTACTS_URI_LIMIT_1;
        String[] strArr2 = ID_PROJECTION;
        Cursor query = contentResolver.query(uri, strArr2, str, strArr, (String) null);
        if (query == null) {
            return false;
        }
        try {
            return query.moveToFirst();
        } finally {
            query.close();
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AccountTypeWithDataSet)) {
            return false;
        }
        AccountTypeWithDataSet accountTypeWithDataSet = (AccountTypeWithDataSet) obj;
        if (!Objects.equal(this.accountType, accountTypeWithDataSet.accountType) || !Objects.equal(this.dataSet, accountTypeWithDataSet.dataSet)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.accountType;
        int i = 0;
        int hashCode = str == null ? 0 : str.hashCode();
        String str2 = this.dataSet;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        return "[" + this.accountType + "/" + this.dataSet + "]";
    }
}
