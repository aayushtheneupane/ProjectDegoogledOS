package p000;

/* renamed from: kf */
/* compiled from: PG */
final class C0282kf implements C0284kh {

    /* renamed from: a */
    public static final C0282kf f15128a = new C0282kf();

    private C0282kf() {
    }

    /* renamed from: a */
    public final int mo9183a(CharSequence charSequence, int i) {
        boolean z = false;
        for (int i2 = 0; i2 < i; i2++) {
            int a = C0288kl.m14506a(Character.getDirectionality(charSequence.charAt(i2)));
            if (a == 0) {
                return 0;
            }
            if (a == 1) {
                z = true;
            }
        }
        return !z ? 2 : 1;
    }
}
