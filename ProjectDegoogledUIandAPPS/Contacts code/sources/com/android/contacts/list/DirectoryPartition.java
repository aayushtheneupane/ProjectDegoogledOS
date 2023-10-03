package com.android.contacts.list;

import com.android.common.widget.CompositeCursorAdapter;

public final class DirectoryPartition extends CompositeCursorAdapter.Partition {
    private String mContentUri;
    private long mDirectoryId;
    private String mDirectoryType;
    private String mDisplayName;
    private boolean mDisplayNumber = true;
    private String mLabel;
    private boolean mPhotoSupported;
    private boolean mPriorityDirectory;
    private int mResultLimit = -1;
    private int mStatus;

    public DirectoryPartition(boolean z, boolean z2) {
        super(z, z2);
    }

    public long getDirectoryId() {
        return this.mDirectoryId;
    }

    public void setDirectoryId(long j) {
        this.mDirectoryId = j;
    }

    public String getDirectoryType() {
        return this.mDirectoryType;
    }

    public void setDirectoryType(String str) {
        this.mDirectoryType = str;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public boolean isLoading() {
        int i = this.mStatus;
        return i == 0 || i == 1;
    }

    public boolean isPriorityDirectory() {
        return this.mPriorityDirectory;
    }

    public void setPriorityDirectory(boolean z) {
        this.mPriorityDirectory = z;
    }

    public boolean isPhotoSupported() {
        return this.mPhotoSupported;
    }

    public void setPhotoSupported(boolean z) {
        this.mPhotoSupported = z;
    }

    public int getResultLimit() {
        return this.mResultLimit;
    }

    public String getContentUri() {
        return this.mContentUri;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public String toString() {
        return "DirectoryPartition{mDirectoryId=" + this.mDirectoryId + ", mContentUri='" + this.mContentUri + '\'' + ", mDirectoryType='" + this.mDirectoryType + '\'' + ", mDisplayName='" + this.mDisplayName + '\'' + ", mStatus=" + this.mStatus + ", mPriorityDirectory=" + this.mPriorityDirectory + ", mPhotoSupported=" + this.mPhotoSupported + ", mResultLimit=" + this.mResultLimit + ", mLabel='" + this.mLabel + '\'' + '}';
    }

    public boolean isDisplayNumber() {
        return this.mDisplayNumber;
    }
}
