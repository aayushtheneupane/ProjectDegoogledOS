package com.android.messaging.p041ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.rastermill.FrameSequenceDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.android.messaging.C0970i;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.datamodel.p038b.C0839B;
import com.android.messaging.datamodel.p038b.C0840C;
import com.android.messaging.datamodel.p038b.C0865e;
import com.android.messaging.datamodel.p038b.C0878r;
import com.android.messaging.datamodel.p038b.C0880t;
import com.android.messaging.datamodel.p038b.C0881u;
import com.android.messaging.datamodel.p038b.C0883w;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1480va;
import com.android.messaging.util.C1486ya;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.AsyncImageView */
public class AsyncImageView extends ImageView implements C0839B {

    /* renamed from: Kj */
    private boolean f1561Kj;

    /* renamed from: Lj */
    private final boolean f1562Lj;

    /* renamed from: Mj */
    private final Drawable f1563Mj;

    /* renamed from: Nj */
    protected C0881u f1564Nj;

    /* renamed from: Oj */
    private final Runnable f1565Oj = new C1073a(this);
    /* access modifiers changed from: private */

    /* renamed from: Pj */
    public C0880t f1566Pj;

    /* renamed from: eh */
    private final Path f1567eh;

    /* renamed from: fh */
    private int f1568fh;

    /* renamed from: gh */
    private int f1569gh;
    private final int mCornerRadius;
    public final C0783c mImageRequestBinding = C0784d.m1315q(this);

