package p000;

import android.graphics.Bitmap;
import android.net.Uri;

/* renamed from: chd */
/* compiled from: PG */
final /* synthetic */ class chd implements icf {

    /* renamed from: a */
    private final che f4386a;

    /* renamed from: b */
    private final Uri f4387b;

    public chd(che che, Uri uri) {
        this.f4386a = che;
        this.f4387b = uri;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        che che = this.f4386a;
        Uri uri = this.f4387b;
        Bitmap bitmap = (Bitmap) obj;
        Bitmap copy = bitmap.copy(bitmap.getConfig(), false);
        che.f4388a = uri;
        Bitmap bitmap2 = che.f4389b;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        che.f4389b = copy;
        return ife.m12820a((Object) che.f4389b);
    }
}
