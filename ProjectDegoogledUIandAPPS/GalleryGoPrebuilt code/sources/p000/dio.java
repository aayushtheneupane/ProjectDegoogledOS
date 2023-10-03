package p000;

import android.content.ContentResolver;
import java.util.concurrent.Callable;

/* renamed from: dio */
/* compiled from: PG */
public final class dio implements dil {

    /* renamed from: a */
    public final dhl f6623a;

    /* renamed from: b */
    public final dhc f6624b;

    /* renamed from: c */
    public final ContentResolver f6625c;

    /* renamed from: d */
    public final cwq f6626d;

    /* renamed from: e */
    private final iel f6627e;

    public dio(dhl dhl, dhc dhc, ContentResolver contentResolver, iel iel, cwq cwq) {
        this.f6623a = dhl;
        this.f6624b = dhc;
        this.f6625c = contentResolver;
        this.f6627e = iel;
        this.f6626d = cwq;
    }

    /* renamed from: a */
    public final ieh mo4154a(String str) {
        new Object[1][0] = str;
        hlj a = hnb.m11765a("Loading special type data");
        try {
            ieh a2 = a.mo7548a(this.f6627e.mo5933a(hmq.m11749a((Callable) new dim(this, str))));
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
