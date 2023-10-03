package p000a.p013g.p014a.p015a;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Shader;
import androidx.core.content.p022a.C0309b;
import p000a.p005b.C0015b;

/* renamed from: a.g.a.a.n */
class C0061n {
    private static final Matrix IDENTITY_MATRIX = new Matrix();
    float mBaseHeight;
    float mBaseWidth;
    private int mChangingConfigurations;
    private final Path mPath;
    final C0058k mRootGroup;
    String mRootName;
    final C0015b mVGTargetsMap;
    float mViewportHeight;
    float mViewportWidth;

    /* renamed from: ru */
    private final Path f61ru;

    /* renamed from: su */
    private final Matrix f62su;

    /* renamed from: tu */
    Paint f63tu;

    /* renamed from: uu */
    Paint f64uu;

    /* renamed from: vu */
    private PathMeasure f65vu;

    /* renamed from: wu */
    int f66wu;

    /* renamed from: xu */
    Boolean f67xu;

    public C0061n() {
        this.f62su = new Matrix();
        this.mBaseWidth = 0.0f;
        this.mBaseHeight = 0.0f;
        this.mViewportWidth = 0.0f;
        this.mViewportHeight = 0.0f;
        this.f66wu = 255;
        this.mRootName = null;
        this.f67xu = null;
        this.mVGTargetsMap = new C0015b();
        this.mRootGroup = new C0058k();
        this.mPath = new Path();
        this.f61ru = new Path();
    }

