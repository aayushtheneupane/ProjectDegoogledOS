package com.android.incallui.ringtone;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;
import com.android.incallui.call.CallList;
import java.util.Objects;

public class DialerRingtoneManager {
    private final CallList callList;
    private final InCallTonePlayer inCallTonePlayer;
    private Boolean isDialerRingingEnabledForTesting;

    public DialerRingtoneManager(InCallTonePlayer inCallTonePlayer2, CallList callList2) {
        this.inCallTonePlayer = (InCallTonePlayer) Objects.requireNonNull(inCallTonePlayer2);
        this.callList = (CallList) Objects.requireNonNull(callList2);
    }

    private boolean isDialerRingingEnabled() {
        Boolean bool = this.isDialerRingingEnabledForTesting;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public void playCallWaitingTone() {
        if (isDialerRingingEnabled()) {
            this.inCallTonePlayer.play(4);
        }
    }

    public boolean shouldPlayCallWaitingTone(int i) {
        if (isDialerRingingEnabled()) {
            if (i == 4) {
                i = this.callList.getActiveCall() == null ? 4 : 5;
            }
            return i == 5 && !this.inCallTonePlayer.isPlayingTone();
        }
    }

    public boolean shouldPlayRingtone(int i, Uri uri) {
        if (isDialerRingingEnabled()) {
            if (i == 4) {
                i = this.callList.getActiveCall() == null ? 4 : 5;
            }
            return i == 4 && uri != null;
        }
    }

    public boolean shouldVibrate(ContentResolver contentResolver) {
        return Settings.System.getInt(contentResolver, "vibrate_when_ringing", 0) != 0;
    }

    public void stopCallWaitingTone() {
        if (isDialerRingingEnabled()) {
            this.inCallTonePlayer.stop();
        }
    }
}
