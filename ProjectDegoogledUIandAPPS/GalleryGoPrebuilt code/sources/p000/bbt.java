package p000;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import java.nio.ByteBuffer;

/* renamed from: bbt */
/* compiled from: PG */
public final class bbt extends Drawable implements Animatable, bby {

    /* renamed from: a */
    public final bbs f2006a;

    /* renamed from: b */
    public boolean f2007b;

    /* renamed from: c */
    private boolean f2008c;

    /* renamed from: d */
    private boolean f2009d;

    /* renamed from: e */
    private boolean f2010e;

    /* renamed from: f */
    private int f2011f;

    /* renamed from: g */
    private int f2012g;

    /* renamed from: h */
    private boolean f2013h;

    /* renamed from: i */
    private Paint f2014i;

    /* renamed from: j */
    private Rect f2015j;

    public bbt(Context context, apz apz, ard ard, int i, int i2, Bitmap bitmap) {
        this(new bbs(new bca(aow.m1346a(context), apz, i, i2, ard, bitmap)));
    }

    public final Drawable.ConstantState getConstantState() {
        return this.f2006a;
    }

    public final int getOpacity() {
        return -2;
    }

    public final boolean isRunning() {
        return this.f2008c;
    }

    public bbt(bbs bbs) {
        this.f2010e = true;
        this.f2012g = -1;
        this.f2006a = (bbs) cns.m4632a((Object) bbs);
    }

    public final void draw(Canvas canvas) {
        Bitmap bitmap;
        if (!this.f2007b) {
            if (this.f2013h) {
                Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), m2121f());
                this.f2013h = false;
            }
            bca bca = this.f2006a.f2005a;
            bbx bbx = bca.f2029e;
            if (bbx == null) {
                bitmap = bca.f2032h;
            } else {
                bitmap = bbx.f2018b;
            }
            canvas.drawBitmap(bitmap, (Rect) null, m2121f(), m2122g());
        }
    }

    /* renamed from: b */
    public final ByteBuffer mo1784b() {
        return ((aqd) this.f2006a.f2005a.f2025a).f1431b.asReadOnlyBuffer();
    }

    /* renamed from: f */
    private final Rect m2121f() {
        if (this.f2015j == null) {
            this.f2015j = new Rect();
        }
        return this.f2015j;
    }

    /* renamed from: a */
    public final Bitmap mo1783a() {
        return this.f2006a.f2005a.f2032h;
    }

    public final int getIntrinsicHeight() {
        return this.f2006a.f2005a.f2036l;
    }

    public final int getIntrinsicWidth() {
        return this.f2006a.f2005a.f2035k;
    }

    /* renamed from: g */
    private final Paint m2122g() {
        if (this.f2014i == null) {
            this.f2014i = new Paint(2);
        }
        return this.f2014i;
    }

    /* access modifiers changed from: protected */
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f2013h = true;
    }

    /* renamed from: c */
    public final void mo1785c() {
        Drawable.Callback callback = getCallback();
        while (callback instanceof Drawable) {
            callback = ((Drawable) callback).getCallback();
        }
        if (callback != null) {
            invalidateSelf();
            bca bca = this.f2006a.f2005a;
            bbx bbx = bca.f2029e;
            if ((bbx != null ? bbx.f2017a : -1) == bca.mo1800a() - 1) {
                this.f2011f++;
            }
            if (this.f2012g != -1 && this.f2011f >= 0) {
                stop();
                return;
            }
            return;
        }
        stop();
        invalidateSelf();
    }

    public final void setAlpha(int i) {
        m2122g().setAlpha(i);
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        m2122g().setColorFilter(colorFilter);
    }

    public final boolean setVisible(boolean z, boolean z2) {
        cns.m4637a(!this.f2007b, "Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.");
        this.f2010e = z;
        if (!z) {
            m2120e();
        } else if (this.f2009d) {
            m2119d();
        }
        return super.setVisible(z, z2);
    }

    public final void start() {
        this.f2009d = true;
        this.f2011f = 0;
        if (this.f2010e) {
            m2119d();
        }
    }

    /* renamed from: d */
    private final void m2119d() {
        cns.m4637a(!this.f2007b, "You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request.");
        if (this.f2006a.f2005a.mo1800a() == 1) {
            invalidateSelf();
        } else if (!this.f2008c) {
            this.f2008c = true;
            bca bca = this.f2006a.f2005a;
            if (bca.f2030f) {
                throw new IllegalStateException("Cannot subscribe to a cleared frame loader");
            } else if (!bca.f2026b.contains(this)) {
                boolean isEmpty = bca.f2026b.isEmpty();
                bca.f2026b.add(this);
                if (isEmpty && !bca.f2028d) {
                    bca.f2028d = true;
                    bca.f2030f = false;
                    bca.mo1804c();
                }
                invalidateSelf();
            } else {
                throw new IllegalStateException("Cannot subscribe twice in a row");
            }
        }
    }

    public final void stop() {
        this.f2009d = false;
        m2120e();
    }

    /* renamed from: e */
    private final void m2120e() {
        this.f2008c = false;
        bca bca = this.f2006a.f2005a;
        bca.f2026b.remove(this);
        if (bca.f2026b.isEmpty()) {
            bca.mo1803b();
        }
    }
}
