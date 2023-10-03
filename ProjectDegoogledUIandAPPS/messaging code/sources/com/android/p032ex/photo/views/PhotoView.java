package com.android.p032ex.photo.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ScaleGestureDetectorCompat;
import com.android.p032ex.photo.C0737r;
import com.android.p032ex.photo.C0738s;
import com.android.p032ex.photo.fragments.C0724b;

/* renamed from: com.android.ex.photo.views.PhotoView */
public class PhotoView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, ScaleGestureDetector.OnScaleGestureListener, C0724b {

    /* renamed from: Gk */
    private static int f918Gk;

    /* renamed from: Hk */
    private static int f919Hk;

    /* renamed from: Ik */
    private static Bitmap f920Ik;

    /* renamed from: Jk */
    private static Bitmap f921Jk;

    /* renamed from: Kk */
    private static Paint f922Kk;

    /* renamed from: Lk */
    private static Paint f923Lk;
    private static boolean sInitialized;

    /* renamed from: Aj */
    private boolean f924Aj;

    /* renamed from: Ak */
    private float f925Ak;

    /* renamed from: Bk */
    private RectF f926Bk = new RectF();

    /* renamed from: Ck */
    private RectF f927Ck = new RectF();
    /* access modifiers changed from: private */

    /* renamed from: Dk */
    public RectF f928Dk = new RectF();

    /* renamed from: Ek */
    private boolean f929Ek;

    /* renamed from: Fk */
    private boolean f930Fk;

    /* renamed from: ek */
    private Matrix f931ek;

    /* renamed from: fk */
    private Matrix f932fk = new Matrix();

    /* renamed from: gk */
    private int f933gk = -1;

    /* renamed from: hk */
    private boolean f934hk;

    /* renamed from: ik */
    private byte[] f935ik;

    /* renamed from: jk */
    private boolean f936jk;

    /* renamed from: kk */
    private Rect f937kk = new Rect();

    /* renamed from: lk */
    private int f938lk;
    private float mDownFocusX;
    private float mDownFocusY;
    private Drawable mDrawable;
    private GestureDetectorCompat mGestureDetector;
    private Matrix mMatrix = new Matrix();
    private float[] mValues = new float[9];

    /* renamed from: mk */
    private float f939mk;

    /* renamed from: nk */
    private ScaleGestureDetector f940nk;

    /* renamed from: pk */
    private View.OnClickListener f941pk;

    /* renamed from: qk */
    private boolean f942qk;

    /* renamed from: rk */
    private boolean f943rk = true;

    /* renamed from: sk */
    private boolean f944sk;

    /* renamed from: tk */
    private boolean f945tk;
    /* access modifiers changed from: private */

    /* renamed from: uk */
    public C0750c f946uk;

    /* renamed from: vf */
    private boolean f947vf;

    /* renamed from: vk */
    private float f948vk;
    /* access modifiers changed from: private */

    /* renamed from: wk */
    public float f949wk;

    /* renamed from: xk */
    private C0752e f950xk;

    /* renamed from: yk */
    private C0751d f951yk;

    /* renamed from: zk */
    private C0749b f952zk;

    public PhotoView(Context context) {
        super(context);
        initialize();
    }

