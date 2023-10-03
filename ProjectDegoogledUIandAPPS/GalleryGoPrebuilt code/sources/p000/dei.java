package p000;

import android.net.Uri;
import android.provider.MediaStore;

/* renamed from: dei */
/* compiled from: PG */
public final class dei {

    /* renamed from: a */
    public static final Uri f6387a = MediaStore.Video.Media.getContentUri("phoneStorage");

    /* renamed from: b */
    public static final Uri f6388b = MediaStore.Images.Media.getContentUri("phoneStorage");

    static {
        Uri[] uriArr = {MediaStore.Images.Media.EXTERNAL_CONTENT_URI, f6388b, MediaStore.Video.Media.EXTERNAL_CONTENT_URI, f6387a};
    }
}
