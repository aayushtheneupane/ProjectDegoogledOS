package com.android.contacts.common.model.account;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Objects;
import java.util.regex.Pattern;

public class AccountWithDataSet implements Parcelable {
    public static final Parcelable.Creator<AccountWithDataSet> CREATOR = new Parcelable.Creator<AccountWithDataSet>() {
        public Object createFromParcel(Parcel parcel) {
            return new AccountWithDataSet(parcel);
        }

        public Object[] newArray(int i) {
            return new AccountWithDataSet[i];
        }
    };
    public final String dataSet;
    private final AccountTypeWithDataSet mAccountTypeWithDataSet;
    public final String name;
    public final String type;

    static {
        Pattern.compile(Pattern.quote("\u0001"));
        Pattern.compile(Pattern.quote("\u0002"));
        new String[]{"_id"};
        ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter("limit", "1").build();
    }

    public AccountWithDataSet(String str, String str2, String str3) {
        this.name = emptyToNull(str);
        this.type = emptyToNull(str2);
        this.dataSet = emptyToNull(str3);
        this.mAccountTypeWithDataSet = AccountTypeWithDataSet.get(str2, str3);
    }

    private static String emptyToNull(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AccountWithDataSet)) {
            return false;
        }
        AccountWithDataSet accountWithDataSet = (AccountWithDataSet) obj;
        if (!Objects.equals(this.name, accountWithDataSet.name) || !Objects.equals(this.type, accountWithDataSet.type) || !Objects.equals(this.dataSet, accountWithDataSet.dataSet)) {
            return false;
        }
        return true;
    }

    public AccountTypeWithDataSet getAccountTypeWithDataSet() {
        return this.mAccountTypeWithDataSet;
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
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("AccountWithDataSet {name=");
        outline13.append(this.name);
        outline13.append(", type=");
        outline13.append(this.type);
        outline13.append(", dataSet=");
        return GeneratedOutlineSupport.outline12(outline13, this.dataSet, "}");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.type);
        parcel.writeString(this.dataSet);
    }

    public AccountWithDataSet(Parcel parcel) {
        this.name = parcel.readString();
        this.type = parcel.readString();
        this.dataSet = parcel.readString();
        this.mAccountTypeWithDataSet = AccountTypeWithDataSet.get(this.type, this.dataSet);
    }
}
