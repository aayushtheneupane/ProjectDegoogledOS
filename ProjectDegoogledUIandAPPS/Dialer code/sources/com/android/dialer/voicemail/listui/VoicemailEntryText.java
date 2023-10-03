package com.android.dialer.voicemail.listui;

import android.content.Context;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.voicemail.model.VoicemailEntry;
import java.util.concurrent.TimeUnit;

public class VoicemailEntryText {
    static String getVoicemailDuration(Context context, VoicemailEntry voicemailEntry) {
        long minutes = TimeUnit.SECONDS.toMinutes(voicemailEntry.getDuration());
        long duration = voicemailEntry.getDuration() - TimeUnit.MINUTES.toSeconds(minutes);
        if (minutes > 99) {
            LogUtil.m10w("VoicemailEntryText.getVoicemailDuration", "Duration was %d", Long.valueOf(voicemailEntry.getDuration()));
            minutes = 99;
        }
        return context.getString(R.string.voicemailDurationFormat, new Object[]{Long.valueOf(minutes), Long.valueOf(duration)});
    }
}
