package p000a.p013g.p014a.p015a;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.core.content.p022a.C0308a;
import androidx.core.content.p022a.C0309b;
import androidx.core.graphics.PathParser;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: a.g.a.a.j */
class C0057j extends C0060m {

    /* renamed from: hu */
    C0309b f41hu;

    /* renamed from: iu */
    C0309b f42iu;

    /* renamed from: ju */
    float f43ju = 1.0f;

    /* renamed from: ku */
    float f44ku = 1.0f;

    /* renamed from: lu */
    float f45lu = 0.0f;
    float mStrokeWidth = 0.0f;
    private int[] mThemeAttrs;

    /* renamed from: mu */
    float f46mu = 1.0f;

    /* renamed from: nu */
    float f47nu = 0.0f;

    /* renamed from: ou */
    Paint.Cap f48ou = Paint.Cap.BUTT;

    /* renamed from: pu */
    Paint.Join f49pu = Paint.Join.MITER;

    /* renamed from: qu */
    float f50qu = 4.0f;

    C0057j() {
    }

    /* renamed from: a */
    public void mo317a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
        TypedArray a = C0308a.m241a(resources, theme, attributeSet, C0048a.f26Ot);
        this.mThemeAttrs = null;
        if (C0308a.m244a(xmlPullParser, "pathData")) {
            String string = a.getString(0);
            if (string != null) {
                this.mPathName = string;
            }
            String string2 = a.getString(2);
            if (string2 != null) {
                this.f59fu = PathParser.createNodesFromPathData(string2);
            }
            this.f42iu = C0308a.m242a(a, xmlPullParser, theme, "fillColor", 1, 0);
            this.f44ku = C0308a.m239a(a, xmlPullParser, "fillAlpha", 12, this.f44ku);
            int b = C0308a.m245b(a, xmlPullParser, "strokeLineCap", 8, -1);
            Paint.Cap cap = this.f48ou;
            if (b == 0) {
                cap = Paint.Cap.BUTT;
            } else if (b == 1) {
                cap = Paint.Cap.ROUND;
            } else if (b == 2) {
                cap = Paint.Cap.SQUARE;
            }
            this.f48ou = cap;
            int b2 = C0308a.m245b(a, xmlPullParser, "strokeLineJoin", 9, -1);
            Paint.Join join = this.f49pu;
            if (b2 == 0) {
                join = Paint.Join.MITER;
            } else if (b2 == 1) {
                join = Paint.Join.ROUND;
            } else if (b2 == 2) {
                join = Paint.Join.BEVEL;
            }
            this.f49pu = join;
            this.f50qu = C0308a.m239a(a, xmlPullParser, "strokeMiterLimit", 10, this.f50qu);
            this.f41hu = C0308a.m242a(a, xmlPullParser, theme, "strokeColor", 3, 0);
            this.f43ju = C0308a.m239a(a, xmlPullParser, "strokeAlpha", 11, this.f43ju);
            this.mStrokeWidth = C0308a.m239a(a, xmlPullParser, "strokeWidth", 4, this.mStrokeWidth);
            this.f46mu = C0308a.m239a(a, xmlPullParser, "trimPathEnd", 6, this.f46mu);
            this.f47nu = C0308a.m239a(a, xmlPullParser, "trimPathOffset", 7, this.f47nu);
            this.f45lu = C0308a.m239a(a, xmlPullParser, "trimPathStart", 5, this.f45lu);
            this.f60gu = C0308a.m245b(a, xmlPullParser, "fillType", 13, this.f60gu);
        }
        a.recycle();
    }

    /* access modifiers changed from: package-private */
    public float getFillAlpha() {
        return this.f44ku;
    }

    /* access modifiers changed from: package-private */
    public int getFillColor() {
        return this.f42iu.getColor();
    }

    /* access modifiers changed from: package-private */
    public float getStrokeAlpha() {
        return this.f43ju;
    }

    /* access modifiers changed from: package-private */
    public int getStrokeColor() {
        return this.f41hu.getColor();
    }

    /* access modifiers changed from: package-private */
    public float getStrokeWidth() {
        return this.mStrokeWidth;
    }

    /* access modifiers changed from: package-private */
    public float getTrimPathEnd() {
        return this.f46mu;
    }

    /* access modifiers changed from: package-private */
    public float getTrimPathOffset() {
        return this.f47nu;
    }

    /* access modifiers changed from: package-private */
    public float getTrimPathStart() {
        return this.f45lu;
    }

    public boolean isStateful() {
        return this.f42iu.isStateful() || this.f41hu.isStateful();
    }

    /* access modifiers changed from: package-private */
    public void setFillAlpha(float f) {
        this.f44ku = f;
    }

    /* access modifiers changed from: package-private */
    public void setFillColor(int i) {
        this.f42iu.setColor(i);
    }

    /* access modifiers changed from: package-private */
    public void setStrokeAlpha(float f) {
        this.f43ju = f;
    }

    /* access modifiers changed from: package-private */
    public void setStrokeColor(int i) {
        this.f41hu.setColor(i);
    }

    /* access modifiers changed from: package-private */
    public void setStrokeWidth(float f) {
        this.mStrokeWidth = f;
    }

    /* access modifiers changed from: package-private */
    public void setTrimPathEnd(float f) {
        this.f46mu = f;
    }

    /* access modifiers changed from: package-private */
    public void setTrimPathOffset(float f) {
        this.f47nu = f;
    }

    /* access modifiers changed from: package-private */
    public void setTrimPathStart(float f) {
        this.f45lu = f;
    }

    C0057j(C0057j jVar) {
        super(jVar);
        this.mThemeAttrs = jVar.mThemeAttrs;
        this.f41hu = jVar.f41hu;
        this.mStrokeWidth = jVar.mStrokeWidth;
        this.f43ju = jVar.f43ju;
        this.f42iu = jVar.f42iu;
        this.f60gu = jVar.f60gu;
        this.f44ku = jVar.f44ku;
        this.f45lu = jVar.f45lu;
        this.f46mu = jVar.f46mu;
        this.f47nu = jVar.f47nu;
        this.f48ou = jVar.f48ou;
        this.f49pu = jVar.f49pu;
        this.f50qu = jVar.f50qu;
    }

    /* renamed from: a */
    public boolean mo318a(int[] iArr) {
        return this.f41hu.mo3388a(iArr) | this.f42iu.mo3388a(iArr);
    }
}
