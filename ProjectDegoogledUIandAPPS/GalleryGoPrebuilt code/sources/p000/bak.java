package p000;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.util.List;

/* renamed from: bak */
/* compiled from: PG */
public final class bak implements bal {

    /* renamed from: a */
    private final aui f1957a;

    /* renamed from: b */
    private final List f1958b;

    /* renamed from: c */
    private final arx f1959c;

    public bak(ParcelFileDescriptor parcelFileDescriptor, List list, aui aui) {
        this.f1957a = (aui) cns.m4632a((Object) aui);
        this.f1958b = (List) cns.m4632a((Object) list);
        this.f1959c = new arx(parcelFileDescriptor);
    }

    /* renamed from: c */
    public final void mo1759c() {
    }

    /* renamed from: a */
    public final Bitmap mo1756a(BitmapFactory.Options options) {
        return BitmapFactory.decodeFileDescriptor(this.f1959c.mo1528a().getFileDescriptor(), (Rect) null, options);
    }

    /* renamed from: b */
    public final int mo1758b() {
        return C0652xy.m16059a(this.f1958b, (aqs) new aqr(this.f1959c, this.f1957a));
    }

    /* renamed from: a */
    public final ImageHeaderParser$ImageType mo1757a() {
        return C0652xy.m16060a(this.f1958b, (aqt) new aqp(this.f1959c, this.f1957a));
    }
}
