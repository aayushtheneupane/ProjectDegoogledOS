package com.android.settings.language;

import android.content.Context;
import android.speech.tts.TtsEngines;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class TtsPreferenceController extends BasePreferenceController {
    TtsEngines mTtsEngines;

    public TtsPreferenceController(Context context, String str) {
        super(context, str);
        this.mTtsEngines = new TtsEngines(context);
    }

    public int getAvailabilityStatus() {
        return (this.mTtsEngines.getEngines().isEmpty() || !this.mContext.getResources().getBoolean(C1715R.bool.config_show_tts_settings_summary)) ? 2 : 1;
    }
}
