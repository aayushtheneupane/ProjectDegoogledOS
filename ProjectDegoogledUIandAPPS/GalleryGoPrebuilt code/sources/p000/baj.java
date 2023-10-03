package p000;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.InputStream;
import java.util.List;

/* renamed from: baj */
/* compiled from: PG */
public final class baj implements bal {

    /* renamed from: a */
    private final art f1954a;

    /* renamed from: b */
    private final aui f1955b;

    /* renamed from: c */
    private final List f1956c;

    public baj(InputStream inputStream, List list, aui aui) {
        this.f1955b = (aui) cns.m4632a((Object) aui);
        this.f1956c = (List) cns.m4632a((Object) list);
        this.f1954a = new art(inputStream, aui);
    }

    /* renamed from: a */
    public final Bitmap mo1756a(BitmapFactory.Options options) {
        return BitmapFactory.decodeStream(this.f1954a.mo1528a(), (Rect) null, options);
    }

    /* renamed from: b */
    public final int mo1758b() {
        return C0652xy.m16069b(this.f1956c, this.f1954a.mo1528a(), this.f1955b);
    }

    /* renamed from: a */
    public final ImageHeaderParser$ImageType mo1757a() {
        return C0652xy.m16061a(this.f1956c, this.f1954a.mo1528a(), this.f1955b);
    }

    /* renamed from: c */
    public final void mo1759c() {
        this.f1954a.f1498a.mo1760a();
    }
}
