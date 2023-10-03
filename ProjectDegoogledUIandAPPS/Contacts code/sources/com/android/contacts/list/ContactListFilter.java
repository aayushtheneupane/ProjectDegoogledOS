package com.android.contacts.list;

import android.accounts.Account;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.contacts.ContactSaveService;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.model.account.GoogleAccountType;
import java.util.ArrayList;
import java.util.List;

public final class ContactListFilter implements Comparable<ContactListFilter>, Parcelable {
    public static final Parcelable.Creator<ContactListFilter> CREATOR = new Parcelable.Creator<ContactListFilter>() {
        public ContactListFilter createFromParcel(Parcel parcel) {
            return new ContactListFilter(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), (Drawable) null);
        }

        public ContactListFilter[] newArray(int i) {
            return new ContactListFilter[i];
        }
    };
    public final String accountName;
    public final String accountType;
    public final String dataSet;
    public final int filterType;
    public final Drawable icon;
    private String mId;

    public static final String filterTypeToString(int i) {
        switch (i) {
            case -8:
                return "FILTER_TYPE_DEVICE_CONTACTS";
            case -7:
                return "FILTER_TYPE_GROUP_MEMBERS";
            case -6:
                return "FILTER_TYPE_SINGLE_CONTACT";
            case -5:
                return "FILTER_TYPE_WITH_PHONE_NUMBERS_ONLY";
            case -4:
                return "FILTER_TYPE_STARRED";
            case -3:
                return "FILTER_TYPE_CUSTOM";
            case -2:
                return "FILTER_TYPE_ALL_ACCOUNTS";
            case -1:
                return "FILTER_TYPE_DEFAULT";
            case 0:
                return "FILTER_TYPE_ACCOUNT";
            default:
                return "(unknown)";
        }
    }

    public int describeContents() {
        return 0;
    }

    public ContactListFilter(int i, String str, String str2, String str3, Drawable drawable) {
        this.filterType = i;
        this.accountType = str;
        this.accountName = str2;
        this.dataSet = str3;
        this.icon = drawable;
    }

    public static ContactListFilter createFilterWithType(int i) {
        return new ContactListFilter(i, (String) null, (String) null, (String) null, (Drawable) null);
    }

    public static ContactListFilter createAccountFilter(String str, String str2, String str3, Drawable drawable) {
        return new ContactListFilter(0, str, str2, str3, drawable);
    }

    public static ContactListFilter createGroupMembersFilter(String str, String str2, String str3) {
        return new ContactListFilter(-7, str, str2, str3, (Drawable) null);
    }

    public static ContactListFilter createDeviceContactsFilter(Drawable drawable, AccountWithDataSet accountWithDataSet) {
        return new ContactListFilter(-8, accountWithDataSet.type, accountWithDataSet.name, accountWithDataSet.dataSet, drawable);
    }

    public boolean isContactsFilterType() {
        int i = this.filterType;
        return i == -1 || i == -2 || i == -3;
    }

    public int toListType() {
        switch (this.filterType) {
            case -8:
                return 5;
            case -7:
                return 3;
            case -6:
                return 9;
            case -5:
                return 8;
            case -4:
                return 7;
            case -3:
                return 6;
            case -2:
            case -1:
                return 1;
            case 0:
                return 2;
            default:
                return 0;
        }
    }

    public String toString() {
        String str;
        switch (this.filterType) {
            case -8:
                return "device_contacts";
            case -7:
                return "group_members";
            case -6:
                return "single";
            case -5:
                return "with_phones";
            case -4:
                return ContactSaveService.EXTRA_STARRED_FLAG;
            case -3:
                return "custom";
            case -2:
                return "all_accounts";
            case -1:
                return "default";
            case 0:
                StringBuilder sb = new StringBuilder();
                sb.append("account: ");
                sb.append(this.accountType);
                if (this.dataSet != null) {
                    str = "/" + this.dataSet;
                } else {
                    str = "";
                }
                sb.append(str);
                sb.append(" ");
                sb.append(this.accountName);
                return sb.toString();
            default:
                return super.toString();
        }
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

    public int hashCode() {
        int i = this.filterType;
        String str = this.accountType;
        if (str != null) {
            i = (i * 31) + str.hashCode();
        }
        String str2 = this.accountName;
        if (str2 != null) {
            i = (i * 31) + str2.hashCode();
        }
        String str3 = this.dataSet;
        return str3 != null ? (i * 31) + str3.hashCode() : i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContactListFilter)) {
            return false;
        }
        ContactListFilter contactListFilter = (ContactListFilter) obj;
        return this.filterType == contactListFilter.filterType && TextUtils.equals(this.accountName, contactListFilter.accountName) && TextUtils.equals(this.accountType, contactListFilter.accountType) && TextUtils.equals(this.dataSet, contactListFilter.dataSet);
    }

    public static void storeToPreferences(SharedPreferences sharedPreferences, ContactListFilter contactListFilter) {
        int i;
        String str;
        String str2;
        if (contactListFilter == null || contactListFilter.filterType != -6) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (contactListFilter == null) {
                i = -1;
            } else {
                i = contactListFilter.filterType;
            }
            SharedPreferences.Editor putInt = edit.putInt("filter.type", i);
            String str3 = null;
            if (contactListFilter == null) {
                str = null;
            } else {
                str = contactListFilter.accountName;
            }
            SharedPreferences.Editor putString = putInt.putString("filter.accountName", str);
            if (contactListFilter == null) {
                str2 = null;
            } else {
                str2 = contactListFilter.accountType;
            }
            SharedPreferences.Editor putString2 = putString.putString("filter.accountType", str2);
            if (contactListFilter != null) {
                str3 = contactListFilter.dataSet;
            }
            putString2.putString("filter.dataSet", str3).apply();
        }
    }

    public static ContactListFilter restoreDefaultPreferences(SharedPreferences sharedPreferences) {
        ContactListFilter restoreFromPreferences = restoreFromPreferences(sharedPreferences);
        if (restoreFromPreferences == null) {
            restoreFromPreferences = createFilterWithType(-2);
        }
        int i = restoreFromPreferences.filterType;
        return (i == 1 || i == -6) ? createFilterWithType(-2) : restoreFromPreferences;
    }

    private static ContactListFilter restoreFromPreferences(SharedPreferences sharedPreferences) {
        int i = sharedPreferences.getInt("filter.type", -1);
        if (i == -1) {
            return null;
        }
        return new ContactListFilter(i, sharedPreferences.getString("filter.accountType", (String) null), sharedPreferences.getString("filter.accountName", (String) null), sharedPreferences.getString("filter.dataSet", (String) null), (Drawable) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.filterType);
        parcel.writeString(this.accountName);
        parcel.writeString(this.accountType);
        parcel.writeString(this.dataSet);
    }

    public String getId() {
        if (this.mId == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.filterType);
            if (this.accountType != null) {
                sb.append('-');
                sb.append(this.accountType);
            }
            if (this.dataSet != null) {
                sb.append('/');
                sb.append(this.dataSet);
            }
            if (this.accountName != null) {
                sb.append('-');
                sb.append(this.accountName.replace('-', '_'));
            }
            this.mId = sb.toString();
        }
        return this.mId;
    }

    public Uri.Builder addAccountQueryParameterToUrl(Uri.Builder builder) {
        int i = this.filterType;
        if (i == 0 || i == -7) {
            String str = this.accountName;
            if (str != null) {
                builder.appendQueryParameter("account_name", str);
                builder.appendQueryParameter("account_type", this.accountType);
            }
            String str2 = this.dataSet;
            if (str2 != null) {
                builder.appendQueryParameter("data_set", str2);
            }
            return builder;
        }
        throw new IllegalStateException("filterType must be FILTER_TYPE_ACCOUNT or FILER_TYPE_GROUP_MEMBERS");
    }

    public AccountWithDataSet toAccountWithDataSet() {
        int i = this.filterType;
        if (i == 0 || i == -8) {
            return new AccountWithDataSet(this.accountName, this.accountType, this.dataSet);
        }
        throw new IllegalStateException("Cannot create Account from filter type " + filterTypeToString(this.filterType));
    }

    public boolean isSyncable() {
        return isGoogleAccountType() && this.filterType == 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r0 = r2.filterType;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldShowSyncState() {
        /*
            r2 = this;
            boolean r0 = r2.isGoogleAccountType()
            if (r0 == 0) goto L_0x000a
            int r0 = r2.filterType
            if (r0 == 0) goto L_0x0018
        L_0x000a:
            int r0 = r2.filterType
            r1 = -2
            if (r0 == r1) goto L_0x0018
            r1 = -3
            if (r0 == r1) goto L_0x0018
            r1 = -1
            if (r0 != r1) goto L_0x0016
            goto L_0x0018
        L_0x0016:
            r0 = 0
            goto L_0x0019
        L_0x0018:
            r0 = 1
        L_0x0019:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.list.ContactListFilter.shouldShowSyncState():boolean");
    }

    public List<Account> getSyncableAccounts(List<AccountWithDataSet> list) {
        ArrayList arrayList = new ArrayList();
        if (!isGoogleAccountType() || this.filterType != 0) {
            int i = this.filterType;
            if ((i == -2 || i == -3 || i == -1) && list != null && list.size() > 0) {
                for (AccountWithDataSet next : list) {
                    if (GoogleAccountType.ACCOUNT_TYPE.equals(next.type) && next.dataSet == null) {
                        arrayList.add(new Account(next.name, next.type));
                    }
                }
            }
        } else {
            arrayList.add(new Account(this.accountName, this.accountType));
        }
        return arrayList;
    }

    public boolean isGoogleAccountType() {
        return GoogleAccountType.ACCOUNT_TYPE.equals(this.accountType) && this.dataSet == null;
    }
}
