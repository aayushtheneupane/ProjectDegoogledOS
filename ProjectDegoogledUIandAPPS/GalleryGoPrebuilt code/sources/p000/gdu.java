package p000;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.R;
import com.google.android.material.card.MaterialCardView;

/* renamed from: gdu */
/* compiled from: PG */
public final class gdu {

    /* renamed from: a */
    public static final double f11054a = Math.cos(Math.toRadians(45.0d));

    /* renamed from: s */
    private static final int[] f11055s = {16842912};

    /* renamed from: b */
    public final MaterialCardView f11056b;

    /* renamed from: c */
    public final Rect f11057c = new Rect();

    /* renamed from: d */
    public final ggu f11058d;

    /* renamed from: e */
    public final ggu f11059e;

    /* renamed from: f */
    public final int f11060f;

    /* renamed from: g */
    public final int f11061g;

    /* renamed from: h */
    public int f11062h;

    /* renamed from: i */
    public Drawable f11063i;

    /* renamed from: j */
    public Drawable f11064j;

    /* renamed from: k */
    public ColorStateList f11065k;

    /* renamed from: l */
    public ColorStateList f11066l;

    /* renamed from: m */
    public ColorStateList f11067m;

    /* renamed from: n */
    public Drawable f11068n;

    /* renamed from: o */
    public LayerDrawable f11069o;

    /* renamed from: p */
    public ggu f11070p;

    /* renamed from: q */
    public boolean f11071q = false;

    /* renamed from: r */
    public boolean f11072r;

    /* renamed from: t */
    private gha f11073t;

    /* renamed from: u */
    private ggu f11074u;

