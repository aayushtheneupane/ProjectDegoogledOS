package p000;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import java.io.IOException;

/* renamed from: bbf */
/* compiled from: PG */
public final class bbf implements arb {

    /* renamed from: a */
    public static final aqy f1988a = aqy.m1472a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new bba());

    /* renamed from: b */
    private static final aqy f1989b = aqy.m1472a("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new bbb());

    /* renamed from: c */
    private final bbd f1990c;

    /* renamed from: d */
    private final auk f1991d;

    /* renamed from: a */
    public final boolean mo1508a(Object obj, aqz aqz) {
        return true;
    }

    public bbf(auk auk, bbd bbd) {
        this.f1991d = auk;
        this.f1990c = bbd;
    }

    /* renamed from: a */
    public final aua mo1507a(Object obj, int i, int i2, aqz aqz) {
        int i3 = i;
        int i4 = i2;
        aqz aqz2 = aqz;
        long longValue = ((Long) aqz2.mo1502a(f1988a)).longValue();
        if (longValue < 0 && longValue != -1) {
            StringBuilder sb = new StringBuilder(83);
            sb.append("Requested frame must be non-negative, or DEFAULT_FRAME, given: ");
            sb.append(longValue);
            throw new IllegalArgumentException(sb.toString());
        }
        Integer num = (Integer) aqz2.mo1502a(f1989b);
        if (num == null) {
            num = 2;
        }
        azz azz = (azz) aqz2.mo1502a(azz.f1922f);
        if (azz == null) {
            azz = azz.f1921e;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            this.f1990c.mo1776a(mediaMetadataRetriever, obj);
            int intValue = num.intValue();
            Bitmap bitmap = null;
            if (Build.VERSION.SDK_INT >= 27) {
                if (i3 != Integer.MIN_VALUE) {
                    if (!(i4 == Integer.MIN_VALUE || azz == azz.f1920d)) {
                        try {
                            int parseInt = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                            int parseInt2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                            int parseInt3 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
                            if (parseInt3 != 90) {
                                if (parseInt3 == 270) {
                                }
                                float a = azz.mo1747a(parseInt, parseInt2, i3, i4);
                                bitmap = mediaMetadataRetriever.getScaledFrameAtTime(longValue, intValue, Math.round(((float) parseInt) * a), Math.round(a * ((float) parseInt2)));
                            }
                            int i5 = parseInt2;
                            parseInt2 = parseInt;
                            parseInt = i5;
                            float a2 = azz.mo1747a(parseInt, parseInt2, i3, i4);
                            bitmap = mediaMetadataRetriever.getScaledFrameAtTime(longValue, intValue, Math.round(((float) parseInt) * a2), Math.round(a2 * ((float) parseInt2)));
                        } catch (Throwable th) {
                        }
                    }
                }
            }
            if (bitmap == null) {
                bitmap = mediaMetadataRetriever.getFrameAtTime(longValue, intValue);
            }
            mediaMetadataRetriever.release();
            return azk.m1961a(bitmap, this.f1991d);
        } catch (RuntimeException e) {
            throw new IOException(e);
        } catch (Throwable th2) {
            mediaMetadataRetriever.release();
            throw th2;
        }
    }
}
