package com.android.contacts.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.util.DeviceLocalAccountTypeFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cp2DeviceLocalAccountLocator extends DeviceLocalAccountLocator {
    private static final int COL_DATA_SET = 2;
    private static final int COL_NAME = 0;
    private static final int COL_TYPE = 1;
    static String[] PROJECTION = {"account_name", "account_type", "data_set"};
    private final DeviceLocalAccountTypeFactory mAccountTypeFactory;
    private final ContentResolver mResolver;
    private final String mSelection;
    private final String[] mSelectionArgs;

    public Cp2DeviceLocalAccountLocator(ContentResolver contentResolver, DeviceLocalAccountTypeFactory deviceLocalAccountTypeFactory, Set<String> set) {
        this.mResolver = contentResolver;
        this.mAccountTypeFactory = deviceLocalAccountTypeFactory;
        this.mSelection = getSelection(set);
        this.mSelectionArgs = getSelectionArgs(set);
    }

    public List<AccountWithDataSet> getDeviceLocalAccounts() {
        HashSet hashSet = new HashSet();
        addAccountsFromQuery(ContactsContract.Groups.CONTENT_URI, hashSet);
        addAccountsFromQuery(ContactsContract.Settings.CONTENT_URI, hashSet);
        addAccountsFromQuery(ContactsContract.RawContacts.CONTENT_URI, hashSet);
        return new ArrayList(hashSet);
    }

    private void addAccountsFromQuery(Uri uri, Set<AccountWithDataSet> set) {
        Cursor query = this.mResolver.query(uri, PROJECTION, this.mSelection, this.mSelectionArgs, (String) null);
        if (query != null) {
            try {
                addAccountsFromCursor(query, set);
            } finally {
                query.close();
            }
        }
    }

    private void addAccountsFromCursor(Cursor cursor, Set<AccountWithDataSet> set) {
        while (cursor.moveToNext()) {
            String string = cursor.getString(0);
            String string2 = cursor.getString(1);
            String string3 = cursor.getString(2);
            if (DeviceLocalAccountTypeFactory.Util.isLocalAccountType(this.mAccountTypeFactory, string2)) {
                set.add(new AccountWithDataSet(string, string2, string3));
            }
        }
    }

    public String getSelection() {
        return this.mSelection;
    }

    public String[] getSelectionArgs() {
        return this.mSelectionArgs;
    }

    private static String getSelection(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        sb.append("account_type");
        sb.append(" IS NULL");
        if (set.isEmpty()) {
            return sb.toString();
        }
        sb.append(" OR ");
        sb.append("account_type");
        sb.append(" NOT IN (");
        for (String next : set) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1).append(')');
        return sb.toString();
    }

    private static String[] getSelectionArgs(Set<String> set) {
        if (set.isEmpty()) {
            return null;
        }
        return (String[]) set.toArray(new String[set.size()]);
    }
}
