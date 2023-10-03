package p000;

/* renamed from: akl */
/* compiled from: PG */
final class akl extends C0047br {
    public akl(C0053bx bxVar) {
        super(bxVar);
    }

    /* renamed from: a */
    public final String mo578a() {
        return "INSERT OR IGNORE INTO `Dependency` (`work_spec_id`,`prerequisite_id`) VALUES (?,?)";
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo579a(C0037bh bhVar, Object obj) {
        akj akj = (akj) obj;
        String str = akj.f688a;
        if (str == null) {
            bhVar.mo1920a(1);
        } else {
            bhVar.mo1922a(1, str);
        }
        String str2 = akj.f689b;
        if (str2 == null) {
            bhVar.mo1920a(2);
        } else {
            bhVar.mo1922a(2, str2);
        }
    }
}
