package com.android.settings.biometrics.fingerprint;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.android.settings.Utils;
import com.android.settings.biometrics.BiometricEnrollIntroduction;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.span.LinkSpan;
import com.havoc.config.center.C1715R;

public class FingerprintEnrollIntroduction extends BiometricEnrollIntroduction {
    private FingerprintManager mFingerprintManager;

    /* access modifiers changed from: protected */
    public int getConfirmLockTitleResId() {
        return C1715R.string.security_settings_fingerprint_preference_title;
    }

    /* access modifiers changed from: protected */
    public int getDescriptionResDisabledByAdmin() {
        return C1715R.string.f102x4ab1f9db;
    }

    /* access modifiers changed from: protected */
    public String getExtraKeyForBiometric() {
        return "for_fingerprint";
    }

    /* access modifiers changed from: protected */
    public int getHeaderResDefault() {
        return C1715R.string.security_settings_fingerprint_enroll_introduction_title;
    }

    /* access modifiers changed from: protected */
    public int getHeaderResDisabledByAdmin() {
        return C1715R.string.f103x2980692c;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return C1715R.layout.fingerprint_enroll_introduction;
    }

    public int getMetricsCategory() {
        return 243;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFingerprintManager = Utils.getFingerprintManagerOrNull(this);
        this.mFooterBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        FooterBarMixin footerBarMixin = this.mFooterBarMixin;
        FooterButton.Builder builder = new FooterButton.Builder(this);
        builder.setText(C1715R.string.security_settings_face_enroll_introduction_cancel);
        builder.setListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FingerprintEnrollIntroduction.this.onCancelButtonClick(view);
            }
        });
        builder.setButtonType(7);
        builder.setTheme(2131952051);
        footerBarMixin.setSecondaryButton(builder.build());
        FooterBarMixin footerBarMixin2 = this.mFooterBarMixin;
        FooterButton.Builder builder2 = new FooterButton.Builder(this);
        builder2.setText(C1715R.string.wizard_next);
        builder2.setListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FingerprintEnrollIntroduction.this.onNextButtonClick(view);
            }
        });
        builder2.setButtonType(5);
        builder2.setTheme(2131952050);
        footerBarMixin2.setPrimaryButton(builder2.build());
    }

    /* access modifiers changed from: protected */
    public boolean isDisabledByAdmin() {
        return RestrictedLockUtilsInternal.checkIfKeyguardFeaturesDisabled(this, 32, this.mUserId) != null;
    }

    /* access modifiers changed from: protected */
    public FooterButton getCancelButton() {
        FooterBarMixin footerBarMixin = this.mFooterBarMixin;
        if (footerBarMixin != null) {
            return footerBarMixin.getSecondaryButton();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public FooterButton getNextButton() {
        FooterBarMixin footerBarMixin = this.mFooterBarMixin;
        if (footerBarMixin != null) {
            return footerBarMixin.getPrimaryButton();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public TextView getErrorTextView() {
        return (TextView) findViewById(C1715R.C1718id.error_text);
    }

    /* access modifiers changed from: protected */
    public int checkMaxEnrolled() {
        if (this.mFingerprintManager == null) {
            return C1715R.string.fingerprint_intro_error_unknown;
        }
        if (this.mFingerprintManager.getEnrolledFingerprints(this.mUserId).size() >= getResources().getInteger(17694825)) {
            return C1715R.string.fingerprint_intro_error_max;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public long getChallenge() {
        this.mFingerprintManager = Utils.getFingerprintManagerOrNull(this);
        FingerprintManager fingerprintManager = this.mFingerprintManager;
        if (fingerprintManager == null) {
            return 0;
        }
        return fingerprintManager.preEnroll();
    }

    /* access modifiers changed from: protected */
    public Intent getEnrollingIntent() {
        return new Intent(this, FingerprintEnrollFindSensor.class);
    }

    public void onClick(LinkSpan linkSpan) {
        if ("url".equals(linkSpan.getId())) {
            Intent helpIntent = HelpUtils.getHelpIntent(this, getString(C1715R.string.help_url_fingerprint), getClass().getName());
            if (helpIntent == null) {
                Log.w("FingerprintIntro", "Null help intent.");
                return;
            }
            try {
                startActivityForResult(helpIntent, 3);
            } catch (ActivityNotFoundException e) {
                Log.w("FingerprintIntro", "Activity was not found for intent, " + e);
            }
        }
    }
}
