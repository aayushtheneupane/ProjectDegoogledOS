package com.android.contacts.common.list;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class ContactListFilter implements Comparable<ContactListFilter>, Parcelable {
    public static final Parcelable.Creator<ContactListFilter> CREATOR = new Parcelable.Creator<ContactListFilter>() {
        public Object createFromParcel(Parcel parcel) {
            return new ContactListFilter(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), (Drawable) null);
        }

        public Object[] newArray(int i) {
            return new ContactListFilter[i];
        }
    };
    public final String accountName;
    public final String accountType;
    public final String dataSet;
    public final int filterType;

    public ContactListFilter(int i, String str, String str2, String str3, Drawable drawable) {
        this.filterType = i;
        this.accountType = str;
        this.accountName = str2;
        this.dataSet = str3;
    }

    public static ContactListFilter createFilterWithType(int i) {
        return new ContactListFilter(i, (String) null, (String) null, (String) null, (Drawable) null);
    }

    public static ContactListFilter restoreDefaultPreferences(SharedPreferences sharedPreferences) {
        ContactListFilter contactListFilter;
        int i = sharedPreferences.getInt("filter.type", -1);
        if (i == -1) {
            contactListFilter = null;
        } else {
            contactListFilter = new ContactListFilter(i, sharedPreferences.getString("filter.accountType", (String) null), sharedPreferences.getString("filter.accountName", (String) null), sharedPreferences.getString("filter.dataSet", (String) null), (Drawable) null);
        }
        if (contactListFilter == null) {
            contactListFilter = createFilterWithType(-2);
        }
        int i2 = contactListFilter.filterType;
        return (i2 == 1 || i2 == -6) ? createFilterWithType(-2) : contactListFilter;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContactListFilter)) {
            return false;
        }
        ContactListFilter contactListFilter = (ContactListFilter) obj;
        if (this.filterType != contactListFilter.filterType || !TextUtils.equals(this.accountName, contactListFilter.accountName) || !TextUtils.equals(this.accountType, contactListFilter.accountType) || !TextUtils.equals(this.dataSet, contactListFilter.dataSet)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.filterType;
        String str = this.accountType;
        if (str != null) {
            i = this.accountName.hashCode() + ((str.hashCode() + (i * 31)) * 31);
        }
        String str2 = this.dataSet;
        return str2 != null ? (i * 31) + str2.hashCode() : i;
    }

    public String toString() {
        String str;
        switch (this.filterType) {
            case -6:
                return "single";
            case -5:
                return "with_phones";
            case -4:
                return "starred";
            case -3:
                return "custom";
            case -2:
                return "all_accounts";
            case -1:
                return "default";
            case 0:
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("account: ");
                outline13.append(this.accountType);
                if (this.dataSet != null) {
                    StringBuilder outline132 = GeneratedOutlineSupport.outline13("/");
                    outline132.append(this.dataSet);
                    str = outline132.toString();
                } else {
                    str = "";
                }
                outline13.append(str);
                outline13.append(" ");
                outline13.append(this.accountName);
                return outline13.toString();
            default:
                return super.toString();
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.filterType);
        parcel.writeString(this.accountName);
        parcel.writeString(this.accountType);
        parcel.writeString(this.dataSet);
    }

    public int compareTo(ContactListFilter contactListFilter) {
        int compareTo = this.accountName.compareTo(contactListFilter.accountName);
        if (compareTo != 0) {
            return compareTo;
        }
        int compareTo2 = this.accountType.compareTo(contactListFilter.accountType);
        if (compareTo2 != 0) {
            return compareTo2;
        }
        return this.filterType - contactListFilter.filterType;
    }
}
