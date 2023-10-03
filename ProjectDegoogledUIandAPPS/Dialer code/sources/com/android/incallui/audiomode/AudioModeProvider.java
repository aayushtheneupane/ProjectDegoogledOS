package com.android.incallui.audiomode;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.telecom.CallAudioState;
import com.android.dialer.common.LogUtil;
import java.util.ArrayList;
import java.util.List;

public class AudioModeProvider {
    private static final AudioModeProvider instance = new AudioModeProvider();
    private CallAudioState audioState = new CallAudioState(false, 1, 15);
    private final List<AudioModeListener> listeners = new ArrayList();

    public interface AudioModeListener {
        void onAudioStateChanged(CallAudioState callAudioState);
    }

    public static AudioModeProvider getInstance() {
        return instance;
    }

    public void addListener(AudioModeListener audioModeListener) {
        if (!this.listeners.contains(audioModeListener)) {
            this.listeners.add(audioModeListener);
            audioModeListener.onAudioStateChanged(this.audioState);
        }
    }

    public CallAudioState getAudioState() {
        return this.audioState;
    }

    public void initializeAudioState(Context context) {
        int i = 2;
        boolean z = false;
        boolean z2 = false;
        for (AudioDeviceInfo type : ((AudioManager) context.getSystemService(AudioManager.class)).getDevices(2)) {
            int type2 = type.getType();
            if (type2 == 3 || type2 == 22) {
                z2 = true;
            } else if (type2 == 7 || type2 == 8) {
                z = true;
            }
        }
        if (z) {
            LogUtil.m9i("AudioModeProvider.getApproximatedAudioRoute", "Routing to bluetooth", new Object[0]);
        } else if (z2) {
            LogUtil.m9i("AudioModeProvider.getApproximatedAudioRoute", "Routing to headset", new Object[0]);
            i = 4;
        } else {
            LogUtil.m9i("AudioModeProvider.getApproximatedAudioRoute", "Routing to earpiece", new Object[0]);
            i = 1;
        }
        onAudioStateChanged(new CallAudioState(false, i, 15));
    }

    public void onAudioStateChanged(CallAudioState callAudioState) {
        if (!this.audioState.equals(callAudioState)) {
            this.audioState = callAudioState;
            for (AudioModeListener onAudioStateChanged : this.listeners) {
                onAudioStateChanged.onAudioStateChanged(callAudioState);
            }
        }
    }

    public void removeListener(AudioModeListener audioModeListener) {
        this.listeners.remove(audioModeListener);
    }
}