    public AsyncImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0970i.AsyncImageView, 0, 0);
        this.f1561Kj = obtainStyledAttributes.getBoolean(1, true);
        this.f1562Lj = obtainStyledAttributes.getBoolean(3, false);
        this.f1563Mj = obtainStyledAttributes.getDrawable(2);
        this.mCornerRadius = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.f1567eh = new Path();
        obtainStyledAttributes.recycle();
    }

    /* renamed from: d */
    private static int m2458d(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(Math.min(i, size), i2);
        }
        if (mode == 0) {
            return Math.min(i, i2);
        }
        C1424b.fail("Unreachable");
        return size;
    }

    /* access modifiers changed from: private */
    /* renamed from: qn */
    public void m2459qn() {
        Drawable drawable = getDrawable();
        if (drawable instanceof FrameSequenceDrawable) {
            FrameSequenceDrawable frameSequenceDrawable = (FrameSequenceDrawable) drawable;
            frameSequenceDrawable.stop();
            frameSequenceDrawable.destroy();
        }
        C0881u uVar = this.f1564Nj;
        if (uVar != null) {
            uVar.release();
            this.f1564Nj = null;
        }
        setImageDrawable((Drawable) null);
        setBackground((Drawable) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: rn */
    public void m2460rn() {
        if (this.mImageRequestBinding.isBound()) {
            this.mImageRequestBinding.unbind();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        C0880t tVar;
        super.onAttachedToWindow();
        C1480va.getMainThreadHandler().removeCallbacks(this.f1565Oj);
        if (this.f1561Kj) {
            setAlpha(1.0f);
        }
        if (!this.mImageRequestBinding.isBound() && (tVar = this.f1566Pj) != null) {
            mo6858a(tVar);
        }
        this.f1566Pj = null;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C1480va.getMainThreadHandler().postDelayed(this.f1565Oj, 100);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mCornerRadius > 0) {
            int width = getWidth();
            int height = getHeight();
            if (!(this.f1568fh == width && this.f1569gh == height)) {
                RectF rectF = new RectF(0.0f, 0.0f, (float) width, (float) height);
                this.f1567eh.reset();
                Path path = this.f1567eh;
                int i = this.mCornerRadius;
                path.addRoundRect(rectF, (float) i, (float) i, Path.Direction.CW);
                this.f1568fh = width;
                this.f1569gh = height;
            }
            int saveCount = canvas.getSaveCount();
            canvas.save();
            canvas.clipPath(this.f1567eh);
            super.onDraw(canvas);
            canvas.restoreToCount(saveCount);
            return;
        }
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth < getMinimumWidth() && measuredHeight < getMinimumHeight() && getAdjustViewBounds()) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            if (mode != 1073741824 || mode2 != 1073741824) {
                int d = m2458d(getMinimumWidth(), getMaxWidth(), i);
                int d2 = m2458d(getMinimumHeight(), getMaxHeight(), i2);
                float f = ((float) measuredWidth) / ((float) measuredHeight);
                if (f != 0.0f) {
                    if (measuredWidth < d) {
                        measuredHeight = m2458d((int) (((float) d) / f), getMaxHeight(), i2);
                        measuredWidth = (int) (((float) measuredHeight) * f);
                    }
                    if (measuredHeight < d2) {
                        measuredWidth = m2458d((int) (((float) d2) * f), getMaxWidth(), i);
                        measuredHeight = (int) (((float) measuredWidth) / f);
                    }
                    setMeasuredDimension(measuredWidth, measuredHeight);
                }
            }
        }
    }

    public void onMediaResourceLoadError(C0883w wVar, Exception exc) {
        m2460rn();
        mo6859a((C0881u) null);
    }

    /* renamed from: b */
    public void mo6861b(C1114b bVar) {
        C1424b.m3592ia(true);
    }

    public void onMediaResourceLoaded(C0883w wVar, C0881u uVar, boolean z) {
        if (this.f1564Nj != uVar) {
            mo6860a(uVar, z);
        }
    }

    /* renamed from: a */
    public void mo6858a(C0880t tVar) {
        String key = tVar == null ? null : tVar.getKey();
        if (!this.mImageRequestBinding.isBound()) {
            this.f1566Pj = null;
        } else if (!TextUtils.equals(((C0865e) this.mImageRequestBinding.getData()).getKey(), key)) {
            m2460rn();
        } else {
            return;
        }
        mo6859a((C0881u) null);
        clearAnimation();
        setAlpha(1.0f);
        if (!TextUtils.isEmpty(key)) {
            if (!TextUtils.isEmpty(tVar.getKey()) && this.f1563Mj != null) {
                if (!(tVar.f1129zC == -1 || tVar.f1124AC == -1)) {
                    setImageDrawable(C1247da.m3175a(new ColorDrawable(0), tVar.f1129zC, tVar.f1124AC));
                }
                setBackground(this.f1563Mj);
            }
            C0865e a = tVar.mo6164a(getContext(), this);
            this.mImageRequestBinding.mo5930b(a);
            C0840C.get().mo6080a(a);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo6859a(C0881u uVar) {
        mo6860a(uVar, false);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo6860a(C0881u uVar, boolean z) {
        m2459qn();
        C1480va.getMainThreadHandler().removeCallbacks(this.f1565Oj);
        Drawable b = uVar != null ? uVar.mo6156b(getResources()) : null;
        if (b != null) {
            this.f1564Nj = uVar;
            this.f1564Nj.mo6100Oh();
            setImageDrawable(b);
            if (b instanceof FrameSequenceDrawable) {
                ((FrameSequenceDrawable) b).start();
            }
            if (getVisibility() == 0) {
                if (this.f1562Lj) {
                    setVisibility(4);
                    C1486ya.m3854a((View) this, 0, (Runnable) null);
                } else if (this.f1561Kj && !z) {
                    setAlpha(0.0f);
                    animate().alpha(1.0f).start();
                }
            }
            if (Log.isLoggable("MessagingAppDataModel", 2)) {
                if (this.f1564Nj instanceof C0878r) {
                    C1430e.m3628v("MessagingAppDataModel", "setImage size unknown -- it's a GIF");
                } else {
                    StringBuilder Pa = C0632a.m1011Pa("setImage size: ");
                    Pa.append(this.f1564Nj.getMediaSize());
                    Pa.append(" width: ");
                    Pa.append(this.f1564Nj.getBitmap().getWidth());
                    Pa.append(" heigh: ");
                    Pa.append(this.f1564Nj.getBitmap().getHeight());
                    C1430e.m3628v("MessagingAppDataModel", Pa.toString());
                }
            }
        }
        invalidate();
    }
}
