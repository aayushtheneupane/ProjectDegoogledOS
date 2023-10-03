package com.android.contacts.vcard;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.android.contacts.R;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.util.AccountSelectionUtil;
import java.util.List;

public class SelectAccountActivity extends Activity {
    private AccountSelectionUtil.AccountSelectedListener mAccountSelectionListener;

    private class CancelListener implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener {
        private CancelListener() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            SelectAccountActivity.this.finish();
        }

        public void onCancel(DialogInterface dialogInterface) {
            SelectAccountActivity.this.finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        List<AccountWithDataSet> blockForWritableAccounts = AccountTypeManager.getInstance(this).blockForWritableAccounts();
        if (blockForWritableAccounts.size() == 0) {
            Log.w("SelectAccountActivity", "Account does not exist");
            finish();
        } else if (blockForWritableAccounts.size() == 1) {
            AccountWithDataSet accountWithDataSet = blockForWritableAccounts.get(0);
            Intent intent = new Intent();
            intent.putExtra("account_name", accountWithDataSet.name);
            intent.putExtra("account_type", accountWithDataSet.type);
            intent.putExtra("data_set", accountWithDataSet.dataSet);
            setResult(-1, intent);
            finish();
        } else {
            Log.i("SelectAccountActivity", "The number of available accounts: " + blockForWritableAccounts.size());
            this.mAccountSelectionListener = new AccountSelectionUtil.AccountSelectedListener(this, blockForWritableAccounts, R.string.import_from_vcf_file) {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    AccountWithDataSet accountWithDataSet = this.mAccountList.get(i);
                    Intent intent = new Intent();
                    intent.putExtra("account_name", accountWithDataSet.name);
                    intent.putExtra("account_type", accountWithDataSet.type);
                    intent.putExtra("data_set", accountWithDataSet.dataSet);
                    SelectAccountActivity.this.setResult(-1, intent);
                    SelectAccountActivity.this.finish();
                }
            };
            showDialog(R.string.import_from_vcf_file);
        }
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i, Bundle bundle) {
        if (i != R.string.import_from_vcf_file) {
            return super.onCreateDialog(i, bundle);
        }
        AccountSelectionUtil.AccountSelectedListener accountSelectedListener = this.mAccountSelectionListener;
        if (accountSelectedListener != null) {
            return AccountSelectionUtil.getSelectAccountDialog(this, i, accountSelectedListener, new CancelListener());
        }
        throw new NullPointerException("mAccountSelectionListener must not be null.");
    }
}
