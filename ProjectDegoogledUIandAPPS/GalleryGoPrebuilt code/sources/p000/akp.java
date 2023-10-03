package p000;

/* renamed from: akp */
/* compiled from: PG */
final class akp extends C0047br {
    public akp(C0053bx bxVar) {
        super(bxVar);
    }

    /* renamed from: a */
    public final String mo578a() {
        return "INSERT OR REPLACE INTO `Preference` (`key`,`long_value`) VALUES (?,?)";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo579a(C0037bh bhVar, Object obj) {
        akn akn = (akn) obj;
        String str = akn.f692a;
        if (str == null) {
            bhVar.mo1920a(1);
        } else {
            bhVar.mo1922a(1, str);
        }
        Long l = akn.f693b;
        if (l == null) {
            bhVar.mo1920a(2);
        } else {
            bhVar.mo1921a(2, l.longValue());
        }
    }
}