    public gdu(MaterialCardView materialCardView, AttributeSet attributeSet, int i) {
        this.f11056b = materialCardView;
        ggu ggu = new ggu(materialCardView.getContext(), attributeSet, i, 2131952562);
        this.f11058d = ggu;
        ggu.mo6634a(materialCardView.getContext());
        this.f11058d.mo6642f();
        ggy b = this.f11058d.mo6630a().mo6672b();
        TypedArray obtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, gdv.f11075a, i, R.style.CardView);
        if (obtainStyledAttributes.hasValue(3)) {
            b.mo6665e(obtainStyledAttributes.getDimension(3, 0.0f));
        }
        this.f11059e = new ggu();
        mo6487a(b.mo6660a());
        Resources resources = materialCardView.getResources();
        this.f11060f = resources.getDimensionPixelSize(R.dimen.mtrl_card_checked_icon_margin);
        this.f11061g = resources.getDimensionPixelSize(R.dimen.mtrl_card_checked_icon_size);
        obtainStyledAttributes.recycle();
    }

    /* renamed from: c */
    public final float mo6490c() {
        float a = m10138a(this.f11073t.f11344i, this.f11058d.mo6639d());
        gqb gqb = this.f11073t.f11345j;
        ggu ggu = this.f11058d;
        float max = Math.max(a, m10138a(gqb, ggu.f11293a.f11274a.f11337b.mo6621a(ggu.mo6636b())));
        gqb gqb2 = this.f11073t.f11346k;
        ggu ggu2 = this.f11058d;
        float a2 = m10138a(gqb2, ggu2.f11293a.f11274a.f11338c.mo6621a(ggu2.mo6636b()));
        gqb gqb3 = this.f11073t.f11347l;
        ggu ggu3 = this.f11058d;
        return Math.max(max, Math.max(a2, m10138a(gqb3, ggu3.f11293a.f11274a.f11339d.mo6621a(ggu3.mo6636b()))));
    }

    /* renamed from: a */
    private static final float m10138a(gqb gqb, float f) {
        if (gqb instanceof ggx) {
            double d = (double) f;
            Double.isNaN(d);
            return (float) ((1.0d - f11054a) * d);
        } else if (gqb instanceof ggp) {
            return f / 2.0f;
        } else {
            return 0.0f;
        }
    }

    /* renamed from: a */
    public final boolean mo6488a() {
        int i = Build.VERSION.SDK_INT;
        return this.f11058d.mo6641e();
    }

    /* renamed from: e */
    public final Drawable mo6492e() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = this.f11064j;
        if (drawable != null) {
            stateListDrawable.addState(f11055s, drawable);
        }
        return stateListDrawable;
    }

    /* renamed from: f */
    private final ggu m10139f() {
        return new ggu(this.f11073t);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: android.graphics.drawable.RippleDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: android.graphics.drawable.StateListDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: android.graphics.drawable.RippleDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: android.graphics.drawable.RippleDrawable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.drawable.Drawable mo6491d() {
        /*
            r6 = this;
            android.graphics.drawable.Drawable r0 = r6.f11068n
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0039
            boolean r0 = p000.ggk.f11254a
            if (r0 != 0) goto L_0x0027
            android.graphics.drawable.StateListDrawable r0 = new android.graphics.drawable.StateListDrawable
            r0.<init>()
            ggu r3 = r6.m10139f()
            r6.f11070p = r3
            android.content.res.ColorStateList r4 = r6.f11065k
            r3.mo6635a((android.content.res.ColorStateList) r4)
            int[] r3 = new int[r1]
            r4 = 16842919(0x10100a7, float:2.3694026E-38)
            r3[r2] = r4
            ggu r4 = r6.f11070p
            r0.addState(r3, r4)
            goto L_0x0037
        L_0x0027:
            ggu r0 = r6.m10139f()
            r6.f11074u = r0
            android.graphics.drawable.RippleDrawable r0 = new android.graphics.drawable.RippleDrawable
            android.content.res.ColorStateList r3 = r6.f11065k
            r4 = 0
            ggu r5 = r6.f11074u
            r0.<init>(r3, r4, r5)
        L_0x0037:
            r6.f11068n = r0
        L_0x0039:
            android.graphics.drawable.LayerDrawable r0 = r6.f11069o
            if (r0 != 0) goto L_0x005c
            android.graphics.drawable.Drawable r0 = r6.mo6492e()
            android.graphics.drawable.LayerDrawable r3 = new android.graphics.drawable.LayerDrawable
            r4 = 3
            android.graphics.drawable.Drawable[] r4 = new android.graphics.drawable.Drawable[r4]
            android.graphics.drawable.Drawable r5 = r6.f11068n
            r4[r2] = r5
            ggu r2 = r6.f11059e
            r4[r1] = r2
            r1 = 2
            r4[r1] = r0
            r3.<init>(r4)
            r6.f11069o = r3
            r0 = 2131362097(0x7f0a0131, float:1.8343965E38)
            r3.setId(r1, r0)
        L_0x005c:
            android.graphics.drawable.LayerDrawable r0 = r6.f11069o
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.gdu.mo6491d():android.graphics.drawable.Drawable");
    }

    /* renamed from: a */
    public final Drawable mo6486a(Drawable drawable) {
        int i;
        int i2;
        int i3 = Build.VERSION.SDK_INT;
        MaterialCardView materialCardView = this.f11056b;
        if (materialCardView.f24b) {
            float f = 0.0f;
            int ceil = (int) Math.ceil((double) ((materialCardView.mo19a() * 1.5f) + (mo6489b() ? mo6490c() : 0.0f)));
            float a = this.f11056b.mo19a();
            if (mo6489b()) {
                f = mo6490c();
            }
            i = ceil;
            i2 = (int) Math.ceil((double) (a + f));
        } else {
            i2 = 0;
            i = 0;
        }
        return new gdt(drawable, i2, i, i2, i);
    }

    /* renamed from: a */
    public final void mo6487a(gha gha) {
        this.f11073t = gha;
        this.f11058d.mo3619a(gha);
        ggu ggu = this.f11058d;
        ggu.f11298f = !ggu.mo6641e();
        ggu ggu2 = this.f11059e;
        if (ggu2 != null) {
            ggu2.mo3619a(gha);
        }
        ggu ggu3 = this.f11074u;
        if (ggu3 != null) {
            ggu3.mo3619a(gha);
        }
        ggu ggu4 = this.f11070p;
        if (ggu4 != null) {
            ggu4.mo3619a(gha);
        }
    }

    /* renamed from: b */
    public final boolean mo6489b() {
        return this.f11056b.f25c && mo6488a() && this.f11056b.f24b;
    }
}
