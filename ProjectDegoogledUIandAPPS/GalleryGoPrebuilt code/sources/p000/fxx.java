package p000;

import android.net.Uri;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: fxx */
/* compiled from: PG */
public final class fxx implements fyr {
    public fxx() {
        new fym();
    }

    /* renamed from: b */
    public final String mo6322b() {
        return "file";
    }

    /* renamed from: e */
    public final void mo6327e(Uri uri) {
        File a = fym.m9879a(uri);
        if (a.isDirectory()) {
            throw new FileNotFoundException(String.format("%s is a directory", new Object[]{uri}));
        } else if (a.delete()) {
        } else {
            if (!a.exists()) {
                throw new FileNotFoundException(String.format("%s does not exist", new Object[]{uri}));
            } else {
                throw new IOException(String.format("%s could not be deleted", new Object[]{uri}));
            }
        }
    }

    /* renamed from: b */
    public final boolean mo6323b(Uri uri) {
        return fym.m9879a(uri).exists();
    }

    /* renamed from: a */
    public final InputStream mo6321a(Uri uri) {
        File a = fym.m9879a(uri);
        return new fyg(new FileInputStream(a), a);
    }

    /* renamed from: g */
    public final OutputStream mo6328g(Uri uri) {
        File a = fym.m9879a(uri);
        iab.m12561a(a);
        return new fyi(new FileOutputStream(a), a);
    }

    /* renamed from: a */
    public final void mo6326a(Uri uri, Uri uri2) {
        File a = fym.m9879a(uri);
        File a2 = fym.m9879a(uri2);
        iab.m12561a(a2);
        if (!a.renameTo(a2)) {
            throw new IOException(String.format("%s could not be renamed to %s", new Object[]{uri, uri2}));
        }
    }

    /* renamed from: d */
    public final File mo6325d(Uri uri) {
        return fym.m9879a(uri);
    }
}
