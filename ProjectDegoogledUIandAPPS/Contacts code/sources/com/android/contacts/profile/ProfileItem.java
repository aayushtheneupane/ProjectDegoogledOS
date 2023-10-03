package com.android.contacts.profile;

public final class ProfileItem {
    private final long mContactId;
    private final String mDisplayName;
    private final int mDisplayNameSource;
    private final boolean mHasProfile;
    private final long mPhotoId;
    private final String mPhotoUri;

    public ProfileItem(String str, long j, long j2, String str2, int i, boolean z) {
        this.mDisplayName = str;
        this.mContactId = j;
        this.mPhotoId = j2;
        this.mPhotoUri = str2;
        this.mDisplayNameSource = i;
        this.mHasProfile = z;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public long getContactId() {
        return this.mContactId;
    }

    public long getPhotoId() {
        return this.mPhotoId;
    }

    public String getPhotoUri() {
        return this.mPhotoUri;
    }

    public int getDisplayNameSource() {
        return this.mDisplayNameSource;
    }

    public boolean HasProfile() {
        return this.mHasProfile;
    }
}
