package p000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: ffo */
/* compiled from: PG */
public final class ffo implements fcv {

    /* renamed from: a */
    public final iek f9481a;

    /* renamed from: b */
    public final fdd f9482b;

    /* renamed from: c */
    private final ffp f9483c;

    /* renamed from: d */
    private final fft f9484d;

    /* renamed from: e */
    private final hqa f9485e;

    /* renamed from: f */
    private final ffs f9486f;

    public ffo(iek iek, ffp ffp, fft fft, fdd fdd, hqa hqa) {
        this.f9481a = iek;
        this.f9483c = ffp;
        this.f9484d = fft;
        this.f9482b = fdd;
        this.f9485e = hqa;
        ffs a = fft.mo5577a(-1);
        this.f9486f = a;
        ife.m12869b((Object) a, (Object) "AVE handler is required.");
    }

    /* renamed from: a */
    public final Set mo5492a() {
        return hto.m12120a((Object) fff.class);
    }

    /* renamed from: a */
    public final ieh mo5491a(fcz fcz) {
        Object obj;
        boolean z;
        Object obj2;
        fff fff = (fff) fcz.f9291a;
        List list = fff.f9448a;
        int size = list.size();
        int i = 0;
        while (i < size) {
            i++;
            if (this.f9485e.mo4768a((fdx) list.get(i))) {
                String a = this.f9483c.mo3871a();
                ieh b = this.f9483c.mo3872b();
                ArrayList arrayList = new ArrayList();
                ffm ffm = new ffm(this, arrayList, a, fcz, b);
                HashMap hashMap = new HashMap();
                ArrayList arrayList2 = new ArrayList();
                List list2 = fff.f9448a;
                iah iah = (iah) iai.f13716g.mo8793g();
                fdx fdx = (fdx) fff.f9448a.get(0);
                int i2 = fdx.f9341b;
                if (iah.f14319c) {
                    iah.mo8751b();
                    iah.f14319c = false;
                }
                iai iai = (iai) iah.f14318b;
                int i3 = 1;
                iai.f13718a |= 1;
                iai.f13719b = i2;
                this.f9484d.mo5578a(fdx, fdx.f9342c, hashMap, arrayList2);
                iih iih = fex.f9413a;
                fdx.mo8786b(iih);
                if (fdx.f14321j.mo8726a((iil) iih.f14244d)) {
                    iih iih2 = fex.f9413a;
                    fdx.mo8786b(iih2);
                    Object b2 = fdx.f14321j.mo8728b((iil) iih2.f14244d);
                    if (b2 == null) {
                        obj2 = iih2.f14242b;
                    } else {
                        obj2 = iih2.mo8711a(b2);
                    }
                    int i4 = ((fez) obj2).f9422b;
                    if (iah.f14319c) {
                        iah.mo8751b();
                        iah.f14319c = false;
                    }
                    iai iai2 = (iai) iah.f14318b;
                    iai2.f13718a |= 2;
                    iai2.f13720c = i4;
                }
                for (int i5 = 1; i5 < list2.size(); i5++) {
                    fdx fdx2 = (fdx) list2.get(i5);
                    fdh fdh = fdx2.f9343d;
                    if (fdh == null) {
                        fdh = fdh.f9308e;
                    }
                    if ((fdh.f9310a & 1) != 0) {
                        fdh fdh2 = fdx2.f9343d;
                        if (fdh2 == null) {
                            fdh2 = fdh.f9308e;
                        }
                        ial ial = fdh2.f9311b;
                        if (ial == null) {
                            ial = ial.f13725d;
                        }
                        if ((ial.f13727a & 2) == 0) {
                            z = false;
                            ife.m12890c(z);
                            iah.mo8330a(fdx2.f9341b);
                            this.f9484d.mo5578a(fdx2, fdx2.f9342c, hashMap, arrayList2);
                        }
                    }
                    z = true;
                    ife.m12890c(z);
                    iah.mo8330a(fdx2.f9341b);
                    this.f9484d.mo5578a(fdx2, fdx2.f9342c, hashMap, arrayList2);
                }
                fdw fdw = fff.f9449b.f9331a;
                iay a2 = iay.m12593a(fdw.f9336b);
                if (a2 == null) {
                    a2 = iay.UNASSIGNED_USER_ACTION_ID;
                }
                ife.m12896d(!iay.UNASSIGNED_USER_ACTION_ID.equals(a2));
                iay a3 = iay.m12593a(fdw.f9336b);
                if (a3 == null) {
                    a3 = iay.UNASSIGNED_USER_ACTION_ID;
                }
                if (iah.f14319c) {
                    iah.mo8751b();
                    iah.f14319c = false;
                }
                iai iai3 = (iai) iah.f14318b;
                iai3.f13722e = a3.f13814c;
                iai3.f13718a |= 4;
                iih iih3 = fex.f9414b;
                fdw.mo8786b(iih3);
                if (fdw.f14321j.mo8726a((iil) iih3.f14244d)) {
                    iih iih4 = fex.f9414b;
                    fdw.mo8786b(iih4);
                    Object b3 = fdw.f14321j.mo8728b((iil) iih4.f14244d);
                    if (b3 == null) {
                        obj = iih4.f14242b;
                    } else {
                        obj = iih4.mo8711a(b3);
                    }
                    int a4 = ife.m12803a(((fey) obj).f9418b);
                    if (a4 != 0) {
                        i3 = a4;
                    }
                    if (iah.f14319c) {
                        iah.mo8751b();
                        iah.f14319c = false;
                    }
                    iai iai4 = (iai) iah.f14318b;
                    iai4.f13723f = i3 - 1;
                    iai4.f13718a |= 8;
                }
                ieh a5 = this.f9486f.mo3874a((iai) iah.mo8770g());
                ife.m12869b((Object) a5, (Object) "AVE populator is required.");
                arrayList2.add(a5);
                ffm.mo5576a(arrayList2);
                return ife.m12883c((Iterable) arrayList).mo8443a(ife.m12887c(), (Executor) idh.f13918a);
            }
        }
        return ife.m12820a((Object) null);
    }
}
