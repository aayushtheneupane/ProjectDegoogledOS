package p000;

import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: bag */
/* compiled from: PG */
public final class bag implements aqm {
    /* renamed from: a */
    public final ImageHeaderParser$ImageType mo1490a(InputStream inputStream) {
        return ImageHeaderParser$ImageType.UNKNOWN;
    }

    /* renamed from: a */
    public final ImageHeaderParser$ImageType mo1491a(ByteBuffer byteBuffer) {
        return ImageHeaderParser$ImageType.UNKNOWN;
    }

    /* renamed from: a */
    public final int mo1489a(InputStream inputStream, aui aui) {
        int a = new abz(inputStream).mo152a("Orientation", 1);
        if (a == 0) {
            return -1;
        }
        return a;
    }
}
