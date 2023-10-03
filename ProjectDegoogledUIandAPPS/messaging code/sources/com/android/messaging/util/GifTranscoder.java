package com.android.messaging.util;

import android.content.Context;
import android.text.format.Formatter;
import com.google.common.base.C1518O;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class GifTranscoder {
    static {
        System.loadLibrary("giftranscode");
    }

    /* renamed from: G */
    public static boolean m3540G(int i, int i2) {
        C1449g.get().getBoolean("bugle_gif_transcoding", true);
        return i >= 100 && i2 >= 100;
    }

    /* renamed from: c */
    public static boolean m3541c(Context context, String str, String str2) {
        C1449g.get().getBoolean("bugle_gif_transcoding", true);
        long length = new File(str).length();
        C1518O Xk = C1518O.m3981Xk();
        boolean transcodeInternal = transcodeInternal(str, str2);
        Xk.stop();
        long a = Xk.mo8533a(TimeUnit.MILLISECONDS);
        long length2 = new File(str2).length();
        float f = length > 0 ? ((float) length2) / ((float) length) : 0.0f;
        if (transcodeInternal) {
            C1430e.m3625i("MessagingApp", String.format("Resized GIF (%s) in %d ms, %s => %s (%.0f%%)", new Object[]{C1430e.m3633xa(str), Long.valueOf(a), Formatter.formatShortFileSize(context, length), Formatter.formatShortFileSize(context, length2), Float.valueOf(f * 100.0f)}));
        }
        return transcodeInternal;
    }

    private static native boolean transcodeInternal(String str, String str2);
}
