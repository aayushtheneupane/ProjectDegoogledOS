package p000;

/* renamed from: hpk */
/* compiled from: PG */
final class hpk extends hpj {

    /* renamed from: a */
    private final char f13226a;

    public hpk(char c) {
        this.f13226a = c;
    }

    /* renamed from: a */
    public final boolean mo7657a(char c) {
        return c == this.f13226a;
    }

    public final String toString() {
        int i = this.f13226a;
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i2 = 0; i2 < 4; i2++) {
            cArr[5 - i2] = "0123456789ABCDEF".charAt(i & 15);
            i >>= 4;
        }
        String copyValueOf = String.copyValueOf(cArr);
        StringBuilder sb = new StringBuilder(String.valueOf(copyValueOf).length() + 18);
        sb.append("CharMatcher.is('");
        sb.append(copyValueOf);
        sb.append("')");
        return sb.toString();
    }
}
