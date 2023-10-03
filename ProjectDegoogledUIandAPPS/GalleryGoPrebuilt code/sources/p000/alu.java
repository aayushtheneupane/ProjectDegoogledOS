package p000;

/* renamed from: alu */
/* compiled from: PG */
final class alu extends C0047br {
    public alu(C0053bx bxVar) {
        super(bxVar);
    }

    /* renamed from: a */
    public final String mo578a() {
        return "INSERT OR IGNORE INTO `WorkTag` (`tag`,`work_spec_id`) VALUES (?,?)";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo579a(C0037bh bhVar, Object obj) {
        als als = (als) obj;
        String str = als.f739a;
        if (str == null) {
            bhVar.mo1920a(1);
        } else {
            bhVar.mo1922a(1, str);
        }
        String str2 = als.f740b;
        if (str2 == null) {
            bhVar.mo1920a(2);
        } else {
            bhVar.mo1922a(2, str2);
        }
    }
}
