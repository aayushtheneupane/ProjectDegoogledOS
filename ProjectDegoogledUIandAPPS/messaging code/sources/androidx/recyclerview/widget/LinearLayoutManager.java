package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public class LinearLayoutManager extends C0558ca implements C0580na {

    /* renamed from: Vr */
    private boolean f550Vr;

    /* renamed from: Wr */
    private boolean f551Wr = false;

    /* renamed from: Xr */
    private boolean f552Xr = false;

    /* renamed from: Yr */
    private boolean f553Yr = true;

    /* renamed from: Zr */
    private boolean f554Zr;

    /* renamed from: _r */
    private final C0523B f555_r = new C0523B();

    /* renamed from: as */
    private int f556as = 2;
    final C0521A mAnchorInfo = new C0521A();
    private C0525C mLayoutState;
    int mOrientation = 1;
    C0536I mOrientationHelper;
    SavedState mPendingSavedState = null;
    int mPendingScrollPosition = -1;
    int mPendingScrollPositionOffset = RtlSpacingHelper.UNDEFINED;
    boolean mShouldReverseLayout = false;

    /* renamed from: yi */
    private int[] f557yi = new int[2];

    @SuppressLint({"BanParcelableUsage"})
    public class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR = new C0527D();
        boolean mAnchorLayoutFromEnd;
        int mAnchorOffset;
        int mAnchorPosition;

        public SavedState() {
        }

        public int describeContents() {
            return 0;
        }

        /* access modifiers changed from: package-private */
        public boolean hasValidAnchor() {
            return this.mAnchorPosition >= 0;
        }

        /* access modifiers changed from: package-private */
        public void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
            parcel.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
        }

        SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mAnchorOffset = parcel.readInt();
            this.mAnchorLayoutFromEnd = parcel.readInt() != 1 ? false : true;
        }

        public SavedState(SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
        }
    }

    public LinearLayoutManager(Context context) {
        setOrientation(1);
        setReverseLayout(false);
    }

    /* renamed from: Kn */
    private View m633Kn() {
        return mo4772l(0, getChildCount());
    }

    /* renamed from: Ln */
    private View m634Ln() {
        return mo4772l(getChildCount() - 1, -1);
    }

    /* renamed from: Mn */
    private View m635Mn() {
        return getChildAt(this.mShouldReverseLayout ? 0 : getChildCount() - 1);
    }

    /* renamed from: Nn */
    private View m636Nn() {
        return getChildAt(this.mShouldReverseLayout ? getChildCount() - 1 : 0);
    }

    /* renamed from: On */
    private void m637On() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.f551Wr;
        } else {
            this.mShouldReverseLayout = !this.f551Wr;
        }
    }

    /* renamed from: T */
    private void m638T(int i, int i2) {
        this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - i2;
        this.mLayoutState.mItemDirection = this.mShouldReverseLayout ? -1 : 1;
        C0525C c = this.mLayoutState;
        c.mCurrentPosition = i;
        c.mLayoutDirection = 1;
        c.mOffset = i2;
        c.mScrollingOffset = RtlSpacingHelper.UNDEFINED;
    }

    /* renamed from: U */
    private void m639U(int i, int i2) {
        this.mLayoutState.mAvailable = i2 - this.mOrientationHelper.getStartAfterPadding();
        C0525C c = this.mLayoutState;
        c.mCurrentPosition = i;
        c.mItemDirection = this.mShouldReverseLayout ? 1 : -1;
        C0525C c2 = this.mLayoutState;
        c2.mLayoutDirection = -1;
        c2.mOffset = i2;
        c2.mScrollingOffset = RtlSpacingHelper.UNDEFINED;
    }

    /* renamed from: b */
    private int m644b(int i, C0572ja jaVar, C0582oa oaVar, boolean z) {
        int startAfterPadding;
        int startAfterPadding2 = i - this.mOrientationHelper.getStartAfterPadding();
        if (startAfterPadding2 <= 0) {
            return 0;
        }
        int i2 = -mo4751c(startAfterPadding2, jaVar, oaVar);
        int i3 = i + i2;
        if (!z || (startAfterPadding = i3 - this.mOrientationHelper.getStartAfterPadding()) <= 0) {
            return i2;
        }
        this.mOrientationHelper.offsetChildren(-startAfterPadding);
        return i2 - startAfterPadding;
    }

    /* renamed from: k */
    private int m647k(C0582oa oaVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        C0536I i = this.mOrientationHelper;
        View g = mo4766g(!this.f553Yr, true);
        return C0592ta.m911a(oaVar, i, g, mo4760f(!this.f553Yr, true), this, this.f553Yr);
    }

    /* renamed from: l */
    private int m648l(C0582oa oaVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        C0536I i = this.mOrientationHelper;
        View g = mo4766g(!this.f553Yr, true);
        return C0592ta.m912a(oaVar, i, g, mo4760f(!this.f553Yr, true), this, this.f553Yr, this.mShouldReverseLayout);
    }

    /* renamed from: m */
    private int m649m(C0582oa oaVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        C0536I i = this.mOrientationHelper;
        View g = mo4766g(!this.f553Yr, true);
        return C0592ta.m913b(oaVar, i, g, mo4760f(!this.f553Yr, true), this, this.f553Yr);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4717a(C0572ja jaVar, C0582oa oaVar, C0521A a, int i) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo4748a(C0582oa oaVar, int[] iArr) {
        int i;
        int j = mo4771j(oaVar);
        if (this.mLayoutState.mLayoutDirection == -1) {
            i = 0;
        } else {
            i = j;
            j = 0;
        }
        iArr[0] = j;
        iArr[1] = i;
    }

    public void assertNotInLayoutOrScroll(String str) {
        RecyclerView recyclerView;
        if (this.mPendingSavedState == null && (recyclerView = this.mRecyclerView) != null) {
            recyclerView.assertNotInLayoutOrScroll(str);
        }
    }

    /* renamed from: c */
    public int mo4752c(C0582oa oaVar) {
        return m647k(oaVar);
    }

    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        boolean z = false;
        int i2 = 1;
        if (i < getPosition(getChildAt(0))) {
            z = true;
        }
        if (z != this.mShouldReverseLayout) {
            i2 = -1;
        }
        if (this.mOrientation == 0) {
            return new PointF((float) i2, 0.0f);
        }
        return new PointF(0.0f, (float) i2);
    }

    /* access modifiers changed from: package-private */
    public int convertFocusDirectionToLayoutDirection(int i) {
        if (i == 1) {
            return (this.mOrientation != 1 && isLayoutRTL()) ? 1 : -1;
        }
        if (i == 2) {
            return (this.mOrientation != 1 && isLayoutRTL()) ? -1 : 1;
        }
        if (i != 17) {
            if (i != 33) {
                if (i != 66) {
                    if (i != 130) {
                        return RtlSpacingHelper.UNDEFINED;
                    }
                    if (this.mOrientation == 1) {
                        return 1;
                    }
                    return RtlSpacingHelper.UNDEFINED;
                } else if (this.mOrientation == 0) {
                    return 1;
                } else {
                    return RtlSpacingHelper.UNDEFINED;
                }
            } else if (this.mOrientation == 1) {
                return -1;
            } else {
                return RtlSpacingHelper.UNDEFINED;
            }
        } else if (this.mOrientation == 0) {
            return -1;
        } else {
            return RtlSpacingHelper.UNDEFINED;
        }
    }

    /* access modifiers changed from: package-private */
    public C0525C createLayoutState() {
        return new C0525C();
    }

    /* renamed from: d */
    public int mo4724d(C0582oa oaVar) {
        return m648l(oaVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x0216  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0184  */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4727e(androidx.recyclerview.widget.C0572ja r17, androidx.recyclerview.widget.C0582oa r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            androidx.recyclerview.widget.LinearLayoutManager$SavedState r3 = r0.mPendingSavedState
            r4 = -1
            if (r3 != 0) goto L_0x000f
            int r3 = r0.mPendingScrollPosition
            if (r3 == r4) goto L_0x0019
        L_0x000f:
            int r3 = r18.getItemCount()
            if (r3 != 0) goto L_0x0019
            r16.mo5041c((androidx.recyclerview.widget.C0572ja) r17)
            return
        L_0x0019:
            androidx.recyclerview.widget.LinearLayoutManager$SavedState r3 = r0.mPendingSavedState
            if (r3 == 0) goto L_0x0029
            boolean r3 = r3.hasValidAnchor()
            if (r3 == 0) goto L_0x0029
            androidx.recyclerview.widget.LinearLayoutManager$SavedState r3 = r0.mPendingSavedState
            int r3 = r3.mAnchorPosition
            r0.mPendingScrollPosition = r3
        L_0x0029:
            r16.ensureLayoutState()
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            r5 = 0
            r3.mRecycle = r5
            r16.m637On()
            android.view.View r3 = r16.getFocusedChild()
            androidx.recyclerview.widget.A r6 = r0.mAnchorInfo
            boolean r6 = r6.mValid
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r8 = 1
            if (r6 == 0) goto L_0x0073
            int r6 = r0.mPendingScrollPosition
            if (r6 != r4) goto L_0x0073
            androidx.recyclerview.widget.LinearLayoutManager$SavedState r6 = r0.mPendingSavedState
            if (r6 == 0) goto L_0x004a
            goto L_0x0073
        L_0x004a:
            if (r3 == 0) goto L_0x022a
            androidx.recyclerview.widget.I r6 = r0.mOrientationHelper
            int r6 = r6.getDecoratedStart(r3)
            androidx.recyclerview.widget.I r9 = r0.mOrientationHelper
            int r9 = r9.getEndAfterPadding()
            if (r6 >= r9) goto L_0x0068
            androidx.recyclerview.widget.I r6 = r0.mOrientationHelper
            int r6 = r6.getDecoratedEnd(r3)
            androidx.recyclerview.widget.I r9 = r0.mOrientationHelper
            int r9 = r9.getStartAfterPadding()
            if (r6 > r9) goto L_0x022a
        L_0x0068:
            androidx.recyclerview.widget.A r6 = r0.mAnchorInfo
            int r9 = r0.getPosition(r3)
            r6.mo4614b(r3, r9)
            goto L_0x022a
        L_0x0073:
            androidx.recyclerview.widget.A r3 = r0.mAnchorInfo
            r3.reset()
            androidx.recyclerview.widget.A r3 = r0.mAnchorInfo
            boolean r6 = r0.mShouldReverseLayout
            boolean r9 = r0.f552Xr
            r6 = r6 ^ r9
            r3.mLayoutFromEnd = r6
            boolean r6 = r2.mInPreLayout
            if (r6 != 0) goto L_0x017f
            int r6 = r0.mPendingScrollPosition
            if (r6 != r4) goto L_0x008b
            goto L_0x017f
        L_0x008b:
            if (r6 < 0) goto L_0x017b
            int r9 = r18.getItemCount()
            if (r6 < r9) goto L_0x0095
            goto L_0x017b
        L_0x0095:
            int r6 = r0.mPendingScrollPosition
            r3.mPosition = r6
            androidx.recyclerview.widget.LinearLayoutManager$SavedState r6 = r0.mPendingSavedState
            if (r6 == 0) goto L_0x00cb
            boolean r6 = r6.hasValidAnchor()
            if (r6 == 0) goto L_0x00cb
            androidx.recyclerview.widget.LinearLayoutManager$SavedState r6 = r0.mPendingSavedState
            boolean r6 = r6.mAnchorLayoutFromEnd
            r3.mLayoutFromEnd = r6
            boolean r6 = r3.mLayoutFromEnd
            if (r6 == 0) goto L_0x00bc
            androidx.recyclerview.widget.I r6 = r0.mOrientationHelper
            int r6 = r6.getEndAfterPadding()
            androidx.recyclerview.widget.LinearLayoutManager$SavedState r9 = r0.mPendingSavedState
            int r9 = r9.mAnchorOffset
            int r6 = r6 - r9
            r3.mCoordinate = r6
            goto L_0x0179
        L_0x00bc:
            androidx.recyclerview.widget.I r6 = r0.mOrientationHelper
            int r6 = r6.getStartAfterPadding()
            androidx.recyclerview.widget.LinearLayoutManager$SavedState r9 = r0.mPendingSavedState
            int r9 = r9.mAnchorOffset
            int r6 = r6 + r9
            r3.mCoordinate = r6
            goto L_0x0179
        L_0x00cb:
            int r6 = r0.mPendingScrollPositionOffset
            if (r6 != r7) goto L_0x015c
            int r6 = r0.mPendingScrollPosition
            android.view.View r6 = r0.findViewByPosition(r6)
            if (r6 == 0) goto L_0x013a
            androidx.recyclerview.widget.I r9 = r0.mOrientationHelper
            int r9 = r9.getDecoratedMeasurement(r6)
            androidx.recyclerview.widget.I r10 = r0.mOrientationHelper
            int r10 = r10.getTotalSpace()
            if (r9 <= r10) goto L_0x00ea
            r3.assignCoordinateFromPadding()
            goto L_0x0179
        L_0x00ea:
            androidx.recyclerview.widget.I r9 = r0.mOrientationHelper
            int r9 = r9.getDecoratedStart(r6)
            androidx.recyclerview.widget.I r10 = r0.mOrientationHelper
            int r10 = r10.getStartAfterPadding()
            int r9 = r9 - r10
            if (r9 >= 0) goto L_0x0105
            androidx.recyclerview.widget.I r6 = r0.mOrientationHelper
            int r6 = r6.getStartAfterPadding()
            r3.mCoordinate = r6
            r3.mLayoutFromEnd = r5
            goto L_0x0179
        L_0x0105:
            androidx.recyclerview.widget.I r9 = r0.mOrientationHelper
            int r9 = r9.getEndAfterPadding()
            androidx.recyclerview.widget.I r10 = r0.mOrientationHelper
            int r10 = r10.getDecoratedEnd(r6)
            int r9 = r9 - r10
            if (r9 >= 0) goto L_0x011f
            androidx.recyclerview.widget.I r6 = r0.mOrientationHelper
            int r6 = r6.getEndAfterPadding()
            r3.mCoordinate = r6
            r3.mLayoutFromEnd = r8
            goto L_0x0179
        L_0x011f:
            boolean r9 = r3.mLayoutFromEnd
            if (r9 == 0) goto L_0x0131
            androidx.recyclerview.widget.I r9 = r0.mOrientationHelper
            int r6 = r9.getDecoratedEnd(r6)
            androidx.recyclerview.widget.I r9 = r0.mOrientationHelper
            int r9 = r9.getTotalSpaceChange()
            int r9 = r9 + r6
            goto L_0x0137
        L_0x0131:
            androidx.recyclerview.widget.I r9 = r0.mOrientationHelper
            int r9 = r9.getDecoratedStart(r6)
        L_0x0137:
            r3.mCoordinate = r9
            goto L_0x0179
        L_0x013a:
            int r6 = r16.getChildCount()
            if (r6 <= 0) goto L_0x0158
            android.view.View r6 = r0.getChildAt(r5)
            int r6 = r0.getPosition(r6)
            int r9 = r0.mPendingScrollPosition
            if (r9 >= r6) goto L_0x014e
            r6 = r8
            goto L_0x014f
        L_0x014e:
            r6 = r5
        L_0x014f:
            boolean r9 = r0.mShouldReverseLayout
            if (r6 != r9) goto L_0x0155
            r6 = r8
            goto L_0x0156
        L_0x0155:
            r6 = r5
        L_0x0156:
            r3.mLayoutFromEnd = r6
        L_0x0158:
            r3.assignCoordinateFromPadding()
            goto L_0x0179
        L_0x015c:
            boolean r6 = r0.mShouldReverseLayout
            r3.mLayoutFromEnd = r6
            if (r6 == 0) goto L_0x016e
            androidx.recyclerview.widget.I r6 = r0.mOrientationHelper
            int r6 = r6.getEndAfterPadding()
            int r9 = r0.mPendingScrollPositionOffset
            int r6 = r6 - r9
            r3.mCoordinate = r6
            goto L_0x0179
        L_0x016e:
            androidx.recyclerview.widget.I r6 = r0.mOrientationHelper
            int r6 = r6.getStartAfterPadding()
            int r9 = r0.mPendingScrollPositionOffset
            int r6 = r6 + r9
            r3.mCoordinate = r6
        L_0x0179:
            r6 = r8
            goto L_0x0180
        L_0x017b:
            r0.mPendingScrollPosition = r4
            r0.mPendingScrollPositionOffset = r7
        L_0x017f:
            r6 = r5
        L_0x0180:
            if (r6 == 0) goto L_0x0184
            goto L_0x0226
        L_0x0184:
            int r6 = r16.getChildCount()
            if (r6 != 0) goto L_0x018c
            goto L_0x0212
        L_0x018c:
            android.view.View r6 = r16.getFocusedChild()
            if (r6 == 0) goto L_0x01a1
            boolean r9 = r3.mo4612a((android.view.View) r6, (androidx.recyclerview.widget.C0582oa) r2)
            if (r9 == 0) goto L_0x01a1
            int r9 = r0.getPosition(r6)
            r3.mo4614b(r6, r9)
            goto L_0x0210
        L_0x01a1:
            boolean r6 = r0.f550Vr
            boolean r9 = r0.f552Xr
            if (r6 == r9) goto L_0x01a9
            goto L_0x0212
        L_0x01a9:
            boolean r6 = r3.mLayoutFromEnd
            if (r6 == 0) goto L_0x01bb
            boolean r6 = r0.mShouldReverseLayout
            if (r6 == 0) goto L_0x01b6
            android.view.View r6 = r16.m645f((androidx.recyclerview.widget.C0572ja) r17, (androidx.recyclerview.widget.C0582oa) r18)
            goto L_0x01c8
        L_0x01b6:
            android.view.View r6 = r16.m646g((androidx.recyclerview.widget.C0572ja) r17, (androidx.recyclerview.widget.C0582oa) r18)
            goto L_0x01c8
        L_0x01bb:
            boolean r6 = r0.mShouldReverseLayout
            if (r6 == 0) goto L_0x01c4
            android.view.View r6 = r16.m646g((androidx.recyclerview.widget.C0572ja) r17, (androidx.recyclerview.widget.C0582oa) r18)
            goto L_0x01c8
        L_0x01c4:
            android.view.View r6 = r16.m645f((androidx.recyclerview.widget.C0572ja) r17, (androidx.recyclerview.widget.C0582oa) r18)
        L_0x01c8:
            if (r6 == 0) goto L_0x0212
            int r9 = r0.getPosition(r6)
            r3.mo4611a((android.view.View) r6, (int) r9)
            boolean r9 = r2.mInPreLayout
            if (r9 != 0) goto L_0x0210
            boolean r9 = r16.supportsPredictiveItemAnimations()
            if (r9 == 0) goto L_0x0210
            androidx.recyclerview.widget.I r9 = r0.mOrientationHelper
            int r9 = r9.getDecoratedStart(r6)
            androidx.recyclerview.widget.I r10 = r0.mOrientationHelper
            int r10 = r10.getEndAfterPadding()
            if (r9 >= r10) goto L_0x01fa
            androidx.recyclerview.widget.I r9 = r0.mOrientationHelper
            int r6 = r9.getDecoratedEnd(r6)
            androidx.recyclerview.widget.I r9 = r0.mOrientationHelper
            int r9 = r9.getStartAfterPadding()
            if (r6 >= r9) goto L_0x01f8
            goto L_0x01fa
        L_0x01f8:
            r6 = r5
            goto L_0x01fb
        L_0x01fa:
            r6 = r8
        L_0x01fb:
            if (r6 == 0) goto L_0x0210
            boolean r6 = r3.mLayoutFromEnd
            if (r6 == 0) goto L_0x0208
            androidx.recyclerview.widget.I r6 = r0.mOrientationHelper
            int r6 = r6.getEndAfterPadding()
            goto L_0x020e
        L_0x0208:
            androidx.recyclerview.widget.I r6 = r0.mOrientationHelper
            int r6 = r6.getStartAfterPadding()
        L_0x020e:
            r3.mCoordinate = r6
        L_0x0210:
            r6 = r8
            goto L_0x0213
        L_0x0212:
            r6 = r5
        L_0x0213:
            if (r6 == 0) goto L_0x0216
            goto L_0x0226
        L_0x0216:
            r3.assignCoordinateFromPadding()
            boolean r6 = r0.f552Xr
            if (r6 == 0) goto L_0x0223
            int r6 = r18.getItemCount()
            int r6 = r6 + r4
            goto L_0x0224
        L_0x0223:
            r6 = r5
        L_0x0224:
            r3.mPosition = r6
        L_0x0226:
            androidx.recyclerview.widget.A r3 = r0.mAnchorInfo
            r3.mValid = r8
        L_0x022a:
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            int r6 = r3.mLastScrollDelta
            if (r6 < 0) goto L_0x0232
            r6 = r8
            goto L_0x0233
        L_0x0232:
            r6 = r4
        L_0x0233:
            r3.mLayoutDirection = r6
            int[] r3 = r0.f557yi
            r3[r5] = r5
            r3[r8] = r5
            r0.mo4748a((androidx.recyclerview.widget.C0582oa) r2, (int[]) r3)
            int[] r3 = r0.f557yi
            r3 = r3[r5]
            int r3 = java.lang.Math.max(r5, r3)
            androidx.recyclerview.widget.I r6 = r0.mOrientationHelper
            int r6 = r6.getStartAfterPadding()
            int r6 = r6 + r3
            int[] r3 = r0.f557yi
            r3 = r3[r8]
            int r3 = java.lang.Math.max(r5, r3)
            androidx.recyclerview.widget.I r9 = r0.mOrientationHelper
            int r9 = r9.getEndPadding()
            int r9 = r9 + r3
            boolean r3 = r2.mInPreLayout
            if (r3 == 0) goto L_0x0297
            int r3 = r0.mPendingScrollPosition
            if (r3 == r4) goto L_0x0297
            int r10 = r0.mPendingScrollPositionOffset
            if (r10 == r7) goto L_0x0297
            android.view.View r3 = r0.findViewByPosition(r3)
            if (r3 == 0) goto L_0x0297
            boolean r7 = r0.mShouldReverseLayout
            if (r7 == 0) goto L_0x0282
            androidx.recyclerview.widget.I r7 = r0.mOrientationHelper
            int r7 = r7.getEndAfterPadding()
            androidx.recyclerview.widget.I r10 = r0.mOrientationHelper
            int r3 = r10.getDecoratedEnd(r3)
            int r7 = r7 - r3
            int r3 = r0.mPendingScrollPositionOffset
            goto L_0x0291
        L_0x0282:
            androidx.recyclerview.widget.I r7 = r0.mOrientationHelper
            int r3 = r7.getDecoratedStart(r3)
            androidx.recyclerview.widget.I r7 = r0.mOrientationHelper
            int r7 = r7.getStartAfterPadding()
            int r3 = r3 - r7
            int r7 = r0.mPendingScrollPositionOffset
        L_0x0291:
            int r7 = r7 - r3
            if (r7 <= 0) goto L_0x0296
            int r6 = r6 + r7
            goto L_0x0297
        L_0x0296:
            int r9 = r9 - r7
        L_0x0297:
            androidx.recyclerview.widget.A r3 = r0.mAnchorInfo
            boolean r3 = r3.mLayoutFromEnd
            if (r3 == 0) goto L_0x02a2
            boolean r3 = r0.mShouldReverseLayout
            if (r3 == 0) goto L_0x02a6
            goto L_0x02a8
        L_0x02a2:
            boolean r3 = r0.mShouldReverseLayout
            if (r3 == 0) goto L_0x02a8
        L_0x02a6:
            r3 = r4
            goto L_0x02a9
        L_0x02a8:
            r3 = r8
        L_0x02a9:
            androidx.recyclerview.widget.A r7 = r0.mAnchorInfo
            r0.mo4717a((androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0582oa) r2, (androidx.recyclerview.widget.C0521A) r7, (int) r3)
            r16.mo5038b((androidx.recyclerview.widget.C0572ja) r17)
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            boolean r7 = r16.resolveIsInfinite()
            r3.mInfinite = r7
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            boolean r7 = r2.mInPreLayout
            r3.f508fr = r5
            androidx.recyclerview.widget.A r3 = r0.mAnchorInfo
            boolean r7 = r3.mLayoutFromEnd
            if (r7 == 0) goto L_0x030c
            int r7 = r3.mPosition
            int r3 = r3.mCoordinate
            r0.m639U(r7, r3)
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            r3.f507er = r6
            r0.mo4744a((androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0525C) r3, (androidx.recyclerview.widget.C0582oa) r2, (boolean) r5)
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            int r6 = r3.mOffset
            int r7 = r3.mCurrentPosition
            int r3 = r3.mAvailable
            if (r3 <= 0) goto L_0x02de
            int r9 = r9 + r3
        L_0x02de:
            androidx.recyclerview.widget.A r3 = r0.mAnchorInfo
            int r10 = r3.mPosition
            int r3 = r3.mCoordinate
            r0.m638T(r10, r3)
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            r3.f507er = r9
            int r9 = r3.mCurrentPosition
            int r10 = r3.mItemDirection
            int r9 = r9 + r10
            r3.mCurrentPosition = r9
            r0.mo4744a((androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0525C) r3, (androidx.recyclerview.widget.C0582oa) r2, (boolean) r5)
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            int r9 = r3.mOffset
            int r3 = r3.mAvailable
            if (r3 <= 0) goto L_0x0352
            r0.m639U(r7, r6)
            androidx.recyclerview.widget.C r6 = r0.mLayoutState
            r6.f507er = r3
            r0.mo4744a((androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0525C) r6, (androidx.recyclerview.widget.C0582oa) r2, (boolean) r5)
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            int r6 = r3.mOffset
            goto L_0x0352
        L_0x030c:
            int r7 = r3.mPosition
            int r3 = r3.mCoordinate
            r0.m638T(r7, r3)
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            r3.f507er = r9
            r0.mo4744a((androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0525C) r3, (androidx.recyclerview.widget.C0582oa) r2, (boolean) r5)
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            int r9 = r3.mOffset
            int r7 = r3.mCurrentPosition
            int r3 = r3.mAvailable
            if (r3 <= 0) goto L_0x0325
            int r6 = r6 + r3
        L_0x0325:
            androidx.recyclerview.widget.A r3 = r0.mAnchorInfo
            int r10 = r3.mPosition
            int r3 = r3.mCoordinate
            r0.m639U(r10, r3)
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            r3.f507er = r6
            int r6 = r3.mCurrentPosition
            int r10 = r3.mItemDirection
            int r6 = r6 + r10
            r3.mCurrentPosition = r6
            r0.mo4744a((androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0525C) r3, (androidx.recyclerview.widget.C0582oa) r2, (boolean) r5)
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            int r6 = r3.mOffset
            int r3 = r3.mAvailable
            if (r3 <= 0) goto L_0x0352
            r0.m638T(r7, r9)
            androidx.recyclerview.widget.C r7 = r0.mLayoutState
            r7.f507er = r3
            r0.mo4744a((androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0525C) r7, (androidx.recyclerview.widget.C0582oa) r2, (boolean) r5)
            androidx.recyclerview.widget.C r3 = r0.mLayoutState
            int r9 = r3.mOffset
        L_0x0352:
            int r3 = r16.getChildCount()
            if (r3 <= 0) goto L_0x0376
            boolean r3 = r0.mShouldReverseLayout
            boolean r7 = r0.f552Xr
            r3 = r3 ^ r7
            if (r3 == 0) goto L_0x036a
            int r3 = r0.m640a((int) r9, (androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0582oa) r2, (boolean) r8)
            int r6 = r6 + r3
            int r9 = r9 + r3
            int r3 = r0.m644b(r6, r1, r2, r5)
            goto L_0x0374
        L_0x036a:
            int r3 = r0.m644b(r6, r1, r2, r8)
            int r6 = r6 + r3
            int r9 = r9 + r3
            int r3 = r0.m640a((int) r9, (androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0582oa) r2, (boolean) r5)
        L_0x0374:
            int r6 = r6 + r3
            int r9 = r9 + r3
        L_0x0376:
            boolean r3 = r2.mRunPredictiveAnimations
            if (r3 == 0) goto L_0x0418
            int r3 = r16.getChildCount()
            if (r3 == 0) goto L_0x0418
            boolean r3 = r2.mInPreLayout
            if (r3 != 0) goto L_0x0418
            boolean r3 = r16.supportsPredictiveItemAnimations()
            if (r3 != 0) goto L_0x038c
            goto L_0x0418
        L_0x038c:
            java.util.List r3 = r17.getScrapList()
            int r7 = r3.size()
            android.view.View r10 = r0.getChildAt(r5)
            int r10 = r0.getPosition(r10)
            r11 = r5
            r12 = r11
            r13 = r12
        L_0x039f:
            if (r11 >= r7) goto L_0x03d9
            java.lang.Object r14 = r3.get(r11)
            androidx.recyclerview.widget.qa r14 = (androidx.recyclerview.widget.C0586qa) r14
            boolean r15 = r14.isRemoved()
            if (r15 == 0) goto L_0x03ae
            goto L_0x03d5
        L_0x03ae:
            int r15 = r14.getLayoutPosition()
            if (r15 >= r10) goto L_0x03b6
            r15 = r8
            goto L_0x03b7
        L_0x03b6:
            r15 = r5
        L_0x03b7:
            boolean r8 = r0.mShouldReverseLayout
            if (r15 == r8) goto L_0x03bd
            r8 = r4
            goto L_0x03be
        L_0x03bd:
            r8 = 1
        L_0x03be:
            if (r8 != r4) goto L_0x03cb
            androidx.recyclerview.widget.I r8 = r0.mOrientationHelper
            android.view.View r14 = r14.itemView
            int r8 = r8.getDecoratedMeasurement(r14)
            int r8 = r8 + r12
            r12 = r8
            goto L_0x03d5
        L_0x03cb:
            androidx.recyclerview.widget.I r8 = r0.mOrientationHelper
            android.view.View r14 = r14.itemView
            int r8 = r8.getDecoratedMeasurement(r14)
            int r8 = r8 + r13
            r13 = r8
        L_0x03d5:
            int r11 = r11 + 1
            r8 = 1
            goto L_0x039f
        L_0x03d9:
            androidx.recyclerview.widget.C r4 = r0.mLayoutState
            r4.mScrapList = r3
            r3 = 0
            if (r12 <= 0) goto L_0x03f9
            android.view.View r4 = r16.m636Nn()
            int r4 = r0.getPosition(r4)
            r0.m639U(r4, r6)
            androidx.recyclerview.widget.C r4 = r0.mLayoutState
            r4.f507er = r12
            r4.mAvailable = r5
            r4.assignPositionFromScrapList(r3)
            androidx.recyclerview.widget.C r4 = r0.mLayoutState
            r0.mo4744a((androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0525C) r4, (androidx.recyclerview.widget.C0582oa) r2, (boolean) r5)
        L_0x03f9:
            if (r13 <= 0) goto L_0x0414
            android.view.View r4 = r16.m635Mn()
            int r4 = r0.getPosition(r4)
            r0.m638T(r4, r9)
            androidx.recyclerview.widget.C r4 = r0.mLayoutState
            r4.f507er = r13
            r4.mAvailable = r5
            r4.assignPositionFromScrapList(r3)
            androidx.recyclerview.widget.C r4 = r0.mLayoutState
            r0.mo4744a((androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0525C) r4, (androidx.recyclerview.widget.C0582oa) r2, (boolean) r5)
        L_0x0414:
            androidx.recyclerview.widget.C r1 = r0.mLayoutState
            r1.mScrapList = r3
        L_0x0418:
            boolean r1 = r2.mInPreLayout
            if (r1 != 0) goto L_0x0422
            androidx.recyclerview.widget.I r1 = r0.mOrientationHelper
            r1.onLayoutComplete()
            goto L_0x0427
        L_0x0422:
            androidx.recyclerview.widget.A r1 = r0.mAnchorInfo
            r1.reset()
        L_0x0427:
            boolean r1 = r0.f552Xr
            r0.f550Vr = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.mo4727e(androidx.recyclerview.widget.ja, androidx.recyclerview.widget.oa):void");
    }

    /* access modifiers changed from: package-private */
    public void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = createLayoutState();
        }
    }

    /* renamed from: f */
    public int mo4759f(C0582oa oaVar) {
        return m647k(oaVar);
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), true, false);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findFirstVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), false, true);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findLastVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, false, true);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    /* access modifiers changed from: package-private */
    public View findOneVisibleChild(int i, int i2, boolean z, boolean z2) {
        ensureLayoutState();
        int i3 = 320;
        int i4 = z ? 24579 : 320;
        if (!z2) {
            i3 = 0;
        }
        if (this.mOrientation == 0) {
            return this.f628Pr.mo4659a(i, i2, i4, i3);
        }
        return this.f629Qr.mo4659a(i, i2, i4, i3);
    }

    public View findViewByPosition(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i) {
                return childAt;
            }
        }
        int childCount2 = getChildCount();
        for (int i2 = 0; i2 < childCount2; i2++) {
            View childAt2 = getChildAt(i2);
            C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt2);
            if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.mInPreLayout || !childViewHolderInt.isRemoved())) {
                return childAt2;
            }
        }
        return null;
    }

    /* renamed from: g */
    public int mo4728g(C0582oa oaVar) {
        return m648l(oaVar);
    }

    public C0560da generateDefaultLayoutParams() {
        return new C0560da(-2, -2);
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    /* renamed from: h */
    public int mo4733h(C0582oa oaVar) {
        return m649m(oaVar);
    }

    /* renamed from: i */
    public void mo4734i(C0582oa oaVar) {
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = RtlSpacingHelper.UNDEFINED;
        this.mAnchorInfo.reset();
    }

    public boolean isAutoMeasureEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.f553Yr;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    /* renamed from: j */
    public int mo4771j(C0582oa oaVar) {
        if (oaVar.f658gr != -1) {
            return this.mOrientationHelper.getTotalSpace();
        }
        return 0;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.mRecyclerView;
        mo5021a(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityEvent.setToIndex(findLastVisibleItemPosition());
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        if (getChildCount() > 0) {
            ensureLayoutState();
            boolean z = this.f550Vr ^ this.mShouldReverseLayout;
            savedState2.mAnchorLayoutFromEnd = z;
            if (z) {
                View Mn = m635Mn();
                savedState2.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(Mn);
                savedState2.mAnchorPosition = getPosition(Mn);
            } else {
                View Nn = m636Nn();
                savedState2.mAnchorPosition = getPosition(Nn);
                savedState2.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(Nn) - this.mOrientationHelper.getStartAfterPadding();
            }
        } else {
            savedState2.invalidateAnchor();
        }
        return savedState2;
    }

    /* access modifiers changed from: package-private */
    public boolean resolveIsInfinite() {
        return this.mOrientationHelper.getMode() == 0 && this.mOrientationHelper.getEnd() == 0;
    }

    public void scrollToPosition(int i) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = RtlSpacingHelper.UNDEFINED;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.invalidateAnchor();
        }
        requestLayout();
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            assertNotInLayoutOrScroll((String) null);
            if (i != this.mOrientation || this.mOrientationHelper == null) {
                this.mOrientationHelper = C0536I.m632a(this, i);
                this.mAnchorInfo.mOrientationHelper = this.mOrientationHelper;
                this.mOrientation = i;
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation:" + i);
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll((String) null);
        if (z != this.f551Wr) {
            this.f551Wr = z;
            requestLayout();
        }
    }

    public void setStackFromEnd(boolean z) {
        assertNotInLayoutOrScroll((String) null);
        if (this.f552Xr != z) {
            this.f552Xr = z;
            requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldMeasureTwice() {
        return (getHeightMode() == 1073741824 || getWidthMode() == 1073741824 || !hasFlexibleChildInBothOrientations()) ? false : true;
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && this.f550Vr == this.f552Xr;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public int mo4751c(int i, C0572ja jaVar, C0582oa oaVar) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        ensureLayoutState();
        this.mLayoutState.mRecycle = true;
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        m641a(i2, abs, true, oaVar);
        C0525C c = this.mLayoutState;
        int a = c.mScrollingOffset + mo4744a(jaVar, c, oaVar, false);
        if (a < 0) {
            return 0;
        }
        if (abs > a) {
            i = i2 * a;
        }
        this.mOrientationHelper.offsetChildren(-i);
        this.mLayoutState.mLastScrollDelta = i;
        return i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public View mo4760f(boolean z, boolean z2) {
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild(0, getChildCount(), z, z2);
        }
        return findOneVisibleChild(getChildCount() - 1, -1, z, z2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public View mo4766g(boolean z, boolean z2) {
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild(getChildCount() - 1, -1, z, z2);
        }
        return findOneVisibleChild(0, getChildCount(), z, z2);
    }

    /* renamed from: f */
    private View m645f(C0572ja jaVar, C0582oa oaVar) {
        return mo4713a(jaVar, oaVar, 0, getChildCount(), oaVar.getItemCount());
    }

    /* renamed from: g */
    private View m646g(C0572ja jaVar, C0582oa oaVar) {
        return mo4713a(jaVar, oaVar, getChildCount() - 1, -1, oaVar.getItemCount());
    }

    /* renamed from: a */
    public void mo4747a(RecyclerView recyclerView, C0582oa oaVar, int i) {
        C0529E e = new C0529E(recyclerView.getContext());
        e.setTargetPosition(i);
        mo5037b(e);
    }

    /* renamed from: b */
    public int mo4721b(int i, C0572ja jaVar, C0582oa oaVar) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return mo4751c(i, jaVar, oaVar);
    }

    /* renamed from: b */
    public void mo4750b(RecyclerView recyclerView, C0572ja jaVar) {
        mo5049f(recyclerView);
        if (this.f554Zr) {
            mo5041c(jaVar);
            jaVar.clear();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: l */
    public View mo4772l(int i, int i2) {
        int i3;
        int i4;
        ensureLayoutState();
        if ((i2 > i ? 1 : i2 < i ? (char) 65535 : 0) == 0) {
            return getChildAt(i);
        }
        if (this.mOrientationHelper.getDecoratedStart(getChildAt(i)) < this.mOrientationHelper.getStartAfterPadding()) {
            i4 = 16644;
            i3 = 16388;
        } else {
            i4 = 4161;
            i3 = 4097;
        }
        if (this.mOrientation == 0) {
            return this.f628Pr.mo4659a(i, i2, i4, i3);
        }
        return this.f629Qr.mo4659a(i, i2, i4, i3);
    }

    /* renamed from: a */
    private int m640a(int i, C0572ja jaVar, C0582oa oaVar, boolean z) {
        int endAfterPadding;
        int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i;
        if (endAfterPadding2 <= 0) {
            return 0;
        }
        int i2 = -mo4751c(-endAfterPadding2, jaVar, oaVar);
        int i3 = i + i2;
        if (!z || (endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i3) <= 0) {
            return i2;
        }
        this.mOrientationHelper.offsetChildren(endAfterPadding);
        return endAfterPadding + i2;
    }

    /* renamed from: a */
    public int mo4710a(int i, C0572ja jaVar, C0582oa oaVar) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return mo4751c(i, jaVar, oaVar);
    }

    /* renamed from: a */
    private void m641a(int i, int i2, boolean z, C0582oa oaVar) {
        int i3;
        this.mLayoutState.mInfinite = resolveIsInfinite();
        this.mLayoutState.mLayoutDirection = i;
        int[] iArr = this.f557yi;
        boolean z2 = false;
        iArr[0] = 0;
        iArr[1] = 0;
        mo4748a(oaVar, iArr);
        int max = Math.max(0, this.f557yi[0]);
        int max2 = Math.max(0, this.f557yi[1]);
        if (i == 1) {
            z2 = true;
        }
        this.mLayoutState.f507er = z2 ? max2 : max;
        C0525C c = this.mLayoutState;
        if (!z2) {
            max = max2;
        }
        c.f508fr = max;
        int i4 = -1;
        if (z2) {
            C0525C c2 = this.mLayoutState;
            c2.f507er = this.mOrientationHelper.getEndPadding() + c2.f507er;
            View Mn = m635Mn();
            C0525C c3 = this.mLayoutState;
            if (!this.mShouldReverseLayout) {
                i4 = 1;
            }
            c3.mItemDirection = i4;
            C0525C c4 = this.mLayoutState;
            int position = getPosition(Mn);
            C0525C c5 = this.mLayoutState;
            c4.mCurrentPosition = position + c5.mItemDirection;
            c5.mOffset = this.mOrientationHelper.getDecoratedEnd(Mn);
            i3 = this.mOrientationHelper.getDecoratedEnd(Mn) - this.mOrientationHelper.getEndAfterPadding();
        } else {
            View Nn = m636Nn();
            C0525C c6 = this.mLayoutState;
            c6.f507er = this.mOrientationHelper.getStartAfterPadding() + c6.f507er;
            C0525C c7 = this.mLayoutState;
            if (this.mShouldReverseLayout) {
                i4 = 1;
            }
            c7.mItemDirection = i4;
            C0525C c8 = this.mLayoutState;
            int position2 = getPosition(Nn);
            C0525C c9 = this.mLayoutState;
            c8.mCurrentPosition = position2 + c9.mItemDirection;
            c9.mOffset = this.mOrientationHelper.getDecoratedStart(Nn);
            i3 = (-this.mOrientationHelper.getDecoratedStart(Nn)) + this.mOrientationHelper.getStartAfterPadding();
        }
        C0525C c10 = this.mLayoutState;
        c10.mAvailable = i2;
        if (z) {
            c10.mAvailable -= i3;
        }
        this.mLayoutState.mScrollingOffset = i3;
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        C0556ba properties = C0558ca.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.orientation);
        setReverseLayout(properties.reverseLayout);
        setStackFromEnd(properties.stackFromEnd);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4719a(C0582oa oaVar, C0525C c, C0554aa aaVar) {
        int i = c.mCurrentPosition;
        if (i >= 0 && i < oaVar.getItemCount()) {
            ((C0593u) aaVar).addPosition(i, Math.max(0, c.mScrollingOffset));
        }
    }

    /* renamed from: a */
    public void mo4746a(int i, C0554aa aaVar) {
        boolean z;
        int i2;
        SavedState savedState = this.mPendingSavedState;
        int i3 = -1;
        if (savedState == null || !savedState.hasValidAnchor()) {
            m637On();
            z = this.mShouldReverseLayout;
            i2 = this.mPendingScrollPosition;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.mPendingSavedState;
            z = savedState2.mAnchorLayoutFromEnd;
            i2 = savedState2.mAnchorPosition;
        }
        if (!z) {
            i3 = 1;
        }
        int i4 = i2;
        for (int i5 = 0; i5 < this.f556as && i4 >= 0 && i4 < i; i5++) {
            ((C0593u) aaVar).addPosition(i4, 0);
            i4 += i3;
        }
    }

    /* renamed from: a */
    public void mo4745a(int i, int i2, C0582oa oaVar, C0554aa aaVar) {
        if (this.mOrientation != 0) {
            i = i2;
        }
        if (getChildCount() != 0 && i != 0) {
            ensureLayoutState();
            m641a(i > 0 ? 1 : -1, Math.abs(i), true, oaVar);
            mo4719a(oaVar, this.mLayoutState, aaVar);
        }
    }

    /* renamed from: a */
    private void m642a(C0572ja jaVar, int i, int i2) {
        if (i != i2) {
            if (i2 > i) {
                for (int i3 = i2 - 1; i3 >= i; i3--) {
                    mo5012a(i3, jaVar);
                }
                return;
            }
            while (i > i2) {
                mo5012a(i, jaVar);
                i--;
            }
        }
    }

    /* renamed from: a */
    private void m643a(C0572ja jaVar, C0525C c) {
        if (c.mRecycle && !c.mInfinite) {
            int i = c.mScrollingOffset;
            int i2 = c.f508fr;
            if (c.mLayoutDirection == -1) {
                int childCount = getChildCount();
                if (i >= 0) {
                    int end = (this.mOrientationHelper.getEnd() - i) + i2;
                    if (this.mShouldReverseLayout) {
                        for (int i3 = 0; i3 < childCount; i3++) {
                            View childAt = getChildAt(i3);
                            if (this.mOrientationHelper.getDecoratedStart(childAt) < end || this.mOrientationHelper.getTransformedStartWithDecoration(childAt) < end) {
                                m642a(jaVar, 0, i3);
                                return;
                            }
                        }
                        return;
                    }
                    int i4 = childCount - 1;
                    for (int i5 = i4; i5 >= 0; i5--) {
                        View childAt2 = getChildAt(i5);
                        if (this.mOrientationHelper.getDecoratedStart(childAt2) < end || this.mOrientationHelper.getTransformedStartWithDecoration(childAt2) < end) {
                            m642a(jaVar, i4, i5);
                            return;
                        }
                    }
                }
            } else if (i >= 0) {
                int i6 = i - i2;
                int childCount2 = getChildCount();
                if (this.mShouldReverseLayout) {
                    int i7 = childCount2 - 1;
                    for (int i8 = i7; i8 >= 0; i8--) {
                        View childAt3 = getChildAt(i8);
                        if (this.mOrientationHelper.getDecoratedEnd(childAt3) > i6 || this.mOrientationHelper.getTransformedEndWithDecoration(childAt3) > i6) {
                            m642a(jaVar, i7, i8);
                            return;
                        }
                    }
                    return;
                }
                for (int i9 = 0; i9 < childCount2; i9++) {
                    View childAt4 = getChildAt(i9);
                    if (this.mOrientationHelper.getDecoratedEnd(childAt4) > i6 || this.mOrientationHelper.getTransformedEndWithDecoration(childAt4) > i6) {
                        m642a(jaVar, 0, i9);
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo4744a(C0572ja jaVar, C0525C c, C0582oa oaVar, boolean z) {
        int i = c.mAvailable;
        int i2 = c.mScrollingOffset;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                c.mScrollingOffset = i2 + i;
            }
            m643a(jaVar, c);
        }
        int i3 = c.mAvailable + c.f507er;
        C0523B b = this.f555_r;
        while (true) {
            if ((!c.mInfinite && i3 <= 0) || !c.mo4637b(oaVar)) {
                break;
            }
            b.mConsumed = 0;
            b.mFinished = false;
            b.mIgnoreConsumed = false;
            b.mFocusable = false;
            mo4718a(jaVar, oaVar, c, b);
            if (!b.mFinished) {
                c.mOffset = (b.mConsumed * c.mLayoutDirection) + c.mOffset;
                if (!b.mIgnoreConsumed || c.mScrapList != null || !oaVar.mInPreLayout) {
                    int i4 = c.mAvailable;
                    int i5 = b.mConsumed;
                    c.mAvailable = i4 - i5;
                    i3 -= i5;
                }
                int i6 = c.mScrollingOffset;
                if (i6 != Integer.MIN_VALUE) {
                    c.mScrollingOffset = i6 + b.mConsumed;
                    int i7 = c.mAvailable;
                    if (i7 < 0) {
                        c.mScrollingOffset += i7;
                    }
                    m643a(jaVar, c);
                }
                if (z && b.mFocusable) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - c.mAvailable;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4718a(C0572ja jaVar, C0582oa oaVar, C0525C c, C0523B b) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        View a = c.mo4635a(jaVar);
        if (a == null) {
            b.mFinished = true;
            return;
        }
        C0560da daVar = (C0560da) a.getLayoutParams();
        if (c.mScrapList == null) {
            if (this.mShouldReverseLayout == (c.mLayoutDirection == -1)) {
                addView(a);
            } else {
                addView(a, 0);
            }
        } else {
            if (this.mShouldReverseLayout == (c.mLayoutDirection == -1)) {
                addDisappearingView(a);
            } else {
                addDisappearingView(a, 0);
            }
        }
        measureChildWithMargins(a, 0, 0);
        b.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(a);
        if (this.mOrientation == 1) {
            if (isLayoutRTL()) {
                i5 = getWidth() - getPaddingRight();
                i4 = i5 - this.mOrientationHelper.getDecoratedMeasurementInOther(a);
            } else {
                i4 = getPaddingLeft();
                i5 = this.mOrientationHelper.getDecoratedMeasurementInOther(a) + i4;
            }
            if (c.mLayoutDirection == -1) {
                int i6 = c.mOffset;
                i = i6;
                i2 = i5;
                i3 = i6 - b.mConsumed;
            } else {
                int i7 = c.mOffset;
                i3 = i7;
                i2 = i5;
                i = b.mConsumed + i7;
            }
        } else {
            int paddingTop = getPaddingTop();
            int decoratedMeasurementInOther = this.mOrientationHelper.getDecoratedMeasurementInOther(a) + paddingTop;
            if (c.mLayoutDirection == -1) {
                int i8 = c.mOffset;
                i2 = i8;
                i3 = paddingTop;
                i = decoratedMeasurementInOther;
                i4 = i8 - b.mConsumed;
            } else {
                int i9 = c.mOffset;
                i3 = paddingTop;
                i2 = b.mConsumed + i9;
                i = decoratedMeasurementInOther;
                i4 = i9;
            }
        }
        layoutDecoratedWithMargins(a, i4, i3, i2, i);
        if (daVar.isItemRemoved() || daVar.isItemChanged()) {
            b.mIgnoreConsumed = true;
        }
        b.mFocusable = a.hasFocusable();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public View mo4713a(C0572ja jaVar, C0582oa oaVar, int i, int i2, int i3) {
        ensureLayoutState();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((C0560da) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(childAt) < endAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) >= startAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    /* renamed from: a */
    public View mo4712a(View view, int i, C0572ja jaVar, C0582oa oaVar) {
        int convertFocusDirectionToLayoutDirection;
        View view2;
        View view3;
        m637On();
        if (getChildCount() == 0 || (convertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i)) == Integer.MIN_VALUE) {
            return null;
        }
        ensureLayoutState();
        m641a(convertFocusDirectionToLayoutDirection, (int) (((float) this.mOrientationHelper.getTotalSpace()) * 0.33333334f), false, oaVar);
        C0525C c = this.mLayoutState;
        c.mScrollingOffset = RtlSpacingHelper.UNDEFINED;
        c.mRecycle = false;
        mo4744a(jaVar, c, oaVar, true);
        if (convertFocusDirectionToLayoutDirection == -1) {
            if (this.mShouldReverseLayout) {
                view2 = m634Ln();
            } else {
                view2 = m633Kn();
            }
        } else if (this.mShouldReverseLayout) {
            view2 = m633Kn();
        } else {
            view2 = m634Ln();
        }
        if (convertFocusDirectionToLayoutDirection == -1) {
            view3 = m636Nn();
        } else {
            view3 = m635Mn();
        }
        if (!view3.hasFocusable()) {
            return view2;
        }
        if (view2 == null) {
            return null;
        }
        return view3;
    }

    /* renamed from: e */
    public int mo4726e(C0582oa oaVar) {
        return m649m(oaVar);
    }
}
