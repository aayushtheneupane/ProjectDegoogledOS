package p000;

import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: awq */
/* compiled from: PG */
final class awq implements ari {

    /* renamed from: a */
    private final String f1799a;

    /* renamed from: b */
    private Object f1800b;

    public awq(String str) {
        this.f1799a = str;
    }

    /* renamed from: a */
    public final Class mo1510a() {
        return InputStream.class;
    }

    /* renamed from: c */
    public final void mo1517c() {
    }

    /* renamed from: d */
    public final int mo1518d() {
        return 1;
    }

    /* renamed from: b */
    public final void mo1516b() {
        try {
            ((InputStream) this.f1800b).close();
        } catch (IOException e) {
        }
    }

    /* renamed from: a */
    public final void mo1514a(apb apb, arh arh) {
        try {
            String str = this.f1799a;
            if (str.startsWith("data:image")) {
                int indexOf = str.indexOf(44);
                if (indexOf == -1) {
                    throw new IllegalArgumentException("Missing comma in data URL.");
                } else if (str.substring(0, indexOf).endsWith(";base64")) {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str.substring(indexOf + 1), 0));
                    this.f1800b = byteArrayInputStream;
                    arh.mo1525a((Object) byteArrayInputStream);
                } else {
                    throw new IllegalArgumentException("Not a base64 image data URL.");
                }
            } else {
                throw new IllegalArgumentException("Not a valid image data URL.");
            }
        } catch (IllegalArgumentException e) {
            arh.mo1524a((Exception) e);
        }
    }
}
