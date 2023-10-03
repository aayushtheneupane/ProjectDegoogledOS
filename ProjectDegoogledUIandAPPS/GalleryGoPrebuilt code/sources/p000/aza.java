package p000;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.InputStream;

/* renamed from: aza */
/* compiled from: PG */
public final class aza implements axp {

    /* renamed from: a */
    private final Context f1880a;

    /* renamed from: b */
    private final Class f1881b;

    private aza(Context context, Class cls) {
        this.f1880a = context;
        this.f1881b = cls;
    }

    /* renamed from: a */
    public final axo mo1696a(axx axx) {
        return new azc(this.f1880a, axx.mo1722a(File.class, this.f1881b), axx.mo1722a(Uri.class, this.f1881b), this.f1881b);
    }

    public aza(Context context) {
        this(context, ParcelFileDescriptor.class);
    }

    public aza(Context context, byte[] bArr) {
        this(context, InputStream.class);
    }
}
