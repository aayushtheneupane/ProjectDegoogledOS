package p000;

/* renamed from: fpl */
/* compiled from: PG */
final class fpl implements fpm {
    /* renamed from: b */
    public final /* bridge */ /* synthetic */ String mo6018b(ike ike) {
        return ((irk) ((iir) ike).f14318b).f14856b;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ String mo6016a(ike ike) {
        return ((irk) ((iir) ike).f14318b).f14858d;
    }

    /* renamed from: c */
    public final /* bridge */ /* synthetic */ void mo6019c(ike ike) {
        iir iir = (iir) ike;
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        irk irk = (irk) iir.f14318b;
        irk irk2 = irk.f14853m;
        irk.f14855a &= -5;
        irk.f14858d = irk.f14853m.f14858d;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo6017a(ike ike, Long l) {
        if (l != null) {
            long longValue = l.longValue();
            iir iir = (iir) ike;
            if (iir.f14319c) {
                iir.mo8751b();
                iir.f14319c = false;
            }
            irk irk = (irk) iir.f14318b;
            irk irk2 = irk.f14853m;
            irk.f14855a |= 2;
            irk.f14857c = longValue;
            return;
        }
        iir iir2 = (iir) ike;
        if (iir2.f14319c) {
            iir2.mo8751b();
            iir2.f14319c = false;
        }
        irk irk3 = (irk) iir2.f14318b;
        irk irk4 = irk.f14853m;
        irk3.f14855a &= -3;
        irk3.f14857c = 0;
    }
}
