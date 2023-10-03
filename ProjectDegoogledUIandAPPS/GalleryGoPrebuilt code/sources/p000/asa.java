package p000;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/* renamed from: asa */
/* compiled from: PG */
public final class asa implements asd {

    /* renamed from: b */
    private static final String[] f1505b = {"_data"};

    /* renamed from: a */
    private final ContentResolver f1506a;

    public asa(ContentResolver contentResolver) {
        this.f1506a = contentResolver;
    }

    /* renamed from: a */
    public final Cursor mo1542a(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        return this.f1506a.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, f1505b, "kind = 1 AND image_id = ?", new String[]{lastPathSegment}, (String) null);
    }
}
