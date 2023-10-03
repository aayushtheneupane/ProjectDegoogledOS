package com.android.contacts.group;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.contacts.ContactSaveService;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountType;
import com.google.common.base.MoreObjects;

public final class GroupMetaData implements Parcelable {
    public static final Parcelable.Creator<GroupMetaData> CREATOR = new Parcelable.Creator<GroupMetaData>() {
        public GroupMetaData createFromParcel(Parcel parcel) {
            return new GroupMetaData(parcel);
        }

        public GroupMetaData[] newArray(int i) {
            return new GroupMetaData[i];
        }
    };
    public final String accountName;
    public final String accountType;
    public final String dataSet;
    public final boolean defaultGroup;
    public final boolean editable;
    public final boolean favorites;
    public final long groupId;
    public final String groupName;
    public final boolean readOnly;
    public final Uri uri;

    public int describeContents() {
        return 0;
    }

    public GroupMetaData(Context context, Cursor cursor) {
        boolean z;
        AccountTypeManager instance = AccountTypeManager.getInstance(context);
        long j = cursor.getLong(3);
        Uri withAppendedId = ContentUris.withAppendedId(ContactsContract.Groups.CONTENT_URI, j);
        AccountType accountType2 = instance.getAccountType(cursor.getString(1), cursor.getString(2));
        if (accountType2 == null) {
            z = false;
        } else {
            z = accountType2.isGroupMembershipEditable();
        }
        this.uri = withAppendedId;
        this.accountName = cursor.getString(0);
        this.accountType = cursor.getString(1);
        this.dataSet = cursor.getString(2);
        this.groupId = j;
        this.groupName = cursor.getString(4);
        this.readOnly = getBoolean(cursor, 7);
        this.defaultGroup = getBoolean(cursor, 5);
        this.favorites = getBoolean(cursor, 6);
        this.editable = z;
    }

    private static boolean getBoolean(Cursor cursor, int i) {
        return !cursor.isNull(i) && cursor.getInt(i) != 0;
    }

    private GroupMetaData(Parcel parcel) {
        this.uri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.accountName = parcel.readString();
        this.accountType = parcel.readString();
        this.dataSet = parcel.readString();
        this.groupId = parcel.readLong();
        this.groupName = parcel.readString();
        boolean z = false;
        this.readOnly = parcel.readInt() == 1;
        this.defaultGroup = parcel.readInt() == 1;
        this.favorites = parcel.readInt() == 1;
        this.editable = parcel.readInt() == 1 ? true : z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.uri, 0);
        parcel.writeString(this.accountName);
        parcel.writeString(this.accountType);
        parcel.writeString(this.dataSet);
        parcel.writeLong(this.groupId);
        parcel.writeString(this.groupName);
        parcel.writeInt(this.readOnly ? 1 : 0);
        parcel.writeInt(this.defaultGroup ? 1 : 0);
        parcel.writeInt(this.favorites ? 1 : 0);
        parcel.writeInt(this.editable ? 1 : 0);
    }

    public boolean isValid() {
        return this.uri != null && !TextUtils.isEmpty(this.accountName) && !TextUtils.isEmpty(this.groupName) && this.groupId > 0;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Object) this);
        stringHelper.add(ContactSaveService.EXTRA_ACCOUNT_NAME, (Object) this.accountName);
        stringHelper.add(ContactSaveService.EXTRA_ACCOUNT_TYPE, (Object) this.accountType);
        stringHelper.add(ContactSaveService.EXTRA_DATA_SET, (Object) this.dataSet);
        stringHelper.add(ContactSaveService.EXTRA_GROUP_ID, this.groupId);
        stringHelper.add("groupName", (Object) this.groupName);
        stringHelper.add("readOnly", this.readOnly);
        stringHelper.add("defaultGroup", this.defaultGroup);
        stringHelper.add("favorites", this.favorites);
        stringHelper.add("editable", this.editable);
        stringHelper.add("isValid", isValid());
        return stringHelper.toString();
    }
}
