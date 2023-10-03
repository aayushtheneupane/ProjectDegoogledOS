package p000;

import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

/* renamed from: cxw */
/* compiled from: PG */
public final /* synthetic */ class cxw implements Callable {

    /* renamed from: a */
    private final cxy f5968a;

    /* renamed from: b */
    private final Uri f5969b;

    /* renamed from: c */
    private final cxp f5970c;

    public cxw(cxy cxy, Uri uri, cxp cxp) {
        this.f5968a = cxy;
        this.f5969b = uri;
        this.f5970c = cxp;
    }

    public final Object call() {
        InputStream a;
        cxy cxy = this.f5968a;
        Uri uri = this.f5969b;
        cxp cxp = this.f5970c;
        try {
            a = cxy.f5976b.mo4664a((Build.VERSION.SDK_INT < 29 || !fxk.m9827a(uri) || cxy.f5975a.checkSelfPermission("android.permission.ACCESS_MEDIA_LOCATION") != 0) ? uri : MediaStore.setRequireOriginal(uri));
            cxy.m5628a(cxp, a);
            if (a == null) {
                return null;
            }
            a.close();
            return null;
        } catch (IOException e) {
            cwn.m5511a((Throwable) e, "Failed to read EXIF from %s", uri.toString());
            return null;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
