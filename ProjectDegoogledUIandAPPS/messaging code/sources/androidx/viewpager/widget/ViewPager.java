package androidx.viewpager.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

public class ViewPager extends ViewGroup {
    private static final Comparator COMPARATOR = new C0617b();

    /* renamed from: tj */
    static final int[] f699tj = {16842931};

    /* renamed from: uj */
    private static final Interpolator f700uj = new C0618c();

    /* renamed from: vj */
    private static final C0630o f701vj = new C0630o();

    /* renamed from: Hi */
    private int f702Hi;

    /* renamed from: Ii */
    private final C0622g f703Ii = new C0622g();

    /* renamed from: Ji */
    int f704Ji;

    /* renamed from: Ki */
    private int f705Ki = -1;

    /* renamed from: Li */
    private Parcelable f706Li = null;

    /* renamed from: Mi */
    private ClassLoader f707Mi = null;

    /* renamed from: Ni */
    private boolean f708Ni;

    /* renamed from: Oi */
    private int f709Oi;

    /* renamed from: Pi */
    private Drawable f710Pi;

    /* renamed from: Qi */
    private int f711Qi;

    /* renamed from: Ri */
    private int f712Ri;

    /* renamed from: Si */
    private float f713Si = -3.4028235E38f;

    /* renamed from: Ti */
    private float f714Ti = Float.MAX_VALUE;

    /* renamed from: Ui */
    private boolean f715Ui;

    /* renamed from: Vi */
    private int f716Vi = 1;

    /* renamed from: Wi */
    private boolean f717Wi;

    /* renamed from: Xi */
    private int f718Xi;

    /* renamed from: Yi */
    private int f719Yi;

    /* renamed from: Zi */
    private boolean f720Zi = true;

    /* renamed from: _i */
    private float f721_i;

    /* renamed from: aj */
    private float f722aj;

    /* renamed from: bj */
    private float f723bj;

    /* renamed from: cj */
    private int f724cj;

    /* renamed from: dj */
    private int f725dj;

    /* renamed from: ej */
    private boolean f726ej;

    /* renamed from: fj */
    private EdgeEffect f727fj;

    /* renamed from: gj */
    private EdgeEffect f728gj;

    /* renamed from: hj */
    private boolean f729hj = true;

    /* renamed from: ij */
    private boolean f730ij;

    /* renamed from: jj */
    private int f731jj;

    /* renamed from: kj */
    private List f732kj;

    /* renamed from: li */
    private int f733li = 0;

    /* renamed from: lj */
    private C0626k f734lj;
    private int mActivePointerId = -1;
    C0616a mAdapter;
    private boolean mInLayout;
    private boolean mIsBeingDragged;
    private final ArrayList mItems = new ArrayList();
    private float mLastMotionY;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private C0628m mObserver;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final Rect mTempRect = new Rect();
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    /* renamed from: mj */
    private C0626k f735mj;

    /* renamed from: nj */
    private List f736nj;

    /* renamed from: oj */
    private C0627l f737oj;

    /* renamed from: pj */
    private int f738pj;

    /* renamed from: qj */
    private int f739qj;

    /* renamed from: rj */
    private ArrayList f740rj;

