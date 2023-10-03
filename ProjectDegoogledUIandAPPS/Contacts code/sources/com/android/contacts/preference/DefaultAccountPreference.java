package com.android.contacts.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import com.android.contacts.model.account.AccountInfo;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.util.AccountsListAdapter;
import java.util.List;

public class DefaultAccountPreference extends DialogPreference {
    private List<AccountInfo> mAccounts;
    /* access modifiers changed from: private */
    public int mChosenIndex = -1;
    private AccountsListAdapter mListAdapter;
    private ContactsPreferences mPreferences;

    /* access modifiers changed from: protected */
    public boolean shouldPersist() {
        return false;
    }

    public DefaultAccountPreference(Context context) {
        super(context);
        prepare();
    }

    public DefaultAccountPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        prepare();
    }

    public void setAccounts(List<AccountInfo> list) {
        this.mAccounts = list;
        AccountsListAdapter accountsListAdapter = this.mListAdapter;
        if (accountsListAdapter != null) {
            accountsListAdapter.setAccounts(list, (AccountWithDataSet) null);
            notifyChanged();
        }
    }

    /* access modifiers changed from: protected */
    public View onCreateDialogView() {
        prepare();
        return super.onCreateDialogView();
    }

    private void prepare() {
        this.mPreferences = new ContactsPreferences(getContext());
        this.mListAdapter = new AccountsListAdapter(getContext());
        List<AccountInfo> list = this.mAccounts;
        if (list != null) {
            this.mListAdapter.setAccounts(list, (AccountWithDataSet) null);
        }
    }

    public CharSequence getSummary() {
        List<AccountInfo> list;
        AccountWithDataSet defaultAccount = this.mPreferences.getDefaultAccount();
        if (defaultAccount == null || (list = this.mAccounts) == null || !AccountInfo.contains(list, defaultAccount)) {
            return null;
        }
        return AccountInfo.getAccount(this.mAccounts, defaultAccount).getNameLabel();
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        super.onPrepareDialogBuilder(builder);
        builder.setNegativeButton((CharSequence) null, (DialogInterface.OnClickListener) null);
        builder.setPositiveButton((CharSequence) null, (DialogInterface.OnClickListener) null);
        builder.setAdapter(this.mListAdapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                int unused = DefaultAccountPreference.this.mChosenIndex = i;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDialogClosed(boolean z) {
        AccountWithDataSet defaultAccount = this.mPreferences.getDefaultAccount();
        int i = this.mChosenIndex;
        if (i != -1) {
            AccountWithDataSet item = this.mListAdapter.getItem(i);
            if (!item.equals(defaultAccount)) {
                this.mPreferences.setDefaultAccount(item);
                notifyChanged();
            }
        }
    }
}
