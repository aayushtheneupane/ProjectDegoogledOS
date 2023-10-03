package com.android.contacts.group;

public final class GroupListItem {
    private final String mAccountName;
    private final String mAccountType;
    private final String mDataSet;
    private final long mGroupId;
    private final boolean mIsFirstGroupInAccount;
    private final boolean mIsReadOnly;
    private final int mMemberCount;
    private final String mSystemId;
    private final String mTitle;

    public GroupListItem(String str, String str2, String str3, long j, String str4, boolean z, int i, boolean z2, String str5) {
        this.mAccountName = str;
        this.mAccountType = str2;
        this.mDataSet = str3;
        this.mGroupId = j;
        this.mTitle = str4;
        this.mIsFirstGroupInAccount = z;
        this.mMemberCount = i;
        this.mIsReadOnly = z2;
        this.mSystemId = str5;
    }

    public long getGroupId() {
        return this.mGroupId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getMemberCount() {
        return this.mMemberCount;
    }

    public boolean isReadOnly() {
        return this.mIsReadOnly;
    }

    public String getSystemId() {
        return this.mSystemId;
    }
}
