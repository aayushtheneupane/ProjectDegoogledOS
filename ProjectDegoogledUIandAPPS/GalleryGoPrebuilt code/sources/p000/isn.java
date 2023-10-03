package p000;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* renamed from: isn */
/* compiled from: PG */
public final class isn implements View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, isg, ist {

    /* renamed from: a */
    public final Interpolator f15015a = new AccelerateDecelerateInterpolator();

    /* renamed from: b */
    public final int f15016b = 200;

    /* renamed from: c */
    public float f15017c = 1.0f;

    /* renamed from: d */
    public float f15018d = 1.75f;

    /* renamed from: e */
    public float f15019e = 3.0f;

    /* renamed from: f */
    public final boolean f15020f = true;

    /* renamed from: g */
    public boolean f15021g = false;

    /* renamed from: h */
    public iss f15022h;

    /* renamed from: i */
    public final Matrix f15023i = new Matrix();

    /* renamed from: j */
    public ism f15024j;

    /* renamed from: k */
    public View.OnLongClickListener f15025k;

    /* renamed from: l */
    public isl f15026l;

    /* renamed from: m */
    public int f15027m = 2;

    /* renamed from: n */
    public ImageView.ScaleType f15028n = ImageView.ScaleType.FIT_CENTER;

    /* renamed from: o */
    private WeakReference f15029o;

    /* renamed from: p */
    private GestureDetector f15030p;

    /* renamed from: q */
    private final Matrix f15031q = new Matrix();

    /* renamed from: r */
    private final Matrix f15032r = new Matrix();

    /* renamed from: s */
    private final RectF f15033s = new RectF();

    /* renamed from: t */
    private final float[] f15034t = new float[9];

    /* renamed from: u */
    private int f15035u;

    /* renamed from: v */
    private int f15036v;

    /* renamed from: w */
    private int f15037w;

    /* renamed from: x */
    private int f15038x;

    /* renamed from: y */
    private boolean f15039y;

