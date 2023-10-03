package com.android.messaging.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import com.android.messaging.C0967f;

/* renamed from: com.android.messaging.util.ha */
public class C1452ha {
    /* renamed from: Qj */
    public static boolean m3728Qj() {
        return C1464na.m3760_j();
    }

    public static C1452ha get() {
        return C0967f.get().mo6650Md();
    }

    /* renamed from: a */
    public void mo8183a(Context context, int i, C1448fa faVar) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(5);
            AssetFileDescriptor openRawResourceFd = context.getResources().openRawResourceFd(i);
            mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            openRawResourceFd.close();
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new C1450ga(this, faVar));
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        } catch (Exception e) {
            C1430e.m3631w("MediaUtilImpl", "Error playing sound id: " + i, e);
            if (faVar != null) {
                faVar.mo7850ba();
            }
        }
    }
}
