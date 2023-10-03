package p000;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* renamed from: agu */
/* compiled from: PG */
final class agu extends Drawable.ConstantState {

    /* renamed from: a */
    public int f438a;

    /* renamed from: b */
    public agt f439b;

    /* renamed from: c */
    public ColorStateList f440c;

    /* renamed from: d */
    public PorterDuff.Mode f441d;

    /* renamed from: e */
    public boolean f442e;

    /* renamed from: f */
    public Bitmap f443f;

    /* renamed from: g */
    public ColorStateList f444g;

    /* renamed from: h */
    public PorterDuff.Mode f445h;

    /* renamed from: i */
    public int f446i;

    /* renamed from: j */
    public boolean f447j;

    /* renamed from: k */
    public boolean f448k;

    /* renamed from: l */
    public Paint f449l;

    public agu() {
        this.f440c = null;
        this.f441d = agw.f451b;
        this.f439b = new agt();
    }

    public int getChangingConfigurations() {
        return this.f438a;
    }

    public agu(agu agu) {
        this.f440c = null;
        this.f441d = agw.f451b;
        if (agu != null) {
            this.f438a = agu.f438a;
            this.f439b = new agt(agu.f439b);
            Paint paint = agu.f439b.f424c;
            if (paint != null) {
                this.f439b.f424c = new Paint(paint);
            }
            Paint paint2 = agu.f439b.f423b;
            if (paint2 != null) {
                this.f439b.f423b = new Paint(paint2);
            }
            this.f440c = agu.f440c;
            this.f441d = agu.f441d;
            this.f442e = agu.f442e;
        }
    }

    /* renamed from: a */
    public final boolean mo421a() {
        agt agt = this.f439b;
        if (agt.f431j == null) {
            agt.f431j = Boolean.valueOf(agt.f425d.mo378b());
        }
        return agt.f431j.booleanValue();
    }

    public final Drawable newDrawable() {
        return new agw(this);
    }

    public final Drawable newDrawable(Resources resources) {
        return new agw(this);
    }

    /* renamed from: a */
    public final void mo420a(int i, int i2) {
        this.f443f.eraseColor(0);
        Canvas canvas = new Canvas(this.f443f);
        agt agt = this.f439b;
        agt.mo415a(agt.f425d, agt.f422a, canvas, i, i2);
    }
}
