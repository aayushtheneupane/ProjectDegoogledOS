package com.android.contacts.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import com.android.contacts.ContactSaveService;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.model.dataitem.DataItem;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class RawContact implements Parcelable {
    public static final Parcelable.Creator<RawContact> CREATOR = new Parcelable.Creator<RawContact>() {
        public RawContact createFromParcel(Parcel parcel) {
            return new RawContact(parcel);
        }

        public RawContact[] newArray(int i) {
            return new RawContact[i];
        }
    };
    private AccountTypeManager mAccountTypeManager;
    private final ArrayList<NamedDataItem> mDataItems;
    private final ContentValues mValues;

    public int describeContents() {
        return 0;
    }

    public static final class NamedDataItem implements Parcelable {
        public static final Parcelable.Creator<NamedDataItem> CREATOR = new Parcelable.Creator<NamedDataItem>() {
            public NamedDataItem createFromParcel(Parcel parcel) {
                return new NamedDataItem(parcel);
            }

            public NamedDataItem[] newArray(int i) {
                return new NamedDataItem[i];
            }
        };
        public final ContentValues mContentValues;
        public final Uri mUri;

        public int describeContents() {
            return 0;
        }

        public NamedDataItem(Uri uri, ContentValues contentValues) {
            this.mUri = uri;
            this.mContentValues = contentValues;
        }

        public NamedDataItem(Parcel parcel) {
            this.mUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
            this.mContentValues = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mUri, i);
            parcel.writeParcelable(this.mContentValues, i);
        }

        public int hashCode() {
            return Objects.hashCode(this.mUri, this.mContentValues);
        }

        public boolean equals(Object obj) {
            if (obj == null || NamedDataItem.class != obj.getClass()) {
                return false;
            }
            NamedDataItem namedDataItem = (NamedDataItem) obj;
            if (!Objects.equal(this.mUri, namedDataItem.mUri) || !Objects.equal(this.mContentValues, namedDataItem.mContentValues)) {
                return false;
            }
            return true;
        }
    }

    public static RawContact createFrom(Entity entity) {
        ContentValues entityValues = entity.getEntityValues();
        ArrayList<Entity.NamedContentValues> subValues = entity.getSubValues();
        RawContact rawContact = new RawContact(entityValues);
        Iterator<Entity.NamedContentValues> it = subValues.iterator();
        while (it.hasNext()) {
            Entity.NamedContentValues next = it.next();
            rawContact.addNamedDataItemValues(next.uri, next.values);
        }
        return rawContact;
    }

    public RawContact() {
        this(new ContentValues());
    }

    public RawContact(ContentValues contentValues) {
        this.mValues = contentValues;
        this.mDataItems = new ArrayList<>();
    }

    private RawContact(Parcel parcel) {
        this.mValues = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
        this.mDataItems = Lists.newArrayList();
        parcel.readTypedList(this.mDataItems, NamedDataItem.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mValues, i);
        parcel.writeTypedList(this.mDataItems);
    }

    public AccountTypeManager getAccountTypeManager(Context context) {
        if (this.mAccountTypeManager == null) {
            this.mAccountTypeManager = AccountTypeManager.getInstance(context);
        }
        return this.mAccountTypeManager;
    }

    public ContentValues getValues() {
        return this.mValues;
    }

    public Long getId() {
        return getValues().getAsLong("_id");
    }

    public String getAccountName() {
        return getValues().getAsString("account_name");
    }

    public String getAccountTypeString() {
        return getValues().getAsString("account_type");
    }

    public String getDataSet() {
        return getValues().getAsString("data_set");
    }

    public boolean isDirty() {
        return getValues().getAsBoolean("dirty").booleanValue();
    }

    public String getSourceId() {
        return getValues().getAsString("sourceid");
    }

    public String getSync1() {
        return getValues().getAsString("sync1");
    }

    public String getSync2() {
        return getValues().getAsString("sync2");
    }

    public String getSync3() {
        return getValues().getAsString("sync3");
    }

    public String getSync4() {
        return getValues().getAsString("sync4");
    }

    public boolean isDeleted() {
        return getValues().getAsBoolean("deleted").booleanValue();
    }

    public long getContactId() {
        return getValues().getAsLong("contact_id").longValue();
    }

    public boolean isStarred() {
        return getValues().getAsBoolean(ContactSaveService.EXTRA_STARRED_FLAG).booleanValue();
    }

    public AccountType getAccountType(Context context) {
        return getAccountTypeManager(context).getAccountType(getAccountTypeString(), getDataSet());
    }

    private void setAccount(String str, String str2, String str3) {
        ContentValues values = getValues();
        if (str == null) {
            if (str2 == null && str3 == null) {
                values.putNull("account_name");
                values.putNull("account_type");
                values.putNull("data_set");
                return;
            }
        } else if (str2 != null) {
            values.put("account_name", str);
            values.put("account_type", str2);
            if (str3 == null) {
                values.putNull("data_set");
                return;
            } else {
                values.put("data_set", str3);
                return;
            }
        }
        throw new IllegalArgumentException("Not a valid combination of account name, type, and data set.");
    }

    public void setAccount(AccountWithDataSet accountWithDataSet) {
        if (accountWithDataSet != null) {
            setAccount(accountWithDataSet.name, accountWithDataSet.type, accountWithDataSet.dataSet);
        } else {
            setAccount((String) null, (String) null, (String) null);
        }
    }

    public void setAccountToLocal() {
        setAccount((String) null, (String) null, (String) null);
    }

    public void addDataItemValues(ContentValues contentValues) {
        addNamedDataItemValues(ContactsContract.Data.CONTENT_URI, contentValues);
    }

    public NamedDataItem addNamedDataItemValues(Uri uri, ContentValues contentValues) {
        NamedDataItem namedDataItem = new NamedDataItem(uri, contentValues);
        this.mDataItems.add(namedDataItem);
        return namedDataItem;
    }

    public ArrayList<ContentValues> getContentValues() {
        ArrayList<ContentValues> newArrayListWithCapacity = Lists.newArrayListWithCapacity(this.mDataItems.size());
        Iterator<NamedDataItem> it = this.mDataItems.iterator();
        while (it.hasNext()) {
            NamedDataItem next = it.next();
            if (ContactsContract.Data.CONTENT_URI.equals(next.mUri)) {
                newArrayListWithCapacity.add(next.mContentValues);
            }
        }
        return newArrayListWithCapacity;
    }

    public List<DataItem> getDataItems() {
        ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(this.mDataItems.size());
        Iterator<NamedDataItem> it = this.mDataItems.iterator();
        while (it.hasNext()) {
            NamedDataItem next = it.next();
            if (ContactsContract.Data.CONTENT_URI.equals(next.mUri)) {
                newArrayListWithCapacity.add(DataItem.createFrom(next.mContentValues));
            }
        }
        return newArrayListWithCapacity;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RawContact: ");
        sb.append(this.mValues);
        Iterator<NamedDataItem> it = this.mDataItems.iterator();
        while (it.hasNext()) {
            NamedDataItem next = it.next();
            sb.append("\n  ");
            sb.append(next.mUri);
            sb.append("\n  -> ");
            sb.append(next.mContentValues);
        }
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.mValues, this.mDataItems);
    }

    public boolean equals(Object obj) {
        if (obj == null || RawContact.class != obj.getClass()) {
            return false;
        }
        RawContact rawContact = (RawContact) obj;
        if (!Objects.equal(this.mValues, rawContact.mValues) || !Objects.equal(this.mDataItems, rawContact.mDataItems)) {
            return false;
        }
        return true;
    }
}
