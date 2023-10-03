package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: fot */
/* compiled from: PG */
public final class fot {

    /* renamed from: a */
    public final fos f10171a;

    /* renamed from: b */
    public final List f10172b = new ArrayList();

    /* renamed from: c */
    private long f10173c;

    public fot(fos fos) {
        this.f10171a = fos;
        this.f10173c = 1;
    }

    /* renamed from: a */
    public final void mo6010a(fos fos, long j) {
        fos fos2 = fos;
        List<fos> list = fos2.f10168e;
        fos2.f10168e = Cfor.f10163a;
        long j2 = this.f10173c;
        this.f10173c = 1 + j2;
        iir g = irk.f14853m.mo8793g();
        int i = fos2.f10169f;
        String str = fos2.f10164a;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        irk irk = (irk) g.f14318b;
        str.getClass();
        int i2 = irk.f14855a | 1;
        irk.f14855a = i2;
        irk.f14856b = str;
        long j3 = fos2.f10165b;
        int i3 = i2 | 32;
        irk.f14855a = i3;
        irk.f14861g = j3;
        long j4 = fos2.f10166c;
        long j5 = -1;
        if (j4 != -1) {
            j5 = j4 - j3;
        }
        int i4 = i3 | 64;
        irk.f14855a = i4;
        irk.f14862h = j5;
        long j6 = fos2.f10167d;
        int i5 = i4 | 256;
        irk.f14855a = i5;
        irk.f14864j = j6;
        int i6 = i5 | 8;
        irk.f14855a = i6;
        irk.f14859e = j2;
        int i7 = i6 | 16;
        irk.f14855a = i7;
        irk.f14860f = j;
        if (fos2.f10170g - 1 != 1) {
            irk.f14865k = 0;
            irk.f14855a = i7 | 512;
        } else {
            irk.f14865k = 1;
            irk.f14855a = i7 | 512;
        }
        irk irk2 = (irk) g.mo8770g();
        iir iir = (iir) irk2.mo8790b(5);
        iir.mo8503a((iix) irk2);
        this.f10172b.add((irk) iir.mo8770g());
        for (fos a : list) {
            mo6010a(a, ((irk) iir.f14318b).f14859e);
        }
    }
}
