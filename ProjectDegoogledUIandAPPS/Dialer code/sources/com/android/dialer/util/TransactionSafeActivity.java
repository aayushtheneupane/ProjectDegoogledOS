package com.android.dialer.util;

import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;

public abstract class TransactionSafeActivity extends AppCompatActivity {
    private boolean isSafeToCommitTransactions;

    public boolean isSafeToCommitTransactions() {
        return this.isSafeToCommitTransactions;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.isSafeToCommitTransactions = true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.isSafeToCommitTransactions = true;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.isSafeToCommitTransactions = false;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.isSafeToCommitTransactions = true;
    }
}
