package com.android.contacts.vcard;

import android.accounts.Account;
import android.net.Uri;
import com.android.contacts.model.account.AccountWithDataSet;

public class ImportRequest {
    public final Account account;
    public final byte[] data;
    public final String displayName;
    public final int entryCount;
    public final String estimatedCharset;
    public final int estimatedVCardType;
    public final Uri uri;
    public final int vcardVersion;

    public ImportRequest(AccountWithDataSet accountWithDataSet, byte[] bArr, Uri uri2, String str, int i, String str2, int i2, int i3) {
        this.account = accountWithDataSet != null ? accountWithDataSet.getAccountOrNull() : null;
        this.data = bArr;
        this.uri = uri2;
        this.displayName = str;
        this.estimatedVCardType = i;
        this.estimatedCharset = str2;
        this.vcardVersion = i2;
        this.entryCount = i3;
    }
}
