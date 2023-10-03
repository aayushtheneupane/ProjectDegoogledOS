package com.android.contacts.editor;

import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import com.android.contacts.model.AccountTypeManager;
import java.util.ArrayList;
import java.util.HashMap;

public class PickRawContactLoader extends AsyncTaskLoader<RawContactsMetadata> {
    private static final String[] RAW_CONTACT_PROJECTION = {"account_name", "account_type", "data_set", "_id", "display_name", "display_name_alt"};
    private RawContactsMetadata mCachedResult;
    private Uri mContactUri;

    public PickRawContactLoader(Context context, Uri uri) {
        super(context);
        ensureIsContactUri(uri);
        this.mContactUri = uri;
    }

    /* JADX INFO: finally extract failed */
    public RawContactsMetadata loadInBackground() {
        Uri uri;
        Uri uri2;
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor query = contentResolver.query(this.mContactUri, new String[]{"_id", "is_user_profile"}, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return null;
        }
        if (query.getCount() < 1) {
            query.close();
            return null;
        }
        RawContactsMetadata rawContactsMetadata = new RawContactsMetadata();
        try {
            query.moveToFirst();
            rawContactsMetadata.contactId = query.getLong(0);
            rawContactsMetadata.isUserProfile = query.getInt(1) == 1;
            query.close();
            if (rawContactsMetadata.isUserProfile) {
                uri = ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI;
            } else {
                uri = ContactsContract.RawContacts.CONTENT_URI;
            }
            Cursor query2 = contentResolver.query(uri, RAW_CONTACT_PROJECTION, "contact_id=?", new String[]{Long.toString(rawContactsMetadata.contactId)}, (String) null);
            if (query2 == null) {
                return null;
            }
            if (query2.getCount() < 1) {
                query2.close();
                return null;
            }
            query2.moveToPosition(-1);
            StringBuilder sb = new StringBuilder("raw_contact_id IN (");
            HashMap hashMap = new HashMap();
            while (query2.moveToNext()) {
                try {
                    RawContact rawContact = new RawContact();
                    rawContact.f10id = query2.getLong(3);
                    sb.append(rawContact.f10id);
                    sb.append(',');
                    rawContact.displayName = query2.getString(4);
                    rawContact.displayNameAlt = query2.getString(5);
                    rawContact.accountName = query2.getString(0);
                    rawContact.accountType = query2.getString(1);
                    rawContact.accountDataSet = query2.getString(2);
                    rawContactsMetadata.rawContacts.add(rawContact);
                    hashMap.put(Long.valueOf(rawContact.f10id), rawContact);
                } catch (Throwable th) {
                    query2.close();
                    throw th;
                }
            }
            query2.close();
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(") AND mimetype=\"vnd.android.cursor.item/photo\"");
            if (rawContactsMetadata.isUserProfile) {
                uri2 = Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, ContactsContract.Data.CONTENT_URI.getPath());
            } else {
                uri2 = ContactsContract.Data.CONTENT_URI;
            }
            Cursor query3 = contentResolver.query(uri2, new String[]{"raw_contact_id", "_id"}, sb.toString(), (String[]) null, (String) null);
            if (query3 != null) {
                try {
                    query3.moveToPosition(-1);
                    while (query3.moveToNext()) {
                        ((RawContact) hashMap.get(Long.valueOf(query3.getLong(0)))).photoId = query3.getLong(1);
                    }
                } finally {
                    query3.close();
                }
            }
            return rawContactsMetadata;
        } catch (Throwable th2) {
            query.close();
            throw th2;
        }
    }

    public void deliverResult(RawContactsMetadata rawContactsMetadata) {
        this.mCachedResult = rawContactsMetadata;
        if (isStarted()) {
            super.deliverResult(rawContactsMetadata);
        }
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        super.onStartLoading();
        RawContactsMetadata rawContactsMetadata = this.mCachedResult;
        if (rawContactsMetadata == null) {
            forceLoad();
        } else {
            deliverResult(rawContactsMetadata);
        }
    }

    private static Uri ensureIsContactUri(Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("Uri must not be null");
        } else if (uri.toString().startsWith(ContactsContract.Contacts.CONTENT_URI.toString()) || uri.toString().equals(ContactsContract.Profile.CONTENT_URI.toString())) {
            return uri;
        } else {
            throw new IllegalArgumentException("Invalid contact Uri: " + uri);
        }
    }

    public static class RawContactsMetadata implements Parcelable {
        public static final Parcelable.Creator<RawContactsMetadata> CREATOR = new Parcelable.Creator<RawContactsMetadata>() {
            public RawContactsMetadata createFromParcel(Parcel parcel) {
                return new RawContactsMetadata(parcel);
            }

            public RawContactsMetadata[] newArray(int i) {
                return new RawContactsMetadata[i];
            }
        };
        public long contactId;
        public boolean isUserProfile;
        public ArrayList<RawContact> rawContacts;
        public boolean showReadOnly;

        public int describeContents() {
            return 0;
        }

        public RawContactsMetadata() {
            this.showReadOnly = false;
            this.rawContacts = new ArrayList<>();
        }

        private RawContactsMetadata(Parcel parcel) {
            boolean z = false;
            this.showReadOnly = false;
            this.rawContacts = new ArrayList<>();
            this.contactId = parcel.readLong();
            this.isUserProfile = parcel.readInt() == 1;
            this.showReadOnly = parcel.readInt() == 1 ? true : z;
            parcel.readTypedList(this.rawContacts, RawContact.CREATOR);
        }

        public void trimReadOnly(AccountTypeManager accountTypeManager) {
            for (int size = this.rawContacts.size() - 1; size >= 0; size--) {
                RawContact rawContact = this.rawContacts.get(size);
                if (!accountTypeManager.getAccountType(rawContact.accountType, rawContact.accountDataSet).areContactsWritable()) {
                    this.rawContacts.remove(size);
                }
            }
        }

        public int getIndexOfFirstWritableAccount(AccountTypeManager accountTypeManager) {
            for (int i = 0; i < this.rawContacts.size(); i++) {
                RawContact rawContact = this.rawContacts.get(i);
                if (accountTypeManager.getAccountType(rawContact.accountType, rawContact.accountDataSet).areContactsWritable()) {
                    return i;
                }
            }
            return -1;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.contactId);
            parcel.writeInt(this.isUserProfile ? 1 : 0);
            parcel.writeInt(this.showReadOnly ? 1 : 0);
            parcel.writeTypedList(this.rawContacts);
        }
    }

    public static class RawContact implements Parcelable {
        public static final Parcelable.Creator<RawContact> CREATOR = new Parcelable.Creator<RawContact>() {
            public RawContact createFromParcel(Parcel parcel) {
                return new RawContact(parcel);
            }

            public RawContact[] newArray(int i) {
                return new RawContact[i];
            }
        };
        public String accountDataSet;
        public String accountName;
        public String accountType;
        public String displayName;
        public String displayNameAlt;

        /* renamed from: id */
        public long f10id;
        public long photoId;

        public int describeContents() {
            return 0;
        }

        public RawContact() {
        }

        private RawContact(Parcel parcel) {
            this.f10id = parcel.readLong();
            this.photoId = parcel.readLong();
            this.displayName = parcel.readString();
            this.displayNameAlt = parcel.readString();
            this.accountName = parcel.readString();
            this.accountType = parcel.readString();
            this.accountDataSet = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.f10id);
            parcel.writeLong(this.photoId);
            parcel.writeString(this.displayName);
            parcel.writeString(this.displayNameAlt);
            parcel.writeString(this.accountName);
            parcel.writeString(this.accountType);
            parcel.writeString(this.accountDataSet);
        }
    }
}
