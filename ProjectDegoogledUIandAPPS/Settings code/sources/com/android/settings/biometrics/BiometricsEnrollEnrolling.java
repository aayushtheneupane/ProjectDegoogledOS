package com.android.settings.biometrics;

import android.content.Intent;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.android.settings.biometrics.BiometricEnrollSidecar;
import com.havoc.config.center.C1715R;

public abstract class BiometricsEnrollEnrolling extends BiometricEnrollBase implements BiometricEnrollSidecar.Listener {
    protected BiometricEnrollSidecar mSidecar;

    /* access modifiers changed from: protected */
    public abstract Intent getFinishIntent();

    /* access modifiers changed from: protected */
    public abstract BiometricEnrollSidecar getSidecar();

    /* access modifiers changed from: protected */
    public abstract boolean shouldStartAutomatically();

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (shouldStartAutomatically()) {
            startEnrollment();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        BiometricEnrollSidecar biometricEnrollSidecar = this.mSidecar;
        if (biometricEnrollSidecar != null) {
            biometricEnrollSidecar.setListener((BiometricEnrollSidecar.Listener) null);
        }
        if (!isChangingConfigurations()) {
            BiometricEnrollSidecar biometricEnrollSidecar2 = this.mSidecar;
            if (biometricEnrollSidecar2 != null) {
                biometricEnrollSidecar2.cancelEnrollment();
                FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
                beginTransaction.remove(this.mSidecar);
                beginTransaction.commitAllowingStateLoss();
            }
            finish();
        }
    }

    public void onBackPressed() {
        BiometricEnrollSidecar biometricEnrollSidecar = this.mSidecar;
        if (biometricEnrollSidecar != null) {
            biometricEnrollSidecar.setListener((BiometricEnrollSidecar.Listener) null);
            this.mSidecar.cancelEnrollment();
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.remove(this.mSidecar);
            beginTransaction.commitAllowingStateLoss();
            this.mSidecar = null;
        }
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onSkipButtonClick(View view) {
        setResult(2);
        finish();
    }

    public void startEnrollment() {
        this.mSidecar = (BiometricEnrollSidecar) getSupportFragmentManager().findFragmentByTag("sidecar");
        if (this.mSidecar == null) {
            this.mSidecar = getSidecar();
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.add((Fragment) this.mSidecar, "sidecar");
            beginTransaction.commitAllowingStateLoss();
        }
        this.mSidecar.setListener(this);
    }

    /* access modifiers changed from: protected */
    public void launchFinish(byte[] bArr) {
        Intent finishIntent = getFinishIntent();
        finishIntent.addFlags(637534208);
        finishIntent.putExtra("hw_auth_token", bArr);
        finishIntent.putExtra("from_settings_summary", this.mFromSettingsSummary);
        int i = this.mUserId;
        if (i != -10000) {
            finishIntent.putExtra("android.intent.extra.USER_ID", i);
        }
        startActivity(finishIntent);
        overridePendingTransition(C1715R.anim.sud_slide_next_in, C1715R.anim.sud_slide_next_out);
        finish();
    }
}
