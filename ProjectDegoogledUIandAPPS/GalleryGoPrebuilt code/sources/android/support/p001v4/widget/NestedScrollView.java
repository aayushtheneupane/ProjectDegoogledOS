package android.support.p001v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import java.util.ArrayList;

/* renamed from: android.support.v4.widget.NestedScrollView */
/* compiled from: PG */
public class NestedScrollView extends FrameLayout implements C0327lx, C0324lu {

    /* renamed from: w */
    private static final C0373np f829w = new C0373np();

    /* renamed from: x */
    private static final int[] f830x = {16843130};

    /* renamed from: A */
    private float f831A;

    /* renamed from: a */
    private long f832a;

    /* renamed from: b */
    private final Rect f833b;

    /* renamed from: c */
    private OverScroller f834c;

    /* renamed from: d */
    private EdgeEffect f835d;

    /* renamed from: e */
    private EdgeEffect f836e;

    /* renamed from: f */
    private int f837f;

    /* renamed from: g */
    private boolean f838g;

    /* renamed from: h */
    private boolean f839h;

    /* renamed from: i */
    private View f840i;

    /* renamed from: j */
    private boolean f841j;

    /* renamed from: k */
    private VelocityTracker f842k;

    /* renamed from: l */
    private boolean f843l;

    /* renamed from: m */
    private boolean f844m;

    /* renamed from: n */
    private int f845n;

    /* renamed from: o */
    private int f846o;

    /* renamed from: p */
    private int f847p;

    /* renamed from: q */
    private int f848q;

    /* renamed from: r */
    private final int[] f849r;

    /* renamed from: s */
    private final int[] f850s;

    /* renamed from: t */
    private int f851t;

    /* renamed from: u */
    private int f852u;

    /* renamed from: v */
    private C0375nr f853v;

    /* renamed from: y */
    private final C0328ly f854y;

    /* renamed from: z */
    private final C0325lv f855z;

    /* renamed from: b */
    private static int m821b(int i, int i2, int i3) {
        if (i2 >= i3 || i < 0) {
            return 0;
        }
        return i2 + i > i3 ? i3 - i2 : i;
    }

    /* renamed from: a */
    public final boolean mo709a(View view, View view2, int i, int i2) {
        return (i & 2) != 0;
    }

    public final boolean isNestedScrollingEnabled() {
        return this.f855z.f15213a;
    }

    public final boolean shouldDelayChildPressedState() {
        return true;
    }

