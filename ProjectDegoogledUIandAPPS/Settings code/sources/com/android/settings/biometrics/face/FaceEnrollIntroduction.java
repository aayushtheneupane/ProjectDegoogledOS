package com.android.settings.biometrics.face;

import android.content.Intent;
import android.hardware.face.FaceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.android.settings.Utils;
import com.android.settings.biometrics.BiometricEnrollIntroduction;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.google.android.setupdesign.span.LinkSpan;
import com.google.android.setupdesign.template.RequireScrollMixin;
import com.havoc.config.center.C1715R;

public class FaceEnrollIntroduction extends BiometricEnrollIntroduction {
    private FaceFeatureProvider mFaceFeatureProvider;
    private FaceManager mFaceManager;

    /* access modifiers changed from: protected */
    public int getConfirmLockTitleResId() {
        return C1715R.string.security_settings_face_preference_title;
    }

    /* access modifiers changed from: protected */
    public int getDescriptionResDisabledByAdmin() {
        return C1715R.string.f97x7c35c12e;
    }

    /* access modifiers changed from: protected */
    public String getExtraKeyForBiometric() {
        return "for_face";
    }

    /* access modifiers changed from: protected */
    public int getHeaderResDefault() {
        return C1715R.string.security_settings_face_enroll_introduction_title;
    }

    /* access modifiers changed from: protected */
    public int getHeaderResDisabledByAdmin() {
        return C1715R.string.security_settings_face_enroll_introduction_title_unlock_disabled;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return C1715R.layout.face_enroll_introduction;
    }

    public int getMetricsCategory() {
        return 1506;
    }

    public void onClick(LinkSpan linkSpan) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFaceManager = Utils.getFaceManagerOrNull(this);
        this.mFaceFeatureProvider = FeatureFactory.getFactory(getApplicationContext()).getFaceFeatureProvider();
        this.mFooterBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        if (WizardManagerHelper.isAnySetupWizard(getIntent())) {
            FooterBarMixin footerBarMixin = this.mFooterBarMixin;
            FooterButton.Builder builder = new FooterButton.Builder(this);
            builder.setText(C1715R.string.security_settings_face_enroll_introduction_no_thanks);
            builder.setListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    FaceEnrollIntroduction.this.onSkipButtonClick(view);
                }
            });
            builder.setButtonType(7);
            builder.setTheme(2131952051);
            footerBarMixin.setSecondaryButton(builder.build());
        } else {
            FooterBarMixin footerBarMixin2 = this.mFooterBarMixin;
            FooterButton.Builder builder2 = new FooterButton.Builder(this);
            builder2.setText(C1715R.string.security_settings_face_enroll_introduction_no_thanks);
            builder2.setListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    FaceEnrollIntroduction.this.onCancelButtonClick(view);
                }
            });
            builder2.setButtonType(2);
            builder2.setTheme(2131952051);
            footerBarMixin2.setSecondaryButton(builder2.build());
        }
        FooterButton.Builder builder3 = new FooterButton.Builder(this);
        builder3.setText(C1715R.string.security_settings_face_enroll_introduction_agree);
        builder3.setButtonType(5);
        builder3.setTheme(2131952050);
        if (maxFacesEnrolled()) {
            builder3.setListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    FaceEnrollIntroduction.this.onNextButtonClick(view);
                }
            });
            this.mFooterBarMixin.setPrimaryButton(builder3.build());
        } else {
            FooterButton build = builder3.build();
            this.mFooterBarMixin.setPrimaryButton(build);
            ((RequireScrollMixin) getLayout().getMixin(RequireScrollMixin.class)).requireScrollWithButton(this, build, C1715R.string.security_settings_face_enroll_introduction_more, new View.OnClickListener() {
                public final void onClick(View view) {
                    FaceEnrollIntroduction.this.lambda$onCreate$0$FaceEnrollIntroduction(view);
                }
            });
        }
        ((TextView) findViewById(C1715R.C1718id.face_enroll_introduction_footer_part_2)).setText(this.mFaceFeatureProvider.isAttentionSupported(getApplicationContext()) ? C1715R.string.security_settings_face_enroll_introduction_footer_part_2 : C1715R.string.security_settings_face_settings_footer_attention_not_supported);
    }

    public /* synthetic */ void lambda$onCreate$0$FaceEnrollIntroduction(View view) {
        onNextButtonClick(view);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (!isChangingConfigurations() && !this.mConfirmingCredentials && !this.mNextClicked && !WizardManagerHelper.isAnySetupWizard(getIntent())) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public boolean isDisabledByAdmin() {
        return RestrictedLockUtilsInternal.checkIfKeyguardFeaturesDisabled(this, 128, this.mUserId) != null;
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

    private boolean maxFacesEnrolled() {
        if (this.mFaceManager == null || this.mFaceManager.getEnrolledFaces(this.mUserId).size() < getResources().getInteger(17694824)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int checkMaxEnrolled() {
        if (this.mFaceManager == null) {
            return C1715R.string.face_intro_error_unknown;
        }
        if (maxFacesEnrolled()) {
            return C1715R.string.face_intro_error_max;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public long getChallenge() {
        this.mFaceManager = Utils.getFaceManagerOrNull(this);
        FaceManager faceManager = this.mFaceManager;
        if (faceManager == null) {
            return 0;
        }
        return faceManager.generateChallenge();
    }

    /* access modifiers changed from: protected */
    public Intent getEnrollingIntent() {
        Intent intent = new Intent(this, FaceEnrollEducation.class);
        WizardManagerHelper.copyWizardManagerExtras(getIntent(), intent);
        return intent;
    }
}
