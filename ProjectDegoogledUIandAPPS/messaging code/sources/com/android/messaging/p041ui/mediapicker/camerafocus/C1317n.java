package com.android.messaging.p041ui.mediapicker.camerafocus;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import com.android.messaging.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.android.messaging.ui.mediapicker.camerafocus.n */
public class C1317n extends C1307d {
    /* access modifiers changed from: private */

    /* renamed from: Kj */
    public C1315l f2084Kj;
    /* access modifiers changed from: private */

    /* renamed from: UI */
    public Runnable f2085UI = new C1313j(this, (C1309f) null);

    /* renamed from: VI */
    private Point f2086VI;

    /* renamed from: WI */
    private int f2087WI;

    /* renamed from: XI */
    private int f2088XI;

    /* renamed from: ZI */
    private C1308e f2089ZI;

    /* renamed from: _I */
    private Paint f2090_I;

    /* renamed from: aJ */
    private Paint f2091aJ;

    /* renamed from: bJ */
    private C1308e f2092bJ;

    /* renamed from: cJ */
    private Paint f2093cJ;

    /* renamed from: dJ */
    private int f2094dJ;

    /* renamed from: eJ */
    private int f2095eJ;

    /* renamed from: fJ */
    private int f2096fJ;
    /* access modifiers changed from: private */

    /* renamed from: gJ */
    public int f2097gJ;
    /* access modifiers changed from: private */

    /* renamed from: hJ */
    public int f2098hJ;
    /* access modifiers changed from: private */

    /* renamed from: iJ */
    public int f2099iJ;

    /* renamed from: jJ */
    private RectF f2100jJ;

    /* renamed from: kJ */
    private RectF f2101kJ;

    /* renamed from: lJ */
    private Point f2102lJ;
    private C1316m mAnimation = new C1316m(this);
    /* access modifiers changed from: private */
    public int mCenterX;
    /* access modifiers changed from: private */
    public int mCenterY;
    private Animation.AnimationListener mEndAction = new C1314k(this, (C1309f) null);
    private Handler mHandler = new C1309f(this);
    private List mItems;

    /* renamed from: mJ */
    private Point f2103mJ;
    private int mRadius;
    /* access modifiers changed from: private */
    public volatile int mState;

    /* renamed from: nJ */
    private int f2104nJ;
    /* access modifiers changed from: private */

    /* renamed from: oJ */
    public boolean f2105oJ;

    /* renamed from: pJ */
    private int f2106pJ;

    /* renamed from: qJ */
    private int f2107qJ;

    /* renamed from: rJ */
    private int f2108rJ;

    /* renamed from: sJ */
    private boolean f2109sJ;

    /* renamed from: tJ */
    private boolean f2110tJ;

    /* renamed from: uJ */
    private int f2111uJ;

    /* renamed from: vJ */
    private Point f2112vJ;

    /* renamed from: wJ */
    private boolean f2113wJ;
    /* access modifiers changed from: private */

    /* renamed from: xJ */
    public C1315l f2114xJ;
    /* access modifiers changed from: private */

    /* renamed from: yJ */
    public volatile boolean f2115yJ;