    public isn(ImageView imageView) {
        this.f15029o = new WeakReference(imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.setOnTouchListener(this);
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        m14391c(imageView);
        if (!imageView.isInEditMode()) {
            Context context = imageView.getContext();
            int i = Build.VERSION.SDK_INT;
            isr isr = new isr(context);
            isr.f15040a = this;
            this.f15022h = isr;
            GestureDetector gestureDetector = new GestureDetector(imageView.getContext(), new isi(this));
            this.f15030p = gestureDetector;
            gestureDetector.setOnDoubleTapListener(new isf(this));
            mo9092a(true);
        }
    }

    /* renamed from: h */
    private final void m14392h() {
        isl isl = this.f15026l;
        if (isl != null) {
            ((isu) isl.f15011a).f15051a.forceFinished(true);
            this.f15026l = null;
        }
    }

    /* renamed from: g */
    public final void mo9099g() {
        if (m14393i()) {
            mo9091a(mo9098f());
        }
    }

    /* renamed from: i */
    private final boolean m14393i() {
        RectF b;
        float f;
        ImageView c = mo9095c();
        if (c == null || (b = m14390b(mo9098f())) == null) {
            return false;
        }
        float height = b.height();
        float width = b.width();
        float a = (float) m14387a(c);
        float f2 = 0.0f;
        if (height <= a) {
            int i = isj.f15004a[this.f15028n.ordinal()];
            f = i != 2 ? i != 3 ? ((a - height) / 2.0f) - b.top : (a - height) - b.top : -b.top;
        } else {
            f = b.top > 0.0f ? -b.top : b.bottom >= a ? 0.0f : a - b.bottom;
        }
        float b2 = (float) m14389b(c);
        if (width <= b2) {
            int i2 = isj.f15004a[this.f15028n.ordinal()];
            if (i2 == 2) {
                f2 = -b.left;
            } else if (i2 != 3) {
                f2 = ((b2 - width) / 2.0f) - b.left;
            } else {
                f2 = (b2 - width) - b.left;
            }
            this.f15027m = 2;
        } else if (b.left > 0.0f) {
            this.f15027m = 0;
            f2 = -b.left;
        } else if (b.right < b2) {
            f2 = b2 - b.right;
            this.f15027m = 1;
        } else {
            this.f15027m = -1;
        }
        this.f15023i.postTranslate(f2, f);
        return true;
    }

    /* renamed from: a */
    public final void mo9089a() {
        WeakReference weakReference = this.f15029o;
        if (weakReference != null) {
            ImageView imageView = (ImageView) weakReference.get();
            if (imageView != null) {
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                imageView.setOnTouchListener((View.OnTouchListener) null);
                m14392h();
            }
            GestureDetector gestureDetector = this.f15030p;
            if (gestureDetector != null) {
                gestureDetector.setOnDoubleTapListener((GestureDetector.OnDoubleTapListener) null);
            }
            this.f15024j = null;
            this.f15029o = null;
        }
    }

    /* renamed from: b */
    public final RectF mo9093b() {
        m14393i();
        return m14390b(mo9098f());
    }

    /* renamed from: b */
    private final RectF m14390b(Matrix matrix) {
        Drawable drawable;
        ImageView c = mo9095c();
        if (c == null || (drawable = c.getDrawable()) == null) {
            return null;
        }
        this.f15033s.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        matrix.mapRect(this.f15033s);
        return this.f15033s;
    }

    @Deprecated
    /* renamed from: f */
    public final Matrix mo9098f() {
        this.f15032r.set(this.f15031q);
        this.f15032r.postConcat(this.f15023i);
        return this.f15032r;
    }

    /* renamed from: c */
    public final ImageView mo9095c() {
        ImageView imageView;
        WeakReference weakReference = this.f15029o;
        if (weakReference != null) {
            imageView = (ImageView) weakReference.get();
        } else {
            imageView = null;
        }
        if (imageView == null) {
            mo9089a();
        }
        return imageView;
    }

    /* renamed from: a */
    public static final int m14387a(ImageView imageView) {
        if (imageView != null) {
            return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
        }
        return 0;
    }

    /* renamed from: b */
    public static final int m14389b(ImageView imageView) {
        if (imageView != null) {
            return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
        }
        return 0;
    }

    /* renamed from: d */
    public final float mo9096d() {
        return (float) Math.sqrt((double) (((float) Math.pow((double) m14386a(this.f15023i, 0), 2.0d)) + ((float) Math.pow((double) m14386a(this.f15023i, 3), 2.0d))));
    }

    /* renamed from: a */
    private final float m14386a(Matrix matrix, int i) {
        matrix.getValues(this.f15034t);
        return this.f15034t[i];
    }

    public final void onGlobalLayout() {
        ImageView c = mo9095c();
        if (c == null) {
            return;
        }
        if (this.f15039y) {
            int top = c.getTop();
            int right = c.getRight();
            int bottom = c.getBottom();
            int left = c.getLeft();
            if (top != this.f15035u || bottom != this.f15037w || left != this.f15038x || right != this.f15036v) {
                m14388a(c.getDrawable());
                this.f15035u = top;
                this.f15036v = right;
                this.f15037w = bottom;
                this.f15038x = left;
                return;
            }
            return;
        }
        m14388a(c.getDrawable());
    }

    /* renamed from: a */
    public final void mo9090a(float f, float f2, float f3) {
        if (f <= 0.0f) {
            return;
        }
        if (mo9096d() >= this.f15019e && f >= 1.0f) {
            return;
        }
        if (mo9096d() > this.f15017c || f > 1.0f) {
            this.f15023i.postScale(f, f, f2, f3);
            mo9099g();
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        ImageView imageView;
        boolean z;
        RectF b;
        boolean z2 = false;
        if (!(!this.f15039y || (imageView = (ImageView) view) == null || imageView.getDrawable() == null)) {
            ViewParent parent = view.getParent();
            int action = motionEvent.getAction();
            if (action == 0) {
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                m14392h();
                z = false;
            } else if ((action == 1 || action == 3) && mo9096d() < this.f15017c && (b = mo9093b()) != null) {
                view.post(new isk(this, mo9096d(), this.f15017c, b.centerX(), b.centerY()));
                z = true;
            } else {
                z = false;
            }
            iss iss = this.f15022h;
            if (iss != null) {
                boolean a = iss.mo9103a();
                iss iss2 = this.f15022h;
                boolean z3 = ((iso) iss2).f15046g;
                iss2.mo9105c(motionEvent);
                boolean z4 = !a && !this.f15022h.mo9103a();
                boolean z5 = !z3 && !((iso) this.f15022h).f15046g;
                if (z4 && z5) {
                    z2 = true;
                }
                this.f15021g = z2;
                z2 = true;
            } else {
                z2 = z;
            }
            GestureDetector gestureDetector = this.f15030p;
            if (gestureDetector == null || !gestureDetector.onTouchEvent(motionEvent)) {
                return z2;
            }
            return true;
        }
        return z2;
    }

    /* renamed from: j */
    private final void m14394j() {
        this.f15023i.reset();
        this.f15023i.postRotate(0.0f);
        mo9099g();
        mo9091a(mo9098f());
        m14393i();
    }

    /* renamed from: a */
    public final void mo9091a(Matrix matrix) {
        ImageView c = mo9095c();
        if (c != null) {
            ImageView c2 = mo9095c();
            if (c2 == null || (c2 instanceof isg) || ImageView.ScaleType.MATRIX.equals(c2.getScaleType())) {
                c.setImageMatrix(matrix);
                return;
            }
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher. You should call setScaleType on the PhotoViewAttacher instead of on the ImageView");
        }
    }

    /* renamed from: c */
    private static void m14391c(ImageView imageView) {
        if (!(imageView instanceof isg) && !ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
        }
    }

    /* renamed from: b */
    public final void mo9094b(float f, float f2, float f3) {
        ImageView c = mo9095c();
        if (c != null && f >= this.f15017c && f <= this.f15019e) {
            c.post(new isk(this, mo9096d(), f, f2, f3));
        }
    }

    /* renamed from: a */
    public final void mo9092a(boolean z) {
        this.f15039y = z;
        mo9097e();
    }

    /* renamed from: e */
    public final void mo9097e() {
        ImageView c = mo9095c();
        if (c == null) {
            return;
        }
        if (this.f15039y) {
            m14391c(c);
            m14388a(c.getDrawable());
            return;
        }
        m14394j();
    }

    /* renamed from: a */
    private final void m14388a(Drawable drawable) {
        ImageView c = mo9095c();
        if (c != null && drawable != null) {
            float b = (float) m14389b(c);
            float a = (float) m14387a(c);
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.f15031q.reset();
            float f = (float) intrinsicWidth;
            float f2 = b / f;
            float f3 = (float) intrinsicHeight;
            float f4 = a / f3;
            if (this.f15028n == ImageView.ScaleType.CENTER) {
                this.f15031q.postTranslate((b - f) / 2.0f, (a - f3) / 2.0f);
            } else if (this.f15028n == ImageView.ScaleType.CENTER_CROP) {
                float max = Math.max(f2, f4);
                this.f15031q.postScale(max, max);
                this.f15031q.postTranslate((b - (f * max)) / 2.0f, (a - (f3 * max)) / 2.0f);
            } else if (this.f15028n == ImageView.ScaleType.CENTER_INSIDE) {
                float min = Math.min(1.0f, Math.min(f2, f4));
                this.f15031q.postScale(min, min);
                this.f15031q.postTranslate((b - (f * min)) / 2.0f, (a - (f3 * min)) / 2.0f);
            } else {
                RectF rectF = new RectF(0.0f, 0.0f, f, f3);
                RectF rectF2 = new RectF(0.0f, 0.0f, b, a);
                int i = isj.f15004a[this.f15028n.ordinal()];
                if (i == 2) {
                    this.f15031q.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
                } else if (i == 3) {
                    this.f15031q.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
                } else if (i == 4) {
                    this.f15031q.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
                } else if (i == 5) {
                    this.f15031q.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
                }
            }
            m14394j();
        }
    }
}
