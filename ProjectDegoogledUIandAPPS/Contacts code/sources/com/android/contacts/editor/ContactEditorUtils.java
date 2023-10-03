package com.android.contacts.editor;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.contacts.ContactSaveService;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.preference.ContactsPreferences;
import java.util.List;

public class ContactEditorUtils {
    private static final String TAG = "ContactEditorUtils";
    private final ContactsPreferences mContactsPrefs;

    private ContactEditorUtils(Context context) {
        this.mContactsPrefs = new ContactsPreferences(context);
    }

    public static ContactEditorUtils create(Context context) {
        return new ContactEditorUtils(context.getApplicationContext());
    }

    static Uri maybeConvertToLegacyLookupUri(Context context, Uri uri, Uri uri2) {
        if (!"contacts".equals(uri2 == null ? null : uri2.getAuthority())) {
            return uri;
        }
        return ContentUris.withAppendedId(Uri.parse("content://contacts/people"), ContentUris.parseId(ContactsContract.Contacts.lookupContact(context.getContentResolver(), uri)));
    }

    /* access modifiers changed from: package-private */
    public void cleanupForTest() {
        this.mContactsPrefs.clearDefaultAccount();
    }

    /* access modifiers changed from: package-private */
    public void removeDefaultAccountForTest() {
        this.mContactsPrefs.clearDefaultAccount();
    }

    public void saveDefaultAccount(AccountWithDataSet accountWithDataSet) {
        if (accountWithDataSet == null) {
            this.mContactsPrefs.clearDefaultAccount();
        } else {
            this.mContactsPrefs.setDefaultAccount(accountWithDataSet);
        }
    }

    public AccountWithDataSet getOnlyOrDefaultAccount(List<AccountWithDataSet> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        return this.mContactsPrefs.getDefaultAccount();
    }

    public boolean shouldShowAccountChangedNotification(List<AccountWithDataSet> list) {
        return this.mContactsPrefs.shouldShowAccountChangedNotification(list);
    }

    public void maybeUpdateDefaultAccount(List<AccountWithDataSet> list) {
        if (list.size() == 1) {
            AccountWithDataSet accountWithDataSet = list.get(0);
            if (!accountWithDataSet.isNullAccount() && !accountWithDataSet.equals(this.mContactsPrefs.getDefaultAccount())) {
                this.mContactsPrefs.setDefaultAccount(accountWithDataSet);
            }
        }
    }

    public AccountWithDataSet getCreatedAccount(int i, Intent intent) {
        if (intent == null) {
            return null;
        }
        String stringExtra = intent.getStringExtra(ContactSaveService.EXTRA_ACCOUNT_TYPE);
        String stringExtra2 = intent.getStringExtra("authAccount");
        if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2)) {
            return null;
        }
        return new AccountWithDataSet(stringExtra2, stringExtra, (String) null);
    }
}
