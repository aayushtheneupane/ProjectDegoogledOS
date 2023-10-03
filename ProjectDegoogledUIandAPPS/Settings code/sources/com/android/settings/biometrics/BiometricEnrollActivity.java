package com.android.settings.biometrics;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.android.settings.SetupWizardUtils;
import com.android.settings.biometrics.face.FaceEnrollIntroduction;
import com.android.settings.biometrics.fingerprint.FingerprintEnrollFindSensor;
import com.android.settings.biometrics.fingerprint.FingerprintEnrollIntroduction;
import com.android.settings.biometrics.fingerprint.SetupFingerprintEnrollIntroduction;
import com.android.settings.core.InstrumentedActivity;
import com.google.android.setupcompat.util.WizardManagerHelper;

public class BiometricEnrollActivity extends InstrumentedActivity {

    public static final class InternalActivity extends BiometricEnrollActivity {
    }

    public int getMetricsCategory() {
        return 1586;
    }

    public void onCreate(Bundle bundle) {
        Intent intent;
        super.onCreate(bundle);
        PackageManager packageManager = getApplicationContext().getPackageManager();
        if (packageManager.hasSystemFeature("android.hardware.fingerprint")) {
            intent = (!getIntent().getBooleanExtra("skip_intro", false) || !(this instanceof InternalActivity)) ? getFingerprintIntroIntent() : getFingerprintFindSensorIntent();
        } else {
            intent = packageManager.hasSystemFeature("android.hardware.biometrics.face") ? getFaceIntroIntent() : null;
        }
        if (intent != null) {
            intent.setFlags(33554432);
            if (this instanceof InternalActivity) {
                byte[] byteArrayExtra = getIntent().getByteArrayExtra("hw_auth_token");
                int intExtra = getIntent().getIntExtra("android.intent.extra.USER_ID", -10000);
                intent.putExtra("hw_auth_token", byteArrayExtra);
                intent.putExtra("android.intent.extra.USER_ID", intExtra);
            }
            startActivity(intent);
        }
        finish();
    }

    private Intent getFingerprintFindSensorIntent() {
        Intent intent = new Intent(this, FingerprintEnrollFindSensor.class);
        SetupWizardUtils.copySetupExtras(getIntent(), intent);
        return intent;
    }

    private Intent getFingerprintIntroIntent() {
        if (!WizardManagerHelper.isAnySetupWizard(getIntent())) {
            return new Intent(this, FingerprintEnrollIntroduction.class);
        }
        Intent intent = new Intent(this, SetupFingerprintEnrollIntroduction.class);
        WizardManagerHelper.copyWizardManagerExtras(getIntent(), intent);
        return intent;
    }

    private Intent getFaceIntroIntent() {
        Intent intent = new Intent(this, FaceEnrollIntroduction.class);
        WizardManagerHelper.copyWizardManagerExtras(getIntent(), intent);
        return intent;
    }
}
