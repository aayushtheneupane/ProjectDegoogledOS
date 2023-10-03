package p000;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;

/* renamed from: ghf */
/* compiled from: PG */
final class ghf extends ghk {

    /* renamed from: a */
    private final ghh f11365a;

    public ghf(ghh ghh) {
        this.f11365a = ghh;
    }

    /* renamed from: a */
    public final void mo6674a(Matrix matrix, ggl ggl, int i, Canvas canvas) {
        ggl ggl2 = ggl;
        int i2 = i;
        Canvas canvas2 = canvas;
        ghh ghh = this.f11365a;
        float f = ghh.f11374e;
        float f2 = ghh.f11375f;
        RectF rectF = new RectF(ghh.f11370a, ghh.f11371b, ghh.f11372c, ghh.f11373d);
        Path path = ggl2.f11267k;
        if (f2 >= 0.0f) {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f, f2);
            path.close();
            float f3 = (float) (-i2);
            rectF.inset(f3, f3);
            ggl.f11259i[0] = 0;
            ggl.f11259i[1] = ggl2.f11264d;
            ggl.f11259i[2] = ggl2.f11265e;
            ggl.f11259i[3] = ggl2.f11266f;
        } else {
            ggl.f11259i[0] = 0;
            ggl.f11259i[1] = ggl2.f11266f;
            ggl.f11259i[2] = ggl2.f11265e;
            ggl.f11259i[3] = ggl2.f11264d;
        }
        float width = 1.0f - (((float) i2) / (rectF.width() / 2.0f));
        ggl.f11260j[1] = width;
        ggl.f11260j[2] = width + ((1.0f - width) / 2.0f);
        ggl2.f11262b.setShader(new RadialGradient(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, ggl.f11259i, ggl.f11260j, Shader.TileMode.CLAMP));
        canvas.save();
        canvas2.concat(matrix);
        if (f2 >= 0.0f) {
            canvas2.clipPath(path, Region.Op.DIFFERENCE);
            canvas2.drawPath(path, ggl2.f11268l);
        }
        canvas.drawArc(rectF, f, f2, true, ggl2.f11262b);
        canvas.restore();
    }
}
