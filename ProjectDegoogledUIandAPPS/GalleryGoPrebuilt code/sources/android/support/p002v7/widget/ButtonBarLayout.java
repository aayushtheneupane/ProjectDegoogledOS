package android.support.p002v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.apps.photosgo.R;

/* renamed from: android.support.v7.widget.ButtonBarLayout */
/* compiled from: PG */
public class ButtonBarLayout extends LinearLayout {

    /* renamed from: a */
    private boolean f947a;

    /* renamed from: b */
    private int f948b = -1;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0435px.f15583k);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, C0435px.f15583k, attributeSet, obtainStyledAttributes, 0, 0);
        }
        this.f947a = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
    }

    public final int getMinimumHeight() {
        return Math.max(0, super.getMinimumHeight());
    }

    /* renamed from: a */
    private final int m908a(int i) {
        int childCount = getChildCount();
        while (i < childCount) {
            if (getChildAt(i).getVisibility() == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /* renamed from: a */
    private final boolean m910a() {
        return getOrientation() == 1;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004e, code lost:
        if (r1 != false) goto L_0x0050;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            int r0 = android.view.View.MeasureSpec.getSize(r6)
            boolean r1 = r5.f947a
            r2 = 0
            if (r1 == 0) goto L_0x0018
            int r1 = r5.f948b
            if (r0 <= r1) goto L_0x0016
            boolean r1 = r5.m910a()
            if (r1 == 0) goto L_0x0016
            r5.m909a((boolean) r2)
        L_0x0016:
            r5.f948b = r0
        L_0x0018:
            boolean r1 = r5.m910a()
            r3 = 1
            if (r1 != 0) goto L_0x0030
            int r1 = android.view.View.MeasureSpec.getMode(r6)
            r4 = 1073741824(0x40000000, float:2.0)
            if (r1 != r4) goto L_0x002f
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r1)
            r1 = 1
            goto L_0x0032
        L_0x002f:
        L_0x0030:
            r0 = r6
            r1 = 0
        L_0x0032:
            super.onMeasure(r0, r7)
            boolean r0 = r5.f947a
            if (r0 == 0) goto L_0x004e
            boolean r0 = r5.m910a()
            if (r0 != 0) goto L_0x004e
            int r0 = r5.getMeasuredWidthAndState()
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r0 = r0 & r4
            r4 = 16777216(0x1000000, float:2.3509887E-38)
            if (r0 != r4) goto L_0x004e
            r5.m909a((boolean) r3)
            goto L_0x0050
        L_0x004e:
            if (r1 == 0) goto L_0x0053
        L_0x0050:
            super.onMeasure(r6, r7)
        L_0x0053:
            int r6 = r5.m908a((int) r2)
            if (r6 < 0) goto L_0x00a1
            android.view.View r7 = r5.getChildAt(r6)
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r0 = (android.widget.LinearLayout.LayoutParams) r0
            int r1 = r5.getPaddingTop()
            int r7 = r7.getMeasuredHeight()
            int r1 = r1 + r7
            int r7 = r0.topMargin
            int r1 = r1 + r7
            int r7 = r0.bottomMargin
            int r2 = r1 + r7
            boolean r7 = r5.m910a()
            if (r7 == 0) goto L_0x009b
            int r6 = r6 + r3
            int r6 = r5.m908a((int) r6)
            if (r6 < 0) goto L_0x00a2
            android.view.View r6 = r5.getChildAt(r6)
            int r6 = r6.getPaddingTop()
            android.content.res.Resources r7 = r5.getResources()
            android.util.DisplayMetrics r7 = r7.getDisplayMetrics()
            float r7 = r7.density
            r0 = 1098907648(0x41800000, float:16.0)
            float r7 = r7 * r0
            int r7 = (int) r7
            int r6 = r6 + r7
            int r2 = r2 + r6
            goto L_0x00a3
        L_0x009b:
            int r6 = r5.getPaddingBottom()
            int r2 = r2 + r6
            goto L_0x00a3
        L_0x00a1:
        L_0x00a2:
        L_0x00a3:
            int r6 = p000.C0340mj.m14719j(r5)
            if (r6 == r2) goto L_0x00ac
            r5.setMinimumHeight(r2)
        L_0x00ac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.ButtonBarLayout.onMeasure(int, int):void");
    }

    /* renamed from: a */
    private final void m909a(boolean z) {
        int i;
        setOrientation(z ? 1 : 0);
        if (!z) {
            i = 80;
        } else {
            i = 5;
        }
        setGravity(i);
        View findViewById = findViewById(R.id.spacer);
        if (findViewById != null) {
            findViewById.setVisibility(!z ? 4 : 8);
        }
        for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
            bringChildToFront(getChildAt(childCount));
        }
    }
}
