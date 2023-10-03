package p000;

/* renamed from: fpj */
/* compiled from: PG */
final class fpj implements fpm {
    /* renamed from: b */
    public final /* bridge */ /* synthetic */ String mo6018b(ike ike) {
        return ((isc) ((iir) ike).f14318b).f14990o;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ String mo6016a(ike ike) {
        return ((isc) ((iir) ike).f14318b).f14979d;
    }

    /* renamed from: c */
    public final /* bridge */ /* synthetic */ void mo6019c(ike ike) {
        iir iir = (iir) ike;
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        isc isc = (isc) iir.f14318b;
        isc isc2 = isc.f14974r;
        isc.f14976a &= -5;
        isc.f14979d = isc.f14974r.f14979d;
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
            isc isc = (isc) iir.f14318b;
            isc isc2 = isc.f14974r;
            isc.f14976a |= 2;
            isc.f14978c = longValue;
            return;
        }
        iir iir2 = (iir) ike;
        if (iir2.f14319c) {
            iir2.mo8751b();
            iir2.f14319c = false;
        }
        isc isc3 = (isc) iir2.f14318b;
        isc isc4 = isc.f14974r;
        isc3.f14976a &= -3;
        isc3.f14978c = 0;
    }
}
