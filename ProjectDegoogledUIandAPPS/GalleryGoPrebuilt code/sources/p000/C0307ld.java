package p000;

/* renamed from: ld */
/* compiled from: PG */
public class C0307ld implements C0306lc {

    /* renamed from: a */
    private final Object[] f15185a;

    /* renamed from: b */
    private int f15186b;

    public C0307ld(int i) {
        this.f15185a = new Object[i];
    }

    /* renamed from: a */
    public Object mo1971a() {
        int i = this.f15186b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f15185a;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.f15186b = i2;
        return obj;
    }

    /* renamed from: a */
    public boolean mo1972a(Object obj) {
        int i = 0;
        while (true) {
            int i2 = this.f15186b;
            if (i >= i2) {
                Object[] objArr = this.f15185a;
                if (i2 >= objArr.length) {
                    return false;
                }
                objArr[i2] = obj;
                this.f15186b = i2 + 1;
                return true;
            } else if (this.f15185a[i] != obj) {
                i++;
            } else {
                throw new IllegalStateException("Already in the pool!");
            }
        }
    }
}
