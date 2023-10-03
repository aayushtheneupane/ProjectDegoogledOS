package p000;

import android.os.Build;
import android.view.View;

/* renamed from: mg */
/* compiled from: PG */
public abstract class C0337mg {

    /* renamed from: a */
    public final int f15227a;

    /* renamed from: b */
    private final Class f15228b;

    /* renamed from: c */
    private final int f15229c;

    public C0337mg(int i, Class cls) {
        this(i, cls, (byte[]) null);
    }

    /* renamed from: a */
    public abstract Object mo9386a(View view);

    public C0337mg(int i, Class cls, byte[] bArr) {
        this.f15227a = i;
        this.f15228b = cls;
        this.f15229c = 28;
    }

    /* renamed from: a */
    public final boolean mo9390a() {
        return Build.VERSION.SDK_INT >= this.f15229c;
    }

    /* renamed from: b */
    public final Object mo9391b(View view) {
        if (mo9390a()) {
            return mo9386a(view);
        }
        int i = Build.VERSION.SDK_INT;
        Object tag = view.getTag(this.f15227a);
        if (!this.f15228b.isInstance(tag)) {
            return null;
        }
        return tag;
    }
}
