package p000;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import java.util.List;

/* renamed from: dgg */
/* compiled from: PG */
public final class dgg implements cby {

    /* renamed from: a */
    public final Context f6499a;

    public dgg(Context context) {
        this.f6499a = context;
    }

    /* renamed from: a */
    public static int m6084a(MediaMetadataRetriever mediaMetadataRetriever, int i) {
        boolean z;
        String extractMetadata = mediaMetadataRetriever.extractMetadata(i);
        if (extractMetadata != null) {
            z = true;
        } else {
            z = false;
        }
        try {
            ife.m12877b(z, "failed to get metadata %s", i);
            return Integer.parseInt(extractMetadata);
        } catch (IllegalStateException | NumberFormatException e) {
            throw cbz.m4020a(e);
        }
    }

    /* renamed from: a */
    public static void m6085a(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((Bitmap) list.get(i)).recycle();
        }
    }
}
