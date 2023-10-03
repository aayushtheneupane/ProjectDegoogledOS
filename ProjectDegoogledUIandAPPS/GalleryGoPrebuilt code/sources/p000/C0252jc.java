package p000;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* renamed from: jc */
/* compiled from: PG */
public class C0252jc extends Drawable {

    /* renamed from: a */
    public final Paint f15067a = new Paint(3);

    /* renamed from: b */
    public final BitmapShader f15068b;

    /* renamed from: c */
    public float f15069c;

    /* renamed from: d */
    public final Rect f15070d = new Rect();

    /* renamed from: e */
    private final Bitmap f15071e;

    /* renamed from: f */
    private int f15072f = 160;

    /* renamed from: g */
    private final int f15073g = 119;

    /* renamed from: h */
    private final Matrix f15074h = new Matrix();

    /* renamed from: i */
    private final RectF f15075i = new RectF();

    /* renamed from: j */
    private boolean f15076j = true;

    /* renamed from: k */
    private final int f15077k;

    /* renamed from: l */
    private final int f15078l;

    public C0252jc(Resources resources, Bitmap bitmap) {
        if (resources != null) {
            this.f15072f = resources.getDisplayMetrics().densityDpi;
        }
        this.f15071e = bitmap;
        if (bitmap == null) {
            this.f15078l = -1;
            this.f15077k = -1;
            this.f15068b = null;
            return;
        }
        this.f15077k = bitmap.getScaledWidth(this.f15072f);
        this.f15078l = this.f15071e.getScaledHeight(this.f15072f);
        this.f15068b = new BitmapShader(this.f15071e, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    /* renamed from: a */
    public static boolean m14459a(float f) {
        return f > 0.05f;
    }

    /* renamed from: a */
    public void mo9121a(int i, int i2, int i3, Rect rect, Rect rect2) {
        throw null;
    }

    public final int getIntrinsicHeight() {
        return this.f15078l;
    }

    public final int getIntrinsicWidth() {
        return this.f15077k;
    }

    public final void draw(Canvas canvas) {
        Bitmap bitmap = this.f15071e;
        if (bitmap != null) {
            mo9123a();
            if (this.f15067a.getShader() == null) {
                canvas.drawBitmap(bitmap, (Rect) null, this.f15070d, this.f15067a);
                return;
            }
            RectF rectF = this.f15075i;
            float f = this.f15069c;
            canvas.drawRoundRect(rectF, f, f, this.f15067a);
        }
    }

    public final int getAlpha() {
        return this.f15067a.getAlpha();
    }

    public final ColorFilter getColorFilter() {
        return this.f15067a.getColorFilter();
    }

    public final int getOpacity() {
        Bitmap bitmap;
        if (this.f15073g != 119 || (bitmap = this.f15071e) == null || bitmap.hasAlpha() || this.f15067a.getAlpha() < 255 || m14459a(this.f15069c)) {
            return -3;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f15076j = true;
    }

    public final void setAlpha(int i) {
        if (i != this.f15067a.getAlpha()) {
            this.f15067a.setAlpha(i);
            invalidateSelf();
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.f15067a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public final void setDither(boolean z) {
        this.f15067a.setDither(z);
        invalidateSelf();
    }

    public final void setFilterBitmap(boolean z) {
        this.f15067a.setFilterBitmap(z);
        invalidateSelf();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo9123a() {
        if (this.f15076j) {
            mo9121a(this.f15073g, this.f15077k, this.f15078l, getBounds(), this.f15070d);
            this.f15075i.set(this.f15070d);
            if (this.f15068b != null) {
                this.f15074h.setTranslate(this.f15075i.left, this.f15075i.top);
                this.f15074h.preScale(this.f15075i.width() / ((float) this.f15071e.getWidth()), this.f15075i.height() / ((float) this.f15071e.getHeight()));
                this.f15068b.setLocalMatrix(this.f15074h);
                this.f15067a.setShader(this.f15068b);
            }
            this.f15076j = false;
        }
    }
}
