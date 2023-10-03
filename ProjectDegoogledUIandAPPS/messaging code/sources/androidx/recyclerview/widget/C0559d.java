package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.recyclerview.widget.d */
class C0559d {
    final C0557c mBucket = new C0557c();
    final C0541N mCallback;
    final List mHiddenViews = new ArrayList();

    C0559d(C0541N n) {
        this.mCallback = n;
    }

    /* renamed from: j */
    private boolean m878j(View view) {
        if (!this.mHiddenViews.remove(view)) {
            return false;
        }
        this.mCallback.onLeftHiddenState(view);
        return true;
    }

    /* renamed from: rb */
    private int m879rb(int i) {
        if (i < 0) {
            return -1;
        }
        int childCount = this.mCallback.getChildCount();
        int i2 = i;
        while (i2 < childCount) {
            int countOnesBefore = i - (i2 - this.mBucket.countOnesBefore(i2));
            if (countOnesBefore == 0) {
                while (this.mBucket.get(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += countOnesBefore;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void addView(View view, int i, boolean z) {
        int i2;
        if (i < 0) {
            i2 = this.mCallback.getChildCount();
        } else {
            i2 = m879rb(i);
        }
        this.mBucket.insert(i2, z);
        if (z) {
            this.mHiddenViews.add(view);
            this.mCallback.onEnteredHiddenState(view);
        }
        C0541N n = this.mCallback;
        n.this$0.addView(view, i2);
        n.this$0.dispatchChildAttached(view);
    }

    /* access modifiers changed from: package-private */
    public void attachViewToParent(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int i2;
        if (i < 0) {
            i2 = this.mCallback.getChildCount();
        } else {
            i2 = m879rb(i);
        }
        this.mBucket.insert(i2, z);
        if (z) {
            this.mHiddenViews.add(view);
            this.mCallback.onEnteredHiddenState(view);
        }
        this.mCallback.attachViewToParent(view, i2, layoutParams);
    }

    /* access modifiers changed from: package-private */
    public void detachViewFromParent(int i) {
        C0586qa childViewHolderInt;
        int rb = m879rb(i);
        this.mBucket.remove(rb);
        C0541N n = this.mCallback;
        View childAt = n.this$0.getChildAt(rb);
        if (!(childAt == null || (childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt)) == null)) {
            if (!childViewHolderInt.isTmpDetached() || childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(256);
            } else {
                throw new IllegalArgumentException("called detach on an already detached child " + childViewHolderInt + n.this$0.mo4815Vb());
            }
        }
        n.this$0.detachViewFromParent(rb);
    }

    /* access modifiers changed from: package-private */
    public View getChildAt(int i) {
        return this.mCallback.getChildAt(m879rb(i));
    }

    /* access modifiers changed from: package-private */
    public int getChildCount() {
        return this.mCallback.getChildCount() - this.mHiddenViews.size();
    }

    /* access modifiers changed from: package-private */
    public View getUnfilteredChildAt(int i) {
        return this.mCallback.this$0.getChildAt(i);
    }

    /* access modifiers changed from: package-private */
    public int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }

    /* access modifiers changed from: package-private */
    public int indexOfChild(View view) {
        int indexOfChild = this.mCallback.this$0.indexOfChild(view);
        if (indexOfChild != -1 && !this.mBucket.get(indexOfChild)) {
            return indexOfChild - this.mBucket.countOnesBefore(indexOfChild);
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public boolean isHidden(View view) {
        return this.mHiddenViews.contains(view);
    }

    /* access modifiers changed from: package-private */
    public void removeView(View view) {
        int indexOfChild = this.mCallback.this$0.indexOfChild(view);
        if (indexOfChild >= 0) {
            if (this.mBucket.remove(indexOfChild)) {
                m878j(view);
            }
            this.mCallback.removeViewAt(indexOfChild);
        }
    }

    /* access modifiers changed from: package-private */
    public void removeViewAt(int i) {
        int rb = m879rb(i);
        View childAt = this.mCallback.getChildAt(rb);
        if (childAt != null) {
            if (this.mBucket.remove(rb)) {
                m878j(childAt);
            }
            this.mCallback.removeViewAt(rb);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean removeViewIfHidden(View view) {
        int indexOfChild = this.mCallback.this$0.indexOfChild(view);
        if (indexOfChild == -1) {
            m878j(view);
            return true;
        } else if (!this.mBucket.get(indexOfChild)) {
            return false;
        } else {
            this.mBucket.remove(indexOfChild);
            m878j(view);
            this.mCallback.removeViewAt(indexOfChild);
            return true;
        }
    }

    public String toString() {
        return this.mBucket.toString() + ", hidden list:" + this.mHiddenViews.size();
    }

    /* access modifiers changed from: package-private */
    public void unhide(View view) {
        int indexOfChild = this.mCallback.this$0.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException(C0632a.m1014a("view is not a child, cannot hide ", view));
        } else if (this.mBucket.get(indexOfChild)) {
            this.mBucket.clear(indexOfChild);
            m878j(view);
        } else {
            throw new RuntimeException(C0632a.m1014a("trying to unhide a view that was not hidden", view));
        }
    }
}
