package com.android.contacts.common.model;

import android.content.ContentValues;
import android.net.Uri;
import com.android.contacts.common.GroupMetaData;
import com.android.contacts.common.model.account.AccountType;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableList;
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
    private ImmutableList<AccountType> mInvitableAccountTypes;
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
        this.mNameRawContactId = -1;
        this.mDisplayNameSource = 0;
        this.mPhotoId = -1;
        this.mPhotoUri = null;
        this.mDisplayName = null;
        this.mAltDisplayName = null;
        this.mPhoneticName = null;
        this.mStarred = false;
        this.mPresence = null;
        this.mInvitableAccountTypes = null;
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

    public long getDirectoryId() {
        return this.mDirectoryId;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public int getDisplayNameSource() {
        return this.mDisplayNameSource;
    }

    public ImmutableList<GroupMetaData> getGroupMetaData() {
        return this.mGroups;
    }

    public long getId() {
        return this.mId;
    }

    public ImmutableList<AccountType> getInvitableAccountTypes() {
        return this.mInvitableAccountTypes;
    }

    public Uri getLookupUri() {
        return this.mLookupUri;
    }

    public long getPhotoId() {
        return this.mPhotoId;
    }

    public String getPhotoUri() {
        return this.mPhotoUri;
    }

    public ImmutableList<RawContact> getRawContacts() {
        return this.mRawContacts;
    }

    public byte[] getThumbnailPhotoBinaryData() {
        return this.mThumbnailPhotoBinaryData;
    }

    public boolean isDirectoryEntry() {
        long j = this.mDirectoryId;
        return (j == -1 || j == 0 || j == 1) ? false : true;
    }

    public boolean isLoaded() {
        return this.mStatus == Status.LOADED;
    }

    public boolean isUserProfile() {
        return this.mIsUserProfile;
    }

    public void setDirectoryMetaData(String str, String str2, String str3, String str4, int i) {
        this.mDirectoryDisplayName = str;
        this.mDirectoryType = str2;
        this.mDirectoryAccountType = str3;
        this.mDirectoryAccountName = str4;
        this.mDirectoryExportSupport = i;
    }

    /* access modifiers changed from: package-private */
    public void setGroupMetaData(ImmutableList<GroupMetaData> immutableList) {
        this.mGroups = immutableList;
    }

    /* access modifiers changed from: package-private */
    public void setInvitableAccountTypes(ImmutableList<AccountType> immutableList) {
        this.mInvitableAccountTypes = immutableList;
    }

    /* access modifiers changed from: package-private */
    public void setPhotoBinaryData(byte[] bArr) {
        this.mPhotoBinaryData = bArr;
    }

    /* access modifiers changed from: package-private */
    public void setRawContacts(ImmutableList<RawContact> immutableList) {
        this.mRawContacts = immutableList;
    }

    /* access modifiers changed from: package-private */
    public void setThumbnailPhotoBinaryData(byte[] bArr) {
        this.mThumbnailPhotoBinaryData = bArr;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("{requested=");
        outline13.append(this.mRequestedUri);
        outline13.append(",lookupkey=");
        outline13.append(this.mLookupKey);
        outline13.append(",uri=");
        outline13.append(this.mUri);
        outline13.append(",status=");
        return GeneratedOutlineSupport.outline11(outline13, this.mStatus, "}");
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
        this.mNameRawContactId = j3;
        this.mDisplayNameSource = i;
        this.mPhotoId = j4;
        this.mPhotoUri = str2;
        this.mDisplayName = str3;
        this.mAltDisplayName = str4;
        this.mPhoneticName = str5;
        this.mStarred = z;
        this.mPresence = num;
        this.mInvitableAccountTypes = null;
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
        this.mInvitableAccountTypes = contact.mInvitableAccountTypes;
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
}
