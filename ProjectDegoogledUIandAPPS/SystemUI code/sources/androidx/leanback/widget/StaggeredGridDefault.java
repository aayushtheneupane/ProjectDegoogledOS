package androidx.leanback.widget;

import androidx.leanback.widget.StaggeredGrid;

final class StaggeredGridDefault extends StaggeredGrid {
    StaggeredGridDefault() {
    }

    /* access modifiers changed from: package-private */
    public int getRowMax(int i) {
        int i2;
        StaggeredGrid.Location location;
        int i3 = this.mFirstVisibleIndex;
        if (i3 < 0) {
            return Integer.MIN_VALUE;
        }
        if (this.mReversedFlow) {
            int edge = this.mProvider.getEdge(i3);
            if (getLocation(this.mFirstVisibleIndex).row == i) {
                return edge;
            }
            int i4 = this.mFirstVisibleIndex;
            do {
                i4++;
                if (i4 <= getLastIndex()) {
                    location = getLocation(i4);
                    edge += location.offset;
                }
            } while (location.row != i);
            return edge;
        }
        int edge2 = this.mProvider.getEdge(this.mLastVisibleIndex);
        StaggeredGrid.Location location2 = getLocation(this.mLastVisibleIndex);
        if (location2.row != i) {
            int i5 = this.mLastVisibleIndex;
            while (true) {
                i5--;
                if (i5 < getFirstIndex()) {
                    break;
                }
                edge2 -= location2.offset;
                location2 = getLocation(i5);
                if (location2.row == i) {
                    i2 = location2.size;
                    break;
                }
            }
        } else {
            i2 = location2.size;
        }
        return edge2 + i2;
        return Integer.MIN_VALUE;
    }

    /* access modifiers changed from: package-private */
    public int getRowMin(int i) {
        StaggeredGrid.Location location;
        int i2;
        int i3 = this.mFirstVisibleIndex;
        if (i3 < 0) {
            return Integer.MAX_VALUE;
        }
        if (this.mReversedFlow) {
            int edge = this.mProvider.getEdge(this.mLastVisibleIndex);
            StaggeredGrid.Location location2 = getLocation(this.mLastVisibleIndex);
            if (location2.row != i) {
                int i4 = this.mLastVisibleIndex;
                while (true) {
                    i4--;
                    if (i4 < getFirstIndex()) {
                        break;
                    }
                    edge -= location2.offset;
                    location2 = getLocation(i4);
                    if (location2.row == i) {
                        i2 = location2.size;
                        break;
                    }
                }
            } else {
                i2 = location2.size;
            }
            return edge - i2;
        }
        int edge2 = this.mProvider.getEdge(i3);
        if (getLocation(this.mFirstVisibleIndex).row == i) {
            return edge2;
        }
        int i5 = this.mFirstVisibleIndex;
        do {
            i5++;
            if (i5 <= getLastIndex()) {
                location = getLocation(i5);
                edge2 += location.offset;
            }
        } while (location.row != i);
        return edge2;
        return Integer.MAX_VALUE;
    }

    public int findRowMax(boolean z, int i, int[] iArr) {
        int i2;
        int edge = this.mProvider.getEdge(i);
        StaggeredGrid.Location location = getLocation(i);
        int i3 = location.row;
        if (this.mReversedFlow) {
            int i4 = i + 1;
            i2 = i;
            int i5 = edge;
            int i6 = i3;
            int i7 = 1;
            while (i7 < this.mNumRows && i4 <= this.mLastVisibleIndex) {
                StaggeredGrid.Location location2 = getLocation(i4);
                i5 += location2.offset;
                int i8 = location2.row;
                if (i8 != i6) {
                    i7++;
                    if (!z ? i5 >= edge : i5 <= edge) {
                        i6 = i8;
                    } else {
                        i2 = i4;
                        edge = i5;
                        i3 = i8;
                        i6 = i3;
                    }
                }
                i4++;
            }
        } else {
            int i9 = i - 1;
            StaggeredGrid.Location location3 = location;
            int i10 = i3;
            int i11 = edge;
            edge = this.mProvider.getSize(i) + edge;
            i2 = i;
            int i12 = 1;
            while (i12 < this.mNumRows && i9 >= this.mFirstVisibleIndex) {
                i11 -= location3.offset;
                location3 = getLocation(i9);
                int i13 = location3.row;
                if (i13 != i10) {
                    i12++;
                    int size = this.mProvider.getSize(i9) + i11;
                    if (!z ? size >= edge : size <= edge) {
                        i10 = i13;
                    } else {
                        i2 = i9;
                        edge = size;
                        i3 = i13;
                        i10 = i3;
                    }
                }
                i9--;
            }
        }
        if (iArr != null) {
            iArr[0] = i3;
            iArr[1] = i2;
        }
        return edge;
    }

