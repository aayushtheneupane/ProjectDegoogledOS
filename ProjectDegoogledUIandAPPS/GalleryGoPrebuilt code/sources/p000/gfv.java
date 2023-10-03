package p000;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;

/* renamed from: gfv */
/* compiled from: PG */
public final class gfv {

    /* renamed from: A */
    public ColorStateList f11181A;

    /* renamed from: B */
    public ggj f11182B;

    /* renamed from: C */
    private final RectF f11183C;

    /* renamed from: D */
    private int f11184D = 16;

    /* renamed from: E */
    private int f11185E = 16;

    /* renamed from: F */
    private float f11186F;

    /* renamed from: G */
    private float f11187G;

    /* renamed from: H */
    private float f11188H;

    /* renamed from: I */
    private float f11189I;

    /* renamed from: J */
    private Typeface f11190J;

    /* renamed from: K */
    private Typeface f11191K;

    /* renamed from: L */
    private float f11192L;

    /* renamed from: a */
    public final View f11193a;

    /* renamed from: b */
    public boolean f11194b;

    /* renamed from: c */
    public float f11195c;

    /* renamed from: d */
    public final Rect f11196d;

    /* renamed from: e */
    public final Rect f11197e;

    /* renamed from: f */
    public float f11198f = 15.0f;

    /* renamed from: g */
    public float f11199g = 15.0f;

    /* renamed from: h */
    public ColorStateList f11200h;

    /* renamed from: i */
    public ColorStateList f11201i;

    /* renamed from: j */
    public float f11202j;

    /* renamed from: k */
    public float f11203k;

    /* renamed from: l */
    public Typeface f11204l;

    /* renamed from: m */
    public CharSequence f11205m;

    /* renamed from: n */
    public CharSequence f11206n;

    /* renamed from: o */
    public boolean f11207o;

    /* renamed from: p */
    public boolean f11208p;

    /* renamed from: q */
    public float f11209q;

    /* renamed from: r */
    public int[] f11210r;

    /* renamed from: s */
    public boolean f11211s;

    /* renamed from: t */
    public final TextPaint f11212t;

    /* renamed from: u */
    public final TextPaint f11213u;

    /* renamed from: v */
    public TimeInterpolator f11214v;

    /* renamed from: w */
    public TimeInterpolator f11215w;

    /* renamed from: x */
    public float f11216x;

    /* renamed from: y */
    public float f11217y;

    /* renamed from: z */
    public float f11218z;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    public gfv(View view) {
        this.f11193a = view;
        this.f11212t = new TextPaint(129);
        this.f11213u = new TextPaint(this.f11212t);
        this.f11197e = new Rect();
        this.f11196d = new Rect();
        this.f11183C = new RectF();
    }

