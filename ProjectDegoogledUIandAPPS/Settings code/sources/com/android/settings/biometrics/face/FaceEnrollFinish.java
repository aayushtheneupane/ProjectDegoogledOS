package com.android.settings.biometrics.face;

import android.os.Bundle;
import android.view.View;
import com.android.settings.biometrics.BiometricEnrollBase;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.havoc.config.center.C1715R;

public class FaceEnrollFinish extends BiometricEnrollBase {
    public int getMetricsCategory() {
        return 1508;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1715R.layout.face_enroll_finish);
        setHeaderText(C1715R.string.security_settings_face_enroll_finish_title);
        this.mFooterBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        FooterBarMixin footerBarMixin = this.mFooterBarMixin;
        FooterButton.Builder builder = new FooterButton.Builder(this);
        builder.setText(C1715R.string.security_settings_face_enroll_done);
        builder.setListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FaceEnrollFinish.this.onNextButtonClick(view);
            }
        });
        builder.setButtonType(5);
        builder.setTheme(2131952050);
        footerBarMixin.setPrimaryButton(builder.build());
    }

    public void onNextButtonClick(View view) {
        setResult(1);
        finish();
    }
}
