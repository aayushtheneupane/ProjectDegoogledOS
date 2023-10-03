package p000;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/* renamed from: asb */
/* compiled from: PG */
public final class asb implements asd {

    /* renamed from: b */
    private static final String[] f1507b = {"_data"};

    /* renamed from: a */
    private final ContentResolver f1508a;

    public asb(ContentResolver contentResolver) {
        this.f1508a = contentResolver;
    }

    /* renamed from: a */
    public final Cursor mo1542a(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        return this.f1508a.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, f1507b, "kind = 1 AND video_id = ?", new String[]{lastPathSegment}, (String) null);
    }
}
