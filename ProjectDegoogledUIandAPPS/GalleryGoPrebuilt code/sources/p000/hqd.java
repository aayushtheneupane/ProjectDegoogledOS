package p000;

/* renamed from: hqd */
/* compiled from: PG */
final class hqd extends hpi {

    /* renamed from: b */
    private final /* synthetic */ hqe f13251b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public hqd(hqe hqe, hqj hqj, CharSequence charSequence) {
        super(hqj, charSequence);
        this.f13251b = hqe;
    }

    /* renamed from: b */
    public final int mo7653b(int i) {
        return i + 1;
    }

    /* renamed from: a */
    public final int mo7652a(int i) {
        hpn hpn = this.f13251b.f13252a;
        CharSequence charSequence = this.f13220a;
        int length = charSequence.length();
        ife.m12888c(i, length);
        while (i < length) {
            if (hpn.mo7657a(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
