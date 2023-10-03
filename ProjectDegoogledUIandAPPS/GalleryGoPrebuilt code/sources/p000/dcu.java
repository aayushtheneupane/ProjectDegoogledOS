package p000;

import android.net.Uri;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: dcu */
/* compiled from: PG */
public final class dcu extends iox {

    /* renamed from: b */
    private final ioq f6287b;

    /* renamed from: c */
    private final ioq f6288c;

    /* renamed from: d */
    private final ioq f6289d;

    /* renamed from: e */
    private final ioq f6290e;

    /* renamed from: f */
    private final ioq f6291f;

    public dcu(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4, ioq ioq5) {
        super(iqk2, iph.m14288a(dcu.class), iqk);
        this.f6287b = ipd.m14274a(ioq);
        this.f6288c = ipd.m14274a(ioq2);
        this.f6289d = ipd.m14274a(ioq3);
        this.f6290e = ipd.m14274a(ioq4);
        this.f6291f = ipd.m14274a(ioq5);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        boolean booleanValue = ((Boolean) list.get(0)).booleanValue();
        Uri uri = (Uri) list.get(1);
        Uri uri2 = (Uri) list.get(2);
        dge dge = (dge) list.get(3);
        dbo dbo = (dbo) list.get(4);
        if (booleanValue && !Uri.EMPTY.equals(uri)) {
            return dge.mo4115a(uri);
        }
        String b = dbo.mo3205b();
        iir g = cxi.f5908x.mo8793g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        cxi cxi = (cxi) g.f14318b;
        cxi.f5909a |= 4;
        cxi.f5912d = true;
        String uri3 = uri2.toString();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        cxi cxi2 = (cxi) g.f14318b;
        uri3.getClass();
        cxi2.f5909a = 1 | cxi2.f5909a;
        cxi2.f5910b = uri3;
        cxh a = dgt.m6095a(b);
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        cxi cxi3 = (cxi) g.f14318b;
        cxi3.f5913e = a.f5906d;
        int i = cxi3.f5909a | 8;
        cxi3.f5909a = i;
        if (b == null) {
            b = "";
        }
        b.getClass();
        int i2 = i | 16;
        cxi3.f5909a = i2;
        cxi3.f5914f = b;
        cxi3.f5909a = i2 & -4097;
        cxi3.f5922n = cxi.f5908x.f5922n;
        return ife.m12820a((Object) Optional.m16285of((cxi) g.mo8770g()));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6287b.mo9044b(), this.f6288c.mo9044b(), this.f6289d.mo9044b(), this.f6290e.mo9044b(), this.f6291f.mo9044b());
    }
}
