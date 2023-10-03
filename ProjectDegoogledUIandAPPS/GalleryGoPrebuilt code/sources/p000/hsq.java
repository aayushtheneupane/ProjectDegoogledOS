package p000;

import java.util.Arrays;
import java.util.Map;

/* renamed from: hsq */
/* compiled from: PG */
public final class hsq {

    /* renamed from: a */
    public int f13353a;

    /* renamed from: b */
    private Object[] f13354b;

    public hsq() {
        this(4);
    }

    public hsq(int i) {
        this.f13354b = new Object[(i + i)];
        this.f13353a = 0;
    }

    /* renamed from: a */
    public final hsu mo7930a() {
        return hvb.m12214a(this.f13353a, this.f13354b);
    }

    /* renamed from: a */
    public final void mo7931a(int i) {
        int i2 = i + i;
        Object[] objArr = this.f13354b;
        int length = objArr.length;
        if (i2 > length) {
            this.f13354b = Arrays.copyOf(objArr, hse.m12003a(length, i2));
        }
    }

    /* renamed from: a */
    public final void mo7932a(Object obj, Object obj2) {
        mo7931a(this.f13353a + 1);
        ife.m12843a(obj, obj2);
        Object[] objArr = this.f13354b;
        int i = this.f13353a;
        int i2 = i + i;
        objArr[i2] = obj;
        objArr[i2 + 1] = obj2;
        this.f13353a = i + 1;
    }

    /* renamed from: a */
    public final void mo7933a(Map.Entry entry) {
        mo7932a(entry.getKey(), entry.getValue());
    }
}
