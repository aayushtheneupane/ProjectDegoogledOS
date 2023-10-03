package p000;

/* renamed from: fpk */
/* compiled from: PG */
final class fpk implements fpm {
    /* renamed from: b */
    public final /* bridge */ /* synthetic */ String mo6018b(ike ike) {
        return ((iqn) ((iir) ike).f14318b).f14666e;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ String mo6016a(ike ike) {
        return ((iqn) ((iir) ike).f14318b).f14665d;
    }

    /* renamed from: c */
    public final /* bridge */ /* synthetic */ void mo6019c(ike ike) {
        iir iir = (iir) ike;
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        iqn iqn = (iqn) iir.f14318b;
        iqn iqn2 = iqn.f14660k;
        iqn.f14662a &= -5;
        iqn.f14665d = iqn.f14660k.f14665d;
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
            iqn iqn = (iqn) iir.f14318b;
            iqn iqn2 = iqn.f14660k;
            iqn.f14662a |= 2;
            iqn.f14664c = longValue;
            return;
        }
        iir iir2 = (iir) ike;
        if (iir2.f14319c) {
            iir2.mo8751b();
            iir2.f14319c = false;
        }
        iqn iqn3 = (iqn) iir2.f14318b;
        iqn iqn4 = iqn.f14660k;
        iqn3.f14662a &= -3;
        iqn3.f14664c = 0;
    }
}
