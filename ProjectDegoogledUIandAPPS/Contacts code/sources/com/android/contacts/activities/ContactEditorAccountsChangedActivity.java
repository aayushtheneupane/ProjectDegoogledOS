package com.android.contacts.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.android.contacts.R;
import com.android.contacts.editor.ContactEditorUtils;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountInfo;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.model.account.AccountsLoader;
import com.android.contacts.util.AccountsListAdapter;
import com.android.contacts.util.ImplicitIntentsUtil;
import java.util.List;

public class ContactEditorAccountsChangedActivity extends Activity implements AccountsLoader.AccountsListener {
    /* access modifiers changed from: private */
    public AccountsListAdapter mAccountListAdapter;
    private final AdapterView.OnItemClickListener mAccountListItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (ContactEditorAccountsChangedActivity.this.mAccountListAdapter != null) {
                ContactEditorAccountsChangedActivity contactEditorAccountsChangedActivity = ContactEditorAccountsChangedActivity.this;
                contactEditorAccountsChangedActivity.saveAccountAndReturnResult(contactEditorAccountsChangedActivity.mAccountListAdapter.getItem(i));
            }
        }
    };
    private final View.OnClickListener mAddAccountClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            ContactEditorAccountsChangedActivity.this.startActivityForResult(ImplicitIntentsUtil.getIntentForAddingGoogleAccount(), 1);
        }
    };
    private AlertDialog mDialog;
    private ContactEditorUtils mEditorUtils;

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null && !alertDialog.isShowing()) {
            this.mDialog.show();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mEditorUtils = ContactEditorUtils.create(this);
        AccountsLoader.loadAccounts(this, 0, AccountTypeManager.writableFilter());
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            AccountWithDataSet createdAccount = this.mEditorUtils.getCreatedAccount(i2, intent);
            if (createdAccount == null) {
                setResult(i2);
                finish();
                return;
            }
            saveAccountAndReturnResult(createdAccount);
        }
    }

    private void updateDisplayedAccounts(List<AccountInfo> list) {
        View view;
        int size = list.size();
        if (size >= 0) {
            if (size >= 2) {
                view = View.inflate(this, R.layout.contact_editor_accounts_changed_activity_with_picker, (ViewGroup) null);
                ((TextView) view.findViewById(R.id.text)).setText(getString(R.string.contact_editor_prompt_multiple_accounts));
                Button button = (Button) view.findViewById(R.id.add_account_button);
                button.setText(getString(R.string.add_new_account));
                button.setOnClickListener(this.mAddAccountClickListener);
                ListView listView = (ListView) view.findViewById(R.id.account_list);
                this.mAccountListAdapter = new AccountsListAdapter(this, list);
                listView.setAdapter(this.mAccountListAdapter);
                listView.setOnItemClickListener(this.mAccountListItemClickListener);
            } else if (size != 1 || list.get(0).getAccount().isNullAccount()) {
                view = View.inflate(this, R.layout.contact_editor_accounts_changed_activity_with_text, (ViewGroup) null);
                Button button2 = (Button) view.findViewById(R.id.left_button);
                Button button3 = (Button) view.findViewById(R.id.right_button);
                ((TextView) view.findViewById(R.id.text)).setText(getString(R.string.contact_editor_prompt_zero_accounts));
                button2.setText(getString(17039360));
                button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ContactEditorAccountsChangedActivity.this.saveAccountAndReturnResult(AccountWithDataSet.getNullAccount());
                        ContactEditorAccountsChangedActivity.this.finish();
                    }
                });
                button3.setText(getString(R.string.add_account));
                button3.setOnClickListener(this.mAddAccountClickListener);
            } else {
                View inflate = View.inflate(this, R.layout.contact_editor_accounts_changed_activity_with_text, (ViewGroup) null);
                Button button4 = (Button) inflate.findViewById(R.id.left_button);
                Button button5 = (Button) inflate.findViewById(R.id.right_button);
                final AccountInfo accountInfo = list.get(0);
                ((TextView) inflate.findViewById(R.id.text)).setText(getString(R.string.contact_editor_prompt_one_account, new Object[]{accountInfo.getNameLabel()}));
                button4.setText(getString(R.string.add_new_account));
                button4.setOnClickListener(this.mAddAccountClickListener);
                button5.setText(getString(17039370));
                button5.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ContactEditorAccountsChangedActivity.this.saveAccountAndReturnResult(accountInfo.getAccount());
                    }
                });
                view = inflate;
            }
            AlertDialog alertDialog = this.mDialog;
            if (alertDialog != null && alertDialog.isShowing()) {
                this.mDialog.dismiss();
            }
            this.mDialog = new AlertDialog.Builder(this).setView(view).setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    ContactEditorAccountsChangedActivity.this.finish();
                }
            }).create();
            this.mDialog.show();
            return;
        }
        throw new IllegalStateException("Cannot have a negative number of accounts");
    }

    /* access modifiers changed from: private */
    public void saveAccountAndReturnResult(AccountWithDataSet accountWithDataSet) {
        this.mEditorUtils.saveDefaultAccount(accountWithDataSet);
        Intent intent = new Intent();
        intent.putExtra("android.provider.extra.ACCOUNT", accountWithDataSet);
        setResult(-1, intent);
        finish();
    }

    public void onAccountsLoaded(List<AccountInfo> list) {
        updateDisplayedAccounts(list);
    }
}
