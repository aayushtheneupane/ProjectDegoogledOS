package p000;

import android.content.ContentResolver;
import android.media.MediaMetadataRetriever;
import java.io.File;

/* renamed from: dej */
/* compiled from: PG */
public final class dej {

    /* renamed from: a */
    public final ContentResolver f6389a;

    /* renamed from: b */
    public final exm f6390b;

    public dej(ContentResolver contentResolver, exm exm) {
        this.f6389a = contentResolver;
        this.f6390b = exm;
    }

    /* renamed from: a */
    public static long m5996a(String str) {
        return new File(str).length();
    }

    /* renamed from: a */
    public static void m5997a(deh deh, MediaMetadataRetriever mediaMetadataRetriever) {
        String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
        try {
            deh.mo4087b(Long.parseLong(extractMetadata));
        } catch (NumberFormatException e) {
            new Object[1][0] = extractMetadata;
        }
    }
}
