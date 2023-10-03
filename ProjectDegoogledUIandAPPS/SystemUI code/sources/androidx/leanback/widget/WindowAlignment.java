package androidx.leanback.widget;

class WindowAlignment {
    public final Axis horizontal = new Axis("horizontal");
    private Axis mMainAxis = this.horizontal;
    private int mOrientation = 0;
    private Axis mSecondAxis = this.vertical;
    public final Axis vertical = new Axis("vertical");

    WindowAlignment() {
    }

    public static class Axis {
        private int mMaxEdge;
        private int mMaxScroll;
        private int mMinEdge;
        private int mMinScroll;
        private String mName;
        private int mPaddingMax;
        private int mPaddingMin;
        private int mPreferredKeyLine = 2;
        private boolean mReversedFlow;
        private int mSize;
        private int mWindowAlignment = 3;
        private int mWindowAlignmentOffset = 0;
        private float mWindowAlignmentOffsetPercent = 50.0f;

        /* access modifiers changed from: package-private */
        public final int calculateScrollToKeyLine(int i, int i2) {
            return i - i2;
        }

        public Axis(String str) {
            reset();
            this.mName = str;
        }

        public final void setWindowAlignment(int i) {
            this.mWindowAlignment = i;
        }

        /* access modifiers changed from: package-private */
        public final boolean isPreferKeylineOverHighEdge() {
            return (this.mPreferredKeyLine & 2) != 0;
        }

        /* access modifiers changed from: package-private */
        public final boolean isPreferKeylineOverLowEdge() {
            return (this.mPreferredKeyLine & 1) != 0;
        }

        public final int getMinScroll() {
            return this.mMinScroll;
        }

        public final void invalidateScrollMin() {
            this.mMinEdge = Integer.MIN_VALUE;
            this.mMinScroll = Integer.MIN_VALUE;
        }

        public final int getMaxScroll() {
            return this.mMaxScroll;
        }

        public final void invalidateScrollMax() {
            this.mMaxEdge = Integer.MAX_VALUE;
            this.mMaxScroll = Integer.MAX_VALUE;
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.mMinEdge = Integer.MIN_VALUE;
            this.mMaxEdge = Integer.MAX_VALUE;
        }

        public final boolean isMinUnknown() {
            return this.mMinEdge == Integer.MIN_VALUE;
        }

        public final boolean isMaxUnknown() {
            return this.mMaxEdge == Integer.MAX_VALUE;
        }

        public final void setSize(int i) {
            this.mSize = i;
        }

        public final int getSize() {
            return this.mSize;
        }

        public final void setPadding(int i, int i2) {
            this.mPaddingMin = i;
            this.mPaddingMax = i2;
        }

        public final int getPaddingMin() {
            return this.mPaddingMin;
        }

        public final int getPaddingMax() {
            return this.mPaddingMax;
        }

        public final int getClientSize() {
            return (this.mSize - this.mPaddingMin) - this.mPaddingMax;
        }

        /* access modifiers changed from: package-private */
        public final int calculateKeyline() {
            if (!this.mReversedFlow) {
                int i = this.mWindowAlignmentOffset;
                if (i < 0) {
                    i += this.mSize;
                }
                float f = this.mWindowAlignmentOffsetPercent;
                if (f != -1.0f) {
                    return i + ((int) ((((float) this.mSize) * f) / 100.0f));
                }
                return i;
            }
            int i2 = this.mWindowAlignmentOffset;
            int i3 = i2 >= 0 ? this.mSize - i2 : -i2;
            float f2 = this.mWindowAlignmentOffsetPercent;
            return f2 != -1.0f ? i3 - ((int) ((((float) this.mSize) * f2) / 100.0f)) : i3;
        }

