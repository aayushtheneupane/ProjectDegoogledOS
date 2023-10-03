package p000;

/* renamed from: kg */
/* compiled from: PG */
final class C0283kg implements C0284kh {

    /* renamed from: a */
    public static final C0283kg f15129a = new C0283kg();

    private C0283kg() {
    }

    /* renamed from: a */
    public final int mo9183a(CharSequence charSequence, int i) {
        int i2 = 2;
        for (int i3 = 0; i3 < i && i2 == 2; i3++) {
            i2 = C0288kl.m14507b(Character.getDirectionality(charSequence.charAt(i3)));
        }
        return i2;
    }
}