    public NestedScrollView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f833b = new Rect();
        this.f838g = true;
        this.f839h = false;
        this.f840i = null;
        this.f841j = false;
        this.f844m = true;
        this.f848q = -1;
        this.f849r = new int[2];
        this.f850s = new int[2];
        this.f834c = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f845n = viewConfiguration.getScaledTouchSlop();
        this.f846o = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f847p = viewConfiguration.getScaledMaximumFlingVelocity();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f830x, i, 0);
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        if (z != this.f843l) {
            this.f843l = z;
            requestLayout();
        }
        obtainStyledAttributes.recycle();
        this.f854y = new C0328ly();
        this.f855z = new C0325lv(this);
        setNestedScrollingEnabled(true);
        C0340mj.m14698a((View) this, (C0315ll) f829w);
    }

    /* renamed from: d */
    private final void m827d() {
        this.f834c.abortAnimation();
        m823b(1);
    }

    public final void addView(View view) {
        if (getChildCount() <= 0) {
            super.addView(view);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public final void addView(View view, int i) {
        if (getChildCount() <= 0) {
            super.addView(view, i);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    /* renamed from: e */
    private final boolean m830e(int i) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        int height = (int) (((float) getHeight()) * 0.5f);
        if (findNextFocus == null || !m819a(findNextFocus, height, getHeight())) {
            if (i == 33 && getScrollY() < height) {
                height = getScrollY();
            } else if (i == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                height = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), height);
            }
            if (height == 0) {
                return false;
            }
            if (i != 130) {
                height = -height;
            }
            m832f(height);
        } else {
            findNextFocus.getDrawingRect(this.f833b);
            offsetDescendantRectToMyCoords(findNextFocus, this.f833b);
            m832f(m808a(this.f833b));
            findNextFocus.requestFocus(i);
        }
        if (findFocus == null || !findFocus.isFocused() || !m818a(findFocus)) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
    }

    public final int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    public final int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    public final int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    public final void computeScroll() {
        if (!this.f834c.isFinished()) {
            this.f834c.computeScrollOffset();
            int currY = this.f834c.getCurrY();
            int i = currY - this.f852u;
            this.f852u = currY;
            int[] iArr = this.f850s;
            iArr[1] = 0;
            m817a(0, i, iArr, (int[]) null, 1);
            int i2 = i - this.f850s[1];
            int a = mo702a();
            if (i2 != 0) {
                int scrollY = getScrollY();
                m816a(i2, getScrollX(), scrollY, a);
                int scrollY2 = getScrollY() - scrollY;
                int i3 = i2 - scrollY2;
                int[] iArr2 = this.f850s;
                iArr2[1] = 0;
                m811a(scrollY2, i3, this.f849r, 1, iArr2);
                i2 = i3 - this.f850s[1];
            }
            if (i2 != 0) {
                int overScrollMode = getOverScrollMode();
                if (overScrollMode == 0 || (overScrollMode == 1 && a > 0)) {
                    m831f();
                    if (i2 < 0) {
                        if (this.f835d.isFinished()) {
                            this.f835d.onAbsorb((int) this.f834c.getCurrVelocity());
                        }
                    } else if (this.f836e.isFinished()) {
                        this.f836e.onAbsorb((int) this.f834c.getCurrVelocity());
                    }
                }
                m827d();
            }
            if (!this.f834c.isFinished()) {
                C0340mj.m14710d(this);
            } else {
                m823b(1);
            }
        }
    }

    /* renamed from: a */
    private final int m808a(Rect rect) {
        int i;
        int i2;
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i3 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i4 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i3 - verticalFadingEdgeLength : i3;
        if (rect.bottom > i4 && rect.top > scrollY) {
            if (rect.height() > height) {
                i2 = rect.top - scrollY;
            } else {
                i2 = rect.bottom - i4;
            }
            return Math.min(i2, (childAt.getBottom() + layoutParams.bottomMargin) - i3);
        } else if (rect.top >= scrollY || rect.bottom >= i4) {
            return 0;
        } else {
            if (rect.height() > height) {
                i = -(i4 - rect.bottom);
            } else {
                i = -(scrollY - rect.top);
            }
            return Math.max(i, -getScrollY());
        }
    }

    public final int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    public final int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    public final int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int max = Math.max(0, bottom - height);
        if (scrollY < 0) {
            return bottom - scrollY;
        }
        return scrollY <= max ? bottom : bottom + (scrollY - max);
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || mo708a(keyEvent);
    }

    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.f855z.mo9372a(f, f2, z);
    }

    public final boolean dispatchNestedPreFling(float f, float f2) {
        return this.f855z.mo9371a(f, f2);
    }

    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return m817a(i, i2, iArr, iArr2, 0);
    }

    /* renamed from: a */
    private final boolean m817a(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return this.f855z.mo9377a(i, i2, iArr, iArr2, i3);
    }

    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.f855z.mo9375a(i, i2, i3, i4, iArr);
    }

    /* renamed from: a */
    private final void m811a(int i, int i2, int[] iArr, int i3, int[] iArr2) {
        this.f855z.mo9376a(0, i, 0, i2, iArr, i3, iArr2);
    }

    /* renamed from: f */
    private final void m832f(int i) {
        if (i == 0) {
            return;
        }
        if (this.f844m) {
            m834h(i);
        } else {
            scrollBy(0, i);
        }
    }

    public final void draw(Canvas canvas) {
        int i;
        super.draw(canvas);
        if (this.f835d != null) {
            int scrollY = getScrollY();
            int i2 = 0;
            if (!this.f835d.isFinished()) {
                int save = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int min = Math.min(0, scrollY);
                int i3 = Build.VERSION.SDK_INT;
                if (getClipToPadding()) {
                    width -= getPaddingLeft() + getPaddingRight();
                    i = getPaddingLeft();
                } else {
                    i = 0;
                }
                int i4 = Build.VERSION.SDK_INT;
                if (getClipToPadding()) {
                    height -= getPaddingTop() + getPaddingBottom();
                    min += getPaddingTop();
                }
                canvas.translate((float) i, (float) min);
                this.f835d.setSize(width, height);
                if (this.f835d.draw(canvas)) {
                    C0340mj.m14710d(this);
                }
                canvas.restoreToCount(save);
            }
            if (!this.f836e.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = getHeight();
                int max = Math.max(mo702a(), scrollY) + height2;
                int i5 = Build.VERSION.SDK_INT;
                if (getClipToPadding()) {
                    width2 -= getPaddingLeft() + getPaddingRight();
                    i2 = getPaddingLeft();
                }
                int i6 = Build.VERSION.SDK_INT;
                if (getClipToPadding()) {
                    height2 -= getPaddingTop() + getPaddingBottom();
                    max -= getPaddingBottom();
                }
                canvas.translate((float) (i2 - width2), (float) max);
                canvas.rotate(180.0f, (float) width2, 0.0f);
                this.f836e.setSize(width2, height2);
                if (this.f836e.draw(canvas)) {
                    C0340mj.m14710d(this);
                }
                canvas.restoreToCount(save2);
            }
        }
    }

    /* renamed from: e */
    private final void m829e() {
        this.f841j = false;
        m825c();
        m823b(0);
        EdgeEffect edgeEffect = this.f835d;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.f836e.onRelease();
        }
    }

    /* renamed from: f */
    private final void m831f() {
        if (getOverScrollMode() == 2) {
            this.f835d = null;
            this.f836e = null;
        } else if (this.f835d == null) {
            Context context = getContext();
            this.f835d = new EdgeEffect(context);
            this.f836e = new EdgeEffect(context);
        }
    }

    /* renamed from: a */
    public final boolean mo708a(KeyEvent keyEvent) {
        this.f833b.setEmpty();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            if (childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom()) {
                if (keyEvent.getAction() != 0) {
                    return false;
                }
                int keyCode = keyEvent.getKeyCode();
                int i = 33;
                if (keyCode != 19) {
                    if (keyCode != 20) {
                        if (keyCode != 62) {
                            return false;
                        }
                        if (!keyEvent.isShiftPressed()) {
                            i = 130;
                        }
                        int height = getHeight();
                        if (i == 130) {
                            this.f833b.top = getScrollY() + height;
                            int childCount = getChildCount();
                            if (childCount > 0) {
                                View childAt2 = getChildAt(childCount - 1);
                                int bottom = childAt2.getBottom() + ((FrameLayout.LayoutParams) childAt2.getLayoutParams()).bottomMargin + getPaddingBottom();
                                if (this.f833b.top + height > bottom) {
                                    this.f833b.top = bottom - height;
                                }
                            }
                        } else {
                            this.f833b.top = getScrollY() - height;
                            if (this.f833b.top < 0) {
                                this.f833b.top = 0;
                            }
                        }
                        Rect rect = this.f833b;
                        rect.bottom = rect.top + height;
                        m815a(i, this.f833b.top, this.f833b.bottom);
                        return false;
                    } else if (!keyEvent.isAltPressed()) {
                        return m830e(130);
                    } else {
                        return m828d(130);
                    }
                } else if (!keyEvent.isAltPressed()) {
                    return m830e(33);
                } else {
                    return m828d(33);
                }
            }
        }
        if (isFocused() && keyEvent.getKeyCode() != 4) {
            View findFocus = findFocus();
            if (findFocus == this) {
                findFocus = null;
            }
            View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, 130);
            if (findNextFocus == null || findNextFocus == this || !findNextFocus.requestFocus(130)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: g */
    private final void m833g(int i) {
        if (getChildCount() > 0) {
            this.f834c.fling(getScrollX(), getScrollY(), 0, i, 0, 0, RecyclerView.UNDEFINED_DURATION, Integer.MAX_VALUE, 0, 0);
            m813a(true);
        }
    }

    /* renamed from: d */
    private final boolean m828d(int i) {
        int childCount;
        int height = getHeight();
        this.f833b.top = 0;
        this.f833b.bottom = height;
        if (i == 130 && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.f833b.bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
            Rect rect = this.f833b;
            rect.top = rect.bottom - height;
        }
        return m815a(i, this.f833b.top, this.f833b.bottom);
    }

    /* access modifiers changed from: protected */
    public final float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View childAt = getChildAt(0);
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return ((float) bottom) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public final int getNestedScrollAxes() {
        return this.f854y.mo9379a();
    }

    /* renamed from: a */
    public final int mo702a() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    /* access modifiers changed from: protected */
    public final float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return ((float) scrollY) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public final boolean hasNestedScrollingParent() {
        return m826c(0);
    }

    /* renamed from: c */
    private final boolean m826c(int i) {
        return this.f855z.mo9373a(i);
    }

    /* renamed from: b */
    private final void m822b() {
        if (this.f842k == null) {
            this.f842k = VelocityTracker.obtain();
        }
    }

    /* renamed from: a */
    private final boolean m818a(View view) {
        return !m819a(view, 0, getHeight());
    }

    /* renamed from: a */
    private static boolean m820a(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        if (!(parent instanceof ViewGroup) || !m820a((View) parent, view2)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private final boolean m819a(View view, int i, int i2) {
        view.getDrawingRect(this.f833b);
        offsetDescendantRectToMyCoords(view, this.f833b);
        return this.f833b.bottom + i >= getScrollY() && this.f833b.top - i <= getScrollY() + i2;
    }

    /* access modifiers changed from: protected */
    public final void measureChild(View view, int i, int i2) {
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* access modifiers changed from: protected */
    public final void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f839h = false;
    }

    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.f841j) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                float f = this.f831A;
                if (f == 0.0f) {
                    TypedValue typedValue = new TypedValue();
                    Context context = getContext();
                    if (context.getTheme().resolveAttribute(16842829, typedValue, true)) {
                        f = typedValue.getDimension(context.getResources().getDisplayMetrics());
                        this.f831A = f;
                    } else {
                        throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
                    }
                }
                int a = mo702a();
                int scrollY = getScrollY();
                int i = scrollY - ((int) (axisValue * f));
                if (i < 0) {
                    a = 0;
                } else if (i <= a) {
                    a = i;
                }
                if (a != scrollY) {
                    super.scrollTo(getScrollX(), a);
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.f841j) {
            return true;
        }
        int i = action & 255;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    int i2 = this.f848q;
                    if (i2 != -1) {
                        int findPointerIndex = motionEvent.findPointerIndex(i2);
                        if (findPointerIndex == -1) {
                            Log.e("NestedScrollView", "Invalid pointerId=" + i2 + " in onInterceptTouchEvent");
                        } else {
                            int y = (int) motionEvent.getY(findPointerIndex);
                            if (Math.abs(y - this.f837f) > this.f845n && (2 & getNestedScrollAxes()) == 0) {
                                this.f841j = true;
                                this.f837f = y;
                                m822b();
                                this.f842k.addMovement(motionEvent);
                                this.f851t = 0;
                                ViewParent parent = getParent();
                                if (parent != null) {
                                    parent.requestDisallowInterceptTouchEvent(true);
                                }
                            }
                        }
                    }
                } else if (i != 3) {
                    if (i == 6) {
                        m812a(motionEvent);
                    }
                }
            }
            this.f841j = false;
            this.f848q = -1;
            m825c();
            if (this.f834c.springBack(getScrollX(), getScrollY(), 0, 0, 0, mo702a())) {
                C0340mj.m14710d(this);
            }
            m823b(0);
        } else {
            int y2 = (int) motionEvent.getY();
            int x = (int) motionEvent.getX();
            if (getChildCount() > 0) {
                int scrollY = getScrollY();
                View childAt = getChildAt(0);
                if (y2 >= childAt.getTop() - scrollY && y2 < childAt.getBottom() - scrollY && x >= childAt.getLeft() && x < childAt.getRight()) {
                    this.f837f = y2;
                    this.f848q = motionEvent.getPointerId(0);
                    VelocityTracker velocityTracker = this.f842k;
                    if (velocityTracker != null) {
                        velocityTracker.clear();
                    } else {
                        this.f842k = VelocityTracker.obtain();
                    }
                    this.f842k.addMovement(motionEvent);
                    this.f834c.computeScrollOffset();
                    this.f841j = !this.f834c.isFinished();
                    m814a(2, 0);
                }
            }
            this.f841j = false;
            m825c();
        }
        return this.f841j;
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = 0;
        this.f838g = false;
        View view = this.f840i;
        if (view != null && m820a(view, (View) this)) {
            m824b(this.f840i);
        }
        this.f840i = null;
        if (!this.f839h) {
            if (this.f853v != null) {
                scrollTo(getScrollX(), this.f853v.f15306a);
                this.f853v = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                i5 = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int scrollY = getScrollY();
            int b = m821b(scrollY, ((i4 - i2) - paddingTop) - paddingBottom, i5);
            if (b != scrollY) {
                scrollTo(getScrollX(), b);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f839h = true;
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f843l && View.MeasureSpec.getMode(i2) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (z) {
            return false;
        }
        dispatchNestedFling(0.0f, f2, true);
        m833g((int) f2);
        return true;
    }

    public final boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        mo707a(view, i, i2, iArr, 0);
    }

    /* renamed from: a */
    public final void mo707a(View view, int i, int i2, int[] iArr, int i3) {
        m817a(i, i2, iArr, (int[]) null, i3);
    }

    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        m810a(i4, 0, (int[]) null);
    }

    /* renamed from: a */
    public final void mo705a(View view, int i, int i2, int i3, int i4, int i5) {
        m810a(i4, i5, (int[]) null);
    }

    /* renamed from: a */
    public final void mo706a(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        m810a(i4, i5, iArr);
    }

    public final void onNestedScrollAccepted(View view, View view2, int i) {
        mo714b(view, view2, i, 0);
    }

    /* renamed from: b */
    public final void mo714b(View view, View view2, int i, int i2) {
        this.f854y.mo9381a(i, i2);
        m814a(2, i2);
    }

    /* renamed from: a */
    private final void m810a(int i, int i2, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.f855z.mo9376a(0, scrollY2, 0, i - scrollY2, (int[]) null, i2, iArr);
    }

    /* access modifiers changed from: protected */
    public final void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.scrollTo(i, i2);
    }

    /* access modifiers changed from: protected */
    public final boolean onRequestFocusInDescendants(int i, Rect rect) {
        View view;
        if (i == 2) {
            i = 130;
        } else if (i == 1) {
            i = 33;
        }
        if (rect == null) {
            view = FocusFinder.getInstance().findNextFocus(this, (View) null, i);
        } else {
            view = FocusFinder.getInstance().findNextFocusFromRect(this, rect, i);
        }
        if (view == null || m818a(view)) {
            return false;
        }
        return view.requestFocus(i, rect);
    }

    /* access modifiers changed from: protected */
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof C0375nr)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        C0375nr nrVar = (C0375nr) parcelable;
        super.onRestoreInstanceState(nrVar.getSuperState());
        this.f853v = nrVar;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public final Parcelable onSaveInstanceState() {
        C0375nr nrVar = new C0375nr(super.onSaveInstanceState());
        nrVar.f15306a = getScrollY();
        return nrVar;
    }

    /* renamed from: a */
    private final void m812a(MotionEvent motionEvent) {
        int i;
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f848q) {
            if (actionIndex == 0) {
                i = 1;
            } else {
                i = 0;
            }
            this.f837f = (int) motionEvent.getY(i);
            this.f848q = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.f842k;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View findFocus = findFocus();
        if (findFocus != null && this != findFocus && m819a(findFocus, 0, i4)) {
            findFocus.getDrawingRect(this.f833b);
            offsetDescendantRectToMyCoords(findFocus, this.f833b);
            m832f(m808a(this.f833b));
        }
    }

    public final boolean onStartNestedScroll(View view, View view2, int i) {
        return mo709a(view, view2, i, 0);
    }

    public final void onStopNestedScroll(View view) {
        mo704a(view, 0);
    }

    /* renamed from: a */
    public final void mo704a(View view, int i) {
        this.f854y.mo9380a(i);
        m823b(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouchEvent(android.view.MotionEvent r19) {
        /*
            r18 = this;
            r6 = r18
            r7 = r19
            r18.m822b()
            int r0 = r19.getActionMasked()
            r8 = 0
            if (r0 != 0) goto L_0x0010
            r6.f851t = r8
        L_0x0010:
            android.view.MotionEvent r9 = android.view.MotionEvent.obtain(r19)
            int r1 = r6.f851t
            float r1 = (float) r1
            r2 = 0
            r9.offsetLocation(r2, r1)
            r1 = 2
            r10 = 1
            if (r0 == 0) goto L_0x0203
            r3 = -1
            if (r0 == r10) goto L_0x01b9
            if (r0 == r1) goto L_0x0081
            r1 = 3
            if (r0 == r1) goto L_0x0054
            r1 = 5
            if (r0 == r1) goto L_0x0041
            r1 = 6
            if (r0 == r1) goto L_0x002f
            goto L_0x023a
        L_0x002f:
            r18.m812a((android.view.MotionEvent) r19)
            int r0 = r6.f848q
            int r0 = r7.findPointerIndex(r0)
            float r0 = r7.getY(r0)
            int r0 = (int) r0
            r6.f837f = r0
            goto L_0x023a
        L_0x0041:
            int r0 = r19.getActionIndex()
            float r1 = r7.getY(r0)
            int r1 = (int) r1
            r6.f837f = r1
            int r0 = r7.getPointerId(r0)
            r6.f848q = r0
            goto L_0x023a
        L_0x0054:
            boolean r0 = r6.f841j
            if (r0 == 0) goto L_0x0079
            int r0 = r18.getChildCount()
            if (r0 <= 0) goto L_0x0079
            android.widget.OverScroller r11 = r6.f834c
            int r12 = r18.getScrollX()
            int r13 = r18.getScrollY()
            r14 = 0
            r15 = 0
            r16 = 0
            int r17 = r18.mo702a()
            boolean r0 = r11.springBack(r12, r13, r14, r15, r16, r17)
            if (r0 == 0) goto L_0x0079
            p000.C0340mj.m14710d(r18)
        L_0x0079:
            r6.f848q = r3
            r18.m829e()
            goto L_0x023a
        L_0x0081:
            int r0 = r6.f848q
            int r11 = r7.findPointerIndex(r0)
            if (r11 != r3) goto L_0x00a8
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid pointerId="
            r0.append(r1)
            int r1 = r6.f848q
            r0.append(r1)
            java.lang.String r1 = " in onTouchEvent"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "NestedScrollView"
            android.util.Log.e(r1, r0)
            goto L_0x023a
        L_0x00a8:
            float r0 = r7.getY(r11)
            int r12 = (int) r0
            int r0 = r6.f837f
            int r0 = r0 - r12
            boolean r1 = r6.f841j
            if (r1 != 0) goto L_0x00d3
            int r1 = java.lang.Math.abs(r0)
            int r2 = r6.f845n
            if (r1 <= r2) goto L_0x00d3
            android.view.ViewParent r1 = r18.getParent()
            if (r1 == 0) goto L_0x00c5
            r1.requestDisallowInterceptTouchEvent(r10)
        L_0x00c5:
            r6.f841j = r10
            if (r0 > 0) goto L_0x00ce
            int r1 = r6.f845n
            int r0 = r0 + r1
            goto L_0x00d3
        L_0x00ce:
            int r1 = r6.f845n
            int r0 = r0 - r1
            r13 = r0
            goto L_0x00d4
        L_0x00d3:
            r13 = r0
        L_0x00d4:
            boolean r0 = r6.f841j
            if (r0 == 0) goto L_0x023a
            r1 = 0
            int[] r3 = r6.f850s
            int[] r4 = r6.f849r
            r5 = 0
            r0 = r18
            r2 = r13
            boolean r0 = r0.m817a((int) r1, (int) r2, (int[]) r3, (int[]) r4, (int) r5)
            if (r0 == 0) goto L_0x00f5
            int[] r0 = r6.f850s
            r0 = r0[r10]
            int r13 = r13 - r0
            int r0 = r6.f851t
            int[] r1 = r6.f849r
            r1 = r1[r10]
            int r0 = r0 + r1
            r6.f851t = r0
        L_0x00f5:
            int[] r0 = r6.f849r
            r0 = r0[r10]
            int r12 = r12 - r0
            r6.f837f = r12
            int r12 = r18.getScrollY()
            int r14 = r18.mo702a()
            int r0 = r18.getOverScrollMode()
            if (r0 != 0) goto L_0x010c
        L_0x010a:
            r15 = 1
            goto L_0x0111
        L_0x010c:
            if (r0 != r10) goto L_0x0110
            if (r14 > 0) goto L_0x010a
        L_0x0110:
            r15 = 0
        L_0x0111:
            int r0 = r18.getScrollY()
            boolean r0 = r6.m816a((int) r13, (int) r8, (int) r0, (int) r14)
            if (r0 == 0) goto L_0x0126
            boolean r0 = r6.m826c(r8)
            if (r0 != 0) goto L_0x0126
            android.view.VelocityTracker r0 = r6.f842k
            r0.clear()
        L_0x0126:
            int r0 = r18.getScrollY()
            int r1 = r0 - r12
            int[] r5 = r6.f850s
            r5[r10] = r8
            int r2 = r13 - r1
            int[] r3 = r6.f849r
            r4 = 0
            r0 = r18
            r0.m811a((int) r1, (int) r2, (int[]) r3, (int) r4, (int[]) r5)
            int r0 = r6.f837f
            int[] r1 = r6.f849r
            r1 = r1[r10]
            int r0 = r0 - r1
            r6.f837f = r0
            int r0 = r6.f851t
            int r0 = r0 + r1
            r6.f851t = r0
            if (r15 == 0) goto L_0x023a
            int[] r0 = r6.f850s
            r0 = r0[r10]
            int r13 = r13 - r0
            r18.m831f()
            int r12 = r12 + r13
            if (r12 >= 0) goto L_0x017a
            android.widget.EdgeEffect r0 = r6.f835d
            float r1 = (float) r13
            int r2 = r18.getHeight()
            float r2 = (float) r2
            float r1 = r1 / r2
            float r2 = r7.getX(r11)
            int r3 = r18.getWidth()
            float r3 = (float) r3
            float r2 = r2 / r3
            p000.cya.m5635a(r0, r1, r2)
            android.widget.EdgeEffect r0 = r6.f836e
            boolean r0 = r0.isFinished()
            if (r0 == 0) goto L_0x0174
            goto L_0x01a2
        L_0x0174:
            android.widget.EdgeEffect r0 = r6.f836e
            r0.onRelease()
            goto L_0x01a2
        L_0x017a:
            if (r12 <= r14) goto L_0x01a2
            android.widget.EdgeEffect r0 = r6.f836e
            float r1 = (float) r13
            int r2 = r18.getHeight()
            float r2 = (float) r2
            float r1 = r1 / r2
            r2 = 1065353216(0x3f800000, float:1.0)
            float r3 = r7.getX(r11)
            int r4 = r18.getWidth()
            float r4 = (float) r4
            float r3 = r3 / r4
            float r2 = r2 - r3
            p000.cya.m5635a(r0, r1, r2)
            android.widget.EdgeEffect r0 = r6.f835d
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L_0x01a2
            android.widget.EdgeEffect r0 = r6.f835d
            r0.onRelease()
        L_0x01a2:
            android.widget.EdgeEffect r0 = r6.f835d
            if (r0 == 0) goto L_0x023a
            boolean r0 = r0.isFinished()
            if (r0 == 0) goto L_0x01b4
            android.widget.EdgeEffect r0 = r6.f836e
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L_0x023a
        L_0x01b4:
            p000.C0340mj.m14710d(r18)
            goto L_0x023a
        L_0x01b9:
            android.view.VelocityTracker r0 = r6.f842k
            r1 = 1000(0x3e8, float:1.401E-42)
            int r4 = r6.f847p
            float r4 = (float) r4
            r0.computeCurrentVelocity(r1, r4)
            int r1 = r6.f848q
            float r0 = r0.getYVelocity(r1)
            int r0 = (int) r0
            int r1 = java.lang.Math.abs(r0)
            int r4 = r6.f846o
            if (r1 < r4) goto L_0x01e1
            int r0 = -r0
            float r1 = (float) r0
            boolean r4 = r6.dispatchNestedPreFling(r2, r1)
            if (r4 != 0) goto L_0x01fc
            r6.dispatchNestedFling(r2, r1, r10)
            r6.m833g(r0)
            goto L_0x01fc
        L_0x01e1:
            android.widget.OverScroller r11 = r6.f834c
            int r12 = r18.getScrollX()
            int r13 = r18.getScrollY()
            r14 = 0
            r15 = 0
            r16 = 0
            int r17 = r18.mo702a()
            boolean r0 = r11.springBack(r12, r13, r14, r15, r16, r17)
            if (r0 == 0) goto L_0x01fc
            p000.C0340mj.m14710d(r18)
        L_0x01fc:
            r6.f848q = r3
            r18.m829e()
            goto L_0x023a
        L_0x0203:
            int r0 = r18.getChildCount()
            if (r0 == 0) goto L_0x0245
            android.widget.OverScroller r0 = r6.f834c
            boolean r0 = r0.isFinished()
            r0 = r0 ^ r10
            r6.f841j = r0
            if (r0 == 0) goto L_0x021f
            android.view.ViewParent r0 = r18.getParent()
            if (r0 != 0) goto L_0x021b
            goto L_0x021f
        L_0x021b:
            r0.requestDisallowInterceptTouchEvent(r10)
        L_0x021f:
            android.widget.OverScroller r0 = r6.f834c
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L_0x022a
            r18.m827d()
        L_0x022a:
            float r0 = r19.getY()
            int r0 = (int) r0
            r6.f837f = r0
            int r0 = r7.getPointerId(r8)
            r6.f848q = r0
            r6.m814a((int) r1, (int) r8)
        L_0x023a:
            android.view.VelocityTracker r0 = r6.f842k
            if (r0 == 0) goto L_0x0241
            r0.addMovement(r9)
        L_0x0241:
            r9.recycle()
            return r10
        L_0x0245:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p001v4.widget.NestedScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* renamed from: a */
    private final boolean m816a(int i, int i2, int i3, int i4) {
        boolean z;
        getOverScrollMode();
        super.computeHorizontalScrollRange();
        super.computeHorizontalScrollExtent();
        computeVerticalScrollRange();
        super.computeVerticalScrollExtent();
        int i5 = i3 + i;
        boolean z2 = i2 > 0 || i2 < 0;
        if (i5 > i4) {
            z = true;
        } else if (i5 >= 0) {
            i4 = i5;
            z = false;
        } else {
            z = true;
            i4 = 0;
        }
        if (z && !m826c(1)) {
            this.f834c.springBack(0, i4, 0, 0, 0, mo702a());
        }
        super.scrollTo(0, i4);
        return z2 || z;
    }

    /* renamed from: c */
    private final void m825c() {
        VelocityTracker velocityTracker = this.f842k;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f842k = null;
        }
    }

    public final void requestChildFocus(View view, View view2) {
        if (!this.f838g) {
            m824b(view2);
        } else {
            this.f840i = view2;
        }
        super.requestChildFocus(view, view2);
    }

    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        int a = m808a(rect);
        boolean z2 = a != 0;
        if (z2) {
            if (z) {
                scrollBy(0, a);
            } else {
                m834h(a);
            }
        }
        return z2;
    }

    public final void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            m825c();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public final void requestLayout() {
        this.f838g = true;
        super.requestLayout();
    }

    /* renamed from: a */
    private final void m813a(boolean z) {
        if (z) {
            m814a(2, 1);
        } else {
            m823b(1);
        }
        this.f852u = getScrollY();
        C0340mj.m14710d(this);
    }

    /* renamed from: a */
    private final boolean m815a(int i, int i2, int i3) {
        boolean z;
        int i4;
        boolean z2;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int height = getHeight();
        int scrollY = getScrollY();
        int i8 = height + scrollY;
        ArrayList focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z3 = false;
        for (int i9 = 0; i9 < size; i9++) {
            View view2 = (View) focusables.get(i9);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i6 < bottom && top < i7) {
                if (i6 < top && bottom < i7) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (view == null) {
                    view = view2;
                    z3 = z2;
                } else {
                    boolean z4 = i5 == 33 ? top < view.getTop() : bottom > view.getBottom();
                    if (z3) {
                        if (z2) {
                            if (!z4) {
                            }
                        }
                    } else if (z2) {
                        view = view2;
                        z3 = true;
                    } else if (!z4) {
                    }
                    view = view2;
                }
            }
        }
        if (view == null) {
            view = this;
        }
        if (i6 < scrollY || i7 > i8) {
            if (i5 != 33) {
                i4 = i7 - i8;
            } else {
                i4 = i6 - scrollY;
            }
            m832f(i4);
            z = true;
        } else {
            z = false;
        }
        if (view != findFocus()) {
            view.requestFocus(i5);
        }
        return z;
    }

    public final void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int b = m821b(i, (getWidth() - getPaddingLeft()) - getPaddingRight(), childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
            int b2 = m821b(i2, (getHeight() - getPaddingTop()) - getPaddingBottom(), childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
            if (b != getScrollX() || b2 != getScrollY()) {
                super.scrollTo(b, b2);
            }
        }
    }

    /* renamed from: b */
    private final void m824b(View view) {
        view.getDrawingRect(this.f833b);
        offsetDescendantRectToMyCoords(view, this.f833b);
        int a = m808a(this.f833b);
        if (a != 0) {
            scrollBy(0, a);
        }
    }

    public final void setNestedScrollingEnabled(boolean z) {
        this.f855z.mo9370a(z);
    }

    /* renamed from: h */
    private final void m834h(int i) {
        m809a(0, i, false);
    }

    /* renamed from: a */
    private final void m809a(int i, int i2, boolean z) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.f832a > 250) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                int height = childAt.getHeight();
                int i3 = layoutParams.topMargin;
                int i4 = layoutParams.bottomMargin;
                int height2 = getHeight();
                int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int scrollY = getScrollY();
                this.f834c.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i2 + scrollY, Math.max(0, ((height + i3) + i4) - ((height2 - paddingTop) - paddingBottom)))) - scrollY);
                m813a(z);
            } else {
                if (!this.f834c.isFinished()) {
                    m827d();
                }
                scrollBy(i, i2);
            }
            this.f832a = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    /* renamed from: a */
    public final void mo703a(int i) {
        m809a(-getScrollX(), i - getScrollY(), true);
    }

    public final boolean startNestedScroll(int i) {
        return m814a(i, 0);
    }

    /* renamed from: a */
    private final boolean m814a(int i, int i2) {
        return this.f855z.mo9374a(i, i2);
    }

    public final void stopNestedScroll() {
        m823b(0);
    }

    /* renamed from: b */
    private final void m823b(int i) {
        this.f855z.mo9378b(i);
    }
}
