package androidx.recyclerview.widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: androidx.recyclerview.widget.za */
class C0604za {
    int[] mData;

    /* renamed from: ut */
    List f688ut;

    C0604za() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: T */
    public void mo5271T(int i) {
        int[] iArr = this.mData;
        if (iArr == null) {
            this.mData = new int[(Math.max(i, 10) + 1)];
            Arrays.fill(this.mData, -1);
        } else if (i >= iArr.length) {
            int length = iArr.length;
            while (length <= i) {
                length *= 2;
            }
            this.mData = new int[length];
            System.arraycopy(iArr, 0, this.mData, 0, iArr.length);
            int[] iArr2 = this.mData;
            Arrays.fill(iArr2, iArr.length, iArr2.length, -1);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: U */
    public int mo5272U(int i) {
        List list = this.f688ut;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (((StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) this.f688ut.get(size)).mPosition >= i) {
                    this.f688ut.remove(size);
                }
            }
        }
        return mo5274W(i);
    }

    /* renamed from: V */
    public StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem mo5273V(int i) {
        List list = this.f688ut;
        if (list == null) {
            return null;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = (StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) this.f688ut.get(size);
            if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition == i) {
                return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0052  */
    /* renamed from: W */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mo5274W(int r5) {
        /*
            r4 = this;
            int[] r0 = r4.mData
            r1 = -1
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            int r0 = r0.length
            if (r5 < r0) goto L_0x000a
            return r1
        L_0x000a:
            java.util.List r0 = r4.f688ut
            if (r0 != 0) goto L_0x0010
        L_0x000e:
            r0 = r1
            goto L_0x0046
        L_0x0010:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r0 = r4.mo5273V(r5)
            if (r0 == 0) goto L_0x001b
            java.util.List r2 = r4.f688ut
            r2.remove(r0)
        L_0x001b:
            java.util.List r0 = r4.f688ut
            int r0 = r0.size()
            r2 = 0
        L_0x0022:
            if (r2 >= r0) goto L_0x0034
            java.util.List r3 = r4.f688ut
            java.lang.Object r3 = r3.get(r2)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r3 = (androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r3
            int r3 = r3.mPosition
            if (r3 < r5) goto L_0x0031
            goto L_0x0035
        L_0x0031:
            int r2 = r2 + 1
            goto L_0x0022
        L_0x0034:
            r2 = r1
        L_0x0035:
            if (r2 == r1) goto L_0x000e
            java.util.List r0 = r4.f688ut
            java.lang.Object r0 = r0.get(r2)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r0 = (androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r0
            java.util.List r3 = r4.f688ut
            r3.remove(r2)
            int r0 = r0.mPosition
        L_0x0046:
            if (r0 != r1) goto L_0x0052
            int[] r0 = r4.mData
            int r2 = r0.length
            java.util.Arrays.fill(r0, r5, r2, r1)
            int[] r4 = r4.mData
            int r4 = r4.length
            return r4
        L_0x0052:
            int[] r4 = r4.mData
            int r0 = r0 + 1
            java.util.Arrays.fill(r4, r5, r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.C0604za.mo5274W(int):int");
    }

    /* renamed from: a */
    public void mo5276a(StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem) {
        if (this.f688ut == null) {
            this.f688ut = new ArrayList();
        }
        int size = this.f688ut.size();
        for (int i = 0; i < size; i++) {
            StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2 = (StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) this.f688ut.get(i);
            if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2.mPosition == staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition) {
                this.f688ut.remove(i);
            }
            if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem2.mPosition >= staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition) {
                this.f688ut.add(i, staggeredGridLayoutManager$LazySpanLookup$FullSpanItem);
                return;
            }
        }
        this.f688ut.add(staggeredGridLayoutManager$LazySpanLookup$FullSpanItem);
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        int[] iArr = this.mData;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        this.f688ut = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: n */
    public void mo5278n(int i, int i2) {
        int[] iArr = this.mData;
        if (iArr != null && i < iArr.length) {
            int i3 = i + i2;
            mo5271T(i3);
            int[] iArr2 = this.mData;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.mData, i, i3, -1);
            List list = this.f688ut;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = (StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) this.f688ut.get(size);
                    int i4 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition;
                    if (i4 >= i) {
                        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition = i4 + i2;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public void mo5279o(int i, int i2) {
        int[] iArr = this.mData;
        if (iArr != null && i < iArr.length) {
            int i3 = i + i2;
            mo5271T(i3);
            int[] iArr2 = this.mData;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.mData;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            List list = this.f688ut;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = (StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) this.f688ut.get(size);
                    int i4 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition;
                    if (i4 >= i) {
                        if (i4 < i3) {
                            this.f688ut.remove(size);
                        } else {
                            staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition = i4 - i2;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem mo5275a(int i, int i2, int i3, boolean z) {
        List list = this.f688ut;
        if (list == null) {
            return null;
        }
        int size = list.size();
        for (int i4 = 0; i4 < size; i4++) {
            StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = (StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) this.f688ut.get(i4);
            int i5 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.mPosition;
            if (i5 >= i2) {
                return null;
            }
            if (i5 >= i && (i3 == 0 || staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.f621rt == i3 || (z && staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.f623tt))) {
                return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
            }
        }
        return null;
    }
}
