package p000a.p013g.p014a.p015a;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.util.AttributeSet;
import androidx.core.content.p022a.C0308a;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import p000a.p005b.C0015b;

/* renamed from: a.g.a.a.k */
class C0058k extends C0059l {

    /* renamed from: Yt */
    final Matrix f51Yt = new Matrix();

    /* renamed from: Zt */
    float f52Zt = 0.0f;

    /* renamed from: _t */
    private float f53_t = 1.0f;

    /* renamed from: au */
    private float f54au = 1.0f;

    /* renamed from: bu */
    private float f55bu = 0.0f;

    /* renamed from: cu */
    private float f56cu = 0.0f;

    /* renamed from: du */
    final Matrix f57du = new Matrix();

    /* renamed from: eu */
    private String f58eu = null;
    int mChangingConfigurations;
    final ArrayList mChildren = new ArrayList();
    private float mPivotX = 0.0f;
    private float mPivotY = 0.0f;
    private int[] mThemeAttrs;

    public C0058k(C0058k kVar, C0015b bVar) {
        super((C0055h) null);
        C0060m mVar;
        this.f52Zt = kVar.f52Zt;
        this.mPivotX = kVar.mPivotX;
        this.mPivotY = kVar.mPivotY;
        this.f53_t = kVar.f53_t;
        this.f54au = kVar.f54au;
        this.f55bu = kVar.f55bu;
        this.f56cu = kVar.f56cu;
        this.mThemeAttrs = kVar.mThemeAttrs;
        this.f58eu = kVar.f58eu;
        this.mChangingConfigurations = kVar.mChangingConfigurations;
        String str = this.f58eu;
        if (str != null) {
            bVar.put(str, this);
        }
        this.f57du.set(kVar.f57du);
        ArrayList arrayList = kVar.mChildren;
        for (int i = 0; i < arrayList.size(); i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof C0058k) {
                this.mChildren.add(new C0058k((C0058k) obj, bVar));
            } else {
                if (obj instanceof C0057j) {
                    mVar = new C0057j((C0057j) obj);
                } else if (obj instanceof C0056i) {
                    mVar = new C0056i((C0056i) obj);
                } else {
                    throw new IllegalStateException("Unknown object in the tree!");
                }
                this.mChildren.add(mVar);
                String str2 = mVar.mPathName;
                if (str2 != null) {
                    bVar.put(str2, mVar);
                }
            }
        }
    }

    /* renamed from: Un */
    private void m65Un() {
        this.f57du.reset();
        this.f57du.postTranslate(-this.mPivotX, -this.mPivotY);
        this.f57du.postScale(this.f53_t, this.f54au);
        this.f57du.postRotate(this.f52Zt, 0.0f, 0.0f);
        this.f57du.postTranslate(this.f55bu + this.mPivotX, this.f56cu + this.mPivotY);
    }

    /* renamed from: a */
    public void mo336a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
        TypedArray a = C0308a.m241a(resources, theme, attributeSet, C0048a.f25Nt);
        this.mThemeAttrs = null;
        this.f52Zt = C0308a.m239a(a, xmlPullParser, "rotation", 5, this.f52Zt);
        this.mPivotX = a.getFloat(1, this.mPivotX);
        this.mPivotY = a.getFloat(2, this.mPivotY);
        this.f53_t = C0308a.m239a(a, xmlPullParser, "scaleX", 3, this.f53_t);
        this.f54au = C0308a.m239a(a, xmlPullParser, "scaleY", 4, this.f54au);
        this.f55bu = C0308a.m239a(a, xmlPullParser, "translateX", 6, this.f55bu);
        this.f56cu = C0308a.m239a(a, xmlPullParser, "translateY", 7, this.f56cu);
        String string = a.getString(0);
        if (string != null) {
            this.f58eu = string;
        }
        m65Un();
        a.recycle();
    }

    public String getGroupName() {
        return this.f58eu;
    }

    public Matrix getLocalMatrix() {
        return this.f57du;
    }

    public float getPivotX() {
        return this.mPivotX;
    }

    public float getPivotY() {
        return this.mPivotY;
    }

    public float getRotation() {
        return this.f52Zt;
    }

    public float getScaleX() {
        return this.f53_t;
    }

    public float getScaleY() {
        return this.f54au;
    }

    public float getTranslateX() {
        return this.f55bu;
    }

    public float getTranslateY() {
        return this.f56cu;
    }

    public boolean isStateful() {
        for (int i = 0; i < this.mChildren.size(); i++) {
            if (((C0059l) this.mChildren.get(i)).isStateful()) {
                return true;
            }
        }
        return false;
    }

    public void setPivotX(float f) {
        if (f != this.mPivotX) {
            this.mPivotX = f;
            m65Un();
        }
    }

    public void setPivotY(float f) {
        if (f != this.mPivotY) {
            this.mPivotY = f;
            m65Un();
        }
    }

    public void setRotation(float f) {
        if (f != this.f52Zt) {
            this.f52Zt = f;
            m65Un();
        }
    }

    public void setScaleX(float f) {
        if (f != this.f53_t) {
            this.f53_t = f;
            m65Un();
        }
    }

    public void setScaleY(float f) {
        if (f != this.f54au) {
            this.f54au = f;
            m65Un();
        }
    }

    public void setTranslateX(float f) {
        if (f != this.f55bu) {
            this.f55bu = f;
            m65Un();
        }
    }

    public void setTranslateY(float f) {
        if (f != this.f56cu) {
            this.f56cu = f;
            m65Un();
        }
    }

    /* renamed from: a */
    public boolean mo318a(int[] iArr) {
        boolean z = false;
        for (int i = 0; i < this.mChildren.size(); i++) {
            z |= ((C0059l) this.mChildren.get(i)).mo318a(iArr);
        }
        return z;
    }

    public C0058k() {
        super((C0055h) null);
    }
}
