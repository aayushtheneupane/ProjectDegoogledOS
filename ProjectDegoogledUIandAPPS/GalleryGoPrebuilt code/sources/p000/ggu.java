package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;

/* renamed from: ggu */
/* compiled from: PG */
public class ggu extends Drawable implements ghm {

    /* renamed from: g */
    private static final Paint f11292g = new Paint(1);

    /* renamed from: a */
    public ggt f11293a;

    /* renamed from: b */
    public final ghk[] f11294b;

    /* renamed from: c */
    public final ghk[] f11295c;

    /* renamed from: d */
    public boolean f11296d;

    /* renamed from: e */
    public Rect f11297e;

    /* renamed from: f */
    public boolean f11298f;

    /* renamed from: h */
    private final Matrix f11299h;

    /* renamed from: i */
    private final Path f11300i;

    /* renamed from: j */
    private final Path f11301j;

    /* renamed from: k */
    private final RectF f11302k;

    /* renamed from: l */
    private final RectF f11303l;

    /* renamed from: m */
    private final Region f11304m;

    /* renamed from: n */
    private final Region f11305n;

    /* renamed from: o */
    private gha f11306o;

    /* renamed from: p */
    private final Paint f11307p;

    /* renamed from: q */
    private final Paint f11308q;

    /* renamed from: r */
    private final ggl f11309r;

    /* renamed from: s */
    private final ghb f11310s;

    /* renamed from: t */
    private final ghd f11311t;

    /* renamed from: u */
    private PorterDuffColorFilter f11312u;

    /* renamed from: v */
    private PorterDuffColorFilter f11313v;

    /* renamed from: w */
    private final RectF f11314w;

    /* renamed from: a */
    private static int m10285a(int i, int i2) {
        return (i * (i2 + (i2 >>> 7))) >>> 8;
    }

    public final Drawable.ConstantState getConstantState() {
        return this.f11293a;
    }

    public final int getOpacity() {
        return -3;
    }

    public ggu() {
        this(new gha());
    }

    public ggu(Context context, AttributeSet attributeSet, int i, int i2) {
        this(gha.m10331a(context, attributeSet, i, i2).mo6660a());
    }

    private ggu(ggt ggt) {
        this.f11294b = new ghk[4];
        this.f11295c = new ghk[4];
        this.f11299h = new Matrix();
        this.f11300i = new Path();
        this.f11301j = new Path();
        this.f11302k = new RectF();
        this.f11303l = new RectF();
        this.f11304m = new Region();
        this.f11305n = new Region();
        this.f11307p = new Paint(1);
        this.f11308q = new Paint(1);
        this.f11309r = new ggl();
        this.f11311t = new ghd();
        this.f11314w = new RectF();
        this.f11298f = true;
        this.f11293a = ggt;
        this.f11308q.setStyle(Paint.Style.STROKE);
        this.f11307p.setStyle(Paint.Style.FILL);
        f11292g.setColor(-1);
        f11292g.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        m10298k();
        m10291a(getState());
        this.f11310s = new ggr(this);
    }

    public /* synthetic */ ggu(ggt ggt, byte[] bArr) {
        this(ggt);
    }

    public ggu(gha gha) {
        this(new ggt(gha));
    }