    /* renamed from: a */
    private static int m10214a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((((float) Color.alpha(i)) * f2) + (((float) Color.alpha(i2)) * f)), (int) ((((float) Color.red(i)) * f2) + (((float) Color.red(i2)) * f)), (int) ((((float) Color.green(i)) * f2) + (((float) Color.green(i2)) * f)), (int) ((((float) Color.blue(i)) * f2) + (((float) Color.blue(i2)) * f)));
    }

    /* renamed from: a */
    public final float mo6591a() {
        if (this.f11205m == null) {
            return 0.0f;
        }
        m10215a(this.f11213u);
        TextPaint textPaint = this.f11213u;
        CharSequence charSequence = this.f11205m;
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    /* renamed from: e */
    private final void m10221e() {
        float f = this.f11195c;
        this.f11183C.left = m10213a((float) this.f11196d.left, (float) this.f11197e.left, f, this.f11214v);
        this.f11183C.top = m10213a(this.f11186F, this.f11187G, f, this.f11214v);
        this.f11183C.right = m10213a((float) this.f11196d.right, (float) this.f11197e.right, f, this.f11214v);
        this.f11183C.bottom = m10213a((float) this.f11196d.bottom, (float) this.f11197e.bottom, f, this.f11214v);
        this.f11202j = m10213a(this.f11188H, this.f11189I, f, this.f11214v);
        this.f11203k = m10213a(this.f11186F, this.f11187G, f, this.f11214v);
        m10218b(m10213a(this.f11198f, this.f11199g, f, this.f11215w));
        ColorStateList colorStateList = this.f11201i;
        ColorStateList colorStateList2 = this.f11200h;
        if (colorStateList == colorStateList2) {
            this.f11212t.setColor(m10222f());
        } else {
            this.f11212t.setColor(m10214a(m10219c(colorStateList2), m10222f(), f));
        }
        this.f11212t.setShadowLayer(m10213a(0.0f, this.f11216x, f, (TimeInterpolator) null), m10213a(0.0f, this.f11217y, f, (TimeInterpolator) null), m10213a(0.0f, this.f11218z, f, (TimeInterpolator) null), m10214a(m10219c((ColorStateList) null), m10219c(this.f11181A), f));
        C0340mj.m14710d(this.f11193a);
    }

    /* renamed from: a */
    public final boolean mo6596a(CharSequence charSequence) {
        C0281ke keVar;
        if (C0340mj.m14714f(this.f11193a) != 1) {
            keVar = C0288kl.f15133a;
        } else {
            keVar = C0288kl.f15134b;
        }
        int length = charSequence.length();
        if (charSequence == null || length < 0 || charSequence.length() - length < 0) {
            throw new IllegalArgumentException();
        }
        C0284kh khVar = ((C0285ki) keVar).f15130a;
        if (khVar == null) {
            return ((C0286kj) keVar).f15131b;
        }
        int a = khVar.mo9183a(charSequence, length);
        if (a == 0) {
            return true;
        }
        if (a != 1) {
            return ((C0286kj) keVar).f15131b;
        }
        return false;
    }

    /* renamed from: c */
    private final void m10220c(float f) {
        float f2;
        boolean z;
        boolean z2;
        if (this.f11205m != null) {
            float width = (float) this.f11197e.width();
            float width2 = (float) this.f11196d.width();
            boolean z3 = false;
            if (m10216a(f, this.f11199g)) {
                f2 = this.f11199g;
                this.f11209q = 1.0f;
                Typeface typeface = this.f11191K;
                Typeface typeface2 = this.f11190J;
                if (typeface != typeface2) {
                    this.f11191K = typeface2;
                    z = true;
                } else {
                    z = false;
                }
            } else {
                float f3 = this.f11198f;
                Typeface typeface3 = this.f11191K;
                Typeface typeface4 = this.f11204l;
                if (typeface3 != typeface4) {
                    this.f11191K = typeface4;
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (m10216a(f, f3)) {
                    this.f11209q = 1.0f;
                } else {
                    this.f11209q = f / this.f11198f;
                }
                float f4 = this.f11199g / this.f11198f;
                if (width2 * f4 > width) {
                    width = Math.min(width / f4, width2);
                    f2 = f3;
                    z = z2;
                } else {
                    width = width2;
                    f2 = f3;
                    z = z2;
                }
            }
            if (width > 0.0f) {
                z = this.f11192L != f2 || this.f11211s || z;
                this.f11192L = f2;
                this.f11211s = false;
            }
            if (this.f11206n == null || z) {
                this.f11212t.setTextSize(this.f11192L);
                this.f11212t.setTypeface(this.f11191K);
                TextPaint textPaint = this.f11212t;
                if (this.f11209q != 1.0f) {
                    z3 = true;
                }
                textPaint.setLinearText(z3);
                CharSequence ellipsize = TextUtils.ellipsize(this.f11205m, this.f11212t, width, TextUtils.TruncateAt.END);
                if (!TextUtils.equals(ellipsize, this.f11206n)) {
                    this.f11206n = ellipsize;
                    this.f11207o = mo6596a(ellipsize);
                }
            }
        }
    }

    /* renamed from: b */
    public final float mo6597b() {
        m10215a(this.f11213u);
        return -this.f11213u.ascent();
    }

    /* renamed from: f */
    private final int m10222f() {
        return m10219c(this.f11201i);
    }

    /* renamed from: c */
    private final int m10219c(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.f11210r;
        if (iArr != null) {
            return colorStateList.getColorForState(iArr, 0);
        }
        return colorStateList.getDefaultColor();
    }

    /* renamed from: a */
    private final void m10215a(TextPaint textPaint) {
        textPaint.setTextSize(this.f11199g);
        textPaint.setTypeface(this.f11190J);
    }

    /* renamed from: a */
    private static boolean m10216a(float f, float f2) {
        return Math.abs(f - f2) < 0.001f;
    }

    /* renamed from: a */
    private static float m10213a(float f, float f2, float f3, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f3 = timeInterpolator.getInterpolation(f3);
        }
        return gci.m10013a(f, f2, f3);
    }

    /* renamed from: c */
    public final void mo6600c() {
        boolean z = false;
        if (this.f11197e.width() > 0 && this.f11197e.height() > 0 && this.f11196d.width() > 0 && this.f11196d.height() > 0) {
            z = true;
        }
        this.f11194b = z;
    }

    /* renamed from: d */
    public final void mo6601d() {
        if (this.f11193a.getHeight() > 0 && this.f11193a.getWidth() > 0) {
            float f = this.f11192L;
            m10220c(this.f11199g);
            CharSequence charSequence = this.f11206n;
            float f2 = 0.0f;
            float measureText = charSequence != null ? this.f11212t.measureText(charSequence, 0, charSequence.length()) : 0.0f;
            int a = C0321lr.m14621a(this.f11185E, this.f11207o ? 1 : 0);
            int i = a & 112;
            if (i == 48) {
                this.f11187G = ((float) this.f11197e.top) - this.f11212t.ascent();
            } else if (i != 80) {
                this.f11187G = ((float) this.f11197e.centerY()) + (((this.f11212t.descent() - this.f11212t.ascent()) / 2.0f) - this.f11212t.descent());
            } else {
                this.f11187G = (float) this.f11197e.bottom;
            }
            int i2 = a & 8388615;
            if (i2 == 1) {
                this.f11189I = ((float) this.f11197e.centerX()) - (measureText / 2.0f);
            } else if (i2 != 5) {
                this.f11189I = (float) this.f11197e.left;
            } else {
                this.f11189I = ((float) this.f11197e.right) - measureText;
            }
            m10220c(this.f11198f);
            CharSequence charSequence2 = this.f11206n;
            if (charSequence2 != null) {
                f2 = this.f11212t.measureText(charSequence2, 0, charSequence2.length());
            }
            int a2 = C0321lr.m14621a(this.f11184D, this.f11207o ? 1 : 0);
            int i3 = a2 & 112;
            if (i3 == 48) {
                this.f11186F = ((float) this.f11196d.top) - this.f11212t.ascent();
            } else if (i3 != 80) {
                this.f11186F = ((float) this.f11196d.centerY()) + (((this.f11212t.descent() - this.f11212t.ascent()) / 2.0f) - this.f11212t.descent());
            } else {
                this.f11186F = (float) this.f11196d.bottom;
            }
            int i4 = a2 & 8388615;
            if (i4 == 1) {
                this.f11188H = ((float) this.f11196d.centerX()) - (f2 / 2.0f);
            } else if (i4 != 5) {
                this.f11188H = (float) this.f11196d.left;
            } else {
                this.f11188H = ((float) this.f11196d.right) - f2;
            }
            m10218b(f);
            m10221e();
        }
    }

    /* renamed from: a */
    public static boolean m10217a(Rect rect, int i, int i2, int i3, int i4) {
        return rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4;
    }

    /* renamed from: a */
    public final void mo6594a(ColorStateList colorStateList) {
        if (this.f11201i != colorStateList) {
            this.f11201i = colorStateList;
            mo6601d();
        }
    }

    /* renamed from: b */
    public final void mo6598b(int i) {
        if (this.f11185E != i) {
            this.f11185E = i;
            mo6601d();
        }
    }

    /* renamed from: a */
    public final boolean mo6595a(Typeface typeface) {
        ggj ggj = this.f11182B;
        if (ggj != null) {
            ggj.mo6617a();
        }
        if (this.f11190J == typeface) {
            return false;
        }
        this.f11190J = typeface;
        return true;
    }

    /* renamed from: b */
    public final void mo6599b(ColorStateList colorStateList) {
        if (this.f11200h != colorStateList) {
            this.f11200h = colorStateList;
            mo6601d();
        }
    }

    /* renamed from: a */
    public final void mo6593a(int i) {
        if (this.f11184D != i) {
            this.f11184D = i;
            mo6601d();
        }
    }

    /* renamed from: a */
    public final void mo6592a(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        if (f != this.f11195c) {
            this.f11195c = f;
            m10221e();
        }
    }

    /* renamed from: b */
    private final void m10218b(float f) {
        m10220c(f);
        this.f11208p = false;
        C0340mj.m14710d(this.f11193a);
    }
}
