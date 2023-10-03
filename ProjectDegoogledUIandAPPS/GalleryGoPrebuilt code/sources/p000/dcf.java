package p000;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import java.util.concurrent.Callable;

/* renamed from: dcf */
/* compiled from: PG */
final /* synthetic */ class dcf implements Callable {

    /* renamed from: a */
    private final ContentResolver f6241a;

    /* renamed from: b */
    private final Uri f6242b;

    public dcf(ContentResolver contentResolver, Uri uri) {
        this.f6241a = contentResolver;
        this.f6242b = uri;
    }

    public final Object call() {
        ContentResolver contentResolver = this.f6241a;
        Uri uri = this.f6242b;
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_pending", 0);
        contentResolver.update(uri, contentValues, (String) null, (String[]) null);
        return uri;
    }
}