    public C1317n(Context context) {
        this.mVisible = false;
        update();
        this.mItems = new ArrayList();
        Resources resources = context.getResources();
        this.mRadius = resources.getDimensionPixelSize(R.dimen.pie_radius_start);
        this.f2096fJ = this.mRadius - resources.getDimensionPixelSize(R.dimen.focus_radius_offset);
        this.f2087WI = resources.getDimensionPixelSize(R.dimen.pie_radius_increment);
        this.f2088XI = resources.getDimensionPixelSize(R.dimen.pie_touch_offset);
        this.f2086VI = new Point(0, 0);
        this.f2090_I = new Paint();
        this.f2090_I.setColor(Color.argb(255, 51, 181, 229));
        this.f2090_I.setAntiAlias(true);
        this.f2091aJ = new Paint();
        this.f2091aJ.setAntiAlias(true);
        this.f2091aJ.setColor(Color.argb(200, 250, 230, 128));
        this.f2093cJ = new Paint();
        this.f2093cJ.setAntiAlias(true);
        this.f2093cJ.setColor(-1);
        this.f2093cJ.setStyle(Paint.Style.STROKE);
        this.f2094dJ = -16711936;
        this.f2095eJ = -65536;
        this.f2100jJ = new RectF();
        this.f2101kJ = new RectF();
        this.f2102lJ = new Point();
        this.f2103mJ = new Point();
        this.f2106pJ = resources.getDimensionPixelSize(R.dimen.focus_inner_offset);
        this.f2107qJ = resources.getDimensionPixelSize(R.dimen.focus_outer_stroke);
        this.f2108rJ = resources.getDimensionPixelSize(R.dimen.focus_inner_stroke);
        this.mState = 0;
        this.f2110tJ = false;
        this.f2111uJ = ViewConfiguration.get(context).getScaledTouchSlop();
        int i = this.f2111uJ;
        this.f2111uJ = i * i;
        this.f2112vJ = new Point();
    }

    /* renamed from: Jo */
    private void m3334Jo() {
        this.f2115yJ = true;
        this.mOverlay.removeCallbacks(this.f2085UI);
        C1316m mVar = this.mAnimation;
        if (mVar != null) {
            mVar.cancel();
        }
        this.f2115yJ = false;
        this.f2105oJ = false;
        this.mState = 0;
    }

    /* access modifiers changed from: private */
    /* renamed from: Ko */
    public void m3335Ko() {
        C1308e eVar = this.f2092bJ;
        if (eVar != null) {
            eVar.setSelected(false);
        }
        if (this.f2089ZI != null) {
            this.f2089ZI = null;
        }
        this.f2092bJ = null;
    }

    /* renamed from: Lo */
    private void m3336Lo() {
        int i = this.mRadius;
        m3348a(this.mItems, 1.5707964f, i + 2, (i + this.f2087WI) - 2, 1);
    }

    /* access modifiers changed from: private */
    /* renamed from: Y */
    public void m3337Y(int i, int i2) {
        RectF rectF = this.f2100jJ;
        int i3 = this.f2096fJ;
        rectF.set((float) (i - i3), (float) (i2 - i3), (float) (i + i3), (float) (i3 + i2));
        RectF rectF2 = this.f2101kJ;
        int i4 = this.f2096fJ;
        int i5 = this.f2106pJ;
        rectF2.set((float) ((i - i4) + i5), (float) ((i2 - i4) + i5), (float) ((i + i4) - i5), (float) ((i2 + i4) - i5));
    }

    /* renamed from: a */
    private float m3338a(double d) {
        return (float) (360.0d - ((d * 180.0d) / 3.141592653589793d));
    }

    /* renamed from: a */
    private void m3343a(long j, boolean z, float f, float f2) {
        this.mVisible = true;
        RenderOverlay renderOverlay = this.mOverlay;
        if (renderOverlay != null) {
            renderOverlay.update();
        }
        this.mAnimation.reset();
        this.mAnimation.setDuration(j);
        this.mAnimation.setScale(f, f2);
        this.mAnimation.setAnimationListener(z ? this.mEndAction : null);
        this.mOverlay.startAnimation(this.mAnimation);
        RenderOverlay renderOverlay2 = this.mOverlay;
        if (renderOverlay2 != null) {
            renderOverlay2.update();
        }
    }

    /* access modifiers changed from: private */
    public void show(boolean z) {
        if (z) {
            this.mState = 8;
            this.f2092bJ = null;
            this.f2089ZI = null;
            for (C1308e selected : this.mItems) {
                selected.setSelected(false);
            }
            m3336Lo();
            this.f2084Kj = new C1315l(this, 0.0f, 1.0f);
            this.f2084Kj.setDuration(200);
            this.f2084Kj.setAnimationListener(new C1310g(this));
            this.f2084Kj.startNow();
            this.mOverlay.startAnimation(this.f2084Kj);
        } else {
            this.mState = 0;
            this.f2109sJ = false;
            C1315l lVar = this.f2114xJ;
            if (lVar != null) {
                lVar.cancel();
            }
        }
        this.mVisible = z;
        update();
        this.mHandler.sendEmptyMessage(z ^ true ? 1 : 0);
    }

