package p000;

import android.os.Environment;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: egw */
/* compiled from: PG */
public final class egw implements Closeable, dbo {

    /* renamed from: a */
    public final String f8233a;

    /* renamed from: b */
    private final File f8234b;

    /* renamed from: c */
    private final String f8235c;

    /* renamed from: d */
    private final int f8236d;

    /* renamed from: e */
    private final int f8237e;

    public egw(egx egx) {
        String str;
        if (dgt.m6098c(egx.f8242c) || dgt.m6097b(egx.f8242c)) {
            this.f8234b = new File(egx.f8241b);
            String str2 = egx.f8241b;
            int lastIndexOf = str2.lastIndexOf(".");
            if (lastIndexOf <= 0) {
                str = "";
            } else {
                str = str2.substring(lastIndexOf);
            }
            this.f8233a = str;
            this.f8235c = egx.f8242c;
            this.f8236d = egx.f8243d;
            this.f8237e = egx.f8244e;
            return;
        }
        String valueOf = String.valueOf(egx.f8242c);
        throw new IllegalArgumentException(valueOf.length() == 0 ? new String("Unsupported mime type ") : "Unsupported mime type ".concat(valueOf));
    }

    /* renamed from: a */
    public final String mo3203a() {
        return this.f8233a;
    }

    /* renamed from: b */
    public final String mo3205b() {
        return this.f8235c;
    }

    /* renamed from: c */
    public final int mo3206c() {
        return this.f8236d;
    }

    /* renamed from: d */
    public final int mo3207d() {
        return this.f8237e;
    }

    public final void close() {
        mo4807g();
    }

    /* renamed from: g */
    public final void mo4807g() {
        if (this.f8234b.exists()) {
            this.f8234b.delete();
        }
    }

    /* renamed from: e */
    public final String mo3208e() {
        if (dgt.m6097b(this.f8235c)) {
            return dcm.m5896a(this.f8234b.getName());
        }
        return dcm.m5897a(Environment.DIRECTORY_MOVIES, this.f8234b.getName());
    }

    /* renamed from: f */
    public final String mo4806f() {
        return this.f8234b.getPath();
    }

    /* renamed from: a */
    public final void mo3204a(OutputStream outputStream) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(this.f8234b);
            hzt.m12551a((InputStream) fileInputStream, outputStream);
            fileInputStream.close();
            this.f8234b.delete();
            return;
        } catch (Throwable th) {
            this.f8234b.delete();
            throw th;
        }
        throw th;
    }
}
