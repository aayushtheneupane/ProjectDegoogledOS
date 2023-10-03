package com.android.settings.widget;

import android.media.MediaPlayer;

/* renamed from: com.android.settings.widget.-$$Lambda$VideoPreference$aFxutwOOqnKuOzRYe5J9dLOLMfs  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$VideoPreference$aFxutwOOqnKuOzRYe5J9dLOLMfs implements MediaPlayer.OnPreparedListener {
    public static final /* synthetic */ $$Lambda$VideoPreference$aFxutwOOqnKuOzRYe5J9dLOLMfs INSTANCE = new $$Lambda$VideoPreference$aFxutwOOqnKuOzRYe5J9dLOLMfs();

    private /* synthetic */ $$Lambda$VideoPreference$aFxutwOOqnKuOzRYe5J9dLOLMfs() {
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.setLooping(true);
    }
}