    /* renamed from: Bj */
    public void mo7838Bj() {
        if (this.mState != 8) {
            m3334Jo();
            this.f2104nJ = 67;
            int i = this.f2104nJ;
            m3343a(600, false, (float) i, (float) (i + ((int) ((Math.random() * 120.0d) - 0.0703125d))));
            this.mState = 1;
        }
    }

    /* renamed from: D */
    public void mo7839D(int i, int i2) {
        Point point = this.f2086VI;
        point.x = i;
        point.y = i2;
        this.mOverlay.removeCallbacks(this.f2085UI);
        this.mAnimation.cancel();
        this.mAnimation.reset();
        this.f2097gJ = i;
        this.f2098hJ = i2;
        this.f2099iJ = 157;
        m3337Y(i, i2);
        this.f2105oJ = false;
    }

    /* renamed from: E */
    public void mo7840E(int i, int i2) {
        this.f2097gJ = i;
        this.f2098hJ = i2;
        m3337Y(this.f2097gJ, this.f2098hJ);
    }

    public void clear() {
        if (this.mState != 8) {
            m3334Jo();
            this.mOverlay.post(this.f2085UI);
        }
    }

    /* renamed from: fa */
    public void mo7842fa(boolean z) {
        if (this.mState == 1) {
            boolean z2 = z;
            m3343a(100, z2, (float) this.f2099iJ, (float) this.f2104nJ);
            this.mState = 2;
            this.f2105oJ = false;
        }
    }

    /* renamed from: ga */
    public void mo7843ga(boolean z) {
        if (this.mState == 1) {
            boolean z2 = z;
            m3343a(100, z2, (float) this.f2099iJ, (float) this.f2104nJ);
            this.mState = 2;
            this.f2105oJ = true;
        }
    }

    public int getSize() {
        return this.f2096fJ * 2;
    }

