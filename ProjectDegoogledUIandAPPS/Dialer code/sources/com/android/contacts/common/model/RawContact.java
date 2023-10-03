package com.android.contacts.common.model;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import com.android.contacts.common.model.account.AccountType;
import com.android.contacts.common.model.dataitem.DataItem;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public final class RawContact implements Parcelable {
    public static final Parcelable.Creator<RawContact> CREATOR = new Parcelable.Creator<RawContact>() {
        public Object createFromParcel(Parcel parcel) {
            return new RawContact(parcel, (C02631) null);
        }

        public Object[] newArray(int i) {
            return new RawContact[i];
        }
    };
    private AccountTypeManager mAccountTypeManager;
    private final ArrayList<NamedDataItem> mDataItems;
    private final ContentValues mValues;

    public RawContact() {
        this.mValues = new ContentValues();
        this.mDataItems = new ArrayList<>();
    }

    public void addDataItemValues(ContentValues contentValues) {
        addNamedDataItemValues(ContactsContract.Data.CONTENT_URI, contentValues);
    }

    public NamedDataItem addNamedDataItemValues(Uri uri, ContentValues contentValues) {
        NamedDataItem namedDataItem = new NamedDataItem(uri, contentValues);
        this.mDataItems.add(namedDataItem);
        return namedDataItem;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null || RawContact.class != obj.getClass()) {
            return false;
        }
        RawContact rawContact = (RawContact) obj;
        if (!Objects.equals(this.mValues, rawContact.mValues) || !Objects.equals(this.mDataItems, rawContact.mDataItems)) {
            return false;
        }
        return true;
    }

    public String getAccountName() {
        return getValues().getAsString("account_name");
    }

    public AccountType getAccountType(Context context) {
        return getAccountTypeManager(context).getAccountType(getAccountTypeString(), getDataSet());
    }

    public AccountTypeManager getAccountTypeManager(Context context) {
        if (this.mAccountTypeManager == null) {
            this.mAccountTypeManager = AccountTypeManager.getInstance(context);
        }
        return this.mAccountTypeManager;
    }

    public String getAccountTypeString() {
        return getValues().getAsString("account_type");
    }

    public ArrayList<ContentValues> getContentValues() {
        ArrayList<ContentValues> arrayList = new ArrayList<>(this.mDataItems.size());
        Iterator<NamedDataItem> it = this.mDataItems.iterator();
        while (it.hasNext()) {
            NamedDataItem next = it.next();
            if (ContactsContract.Data.CONTENT_URI.equals(next.mUri)) {
                arrayList.add(next.mContentValues);
            }
        }
        return arrayList;
    }

    public List<DataItem> getDataItems() {
        ArrayList arrayList = new ArrayList(this.mDataItems.size());
        Iterator<NamedDataItem> it = this.mDataItems.iterator();
        while (it.hasNext()) {
            NamedDataItem next = it.next();
            if (ContactsContract.Data.CONTENT_URI.equals(next.mUri)) {
                arrayList.add(DataItem.createFrom(next.mContentValues));
            }
        }
        return arrayList;
    }

    public String getDataSet() {
        return getValues().getAsString("data_set");
    }

    public Long getId() {
        return getValues().getAsLong("_id");
    }

    public ContentValues getValues() {
        return this.mValues;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mValues, this.mDataItems});
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("RawContact: ");
        outline13.append(this.mValues);
        Iterator<NamedDataItem> it = this.mDataItems.iterator();
        while (it.hasNext()) {
            NamedDataItem next = it.next();
            outline13.append("\n  ");
            outline13.append(next.mUri);
            outline13.append("\n  -> ");
            outline13.append(next.mContentValues);
        }
        return outline13.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mValues, i);
        parcel.writeTypedList(this.mDataItems);
    }

    public static final class NamedDataItem implements Parcelable {
        public static final Parcelable.Creator<NamedDataItem> CREATOR = new Parcelable.Creator<NamedDataItem>() {
            public Object createFromParcel(Parcel parcel) {
                return new NamedDataItem(parcel);
            }

            public Object[] newArray(int i) {
                return new NamedDataItem[i];
            }
        };
        public final ContentValues mContentValues;
        public final Uri mUri;

        public NamedDataItem(Uri uri, ContentValues contentValues) {
            this.mUri = uri;
            this.mContentValues = contentValues;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (obj == null || NamedDataItem.class != obj.getClass()) {
                return false;
            }
            NamedDataItem namedDataItem = (NamedDataItem) obj;
            if (!Objects.equals(this.mUri, namedDataItem.mUri) || !Objects.equals(this.mContentValues, namedDataItem.mContentValues)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.mUri, this.mContentValues});
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mUri, i);
            parcel.writeParcelable(this.mContentValues, i);
        }

        public NamedDataItem(Parcel parcel) {
            this.mUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
            this.mContentValues = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
        }
    }

    public RawContact(ContentValues contentValues) {
        this.mValues = contentValues;
        this.mDataItems = new ArrayList<>();
    }

    /* synthetic */ RawContact(Parcel parcel, C02631 r2) {
        this.mValues = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
        this.mDataItems = new ArrayList<>();
        parcel.readTypedList(this.mDataItems, NamedDataItem.CREATOR);
    }
}
