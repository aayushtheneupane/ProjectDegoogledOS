package p000;

import android.content.res.ColorStateList;
import android.graphics.Shader;

/* renamed from: ig */
/* compiled from: PG */
public final class C0229ig {

    /* renamed from: a */
    public final Shader f14045a;

    /* renamed from: b */
    public int f14046b;

    /* renamed from: c */
    private final ColorStateList f14047c;

    public C0229ig(Shader shader, ColorStateList colorStateList, int i) {
        this.f14045a = shader;
        this.f14047c = colorStateList;
        this.f14046b = i;
    }

    /* renamed from: a */
    public final boolean mo8494a() {
        return this.f14045a != null;
    }

    /* renamed from: a */
    public static C0229ig m12951a(int i) {
        return new C0229ig((Shader) null, (ColorStateList) null, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.f14047c;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo8496b() {
        /*
            r1 = this;
            android.graphics.Shader r0 = r1.f14045a
            if (r0 != 0) goto L_0x0010
            android.content.res.ColorStateList r0 = r1.f14047c
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.isStateful()
            if (r0 == 0) goto L_0x0010
            r0 = 1
            return r0
        L_0x0010:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0229ig.mo8496b():boolean");
    }

    /* renamed from: a */
    public final boolean mo8495a(int[] iArr) {
        if (!mo8496b()) {
            return false;
        }
        ColorStateList colorStateList = this.f14047c;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        if (colorForState == this.f14046b) {
            return false;
        }
        this.f14046b = colorForState;
        return true;
    }

    /* renamed from: c */
    public final boolean mo8497c() {
        return mo8494a() || this.f14046b != 0;
    }
}
