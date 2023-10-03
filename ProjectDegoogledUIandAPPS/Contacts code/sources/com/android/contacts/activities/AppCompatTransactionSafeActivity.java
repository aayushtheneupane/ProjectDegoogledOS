package com.android.contacts.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public abstract class AppCompatTransactionSafeActivity extends AppCompatActivity {
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

    public boolean isSafeToCommitTransactions() {
        return this.mIsSafeToCommitTransactions;
    }
}
