package com.android.messaging.p041ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1486ya;
import java.util.ArrayList;

/* renamed from: com.android.messaging.ui.LineWrapLayout */
public class LineWrapLayout extends ViewGroup {
    public LineWrapLayout(Context context) {
        super(context, (AttributeSet) null);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        LineWrapLayout lineWrapLayout = this;
        int paddingStart = C1486ya.getPaddingStart(this);
        int width = (getWidth() - paddingStart) - (C1464na.m3755Vj() ? getPaddingEnd() : getPaddingRight());
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        ArrayList arrayList = new ArrayList();
        int i8 = paddingStart;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            i5 = 8;
            if (i9 >= childCount) {
                break;
            }
            View childAt = lineWrapLayout.getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                C1059O o = (C1059O) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int cc = o.mo7054cc();
                int bc = o.mo7053bc();
                if (i8 + measuredWidth + cc + bc > width) {
                    arrayList.add(Integer.valueOf(i10));
                    i8 = paddingStart;
                    i10 = 0;
                    cc = 0;
                }
                i10 = Math.max(i10, measuredHeight + o.topMargin + o.bottomMargin);
                i8 += measuredWidth + cc + bc;
            }
            i9++;
        }
        arrayList.add(Integer.valueOf(i10));
        int i11 = paddingStart;
        int i12 = paddingTop;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < childCount) {
            View childAt2 = lineWrapLayout.getChildAt(i13);
            if (childAt2.getVisibility() == i5) {
                i7 = paddingStart;
                i6 = childCount;
            } else {
                C1059O o2 = (C1059O) childAt2.getLayoutParams();
                int measuredWidth2 = childAt2.getMeasuredWidth();
                int measuredHeight2 = childAt2.getMeasuredHeight();
                int cc2 = o2.mo7054cc();
                int bc2 = o2.mo7053bc();
                if (i11 + measuredWidth2 + cc2 + bc2 > width) {
                    i12 += i14;
                    i15++;
                    i11 = paddingStart;
                    i14 = 0;
                    cc2 = 0;
                }
                int i16 = i11 + cc2;
                int i17 = o2.topMargin + i12;
                int i18 = o2.gravity & 112;
                i7 = paddingStart;
                if (i18 == 48 || arrayList.size() <= i15) {
                    i6 = childCount;
                } else {
                    int intValue = ((Integer) arrayList.get(i15)).intValue();
                    i6 = childCount;
                    if (i18 == 16) {
                        i17 = ((intValue - measuredHeight2) / 2) + i12;
                    } else if (i18 == 80) {
                        i17 = ((intValue + i12) - measuredHeight2) - o2.bottomMargin;
                    }
                }
                if (!C1464na.m3756Wj() || getResources().getConfiguration().getLayoutDirection() != 1) {
                    childAt2.layout(i16, i17, i16 + measuredWidth2, i17 + measuredHeight2);
                } else {
                    int i19 = width - i16;
                    childAt2.layout(i19 - measuredWidth2, i17, i19, i17 + measuredHeight2);
                }
                i14 = Math.max(i14, measuredHeight2 + o2.topMargin + o2.bottomMargin);
                i11 = measuredWidth2 + cc2 + bc2 + i11;
            }
            i13++;
            i5 = 8;
            lineWrapLayout = this;
            paddingStart = i7;
            childCount = i6;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int paddingStart = C1486ya.getPaddingStart(this);
        int paddingEnd = C1464na.m3755Vj() ? getPaddingEnd() : getPaddingRight();
        int mode = View.MeasureSpec.getMode(i);
        int size = (View.MeasureSpec.getSize(i) - paddingStart) - paddingEnd;
        boolean z = mode == 1073741824;
        int childCount = getChildCount();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, RtlSpacingHelper.UNDEFINED);
        int i4 = paddingStart;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() == 8) {
                i3 = childCount;
            } else {
                C1059O o = (C1059O) childAt.getLayoutParams();
                int cc = o.mo7054cc();
                int bc = o.mo7053bc();
                i3 = childCount;
                childAt.measure(makeMeasureSpec, 0);
                int measuredWidth = childAt.getMeasuredWidth() + cc + bc;
                int measuredHeight = childAt.getMeasuredHeight() + o.topMargin + o.bottomMargin;
                if (i4 + measuredWidth > size) {
                    i6 += i7;
                    i4 = paddingStart;
                    i7 = 0;
                    i9 = 0;
                }
                i4 += measuredWidth;
                i9 += measuredWidth;
                int max = Math.max(i7, measuredHeight);
                i8 = Math.max(i9, i8);
                i7 = max;
            }
            i5++;
            childCount = i3;
        }
        int i10 = i6 + i7;
        if (!z) {
            size = i8;
        }
        setMeasuredDimension(size + paddingStart + paddingEnd, getPaddingBottom() + getPaddingTop() + i10);
    }

    public LineWrapLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C1059O generateDefaultLayoutParams() {
        return new C1059O(-1, -1);
    }

    /* access modifiers changed from: protected */
    public C1059O generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C1059O(layoutParams);
    }

    public C1059O generateLayoutParams(AttributeSet attributeSet) {
        return new C1059O(getContext(), attributeSet);
    }
}
