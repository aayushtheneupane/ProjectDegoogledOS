package p000;

import android.graphics.Paint;

/* renamed from: agq */
/* compiled from: PG */
final class agq extends ags {

    /* renamed from: a */
    public C0229ig f397a;

    /* renamed from: b */
    public float f398b = 0.0f;

    /* renamed from: c */
    public C0229ig f399c;

    /* renamed from: d */
    public float f400d = 1.0f;

    /* renamed from: e */
    public float f401e = 1.0f;

    /* renamed from: f */
    public float f402f = 0.0f;

    /* renamed from: g */
    public float f403g = 1.0f;

    /* renamed from: h */
    public float f404h = 0.0f;

    /* renamed from: i */
    public Paint.Cap f405i = Paint.Cap.BUTT;

    /* renamed from: j */
    public Paint.Join f406j = Paint.Join.MITER;

    /* renamed from: k */
    public float f407k = 4.0f;

    public agq() {
    }

    /* access modifiers changed from: package-private */
    public float getFillAlpha() {
        return this.f401e;
    }

    /* access modifiers changed from: package-private */
    public int getFillColor() {
        return this.f399c.f14046b;
    }

    /* access modifiers changed from: package-private */
    public float getStrokeAlpha() {
        return this.f400d;
    }

    /* access modifiers changed from: package-private */
    public int getStrokeColor() {
        return this.f397a.f14046b;
    }

    /* access modifiers changed from: package-private */
    public float getStrokeWidth() {
        return this.f398b;
    }

    /* access modifiers changed from: package-private */
    public float getTrimPathEnd() {
        return this.f403g;
    }

    /* access modifiers changed from: package-private */
    public float getTrimPathOffset() {
        return this.f404h;
    }

    /* access modifiers changed from: package-private */
    public float getTrimPathStart() {
        return this.f402f;
    }

    public agq(agq agq) {
        super(agq);
        this.f397a = agq.f397a;
        this.f398b = agq.f398b;
        this.f400d = agq.f400d;
        this.f399c = agq.f399c;
        this.f421n = agq.f421n;
        this.f401e = agq.f401e;
        this.f402f = agq.f402f;
        this.f403g = agq.f403g;
        this.f404h = agq.f404h;
        this.f405i = agq.f405i;
        this.f406j = agq.f406j;
        this.f407k = agq.f407k;
    }

    /* renamed from: b */
    public final boolean mo378b() {
        return this.f399c.mo8496b() || this.f397a.mo8496b();
    }

    /* renamed from: a */
    public final boolean mo377a(int[] iArr) {
        return this.f397a.mo8495a(iArr) | this.f399c.mo8495a(iArr);
    }

    /* access modifiers changed from: package-private */
    public void setFillAlpha(float f) {
        this.f401e = f;
    }

    /* access modifiers changed from: package-private */
    public void setFillColor(int i) {
        this.f399c.f14046b = i;
    }

    /* access modifiers changed from: package-private */
    public void setStrokeAlpha(float f) {
        this.f400d = f;
    }

    /* access modifiers changed from: package-private */
    public void setStrokeColor(int i) {
        this.f397a.f14046b = i;
    }

    /* access modifiers changed from: package-private */
    public void setStrokeWidth(float f) {
        this.f398b = f;
    }

    /* access modifiers changed from: package-private */
    public void setTrimPathEnd(float f) {
        this.f403g = f;
    }

    /* access modifiers changed from: package-private */
    public void setTrimPathOffset(float f) {
        this.f404h = f;
    }

    /* access modifiers changed from: package-private */
    public void setTrimPathStart(float f) {
        this.f402f = f;
    }
}