    public void layout(int i, int i2, int i3, int i4) {
        this.mLeft = i;
        this.mRight = i3;
        this.mTop = i2;
        this.mBottom = i4;
        this.mCenterX = (i3 - i) / 2;
        this.mCenterY = (i4 - i2) / 2;
        this.f2097gJ = this.mCenterX;
        this.f2098hJ = this.mCenterY;
        m3337Y(this.f2097gJ, this.f2098hJ);
        if (this.mVisible && this.mState == 8) {
            mo7839D(this.mCenterX, this.mCenterY);
            m3336Lo();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00d5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDraw(android.graphics.Canvas r16) {
        /*
            r15 = this;
            r0 = r15
            r7 = r16
            com.android.messaging.ui.mediapicker.camerafocus.l r1 = r0.f2114xJ
            r8 = 1065353216(0x3f800000, float:1.0)
            if (r1 == 0) goto L_0x000f
            float r1 = r1.getValue()
        L_0x000d:
            r9 = r1
            goto L_0x0019
        L_0x000f:
            com.android.messaging.ui.mediapicker.camerafocus.l r1 = r0.f2084Kj
            if (r1 == 0) goto L_0x0018
            float r1 = r1.getValue()
            goto L_0x000d
        L_0x0018:
            r9 = r8
        L_0x0019:
            int r10 = r16.save()
            com.android.messaging.ui.mediapicker.camerafocus.l r1 = r0.f2084Kj
            if (r1 == 0) goto L_0x0034
            r1 = 1063675494(0x3f666666, float:0.9)
            r2 = 1036831949(0x3dcccccd, float:0.1)
            float r2 = r2 * r9
            float r2 = r2 + r1
            android.graphics.Point r1 = r0.f2086VI
            int r3 = r1.x
            float r3 = (float) r3
            int r1 = r1.y
            float r1 = (float) r1
            r7.scale(r2, r2, r3, r1)
        L_0x0034:
            boolean r1 = r0.f2110tJ
            r11 = 2
            if (r1 == 0) goto L_0x003b
            goto L_0x00cd
        L_0x003b:
            android.graphics.Paint r1 = r0.f2093cJ
            int r2 = r0.f2107qJ
            float r2 = (float) r2
            r1.setStrokeWidth(r2)
            int r1 = r0.f2097gJ
            float r1 = (float) r1
            int r2 = r0.f2098hJ
            float r2 = (float) r2
            int r3 = r0.f2096fJ
            float r3 = (float) r3
            android.graphics.Paint r4 = r0.f2093cJ
            r7.drawCircle(r1, r2, r3, r4)
            int r1 = r0.mState
            r2 = 8
            if (r1 != r2) goto L_0x0059
            goto L_0x00cd
        L_0x0059:
            android.graphics.Paint r1 = r0.f2093cJ
            int r12 = r1.getColor()
            int r1 = r0.mState
            if (r1 != r11) goto L_0x0071
            android.graphics.Paint r1 = r0.f2093cJ
            boolean r2 = r0.f2105oJ
            if (r2 == 0) goto L_0x006c
            int r2 = r0.f2094dJ
            goto L_0x006e
        L_0x006c:
            int r2 = r0.f2095eJ
        L_0x006e:
            r1.setColor(r2)
        L_0x0071:
            android.graphics.Paint r1 = r0.f2093cJ
            int r2 = r0.f2108rJ
            float r2 = (float) r2
            r1.setStrokeWidth(r2)
            int r1 = r0.f2099iJ
            android.graphics.Paint r2 = r0.f2093cJ
            r15.m3344a((android.graphics.Canvas) r7, (int) r1, (android.graphics.Paint) r2)
            int r1 = r0.f2099iJ
            int r1 = r1 + 45
            android.graphics.Paint r2 = r0.f2093cJ
            r15.m3344a((android.graphics.Canvas) r7, (int) r1, (android.graphics.Paint) r2)
            int r1 = r0.f2099iJ
            int r1 = r1 + 180
            android.graphics.Paint r2 = r0.f2093cJ
            r15.m3344a((android.graphics.Canvas) r7, (int) r1, (android.graphics.Paint) r2)
            int r1 = r0.f2099iJ
            int r1 = r1 + 225
            android.graphics.Paint r2 = r0.f2093cJ
            r15.m3344a((android.graphics.Canvas) r7, (int) r1, (android.graphics.Paint) r2)
            r16.save()
            int r1 = r0.f2099iJ
            float r1 = (float) r1
            int r2 = r0.f2097gJ
            float r2 = (float) r2
            int r3 = r0.f2098hJ
            float r3 = (float) r3
            r7.rotate(r1, r2, r3)
            android.graphics.RectF r2 = r0.f2101kJ
            r3 = 0
            r13 = 1110704128(0x42340000, float:45.0)
            r14 = 0
            android.graphics.Paint r6 = r0.f2093cJ
            r4 = 1110704128(0x42340000, float:45.0)
            r5 = 0
            r1 = r16
            r1.drawArc(r2, r3, r4, r5, r6)
            android.graphics.RectF r2 = r0.f2101kJ
            r3 = 1127481344(0x43340000, float:180.0)
            android.graphics.Paint r6 = r0.f2093cJ
            r4 = r13
            r5 = r14
            r1.drawArc(r2, r3, r4, r5, r6)
            r16.restore()
            android.graphics.Paint r1 = r0.f2093cJ
            r1.setColor(r12)
        L_0x00cd:
            int r1 = r0.mState
            if (r1 != r11) goto L_0x00d5
            r7.restoreToCount(r10)
            return
        L_0x00d5:
            com.android.messaging.ui.mediapicker.camerafocus.e r1 = r0.f2089ZI
            if (r1 == 0) goto L_0x00dd
            com.android.messaging.ui.mediapicker.camerafocus.l r1 = r0.f2114xJ
            if (r1 == 0) goto L_0x00f3
        L_0x00dd:
            java.util.List r1 = r0.mItems
            java.util.Iterator r1 = r1.iterator()
        L_0x00e3:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00f3
            java.lang.Object r2 = r1.next()
            com.android.messaging.ui.mediapicker.camerafocus.e r2 = (com.android.messaging.p041ui.mediapicker.camerafocus.C1308e) r2
            r15.m3345a((android.graphics.Canvas) r7, (com.android.messaging.p041ui.mediapicker.camerafocus.C1308e) r2, (float) r9)
            goto L_0x00e3
        L_0x00f3:
            com.android.messaging.ui.mediapicker.camerafocus.e r1 = r0.f2089ZI
            if (r1 == 0) goto L_0x011a
            java.util.List r1 = r1.getItems()
            java.util.Iterator r1 = r1.iterator()
        L_0x00ff:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x011a
            java.lang.Object r2 = r1.next()
            com.android.messaging.ui.mediapicker.camerafocus.e r2 = (com.android.messaging.p041ui.mediapicker.camerafocus.C1308e) r2
            com.android.messaging.ui.mediapicker.camerafocus.l r3 = r0.f2114xJ
            if (r3 == 0) goto L_0x0115
            r3 = 1056964608(0x3f000000, float:0.5)
            float r3 = r3 * r9
            float r3 = r8 - r3
            goto L_0x0116
        L_0x0115:
            r3 = r8
        L_0x0116:
            r15.m3345a((android.graphics.Canvas) r7, (com.android.messaging.p041ui.mediapicker.camerafocus.C1308e) r2, (float) r3)
            goto L_0x00ff
        L_0x011a:
            r7.restoreToCount(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.mediapicker.camerafocus.C1317n.onDraw(android.graphics.Canvas):void");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int actionMasked = motionEvent.getActionMasked();
        boolean z = true;
        boolean z2 = !this.f2109sJ;
        PointF pointF = new PointF();
        pointF.x = 1.5707964f;
        Point point = this.f2086VI;
        float f = x - ((float) point.x);
        float f2 = ((float) point.y) - y;
        pointF.y = (float) Math.sqrt((double) ((f2 * f2) + (f * f)));
        if (f != 0.0f) {
            pointF.x = (float) Math.atan2((double) f2, (double) f);
            float f3 = pointF.x;
            if (f3 < 0.0f) {
                pointF.x = (float) (((double) f3) + 6.283185307179586d);
            }
        }
        pointF.y += (float) (z2 ? this.f2088XI : 0);
        if (actionMasked == 0) {
            this.f2112vJ.x = (int) motionEvent.getX();
            this.f2112vJ.y = (int) motionEvent.getY();
            this.f2113wJ = false;
            if (this.f2109sJ) {
                C1308e a = m3340a(pointF);
                if (!(a == null || this.f2092bJ == a)) {
                    this.mState = 8;
                    m3346a(a);
                }
            } else {
                mo7839D((int) x, (int) y);
                show(true);
            }
            return true;
        }
        if (1 == actionMasked) {
            if (this.mVisible) {
                C1308e eVar = this.f2092bJ;
                if (!this.f2109sJ || (eVar = m3340a(pointF)) == null || !this.f2113wJ) {
                    if (eVar == null) {
                        this.f2109sJ = false;
                        show(false);
                    } else if (!this.f2113wJ && !eVar.mo7806Ej()) {
                        this.mOverlay.animate().alpha(0.0f).setListener(new C1311h(this)).setDuration(300);
                        this.f2109sJ = false;
                    }
                    return true;
                }
                this.f2113wJ = false;
                return true;
            }
        } else if (3 == actionMasked) {
            if (this.mVisible || this.f2109sJ) {
                show(false);
            }
            m3335Ko();
            return false;
        } else if (2 == actionMasked) {
            if (pointF.y < ((float) this.mRadius)) {
                if (this.f2089ZI != null) {
                    this.f2089ZI = null;
                } else {
                    m3335Ko();
                }
                return false;
            }
            C1308e a2 = m3340a(pointF);
            if (((float) this.f2111uJ) >= ((motionEvent.getY() - ((float) this.f2112vJ.y)) * (motionEvent.getY() - ((float) this.f2112vJ.y))) + ((motionEvent.getX() - ((float) this.f2112vJ.x)) * (motionEvent.getX() - ((float) this.f2112vJ.x)))) {
                z = false;
            }
            if (!(a2 == null || this.f2092bJ == a2 || (this.f2113wJ && !z))) {
                this.f2113wJ = false;
                if (z) {
                    this.f2109sJ = false;
                }
                m3346a(a2);
            }
        }
        return false;
    }

    /* renamed from: a */
    private void m3348a(List list, float f, int i, int i2, int i3) {
        float f2;
        int i4 = i;
        int i5 = i2;
        float size = 1.8325958f / ((float) list.size());
        float f3 = (size / 2.0f) + (f - 1.0471976f) + 0.1308997f;
        Iterator it = list.iterator();
        while (true) {
            f2 = 0.0f;
            if (!it.hasNext()) {
                break;
            }
            C1308e eVar = (C1308e) it.next();
            if (eVar.getCenter() >= 0.0f) {
                size = eVar.mo7805Dj();
                break;
            }
        }
        float f4 = size;
        float f5 = (float) i3;
        float a = m3338a(0.0d) - f5;
        float a2 = m3338a((double) f4) + f5;
        Point point = this.f2086VI;
        int i6 = point.x;
        int i7 = point.y;
        RectF rectF = new RectF((float) (i6 - i5), (float) (i7 - i5), (float) (i6 + i5), (float) (i7 + i5));
        int i8 = point.x;
        int i9 = point.y;
        RectF rectF2 = new RectF((float) (i8 - i4), (float) (i9 - i4), (float) (i8 + i4), (float) (i9 + i4));
        Path path = new Path();
        path.arcTo(rectF, a, a2 - a, true);
        path.arcTo(rectF2, a2, a - a2);
        path.close();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            C1308e eVar2 = (C1308e) it2.next();
            eVar2.mo7808a(path);
            float center = eVar2.getCenter() >= f2 ? eVar2.getCenter() : f3;
            int intrinsicWidth = eVar2.getIntrinsicWidth();
            int intrinsicHeight = eVar2.getIntrinsicHeight();
            double d = (double) ((((i5 - i4) * 2) / 3) + i4);
            double d2 = (double) center;
            Path path2 = path;
            int cos = (int) (Math.cos(d2) * d);
            int sin = (this.f2086VI.y - ((int) (Math.sin(d2) * d))) - (intrinsicHeight / 2);
            int i10 = (this.f2086VI.x + cos) - (intrinsicWidth / 2);
            eVar2.setBounds(i10, sin, intrinsicWidth + i10, intrinsicHeight + sin);
            eVar2.mo7807a(center - (f4 / 2.0f), f4, i4, i5);
            if (eVar2.mo7806Ej()) {
                m3348a(eVar2.getItems(), center, i, (this.f2087WI / 2) + i5, i3);
            }
            f3 = center + f4;
            int i11 = i3;
            path = path2;
            f2 = 0.0f;
        }
    }

    /* renamed from: a */
    private void m3345a(Canvas canvas, C1308e eVar, float f) {
        if (this.mState == 8 && eVar.getPath() != null) {
            if (eVar.isSelected()) {
                Paint paint = this.f2090_I;
                int save = canvas.save();
                float a = m3338a((double) eVar.getStartAngle());
                Point point = this.f2086VI;
                canvas.rotate(a, (float) point.x, (float) point.y);
                canvas.drawPath(eVar.getPath(), paint);
                canvas.restoreToCount(save);
            }
            eVar.setAlpha(f * (eVar.isEnabled() ? 1.0f : 0.3f));
            eVar.draw(canvas);
        }
    }

    /* renamed from: a */
    private void m3346a(C1308e eVar) {
        C1308e eVar2;
        C1308e eVar3 = this.f2092bJ;
        if (eVar3 != null) {
            eVar3.setSelected(false);
        }
        if (eVar == null || !eVar.isEnabled()) {
            this.f2092bJ = null;
            return;
        }
        eVar.setSelected(true);
        this.f2092bJ = eVar;
        C1308e eVar4 = this.f2092bJ;
        if (eVar4 != this.f2089ZI && eVar4.mo7806Ej() && (eVar2 = this.f2092bJ) != null && eVar2.mo7806Ej()) {
            this.f2092bJ.setSelected(false);
            this.f2089ZI = this.f2092bJ;
            this.f2113wJ = true;
            this.f2114xJ = new C1315l(this, 1.0f, 0.0f);
            this.f2114xJ.setDuration(200);
            this.f2114xJ.setAnimationListener(new C1312i(this));
            this.f2114xJ.startNow();
            this.mOverlay.startAnimation(this.f2114xJ);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.android.messaging.p041ui.mediapicker.camerafocus.C1308e m3340a(android.graphics.PointF r5) {
        /*
            r4 = this;
            com.android.messaging.ui.mediapicker.camerafocus.e r0 = r4.f2089ZI
            if (r0 == 0) goto L_0x0009
            java.util.List r0 = r0.getItems()
            goto L_0x000b
        L_0x0009:
            java.util.List r0 = r4.mItems
        L_0x000b:
            java.util.Iterator r0 = r0.iterator()
        L_0x000f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0054
            java.lang.Object r1 = r0.next()
            com.android.messaging.ui.mediapicker.camerafocus.e r1 = (com.android.messaging.p041ui.mediapicker.camerafocus.C1308e) r1
            int r2 = r1.getInnerRadius()
            float r2 = (float) r2
            float r3 = r5.y
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 >= 0) goto L_0x0050
            float r2 = r1.getStartAngle()
            float r3 = r5.x
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 >= 0) goto L_0x0050
            float r2 = r1.getStartAngle()
            float r3 = r1.mo7805Dj()
            float r3 = r3 + r2
            float r2 = r5.x
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x0050
            boolean r2 = r4.f2109sJ
            if (r2 == 0) goto L_0x004e
            int r2 = r1.mo7804Cj()
            float r2 = (float) r2
            float r3 = r5.y
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L_0x0050
        L_0x004e:
            r2 = 1
            goto L_0x0051
        L_0x0050:
            r2 = 0
        L_0x0051:
            if (r2 == 0) goto L_0x000f
            return r1
        L_0x0054:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.mediapicker.camerafocus.C1317n.m3340a(android.graphics.PointF):com.android.messaging.ui.mediapicker.camerafocus.e");
    }

    /* renamed from: a */
    private void m3344a(Canvas canvas, int i, Paint paint) {
        m3342a(i, this.f2096fJ - this.f2106pJ, this.f2102lJ);
        int i2 = this.f2096fJ;
        int i3 = this.f2106pJ;
        m3342a(i, (i3 / 3) + (i2 - i3), this.f2103mJ);
        Point point = this.f2102lJ;
        int i4 = point.x;
        int i5 = this.f2097gJ;
        float f = (float) (i4 + i5);
        int i6 = point.y;
        int i7 = this.f2098hJ;
        Point point2 = this.f2103mJ;
        canvas.drawLine(f, (float) (i6 + i7), (float) (point2.x + i5), (float) (point2.y + i7), paint);
    }

    /* renamed from: a */
    private static void m3342a(int i, int i2, Point point) {
        double d = (((double) (i % 360)) * 6.283185307179586d) / 360.0d;
        double d2 = (double) i2;
        point.x = (int) ((Math.cos(d) * d2) + 0.5d);
        point.y = (int) ((Math.sin(d) * d2) + 0.5d);
    }
}
