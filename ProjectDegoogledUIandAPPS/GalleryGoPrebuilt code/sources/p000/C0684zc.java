package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;

/* renamed from: zc */
/* compiled from: PG */
public final class C0684zc {

    /* renamed from: a */
    public final Context f16435a;

    /* renamed from: b */
    public final TypedArray f16436b;

    /* renamed from: c */
    public TypedValue f16437c;

    private C0684zc(Context context, TypedArray typedArray) {
        this.f16435a = context;
        this.f16436b = typedArray;
    }

    /* renamed from: a */
    public final boolean mo10725a(int i, boolean z) {
        return this.f16436b.getBoolean(i, z);
    }

    /* renamed from: h */
    public final int mo10737h(int i) {
        return this.f16436b.getColor(i, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0011, code lost:
        r0 = p000.C0436py.m15104a(r2.f16435a, (r0 = r2.f16436b.getResourceId(r3, 0)));
     */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.content.res.ColorStateList mo10733e(int r3) {
        /*
            r2 = this;
            android.content.res.TypedArray r0 = r2.f16436b
            boolean r0 = r0.hasValue(r3)
            if (r0 == 0) goto L_0x001b
            android.content.res.TypedArray r0 = r2.f16436b
            r1 = 0
            int r0 = r0.getResourceId(r3, r1)
            if (r0 == 0) goto L_0x001b
            android.content.Context r1 = r2.f16435a
            android.content.res.ColorStateList r0 = p000.C0436py.m15104a(r1, r0)
            if (r0 != 0) goto L_0x001a
            goto L_0x001b
        L_0x001a:
            return r0
        L_0x001b:
            android.content.res.TypedArray r0 = r2.f16436b
            android.content.res.ColorStateList r3 = r0.getColorStateList(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0684zc.mo10733e(int):android.content.res.ColorStateList");
    }

    /* renamed from: g */
    public final float mo10736g(int i) {
        return this.f16436b.getDimension(i, -1.0f);
    }

    /* renamed from: c */
    public final int mo10728c(int i, int i2) {
        return this.f16436b.getDimensionPixelOffset(i, i2);
    }

    /* renamed from: d */
    public final int mo10730d(int i, int i2) {
        return this.f16436b.getDimensionPixelSize(i, i2);
    }

    /* renamed from: a */
    public final Drawable mo10723a(int i) {
        int resourceId;
        if (!this.f16436b.hasValue(i) || (resourceId = this.f16436b.getResourceId(i, 0)) == 0) {
            return this.f16436b.getDrawable(i);
        }
        return C0436py.m15105b(this.f16435a, resourceId);
    }

    /* renamed from: b */
    public final Drawable mo10727b(int i) {
        int resourceId;
        if (!this.f16436b.hasValue(i) || (resourceId = this.f16436b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return C0529tj.m15440b().mo10134c(this.f16435a, resourceId);
    }

    /* renamed from: a */
    public final int mo10722a(int i, int i2) {
        return this.f16436b.getInt(i, i2);
    }

    /* renamed from: b */
    public final int mo10726b(int i, int i2) {
        return this.f16436b.getInteger(i, i2);
    }

    /* renamed from: e */
    public final int mo10732e(int i, int i2) {
        return this.f16436b.getLayoutDimension(i, i2);
    }

    /* renamed from: f */
    public final int mo10734f(int i, int i2) {
        return this.f16436b.getResourceId(i, i2);
    }

    /* renamed from: d */
    public final String mo10731d(int i) {
        return this.f16436b.getString(i);
    }

    /* renamed from: c */
    public final CharSequence mo10729c(int i) {
        return this.f16436b.getText(i);
    }

    /* renamed from: f */
    public final boolean mo10735f(int i) {
        return this.f16436b.hasValue(i);
    }

    /* renamed from: a */
    public static C0684zc m16190a(Context context, int i, int[] iArr) {
        return new C0684zc(context, context.obtainStyledAttributes(i, iArr));
    }

    /* renamed from: a */
    public static C0684zc m16191a(Context context, AttributeSet attributeSet, int[] iArr) {
        return new C0684zc(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    /* renamed from: a */
    public static C0684zc m16192a(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new C0684zc(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    /* renamed from: a */
    public final void mo10724a() {
        this.f16436b.recycle();
    }
}
