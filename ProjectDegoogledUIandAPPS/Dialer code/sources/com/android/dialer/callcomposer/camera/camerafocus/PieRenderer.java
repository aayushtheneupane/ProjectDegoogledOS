package com.android.dialer.callcomposer.camera.camerafocus;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.os.Message;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import com.android.dialer.R;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PieRenderer extends OverlayRenderer implements FocusIndicator {
    private ScaleAnimation animation = new ScaleAnimation();
    private boolean blockFocus;
    private Point center;
    /* access modifiers changed from: private */
    public int centerX;
    /* access modifiers changed from: private */
    public int centerY;
    private RectF circle;
    private int circleSize;
    private PieItem currentItem;
    private RectF dial;
    /* access modifiers changed from: private */
    public int dialAngle;
    /* access modifiers changed from: private */
    public Runnable disappear = new Disappear((C04091) null);
    private Point down;
    private Animation.AnimationListener endAction = new EndAction((C04091) null);
    /* access modifiers changed from: private */
    public LinearAnimation fadeIn;
    private int failColor;
    /* access modifiers changed from: private */
    public volatile boolean focusCancelled;
    private Paint focusPaint;
    /* access modifiers changed from: private */
    public int focusX;
    /* access modifiers changed from: private */
    public int focusY;
    /* access modifiers changed from: private */
    public boolean focused;
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 0) {
            }
        }
    };
    private int innerOffset;
    private int innerStroke;
    private List<PieItem> items;
    private PieItem openItem;
    private boolean opening;
    private int outerStroke;
    private Point point1;
    private Point point2;
    private int radius;
    private int radiusInc;
    private Paint selectedPaint;
    private int startAnimationAngle;
    /* access modifiers changed from: private */
    public volatile int state;
    private Paint subPaint;
    private int successColor;
    private boolean tapMode;
    private int touchOffset;
    private int touchSlopSquared;
    /* access modifiers changed from: private */
    public LinearAnimation xFade;

    private class Disappear implements Runnable {
        /* synthetic */ Disappear(C04091 r2) {
        }

        public void run() {
            if (PieRenderer.this.state != 8) {
                PieRenderer.this.setVisible(false);
                PieRenderer pieRenderer = PieRenderer.this;
                int unused = pieRenderer.focusX = pieRenderer.centerX;
                PieRenderer pieRenderer2 = PieRenderer.this;
                int unused2 = pieRenderer2.focusY = pieRenderer2.centerY;
                int unused3 = PieRenderer.this.state = 0;
                PieRenderer pieRenderer3 = PieRenderer.this;
                pieRenderer3.setCircle(pieRenderer3.focusX, PieRenderer.this.focusY);
                boolean unused4 = PieRenderer.this.focused = false;
            }
        }
    }

    private class EndAction implements Animation.AnimationListener {
        /* synthetic */ EndAction(C04091 r2) {
        }

        public void onAnimationEnd(Animation animation) {
            if (!PieRenderer.this.focusCancelled) {
                PieRenderer pieRenderer = PieRenderer.this;
                pieRenderer.overlay.postDelayed(pieRenderer.disappear, 200);
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    private static class LinearAnimation extends Animation {
        private float from;

        /* renamed from: to */
        private float f12to;
        private float value;

        public LinearAnimation(float f, float f2) {
            setFillAfter(true);
            setInterpolator(new LinearInterpolator());
            this.from = f;
            this.f12to = f2;
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            float f2 = this.from;
            this.value = GeneratedOutlineSupport.outline0(this.f12to, f2, f, f2);
        }

        public float getValue() {
            return this.value;
        }
    }

    private class ScaleAnimation extends Animation {
        private float from = 1.0f;

        /* renamed from: to */
        private float f13to = 1.0f;

        public ScaleAnimation() {
            setFillAfter(true);
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            PieRenderer pieRenderer = PieRenderer.this;
            float f2 = this.from;
            int unused = pieRenderer.dialAngle = (int) GeneratedOutlineSupport.outline0(this.f13to, f2, f, f2);
        }

        public void setScale(float f, float f2) {
            this.from = f;
            this.f13to = f2;
        }
    }

    public PieRenderer(Context context) {
        setVisible(false);
        this.items = new ArrayList();
        Resources resources = context.getResources();
        this.radius = resources.getDimensionPixelSize(R.dimen.pie_radius_start);
        this.circleSize = this.radius - resources.getDimensionPixelSize(R.dimen.focus_radius_offset);
        this.radiusInc = resources.getDimensionPixelSize(R.dimen.pie_radius_increment);
        this.touchOffset = resources.getDimensionPixelSize(R.dimen.pie_touch_offset);
        this.center = new Point(0, 0);
        this.selectedPaint = new Paint();
        this.selectedPaint.setColor(Color.argb(255, 51, 181, 229));
        this.selectedPaint.setAntiAlias(true);
        this.subPaint = new Paint();
        this.subPaint.setAntiAlias(true);
        this.subPaint.setColor(Color.argb(200, 250, 230, 128));
        this.focusPaint = new Paint();
        this.focusPaint.setAntiAlias(true);
        this.focusPaint.setColor(-1);
        this.focusPaint.setStyle(Paint.Style.STROKE);
        this.successColor = -16711936;
        this.failColor = -65536;
        this.circle = new RectF();
        this.dial = new RectF();
        this.point1 = new Point();
        this.point2 = new Point();
        this.innerOffset = resources.getDimensionPixelSize(R.dimen.focus_inner_offset);
        this.outerStroke = resources.getDimensionPixelSize(R.dimen.focus_outer_stroke);
        this.innerStroke = resources.getDimensionPixelSize(R.dimen.focus_inner_stroke);
        this.state = 0;
        this.blockFocus = false;
        this.touchSlopSquared = ViewConfiguration.get(context).getScaledTouchSlop();
        int i = this.touchSlopSquared;
        this.touchSlopSquared = i * i;
        this.down = new Point();
    }

    private void cancelFocus() {
        this.focusCancelled = true;
        this.overlay.removeCallbacks(this.disappear);
        ScaleAnimation scaleAnimation = this.animation;
        if (scaleAnimation != null) {
            scaleAnimation.cancel();
        }
        this.focusCancelled = false;
        this.focused = false;
        this.state = 0;
    }

    private static void convertCart(int i, int i2, Point point) {
        double d = (((double) (i % 360)) * 6.283185307179586d) / 360.0d;
        double d2 = (double) i2;
        point.x = (int) ((Math.cos(d) * d2) + 0.5d);
        point.y = (int) ((Math.sin(d) * d2) + 0.5d);
    }

    /* access modifiers changed from: private */
    public void deselect() {
        PieItem pieItem = this.currentItem;
        if (pieItem != null) {
            pieItem.setSelected(false);
        }
        if (this.openItem != null) {
            this.openItem = null;
        }
        this.currentItem = null;
    }

    private void drawItem(Canvas canvas, PieItem pieItem, float f) {
        if (this.state == 8 && pieItem.getPath() != null) {
            if (pieItem.isSelected()) {
                Paint paint = this.selectedPaint;
                int save = canvas.save();
                float degrees = getDegrees((double) pieItem.getStartAngle());
                Point point = this.center;
                canvas.rotate(degrees, (float) point.x, (float) point.y);
                canvas.drawPath(pieItem.getPath(), paint);
                canvas.restoreToCount(save);
            }
            pieItem.setAlpha(f * (pieItem.isEnabled() ? 1.0f : 0.3f));
            pieItem.draw(canvas);
        }
    }

    private void drawLine(Canvas canvas, int i, Paint paint) {
        convertCart(i, this.circleSize - this.innerOffset, this.point1);
        int i2 = this.circleSize;
        int i3 = this.innerOffset;
        convertCart(i, (i3 / 3) + (i2 - i3), this.point2);
        Point point = this.point1;
        int i4 = point.x;
        int i5 = this.focusX;
        float f = (float) (i4 + i5);
        int i6 = point.y;
        int i7 = this.focusY;
        Point point3 = this.point2;
        canvas.drawLine(f, (float) (i6 + i7), (float) (point3.x + i5), (float) (point3.y + i7), paint);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.android.dialer.callcomposer.camera.camerafocus.PieItem findItem(android.graphics.PointF r5) {
        /*
            r4 = this;
            com.android.dialer.callcomposer.camera.camerafocus.PieItem r0 = r4.openItem
            if (r0 == 0) goto L_0x0009
            java.util.List r0 = r0.getItems()
            goto L_0x000b
        L_0x0009:
            java.util.List<com.android.dialer.callcomposer.camera.camerafocus.PieItem> r0 = r4.items
        L_0x000b:
            java.util.Iterator r0 = r0.iterator()
        L_0x000f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0054
            java.lang.Object r1 = r0.next()
            com.android.dialer.callcomposer.camera.camerafocus.PieItem r1 = (com.android.dialer.callcomposer.camera.camerafocus.PieItem) r1
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
            float r3 = r1.getSweep()
            float r3 = r3 + r2
            float r2 = r5.x
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x0050
            boolean r2 = r4.tapMode
            if (r2 == 0) goto L_0x004e
            int r2 = r1.getOuterRadius()
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
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.callcomposer.camera.camerafocus.PieRenderer.findItem(android.graphics.PointF):com.android.dialer.callcomposer.camera.camerafocus.PieItem");
    }

    private float getDegrees(double d) {
        return (float) (360.0d - ((d * 180.0d) / 3.141592653589793d));
    }

    private void layoutItems(List<PieItem> list, float f, int i, int i2, int i3) {
        float f2;
        int i4 = i;
        int i5 = i2;
        float size = 1.8325958f / ((float) list.size());
        float f3 = (size / 2.0f) + (f - 1.0471976f) + 0.1308997f;
        Iterator<PieItem> it = list.iterator();
        while (true) {
            f2 = 0.0f;
            if (!it.hasNext()) {
                break;
            }
            PieItem next = it.next();
            if (next.getCenter() >= 0.0f) {
                size = next.getSweep();
                break;
            }
        }
        float f4 = size;
        float f5 = (float) i3;
        float degrees = getDegrees(0.0d) - f5;
        float degrees2 = getDegrees((double) f4) + f5;
        Point point = this.center;
        int i6 = point.x;
        int i7 = point.y;
        RectF rectF = new RectF((float) (i6 - i5), (float) (i7 - i5), (float) (i6 + i5), (float) (i7 + i5));
        int i8 = point.x;
        int i9 = point.y;
        RectF rectF2 = new RectF((float) (i8 - i4), (float) (i9 - i4), (float) (i8 + i4), (float) (i9 + i4));
        Path path = new Path();
        path.arcTo(rectF, degrees, degrees2 - degrees, true);
        path.arcTo(rectF2, degrees2, degrees - degrees2);
        path.close();
        for (PieItem next2 : list) {
            next2.setPath(path);
            float center2 = next2.getCenter() >= f2 ? next2.getCenter() : f3;
            int intrinsicWidth = next2.getIntrinsicWidth();
            int intrinsicHeight = next2.getIntrinsicHeight();
            double d = (double) ((((i5 - i4) * 2) / 3) + i4);
            double d2 = (double) center2;
            Path path2 = path;
            int cos = (int) (Math.cos(d2) * d);
            int sin = (this.center.y - ((int) (Math.sin(d2) * d))) - (intrinsicHeight / 2);
            int i10 = (this.center.x + cos) - (intrinsicWidth / 2);
            next2.setBounds(i10, sin, intrinsicWidth + i10, intrinsicHeight + sin);
            next2.setGeometry(center2 - (f4 / 2.0f), f4, i4, i5);
            if (next2.hasItems()) {
                layoutItems(next2.getItems(), center2, i, (this.radiusInc / 2) + i5, i3);
            }
            f3 = center2 + f4;
            int i11 = i3;
            path = path2;
            f2 = 0.0f;
        }
    }

    private void layoutPie() {
        int i = this.radius;
        layoutItems(this.items, 1.5707964f, i + 2, (i + this.radiusInc) - 2, 1);
    }

    private void onEnter(PieItem pieItem) {
        PieItem pieItem2;
        PieItem pieItem3 = this.currentItem;
        if (pieItem3 != null) {
            pieItem3.setSelected(false);
        }
        if (pieItem == null || !pieItem.isEnabled()) {
            this.currentItem = null;
            return;
        }
        pieItem.setSelected(true);
        this.currentItem = pieItem;
        PieItem pieItem4 = this.currentItem;
        if (pieItem4 != this.openItem && pieItem4.hasItems() && (pieItem2 = this.currentItem) != null && pieItem2.hasItems()) {
            this.currentItem.setSelected(false);
            this.openItem = this.currentItem;
            this.opening = true;
            this.xFade = new LinearAnimation(1.0f, 0.0f);
            this.xFade.setDuration(200);
            this.xFade.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    LinearAnimation unused = PieRenderer.this.xFade = null;
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
            this.xFade.startNow();
            this.overlay.startAnimation(this.xFade);
        }
    }

    /* access modifiers changed from: private */
    public void setCircle(int i, int i2) {
        RectF rectF = this.circle;
        int i3 = this.circleSize;
        rectF.set((float) (i - i3), (float) (i2 - i3), (float) (i + i3), (float) (i3 + i2));
        RectF rectF2 = this.dial;
        int i4 = this.circleSize;
        int i5 = this.innerOffset;
        rectF2.set((float) ((i - i4) + i5), (float) ((i2 - i4) + i5), (float) ((i + i4) - i5), (float) ((i2 + i4) - i5));
    }

    /* access modifiers changed from: private */
    public void show(boolean z) {
        if (z) {
            this.state = 8;
            this.currentItem = null;
            this.openItem = null;
            for (PieItem selected : this.items) {
                selected.setSelected(false);
            }
            layoutPie();
            this.fadeIn = new LinearAnimation(0.0f, 1.0f);
            this.fadeIn.setDuration(200);
            this.fadeIn.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    LinearAnimation unused = PieRenderer.this.fadeIn = null;
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
            this.fadeIn.startNow();
            this.overlay.startAnimation(this.fadeIn);
        } else {
            this.state = 0;
            this.tapMode = false;
            LinearAnimation linearAnimation = this.xFade;
            if (linearAnimation != null) {
                linearAnimation.cancel();
            }
        }
        setVisible(z);
        this.handler.sendEmptyMessage(z ^ true ? 1 : 0);
    }

    private void startAnimation(long j, boolean z, float f, float f2) {
        setVisible(true);
        this.animation.reset();
        this.animation.setDuration(j);
        this.animation.setScale(f, f2);
        this.animation.setAnimationListener(z ? this.endAction : null);
        this.overlay.startAnimation(this.animation);
        RenderOverlay renderOverlay = this.overlay;
        if (renderOverlay != null) {
            renderOverlay.update();
        }
    }

    public void clear() {
        if (this.state != 8) {
            cancelFocus();
            this.overlay.post(this.disappear);
        }
    }

    public int getSize() {
        return this.circleSize * 2;
    }

    public void layout(int i, int i2, int i3, int i4) {
        super.layout(i, i2, i3, i4);
        this.centerX = (i3 - i) / 2;
        this.centerY = (i4 - i2) / 2;
        this.focusX = this.centerX;
        this.focusY = this.centerY;
        setCircle(this.focusX, this.focusY);
        if (isVisible() && this.state == 8) {
            setCenter(this.centerX, this.centerY);
            layoutPie();
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
            com.android.dialer.callcomposer.camera.camerafocus.PieRenderer$LinearAnimation r1 = r0.xFade
            r8 = 1065353216(0x3f800000, float:1.0)
            if (r1 == 0) goto L_0x000f
            float r1 = r1.getValue()
        L_0x000d:
            r9 = r1
            goto L_0x0019
        L_0x000f:
            com.android.dialer.callcomposer.camera.camerafocus.PieRenderer$LinearAnimation r1 = r0.fadeIn
            if (r1 == 0) goto L_0x0018
            float r1 = r1.getValue()
            goto L_0x000d
        L_0x0018:
            r9 = r8
        L_0x0019:
            int r10 = r16.save()
            com.android.dialer.callcomposer.camera.camerafocus.PieRenderer$LinearAnimation r1 = r0.fadeIn
            if (r1 == 0) goto L_0x0034
            r1 = 1063675494(0x3f666666, float:0.9)
            r2 = 1036831949(0x3dcccccd, float:0.1)
            float r2 = r2 * r9
            float r2 = r2 + r1
            android.graphics.Point r1 = r0.center
            int r3 = r1.x
            float r3 = (float) r3
            int r1 = r1.y
            float r1 = (float) r1
            r7.scale(r2, r2, r3, r1)
        L_0x0034:
            boolean r1 = r0.blockFocus
            r11 = 2
            if (r1 == 0) goto L_0x003b
            goto L_0x00cd
        L_0x003b:
            android.graphics.Paint r1 = r0.focusPaint
            int r2 = r0.outerStroke
            float r2 = (float) r2
            r1.setStrokeWidth(r2)
            int r1 = r0.focusX
            float r1 = (float) r1
            int r2 = r0.focusY
            float r2 = (float) r2
            int r3 = r0.circleSize
            float r3 = (float) r3
            android.graphics.Paint r4 = r0.focusPaint
            r7.drawCircle(r1, r2, r3, r4)
            int r1 = r0.state
            r2 = 8
            if (r1 != r2) goto L_0x0059
            goto L_0x00cd
        L_0x0059:
            android.graphics.Paint r1 = r0.focusPaint
            int r12 = r1.getColor()
            int r1 = r0.state
            if (r1 != r11) goto L_0x0071
            android.graphics.Paint r1 = r0.focusPaint
            boolean r2 = r0.focused
            if (r2 == 0) goto L_0x006c
            int r2 = r0.successColor
            goto L_0x006e
        L_0x006c:
            int r2 = r0.failColor
        L_0x006e:
            r1.setColor(r2)
        L_0x0071:
            android.graphics.Paint r1 = r0.focusPaint
            int r2 = r0.innerStroke
            float r2 = (float) r2
            r1.setStrokeWidth(r2)
            int r1 = r0.dialAngle
            android.graphics.Paint r2 = r0.focusPaint
            r15.drawLine(r7, r1, r2)
            int r1 = r0.dialAngle
            int r1 = r1 + 45
            android.graphics.Paint r2 = r0.focusPaint
            r15.drawLine(r7, r1, r2)
            int r1 = r0.dialAngle
            int r1 = r1 + 180
            android.graphics.Paint r2 = r0.focusPaint
            r15.drawLine(r7, r1, r2)
            int r1 = r0.dialAngle
            int r1 = r1 + 225
            android.graphics.Paint r2 = r0.focusPaint
            r15.drawLine(r7, r1, r2)
            r16.save()
            int r1 = r0.dialAngle
            float r1 = (float) r1
            int r2 = r0.focusX
            float r2 = (float) r2
            int r3 = r0.focusY
            float r3 = (float) r3
            r7.rotate(r1, r2, r3)
            android.graphics.RectF r2 = r0.dial
            r3 = 0
            r13 = 1110704128(0x42340000, float:45.0)
            r14 = 0
            android.graphics.Paint r6 = r0.focusPaint
            r4 = 1110704128(0x42340000, float:45.0)
            r5 = 0
            r1 = r16
            r1.drawArc(r2, r3, r4, r5, r6)
            android.graphics.RectF r2 = r0.dial
            r3 = 1127481344(0x43340000, float:180.0)
            android.graphics.Paint r6 = r0.focusPaint
            r4 = r13
            r5 = r14
            r1.drawArc(r2, r3, r4, r5, r6)
            r16.restore()
            android.graphics.Paint r1 = r0.focusPaint
            r1.setColor(r12)
        L_0x00cd:
            int r1 = r0.state
            if (r1 != r11) goto L_0x00d5
            r7.restoreToCount(r10)
            return
        L_0x00d5:
            com.android.dialer.callcomposer.camera.camerafocus.PieItem r1 = r0.openItem
            if (r1 == 0) goto L_0x00dd
            com.android.dialer.callcomposer.camera.camerafocus.PieRenderer$LinearAnimation r1 = r0.xFade
            if (r1 == 0) goto L_0x00f3
        L_0x00dd:
            java.util.List<com.android.dialer.callcomposer.camera.camerafocus.PieItem> r1 = r0.items
            java.util.Iterator r1 = r1.iterator()
        L_0x00e3:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00f3
            java.lang.Object r2 = r1.next()
            com.android.dialer.callcomposer.camera.camerafocus.PieItem r2 = (com.android.dialer.callcomposer.camera.camerafocus.PieItem) r2
            r15.drawItem(r7, r2, r9)
            goto L_0x00e3
        L_0x00f3:
            com.android.dialer.callcomposer.camera.camerafocus.PieItem r1 = r0.openItem
            if (r1 == 0) goto L_0x011a
            java.util.List r1 = r1.getItems()
            java.util.Iterator r1 = r1.iterator()
        L_0x00ff:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x011a
            java.lang.Object r2 = r1.next()
            com.android.dialer.callcomposer.camera.camerafocus.PieItem r2 = (com.android.dialer.callcomposer.camera.camerafocus.PieItem) r2
            com.android.dialer.callcomposer.camera.camerafocus.PieRenderer$LinearAnimation r3 = r0.xFade
            if (r3 == 0) goto L_0x0115
            r3 = 1056964608(0x3f000000, float:0.5)
            float r3 = r3 * r9
            float r3 = r8 - r3
            goto L_0x0116
        L_0x0115:
            r3 = r8
        L_0x0116:
            r15.drawItem(r7, r2, r3)
            goto L_0x00ff
        L_0x011a:
            r7.restoreToCount(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.callcomposer.camera.camerafocus.PieRenderer.onDraw(android.graphics.Canvas):void");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int actionMasked = motionEvent.getActionMasked();
        boolean z = true;
        boolean z2 = !this.tapMode;
        PointF pointF = new PointF();
        pointF.x = 1.5707964f;
        Point point = this.center;
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
        pointF.y += (float) (z2 ? this.touchOffset : 0);
        if (actionMasked == 0) {
            this.down.x = (int) motionEvent.getX();
            this.down.y = (int) motionEvent.getY();
            this.opening = false;
            if (this.tapMode) {
                PieItem findItem = findItem(pointF);
                if (!(findItem == null || this.currentItem == findItem)) {
                    this.state = 8;
                    onEnter(findItem);
                }
            } else {
                setCenter((int) x, (int) y);
                show(true);
            }
            return true;
        }
        if (1 == actionMasked) {
            if (isVisible()) {
                PieItem pieItem = this.currentItem;
                if (!this.tapMode || (pieItem = findItem(pointF)) == null || !this.opening) {
                    if (pieItem == null) {
                        this.tapMode = false;
                        show(false);
                    } else if (!this.opening && !pieItem.hasItems()) {
                        this.overlay.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                            public void onAnimationEnd(Animator animator) {
                                PieRenderer.this.deselect();
                                PieRenderer.this.show(false);
                                PieRenderer.this.overlay.setAlpha(1.0f);
                                super.onAnimationEnd(animator);
                            }
                        }).setDuration(300);
                        this.tapMode = false;
                    }
                    return true;
                }
                this.opening = false;
                return true;
            }
        } else if (3 == actionMasked) {
            if (isVisible() || this.tapMode) {
                show(false);
            }
            deselect();
            return false;
        } else if (2 == actionMasked) {
            if (pointF.y < ((float) this.radius)) {
                if (this.openItem != null) {
                    this.openItem = null;
                } else {
                    deselect();
                }
                return false;
            }
            PieItem findItem2 = findItem(pointF);
            if (((float) this.touchSlopSquared) >= GeneratedOutlineSupport.outline0(motionEvent.getY(), (float) this.down.y, motionEvent.getY() - ((float) this.down.y), (motionEvent.getX() - ((float) this.down.x)) * (motionEvent.getX() - ((float) this.down.x)))) {
                z = false;
            }
            if (!(findItem2 == null || this.currentItem == findItem2 || (this.opening && !z))) {
                this.opening = false;
                if (z) {
                    this.tapMode = false;
                }
                onEnter(findItem2);
            }
        }
        return false;
    }

    public void setCenter(int i, int i2) {
        Point point = this.center;
        point.x = i;
        point.y = i2;
        this.overlay.removeCallbacks(this.disappear);
        this.animation.cancel();
        this.animation.reset();
        this.focusX = i;
        this.focusY = i2;
        this.dialAngle = 157;
        setCircle(i, i2);
        this.focused = false;
    }

    public void setFocus(int i, int i2) {
        this.focusX = i;
        this.focusY = i2;
        setCircle(this.focusX, this.focusY);
    }

    public void showFail(boolean z) {
        if (this.state == 1) {
            boolean z2 = z;
            startAnimation(100, z2, (float) this.dialAngle, (float) this.startAnimationAngle);
            this.state = 2;
            this.focused = false;
        }
    }

    public void showStart() {
        if (this.state != 8) {
            cancelFocus();
            this.startAnimationAngle = 67;
            int i = this.startAnimationAngle;
            startAnimation(600, false, (float) i, (float) (i + ((int) ((Math.random() * 120.0d) - 0.0703125d))));
            this.state = 1;
        }
    }

    public void showSuccess(boolean z) {
        if (this.state == 1) {
            boolean z2 = z;
            startAnimation(100, z2, (float) this.dialAngle, (float) this.startAnimationAngle);
            this.state = 2;
            this.focused = true;
        }
    }
}
