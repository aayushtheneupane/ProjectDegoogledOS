package p000;

/* renamed from: aky */
/* compiled from: PG */
final class aky extends C0047br {
    public aky(C0053bx bxVar) {
        super(bxVar);
    }

    /* renamed from: a */
    public final String mo578a() {
        return "INSERT OR IGNORE INTO `WorkName` (`name`,`work_spec_id`) VALUES (?,?)";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo579a(C0037bh bhVar, Object obj) {
        akw akw = (akw) obj;
        String str = akw.f701a;
        if (str == null) {
            bhVar.mo1920a(1);
        } else {
            bhVar.mo1922a(1, str);
        }
        String str2 = akw.f702b;
        if (str2 == null) {
            bhVar.mo1920a(2);
        } else {
            bhVar.mo1922a(2, str2);
        }
    }
}
