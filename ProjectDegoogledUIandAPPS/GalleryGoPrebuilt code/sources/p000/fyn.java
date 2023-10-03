package p000;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: fyn */
/* compiled from: PG */
public final class fyn implements fxq {

    /* renamed from: a */
    private boolean f10704a = false;

    static {
        new AtomicInteger();
    }

    private fyn() {
    }

    /* renamed from: a */
    public static fyn m9881a() {
        return new fyn();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo6315a(fxp fxp) {
        if (!this.f10704a) {
            fyc fyc = new fyc(fyp.m9885b(fxp));
            try {
                Closeable closeable = fyc.f10696a;
                if (closeable instanceof fxz) {
                    File a = ((fxz) closeable).mo6329a();
                    fyc.close();
                    return a;
                }
                throw new IOException("Not convertible and fallback to pipe is disabled.");
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else if (fxp.f10677b.isEmpty() && fxp.f10678c.isEmpty()) {
            return fxp.f10676a.mo6325d(fxp.f10679d);
        } else {
            throw new fye("Short circuit would ignore fragment.");
        }
        throw th;
    }

    /* renamed from: b */
    public final void mo6338b() {
        this.f10704a = true;
    }
}
