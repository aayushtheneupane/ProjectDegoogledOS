package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.p025a.C0362d;
import androidx.core.view.p025a.C0363e;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StaggeredGridLayoutManager extends C0558ca implements C0580na {

    /* renamed from: Wr */
    boolean f597Wr = false;

    /* renamed from: Yr */
    private boolean f598Yr = true;

    /* renamed from: cs */
    private int f599cs = -1;

    /* renamed from: js */
    C0524Ba[] f600js;

    /* renamed from: ks */
    C0536I f601ks;

    /* renamed from: ls */
    C0536I f602ls;
    private final C0598wa mAnchorInfo = new C0598wa(this);
    private final C0603z mLayoutState;
    private int mOrientation;
    private SavedState mPendingSavedState;
    int mPendingScrollPosition = -1;
    int mPendingScrollPositionOffset = RtlSpacingHelper.UNDEFINED;
    boolean mShouldReverseLayout = false;
    private final Rect mTmpRect = new Rect();

    /* renamed from: ms */
    private int f603ms;

    /* renamed from: ns */
    private BitSet f604ns;

    /* renamed from: os */
    C0604za f605os = new C0604za();

    /* renamed from: ps */
    private int f606ps = 2;

    /* renamed from: qs */
    private boolean f607qs;

    /* renamed from: rs */
    private boolean f608rs;

    /* renamed from: ss */
    private int f609ss;

    /* renamed from: us */
    private boolean f610us = false;

    /* renamed from: vs */
    private int[] f611vs;

    /* renamed from: ws */
    private final Runnable f612ws = new C0596va(this);

    @SuppressLint({"BanParcelableUsage"})
    public class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR = new C0522Aa();

        /* renamed from: Wr */
        boolean f613Wr;
        boolean mAnchorLayoutFromEnd;
        int mAnchorPosition;

        /* renamed from: rs */
        boolean f614rs;

        /* renamed from: ut */
        List f615ut;

        /* renamed from: vt */
        int f616vt;

        /* renamed from: wt */
        int f617wt;

        /* renamed from: xt */
        int[] f618xt;

        /* renamed from: yt */
        int f619yt;

        /* renamed from: zt */
        int[] f620zt;

        public SavedState() {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: Zc */
        public void mo4973Zc() {
            this.f618xt = null;
            this.f617wt = 0;
            this.mAnchorPosition = -1;
            this.f616vt = -1;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: _c */
        public void mo4974_c() {
            this.f618xt = null;
            this.f617wt = 0;
            this.f619yt = 0;
            this.f620zt = null;
            this.f615ut = null;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.f616vt);
            parcel.writeInt(this.f617wt);
            if (this.f617wt > 0) {
                parcel.writeIntArray(this.f618xt);
            }
            parcel.writeInt(this.f619yt);
            if (this.f619yt > 0) {
                parcel.writeIntArray(this.f620zt);
            }
            parcel.writeInt(this.f613Wr ? 1 : 0);
            parcel.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
            parcel.writeInt(this.f614rs ? 1 : 0);
            parcel.writeList(this.f615ut);
        }

        SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.f616vt = parcel.readInt();
            this.f617wt = parcel.readInt();
            int i = this.f617wt;
            if (i > 0) {
                this.f618xt = new int[i];
                parcel.readIntArray(this.f618xt);
            }
            this.f619yt = parcel.readInt();
            int i2 = this.f619yt;
            if (i2 > 0) {
                this.f620zt = new int[i2];
                parcel.readIntArray(this.f620zt);
            }
            boolean z = false;
            this.f613Wr = parcel.readInt() == 1;
            this.mAnchorLayoutFromEnd = parcel.readInt() == 1;
            this.f614rs = parcel.readInt() == 1 ? true : z;
            this.f615ut = parcel.readArrayList(StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.f617wt = savedState.f617wt;
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.f616vt = savedState.f616vt;
            this.f618xt = savedState.f618xt;
            this.f619yt = savedState.f619yt;
            this.f620zt = savedState.f620zt;
            this.f613Wr = savedState.f613Wr;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
            this.f614rs = savedState.f614rs;
            this.f615ut = savedState.f615ut;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        C0556ba properties = C0558ca.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.orientation);
        mo4955Q(properties.spanCount);
        setReverseLayout(properties.reverseLayout);
        this.mLayoutState = new C0603z();
        this.f601ks = C0536I.m632a(this, this.mOrientation);
        this.f602ls = C0536I.m632a(this, 1 - this.mOrientation);
    }

    /* renamed from: On */
    private void m731On() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.f597Wr;
        } else {
            this.mShouldReverseLayout = !this.f597Wr;
        }
    }

    /* renamed from: V */
    private void m732V(int i, int i2) {
        for (int i3 = 0; i3 < this.f599cs; i3++) {
            if (!this.f600js[i3].f503At.isEmpty()) {
                m735a(this.f600js[i3], i, i2);
            }
        }
    }

    /* renamed from: a */
    private void m734a(View view, int i, int i2, boolean z) {
        boolean z2;
        calculateItemDecorationsForChild(view, this.mTmpRect);
        C0600xa xaVar = (C0600xa) view.getLayoutParams();
        int i3 = xaVar.leftMargin;
        Rect rect = this.mTmpRect;
        int g = m744g(i, i3 + rect.left, xaVar.rightMargin + rect.right);
        int i4 = xaVar.topMargin;
        Rect rect2 = this.mTmpRect;
        int g2 = m744g(i2, i4 + rect2.top, xaVar.bottomMargin + rect2.bottom);
        if (z) {
            z2 = mo5039b(view, g, g2, xaVar);
        } else {
            z2 = mo5023a(view, g, g2, (C0560da) xaVar);
        }
        if (z2) {
            view.measure(g, g2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:151:0x029b, code lost:
        if (mo4956Qc() != false) goto L_0x029f;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m742c(androidx.recyclerview.widget.C0572ja r12, androidx.recyclerview.widget.C0582oa r13, boolean r14) {
        /*
            r11 = this;
            androidx.recyclerview.widget.wa r0 = r11.mAnchorInfo
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r1 = r11.mPendingSavedState
            r2 = -1
            if (r1 != 0) goto L_0x000b
            int r1 = r11.mPendingScrollPosition
            if (r1 == r2) goto L_0x0018
        L_0x000b:
            int r1 = r13.getItemCount()
            if (r1 != 0) goto L_0x0018
            r11.mo5041c((androidx.recyclerview.widget.C0572ja) r12)
            r0.reset()
            return
        L_0x0018:
            boolean r1 = r0.mValid
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0029
            int r1 = r11.mPendingScrollPosition
            if (r1 != r2) goto L_0x0029
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r1 = r11.mPendingSavedState
            if (r1 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r1 = r3
            goto L_0x002a
        L_0x0029:
            r1 = r4
        L_0x002a:
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == 0) goto L_0x00b3
            r0.reset()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            if (r6 == 0) goto L_0x00a7
            int r7 = r6.f617wt
            if (r7 <= 0) goto L_0x0077
            int r8 = r11.f599cs
            if (r7 != r8) goto L_0x006e
            r6 = r3
        L_0x003e:
            int r7 = r11.f599cs
            if (r6 >= r7) goto L_0x0077
            androidx.recyclerview.widget.Ba[] r7 = r11.f600js
            r7 = r7[r6]
            r7.clear()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r7 = r11.mPendingSavedState
            int[] r8 = r7.f618xt
            r8 = r8[r6]
            if (r8 == r5) goto L_0x0063
            boolean r7 = r7.mAnchorLayoutFromEnd
            if (r7 == 0) goto L_0x005c
            androidx.recyclerview.widget.I r7 = r11.f601ks
            int r7 = r7.getEndAfterPadding()
            goto L_0x0062
        L_0x005c:
            androidx.recyclerview.widget.I r7 = r11.f601ks
            int r7 = r7.getStartAfterPadding()
        L_0x0062:
            int r8 = r8 + r7
        L_0x0063:
            androidx.recyclerview.widget.Ba[] r7 = r11.f600js
            r7 = r7[r6]
            r7.f504Bt = r8
            r7.f505Ct = r8
            int r6 = r6 + 1
            goto L_0x003e
        L_0x006e:
            r6.mo4974_c()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            int r7 = r6.f616vt
            r6.mAnchorPosition = r7
        L_0x0077:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            boolean r7 = r6.f614rs
            r11.f608rs = r7
            boolean r6 = r6.f613Wr
            r11.setReverseLayout(r6)
            r11.m731On()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            int r7 = r6.mAnchorPosition
            if (r7 == r2) goto L_0x0092
            r11.mPendingScrollPosition = r7
            boolean r6 = r6.mAnchorLayoutFromEnd
            r0.mLayoutFromEnd = r6
            goto L_0x0096
        L_0x0092:
            boolean r6 = r11.mShouldReverseLayout
            r0.mLayoutFromEnd = r6
        L_0x0096:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            int r7 = r6.f619yt
            if (r7 <= r4) goto L_0x00ae
            androidx.recyclerview.widget.za r7 = r11.f605os
            int[] r8 = r6.f620zt
            r7.mData = r8
            java.util.List r6 = r6.f615ut
            r7.f688ut = r6
            goto L_0x00ae
        L_0x00a7:
            r11.m731On()
            boolean r6 = r11.mShouldReverseLayout
            r0.mLayoutFromEnd = r6
        L_0x00ae:
            r11.mo4965b((androidx.recyclerview.widget.C0582oa) r13, (androidx.recyclerview.widget.C0598wa) r0)
            r0.mValid = r4
        L_0x00b3:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            if (r6 != 0) goto L_0x00d0
            int r6 = r11.mPendingScrollPosition
            if (r6 != r2) goto L_0x00d0
            boolean r6 = r0.mLayoutFromEnd
            boolean r7 = r11.f607qs
            if (r6 != r7) goto L_0x00c9
            boolean r6 = r11.isLayoutRTL()
            boolean r7 = r11.f608rs
            if (r6 == r7) goto L_0x00d0
        L_0x00c9:
            androidx.recyclerview.widget.za r6 = r11.f605os
            r6.clear()
            r0.f675pt = r4
        L_0x00d0:
            int r6 = r11.getChildCount()
            if (r6 <= 0) goto L_0x0167
            androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState r6 = r11.mPendingSavedState
            if (r6 == 0) goto L_0x00de
            int r6 = r6.f617wt
            if (r6 >= r4) goto L_0x0167
        L_0x00de:
            boolean r6 = r0.f675pt
            if (r6 == 0) goto L_0x00fd
            r1 = r3
        L_0x00e3:
            int r6 = r11.f599cs
            if (r1 >= r6) goto L_0x0167
            androidx.recyclerview.widget.Ba[] r6 = r11.f600js
            r6 = r6[r1]
            r6.clear()
            int r6 = r0.mOffset
            if (r6 == r5) goto L_0x00fa
            androidx.recyclerview.widget.Ba[] r7 = r11.f600js
            r7 = r7[r1]
            r7.f504Bt = r6
            r7.f505Ct = r6
        L_0x00fa:
            int r1 = r1 + 1
            goto L_0x00e3
        L_0x00fd:
            if (r1 != 0) goto L_0x011f
            androidx.recyclerview.widget.wa r1 = r11.mAnchorInfo
            int[] r1 = r1.f676qt
            if (r1 != 0) goto L_0x0106
            goto L_0x011f
        L_0x0106:
            r1 = r3
        L_0x0107:
            int r6 = r11.f599cs
            if (r1 >= r6) goto L_0x0167
            androidx.recyclerview.widget.Ba[] r6 = r11.f600js
            r6 = r6[r1]
            r6.clear()
            androidx.recyclerview.widget.wa r7 = r11.mAnchorInfo
            int[] r7 = r7.f676qt
            r7 = r7[r1]
            r6.f504Bt = r7
            r6.f505Ct = r7
            int r1 = r1 + 1
            goto L_0x0107
        L_0x011f:
            r1 = r3
        L_0x0120:
            int r6 = r11.f599cs
            if (r1 >= r6) goto L_0x0160
            androidx.recyclerview.widget.Ba[] r6 = r11.f600js
            r6 = r6[r1]
            boolean r7 = r11.mShouldReverseLayout
            int r8 = r0.mOffset
            if (r7 == 0) goto L_0x0133
            int r9 = r6.mo4619X(r5)
            goto L_0x0137
        L_0x0133:
            int r9 = r6.mo4620Y(r5)
        L_0x0137:
            r6.clear()
            if (r9 != r5) goto L_0x013d
            goto L_0x015d
        L_0x013d:
            if (r7 == 0) goto L_0x0149
            androidx.recyclerview.widget.StaggeredGridLayoutManager r10 = r6.this$0
            androidx.recyclerview.widget.I r10 = r10.f601ks
            int r10 = r10.getEndAfterPadding()
            if (r9 < r10) goto L_0x015d
        L_0x0149:
            if (r7 != 0) goto L_0x0156
            androidx.recyclerview.widget.StaggeredGridLayoutManager r7 = r6.this$0
            androidx.recyclerview.widget.I r7 = r7.f601ks
            int r7 = r7.getStartAfterPadding()
            if (r9 <= r7) goto L_0x0156
            goto L_0x015d
        L_0x0156:
            if (r8 == r5) goto L_0x0159
            int r9 = r9 + r8
        L_0x0159:
            r6.f505Ct = r9
            r6.f504Bt = r9
        L_0x015d:
            int r1 = r1 + 1
            goto L_0x0120
        L_0x0160:
            androidx.recyclerview.widget.wa r1 = r11.mAnchorInfo
            androidx.recyclerview.widget.Ba[] r6 = r11.f600js
            r1.mo5260a(r6)
        L_0x0167:
            r11.mo5038b((androidx.recyclerview.widget.C0572ja) r12)
            androidx.recyclerview.widget.z r1 = r11.mLayoutState
            r1.mRecycle = r3
            r11.f610us = r3
            androidx.recyclerview.widget.I r1 = r11.f602ls
            int r1 = r1.getTotalSpace()
            r11.mo4957R(r1)
            int r1 = r0.mPosition
            r11.m739b((int) r1, (androidx.recyclerview.widget.C0582oa) r13)
            boolean r1 = r0.mLayoutFromEnd
            if (r1 == 0) goto L_0x019a
            r11.m752xb(r2)
            androidx.recyclerview.widget.z r1 = r11.mLayoutState
            r11.m733a((androidx.recyclerview.widget.C0572ja) r12, (androidx.recyclerview.widget.C0603z) r1, (androidx.recyclerview.widget.C0582oa) r13)
            r11.m752xb(r4)
            androidx.recyclerview.widget.z r1 = r11.mLayoutState
            int r2 = r0.mPosition
            int r6 = r1.mItemDirection
            int r2 = r2 + r6
            r1.mCurrentPosition = r2
            r11.m733a((androidx.recyclerview.widget.C0572ja) r12, (androidx.recyclerview.widget.C0603z) r1, (androidx.recyclerview.widget.C0582oa) r13)
            goto L_0x01b1
        L_0x019a:
            r11.m752xb(r4)
            androidx.recyclerview.widget.z r1 = r11.mLayoutState
            r11.m733a((androidx.recyclerview.widget.C0572ja) r12, (androidx.recyclerview.widget.C0603z) r1, (androidx.recyclerview.widget.C0582oa) r13)
            r11.m752xb(r2)
            androidx.recyclerview.widget.z r1 = r11.mLayoutState
            int r2 = r0.mPosition
            int r6 = r1.mItemDirection
            int r2 = r2 + r6
            r1.mCurrentPosition = r2
            r11.m733a((androidx.recyclerview.widget.C0572ja) r12, (androidx.recyclerview.widget.C0603z) r1, (androidx.recyclerview.widget.C0582oa) r13)
        L_0x01b1:
            androidx.recyclerview.widget.I r1 = r11.f602ls
            int r1 = r1.getMode()
            r2 = 1073741824(0x40000000, float:2.0)
            if (r1 != r2) goto L_0x01bd
            goto L_0x025c
        L_0x01bd:
            r1 = 0
            int r2 = r11.getChildCount()
            r6 = r1
            r1 = r3
        L_0x01c4:
            if (r1 >= r2) goto L_0x01ee
            android.view.View r7 = r11.getChildAt(r1)
            androidx.recyclerview.widget.I r8 = r11.f602ls
            int r8 = r8.getDecoratedMeasurement(r7)
            float r8 = (float) r8
            int r9 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r9 >= 0) goto L_0x01d6
            goto L_0x01eb
        L_0x01d6:
            android.view.ViewGroup$LayoutParams r7 = r7.getLayoutParams()
            androidx.recyclerview.widget.xa r7 = (androidx.recyclerview.widget.C0600xa) r7
            boolean r7 = r7.f682Yk
            if (r7 == 0) goto L_0x01e7
            r7 = 1065353216(0x3f800000, float:1.0)
            float r8 = r8 * r7
            int r7 = r11.f599cs
            float r7 = (float) r7
            float r8 = r8 / r7
        L_0x01e7:
            float r6 = java.lang.Math.max(r6, r8)
        L_0x01eb:
            int r1 = r1 + 1
            goto L_0x01c4
        L_0x01ee:
            int r1 = r11.f603ms
            int r7 = r11.f599cs
            float r7 = (float) r7
            float r6 = r6 * r7
            int r6 = java.lang.Math.round(r6)
            androidx.recyclerview.widget.I r7 = r11.f602ls
            int r7 = r7.getMode()
            if (r7 != r5) goto L_0x020a
            androidx.recyclerview.widget.I r5 = r11.f602ls
            int r5 = r5.getTotalSpace()
            int r6 = java.lang.Math.min(r6, r5)
        L_0x020a:
            r11.mo4957R(r6)
            int r5 = r11.f603ms
            if (r5 != r1) goto L_0x0212
            goto L_0x025c
        L_0x0212:
            r5 = r3
        L_0x0213:
            if (r5 >= r2) goto L_0x025c
            android.view.View r6 = r11.getChildAt(r5)
            android.view.ViewGroup$LayoutParams r7 = r6.getLayoutParams()
            androidx.recyclerview.widget.xa r7 = (androidx.recyclerview.widget.C0600xa) r7
            boolean r8 = r7.f682Yk
            if (r8 == 0) goto L_0x0224
            goto L_0x0259
        L_0x0224:
            boolean r8 = r11.isLayoutRTL()
            if (r8 == 0) goto L_0x0244
            int r8 = r11.mOrientation
            if (r8 != r4) goto L_0x0244
            int r8 = r11.f599cs
            int r9 = r8 + -1
            androidx.recyclerview.widget.Ba r7 = r7.f681Xk
            int r7 = r7.mIndex
            int r9 = r9 - r7
            int r9 = -r9
            int r10 = r11.f603ms
            int r9 = r9 * r10
            int r8 = r8 - r4
            int r8 = r8 - r7
            int r7 = -r8
            int r7 = r7 * r1
            int r9 = r9 - r7
            r6.offsetLeftAndRight(r9)
            goto L_0x0259
        L_0x0244:
            androidx.recyclerview.widget.Ba r7 = r7.f681Xk
            int r7 = r7.mIndex
            int r8 = r11.f603ms
            int r8 = r8 * r7
            int r7 = r7 * r1
            int r9 = r11.mOrientation
            if (r9 != r4) goto L_0x0255
            int r8 = r8 - r7
            r6.offsetLeftAndRight(r8)
            goto L_0x0259
        L_0x0255:
            int r8 = r8 - r7
            r6.offsetTopAndBottom(r8)
        L_0x0259:
            int r5 = r5 + 1
            goto L_0x0213
        L_0x025c:
            int r1 = r11.getChildCount()
            if (r1 <= 0) goto L_0x0273
            boolean r1 = r11.mShouldReverseLayout
            if (r1 == 0) goto L_0x026d
            r11.m737a((androidx.recyclerview.widget.C0572ja) r12, (androidx.recyclerview.widget.C0582oa) r13, (boolean) r4)
            r11.m741b((androidx.recyclerview.widget.C0572ja) r12, (androidx.recyclerview.widget.C0582oa) r13, (boolean) r3)
            goto L_0x0273
        L_0x026d:
            r11.m741b((androidx.recyclerview.widget.C0572ja) r12, (androidx.recyclerview.widget.C0582oa) r13, (boolean) r4)
            r11.m737a((androidx.recyclerview.widget.C0572ja) r12, (androidx.recyclerview.widget.C0582oa) r13, (boolean) r3)
        L_0x0273:
            if (r14 == 0) goto L_0x029e
            boolean r14 = r13.mInPreLayout
            if (r14 != 0) goto L_0x029e
            int r14 = r11.f606ps
            if (r14 == 0) goto L_0x028f
            int r14 = r11.getChildCount()
            if (r14 <= 0) goto L_0x028f
            boolean r14 = r11.f610us
            if (r14 != 0) goto L_0x028d
            android.view.View r14 = r11.mo4961Uc()
            if (r14 == 0) goto L_0x028f
        L_0x028d:
            r14 = r4
            goto L_0x0290
        L_0x028f:
            r14 = r3
        L_0x0290:
            if (r14 == 0) goto L_0x029e
            java.lang.Runnable r14 = r11.f612ws
            r11.removeCallbacks(r14)
            boolean r14 = r11.mo4956Qc()
            if (r14 == 0) goto L_0x029e
            goto L_0x029f
        L_0x029e:
            r4 = r3
        L_0x029f:
            boolean r14 = r13.mInPreLayout
            if (r14 == 0) goto L_0x02a8
            androidx.recyclerview.widget.wa r14 = r11.mAnchorInfo
            r14.reset()
        L_0x02a8:
            boolean r14 = r0.mLayoutFromEnd
            r11.f607qs = r14
            boolean r14 = r11.isLayoutRTL()
            r11.f608rs = r14
            if (r4 == 0) goto L_0x02bc
            androidx.recyclerview.widget.wa r14 = r11.mAnchorInfo
            r14.reset()
            r11.m742c((androidx.recyclerview.widget.C0572ja) r12, (androidx.recyclerview.widget.C0582oa) r13, (boolean) r3)
        L_0x02bc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.m742c(androidx.recyclerview.widget.ja, androidx.recyclerview.widget.oa, boolean):void");
    }

    /* renamed from: k */
    private int m745k(C0582oa oaVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        return C0592ta.m911a(oaVar, this.f601ks, mo4952D(!this.f598Yr), mo4951C(!this.f598Yr), this, this.f598Yr);
    }

    /* renamed from: l */
    private int m746l(C0582oa oaVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        return C0592ta.m912a(oaVar, this.f601ks, mo4952D(!this.f598Yr), mo4951C(!this.f598Yr), this, this.f598Yr, this.mShouldReverseLayout);
    }

    /* renamed from: m */
    private int m747m(C0582oa oaVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        return C0592ta.m913b(oaVar, this.f601ks, mo4952D(!this.f598Yr), mo4951C(!this.f598Yr), this, this.f598Yr);
    }

    /* renamed from: tb */
    private int m748tb(int i) {
        if (getChildCount() != 0) {
            if ((i < mo4959Sc()) != this.mShouldReverseLayout) {
                return -1;
            }
            return 1;
        } else if (this.mShouldReverseLayout) {
            return 1;
        } else {
            return -1;
        }
    }

    /* renamed from: ub */
    private int m749ub(int i) {
        int X = this.f600js[0].mo4619X(i);
        for (int i2 = 1; i2 < this.f599cs; i2++) {
            int X2 = this.f600js[i2].mo4619X(i);
            if (X2 > X) {
                X = X2;
            }
        }
        return X;
    }

    /* renamed from: vb */
    private int m750vb(int i) {
        int Y = this.f600js[0].mo4620Y(i);
        for (int i2 = 1; i2 < this.f599cs; i2++) {
            int Y2 = this.f600js[i2].mo4620Y(i);
            if (Y2 < Y) {
                Y = Y2;
            }
        }
        return Y;
    }

    /* renamed from: wb */
    private boolean m751wb(int i) {
        if (this.mOrientation == 0) {
            if ((i == -1) != this.mShouldReverseLayout) {
                return true;
            }
            return false;
        }
        if (((i == -1) == this.mShouldReverseLayout) == isLayoutRTL()) {
            return true;
        }
        return false;
    }

    /* renamed from: xb */
    private void m752xb(int i) {
        C0603z zVar = this.mLayoutState;
        zVar.mLayoutDirection = i;
        int i2 = 1;
        if (this.mShouldReverseLayout != (i == -1)) {
            i2 = -1;
        }
        zVar.mItemDirection = i2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: C */
    public View mo4951C(boolean z) {
        int startAfterPadding = this.f601ks.getStartAfterPadding();
        int endAfterPadding = this.f601ks.getEndAfterPadding();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int decoratedStart = this.f601ks.getDecoratedStart(childAt);
            int decoratedEnd = this.f601ks.getDecoratedEnd(childAt);
            if (decoratedEnd > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedEnd <= endAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: D */
    public View mo4952D(boolean z) {
        int startAfterPadding = this.f601ks.getStartAfterPadding();
        int endAfterPadding = this.f601ks.getEndAfterPadding();
        int childCount = getChildCount();
        View view = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int decoratedStart = this.f601ks.getDecoratedStart(childAt);
            if (this.f601ks.getDecoratedEnd(childAt) > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedStart >= startAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Oc */
    public boolean mo4953Oc() {
        int X = this.f600js[0].mo4619X(RtlSpacingHelper.UNDEFINED);
        for (int i = 1; i < this.f599cs; i++) {
            if (this.f600js[i].mo4619X(RtlSpacingHelper.UNDEFINED) != X) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Pc */
    public boolean mo4954Pc() {
        int Y = this.f600js[0].mo4620Y(RtlSpacingHelper.UNDEFINED);
        for (int i = 1; i < this.f599cs; i++) {
            if (this.f600js[i].mo4620Y(RtlSpacingHelper.UNDEFINED) != Y) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: Q */
    public void mo4955Q(int i) {
        assertNotInLayoutOrScroll((String) null);
        if (i != this.f599cs) {
            mo4962Vc();
            this.f599cs = i;
            this.f604ns = new BitSet(this.f599cs);
            this.f600js = new C0524Ba[this.f599cs];
            for (int i2 = 0; i2 < this.f599cs; i2++) {
                this.f600js[i2] = new C0524Ba(this, i2);
            }
            requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Qc */
    public boolean mo4956Qc() {
        int i;
        int i2;
        if (getChildCount() == 0 || this.f606ps == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.mShouldReverseLayout) {
            i2 = mo4960Tc();
            i = mo4959Sc();
        } else {
            i2 = mo4959Sc();
            i = mo4960Tc();
        }
        if (i2 == 0 && mo4961Uc() != null) {
            this.f605os.clear();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        } else if (!this.f610us) {
            return false;
        } else {
            int i3 = this.mShouldReverseLayout ? -1 : 1;
            int i4 = i + 1;
            StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem a = this.f605os.mo5275a(i2, i4, i3, true);
            if (a == null) {
                this.f610us = false;
                this.f605os.mo5272U(i4);
                return false;
            }
            StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem a2 = this.f605os.mo5275a(i2, a.mPosition, i3 * -1, true);
            if (a2 == null) {
                this.f605os.mo5272U(a.mPosition);
            } else {
                this.f605os.mo5272U(a2.mPosition + 1);
            }
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: R */
    public void mo4957R(int i) {
        this.f603ms = i / this.f599cs;
        this.f609ss = View.MeasureSpec.makeMeasureSpec(i, this.f602ls.getMode());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Rc */
    public int mo4958Rc() {
        View view;
        if (this.mShouldReverseLayout) {
            view = mo4951C(true);
        } else {
            view = mo4952D(true);
        }
        if (view == null) {
            return -1;
        }
        return getPosition(view);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Sc */
    public int mo4959Sc() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Tc */
    public int mo4960Tc() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b3, code lost:
        if (r11 == r12) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c5, code lost:
        if (r11 == r12) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c9, code lost:
        r11 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x008b A[SYNTHETIC] */
    /* renamed from: Uc */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View mo4961Uc() {
        /*
            r13 = this;
            int r0 = r13.getChildCount()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r13.f599cs
            r2.<init>(r3)
            int r3 = r13.f599cs
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r13.mOrientation
            r5 = -1
            if (r3 != r1) goto L_0x0020
            boolean r3 = r13.isLayoutRTL()
            if (r3 == 0) goto L_0x0020
            r3 = r1
            goto L_0x0021
        L_0x0020:
            r3 = r5
        L_0x0021:
            boolean r6 = r13.mShouldReverseLayout
            if (r6 == 0) goto L_0x0027
            r6 = r5
            goto L_0x002b
        L_0x0027:
            int r0 = r0 + 1
            r6 = r0
            r0 = r4
        L_0x002b:
            if (r0 >= r6) goto L_0x002f
            r7 = r1
            goto L_0x0030
        L_0x002f:
            r7 = r5
        L_0x0030:
            if (r0 == r6) goto L_0x00eb
            android.view.View r8 = r13.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            androidx.recyclerview.widget.xa r9 = (androidx.recyclerview.widget.C0600xa) r9
            androidx.recyclerview.widget.Ba r10 = r9.f681Xk
            int r10 = r10.mIndex
            boolean r10 = r2.get(r10)
            if (r10 == 0) goto L_0x0093
            androidx.recyclerview.widget.Ba r10 = r9.f681Xk
            boolean r11 = r13.mShouldReverseLayout
            if (r11 == 0) goto L_0x006c
            int r11 = r10.mo4629ed()
            androidx.recyclerview.widget.I r12 = r13.f601ks
            int r12 = r12.getEndAfterPadding()
            if (r11 >= r12) goto L_0x0088
            java.util.ArrayList r11 = r10.f503At
            int r12 = r11.size()
            int r12 = r12 + r5
            java.lang.Object r11 = r11.get(r12)
            android.view.View r11 = (android.view.View) r11
            androidx.recyclerview.widget.xa r10 = r10.getLayoutParams(r11)
            boolean r10 = r10.f682Yk
            goto L_0x0086
        L_0x006c:
            int r11 = r10.mo4630fd()
            androidx.recyclerview.widget.I r12 = r13.f601ks
            int r12 = r12.getStartAfterPadding()
            if (r11 <= r12) goto L_0x0088
            java.util.ArrayList r11 = r10.f503At
            java.lang.Object r11 = r11.get(r4)
            android.view.View r11 = (android.view.View) r11
            androidx.recyclerview.widget.xa r10 = r10.getLayoutParams(r11)
            boolean r10 = r10.f682Yk
        L_0x0086:
            r10 = r10 ^ r1
            goto L_0x0089
        L_0x0088:
            r10 = r4
        L_0x0089:
            if (r10 == 0) goto L_0x008c
            return r8
        L_0x008c:
            androidx.recyclerview.widget.Ba r10 = r9.f681Xk
            int r10 = r10.mIndex
            r2.clear(r10)
        L_0x0093:
            boolean r10 = r9.f682Yk
            if (r10 == 0) goto L_0x0098
            goto L_0x00e8
        L_0x0098:
            int r10 = r0 + r7
            if (r10 == r6) goto L_0x00e8
            android.view.View r10 = r13.getChildAt(r10)
            boolean r11 = r13.mShouldReverseLayout
            if (r11 == 0) goto L_0x00b6
            androidx.recyclerview.widget.I r11 = r13.f601ks
            int r11 = r11.getDecoratedEnd(r8)
            androidx.recyclerview.widget.I r12 = r13.f601ks
            int r12 = r12.getDecoratedEnd(r10)
            if (r11 >= r12) goto L_0x00b3
            return r8
        L_0x00b3:
            if (r11 != r12) goto L_0x00c9
            goto L_0x00c7
        L_0x00b6:
            androidx.recyclerview.widget.I r11 = r13.f601ks
            int r11 = r11.getDecoratedStart(r8)
            androidx.recyclerview.widget.I r12 = r13.f601ks
            int r12 = r12.getDecoratedStart(r10)
            if (r11 <= r12) goto L_0x00c5
            return r8
        L_0x00c5:
            if (r11 != r12) goto L_0x00c9
        L_0x00c7:
            r11 = r1
            goto L_0x00ca
        L_0x00c9:
            r11 = r4
        L_0x00ca:
            if (r11 == 0) goto L_0x00e8
            android.view.ViewGroup$LayoutParams r10 = r10.getLayoutParams()
            androidx.recyclerview.widget.xa r10 = (androidx.recyclerview.widget.C0600xa) r10
            androidx.recyclerview.widget.Ba r9 = r9.f681Xk
            int r9 = r9.mIndex
            androidx.recyclerview.widget.Ba r10 = r10.f681Xk
            int r10 = r10.mIndex
            int r9 = r9 - r10
            if (r9 >= 0) goto L_0x00df
            r9 = r1
            goto L_0x00e0
        L_0x00df:
            r9 = r4
        L_0x00e0:
            if (r3 >= 0) goto L_0x00e4
            r10 = r1
            goto L_0x00e5
        L_0x00e4:
            r10 = r4
        L_0x00e5:
            if (r9 == r10) goto L_0x00e8
            return r8
        L_0x00e8:
            int r0 = r0 + r7
            goto L_0x0030
        L_0x00eb:
            r13 = 0
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.mo4961Uc():android.view.View");
    }

    /* renamed from: Vc */
    public void mo4962Vc() {
        this.f605os.clear();
        requestLayout();
    }

    public void assertNotInLayoutOrScroll(String str) {
        RecyclerView recyclerView;
        if (this.mPendingSavedState == null && (recyclerView = this.mRecyclerView) != null) {
            recyclerView.assertNotInLayoutOrScroll(str);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo4965b(C0582oa oaVar, C0598wa waVar) {
        if (!mo4964a(oaVar, waVar)) {
            int i = 0;
            if (!this.f607qs) {
                int itemCount = oaVar.getItemCount();
                int childCount = getChildCount();
                int i2 = 0;
                while (true) {
                    if (i2 < childCount) {
                        int position = getPosition(getChildAt(i2));
                        if (position >= 0 && position < itemCount) {
                            i = position;
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            } else {
                int itemCount2 = oaVar.getItemCount();
                int childCount2 = getChildCount();
                while (true) {
                    childCount2--;
                    if (childCount2 >= 0) {
                        int position2 = getPosition(getChildAt(childCount2));
                        if (position2 >= 0 && position2 < itemCount2) {
                            i = position2;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            waVar.mPosition = i;
            waVar.mOffset = RtlSpacingHelper.UNDEFINED;
        }
    }

    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    public PointF computeScrollVectorForPosition(int i) {
        int tb = m748tb(i);
        PointF pointF = new PointF();
        if (tb == 0) {
            return null;
        }
        if (this.mOrientation == 0) {
            pointF.x = (float) tb;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = (float) tb;
        }
        return pointF;
    }

    /* renamed from: d */
    public int mo4724d(C0582oa oaVar) {
        return m746l(oaVar);
    }

    /* renamed from: e */
    public void mo4727e(C0572ja jaVar, C0582oa oaVar) {
        m742c(jaVar, oaVar, true);
    }

    /* renamed from: f */
    public int mo4759f(C0582oa oaVar) {
        return m745k(oaVar);
    }

    /* renamed from: g */
    public int mo4728g(C0582oa oaVar) {
        return m746l(oaVar);
    }

    public C0560da generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new C0600xa(-2, -1);
        }
        return new C0600xa(-1, -2);
    }

    public C0560da generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new C0600xa(context, attributeSet);
    }

    /* renamed from: h */
    public int mo4733h(C0582oa oaVar) {
        return m747m(oaVar);
    }

    /* renamed from: i */
    public void mo4734i(C0582oa oaVar) {
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = RtlSpacingHelper.UNDEFINED;
        this.mPendingSavedState = null;
        this.mAnchorInfo.reset();
    }

    public boolean isAutoMeasureEnabled() {
        return this.f606ps != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public void offsetChildrenHorizontal(int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.offsetChildrenHorizontal(i);
        }
        for (int i2 = 0; i2 < this.f599cs; i2++) {
            C0524Ba ba = this.f600js[i2];
            int i3 = ba.f504Bt;
            if (i3 != Integer.MIN_VALUE) {
                ba.f504Bt = i3 + i;
            }
            int i4 = ba.f505Ct;
            if (i4 != Integer.MIN_VALUE) {
                ba.f505Ct = i4 + i;
            }
        }
    }

    public void offsetChildrenVertical(int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.offsetChildrenVertical(i);
        }
        for (int i2 = 0; i2 < this.f599cs; i2++) {
            C0524Ba ba = this.f600js[i2];
            int i3 = ba.f504Bt;
            if (i3 != Integer.MIN_VALUE) {
                ba.f504Bt = i3 + i;
            }
            int i4 = ba.f505Ct;
            if (i4 != Integer.MIN_VALUE) {
                ba.f505Ct = i4 + i;
            }
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.mRecyclerView;
        mo5021a(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
        if (getChildCount() > 0) {
            View D = mo4952D(false);
            View C = mo4951C(false);
            if (D != null && C != null) {
                int position = getPosition(D);
                int position2 = getPosition(C);
                if (position < position2) {
                    accessibilityEvent.setFromIndex(position);
                    accessibilityEvent.setToIndex(position2);
                    return;
                }
                accessibilityEvent.setFromIndex(position2);
                accessibilityEvent.setToIndex(position);
            }
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        int i;
        int i2;
        int i3;
        int[] iArr;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        savedState2.f613Wr = this.f597Wr;
        savedState2.mAnchorLayoutFromEnd = this.f607qs;
        savedState2.f614rs = this.f608rs;
        C0604za zaVar = this.f605os;
        if (zaVar == null || (iArr = zaVar.mData) == null) {
            savedState2.f619yt = 0;
        } else {
            savedState2.f620zt = iArr;
            savedState2.f619yt = savedState2.f620zt.length;
            savedState2.f615ut = zaVar.f688ut;
        }
        if (getChildCount() > 0) {
            if (this.f607qs) {
                i = mo4960Tc();
            } else {
                i = mo4959Sc();
            }
            savedState2.mAnchorPosition = i;
            savedState2.f616vt = mo4958Rc();
            int i4 = this.f599cs;
            savedState2.f617wt = i4;
            savedState2.f618xt = new int[i4];
            for (int i5 = 0; i5 < this.f599cs; i5++) {
                if (this.f607qs) {
                    i2 = this.f600js[i5].mo4619X(RtlSpacingHelper.UNDEFINED);
                    if (i2 != Integer.MIN_VALUE) {
                        i3 = this.f601ks.getEndAfterPadding();
                    } else {
                        savedState2.f618xt[i5] = i2;
                    }
                } else {
                    i2 = this.f600js[i5].mo4620Y(RtlSpacingHelper.UNDEFINED);
                    if (i2 != Integer.MIN_VALUE) {
                        i3 = this.f601ks.getStartAfterPadding();
                    } else {
                        savedState2.f618xt[i5] = i2;
                    }
                }
                i2 -= i3;
                savedState2.f618xt[i5] = i2;
            }
        } else {
            savedState2.mAnchorPosition = -1;
            savedState2.f616vt = -1;
            savedState2.f617wt = 0;
        }
        return savedState2;
    }

    public void onScrollStateChanged(int i) {
        if (i == 0) {
            mo4956Qc();
        }
    }

    public void scrollToPosition(int i) {
        SavedState savedState = this.mPendingSavedState;
        if (!(savedState == null || savedState.mAnchorPosition == i)) {
            savedState.mo4973Zc();
        }
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = RtlSpacingHelper.UNDEFINED;
        requestLayout();
    }

    public void setMeasuredDimension(Rect rect, int i, int i2) {
        int i3;
        int i4;
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (this.mOrientation == 1) {
            i4 = C0558ca.chooseSize(i2, rect.height() + paddingBottom, getMinimumHeight());
            i3 = C0558ca.chooseSize(i, (this.f603ms * this.f599cs) + paddingRight, getMinimumWidth());
        } else {
            i3 = C0558ca.chooseSize(i, rect.width() + paddingRight, getMinimumWidth());
            i4 = C0558ca.chooseSize(i2, (this.f603ms * this.f599cs) + paddingBottom, getMinimumHeight());
        }
        setMeasuredDimension(i3, i4);
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            assertNotInLayoutOrScroll((String) null);
            if (i != this.mOrientation) {
                this.mOrientation = i;
                C0536I i2 = this.f601ks;
                this.f601ks = this.f602ls;
                this.f602ls = i2;
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation.");
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll((String) null);
        SavedState savedState = this.mPendingSavedState;
        if (!(savedState == null || savedState.f613Wr == z)) {
            savedState.f613Wr = z;
        }
        this.f597Wr = z;
        requestLayout();
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0046  */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m743f(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.mShouldReverseLayout
            if (r0 == 0) goto L_0x0009
            int r0 = r6.mo4960Tc()
            goto L_0x000d
        L_0x0009:
            int r0 = r6.mo4959Sc()
        L_0x000d:
            r1 = 8
            if (r9 != r1) goto L_0x001b
            if (r7 >= r8) goto L_0x0016
            int r2 = r8 + 1
            goto L_0x001d
        L_0x0016:
            int r2 = r7 + 1
            r3 = r2
            r2 = r8
            goto L_0x001f
        L_0x001b:
            int r2 = r7 + r8
        L_0x001d:
            r3 = r2
            r2 = r7
        L_0x001f:
            androidx.recyclerview.widget.za r4 = r6.f605os
            r4.mo5274W(r2)
            r4 = 1
            if (r9 == r4) goto L_0x003e
            r5 = 2
            if (r9 == r5) goto L_0x0038
            if (r9 == r1) goto L_0x002d
            goto L_0x0043
        L_0x002d:
            androidx.recyclerview.widget.za r9 = r6.f605os
            r9.mo5279o(r7, r4)
            androidx.recyclerview.widget.za r7 = r6.f605os
            r7.mo5278n(r8, r4)
            goto L_0x0043
        L_0x0038:
            androidx.recyclerview.widget.za r9 = r6.f605os
            r9.mo5279o(r7, r8)
            goto L_0x0043
        L_0x003e:
            androidx.recyclerview.widget.za r9 = r6.f605os
            r9.mo5278n(r7, r8)
        L_0x0043:
            if (r3 > r0) goto L_0x0046
            return
        L_0x0046:
            boolean r7 = r6.mShouldReverseLayout
            if (r7 == 0) goto L_0x004f
            int r7 = r6.mo4959Sc()
            goto L_0x0053
        L_0x004f:
            int r7 = r6.mo4960Tc()
        L_0x0053:
            if (r2 > r7) goto L_0x0058
            r6.requestLayout()
        L_0x0058:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.m743f(int, int, int):void");
    }

    /* renamed from: g */
    private int m744g(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode);
        }
        return i;
    }

    /* renamed from: d */
    public void mo4725d(RecyclerView recyclerView, int i, int i2) {
        m743f(i, i2, 2);
    }

    /* renamed from: e */
    public int mo4726e(C0582oa oaVar) {
        return m747m(oaVar);
    }

    public C0560da generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new C0600xa((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new C0600xa(layoutParams);
    }

    /* renamed from: g */
    public void mo4729g(RecyclerView recyclerView) {
        this.f605os.clear();
        requestLayout();
    }

    /* renamed from: a */
    public void mo4716a(C0572ja jaVar, C0582oa oaVar, View view, C0363e eVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof C0600xa)) {
            super.mo5014a(view, eVar);
            return;
        }
        C0600xa xaVar = (C0600xa) layoutParams;
        int i = 1;
        int i2 = -1;
        if (this.mOrientation == 0) {
            C0524Ba ba = xaVar.f681Xk;
            if (ba != null) {
                i2 = ba.mIndex;
            }
            int i3 = i2;
            if (xaVar.f682Yk) {
                i = this.f599cs;
            }
            eVar.mo3858h(C0362d.obtain(i3, i, -1, -1, false, false));
            return;
        }
        C0524Ba ba2 = xaVar.f681Xk;
        if (ba2 != null) {
            i2 = ba2.mIndex;
        }
        int i4 = i2;
        if (xaVar.f682Yk) {
            i = this.f599cs;
        }
        eVar.mo3858h(C0362d.obtain(-1, -1, i4, i, false, false));
    }

    /* renamed from: b */
    public int mo4722b(C0572ja jaVar, C0582oa oaVar) {
        if (this.mOrientation == 0) {
            return this.f599cs;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.mAdapter == null || !canScrollVertically()) {
            return 1;
        }
        return this.mRecyclerView.mAdapter.getItemCount();
    }

    /* renamed from: b */
    private void m741b(C0572ja jaVar, C0582oa oaVar, boolean z) {
        int startAfterPadding;
        int vb = m750vb(Integer.MAX_VALUE);
        if (vb != Integer.MAX_VALUE && (startAfterPadding = vb - this.f601ks.getStartAfterPadding()) > 0) {
            int c = startAfterPadding - mo4966c(startAfterPadding, jaVar, oaVar);
            if (z && c > 0) {
                this.f601ks.offsetChildren(-c);
            }
        }
    }

    /* renamed from: b */
    private void m739b(int i, C0582oa oaVar) {
        int i2;
        int i3;
        int i4;
        C0603z zVar = this.mLayoutState;
        boolean z = false;
        zVar.mAvailable = 0;
        zVar.mCurrentPosition = i;
        if (!isSmoothScrolling() || (i4 = oaVar.f658gr) == -1) {
            i3 = 0;
            i2 = 0;
        } else {
            if (this.mShouldReverseLayout == (i4 < i)) {
                i3 = this.f601ks.getTotalSpace();
                i2 = 0;
            } else {
                i2 = this.f601ks.getTotalSpace();
                i3 = 0;
            }
        }
        if (getClipToPadding()) {
            this.mLayoutState.f685br = this.f601ks.getStartAfterPadding() - i2;
            this.mLayoutState.f686cr = this.f601ks.getEndAfterPadding() + i3;
        } else {
            this.mLayoutState.f686cr = this.f601ks.getEnd() + i3;
            this.mLayoutState.f685br = -i2;
        }
        C0603z zVar2 = this.mLayoutState;
        zVar2.f687dr = false;
        zVar2.mRecycle = true;
        if (this.f601ks.getMode() == 0 && this.f601ks.getEnd() == 0) {
            z = true;
        }
        zVar2.mInfinite = z;
    }

    /* renamed from: a */
    public int mo4711a(C0572ja jaVar, C0582oa oaVar) {
        if (this.mOrientation == 1) {
            return this.f599cs;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.mAdapter == null || !canScrollHorizontally()) {
            return 1;
        }
        return this.mRecyclerView.mAdapter.getItemCount();
    }

    /* renamed from: a */
    private void m737a(C0572ja jaVar, C0582oa oaVar, boolean z) {
        int endAfterPadding;
        int ub = m749ub(RtlSpacingHelper.UNDEFINED);
        if (ub != Integer.MIN_VALUE && (endAfterPadding = this.f601ks.getEndAfterPadding() - ub) > 0) {
            int i = endAfterPadding - (-mo4966c(-endAfterPadding, jaVar, oaVar));
            if (z && i > 0) {
                this.f601ks.offsetChildren(i);
            }
        }
    }

    /* renamed from: a */
    public void mo4714a(RecyclerView recyclerView, int i, int i2, int i3) {
        m743f(i, i2, 8);
    }

    /* renamed from: a */
    public void mo4715a(RecyclerView recyclerView, int i, int i2, Object obj) {
        m743f(i, i2, 4);
    }

    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: type inference failed for: r10v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r10v4 */
    /* renamed from: a */
    private int m733a(C0572ja jaVar, C0603z zVar, C0582oa oaVar) {
        int i;
        int i2;
        int i3;
        C0524Ba ba;
        int i4;
        int i5;
        int i6;
        int i7;
        C0600xa xaVar;
        boolean z;
        int i8;
        int i9;
        boolean z2;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        C0572ja jaVar2 = jaVar;
        C0603z zVar2 = zVar;
        ? r10 = 0;
        this.f604ns.set(0, this.f599cs, true);
        if (this.mLayoutState.mInfinite) {
            i = zVar2.mLayoutDirection == 1 ? Integer.MAX_VALUE : RtlSpacingHelper.UNDEFINED;
        } else {
            if (zVar2.mLayoutDirection == 1) {
                i15 = zVar2.f686cr + zVar2.mAvailable;
            } else {
                i15 = zVar2.f685br - zVar2.mAvailable;
            }
            i = i15;
        }
        m732V(zVar2.mLayoutDirection, i);
        if (this.mShouldReverseLayout) {
            i2 = this.f601ks.getEndAfterPadding();
        } else {
            i2 = this.f601ks.getStartAfterPadding();
        }
        int i16 = i2;
        boolean z3 = false;
        while (true) {
            int i17 = zVar2.mCurrentPosition;
            if (!((i17 < 0 || i17 >= oaVar.getItemCount()) ? r10 : true) || (!this.mLayoutState.mInfinite && this.f604ns.isEmpty())) {
                C0572ja jaVar3 = jaVar2;
                int i18 = r10;
            } else {
                View view = jaVar2.tryGetViewHolderForPositionByDeadline(zVar2.mCurrentPosition, r10, Long.MAX_VALUE).itemView;
                zVar2.mCurrentPosition += zVar2.mItemDirection;
                C0600xa xaVar2 = (C0600xa) view.getLayoutParams();
                int viewLayoutPosition = xaVar2.getViewLayoutPosition();
                int[] iArr = this.f605os.mData;
                int i19 = (iArr == null || viewLayoutPosition >= iArr.length) ? -1 : iArr[viewLayoutPosition];
                boolean z4 = i19 == -1 ? true : r10;
                if (z4) {
                    if (xaVar2.f682Yk) {
                        ba = this.f600js[r10];
                    } else {
                        if (m751wb(zVar2.mLayoutDirection)) {
                            i14 = this.f599cs - 1;
                            i13 = -1;
                            i12 = -1;
                        } else {
                            i13 = this.f599cs;
                            i12 = 1;
                            i14 = r10;
                        }
                        C0524Ba ba2 = null;
                        if (zVar2.mLayoutDirection == 1) {
                            int startAfterPadding = this.f601ks.getStartAfterPadding();
                            int i20 = Integer.MAX_VALUE;
                            while (i14 != i13) {
                                C0524Ba ba3 = this.f600js[i14];
                                int X = ba3.mo4619X(startAfterPadding);
                                if (X < i20) {
                                    ba2 = ba3;
                                    i20 = X;
                                }
                                i14 += i12;
                            }
                        } else {
                            int endAfterPadding = this.f601ks.getEndAfterPadding();
                            int i21 = RtlSpacingHelper.UNDEFINED;
                            while (i14 != i13) {
                                C0524Ba ba4 = this.f600js[i14];
                                int Y = ba4.mo4620Y(endAfterPadding);
                                if (Y > i21) {
                                    ba2 = ba4;
                                    i21 = Y;
                                }
                                i14 += i12;
                            }
                        }
                        ba = ba2;
                    }
                    C0604za zaVar = this.f605os;
                    zaVar.mo5271T(viewLayoutPosition);
                    zaVar.mData[viewLayoutPosition] = ba.mIndex;
                } else {
                    ba = this.f600js[i19];
                }
                C0524Ba ba5 = ba;
                xaVar2.f681Xk = ba5;
                if (zVar2.mLayoutDirection == 1) {
                    addView(view);
                } else {
                    addView(view, 0);
                }
                if (xaVar2.f682Yk) {
                    if (this.mOrientation == 1) {
                        m734a(view, this.f609ss, C0558ca.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop(), xaVar2.height, true), false);
                    } else {
                        m734a(view, C0558ca.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft(), xaVar2.width, true), this.f609ss, false);
                    }
                } else if (this.mOrientation == 1) {
                    m734a(view, C0558ca.getChildMeasureSpec(this.f603ms, getWidthMode(), 0, xaVar2.width, false), C0558ca.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop(), xaVar2.height, true), false);
                } else {
                    m734a(view, C0558ca.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft(), xaVar2.width, true), C0558ca.getChildMeasureSpec(this.f603ms, getHeightMode(), 0, xaVar2.height, false), false);
                }
                if (zVar2.mLayoutDirection == 1) {
                    if (xaVar2.f682Yk) {
                        i11 = m749ub(i16);
                    } else {
                        i11 = ba5.mo4619X(i16);
                    }
                    int decoratedMeasurement = this.f601ks.getDecoratedMeasurement(view) + i11;
                    if (z4 && xaVar2.f682Yk) {
                        StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = new StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem();
                        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.f622st = new int[this.f599cs];
                        for (int i22 = 0; i22 < this.f599cs; i22++) {
                            staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.f622st[i22] = i11 - this.f600js[i22].mo4619X(i11);
                        }
                        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.f621rt = -1;
                        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition = viewLayoutPosition;
                        this.f605os.mo5276a(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem);
                    }
                    i5 = i11;
                    i4 = decoratedMeasurement;
                } else {
                    if (xaVar2.f682Yk) {
                        i10 = m750vb(i16);
                    } else {
                        i10 = ba5.mo4620Y(i16);
                    }
                    int decoratedMeasurement2 = i10 - this.f601ks.getDecoratedMeasurement(view);
                    if (z4 && xaVar2.f682Yk) {
                        StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2 = new StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem();
                        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2.f622st = new int[this.f599cs];
                        for (int i23 = 0; i23 < this.f599cs; i23++) {
                            staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2.f622st[i23] = this.f600js[i23].mo4620Y(i10) - i10;
                        }
                        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2.f621rt = 1;
                        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2.mPosition = viewLayoutPosition;
                        this.f605os.mo5276a(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2);
                    }
                    i4 = i10;
                    i5 = decoratedMeasurement2;
                }
                if (xaVar2.f682Yk && zVar2.mItemDirection == -1) {
                    if (z4) {
                        this.f610us = true;
                    } else {
                        if (zVar2.mLayoutDirection == 1) {
                            z2 = mo4953Oc();
                        } else {
                            z2 = mo4954Pc();
                        }
                        if (!z2) {
                            StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem V = this.f605os.mo5273V(viewLayoutPosition);
                            if (V != null) {
                                V.f623tt = true;
                            }
                            this.f610us = true;
                        }
                    }
                }
                if (zVar2.mLayoutDirection == 1) {
                    if (xaVar2.f682Yk) {
                        int i24 = this.f599cs;
                        while (true) {
                            i24--;
                            if (i24 < 0) {
                                break;
                            }
                            this.f600js[i24].mo4626d(view);
                        }
                    } else {
                        xaVar2.f681Xk.mo4626d(view);
                    }
                } else if (xaVar2.f682Yk) {
                    int i25 = this.f599cs;
                    while (true) {
                        i25--;
                        if (i25 < 0) {
                            break;
                        }
                        this.f600js[i25].mo4628e(view);
                    }
                } else {
                    xaVar2.f681Xk.mo4628e(view);
                }
                if (!isLayoutRTL() || this.mOrientation != 1) {
                    if (xaVar2.f682Yk) {
                        i8 = this.f602ls.getStartAfterPadding();
                    } else {
                        i8 = (ba5.mIndex * this.f603ms) + this.f602ls.getStartAfterPadding();
                    }
                    i7 = i8;
                    i6 = this.f602ls.getDecoratedMeasurement(view) + i8;
                } else {
                    if (xaVar2.f682Yk) {
                        i9 = this.f602ls.getEndAfterPadding();
                    } else {
                        i9 = this.f602ls.getEndAfterPadding() - (((this.f599cs - 1) - ba5.mIndex) * this.f603ms);
                    }
                    i6 = i9;
                    i7 = i9 - this.f602ls.getDecoratedMeasurement(view);
                }
                if (this.mOrientation == 1) {
                    xaVar = xaVar2;
                    layoutDecoratedWithMargins(view, i7, i5, i6, i4);
                } else {
                    xaVar = xaVar2;
                    layoutDecoratedWithMargins(view, i5, i7, i4, i6);
                }
                if (xaVar.f682Yk) {
                    m732V(this.mLayoutState.mLayoutDirection, i);
                } else {
                    m735a(ba5, this.mLayoutState.mLayoutDirection, i);
                }
                C0572ja jaVar4 = jaVar;
                m738a(jaVar4, this.mLayoutState);
                if (this.mLayoutState.f687dr && view.hasFocusable()) {
                    if (xaVar.f682Yk) {
                        this.f604ns.clear();
                    } else {
                        z = false;
                        this.f604ns.set(ba5.mIndex, false);
                        jaVar2 = jaVar4;
                        r10 = z;
                        z3 = true;
                    }
                }
                z = false;
                jaVar2 = jaVar4;
                r10 = z;
                z3 = true;
            }
        }
        C0572ja jaVar32 = jaVar2;
        int i182 = r10;
        if (!z3) {
            m738a(jaVar32, this.mLayoutState);
        }
        if (this.mLayoutState.mLayoutDirection == -1) {
            i3 = this.f601ks.getStartAfterPadding() - m750vb(this.f601ks.getStartAfterPadding());
        } else {
            i3 = m749ub(this.f601ks.getEndAfterPadding()) - this.f601ks.getEndAfterPadding();
        }
        return i3 > 0 ? Math.min(zVar2.mAvailable, i3) : i182;
    }

    /* renamed from: b */
    private void m740b(C0572ja jaVar, int i) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.f601ks.getDecoratedEnd(childAt) <= i && this.f601ks.getTransformedEndWithDecoration(childAt) <= i) {
                C0600xa xaVar = (C0600xa) childAt.getLayoutParams();
                if (xaVar.f682Yk) {
                    int i2 = 0;
                    while (i2 < this.f599cs) {
                        if (this.f600js[i2].f503At.size() != 1) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    for (int i3 = 0; i3 < this.f599cs; i3++) {
                        this.f600js[i3].mo4633hd();
                    }
                } else if (xaVar.f681Xk.f503At.size() != 1) {
                    xaVar.f681Xk.mo4633hd();
                } else {
                    return;
                }
                mo5015a(childAt, jaVar);
            } else {
                return;
            }
        }
    }

    /* renamed from: b */
    public int mo4721b(int i, C0572ja jaVar, C0582oa oaVar) {
        return mo4966c(i, jaVar, oaVar);
    }

    /* renamed from: b */
    public void mo4750b(RecyclerView recyclerView, C0572ja jaVar) {
        mo5049f(recyclerView);
        removeCallbacks(this.f612ws);
        for (int i = 0; i < this.f599cs; i++) {
            this.f600js[i].clear();
        }
        recyclerView.requestLayout();
    }

    /* renamed from: c */
    public int mo4752c(C0582oa oaVar) {
        return m745k(oaVar);
    }

    /* renamed from: c */
    public void mo4723c(RecyclerView recyclerView, int i, int i2) {
        m743f(i, i2, 1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public int mo4966c(int i, C0572ja jaVar, C0582oa oaVar) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        mo4963a(i, oaVar);
        int a = m733a(jaVar, this.mLayoutState, oaVar);
        if (this.mLayoutState.mAvailable >= a) {
            i = i < 0 ? -a : a;
        }
        this.f601ks.offsetChildren(-i);
        this.f607qs = this.mShouldReverseLayout;
        C0603z zVar = this.mLayoutState;
        zVar.mAvailable = 0;
        m738a(jaVar, zVar);
        return i;
    }

    /* renamed from: a */
    private void m738a(C0572ja jaVar, C0603z zVar) {
        int i;
        int i2;
        if (zVar.mRecycle && !zVar.mInfinite) {
            if (zVar.mAvailable != 0) {
                int i3 = 1;
                if (zVar.mLayoutDirection == -1) {
                    int i4 = zVar.f685br;
                    int Y = this.f600js[0].mo4620Y(i4);
                    while (i3 < this.f599cs) {
                        int Y2 = this.f600js[i3].mo4620Y(i4);
                        if (Y2 > Y) {
                            Y = Y2;
                        }
                        i3++;
                    }
                    int i5 = i4 - Y;
                    if (i5 < 0) {
                        i2 = zVar.f686cr;
                    } else {
                        i2 = zVar.f686cr - Math.min(i5, zVar.mAvailable);
                    }
                    m736a(jaVar, i2);
                    return;
                }
                int i6 = zVar.f686cr;
                int X = this.f600js[0].mo4619X(i6);
                while (i3 < this.f599cs) {
                    int X2 = this.f600js[i3].mo4619X(i6);
                    if (X2 < X) {
                        X = X2;
                    }
                    i3++;
                }
                int i7 = X - zVar.f686cr;
                if (i7 < 0) {
                    i = zVar.f685br;
                } else {
                    i = Math.min(i7, zVar.mAvailable) + zVar.f685br;
                }
                m740b(jaVar, i);
            } else if (zVar.mLayoutDirection == -1) {
                m736a(jaVar, zVar.f686cr);
            } else {
                m740b(jaVar, zVar.f685br);
            }
        }
    }

    /* renamed from: a */
    private void m736a(C0572ja jaVar, int i) {
        int childCount = getChildCount() - 1;
        while (childCount >= 0) {
            View childAt = getChildAt(childCount);
            if (this.f601ks.getDecoratedStart(childAt) >= i && this.f601ks.getTransformedStartWithDecoration(childAt) >= i) {
                C0600xa xaVar = (C0600xa) childAt.getLayoutParams();
                if (xaVar.f682Yk) {
                    int i2 = 0;
                    while (i2 < this.f599cs) {
                        if (this.f600js[i2].f503At.size() != 1) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    for (int i3 = 0; i3 < this.f599cs; i3++) {
                        this.f600js[i3].mo4631gd();
                    }
                } else if (xaVar.f681Xk.f503At.size() != 1) {
                    xaVar.f681Xk.mo4631gd();
                } else {
                    return;
                }
                mo5015a(childAt, jaVar);
                childCount--;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    public int mo4710a(int i, C0572ja jaVar, C0582oa oaVar) {
        return mo4966c(i, jaVar, oaVar);
    }

    /* renamed from: a */
    public void mo4747a(RecyclerView recyclerView, C0582oa oaVar, int i) {
        C0529E e = new C0529E(recyclerView.getContext());
        e.setTargetPosition(i);
        mo5037b(e);
    }

    /* renamed from: a */
    public void mo4745a(int i, int i2, C0582oa oaVar, C0554aa aaVar) {
        int i3;
        int i4;
        if (this.mOrientation != 0) {
            i = i2;
        }
        if (getChildCount() != 0 && i != 0) {
            mo4963a(i, oaVar);
            int[] iArr = this.f611vs;
            if (iArr == null || iArr.length < this.f599cs) {
                this.f611vs = new int[this.f599cs];
            }
            int i5 = 0;
            for (int i6 = 0; i6 < this.f599cs; i6++) {
                C0603z zVar = this.mLayoutState;
                if (zVar.mItemDirection == -1) {
                    i4 = zVar.f685br;
                    i3 = this.f600js[i6].mo4620Y(i4);
                } else {
                    i4 = this.f600js[i6].mo4619X(zVar.f686cr);
                    i3 = this.mLayoutState.f686cr;
                }
                int i7 = i4 - i3;
                if (i7 >= 0) {
                    this.f611vs[i5] = i7;
                    i5++;
                }
            }
            Arrays.sort(this.f611vs, 0, i5);
            int i8 = 0;
            while (i8 < i5) {
                int i9 = this.mLayoutState.mCurrentPosition;
                if (i9 >= 0 && i9 < oaVar.getItemCount()) {
                    ((C0593u) aaVar).addPosition(this.mLayoutState.mCurrentPosition, this.f611vs[i8]);
                    C0603z zVar2 = this.mLayoutState;
                    zVar2.mCurrentPosition += zVar2.mItemDirection;
                    i8++;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4963a(int i, C0582oa oaVar) {
        int i2;
        int i3;
        if (i > 0) {
            i3 = mo4960Tc();
            i2 = 1;
        } else {
            i2 = -1;
            i3 = mo4959Sc();
        }
        this.mLayoutState.mRecycle = true;
        m739b(i3, oaVar);
        m752xb(i2);
        C0603z zVar = this.mLayoutState;
        zVar.mCurrentPosition = i3 + zVar.mItemDirection;
        zVar.mAvailable = Math.abs(i);
    }

    /* renamed from: a */
    public boolean mo4720a(C0560da daVar) {
        return daVar instanceof C0600xa;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003b, code lost:
        if (r9.mOrientation == 1) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0041, code lost:
        if (r9.mOrientation == 0) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004d, code lost:
        if (isLayoutRTL() == false) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0059, code lost:
        if (isLayoutRTL() == false) goto L_0x003d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x005e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x005f  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View mo4712a(android.view.View r10, int r11, androidx.recyclerview.widget.C0572ja r12, androidx.recyclerview.widget.C0582oa r13) {
        /*
            r9 = this;
            int r0 = r9.getChildCount()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            android.view.View r10 = r9.findContainingItemView(r10)
            if (r10 != 0) goto L_0x000f
            return r1
        L_0x000f:
            r9.m731On()
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = -1
            r3 = 1
            if (r11 == r3) goto L_0x0050
            r4 = 2
            if (r11 == r4) goto L_0x0044
            r4 = 17
            if (r11 == r4) goto L_0x003f
            r4 = 33
            if (r11 == r4) goto L_0x0039
            r4 = 66
            if (r11 == r4) goto L_0x0034
            r4 = 130(0x82, float:1.82E-43)
            if (r11 == r4) goto L_0x002c
            goto L_0x0032
        L_0x002c:
            int r11 = r9.mOrientation
            if (r11 != r3) goto L_0x0032
        L_0x0030:
            r11 = r3
            goto L_0x005c
        L_0x0032:
            r11 = r0
            goto L_0x005c
        L_0x0034:
            int r11 = r9.mOrientation
            if (r11 != 0) goto L_0x0032
            goto L_0x0030
        L_0x0039:
            int r11 = r9.mOrientation
            if (r11 != r3) goto L_0x0032
        L_0x003d:
            r11 = r2
            goto L_0x005c
        L_0x003f:
            int r11 = r9.mOrientation
            if (r11 != 0) goto L_0x0032
        L_0x0043:
            goto L_0x003d
        L_0x0044:
            int r11 = r9.mOrientation
            if (r11 != r3) goto L_0x0049
            goto L_0x0030
        L_0x0049:
            boolean r11 = r9.isLayoutRTL()
            if (r11 == 0) goto L_0x0030
            goto L_0x0054
        L_0x0050:
            int r11 = r9.mOrientation
            if (r11 != r3) goto L_0x0055
        L_0x0054:
            goto L_0x0043
        L_0x0055:
            boolean r11 = r9.isLayoutRTL()
            if (r11 == 0) goto L_0x003d
            goto L_0x0030
        L_0x005c:
            if (r11 != r0) goto L_0x005f
            return r1
        L_0x005f:
            android.view.ViewGroup$LayoutParams r0 = r10.getLayoutParams()
            androidx.recyclerview.widget.xa r0 = (androidx.recyclerview.widget.C0600xa) r0
            boolean r4 = r0.f682Yk
            androidx.recyclerview.widget.Ba r0 = r0.f681Xk
            if (r11 != r3) goto L_0x0070
            int r5 = r9.mo4960Tc()
            goto L_0x0074
        L_0x0070:
            int r5 = r9.mo4959Sc()
        L_0x0074:
            r9.m739b((int) r5, (androidx.recyclerview.widget.C0582oa) r13)
            r9.m752xb(r11)
            androidx.recyclerview.widget.z r6 = r9.mLayoutState
            int r7 = r6.mItemDirection
            int r7 = r7 + r5
            r6.mCurrentPosition = r7
            r7 = 1051372203(0x3eaaaaab, float:0.33333334)
            androidx.recyclerview.widget.I r8 = r9.f601ks
            int r8 = r8.getTotalSpace()
            float r8 = (float) r8
            float r8 = r8 * r7
            int r7 = (int) r8
            r6.mAvailable = r7
            androidx.recyclerview.widget.z r6 = r9.mLayoutState
            r6.f687dr = r3
            r7 = 0
            r6.mRecycle = r7
            r9.m733a((androidx.recyclerview.widget.C0572ja) r12, (androidx.recyclerview.widget.C0603z) r6, (androidx.recyclerview.widget.C0582oa) r13)
            boolean r12 = r9.mShouldReverseLayout
            r9.f607qs = r12
            if (r4 != 0) goto L_0x00a8
            android.view.View r12 = r0.mo4634p(r5, r11)
            if (r12 == 0) goto L_0x00a8
            if (r12 == r10) goto L_0x00a8
            return r12
        L_0x00a8:
            boolean r12 = r9.m751wb(r11)
            if (r12 == 0) goto L_0x00c3
            int r12 = r9.f599cs
            int r12 = r12 - r3
        L_0x00b1:
            if (r12 < 0) goto L_0x00d8
            androidx.recyclerview.widget.Ba[] r13 = r9.f600js
            r13 = r13[r12]
            android.view.View r13 = r13.mo4634p(r5, r11)
            if (r13 == 0) goto L_0x00c0
            if (r13 == r10) goto L_0x00c0
            return r13
        L_0x00c0:
            int r12 = r12 + -1
            goto L_0x00b1
        L_0x00c3:
            r12 = r7
        L_0x00c4:
            int r13 = r9.f599cs
            if (r12 >= r13) goto L_0x00d8
            androidx.recyclerview.widget.Ba[] r13 = r9.f600js
            r13 = r13[r12]
            android.view.View r13 = r13.mo4634p(r5, r11)
            if (r13 == 0) goto L_0x00d5
            if (r13 == r10) goto L_0x00d5
            return r13
        L_0x00d5:
            int r12 = r12 + 1
            goto L_0x00c4
        L_0x00d8:
            boolean r12 = r9.f597Wr
            r12 = r12 ^ r3
            if (r11 != r2) goto L_0x00df
            r13 = r3
            goto L_0x00e0
        L_0x00df:
            r13 = r7
        L_0x00e0:
            if (r12 != r13) goto L_0x00e4
            r12 = r3
            goto L_0x00e5
        L_0x00e4:
            r12 = r7
        L_0x00e5:
            if (r4 != 0) goto L_0x00fb
            if (r12 == 0) goto L_0x00ee
            int r13 = r0.mo4624cd()
            goto L_0x00f2
        L_0x00ee:
            int r13 = r0.mo4627dd()
        L_0x00f2:
            android.view.View r13 = r9.findViewByPosition(r13)
            if (r13 == 0) goto L_0x00fb
            if (r13 == r10) goto L_0x00fb
            return r13
        L_0x00fb:
            boolean r11 = r9.m751wb(r11)
            if (r11 == 0) goto L_0x012a
            int r11 = r9.f599cs
            int r11 = r11 - r3
        L_0x0104:
            if (r11 < 0) goto L_0x014d
            int r13 = r0.mIndex
            if (r11 != r13) goto L_0x010b
            goto L_0x0127
        L_0x010b:
            if (r12 == 0) goto L_0x0116
            androidx.recyclerview.widget.Ba[] r13 = r9.f600js
            r13 = r13[r11]
            int r13 = r13.mo4624cd()
            goto L_0x011e
        L_0x0116:
            androidx.recyclerview.widget.Ba[] r13 = r9.f600js
            r13 = r13[r11]
            int r13 = r13.mo4627dd()
        L_0x011e:
            android.view.View r13 = r9.findViewByPosition(r13)
            if (r13 == 0) goto L_0x0127
            if (r13 == r10) goto L_0x0127
            return r13
        L_0x0127:
            int r11 = r11 + -1
            goto L_0x0104
        L_0x012a:
            int r11 = r9.f599cs
            if (r7 >= r11) goto L_0x014d
            if (r12 == 0) goto L_0x0139
            androidx.recyclerview.widget.Ba[] r11 = r9.f600js
            r11 = r11[r7]
            int r11 = r11.mo4624cd()
            goto L_0x0141
        L_0x0139:
            androidx.recyclerview.widget.Ba[] r11 = r9.f600js
            r11 = r11[r7]
            int r11 = r11.mo4627dd()
        L_0x0141:
            android.view.View r11 = r9.findViewByPosition(r11)
            if (r11 == 0) goto L_0x014a
            if (r11 == r10) goto L_0x014a
            return r11
        L_0x014a:
            int r7 = r7 + 1
            goto L_0x012a
        L_0x014d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.mo4712a(android.view.View, int, androidx.recyclerview.widget.ja, androidx.recyclerview.widget.oa):android.view.View");
    }

    /* renamed from: a */
    private void m735a(C0524Ba ba, int i, int i2) {
        int i3 = ba.f506Dt;
        if (i == -1) {
            int i4 = ba.f504Bt;
            if (i4 == Integer.MIN_VALUE) {
                ba.mo4623bd();
                i4 = ba.f504Bt;
            }
            if (i4 + i3 <= i2) {
                this.f604ns.set(ba.mIndex, false);
                return;
            }
            return;
        }
        int i5 = ba.f505Ct;
        if (i5 == Integer.MIN_VALUE) {
            ba.mo4621ad();
            i5 = ba.f505Ct;
        }
        if (i5 - i3 >= i2) {
            this.f604ns.set(ba.mIndex, false);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo4964a(C0582oa oaVar, C0598wa waVar) {
        int i;
        int i2;
        int i3;
        boolean z = false;
        if (!oaVar.mInPreLayout && (i = this.mPendingScrollPosition) != -1) {
            if (i < 0 || i >= oaVar.getItemCount()) {
                this.mPendingScrollPosition = -1;
                this.mPendingScrollPositionOffset = RtlSpacingHelper.UNDEFINED;
            } else {
                SavedState savedState = this.mPendingSavedState;
                if (savedState == null || savedState.mAnchorPosition == -1 || savedState.f617wt < 1) {
                    View findViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                    if (findViewByPosition != null) {
                        if (this.mShouldReverseLayout) {
                            i2 = mo4960Tc();
                        } else {
                            i2 = mo4959Sc();
                        }
                        waVar.mPosition = i2;
                        if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                            if (waVar.mLayoutFromEnd) {
                                waVar.mOffset = (this.f601ks.getEndAfterPadding() - this.mPendingScrollPositionOffset) - this.f601ks.getDecoratedEnd(findViewByPosition);
                            } else {
                                waVar.mOffset = (this.f601ks.getStartAfterPadding() + this.mPendingScrollPositionOffset) - this.f601ks.getDecoratedStart(findViewByPosition);
                            }
                            return true;
                        } else if (this.f601ks.getDecoratedMeasurement(findViewByPosition) > this.f601ks.getTotalSpace()) {
                            if (waVar.mLayoutFromEnd) {
                                i3 = this.f601ks.getEndAfterPadding();
                            } else {
                                i3 = this.f601ks.getStartAfterPadding();
                            }
                            waVar.mOffset = i3;
                            return true;
                        } else {
                            int decoratedStart = this.f601ks.getDecoratedStart(findViewByPosition) - this.f601ks.getStartAfterPadding();
                            if (decoratedStart < 0) {
                                waVar.mOffset = -decoratedStart;
                                return true;
                            }
                            int endAfterPadding = this.f601ks.getEndAfterPadding() - this.f601ks.getDecoratedEnd(findViewByPosition);
                            if (endAfterPadding < 0) {
                                waVar.mOffset = endAfterPadding;
                                return true;
                            }
                            waVar.mOffset = RtlSpacingHelper.UNDEFINED;
                        }
                    } else {
                        waVar.mPosition = this.mPendingScrollPosition;
                        int i4 = this.mPendingScrollPositionOffset;
                        if (i4 == Integer.MIN_VALUE) {
                            if (m748tb(waVar.mPosition) == 1) {
                                z = true;
                            }
                            waVar.mLayoutFromEnd = z;
                            waVar.assignCoordinateFromPadding();
                        } else if (waVar.mLayoutFromEnd) {
                            waVar.mOffset = waVar.this$0.f601ks.getEndAfterPadding() - i4;
                        } else {
                            waVar.mOffset = waVar.this$0.f601ks.getStartAfterPadding() + i4;
                        }
                        waVar.f675pt = true;
                    }
                } else {
                    waVar.mOffset = RtlSpacingHelper.UNDEFINED;
                    waVar.mPosition = this.mPendingScrollPosition;
                }
                return true;
            }
        }
        return false;
    }
}