    /* renamed from: Aa */
    private void m1197Aa(boolean z) {
        Drawable drawable = this.mDrawable;
        if (drawable != null && this.f934hk) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = this.mDrawable.getIntrinsicHeight();
            boolean z2 = true;
            boolean z3 = (intrinsicWidth < 0 || getWidth() == intrinsicWidth) && (intrinsicHeight < 0 || getHeight() == intrinsicHeight);
            this.mDrawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            if (z || (this.f948vk == 0.0f && this.mDrawable != null && this.f934hk)) {
                int intrinsicWidth2 = this.mDrawable.getIntrinsicWidth();
                int intrinsicHeight2 = this.mDrawable.getIntrinsicHeight();
                int width = this.f947vf ? f919Hk : getWidth();
                int height = this.f947vf ? f919Hk : getHeight();
                if ((intrinsicWidth2 >= 0 && width != intrinsicWidth2) || (intrinsicHeight2 >= 0 && height != intrinsicHeight2)) {
                    z2 = false;
                }
                if (!z2 || this.f947vf) {
                    float f = (float) intrinsicWidth2;
                    float f2 = (float) intrinsicHeight2;
                    this.f926Bk.set(0.0f, 0.0f, f, f2);
                    if (this.f947vf) {
                        this.f927Ck.set(this.f937kk);
                    } else {
                        this.f927Ck.set(0.0f, 0.0f, (float) width, (float) height);
                    }
                    float f3 = (float) (width / 2);
                    float f4 = this.f939mk;
                    float f5 = (f * f4) / 2.0f;
                    float f6 = (float) (height / 2);
                    float f7 = (f2 * f4) / 2.0f;
                    RectF rectF = new RectF(f3 - f5, f6 - f7, f5 + f3, f7 + f6);
                    if (this.f927Ck.contains(rectF)) {
                        this.mMatrix.setRectToRect(this.f926Bk, rectF, Matrix.ScaleToFit.CENTER);
                    } else {
                        this.mMatrix.setRectToRect(this.f926Bk, this.f927Ck, Matrix.ScaleToFit.CENTER);
                    }
                } else {
                    this.mMatrix.reset();
                }
                this.f932fk.set(this.mMatrix);
                int intrinsicWidth3 = this.mDrawable.getIntrinsicWidth();
                int intrinsicHeight3 = this.mDrawable.getIntrinsicHeight();
                int un = this.f947vf ? m1207un() : getWidth();
                int un2 = this.f947vf ? m1207un() : getHeight();
                if (intrinsicWidth3 >= un || intrinsicHeight3 >= un2 || this.f947vf) {
                    this.f948vk = getScale();
                } else {
                    this.f948vk = 1.0f;
                }
                this.f949wk = Math.max(this.f948vk * 4.0f, 4.0f);
            }
            if (z3 || this.mMatrix.isIdentity()) {
                this.f931ek = null;
            } else {
                this.f931ek = this.mMatrix;
            }
        }
    }

    /* access modifiers changed from: private */
    public float getScale() {
        this.mMatrix.getValues(this.mValues);
        return this.mValues[0];
    }

    private void initialize() {
        Context context = getContext();
        if (!sInitialized) {
            sInitialized = true;
            Resources resources = context.getApplicationContext().getResources();
            f919Hk = resources.getDimensionPixelSize(C0738s.photo_crop_width);
            f922Kk = new Paint();
            f922Kk.setAntiAlias(true);
            f922Kk.setColor(resources.getColor(C0737r.photo_crop_dim_color));
            f922Kk.setStyle(Paint.Style.FILL);
            f923Lk = new Paint();
            f923Lk.setAntiAlias(true);
            f923Lk.setColor(resources.getColor(C0737r.photo_crop_highlight_color));
            f923Lk.setStyle(Paint.Style.STROKE);
            f923Lk.setStrokeWidth(resources.getDimension(C0738s.photo_crop_stroke_width));
            int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
            f918Gk = scaledTouchSlop * scaledTouchSlop;
        }
        this.mGestureDetector = new GestureDetectorCompat(context, this, (Handler) null);
        this.f940nk = new ScaleGestureDetector(context, this);
        this.f930Fk = ScaleGestureDetectorCompat.isQuickScaleEnabled(this.f940nk);
        this.f946uk = new C0750c(this);
        this.f950xk = new C0752e(this);
        this.f951yk = new C0751d(this);
        this.f952zk = new C0749b(this);
    }

    /* access modifiers changed from: private */
    public void scale(float f, float f2, float f3) {
        this.mMatrix.postRotate(-this.f925Ak, (float) (getWidth() / 2), (float) (getHeight() / 2));
        float min = Math.min(Math.max(f, this.f948vk), this.f949wk * 1.5f);
        float scale = getScale();
        float f4 = this.f949wk;
        if (min > f4 && scale <= f4) {
            postDelayed(new C0748a(this), 600);
        }
        float f5 = min / scale;
        this.mMatrix.postScale(f5, f5, f2, f3);
        this.mMatrix.postRotate(this.f925Ak, (float) (getWidth() / 2), (float) (getHeight() / 2));
        invalidate();
    }

    /* access modifiers changed from: private */
    public int translate(float f, float f2) {
        float f3;
        float f4;
        this.f928Dk.set(this.f926Bk);
        this.mMatrix.mapRect(this.f928Dk);
        float f5 = 0.0f;
        float f6 = this.f947vf ? (float) this.f937kk.left : 0.0f;
        float width = (float) (this.f947vf ? this.f937kk.right : getWidth());
        RectF rectF = this.f928Dk;
        float f7 = rectF.left;
        float f8 = rectF.right;
        if (this.f947vf) {
            f3 = Math.max(f6 - f8, Math.min(width - f7, f));
        } else {
            float f9 = width - f6;
            f3 = f8 - f7 < f9 ? f6 + ((f9 - (f8 + f7)) / 2.0f) : Math.max(width - f8, Math.min(f6 - f7, f));
        }
        if (this.f947vf) {
            f5 = (float) this.f937kk.top;
        }
        float height = (float) (this.f947vf ? this.f937kk.bottom : getHeight());
        RectF rectF2 = this.f928Dk;
        float f10 = rectF2.top;
        float f11 = rectF2.bottom;
        if (this.f947vf) {
            f4 = Math.max(f5 - f11, Math.min(height - f10, f2));
        } else {
            float f12 = height - f5;
            f4 = f11 - f10 < f12 ? f5 + ((f12 - (f11 + f10)) / 2.0f) : Math.max(height - f11, Math.min(f5 - f10, f2));
        }
        this.mMatrix.postTranslate(f3, f4);
        invalidate();
        boolean z = f3 == f;
        boolean z2 = f4 == f2;
        if (z && z2) {
            return 3;
        }
        if (z) {
            return 1;
        }
        return z2 ? 2 : 0;
    }

    /* renamed from: un */
    private int m1207un() {
        int i = this.f938lk;
        return i > 0 ? i : f919Hk;
    }

    /* access modifiers changed from: private */
    /* renamed from: vn */
    public void m1208vn() {
        this.f928Dk.set(this.f926Bk);
        this.mMatrix.mapRect(this.f928Dk);
        float f = 0.0f;
        float f2 = this.f947vf ? (float) this.f937kk.left : 0.0f;
        float width = (float) (this.f947vf ? this.f937kk.right : getWidth());
        RectF rectF = this.f928Dk;
        float f3 = rectF.left;
        float f4 = rectF.right;
        float f5 = width - f2;
        float f6 = f4 - f3 < f5 ? f2 + ((f5 - (f4 + f3)) / 2.0f) : f3 > f2 ? f2 - f3 : f4 < width ? width - f4 : 0.0f;
        float f7 = this.f947vf ? (float) this.f937kk.top : 0.0f;
        float height = (float) (this.f947vf ? this.f937kk.bottom : getHeight());
        RectF rectF2 = this.f928Dk;
        float f8 = rectF2.top;
        float f9 = rectF2.bottom;
        float f10 = height - f7;
        if (f9 - f8 < f10) {
            f = ((f10 - (f9 + f8)) / 2.0f) + f7;
        } else if (f8 > f7) {
            f = f7 - f8;
        } else if (f9 < height) {
            f = height - f9;
        }
        if (Math.abs(f6) > 20.0f || Math.abs(f) > 20.0f) {
            this.f951yk.mo5848f(f6, f);
            return;
        }
        this.mMatrix.postTranslate(f6, f);
        invalidate();
    }

    /* renamed from: _b */
    public boolean mo5812_b() {
        return this.mDrawable != null;
    }

    /* renamed from: ac */
    public void mo5814ac() {
        this.mMatrix.set(this.f932fk);
        invalidate();
    }

    public void clear() {
        this.mGestureDetector = null;
        this.f940nk = null;
        this.mDrawable = null;
        this.f946uk.stop();
        this.f946uk = null;
        this.f950xk.stop();
        this.f950xk = null;
        this.f951yk.stop();
        this.f951yk = null;
        this.f952zk.stop();
        this.f952zk = null;
        setOnClickListener((View.OnClickListener) null);
        this.f941pk = null;
        this.f929Ek = false;
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.mDrawable == drawable) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        this.f929Ek = true;
        if (!this.f930Fk) {
            return m1204c(motionEvent);
        }
        return false;
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2 || !this.f930Fk || !this.f929Ek) {
                    return false;
                }
                int x = (int) (motionEvent.getX() - this.mDownFocusX);
                int y = (int) (motionEvent.getY() - this.mDownFocusY);
                if ((y * y) + (x * x) <= f918Gk) {
                    return false;
                }
                this.f929Ek = false;
                return false;
            } else if (this.f930Fk) {
                return m1204c(motionEvent);
            } else {
                return false;
            }
        } else if (!this.f930Fk) {
            return false;
        } else {
            this.mDownFocusX = motionEvent.getX();
            this.mDownFocusY = motionEvent.getY();
            return false;
        }
    }

    public boolean onDown(MotionEvent motionEvent) {
        if (!this.f942qk) {
            return true;
        }
        this.f950xk.stop();
        this.f951yk.stop();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDrawable != null) {
            int saveCount = canvas.getSaveCount();
            canvas.save();
            Matrix matrix = this.f931ek;
            if (matrix != null) {
                canvas.concat(matrix);
            }
            this.mDrawable.draw(canvas);
            canvas.restoreToCount(saveCount);
            if (this.f935ik != null) {
                Bitmap bitmap = this.f936jk ? f920Ik : f921Jk;
                canvas.drawBitmap(bitmap, (float) ((getWidth() - bitmap.getWidth()) / 2), (float) ((getHeight() - bitmap.getHeight()) / 2), (Paint) null);
            }
            this.f928Dk.set(this.mDrawable.getBounds());
            Matrix matrix2 = this.f931ek;
            if (matrix2 != null) {
                matrix2.mapRect(this.f928Dk);
            }
            if (this.f947vf) {
                int saveCount2 = canvas.getSaveCount();
                canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), f922Kk);
                canvas.save();
                canvas.clipRect(this.f937kk);
                Matrix matrix3 = this.f931ek;
                if (matrix3 != null) {
                    canvas.concat(matrix3);
                }
                this.mDrawable.draw(canvas);
                canvas.restoreToCount(saveCount2);
                canvas.drawRect(this.f937kk, f923Lk);
            }
        }
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.f942qk || this.f946uk.mRunning) {
            return true;
        }
        this.f950xk.mo5851f(f, f2);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f934hk = true;
        int width = getWidth();
        int height = getHeight();
        if (this.f947vf) {
            this.f938lk = Math.min(f919Hk, Math.min(width, height));
            int i5 = this.f938lk;
            int i6 = (width - i5) / 2;
            int i7 = (height - i5) / 2;
            this.f937kk.set(i6, i7, i6 + i5, i5 + i7);
        }
        m1197Aa(z);
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3 = this.f933gk;
        if (i3 != -1) {
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3, RtlSpacingHelper.UNDEFINED));
            setMeasuredDimension(getMeasuredWidth(), this.f933gk);
            return;
        }
        super.onMeasure(i, i2);
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        if (!this.f942qk || this.f946uk.mRunning) {
            return true;
        }
        this.f945tk = false;
        scale(scaleGestureDetector.getScaleFactor() * getScale(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        if (this.f942qk && !this.f946uk.mRunning) {
            this.f946uk.stop();
            this.f945tk = true;
        }
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        if (this.f942qk && this.f945tk) {
            this.f944sk = true;
            mo5814ac();
        }
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.f942qk || this.f946uk.mRunning) {
            return true;
        }
        translate(-f, -f2);
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        View.OnClickListener onClickListener = this.f941pk;
        if (onClickListener != null && !this.f945tk) {
            onClickListener.onClick(this);
        }
        this.f945tk = false;
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ScaleGestureDetector scaleGestureDetector = this.f940nk;
        if (!(scaleGestureDetector == null || this.mGestureDetector == null)) {
            scaleGestureDetector.onTouchEvent(motionEvent);
            this.mGestureDetector.onTouchEvent(motionEvent);
            int action = motionEvent.getAction();
            if ((action == 1 || action == 3) && !this.f950xk.mRunning) {
                m1208vn();
            }
        }
        return true;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f941pk = onClickListener;
    }

    public boolean verifyDrawable(Drawable drawable) {
        return this.mDrawable == drawable || super.verifyDrawable(drawable);
    }

    /* renamed from: y */
    public void mo5840y(boolean z) {
        this.f942qk = z;
        if (!this.f942qk) {
            mo5814ac();
        }
    }

    /* renamed from: c */
    private boolean m1204c(MotionEvent motionEvent) {
        boolean z;
        float f;
        float f2;
        if (!this.f943rk || !this.f942qk || !this.f929Ek) {
            z = false;
        } else {
            if (!this.f944sk) {
                float scale = getScale();
                float f3 = this.f948vk;
                if (scale > f3) {
                    float f4 = f3 / scale;
                    float width = ((float) (getWidth() / 2)) - (this.f928Dk.centerX() * f4);
                    float f5 = 1.0f - f4;
                    f = (((float) (getHeight() / 2)) - (this.f928Dk.centerY() * f4)) / f5;
                    f2 = width / f5;
                } else {
                    f3 = Math.min(this.f949wk, Math.max(f3, scale * 2.0f));
                    float f6 = f3 / scale;
                    float width2 = (((float) getWidth()) - this.f928Dk.width()) / f6;
                    float height = (((float) getHeight()) - this.f928Dk.height()) / f6;
                    if (this.f928Dk.width() <= width2 * 2.0f) {
                        f2 = this.f928Dk.centerX();
                    } else {
                        f2 = Math.min(Math.max(this.f928Dk.left + width2, motionEvent.getX()), this.f928Dk.right - width2);
                    }
                    if (this.f928Dk.height() <= 2.0f * height) {
                        f = this.f928Dk.centerY();
                    } else {
                        f = Math.min(Math.max(this.f928Dk.top + height, motionEvent.getY()), this.f928Dk.bottom - height);
                    }
                }
                this.f946uk.mo5845b(scale, f3, f2, f);
                z = true;
            } else {
                z = false;
            }
            this.f944sk = false;
        }
        this.f929Ek = false;
        return z;
    }

    /* renamed from: b */
    public void mo5815b(float f) {
        this.f939mk = f;
    }

    /* renamed from: d */
    public boolean mo5819d(float f, float f2) {
        if (!this.f942qk) {
            return false;
        }
        if (this.f950xk.mRunning) {
            return true;
        }
        this.mMatrix.getValues(this.mValues);
        this.f928Dk.set(this.f926Bk);
        this.mMatrix.mapRect(this.f928Dk);
        float width = (float) getWidth();
        float f3 = this.mValues[2];
        RectF rectF = this.f928Dk;
        float f4 = rectF.right - rectF.left;
        if (!this.f942qk || f4 <= width) {
            return false;
        }
        if (f3 != 0.0f && width >= f4 + f3) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public void mo5813a(Drawable drawable) {
        boolean z;
        Drawable drawable2;
        if (drawable == null || drawable == (drawable2 = this.mDrawable)) {
            z = false;
        } else {
            if (drawable2 != null) {
                drawable2.setCallback((Drawable.Callback) null);
            }
            this.mDrawable = drawable;
            this.f948vk = 0.0f;
            this.mDrawable.setCallback(this);
            z = true;
        }
        m1197Aa(z);
        invalidate();
    }

    /* renamed from: a */
    static /* synthetic */ void m1201a(PhotoView photoView, float f, boolean z) {
        if (z) {
            photoView.f952zk.mo5842c(f);
            return;
        }
        photoView.f925Ak += f;
        photoView.mMatrix.postRotate(f, (float) (photoView.getWidth() / 2), (float) (photoView.getHeight() / 2));
        photoView.invalidate();
    }

    /* renamed from: d */
    public void mo5818d(boolean z, boolean z2) {
        if (z != this.f924Aj) {
            this.f924Aj = z;
            requestLayout();
            invalidate();
        }
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize();
    }

    /* renamed from: c */
    public boolean mo5816c(float f, float f2) {
        if (!this.f942qk) {
            return false;
        }
        if (this.f950xk.mRunning) {
            return true;
        }
        this.mMatrix.getValues(this.mValues);
        this.f928Dk.set(this.f926Bk);
        this.mMatrix.mapRect(this.f928Dk);
        float width = (float) getWidth();
        float f3 = this.mValues[2];
        RectF rectF = this.f928Dk;
        float f4 = rectF.right - rectF.left;
        if (!this.f942qk || f4 <= width || f3 == 0.0f) {
            return false;
        }
        if (width >= f4 + f3) {
        }
        return true;
    }
}
