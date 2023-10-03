package p000;

import java.util.List;

/* renamed from: il */
/* compiled from: PG */
public final class C0234il {

    /* renamed from: a */
    public final int[] f14426a;

    /* renamed from: b */
    public final float[] f14427b;

    public C0234il(int i, int i2) {
        this.f14426a = new int[]{i, i2};
        this.f14427b = new float[]{0.0f, 1.0f};
    }

    public C0234il(int i, int i2, int i3) {
        this.f14426a = new int[]{i, i2, i3};
        this.f14427b = new float[]{0.0f, 0.5f, 1.0f};
    }

    public C0234il(List list, List list2) {
        int size = list.size();
        this.f14426a = new int[size];
        this.f14427b = new float[size];
        for (int i = 0; i < size; i++) {
            this.f14426a[i] = ((Integer) list.get(i)).intValue();
            this.f14427b[i] = ((Float) list2.get(i)).floatValue();
        }
    }
}