    /* renamed from: a */
    private final void m10290a(RectF rectF, Path path) {
        ghd ghd = this.f11311t;
        ggt ggt = this.f11293a;
        gha gha = ggt.f11274a;
        float f = ggt.f11283j;
        ghd.mo6673a(gha, f, rectF, this.f11310s, path);
        if (this.f11293a.f11282i != 1.0f) {
            this.f11299h.reset();
            Matrix matrix = this.f11299h;
            float f2 = this.f11293a.f11282i;
            matrix.setScale(f2, f2, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.f11299h);
        }
        path.computeBounds(this.f11314w, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        r2 = r4.getColor();
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.graphics.PorterDuffColorFilter m10286a(android.content.res.ColorStateList r2, android.graphics.PorterDuff.Mode r3, android.graphics.Paint r4, boolean r5) {
        /*
            r1 = this;
            r0 = 0
            if (r2 == 0) goto L_0x001c
            if (r3 == 0) goto L_0x001c
            int[] r4 = r1.getState()
            r0 = 0
            int r2 = r2.getColorForState(r4, r0)
            if (r5 == 0) goto L_0x0015
            int r2 = r1.m10284a((int) r2)
            goto L_0x0016
        L_0x0015:
        L_0x0016:
            android.graphics.PorterDuffColorFilter r0 = new android.graphics.PorterDuffColorFilter
            r0.<init>(r2, r3)
            goto L_0x002a
        L_0x001c:
            if (r5 != 0) goto L_0x001f
        L_0x001e:
            goto L_0x002a
        L_0x001f:
            int r2 = r4.getColor()
            int r3 = r1.m10284a((int) r2)
            if (r3 != r2) goto L_0x002b
            goto L_0x001e
        L_0x002a:
            return r0
        L_0x002b:
            android.graphics.PorterDuffColorFilter r2 = new android.graphics.PorterDuffColorFilter
            android.graphics.PorterDuff$Mode r4 = android.graphics.PorterDuff.Mode.SRC_IN
            r2.<init>(r3, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ggu.m10286a(android.content.res.ColorStateList, android.graphics.PorterDuff$Mode, android.graphics.Paint, boolean):android.graphics.PorterDuffColorFilter");
    }

    /* renamed from: a */
    private final int m10284a(int i) {
        float g = m10294g();
        ggt ggt = this.f11293a;
        float f = g + ggt.f11286m;
        gfk gfk = ggt.f11275b;
        if (gfk == null || !gfk.f11167a || C0238ip.m14267b(i, 255) != gfk.f11169c) {
            return i;
        }
        float f2 = gfk.f11170d;
        float f3 = 0.0f;
        if (f2 > 0.0f && f > 0.0f) {
            f3 = Math.min(((((float) Math.log1p((double) (f / f2))) * 4.5f) + 2.0f) / 100.0f, 1.0f);
        }
        return C0238ip.m14267b(ggf.m10243a(C0238ip.m14267b(i, 255), gfk.f11168b, f3), Color.alpha(i));
    }

    /* renamed from: a */
    public static ggu m10287a(Context context, float f) {
        int a = ggf.m10245a(context, ggu.class.getSimpleName());
        ggu ggu = new ggu();
        ggu.mo6634a(context);
        ggu.mo6635a(ColorStateList.valueOf(a));
        ggu.mo6637b(f);
        return ggu;
    }

    public void draw(Canvas canvas) {
        this.f11307p.setColorFilter(this.f11312u);
        int alpha = this.f11307p.getAlpha();
        this.f11307p.setAlpha(m10285a(alpha, this.f11293a.f11285l));
        this.f11308q.setColorFilter(this.f11313v);
        this.f11308q.setStrokeWidth(this.f11293a.f11284k);
        int alpha2 = this.f11308q.getAlpha();
        this.f11308q.setAlpha(m10285a(alpha2, this.f11293a.f11285l));
        if (this.f11296d) {
            float l = m10299l();
            gha a = mo6630a();
            ggs ggs = new ggs(-l);
            ggy b = a.mo6672b();
            b.f11318a = ggs.mo6627a(a.f11336a);
            b.f11319b = ggs.mo6627a(a.f11337b);
            b.f11321d = ggs.mo6627a(a.f11339d);
            b.f11320c = ggs.mo6627a(a.f11338c);
            gha a2 = b.mo6660a();
            this.f11306o = a2;
            this.f11311t.mo6673a(a2, this.f11293a.f11283j, m10300m(), (ghb) null, this.f11301j);
            m10290a(mo6636b(), this.f11300i);
            this.f11296d = false;
        }
        if (this.f11293a.f11288o > 0) {
            int i = Build.VERSION.SDK_INT;
            if (!mo6641e() && !this.f11300i.isConvex() && Build.VERSION.SDK_INT < 29) {
                canvas.save();
                int i2 = m10296i();
                int j = m10297j();
                int i3 = Build.VERSION.SDK_INT;
                canvas.translate((float) i2, (float) j);
                if (this.f11298f) {
                    int width = (int) (this.f11314w.width() - ((float) getBounds().width()));
                    int height = (int) (this.f11314w.height() - ((float) getBounds().height()));
                    float width2 = this.f11314w.width();
                    int i4 = this.f11293a.f11288o;
                    float height2 = this.f11314w.height();
                    int i5 = this.f11293a.f11288o;
                    Bitmap createBitmap = Bitmap.createBitmap(((int) width2) + i4 + i4 + width, ((int) height2) + i5 + i5 + height, Bitmap.Config.ARGB_8888);
                    Canvas canvas2 = new Canvas(createBitmap);
                    float f = (float) ((getBounds().left - this.f11293a.f11288o) - width);
                    float f2 = (float) ((getBounds().top - this.f11293a.f11288o) - height);
                    canvas2.translate(-f, -f2);
                    m10288a(canvas2);
                    canvas.drawBitmap(createBitmap, f, f2, (Paint) null);
                    createBitmap.recycle();
                    canvas.restore();
                } else {
                    m10288a(canvas);
                    canvas.restore();
                }
            }
        }
        if (this.f11293a.f11291r == Paint.Style.FILL_AND_STROKE || this.f11293a.f11291r == Paint.Style.FILL) {
            m10289a(canvas, this.f11307p, this.f11300i, this.f11293a.f11274a, mo6636b());
        }
        if (m10295h()) {
            m10289a(canvas, this.f11308q, this.f11301j, this.f11306o, m10300m());
        }
        this.f11307p.setAlpha(alpha);
        this.f11308q.setAlpha(alpha2);
    }

    /* renamed from: a */
    private final void m10288a(Canvas canvas) {
        if (this.f11293a.f11289p != 0) {
            canvas.drawPath(this.f11300i, this.f11309r.f11261a);
        }
        for (int i = 0; i < 4; i++) {
            this.f11294b[i].mo6677a(this.f11309r, this.f11293a.f11288o, canvas);
            this.f11295c[i].mo6677a(this.f11309r, this.f11293a.f11288o, canvas);
        }
        if (this.f11298f) {
            int i2 = m10296i();
            int j = m10297j();
            canvas.translate((float) (-i2), (float) (-j));
            canvas.drawPath(this.f11300i, f11292g);
            canvas.translate((float) i2, (float) j);
        }
    }

    /* renamed from: a */
    private static final void m10289a(Canvas canvas, Paint paint, Path path, gha gha, RectF rectF) {
        if (gha.mo6671a(rectF)) {
            float a = gha.f11337b.mo6621a(rectF);
            canvas.drawRoundRect(rectF, a, a, paint);
            return;
        }
        canvas.drawPath(path, paint);
    }

    /* renamed from: b */
    public final RectF mo6636b() {
        this.f11302k.set(getBounds());
        return this.f11302k;
    }

    /* renamed from: m */
    private final RectF m10300m() {
        this.f11303l.set(mo6636b());
        float l = m10299l();
        this.f11303l.inset(l, l);
        return this.f11303l;
    }

    public final void getOutline(Outline outline) {
        if (mo6641e()) {
            outline.setRoundRect(getBounds(), mo6639d());
            return;
        }
        m10290a(mo6636b(), this.f11300i);
        if (this.f11300i.isConvex() || Build.VERSION.SDK_INT >= 29) {
            outline.setConvexPath(this.f11300i);
        }
    }

    public final boolean getPadding(Rect rect) {
        Rect rect2 = this.f11297e;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    /* renamed from: i */
    private final int m10296i() {
        double d = (double) this.f11293a.f11289p;
        double sin = Math.sin(Math.toRadians(0.0d));
        Double.isNaN(d);
        return (int) (d * sin);
    }

    /* renamed from: j */
    private final int m10297j() {
        double d = (double) this.f11293a.f11289p;
        double cos = Math.cos(Math.toRadians(0.0d));
        Double.isNaN(d);
        return (int) (d * cos);
    }

    /* renamed from: a */
    public final gha mo6630a() {
        return this.f11293a.f11274a;
    }

    /* renamed from: l */
    private final float m10299l() {
        if (m10295h()) {
            return this.f11308q.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    /* renamed from: d */
    public final float mo6639d() {
        return this.f11293a.f11274a.f11336a.mo6621a(mo6636b());
    }

    public final Region getTransparentRegion() {
        this.f11304m.set(getBounds());
        m10290a(mo6636b(), this.f11300i);
        this.f11305n.setPath(this.f11300i, this.f11304m);
        this.f11304m.op(this.f11305n, Region.Op.DIFFERENCE);
        return this.f11304m;
    }

    /* renamed from: g */
    private final float m10294g() {
        return this.f11293a.f11287n + 0.0f;
    }

    /* renamed from: h */
    private final boolean m10295h() {
        return (this.f11293a.f11291r == Paint.Style.FILL_AND_STROKE || this.f11293a.f11291r == Paint.Style.STROKE) && this.f11308q.getStrokeWidth() > 0.0f;
    }

    /* renamed from: a */
    public final void mo6634a(Context context) {
        this.f11293a.f11275b = new gfk(context);
        mo6638c();
    }

    public final void invalidateSelf() {
        this.f11296d = true;
        super.invalidateSelf();
    }

    /* renamed from: e */
    public final boolean mo6641e() {
        return this.f11293a.f11274a.mo6671a(mo6636b());
    }

    public final boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        if (super.isStateful() || (((colorStateList = this.f11293a.f11279f) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f11293a.f11278e) != null && colorStateList2.isStateful()) || ((colorStateList3 = this.f11293a.f11277d) != null && colorStateList3.isStateful())))) {
            return true;
        }
        return false;
    }

    public final Drawable mutate() {
        this.f11293a = new ggt(this.f11293a);
        return this;
    }

    /* access modifiers changed from: protected */
    public final void onBoundsChange(Rect rect) {
        this.f11296d = true;
        super.onBoundsChange(rect);
    }

    /* access modifiers changed from: protected */
    public final boolean onStateChange(int[] iArr) {
        boolean a = m10291a(iArr);
        boolean k = m10298k();
        boolean z = true;
        if (!a && !k) {
            z = false;
        }
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public final void setAlpha(int i) {
        ggt ggt = this.f11293a;
        if (ggt.f11285l != i) {
            ggt.f11285l = i;
            super.invalidateSelf();
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.f11293a.f11276c = colorFilter;
        super.invalidateSelf();
    }

    /* renamed from: b */
    public final void mo6637b(float f) {
        ggt ggt = this.f11293a;
        if (ggt.f11287n != f) {
            ggt.f11287n = f;
            mo6638c();
        }
    }

    /* renamed from: a */
    public final void mo6635a(ColorStateList colorStateList) {
        ggt ggt = this.f11293a;
        if (ggt.f11277d != colorStateList) {
            ggt.f11277d = colorStateList;
            onStateChange(getState());
        }
    }

    /* renamed from: a */
    public final void mo6631a(float f) {
        ggt ggt = this.f11293a;
        if (ggt.f11283j != f) {
            ggt.f11283j = f;
            this.f11296d = true;
            invalidateSelf();
        }
    }

    /* renamed from: f */
    public final void mo6642f() {
        this.f11309r.mo6620a(-12303292);
        this.f11293a.f11290q = false;
        super.invalidateSelf();
    }

    /* renamed from: a */
    public final void mo3619a(gha gha) {
        this.f11293a.f11274a = gha;
        invalidateSelf();
    }

    /* renamed from: a */
    public final void mo6632a(float f, int i) {
        m10293c(f);
        m10292b(ColorStateList.valueOf(i));
    }

    /* renamed from: a */
    public final void mo6633a(float f, ColorStateList colorStateList) {
        m10293c(f);
        m10292b(colorStateList);
    }

    /* renamed from: b */
    private final void m10292b(ColorStateList colorStateList) {
        ggt ggt = this.f11293a;
        if (ggt.f11278e != colorStateList) {
            ggt.f11278e = colorStateList;
            onStateChange(getState());
        }
    }

    /* renamed from: c */
    private final void m10293c(float f) {
        this.f11293a.f11284k = f;
        invalidateSelf();
    }

    public final void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    public final void setTintList(ColorStateList colorStateList) {
        this.f11293a.f11279f = colorStateList;
        m10298k();
        super.invalidateSelf();
    }

    public final void setTintMode(PorterDuff.Mode mode) {
        ggt ggt = this.f11293a;
        if (ggt.f11280g != mode) {
            ggt.f11280g = mode;
            m10298k();
            super.invalidateSelf();
        }
    }

    /* renamed from: a */
    private final boolean m10291a(int[] iArr) {
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        boolean z = false;
        if (!(this.f11293a.f11277d == null || (color2 = this.f11307p.getColor()) == (colorForState2 = this.f11293a.f11277d.getColorForState(iArr, color2)))) {
            this.f11307p.setColor(colorForState2);
            z = true;
        }
        if (this.f11293a.f11278e == null || (color = this.f11308q.getColor()) == (colorForState = this.f11293a.f11278e.getColorForState(iArr, color))) {
            return z;
        }
        this.f11308q.setColor(colorForState);
        return true;
    }

    /* renamed from: k */
    private final boolean m10298k() {
        PorterDuffColorFilter porterDuffColorFilter = this.f11312u;
        PorterDuffColorFilter porterDuffColorFilter2 = this.f11313v;
        ggt ggt = this.f11293a;
        this.f11312u = m10286a(ggt.f11279f, ggt.f11280g, this.f11307p, true);
        this.f11313v = m10286a((ColorStateList) null, this.f11293a.f11280g, this.f11308q, false);
        if (!C0321lr.m14631a((Object) porterDuffColorFilter, (Object) this.f11312u) || !C0321lr.m14631a((Object) porterDuffColorFilter2, (Object) this.f11313v)) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    public final void mo6638c() {
        float g = m10294g();
        this.f11293a.f11288o = (int) Math.ceil((double) (0.75f * g));
        this.f11293a.f11289p = (int) Math.ceil((double) (g * 0.25f));
        m10298k();
        super.invalidateSelf();
    }
}
