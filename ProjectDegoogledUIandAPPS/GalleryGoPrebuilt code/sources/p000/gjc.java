package p000;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/* renamed from: gjc */
/* compiled from: PG */
public final class gjc extends ggu {

    /* renamed from: g */
    public final RectF f11468g;

    /* renamed from: h */
    private final Paint f11469h;

    /* renamed from: i */
    private int f11470i;

    gjc() {
        this((gha) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public gjc(gha gha) {
        super(gha == null ? new gha() : gha);
        Paint paint = new Paint(1);
        this.f11469h = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.f11469h.setColor(-1);
        this.f11469h.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.f11468g = new RectF();
    }

    public final void draw(Canvas canvas) {
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof View)) {
            int i = Build.VERSION.SDK_INT;
            this.f11470i = canvas.saveLayer(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), (Paint) null);
        } else {
            View view = (View) callback;
            if (view.getLayerType() != 2) {
                view.setLayerType(2, (Paint) null);
            }
        }
        super.draw(canvas);
        canvas.drawRect(this.f11468g, this.f11469h);
        if (!(getCallback() instanceof View)) {
            canvas.restoreToCount(this.f11470i);
        }
    }

    /* renamed from: a */
    public final void mo6746a(float f, float f2, float f3, float f4) {
        if (f != this.f11468g.left || f2 != this.f11468g.top || f3 != this.f11468g.right || f4 != this.f11468g.bottom) {
            this.f11468g.set(f, f2, f3, f4);
            invalidateSelf();
        }
    }
}
