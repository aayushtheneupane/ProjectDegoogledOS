package p000;

import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.InputStream;

/* renamed from: aqn */
/* compiled from: PG */
public final class aqn implements aqt {

    /* renamed from: a */
    private final /* synthetic */ InputStream f1458a;

    public aqn(InputStream inputStream) {
        this.f1458a = inputStream;
    }

    /* renamed from: a */
    public final ImageHeaderParser$ImageType mo1492a(aqm aqm) {
        try {
            return aqm.mo1490a(this.f1458a);
        } finally {
            this.f1458a.reset();
        }
    }
}
