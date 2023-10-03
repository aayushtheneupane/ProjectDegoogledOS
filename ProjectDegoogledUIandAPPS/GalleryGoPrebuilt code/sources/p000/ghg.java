package p000;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.Shader;

/* renamed from: ghg */
/* compiled from: PG */
final class ghg extends ghk {

    /* renamed from: a */
    private final ghi f11366a;

    /* renamed from: b */
    private final float f11367b;

    /* renamed from: c */
    private final float f11368c;

    public ghg(ghi ghi, float f, float f2) {
        this.f11366a = ghi;
        this.f11367b = f;
        this.f11368c = f2;
    }

    /* renamed from: a */
    public final void mo6674a(Matrix matrix, ggl ggl, int i, Canvas canvas) {
        ggl ggl2 = ggl;
        int i2 = i;
        Canvas canvas2 = canvas;
        ghi ghi = this.f11366a;
        RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot((double) (ghi.f11377b - this.f11368c), (double) (ghi.f11376a - this.f11367b)), 0.0f);
        Matrix matrix2 = new Matrix(matrix);
        matrix2.preTranslate(this.f11367b, this.f11368c);
        matrix2.preRotate(mo6675a());
        rectF.bottom += (float) i2;
        rectF.offset(0.0f, (float) (-i2));
        ggl.f11257g[0] = ggl2.f11266f;
        ggl.f11257g[1] = ggl2.f11265e;
        ggl.f11257g[2] = ggl2.f11264d;
        ggl2.f11263c.setShader(new LinearGradient(rectF.left, rectF.top, rectF.left, rectF.bottom, ggl.f11257g, ggl.f11258h, Shader.TileMode.CLAMP));
        canvas.save();
        canvas2.concat(matrix2);
        canvas2.drawRect(rectF, ggl2.f11263c);
        canvas.restore();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final float mo6675a() {
        ghi ghi = this.f11366a;
        return (float) Math.toDegrees(Math.atan((double) ((ghi.f11377b - this.f11368c) / (ghi.f11376a - this.f11367b))));
    }
}
