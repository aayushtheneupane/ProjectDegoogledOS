package p000;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

/* renamed from: bce */
/* compiled from: PG */
public final class bce implements bci {

    /* renamed from: a */
    private final Bitmap.CompressFormat f2047a = Bitmap.CompressFormat.JPEG;

    /* renamed from: a */
    public final aua mo1806a(aua aua, aqz aqz) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ((Bitmap) aua.mo1605b()).compress(this.f2047a, 100, byteArrayOutputStream);
        aua.mo1607d();
        return new bbi(byteArrayOutputStream.toByteArray());
    }
}