    /* renamed from: sj */
    private final Runnable f741sj = new C0619d(this);

    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new C0629n();
        Parcelable adapterState;
        ClassLoader loader;
        int position;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder Pa = C0632a.m1011Pa("FragmentPager.SavedState{");
            Pa.append(Integer.toHexString(System.identityHashCode(this)));
            Pa.append(" position=");
            Pa.append(this.position);
            Pa.append("}");
            return Pa.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.position);
            parcel.writeParcelable(this.adapterState, i);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? SavedState.class.getClassLoader() : classLoader;
            this.position = parcel.readInt();
            this.adapterState = parcel.readParcelable(classLoader);
            this.loader = classLoader;
        }
    }

    public ViewPager(Context context) {
        super(context);
        mo5329Xb();
    }

    /* renamed from: c */
    private void m994c(int i, int i2, int i3, int i4) {
        if (i2 <= 0 || this.mItems.isEmpty()) {
            C0622g infoForPosition = infoForPosition(this.f704Ji);
            int min = (int) ((infoForPosition != null ? Math.min(infoForPosition.offset, this.f714Ti) : 0.0f) * ((float) ((i - getPaddingLeft()) - getPaddingRight())));
            if (min != getScrollX()) {
                m1002ya(false);
                scrollTo(min, getScrollY());
            }
        } else if (!this.mScroller.isFinished()) {
            this.mScroller.setFinalX(getCurrentItem() * m996jn());
        } else {
            scrollTo((int) ((((float) getScrollX()) / ((float) (((i2 - getPaddingLeft()) - getPaddingRight()) + i4))) * ((float) (((i - getPaddingLeft()) - getPaddingRight()) + i3))), getScrollY());
        }
    }

    /* renamed from: d */
    private boolean m995d(float f) {
        boolean z;
        boolean z2;
        float f2 = this.f721_i - f;
        this.f721_i = f;
        float scrollX = ((float) getScrollX()) + f2;
        float jn = (float) m996jn();
        float f3 = this.f713Si * jn;
        float f4 = this.f714Ti * jn;
        boolean z3 = false;
        C0622g gVar = (C0622g) this.mItems.get(0);
        ArrayList arrayList = this.mItems;
        C0622g gVar2 = (C0622g) arrayList.get(arrayList.size() - 1);
        if (gVar.position != 0) {
            f3 = gVar.offset * jn;
            z = false;
        } else {
            z = true;
        }
        if (gVar2.position != this.mAdapter.getCount() - 1) {
            f4 = gVar2.offset * jn;
            z2 = false;
        } else {
            z2 = true;
        }
        if (scrollX < f3) {
            if (z) {
                this.f727fj.onPull(Math.abs(f3 - scrollX) / jn);
                z3 = true;
            }
            scrollX = f3;
        } else if (scrollX > f4) {
            if (z2) {
                this.f728gj.onPull(Math.abs(scrollX - f4) / jn);
                z3 = true;
            }
            scrollX = f4;
        }
        int i = (int) scrollX;
        this.f721_i = (scrollX - ((float) i)) + this.f721_i;
        scrollTo(i, getScrollY());
        m1001nb(i);
        return z3;
    }

    /* renamed from: jn */
    private int m996jn() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    /* renamed from: kn */
    private C0622g m997kn() {
        int i;
        int jn = m996jn();
        float scrollX = jn > 0 ? ((float) getScrollX()) / ((float) jn) : 0.0f;
        float f = jn > 0 ? ((float) this.f709Oi) / ((float) jn) : 0.0f;
        C0622g gVar = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i2 = 0;
        int i3 = -1;
        boolean z = true;
        while (i2 < this.mItems.size()) {
            C0622g gVar2 = (C0622g) this.mItems.get(i2);
            if (!z && gVar2.position != (i = i3 + 1)) {
                gVar2 = this.f703Ii;
                gVar2.offset = f2 + f3 + f;
                gVar2.position = i;
                gVar2.widthFactor = this.mAdapter.getPageWidth(gVar2.position);
                i2--;
            }
            f2 = gVar2.offset;
            float f4 = gVar2.widthFactor + f2 + f;
            if (!z && scrollX < f2) {
                return gVar;
            }
            if (scrollX < f4 || i2 == this.mItems.size() - 1) {
                return gVar2;
            }
            i3 = gVar2.position;
            f3 = gVar2.widthFactor;
            i2++;
            z = false;
            gVar = gVar2;
        }
        return gVar;
    }

    /* renamed from: ln */
    private boolean m998ln() {
        this.mActivePointerId = -1;
        this.mIsBeingDragged = false;
        this.f717Wi = false;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        this.f727fj.onRelease();
        this.f728gj.onRelease();
        if (this.f727fj.isFinished() || this.f728gj.isFinished()) {
            return true;
        }
        return false;
    }

    /* renamed from: mb */
    private void m999mb(int i) {
        C0626k kVar = this.f734lj;
        if (kVar != null) {
            kVar.onPageSelected(i);
        }
        List list = this.f732kj;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                C0626k kVar2 = (C0626k) this.f732kj.get(i2);
                if (kVar2 != null) {
                    kVar2.onPageSelected(i);
                }
            }
        }
        C0626k kVar3 = this.f735mj;
        if (kVar3 != null) {
            kVar3.onPageSelected(i);
        }
    }

    /* renamed from: mn */
    private void m1000mn() {
        if (this.f739qj != 0) {
            ArrayList arrayList = this.f740rj;
            if (arrayList == null) {
                this.f740rj = new ArrayList();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.f740rj.add(getChildAt(i));
            }
            Collections.sort(this.f740rj, f701vj);
        }
    }

    /* renamed from: nb */
    private boolean m1001nb(int i) {
        if (this.mItems.size() != 0) {
            C0622g kn = m997kn();
            int jn = m996jn();
            int i2 = this.f709Oi;
            int i3 = jn + i2;
            float f = (float) jn;
            int i4 = kn.position;
            float f2 = ((((float) i) / f) - kn.offset) / (kn.widthFactor + (((float) i2) / f));
            this.f730ij = false;
            onPageScrolled(i4, f2, (int) (((float) i3) * f2));
            if (this.f730ij) {
                return true;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        } else if (this.f729hj) {
            return false;
        } else {
            this.f730ij = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.f730ij) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            int i = actionIndex == 0 ? 1 : 0;
            this.f721_i = motionEvent.getX(i);
            this.mActivePointerId = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.mScrollingCacheEnabled != z) {
            this.mScrollingCacheEnabled = z;
        }
    }

    /* renamed from: ya */
    private void m1002ya(boolean z) {
        boolean z2 = this.f733li == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (!(scrollX == currX && scrollY == currY)) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        m1001nb(currX);
                    }
                }
            }
        }
        this.f715Ui = false;
        boolean z3 = z2;
        for (int i = 0; i < this.mItems.size(); i++) {
            C0622g gVar = (C0622g) this.mItems.get(i);
            if (gVar.scrolling) {
                gVar.scrolling = false;
                z3 = true;
            }
        }
        if (!z3) {
            return;
        }
        if (z) {
            ViewCompat.postOnAnimation(this, this.f741sj);
        } else {
            this.f741sj.run();
        }
    }

    /* renamed from: za */
    private void m1003za(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Xb */
    public void mo5329Xb() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.mScroller = new Scroller(context, f700uj);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int) (400.0f * f);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f727fj = new EdgeEffect(context);
        this.f728gj = new EdgeEffect(context);
        this.f724cj = (int) (25.0f * f);
        this.f725dj = (int) (2.0f * f);
        this.f718Xi = (int) (f * 16.0f);
        ViewCompat.setAccessibilityDelegate(this, new C0624i(this));
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            int i = Build.VERSION.SDK_INT;
            setImportantForAccessibility(1);
        }
        ViewCompat.setOnApplyWindowInsetsListener(this, new C0620e(this));
    }

    /* renamed from: a */
    public void mo5330a(C0616a aVar) {
        C0616a aVar2 = this.mAdapter;
        if (aVar2 != null) {
            aVar2.mo5386a((DataSetObserver) null);
            this.mAdapter.startUpdate((ViewGroup) this);
            for (int i = 0; i < this.mItems.size(); i++) {
                C0622g gVar = (C0622g) this.mItems.get(i);
                this.mAdapter.destroyItem((ViewGroup) this, gVar.position, gVar.object);
            }
            this.mAdapter.finishUpdate((ViewGroup) this);
            this.mItems.clear();
            int i2 = 0;
            while (i2 < getChildCount()) {
                if (!((C0623h) getChildAt(i2).getLayoutParams()).isDecor) {
                    removeViewAt(i2);
                    i2--;
                }
                i2++;
            }
            this.f704Ji = 0;
            scrollTo(0, 0);
        }
        C0616a aVar3 = this.mAdapter;
        this.mAdapter = aVar;
        this.f702Hi = 0;
        if (this.mAdapter != null) {
            if (this.mObserver == null) {
                this.mObserver = new C0628m(this);
            }
            this.mAdapter.mo5386a(this.mObserver);
            this.f715Ui = false;
            boolean z = this.f729hj;
            this.f729hj = true;
            this.f702Hi = this.mAdapter.getCount();
            if (this.f705Ki >= 0) {
                this.mAdapter.restoreState(this.f706Li, this.f707Mi);
                setCurrentItemInternal(this.f705Ki, false, true);
                this.f705Ki = -1;
                this.f706Li = null;
                this.f707Mi = null;
            } else if (!z) {
                populate();
            } else {
                requestLayout();
            }
        }
        List list = this.f736nj;
        if (list != null && !list.isEmpty()) {
            int size = this.f736nj.size();
            for (int i3 = 0; i3 < size; i3++) {
                ((C0625j) this.f736nj.get(i3)).mo5408a(this, aVar3, aVar);
            }
        }
    }

    public void addFocusables(ArrayList arrayList, int i, int i2) {
        C0622g infoForChild;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.position == this.f704Ji) {
                    childAt.addFocusables(arrayList, i, i2);
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if ((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) {
            arrayList.add(this);
        }
    }

    /* access modifiers changed from: package-private */
    public C0622g addNewItem(int i, int i2) {
        C0622g gVar = new C0622g();
        gVar.position = i;
        gVar.object = this.mAdapter.instantiateItem((ViewGroup) this, i);
        gVar.widthFactor = this.mAdapter.getPageWidth(i);
        if (i2 < 0 || i2 >= this.mItems.size()) {
            this.mItems.add(gVar);
        } else {
            this.mItems.add(i2, gVar);
        }
        return gVar;
    }

    public void addTouchables(ArrayList arrayList) {
        C0622g infoForChild;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.position == this.f704Ji) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        C0623h hVar = (C0623h) layoutParams;
        hVar.isDecor |= view.getClass().getAnnotation(C0621f.class) != null;
        if (!this.mInLayout) {
            super.addView(view, i, layoutParams);
        } else if (!hVar.isDecor) {
            hVar.needsMeasure = true;
            addViewInLayout(view, i, layoutParams);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean arrowScroll(int r7) {
        /*
            r6 = this;
            android.view.View r0 = r6.findFocus()
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 != r6) goto L_0x000a
            goto L_0x0063
        L_0x000a:
            if (r0 == 0) goto L_0x0064
            android.view.ViewParent r4 = r0.getParent()
        L_0x0010:
            boolean r5 = r4 instanceof android.view.ViewGroup
            if (r5 == 0) goto L_0x001d
            if (r4 != r6) goto L_0x0018
            r4 = r1
            goto L_0x001e
        L_0x0018:
            android.view.ViewParent r4 = r4.getParent()
            goto L_0x0010
        L_0x001d:
            r4 = r2
        L_0x001e:
            if (r4 != 0) goto L_0x0064
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.Class r5 = r0.getClass()
            java.lang.String r5 = r5.getSimpleName()
            r4.append(r5)
            android.view.ViewParent r0 = r0.getParent()
        L_0x0034:
            boolean r5 = r0 instanceof android.view.ViewGroup
            if (r5 == 0) goto L_0x004d
            java.lang.String r5 = " => "
            r4.append(r5)
            java.lang.Class r5 = r0.getClass()
            java.lang.String r5 = r5.getSimpleName()
            r4.append(r5)
            android.view.ViewParent r0 = r0.getParent()
            goto L_0x0034
        L_0x004d:
            java.lang.String r0 = "arrowScroll tried to find focus based on non-child current focused view "
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            java.lang.String r4 = r4.toString()
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.String r4 = "ViewPager"
            android.util.Log.e(r4, r0)
        L_0x0063:
            r0 = r3
        L_0x0064:
            android.view.FocusFinder r3 = android.view.FocusFinder.getInstance()
            android.view.View r3 = r3.findNextFocus(r6, r0, r7)
            r4 = 66
            r5 = 17
            if (r3 == 0) goto L_0x00b5
            if (r3 == r0) goto L_0x00b5
            if (r7 != r5) goto L_0x0095
            android.graphics.Rect r1 = r6.mTempRect
            android.graphics.Rect r1 = r6.m992a((android.graphics.Rect) r1, (android.view.View) r3)
            int r1 = r1.left
            android.graphics.Rect r2 = r6.mTempRect
            android.graphics.Rect r2 = r6.m992a((android.graphics.Rect) r2, (android.view.View) r0)
            int r2 = r2.left
            if (r0 == 0) goto L_0x008f
            if (r1 < r2) goto L_0x008f
            boolean r0 = r6.pageLeft()
            goto L_0x0093
        L_0x008f:
            boolean r0 = r3.requestFocus()
        L_0x0093:
            r2 = r0
            goto L_0x00c8
        L_0x0095:
            if (r7 != r4) goto L_0x00c8
            android.graphics.Rect r1 = r6.mTempRect
            android.graphics.Rect r1 = r6.m992a((android.graphics.Rect) r1, (android.view.View) r3)
            int r1 = r1.left
            android.graphics.Rect r2 = r6.mTempRect
            android.graphics.Rect r2 = r6.m992a((android.graphics.Rect) r2, (android.view.View) r0)
            int r2 = r2.left
            if (r0 == 0) goto L_0x00b0
            if (r1 > r2) goto L_0x00b0
            boolean r0 = r6.pageRight()
            goto L_0x0093
        L_0x00b0:
            boolean r0 = r3.requestFocus()
            goto L_0x0093
        L_0x00b5:
            if (r7 == r5) goto L_0x00c4
            if (r7 != r1) goto L_0x00ba
            goto L_0x00c4
        L_0x00ba:
            if (r7 == r4) goto L_0x00bf
            r0 = 2
            if (r7 != r0) goto L_0x00c8
        L_0x00bf:
            boolean r2 = r6.pageRight()
            goto L_0x00c8
        L_0x00c4:
            boolean r2 = r6.pageLeft()
        L_0x00c8:
            if (r2 == 0) goto L_0x00d1
            int r7 = android.view.SoundEffectConstants.getContantForFocusDirection(r7)
            r6.playSoundEffect(r7)
        L_0x00d1:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.arrowScroll(int):boolean");
    }

    /* access modifiers changed from: protected */
    public boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        int i4;
        View view2 = view;
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i5 = i2 + scrollX;
                if (i5 >= childAt.getLeft() && i5 < childAt.getRight() && (i4 = i3 + scrollY) >= childAt.getTop() && i4 < childAt.getBottom()) {
                    if (canScroll(childAt, true, i, i5 - childAt.getLeft(), i4 - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (!z || !view.canScrollHorizontally(-i)) {
            return false;
        }
        return true;
    }

    public boolean canScrollHorizontally(int i) {
        if (this.mAdapter == null) {
            return false;
        }
        int jn = m996jn();
        int scrollX = getScrollX();
        if (i < 0) {
            if (scrollX > ((int) (((float) jn) * this.f713Si))) {
                return true;
            }
            return false;
        } else if (i <= 0 || scrollX >= ((int) (((float) jn) * this.f714Ti))) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof C0623h) && super.checkLayoutParams(layoutParams);
    }

    public void computeScroll() {
        this.f708Ni = true;
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            m1002ya(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.mScroller.getCurrX();
        int currY = this.mScroller.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!m1001nb(currX)) {
                this.mScroller.abortAnimation();
                scrollTo(0, currY);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* access modifiers changed from: package-private */
    public void dataSetChanged() {
        int count = this.mAdapter.getCount();
        this.f702Hi = count;
        boolean z = this.mItems.size() < (this.f716Vi * 2) + 1 && this.mItems.size() < count;
        int i = this.f704Ji;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < this.mItems.size()) {
            C0622g gVar = (C0622g) this.mItems.get(i2);
            int itemPosition = this.mAdapter.getItemPosition(gVar.object);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.mItems.remove(i2);
                    i2--;
                    if (!z2) {
                        this.mAdapter.startUpdate((ViewGroup) this);
                        z2 = true;
                    }
                    this.mAdapter.destroyItem((ViewGroup) this, gVar.position, gVar.object);
                    int i3 = this.f704Ji;
                    if (i3 == gVar.position) {
                        i = Math.max(0, Math.min(i3, count - 1));
                    }
                } else {
                    int i4 = gVar.position;
                    if (i4 != itemPosition) {
                        if (i4 == this.f704Ji) {
                            i = itemPosition;
                        }
                        gVar.position = itemPosition;
                    }
                }
                z = true;
            }
            i2++;
        }
        if (z2) {
            this.mAdapter.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (z) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                C0623h hVar = (C0623h) getChildAt(i5).getLayoutParams();
                if (!hVar.isDecor) {
                    hVar.widthFactor = 0.0f;
                }
            }
            setCurrentItemInternal(i, false, true);
            requestLayout();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        C0622g infoForChild;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.position == this.f704Ji && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
    }

    public void draw(Canvas canvas) {
        C0616a aVar;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean z = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (aVar = this.mAdapter) != null && aVar.getCount() > 1)) {
            if (!this.f727fj.isFinished()) {
                int save = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) (getPaddingTop() + (-height)), this.f713Si * ((float) width));
                this.f727fj.setSize(height, width);
                z = false | this.f727fj.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.f728gj.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.f714Ti + 1.0f)) * ((float) width2));
                this.f728gj.setSize(height2, width2);
                z |= this.f728gj.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.f727fj.finish();
            this.f728gj.finish();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f710Pi;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 21) {
                if (keyCode != 22) {
                    if (keyCode == 61) {
                        if (keyEvent.hasNoModifiers()) {
                            return arrowScroll(2);
                        }
                        if (keyEvent.hasModifiers(1)) {
                            return arrowScroll(1);
                        }
                    }
                } else if (keyEvent.hasModifiers(2)) {
                    return pageRight();
                } else {
                    return arrowScroll(66);
                }
            } else if (keyEvent.hasModifiers(2)) {
                return pageLeft();
            } else {
                return arrowScroll(17);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C0623h();
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public C0616a getAdapter() {
        return this.mAdapter;
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i, int i2) {
        if (this.f739qj == 2) {
            i2 = (i - 1) - i2;
        }
        return ((C0623h) ((View) this.f740rj.get(i2)).getLayoutParams()).childIndex;
    }

    public int getCurrentItem() {
        return this.f704Ji;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.view.ViewParent] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.viewpager.widget.C0622g infoForAnyChild(android.view.View r2) {
        /*
            r1 = this;
        L_0x0000:
            android.view.ViewParent r0 = r2.getParent()
            if (r0 == r1) goto L_0x0010
            boolean r2 = r0 instanceof android.view.View
            if (r2 != 0) goto L_0x000c
            r1 = 0
            return r1
        L_0x000c:
            r2 = r0
            android.view.View r2 = (android.view.View) r2
            goto L_0x0000
        L_0x0010:
            androidx.viewpager.widget.g r1 = r1.infoForChild(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.infoForAnyChild(android.view.View):androidx.viewpager.widget.g");
    }

    /* access modifiers changed from: package-private */
    public C0622g infoForChild(View view) {
        for (int i = 0; i < this.mItems.size(); i++) {
            C0622g gVar = (C0622g) this.mItems.get(i);
            if (this.mAdapter.isViewFromObject(view, gVar.object)) {
                return gVar;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public C0622g infoForPosition(int i) {
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            C0622g gVar = (C0622g) this.mItems.get(i2);
            if (gVar.position == i) {
                return gVar;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f729hj = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        removeCallbacks(this.f741sj);
        Scroller scroller = this.mScroller;
        if (scroller != null && !scroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f;
        float f2;
        super.onDraw(canvas);
        if (this.f709Oi > 0 && this.f710Pi != null && this.mItems.size() > 0 && this.mAdapter != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f3 = (float) width;
            float f4 = ((float) this.f709Oi) / f3;
            int i = 0;
            C0622g gVar = (C0622g) this.mItems.get(0);
            float f5 = gVar.offset;
            int size = this.mItems.size();
            int i2 = gVar.position;
            int i3 = ((C0622g) this.mItems.get(size - 1)).position;
            while (i2 < i3) {
                while (i2 > gVar.position && i < size) {
                    i++;
                    gVar = (C0622g) this.mItems.get(i);
                }
                if (i2 == gVar.position) {
                    float f6 = gVar.offset;
                    float f7 = gVar.widthFactor;
                    f = (f6 + f7) * f3;
                    f5 = f6 + f7 + f4;
                } else {
                    float pageWidth = this.mAdapter.getPageWidth(i2);
                    f = (f5 + pageWidth) * f3;
                    f5 = pageWidth + f4 + f5;
                }
                if (((float) this.f709Oi) + f > ((float) scrollX)) {
                    f2 = f4;
                    this.f710Pi.setBounds(Math.round(f), this.f711Qi, Math.round(((float) this.f709Oi) + f), this.f712Ri);
                    this.f710Pi.draw(canvas);
                } else {
                    Canvas canvas2 = canvas;
                    f2 = f4;
                }
                if (f <= ((float) (scrollX + width))) {
                    i2++;
                    f4 = f2;
                } else {
                    return;
                }
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            m998ln();
            return false;
        }
        if (action != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.f717Wi) {
                return false;
            }
        }
        if (action == 0) {
            float x = motionEvent.getX();
            this.f722aj = x;
            this.f721_i = x;
            float y = motionEvent.getY();
            this.f723bj = y;
            this.mLastMotionY = y;
            this.mActivePointerId = motionEvent2.getPointerId(0);
            this.f717Wi = false;
            this.f708Ni = true;
            this.mScroller.computeScrollOffset();
            if (this.f733li != 2 || Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) <= this.f725dj) {
                m1002ya(false);
                this.mIsBeingDragged = false;
            } else {
                this.mScroller.abortAnimation();
                this.f715Ui = false;
                populate();
                this.mIsBeingDragged = true;
                m1003za(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i = this.mActivePointerId;
            if (i != -1) {
                int findPointerIndex = motionEvent2.findPointerIndex(i);
                float x2 = motionEvent2.getX(findPointerIndex);
                float f = x2 - this.f721_i;
                float abs = Math.abs(f);
                float y2 = motionEvent2.getY(findPointerIndex);
                float abs2 = Math.abs(y2 - this.f723bj);
                int i2 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                if (i2 != 0) {
                    float f2 = this.f721_i;
                    if (!(!this.f720Zi && ((f2 < ((float) this.f719Yi) && i2 > 0) || (f2 > ((float) (getWidth() - this.f719Yi)) && f < 0.0f)))) {
                        if (canScroll(this, false, (int) f, (int) x2, (int) y2)) {
                            this.f721_i = x2;
                            this.mLastMotionY = y2;
                            this.f717Wi = true;
                            return false;
                        }
                    }
                }
                if (abs > ((float) this.mTouchSlop) && abs * 0.5f > abs2) {
                    this.mIsBeingDragged = true;
                    m1003za(true);
                    setScrollState(1);
                    float f3 = this.f722aj;
                    float f4 = (float) this.mTouchSlop;
                    this.f721_i = i2 > 0 ? f3 + f4 : f3 - f4;
                    this.mLastMotionY = y2;
                    setScrollingCacheEnabled(true);
                } else if (abs2 > ((float) this.mTouchSlop)) {
                    this.f717Wi = true;
                }
                if (this.mIsBeingDragged && m995d(x2)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            }
        } else if (action == 6) {
            onSecondaryPointerUp(motionEvent);
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent2);
        return this.mIsBeingDragged;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        C0622g infoForChild;
        int i5;
        int i6;
        int childCount = getChildCount();
        int i7 = i3 - i;
        int i8 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i9 = paddingBottom;
        int i10 = 0;
        int i11 = paddingTop;
        int i12 = paddingLeft;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                C0623h hVar = (C0623h) childAt.getLayoutParams();
                if (hVar.isDecor) {
                    int i14 = hVar.gravity;
                    int i15 = i14 & 7;
                    int i16 = i14 & 112;
                    if (i15 == 1) {
                        i5 = Math.max((i7 - childAt.getMeasuredWidth()) / 2, i12);
                    } else if (i15 == 3) {
                        i5 = i12;
                        i12 = childAt.getMeasuredWidth() + i12;
                    } else if (i15 != 5) {
                        i5 = i12;
                    } else {
                        i5 = (i7 - paddingRight) - childAt.getMeasuredWidth();
                        paddingRight += childAt.getMeasuredWidth();
                    }
                    if (i16 == 16) {
                        i6 = Math.max((i8 - childAt.getMeasuredHeight()) / 2, i11);
                    } else if (i16 == 48) {
                        i6 = i11;
                        i11 = childAt.getMeasuredHeight() + i11;
                    } else if (i16 != 80) {
                        i6 = i11;
                    } else {
                        i6 = (i8 - i9) - childAt.getMeasuredHeight();
                        i9 += childAt.getMeasuredHeight();
                    }
                    int i17 = i5 + scrollX;
                    childAt.layout(i17, i6, childAt.getMeasuredWidth() + i17, childAt.getMeasuredHeight() + i6);
                    i10++;
                }
            }
        }
        int i18 = (i7 - i12) - paddingRight;
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt2 = getChildAt(i19);
            if (childAt2.getVisibility() != 8) {
                C0623h hVar2 = (C0623h) childAt2.getLayoutParams();
                if (!hVar2.isDecor && (infoForChild = infoForChild(childAt2)) != null) {
                    float f = (float) i18;
                    int i20 = ((int) (infoForChild.offset * f)) + i12;
                    if (hVar2.needsMeasure) {
                        hVar2.needsMeasure = false;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (f * hVar2.widthFactor), 1073741824), View.MeasureSpec.makeMeasureSpec((i8 - i11) - i9, 1073741824));
                    }
                    childAt2.layout(i20, i11, childAt2.getMeasuredWidth() + i20, childAt2.getMeasuredHeight() + i11);
                }
            }
        }
        this.f711Qi = i11;
        this.f712Ri = i8 - i9;
        this.f731jj = i10;
        if (this.f729hj) {
            z2 = false;
            m993a(this.f704Ji, false, 0, false);
        } else {
            z2 = false;
        }
        this.f729hj = z2;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        C0623h hVar;
        C0623h hVar2;
        int i3;
        setMeasuredDimension(ViewGroup.getDefaultSize(0, i), ViewGroup.getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.f719Yi = Math.min(measuredWidth / 10, this.f718Xi);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i4 = measuredHeight;
        int i5 = paddingLeft;
        int i6 = 0;
        while (true) {
            boolean z = true;
            int i7 = 1073741824;
            if (i6 >= childCount) {
                break;
            }
            View childAt = getChildAt(i6);
            if (!(childAt.getVisibility() == 8 || (hVar2 = (C0623h) childAt.getLayoutParams()) == null || !hVar2.isDecor)) {
                int i8 = hVar2.gravity;
                int i9 = i8 & 7;
                int i10 = i8 & 112;
                boolean z2 = i10 == 48 || i10 == 80;
                if (!(i9 == 3 || i9 == 5)) {
                    z = false;
                }
                int i11 = RtlSpacingHelper.UNDEFINED;
                if (z2) {
                    i3 = Integer.MIN_VALUE;
                    i11 = 1073741824;
                } else {
                    i3 = z ? 1073741824 : Integer.MIN_VALUE;
                }
                int i12 = hVar2.width;
                if (i12 != -2) {
                    if (i12 == -1) {
                        i12 = i5;
                    }
                    i11 = 1073741824;
                } else {
                    i12 = i5;
                }
                int i13 = hVar2.height;
                if (i13 == -2) {
                    i13 = i4;
                    i7 = i3;
                } else if (i13 == -1) {
                    i13 = i4;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i12, i11), View.MeasureSpec.makeMeasureSpec(i13, i7));
                if (z2) {
                    i4 -= childAt.getMeasuredHeight();
                } else if (z) {
                    i5 -= childAt.getMeasuredWidth();
                }
            }
            i6++;
        }
        View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
        this.mInLayout = true;
        populate();
        this.mInLayout = false;
        int childCount2 = getChildCount();
        for (int i14 = 0; i14 < childCount2; i14++) {
            View childAt2 = getChildAt(i14);
            if (childAt2.getVisibility() != 8 && ((hVar = (C0623h) childAt2.getLayoutParams()) == null || !hVar.isDecor)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (((float) i5) * hVar.widthFactor), 1073741824), makeMeasureSpec);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPageScrolled(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.f731jj
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L_0x006d
            int r0 = r12.getScrollX()
            int r3 = r12.getPaddingLeft()
            int r4 = r12.getPaddingRight()
            int r5 = r12.getWidth()
            int r6 = r12.getChildCount()
            r7 = r4
            r4 = r3
            r3 = r1
        L_0x001d:
            if (r3 >= r6) goto L_0x006d
            android.view.View r8 = r12.getChildAt(r3)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            androidx.viewpager.widget.h r9 = (androidx.viewpager.widget.C0623h) r9
            boolean r10 = r9.isDecor
            if (r10 != 0) goto L_0x002e
            goto L_0x006a
        L_0x002e:
            int r9 = r9.gravity
            r9 = r9 & 7
            if (r9 == r2) goto L_0x004f
            r10 = 3
            if (r9 == r10) goto L_0x0049
            r10 = 5
            if (r9 == r10) goto L_0x003c
            r9 = r4
            goto L_0x005e
        L_0x003c:
            int r9 = r5 - r7
            int r10 = r8.getMeasuredWidth()
            int r9 = r9 - r10
            int r10 = r8.getMeasuredWidth()
            int r7 = r7 + r10
            goto L_0x005b
        L_0x0049:
            int r9 = r8.getWidth()
            int r9 = r9 + r4
            goto L_0x005e
        L_0x004f:
            int r9 = r8.getMeasuredWidth()
            int r9 = r5 - r9
            int r9 = r9 / 2
            int r9 = java.lang.Math.max(r9, r4)
        L_0x005b:
            r11 = r9
            r9 = r4
            r4 = r11
        L_0x005e:
            int r4 = r4 + r0
            int r10 = r8.getLeft()
            int r4 = r4 - r10
            if (r4 == 0) goto L_0x0069
            r8.offsetLeftAndRight(r4)
        L_0x0069:
            r4 = r9
        L_0x006a:
            int r3 = r3 + 1
            goto L_0x001d
        L_0x006d:
            androidx.viewpager.widget.k r0 = r12.f734lj
            if (r0 == 0) goto L_0x0074
            r0.onPageScrolled(r13, r14, r15)
        L_0x0074:
            java.util.List r0 = r12.f732kj
            if (r0 == 0) goto L_0x008f
            int r0 = r0.size()
            r3 = r1
        L_0x007d:
            if (r3 >= r0) goto L_0x008f
            java.util.List r4 = r12.f732kj
            java.lang.Object r4 = r4.get(r3)
            androidx.viewpager.widget.k r4 = (androidx.viewpager.widget.C0626k) r4
            if (r4 == 0) goto L_0x008c
            r4.onPageScrolled(r13, r14, r15)
        L_0x008c:
            int r3 = r3 + 1
            goto L_0x007d
        L_0x008f:
            androidx.viewpager.widget.k r0 = r12.f735mj
            if (r0 == 0) goto L_0x0096
            r0.onPageScrolled(r13, r14, r15)
        L_0x0096:
            androidx.viewpager.widget.l r13 = r12.f737oj
            if (r13 == 0) goto L_0x00c7
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L_0x00a2:
            if (r1 >= r14) goto L_0x00c7
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            androidx.viewpager.widget.h r0 = (androidx.viewpager.widget.C0623h) r0
            boolean r0 = r0.isDecor
            if (r0 == 0) goto L_0x00b3
            goto L_0x00c4
        L_0x00b3:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.m996jn()
            float r3 = (float) r3
            float r0 = r0 / r3
            androidx.viewpager.widget.l r3 = r12.f737oj
            r3.transformPage(r15, r0)
        L_0x00c4:
            int r1 = r1 + 1
            goto L_0x00a2
        L_0x00c7:
            r12.f730ij = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onPageScrolled(int, float, int):void");
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3;
        C0622g infoForChild;
        int childCount = getChildCount();
        int i4 = -1;
        if ((i & 2) != 0) {
            i4 = childCount;
            i3 = 0;
            i2 = 1;
        } else {
            i3 = childCount - 1;
            i2 = -1;
        }
        while (i3 != i4) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.position == this.f704Ji && childAt.requestFocus(i, rect)) {
                return true;
            }
            i3 += i2;
        }
        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        C0616a aVar = this.mAdapter;
        if (aVar != null) {
            aVar.restoreState(savedState.adapterState, savedState.loader);
            setCurrentItemInternal(savedState.position, false, true);
            return;
        }
        this.f705Ki = savedState.position;
        this.f706Li = savedState.adapterState;
        this.f707Mi = savedState.loader;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.position = this.f704Ji;
        C0616a aVar = this.mAdapter;
        if (aVar != null) {
            savedState.adapterState = aVar.saveState();
        }
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            int i5 = this.f709Oi;
            m994c(i, i3, i5, i5);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        C0616a aVar;
        if (this.f726ej) {
            return true;
        }
        boolean z = false;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || (aVar = this.mAdapter) == null || aVar.getCount() == 0) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.mScroller.abortAnimation();
            this.f715Ui = false;
            populate();
            float x = motionEvent.getX();
            this.f722aj = x;
            this.f721_i = x;
            float y = motionEvent.getY();
            this.f723bj = y;
            this.mLastMotionY = y;
            this.mActivePointerId = motionEvent.getPointerId(0);
        } else if (action != 1) {
            if (action == 2) {
                if (!this.mIsBeingDragged) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (findPointerIndex == -1) {
                        z = m998ln();
                    } else {
                        float x2 = motionEvent.getX(findPointerIndex);
                        float abs = Math.abs(x2 - this.f721_i);
                        float y2 = motionEvent.getY(findPointerIndex);
                        float abs2 = Math.abs(y2 - this.mLastMotionY);
                        if (abs > ((float) this.mTouchSlop) && abs > abs2) {
                            this.mIsBeingDragged = true;
                            m1003za(true);
                            float f = this.f722aj;
                            this.f721_i = x2 - f > 0.0f ? f + ((float) this.mTouchSlop) : f - ((float) this.mTouchSlop);
                            this.mLastMotionY = y2;
                            setScrollState(1);
                            setScrollingCacheEnabled(true);
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                    }
                }
                if (this.mIsBeingDragged) {
                    z = false | m995d(motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId)));
                }
            } else if (action != 3) {
                if (action == 5) {
                    int actionIndex = motionEvent.getActionIndex();
                    this.f721_i = motionEvent.getX(actionIndex);
                    this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                } else if (action == 6) {
                    onSecondaryPointerUp(motionEvent);
                    this.f721_i = motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId));
                }
            } else if (this.mIsBeingDragged) {
                m993a(this.f704Ji, true, 0, false);
                z = m998ln();
            }
        } else if (this.mIsBeingDragged) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
            int xVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
            this.f715Ui = true;
            int jn = m996jn();
            int scrollX = getScrollX();
            C0622g kn = m997kn();
            float f2 = (float) jn;
            float f3 = ((float) this.f709Oi) / f2;
            int i = kn.position;
            float f4 = ((((float) scrollX) / f2) - kn.offset) / (kn.widthFactor + f3);
            if (Math.abs((int) (motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId)) - this.f722aj)) <= this.f724cj || Math.abs(xVelocity) <= this.mMinimumVelocity) {
                i += (int) (f4 + (i >= this.f704Ji ? 0.4f : 0.6f));
            } else if (xVelocity <= 0) {
                i++;
            }
            if (this.mItems.size() > 0) {
                ArrayList arrayList = this.mItems;
                i = Math.max(((C0622g) this.mItems.get(0)).position, Math.min(i, ((C0622g) arrayList.get(arrayList.size() - 1)).position));
            }
            setCurrentItemInternal(i, true, true, xVelocity);
            z = m998ln();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean pageLeft() {
        int i = this.f704Ji;
        if (i <= 0) {
            return false;
        }
        setCurrentItem(i - 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean pageRight() {
        C0616a aVar = this.mAdapter;
        if (aVar == null || this.f704Ji >= aVar.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.f704Ji + 1, true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void populate() {
        populate(this.f704Ji);
    }

    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setCurrentItem(int i) {
        this.f715Ui = false;
        setCurrentItemInternal(i, !this.f729hj, false);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i, boolean z, boolean z2) {
        setCurrentItemInternal(i, z, z2, 0);
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i + " too small; defaulting to " + 1);
            i = 1;
        }
        if (i != this.f716Vi) {
            this.f716Vi = i;
            populate();
        }
    }

    public void setPageMargin(int i) {
        int i2 = this.f709Oi;
        this.f709Oi = i;
        int width = getWidth();
        m994c(width, width, i, i2);
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public void setScrollState(int i) {
        if (this.f733li != i) {
            this.f733li = i;
            if (this.f737oj != null) {
                boolean z = i != 0;
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    getChildAt(i2).setLayerType(z ? this.f738pj : 0, (Paint) null);
                }
            }
            C0626k kVar = this.f734lj;
            if (kVar != null) {
                kVar.onPageScrollStateChanged(i);
            }
            List list = this.f732kj;
            if (list != null) {
                int size = list.size();
                for (int i3 = 0; i3 < size; i3++) {
                    C0626k kVar2 = (C0626k) this.f732kj.get(i3);
                    if (kVar2 != null) {
                        kVar2.onPageScrollStateChanged(i);
                    }
                }
            }
            C0626k kVar3 = this.f735mj;
            if (kVar3 != null) {
                kVar3.onPageScrollStateChanged(i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void smoothScrollTo(int i, int i2, int i3) {
        int i4;
        int i5;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.mScroller;
        if (scroller != null && !scroller.isFinished()) {
            i4 = this.f708Ni ? this.mScroller.getCurrX() : this.mScroller.getStartX();
            this.mScroller.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            i4 = getScrollX();
        }
        int i6 = i4;
        int scrollY = getScrollY();
        int i7 = i - i6;
        int i8 = i2 - scrollY;
        if (i7 == 0 && i8 == 0) {
            m1002ya(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int jn = m996jn();
        int i9 = jn / 2;
        float f = (float) jn;
        float f2 = (float) i9;
        float distanceInfluenceForSnapDuration = (distanceInfluenceForSnapDuration(Math.min(1.0f, (((float) Math.abs(i7)) * 1.0f) / f)) * f2) + f2;
        int abs = Math.abs(i3);
        if (abs > 0) {
            i5 = Math.round(Math.abs(distanceInfluenceForSnapDuration / ((float) abs)) * 1000.0f) * 4;
        } else {
            i5 = (int) (((((float) Math.abs(i7)) / ((this.mAdapter.getPageWidth(this.f704Ji) * f) + ((float) this.f709Oi))) + 1.0f) * 100.0f);
        }
        int min = Math.min(i5, 600);
        this.f708Ni = false;
        this.mScroller.startScroll(i6, scrollY, i7, i8, min);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f710Pi;
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0623h(getContext(), attributeSet);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005b, code lost:
        if (r5 == r6) goto L_0x0062;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void populate(int r15) {
        /*
            r14 = this;
            int r0 = r14.f704Ji
            if (r0 == r15) goto L_0x000b
            androidx.viewpager.widget.g r0 = r14.infoForPosition(r0)
            r14.f704Ji = r15
            goto L_0x000c
        L_0x000b:
            r0 = 0
        L_0x000c:
            androidx.viewpager.widget.a r15 = r14.mAdapter
            if (r15 != 0) goto L_0x0014
            r14.m1000mn()
            return
        L_0x0014:
            boolean r15 = r14.f715Ui
            if (r15 == 0) goto L_0x001c
            r14.m1000mn()
            return
        L_0x001c:
            android.os.IBinder r15 = r14.getWindowToken()
            if (r15 != 0) goto L_0x0023
            return
        L_0x0023:
            androidx.viewpager.widget.a r15 = r14.mAdapter
            r15.startUpdate((android.view.ViewGroup) r14)
            int r15 = r14.f716Vi
            int r1 = r14.f704Ji
            int r1 = r1 - r15
            r2 = 0
            int r1 = java.lang.Math.max(r2, r1)
            androidx.viewpager.widget.a r3 = r14.mAdapter
            int r3 = r3.getCount()
            int r4 = r3 + -1
            int r5 = r14.f704Ji
            int r5 = r5 + r15
            int r15 = java.lang.Math.min(r4, r5)
            int r4 = r14.f702Hi
            if (r3 != r4) goto L_0x033e
        L_0x0045:
            java.util.ArrayList r4 = r14.mItems
            int r4 = r4.size()
            if (r2 >= r4) goto L_0x0061
            java.util.ArrayList r4 = r14.mItems
            java.lang.Object r4 = r4.get(r2)
            androidx.viewpager.widget.g r4 = (androidx.viewpager.widget.C0622g) r4
            int r5 = r4.position
            int r6 = r14.f704Ji
            if (r5 < r6) goto L_0x005e
            if (r5 != r6) goto L_0x0061
            goto L_0x0062
        L_0x005e:
            int r2 = r2 + 1
            goto L_0x0045
        L_0x0061:
            r4 = 0
        L_0x0062:
            if (r4 != 0) goto L_0x006c
            if (r3 <= 0) goto L_0x006c
            int r4 = r14.f704Ji
            androidx.viewpager.widget.g r4 = r14.addNewItem(r4, r2)
        L_0x006c:
            r5 = 0
            if (r4 == 0) goto L_0x02cb
            int r6 = r2 + -1
            if (r6 < 0) goto L_0x007c
            java.util.ArrayList r7 = r14.mItems
            java.lang.Object r7 = r7.get(r6)
            androidx.viewpager.widget.g r7 = (androidx.viewpager.widget.C0622g) r7
            goto L_0x007d
        L_0x007c:
            r7 = 0
        L_0x007d:
            int r8 = r14.m996jn()
            r9 = 1073741824(0x40000000, float:2.0)
            if (r8 > 0) goto L_0x0087
            r10 = r5
            goto L_0x0093
        L_0x0087:
            float r10 = r4.widthFactor
            float r10 = r9 - r10
            int r11 = r14.getPaddingLeft()
            float r11 = (float) r11
            float r12 = (float) r8
            float r11 = r11 / r12
            float r10 = r10 + r11
        L_0x0093:
            int r11 = r14.f704Ji
            int r11 = r11 + -1
            r12 = r6
            r6 = r2
            r2 = r5
        L_0x009a:
            if (r11 < 0) goto L_0x00f8
            int r13 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r13 < 0) goto L_0x00c8
            if (r11 >= r1) goto L_0x00c8
            if (r7 != 0) goto L_0x00a5
            goto L_0x00f8
        L_0x00a5:
            int r13 = r7.position
            if (r11 != r13) goto L_0x00f5
            boolean r13 = r7.scrolling
            if (r13 != 0) goto L_0x00f5
            java.util.ArrayList r13 = r14.mItems
            r13.remove(r12)
            androidx.viewpager.widget.a r13 = r14.mAdapter
            java.lang.Object r7 = r7.object
            r13.destroyItem((android.view.ViewGroup) r14, (int) r11, (java.lang.Object) r7)
            int r12 = r12 + -1
            int r6 = r6 + -1
            if (r12 < 0) goto L_0x00f4
            java.util.ArrayList r7 = r14.mItems
            java.lang.Object r7 = r7.get(r12)
            androidx.viewpager.widget.g r7 = (androidx.viewpager.widget.C0622g) r7
            goto L_0x00f5
        L_0x00c8:
            if (r7 == 0) goto L_0x00de
            int r13 = r7.position
            if (r11 != r13) goto L_0x00de
            float r7 = r7.widthFactor
            float r2 = r2 + r7
            int r12 = r12 + -1
            if (r12 < 0) goto L_0x00f4
            java.util.ArrayList r7 = r14.mItems
            java.lang.Object r7 = r7.get(r12)
            androidx.viewpager.widget.g r7 = (androidx.viewpager.widget.C0622g) r7
            goto L_0x00f5
        L_0x00de:
            int r7 = r12 + 1
            androidx.viewpager.widget.g r7 = r14.addNewItem(r11, r7)
            float r7 = r7.widthFactor
            float r2 = r2 + r7
            int r6 = r6 + 1
            if (r12 < 0) goto L_0x00f4
            java.util.ArrayList r7 = r14.mItems
            java.lang.Object r7 = r7.get(r12)
            androidx.viewpager.widget.g r7 = (androidx.viewpager.widget.C0622g) r7
            goto L_0x00f5
        L_0x00f4:
            r7 = 0
        L_0x00f5:
            int r11 = r11 + -1
            goto L_0x009a
        L_0x00f8:
            float r1 = r4.widthFactor
            int r2 = r6 + 1
            int r7 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r7 >= 0) goto L_0x018e
            java.util.ArrayList r7 = r14.mItems
            int r7 = r7.size()
            if (r2 >= r7) goto L_0x0111
            java.util.ArrayList r7 = r14.mItems
            java.lang.Object r7 = r7.get(r2)
            androidx.viewpager.widget.g r7 = (androidx.viewpager.widget.C0622g) r7
            goto L_0x0112
        L_0x0111:
            r7 = 0
        L_0x0112:
            if (r8 > 0) goto L_0x0116
            r8 = r5
            goto L_0x011f
        L_0x0116:
            int r10 = r14.getPaddingRight()
            float r10 = (float) r10
            float r8 = (float) r8
            float r10 = r10 / r8
            float r8 = r10 + r9
        L_0x011f:
            int r9 = r14.f704Ji
            int r9 = r9 + 1
            r10 = r2
        L_0x0124:
            if (r9 >= r3) goto L_0x018e
            int r11 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r11 < 0) goto L_0x0154
            if (r9 <= r15) goto L_0x0154
            if (r7 != 0) goto L_0x012f
            goto L_0x018e
        L_0x012f:
            int r11 = r7.position
            if (r9 != r11) goto L_0x018b
            boolean r11 = r7.scrolling
            if (r11 != 0) goto L_0x018b
            java.util.ArrayList r11 = r14.mItems
            r11.remove(r10)
            androidx.viewpager.widget.a r11 = r14.mAdapter
            java.lang.Object r7 = r7.object
            r11.destroyItem((android.view.ViewGroup) r14, (int) r9, (java.lang.Object) r7)
            java.util.ArrayList r7 = r14.mItems
            int r7 = r7.size()
            if (r10 >= r7) goto L_0x018a
            java.util.ArrayList r7 = r14.mItems
            java.lang.Object r7 = r7.get(r10)
            androidx.viewpager.widget.g r7 = (androidx.viewpager.widget.C0622g) r7
            goto L_0x018b
        L_0x0154:
            if (r7 == 0) goto L_0x0170
            int r11 = r7.position
            if (r9 != r11) goto L_0x0170
            float r7 = r7.widthFactor
            float r1 = r1 + r7
            int r10 = r10 + 1
            java.util.ArrayList r7 = r14.mItems
            int r7 = r7.size()
            if (r10 >= r7) goto L_0x018a
            java.util.ArrayList r7 = r14.mItems
            java.lang.Object r7 = r7.get(r10)
            androidx.viewpager.widget.g r7 = (androidx.viewpager.widget.C0622g) r7
            goto L_0x018b
        L_0x0170:
            androidx.viewpager.widget.g r7 = r14.addNewItem(r9, r10)
            int r10 = r10 + 1
            float r7 = r7.widthFactor
            float r1 = r1 + r7
            java.util.ArrayList r7 = r14.mItems
            int r7 = r7.size()
            if (r10 >= r7) goto L_0x018a
            java.util.ArrayList r7 = r14.mItems
            java.lang.Object r7 = r7.get(r10)
            androidx.viewpager.widget.g r7 = (androidx.viewpager.widget.C0622g) r7
            goto L_0x018b
        L_0x018a:
            r7 = 0
        L_0x018b:
            int r9 = r9 + 1
            goto L_0x0124
        L_0x018e:
            androidx.viewpager.widget.a r15 = r14.mAdapter
            int r15 = r15.getCount()
            int r1 = r14.m996jn()
            if (r1 <= 0) goto L_0x01a1
            int r3 = r14.f709Oi
            float r3 = (float) r3
            float r1 = (float) r1
            float r1 = r3 / r1
            goto L_0x01a2
        L_0x01a1:
            r1 = r5
        L_0x01a2:
            if (r0 == 0) goto L_0x0239
            int r3 = r0.position
            int r7 = r4.position
            if (r3 >= r7) goto L_0x01f6
            float r7 = r0.offset
            float r0 = r0.widthFactor
            float r7 = r7 + r0
            float r7 = r7 + r1
            r0 = 0
        L_0x01b1:
            int r3 = r3 + 1
            int r8 = r4.position
            if (r3 > r8) goto L_0x0239
            java.util.ArrayList r8 = r14.mItems
            int r8 = r8.size()
            if (r0 >= r8) goto L_0x0239
            java.util.ArrayList r8 = r14.mItems
            java.lang.Object r8 = r8.get(r0)
            androidx.viewpager.widget.g r8 = (androidx.viewpager.widget.C0622g) r8
        L_0x01c7:
            int r9 = r8.position
            if (r3 <= r9) goto L_0x01e0
            java.util.ArrayList r9 = r14.mItems
            int r9 = r9.size()
            int r9 = r9 + -1
            if (r0 >= r9) goto L_0x01e0
            int r0 = r0 + 1
            java.util.ArrayList r8 = r14.mItems
            java.lang.Object r8 = r8.get(r0)
            androidx.viewpager.widget.g r8 = (androidx.viewpager.widget.C0622g) r8
            goto L_0x01c7
        L_0x01e0:
            int r9 = r8.position
            if (r3 >= r9) goto L_0x01ef
            androidx.viewpager.widget.a r9 = r14.mAdapter
            float r9 = r9.getPageWidth(r3)
            float r9 = r9 + r1
            float r7 = r7 + r9
            int r3 = r3 + 1
            goto L_0x01e0
        L_0x01ef:
            r8.offset = r7
            float r8 = r8.widthFactor
            float r8 = r8 + r1
            float r7 = r7 + r8
            goto L_0x01b1
        L_0x01f6:
            if (r3 <= r7) goto L_0x0239
            java.util.ArrayList r7 = r14.mItems
            int r7 = r7.size()
            int r7 = r7 + -1
            float r0 = r0.offset
        L_0x0202:
            int r3 = r3 + -1
            int r8 = r4.position
            if (r3 < r8) goto L_0x0239
            if (r7 < 0) goto L_0x0239
            java.util.ArrayList r8 = r14.mItems
            java.lang.Object r8 = r8.get(r7)
            androidx.viewpager.widget.g r8 = (androidx.viewpager.widget.C0622g) r8
        L_0x0212:
            int r9 = r8.position
            if (r3 >= r9) goto L_0x0223
            if (r7 <= 0) goto L_0x0223
            int r7 = r7 + -1
            java.util.ArrayList r8 = r14.mItems
            java.lang.Object r8 = r8.get(r7)
            androidx.viewpager.widget.g r8 = (androidx.viewpager.widget.C0622g) r8
            goto L_0x0212
        L_0x0223:
            int r9 = r8.position
            if (r3 <= r9) goto L_0x0232
            androidx.viewpager.widget.a r9 = r14.mAdapter
            float r9 = r9.getPageWidth(r3)
            float r9 = r9 + r1
            float r0 = r0 - r9
            int r3 = r3 + -1
            goto L_0x0223
        L_0x0232:
            float r9 = r8.widthFactor
            float r9 = r9 + r1
            float r0 = r0 - r9
            r8.offset = r0
            goto L_0x0202
        L_0x0239:
            java.util.ArrayList r0 = r14.mItems
            int r0 = r0.size()
            float r3 = r4.offset
            int r7 = r4.position
            int r8 = r7 + -1
            if (r7 != 0) goto L_0x0249
            r7 = r3
            goto L_0x024c
        L_0x0249:
            r7 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
        L_0x024c:
            r14.f713Si = r7
            int r7 = r4.position
            int r15 = r15 + -1
            r9 = 1065353216(0x3f800000, float:1.0)
            if (r7 != r15) goto L_0x025d
            float r7 = r4.offset
            float r10 = r4.widthFactor
            float r7 = r7 + r10
            float r7 = r7 - r9
            goto L_0x0260
        L_0x025d:
            r7 = 2139095039(0x7f7fffff, float:3.4028235E38)
        L_0x0260:
            r14.f714Ti = r7
            int r6 = r6 + -1
        L_0x0264:
            if (r6 < 0) goto L_0x028d
            java.util.ArrayList r7 = r14.mItems
            java.lang.Object r7 = r7.get(r6)
            androidx.viewpager.widget.g r7 = (androidx.viewpager.widget.C0622g) r7
        L_0x026e:
            int r10 = r7.position
            if (r8 <= r10) goto L_0x027e
            androidx.viewpager.widget.a r10 = r14.mAdapter
            int r11 = r8 + -1
            float r8 = r10.getPageWidth(r8)
            float r8 = r8 + r1
            float r3 = r3 - r8
            r8 = r11
            goto L_0x026e
        L_0x027e:
            float r11 = r7.widthFactor
            float r11 = r11 + r1
            float r3 = r3 - r11
            r7.offset = r3
            if (r10 != 0) goto L_0x0288
            r14.f713Si = r3
        L_0x0288:
            int r6 = r6 + -1
            int r8 = r8 + -1
            goto L_0x0264
        L_0x028d:
            float r3 = r4.offset
            float r6 = r4.widthFactor
            float r3 = r3 + r6
            float r3 = r3 + r1
            int r6 = r4.position
        L_0x0295:
            int r6 = r6 + 1
            if (r2 >= r0) goto L_0x02c2
            java.util.ArrayList r7 = r14.mItems
            java.lang.Object r7 = r7.get(r2)
            androidx.viewpager.widget.g r7 = (androidx.viewpager.widget.C0622g) r7
        L_0x02a1:
            int r8 = r7.position
            if (r6 >= r8) goto L_0x02b1
            androidx.viewpager.widget.a r8 = r14.mAdapter
            int r10 = r6 + 1
            float r6 = r8.getPageWidth(r6)
            float r6 = r6 + r1
            float r3 = r3 + r6
            r6 = r10
            goto L_0x02a1
        L_0x02b1:
            if (r8 != r15) goto L_0x02b9
            float r8 = r7.widthFactor
            float r8 = r8 + r3
            float r8 = r8 - r9
            r14.f714Ti = r8
        L_0x02b9:
            r7.offset = r3
            float r7 = r7.widthFactor
            float r7 = r7 + r1
            float r3 = r3 + r7
            int r2 = r2 + 1
            goto L_0x0295
        L_0x02c2:
            androidx.viewpager.widget.a r15 = r14.mAdapter
            int r0 = r14.f704Ji
            java.lang.Object r1 = r4.object
            r15.setPrimaryItem((android.view.ViewGroup) r14, (int) r0, (java.lang.Object) r1)
        L_0x02cb:
            androidx.viewpager.widget.a r15 = r14.mAdapter
            r15.finishUpdate((android.view.ViewGroup) r14)
            int r15 = r14.getChildCount()
            r0 = 0
        L_0x02d5:
            if (r0 >= r15) goto L_0x02fe
            android.view.View r1 = r14.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r2 = r1.getLayoutParams()
            androidx.viewpager.widget.h r2 = (androidx.viewpager.widget.C0623h) r2
            r2.childIndex = r0
            boolean r3 = r2.isDecor
            if (r3 != 0) goto L_0x02fb
            float r3 = r2.widthFactor
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x02fb
            androidx.viewpager.widget.g r1 = r14.infoForChild(r1)
            if (r1 == 0) goto L_0x02fb
            float r3 = r1.widthFactor
            r2.widthFactor = r3
            int r1 = r1.position
            r2.position = r1
        L_0x02fb:
            int r0 = r0 + 1
            goto L_0x02d5
        L_0x02fe:
            r14.m1000mn()
            boolean r15 = r14.hasFocus()
            if (r15 == 0) goto L_0x033d
            android.view.View r15 = r14.findFocus()
            if (r15 == 0) goto L_0x0312
            androidx.viewpager.widget.g r15 = r14.infoForAnyChild(r15)
            goto L_0x0313
        L_0x0312:
            r15 = 0
        L_0x0313:
            if (r15 == 0) goto L_0x031b
            int r15 = r15.position
            int r0 = r14.f704Ji
            if (r15 == r0) goto L_0x033d
        L_0x031b:
            r15 = 0
        L_0x031c:
            int r0 = r14.getChildCount()
            if (r15 >= r0) goto L_0x033d
            android.view.View r0 = r14.getChildAt(r15)
            androidx.viewpager.widget.g r1 = r14.infoForChild(r0)
            if (r1 == 0) goto L_0x033a
            int r1 = r1.position
            int r2 = r14.f704Ji
            if (r1 != r2) goto L_0x033a
            r1 = 2
            boolean r0 = r0.requestFocus(r1)
            if (r0 == 0) goto L_0x033a
            goto L_0x033d
        L_0x033a:
            int r15 = r15 + 1
            goto L_0x031c
        L_0x033d:
            return
        L_0x033e:
            android.content.res.Resources r15 = r14.getResources()     // Catch:{ NotFoundException -> 0x034b }
            int r0 = r14.getId()     // Catch:{ NotFoundException -> 0x034b }
            java.lang.String r15 = r15.getResourceName(r0)     // Catch:{ NotFoundException -> 0x034b }
            goto L_0x0353
        L_0x034b:
            int r15 = r14.getId()
            java.lang.String r15 = java.lang.Integer.toHexString(r15)
        L_0x0353:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: "
            java.lang.StringBuilder r1 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r1)
            int r2 = r14.f702Hi
            r1.append(r2)
            java.lang.String r2 = ", found: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r2 = " Pager id: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r15 = " Pager class: "
            r1.append(r15)
            java.lang.Class r15 = r14.getClass()
            r1.append(r15)
            java.lang.String r15 = " Problematic adapter: "
            r1.append(r15)
            androidx.viewpager.widget.a r14 = r14.mAdapter
            java.lang.Class r14 = r14.getClass()
            r1.append(r14)
            java.lang.String r14 = r1.toString()
            r0.<init>(r14)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.populate(int):void");
    }

    /* access modifiers changed from: package-private */
    public void setCurrentItemInternal(int i, boolean z, boolean z2, int i2) {
        C0616a aVar = this.mAdapter;
        if (aVar == null || aVar.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z2 || this.f704Ji != i || this.mItems.size() == 0) {
            boolean z3 = true;
            if (i < 0) {
                i = 0;
            } else if (i >= this.mAdapter.getCount()) {
                i = this.mAdapter.getCount() - 1;
            }
            int i3 = this.f716Vi;
            int i4 = this.f704Ji;
            if (i > i4 + i3 || i < i4 - i3) {
                for (int i5 = 0; i5 < this.mItems.size(); i5++) {
                    ((C0622g) this.mItems.get(i5)).scrolling = true;
                }
            }
            if (this.f704Ji == i) {
                z3 = false;
            }
            if (this.f729hj) {
                this.f704Ji = i;
                if (z3) {
                    m999mb(i);
                }
                requestLayout();
                return;
            }
            populate(i);
            m993a(i, z, i2, z3);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    public void setCurrentItem(int i, boolean z) {
        this.f715Ui = false;
        setCurrentItemInternal(i, z, false);
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo5329Xb();
    }

    /* renamed from: a */
    private void m993a(int i, boolean z, int i2, boolean z2) {
        int i3;
        C0622g infoForPosition = infoForPosition(i);
        if (infoForPosition != null) {
            i3 = (int) (Math.max(this.f713Si, Math.min(infoForPosition.offset, this.f714Ti)) * ((float) m996jn()));
        } else {
            i3 = 0;
        }
        if (z) {
            smoothScrollTo(i3, 0, i2);
            if (z2) {
                m999mb(i);
                return;
            }
            return;
        }
        if (z2) {
            m999mb(i);
        }
        m1002ya(false);
        scrollTo(i3, 0);
        m1001nb(i3);
    }

    @Deprecated
    /* renamed from: a */
    public void mo5331a(C0626k kVar) {
        this.f734lj = kVar;
    }

    /* renamed from: a */
    public void mo5332a(boolean z, C0627l lVar) {
        mo5333a(z, lVar, 2);
    }

    /* renamed from: a */
    public void mo5333a(boolean z, C0627l lVar, int i) {
        int i2 = 1;
        boolean z2 = lVar != null;
        boolean z3 = z2 != (this.f737oj != null);
        this.f737oj = lVar;
        setChildrenDrawingOrderEnabled(z2);
        if (z2) {
            if (z) {
                i2 = 2;
            }
            this.f739qj = i2;
            this.f738pj = i;
        } else {
            this.f739qj = 0;
        }
        if (z3) {
            populate();
        }
    }

    /* renamed from: a */
    private Rect m992a(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left = viewGroup.getLeft() + rect.left;
            rect.right = viewGroup.getRight() + rect.right;
            rect.top = viewGroup.getTop() + rect.top;
            rect.bottom = viewGroup.getBottom() + rect.bottom;
            parent = viewGroup.getParent();
        }
        return rect;
    }
}
