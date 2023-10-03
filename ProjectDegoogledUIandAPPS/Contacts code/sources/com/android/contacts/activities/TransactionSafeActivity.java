package com.android.contacts.activities;

import android.app.Activity;
import android.os.Bundle;

public abstract class TransactionSafeActivity extends Activity {
    private boolean mIsSafeToCommitTransactions;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mIsSafeToCommitTransactions = true;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mIsSafeToCommitTransactions = true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mIsSafeToCommitTransactions = true;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mIsSafeToCommitTransactions = false;
    }
}
