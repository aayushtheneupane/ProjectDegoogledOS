package p000;

import android.net.Uri;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Future;

/* renamed from: fze */
/* compiled from: PG */
final /* synthetic */ class fze implements icf {

    /* renamed from: a */
    private final fzg f10719a;

    /* renamed from: b */
    private final ieh f10720b;

    public fze(fzg fzg, ieh ieh) {
        this.f10719a = fzg;
        this.f10720b = ieh;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        OutputStream outputStream;
        fzg fzg = this.f10719a;
        ieh ieh = this.f10720b;
        ikf ikf = (ikf) obj;
        Uri uri = (Uri) ife.m12871b((Future) fzg.f10723b);
        Uri uri2 = (Uri) ife.m12871b((Future) fzg.f10724c);
        if (fzg.f10726e.mo6319b(uri)) {
            if (!fzg.f10726e.mo6319b(uri2)) {
                fzg.f10726e.mo6318a(uri, uri2);
            } else {
                try {
                    fzg.f10726e.mo6317a(uri);
                } catch (IOException e) {
                    String valueOf = String.valueOf(uri.getLastPathSegment());
                    Log.e("ProtoDataStore", valueOf.length() == 0 ? new String("Previous write failed: ") : "Previous write failed: ".concat(valueOf));
                }
            }
        }
        try {
            String valueOf2 = String.valueOf(fzg.f10722a);
            hlj a = hnb.m11766a(valueOf2.length() == 0 ? new String("Write ") : "Write ".concat(valueOf2), hnf.f13084a);
            try {
                fxy fxy = new fxy();
                try {
                    outputStream = (OutputStream) fzg.f10726e.mo6316a(uri, new fyq(), fxy);
                    ikf.mo8511a(outputStream);
                    if (fxy.f10694b != null) {
                        fxy.f10693a.flush();
                        fxy.f10694b.mo6331b();
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (a != null) {
                            a.close();
                        }
                        if (fzg.f10726e.mo6319b(uri2)) {
                            fzg.f10726e.mo6317a(uri2);
                        }
                        synchronized (fzg.f10728g) {
                            fzg.f10729h = ieh;
                        }
                        return ife.m12820a((Object) null);
                    }
                    throw new fye("Cannot sync underlying stream");
                } catch (IOException e2) {
                    e = e2;
                    fxr fxr = fzg.f10726e;
                    fyn a2 = fyn.m9881a();
                    a2.mo6338b();
                    File file = (File) fxr.mo6316a(uri, a2, new fxl[0]);
                    if (file.exists()) {
                        if (!file.isFile()) {
                            e = new IOException(e);
                            throw e;
                        }
                    }
                    if (!file.exists() || file.canWrite()) {
                        e = gbz.m9994a(file, e);
                    } else {
                        e = new IOException(e);
                    }
                    throw e;
                } catch (Throwable th) {
                    ifn.m12935a(th, th);
                }
                throw th;
            } catch (IOException e3) {
            } catch (Throwable th2) {
                if (a != null) {
                    a.close();
                }
                throw th2;
            }
        } catch (IOException e4) {
            if (fzg.f10726e.mo6319b(uri)) {
                try {
                    fzg.f10726e.mo6317a(uri);
                } catch (IOException e5) {
                    int i = Build.VERSION.SDK_INT;
                    ifn.m12935a((Throwable) e4, (Throwable) e5);
                }
            }
            throw e4;
        } catch (Throwable th3) {
            ifn.m12935a(th2, th3);
        }
    }
}
