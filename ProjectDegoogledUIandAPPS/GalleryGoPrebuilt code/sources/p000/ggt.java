package p000;

import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* renamed from: ggt */
/* compiled from: PG */
public final class ggt extends Drawable.ConstantState {

    /* renamed from: a */
    public gha f11274a;

    /* renamed from: b */
    public gfk f11275b;

    /* renamed from: c */
    public ColorFilter f11276c;

    /* renamed from: d */
    public ColorStateList f11277d = null;

    /* renamed from: e */
    public ColorStateList f11278e = null;

    /* renamed from: f */
    public ColorStateList f11279f = null;

    /* renamed from: g */
    public PorterDuff.Mode f11280g = PorterDuff.Mode.SRC_IN;

    /* renamed from: h */
    public Rect f11281h = null;

    /* renamed from: i */
    public float f11282i = 1.0f;

    /* renamed from: j */
    public float f11283j = 1.0f;

    /* renamed from: k */
    public float f11284k;

    /* renamed from: l */
    public int f11285l = 255;

    /* renamed from: m */
    public float f11286m = 0.0f;

    /* renamed from: n */
    public float f11287n = 0.0f;

    /* renamed from: o */
    public int f11288o = 0;

    /* renamed from: p */
    public int f11289p = 0;

    /* renamed from: q */
    public boolean f11290q = false;

    /* renamed from: r */
    public Paint.Style f11291r = Paint.Style.FILL_AND_STROKE;

    public ggt(ggt ggt) {
        this.f11274a = ggt.f11274a;
        this.f11275b = ggt.f11275b;
        this.f11284k = ggt.f11284k;
        this.f11276c = ggt.f11276c;
        this.f11277d = ggt.f11277d;
        this.f11278e = ggt.f11278e;
        this.f11280g = ggt.f11280g;
        this.f11279f = ggt.f11279f;
        this.f11285l = ggt.f11285l;
        this.f11282i = ggt.f11282i;
        this.f11289p = ggt.f11289p;
        this.f11290q = false;
        this.f11283j = ggt.f11283j;
        this.f11286m = ggt.f11286m;
        this.f11287n = ggt.f11287n;
        this.f11288o = ggt.f11288o;
        this.f11291r = ggt.f11291r;
        Rect rect = ggt.f11281h;
        if (rect != null) {
            this.f11281h = new Rect(rect);
        }
    }

    public final int getChangingConfigurations() {
        return 0;
    }

    public ggt(gha gha) {
        this.f11274a = gha;
        this.f11275b = null;
    }

    public final Drawable newDrawable() {
        ggu ggu = new ggu(this, (byte[]) null);
        ggu.f11296d = true;
        return ggu;
    }
}
