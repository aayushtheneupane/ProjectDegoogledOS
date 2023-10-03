package androidx.appcompat.p020b.p021a;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.C0126R;
import androidx.core.graphics.drawable.C0322a;

/* renamed from: androidx.appcompat.b.a.n */
public class C0171n extends Drawable {

    /* renamed from: bd */
    private static final float f159bd = ((float) Math.toRadians(45.0d));

    /* renamed from: Uc */
    private float f160Uc;

    /* renamed from: Vc */
    private float f161Vc;

    /* renamed from: Wc */
    private float f162Wc;

    /* renamed from: Xc */
    private float f163Xc;

    /* renamed from: Yc */
    private boolean f164Yc;

    /* renamed from: Zc */
    private boolean f165Zc = false;

    /* renamed from: _c */
    private float f166_c;

    /* renamed from: ad */
    private int f167ad = 2;
    private final Paint mPaint = new Paint();
    private final Path mPath = new Path();
    private float mProgress;
    private final int mSize;

    public C0171n(Context context) {
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeJoin(Paint.Join.MITER);
        this.mPaint.setStrokeCap(Paint.Cap.BUTT);
        this.mPaint.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes((AttributeSet) null, C0126R.styleable.DrawerArrowToggle, C0126R.attr.drawerArrowStyle, C0126R.style.Base_Widget_AppCompat_DrawerArrowToggle);
        int color = obtainStyledAttributes.getColor(C0126R.styleable.DrawerArrowToggle_color, 0);
        if (color != this.mPaint.getColor()) {
            this.mPaint.setColor(color);
            invalidateSelf();
        }
        float dimension = obtainStyledAttributes.getDimension(C0126R.styleable.DrawerArrowToggle_thickness, 0.0f);
        if (this.mPaint.getStrokeWidth() != dimension) {
            this.mPaint.setStrokeWidth(dimension);
            this.f166_c = (float) (Math.cos((double) f159bd) * ((double) (dimension / 2.0f)));
            invalidateSelf();
        }
        boolean z = obtainStyledAttributes.getBoolean(C0126R.styleable.DrawerArrowToggle_spinBars, true);
        if (this.f164Yc != z) {
            this.f164Yc = z;
            invalidateSelf();
        }
        float round = (float) Math.round(obtainStyledAttributes.getDimension(C0126R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0f));
        if (round != this.f163Xc) {
            this.f163Xc = round;
            invalidateSelf();
        }
        this.mSize = obtainStyledAttributes.getDimensionPixelSize(C0126R.styleable.DrawerArrowToggle_drawableSize, 0);
        this.f161Vc = (float) Math.round(obtainStyledAttributes.getDimension(C0126R.styleable.DrawerArrowToggle_barLength, 0.0f));
        this.f160Uc = (float) Math.round(obtainStyledAttributes.getDimension(C0126R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.f162Wc = obtainStyledAttributes.getDimension(C0126R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0f);
        obtainStyledAttributes.recycle();
    }

    private static float lerp(float f, float f2, float f3) {
        return ((f2 - f) * f3) + f;
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        Rect bounds = getBounds();
        int i = this.f167ad;
        boolean z = false;
        if (i != 0 && (i == 1 || (i == 3 ? C0322a.m262b(this) == 0 : C0322a.m262b(this) == 1))) {
            z = true;
        }
        float f = this.f160Uc;
        float lerp = lerp(this.f161Vc, (float) Math.sqrt((double) (f * f * 2.0f)), this.mProgress);
        float lerp2 = lerp(this.f161Vc, this.f162Wc, this.mProgress);
        float round = (float) Math.round(lerp(0.0f, this.f166_c, this.mProgress));
        float lerp3 = lerp(0.0f, f159bd, this.mProgress);
        double d = (double) lerp;
        float lerp4 = lerp(z ? 0.0f : -180.0f, z ? 180.0f : 0.0f, this.mProgress);
        double d2 = (double) lerp3;
        boolean z2 = z;
        float round2 = (float) Math.round(Math.cos(d2) * d);
        float round3 = (float) Math.round(Math.sin(d2) * d);
        this.mPath.rewind();
        float lerp5 = lerp(this.mPaint.getStrokeWidth() + this.f163Xc, -this.f166_c, this.mProgress);
        float f2 = (-lerp2) / 2.0f;
        this.mPath.moveTo(f2 + round, 0.0f);
        this.mPath.rLineTo(lerp2 - (round * 2.0f), 0.0f);
        this.mPath.moveTo(f2, lerp5);
        this.mPath.rLineTo(round2, round3);
        this.mPath.moveTo(f2, -lerp5);
        this.mPath.rLineTo(round2, -round3);
        this.mPath.close();
        canvas.save();
        float strokeWidth = this.mPaint.getStrokeWidth();
        float height = ((float) bounds.height()) - (3.0f * strokeWidth);
        float f3 = this.f163Xc;
        canvas2.translate((float) bounds.centerX(), (strokeWidth * 1.5f) + f3 + ((float) ((((int) (height - (2.0f * f3))) / 4) * 2)));
        if (this.f164Yc) {
            canvas2.rotate(lerp4 * ((float) (this.f165Zc ^ z2 ? -1 : 1)));
        } else if (z2) {
            canvas2.rotate(180.0f);
        }
        canvas2.drawPath(this.mPath, this.mPaint);
        canvas.restore();
    }

    public int getIntrinsicHeight() {
        return this.mSize;
    }

    public int getIntrinsicWidth() {
        return this.mSize;
    }

    public int getOpacity() {
        return -3;
    }

    /* renamed from: k */
    public void mo1181k(boolean z) {
        if (this.f165Zc != z) {
            this.f165Zc = z;
            invalidateSelf();
        }
    }

    public void setAlpha(int i) {
        if (i != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setProgress(float f) {
        if (this.mProgress != f) {
            this.mProgress = f;
            invalidateSelf();
        }
    }
}
