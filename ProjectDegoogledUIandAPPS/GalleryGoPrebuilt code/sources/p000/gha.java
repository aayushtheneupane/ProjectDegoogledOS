package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* renamed from: gha */
/* compiled from: PG */
public final class gha {

    /* renamed from: a */
    public final ggo f11336a;

    /* renamed from: b */
    public final ggo f11337b;

    /* renamed from: c */
    public final ggo f11338c;

    /* renamed from: d */
    public final ggo f11339d;

    /* renamed from: e */
    public final ggq f11340e;

    /* renamed from: f */
    public final ggq f11341f;

    /* renamed from: g */
    public final ggq f11342g;

    /* renamed from: h */
    public final ggq f11343h;

    /* renamed from: i */
    public final gqb f11344i;

    /* renamed from: j */
    public final gqb f11345j;

    /* renamed from: k */
    public final gqb f11346k;

    /* renamed from: l */
    public final gqb f11347l;

    static {
        new ggw(0.5f);
    }

    public gha() {
        this.f11344i = ggq.m10282b();
        this.f11345j = ggq.m10282b();
        this.f11346k = ggq.m10282b();
        this.f11347l = ggq.m10282b();
        this.f11336a = new ggm(0.0f);
        this.f11337b = new ggm(0.0f);
        this.f11338c = new ggm(0.0f);
        this.f11339d = new ggm(0.0f);
        this.f11340e = ggq.m10277a();
        this.f11341f = ggq.m10277a();
        this.f11342g = ggq.m10277a();
        this.f11343h = ggq.m10277a();
    }

    public /* synthetic */ gha(ggy ggy) {
        this.f11344i = ggy.f11326i;
        this.f11345j = ggy.f11327j;
        this.f11346k = ggy.f11328k;
        this.f11347l = ggy.f11329l;
        this.f11336a = ggy.f11318a;
        this.f11337b = ggy.f11319b;
        this.f11338c = ggy.f11320c;
        this.f11339d = ggy.f11321d;
        this.f11340e = ggy.f11322e;
        this.f11341f = ggy.f11323f;
        this.f11342g = ggy.f11324g;
        this.f11343h = ggy.f11325h;
    }

    /* renamed from: a */
    public static ggy m10328a() {
        return new ggy();
    }

    /* renamed from: a */
    public static ggy m10329a(Context context, int i, int i2) {
        return m10330a(context, i, i2, (ggo) new ggm(0.0f));
    }

    /* renamed from: a */
    private static ggy m10330a(Context context, int i, int i2, ggo ggo) {
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            i = i2;
            context = contextThemeWrapper;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, ggv.f11316b);
        try {
            int i3 = obtainStyledAttributes.getInt(0, 0);
            int i4 = obtainStyledAttributes.getInt(3, i3);
            int i5 = obtainStyledAttributes.getInt(4, i3);
            int i6 = obtainStyledAttributes.getInt(2, i3);
            int i7 = obtainStyledAttributes.getInt(1, i3);
            ggo a = m10327a(obtainStyledAttributes, 5, ggo);
            ggo a2 = m10327a(obtainStyledAttributes, 8, a);
            ggo a3 = m10327a(obtainStyledAttributes, 9, a);
            ggo a4 = m10327a(obtainStyledAttributes, 7, a);
            ggo a5 = m10327a(obtainStyledAttributes, 6, a);
            ggy ggy = new ggy();
            gqb a6 = ggq.m10278a(i4);
            ggy.f11326i = a6;
            ggy.m10316a(a6);
            ggy.f11318a = a2;
            gqb a7 = ggq.m10278a(i5);
            ggy.f11327j = a7;
            ggy.m10316a(a7);
            ggy.f11319b = a3;
            gqb a8 = ggq.m10278a(i6);
            ggy.f11328k = a8;
            ggy.m10316a(a8);
            ggy.f11320c = a4;
            gqb a9 = ggq.m10278a(i7);
            ggy.f11329l = a9;
            ggy.m10316a(a9);
            ggy.f11321d = a5;
            return ggy;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* renamed from: a */
    public static ggy m10331a(Context context, AttributeSet attributeSet, int i, int i2) {
        ggm ggm = new ggm(0.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ggv.f11315a, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        return m10330a(context, resourceId, resourceId2, (ggo) ggm);
    }

    /* renamed from: a */
    private static ggo m10327a(TypedArray typedArray, int i, ggo ggo) {
        TypedValue peekValue = typedArray.peekValue(i);
        if (peekValue != null) {
            if (peekValue.type == 5) {
                return new ggm((float) TypedValue.complexToDimensionPixelSize(peekValue.data, typedArray.getResources().getDisplayMetrics()));
            }
            if (peekValue.type == 6) {
                return new ggw(peekValue.getFraction(1.0f, 1.0f));
            }
        }
        return ggo;
    }

    /* renamed from: a */
    public final boolean mo6671a(RectF rectF) {
        boolean z;
        boolean z2 = this.f11343h.getClass().equals(ggq.class) && this.f11341f.getClass().equals(ggq.class) && this.f11340e.getClass().equals(ggq.class) && this.f11342g.getClass().equals(ggq.class);
        float a = this.f11336a.mo6621a(rectF);
        if (this.f11337b.mo6621a(rectF) == a && this.f11339d.mo6621a(rectF) == a && this.f11338c.mo6621a(rectF) == a) {
            z = true;
        } else {
            z = false;
        }
        return z2 && z && ((this.f11345j instanceof ggx) && (this.f11344i instanceof ggx) && (this.f11346k instanceof ggx) && (this.f11347l instanceof ggx));
    }

    /* renamed from: b */
    public final ggy mo6672b() {
        return new ggy(this);
    }

    /* renamed from: a */
    public final gha mo6670a(float f) {
        ggy b = mo6672b();
        b.mo6665e(f);
        return b.mo6660a();
    }
}
