package com.android.incallui.speakeasy;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;
import com.google.common.base.Optional;

public abstract class SpeakEasyComponent {

    public interface HasComponent {
    }

    public static SpeakEasyComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).speakEasyComponent();
    }

    public abstract SpeakEasyCallManager speakEasyCallManager();

    public abstract Optional<Integer> speakEasyChip();
}
