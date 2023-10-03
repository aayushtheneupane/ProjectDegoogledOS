package p000;

import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* renamed from: fzg */
/* compiled from: PG */
public final class fzg implements fzy {

    /* renamed from: a */
    public final String f10722a;

    /* renamed from: b */
    public final ieh f10723b;

    /* renamed from: c */
    public final ieh f10724c;

    /* renamed from: d */
    public final Executor f10725d;

    /* renamed from: e */
    public final fxr f10726e;

    /* renamed from: f */
    public final fyx f10727f;

    /* renamed from: g */
    public final Object f10728g = new Object();

    /* renamed from: h */
    public ieh f10729h = null;

    /* renamed from: i */
    private final ikf f10730i;

    /* renamed from: j */
    private final iij f10731j;

    /* renamed from: k */
    private final ido f10732k = ido.m12729a();

    public fzg(String str, ieh ieh, ikf ikf, Executor executor, fxr fxr, fyx fyx, iij iij) {
        this.f10722a = str;
        ieh a = ife.m12817a(ieh);
        this.f10723b = a;
        this.f10724c = ife.m12817a(ibv.m12657a(a, fyy.f10707a, (Executor) idh.f13918a));
        this.f10730i = ikf;
        this.f10725d = ife.m12837a(executor);
        this.f10726e = fxr;
        this.f10727f = fyx;
        this.f10731j = iij;
    }

    /* renamed from: a */
    public final ieh mo6351a() {
        ieh ieh;
        synchronized (this.f10728g) {
            ieh ieh2 = this.f10729h;
            if (ieh2 != null && ieh2.isDone()) {
                try {
                    ife.m12871b((Future) this.f10729h);
                } catch (ExecutionException e) {
                    this.f10729h = null;
                }
            }
            if (this.f10729h == null) {
                this.f10729h = ife.m12817a(this.f10732k.mo8417a(hmq.m11743a((ice) new fzc(this)), this.f10725d));
            }
            ieh = this.f10729h;
        }
        return ieh;
    }

    /* renamed from: a */
    public final ikf mo6353a(Uri uri) {
        hlj a;
        try {
            String valueOf = String.valueOf(this.f10722a);
            a = hnb.m11766a(valueOf.length() == 0 ? new String("Read ") : "Read ".concat(valueOf), hnf.f13084a);
            if (this.f10726e.mo6319b(uri)) {
                fxr fxr = this.f10726e;
                fyo fyo = new fyo(this.f10730i.mo8792f());
                fyo.f10705a = this.f10731j;
                ikf ikf = (ikf) fxr.mo6316a(uri, fyo, new fxl[0]);
                if (a != null) {
                    a.close();
                }
                return ikf;
            }
            ikf ikf2 = this.f10730i;
            if (a != null) {
                a.close();
            }
            return ikf2;
        } catch (IOException e) {
            e = e;
            fxr fxr2 = this.f10726e;
            try {
                fyn a2 = fyn.m9881a();
                a2.mo6338b();
                File file = (File) fxr2.mo6316a(uri, a2, new fxl[0]);
                if (!file.exists()) {
                    e = new IOException(e);
                } else if (!file.isFile()) {
                    e = new IOException(e);
                } else if (!file.canRead()) {
                    e = new IOException(e);
                } else {
                    e = gbz.m9994a(file, e);
                }
            } catch (IOException e2) {
            }
            throw e;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo6354a(Uri uri, Uri uri2) {
        if (this.f10726e.mo6319b(uri2)) {
            if (this.f10726e.mo6319b(uri)) {
                this.f10726e.mo6317a(uri);
            }
            this.f10726e.mo6318a(uri2, uri);
        }
    }

    /* renamed from: a */
    public final ieh mo6352a(icf icf, Executor executor) {
        return this.f10732k.mo8417a(hmq.m11743a((ice) new fza(this, mo6351a(), icf, executor)), idh.f13918a);
    }
}
