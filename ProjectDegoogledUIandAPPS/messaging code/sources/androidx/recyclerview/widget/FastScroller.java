package androidx.recyclerview.widget;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.core.view.ViewCompat;

class FastScroller extends C0550X implements C0564fa {
    private static final int[] EMPTY_STATE_SET = new int[0];
    private static final int[] PRESSED_STATE_SET = {16842919};

    /* renamed from: Ar */
    private final Drawable f521Ar;

    /* renamed from: Br */
    private final int f522Br;

    /* renamed from: Cr */
    private final int f523Cr;

    /* renamed from: Dr */
    private int f524Dr = 0;

    /* renamed from: Er */
    private int f525Er = 0;

    /* renamed from: Fr */
    private boolean f526Fr = false;

    /* renamed from: Gr */
    private boolean f527Gr = false;

    /* renamed from: Hr */
    private int f528Hr = 0;

    /* renamed from: Ir */
    private final int[] f529Ir = new int[2];

    /* renamed from: Jr */
    private final int[] f530Jr = new int[2];

    /* renamed from: Kr */
    final ValueAnimator f531Kr = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});

    /* renamed from: Lr */
    int f532Lr = 0;

    /* renamed from: Mr */
    private final C0566ga f533Mr = new C0585q(this);
    private final Runnable mHideRunnable = new C0583p(this);
    float mHorizontalDragX;
    int mHorizontalThumbCenterX;
    int mHorizontalThumbWidth;
    private RecyclerView mRecyclerView;
    private int mState = 0;
    float mVerticalDragY;
    int mVerticalThumbCenterY;
    int mVerticalThumbHeight;

    /* renamed from: tr */
    private final int f534tr;

    /* renamed from: ur */
    private final int f535ur;

    /* renamed from: vr */
    final StateListDrawable f536vr;

    /* renamed from: wr */
    final Drawable f537wr;

    /* renamed from: xr */
    private final int f538xr;

    /* renamed from: yr */
    private final int f539yr;

    /* renamed from: zr */
    private final StateListDrawable f540zr;

    FastScroller(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
        this.f536vr = stateListDrawable;
        this.f537wr = drawable;
        this.f540zr = stateListDrawable2;
        this.f521Ar = drawable2;
        this.f538xr = Math.max(i, stateListDrawable.getIntrinsicWidth());
        this.f539yr = Math.max(i, drawable.getIntrinsicWidth());
        this.f522Br = Math.max(i, stateListDrawable2.getIntrinsicWidth());
        this.f523Cr = Math.max(i, drawable2.getIntrinsicWidth());
        this.f534tr = i2;
        this.f535ur = i3;
        this.f536vr.setAlpha(255);
        this.f537wr.setAlpha(255);
        this.f531Kr.addListener(new C0587r(this));
        this.f531Kr.addUpdateListener(new C0589s(this));
        mo4666c(recyclerView);
    }

    /* renamed from: Jn */
    private void m576Jn() {
        this.mRecyclerView.removeCallbacks(this.mHideRunnable);
    }

    private boolean isLayoutRTL() {
        return ViewCompat.getLayoutDirection(this.mRecyclerView) == 1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Nc */
    public void mo4662Nc() {
        this.mRecyclerView.invalidate();
    }

    /* renamed from: a */
    public void mo4663a(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mState != 0) {
            if (motionEvent.getAction() == 0) {
                boolean isPointInsideVerticalThumb = isPointInsideVerticalThumb(motionEvent.getX(), motionEvent.getY());
                boolean isPointInsideHorizontalThumb = isPointInsideHorizontalThumb(motionEvent.getX(), motionEvent.getY());
                if (isPointInsideVerticalThumb || isPointInsideHorizontalThumb) {
                    if (isPointInsideHorizontalThumb) {
                        this.f528Hr = 1;
                        this.mHorizontalDragX = (float) ((int) motionEvent.getX());
                    } else if (isPointInsideVerticalThumb) {
                        this.f528Hr = 2;
                        this.mVerticalDragY = (float) ((int) motionEvent.getY());
                    }
                    setState(2);
                }
            } else if (motionEvent.getAction() == 1 && this.mState == 2) {
                this.mVerticalDragY = 0.0f;
                this.mHorizontalDragX = 0.0f;
                setState(1);
                this.f528Hr = 0;
            } else if (motionEvent.getAction() == 2 && this.mState == 2) {
                show();
                if (this.f528Hr == 1) {
                    float x = motionEvent.getX();
                    int[] iArr = this.f530Jr;
                    int i = this.f535ur;
                    iArr[0] = i;
                    iArr[1] = this.f524Dr - i;
                    float max = Math.max((float) iArr[0], Math.min((float) iArr[1], x));
                    if (Math.abs(((float) this.mHorizontalThumbCenterX) - max) >= 2.0f) {
                        int a = m577a(this.mHorizontalDragX, max, iArr, this.mRecyclerView.computeHorizontalScrollRange(), this.mRecyclerView.computeHorizontalScrollOffset(), this.f524Dr);
                        if (a != 0) {
                            this.mRecyclerView.scrollBy(a, 0);
                        }
                        this.mHorizontalDragX = max;
                    }
                }
                if (this.f528Hr == 2) {
                    float y = motionEvent.getY();
                    int[] iArr2 = this.f529Ir;
                    int i2 = this.f535ur;
                    iArr2[0] = i2;
                    iArr2[1] = this.f525Er - i2;
                    float max2 = Math.max((float) iArr2[0], Math.min((float) iArr2[1], y));
                    if (Math.abs(((float) this.mVerticalThumbCenterY) - max2) >= 2.0f) {
                        int a2 = m577a(this.mVerticalDragY, max2, iArr2, this.mRecyclerView.computeVerticalScrollRange(), this.mRecyclerView.computeVerticalScrollOffset(), this.f525Er);
                        if (a2 != 0) {
                            this.mRecyclerView.scrollBy(0, a2);
                        }
                        this.mVerticalDragY = max2;
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public void mo4664b(Canvas canvas, RecyclerView recyclerView, C0582oa oaVar) {
        if (this.f524Dr != this.mRecyclerView.getWidth() || this.f525Er != this.mRecyclerView.getHeight()) {
            this.f524Dr = this.mRecyclerView.getWidth();
            this.f525Er = this.mRecyclerView.getHeight();
            setState(0);
        } else if (this.f532Lr != 0) {
            if (this.f526Fr) {
                int i = this.f524Dr;
                int i2 = this.f538xr;
                int i3 = i - i2;
                int i4 = this.mVerticalThumbCenterY;
                int i5 = this.mVerticalThumbHeight;
                int i6 = i4 - (i5 / 2);
                this.f536vr.setBounds(0, 0, i2, i5);
                this.f537wr.setBounds(0, 0, this.f539yr, this.f525Er);
                if (isLayoutRTL()) {
                    this.f537wr.draw(canvas);
                    canvas.translate((float) this.f538xr, (float) i6);
                    canvas.scale(-1.0f, 1.0f);
                    this.f536vr.draw(canvas);
                    canvas.scale(1.0f, 1.0f);
                    canvas.translate((float) (-this.f538xr), (float) (-i6));
                } else {
                    canvas.translate((float) i3, 0.0f);
                    this.f537wr.draw(canvas);
                    canvas.translate(0.0f, (float) i6);
                    this.f536vr.draw(canvas);
                    canvas.translate((float) (-i3), (float) (-i6));
                }
            }
            if (this.f527Gr) {
                int i7 = this.f525Er;
                int i8 = this.f522Br;
                int i9 = i7 - i8;
                int i10 = this.mHorizontalThumbCenterX;
                int i11 = this.mHorizontalThumbWidth;
                int i12 = i10 - (i11 / 2);
                this.f540zr.setBounds(0, 0, i11, i8);
                this.f521Ar.setBounds(0, 0, this.f524Dr, this.f523Cr);
                canvas.translate(0.0f, (float) i9);
                this.f521Ar.draw(canvas);
                canvas.translate((float) i12, 0.0f);
                this.f540zr.draw(canvas);
                canvas.translate((float) (-i12), (float) (-i9));
            }
        }
    }

    /* renamed from: c */
    public void mo4666c(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                recyclerView2.mo4834b((C0550X) this);
                this.mRecyclerView.mo4835b((C0564fa) this);
                this.mRecyclerView.mo4836b(this.f533Mr);
                m576Jn();
            }
            this.mRecyclerView = recyclerView;
            RecyclerView recyclerView3 = this.mRecyclerView;
            if (recyclerView3 != null) {
                recyclerView3.mo4820a((C0550X) this);
                this.mRecyclerView.mo4823a((C0564fa) this);
                this.mRecyclerView.mo4824a(this.f533Mr);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Drawable getHorizontalThumbDrawable() {
        return this.f540zr;
    }

    /* access modifiers changed from: package-private */
    public Drawable getHorizontalTrackDrawable() {
        return this.f521Ar;
    }

    /* access modifiers changed from: package-private */
    public Drawable getVerticalThumbDrawable() {
        return this.f536vr;
    }

    /* access modifiers changed from: package-private */
    public Drawable getVerticalTrackDrawable() {
        return this.f537wr;
    }

    /* access modifiers changed from: package-private */
    public void hide(int i) {
        int i2 = this.f532Lr;
        if (i2 == 1) {
            this.f531Kr.cancel();
        } else if (i2 != 2) {
            return;
        }
        this.f532Lr = 3;
        ValueAnimator valueAnimator = this.f531Kr;
        valueAnimator.setFloatValues(new float[]{((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f});
        this.f531Kr.setDuration((long) i);
        this.f531Kr.start();
    }

    /* access modifiers changed from: package-private */
    public boolean isPointInsideHorizontalThumb(float f, float f2) {
        if (f2 >= ((float) (this.f525Er - this.f522Br))) {
            int i = this.mHorizontalThumbCenterX;
            int i2 = this.mHorizontalThumbWidth;
            return f >= ((float) (i - (i2 / 2))) && f <= ((float) ((i2 / 2) + i));
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isPointInsideVerticalThumb(float f, float f2) {
        if (ViewCompat.getLayoutDirection(this.mRecyclerView) == 1) {
            if (f > ((float) (this.f538xr / 2))) {
                return false;
            }
        } else if (f < ((float) (this.f524Dr - this.f538xr))) {
            return false;
        }
        int i = this.mVerticalThumbCenterY;
        int i2 = this.mVerticalThumbHeight / 2;
        if (f2 < ((float) (i - i2)) || f2 > ((float) (i2 + i))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isVisible() {
        return this.mState == 1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public void mo4675k(int i, int i2) {
        int computeVerticalScrollRange = this.mRecyclerView.computeVerticalScrollRange();
        int i3 = this.f525Er;
        this.f526Fr = computeVerticalScrollRange - i3 > 0 && i3 >= this.f534tr;
        int computeHorizontalScrollRange = this.mRecyclerView.computeHorizontalScrollRange();
        int i4 = this.f524Dr;
        this.f527Gr = computeHorizontalScrollRange - i4 > 0 && i4 >= this.f534tr;
        if (this.f526Fr || this.f527Gr) {
            if (this.f526Fr) {
                float f = (float) i3;
                this.mVerticalThumbCenterY = (int) ((((f / 2.0f) + ((float) i2)) * f) / ((float) computeVerticalScrollRange));
                this.mVerticalThumbHeight = Math.min(i3, (i3 * i3) / computeVerticalScrollRange);
            }
            if (this.f527Gr) {
                float f2 = (float) i4;
                this.mHorizontalThumbCenterX = (int) ((((f2 / 2.0f) + ((float) i)) * f2) / ((float) computeHorizontalScrollRange));
                this.mHorizontalThumbWidth = Math.min(i4, (i4 * i4) / computeHorizontalScrollRange);
            }
            int i5 = this.mState;
            if (i5 == 0 || i5 == 1) {
                setState(1);
            }
        } else if (this.mState != 0) {
            setState(0);
        }
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    /* access modifiers changed from: package-private */
    public void setState(int i) {
        if (i == 2 && this.mState != 2) {
            this.f536vr.setState(PRESSED_STATE_SET);
            m576Jn();
        }
        if (i == 0) {
            mo4662Nc();
        } else {
            show();
        }
        if (this.mState == 2 && i != 2) {
            this.f536vr.setState(EMPTY_STATE_SET);
            m576Jn();
            this.mRecyclerView.postDelayed(this.mHideRunnable, (long) 1200);
        } else if (i == 1) {
            m576Jn();
            this.mRecyclerView.postDelayed(this.mHideRunnable, (long) 1500);
        }
        this.mState = i;
    }

    public void show() {
        int i = this.f532Lr;
        if (i != 0) {
            if (i == 3) {
                this.f531Kr.cancel();
            } else {
                return;
            }
        }
        this.f532Lr = 1;
        ValueAnimator valueAnimator = this.f531Kr;
        valueAnimator.setFloatValues(new float[]{((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f});
        this.f531Kr.setDuration(500);
        this.f531Kr.setStartDelay(0);
        this.f531Kr.start();
    }

    /* renamed from: b */
    public boolean mo4665b(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i = this.mState;
        if (i == 1) {
            boolean isPointInsideVerticalThumb = isPointInsideVerticalThumb(motionEvent.getX(), motionEvent.getY());
            boolean isPointInsideHorizontalThumb = isPointInsideHorizontalThumb(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!isPointInsideVerticalThumb && !isPointInsideHorizontalThumb) {
                return false;
            }
            if (isPointInsideHorizontalThumb) {
                this.f528Hr = 1;
                this.mHorizontalDragX = (float) ((int) motionEvent.getX());
            } else if (isPointInsideVerticalThumb) {
                this.f528Hr = 2;
                this.mVerticalDragY = (float) ((int) motionEvent.getY());
            }
            setState(2);
        } else if (i != 2) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private int m577a(float f, float f2, int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[1] - iArr[0];
        if (i4 == 0) {
            return 0;
        }
        int i5 = i - i3;
        int i6 = (int) (((f2 - f) / ((float) i4)) * ((float) i5));
        int i7 = i2 + i6;
        if (i7 >= i5 || i7 < 0) {
            return 0;
        }
        return i6;
    }
}
