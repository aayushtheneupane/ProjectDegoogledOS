package androidx.core.content.p022a;

import java.util.List;

/* renamed from: androidx.core.content.a.g */
final class C0314g {

    /* renamed from: co */
    final float[] f313co;
    final int[] mColors;

    C0314g(List list, List list2) {
        int size = list.size();
        this.mColors = new int[size];
        this.f313co = new float[size];
        for (int i = 0; i < size; i++) {
            this.mColors[i] = ((Integer) list.get(i)).intValue();
            this.f313co[i] = ((Float) list2.get(i)).floatValue();
        }
    }

    C0314g(int i, int i2) {
        this.mColors = new int[]{i, i2};
        this.f313co = new float[]{0.0f, 1.0f};
    }

    C0314g(int i, int i2, int i3) {
        this.mColors = new int[]{i, i2, i3};
        this.f313co = new float[]{0.0f, 0.5f, 1.0f};
    }
}
