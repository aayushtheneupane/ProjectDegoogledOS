package p000;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import java.util.concurrent.Executor;

/* renamed from: gto */
/* compiled from: PG */
public final class gto extends ContentObserver {

    /* renamed from: a */
    public final /* synthetic */ deo f12027a;

    /* renamed from: b */
    private final hlz f12028b;

    /* renamed from: c */
    private final String f12029c;

    /* renamed from: d */
    private final Executor f12030d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public gto(deo deo, hlz hlz, String str, iel iel) {
        super((Handler) null);
        this.f12027a = deo;
        this.f12028b = hlz;
        this.f12029c = str;
        this.f12030d = iel;
    }

    public final void onChange(boolean z) {
        if (!hnb.m11774a(hnf.f13084a)) {
            hlp a = this.f12028b.mo7579a(this.f12029c, hnf.f13084a);
            try {
                m10783a(z, (Uri) null);
                if (a != null) {
                    a.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else {
            m10783a(z, (Uri) null);
            return;
        }
        throw th;
    }

    public final void onChange(boolean z, Uri uri) {
        if (!hnb.m11774a(hnf.f13084a)) {
            hlp a = this.f12028b.mo7579a(this.f12029c, hnf.f13084a);
            try {
                m10783a(z, uri);
                if (a != null) {
                    a.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else {
            m10783a(z, uri);
            return;
        }
        throw th;
    }

    /* renamed from: a */
    private final void m10783a(boolean z, Uri uri) {
        this.f12030d.execute(hmq.m11748a((Runnable) new gtn(this, z, uri)));
    }
}