    /* JADX WARNING: type inference failed for: r11v0 */
    /* JADX WARNING: type inference failed for: r11v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r11v2 */
    /* renamed from: a */
    private void m70a(C0058k kVar, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
        C0061n nVar;
        C0061n nVar2 = this;
        C0058k kVar2 = kVar;
        Canvas canvas2 = canvas;
        ColorFilter colorFilter2 = colorFilter;
        kVar2.f51Yt.set(matrix);
        kVar2.f51Yt.preConcat(kVar2.f57du);
        canvas.save();
        ? r11 = 0;
        int i3 = 0;
        while (i3 < kVar2.mChildren.size()) {
            C0059l lVar = (C0059l) kVar2.mChildren.get(i3);
            if (lVar instanceof C0058k) {
                m70a((C0058k) lVar, kVar2.f51Yt, canvas, i, i2, colorFilter);
            } else if (lVar instanceof C0060m) {
                C0060m mVar = (C0060m) lVar;
                float f = ((float) i) / nVar2.mViewportWidth;
                float f2 = ((float) i2) / nVar2.mViewportHeight;
                float min = Math.min(f, f2);
                Matrix matrix2 = kVar2.f51Yt;
                nVar2.f62su.set(matrix2);
                nVar2.f62su.postScale(f, f2);
                float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
                matrix2.mapVectors(fArr);
                float f3 = min;
                float f4 = (fArr[r11] * fArr[3]) - (fArr[1] * fArr[2]);
                float max = Math.max((float) Math.hypot((double) fArr[r11], (double) fArr[1]), (float) Math.hypot((double) fArr[2], (double) fArr[3]));
                float abs = max > 0.0f ? Math.abs(f4) / max : 0.0f;
                if (abs == 0.0f) {
                    nVar = this;
                } else {
                    nVar = this;
                    mVar.toPath(nVar.mPath);
                    Path path = nVar.mPath;
                    nVar.f61ru.reset();
                    if (mVar.mo316kd()) {
                        nVar.f61ru.setFillType(mVar.f60gu == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                        nVar.f61ru.addPath(path, nVar.f62su);
                        canvas2.clipPath(nVar.f61ru);
                    } else {
                        C0057j jVar = (C0057j) mVar;
                        if (!(jVar.f45lu == 0.0f && jVar.f46mu == 1.0f)) {
                            float f5 = jVar.f45lu;
                            float f6 = jVar.f47nu;
                            float f7 = (f5 + f6) % 1.0f;
                            float f8 = (jVar.f46mu + f6) % 1.0f;
                            if (nVar.f65vu == null) {
                                nVar.f65vu = new PathMeasure();
                            }
                            nVar.f65vu.setPath(nVar.mPath, r11);
                            float length = nVar.f65vu.getLength();
                            float f9 = f7 * length;
                            float f10 = f8 * length;
                            path.reset();
                            if (f9 > f10) {
                                nVar.f65vu.getSegment(f9, length, path, true);
                                nVar.f65vu.getSegment(0.0f, f10, path, true);
                            } else {
                                nVar.f65vu.getSegment(f9, f10, path, true);
                            }
                            path.rLineTo(0.0f, 0.0f);
                        }
                        nVar.f61ru.addPath(path, nVar.f62su);
                        if (jVar.f42iu.mo3393sc()) {
                            C0309b bVar = jVar.f42iu;
                            if (nVar.f64uu == null) {
                                nVar.f64uu = new Paint(1);
                                nVar.f64uu.setStyle(Paint.Style.FILL);
                            }
                            Paint paint = nVar.f64uu;
                            if (bVar.mo3392rc()) {
                                Shader shader = bVar.getShader();
                                shader.setLocalMatrix(nVar.f62su);
                                paint.setShader(shader);
                                paint.setAlpha(Math.round(jVar.f44ku * 255.0f));
                            } else {
                                paint.setShader((Shader) null);
                                paint.setAlpha(255);
                                paint.setColor(C0064q.m80a(bVar.getColor(), jVar.f44ku));
                            }
                            paint.setColorFilter(colorFilter2);
                            nVar.f61ru.setFillType(jVar.f60gu == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                            canvas2.drawPath(nVar.f61ru, paint);
                        }
                        if (jVar.f41hu.mo3393sc()) {
                            C0309b bVar2 = jVar.f41hu;
                            if (nVar.f63tu == null) {
                                nVar.f63tu = new Paint(1);
                                nVar.f63tu.setStyle(Paint.Style.STROKE);
                            }
                            Paint paint2 = nVar.f63tu;
                            Paint.Join join = jVar.f49pu;
                            if (join != null) {
                                paint2.setStrokeJoin(join);
                            }
                            Paint.Cap cap = jVar.f48ou;
                            if (cap != null) {
                                paint2.setStrokeCap(cap);
                            }
                            paint2.setStrokeMiter(jVar.f50qu);
                            if (bVar2.mo3392rc()) {
                                Shader shader2 = bVar2.getShader();
                                shader2.setLocalMatrix(nVar.f62su);
                                paint2.setShader(shader2);
                                paint2.setAlpha(Math.round(jVar.f43ju * 255.0f));
                            } else {
                                paint2.setShader((Shader) null);
                                paint2.setAlpha(255);
                                paint2.setColor(C0064q.m80a(bVar2.getColor(), jVar.f43ju));
                            }
                            paint2.setColorFilter(colorFilter2);
                            paint2.setStrokeWidth(jVar.mStrokeWidth * abs * f3);
                            canvas2.drawPath(nVar.f61ru, paint2);
                        }
                    }
                }
                i3++;
                nVar2 = nVar;
                r11 = 0;
            }
            int i4 = i;
            int i5 = i2;
            nVar = nVar2;
            i3++;
            nVar2 = nVar;
            r11 = 0;
        }
        canvas.restore();
    }

    public float getAlpha() {
        return ((float) getRootAlpha()) / 255.0f;
    }

    public int getRootAlpha() {
        return this.f66wu;
    }

    public boolean isStateful() {
        if (this.f67xu == null) {
            this.f67xu = Boolean.valueOf(this.mRootGroup.isStateful());
        }
        return this.f67xu.booleanValue();
    }

    public void setAlpha(float f) {
        setRootAlpha((int) (f * 255.0f));
    }

    public void setRootAlpha(int i) {
        this.f66wu = i;
    }

    public C0061n(C0061n nVar) {
        this.f62su = new Matrix();
        this.mBaseWidth = 0.0f;
        this.mBaseHeight = 0.0f;
        this.mViewportWidth = 0.0f;
        this.mViewportHeight = 0.0f;
        this.f66wu = 255;
        this.mRootName = null;
        this.f67xu = null;
        this.mVGTargetsMap = new C0015b();
        this.mRootGroup = new C0058k(nVar.mRootGroup, this.mVGTargetsMap);
        this.mPath = new Path(nVar.mPath);
        this.f61ru = new Path(nVar.f61ru);
        this.mBaseWidth = nVar.mBaseWidth;
        this.mBaseHeight = nVar.mBaseHeight;
        this.mViewportWidth = nVar.mViewportWidth;
        this.mViewportHeight = nVar.mViewportHeight;
        this.mChangingConfigurations = nVar.mChangingConfigurations;
        this.f66wu = nVar.f66wu;
        this.mRootName = nVar.mRootName;
        String str = nVar.mRootName;
        if (str != null) {
            this.mVGTargetsMap.put(str, this);
        }
        this.f67xu = nVar.f67xu;
    }

    /* renamed from: a */
    public void mo357a(Canvas canvas, int i, int i2, ColorFilter colorFilter) {
        m70a(this.mRootGroup, IDENTITY_MATRIX, canvas, i, i2, colorFilter);
    }

    /* renamed from: a */
    public boolean mo358a(int[] iArr) {
        return this.mRootGroup.mo318a(iArr);
    }
}
