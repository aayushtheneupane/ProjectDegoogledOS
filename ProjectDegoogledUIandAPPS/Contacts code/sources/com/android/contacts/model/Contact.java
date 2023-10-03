package com.android.contacts.model;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.android.contacts.group.GroupMetaData;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.SimAccountType;
import com.android.contacts.util.DataStatus;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;

public class Contact {
    private final String mAltDisplayName;
    private final String mCustomRingtone;
    private String mDirectoryAccountName;
    private String mDirectoryAccountType;
    private String mDirectoryDisplayName;
    private int mDirectoryExportSupport;
    private final long mDirectoryId;
    private String mDirectoryType;
    private final String mDisplayName;
    private final int mDisplayNameSource;
    private final Exception mException;
    private ImmutableList<GroupMetaData> mGroups;
    private final long mId;
    private final boolean mIsUserProfile;
    private final String mLookupKey;
    private final Uri mLookupUri;
    private final long mNameRawContactId;
    private final String mPhoneticName;
    private byte[] mPhotoBinaryData;
    private final long mPhotoId;
    private final String mPhotoUri;
    private final Integer mPresence;
    private ImmutableList<RawContact> mRawContacts;
    private final Uri mRequestedUri;
    private final boolean mSendToVoicemail;
    private final boolean mStarred;
    private final Status mStatus;
    private ImmutableMap<Long, DataStatus> mStatuses;
    private byte[] mThumbnailPhotoBinaryData;
    private final Uri mUri;

    private enum Status {
        LOADED,
        ERROR,
        NOT_FOUND
    }

    private Contact(Uri uri, Status status, Exception exc) {
        if (status == Status.ERROR && exc == null) {
            throw new IllegalArgumentException("ERROR result must have exception");
        }
        this.mStatus = status;
        this.mException = exc;
        this.mRequestedUri = uri;
        this.mLookupUri = null;
        this.mUri = null;
        this.mDirectoryId = -1;
        this.mLookupKey = null;
        this.mId = -1;
        this.mRawContacts = null;
        this.mStatuses = null;
        this.mNameRawContactId = -1;
        this.mDisplayNameSource = 0;
        this.mPhotoId = -1;
        this.mPhotoUri = null;
        this.mDisplayName = null;
        this.mAltDisplayName = null;
        this.mPhoneticName = null;
        this.mStarred = false;
        this.mPresence = null;
        this.mSendToVoicemail = false;
        this.mCustomRingtone = null;
        this.mIsUserProfile = false;
    }

    public static Contact forError(Uri uri, Exception exc) {
        return new Contact(uri, Status.ERROR, exc);
    }

    public static Contact forNotFound(Uri uri) {
        return new Contact(uri, Status.NOT_FOUND, (Exception) null);
    }

    public Contact(Uri uri, Uri uri2, Uri uri3, long j, String str, long j2, long j3, int i, long j4, String str2, String str3, String str4, String str5, boolean z, Integer num, boolean z2, String str6, boolean z3) {
        this.mStatus = Status.LOADED;
        this.mException = null;
        this.mRequestedUri = uri;
        this.mLookupUri = uri3;
        this.mUri = uri2;
        this.mDirectoryId = j;
        this.mLookupKey = str;
        this.mId = j2;
        this.mRawContacts = null;
        this.mStatuses = null;
        this.mNameRawContactId = j3;
        this.mDisplayNameSource = i;
        this.mPhotoId = j4;
        this.mPhotoUri = str2;
        this.mDisplayName = str3;
        this.mAltDisplayName = str4;
        this.mPhoneticName = str5;
        this.mStarred = z;
        this.mPresence = num;
        this.mSendToVoicemail = z2;
        this.mCustomRingtone = str6;
        this.mIsUserProfile = z3;
    }

    public Contact(Uri uri, Contact contact) {
        this.mRequestedUri = uri;
        this.mStatus = contact.mStatus;
        this.mException = contact.mException;
        this.mLookupUri = contact.mLookupUri;
        this.mUri = contact.mUri;
        this.mDirectoryId = contact.mDirectoryId;
        this.mLookupKey = contact.mLookupKey;
        this.mId = contact.mId;
        this.mNameRawContactId = contact.mNameRawContactId;
        this.mDisplayNameSource = contact.mDisplayNameSource;
        this.mPhotoId = contact.mPhotoId;
        this.mPhotoUri = contact.mPhotoUri;
        this.mDisplayName = contact.mDisplayName;
        this.mAltDisplayName = contact.mAltDisplayName;
        this.mPhoneticName = contact.mPhoneticName;
        this.mStarred = contact.mStarred;
        this.mPresence = contact.mPresence;
        this.mRawContacts = contact.mRawContacts;
        this.mStatuses = contact.mStatuses;
        this.mDirectoryDisplayName = contact.mDirectoryDisplayName;
        this.mDirectoryType = contact.mDirectoryType;
        this.mDirectoryAccountType = contact.mDirectoryAccountType;
        this.mDirectoryAccountName = contact.mDirectoryAccountName;
        this.mDirectoryExportSupport = contact.mDirectoryExportSupport;
        this.mGroups = contact.mGroups;
        this.mPhotoBinaryData = contact.mPhotoBinaryData;
        this.mSendToVoicemail = contact.mSendToVoicemail;
        this.mCustomRingtone = contact.mCustomRingtone;
        this.mIsUserProfile = contact.mIsUserProfile;
    }

