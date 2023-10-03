package p000;

import android.net.Uri;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: fys */
/* compiled from: PG */
public abstract class fys implements fyr {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract fyr mo6320a();

    /* renamed from: a */
    public InputStream mo6321a(Uri uri) {
        throw null;
    }

    /* renamed from: b */
    public boolean mo6323b(Uri uri) {
        throw null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public Uri mo6324c(Uri uri) {
        throw null;
    }

    /* renamed from: d */
    public File mo6325d(Uri uri) {
        throw null;
    }

    /* renamed from: e */
    public final void mo6327e(Uri uri) {
        mo6320a().mo6327e(mo6324c(uri));
    }

    /* renamed from: g */
    public final OutputStream mo6328g(Uri uri) {
        return mo6320a().mo6328g(mo6324c(uri));
    }

    /* renamed from: a */
    public final void mo6326a(Uri uri, Uri uri2) {
        mo6320a().mo6326a(mo6324c(uri), mo6324c(uri2));
    }
}
