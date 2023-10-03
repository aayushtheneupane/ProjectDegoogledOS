package com.android.dialer.precall.impl;

import android.app.Activity;
import android.app.KeyguardManager;
import android.os.Bundle;

public class PreCallActivity extends Activity {
    private PreCallCoordinatorImpl preCallCoordinator;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.preCallCoordinator = new PreCallCoordinatorImpl(this);
        this.preCallCoordinator.onCreate(getIntent(), bundle);
        if (((KeyguardManager) getSystemService(KeyguardManager.class)).isKeyguardLocked()) {
            getWindow().addFlags(2621440);
        }
    }

    public void onPause() {
        super.onPause();
        this.preCallCoordinator.onPause();
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.preCallCoordinator.onRestoreInstanceState(bundle);
    }

    public void onResume() {
        super.onResume();
        this.preCallCoordinator.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.preCallCoordinator.onSaveInstanceState(bundle);
    }
}
