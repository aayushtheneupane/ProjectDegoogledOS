package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.recyclerview.widget.ja */
public final class C0572ja {

    /* renamed from: Rs */
    private final List f643Rs = Collections.unmodifiableList(this.mAttachedScrap);

    /* renamed from: Ss */
    private int f644Ss = 2;
    final ArrayList mAttachedScrap = new ArrayList();
    final ArrayList mCachedViews = new ArrayList();
    ArrayList mChangedScrap = null;
    C0570ia mRecyclerPool;
    int mViewCacheMax = 2;
    final /* synthetic */ RecyclerView this$0;

    public C0572ja(RecyclerView recyclerView) {
        this.this$0 = recyclerView;
    }

    /* renamed from: a */
    private void m887a(ViewGroup viewGroup, boolean z) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof ViewGroup) {
                m887a((ViewGroup) childAt, true);
            }
        }
        if (z) {
            if (viewGroup.getVisibility() == 4) {
                viewGroup.setVisibility(0);
                viewGroup.setVisibility(4);
                return;
            }
            int visibility = viewGroup.getVisibility();
            viewGroup.setVisibility(4);
            viewGroup.setVisibility(visibility);
        }
    }

    public void clear() {
        this.mAttachedScrap.clear();
        recycleAndClearCachedViews();
    }

    public int convertPreLayoutPositionToPostLayout(int i) {
        if (i < 0 || i >= this.this$0.mState.getItemCount()) {
            throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + this.this$0.mState.getItemCount() + this.this$0.mo4815Vb());
        }
        RecyclerView recyclerView = this.this$0;
        if (!recyclerView.mState.mInPreLayout) {
            return i;
        }
        return recyclerView.mAdapterHelper.findPositionOffset(i, 0);
    }

    /* access modifiers changed from: package-private */
    public C0570ia getRecycledViewPool() {
        if (this.mRecyclerPool == null) {
            this.mRecyclerPool = new C0570ia();
        }
        return this.mRecyclerPool;
    }

    public List getScrapList() {
        return this.f643Rs;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: l */
    public void mo5146l(C0586qa qaVar) {
        boolean z;
        boolean z2 = false;
        if (qaVar.isScrap() || qaVar.itemView.getParent() != null) {
            StringBuilder Pa = C0632a.m1011Pa("Scrapped or attached views may not be recycled. isScrap:");
            Pa.append(qaVar.isScrap());
            Pa.append(" isAttached:");
            if (qaVar.itemView.getParent() != null) {
                z2 = true;
            }
            Pa.append(z2);
            Pa.append(this.this$0.mo4815Vb());
            throw new IllegalArgumentException(Pa.toString());
        } else if (qaVar.isTmpDetached()) {
            throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + qaVar + this.this$0.mo4815Vb());
        } else if (!qaVar.shouldIgnore()) {
            boolean Wc = qaVar.mo5190Wc();
            C0543P p = this.this$0.mAdapter;
            if (p != null && Wc) {
                p.mo4802e(qaVar);
            }
            if (qaVar.isRecyclable()) {
                if (this.mViewCacheMax <= 0 || qaVar.hasAnyOfTheFlags(526)) {
                    z = false;
                } else {
                    int size = this.mCachedViews.size();
                    if (size >= this.mViewCacheMax && size > 0) {
                        recycleCachedViewAt(0);
                        size--;
                    }
                    if (RecyclerView.f562Di && size > 0 && !this.this$0.mPrefetchRegistry.lastPrefetchIncludedPosition(qaVar.mPosition)) {
                        do {
                            size--;
                            if (size < 0) {
                                break;
                            }
                        } while (this.this$0.mPrefetchRegistry.lastPrefetchIncludedPosition(((C0586qa) this.mCachedViews.get(size)).mPosition));
                        size++;
                    }
                    this.mCachedViews.add(size, qaVar);
                    z = true;
                }
                if (!z) {
                    mo5141a(qaVar, true);
                    z2 = true;
                }
            } else {
                z = false;
            }
            this.this$0.mViewInfoStore.mo4698F(qaVar);
            if (!z && !z2 && Wc) {
                qaVar.mOwnerRecyclerView = null;
            }
        } else {
            StringBuilder Pa2 = C0632a.m1011Pa("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
            Pa2.append(this.this$0.mo4815Vb());
            throw new IllegalArgumentException(Pa2.toString());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: m */
    public void mo5147m(C0586qa qaVar) {
        if (qaVar.f667ct) {
            this.mChangedScrap.remove(qaVar);
        } else {
            this.mAttachedScrap.remove(qaVar);
        }
        qaVar.f666bt = null;
        qaVar.f667ct = false;
        qaVar.clearReturnedFromScrapFlag();
    }

    /* access modifiers changed from: package-private */
    public void quickRecycleScrapView(View view) {
        C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        childViewHolderInt.f666bt = null;
        childViewHolderInt.f667ct = false;
        childViewHolderInt.clearReturnedFromScrapFlag();
        mo5146l(childViewHolderInt);
    }

    /* access modifiers changed from: package-private */
    public void recycleAndClearCachedViews() {
        for (int size = this.mCachedViews.size() - 1; size >= 0; size--) {
            recycleCachedViewAt(size);
        }
        this.mCachedViews.clear();
        if (RecyclerView.f562Di) {
            C0593u uVar = this.this$0.mPrefetchRegistry;
            int[] iArr = uVar.mPrefetchArray;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            uVar.mCount = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void recycleCachedViewAt(int i) {
        mo5141a((C0586qa) this.mCachedViews.get(i), true);
        this.mCachedViews.remove(i);
    }

    public void recycleView(View view) {
        C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.isTmpDetached()) {
            this.this$0.removeDetachedView(view, false);
        }
        if (childViewHolderInt.isScrap()) {
            childViewHolderInt.unScrap();
        } else if (childViewHolderInt.wasReturnedFromScrap()) {
            childViewHolderInt.clearReturnedFromScrapFlag();
        }
        mo5146l(childViewHolderInt);
        if (this.this$0.mItemAnimator != null && !childViewHolderInt.isRecyclable()) {
            this.this$0.mItemAnimator.mo5179w(childViewHolderInt);
        }
    }

    /* access modifiers changed from: package-private */
    public void scrapView(View view) {
        C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (!childViewHolderInt.hasAnyOfTheFlags(12) && childViewHolderInt.isUpdated() && !this.this$0.mo4830a(childViewHolderInt)) {
            if (this.mChangedScrap == null) {
                this.mChangedScrap = new ArrayList();
            }
            childViewHolderInt.mo5193a(this, true);
            this.mChangedScrap.add(childViewHolderInt);
        } else if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || this.this$0.mAdapter.hasStableIds()) {
            childViewHolderInt.mo5193a(this, false);
            this.mAttachedScrap.add(childViewHolderInt);
        } else {
            StringBuilder Pa = C0632a.m1011Pa("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
            Pa.append(this.this$0.mo4815Vb());
            throw new IllegalArgumentException(Pa.toString());
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c8, code lost:
        r7.addFlags(32);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01ab, code lost:
        if (r7.getItemViewType() != 0) goto L_0x01cb;
     */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0217  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02e3  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x031b  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0330  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x03a7  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x03c6  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x03ed  */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x03f0  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x048b  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x0499  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0097  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.recyclerview.widget.C0586qa tryGetViewHolderForPositionByDeadline(int r18, boolean r19, long r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            if (r1 < 0) goto L_0x04bd
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            androidx.recyclerview.widget.oa r2 = r2.mState
            int r2 = r2.getItemCount()
            if (r1 >= r2) goto L_0x04bd
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            androidx.recyclerview.widget.oa r2 = r2.mState
            boolean r2 = r2.mInPreLayout
            r3 = 32
            r8 = 0
            r9 = 1
            r10 = 0
            if (r2 == 0) goto L_0x0092
            java.util.ArrayList r2 = r0.mChangedScrap
            if (r2 == 0) goto L_0x008d
            int r2 = r2.size()
            if (r2 != 0) goto L_0x0028
            goto L_0x008d
        L_0x0028:
            r4 = r10
        L_0x0029:
            if (r4 >= r2) goto L_0x0046
            java.util.ArrayList r5 = r0.mChangedScrap
            java.lang.Object r5 = r5.get(r4)
            androidx.recyclerview.widget.qa r5 = (androidx.recyclerview.widget.C0586qa) r5
            boolean r6 = r5.wasReturnedFromScrap()
            if (r6 != 0) goto L_0x0043
            int r6 = r5.getLayoutPosition()
            if (r6 != r1) goto L_0x0043
            r5.addFlags(r3)
            goto L_0x008e
        L_0x0043:
            int r4 = r4 + 1
            goto L_0x0029
        L_0x0046:
            androidx.recyclerview.widget.RecyclerView r4 = r0.this$0
            androidx.recyclerview.widget.P r4 = r4.mAdapter
            boolean r4 = r4.hasStableIds()
            if (r4 == 0) goto L_0x008d
            androidx.recyclerview.widget.RecyclerView r4 = r0.this$0
            androidx.recyclerview.widget.b r4 = r4.mAdapterHelper
            int r4 = r4.findPositionOffset(r1, r10)
            if (r4 <= 0) goto L_0x008d
            androidx.recyclerview.widget.RecyclerView r5 = r0.this$0
            androidx.recyclerview.widget.P r5 = r5.mAdapter
            int r5 = r5.getItemCount()
            if (r4 >= r5) goto L_0x008d
            androidx.recyclerview.widget.RecyclerView r5 = r0.this$0
            androidx.recyclerview.widget.P r5 = r5.mAdapter
            long r4 = r5.getItemId(r4)
            r6 = r10
        L_0x006d:
            if (r6 >= r2) goto L_0x008d
            java.util.ArrayList r7 = r0.mChangedScrap
            java.lang.Object r7 = r7.get(r6)
            androidx.recyclerview.widget.qa r7 = (androidx.recyclerview.widget.C0586qa) r7
            boolean r11 = r7.wasReturnedFromScrap()
            if (r11 != 0) goto L_0x008a
            long r11 = r7.getItemId()
            int r11 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r11 != 0) goto L_0x008a
            r7.addFlags(r3)
            r5 = r7
            goto L_0x008e
        L_0x008a:
            int r6 = r6 + 1
            goto L_0x006d
        L_0x008d:
            r5 = r8
        L_0x008e:
            if (r5 == 0) goto L_0x0093
            r2 = r9
            goto L_0x0094
        L_0x0092:
            r5 = r8
        L_0x0093:
            r2 = r10
        L_0x0094:
            r4 = -1
            if (r5 != 0) goto L_0x0217
            java.util.ArrayList r5 = r0.mAttachedScrap
            int r5 = r5.size()
            r6 = r10
        L_0x009e:
            if (r6 >= r5) goto L_0x00d0
            java.util.ArrayList r7 = r0.mAttachedScrap
            java.lang.Object r7 = r7.get(r6)
            androidx.recyclerview.widget.qa r7 = (androidx.recyclerview.widget.C0586qa) r7
            boolean r11 = r7.wasReturnedFromScrap()
            if (r11 != 0) goto L_0x00cd
            int r11 = r7.getLayoutPosition()
            if (r11 != r1) goto L_0x00cd
            boolean r11 = r7.isInvalid()
            if (r11 != 0) goto L_0x00cd
            androidx.recyclerview.widget.RecyclerView r11 = r0.this$0
            androidx.recyclerview.widget.oa r11 = r11.mState
            boolean r11 = r11.mInPreLayout
            if (r11 != 0) goto L_0x00c8
            boolean r11 = r7.isRemoved()
            if (r11 != 0) goto L_0x00cd
        L_0x00c8:
            r7.addFlags(r3)
            goto L_0x017b
        L_0x00cd:
            int r6 = r6 + 1
            goto L_0x009e
        L_0x00d0:
            if (r19 != 0) goto L_0x014c
            androidx.recyclerview.widget.RecyclerView r5 = r0.this$0
            androidx.recyclerview.widget.d r5 = r5.mChildHelper
            java.util.List r6 = r5.mHiddenViews
            int r6 = r6.size()
            r7 = r10
        L_0x00dd:
            if (r7 >= r6) goto L_0x0103
            java.util.List r11 = r5.mHiddenViews
            java.lang.Object r11 = r11.get(r7)
            android.view.View r11 = (android.view.View) r11
            androidx.recyclerview.widget.N r12 = r5.mCallback
            androidx.recyclerview.widget.qa r12 = r12.getChildViewHolder(r11)
            int r13 = r12.getLayoutPosition()
            if (r13 != r1) goto L_0x0100
            boolean r13 = r12.isInvalid()
            if (r13 != 0) goto L_0x0100
            boolean r12 = r12.isRemoved()
            if (r12 != 0) goto L_0x0100
            goto L_0x0104
        L_0x0100:
            int r7 = r7 + 1
            goto L_0x00dd
        L_0x0103:
            r11 = r8
        L_0x0104:
            if (r11 == 0) goto L_0x014c
            androidx.recyclerview.widget.qa r5 = androidx.recyclerview.widget.RecyclerView.getChildViewHolderInt(r11)
            androidx.recyclerview.widget.RecyclerView r6 = r0.this$0
            androidx.recyclerview.widget.d r6 = r6.mChildHelper
            r6.unhide(r11)
            androidx.recyclerview.widget.RecyclerView r6 = r0.this$0
            androidx.recyclerview.widget.d r6 = r6.mChildHelper
            int r6 = r6.indexOfChild(r11)
            if (r6 == r4) goto L_0x012c
            androidx.recyclerview.widget.RecyclerView r7 = r0.this$0
            androidx.recyclerview.widget.d r7 = r7.mChildHelper
            r7.detachViewFromParent(r6)
            r0.scrapView(r11)
            r6 = 8224(0x2020, float:1.1524E-41)
            r5.addFlags(r6)
            r7 = r5
            goto L_0x017b
        L_0x012c:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "layout index should not be -1 after unhiding a view:"
            r2.append(r3)
            r2.append(r5)
            androidx.recyclerview.widget.RecyclerView r0 = r0.this$0
            java.lang.String r0 = r0.mo4815Vb()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x014c:
            java.util.ArrayList r5 = r0.mCachedViews
            int r5 = r5.size()
            r6 = r10
        L_0x0153:
            if (r6 >= r5) goto L_0x017a
            java.util.ArrayList r7 = r0.mCachedViews
            java.lang.Object r7 = r7.get(r6)
            androidx.recyclerview.widget.qa r7 = (androidx.recyclerview.widget.C0586qa) r7
            boolean r11 = r7.isInvalid()
            if (r11 != 0) goto L_0x0177
            int r11 = r7.getLayoutPosition()
            if (r11 != r1) goto L_0x0177
            boolean r11 = r7.mo5191Xc()
            if (r11 != 0) goto L_0x0177
            if (r19 != 0) goto L_0x017b
            java.util.ArrayList r5 = r0.mCachedViews
            r5.remove(r6)
            goto L_0x017b
        L_0x0177:
            int r6 = r6 + 1
            goto L_0x0153
        L_0x017a:
            r7 = r8
        L_0x017b:
            if (r7 == 0) goto L_0x0218
            boolean r5 = r7.isRemoved()
            if (r5 == 0) goto L_0x018a
            androidx.recyclerview.widget.RecyclerView r5 = r0.this$0
            androidx.recyclerview.widget.oa r5 = r5.mState
            boolean r5 = r5.mInPreLayout
            goto L_0x01ce
        L_0x018a:
            int r5 = r7.mPosition
            if (r5 < 0) goto L_0x01f7
            androidx.recyclerview.widget.RecyclerView r6 = r0.this$0
            androidx.recyclerview.widget.P r6 = r6.mAdapter
            int r6 = r6.getItemCount()
            if (r5 >= r6) goto L_0x01f7
            androidx.recyclerview.widget.RecyclerView r5 = r0.this$0
            androidx.recyclerview.widget.oa r6 = r5.mState
            boolean r6 = r6.mInPreLayout
            if (r6 != 0) goto L_0x01ae
            androidx.recyclerview.widget.P r5 = r5.mAdapter
            int r6 = r7.mPosition
            r5.getItemViewType(r6)
            int r5 = r7.getItemViewType()
            if (r5 == 0) goto L_0x01ae
            goto L_0x01cb
        L_0x01ae:
            androidx.recyclerview.widget.RecyclerView r5 = r0.this$0
            androidx.recyclerview.widget.P r5 = r5.mAdapter
            boolean r5 = r5.hasStableIds()
            if (r5 == 0) goto L_0x01cd
            long r5 = r7.getItemId()
            androidx.recyclerview.widget.RecyclerView r11 = r0.this$0
            androidx.recyclerview.widget.P r11 = r11.mAdapter
            int r12 = r7.mPosition
            long r11 = r11.getItemId(r12)
            int r5 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r5 != 0) goto L_0x01cb
            goto L_0x01cd
        L_0x01cb:
            r5 = r10
            goto L_0x01ce
        L_0x01cd:
            r5 = r9
        L_0x01ce:
            if (r5 != 0) goto L_0x01f5
            if (r19 != 0) goto L_0x01f3
            r5 = 4
            r7.addFlags(r5)
            boolean r5 = r7.isScrap()
            if (r5 == 0) goto L_0x01e7
            androidx.recyclerview.widget.RecyclerView r5 = r0.this$0
            android.view.View r6 = r7.itemView
            r5.removeDetachedView(r6, r10)
            r7.unScrap()
            goto L_0x01f0
        L_0x01e7:
            boolean r5 = r7.wasReturnedFromScrap()
            if (r5 == 0) goto L_0x01f0
            r7.clearReturnedFromScrapFlag()
        L_0x01f0:
            r0.mo5146l(r7)
        L_0x01f3:
            r7 = r8
            goto L_0x0218
        L_0x01f5:
            r2 = r9
            goto L_0x0218
        L_0x01f7:
            java.lang.IndexOutOfBoundsException r1 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Inconsistency detected. Invalid view holder adapter position"
            r2.append(r3)
            r2.append(r7)
            androidx.recyclerview.widget.RecyclerView r0 = r0.this$0
            java.lang.String r0 = r0.mo4815Vb()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x0217:
            r7 = r5
        L_0x0218:
            r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            if (r7 != 0) goto L_0x03a7
            androidx.recyclerview.widget.RecyclerView r5 = r0.this$0
            androidx.recyclerview.widget.b r5 = r5.mAdapterHelper
            int r5 = r5.findPositionOffset(r1, r10)
            if (r5 < 0) goto L_0x036f
            androidx.recyclerview.widget.RecyclerView r6 = r0.this$0
            androidx.recyclerview.widget.P r6 = r6.mAdapter
            int r6 = r6.getItemCount()
            if (r5 >= r6) goto L_0x036f
            androidx.recyclerview.widget.RecyclerView r6 = r0.this$0
            androidx.recyclerview.widget.P r6 = r6.mAdapter
            r6.getItemViewType(r5)
            androidx.recyclerview.widget.RecyclerView r6 = r0.this$0
            androidx.recyclerview.widget.P r6 = r6.mAdapter
            boolean r6 = r6.hasStableIds()
            if (r6 == 0) goto L_0x02e0
            androidx.recyclerview.widget.RecyclerView r6 = r0.this$0
            androidx.recyclerview.widget.P r6 = r6.mAdapter
            long r6 = r6.getItemId(r5)
            java.util.ArrayList r13 = r0.mAttachedScrap
            int r13 = r13.size()
            int r13 = r13 + r4
        L_0x0253:
            if (r13 < 0) goto L_0x02a2
            java.util.ArrayList r14 = r0.mAttachedScrap
            java.lang.Object r14 = r14.get(r13)
            androidx.recyclerview.widget.qa r14 = (androidx.recyclerview.widget.C0586qa) r14
            long r15 = r14.getItemId()
            int r15 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1))
            if (r15 != 0) goto L_0x029d
            boolean r15 = r14.wasReturnedFromScrap()
            if (r15 != 0) goto L_0x029d
            int r15 = r14.getItemViewType()
            if (r15 != 0) goto L_0x028a
            r14.addFlags(r3)
            boolean r3 = r14.isRemoved()
            if (r3 == 0) goto L_0x0288
            androidx.recyclerview.widget.RecyclerView r3 = r0.this$0
            androidx.recyclerview.widget.oa r3 = r3.mState
            boolean r3 = r3.mInPreLayout
            if (r3 != 0) goto L_0x0288
            r3 = 2
            r6 = 14
            r14.setFlags(r3, r6)
        L_0x0288:
            r7 = r14
            goto L_0x02da
        L_0x028a:
            if (r19 != 0) goto L_0x029d
            java.util.ArrayList r15 = r0.mAttachedScrap
            r15.remove(r13)
            androidx.recyclerview.widget.RecyclerView r15 = r0.this$0
            android.view.View r3 = r14.itemView
            r15.removeDetachedView(r3, r10)
            android.view.View r3 = r14.itemView
            r0.quickRecycleScrapView(r3)
        L_0x029d:
            int r13 = r13 + -1
            r3 = 32
            goto L_0x0253
        L_0x02a2:
            java.util.ArrayList r3 = r0.mCachedViews
            int r3 = r3.size()
            int r3 = r3 + r4
        L_0x02a9:
            if (r3 < 0) goto L_0x02d9
            java.util.ArrayList r13 = r0.mCachedViews
            java.lang.Object r13 = r13.get(r3)
            androidx.recyclerview.widget.qa r13 = (androidx.recyclerview.widget.C0586qa) r13
            long r14 = r13.getItemId()
            int r14 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r14 != 0) goto L_0x02d6
            boolean r14 = r13.mo5191Xc()
            if (r14 != 0) goto L_0x02d6
            int r14 = r13.getItemViewType()
            if (r14 != 0) goto L_0x02d0
            if (r19 != 0) goto L_0x02ce
            java.util.ArrayList r6 = r0.mCachedViews
            r6.remove(r3)
        L_0x02ce:
            r7 = r13
            goto L_0x02da
        L_0x02d0:
            if (r19 != 0) goto L_0x02d6
            r0.recycleCachedViewAt(r3)
            goto L_0x02d9
        L_0x02d6:
            int r3 = r3 + -1
            goto L_0x02a9
        L_0x02d9:
            r7 = r8
        L_0x02da:
            if (r7 == 0) goto L_0x02e0
            r7.mPosition = r5
            r13 = r9
            goto L_0x02e1
        L_0x02e0:
            r13 = r2
        L_0x02e1:
            if (r7 != 0) goto L_0x032e
            androidx.recyclerview.widget.ia r2 = r17.getRecycledViewPool()
            android.util.SparseArray r2 = r2.mScrap
            java.lang.Object r2 = r2.get(r10)
            androidx.recyclerview.widget.ha r2 = (androidx.recyclerview.widget.C0568ha) r2
            if (r2 == 0) goto L_0x0318
            java.util.ArrayList r3 = r2.mScrapHeap
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x0318
            java.util.ArrayList r2 = r2.mScrapHeap
            int r3 = r2.size()
            int r3 = r3 + r4
        L_0x0300:
            if (r3 < 0) goto L_0x0318
            java.lang.Object r4 = r2.get(r3)
            androidx.recyclerview.widget.qa r4 = (androidx.recyclerview.widget.C0586qa) r4
            boolean r4 = r4.mo5191Xc()
            if (r4 != 0) goto L_0x0315
            java.lang.Object r2 = r2.remove(r3)
            androidx.recyclerview.widget.qa r2 = (androidx.recyclerview.widget.C0586qa) r2
            goto L_0x0319
        L_0x0315:
            int r3 = r3 + -1
            goto L_0x0300
        L_0x0318:
            r2 = r8
        L_0x0319:
            if (r2 == 0) goto L_0x032d
            r2.resetInternal()
            boolean r3 = androidx.recyclerview.widget.RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST
            if (r3 == 0) goto L_0x032d
            android.view.View r3 = r2.itemView
            boolean r4 = r3 instanceof android.view.ViewGroup
            if (r4 == 0) goto L_0x032d
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            r0.m887a((android.view.ViewGroup) r3, (boolean) r10)
        L_0x032d:
            r7 = r2
        L_0x032e:
            if (r7 != 0) goto L_0x03a8
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            long r14 = r2.getNanoTime()
            int r2 = (r20 > r11 ? 1 : (r20 == r11 ? 0 : -1))
            if (r2 == 0) goto L_0x0347
            androidx.recyclerview.widget.ia r2 = r0.mRecyclerPool
            r3 = 0
            r4 = r14
            r6 = r20
            boolean r2 = r2.willCreateInTime(r3, r4, r6)
            if (r2 != 0) goto L_0x0347
            return r8
        L_0x0347:
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            androidx.recyclerview.widget.P r3 = r2.mAdapter
            androidx.recyclerview.widget.qa r7 = r3.createViewHolder(r2, r10)
            boolean r2 = androidx.recyclerview.widget.RecyclerView.f562Di
            if (r2 == 0) goto L_0x0362
            android.view.View r2 = r7.itemView
            androidx.recyclerview.widget.RecyclerView r2 = androidx.recyclerview.widget.RecyclerView.findNestedRecyclerView(r2)
            if (r2 == 0) goto L_0x0362
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference
            r3.<init>(r2)
            r7.mNestedRecyclerView = r3
        L_0x0362:
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            long r2 = r2.getNanoTime()
            androidx.recyclerview.widget.ia r4 = r0.mRecyclerPool
            long r2 = r2 - r14
            r4.factorInCreateTime(r10, r2)
            goto L_0x03a8
        L_0x036f:
            java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Inconsistency detected. Invalid item position "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = "(offset:"
            r3.append(r1)
            r3.append(r5)
            java.lang.String r1 = ").state:"
            r3.append(r1)
            androidx.recyclerview.widget.RecyclerView r1 = r0.this$0
            androidx.recyclerview.widget.oa r1 = r1.mState
            int r1 = r1.getItemCount()
            r3.append(r1)
            androidx.recyclerview.widget.RecyclerView r0 = r0.this$0
            java.lang.String r0 = r0.mo4815Vb()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x03a7:
            r13 = r2
        L_0x03a8:
            r8 = r7
            if (r13 == 0) goto L_0x03df
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            androidx.recyclerview.widget.oa r2 = r2.mState
            boolean r2 = r2.mInPreLayout
            if (r2 != 0) goto L_0x03df
            r2 = 8192(0x2000, float:1.14794E-41)
            boolean r3 = r8.hasAnyOfTheFlags(r2)
            if (r3 == 0) goto L_0x03df
            r8.setFlags(r10, r2)
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            androidx.recyclerview.widget.oa r2 = r2.mState
            boolean r2 = r2.mRunSimpleAnimations
            if (r2 == 0) goto L_0x03df
            int r2 = androidx.recyclerview.widget.C0594ua.m915p(r8)
            r2 = r2 | 4096(0x1000, float:5.74E-42)
            androidx.recyclerview.widget.RecyclerView r3 = r0.this$0
            androidx.recyclerview.widget.ua r4 = r3.mItemAnimator
            androidx.recyclerview.widget.oa r3 = r3.mState
            java.util.List r5 = r8.getUnmodifiedPayloads()
            androidx.recyclerview.widget.V r2 = r4.mo5236a((androidx.recyclerview.widget.C0582oa) r3, (androidx.recyclerview.widget.C0586qa) r8, (int) r2, (java.util.List) r5)
            androidx.recyclerview.widget.RecyclerView r3 = r0.this$0
            r3.mo4826a((androidx.recyclerview.widget.C0586qa) r8, (androidx.recyclerview.widget.C0548V) r2)
        L_0x03df:
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            androidx.recyclerview.widget.oa r2 = r2.mState
            boolean r2 = r2.mInPreLayout
            if (r2 == 0) goto L_0x03f0
            boolean r2 = r8.isBound()
            if (r2 == 0) goto L_0x03f0
            r8.mPreLayoutPosition = r1
            goto L_0x0403
        L_0x03f0:
            boolean r2 = r8.isBound()
            if (r2 == 0) goto L_0x0406
            boolean r2 = r8.needsUpdate()
            if (r2 != 0) goto L_0x0406
            boolean r2 = r8.isInvalid()
            if (r2 == 0) goto L_0x0403
            goto L_0x0406
        L_0x0403:
            r1 = r10
            goto L_0x0483
        L_0x0406:
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            androidx.recyclerview.widget.b r2 = r2.mAdapterHelper
            int r14 = r2.findPositionOffset(r1, r10)
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            r8.mOwnerRecyclerView = r2
            int r3 = r8.getItemViewType()
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            long r15 = r2.getNanoTime()
            int r2 = (r20 > r11 ? 1 : (r20 == r11 ? 0 : -1))
            if (r2 == 0) goto L_0x042c
            androidx.recyclerview.widget.ia r2 = r0.mRecyclerPool
            r4 = r15
            r6 = r20
            boolean r2 = r2.willBindInTime(r3, r4, r6)
            if (r2 != 0) goto L_0x042c
            goto L_0x0403
        L_0x042c:
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            androidx.recyclerview.widget.P r2 = r2.mAdapter
            r2.mo4797a(r8, r14)
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            long r2 = r2.getNanoTime()
            androidx.recyclerview.widget.ia r4 = r0.mRecyclerPool
            int r5 = r8.getItemViewType()
            long r2 = r2 - r15
            r4.factorInBindTime(r5, r2)
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            boolean r2 = r2.isAccessibilityEnabled()
            if (r2 == 0) goto L_0x0478
            android.view.View r2 = r8.itemView
            int r3 = androidx.core.view.ViewCompat.getImportantForAccessibility(r2)
            if (r3 != 0) goto L_0x0458
            int r3 = android.os.Build.VERSION.SDK_INT
            r2.setImportantForAccessibility(r9)
        L_0x0458:
            androidx.core.view.AccessibilityDelegateCompat r3 = androidx.core.view.ViewCompat.getAccessibilityDelegate(r2)
            if (r3 == 0) goto L_0x046a
            java.lang.Class r3 = r3.getClass()
            java.lang.Class<androidx.core.view.AccessibilityDelegateCompat> r4 = androidx.core.view.AccessibilityDelegateCompat.class
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0478
        L_0x046a:
            r3 = 16384(0x4000, float:2.2959E-41)
            r8.addFlags(r3)
            androidx.recyclerview.widget.RecyclerView r3 = r0.this$0
            androidx.recyclerview.widget.sa r3 = r3.mAccessibilityDelegate
            androidx.core.view.AccessibilityDelegateCompat r3 = r3.mItemDelegate
            androidx.core.view.ViewCompat.setAccessibilityDelegate(r2, r3)
        L_0x0478:
            androidx.recyclerview.widget.RecyclerView r2 = r0.this$0
            androidx.recyclerview.widget.oa r2 = r2.mState
            boolean r2 = r2.mInPreLayout
            if (r2 == 0) goto L_0x0482
            r8.mPreLayoutPosition = r1
        L_0x0482:
            r1 = r9
        L_0x0483:
            android.view.View r2 = r8.itemView
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            if (r2 != 0) goto L_0x0499
            androidx.recyclerview.widget.RecyclerView r0 = r0.this$0
            android.view.ViewGroup$LayoutParams r0 = r0.generateDefaultLayoutParams()
            androidx.recyclerview.widget.da r0 = (androidx.recyclerview.widget.C0560da) r0
            android.view.View r2 = r8.itemView
            r2.setLayoutParams(r0)
            goto L_0x04b2
        L_0x0499:
            androidx.recyclerview.widget.RecyclerView r3 = r0.this$0
            boolean r3 = r3.checkLayoutParams(r2)
            if (r3 != 0) goto L_0x04af
            androidx.recyclerview.widget.RecyclerView r0 = r0.this$0
            android.view.ViewGroup$LayoutParams r0 = r0.generateLayoutParams((android.view.ViewGroup.LayoutParams) r2)
            androidx.recyclerview.widget.da r0 = (androidx.recyclerview.widget.C0560da) r0
            android.view.View r2 = r8.itemView
            r2.setLayoutParams(r0)
            goto L_0x04b2
        L_0x04af:
            r0 = r2
            androidx.recyclerview.widget.da r0 = (androidx.recyclerview.widget.C0560da) r0
        L_0x04b2:
            r0.mViewHolder = r8
            if (r13 == 0) goto L_0x04b9
            if (r1 == 0) goto L_0x04b9
            goto L_0x04ba
        L_0x04b9:
            r9 = r10
        L_0x04ba:
            r0.mPendingInvalidate = r9
            return r8
        L_0x04bd:
            java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Invalid item position "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r4 = "("
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = "). Item count:"
            r3.append(r1)
            androidx.recyclerview.widget.RecyclerView r1 = r0.this$0
            androidx.recyclerview.widget.oa r1 = r1.mState
            int r1 = r1.getItemCount()
            r3.append(r1)
            androidx.recyclerview.widget.RecyclerView r0 = r0.this$0
            java.lang.String r0 = r0.mo4815Vb()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.C0572ja.tryGetViewHolderForPositionByDeadline(int, boolean, long):androidx.recyclerview.widget.qa");
    }

    /* access modifiers changed from: package-private */
    public void updateViewCacheSize() {
        C0558ca caVar = this.this$0.mLayout;
        this.mViewCacheMax = this.f644Ss + (caVar != null ? caVar.mPrefetchMaxCountObserved : 0);
        for (int size = this.mCachedViews.size() - 1; size >= 0 && this.mCachedViews.size() > this.mViewCacheMax; size--) {
            recycleCachedViewAt(size);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo5141a(C0586qa qaVar, boolean z) {
        RecyclerView.m695b(qaVar);
        if (qaVar.hasAnyOfTheFlags(16384)) {
            qaVar.setFlags(0, 16384);
            ViewCompat.setAccessibilityDelegate(qaVar.itemView, (AccessibilityDelegateCompat) null);
        }
        if (z) {
            C0543P p = this.this$0.mAdapter;
            if (p != null) {
                p.mo4808h(qaVar);
            }
            RecyclerView recyclerView = this.this$0;
            if (recyclerView.mState != null) {
                recyclerView.mViewInfoStore.mo4698F(qaVar);
            }
        }
        qaVar.mOwnerRecyclerView = null;
        getRecycledViewPool().mo5134k(qaVar);
    }
}
