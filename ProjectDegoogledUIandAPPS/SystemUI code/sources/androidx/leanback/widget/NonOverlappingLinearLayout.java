package androidx.leanback.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;

public class NonOverlappingLinearLayout extends LinearLayout {
    boolean mDeferFocusableViewAvailableInLayout;
    boolean mFocusableViewAvailableFixEnabled;
    final ArrayList<ArrayList<View>> mSortedAvailableViews;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public NonOverlappingLinearLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public NonOverlappingLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NonOverlappingLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFocusableViewAvailableFixEnabled = false;
        this.mSortedAvailableViews = new ArrayList<>();
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    protected void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        /*
            r4 = this;
            r0 = 0
            boolean r1 = r4.mFocusableViewAvailableFixEnabled     // Catch:{ all -> 0x009e }
            r2 = 1
            if (r1 == 0) goto L_0x0014
            int r1 = r4.getOrientation()     // Catch:{ all -> 0x009e }
            if (r1 != 0) goto L_0x0014
            int r1 = r4.getLayoutDirection()     // Catch:{ all -> 0x009e }
            if (r1 != r2) goto L_0x0014
            r1 = r2
            goto L_0x0015
        L_0x0014:
            r1 = r0
        L_0x0015:
            r4.mDeferFocusableViewAvailableInLayout = r1     // Catch:{ all -> 0x009e }
            boolean r1 = r4.mDeferFocusableViewAvailableInLayout     // Catch:{ all -> 0x009e }
            if (r1 == 0) goto L_0x004b
        L_0x001b:
            java.util.ArrayList<java.util.ArrayList<android.view.View>> r1 = r4.mSortedAvailableViews     // Catch:{ all -> 0x009e }
            int r1 = r1.size()     // Catch:{ all -> 0x009e }
            int r3 = r4.getChildCount()     // Catch:{ all -> 0x009e }
            if (r1 <= r3) goto L_0x0034
            java.util.ArrayList<java.util.ArrayList<android.view.View>> r1 = r4.mSortedAvailableViews     // Catch:{ all -> 0x009e }
            java.util.ArrayList<java.util.ArrayList<android.view.View>> r3 = r4.mSortedAvailableViews     // Catch:{ all -> 0x009e }
            int r3 = r3.size()     // Catch:{ all -> 0x009e }
            int r3 = r3 - r2
            r1.remove(r3)     // Catch:{ all -> 0x009e }
            goto L_0x001b
        L_0x0034:
            java.util.ArrayList<java.util.ArrayList<android.view.View>> r1 = r4.mSortedAvailableViews     // Catch:{ all -> 0x009e }
            int r1 = r1.size()     // Catch:{ all -> 0x009e }
            int r2 = r4.getChildCount()     // Catch:{ all -> 0x009e }
            if (r1 >= r2) goto L_0x004b
            java.util.ArrayList<java.util.ArrayList<android.view.View>> r1 = r4.mSortedAvailableViews     // Catch:{ all -> 0x009e }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x009e }
            r2.<init>()     // Catch:{ all -> 0x009e }
            r1.add(r2)     // Catch:{ all -> 0x009e }
            goto L_0x0034
        L_0x004b:
            super.onLayout(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x009e }
            boolean r5 = r4.mDeferFocusableViewAvailableInLayout     // Catch:{ all -> 0x009e }
            if (r5 == 0) goto L_0x0081
            r5 = r0
        L_0x0053:
            java.util.ArrayList<java.util.ArrayList<android.view.View>> r6 = r4.mSortedAvailableViews     // Catch:{ all -> 0x009e }
            int r6 = r6.size()     // Catch:{ all -> 0x009e }
            if (r5 >= r6) goto L_0x0081
            r6 = r0
        L_0x005c:
            java.util.ArrayList<java.util.ArrayList<android.view.View>> r7 = r4.mSortedAvailableViews     // Catch:{ all -> 0x009e }
            java.lang.Object r7 = r7.get(r5)     // Catch:{ all -> 0x009e }
            java.util.ArrayList r7 = (java.util.ArrayList) r7     // Catch:{ all -> 0x009e }
            int r7 = r7.size()     // Catch:{ all -> 0x009e }
            if (r6 >= r7) goto L_0x007e
            java.util.ArrayList<java.util.ArrayList<android.view.View>> r7 = r4.mSortedAvailableViews     // Catch:{ all -> 0x009e }
            java.lang.Object r7 = r7.get(r5)     // Catch:{ all -> 0x009e }
            java.util.ArrayList r7 = (java.util.ArrayList) r7     // Catch:{ all -> 0x009e }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ all -> 0x009e }
            android.view.View r7 = (android.view.View) r7     // Catch:{ all -> 0x009e }
            super.focusableViewAvailable(r7)     // Catch:{ all -> 0x009e }
            int r6 = r6 + 1
            goto L_0x005c
        L_0x007e:
            int r5 = r5 + 1
            goto L_0x0053
        L_0x0081:
            boolean r5 = r4.mDeferFocusableViewAvailableInLayout
            if (r5 == 0) goto L_0x009d
            r4.mDeferFocusableViewAvailableInLayout = r0
        L_0x0087:
            java.util.ArrayList<java.util.ArrayList<android.view.View>> r5 = r4.mSortedAvailableViews
            int r5 = r5.size()
            if (r0 >= r5) goto L_0x009d
            java.util.ArrayList<java.util.ArrayList<android.view.View>> r5 = r4.mSortedAvailableViews
            java.lang.Object r5 = r5.get(r0)
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            r5.clear()
            int r0 = r0 + 1
            goto L_0x0087
        L_0x009d:
            return
        L_0x009e:
            r5 = move-exception
            boolean r6 = r4.mDeferFocusableViewAvailableInLayout
            if (r6 == 0) goto L_0x00bb
            r4.mDeferFocusableViewAvailableInLayout = r0
        L_0x00a5:
            java.util.ArrayList<java.util.ArrayList<android.view.View>> r6 = r4.mSortedAvailableViews
            int r6 = r6.size()
            if (r0 >= r6) goto L_0x00bb
            java.util.ArrayList<java.util.ArrayList<android.view.View>> r6 = r4.mSortedAvailableViews
            java.lang.Object r6 = r6.get(r0)
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            r6.clear()
            int r0 = r0 + 1
            goto L_0x00a5
        L_0x00bb:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.NonOverlappingLinearLayout.onLayout(boolean, int, int, int, int):void");
    }

    public void focusableViewAvailable(View view) {
        int i;
        if (this.mDeferFocusableViewAvailableInLayout) {
            View view2 = view;
            while (true) {
                if (view2 == this || view2 == null) {
                    i = -1;
                } else if (view2.getParent() == this) {
                    i = indexOfChild(view2);
                    break;
                } else {
                    view2 = (View) view2.getParent();
                }
            }
            if (i != -1) {
                this.mSortedAvailableViews.get(i).add(view);
                return;
            }
            return;
        }
        super.focusableViewAvailable(view);
    }
}
