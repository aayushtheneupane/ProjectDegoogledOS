package p000;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import p003j$.util.Optional;

/* renamed from: dcw */
/* compiled from: PG */
public final class dcw extends iox {

    /* renamed from: b */
    private final ioq f6296b;

    /* renamed from: c */
    private final ioq f6297c;

    /* renamed from: d */
    private final ioq f6298d;

    /* renamed from: e */
    private final ioq f6299e;

    /* renamed from: f */
    private final ioq f6300f;

    /* renamed from: g */
    private final ioq f6301g;

    /* renamed from: h */
    private final ioq f6302h;

    /* renamed from: i */
    private final ioq f6303i;

    /* renamed from: j */
    private final ioq f6304j;

    /* renamed from: k */
    private final ioq f6305k;

    /* renamed from: l */
    private final ioq f6306l;

    /* renamed from: m */
    private final ioq f6307m;

    /* renamed from: n */
    private final ioq f6308n;

    /* renamed from: o */
    private final ioq f6309o;

    /* renamed from: p */
    private final ioq f6310p;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public dcw(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4, ioq ioq5, ioq ioq6, ioq ioq7, ioq ioq8, ioq ioq9, ioq ioq10, ioq ioq11, ioq ioq12, ioq ioq13, ioq ioq14, ioq ioq15) {
        super(iqk2, iph.m14288a(dcw.class), iqk);
        iqk iqk3 = iqk;
        iqk iqk4 = iqk2;
        this.f6296b = ipd.m14274a(ioq);
        this.f6297c = ipd.m14274a(ioq2);
        this.f6298d = ipd.m14274a(ioq3);
        this.f6299e = ipd.m14274a(ioq4);
        this.f6300f = ipd.m14274a(ioq5);
        this.f6301g = ipd.m14274a(ioq6);
        this.f6302h = ipd.m14274a(ioq7);
        this.f6303i = ipd.m14274a(ioq8);
        this.f6304j = ipd.m14274a(ioq9);
        this.f6305k = ipd.m14274a(ioq10);
        this.f6306l = ipd.m14274a(ioq11);
        this.f6307m = ipd.m14274a(ioq12);
        this.f6308n = ipd.m14274a(ioq13);
        this.f6309o = ipd.m14274a(ioq14);
        this.f6310p = ipd.m14274a(ioq15);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        double[] dArr;
        List list = (List) obj;
        boolean booleanValue = ((Boolean) list.get(0)).booleanValue();
        bip bip = (bip) list.get(1);
        boolean booleanValue2 = ((Boolean) list.get(2)).booleanValue();
        Optional optional = (Optional) list.get(3);
        dbo dbo = (dbo) list.get(4);
        Optional optional2 = (Optional) list.get(5);
        Uri uri = (Uri) list.get(6);
        Optional optional3 = (Optional) list.get(7);
        ContentResolver contentResolver = (ContentResolver) list.get(8);
        Context context = (Context) list.get(9);
        iel iel = (iel) list.get(10);
        iek iek = (iek) list.get(11);
        exm exm = (exm) list.get(12);
        dee dee = (dee) list.get(13);
        dej dej = (dej) list.get(14);
        if (!booleanValue) {
            return ife.m12820a((Object) Uri.EMPTY);
        }
        Optional optional4 = optional;
        if (Build.VERSION.SDK_INT >= 29) {
            if (!fxk.m9827a(uri)) {
                if (DocumentsContract.isDocumentUri(context, uri)) {
                    return ife.m12818a(C0643xp.m15939a((abc) new dcg(context, uri, exm, iel)), 10, TimeUnit.SECONDS, (ScheduledExecutorService) iel);
                }
            } else if (!booleanValue2) {
                return iek.mo5933a(hmq.m11749a((Callable) new dcf(contentResolver, uri)));
            } else {
                return ife.m12820a((Object) uri);
            }
        } else if (fxk.m9827a(uri)) {
            return ife.m12820a((Object) uri);
        } else {
            if (optional4.isPresent() && (((cyd) optional4.get()).f5987a & 1) != 0 && optional3.isPresent()) {
                cxh cxh = cxh.IMAGE;
                cxh a = cxh.m5592a(((cyd) optional4.get()).f5992f);
                if (a == null) {
                    a = cxh.UNKNOWN_MEDIA_TYPE;
                }
                if (!cxh.equals(a) || (dbo.mo3206c() > 0 && dbo.mo3207d() > 0)) {
                    cyd cyd = (cyd) optional4.get();
                    ddk ddk = new ddk((byte[]) null);
                    ddk.f6352a = Integer.valueOf(dbo.mo3206c());
                    ddk.f6353b = Integer.valueOf(dbo.mo3207d());
                    String str = ddk.f6352a == null ? " width" : "";
                    if (ddk.f6353b == null) {
                        str = str.concat(" height");
                    }
                    if (!str.isEmpty()) {
                        String valueOf = String.valueOf(str);
                        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
                    }
                    ddi ddi = new ddi(ddk.f6352a.intValue(), ddk.f6353b.intValue());
                    if (optional2.isPresent()) {
                        dArr = ((fsc) optional2.get()).mo6112c();
                    } else {
                        dArr = null;
                    }
                    return iek.mo5933a(hmq.m11749a((Callable) new dch(dej, cyd, optional3, booleanValue2, ddi, dArr)));
                }
            }
        }
        return !optional3.isPresent() ? ife.m12822a((Throwable) new IOException("Media store could not be updated.")) : ife.m12818a(C0643xp.m15939a((abc) new dci(dbo, dee, context, optional3)), 10, TimeUnit.SECONDS, (ScheduledExecutorService) iel);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6296b.mo9044b(), this.f6297c.mo9044b(), this.f6298d.mo9044b(), this.f6299e.mo9044b(), this.f6300f.mo9044b(), this.f6301g.mo9044b(), this.f6302h.mo9044b(), this.f6303i.mo9044b(), this.f6304j.mo9044b(), this.f6305k.mo9044b(), this.f6306l.mo9044b(), this.f6307m.mo9044b(), this.f6308n.mo9044b(), this.f6309o.mo9044b(), this.f6310p.mo9044b());
    }
}
