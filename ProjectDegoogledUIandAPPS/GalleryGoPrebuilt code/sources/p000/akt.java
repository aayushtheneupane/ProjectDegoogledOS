package p000;

/* renamed from: akt */
/* compiled from: PG */
final class akt extends C0047br {
    public akt(C0053bx bxVar) {
        super(bxVar);
    }

    /* renamed from: a */
    public final String mo578a() {
        return "INSERT OR REPLACE INTO `SystemIdInfo` (`work_spec_id`,`system_id`) VALUES (?,?)";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo579a(C0037bh bhVar, Object obj) {
        akr akr = (akr) obj;
        String str = akr.f696a;
        if (str == null) {
            bhVar.mo1920a(1);
        } else {
            bhVar.mo1922a(1, str);
        }
        bhVar.mo1921a(2, (long) akr.f697b);
    }
}