        public final void updateMinMax(int i, int i2, int i3, int i4) {
            this.mMinEdge = i;
            this.mMaxEdge = i2;
            int clientSize = getClientSize();
            int calculateKeyline = calculateKeyline();
            boolean isMinUnknown = isMinUnknown();
            boolean isMaxUnknown = isMaxUnknown();
            if (!isMinUnknown) {
                if (this.mReversedFlow ? (this.mWindowAlignment & 2) == 0 : (this.mWindowAlignment & 1) == 0) {
                    this.mMinScroll = calculateScrollToKeyLine(i3, calculateKeyline);
                } else {
                    this.mMinScroll = this.mMinEdge - this.mPaddingMin;
                }
            }
            if (!isMaxUnknown) {
                if (this.mReversedFlow ? (this.mWindowAlignment & 1) == 0 : (this.mWindowAlignment & 2) == 0) {
                    this.mMaxScroll = calculateScrollToKeyLine(i4, calculateKeyline);
                } else {
                    this.mMaxScroll = (this.mMaxEdge - this.mPaddingMin) - clientSize;
                }
            }
            if (!isMaxUnknown && !isMinUnknown) {
                if (!this.mReversedFlow) {
                    int i5 = this.mWindowAlignment;
                    if ((i5 & 1) != 0) {
                        if (isPreferKeylineOverLowEdge()) {
                            this.mMinScroll = Math.min(this.mMinScroll, calculateScrollToKeyLine(i4, calculateKeyline));
                        }
                        this.mMaxScroll = Math.max(this.mMinScroll, this.mMaxScroll);
                    } else if ((i5 & 2) != 0) {
                        if (isPreferKeylineOverHighEdge()) {
                            this.mMaxScroll = Math.max(this.mMaxScroll, calculateScrollToKeyLine(i3, calculateKeyline));
                        }
                        this.mMinScroll = Math.min(this.mMinScroll, this.mMaxScroll);
                    }
                } else {
                    int i6 = this.mWindowAlignment;
                    if ((i6 & 1) != 0) {
                        if (isPreferKeylineOverLowEdge()) {
                            this.mMaxScroll = Math.max(this.mMaxScroll, calculateScrollToKeyLine(i3, calculateKeyline));
                        }
                        this.mMinScroll = Math.min(this.mMinScroll, this.mMaxScroll);
                    } else if ((i6 & 2) != 0) {
                        if (isPreferKeylineOverHighEdge()) {
                            this.mMinScroll = Math.min(this.mMinScroll, calculateScrollToKeyLine(i4, calculateKeyline));
                        }
                        this.mMaxScroll = Math.max(this.mMinScroll, this.mMaxScroll);
                    }
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
            r7 = r7.mMaxScroll;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final int getScroll(int r8) {
            /*
                r7 = this;
                int r0 = r7.getSize()
                int r1 = r7.calculateKeyline()
                boolean r2 = r7.isMinUnknown()
                boolean r3 = r7.isMaxUnknown()
                if (r2 != 0) goto L_0x0039
                int r4 = r7.mPaddingMin
                int r4 = r1 - r4
                boolean r5 = r7.mReversedFlow
                if (r5 != 0) goto L_0x0021
                int r5 = r7.mWindowAlignment
                r5 = r5 & 1
                if (r5 == 0) goto L_0x0039
                goto L_0x0027
            L_0x0021:
                int r5 = r7.mWindowAlignment
                r5 = r5 & 2
                if (r5 == 0) goto L_0x0039
            L_0x0027:
                int r5 = r7.mMinEdge
                int r6 = r8 - r5
                if (r6 > r4) goto L_0x0039
                int r8 = r7.mPaddingMin
                int r5 = r5 - r8
                if (r3 != 0) goto L_0x0037
                int r7 = r7.mMaxScroll
                if (r5 <= r7) goto L_0x0037
                goto L_0x0038
            L_0x0037:
                r7 = r5
            L_0x0038:
                return r7
            L_0x0039:
                if (r3 != 0) goto L_0x0064
                int r3 = r0 - r1
                int r4 = r7.mPaddingMax
                int r3 = r3 - r4
                boolean r4 = r7.mReversedFlow
                if (r4 != 0) goto L_0x004b
                int r4 = r7.mWindowAlignment
                r4 = r4 & 2
                if (r4 == 0) goto L_0x0064
                goto L_0x0051
            L_0x004b:
                int r4 = r7.mWindowAlignment
                r4 = r4 & 1
                if (r4 == 0) goto L_0x0064
            L_0x0051:
                int r4 = r7.mMaxEdge
                int r5 = r4 - r8
                if (r5 > r3) goto L_0x0064
                int r8 = r7.mPaddingMax
                int r0 = r0 - r8
                int r4 = r4 - r0
                if (r2 != 0) goto L_0x0062
                int r7 = r7.mMinScroll
                if (r4 >= r7) goto L_0x0062
                goto L_0x0063
            L_0x0062:
                r7 = r4
            L_0x0063:
                return r7
            L_0x0064:
                int r7 = r7.calculateScrollToKeyLine(r8, r1)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.WindowAlignment.Axis.getScroll(int):int");
        }

        public final void setReversedFlow(boolean z) {
            this.mReversedFlow = z;
        }

        public String toString() {
            return " min:" + this.mMinEdge + " " + this.mMinScroll + " max:" + this.mMaxEdge + " " + this.mMaxScroll;
        }
    }

    public final Axis mainAxis() {
        return this.mMainAxis;
    }

    public final Axis secondAxis() {
        return this.mSecondAxis;
    }

    public final void setOrientation(int i) {
        this.mOrientation = i;
        if (this.mOrientation == 0) {
            this.mMainAxis = this.horizontal;
            this.mSecondAxis = this.vertical;
            return;
        }
        this.mMainAxis = this.vertical;
        this.mSecondAxis = this.horizontal;
    }

    public final void reset() {
        mainAxis().reset();
    }

    public String toString() {
        return "horizontal=" + this.horizontal + "; vertical=" + this.vertical;
    }
}