    public int findRowMin(boolean z, int i, int[] iArr) {
        int i2;
        int i3;
        int edge = this.mProvider.getEdge(i);
        StaggeredGrid.Location location = getLocation(i);
        int i4 = location.row;
        if (this.mReversedFlow) {
            i2 = edge - this.mProvider.getSize(i);
            int i5 = i - 1;
            StaggeredGrid.Location location2 = location;
            int i6 = i4;
            int i7 = edge;
            i3 = i;
            int i8 = 1;
            while (i8 < this.mNumRows && i5 >= this.mFirstVisibleIndex) {
                i7 -= location2.offset;
                location2 = getLocation(i5);
                int i9 = location2.row;
                if (i9 != i6) {
                    i8++;
                    int size = i7 - this.mProvider.getSize(i5);
                    if (!z ? size >= i2 : size <= i2) {
                        i6 = i9;
                    } else {
                        i3 = i5;
                        i2 = size;
                        i4 = i9;
                        i6 = i4;
                    }
                }
                i5--;
            }
        } else {
            int i10 = i + 1;
            i2 = edge;
            int i11 = i2;
            int i12 = i4;
            i3 = i;
            int i13 = 1;
            while (i13 < this.mNumRows && i10 <= this.mLastVisibleIndex) {
                StaggeredGrid.Location location3 = getLocation(i10);
                i11 += location3.offset;
                int i14 = location3.row;
                if (i14 != i12) {
                    i13++;
                    if (!z ? i11 >= i2 : i11 <= i2) {
                        i12 = i14;
                    } else {
                        i3 = i10;
                        i2 = i11;
                        i4 = i14;
                        i12 = i4;
                    }
                }
                i10++;
            }
        }
        if (iArr != null) {
            iArr[0] = i4;
            iArr[1] = i3;
        }
        return i2;
    }

