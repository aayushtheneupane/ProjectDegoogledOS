package com.android.settings.biometrics.face;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.utils.AnnotationSpan;
import com.android.settingslib.HelpUtils;
import com.havoc.config.center.C1715R;

public class FaceSettingsFooterPreferenceController extends BasePreferenceController {
    private static final String ANNOTATION_URL = "url";
    private FaceFeatureProvider mProvider;

    public int getAvailabilityStatus() {
        return 0;
    }

    public FaceSettingsFooterPreferenceController(Context context, String str) {
        super(context, str);
        this.mProvider = FeatureFactory.getFactory(context).getFaceFeatureProvider();
    }

    public FaceSettingsFooterPreferenceController(Context context) {
        this(context, "footer_preference");
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        Context context = this.mContext;
        preference.setTitle(AnnotationSpan.linkify(this.mContext.getText(this.mProvider.isAttentionSupported(this.mContext) ? C1715R.string.security_settings_face_settings_footer : C1715R.string.security_settings_face_settings_footer_attention_not_supported), new AnnotationSpan.LinkInfo(this.mContext, ANNOTATION_URL, HelpUtils.getHelpIntent(context, context.getString(C1715R.string.help_url_face), FaceSettingsFooterPreferenceController.class.getName()))));
    }
}
