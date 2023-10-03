package com.android.contacts.list;

import android.net.Uri;
import com.android.contacts.model.account.AccountWithDataSet;
import java.util.ArrayList;

public class ContactsRequest {
    private AccountWithDataSet mAccountWithDataSet;
    private int mActionCode = 10;
    private Uri mContactUri;
    private boolean mDirectorySearchEnabled = true;
    private boolean mIncludeFavorites;
    private boolean mLegacyCompatibilityMode;
    private String mQueryString;
    private ArrayList<String> mRawContactIds;
    private boolean mSearchMode;
    private CharSequence mTitle;
    private boolean mValid = true;

    public String toString() {
        return "{ContactsRequest:mValid=" + this.mValid + " mActionCode=" + this.mActionCode + " mTitle=" + this.mTitle + " mSearchMode=" + this.mSearchMode + " mQueryString=" + this.mQueryString + " mIncludeFavorites=" + this.mIncludeFavorites + " mLegacyCompatibilityMode=" + this.mLegacyCompatibilityMode + " mDirectorySearchEnabled=" + this.mDirectorySearchEnabled + " mContactUri=" + this.mContactUri + " mAccountWithDataSet=" + this.mAccountWithDataSet + " mRawContactIds=" + this.mRawContactIds + "}";
    }

    public boolean isValid() {
        return this.mValid;
    }

    public void setActivityTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
    }

    public CharSequence getActivityTitle() {
        return this.mTitle;
    }

    public int getActionCode() {
        return this.mActionCode;
    }

    public void setActionCode(int i) {
        this.mActionCode = i;
    }

    public boolean isSearchMode() {
        return this.mSearchMode;
    }

    public void setSearchMode(boolean z) {
        this.mSearchMode = z;
    }

    public String getQueryString() {
        return this.mQueryString;
    }

    public void setQueryString(String str) {
        this.mQueryString = str;
    }

    public boolean shouldIncludeFavorites() {
        return this.mIncludeFavorites;
    }

    public boolean isLegacyCompatibilityMode() {
        return this.mLegacyCompatibilityMode;
    }

    public void setLegacyCompatibilityMode(boolean z) {
        this.mLegacyCompatibilityMode = z;
    }

    public boolean isDirectorySearchEnabled() {
        return this.mDirectorySearchEnabled;
    }

    public Uri getContactUri() {
        return this.mContactUri;
    }

    public void setContactUri(Uri uri) {
        this.mContactUri = uri;
    }

    public void setAccountWithDataSet(AccountWithDataSet accountWithDataSet) {
        this.mAccountWithDataSet = accountWithDataSet;
    }

    public void setRawContactIds(ArrayList<String> arrayList) {
        this.mRawContactIds = arrayList;
    }
}
