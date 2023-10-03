package p000;

import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* renamed from: bcd */
/* compiled from: PG */
public final class bcd implements arb {

    /* renamed from: a */
    private final List f2044a;

    /* renamed from: b */
    private final arb f2045b;

    /* renamed from: c */
    private final aui f2046c;

    public bcd(List list, arb arb, aui aui) {
        this.f2044a = list;
        this.f2045b = arb;
        this.f2046c = aui;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ aua mo1507a(Object obj, int i, int i2, aqz aqz) {
        byte[] bArr;
        InputStream inputStream = (InputStream) obj;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        try {
            byte[] bArr2 = new byte[16384];
            while (true) {
                int read = inputStream.read(bArr2);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
            byteArrayOutputStream.flush();
            bArr = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            if (Log.isLoggable("StreamGifDecoder", 5)) {
                Log.w("StreamGifDecoder", "Error reading data from stream", e);
                bArr = null;
            } else {
                bArr = null;
            }
        }
        if (bArr == null) {
            return null;
        }
        return this.f2045b.mo1507a(ByteBuffer.wrap(bArr), i, i2, aqz);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1508a(Object obj, aqz aqz) {
        InputStream inputStream = (InputStream) obj;
        if (((Boolean) aqz.mo1502a(bcc.f2043b)).booleanValue() || C0652xy.m16061a(this.f2044a, inputStream, this.f2046c) != ImageHeaderParser$ImageType.GIF) {
            return false;
        }
        return true;
    }
}