    private int findRowEdgeLimitSearchIndex(boolean z) {
        boolean z2 = false;
        if (z) {
            for (int i = this.mLastVisibleIndex; i >= this.mFirstVisibleIndex; i--) {
                int i2 = getLocation(i).row;
                if (i2 == 0) {
                    z2 = true;
                } else if (z2 && i2 == this.mNumRows - 1) {
                    return i;
                }
            }
            return -1;
        }
        for (int i3 = this.mFirstVisibleIndex; i3 <= this.mLastVisibleIndex; i3++) {
            int i4 = getLocation(i3).row;
            if (i4 == this.mNumRows - 1) {
                z2 = true;
            } else if (z2 && i4 == 0) {
                return i3;
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0107 A[LOOP:2: B:81:0x0107->B:95:0x012b, LOOP_START, PHI: r6 r7 r10 
      PHI: (r6v9 int) = (r6v3 int), (r6v12 int) binds: [B:80:0x0105, B:95:0x012b] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r7v8 int) = (r7v6 int), (r7v9 int) binds: [B:80:0x0105, B:95:0x012b] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r10v4 int) = (r10v2 int), (r10v6 int) binds: [B:80:0x0105, B:95:0x012b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0139  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean appendVisibleItemsWithoutCache(int r14, boolean r15) {
        /*
            r13 = this;
            androidx.leanback.widget.Grid$Provider r0 = r13.mProvider
            int r0 = r0.getCount()
            int r1 = r13.mLastVisibleIndex
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 0
            r4 = 0
            r5 = 1
            if (r1 < 0) goto L_0x0075
            int r6 = r13.getLastIndex()
            if (r1 >= r6) goto L_0x0016
            return r4
        L_0x0016:
            int r1 = r13.mLastVisibleIndex
            int r6 = r1 + 1
            androidx.leanback.widget.StaggeredGrid$Location r1 = r13.getLocation((int) r1)
            int r1 = r1.row
            int r7 = r13.findRowEdgeLimitSearchIndex(r5)
            if (r7 >= 0) goto L_0x003f
            r8 = r2
            r7 = r4
        L_0x0028:
            int r9 = r13.mNumRows
            if (r7 >= r9) goto L_0x004d
            boolean r8 = r13.mReversedFlow
            if (r8 == 0) goto L_0x0035
            int r8 = r13.getRowMin(r7)
            goto L_0x0039
        L_0x0035:
            int r8 = r13.getRowMax(r7)
        L_0x0039:
            if (r8 == r2) goto L_0x003c
            goto L_0x004d
        L_0x003c:
            int r7 = r7 + 1
            goto L_0x0028
        L_0x003f:
            boolean r8 = r13.mReversedFlow
            if (r8 == 0) goto L_0x0048
            int r7 = r13.findRowMin(r4, r7, r3)
            goto L_0x004c
        L_0x0048:
            int r7 = r13.findRowMax(r5, r7, r3)
        L_0x004c:
            r8 = r7
        L_0x004d:
            boolean r7 = r13.mReversedFlow
            if (r7 == 0) goto L_0x0058
            int r7 = r13.getRowMin(r1)
            if (r7 > r8) goto L_0x0073
            goto L_0x005e
        L_0x0058:
            int r7 = r13.getRowMax(r1)
            if (r7 < r8) goto L_0x0073
        L_0x005e:
            int r1 = r1 + 1
            int r7 = r13.mNumRows
            if (r1 != r7) goto L_0x0073
            boolean r1 = r13.mReversedFlow
            if (r1 == 0) goto L_0x006d
            int r1 = r13.findRowMin(r4, r3)
            goto L_0x0071
        L_0x006d:
            int r1 = r13.findRowMax(r5, r3)
        L_0x0071:
            r8 = r1
            r1 = r4
        L_0x0073:
            r7 = r5
            goto L_0x0097
        L_0x0075:
            int r1 = r13.mStartIndex
            r6 = -1
            if (r1 == r6) goto L_0x007c
            r6 = r1
            goto L_0x007d
        L_0x007c:
            r6 = r4
        L_0x007d:
            androidx.collection.CircularArray<androidx.leanback.widget.StaggeredGrid$Location> r1 = r13.mLocations
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x0091
            int r1 = r13.getLastIndex()
            androidx.leanback.widget.StaggeredGrid$Location r1 = r13.getLocation((int) r1)
            int r1 = r1.row
            int r1 = r1 + r5
            goto L_0x0092
        L_0x0091:
            r1 = r6
        L_0x0092:
            int r7 = r13.mNumRows
            int r1 = r1 % r7
            r7 = r4
            r8 = r7
        L_0x0097:
            r9 = r8
            r8 = r7
            r7 = r4
        L_0x009a:
            int r10 = r13.mNumRows
            if (r1 >= r10) goto L_0x014f
            if (r6 == r0) goto L_0x014e
            if (r15 != 0) goto L_0x00aa
            boolean r10 = r13.checkAppendOverLimit(r14)
            if (r10 == 0) goto L_0x00aa
            goto L_0x014e
        L_0x00aa:
            boolean r7 = r13.mReversedFlow
            if (r7 == 0) goto L_0x00b3
            int r7 = r13.getRowMin(r1)
            goto L_0x00b7
        L_0x00b3:
            int r7 = r13.getRowMax(r1)
        L_0x00b7:
            r10 = 2147483647(0x7fffffff, float:NaN)
            if (r7 == r10) goto L_0x00cb
            if (r7 != r2) goto L_0x00bf
            goto L_0x00cb
        L_0x00bf:
            boolean r10 = r13.mReversedFlow
            if (r10 == 0) goto L_0x00c7
            int r10 = r13.mSpacing
        L_0x00c5:
            int r10 = -r10
            goto L_0x00c9
        L_0x00c7:
            int r10 = r13.mSpacing
        L_0x00c9:
            int r7 = r7 + r10
            goto L_0x00ff
        L_0x00cb:
            if (r1 != 0) goto L_0x00ee
            boolean r7 = r13.mReversedFlow
            if (r7 == 0) goto L_0x00d9
            int r7 = r13.mNumRows
            int r7 = r7 - r5
            int r7 = r13.getRowMin(r7)
            goto L_0x00e0
        L_0x00d9:
            int r7 = r13.mNumRows
            int r7 = r7 - r5
            int r7 = r13.getRowMax(r7)
        L_0x00e0:
            if (r7 == r10) goto L_0x00ff
            if (r7 == r2) goto L_0x00ff
            boolean r10 = r13.mReversedFlow
            if (r10 == 0) goto L_0x00eb
            int r10 = r13.mSpacing
            goto L_0x00c5
        L_0x00eb:
            int r10 = r13.mSpacing
            goto L_0x00c9
        L_0x00ee:
            boolean r7 = r13.mReversedFlow
            if (r7 == 0) goto L_0x00f9
            int r7 = r1 + -1
            int r7 = r13.getRowMax(r7)
            goto L_0x00ff
        L_0x00f9:
            int r7 = r1 + -1
            int r7 = r13.getRowMin(r7)
        L_0x00ff:
            int r10 = r6 + 1
            int r6 = r13.appendVisibleItemToRow(r6, r1, r7)
            if (r8 == 0) goto L_0x0139
        L_0x0107:
            boolean r11 = r13.mReversedFlow
            if (r11 == 0) goto L_0x0110
            int r11 = r7 - r6
            if (r11 <= r9) goto L_0x0137
            goto L_0x0114
        L_0x0110:
            int r11 = r7 + r6
            if (r11 >= r9) goto L_0x0137
        L_0x0114:
            if (r10 == r0) goto L_0x0136
            if (r15 != 0) goto L_0x011f
            boolean r11 = r13.checkAppendOverLimit(r14)
            if (r11 == 0) goto L_0x011f
            goto L_0x0136
        L_0x011f:
            boolean r11 = r13.mReversedFlow
            if (r11 == 0) goto L_0x0128
            int r6 = -r6
            int r11 = r13.mSpacing
            int r6 = r6 - r11
            goto L_0x012b
        L_0x0128:
            int r11 = r13.mSpacing
            int r6 = r6 + r11
        L_0x012b:
            int r7 = r7 + r6
            int r6 = r10 + 1
            int r10 = r13.appendVisibleItemToRow(r10, r1, r7)
            r12 = r10
            r10 = r6
            r6 = r12
            goto L_0x0107
        L_0x0136:
            return r5
        L_0x0137:
            r6 = r10
            goto L_0x0149
        L_0x0139:
            boolean r6 = r13.mReversedFlow
            if (r6 == 0) goto L_0x0142
            int r6 = r13.getRowMin(r1)
            goto L_0x0146
        L_0x0142:
            int r6 = r13.getRowMax(r1)
        L_0x0146:
            r8 = r5
            r9 = r6
            goto L_0x0137
        L_0x0149:
            int r1 = r1 + 1
            r7 = r5
            goto L_0x009a
        L_0x014e:
            return r7
        L_0x014f:
            if (r15 == 0) goto L_0x0152
            return r7
        L_0x0152:
            boolean r1 = r13.mReversedFlow
            if (r1 == 0) goto L_0x015b
            int r1 = r13.findRowMin(r4, r3)
            goto L_0x015f
        L_0x015b:
            int r1 = r13.findRowMax(r5, r3)
        L_0x015f:
            r9 = r1
            r1 = r4
            goto L_0x009a
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.StaggeredGridDefault.appendVisibleItemsWithoutCache(int, boolean):boolean");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0101 A[LOOP:2: B:80:0x0101->B:94:0x0125, LOOP_START, PHI: r5 r6 r9 
      PHI: (r5v9 int) = (r5v3 int), (r5v12 int) binds: [B:79:0x00ff, B:94:0x0125] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r6v8 int) = (r6v6 int), (r6v9 int) binds: [B:79:0x00ff, B:94:0x0125] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r9v3 int) = (r9v1 int), (r9v5 int) binds: [B:79:0x00ff, B:94:0x0125] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0133  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean prependVisibleItemsWithoutCache(int r13, boolean r14) {
        /*
            r12 = this;
            int r0 = r12.mFirstVisibleIndex
            r1 = 2147483647(0x7fffffff, float:NaN)
            r2 = 0
            r3 = 0
            r4 = 1
            if (r0 < 0) goto L_0x0072
            int r5 = r12.getFirstIndex()
            if (r0 <= r5) goto L_0x0011
            return r3
        L_0x0011:
            int r0 = r12.mFirstVisibleIndex
            int r5 = r0 + -1
            androidx.leanback.widget.StaggeredGrid$Location r0 = r12.getLocation((int) r0)
            int r0 = r0.row
            int r6 = r12.findRowEdgeLimitSearchIndex(r3)
            if (r6 >= 0) goto L_0x003c
            int r0 = r0 + -1
            int r6 = r12.mNumRows
            int r6 = r6 - r4
            r7 = r1
        L_0x0027:
            if (r6 < 0) goto L_0x004a
            boolean r7 = r12.mReversedFlow
            if (r7 == 0) goto L_0x0032
            int r7 = r12.getRowMax(r6)
            goto L_0x0036
        L_0x0032:
            int r7 = r12.getRowMin(r6)
        L_0x0036:
            if (r7 == r1) goto L_0x0039
            goto L_0x004a
        L_0x0039:
            int r6 = r6 + -1
            goto L_0x0027
        L_0x003c:
            boolean r7 = r12.mReversedFlow
            if (r7 == 0) goto L_0x0045
            int r6 = r12.findRowMax(r4, r6, r2)
            goto L_0x0049
        L_0x0045:
            int r6 = r12.findRowMin(r3, r6, r2)
        L_0x0049:
            r7 = r6
        L_0x004a:
            boolean r6 = r12.mReversedFlow
            if (r6 == 0) goto L_0x0055
            int r6 = r12.getRowMax(r0)
            if (r6 < r7) goto L_0x0070
            goto L_0x005b
        L_0x0055:
            int r6 = r12.getRowMin(r0)
            if (r6 > r7) goto L_0x0070
        L_0x005b:
            int r0 = r0 + -1
            if (r0 >= 0) goto L_0x0070
            int r0 = r12.mNumRows
            int r0 = r0 - r4
            boolean r6 = r12.mReversedFlow
            if (r6 == 0) goto L_0x006b
            int r6 = r12.findRowMax(r4, r2)
            goto L_0x006f
        L_0x006b:
            int r6 = r12.findRowMin(r3, r2)
        L_0x006f:
            r7 = r6
        L_0x0070:
            r6 = r4
            goto L_0x0097
        L_0x0072:
            int r0 = r12.mStartIndex
            r5 = -1
            if (r0 == r5) goto L_0x0079
            r5 = r0
            goto L_0x007a
        L_0x0079:
            r5 = r3
        L_0x007a:
            androidx.collection.CircularArray<androidx.leanback.widget.StaggeredGrid$Location> r0 = r12.mLocations
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0091
            int r0 = r12.getFirstIndex()
            androidx.leanback.widget.StaggeredGrid$Location r0 = r12.getLocation((int) r0)
            int r0 = r0.row
            int r6 = r12.mNumRows
            int r0 = r0 + r6
            int r0 = r0 - r4
            goto L_0x0092
        L_0x0091:
            r0 = r5
        L_0x0092:
            int r6 = r12.mNumRows
            int r0 = r0 % r6
            r6 = r3
            r7 = r6
        L_0x0097:
            r8 = r7
            r7 = r6
            r6 = r3
        L_0x009a:
            if (r0 < 0) goto L_0x0149
            if (r5 < 0) goto L_0x0148
            if (r14 != 0) goto L_0x00a8
            boolean r9 = r12.checkPrependOverLimit(r13)
            if (r9 == 0) goto L_0x00a8
            goto L_0x0148
        L_0x00a8:
            boolean r6 = r12.mReversedFlow
            if (r6 == 0) goto L_0x00b1
            int r6 = r12.getRowMax(r0)
            goto L_0x00b5
        L_0x00b1:
            int r6 = r12.getRowMin(r0)
        L_0x00b5:
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r6 == r1) goto L_0x00c8
            if (r6 != r9) goto L_0x00bc
            goto L_0x00c8
        L_0x00bc:
            boolean r9 = r12.mReversedFlow
            if (r9 == 0) goto L_0x00c3
            int r9 = r12.mSpacing
            goto L_0x00c6
        L_0x00c3:
            int r9 = r12.mSpacing
        L_0x00c5:
            int r9 = -r9
        L_0x00c6:
            int r6 = r6 + r9
            goto L_0x00f9
        L_0x00c8:
            int r6 = r12.mNumRows
            int r6 = r6 - r4
            if (r0 != r6) goto L_0x00e8
            boolean r6 = r12.mReversedFlow
            if (r6 == 0) goto L_0x00d6
            int r6 = r12.getRowMax(r3)
            goto L_0x00da
        L_0x00d6:
            int r6 = r12.getRowMin(r3)
        L_0x00da:
            if (r6 == r1) goto L_0x00f9
            if (r6 == r9) goto L_0x00f9
            boolean r9 = r12.mReversedFlow
            if (r9 == 0) goto L_0x00e5
            int r9 = r12.mSpacing
            goto L_0x00c6
        L_0x00e5:
            int r9 = r12.mSpacing
            goto L_0x00c5
        L_0x00e8:
            boolean r6 = r12.mReversedFlow
            if (r6 == 0) goto L_0x00f3
            int r6 = r0 + 1
            int r6 = r12.getRowMin(r6)
            goto L_0x00f9
        L_0x00f3:
            int r6 = r0 + 1
            int r6 = r12.getRowMax(r6)
        L_0x00f9:
            int r9 = r5 + -1
            int r5 = r12.prependVisibleItemToRow(r5, r0, r6)
            if (r7 == 0) goto L_0x0133
        L_0x0101:
            boolean r10 = r12.mReversedFlow
            if (r10 == 0) goto L_0x010a
            int r10 = r6 + r5
            if (r10 >= r8) goto L_0x0131
            goto L_0x010e
        L_0x010a:
            int r10 = r6 - r5
            if (r10 <= r8) goto L_0x0131
        L_0x010e:
            if (r9 < 0) goto L_0x0130
            if (r14 != 0) goto L_0x0119
            boolean r10 = r12.checkPrependOverLimit(r13)
            if (r10 == 0) goto L_0x0119
            goto L_0x0130
        L_0x0119:
            boolean r10 = r12.mReversedFlow
            if (r10 == 0) goto L_0x0121
            int r10 = r12.mSpacing
            int r5 = r5 + r10
            goto L_0x0125
        L_0x0121:
            int r5 = -r5
            int r10 = r12.mSpacing
            int r5 = r5 - r10
        L_0x0125:
            int r6 = r6 + r5
            int r5 = r9 + -1
            int r9 = r12.prependVisibleItemToRow(r9, r0, r6)
            r11 = r9
            r9 = r5
            r5 = r11
            goto L_0x0101
        L_0x0130:
            return r4
        L_0x0131:
            r5 = r9
            goto L_0x0143
        L_0x0133:
            boolean r5 = r12.mReversedFlow
            if (r5 == 0) goto L_0x013c
            int r5 = r12.getRowMax(r0)
            goto L_0x0140
        L_0x013c:
            int r5 = r12.getRowMin(r0)
        L_0x0140:
            r7 = r4
            r8 = r5
            goto L_0x0131
        L_0x0143:
            int r0 = r0 + -1
            r6 = r4
            goto L_0x009a
        L_0x0148:
            return r6
        L_0x0149:
            if (r14 == 0) goto L_0x014c
            return r6
        L_0x014c:
            boolean r0 = r12.mReversedFlow
            if (r0 == 0) goto L_0x0155
            int r0 = r12.findRowMax(r4, r2)
            goto L_0x0159
        L_0x0155:
            int r0 = r12.findRowMin(r3, r2)
        L_0x0159:
            r8 = r0
            int r0 = r12.mNumRows
            int r0 = r0 - r4
            goto L_0x009a
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.StaggeredGridDefault.prependVisibleItemsWithoutCache(int, boolean):boolean");
    }
}