    public void setDirectoryMetaData(String str, String str2, String str3, String str4, int i) {
        this.mDirectoryDisplayName = str;
        this.mDirectoryType = str2;
        this.mDirectoryAccountType = str3;
        this.mDirectoryAccountName = str4;
        this.mDirectoryExportSupport = i;
    }

    /* access modifiers changed from: package-private */
    public void setPhotoBinaryData(byte[] bArr) {
        this.mPhotoBinaryData = bArr;
    }

    /* access modifiers changed from: package-private */
    public void setThumbnailPhotoBinaryData(byte[] bArr) {
        this.mThumbnailPhotoBinaryData = bArr;
    }

    public Uri getLookupUri() {
        return this.mLookupUri;
    }

    public String getLookupKey() {
        return this.mLookupKey;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public Uri getRequestedUri() {
        return this.mRequestedUri;
    }

    public RawContactDeltaList createRawContactDeltaList() {
        return RawContactDeltaList.fromIterator(getRawContacts().iterator());
    }

    public long getId() {
        return this.mId;
    }

    public boolean isError() {
        return this.mStatus == Status.ERROR;
    }

    public Exception getException() {
        return this.mException;
    }

    public boolean isNotFound() {
        return this.mStatus == Status.NOT_FOUND;
    }

    public boolean isLoaded() {
        return this.mStatus == Status.LOADED;
    }

    public long getNameRawContactId() {
        return this.mNameRawContactId;
    }

    public int getDisplayNameSource() {
        return this.mDisplayNameSource;
    }

    public boolean isDisplayNameFromOrganization() {
        return 30 == this.mDisplayNameSource;
    }

    public long getPhotoId() {
        return this.mPhotoId;
    }

    public String getPhotoUri() {
        return this.mPhotoUri;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getAltDisplayName() {
        return this.mAltDisplayName;
    }

    public String getPhoneticName() {
        return this.mPhoneticName;
    }

    public boolean getStarred() {
        return this.mStarred;
    }

    public Integer getPresence() {
        return this.mPresence;
    }

    public ImmutableList<RawContact> getRawContacts() {
        return this.mRawContacts;
    }

    public ImmutableMap<Long, DataStatus> getStatuses() {
        return this.mStatuses;
    }

    public long getDirectoryId() {
        return this.mDirectoryId;
    }

    public boolean isDirectoryEntry() {
        long j = this.mDirectoryId;
        return (j == -1 || j == 0 || j == 1) ? false : true;
    }

    public boolean isWritableContact(Context context) {
        return getFirstWritableRawContactId(context) != -1;
    }

    public long getFirstWritableRawContactId(Context context) {
        if (isDirectoryEntry()) {
            return -1;
        }
        UnmodifiableIterator<RawContact> it = getRawContacts().iterator();
        while (it.hasNext()) {
            RawContact next = it.next();
            AccountType accountType = next.getAccountType(context);
            if (accountType != null && accountType.areContactsWritable()) {
                return next.getId().longValue();
            }
        }
        return -1;
    }

    public int getDirectoryExportSupport() {
        return this.mDirectoryExportSupport;
    }

    public String getDirectoryDisplayName() {
        return this.mDirectoryDisplayName;
    }

    public String getDirectoryType() {
        return this.mDirectoryType;
    }

    public String getDirectoryAccountType() {
        return this.mDirectoryAccountType;
    }

    public String getDirectoryAccountName() {
        return this.mDirectoryAccountName;
    }

    public byte[] getPhotoBinaryData() {
        return this.mPhotoBinaryData;
    }

    public byte[] getThumbnailPhotoBinaryData() {
        return this.mThumbnailPhotoBinaryData;
    }

    public ArrayList<ContentValues> getContentValues() {
        if (this.mRawContacts.size() == 1) {
            ArrayList<ContentValues> contentValues = this.mRawContacts.get(0).getContentValues();
            if (this.mPhotoId == 0 && this.mPhotoBinaryData != null) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("mimetype", "vnd.android.cursor.item/photo");
                contentValues2.put("data15", this.mPhotoBinaryData);
                contentValues.add(contentValues2);
            }
            return contentValues;
        }
        throw new IllegalStateException("Cannot extract content values from an aggregated contact");
    }

    public ImmutableList<GroupMetaData> getGroupMetaData() {
        return this.mGroups;
    }

    public boolean isSendToVoicemail() {
        return this.mSendToVoicemail;
    }

    public String getCustomRingtone() {
        return this.mCustomRingtone;
    }

    public boolean isUserProfile() {
        return this.mIsUserProfile;
    }

    public boolean isMultipleRawContacts() {
        return this.mRawContacts.size() > 1;
    }

    public boolean areAllRawContactsSimAccounts(Context context) {
        if (getRawContacts() == null) {
            return false;
        }
        UnmodifiableIterator<RawContact> it = getRawContacts().iterator();
        while (it.hasNext()) {
            if (!(it.next().getAccountType(context) instanceof SimAccountType)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "{requested=" + this.mRequestedUri + ",lookupkey=" + this.mLookupKey + ",uri=" + this.mUri + ",status=" + this.mStatus + "}";
    }

    /* access modifiers changed from: package-private */
    public void setRawContacts(ImmutableList<RawContact> immutableList) {
        this.mRawContacts = immutableList;
    }

    /* access modifiers changed from: package-private */
    public void setStatuses(ImmutableMap<Long, DataStatus> immutableMap) {
        this.mStatuses = immutableMap;
    }

    /* access modifiers changed from: package-private */
    public void setGroupMetaData(ImmutableList<GroupMetaData> immutableList) {
        this.mGroups = immutableList;
    }
}
