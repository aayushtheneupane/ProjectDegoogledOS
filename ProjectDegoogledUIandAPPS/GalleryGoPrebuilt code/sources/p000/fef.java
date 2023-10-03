package p000;

import java.util.Map;

/* renamed from: fef */
/* compiled from: PG */
public final class fef implements fcq {
    /* renamed from: a */
    public final ieh mo5487a(fcx fcx) {
        Object obj;
        if (fcx instanceof ffc) {
            ffc ffc = (ffc) fcx;
            fdx b = ffc.mo5574b();
            iih iih = feh.f9370a;
            Map map = iix.f14324A;
            b.mo8786b(iih);
            if (b.f14321j.mo8726a((iil) iih.f14244d)) {
                fdx b2 = ffc.mo5574b();
                iih iih2 = feh.f9370a;
                b2.mo8786b(iih2);
                Object b3 = b2.f14321j.mo8728b((iil) iih2.f14244d);
                if (b3 == null) {
                    obj = iih2.f14242b;
                } else {
                    obj = iih2.mo8711a(b3);
                }
                feg feg = (feg) obj;
                if ((feg.f9368a & 1) == 0) {
                    return ife.m12820a((Object) new fcm(3, (String) null));
                }
                String str = feg.f9369b;
                if (str.isEmpty()) {
                    return ife.m12820a((Object) new fcm(2, (String) null));
                }
                ife.m12898e((Object) str);
                return ife.m12820a((Object) new fcm(1, str));
            }
        }
        return ife.m12820a((Object) null);
    }
}
