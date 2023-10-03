package com.android.settings.biometrics.fingerprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.android.settings.Utils;
import com.android.settings.biometrics.BiometricEnrollBase;
import com.android.settings.biometrics.BiometricEnrollSidecar;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.havoc.config.center.C1715R;

public class FingerprintEnrollFindSensor extends BiometricEnrollBase {
    private FingerprintFindSensorAnimation mAnimation;
    private boolean mHasFod;
    /* access modifiers changed from: private */
    public boolean mNextClicked;
    private FingerprintEnrollSidecar mSidecar;

    /* access modifiers changed from: protected */
    public int getContentView() {
        return C1715R.layout.fingerprint_enroll_find_sensor;
    }

    public int getMetricsCategory() {
        return 241;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getContentView());
        this.mFooterBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        FooterBarMixin footerBarMixin = this.mFooterBarMixin;
        FooterButton.Builder builder = new FooterButton.Builder(this);
        builder.setText(C1715R.string.skip_label);
        builder.setListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FingerprintEnrollFindSensor.this.onSkipButtonClick(view);
            }
        });
        builder.setButtonType(7);
        builder.setTheme(2131952051);
        footerBarMixin.setSecondaryButton(builder.build());
        setHeaderText(C1715R.string.security_settings_fingerprint_enroll_find_sensor_title);
        startLookingForFingerprint();
        View findViewById = findViewById(C1715R.C1718id.fingerprint_sensor_location_animation);
        if (findViewById instanceof FingerprintFindSensorAnimation) {
            this.mAnimation = (FingerprintFindSensorAnimation) findViewById;
        } else {
            this.mAnimation = null;
        }
        int integer = getResources().getInteger(C1715R.integer.config_fingerprintSensorLocation);
        if (integer < 0 || integer > 3) {
            integer = 0;
        }
        this.mHasFod = getPackageManager().hasSystemFeature("vendor.lineage.biometrics.fingerprint.inscreen");
        if (this.mHasFod) {
            findViewById.setVisibility(8);
            integer = 1;
        }
        ((TextView) findViewById(C1715R.C1718id.sud_layout_description)).setText(getResources().getStringArray(C1715R.array.security_settings_fingerprint_sensor_locations)[integer]);
        if (integer == 1) {
            findViewById(C1715R.C1718id.fingerprint_sensor_location_front_overlay).setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        FingerprintFindSensorAnimation fingerprintFindSensorAnimation = this.mAnimation;
        if (fingerprintFindSensorAnimation != null) {
            fingerprintFindSensorAnimation.startAnimation();
        }
    }

    private void startLookingForFingerprint() {
        this.mSidecar = (FingerprintEnrollSidecar) getSupportFragmentManager().findFragmentByTag("sidecar");
        if (this.mSidecar == null) {
            this.mSidecar = new FingerprintEnrollSidecar();
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.add((Fragment) this.mSidecar, "sidecar");
            beginTransaction.commitAllowingStateLoss();
        }
        this.mSidecar.setListener(new BiometricEnrollSidecar.Listener() {
            public void onEnrollmentHelp(int i, CharSequence charSequence) {
            }

            public void onEnrollmentProgressChange(int i, int i2) {
                boolean unused = FingerprintEnrollFindSensor.this.mNextClicked = true;
                FingerprintEnrollFindSensor.this.proceedToEnrolling(true);
            }

            public void onEnrollmentError(int i, CharSequence charSequence) {
                if (FingerprintEnrollFindSensor.this.mNextClicked && i == 5) {
                    boolean unused = FingerprintEnrollFindSensor.this.mNextClicked = false;
                    FingerprintEnrollFindSensor.this.proceedToEnrolling(false);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        FingerprintFindSensorAnimation fingerprintFindSensorAnimation = this.mAnimation;
        if (fingerprintFindSensorAnimation != null) {
            fingerprintFindSensorAnimation.pauseAnimation();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        FingerprintFindSensorAnimation fingerprintFindSensorAnimation = this.mAnimation;
        if (fingerprintFindSensorAnimation != null) {
            fingerprintFindSensorAnimation.stopAnimation();
        }
    }

    /* access modifiers changed from: protected */
    public void onSkipButtonClick(View view) {
        setResult(2);
        finish();
    }

    /* access modifiers changed from: private */
    public void proceedToEnrolling(boolean z) {
        FingerprintEnrollSidecar fingerprintEnrollSidecar = this.mSidecar;
        if (fingerprintEnrollSidecar == null) {
            return;
        }
        if (!z || !fingerprintEnrollSidecar.cancelEnrollment()) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.remove(this.mSidecar);
            beginTransaction.commitAllowingStateLoss();
            this.mSidecar = null;
            startActivityForResult(getFingerprintEnrollingIntent(), 5);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 4) {
            if (i2 != -1 || intent == null) {
                finish();
                return;
            }
            this.mToken = intent.getByteArrayExtra("hw_auth_token");
            overridePendingTransition(C1715R.anim.sud_slide_next_in, C1715R.anim.sud_slide_next_out);
            getIntent().putExtra("hw_auth_token", this.mToken);
            startLookingForFingerprint();
        } else if (i != 5) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == 1) {
            setResult(1);
            finish();
        } else if (i2 == 2) {
            setResult(2);
            finish();
        } else if (i2 == 3) {
            setResult(3);
            finish();
        } else if (Utils.getFingerprintManagerOrNull(this).getEnrolledFingerprints().size() >= getResources().getInteger(17694825)) {
            finish();
        } else {
            startLookingForFingerprint();
        }
    }
}
