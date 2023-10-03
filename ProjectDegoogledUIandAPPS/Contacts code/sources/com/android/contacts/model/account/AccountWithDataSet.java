package com.android.contacts.model.account;

import android.accounts.Account;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AccountWithDataSet implements Parcelable {
    private static final String ARRAY_STRINGIFY_SEPARATOR = "\u0002";
    private static final Pattern ARRAY_STRINGIFY_SEPARATOR_PAT = Pattern.compile(Pattern.quote(ARRAY_STRINGIFY_SEPARATOR));
    public static final Parcelable.Creator<AccountWithDataSet> CREATOR = new Parcelable.Creator<AccountWithDataSet>() {
        public AccountWithDataSet createFromParcel(Parcel parcel) {
            return new AccountWithDataSet(parcel);
        }

        public AccountWithDataSet[] newArray(int i) {
            return new AccountWithDataSet[i];
        }
    };
    private static final String[] ID_PROJECTION = {"_id"};
    public static final String LOCAL_ACCOUNT_SELECTION = "account_type IS NULL AND account_name IS NULL AND data_set IS NULL";
    private static final Uri RAW_CONTACTS_URI_LIMIT_1 = ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter("limit", "1").build();
    private static final String STRINGIFY_SEPARATOR = "\u0001";
    private static final Pattern STRINGIFY_SEPARATOR_PAT = Pattern.compile(Pattern.quote(STRINGIFY_SEPARATOR));
    public final String dataSet;
    private final AccountTypeWithDataSet mAccountTypeWithDataSet;
    public final String name;
    public final String type;

    public int describeContents() {
        return 0;
    }

    public AccountWithDataSet(String str, String str2, String str3) {
        this.name = emptyToNull(str);
        this.type = emptyToNull(str2);
        this.dataSet = emptyToNull(str3);
        this.mAccountTypeWithDataSet = AccountTypeWithDataSet.get(str2, str3);
    }

    private static final String emptyToNull(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    public AccountWithDataSet(Parcel parcel) {
        this.name = parcel.readString();
        this.type = parcel.readString();
        this.dataSet = parcel.readString();
        this.mAccountTypeWithDataSet = AccountTypeWithDataSet.get(this.type, this.dataSet);
    }

    public boolean isNullAccount() {
        return this.name == null && this.type == null && this.dataSet == null;
    }

    public static AccountWithDataSet getNullAccount() {
        return new AccountWithDataSet((String) null, (String) null, (String) null);
    }

    public Account getAccountOrNull() {
        String str;
        String str2 = this.name;
        if (str2 == null || (str = this.type) == null) {
            return null;
        }
        return new Account(str2, str);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.type);
        parcel.writeString(this.dataSet);
    }

    public AccountTypeWithDataSet getAccountTypeWithDataSet() {
        return this.mAccountTypeWithDataSet;
    }

    public boolean hasData(Context context) {
        String[] strArr;
        String str;
        if (isNullAccount()) {
            strArr = null;
            str = LOCAL_ACCOUNT_SELECTION;
        } else if (TextUtils.isEmpty(this.dataSet)) {
            strArr = new String[]{this.type, this.name};
            str = "account_type = ? AND account_name = ? AND data_set IS NULL";
        } else {
            strArr = new String[]{this.type, this.name, this.dataSet};
            str = "account_type = ? AND account_name = ? AND data_set = ?";
        }
        String[] strArr2 = strArr;
        Cursor query = context.getContentResolver().query(RAW_CONTACTS_URI_LIMIT_1, ID_PROJECTION, str + " AND deleted=0", strArr2, (String) null);
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
        if (!(obj instanceof AccountWithDataSet)) {
            return false;
        }
        AccountWithDataSet accountWithDataSet = (AccountWithDataSet) obj;
        if (!Objects.equal(this.name, accountWithDataSet.name) || !Objects.equal(this.type, accountWithDataSet.type) || !Objects.equal(this.dataSet, accountWithDataSet.dataSet)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (527 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.type;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.dataSet;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "AccountWithDataSet {name=" + this.name + ", type=" + this.type + ", dataSet=" + this.dataSet + "}";
    }

    private static StringBuilder addStringified(StringBuilder sb, AccountWithDataSet accountWithDataSet) {
        if (!TextUtils.isEmpty(accountWithDataSet.name)) {
            sb.append(accountWithDataSet.name);
        }
        sb.append(STRINGIFY_SEPARATOR);
        if (!TextUtils.isEmpty(accountWithDataSet.type)) {
            sb.append(accountWithDataSet.type);
        }
        sb.append(STRINGIFY_SEPARATOR);
        if (!TextUtils.isEmpty(accountWithDataSet.dataSet)) {
            sb.append(accountWithDataSet.dataSet);
        }
        return sb;
    }

    public String stringify() {
        StringBuilder sb = new StringBuilder();
        addStringified(sb, this);
        return sb.toString();
    }

    public ContentProviderOperation newRawContactOperation() {
        ContentProviderOperation.Builder withValue = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_name", this.name).withValue("account_type", this.type);
        String str = this.dataSet;
        if (str != null) {
            withValue.withValue("data_set", str);
        }
        return withValue.build();
    }

    public static AccountWithDataSet unstringify(String str) {
        String[] split = STRINGIFY_SEPARATOR_PAT.split(str, 3);
        if (split.length >= 3) {
            return new AccountWithDataSet(split[0], split[1], TextUtils.isEmpty(split[2]) ? null : split[2]);
        }
        throw new IllegalArgumentException("Invalid string " + str);
    }

    public static String stringifyList(List<AccountWithDataSet> list) {
        StringBuilder sb = new StringBuilder();
        for (AccountWithDataSet next : list) {
            if (sb.length() > 0) {
                sb.append(ARRAY_STRINGIFY_SEPARATOR);
            }
            addStringified(sb, next);
        }
        return sb.toString();
    }

    public static List<AccountWithDataSet> unstringifyList(String str) {
        ArrayList newArrayList = Lists.newArrayList();
        if (TextUtils.isEmpty(str)) {
            return newArrayList;
        }
        String[] split = ARRAY_STRINGIFY_SEPARATOR_PAT.split(str);
        for (String unstringify : split) {
            newArrayList.add(unstringify(unstringify));
        }
        return newArrayList;
    }
}
