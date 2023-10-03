package p000;

import android.graphics.Matrix;
import java.util.ArrayList;

/* renamed from: agr */
/* compiled from: PG */
final class agr extends ggf {

    /* renamed from: a */
    public final Matrix f408a = new Matrix();

    /* renamed from: b */
    public final ArrayList f409b = new ArrayList();

    /* renamed from: c */
    public float f410c = 0.0f;

    /* renamed from: d */
    public float f411d = 0.0f;

    /* renamed from: e */
    public float f412e = 0.0f;

    /* renamed from: f */
    public float f413f = 1.0f;

    /* renamed from: g */
    public float f414g = 1.0f;

    /* renamed from: h */
    public float f415h = 0.0f;

    /* renamed from: i */
    public float f416i = 0.0f;

    /* renamed from: j */
    public final Matrix f417j = new Matrix();

    /* renamed from: k */
    public String f418k = null;

    public agr() {
        super((byte[]) null);
    }

    public String getGroupName() {
        return this.f418k;
    }

    public Matrix getLocalMatrix() {
        return this.f417j;
    }

    public float getPivotX() {
        return this.f411d;
    }

    public float getPivotY() {
        return this.f412e;
    }

    public float getRotation() {
        return this.f410c;
    }

    public float getScaleX() {
        return this.f413f;
    }

    public float getScaleY() {
        return this.f414g;
    }

    public float getTranslateX() {
        return this.f415h;
    }

    public float getTranslateY() {
        return this.f416i;
    }

    public agr(agr agr, C0290kn knVar) {
        super((byte[]) null);
        ags ags;
        this.f410c = agr.f410c;
        this.f411d = agr.f411d;
        this.f412e = agr.f412e;
        this.f413f = agr.f413f;
        this.f414g = agr.f414g;
        this.f415h = agr.f415h;
        this.f416i = agr.f416i;
        String str = agr.f418k;
        this.f418k = str;
        if (str != null) {
            knVar.put(str, this);
        }
        this.f417j.set(agr.f417j);
        ArrayList arrayList = agr.f409b;
        for (int i = 0; i < arrayList.size(); i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof agr) {
                this.f409b.add(new agr((agr) obj, knVar));
            } else {
                if (obj instanceof agq) {
                    ags = new agq((agq) obj);
                } else if (obj instanceof agp) {
                    ags = new agp((agp) obj);
                } else {
                    throw new IllegalStateException("Unknown object in the tree!");
                }
                this.f409b.add(ags);
                String str2 = ags.f420m;
                if (str2 != null) {
                    knVar.put(str2, ags);
                }
            }
        }
    }

    /* renamed from: b */
    public final boolean mo378b() {
        for (int i = 0; i < this.f409b.size(); i++) {
            if (((ggf) this.f409b.get(i)).mo378b()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public final boolean mo377a(int[] iArr) {
        boolean z = false;
        for (int i = 0; i < this.f409b.size(); i++) {
            z |= ((ggf) this.f409b.get(i)).mo377a(iArr);
        }
        if (z) {
            return true;
        }
        return false;
    }

    public void setPivotX(float f) {
        if (f != this.f411d) {
            this.f411d = f;
            mo395a();
        }
    }

    public void setPivotY(float f) {
        if (f != this.f412e) {
            this.f412e = f;
            mo395a();
        }
    }

    public void setRotation(float f) {
        if (f != this.f410c) {
            this.f410c = f;
            mo395a();
        }
    }

    public void setScaleX(float f) {
        if (f != this.f413f) {
            this.f413f = f;
            mo395a();
        }
    }

    public void setScaleY(float f) {
        if (f != this.f414g) {
            this.f414g = f;
            mo395a();
        }
    }

    public void setTranslateX(float f) {
        if (f != this.f415h) {
            this.f415h = f;
            mo395a();
        }
    }

    public void setTranslateY(float f) {
        if (f != this.f416i) {
            this.f416i = f;
            mo395a();
        }
    }

    /* renamed from: a */
    public final void mo395a() {
        this.f417j.reset();
        this.f417j.postTranslate(-this.f411d, -this.f412e);
        this.f417j.postScale(this.f413f, this.f414g);
        this.f417j.postRotate(this.f410c, 0.0f, 0.0f);
        this.f417j.postTranslate(this.f415h + this.f411d, this.f416i + this.f412e);
    }
}
