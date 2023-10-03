package p000;

/* renamed from: hqf */
/* compiled from: PG */
final class hqf extends hpi {

    /* renamed from: b */
    private final /* synthetic */ hqg f13253b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public hqf(hqg hqg, hqj hqj, CharSequence charSequence) {
        super(hqj, charSequence);
        this.f13253b = hqg;
    }

    /* renamed from: b */
    public final int mo7653b(int i) {
        return i + this.f13253b.f13254a.length();
    }

    /* renamed from: a */
    public final int mo7652a(int i) {
        int length = this.f13253b.f13254a.length();
        int length2 = this.f13220a.length() - length;
        while (i <= length2) {
            int i2 = 0;
            while (i2 < length) {
                if (this.f13220a.charAt(i2 + i) != this.f13253b.f13254a.charAt(i2)) {
                    i++;
                } else {
                    i2++;
                }
            }
            return i;
        }
        return -1;
    }
}
