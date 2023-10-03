package com.android.voicemail;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class VoicemailComponent {

    public interface HasComponent {
    }

    public static VoicemailComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).voicemailComponent();
    }

    public abstract VoicemailClient getVoicemailClient();
}
